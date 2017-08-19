package com.bus.baidu.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.alibaba.fastjson.JSON;
import com.bus.baidu.vo.BaiDuVO;
import com.bus.baidu.vo.geoconyVO;
import com.bus.weixin.httpclient.LocalHttpClient;

public class BaiDuApi {
	 
	public static BaiDuVO getRoundCar(String lat,String lng,String radius,String ak){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(apiUrl.SEARSH_CAR)
				.addParameter("query", "汽车服务")
				.addParameter("location", lat+","+lng)
				.addParameter("radius",radius)
				.addParameter("output","json")
				.addParameter("ak",ak)
			    .build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaiDuVO.class);
	}
	
	
	// http://api.map.baidu.com/place/v2/search?q=%E9%A5%AD%E5%BA%97&region=%E5%8C%97%E4%BA%AC&output=json&ak=A2XpQOhszWj4loFSuBKGjUXu93iM7zZT
	
	public static BaiDuVO getShreah(String q,String cityName,String ak){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(apiUrl.SEARSH_CAR)
				.addParameter("query", q)
				.addParameter("region",cityName)
				.addParameter("output","json")
				.addParameter("ak",ak)
			    .build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaiDuVO.class);
	}
	
	
	public static void main(String[] args) {
		String q = "武汉大学";
		String cityName ="湖北省";
		String ak = "A2XpQOhszWj4loFSuBKGjUXu93iM7zZT";
		BaiDuVO  result = getShreah(q,cityName,ak);
		System.out.println(JSON.toJSON(result));
	}
	
	
	
	
	public static geoconyVO geoconyTo(String lat,String lng,String ak){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(apiUrl.GEOCONV)
				.addParameter("coords", lng+","+lat)
				.addParameter("from", "1")
				.addParameter("to","5")
				.addParameter("output","json")
				.addParameter("ak",ak)
			    .build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,geoconyVO.class);
	}
	
	
	public static CoordinateVO getCoordinate(String lat,String lng,String ak){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(apiUrl.GEOCODER)
				.addParameter("location", lat+","+lng)
				.addParameter("pois", "1")
				.addParameter("output","json")
				.addParameter("ak",ak)
			    .build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,CoordinateVO.class);
	}
}
