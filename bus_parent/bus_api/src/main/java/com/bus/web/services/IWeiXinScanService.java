package com.bus.web.services;

import java.util.Date;
import java.util.List;

import com.bus.vo.PackageVO;
import com.bus.vo.UserPackageVO;
import com.bus.vo.ismiUserVO;


/**
 * @author xms
 *
 */
public interface IWeiXinScanService {
	
	public ismiUserVO jump(String imsi,String phone);
	
    /**
     * 购买下单
     * @param phone
     * @param packageType
     * @return
     */
	public void playOrder(String imsi_id, String packageType,String packagePrice,String orderNo);
	
	/**
	 * 返回套餐类型
	 * @return
	 */
	public List<PackageVO> getAllPackage();
	
    /**
     * 套餐详情
     * @param packageType
     * @return
     */
	public PackageVO getPackageMsg(String packageType);
    
	/**
	 * 代付下单
	 * @param imsi_id
	 * @param packageType
	 * @param packagePrice
	 * @param orderNo
	 * @param openid
	 * @return
	 */
	public void otherPlayOrder(String imsi_id, String packageType,
			String packagePrice, String orderNo, String openid);

	
	/**
	 * 修改订单状态
	 * @param orderNo
	 */
	public void editOrderNo(String orderNo,String imsi_id);
   
	/**
	 * 得到用户当前套餐详情
	 * @param imsi_id
	 * @param packageType
	 * @return
	 */
	public UserPackageVO getUserPackageMsg(String imsi_id, String packageType);

	public Date getOtherTime(String orderNo);
    
	/**
	 * 通过openid获取imsi
	 * @param openid
	 * @return
	 */
	public List<String> getImsi(String openid);
	
    /**
     * 录入违章查询信息
     * @param openid
     * @param imsi
     * @param hphm
     * @param engineno
     * @param classno
     */
	public void addIllegalMsg(String openid, String imsi, String hphm,
			String engineno, String classno);

	public List<String> getIIllegalImsi(String openid);
   
}
