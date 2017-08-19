package com.bus.mobile.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.bus.mobile.services.IAppVersionService;
import com.bus.util.test.BaseJunit4Test;
import com.bus.vo.AppVersionVO;
import com.bus.vo.ClientCallbackResultVO;

public class AppVersionServiceImplTest extends BaseJunit4Test{
	
	@Autowired
	private IAppVersionService appVersionService;
    
	/**
	 * 测试版本更新
	 * @throws Exception
	 */
	@Test
	public void testGetAppVersion() throws Exception {
		String version = "1" ;
		ClientCallbackResultVO<AppVersionVO> result = appVersionService.getAppVersion(version);
		System.out.println(JSON.toJSON(result));
		assertTrue(result.getData()!=null);   
	}

}
