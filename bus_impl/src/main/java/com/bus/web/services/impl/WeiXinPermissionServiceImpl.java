package com.bus.web.services.impl;

import org.springframework.stereotype.Service;

import com.bus.vo.WeixinVO;
import com.bus.web.services.IWeiXinPermissionService;
import com.bus.weixin.api.CallbackipAPI;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.Callbackip;
import com.bus.weixin.vo.SnsToken;
import com.bus.weixin.vo.User;
import com.bus.weixin.xml.BaseResult;

/**
 * 
 * @author xms
 *
 */
@Service("weiXinPermissionService")
public class WeiXinPermissionServiceImpl implements IWeiXinPermissionService{

	@Override
	public APPToken getToken() {
		return null;
	}

	@Override
	public WeixinVO getServerIp(String access_token) {
		Callbackip ips = CallbackipAPI.getcallbackip(access_token);
		if(ips!=null){
			WeixinVO vo = new WeixinVO();
			String ipList = "";
			StringBuffer sb = new StringBuffer(); 
			for (String ip : ips.getIp_list()) {
				sb.append(ip+",");
				ipList = sb.toString().substring(0, sb.toString().length()-1);
			};
			vo.setIp(ipList);
			return vo;
		}
		return null;
	}

	@Override
	public SnsToken oauth2AccessToken(String appid, String secret, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SnsToken oauth2ComponentAccessToken(String appid, String code,
			String component_appid, String component_access_token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SnsToken oauth2RefreshToken(String appid, String refresh_token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SnsToken oauth2ComponentRefreshToken(String appid,
			String refresh_token, String component_appid,
			String component_access_token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult auth(String access_token, String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User userinfo(String access_token, String openid, String lang,
			String emoji) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String connectOauth2Authorize(String appid, String redirect_uri,
			boolean snsapi_userinfo, String state, String component_appid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String connectQrconnect(String appid, String redirect_uri,
			String state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
