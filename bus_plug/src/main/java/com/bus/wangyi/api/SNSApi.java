package com.bus.wangyi.api;

import java.util.UUID;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.bus.wangyi.vo.SNSVO;
import com.bus.weixin.httpclient.LocalHttpClient;

/**
 * 网易短信验证api
 * @author xms
 *
 */
public class SNSApi {
	
	/**
	 * 发送短信验证码
	 * @param mobile
	 * @param deviceId
	 * @param templateid
	 * @return
	 */
	public static SNSVO sendSNS(String mobile,String deviceId,Integer templateid,String AppKey,String AppSecret){
		String CurTime = String.valueOf(System.currentTimeMillis());
		String Nonce = UUID.randomUUID().toString();
		String CheckSum = CheckSumBuilder.getCheckSum(AppSecret, Nonce, CurTime);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
			.setUri(apiUrl.SEND_URL)
			.addHeader("AppKey", AppKey)
			.addHeader("CurTime", CurTime)
			.addHeader("Nonce", Nonce)
			.addHeader("CheckSum", CheckSum)
			.addParameter("mobile", mobile)
			.addParameter("deviceId", deviceId)
			.addParameter("templateid", templateid!=null?String.valueOf(templateid):null)
			.build();
	return LocalHttpClient.executeJsonResult(httpUriRequest,SNSVO.class);
	}
	
	/**
	 * 验证验证码
	 * @param mobile
	 * @param deviceId
	 * @param templateid
	 * @param AppKey
	 * @param AppSecret
	 * @return
	 */
	public static SNSVO verifyCode(String mobile,String code,String AppKey,String AppSecret){
		String CurTime = String.valueOf(System.currentTimeMillis());
		String Nonce = UUID.randomUUID().toString();
		String CheckSum = CheckSumBuilder.getCheckSum(AppSecret, Nonce, CurTime);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
			.setUri(apiUrl.VERIFYCODE_URL)
			.addHeader("AppKey", AppKey)
			.addHeader("CurTime", CurTime)
			.addHeader("Nonce", Nonce)
			.addHeader("CheckSum", CheckSum)
			.addParameter("mobile", mobile)
			.addParameter("code", code)
			.build();
	return LocalHttpClient.executeJsonResult(httpUriRequest,SNSVO.class);
	}
	
	/**
	 * 发送通知类和运营类短信
	 * @param params
	 * @param mobiles
	 * @param templateid
	 * @param AppKey
	 * @param AppSecret
	 * @return
	 */
	public static SNSVO sendMsg(String params,String mobiles ,String templateid,String AppKey,String AppSecret){
		String CurTime = String.valueOf(System.currentTimeMillis());
		String Nonce = UUID.randomUUID().toString();
		String CheckSum = CheckSumBuilder.getCheckSum(AppSecret, Nonce, CurTime);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(apiUrl.SEND_MSG_URL)
				.addHeader("AppKey", AppKey)
				.addHeader("CurTime", CurTime)
				.addHeader("Nonce", Nonce)
				.addHeader("CheckSum", CheckSum)
				.addParameter("templateid",templateid)
				.addParameter("mobiles", mobiles)
				.addParameter("params", params)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,SNSVO.class);
	}
	
}
