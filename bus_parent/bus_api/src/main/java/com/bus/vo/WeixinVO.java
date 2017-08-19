package com.bus.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 * 获取微信服务器IP地址
 */
public class WeixinVO implements Serializable{
   
	private static final long serialVersionUID = -3078995949572667558L;
	private String ip ;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	} 
	
	
}
