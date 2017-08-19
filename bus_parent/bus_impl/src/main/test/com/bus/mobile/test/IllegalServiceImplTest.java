package com.bus.mobile.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.bus.illegal.vo.CityParamVO;
import com.bus.illegal.vo.IllegalResultVO;
import com.bus.mobile.services.IIllegalService;
import com.bus.util.test.BaseJunit4Test;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.IllegalMsgVO;
import com.bus.vo.LineVO;
import com.bus.vo.MoveLineVO;

public class IllegalServiceImplTest extends BaseJunit4Test{
	
	@Autowired
	private  IIllegalService iIllegalService;
    
	/**
	 * 测试违章查询
	 * @throws Exception
	 */
	@Test
	public void testFindillegal() throws Exception {
		CityParamVO vo = new CityParamVO();
		vo.setCityName("武汉");
		vo.setHphm("鄂A22SY0");
		vo.setClassno("LVHFC1667G6064037");
		vo.setEngineno("1064045");
		IllegalResultVO result = iIllegalService.findillegal(vo);
		System.out.println(JSON.toJSON(result));
		assertTrue(result!=null);
	}
    
	/**
	 * 测试是否有违章录入信息
	 */
	@Test
	public void testIsIllegal() {
		ClientCallbackResultVO<IllegalMsgVO> result = iIllegalService.isIllegal("898600B10116F0012203");
		System.out.println("是否有违章录入信息："+JSON.toJSON(result));
		assertTrue(result!=null);
	}
    
	
	/**
	 * 测试车辆轨迹上报
	 */
	@Test
	public void testMoveLine() {
		MoveLineVO moveLineVO = new MoveLineVO();
		ClientCallbackResultVO<String> result = iIllegalService.moveLine(moveLineVO);
		assertTrue(result!=null);
	}
    
	
	/**
	 * 测试得到昨天车辆运行轨迹的经纬度集合
	 */
	@Test
	public void testGetAllLine() {
		String openid = "89014103211118510720";
		String selectDate = "2017-10-10 10:10:10";
		List<LineVO>  result = iIllegalService.getAllLine(openid,selectDate);
		System.out.println(JSON.toJSON(result));
		assertTrue(result!=null);
	}
     
	/**
	 * 测试远程拍照
	 * @throws IOException 
	 */
	@Test
	public void testUploadPhoto() throws IOException {
		Attachment attachments = null;
		HttpServletRequest request = null;
		String imsi = null;
		attachments.getDataHandler().getInputStream();
		InputStream in = new FileInputStream(new File("D://1.txt"));
        
		//iIllegalService.uploadPhoto(attachments, request, imsi);
		
	}
    
	/**
	 * 测试远程监控
	 */
	@Test
	public void testUploadVide() {
	}
    
	/**
	 * 测试得到远程拍照的照片
	 */
	@Test
	public void testGetPhoto() {
	}
    
	/**
	 * 测试远程监控的视频
	 */
	@Test
	public void testGetVoide() {
	}

}
