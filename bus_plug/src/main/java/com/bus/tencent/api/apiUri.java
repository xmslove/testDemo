package com.bus.tencent.api;
/**
 * 腾讯信鸽接口URL
 * @author xms
 *
 */
public abstract class apiUri {
	
	/**
	 * 协议描述
	 * 请求URL结构为：
	 * http://接口域名/v2/class/method?params
	 * v2：表示当前api的版本号 暂时只有v2版本
	 * class:提供的接口类别
	 * method:每个接口大类提供的具体操作接口
	 * {}占位符
	 */
 public static final String BASE_URL = "http://openapi.xg.qq.com/v2/{0}/{1}" ;
 /**
  * 签名需要用到的
  */
 public static final String URL = "openapi.xg.qq.com/v2/{0}/{1}" ;
}
