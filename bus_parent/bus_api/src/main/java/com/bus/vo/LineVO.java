package com.bus.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class LineVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614504740596583206L;
    
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
