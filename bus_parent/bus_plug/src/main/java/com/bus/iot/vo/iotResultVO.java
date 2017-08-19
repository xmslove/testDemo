package com.bus.iot.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class iotResultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6769082951207860389L;
	
	private String code ; 
	
	private String description ;
	
	private String server_serial_number ;
	
	private String status;
	
	private resultJsonVO result ;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServer_serial_number() {
		return server_serial_number;
	}

	public void setServer_serial_number(String server_serial_number) {
		this.server_serial_number = server_serial_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public resultJsonVO getResult() {
		return result;
	}

	public void setResult(resultJsonVO result) {
		this.result = result;
	}
	
	
}
