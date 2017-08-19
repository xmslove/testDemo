package com.bus.tencent.api;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.bus.api.util.MapUtil;
import com.bus.api.util.SignatureUtil;
import com.bus.tencent.vo.PushVO;
import com.bus.tencent.vo.ResultVO;
import com.bus.weixin.httpclient.LocalHttpClient;

/**
 * 推送接口
 * http://developer.qq.com/wiki/xg/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%8E%A5%E5%85%A5/Rest%20API%20%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97/Rest%20API%20%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97.html
 * @author xms
 *
 */
public class sendPushApi {
	
	private static String[] PUSH_ONE_DEVICE = {"push","single_device"};
	
	private static String[] PUSH_ALL_DEVICE = {"push","all_device"};
	
	private static String[] PUSH_LIST_DEVICE = {"push","create_multipush"};
	
	private static String[] PUSH_ONE_ACCOUNT = {"push","account"};
	
	private static String[] PUSH_LIST_ACCOUNT = {"push","account_list"};
	
	private static String[] CREATE_MULTIPUSH = {"push","create_multipush"};
	
	private static String[] ACCOUNT_LIST_MULTIPLE = {"push","account_list_multiple"};
	
	
	
	
	
	/**
	 * token单个设备推送
	 * @param pushVO
	 * @param accessKey
	 * @return
	 */
    public static ResultVO sendOneDevice(PushVO pushVO,String accessKey){
    	Map<String,String> map = MapUtil.objectToMap(pushVO);
    	String url = StringFill.fillStringByArgs(apiUri.BASE_URL, PUSH_ONE_DEVICE) ;
    	String signUrl = StringFill.fillStringByArgs(apiUri.URL, PUSH_ONE_DEVICE) ;
    	if(accessKey != null){
    		String sign = SignatureUtil.generateSignTencent(signUrl,map,accessKey);
    		pushVO.setSign(sign);
    	};
    	HttpUriRequest httpUriRequest = RequestBuilder.post()
    			.setUri(url)
    			.addParameter("access_id", pushVO.getAccess_id())
    			.addParameter("timestamp", pushVO.getTimestamp())
    			.addParameter("sign", pushVO.getSign())
    			.addParameter("device_token", pushVO.getDevice_token())
    			.addParameter("message_type", pushVO.getMessage_type())
    			.addParameter("message", pushVO.getMessage())
    			.build();
    	return LocalHttpClient.executeJsonResult(httpUriRequest,ResultVO.class);
    };
    
    /**
     * 批量设备推送
     * @param pushVO
     * @param accessKey
     * @return
     */
    public static ResultVO sendListDevice(PushVO pushVO,String accessKey){
    	Map<String,String> map = MapUtil.objectToMap(pushVO);
    	String url = StringFill.fillStringByArgs(apiUri.BASE_URL, PUSH_LIST_DEVICE) ;
    	String signUrl = StringFill.fillStringByArgs(apiUri.URL, PUSH_LIST_DEVICE) ;
    	if(accessKey != null){
    		String sign = SignatureUtil.generateSignTencent(signUrl,map,accessKey);
    		pushVO.setSign(sign);
    	};
    	HttpUriRequest httpUriRequest = RequestBuilder.post()
    			.setUri(url)
    			.addParameter("access_id", pushVO.getAccess_id())
    			.addParameter("timestamp", pushVO.getTimestamp())
    			.addParameter("sign", pushVO.getSign())
    			.addParameter("multi_pkg", pushVO.getMulti_pkg())
    			.addParameter("message_type", pushVO.getMessage_type())
    			.addParameter("message", pushVO.getMessage())
    			.build();
    	return LocalHttpClient.executeJsonResult(httpUriRequest,ResultVO.class);
    };
    
    
    /**
     * 推送全部设备
     * @param pushVO
     * @param accessKey
     * @return
     */
    public static ResultVO  sendAllDevice(PushVO pushVO,String accessKey){
    	Map<String,String> map = MapUtil.objectToMap(pushVO);
    	String url = StringFill.fillStringByArgs(apiUri.BASE_URL, PUSH_ALL_DEVICE) ;
    	String signUrl = StringFill.fillStringByArgs(apiUri.URL, PUSH_ALL_DEVICE) ;
    	if(accessKey != null){
    		String sign = SignatureUtil.generateSignTencent(signUrl,map,accessKey);
    		pushVO.setSign(sign);
    	};
    	HttpUriRequest httpUriRequest = RequestBuilder.post()
    			.setUri(url)
    			.addParameter("access_id", pushVO.getAccess_id())
    			.addParameter("timestamp", pushVO.getTimestamp())
    			.addParameter("sign", pushVO.getSign())
    			.addParameter("message_type", pushVO.getMessage_type())
    			.addParameter("message", pushVO.getMessage())
    			.build();
    	return LocalHttpClient.executeJsonResult(httpUriRequest,ResultVO.class);
    }
    
    
    /**
     * 单个账号推送
     * @param pushVO
     * @param accessKey
     * @return
     */
    public static ResultVO sendOneAccount(PushVO pushVO,String accessKey){
    	Map<String,String> map = MapUtil.objectToMap(pushVO);
    	String url = StringFill.fillStringByArgs(apiUri.BASE_URL, PUSH_ONE_ACCOUNT) ;
    	String signUrl = StringFill.fillStringByArgs(apiUri.URL, PUSH_ONE_ACCOUNT) ;
    	if(accessKey != null){
    		String sign = SignatureUtil.generateSignTencent(signUrl,map,accessKey);
    		pushVO.setSign(sign);
    	};
    	HttpUriRequest httpUriRequest = RequestBuilder.post()
    			.setUri(url)
    			.addParameter("access_id", pushVO.getAccess_id())
    			.addParameter("timestamp", pushVO.getTimestamp())
    			.addParameter("sign", pushVO.getSign())
    			.addParameter("account", pushVO.getAccount())
    			.addParameter("message_type", pushVO.getMessage_type())
    			.addParameter("message", pushVO.getMessage())
    			.build();
    	return LocalHttpClient.executeJsonResult(httpUriRequest,ResultVO.class);
    };
    
