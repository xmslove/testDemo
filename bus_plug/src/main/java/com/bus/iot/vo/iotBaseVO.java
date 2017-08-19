package com.bus.iot.vo;

/**
 * 
 * @author xms
 *
 */
public class iotBaseVO{

	/**
	 * 物联卡接入平台提供的用户名
	 */
	private String userId; 
	/**
	 * 物联卡接入平台提供的密码
	 */
	private String userPwd;
	/**
	 * 客户生成唯一标识
	 */
	private String transId;
	/**
	 * 固定值，cardId或iccid
	 */
	private String column_name;
	/**
	 * column_name查询字段所对应的卡号值或单个号码
	 */
	private String column_value;
	/**
	 * 签名
	 */
	private String sign;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getColumn_value() {
		return column_value;
	}

	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
