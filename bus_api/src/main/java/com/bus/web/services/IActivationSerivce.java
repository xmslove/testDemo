package com.bus.web.services;


public interface IActivationSerivce {
	
	
	/**
	 * 发送短信验证码
	 */
	public Boolean sendSNS(String mobile,String deviceId,Integer templateid);
	
	
	
	/**
	 * 验证短信验证码
	 */
	public Boolean verifycode(String mobile,String code);


	/**
	 * 用户激活入库（返回用户标准套餐）
	 */
	public void addUser(String imsi,String imei,String phone, String openid);


    /**
     * 验证imsi是否激活过
     * @param phone
     * @param imsi
     * @return
     */
	public Boolean vildPhone(String phone, String imsi);


    /**
     * 获取车主手机号
     * @param imsi
     * @return
     */
	public String getUserPhone(String imsi);


    /**
     * 获取当前版本号
     * @return
     */
	public String getAppVersion();


    /**
     * 测试使用 清除个人所有数据
     * @param openid
     */
	public void deleteMe(String openid);
	
}
