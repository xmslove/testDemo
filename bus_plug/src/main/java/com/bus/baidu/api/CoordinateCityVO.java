package com.bus.baidu.api;

import java.io.Serializable;

public class CoordinateCityVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4146157747970969307L;
	
	private CityVO addressComponent;

	public CityVO getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(CityVO addressComponent) {
		this.addressComponent = addressComponent;
	}
	
	

}
