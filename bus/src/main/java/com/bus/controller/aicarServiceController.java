package com.bus.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bus.baidu.api.BaiDuApi;
import com.bus.baidu.api.CoordinateVO;
import com.bus.baidu.vo.BaiDuVO;
import com.bus.baidu.vo.geoconyVO;
import com.bus.baidu.vo.xyVO;
import com.bus.illegal.api.illegalApi;
import com.bus.illegal.vo.CityParamVO;
import com.bus.illegal.vo.IllegalResultVO;
import com.bus.mobile.services.IIllegalService;
import com.bus.util.JsSDKVO;
import com.bus.util.WeinXinJSSDKUtil;
import com.bus.validate.ValidateArgument;
import com.bus.validate.rule.MaxLengthValidate;
import com.bus.validate.rule.NotNullValidate;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.Constant;
import com.bus.vo.IllegalMsgVO;
import com.bus.web.services.IWeiXinScanService;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.vo.SnsToken;
import com.bus.yunba.api.PushApi;
import com.bus.yunba.vo.RemoteNavigationInfoVO;
import com.bus.yunba.vo.YunBaPushParamVO;
  
/**
 * 
 * @author xms
 * aicar
 */
@Controller  
@RequestMapping("/mobleCar") 
public class aicarServiceController {
	@Resource 
	private IWeiXinScanService weiXinScanService;
	@Resource
	private IIllegalService iIllegalService;
	
	/**
	 * 授权违章查询
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getService", method = RequestMethod.GET) 
	public void toRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String imsi = request.getParameter("imsi");
		String backUri = Constant.WEIXIN_REDIRECT_URI_AICARSERVICE+"?imsi="+imsi;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	/**
	 * 微信授权之后跳转到违章查询服务
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temp", method = RequestMethod.GET)
	public String  temp(HttpServletRequest request,Model model){
		String result  = "aicarService/illegal" ;
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String imsi = request.getParameter("imsi");
		model.addAttribute("imsi", imsi);
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    model.addAttribute("openid", openid);
	    JsSDKVO  jsSDKVO = WeinXinJSSDKUtil.getJsSDK(Constant.WEIXIN_SHAREPROFIT_URL6+"?imsi="+imsi+"&code="+code+"&state="+state);
        model.addAttribute("url", jsSDKVO.getUrl());  
        model.addAttribute("jsapi_ticket", jsSDKVO.getJsapi_ticket());  
        model.addAttribute("nonceStr", jsSDKVO.getNonceStr());  
        model.addAttribute("timestamp", jsSDKVO.getTimestamp());  
        model.addAttribute("signature", jsSDKVO.getSignature());  
        model.addAttribute("appId", Constant.WEIXIN_APPID);
	    List<String> imsiList = weiXinScanService.getIIllegalImsi(openid);
	    List<IllegalMsgVO> iIllegalMsgList = new ArrayList<IllegalMsgVO>();
	    if(imsiList.size()!=0){
	    for (String imsiStr : imsiList) {
	    	ClientCallbackResultVO<IllegalMsgVO> data = iIllegalService.isIllegal(imsiStr);
	    	IllegalMsgVO illegalMsgVO = data.getData().get(0);
	    	iIllegalMsgList.add(illegalMsgVO);
		};
		}
	    if(iIllegalMsgList.size()!=0){
	    	model.addAttribute("iIllegalMsgList",JSONArray.parseArray(JSON.toJSONString(iIllegalMsgList)));
	    	result = "aicarService/iIllegalMsgList";
	    }
		return result;
	}
	
	
	
    /**
     * 添加违章查询信息
     * @param request
     * @param model
     * @return
     */
	@RequestMapping(value = "/addIllegal", method = RequestMethod.GET)
	public String  addIllegal(HttpServletRequest request,Model model){
		String result  = "aicarService/illegal" ;
		String imsi = request.getParameter("imsi");
		request.setAttribute("imsi", imsi);
		return result;
	}
	
