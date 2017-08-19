package com.bus.baidu.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author xms
 *
 */
public class BaiDuVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -175632769370654217L;
	
	private int status;
	
	private String message;
	
	private List<resultVO> results; 
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<resultVO> getResults() {
		return results;
	}

	public void setResults(List<resultVO> results) {
		this.results = results;
	}

}
