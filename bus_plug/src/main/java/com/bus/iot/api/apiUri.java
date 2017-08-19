package com.bus.iot.api;

/**
 * 物联卡平台接口(百悟)
 * @author xms
 *
 */
public abstract class apiUri {
	
	/**
	 * 停复机操作接口
	 */
	public static final String EDIT_IOT_STATUS = "http://iot.hbsmservice.com:8096/iot_platform_interface/iotUpdateSwitchStatusInfo.do" ;
	/**
	 * 实时查询卡状态接口
	 */
	public static final String FIND_IOT_STATUS = "http://iot.hbsmservice.com:8096/iot_platform_interface/iotRealTimeSearchCardStatus.do" ;
    /**
     *基础套餐变更接口
     */
    public static final String UPDATE_IOT_PACKAGE = "http://iot.hbsmservice.com:8096/iot_platform_interface/iotUpdateOrderFlowInfo.do" ;
    /**
     * 叠加包套餐订购接口
     */
    public static final String ADD_IOT_PACKAGE = "http://iot.hbsmservice.com:8096/iot_platform_interface/iotUpdateOrderOverLayFlowInfo.do" ;
}
