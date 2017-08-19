package com.bus.illegal.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class IllegalResultVO implements Serializable{
	
	private static final long serialVersionUID = -2421605096980015628L;
	/**
	 * 查询结果
	 */
	private String resultcode;
	/**
	 * 描述
	 */
	private String reason;
	/**
	 * 违章记录
	 */
	private IllegalMsgVO result;
	/**
	 * 错误码
	 */
	private String error_code;
	
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public IllegalMsgVO getResult() {
		return result;
	}
	public void setResult(IllegalMsgVO result) {
		this.result = result;
	}
}
