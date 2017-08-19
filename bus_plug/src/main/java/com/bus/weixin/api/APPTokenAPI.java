package com.bus.weixin.api;


import com.bus.weixin.httpclient.LocalHttpClient;
import com.bus.weixin.vo.APPToken;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
/**
 * 获取app相关的ACCESS_TOKEN API
 */
public class APPTokenAPI extends BaseAPI{

	/**
	 * 获取access_token
	 * @param appid appid
	 * @param secret secret
	 * @return Token
	 */
	public static APPToken token(String appid, String secret){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/token")
				.addParameter("grant_type","client_credential")
				.addParameter("appid", appid)
				.addParameter("secret", secret)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,APPToken.class);
	}

}
