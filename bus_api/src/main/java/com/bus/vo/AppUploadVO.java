package com.bus.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author xms
 *
 */
/**
 * @author xms
 *
 */
public class AppUploadVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7777109069512114267L;
	
   private List<AppTrafficVO>  appUploadMsg;

   public List<AppTrafficVO> getAppUploadMsg() {
	return appUploadMsg;
    }

   public void setAppUploadMsg(List<AppTrafficVO> appUploadMsg) {
	this.appUploadMsg = appUploadMsg;
   	}
   
   

}
