package com.bus.weixin.vo;

import java.util.List;

import com.bus.weixin.xml.BaseResult;

public class UserInfoList extends BaseResult {

	private List<User> user_info_list;

	public List<User> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<User> user_info_list) {
		this.user_info_list = user_info_list;
	}

}
