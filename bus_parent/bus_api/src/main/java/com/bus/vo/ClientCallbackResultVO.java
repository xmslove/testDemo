package com.bus.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author xms
 *
 * @param <T>
 */
public class ClientCallbackResultVO<T> implements Serializable {
	
     /**
	 * 
	 */
	private static final long serialVersionUID = -4585793476844115920L;
	
	private List<T> data;
    private Boolean success = true;
    private String errorMsg;
    private String errorCode;
    private String appKey;
    
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
