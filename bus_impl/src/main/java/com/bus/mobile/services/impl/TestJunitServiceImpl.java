package com.bus.mobile.services.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.bus.dao.IActivationDao;
import com.bus.mobile.services.ITestJunitService;
/**
 * 
 * @author xms
 *
 */
@Named
public class TestJunitServiceImpl implements ITestJunitService{
	 @Inject
	 private IActivationDao activationDao;
	 
	 
	@Override
	public void testRollbackDataResouse() {
		activationDao.testRollbackDataResouse();
	}

}
