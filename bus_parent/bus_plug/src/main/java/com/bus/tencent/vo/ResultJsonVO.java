package com.bus.tencent.vo;

import java.io.Serializable;

/**
 * 
 * @author xms
 *
 */
public class ResultJsonVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8148144544619121650L;
	
	/**
	 * 表示给app下发的任务id
	 */
	private String push_id;

	public String getPush_id() {
		return push_id;
	}

	public void setPush_id(String push_id) {
		this.push_id = push_id;
	}

}
