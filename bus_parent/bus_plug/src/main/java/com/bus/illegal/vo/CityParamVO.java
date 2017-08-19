package com.bus.illegal.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class CityParamVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6929170721010614855L;
	/**
	 * N  默认全部，省份简写，如：ZJ、JS
	 */
	private String province;
	/**
	 * N  返回数据格式：json或xml或jsonp,默认json
	 */
	private String dtype;		
	/**
	 * N 格式选择1或2，默认1
	 */
	private Integer format = 1;	
	/**
	 * N  返回格式选择jsonp时，必须传递
	 */
	private String callback;		
	/**
	 * Y  你申请的key
	 */
	private String key;
	
	private String cityName;
	
	private String city;
	
	private String hphm;
	
	private String hpzl;
	
	private String engineno;
	
	private String classno;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public Integer getFormat() {
		return format;
	}
	public void setFormat(Integer format) {
		this.format = format;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	
}
