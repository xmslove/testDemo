package com.bus.mobile.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import com.bus.illegal.vo.CityParamVO;
import com.bus.illegal.vo.IllegalResultVO;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.IllegalMsgVO;
import com.bus.vo.LineVO;
import com.bus.vo.MoveLineVO;
import com.bus.vo.PhotoVO;

@Path("/mobleCar")  
@Produces("application/json") 
public interface IIllegalService {
	
	
	/**
	 * 违章查询接口
	 * @param vo
	 * @return
	 */
	@POST
	@Path("/illegal")
	@Produces("application/json;charset=UTF-8")
	IllegalResultVO findillegal(@FormParam("") CityParamVO vo)throws Exception;
	
	/**
	 * 判断车主是否录入违章查询信息
	 * @param imsi
	 * @return
	 */
	@GET
	@Path("/isIllegal")
	@Produces("application/json;charset=UTF-8")
	ClientCallbackResultVO<IllegalMsgVO> isIllegal(@QueryParam("imsi")String imsi);
	
	/**
	 * 客户端轨迹上报
	 * @param imsi
	 * @return
	 */
	@POST
	@Path("/moveLine")
	@Produces("application/json;charset=UTF-8")
	ClientCallbackResultVO<String> moveLine(@FormParam("")MoveLineVO moveLineVO);
	
	/**
	 * 回去轨迹坐标
	 * @param openid
	 * @return
	 */
	@GET
	@Path("/getAllLine")
	@Produces("application/json;charset=UTF-8")
	List<LineVO> getAllLine(@QueryParam("openid")String openid,@QueryParam("selectDate")String selectDate);
	
	
	/**
	 * 图片上传
	 * @param attachments
	 * @return
	 * @throws IOException
	 */
	@POST
	@Path("/uploadPhoto")
	@Produces("application/json;charset=UTF-8")
	@Consumes("multipart/form-data;charset=UTF-8")
	ClientCallbackResultVO<String> uploadPhoto(List<Attachment>attachments,@Context HttpServletRequest request,@HeaderParam("imsi") String imsi)throws IOException;
    
	
	/**
	 * 得到最新的远程拍照的相片
	 * @param imsi
	 * @return
	 */
	@GET
	@Path("/getPhoto")
	@Produces("application/json;charset=UTF-8")
	PhotoVO getPhoto(@QueryParam("imsi")String imsi);
	
	/**
	 * 得到最新的远程视频
	 * @param imsi
	 * @return
	 */
	@GET
	@Path("/getVoide")
	@Produces("text/plain;charset=UTF-8")
	String getVoide(@QueryParam("imsi")String imsi);
	
	
	
	@POST
	@Path("/uploadVide")
	@Consumes("multipart/form-data;charset=UTF-8")
	ClientCallbackResultVO<String> uploadVide(List<Attachment>attachments,@Context HttpServletRequest request,@HeaderParam("imsi") String imsi)throws IOException;
    
	@GET
	@Path("/removeIllegal")
	@Consumes("application/json;charset=UTF-8")
	void removeIllegal(Integer id);
}
