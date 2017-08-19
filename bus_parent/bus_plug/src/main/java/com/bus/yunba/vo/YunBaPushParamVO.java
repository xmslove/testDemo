package com.bus.yunba.vo;

public class YunBaPushParamVO {
	/**
	 * 推送方式
	 */
	private String method;
	/**
	 * 云巴appkey
	 */
	private String appkey;
	/**
	 * 云巴seckey
	 */
	private String seckey;
	/**
	 * 注册别名
	 */
	private String alias;
	/**
	 * 订阅号
	 */
	private String topic;
	/**
	 * 消息
	 */
	private String msg;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getSeckey() {
		return seckey;
	}
	public void setSeckey(String seckey) {
		this.seckey = seckey;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
