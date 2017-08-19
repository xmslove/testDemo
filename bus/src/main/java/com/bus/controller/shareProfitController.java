package com.bus.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.baidu.api.BaiDuApi;
import com.bus.baidu.vo.BaiDuVO;
import com.bus.baidu.vo.geoconyVO;
import com.bus.baidu.vo.resultVO;
import com.bus.exception.UnifiedException;
import com.bus.util.JsSDKVO;
import com.bus.util.WeinXinJSSDKUtil;
import com.bus.vo.Constant;
import com.bus.vo.MchMsgVO;
import com.bus.vo.RoundCarVO;
import com.bus.vo.StaffMsgVO;
import com.bus.web.services.IActivationSerivce;
import com.bus.web.services.IShareProfitSerivce;
import com.bus.weixin.api.APPTokenAPI;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.api.UserAPI;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.SnsToken;
import com.bus.weixin.vo.User;
/**
 * 分利
 * @author xms
 *
 */
@Controller  
@RequestMapping("/shareProfit") 
public class shareProfitController {
	@Resource 
	private IActivationSerivce activationSerivce;
	@Resource 
	private IShareProfitSerivce shareProfitSerivce;
	
	public static String accessToken = null;
	
/**
 * 销售或者商家扫一扫去注册 
 * 微信授权	
 * @param request
 * @param model
 * @return
 * @throws IOException 
 */
@RequestMapping(value = "/shareProfitRegister", method = RequestMethod.GET) 
public void shareProfitRegister(HttpServletRequest request, HttpServletResponse response) throws IOException{
	String brandNo = request.getParameter("brandNo");
	//model.addAttribute("brandNo", brandNo);
	/**
	 * 获取code
	 */
	String backUri = Constant.WEIXIN_REDIRECT_URI_MCH;
	backUri = backUri+"?brandNo="+brandNo;
	backUri = URLEncoder.encode(backUri);
	//scope=snsapi_userinfo：只用来获取openid
	String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_userinfo&state=1231#wechat_redirect";
	response.sendRedirect(url);
	//return "shareProfit/shareProfitRegister";
}

@RequestMapping(value = "/temp", method = RequestMethod.GET)
public String  temp(HttpServletRequest request,Model model){
	String code = request.getParameter("code");
	String brandNo = request.getParameter("brandNo");
	SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
    String openid = token.getOpenid();
    model.addAttribute("brandNo", brandNo);
    model.addAttribute("openid", openid);
	return "shareProfit/shareProfitRegister";
}


/**
 * 获取商户openid
 * 判断商户是否注册
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/mchAuthorize", method = RequestMethod.GET) 
public String mchAuthorize(HttpServletRequest request,Model model){
	String brandNo = request.getParameter("brandNo");
	String openid = request.getParameter("openid");
	//判断商户是否已经注册过
	//Integer flag = shareProfitSerivce.selectMch(brandNo,openid);
	//if(flag==1){//商户已经注册过
		/*MchMsgVO vo = shareProfitSerivce.selectMchMsg(brandNo, openid);
		model.addAttribute("MchMsgVO", vo);
		return "shareProfit/mchLoginMsg";*/
	//}else{
		APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
	    User user = UserAPI.userInfo(tokens.getAccess_token(),openid);
	   if(user.getSubscribe()==0){//没有关注
		   return "shareProfit/followInterest";//去关注页面
	   }else{
			model.addAttribute("brandNo", brandNo);
			model.addAttribute("openid", openid);
			return "shareProfit/startMchRegister"; 
	   }
	//}
}

