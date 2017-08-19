package com.bus.baidu.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class resultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5987613679791594352L;
	
    private String name;
    
    private String address;
    
    private JinWeiVO location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public JinWeiVO getLocation() {
		return location;
	}

	public void setLocation(JinWeiVO location) {
		this.location = location;
	}

}
