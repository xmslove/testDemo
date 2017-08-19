package com.bus.web.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bus.util.test.BaseJunit4Test;
import com.bus.web.services.IWeiXinUserService;

public class WeiXinUserServiceImplTest extends BaseJunit4Test{
	@Autowired
	private IWeiXinUserService weiXinUserService;

	@Test
	public void testUserInfo() {
	}

	@Test
	public void testUserInfoBatchget() {
	}

}
