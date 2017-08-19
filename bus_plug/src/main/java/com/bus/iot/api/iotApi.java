package com.bus.iot.api;

import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.bus.api.util.MapUtil;
import com.bus.api.util.SignatureUtil;
import com.bus.iot.vo.iotParamVO;
import com.bus.iot.vo.iotResultVO;
import com.bus.weixin.httpclient.LocalHttpClient;

/**
 * 
 * @author xms
 *
 */
public class iotApi {
	
	
  /**
   * 停复机操作接口	
   * @param paramVO
   * @return
   */
  public static iotResultVO  editIotStatus(iotParamVO paramVO){
  	Map<String,String> map = MapUtil.objectToMap(paramVO);
  	if(paramVO != null){
  		String sign = SignatureUtil.generateSignIot(map,1);
  		paramVO.setSign(sign);
  	};
  	HttpUriRequest httpUriRequest = RequestBuilder.post()
  			.setUri(apiUri.EDIT_IOT_STATUS)
  			.addParameter("userId",paramVO.getUserId())
  			.addParameter("transId",paramVO.getTransId())
  			.addParameter("column_name",paramVO.getColumn_name())
  			.addParameter("column_value",paramVO.getColumn_value())
  			.addParameter("user_status",paramVO.getUser_status())
  			.addParameter("sign",paramVO.getSign())
  			.build();
  	return LocalHttpClient.executeJsonResult(httpUriRequest,iotResultVO.class);
  }
  
  /**
   * 套餐更改接口	
   * @param paramVO
   * @return
   */
  public static iotResultVO  updateIotPackage(iotParamVO paramVO){
	  	Map<String,String> map = MapUtil.objectToMap(paramVO);
	  	if(paramVO != null){
	  		String sign = SignatureUtil.generateSignIot(map,2);
	  		paramVO.setSign(sign);
	  	};
	  	HttpUriRequest httpUriRequest = RequestBuilder.post()
	  			.setUri(apiUri.UPDATE_IOT_PACKAGE)
	  			.addParameter("userId",paramVO.getUserId())
	  			.addParameter("transId",paramVO.getTransId())
	  			.addParameter("column_name",paramVO.getColumn_name())
	  			.addParameter("column_value",paramVO.getColumn_value())
	  			.addParameter("flow_package",paramVO.getFlow_package())
	  			.addParameter("sign",paramVO.getSign())
	  			.build();
	  	return LocalHttpClient.executeJsonResult(httpUriRequest,iotResultVO.class);
	  }
  
  
  /**
   * 套餐叠加接口	
   * @param paramVO
   * @return
   */
  public static iotResultVO  addIotPackage(iotParamVO paramVO){
	  	Map<String,String> map = MapUtil.objectToMap(paramVO);
	  	if(paramVO != null){
	  		String sign = SignatureUtil.generateSignIot(map,3);
	  		paramVO.setSign(sign);
	  	};
	  	HttpUriRequest httpUriRequest = RequestBuilder.post()
	  			.setUri(apiUri.ADD_IOT_PACKAGE)
	  			.addParameter("userId",paramVO.getUserId())
	  			.addParameter("transId",paramVO.getTransId())
	  			.addParameter("column_name",paramVO.getColumn_name())
	  			.addParameter("column_value",paramVO.getColumn_value())
	  			.addParameter("flow_overlay_package",paramVO.getFlow_package())
	  			.addParameter("sign",paramVO.getSign())
	  			.build();
	  	return LocalHttpClient.executeJsonResult(httpUriRequest,iotResultVO.class);
	  }
  
  /**
   * 
   * @param paramVO
   * @return
   */
  public static iotResultVO  findIotStatus(iotParamVO paramVO){
	  	Map<String,String> map = MapUtil.objectToMap(paramVO);
	  	if(paramVO != null){
	  		String sign = SignatureUtil.generateSignIot(map,4);
	  		paramVO.setSign(sign);
	  	};
	  	HttpUriRequest httpUriRequest = RequestBuilder.post()
	  			.setUri(apiUri.FIND_IOT_STATUS)
	  			.addParameter("userId",paramVO.getUserId())
	  			.addParameter("transId",paramVO.getTransId())
	  			.addParameter("column_name",paramVO.getColumn_name())
	  			.addParameter("column_value",paramVO.getColumn_value())
	  			.addParameter("sign",paramVO.getSign())
	  			.build();
	  	return LocalHttpClient.executeJsonResult(httpUriRequest,iotResultVO.class);
	  } 
  
}