    /**
     * 批量账号推送
     * @param pushVO：account_list：逗号连接的字符串
     * @param accessKey
     * @return
     */
    public static ResultVO sendListAccount(PushVO pushVO,String accessKey){
    	ResultVO result = null;
    	Map<String,String> map = MapUtil.objectToMap(pushVO);
    	String accounts = pushVO.getAccount_list();
    	int accountSize = accounts.split(",").length;
    	if(accountSize<=100){
    	String url = StringFill.fillStringByArgs(apiUri.BASE_URL, PUSH_LIST_ACCOUNT) ;
        String signUrl = StringFill.fillStringByArgs(apiUri.URL, PUSH_LIST_ACCOUNT) ;
        String account_list = Arrays.toString(pushVO.getAccount_list().split(",")); 
        pushVO.setAccount_list(account_list);
        if(accessKey != null){
    		String sign = SignatureUtil.generateSignTencent(signUrl,map,accessKey);
    		pushVO.setSign(sign);
    	};
    	HttpUriRequest httpUriRequest = RequestBuilder.post()
    			.setUri(url)
    			.addParameter("access_id", pushVO.getAccess_id())
    			.addParameter("timestamp", pushVO.getTimestamp())
    			.addParameter("sign", pushVO.getSign())
    			.addParameter("account_list", pushVO.getAccount_list())
    			.addParameter("message_type", pushVO.getMessage_type())
    			.addParameter("message", pushVO.getMessage())
    			.build();
    	result = LocalHttpClient.executeJsonResult(httpUriRequest,ResultVO.class);
    	}else{
    		//创建批量推送的消息
    		String multiPushUrl = StringFill.fillStringByArgs(apiUri.BASE_URL, CREATE_MULTIPUSH) ;
            String multiPushSignUrl = StringFill.fillStringByArgs(apiUri.URL, CREATE_MULTIPUSH) ;
            if(accessKey != null){
        		String sign = SignatureUtil.generateSignTencent(multiPushSignUrl,map,accessKey);
        		pushVO.setSign(sign);
        	};
    		HttpUriRequest httpUriRequest = RequestBuilder.post()
        			.setUri(multiPushUrl)
        			.addParameter("access_id", pushVO.getAccess_id())
        			.addParameter("timestamp", pushVO.getTimestamp())
        			.addParameter("sign", pushVO.getSign())
        			.addParameter("message_type", pushVO.getMessage_type())
        			.addParameter("message", pushVO.getMessage())
        			.build();
    		ResultVO multiPushResult = LocalHttpClient.executeJsonResult(httpUriRequest,ResultVO.class);
    		//消息Id
    		String pushId = multiPushResult.getResult().getPush_id();
    		pushVO.setPush_id(pushId);
    		//推送循环的次数
    		int accSize = 0;
    		float num = (float)accountSize/1000;
    		DecimalFormat df = new DecimalFormat("0.00");
    		String s = df.format(num);
    		if(s.substring(s.length()-2, s.length()).equals("00")){
    			accSize = Integer.parseInt(s.substring(0, s.indexOf(".", 0)));
    		}else{
    			accSize = Integer.parseInt(s.substring(0, s.indexOf(".", 0))) + 1 ;
    		};
    		for (int i = 0; i < accSize; i++) {
    			int k = i*1000;
    			int j = (i+1)*1000-1 ;
    			String[] accountStr = pushVO.getAccount_list().split(",");
    			StringBuffer strbuf = new StringBuffer(); 
    			for (int l = k; l <= j; l++) {
    				 strbuf.append(",").append(accountStr[l]);
				}
    			String accountS = strbuf.deleteCharAt(0).toString();
    			String account_list = Arrays.toString(accountS.split(",")); 
    	        pushVO.setAccount_list(account_list);
    			String accountListMultipleUrl = StringFill.fillStringByArgs(apiUri.BASE_URL, ACCOUNT_LIST_MULTIPLE) ;
    	        String accountListMultipleSignUrl = StringFill.fillStringByArgs(apiUri.URL, ACCOUNT_LIST_MULTIPLE) ;
    	        if(accessKey != null){
    	    		String sign = SignatureUtil.generateSignTencent(accountListMultipleSignUrl,map,accessKey);
    	    		pushVO.setSign(sign);
    	    	};
    	    	HttpUriRequest httpUriRequests = RequestBuilder.post()
            			.setUri(accountListMultipleUrl)
            			.addParameter("access_id", pushVO.getAccess_id())
            			.addParameter("timestamp", pushVO.getTimestamp())
            			.addParameter("sign", pushVO.getSign())
            			.addParameter("account_list", pushVO.getAccount_list())
            			.addParameter("push_id", pushVO.getPush_id())
            			.build();
    	    			
    	    	result = LocalHttpClient.executeJsonResult(httpUriRequests,ResultVO.class);
			}
    	};
		return result;
    }; 
    
}
