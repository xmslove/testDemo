package com.bus.mobile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.bus.vo.ClientCallbackResultVO;

/**
 * 凭证管理
 * @author xms
 *
 */
@Path("/Token")
@Produces("application/json")  
public interface IAccessTokenService {

	/**
	 * 简单上传凭证 、断点上传凭证
	 */
	@GET
	@Path("/getEasyUpToken")
	@Produces("application/json;charset=UTF-8")
	public ClientCallbackResultVO<String>  getEasyUpToken() throws Exception;
	
	/**
	 * 覆盖上传凭证
	 */
	@GET
	@Path("/getCoverUpToken")
	@Produces("application/json;charset=UTF-8")
	public ClientCallbackResultVO<String>  getCoverUpToken(@QueryParam("key")String key) throws Exception;
	
	/**
	 * 上传回调凭证
	 */
	@GET
	@Path("/getCallbackToken")
	@Produces("application/json;charset=UTF-8")
	public ClientCallbackResultVO<String> getCallbackToken(@QueryParam("callBack")String callBack)throws Exception;
	
}
