package com.bus.web.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.dao.IActivationDao;
import com.bus.dao.IWeiXinScanDao;
import com.bus.vo.PackageVO;
import com.bus.vo.UserPackageVO;
import com.bus.vo.ismiUserVO;
import com.bus.web.services.IWeiXinScanService;

@Service("weiXinScanService")
public class WeiXinScanServiceImpl implements IWeiXinScanService {

	@Resource
	private IWeiXinScanDao weiXinDao;
	@Resource  
    private IActivationDao activationDao;

	@Override
	public ismiUserVO jump(String imsi, String phone) {
		ismiUserVO vo = new ismiUserVO();
		vo = weiXinDao.getUserInfo(imsi, phone);
		return vo;
	}

	@Override
	@Transactional
	public void playOrder(String imsi_id, String packageType,
			String packagePrice,String orderNo) {
		/* 添加用户购买记录 */
		weiXinDao.playOrder(imsi_id, Integer.parseInt(packageType),
				packagePrice,orderNo);
	}

	@Override
	public List<PackageVO> getAllPackage() {
		List<PackageVO> resultList = new ArrayList<PackageVO>();
		List<PackageVO> data = weiXinDao.getAllPackage();
		for (PackageVO packageVO : data) {
			if (packageVO.getTrafficCeiling().equals("-")) {
				packageVO.setTrafficCeiling("无限制");
			}
			;
			if (packageVO.getIfVideo().equals("2")) {
				packageVO.setIfVideo("不支持");
			}
			;
			if (packageVO.getWifiCeiling().equals("-")) {
				packageVO.setWifiCeiling("无限制");
			}
			resultList.add(packageVO);
		}
		return resultList;
	}

	@Override
	public PackageVO getPackageMsg(String packageType) {
		return weiXinDao.getPackageMsg(Integer.parseInt(packageType));
	}

	@Override
	@Transactional
	public void otherPlayOrder(String imsi_id, String packageType,
			String packagePrice, String orderNo, String openid) {
		/* 添加用户购买记录 */
		weiXinDao.otherPlayOrder(imsi_id, Integer.parseInt(packageType),
				packagePrice,orderNo,openid);
	}

	@Override
	@Transactional
	public void editOrderNo(String orderNo,String imsi_id) {
		weiXinDao.editOrderNo(orderNo);
		//查询购买的套餐类型
		Integer packageType = weiXinDao.findPackageTypeByOrder(orderNo);
		//查询开通的套餐详情
		PackageVO vo = weiXinDao.selectPackageType(packageType);
		Integer dayCeiling = Integer.parseInt(vo.getDayCeiling());// 开通套餐的时长(单位：月)
		String wifiCeiling = vo.getWifiCeiling();// 开通套餐的wifi时长
		//查询是否有赠送的套餐
		Integer isPay = weiXinDao.isPay(imsi_id);
		if(isPay!=null && isPay>0){
			//********** 一期开通套餐方案：之前的天数，剩余wifi作废 *********//*
			weiXinDao.addUserPackage(packageType, imsi_id,dayCeiling, wifiCeiling);
			// 查询当前imsi的套餐情况（开通的套餐名称，用户套餐过期时间）
		//	return weiXinDao.getUserPackageMsg(imsi_id,packageType);
		}else{
			//激活流程更改只有关注之后才能添加免费套餐
			activationDao.addUserPackage(packageType,imsi_id,Integer.parseInt(vo.getDayCeiling()),vo.getWifiCeiling());
		}
	}

	@Override
	public UserPackageVO getUserPackageMsg(String imsi_id, String packageType) {
		return weiXinDao.getUserPackageMsg(imsi_id,Integer.parseInt(packageType));
	}

	@Override
	public Date getOtherTime(String orderNo) {
		return weiXinDao.getOtherTime(orderNo);
	}


	@Override
	public List<String> getImsi(String openid) {
		return activationDao.getImsi(openid);
	}

	@Override
	public void addIllegalMsg(String openid, String imsi, String hphm,
			String engineno, String classno) {
		weiXinDao.addIllegalMsg(openid,imsi,hphm,engineno,classno);
		
	}

	@Override
	public List<String> getIIllegalImsi(String openid) {
		return weiXinDao.getIIllegalImsi(openid);
	}

}
