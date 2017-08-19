package com.bus.mobile.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.bus.validate.ValidateArgument;
import com.bus.validate.rule.MaxLengthValidate;
import com.bus.validate.rule.NotNullValidate;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.UserInfoVO;

/**
 * 
 * @author xms
 *
 */

@Path("/user")
@Produces("application/json") 
public interface IUserService {
	
	/**
	 * 判断imsi是否激活；
	 * 返回车主基本信息
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getUserInfo")
	@Produces("application/json;charset=UTF-8")
	public ClientCallbackResultVO<UserInfoVO> getUserInfo(
			@QueryParam("imsi") String imsi)throws Exception;
    
    /**
     * 用户套餐购买时间
     * @param imsi
     * @return
     * @throws Exception
     */
	@GET
	@Path("/userPayTime")
	@Produces("text/plain;charset=UTF-8")
	public String userPayTime(@QueryParam("imsi")String imsi)throws Exception;
	
	/**
	 * 用户上报流量
	 * @param vo
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/addUserTraffic")
	@Produces("application/json;charset=UTF-8")
	public ClientCallbackResultVO<String>  addUserTraffic(@FormParam("")UserInfoVO vo,@FormParam("imsi")String imsi,@FormParam("imei")String imei)throws Exception;
	
	/**
	 * 查询用户当前套餐是否可以观看视频
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/userIsVoide")
	@Produces("text/plain;charset=UTF-8")
	public String userIsVoide(@QueryParam("imsi")String imsi)throws Exception;
	
	/**
	 * 查询用户当前以使用的普通流量和wifi流量
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getUserTrafficInfo")
	@Produces("application/json;charset=UTF-8")
	public UserInfoVO getUserTrafficInfo(@QueryParam("imsi")String imsi)throws Exception;
	
	
	/**
	 * 测试单个设备推送
	 * @param pushVO
	 * @return
	 * @throws Exception
	 *//*
	@POST
	@Path("/sendOneDeivce")
	@Produces("application/json;charset=UTF-8")
	public ResultVO sendOneDeivce(@FormParam("") PushVO pushVO) throws Exception;
	
	*//**
	 * 测试停复机接口
	 * @param paramVO
	 * @return
	 * @throws Exception
	 *//*
	@POST
	@Path("/testIot")
	@Produces("application/json;charset=UTF-8")
	public iotResultVO testIot(@FormParam("") iotParamVO paramVO) throws Exception;*/
	
	
	/**
	 * 判断是否激活
	 * 以及关注微信公众号
	 * 已经是否购买套餐
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/isUserActivation")
	@Produces("application/json;charset=UTF-8")
	public UserInfoVO isUserActivation(@QueryParam("imsi")String imsi)throws Exception;
	
	/**
	 * 判断是否激活
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/isActivation")
	@Produces("application/json;charset=UTF-8")
	public UserInfoVO isActivation(@QueryParam("imsi")String imsi)throws Exception;
	
	/**
	 * 
	 * @param imsi
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/isTheAnnualFee")
	@Produces("application/json;charset=UTF-8")
	public UserInfoVO isTheAnnualFee(@QueryParam("imsi")String imsi)throws Exception;

}
