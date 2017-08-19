package com.bus.vo;

import java.io.Serializable;


/**
 * 
 * @author xms
 *
 */
public class PageUtilVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116840696715210386L;
	
	private Integer sEcho;
	
	private Integer iDisplayStart; 
	
	private Integer iDisplayLength;
	
	private String sSearch;

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
}