//查看商家详情
@RequestMapping(value = "/mchLoginMsg", method = RequestMethod.POST) 
public String mchLoginMsg(HttpServletRequest request,Model model) throws UnifiedException{
	String brandNo = request.getParameter("brandNo");
	String openid = request.getParameter("openid");
	MchMsgVO vo = shareProfitSerivce.selectMchMsg(brandNo, openid);
	model.addAttribute("MchMsgVO", vo);
	JsSDKVO  jsSDKVO = WeinXinJSSDKUtil.getJsSDK(Constant.WEIXIN_SHAREPROFIT_URL4);
    model.addAttribute("url", jsSDKVO.getUrl());  
    model.addAttribute("jsapi_ticket", jsSDKVO.getJsapi_ticket());  
    model.addAttribute("nonceStr", jsSDKVO.getNonceStr());  
    model.addAttribute("timestamp", jsSDKVO.getTimestamp());  
    model.addAttribute("signature", jsSDKVO.getSignature());  
    model.addAttribute("appId", Constant.WEIXIN_APPID);
	
	return "shareProfit/mchLoginMsg";
}


//判断商户是否已经注册过
@RequestMapping(value = "/vildMch", method = RequestMethod.GET) 
@ResponseBody
public Boolean vildMch(HttpServletRequest request,Model model){
	String brandNo = request.getParameter("brandNo");
	String openid = request.getParameter("openid");
	//判断商户是否已经注册过
	Integer flag = shareProfitSerivce.selectMch(brandNo,openid);
	if(flag>=1){//商户已经注册过
		return true;
	}else{
		return false;
	}
}



/**
 * 获取销售openid
 * 判断销售是否注册
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffAuthorize", method = RequestMethod.GET) 
public String staffAuthorize(HttpServletRequest request,Model model){
	String openid = request.getParameter("openid");
	//判断销售是否已经注册过
	//Integer flag = shareProfitSerivce.selectStaffRegister(openid);
	//if(flag>=1){//销售已经注册过(去绑卡页面)
		/*StaffMsgVO vo = shareProfitSerivce.findStaffMsgByOpenid(openid);
		model.addAttribute("openid", openid);
		model.addAttribute("StaffMsgVO", vo);
		WeinXinJSSDKUtil.getJsSDK(url3, model);
		return "shareProfit/StaffTiedCard";*/
	//}else{
		APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
	    User user = UserAPI.userInfo(tokens.getAccess_token(),openid);
	   if(user.getSubscribe()==0){//没有关注
		   return "shareProfit/followInterest";//去关注页面
	   }else{
			model.addAttribute("openid", openid);
			return "shareProfit/startStaffRegister"; 
	   }
	//}
}

