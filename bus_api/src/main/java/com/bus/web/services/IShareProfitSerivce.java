package com.bus.web.services;

import com.bus.exception.UnifiedException;
import com.bus.vo.MchMsgVO;
import com.bus.vo.StaffMsgVO;

/**
 * 
 * @author xms
 *
 */
public interface IShareProfitSerivce {

	String addMch(String mchName, String mchAddress, String mchUserName,
			String mchUserPhone,String brandNo,String openid);

	MchMsgVO findMchMsgByNo(String mchNo);

	void addStaff(String mchNo, String staffPhone, String staffName,String openid);
	
	void addStaffAndImsi(String mchNo, String staffPhone, String staffName,String imsi,String openid);

	Integer selectImsi(String imsi);

	Integer selectStaff(String imsi);

	Integer otherStaff(String staffPhone, String imsi);

	Integer selectMch(String brandNo, String openid);

	MchMsgVO selectMchMsg(String brandNo, String openid) throws UnifiedException;

	MchMsgVO findMchMsgByName(String mchName);

	MchMsgVO selectStaffMsg(String openid);

	Integer selectStaffRegister(String openid);

	StaffMsgVO findStaffMsgByOpenid(String openid);
	//判断此卡是否激活
	Boolean isValidImsi(String imsi);
	//判断销售自己是否绑定过此卡
	StaffMsgVO isStaffMeTiedCard(String openid, String imsi);
	//判断别的销售是否绑定过此卡
	StaffMsgVO isStaffOtherTiedCard(String openid, String imsi);
	//判断此卡激活是否超过24小时
	Boolean isValidTimeImsi(String imsi);
	//销售已经注册 添加销售绑定卡
	void addStaffTiedCard(String openid, String imsi);

}
