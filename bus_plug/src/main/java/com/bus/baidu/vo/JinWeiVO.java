package com.bus.baidu.vo;

import java.io.Serializable;

public class JinWeiVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -606168228006655951L;
	
	private String lat;
	
	private String lng;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}	

}
