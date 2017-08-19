package com.bus.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.api.util.PayUtil;
import com.bus.api.util.SignatureUtil;
import com.bus.api.util.StreamUtils;
import com.bus.api.util.XMLConverUtil;
import com.bus.core.util.TenpayUtil;
import com.bus.iot.api.iotApi;
import com.bus.iot.vo.iotParamVO;
import com.bus.iot.vo.iotResultVO;
import com.bus.iot.vo.resultJsonVO;
import com.bus.vo.Constant;
import com.bus.vo.PackageVO;
import com.bus.vo.PayVO;
import com.bus.vo.UserPackageVO;
import com.bus.vo.WeiXinPayVO;
import com.bus.web.services.IWeiXinPayService;
import com.bus.web.services.IWeiXinScanService;
import com.bus.weixin.api.APPTokenAPI;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.api.UserAPI;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.MchBaseResult;
import com.bus.weixin.vo.MchPayNotify;
import com.bus.weixin.vo.SnsToken;
import com.bus.weixin.vo.UnifiedorderResult;
import com.bus.weixin.vo.User;
import com.bus.weixin.vo.jsapiVO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


@Controller  
@RequestMapping("/weiXinScan") 
public class weiXinScanController {
	
	@Resource 
	private IWeiXinScanService weiXinScanService;
	@Resource 
	private IWeiXinPayService weiXinPayService;
	
    private static Set<String> expireKey = new HashSet<String>();
    
    public static String accessToken = null;
    
	//public static String url = "http://www.xccnet.com/bus/weiXinScan/createQRCode";
	
