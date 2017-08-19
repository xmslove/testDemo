package com.bus.baidu.api;
/**
 * 
 * @author xms
 *
 */
public abstract class apiUrl {
	
	/**
	 * 根据经纬度圆形区域检索
	 */
	protected static final String SEARSH_CAR = "http://api.map.baidu.com/place/v2/search";
	
	/**
	 * 微信转换百度经纬度
	 */
	protected static final String GEOCONV = "http://api.map.baidu.com/geoconv/v1/";
	
	/**
	 * 
	 */
	protected static final String GEOCODER = "http://api.map.baidu.com/geocoder/v2/";
	
}
