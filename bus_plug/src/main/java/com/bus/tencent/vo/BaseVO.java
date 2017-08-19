package com.bus.tencent.vo;

/**
 * 
 * @author xms
 *
 */
public class BaseVO {

	/**
	 * 应用的唯一标识符，在提交应用时管理系统返回。可在xg.qq.com管理台查看
	 */
	private String access_id ;  
	/**
     *  本请求的unix时间戳，用于确认请求的有效期。默认情况下，请求时间戳与服务器时间（北京时间）偏差大于600秒则会被拒绝
	 */
	private String timestamp ;  
	/**
	 * 内容签名
	 */
	private String sign ;
	
	public String getAccess_id() {
		return access_id;
	}
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
