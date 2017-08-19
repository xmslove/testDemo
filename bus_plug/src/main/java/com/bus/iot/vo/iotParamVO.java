package com.bus.iot.vo;
/**
 * 
 * @author xms
 *
 */
public class iotParamVO extends iotBaseVO{
	/**
	 * 开关机状态  00：正常；01：停机
	 */
	private String user_status ;
	/**
	 * 套餐流量包 套餐流量包 如500M、1G等,参数传值时带单位(K、M、G)
	 */
	private String flow_package ;
	/**
	 * 流量叠加包 如500M、1G等,参数传值时带单位(K、M、G)
	 */
	private String flow_overlay_package ;
	/**
	 * 卡号(多个卡号以英文逗号隔开，最多200个)
	 */
	private String cardId;

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getFlow_overlay_package() {
		return flow_overlay_package;
	}

	public void setFlow_overlay_package(String flow_overlay_package) {
		this.flow_overlay_package = flow_overlay_package;
	}

	public String getFlow_package() {
		return flow_package;
	}

	public void setFlow_package(String flow_package) {
		this.flow_package = flow_package;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}
