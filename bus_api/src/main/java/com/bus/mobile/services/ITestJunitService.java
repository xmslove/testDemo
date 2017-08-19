package com.bus.mobile.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 
 * @author xms
 *
 */
@Path("/testJunit")  
@Produces("application/json") 
public interface ITestJunitService {
	
	/**
	 * 测试junit单元测试事务回滚
	 */
	@GET
	@Path("/testRollbackDataResouse")
	@Produces("application/json;charset=UTF-8")
	void testRollbackDataResouse();

}
