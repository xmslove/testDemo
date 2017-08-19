package com.bus.web.services;

import com.bus.vo.WeixinVO;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.SnsToken;
import com.bus.weixin.vo.User;
import com.bus.weixin.xml.BaseResult;

/**
 * 微信权限接口
 * @author xms
 *
 */
public interface IWeiXinPermissionService {
    
	/**
	 * 获取微信token
	 * @return
	 */
	public APPToken getToken();
	
	/**
	 * 获取服务器IP
	 * 用于设置回调ip白名单
	 * @param access_token
	 * @return
	 */
	public WeixinVO getServerIp(String access_token);
	
	/**
	 * 通过code换取网页授权access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public SnsToken oauth2AccessToken(
			String appid,
			String secret,
			String code);
	
	/**
	 * 通过code换取网页授权access_token (第三方平台开发)
	 * @param appid
	 * @param code
	 * @param component_appid
	 * @param component_access_token
	 * @return
	 */
	public SnsToken oauth2ComponentAccessToken(
			String appid,
			String code,
			String component_appid,
			String component_access_token);
	
	/**
	 * 刷新access_token
	 * @param appid
	 * @param refresh_token
	 * @return
	 */
	public SnsToken oauth2RefreshToken(
			String appid,
			String refresh_token);
	
	/**
	 * 刷新access_token (第三方平台开发)
	 * @param appid
	 * @param refresh_token
	 * @param component_appid
	 * @param component_access_token
	 * @return
	 */
	public SnsToken oauth2ComponentRefreshToken(
			String appid,
			String refresh_token,
			String component_appid,
			String component_access_token
			);
	
	/**
	 * 检验授权凭证（access_token）是否有效
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public BaseResult auth(
			String access_token,
			String openid
			);
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * @param access_token access_token
	 * @param openid openid
	 * @param lang 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @param emoji 表情解析方式<br>
	 * 0 		  不设置 <br>
	 * 1 HtmlHex 格式<br>
	 * 2 HtmlTag 格式<br>
	 * 3 Alias  格式<br>
	 * 4 HtmlDec 格式<br>
	 * 5 PureText 纯文本<br>
	 * @return User
	 */
	public User userinfo(
			String access_token,
			String openid,
			String lang,
			String emoji);
	
	/**
	 * 生成网页授权 URL  (第三方平台开发)
	 * @param appid appid
	 * @param redirect_uri 自动URLEncoder
	 * @param snsapi_userinfo snsapi_userinfo
	 * @param state 可以为空
	 * @param component_appid 第三方平台开发，可以为空。
	 * 服务方的appid，在申请创建公众号服务成功后，可在公众号服务详情页找到
	 * @return url
	 */
	public String connectOauth2Authorize(
			String appid,
			String redirect_uri,
			boolean snsapi_userinfo,
			String state,
			String component_appid);
	
	/**
	 * 生成网页授权 URL  (网站应用微信登录)
	 * @param appid appid
	 * @param redirect_uri 自动URLEncoder
	 * @param state 可以为空
	 * @return url
	 */
	public String connectQrconnect(
			String appid,
			String redirect_uri,
			String state);
}
