package com.bus.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author xms
 *
 */
public class ismiUserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5343901227108647297L;
	
	private Integer id;
	/**
	 * imsi唯一认证
	 */
	private String imsiId;
	/**
	 * imsi对应的设备
	 */	
	private String imei;
	/**
	 * 车辆类型：1：运营车辆；2：私用；3：其他.....
	 */
	private Integer busType;
	/**
	 * 激活用户的手机号
	 */
	private Integer userPhone;
	/**
	 * imsi绑定状态（0：没有绑定电话号码；1：绑定电话，但没有付钱；2：绑定并且付钱）
	 */
	private Integer status;
	/**
	 * 激活时间
	 */
	private Date imsiretime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImsiId() {
		return imsiId;
	}
	public void setImsiId(String imsiId) {
		this.imsiId = imsiId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Integer getBusType() {
		return busType;
	}
	public void setBusType(Integer busType) {
		this.busType = busType;
	}
	public Integer getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Integer userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getImsiretime() {
		return imsiretime;
	}
	public void setImsiretime(Date imsiretime) {
		this.imsiretime = imsiretime;
	}
}
