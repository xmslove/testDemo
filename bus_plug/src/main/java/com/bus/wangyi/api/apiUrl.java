package com.bus.wangyi.api;

public abstract class apiUrl {
	/**
	 * 发送短信
	 */
	protected static final String SEND_URL = "https://api.netease.im/sms/sendcode.action";
	
	/**
	 * 校验验证码
	 */
	protected static final String VERIFYCODE_URL = "https://api.netease.im/sms/verifycode.action";
	
	/**
	 * 发送通知类和运营类短信
	 */
	protected static final String SEND_MSG_URL =  "https://api.netease.im/sms/sendtemplate.action";
	
	
	
}
