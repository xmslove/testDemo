package com.bus.vo;

import java.io.Serializable;

public class IllegalMsgVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -342298750957576307L;
	
	private Integer id;
	/**
	 * 车牌
	 */
	private String hphm;
	/**
	 * 发动机号
	 */
	private String engineno;
	/**
	 * 车架号
	 */
	private String classno;
	
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public String getEngineno() {
		return engineno;
	}
	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}
	public String getClassno() {
		return classno;
	}
	public void setClassno(String classno) {
		this.classno = classno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
