package com.bus.mobile.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.bus.dao.IAppVersionDao;
import com.bus.mobile.services.IAppVersionService;
import com.bus.vo.AppVersionVO;
import com.bus.vo.ClientCallbackResultVO;

/**
 * 
 * @author xms
 *
 */
@Named
public class AppVersionServiceImpl implements IAppVersionService{
	@Inject
	private IAppVersionDao appVersionDao; 

	@Override
	public ClientCallbackResultVO<AppVersionVO> getAppVersion(String version)
			throws Exception {
ClientCallbackResultVO<AppVersionVO> result=new ClientCallbackResultVO<AppVersionVO>();
        
        List<AppVersionVO> data = appVersionDao.getAppVersion(version);
        
        result.setData(data);
        
        return  result;
	}

}
