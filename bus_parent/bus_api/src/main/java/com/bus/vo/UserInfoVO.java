package com.bus.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author xms
 *
 */
public class UserInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4913107818957441356L;
	
    private String  imsi;
	
	private String  imei;
	
	private String  busType;
	
	private String  userPhone;
	
	private Date  imsireTime;
	
	private String  wifiLength;
	
	private String  trafficNum;
	
	private String  packageDay;
	
	private String packageName;
	
	private Integer  packageType;
	
	private Date payPlayTime;
	
	private String openid;
	
	private Boolean activation = false ;
	
	private Boolean Subscribe = false ;
	
	private Boolean annualFee = false ;
	/**
	 * 包名
	 */
	private String appPackageName;
	/**
	 * 上报时间
	 */
	private Date appUploadTime;
	/**
	 * app名称
	 */
	private String appName;
	/**
	 * app流量消耗
	 */
	private String appTrafficExpend;
	/***
	 * app详情
	 */
	private String appTrafficMsg;

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getImsireTime() {
		return imsireTime;
	}

	public void setImsireTime(Date imsireTime) {
		this.imsireTime = imsireTime;
	}

	public String getWifiLength() {
		return wifiLength;
	}

	public void setWifiLength(String wifiLength) {
		this.wifiLength = wifiLength;
	}

	public String getTrafficNum() {
		return trafficNum;
	}

	public void setTrafficNum(String trafficNum) {
		this.trafficNum = trafficNum;
	}

	public String getPackageDay() {
		return packageDay;
	}

	public void setPackageDay(String packageDay) {
		this.packageDay = packageDay;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public Date getPayPlayTime() {
		return payPlayTime;
	}

	public void setPayPlayTime(Date payPlayTime) {
		this.payPlayTime = payPlayTime;
	}

	public Boolean getActivation() {
		return activation;
	}

	public void setActivation(Boolean activation) {
		this.activation = activation;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Boolean getSubscribe() {
		return Subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		Subscribe = subscribe;
	}

	public Boolean getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(Boolean annualFee) {
		this.annualFee = annualFee;
	}

	public String getAppPackageName() {
		return appPackageName;
	}

	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}

	public Date getAppUploadTime() {
		return appUploadTime;
	}

	public void setAppUploadTime(Date appUploadTime) {
		this.appUploadTime = appUploadTime;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppTrafficExpend() {
		return appTrafficExpend;
	}

	public void setAppTrafficExpend(String appTrafficExpend) {
		this.appTrafficExpend = appTrafficExpend;
	}

	public String getAppTrafficMsg() {
		return appTrafficMsg;
	}

	public void setAppTrafficMsg(String appTrafficMsg) {
		this.appTrafficMsg = appTrafficMsg;
	}
}
