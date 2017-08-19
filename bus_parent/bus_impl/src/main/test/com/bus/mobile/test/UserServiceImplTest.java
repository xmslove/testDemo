package com.bus.mobile.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bus.mobile.services.IUserService;
import com.bus.util.test.BaseJunit4Test;

public class UserServiceImplTest extends BaseJunit4Test{
	
	@Autowired //自动注入  
    private IUserService userService;

	@Test
	public void testGetUserInfo() throws Exception {
		userService.getUserInfo("123");
	}

	@Test
	public void testAddUserTraffic() {
	}

	@Test
	public void testUserPayTime() {
	}

	@Test
	public void testUserIsVoide() {
	}

	@Test
	public void testGetUserTrafficInfo() {
	}

	@Test
	public void testIsUserActivation() {
	}

	@Test
	public void testIsActivation() {
	}

	@Test
	public void testIsTheAnnualFee() {
	}

}
