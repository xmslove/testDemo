package com.bus.dao;

import org.apache.ibatis.annotations.Param;

import com.bus.vo.MchMsgVO;
import com.bus.vo.StaffMsgVO;

/**
 * 
 * @author xms
 *
 */
public interface IShareProfitDao {

	void addMch(@Param("brandNo")String brandNo,@Param("mchNo") String mchNo, @Param("mchName")String mchName,
			@Param("mchAddress")String mchAddress, @Param("mchUserName")String mchUserName, @Param("mchUserPhone")String mchUserPhone,
			@Param("openid")String openid);

	MchMsgVO findMchMsgByNo(@Param("mchNo")String mchNo);

	void addStaff(@Param("mchNo")String mchNo, @Param("staffPhone")String staffPhone, @Param("staffName")String staffName,@Param("openid")String openid);
	
	void addStaffAndImsi(@Param("mchNo")String mchNo, @Param("staffPhone")String staffPhone, @Param("staffName")String staffName, @Param("imsi")String imsi,@Param("openid")String openid);

	Integer selectImsi(@Param("imsi")String imsi);

	Integer selectStaff(@Param("imsi")String imsi);

	Integer otherStaff(@Param("staffPhone")String staffPhone,@Param("imsi") String imsi);

	Integer selectMch(@Param("brandNo")String brandNo, @Param("openid")String openid);

	MchMsgVO selectMchMsg(@Param("brandNo")String brandNo, @Param("openid")String openid);

	MchMsgVO findMchMsgByName(@Param("mchName")String mchName);

	Integer selectStaffRegister(@Param("openid")String openid);

	StaffMsgVO findStaffMsgByOpenid(@Param("openid")String openid);

	Integer isValidImsi(@Param("imsi")String imsi);

	StaffMsgVO isStaffMeTiedCard(@Param("openid")String openid, @Param("imsi")String imsi);

	StaffMsgVO isStaffOtherTiedCard(@Param("openid")String openid, @Param("imsi")String imsi);

	Integer isValidTimeImsi(@Param("imsi")String imsi);

	void addStaffTiedCard(@Param("openid")String openid, @Param("imsi")String imsi);

	String findMchNoByOpenid(@Param("openid")String openid);

	void addShareProfit(@Param("openid")String openid, @Param("imsi")String imsi, @Param("mchNo")String mchNo);

	Integer findPackageType(@Param("imsi")String imsi);
}
