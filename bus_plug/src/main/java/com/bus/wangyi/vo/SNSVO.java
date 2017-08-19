package com.bus.wangyi.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class SNSVO implements Serializable{

	private static final long serialVersionUID = 2984038045255713201L;
	/**
	 * 发送短信验证码的返回码（http://dev.netease.im/docs?doc=server_sms）
	 * 主要的返回码
	 * 200、315、403、414、416、500
	 */
	private String code;
	/**
	 * 此次发送的sendid
	 */
	private String msg;
	/**
	 * 此次发送的验证码
	 */
	private String obj;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	
}
