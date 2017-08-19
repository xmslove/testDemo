package com.bus.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bus.vo.PackageVO;
import com.bus.vo.UserPackageVO;
import com.bus.vo.ismiUserVO;

public interface IWeiXinScanDao {

	ismiUserVO getUserInfo(@Param("imsi")String imsi, @Param("phone")String phone);

	PackageVO selectPackageType(@Param("packageType")Integer packageType);

	void addUserPackage(@Param("packageType")Integer packageType, 
			@Param("imsi_id")String imsi_id, 
			@Param("dayCeiling")Integer dayCeiling,
			@Param("wifiCeiling")String wifiCeiling);

	UserPackageVO getUserPackageMsg(@Param("imsi_id")String imsi_id, @Param("packageType")Integer packageType);

	void playOrder(@Param("imsi_id")String imsi_id, @Param("packageType")Integer packageType,
			@Param("packagePrice") String packagePrice,@Param("orderNo")String orderNo);

	List<PackageVO> getAllPackage();

	PackageVO getPackageMsg(@Param("packageType")Integer packageType);

	void otherPlayOrder(@Param("imsi_id")String imsi_id, @Param("packageType")Integer packageType,
			@Param("packagePrice") String packagePrice,@Param("orderNo")String orderNo,@Param("openid")String openid);

	void editOrderNo(@Param("orderNo")String orderNo);

	Integer findPackageTypeByOrder(@Param("orderNo")String orderNo);

	Date getOtherTime(@Param("orderNo")String orderNo);

	Integer isPay(@Param("imsi_id")String imsi_id);

	void addIllegalMsg(@Param("openid")String openid, @Param("imsi")String imsi, @Param("hphm")String hphm,
			@Param("engineno")String engineno, @Param("classno")String classno);

	List<String> getIIllegalImsi(@Param("openid")String openid);

}
