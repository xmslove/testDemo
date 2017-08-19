package com.bus.baidu.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author xms
 *
 */
public class geoconyVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2907644401542491135L;
	
	private int status ; 
	
	private List<xyVO> result ;
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<xyVO> getResult() {
		return result;
	}

	public void setResult(List<xyVO> result) {
		this.result = result;
	}


	
}
