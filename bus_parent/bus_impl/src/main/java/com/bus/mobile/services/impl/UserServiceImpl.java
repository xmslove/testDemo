package com.bus.mobile.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bus.dao.IActivationDao;
import com.bus.iot.api.iotApi;
import com.bus.iot.vo.iotParamVO;
import com.bus.iot.vo.iotResultVO;
import com.bus.mobile.services.IUserService;
import com.bus.vo.AppTrafficVO;
import com.bus.vo.AppUploadVO;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.Constant;
import com.bus.vo.ErrorCodelist;
import com.bus.vo.UserInfoVO;
import com.bus.weixin.api.APPTokenAPI;
import com.bus.weixin.api.UserAPI;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.User;
/**
 * 
 * @author xms
 *
 */
@Named
public class UserServiceImpl implements IUserService{
    @Inject
	private IActivationDao activationDao;
	
	@Override
	public ClientCallbackResultVO<UserInfoVO> getUserInfo(String imsi)throws Exception {
		ClientCallbackResultVO<UserInfoVO> result = new ClientCallbackResultVO<UserInfoVO>();
		Integer flag = activationDao.toActivate(imsi);
		if(flag!=0){
			List<UserInfoVO> data =  activationDao.getUserInfo(imsi);
			result.setData(data);
		}else{
			result.setSuccess(false);
			result.setErrorCode("03");
			result.setErrorMsg(ErrorCodelist.getErrorCode("03"));
		};
		return result;
	}

	@Override
	@Transactional
	public ClientCallbackResultVO<String> addUserTraffic(UserInfoVO vo,String imsi,String imei)
			throws Exception {
		ClientCallbackResultVO<String> result = new ClientCallbackResultVO<String>();
		try {
			//流量入库
			activationDao.addUserTraffic(vo,imsi);
			//上报app详情
			String trafficNum = vo.getTrafficNum();
			Date  appUploadTime = vo.getAppUploadTime();//上报时间
			String appUploadMsg = vo.getAppTrafficMsg();//流量上报详情
			AppUploadVO av = JSON.parseObject(appUploadMsg,AppUploadVO.class);
			List<AppTrafficVO>  avList = av.getAppUploadMsg();
			activationDao.appUpload(imsi,imei,appUploadTime,avList,trafficNum);
			//查询用户流量套餐过期时间
			Date failureDate = activationDao.findFailureDate(imsi);
			Date nowDate = new Date();
			if(!compareDate(failureDate,nowDate)){//true:套餐过期停卡
				String transId = UUID.randomUUID().toString();
				iotParamVO paramVO = new iotParamVO();
				paramVO.setColumn_name(Constant.IOT_ICCID);
				paramVO.setColumn_value(imsi);
				paramVO.setUser_status(Constant.IOT_STOP);
				paramVO.setUserId(Constant.IOT_USER_ID);
				paramVO.setUserPwd(Constant.IOT_USER_PWD);
				paramVO.setTransId(transId);
				iotResultVO ir = iotApi.editIotStatus(paramVO);
				if(ir.getCode().equals("0000")){
					activationDao.editUserTrafficStatus(imsi);
				}
			}
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	
	
	
	/**  
	 * 比较两个日期之间的大小  
	 *   
	 * @param d1  
	 * @param d2  
	 * @return 前者大于后者返回true 反之false  
	 */    
	private boolean compareDate(Date d1, Date d2) {    
	    Calendar c1 = Calendar.getInstance();    
	    Calendar c2 = Calendar.getInstance();    
	    c1.setTime(d1);    
	    c2.setTime(d2);    
	    
	    int result = c1.compareTo(c2);    
	    if (result >= 0)    
	        return true;    
	    else    
	        return false;    
	} 

	@Override
	public String userPayTime(String imsi) throws Exception {
		Date time = activationDao.userPayTime(imsi);
		return String.valueOf(time.getTime());
	}

	@Override
	public String userIsVoide(String imsi) throws Exception {
		//查询用户当前套餐
		Integer packageType = activationDao.selectUserPackageType(imsi);
		//查询用户当前套餐是否允许观看视频
		return activationDao.userIsVoide(packageType);
	}

	@Override
	public UserInfoVO getUserTrafficInfo(String imsi) throws Exception {
		return activationDao.getUserTrafficInfo(imsi);
	}

	/*@Override
	public ResultVO sendOneDeivce(PushVO pushVO) throws Exception {
		pushVO.setAccess_id(Constant.QQ_ACCESS_ID);
		pushVO.setTimestamp(Long.toString(System.currentTimeMillis() / 1000));
		return sendPushApi.sendOneDevice(pushVO, Constant.QQ_SECRET_KEY);
	}*/

	@Override
	public UserInfoVO isUserActivation(String imsi) throws Exception {
		UserInfoVO user = new UserInfoVO();
		//判断用户是否激活
		UserInfoVO vo = activationDao.isUserActivation(imsi);
		if(vo!=null){
			user.setActivation(true);
		}else{
			return user;
		};
		String openid = vo.getOpenid();
		APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
	    User users = UserAPI.userInfo(tokens.getAccess_token(),openid);
	    if(users!=null && users.getSubscribe()==1){
	    	user.setSubscribe(true);
	    	};
	    Integer annualFee = activationDao.isTheAnnualFee(imsi);
	    if(annualFee!=null && annualFee>=0){
	    	user.setAnnualFee(true);
	    }
		return user;
	}

	@Override
	public UserInfoVO isActivation(String imsi) throws Exception {
		UserInfoVO user = new UserInfoVO();
		//判断用户是否激活
		UserInfoVO vo = activationDao.isUserActivation(imsi);
		if(vo!=null){
			user.setActivation(true);
			}
		return user;
	}

	@Override
	public UserInfoVO isTheAnnualFee(String imsi) throws Exception {
		UserInfoVO user = new UserInfoVO();
		Integer annualFee = activationDao.isTheAnnualFee(imsi);
	    if(annualFee!=null){
	    	user.setAnnualFee(true);
	    }
		return user;
	}

	/*@Override
	public iotResultVO testIot(iotParamVO paramVO) throws Exception {
		paramVO.setColumn_name("iccid");
		paramVO.setColumn_value("89860616020014268416");
		paramVO.setUser_status("00");
		paramVO.setUserId("szsxc3582");
		paramVO.setUserPwd("szsxc3582");
		paramVO.setTransId("qweasd");
		return iotApi.editIotStatus(paramVO);
	}*/

}
