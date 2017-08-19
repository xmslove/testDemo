package com.bus.vo;

import java.io.Serializable;
/**
 * 
 * @author xumengsi
 *
 */
public class PhotoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图片路径
	 */
	private String photoUrl;
	/**
	 * 拍照时间
	 */
	private String photoOp;
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getPhotoOp() {
		return photoOp;
	}
	public void setPhotoOp(String photoOp) {
		this.photoOp = photoOp;
	}

}
