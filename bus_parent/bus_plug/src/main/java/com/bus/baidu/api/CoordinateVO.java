package com.bus.baidu.api;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class CoordinateVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1638494032706484265L;
	
	private CoordinateCityVO result;

	public CoordinateCityVO getResult() {
		return result;
	}

	public void setResult(CoordinateCityVO result) {
		this.result = result;
	}
	
	

}
