package com.bus.weixin.api;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.bus.api.util.EmojiUtil;
import com.bus.weixin.httpclient.LocalHttpClient;
import com.bus.weixin.vo.User;
import com.bus.weixin.vo.UserInfoList;

/**
 * User API
 */
public class UserAPI extends BaseAPI{

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
	public static User userInfo(String access_token,String openid,int emoji){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI+"/cgi-bin/user/info")
				.addParameter(getATPN(),access_token)
				.addParameter("openid",openid)
				.addParameter("lang","zh_CN")
				.build();
		User user = LocalHttpClient.executeJsonResult(httpUriRequest,User.class);
		if(emoji != 0 && user != null && user.getNickname() != null){
			user.setNickname_emoji(EmojiUtil.parse(user.getNickname(), emoji));
		}
		return user;
	}
	
	/**
	 * 获取用户基本信息
	 * @param access_token access_token
	 * @param openid openid
	 * @return User
	 */
	public static User userInfo(String access_token,String openid){
		return userInfo(access_token, openid, 0);
	}


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
	public static UserInfoList userInfoBatchget(String access_token,String lang,List<String> openids,int emoji){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"user_list\": [");
		for(int i = 0;i < openids.size();i++){
			sb.append("{")
			  .append("\"openid\": \"").append(openids.get(i)).append("\",")
			  .append("\"lang\": \"").append(lang).append("\"")
			  .append("}").append(i==openids.size()-1?"":",");
		}
		sb.append("]}");
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI+"/cgi-bin/user/info/batchget")
				.addParameter(getATPN(),access_token)
				.setEntity(new StringEntity(sb.toString(), Charset.forName("utf-8")))
				.build();
		UserInfoList userInfoList = LocalHttpClient.executeJsonResult(httpUriRequest,UserInfoList.class);
		if(emoji != 0 && userInfoList != null && userInfoList.getUser_info_list() != null){
			for(User user : userInfoList.getUser_info_list()){
				if(user.getNickname() != null){
					user.setNickname_emoji(EmojiUtil.parse(user.getNickname(), emoji));
				}
			}
		}
		return userInfoList;
	}
	
	/**
	 * 批量获取用户基本信息
	 * @param access_token access_token
	 * @param lang	zh-CN
	 * @param openids 最多支持一次拉取100条
	 * @return UserInfoList
	 */
	public static UserInfoList userInfoBatchget(String access_token,String lang,List<String> openids){
		return userInfoBatchget(access_token, lang, openids,0);
	}

}