/**
 * 去销售注册介绍页面
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/introduceRegister", method = RequestMethod.GET) 
public String introduceRegister(HttpServletRequest request,Model model){
	  String openid = request.getParameter("openid");
	  model.addAttribute("openid", openid);
	 return "shareProfit/introduceRegister"; 
}

/**
 * 销售去绑卡页面
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/toStaffTiedCard", method = RequestMethod.POST) 
public String toStaffTiedCard(HttpServletRequest request,Model model){
	  String openid = request.getParameter("openid");
	  String mchNo = request.getParameter("mchNo");
	  String staffPhone = request.getParameter("staffPhone");
	  String staffName = request.getParameter("staffName");
	  
	  model.addAttribute("openid", openid);
	  model.addAttribute("mchNo", mchNo);
	  model.addAttribute("staffPhone", staffPhone);
	  model.addAttribute("staffName",staffName);
	  
	  StaffMsgVO vo = shareProfitSerivce.findStaffMsgByOpenid(openid);
		model.addAttribute("StaffMsgVO", vo);
		
		    JsSDKVO  jsSDKVO = WeinXinJSSDKUtil.getJsSDK(Constant.WEIXIN_SHAREPROFIT_URL3);
		    model.addAttribute("url", jsSDKVO.getUrl());  
	        model.addAttribute("jsapi_ticket", jsSDKVO.getJsapi_ticket());  
	        model.addAttribute("nonceStr", jsSDKVO.getNonceStr());  
	        model.addAttribute("timestamp", jsSDKVO.getTimestamp());  
	        model.addAttribute("signature", jsSDKVO.getSignature());  
	        model.addAttribute("appId", Constant.WEIXIN_APPID); 
	        
	 return "shareProfit/StaffTiedCard"; 
}

/**
 * 销售已经注册，直接去绑定imsi
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffTiedCard", method = RequestMethod.GET) 
public String staffTiedCard(HttpServletRequest request,Model model){
	//绑定成功跳转页面
	String result = "shareProfit/staffTiedCardSuccess";
	String openid = request.getParameter("openid");
	String imsi = request.getParameter("imsi");
	//判断此卡是否已经激活
		Boolean flag1 = shareProfitSerivce.isValidImsi(imsi);
		if(!flag1){
			model.addAttribute("errorMsgTitle","您无法绑定此SIM卡");
			model.addAttribute("errorMsgDesc","请先协助客户将车载设备的aicar SIM卡激活，激活成功后您可以在进行绑定操作");
			result = "shareProfit/staffTiedCardError";
			return result;
		}
		//判断此卡激活时间是否超过24小时
		Boolean flag2 = shareProfitSerivce.isValidTimeImsi(imsi);
		if(!flag2){
			model.addAttribute("errorMsgTitle","您无法绑定此SIM卡");
			model.addAttribute("errorMsgDesc","很抱歉，SIM卡绑定时间需要在激活成功后24小时内，您已经超过绑定时限");
			result = "shareProfit/staffTiedCardError";
			return result;
		}
    //判断销售是否绑定此卡(自己绑定或者别的销售绑定)
	//查询自己是否绑定
	StaffMsgVO vo1 = shareProfitSerivce.isStaffMeTiedCard(openid,imsi);
	if(vo1!=null){
		model.addAttribute("errorMsgTitle", "您已经绑定过此SIM卡");
		model.addAttribute("errorMsgDesc", "您已经成功绑定过此SIM卡，无需再重复绑定");
		result = "shareProfit/staffTiedCardError";
		return result;
	}
	//查询别人是否绑定
	StaffMsgVO vo2 = shareProfitSerivce.isStaffOtherTiedCard(openid,imsi);
	if(vo2!=null){
		model.addAttribute("errorMsgTitle", "此SIM卡已经被绑定");
		model.addAttribute("errorMsgDesc", "此卡已经被别的销售绑定，无需重复绑定");
		result = "shareProfit/staffTiedCardError";
		return result;
	}
	//销售已经注册，添加绑卡
	shareProfitSerivce.addStaffTiedCard(openid,imsi);
	return result;
}

@RequestMapping(value = "/theRebate", method = RequestMethod.GET) 
public String theRebate(HttpServletRequest request,Model model){
	 return "shareProfit/theRebate"; 
}

/**
 * 去商家注册页面1
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/mchRegister", method = RequestMethod.POST) 
public String mchRegister(HttpServletRequest request,Model model){
	String brandNo = request.getParameter("brandNo");
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	model.addAttribute("brandNo", brandNo);
	
	JsSDKVO  jsSDKVO = WeinXinJSSDKUtil.getJsSDK(Constant.WEIXIN_SHAREPROFIT_URL5);
    model.addAttribute("url", jsSDKVO.getUrl());  
    model.addAttribute("jsapi_ticket", jsSDKVO.getJsapi_ticket());  
    model.addAttribute("nonceStr", jsSDKVO.getNonceStr());  
    model.addAttribute("timestamp", jsSDKVO.getTimestamp());  
    model.addAttribute("signature", jsSDKVO.getSignature());  
    model.addAttribute("appId", Constant.WEIXIN_APPID);
	
	return "shareProfit/mchRegister";
}


/**
 * 去商家注册页面2
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/mchRegister1", method = RequestMethod.POST) 
public String mchRegister1(HttpServletRequest request,Model model){
	String brandNo = request.getParameter("brandNo");
	String mchName = request.getParameter("mchName");
	String mchAddress = request.getParameter("mchAddress");
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	model.addAttribute("brandNo", brandNo);
	model.addAttribute("mchName", mchName);
	model.addAttribute("mchAddress", mchAddress);
	return "shareProfit/mchRegister2";
}


/**
 * 去商家注册页面3
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/mchRegister2", method = RequestMethod.POST) 
public String mchRegister2(HttpServletRequest request,Model model){
	String brandNo = request.getParameter("brandNo");
	String mchName = request.getParameter("mchName");
	String mchAddress = request.getParameter("mchAddress");
	String mchUserName = request.getParameter("mchUserName");
	String mchUserPhone = request.getParameter("mchUserPhone");
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	model.addAttribute("brandNo", brandNo);
	model.addAttribute("mchName", mchName);
	model.addAttribute("mchAddress", mchAddress);
	model.addAttribute("mchUserName", mchUserName);
	model.addAttribute("mchUserPhone", mchUserPhone);
	return "shareProfit/mchRegister3";
}

/**
 * 商家注册提交
 * 微信 js sdk 授权
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/mchRegister3", method = RequestMethod.POST) 
public String mchRegister3(HttpServletRequest request,Model model){
	String brandNo = request.getParameter("brandNo");
	String mchName = request.getParameter("mchName");
	String mchAddress = request.getParameter("mchAddress");
	String mchUserName = request.getParameter("mchUserName");
	String mchUserPhone = request.getParameter("mchUserPhone");
	String openid = request.getParameter("openid");
	String mchNo = shareProfitSerivce.addMch(mchName,mchAddress,mchUserName,mchUserPhone,brandNo,openid);
	model.addAttribute("openid", openid);
	model.addAttribute("mchNo", mchNo);
	model.addAttribute("mchName", mchName);
	model.addAttribute("mchAddress", mchAddress);
	model.addAttribute("mchUserName", mchUserName);
	model.addAttribute("mchUserPhone", mchUserPhone);
	
	 JsSDKVO  jsSDKVO = WeinXinJSSDKUtil.getJsSDK(Constant.WEIXIN_SHAREPROFIT_URL);
	 model.addAttribute("url", jsSDKVO.getUrl());  
     model.addAttribute("jsapi_ticket", jsSDKVO.getJsapi_ticket());  
     model.addAttribute("nonceStr", jsSDKVO.getNonceStr());  
     model.addAttribute("timestamp", jsSDKVO.getTimestamp());  
     model.addAttribute("signature", jsSDKVO.getSignature());  
     model.addAttribute("appId", Constant.WEIXIN_APPID); 
	
	return "shareProfit/mchMsg";
}


/**
 * 商家分享的页面
 * @param request
 * @param model
 * @return
 * @throws IOException 
 */
