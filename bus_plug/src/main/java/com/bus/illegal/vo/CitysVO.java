package com.bus.illegal.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class CitysVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402810014954821569L;
	/**
	 * 是否需要车架号0,不需要 1,需要
	 */
	private String classa;
	/**
	 * 城市名称
	 */
	private String city_name;
	/**
	 * 	是否需要发动机号0:不需要 1:需要
	 */
	private String engine;
	/**
	 * 
	 */
	private String registno;
	/**
	 * 需要几位发动机号0:全部 1-9 :需要发动机号后N位
	 */
	private String engineno;
	/**
	 * 
	 */
	private String regist;
	/**
	 * 
	 */
	private String abbr;
	/**
	 * 	城市代码
	 */
	private String city_code;
	/**
	 * 	需要几位车架号0:全部 1-9: 需要车架号后N位
	 */
	private String classno;
	
	public String getClassa() {
		return classa;
	}
	public void setClassa(String classa) {
		this.classa = classa;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getRegistno() {
		return registno;
	}
	public void setRegistno(String registno) {
		this.registno = registno;
	}
	public String getEngineno() {
		return engineno;
	}
	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}
	public String getRegist() {
		return regist;
	}
	public void setRegist(String regist) {
		this.regist = regist;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getClassno() {
		return classno;
	}
	public void setClassno(String classno) {
		this.classno = classno;
	}
	
}
