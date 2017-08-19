package com.bus.mobile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.bus.vo.AppVersionVO;
import com.bus.vo.ClientCallbackResultVO;
/**
 * app升级接口
 * @author xms
 *
 */
@Path("/appVersion")
@Produces("application/json")
public interface IAppVersionService {

	
/**
 * 检查版本
 * @param platform
 * @param version
 * @return
 * @throws Exception
 */
@GET
@Path("/getAppVersion")
@Produces("application/json;charset=UTF-8")
public ClientCallbackResultVO<AppVersionVO>  getAppVersion(@QueryParam("version")String version)throws Exception;
}