@RequestMapping(value = "/mchShareUrl", method = RequestMethod.GET) 
public void mchShareUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
	String mchNo = request.getParameter("mchNo");
	String mchName = request.getParameter("mchName");
	//model.addAttribute("mchNo", mchNo);
	//model.addAttribute("mchName", mchName);
	/**
	 * 获取code
	 */
	String backUri = Constant.WEIXIN_REDIRECT_URI_MCH_SHARE;
	backUri = backUri+"?mchNo="+mchNo+"&mchName="+mchName;
	backUri = URLEncoder.encode(backUri);
	//scope=snsapi_userinfo：只用来获取openid
	String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_userinfo&state=1231#wechat_redirect";
	response.sendRedirect(url);
	//return "shareProfit/mchShare";
}

/**
 * 销售去商家分享页面
 * 获取销售的openid
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/tempShare", method = RequestMethod.GET) 
public String tempShare(HttpServletRequest request,Model model){
	String code = request.getParameter("code");
	String mchNo = request.getParameter("mchNo");
	String mchName = request.getParameter("mchName");
	SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
    String openid = token.getOpenid();
    model.addAttribute("mchNo", mchNo);
    //查询商家地址
    MchMsgVO vo =  shareProfitSerivce.findMchMsgByNo(mchNo);
    model.addAttribute("mchAddress", vo.getMchAddress());
    model.addAttribute("mchName", mchName);
    model.addAttribute("openid", openid);
	return "shareProfit/mchShare";
}


@RequestMapping(value = "/vlidPhoneCode", method = RequestMethod.GET) 
@ResponseBody
public Boolean vlidPhoneCode(HttpServletRequest request){
	String phone = request.getParameter("phone");
	String phoneCode = request.getParameter("phoneCode");
	Boolean flag = activationSerivce.verifycode(phone, phoneCode);	
	return flag;
}


@RequestMapping(value = "/selectStaffRegister", method = RequestMethod.GET) 
@ResponseBody
public Integer selectStaffRegister(HttpServletRequest request){
	String openid = request.getParameter("openid");
	Integer flag = shareProfitSerivce.selectStaffRegister(openid);
	return flag;
}

/**
 * 去销售注册页面
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister", method = RequestMethod.GET) 
public String staffRegister(HttpServletRequest request,Model model){
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	return "shareProfit/staffRegister1";
}

/**
 * 销售注册1
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister1", method = RequestMethod.GET) 
public String staffRegister1(HttpServletRequest request,Model model){
	String mchNo = request.getParameter("mchNo");
	model.addAttribute("mchNo", mchNo);
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	return "shareProfit/staffRegister1";
}

/**
 * 销售注册2
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister2", method = RequestMethod.POST) 
public String staffRegister2(HttpServletRequest request,Model model){
	String mchNo = request.getParameter("mchNo");
	model.addAttribute("mchNo", mchNo);
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	return "shareProfit/staffRegister2";
}

/**
 * 销售注册3
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister3", method = RequestMethod.POST) 
public String staffRegister3(HttpServletRequest request,Model model){
	String mchNo = request.getParameter("mchNo");
	String staffPhone = request.getParameter("staffPhone");
	model.addAttribute("mchNo", mchNo);
	model.addAttribute("staffPhone", staffPhone);
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	return "shareProfit/staffRegister3";
}

/**
 * 销售注册4
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister4", method = RequestMethod.POST) 
public String staffRegister4(HttpServletRequest request,Model model){
	String mchNo = request.getParameter("mchNo");
	String staffPhone = request.getParameter("staffPhone");
	model.addAttribute("mchNo", mchNo);
	model.addAttribute("staffPhone", staffPhone);
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	
	/*JsSDKVO  jsSDKVO = WeinXinJSSDKUtil.getJsSDK(url2);
	model.addAttribute("url", jsSDKVO.getUrl());  
    model.addAttribute("jsapi_ticket", jsSDKVO.getJsapi_ticket());  
    model.addAttribute("nonceStr", jsSDKVO.getNonceStr());  
    model.addAttribute("timestamp", jsSDKVO.getTimestamp());  
    model.addAttribute("signature", jsSDKVO.getSignature());  
    model.addAttribute("appId", Constant.WEIXIN_APPID); */
    
	return "shareProfit/staffRegister4";
}

