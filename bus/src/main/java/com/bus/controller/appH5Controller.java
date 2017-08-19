package com.bus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bus.mobile.services.IUserService;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.UserInfoVO;
import com.bus.web.services.IActivationSerivce;

/**
 * appH5页面展示
 * @author xms
 *
 */
@Controller  
@RequestMapping("/appH5")
public class appH5Controller {
	@Resource 
	private IUserService userService;
	@Resource 
	private IActivationSerivce activationSerivce;
	
	@RequestMapping(value = "/aicarHome", method = RequestMethod.GET) 
	public String goHome(HttpServletRequest request,Model model){
		String imsi = request.getParameter("imsi");
		model.addAttribute("imsi", imsi);
		return "appH5/aicarHome";
	}
	
	
	@RequestMapping(value = "/customerService", method = RequestMethod.GET) 
	public String customerService(HttpServletRequest request,Model model){
		String imsi = request.getParameter("imsi");
		model.addAttribute("imsi", imsi);
		return "appH5/customerService";
	}
	
	
	@RequestMapping(value = "/vipAicar", method = RequestMethod.GET) 
	public String vipAicar(HttpServletRequest request,Model model) throws Exception{
		String result = "appH5/vipAicar";
		String imsi = request.getParameter("imsi");
		model.addAttribute("imsi", imsi);
		UserInfoVO vo = userService.isUserActivation(imsi);
		if(!vo.getAnnualFee()){
			result = "appH5/topUp" ;
		}
		return result;
	}
	
	
	@RequestMapping(value = "/set", method = RequestMethod.GET) 
	public String set(HttpServletRequest request,Model model){
		String imsi = request.getParameter("imsi");
		model.addAttribute("imsi", imsi);
		return "appH5/set";
	}
	
	
	@RequestMapping(value = "/getUserPackageMsg", method = RequestMethod.GET) 
	@ResponseBody
	public UserInfoVO getUserPackageMsg(HttpServletRequest request) throws Exception{
		String imsi = request.getParameter("imsi");
		ClientCallbackResultVO<UserInfoVO> result  =userService.getUserInfo(imsi);
		return result.getData().get(0);
	}
	
	
	@RequestMapping(value = "/getUserPhone", method = RequestMethod.GET) 
	@ResponseBody
	public String getUserPhone(HttpServletRequest request) throws Exception{
		String imsi = request.getParameter("imsi");
		return activationSerivce.getUserPhone(imsi);
	}
	
	@RequestMapping(value = "/getAppVersion", method = RequestMethod.GET) 
	@ResponseBody
	public String getAppVersion(HttpServletRequest request) throws Exception{
		return activationSerivce.getAppVersion();
	}
	
	
	@RequestMapping(value = "/isUserActivation", method = RequestMethod.GET) 
	@ResponseBody
	public UserInfoVO isUserActivation(HttpServletRequest request) throws Exception{
		String imsi = request.getParameter("imsi");
		return userService.isUserActivation(imsi);
	}
    
	@RequestMapping(value = "/aicarService", method = RequestMethod.GET) 
	public String aicarService(HttpServletRequest request,Model model) throws Exception{
		String imsi = request.getParameter("imsi");
		model.addAttribute("imsi", imsi);
		return "appH5/aicarService";
	}
	
}
