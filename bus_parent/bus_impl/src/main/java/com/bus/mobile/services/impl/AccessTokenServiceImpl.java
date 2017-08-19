package com.bus.mobile.services.impl;



import javax.inject.Named;

import com.bus.mobile.services.IAccessTokenService;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.Constant;
import com.qiniu.util.Auth;

@Named
public class AccessTokenServiceImpl implements IAccessTokenService {
	@Override
	public ClientCallbackResultVO<String> getEasyUpToken() throws Exception {
	    //Config.zone = Zone.zone1();
	   // Config.zone = new Zone("http://up-z1.qiniu.com","http://up-z1.qiniu.com"); 
		Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY, Constant.QINIU_SECRET_KEY);
		ClientCallbackResultVO<String> result = new ClientCallbackResultVO<String>();
		result.setAppKey(auth.uploadToken(Constant.QINIU_BUCKETNAME));
		return result;
	}

	@Override
	public ClientCallbackResultVO<String> getCoverUpToken(String key) throws Exception {
		Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY, Constant.QINIU_SECRET_KEY);
		ClientCallbackResultVO<String> result = new ClientCallbackResultVO<String>();
		result.setAppKey(auth.uploadToken(Constant.QINIU_BUCKETNAME, key));
		 return result;
	}

	@Override
	public ClientCallbackResultVO<String> getCallbackToken(String callBack) throws Exception {
		return null;
	}

}
