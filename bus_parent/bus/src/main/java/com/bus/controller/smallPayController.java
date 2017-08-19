package com.bus.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashSet;
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
import com.bus.vo.Constant;
import com.bus.vo.PayVO;
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

/**
 * 微视频支付
 * @author xumengsi
 *
 */
@Controller  
@RequestMapping("/smallPay") 
public class smallPayController {
	
	@Resource 
	private IWeiXinPayService weiXinPayService;
	@Resource 
	private IWeiXinScanService weiXinScanService;
	
	private static Set<String> expireKey = new HashSet<String>();
	
	/**
	 * 微信授权ֵ
	 * @param imsi
	 * @param phone
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/playSmallVideo", method = RequestMethod.GET) 
	public void toXcc(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/**
		 * 获取code
		 */
		String backUri = Constant.WEIXIN_REDIRECT_URI_SMALLPAY;
		backUri = URLEncoder.encode(backUri);
		//scope=snsapi_userinfo：只用来获取openid
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_userinfo&state=1231#wechat_redirect";
		response.sendRedirect(url);
	};
	
	
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temp", method = RequestMethod.GET)
	public String  temp(HttpServletRequest request,Model model){
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    request.setAttribute("openid", openid);
	    APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
	    User user = UserAPI.userInfo(tokens.getAccess_token(),openid);
	    if(user!=null && user.getNickname()!=null){
	    	request.setAttribute("nickname", user.getNickname());
	    };
	    if(user!=null && user.getHeadimgurl()!=null){
	    	request.setAttribute("headimgurl", user.getHeadimgurl());
	    }
		return "video/video";
	}
	
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
		String packagePrice = "0.01";
		UnifiedorderResult payReturn = payUtil(request,response,orderNo,openid,packagePrice);
		//得到支付权限 调用H5支付
		String json = PayUtil.generateMchPayJsRequestJson(payReturn.getPrepay_id(), Constant.WEIXIN_APPID, Constant.WEIXIN_PAY_KEY);
			vo.setJson(json);
			vo.setPackagePrice(packagePrice);
			vo.setOrderNo(orderNo);
			vo.setOpenid(openid);
		return vo;
	};
	
	
	/**
	 * 统一下单工具类
	 * @param request
	 * @param response
	 * @param orderNo
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public  UnifiedorderResult payUtil(HttpServletRequest request, HttpServletResponse response,String orderNo,String openid,String packagePrice) throws Exception{
		String money = packagePrice;
		 UUID uuid = UUID.randomUUID();
		String playUrl = uuid.toString();
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
	    String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;
		String body = "充值";
		String attach = playUrl;
		String out_trade_no = orderNo;
		int total_fee = Integer.parseInt(finalmoney);
		String spbill_create_ip = request.getRemoteAddr();
		//微信支付的回调通知注意不能带参数
		String notify_url ="http://www.xccnet.com/bus/smallPay/payStatus";
		String trade_type="";
		WeiXinPayVO vo = new WeiXinPayVO();
		trade_type = "JSAPI";
		vo.setOpenid(openid);
		vo.setAppid(Constant.WEIXIN_APPID);
		vo.setMch_id(Constant.WEIXIN_MCH_ID);
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
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}else{
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR");
			//改变订单状态（支付失败）
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}
	};

}