/**
 * 销售完成注册
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister5", method = RequestMethod.POST) 
public String staffRegister5(HttpServletRequest request,Model model){
	String mchNo = request.getParameter("mchNo");
	String staffPhone = request.getParameter("staffPhone");
	String staffName = request.getParameter("staffName");
	String openid = request.getParameter("openid");
	model.addAttribute("openid", openid);
	shareProfitSerivce.addStaff(mchNo,staffPhone,staffName,openid);
	return "shareProfit/staffRegisterSuccess";
}

/**
 * 销售完成注册
 * 绑定imsi
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/staffRegister6", method = RequestMethod.GET) 
public String staffRegister6(HttpServletRequest request,Model model){	
String result = "shareProfit/staffTiedCardSuccess";
String mchNo = request.getParameter("mchNo");
String staffPhone = request.getParameter("staffPhone");
String staffName = request.getParameter("staffName");
String imsi = request.getParameter("imsi");
String openid = request.getParameter("openid");
//判断此卡是否已经激活
		Boolean flag1 = shareProfitSerivce.isValidImsi(imsi);
		if(!flag1){
			model.addAttribute("errorMsgTitle","您无法绑定此SIM卡");
			model.addAttribute("errorMsgDesc","请先协助客户将车载设备的aicar SIM卡激活，激活成功后您可以在进行绑定操作");
			result = "shareProfit/staffTiedCardError";
			return result;
		}
		//判断此卡激活时间是否超过24小时
		Boolean flag2 = shareProfitSerivce.isValidTimeImsi(imsi);
		if(!flag2){
			model.addAttribute("errorMsgTitle","您无法绑定此SIM卡");
			model.addAttribute("errorMsgDesc","很抱歉，SIM卡绑定时间需要在激活成功后24小时内，您已经超过绑定时限");
			result = "shareProfit/staffTiedCardError";
			return result;
		}
    //判断销售是否绑定此卡(自己绑定或者别的销售绑定)
	//查询自己是否绑定
	StaffMsgVO vo1 = shareProfitSerivce.isStaffMeTiedCard(openid,imsi);
	if(vo1!=null){
		model.addAttribute("errorMsgTitle", "您已经绑定过此SIM卡");
		model.addAttribute("errorMsgDesc", "您已经成功绑定过此SIM卡，无需再重复绑定");
		result = "shareProfit/staffTiedCardError";
		return result;
	}
	//查询别人是否绑定
	StaffMsgVO vo2 = shareProfitSerivce.isStaffOtherTiedCard(openid,imsi);
	if(vo2!=null){
		model.addAttribute("errorMsgTitle", "此SIM卡已经被绑定");
		model.addAttribute("errorMsgDesc", "此卡已经被别的销售绑定，无需重复绑定");
		result = "shareProfit/staffTiedCardError";
		return result;
	}
	shareProfitSerivce.addStaffAndImsi(mchNo,staffPhone,staffName,imsi,openid);
	return result;
	
}

/**
 * 查询商家信息(mchNo)
 * @param request
 * @return
 */
