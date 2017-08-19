package com.bus.weixin.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.bus.api.util.MapUtil;
import com.bus.api.util.SignatureUtil;
import com.bus.api.util.XMLConverUtil;
import com.bus.weixin.httpclient.LocalHttpClient;
import com.bus.weixin.vo.Closeorder;
import com.bus.weixin.vo.DownloadbillResult;
import com.bus.weixin.vo.MchBaseResult;
import com.bus.weixin.vo.MchDownloadbill;
import com.bus.weixin.vo.MchOrderInfoResult;
import com.bus.weixin.vo.MchOrderquery;
import com.bus.weixin.vo.Unifiedorder;
import com.bus.weixin.vo.UnifiedorderResult;

/**
 * 微信支付 基于V3.X 版本
 *
 */
public class PayMchAPI extends BaseAPI{


	/**
	 * 统一下单
	 * @param unifiedorder unifiedorder
	 * @param key key
	 * @return UnifiedorderResult
	 */
	public static UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder, String key){
		Map<String,String> map = MapUtil.objectToMap(unifiedorder);
		UnifiedorderResult result = new UnifiedorderResult();
		String paySgin_="";
		if(key != null){
			String sign = SignatureUtil.generateSign(map,key);
			unifiedorder.setSign(sign);
			paySgin_= sign;
		}
		String unifiedorderXML = XMLConverUtil.convertToXML(unifiedorder);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(xmlHeader)
										.setUri(MCH_URI + "/pay/unifiedorder")
										.setEntity(new StringEntity(unifiedorderXML,Charset.forName("utf-8")))
										.build();
		result =  LocalHttpClient.executeXmlResult(httpUriRequest,UnifiedorderResult.class);
		result.setPaySgin_(paySgin_);
		return result;
		
	}


	/**
	 * 查询订单
	 * @param mchOrderquery mchOrderquery
	 * @param key key
	 * @return MchOrderInfoResult
	 */
	public static MchOrderInfoResult payOrderquery(MchOrderquery mchOrderquery, String key){
		Map<String,String> map = MapUtil.objectToMap(mchOrderquery);
		String sign = SignatureUtil.generateSign(map,key);
		mchOrderquery.setSign(sign);
		String closeorderXML = XMLConverUtil.convertToXML(mchOrderquery);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(xmlHeader)
				.setUri(MCH_URI + "/pay/orderquery")
				.setEntity(new StringEntity(closeorderXML,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeXmlResult(httpUriRequest,MchOrderInfoResult.class);
	}



	/**
	 * 关闭订单
	 * @param closeorder closeorder
	 * @param key 商户支付密钥
	 * @return MchBaseResult
	 */
	public static MchBaseResult payCloseorder(Closeorder closeorder, String key){
		Map<String,String> map = MapUtil.objectToMap(closeorder);
		String sign = SignatureUtil.generateSign(map,key);
		closeorder.setSign(sign);
		String closeorderXML = XMLConverUtil.convertToXML(closeorder);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(xmlHeader)
				.setUri(MCH_URI + "/pay/closeorder")
				.setEntity(new StringEntity(closeorderXML,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeXmlResult(httpUriRequest,MchBaseResult.class);
	}


	/**
	 * 下载对账单
	 * @param downloadbill downloadbill
	 * @param key key
	 * @return DownloadbillResult
	 */
	public static DownloadbillResult payDownloadbill(MchDownloadbill downloadbill, String key){
		Map<String,String> map = MapUtil.objectToMap(downloadbill);
		String sign = SignatureUtil.generateSign(map,key);
		downloadbill.setSign(sign);
		String closeorderXML = XMLConverUtil.convertToXML(downloadbill);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(xmlHeader)
				.setUri(MCH_URI + "/pay/downloadbill")
				.setEntity(new StringEntity(closeorderXML,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.execute(httpUriRequest,new ResponseHandler<DownloadbillResult>() {

			@Override
			public DownloadbillResult handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    String str = EntityUtils.toString(entity,"utf-8");
                    if(str.startsWith("<xml>")){
                    	return XMLConverUtil.convertToObject(DownloadbillResult.class,str);
                    }else{
                    	DownloadbillResult dr = new DownloadbillResult();
                    	dr.setData(str);
                    	return dr;
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
			}
		});
	}
}
