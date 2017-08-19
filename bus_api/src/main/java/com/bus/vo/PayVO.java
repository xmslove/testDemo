package com.bus.vo;

import java.io.Serializable;

/**
 * 
 * @author xms
 *
 */
public class PayVO implements Serializable{
	
     /**
	 * 
	 */
	private static final long serialVersionUID = -7339182963988858226L;
	 private  String json ;  
     private String imsi ;
     private String packageType;
     private String packagePrice;
     private String orderNo;
     private String nickname;
     private String headimgurl;
     private String otherNickname;
     private String otherHeadimgurl;
     private String payModel;
     private String openid;
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(String packagePrice) {
		this.packagePrice = packagePrice;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getOtherNickname() {
		return otherNickname;
	}
	public void setOtherNickname(String otherNickname) {
		this.otherNickname = otherNickname;
	}
	public String getOtherHeadimgurl() {
		return otherHeadimgurl;
	}
	public void setOtherHeadimgurl(String otherHeadimgurl) {
		this.otherHeadimgurl = otherHeadimgurl;
	}
	public String getPayModel() {
		return payModel;
	}
	public void setPayModel(String payModel) {
		this.payModel = payModel;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
  
}