	/* 根据经纬度获取城市
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCoordinate", method = RequestMethod.GET) 
	@ResponseBody
	public CoordinateVO getCoordinate(HttpServletRequest request) throws IOException {   
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		geoconyVO vo = BaiDuApi.geoconyTo(lat, lng, Constant.BAIDU_AK);
		xyVO s = vo.getResult().get(0);
		System.out.println(JSON.toJSONString(s));
		
		String lngNew = s.getX();
		String latNew = s.getY();
		CoordinateVO result = BaiDuApi.getCoordinate(latNew, lngNew, Constant.BAIDU_AK);
        return result;  
    } 
	
	
	@RequestMapping(value = "/removeIllegal", method = RequestMethod.GET)
	@Transactional
	public void removeIllegal(
								 HttpServletRequest request,
								 HttpServletResponse response, 
								 @RequestParam("id")   @ValidateArgument(notNull=@NotNullValidate) String id,
								 @RequestParam("hphm") @ValidateArgument(notNull=@NotNullValidate) String hphm) throws IOException {   
		iIllegalService.removeIllegal(Integer.parseInt(id));
		response.sendRedirect(Constant.WEIXIN_GET_AICARSERVICE);  
    } 
	
	/**
	 * 查询违章信息
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addIllegalMsg", method = RequestMethod.POST)
	@Transactional
	public String  addIllegalMsg(HttpServletRequest request,Model model) throws Exception{
		String result  = "aicarService/addIllegalMsg" ;
		String cityName = request.getParameter("cityName");
		String openid = request.getParameter("openid");
		String imsi = request.getParameter("imsi");
		String hphm = request.getParameter("hphm");
		String engineno = request.getParameter("engineno");
		String classno = request.getParameter("classno");
		weiXinScanService.addIllegalMsg(openid,imsi,hphm,engineno,classno);
		CityParamVO vo = new CityParamVO();
		vo.setCityName(cityName);
		vo.setKey(Constant.ILLEGAL_APPKEY);
		vo.setHphm(hphm);
		vo.setEngineno(engineno);
		vo.setClassno(classno);
		IllegalResultVO illegalMsg = illegalApi.getIllegalResult(vo);
		model.addAttribute("IllegalResultVO",JSON.toJSONString(illegalMsg));
		model.addAttribute("hphm", hphm);
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toiIllegal", method = RequestMethod.GET)
	public String  toiIllegal(HttpServletRequest request,Model model) throws Exception{
		String result  = "aicarService/addIllegalMsg" ;
		String cityName = request.getParameter("cityName");
		String hphm = request.getParameter("hphm");
		String engineno = request.getParameter("engineno");
		String classno = request.getParameter("classno");
		CityParamVO vo = new CityParamVO();
		vo.setCityName(cityName);
		vo.setKey(Constant.ILLEGAL_APPKEY);
		vo.setHphm(hphm);
		vo.setEngineno(engineno);
		vo.setClassno(classno);
		IllegalResultVO illegalMsg = illegalApi.getIllegalResult(vo);
		model.addAttribute("IllegalResultVO",JSON.toJSONString(illegalMsg));
		model.addAttribute("hphm", hphm);
		return result;
	}
	
	/**
	 * 查看轨迹
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getService2", method = RequestMethod.GET) 
	public void getService2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String backUri = Constant.WEIXIN_REDIRECT_URI_AICARSERVICE2;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	
	@RequestMapping(value = "/temp2", method = RequestMethod.GET)
	public String  temp2(HttpServletRequest request,Model model){
		String result  = "aicarService/findLine" ;
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    model.addAttribute("openid", openid);
		return result;
	}
	
	/**
	 * 远程导航
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getService3", method = RequestMethod.GET) 
	public void getService3(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String backUri = Constant.WEIXIN_REDIRECT_URI_AICARSERVICE3;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	
	@RequestMapping(value = "/temp3", method = RequestMethod.GET)
	public String  temp3(HttpServletRequest request,Model model){
		String result  = "aicarService/takingPictures" ;
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    model.addAttribute("openid", openid);
	    List<String>  imsiList = weiXinScanService.getImsi(openid);
	    model.addAttribute("imsi", imsiList.size()!=0?imsiList.get(0):null);
		return result;
	}
	
	
	
	@RequestMapping(value = "/getShreah", method = RequestMethod.GET)
	@ResponseBody
	public BaiDuVO getShreah(HttpServletRequest request){
		String addrsProvence = request.getParameter("addrsProvence");
		String addrs = request.getParameter("addrs");
		BaiDuVO result = BaiDuApi.getShreah(addrs, addrsProvence, Constant.BAIDU_AK);
		return result;
	}
	
	/**
	 * 通知客户端开始导航
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toclient", method = RequestMethod.GET)
	public String  toclient(HttpServletRequest request,Model model){
		String result  = "aicarService/toclient" ;
		String lng = request.getParameter("lng");
		String lat = request.getParameter("lat");
		String imsi = request.getParameter("imsi");
		RemoteNavigationInfoVO  navigation  = new RemoteNavigationInfoVO();
		navigation.setStyle(0);
		navigation.setLat(Double.parseDouble(lat));
		navigation.setLon(Double.parseDouble(lng));
		navigation.setFunctioncode(10000);
		String msg = JSON.toJSONString(navigation);
		YunBaPushParamVO yunBaPushParamVO = new YunBaPushParamVO();
		yunBaPushParamVO.setAlias(imsi);
		yunBaPushParamVO.setAppkey(Constant.YUNBA_APPKEY);
		yunBaPushParamVO.setSeckey(Constant.YUNBA_SECRETKEY);
		yunBaPushParamVO.setMethod("publish_to_alias");
		yunBaPushParamVO.setMsg(msg);
		PushApi.publishToAlias(yunBaPushParamVO);
		return result;
	};
	
	
	/**
	 * 远程拍照
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getService4", method = RequestMethod.GET) 
	public void getService4(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String backUri = Constant.WEIXIN_REDIRECT_URI_AICARSERVICE4;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	/**
	 * 通知客户端拍照
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temp4", method = RequestMethod.GET)
	public String  temp4(HttpServletRequest request,Model model){
		String result  = "aicarService/photo" ;
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    List<String>  imsiList = weiXinScanService.getImsi(openid);
	    model.addAttribute("imsi", imsiList.size()!=0?imsiList.get(0):null);
	    YunBaPushParamVO yunBaPushParamVO = new YunBaPushParamVO();
		yunBaPushParamVO.setAlias(imsiList.get(0));
		yunBaPushParamVO.setAppkey(Constant.YUNBA_APPKEY);
		yunBaPushParamVO.setSeckey(Constant.YUNBA_SECRETKEY);
		yunBaPushParamVO.setMethod("publish_to_alias");
		yunBaPushParamVO.setMsg("{'functioncode':10001}");
		PushApi.publishToAlias(yunBaPushParamVO);
		return result;
	};
	
	/**
	 * 查看远程视频
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getService5", method = RequestMethod.GET) 
	public void getService5(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String backUri = Constant.WEIXIN_REDIRECT_URI_AICARSERVICE5;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	@RequestMapping(value = "/temp5", method = RequestMethod.GET) 
	public String  voide(HttpServletRequest request,Model model){
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    List<String>  imsiList = weiXinScanService.getImsi(openid);
	    model.addAttribute("imsi", imsiList.size()!=0?imsiList.get(0):null);
	    YunBaPushParamVO yunBaPushParamVO = new YunBaPushParamVO();
		yunBaPushParamVO.setAlias(imsiList.get(0));
		yunBaPushParamVO.setAppkey(Constant.YUNBA_APPKEY);
		yunBaPushParamVO.setSeckey(Constant.YUNBA_SECRETKEY);
		yunBaPushParamVO.setMethod("publish_to_alias");
		yunBaPushParamVO.setMsg("{'functioncode':10002}");
		PushApi.publishToAlias(yunBaPushParamVO);
		return  "aicarService/voide" ;
	};
	
	/**
	 * 车辆信息录入
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getService6", method = RequestMethod.GET) 
	public void getService6(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String backUri = Constant.WEIXIN_REDIRECT_URI_AICARSERVICE6;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}
	
	@RequestMapping(value = "/temp6", method = RequestMethod.GET) 
	public String  busInformation(HttpServletRequest request,Model model){
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    List<String>  imsiList = weiXinScanService.getImsi(openid);
	    model.addAttribute("imsi", imsiList.size()!=0?imsiList.get(0):null);
	    model.addAttribute("openid", openid);
		return  "aicarService/busInformation" ;
	};

}
