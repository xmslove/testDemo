package com.bus.mobile.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.bus.dao.IIllegalDao;
import com.bus.illegal.api.illegalApi;
import com.bus.illegal.vo.CityParamVO;
import com.bus.illegal.vo.IllegalResultVO;
import com.bus.mobile.services.IIllegalService;
import com.bus.util.FileParserUtil;
import com.bus.vo.ClientCallbackResultVO;
import com.bus.vo.Constant;
import com.bus.vo.IllegalMsgVO;
import com.bus.vo.LineVO;
import com.bus.vo.MoveLineMsgVO;
import com.bus.vo.MoveLineVO;
import com.bus.vo.PhotoVO;
import com.bus.web.services.IWeiXinScanService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
@Named
public class IllegalServiceImpl implements IIllegalService{
	@Inject
	private IWeiXinScanService weiXinScanService;
	@Inject
	private IIllegalDao iIllegalDao;

	@Override
	public IllegalResultVO findillegal(CityParamVO vo) throws Exception {
		vo.setKey(Constant.ILLEGAL_APPKEY);
		return illegalApi.getIllegalResult(vo);
	}

	@Override
	public ClientCallbackResultVO<IllegalMsgVO> isIllegal(String imsi) {
		ClientCallbackResultVO<IllegalMsgVO> result = new ClientCallbackResultVO<IllegalMsgVO>();
		List<IllegalMsgVO> iIllegal = iIllegalDao.isIllegal(imsi);
		result.setData(iIllegal);
		return result;
	}

	@Override
	@Transactional
	public ClientCallbackResultVO<String> moveLine(MoveLineVO moveLineVO) {
		ClientCallbackResultVO<String> result = new ClientCallbackResultVO<String>();
		String imsi = moveLineVO.getImsi();
		String latlngs = moveLineVO.getLatlngs();
		List<MoveLineMsgVO> MoveLineList = JSONArray.parseArray(latlngs, MoveLineMsgVO.class);
		if(MoveLineList.size()!=0){
			iIllegalDao.moveLine(imsi,MoveLineList);
		}
		return result;
	}

	@Override
	public List<LineVO> getAllLine(String openid,String selectDate) {
		List<String> imsiList = weiXinScanService.getImsi(openid);
		String imsi = imsiList.size()!=0?imsiList.get(0):null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String time = df.format(new Date(new Date().getTime()-24*60*60*1000));
		if(selectDate!=null && !selectDate.equals("")){
			time = selectDate;
		}
		return iIllegalDao.getAllLine(imsi,time);
	}

	@Override
	public ClientCallbackResultVO<String> uploadPhoto(List<Attachment> attachments,HttpServletRequest request,String imsi)
			throws IOException {
		ClientCallbackResultVO<String> result = new ClientCallbackResultVO<String>();
		Configuration cfg = new Configuration(Zone.zone2());
		UploadManager uploadManager = new UploadManager(cfg);
		if (attachments.size() > 0)  
	        for (Attachment attach : attachments) {  
	            DataHandler dh = attach.getDataHandler();  
	            System.out.println(attach.getContentType().toString());  
	            if (attach.getContentType().toString().equals("text/plain")) {  
	                try {  
	                    System.out.println(dh.getName());   
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }else{  
	                try {
	                String path = request.getSession().getServletContext().getRealPath("")+"\\" + new String(dh.getName().getBytes("ISO-8859-1"),"UTF-8");	
	        	    FileParserUtil.writeToFile(dh.getInputStream(),path);
	        	    Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY, Constant.QINIU_SECRET_KEY);
	        	    String upToken = auth.uploadToken(Constant.QINIU_BUCKETNAME);
	        	    try {
	        	    	String key = imsi+"_"+System.currentTimeMillis()+".png";
	        	        Response response = uploadManager.put(path, key, upToken);
	        	        //解析上传成功的结果
	        	        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
	        	        System.out.println(putRet.key);
	        	        System.out.println(putRet.hash);
	        	        iIllegalDao.uploadPhoto(imsi,Constant.QINIU_URL+key);
	        	    } catch (QiniuException ex) {
	        	        Response r = ex.response;
	        	        System.err.println(r.toString());
	        	        result.setSuccess(false);
	        	        try {
	        	            System.err.println(r.bodyString());
	        	        } catch (QiniuException ex2) {
	        	        	result.setSuccess(false);
	        	        }
	        	    }
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    result.setSuccess(false);
	                }  
	            }  
	        }  
		return result;
	}

	@Override
	public ClientCallbackResultVO<String> uploadVide(List<Attachment> attachments, HttpServletRequest request,String imsi)
			throws IOException {
		ClientCallbackResultVO<String> result = new ClientCallbackResultVO<String>();
		Configuration cfg = new Configuration(Zone.zone2());
		UploadManager uploadManager = new UploadManager(cfg);
		if (attachments.size() > 0)  
	        for (Attachment attach : attachments) {  
	            DataHandler dh = attach.getDataHandler();  
	            System.out.println(attach.getContentType().toString());  
	            if (attach.getContentType().toString().equals("text/plain")) {  
	                try {  
	                    System.out.println(dh.getName());   
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }else{  
	                try {
	                String path = request.getSession().getServletContext().getRealPath("")+"\\" + new String(dh.getName().getBytes("ISO-8859-1"),"UTF-8");	
	        	    FileParserUtil.writeToFile(dh.getInputStream(),path);
	        	    Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY, Constant.QINIU_SECRET_KEY);
	        	    String upToken = auth.uploadToken(Constant.QINIU_BUCKETNAME);
	        	    try {
	        	    	String key = imsi+"_"+System.currentTimeMillis()+".mp4";
	        	        Response response = uploadManager.put(path, key, upToken);
	        	        //解析上传成功的结果
	        	        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
	        	        System.out.println(putRet.key);
	        	        System.out.println(putRet.hash);
	        	        iIllegalDao.uploadVide(imsi,Constant.QINIU_URL+key);
	        	    } catch (QiniuException ex) {
	        	        Response r = ex.response;
	        	        System.err.println(r.toString());
	        	        result.setSuccess(false);
	        	        try {
	        	            System.err.println(r.bodyString());
	        	        } catch (QiniuException ex2) {
	        	        	result.setSuccess(false);
	        	        }
	        	    }
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    result.setSuccess(false);
	                }  
	            }  
	        }  
		return result;
	}

	@Override
	public PhotoVO getPhoto(String imsi) {
		return iIllegalDao.getPhoto(imsi);
	}

	@Override
	public String getVoide(String imsi) {
		return iIllegalDao.getVoide(imsi);
	}

	@Override
	public void removeIllegal(Integer id) {
		iIllegalDao.removeIllegal(id);
	}

}
