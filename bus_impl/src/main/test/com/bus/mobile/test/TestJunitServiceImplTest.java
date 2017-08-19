package com.bus.mobile.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bus.mobile.services.ITestJunitService;
import com.bus.util.test.BaseJunit4Test;

public class TestJunitServiceImplTest extends BaseJunit4Test{
	@Autowired
	private ITestJunitService testJunitService;

	@Test
	public void testTestRollbackDataResouse() {
		testJunitService.testRollbackDataResouse();
	}

}
