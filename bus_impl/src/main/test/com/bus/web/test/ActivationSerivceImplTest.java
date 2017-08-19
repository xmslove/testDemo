package com.bus.web.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bus.util.test.BaseJunit4Test;
import com.bus.web.services.IActivationSerivce;

public class ActivationSerivceImplTest extends BaseJunit4Test{
	
	@Autowired
	private IActivationSerivce activationSerivce;

	@Test
	public void testSendSNS() {
		String mobile = "13296653397";
		String deviceId =  null;
		int templateid = 3040107;
		activationSerivce.sendSNS(mobile, deviceId, templateid);
	}

	@Test
	public void testVerifycode() {
		String mobile = "13296653397";
		String code = "1232";
		activationSerivce.verifycode(mobile, code);
	}

	@Test
	public void testAddUser() {
	}

	@Test
	public void testVildPhone() {
	}

	@Test
	public void testGetUserPhone() {
	}

	@Test
	public void testGetAppVersion() {
	}

	@Test
	public void testDeleteMe() {
	}

}
