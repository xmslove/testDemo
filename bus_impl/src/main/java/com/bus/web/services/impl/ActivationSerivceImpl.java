package com.bus.web.services.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.dao.IActivationDao;
import com.bus.vo.Constant;
import com.bus.wangyi.api.SNSApi;
import com.bus.wangyi.vo.SNSVO;
import com.bus.web.services.IActivationSerivce;

@Service("activationSerivce")
public class ActivationSerivceImpl implements IActivationSerivce{
	
	@Resource  
    private IActivationDao activationDao;

	@Override
	public Boolean sendSNS(String mobile, String deviceId, Integer templateid) {
		Boolean flag = false ;
		SNSVO result = SNSApi.sendSNS(mobile, deviceId, templateid, Constant.WANGYI_APPKEY, Constant.WANGYI_APPSECRET);
		if(result.getCode().equals("200")){
			flag = true ;
		}
		return flag;
	}

	@Override
	public Boolean verifycode(String mobile, String code) {
		Boolean flag = false ;
		SNSVO result = SNSApi.verifyCode(mobile, code,Constant.WANGYI_APPKEY, Constant.WANGYI_APPSECRET);
		if(result.getCode().equals("200")){
			flag = true ;
		}
		return flag;
	}

	@Override
	@Transactional
	public void addUser(String imsi,String imei,String phone, String openid) {
		String uuid = UUID.randomUUID().toString();
		//查询用户表记录是否存在
		Integer ac = activationDao.findUser(phone,openid);
		if(ac==0){
			activationDao.addUser(uuid,phone,openid);
		};
		activationDao.addImsiMsg(imsi,imei,phone,openid);
		/*查询用户默认套餐
		PackageVO vo = activationDao.getDefaultPackage();
		激活流程更改只有关注之后才能添加免费套餐
		activationDao.addUserPackage(imsi,Integer.parseInt(vo.getDayCeiling()),vo.getWifiCeiling());*/
	}

	@Override
	public Boolean vildPhone(String phone, String imsi) {
		Boolean flag = false ;
		Integer user = activationDao.vildPhone(phone,imsi);
		if(user==0){
			flag = true ;
		};
		return flag;
	}

	@Override
	public String getUserPhone(String imsi) {
		return activationDao.getUserPhone(imsi);
	}

	@Override
	public String getAppVersion() {
		return activationDao.getAppVersion();
	}

	@Override
	@Transactional
	public void deleteMe(String openid) {
		List<String> imsiList = activationDao.getImsi(openid);
		//清除车主表
		activationDao.deleteUser(openid);
		//清除商家表
		activationDao.deleteMch(openid);
		//清除销售表
		activationDao.deleteStaff(openid);
		//清除返利
		activationDao.deleteMoney(openid);
		
		for (String imsi : imsiList) {
			//清除imsi表
			activationDao.deleteImsi(imsi);
			//清除用户详情表
			activationDao.deleteUserMsg(imsi);
		}
	}

}
