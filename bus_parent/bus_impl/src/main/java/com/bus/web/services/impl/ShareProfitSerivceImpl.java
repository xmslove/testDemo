package com.bus.web.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.core.util.pingjing4jUtil;
import com.bus.dao.IActivationDao;
import com.bus.dao.IShareProfitDao;
import com.bus.exception.UnifiedException;
import com.bus.util.ExceptionMsgUtil;
import com.bus.vo.MchMsgVO;
import com.bus.vo.StaffMsgVO;
import com.bus.web.services.IShareProfitSerivce;

/**
 * 
 * @author xms
 *
 */
@Service("shareProfitSerivce")
public class ShareProfitSerivceImpl implements IShareProfitSerivce{
	
	@Resource 
	private IShareProfitDao shareProfitDao;
	@Resource 
	private IActivationDao activationDao;
	
	
	@Override
	@Transactional
	public String addMch(String mchName, String mchAddress, String mchUserName,
			String mchUserPhone,String brandNo,String openid) {
		String[] mchNos = pingjing4jUtil.getHeadByString(mchName);
		String mchNo = mchNos[0]+mchNos[1]+activationDao.getMchNo().toString();
		shareProfitDao.addMch(brandNo,mchNo,mchName,mchAddress,mchUserName,mchUserPhone,openid);
		return mchNo;
	}


	@Override
	public MchMsgVO findMchMsgByNo(String mchNo) {
		return shareProfitDao.findMchMsgByNo(mchNo);
	}


	@Override
	public void addStaff(String mchNo, String staffPhone, String staffName,String openid) {
		shareProfitDao.addStaff(mchNo,staffPhone,staffName,openid);
	}


	@Override
	@Transactional
	public void addStaffAndImsi(String mchNo, String staffPhone,
			String staffName, String imsi,String openid) {
		shareProfitDao.addStaffAndImsi(mchNo, staffPhone, staffName, imsi,openid);
		//查询用户当前套餐是否为年费套餐
		Integer flag = shareProfitDao.findPackageType(imsi);
		if(flag!=0){
			//添加到返利表
			shareProfitDao.addShareProfit(openid,imsi,mchNo);
		}
	}


	@Override
	public Integer selectImsi(String imsi) {
		return shareProfitDao.selectImsi(imsi);
	}


	@Override
	public Integer selectStaff(String imsi) {
		return shareProfitDao.selectStaff(imsi);
	}


	@Override
	public Integer otherStaff(String staffPhone, String imsi) {
		return shareProfitDao.otherStaff(staffPhone,imsi);
	}


	@Override
	public Integer selectMch(String brandNo, String openid) {
		return shareProfitDao.selectMch(brandNo,openid);
	}


	@Override
	public MchMsgVO selectMchMsg(String brandNo, String openid) throws UnifiedException{
		try {
			return shareProfitDao.selectMchMsg(brandNo,openid);
		} catch (Exception e) {
			ExceptionMsgUtil.setError(1, "001", e);
		}
		return null;
		}


	@Override
	public MchMsgVO findMchMsgByName(String mchName) {
		return shareProfitDao.findMchMsgByName(mchName);
	}


	@Override
	public MchMsgVO selectStaffMsg(String openid) {
		return null;
	}


	@Override
	public Integer selectStaffRegister(String openid) {
		return shareProfitDao.selectStaffRegister(openid);
	}


	@Override
	public StaffMsgVO findStaffMsgByOpenid(String openid){
		return shareProfitDao.findStaffMsgByOpenid(openid);
	}


	@Override
	public Boolean isValidImsi(String imsi) {
		Boolean flag = false ;
		Integer vi = shareProfitDao.isValidImsi(imsi);
		if(vi==1){
			flag = true ;
		}
		return flag;
	}


	@Override
	public StaffMsgVO isStaffMeTiedCard(String openid, String imsi) {
		return shareProfitDao.isStaffMeTiedCard(openid,imsi);
	}


	@Override
	public StaffMsgVO isStaffOtherTiedCard(String openid, String imsi) {
		return shareProfitDao.isStaffOtherTiedCard(openid,imsi);
	}


	@Override
	public Boolean isValidTimeImsi(String imsi) {
		Boolean flag = false ;
		Integer time = shareProfitDao.isValidTimeImsi(imsi);
		if(time!=null && time<=24){
			flag = true ;
		}
		return flag;
	}


	@Override
	public void addStaffTiedCard(String openid, String imsi) {
		//销售绑卡
		shareProfitDao.addStaffTiedCard(openid,imsi);
		//查询销售对应的商家编号
		String mchNo = shareProfitDao.findMchNoByOpenid(openid);
		//查询用户当前套餐是否为年费套餐
				Integer flag = shareProfitDao.findPackageType(imsi);
				if(flag!=0){
					//添加到返利表
					shareProfitDao.addShareProfit(openid,imsi,mchNo);
				}
	}
}
