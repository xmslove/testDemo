package com.bus.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author xms
 *
 */
public class UserPackageVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3177384106168819080L;
	
	private String packageName;
	
	private Date packageFailureTime;
	
	private String imsi;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Date getPackageFailureTime() {
		return packageFailureTime;
	}

	public void setPackageFailureTime(Date packageFailureTime) {
		this.packageFailureTime = packageFailureTime;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

}