@RequestMapping(value = "/findMchMsgByNo", method = RequestMethod.GET) 
@ResponseBody
public MchMsgVO findMchMsgByNo(HttpServletRequest request){
	String mchNo = request.getParameter("mchNo");
	return shareProfitSerivce.findMchMsgByNo(mchNo);
}

/**
 * 查询商家信息(mchName)
 * @param request
 * @return
 */
@RequestMapping(value = "/findMchMsgByName", method = RequestMethod.GET) 
@ResponseBody
public MchMsgVO findMchMsgByName(HttpServletRequest request){
	String mchName = request.getParameter("mchName");
	return shareProfitSerivce.findMchMsgByName(mchName);
}

/**
 * GPS坐标转百度经纬度坐标
 * 附近的4S店
 * @param request
 * @return
 */
@RequestMapping(value = "/getCarList", method = RequestMethod.GET)
@ResponseBody
public List<RoundCarVO> getCarList(HttpServletRequest request){
	String lat = request.getParameter("lat");
	String lng = request.getParameter("lng");
	geoconyVO latg =  BaiDuApi.geoconyTo(lat, lng, Constant.BAIDU_AK);
	List<RoundCarVO> results = new ArrayList<RoundCarVO>();
	BaiDuVO vo =  BaiDuApi.getRoundCar(latg.getResult().get(0).getY(), latg.getResult().get(0).getX(), Constant.BAIDU_RADIUS, Constant.BAIDU_AK);
	for (resultVO result : vo.getResults()) {
		RoundCarVO rv = new RoundCarVO();
		//得到4S店名
		 String name = result.getName();
		 //得到店铺地址
		 String address = result.getAddress();
		 
		 rv.setId(address);
		 rv.setName(name);
		 results.add(rv);
	};
	return results;
};

}
