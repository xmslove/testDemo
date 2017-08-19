package com.bus.vo;

import java.io.Serializable;

/**
 * 
 * @author xms
 *
 */
public class StaffMsgVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8284339647687213924L;
	
	private Integer id;
	/**
	 * 商户编号
	 */
	private String mchNo;
	/**
	 * 销售人名称
	 */
	private String staffName;
	/**
	 * 销售人手机号
	 */
	private String staffPhone;
	/**
	 * 绑定的imsi
	 */
	private String imsiIdList;
	/**
	 * 销售人微信openid
	 */
	private String staffUserOpenid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMchNo() {
		return mchNo;
	}
	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPhone() {
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	public String getImsiIdList() {
		return imsiIdList;
	}
	public void setImsiIdList(String imsiIdList) {
		this.imsiIdList = imsiIdList;
	}
	public String getStaffUserOpenid() {
		return staffUserOpenid;
	}
	public void setStaffUserOpenid(String staffUserOpenid) {
		this.staffUserOpenid = staffUserOpenid;
	}
}
