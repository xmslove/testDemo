package com.bus.vo;

import java.io.Serializable;

/**
 * 商家信息
 * @author xms
 *
 */
public class MchMsgVO implements Serializable{

private static final long serialVersionUID = -7348236228164700350L;

private Integer id;
 /**
  * 所属品牌
  */
 private String brandNo;
 /**
  * 商家店名
  */
 private String mchName;
 /**
  * 商家编号
  */
 private String mchNo;
 /**
  * 商家地址
  */
 private String mchAddress;
 /**
  * 商家注册人姓名
  */
 private String mchUserName;
 /**
  * 商家注册人手机号
  */
 private String mchUserPhone;
 
 private String mchUserOpenid;
 
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getBrandNo() {
	return brandNo;
}
public void setBrandNo(String brandNo) {
	this.brandNo = brandNo;
}
public String getMchName() {
	return mchName;
}
public void setMchName(String mchName) {
	this.mchName = mchName;
}
public String getMchNo() {
	return mchNo;
}
public void setMchNo(String mchNo) {
	this.mchNo = mchNo;
}
public String getMchAddress() {
	return mchAddress;
}
public void setMchAddress(String mchAddress) {
	this.mchAddress = mchAddress;
}
public String getMchUserName() {
	return mchUserName;
}
public void setMchUserName(String mchUserName) {
	this.mchUserName = mchUserName;
}
public String getMchUserPhone() {
	return mchUserPhone;
}
public void setMchUserPhone(String mchUserPhone) {
	this.mchUserPhone = mchUserPhone;
}
public String getMchUserOpenid() {
	return mchUserOpenid;
}
public void setMchUserOpenid(String mchUserOpenid) {
	this.mchUserOpenid = mchUserOpenid;
}
 
}
