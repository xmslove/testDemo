package com.bus.weixin.vo;

import com.bus.weixin.xml.BaseResult;

public class jsapiVO extends BaseResult{
   private String ticket ;
   
   private String expires_in ;
   
public String getTicket() {
	return ticket;
}
public void setTicket(String ticket) {
	this.ticket = ticket;
}
public String getExpires_in() {
	return expires_in;
}
public void setExpires_in(String expires_in) {
	this.expires_in = expires_in;
}
   
   
}
