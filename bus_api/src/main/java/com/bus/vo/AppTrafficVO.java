package com.bus.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class AppTrafficVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2771605852367703982L;
	
	/**
	 * 包名
	 */
	private String appPackageName;
	/**
	 * app名称
	 */
	private String appName;
	/**
	 * app流量消耗
	 */
	private String appTrafficExpend;
	
	public String getAppPackageName() {
		return appPackageName;
	}
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
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
	
	

}
