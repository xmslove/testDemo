package com.bus.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bus.vo.AppTrafficVO;
import com.bus.vo.PackageVO;
import com.bus.vo.UserInfoVO;

public interface IActivationDao {

	void addUser(@Param("uuid")String uuid, @Param("phone")String phone, @Param("openid")String openid);

	void addImsiMsg(@Param("imsi")String imsi, @Param("imei")String imei,
			        @Param("phone")String phone,@Param("openid")String openid);

	void addUserPackage(@Param("packageType")Integer packageType,@Param("imsi")String imsi, @Param("day")Integer day, @Param("wifi")String wifi);

	PackageVO getDefaultPackage();

	Integer getOrderNo();
	
	Integer getMchNo();

	Integer vildPhone(@Param("phone")String phone,@Param("imsi") String imsi);

	Integer toActivate(@Param("imsi")String imsi);

	List<UserInfoVO> getUserInfo(@Param("imsi")String imsi);

	String getUserPhone(@Param("imsi")String imsi);

	Integer findUser(@Param("phone")String phone, @Param("openid")String openid);

	void addUserTraffic(@Param("vo")UserInfoVO vo, @Param("imsi")String imsi);

	Date userPayTime(@Param("imsi")String imsi);

	Integer selectUserPackageType(@Param("imsi")String imsi);

	String userIsVoide(@Param("packageType")Integer packageType);

	UserInfoVO getUserTrafficInfo(@Param("imsi")String imsi);

	String getAppVersion();

	List<String> getImsi(@Param("openid")String openid);

	void deleteUser(@Param("openid")String openid);

	void deleteImsi(@Param("imsi")String imsi);

	void deleteUserMsg(@Param("imsi")String imsi);

	void deleteMch(@Param("openid")String openid);

	void deleteStaff(@Param("openid")String openid);

	void deleteMoney(@Param("openid")String openid);

	UserInfoVO isUserActivation(@Param("imsi")String imsi);
	
	Integer isTheAnnualFee(@Param("imsi")String imsi);

	Date findFailureDate(@Param("imsi")String imsi);

	void editUserTrafficStatus(@Param("imsi")String imsi);

	void appUpload(@Param("imsi")String imsi, @Param("imei")String imei, @Param("appUploadTime")Date appUploadTime,
			@Param("avList")List<AppTrafficVO> avList, @Param("trafficNum")String trafficNum);

	void testRollbackDataResouse();
}
