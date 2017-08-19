package com.bus.vo;

import java.io.Serializable;

/**
 * 
 * @author xms
 *
 */
public class PackageVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365369431867655911L;
	
	private Integer id;
	/**
	 * 套餐类型
	 */
	private Integer packageType;
	/**
	 * 套餐名字
	 */
	private String packageName;
	/**
	 * 套餐价格
	 */
	private String packagePrice;
	/**
	 * 套餐的图片
	 */
	private String packagePhoto;
	/**
	 * 套餐流量上线
	 */
	private String trafficCeiling;
	/**
	 * 套餐时长（单位：月）
	 */
	private String dayCeiling;
	/**
	 * 套餐是否有效
	 */
	private Integer packageStatus;
	/**
	 * 套餐wifi时长（单位M）
	 */
	private String wifiCeiling;
	/**
	 * 套餐是否允许下载
	 */
	private String ifDownload;
	/**
	 * 套餐是否观看视频
	 */
	private String ifVideo;
	/**
	 * 套餐是否浏览网页
	 */
	private String ifWebpage;
	/**
	 * 套餐最大连接人数
	 */
	private String maxUser;
	/**
	 * 套餐最大上传速度
	 */
	private String uploadSpeed;
	/**
	 * 套餐最大下载速度
	 */
	private String downloadSpeed;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPackageType() {
		return packageType;
	}
	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(String packagePrice) {
		this.packagePrice = packagePrice;
	}
	public String getTrafficCeiling() {
		return trafficCeiling;
	}
	public void setTrafficCeiling(String trafficCeiling) {
		this.trafficCeiling = trafficCeiling;
	}
	public String getDayCeiling() {
		return dayCeiling;
	}
	public void setDayCeiling(String dayCeiling) {
		this.dayCeiling = dayCeiling;
	}
	public Integer getPackageStatus() {
		return packageStatus;
	}
	public void setPackageStatus(Integer packageStatus) {
		this.packageStatus = packageStatus;
	}
	public String getWifiCeiling() {
		return wifiCeiling;
	}
	public void setWifiCeiling(String wifiCeiling) {
		this.wifiCeiling = wifiCeiling;
	}
	public String getIfDownload() {
		return ifDownload;
	}
	public void setIfDownload(String ifDownload) {
		this.ifDownload = ifDownload;
	}
	public String getIfVideo() {
		return ifVideo;
	}
	public void setIfVideo(String ifVideo) {
		this.ifVideo = ifVideo;
	}
	public String getIfWebpage() {
		return ifWebpage;
	}
	public void setIfWebpage(String ifWebpage) {
		this.ifWebpage = ifWebpage;
	}
	public String getMaxUser() {
		return maxUser;
	}
	public void setMaxUser(String maxUser) {
		this.maxUser = maxUser;
	}
	public String getUploadSpeed() {
		return uploadSpeed;
	}
	public void setUploadSpeed(String uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}
	public String getDownloadSpeed() {
		return downloadSpeed;
	}
	public void setDownloadSpeed(String downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}
	public String getPackagePhoto() {
		return packagePhoto;
	}
	public void setPackagePhoto(String packagePhoto) {
		this.packagePhoto = packagePhoto;
	}
}
