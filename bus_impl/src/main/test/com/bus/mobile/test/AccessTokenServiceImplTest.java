package com.bus.mobile.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bus.mobile.services.IAccessTokenService;
import com.bus.util.test.BaseJunit4Test;
import com.bus.vo.ClientCallbackResultVO;

public class AccessTokenServiceImplTest extends BaseJunit4Test{
	
	@Autowired
	private IAccessTokenService accessTokenService;

	@Test
	public void testGetEasyUpToken() throws Exception {
		ClientCallbackResultVO<String> result = accessTokenService.getEasyUpToken();
		System.out.println(result.getAppKey());
		assertTrue(result.getAppKey()!=null);
	}

	@Test
	public void testGetCoverUpToken() {
	}

	@Test
	public void testGetCallbackToken() {
	}

}