	/**
	 * 微信授权ֵ
	 * @param imsi
	 * @param phone
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/topayPackage", method = RequestMethod.GET) 
	public void toXcc(
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String imsi = request.getParameter("imsi");
		/**
		 * 获取code
		 */
		String backUri = Constant.WEIXIN_REDIRECT_URI_PAY;
		backUri = backUri+"?imsi="+imsi;
		backUri = URLEncoder.encode(backUri);
		//scope=snsapi_userinfo：只用来获取openid
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_userinfo&state=1231#wechat_redirect";
		response.sendRedirect(url);
	};
	
	
	/**
	 * 跳转到套餐购买页面；保护code参数
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temp", method = RequestMethod.GET)
	public String  temp(HttpServletRequest request,Model model){
		String code = request.getParameter("code");
		String imsi = request.getParameter("imsi");
		//String phone = request.getParameter("phone");
		request.setAttribute("imsi", imsi);
		//request.setAttribute("phone", phone);
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    request.setAttribute("openid", openid);
	    APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
	    User user = UserAPI.userInfo(tokens.getAccess_token(),openid);
	    /*if(user.getSubscribe()==0){
		    //	model.addAttribute("errorMsg", "请关注小车车微信公众号");
	    	return  "registerError";
		    };*/
	    if(user!=null && user.getNickname()!=null){
	    	request.setAttribute("nickname", user.getNickname());
	    };
	    if(user!=null && user.getHeadimgurl()!=null){
	    	request.setAttribute("headimgurl", user.getHeadimgurl());
	    }
		return "payPackage";
	}
	
	
	/**
	 * 用户支付失败后的跳转
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/topayPackageTemp", method = RequestMethod.GET) 
	 public String topayPackageTemp(HttpServletRequest request,Model model) throws IOException{
		    String openid = request.getParameter("openid");
			String nickname = request.getParameter("nickname");
			String headimgurl = request.getParameter("headimgurl");
			String imsi = request.getParameter("imsi");
			 request.setAttribute("openid", openid);
			 request.setAttribute("nickname", nickname);
			 request.setAttribute("headimgurl", headimgurl);
			 request.setAttribute("imsi", imsi);
			return "payPackage";		
	};
			
	
	/**
	 * 微信支付
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.POST) 
	@ResponseBody
	public PayVO pay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PayVO vo = new PayVO();
		String orderNo =  weiXinPayService.getOrderNo();//订单号
		String openid = request.getParameter("openid");
		UnifiedorderResult payReturn = payUtil(request,response,orderNo,openid);
		//得到支付权限 调用H5支付
		String json = PayUtil.generateMchPayJsRequestJson(payReturn.getPrepay_id(), Constant.WEIXIN_APPID, Constant.WEIXIN_PAY_KEY);
		 String packageType = request.getParameter("packageType");
		 String imsi_id = request.getParameter("imsi");
		 String packagePrice = request.getParameter("packagePrice");
			vo.setJson(json);
			vo.setImsi(imsi_id);
			vo.setPackageType(packageType);
			vo.setPackagePrice(packagePrice);
			vo.setOrderNo(orderNo);
			vo.setOpenid(openid);
		//下单
		weiXinScanService.playOrder(imsi_id,packageType,packagePrice,orderNo.toString());
		return vo;
	};
	
	
	/**
	 * 代付人的微信支付
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/otherPayPackage", method = RequestMethod.POST) 
	@ResponseBody
	public PayVO otherPayPackage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PayVO vo = new PayVO();
		String orderNo =  weiXinPayService.getOrderNo();//订单号
		//代付人的openid
		String openid = request.getParameter("openid");
		//發起代付的人的昵称
		String nickname = request.getParameter("nickname");
		//發起代付的人的图像
		String headimgurl = request.getParameter("headimgurl");
		//代付人的昵称
		String otherNickname = request.getParameter("otherNickname");
		//代付人的图像
		String otherHeadimgurl = request.getParameter("otherHeadimgurl");
		//支付方式
		String payModels = request.getParameter("payModel");
		UnifiedorderResult payReturn = payUtil(request,response,orderNo,openid);
		//得到支付权限 调用H5支付
		String json = PayUtil.generateMchPayJsRequestJson(payReturn.getPrepay_id(), Constant.WEIXIN_APPID, Constant.WEIXIN_PAY_KEY);
		 String packageType = request.getParameter("packageType");
		 String imsi_id = request.getParameter("imsi");
		 String packagePrice = request.getParameter("packagePrice");
		vo.setJson(json);
		vo.setImsi(imsi_id);
		vo.setPackageType(packageType);
		vo.setPackagePrice(packagePrice);
		vo.setOrderNo(orderNo);
		vo.setNickname(nickname);
		vo.setHeadimgurl(headimgurl);
		vo.setOtherNickname(otherNickname);
		vo.setOtherHeadimgurl(otherHeadimgurl);
		vo.setPayModel(payModels);
		vo.setOpenid(openid);
		//代付下单
		weiXinScanService.otherPlayOrder(imsi_id,packageType,packagePrice,orderNo.toString(),openid);
		//request.getRequestDispatcher("/WEB-INF/jsp/pay.jsp").forward(request,response);
		return vo;
	};
	
	/**
	 * 找人支付
	 * 进入代付分享页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createQRCode", method = RequestMethod.POST)  
	public String createQRCode(HttpServletRequest request,Model model) throws Exception{
		String nickname = request.getParameter("nickname");
		String headimgurl = request.getParameter("headimgurl");
		String packageType = request.getParameter("packageType");
		String openid = request.getParameter("openid");
		String imsi = request.getParameter("imsi");
		//代付人的微信昵称
		model.addAttribute("nickname", nickname);
		//代付人的微信图像
		model.addAttribute("headimgurl", headimgurl);
		//代付套餐类型
		model.addAttribute("packageType", packageType);
		//区分代付人和发起代付的人
		model.addAttribute("openid", openid);
		model.addAttribute("imsi", imsi);
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		//代付时间
		model.addAttribute("paidTime", dateFormater.format(date));
		 APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
			accessToken = tokens.getAccess_token();
			jsapiVO jsapi =  SnsAPI.getjsapi(accessToken);
			String jsapi_ticket = jsapi.getTicket(); 
	        String nonce_str = create_nonce_str();  
	        String timestamp = create_timestamp();  
	        String string1;  
	        String signature = ""; 
	        //注意这里参数名必须全部小写，且必须有序  
	        string1 ="jsapi_ticket="+jsapi_ticket+
	        		 "&noncestr="+nonce_str +
	                 "&timestamp=" +timestamp+
	                 "&url=" + Constant.WEIXIN_WEIXINSCAN_URL; 
	        System.out.println("string1="+string1);  
	        try  
	        {  
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");  
	            crypt.reset();  
	            crypt.update(string1.getBytes("UTF-8"));  
	            signature = byteToHex(crypt.digest());  
	        }  
	        catch (NoSuchAlgorithmException e)  
	        {  
	            e.printStackTrace();  
	        }  
	        catch (UnsupportedEncodingException e)  
	        {  
	            e.printStackTrace();  
	        }  
	  
	        model.addAttribute("url", Constant.WEIXIN_WEIXINSCAN_URL);  
	        model.addAttribute("jsapi_ticket", jsapi_ticket);  
	        model.addAttribute("nonceStr", nonce_str);  
	        model.addAttribute("timestamp", timestamp);  
	        model.addAttribute("signature", signature);  
	        model.addAttribute("appId", Constant.WEIXIN_APPID); 
	   return "paidMsg";
};

/**
 * 授权进入代付页面
 * 微信授权准备支付
 * @param request
 * @param response
 * @throws IOException 
 */
