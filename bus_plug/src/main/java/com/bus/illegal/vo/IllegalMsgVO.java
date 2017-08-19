package com.bus.illegal.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author xms
 *
 */
public class IllegalMsgVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6426631870997545076L;
	
	private String province;
	
	private String city;
	
	private String hphm;
	
	private String hpzl;
	
	private List<IllegalHistoryVO> lists;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public List<IllegalHistoryVO> getLists() {
		return lists;
	}

	public void setLists(List<IllegalHistoryVO> lists) {
		this.lists = lists;
	}
	
	

}
