package com.bus.web.services;

import java.util.List;

import com.bus.weixin.vo.User;
import com.bus.weixin.vo.UserInfoList;
/**
 * 微信用户接口
 * @author xms
 *
 */
public interface IWeiXinUserService {
	
	/**
	 * 获取用户基本信息
	 * @param access_token access_token
	 * @param openid openid
	 * @param emoji 表情解析方式<br>
	 * 0 		  不设置 <br>
	 * 1 HtmlHex 格式<br>
	 * 2 HtmlTag 格式<br>
	 * 3 Alias  格式<br>
	 * 4 HtmlDec 格式<br>
	 * 5 PureText 纯文本<br>
	 * @return User
	 */
	public User userInfo(
			String access_token,
			String openid,
			String emoji);
	
	/**
	 * 批量获取用户基本信息
	 * @since 2.7.1
	 * @param access_token access_token
	 * @param lang	zh-CN
	 * @param openids 最多支持一次拉取100条
	 * @param emoji 表情解析方式<br>
	 * 0 		  不设置 <br>
	 * 1 HtmlHex 格式<br>
	 * 2 HtmlTag 格式<br>
	 * 3 Alias  格式<br>
	 * 4 HtmlDec 格式<br>
	 * 5 PureText 纯文本<br>
	 * @return UserInfoList
	 */
	public UserInfoList userInfoBatchget(
			String access_token,
		    String lang,
			List<String> openids,
			String emoji
			);

}