@RequestMapping(value = "/otherPay", method = RequestMethod.GET)
public void otherPay(HttpServletRequest request, HttpServletResponse response) throws IOException{
	String nickname = request.getParameter("nickname");
	String headimgurl = request.getParameter("headimgurl");
	String packageType = request.getParameter("packageType");
	String openid = request.getParameter("openid");
	String paidTime = request.getParameter("paidTime");
	String imsi = request.getParameter("imsi");
    //代付授权；获取code
	String backUri = Constant.WEIXIN_REDIRECT_URI_PAID;
	backUri = backUri+"?nickname="+nickname+
						"&headimgurl="+headimgurl+
						"&packageType="+packageType+
						"&openid="+openid+
						"&paidTime="+paidTime+
						"&imsi="+imsi;
	backUri = URLEncoder.encode(backUri);
	String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_userinfo&state=1231#wechat_redirect";
	response.sendRedirect(url);
}

		/**
		 * 授权之后获取代付用户信息
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/paid", method = RequestMethod.GET)
		public String  paid(HttpServletRequest request,Model model){
		String code = request.getParameter("code");
		String nickname = request.getParameter("nickname");
		String headimgurl = request.getParameter("headimgurl");
		String packageType = request.getParameter("packageType");
		String imsi = request.getParameter("imsi");
		//String openid = request.getParameter("openid");
		String paidTime = request.getParameter("paidTime");
		request.setAttribute("nickname", nickname);
		request.setAttribute("headimgurl", headimgurl);
		request.setAttribute("packageType", packageType);
		request.setAttribute("paidTime", paidTime);
		request.setAttribute("imsi", imsi);
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
		String openid = token.getOpenid();
		request.setAttribute("openid", openid);
		APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
		User user = UserAPI.userInfo(tokens.getAccess_token(),openid);
		if(user.getNickname()!=null){
			request.setAttribute("otherNickname", user.getNickname());
		};
		if(user.getHeadimgurl()!=null){
			request.setAttribute("otherHeadimgurl", user.getHeadimgurl());
		};
		return "otherPayPackage";
		}
		
		/**
		 * 用户代付失败后的跳转
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		@RequestMapping(value = "/toOtherPayPackageTemp", method = RequestMethod.GET) 
		 public String toOtherPayPackageTemp(HttpServletRequest request,Model model) throws IOException{
			String packageType = request.getParameter("packageType");
			String imsi = request.getParameter("imsi");
			String nickname = request.getParameter("nickname");
			String headimgurl = request.getParameter("headimgurl");
			String openid = request.getParameter("openid");
			String paidTime = request.getParameter("paidTime");
			String otherNickname = request.getParameter("otherNickname");
			String otherHeadimgurl = request.getParameter("otherHeadimgurl");
			model.addAttribute("packageType", packageType);
			model.addAttribute("imsi", imsi);
			model.addAttribute("nickname", nickname);
			model.addAttribute("headimgurl", headimgurl);
			model.addAttribute("openid", openid);
			model.addAttribute("paidTime", paidTime);
			model.addAttribute("otherNickname", otherNickname);
			model.addAttribute("otherHeadimgurl", otherHeadimgurl);
			return "otherPayPackage";		
		};
		
		
		@RequestMapping(value = "/getPackageMsg", method = RequestMethod.GET)
		@ResponseBody
		public PackageVO  getPackageMsg(HttpServletRequest request, HttpServletResponse response){
			String packageType = request.getParameter("packageType");
			return weiXinScanService.getPackageMsg(packageType);
		}
	
    /**
     * 统一下单工具类
     * @param request
     * @param response
     * @param orderNo
     * @param openid
     * @return
     * @throws Exception
     */
	public  UnifiedorderResult payUtil(HttpServletRequest request, HttpServletResponse response,String orderNo,String openid) throws Exception{
		String payModel = request.getParameter("payModel");
		//String code = request.getParameter("code");
	    String imsi = request.getParameter("imsi");
	   // String phone = request.getParameter("phone");
	    String money = request.getParameter("packagePrice");
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
	    String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;
		String body = "小车车-会员充值";
		String attach = imsi;
		String out_trade_no = orderNo;
		int total_fee = Integer.parseInt(finalmoney);
		String spbill_create_ip = request.getRemoteAddr();
		//微信支付的回调通知注意不能带参数
		String notify_url ="http://www.xccnet.com/bus/weiXinScan/payStatus";
		String trade_type="";
		//String product_id = null;
		WeiXinPayVO vo = new WeiXinPayVO();
		trade_type = "JSAPI";
		vo.setOpenid(openid);
		/**
		 * 判断支付方式
		 */
		/*if(Integer.parseInt(payModel)==1){
			 trade_type = "JSAPI";
			 vo.setOpenid(openid);
		}else{
			 product_id = imsi ;
			 trade_type = "NATIVE";
		}*/
		vo.setAppid(Constant.WEIXIN_APPID);
		vo.setMch_id(Constant.WEIXIN_MCH_ID);
		/*if(product_id!=null){
			vo.setProduct_id(product_id);
		};*/
		vo.setNonce_str(nonce_str);
		vo.setBody(body);
		vo.setAttach(attach);
		vo.setOut_trade_no(out_trade_no);
		vo.setTotal_fee(total_fee);
		vo.setSpbill_create_ip(spbill_create_ip);
		vo.setNotify_url(notify_url);
		vo.setTrade_type(trade_type);
		UnifiedorderResult payResult = weiXinPayService.pay(vo, Constant.WEIXIN_PAY_KEY);
	    return payResult ;
	};
	
	/**
	 * 微信回调支付通知
	 * 主要作用服务器表示接受成功，避免微信一直发送 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/payStatus", method = RequestMethod.POST)
	public void  test(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String xmlData = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
		Map<String,String> mapData = XMLConverUtil.convertToMap(xmlData);
		MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class,xmlData);
		if(expireKey.contains(payNotify.getTransaction_id())){
			return;
		}
		if(SignatureUtil.validateSign(mapData,Constant.WEIXIN_PAY_KEY)){
			expireKey.add(payNotify.getTransaction_id());
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("SUCCESS");
			baseResult.setReturn_msg("OK");
			//改变订单状态（支付成功）
			String orderNo = payNotify.getOut_trade_no();
			String imsi_id = payNotify.getAttach();
			weiXinScanService.editOrderNo(orderNo,imsi_id);
			//查询该卡是否停机
			String transId = UUID.randomUUID().toString();
			iotParamVO paramVO = new iotParamVO();
			paramVO.setColumn_name(Constant.IOT_ICCID);
			paramVO.setColumn_value(imsi_id);
			paramVO.setUserId(Constant.IOT_USER_ID);
			paramVO.setUserPwd(Constant.IOT_USER_PWD);
			paramVO.setTransId(transId);
			iotResultVO iotResult = iotApi.findIotStatus(paramVO);
			resultJsonVO resultJson = iotResult.getResult();
			if(resultJson.getStatus()!=null && "02".equals(resultJson.getStatus())){//调用开卡
				String transId1 = UUID.randomUUID().toString();
				iotParamVO paramVO1 = new iotParamVO();
				paramVO1.setColumn_name(Constant.IOT_ICCID);
				paramVO1.setColumn_value(imsi_id);
				paramVO1.setUser_status(Constant.IOT_START);
				paramVO1.setUserId(Constant.IOT_USER_ID);
				paramVO1.setUserPwd(Constant.IOT_USER_PWD);
				paramVO1.setTransId(transId1);
				iotApi.editIotStatus(paramVO1);
			};
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}else{
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR");
			//改变订单状态（支付失败）
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}
	};
	
	/**
	 * 生成找人支付的二维码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/createQRCodeByUrl", method = RequestMethod.GET)
	public void  createQRCodeByUrl(HttpServletRequest request, HttpServletResponse response){
		String code_url = request.getParameter("payUrl");
	    try {  
	        int width =  280;  
	        int height = 280;  
	        String format = "png";  
	        Hashtable hints = new Hashtable();  
	        hints.put(EncodeHintType.MARGIN, 1);
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(code_url, BarcodeFormat.QR_CODE, width, height, hints);  
	        int[] rec = bitMatrix.getEnclosingRectangle();  
			int resWidth = rec[2] + 1;  
			int resHeight = rec[3] + 1;  
			BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
			resMatrix.clear();  
			for (int i = 0; i < resWidth; i++) {  
			    for (int j = 0; j < resHeight; j++) {  
			        if (bitMatrix.get(i + rec[0], j + rec[1])) { 
			             resMatrix.set(i, j); 
			        } 
			    }  
			}  
	        OutputStream out = null;  
	        out = response.getOutputStream();  
	        MatrixToImageWriter.writeToStream(bitMatrix, format, out);  
	        out.flush();  
	        out.close();  
	    } catch (Exception e) {  
	    }
	}
	
	/**
	 * ֧支付成功 处理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/paySuccess", method = RequestMethod.GET)
	public String paySuccess(HttpServletRequest request,Model model){
		String imsi_id = request.getParameter("imsi_id");
		String packageType = request.getParameter("packageType");
		String packagePrice = request.getParameter("packagePrice");
		String orderNo = request.getParameter("orderNo");
		//weiXinScanService.paySuccess(imsi_id,packageType,packagePrice,orderNo);
		//model.addAttribute("UserPackageVO", vo);
		model.addAttribute("imsi_id", imsi_id);
		model.addAttribute("packageType", packageType);
		model.addAttribute("packagePrice", packagePrice);
		model.addAttribute("orderNo", orderNo);
		return "paySuccess";
	}
	
	@RequestMapping(value = "/otherPaySuccess", method = RequestMethod.GET)
	public String otherPaySuccess(HttpServletRequest request,Model model){
		String imsi_id = request.getParameter("imsi_id");
		String packageType = request.getParameter("packageType");
		String packagePrice = request.getParameter("packagePrice");
		String orderNo = request.getParameter("orderNo");
		String payModels = request.getParameter("payModels");
		String openid = request.getParameter("openid");
		String nickname = request.getParameter("nickname");
		String headimgurl = request.getParameter("headimgurl");
		String otherNickname = request.getParameter("otherNickname");
		String otherHeadimgurl = request.getParameter("otherHeadimgurl");
		//weiXinScanService.otherPaySuccess(imsi_id,packageType,packagePrice,orderNo,openid);
		//model.addAttribute("UserPackageVO", vo);
		model.addAttribute("packageType", packageType);
		model.addAttribute("packagePrice", packagePrice);
		model.addAttribute("nickname", nickname);
		model.addAttribute("headimgurl", headimgurl);
		model.addAttribute("otherNickname", otherNickname);
		model.addAttribute("otherHeadimgurl", otherHeadimgurl);
		return "otherPaySuccess";
	}
	
	
	
	/**
	 * ֧支付失败处理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/payError", method = RequestMethod.GET)
	public String payError(HttpServletRequest request,Model model){
		return "payError";
	}
	
	/**
	 * 获取套餐列表
	 * @return
	 */
	@RequestMapping(value = "/getAllPackage", method = RequestMethod.GET)
	@ResponseBody
	public  List<PackageVO>  getAllPackage(){
		return weiXinScanService.getAllPackage();
	}
	
	
	
	/**
	 * 得到用户当前订购的套餐
	 * @return
	 */
	@RequestMapping(value = "/getUserPackageMsg", method = RequestMethod.GET)
	@ResponseBody
	public  UserPackageVO  getUserPackageMsg(HttpServletRequest request, HttpServletResponse response){
		String imsi_id = request.getParameter("imsi_id");
		String packageType = request.getParameter("packageType");
		return weiXinScanService.getUserPackageMsg(imsi_id,packageType);
	}
	
	
	/**
	 * 查询代付时间
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getOtherTime", method = RequestMethod.GET)
	@ResponseBody
	public  Date  getOtherTime(HttpServletRequest request, HttpServletResponse response){
		String orderNo = request.getParameter("orderNo");
		return weiXinScanService.getOtherTime(orderNo);
	}
	
	/** 
     * 随机加密 
     * @param hash 
     * @return 
     */  
    private static String byteToHex(final byte[] hash) {  
        Formatter formatter = new Formatter();  
        for (byte b : hash)  
        {  
            formatter.format("%02x", b);  
        }  
        String result = formatter.toString();  
        formatter.close();  
        return result;  
    }  
	
	
	/** 
     * 产生随机串--由程序自己随机产生 
     * @return 
     */  
    private static String create_nonce_str() {  
        return UUID.randomUUID().toString();  
    }  
  
    /** 
     * 由程序自己获取当前时间 
     * @return 
     */  
    private static String create_timestamp() {  
        return Long.toString(System.currentTimeMillis() / 1000);  
    }
	
}
