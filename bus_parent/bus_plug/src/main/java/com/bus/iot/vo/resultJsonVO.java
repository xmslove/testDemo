package com.bus.iot.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class resultJsonVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -624185652645612702L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
