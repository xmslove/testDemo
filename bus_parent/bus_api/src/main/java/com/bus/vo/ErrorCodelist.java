package com.bus.vo;

public enum ErrorCodelist {
	ERROR01("01","获取微信失败"),
	ERROR02("02","获取微信服务器地址失败"),
	ERROR03("03","未激活成功");
	
	private String error_code;
	private String error_message;
	
	private ErrorCodelist(String error_code,String error_message){
		this.error_code = error_code;
		this.error_message = error_message;
	}
	
	public static String getErrorCode(String error_code){
		ErrorCodelist ec = null;
		for (ErrorCodelist ErrorCode : values()) {
			if(error_code==ErrorCode.error_code){
				ec=ErrorCode;
			}
		}
		return ec.error_message;
	}

}
