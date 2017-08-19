package com.bus.vo;

import java.io.Serializable;

import com.bus.weixin.vo.MchBase;
/**
 * 
 * @author xms
 *
 */
public class WeiXinPayResultVO extends MchBase implements Serializable{
	
	private static final long serialVersionUID = -8750004737791075375L;
	
	private String device_info;

	private String trade_type;

	private String prepay_id;

	private String code_url;

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

}
