package com.bus.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.vo.Constant;
import com.bus.web.services.IActivationSerivce;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.vo.SnsToken;

/**
 * 车主激活  xms
 * @author xms
 *
 */
@Controller  
@RequestMapping("/activation") 
public class activationController {       
	@Resource 
	private IActivationSerivce activationSerivce;
	
	/**
	 * 授权去激活页面
	 * @param request 
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/toRegister", method = RequestMethod.GET) 
	public void toRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String imsi = request.getParameter("imsi");
		String imei = request.getParameter("imei");
		String backUri = Constant.WEIXIN_REDIRECT_URI_REGISTER+"?imsi="+imsi+"&imei="+imei;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	/**
	 * 获取openid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temp", method = RequestMethod.GET)
	public String  temp(HttpServletRequest request,Model model){
		String result  = "register" ;
		String code = request.getParameter("code");
		String imsi = request.getParameter("imsi");
		String imei = request.getParameter("imei");
		request.setAttribute("code", code);
		request.setAttribute("imsi", imsi);
		request.setAttribute("imei", imei);
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    /*APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
	    User user = UserAPI.userInfo(tokens.getAccess_token(),openid);
	    if(user.getSubscribe()==0){
	    //	model.addAttribute("errorMsg", "请关注小车车微信公众号");
	    	result = "registerError";
	    }else{
	    	request.setAttribute("openid", openid);
	    	result = "register";
	    }*/
	    request.setAttribute("openid", openid);
		return result;
	}   
	
	/**
	 * 发送验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendSNS", method = RequestMethod.GET) 
	@ResponseBody
	public Boolean sendSNS(HttpServletRequest request){
		String phone = request.getParameter("phone");
		Boolean flag = activationSerivce.sendSNS(phone, null, 3040107);
		return flag;
	}
	
	
	
	/**
	 * 验证imsi设备是否已经激活
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/vildPhone", method = RequestMethod.GET) 
	@ResponseBody
	public Boolean vildPhone(HttpServletRequest request){
		String phone = request.getParameter("phone");
		String imsi = request.getParameter("imsi");
		Boolean flag = activationSerivce.vildPhone(phone,imsi);
		return flag;
	}
	
	/**
	 * 激活第二步
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register1", method = RequestMethod.POST) 
	public String register1(HttpServletRequest request){
		String code = request.getParameter("code");
		String imsi = request.getParameter("imsi");
		String imei = request.getParameter("imei");
		String phone =request.getParameter("phone");
		String openid =request.getParameter("openid");
		request.setAttribute("code", code);
		request.setAttribute("imsi", imsi);
		request.setAttribute("imei", imei);
		request.setAttribute("phone", phone);
		request.setAttribute("openid", openid);
		return "register2";
	}
	
	
	
	
	/**
	 * 激活
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST) 
	@Transactional
	public String register(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		String reslut = null;
//		String code =  request.getParameter("code");
		String phone = request.getParameter("phone");
	//	String phoneCode = request.getParameter("phoneCode");
		String imsi = request.getParameter("imsi");
		String imei = request.getParameter("imei");
		String openid = request.getParameter("openid");
		activationSerivce.addUser(imsi,imei,phone,openid);
	     /*PackageVO vo = activationSerivce.addUser(imsi,imei,phone,openid);
	     if(vo.getTrafficCeiling().equals("-")){
	    	 vo.setTrafficCeiling("无限制");
	     };
	     if(vo.getIfVideo().equals("2")){
	    	 vo.setIfVideo("不允许");
	     };
	     int day = Integer.parseInt(vo.getDayCeiling())*30; 
	     vo.setDayCeiling(String.valueOf(day));
	     request.setAttribute("imsi", imsi);
		 request.setAttribute("phone", phone);
		 model.addAttribute("packagess", vo);*/
		 request.setAttribute("imsi", imsi);
		 request.setAttribute("phone", phone);
	     reslut = "defaultPackage" ;
	
		/*Boolean flag = activationSerivce.vildPhone(phone,imsi);
		if(flag){
			Boolean flag2 = activationSerivce.verifycode(phone, phoneCode);	
			if(flag2){}else{
			String errorMsg = "验证码错误";
			model.addAttribute("errorMsg", errorMsg);
			reslut = "registerError" ;
			};
		}else{
			String errorMsg = "激活失败，该设备已经激活";
			model.addAttribute("errorMsg", errorMsg);
			reslut = "registerError" ;
		}*/
		
		   return reslut ;
	}
	
}
