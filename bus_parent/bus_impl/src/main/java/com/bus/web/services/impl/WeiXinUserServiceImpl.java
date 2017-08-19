package com.bus.web.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bus.web.services.IWeiXinUserService;
import com.bus.weixin.vo.User;
import com.bus.weixin.vo.UserInfoList;
/**
 * 
 * @author xms
 *
 */
@Service("weiXinUserService")
public class WeiXinUserServiceImpl implements IWeiXinUserService{

	@Override
	public User userInfo(String access_token, String openid, String emoji) {
		return null;
	}

	@Override
	public UserInfoList userInfoBatchget(String access_token, String lang,
			List<String> openids, String emoji) {
		return null;
	}
	
	
}
