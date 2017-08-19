package com.bus.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class RebateVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2445422012175853642L;
	
	private String id ;
	
	private String openid ;
	
	private String mchNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}
	
}
