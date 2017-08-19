package com.bus.yunba.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.bus.weixin.httpclient.LocalHttpClient;
import com.bus.yunba.vo.PushResultVO;
import com.bus.yunba.vo.YunBaPushParamVO;


/**
 * 云巴推送
 * @author xms
 *
 */
public class PushApi {
	
	public final static String YUNBA_APPKEY = "58f87afdf1ae5ffe36711543";
	public final static String YUNBA_SECRETKEY = "sec-iZcKvZ0aUJULwK6O9EwU8vSY7bZCYGErOgEvoLGkJlhOefuj";
	
	public static PushResultVO publishToAlias(YunBaPushParamVO yunBaPushParamVO){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
			.setUri(apiUrl.PUBLISH_TO_ALIAS)
			.addParameter("method", yunBaPushParamVO.getMethod())
			.addParameter("appkey", yunBaPushParamVO.getAppkey())
			.addParameter("seckey", yunBaPushParamVO.getSeckey())
			.addParameter("alias", yunBaPushParamVO.getAlias())
			.addParameter("msg", yunBaPushParamVO.getMsg())
			.build();
	return LocalHttpClient.executeJsonResult(httpUriRequest,PushResultVO.class);
	} 

	
	
}
