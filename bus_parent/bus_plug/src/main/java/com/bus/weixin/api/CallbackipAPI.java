package com.bus.weixin.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import com.bus.weixin.httpclient.LocalHttpClient;
import com.bus.weixin.vo.Callbackip;

/**
 * 获取微信服务器IP地址
 * 用于设置回调ip白名单
 *
 */
public class CallbackipAPI extends BaseAPI{

	/**
	 * 获取微信服务器IP地址
	 * @param access_token access_token
	 * @return Callbackip
	 */
	public static Callbackip getcallbackip(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/getcallbackip")
				.addParameter(getATPN(),access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Callbackip.class);
	}
}
