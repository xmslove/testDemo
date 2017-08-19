package com.bus.web.services.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.dao.IActivationDao;
import com.bus.vo.WeiXinPayVO;
import com.bus.web.services.IWeiXinPayService;
import com.bus.weixin.api.PayMchAPI;
import com.bus.weixin.vo.Closeorder;
import com.bus.weixin.vo.DownloadbillResult;
import com.bus.weixin.vo.MchBaseResult;
import com.bus.weixin.vo.MchDownloadbill;
import com.bus.weixin.vo.MchOrderInfoResult;
import com.bus.weixin.vo.MchOrderquery;
import com.bus.weixin.vo.Unifiedorder;
import com.bus.weixin.vo.UnifiedorderResult;

@Service("weiXinPayService")
public class WeiXinPayServiceImpl implements IWeiXinPayService{
	@Resource  
    private IActivationDao activationDao;
	
	@Override
	@Transactional
	public UnifiedorderResult pay(WeiXinPayVO vo,String key)
			throws Exception {
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(vo.getAppid());
		unifiedorder.setAttach(vo.getAttach());
		unifiedorder.setBody(vo.getBody());
		unifiedorder.setDevice_info(vo.getDevice_info());
		unifiedorder.setGoods_tag(vo.getGoods_tag());
		unifiedorder.setLimit_pay(vo.getLimit_pay());
		unifiedorder.setMch_id(vo.getMch_id());
		unifiedorder.setNonce_str(vo.getNonce_str());
		unifiedorder.setNotify_url(vo.getNotify_url());
		unifiedorder.setOpenid(vo.getOpenid());
		unifiedorder.setOut_trade_no(vo.getOut_trade_no());
		unifiedorder.setProduct_id(vo.getProduct_id());
		//unifiedorder.setSign(vo.getSign());
		unifiedorder.setSpbill_create_ip(vo.getSpbill_create_ip());
		unifiedorder.setTime_expire(vo.getTime_expire());
		unifiedorder.setTime_start(vo.getTime_start());
		unifiedorder.setTotal_fee(String.valueOf(vo.getTotal_fee()));
		unifiedorder.setTrade_type(vo.getTrade_type());
		UnifiedorderResult  payResult = PayMchAPI.payUnifiedorder(unifiedorder,key);
		return payResult;
	}
    
	@Override
	public MchOrderInfoResult queryTheOrder(
			MchOrderquery mchOrderquery, String key) {
		MchOrderInfoResult  theOrder =PayMchAPI.payOrderquery(mchOrderquery,key);
		return theOrder;
	}

	@Override
	public MchBaseResult payCloseorder(Closeorder closeorder,String key) {
		MchBaseResult  theOrder =PayMchAPI.payCloseorder(closeorder,key);
		return theOrder;
	}

	
	@Override
	public DownloadbillResult payDownloadbill(
			MchDownloadbill downloadbill, String key) {
		DownloadbillResult  theOrder =PayMchAPI.payDownloadbill(downloadbill,key);
		return theOrder;
	}

	@Override
	public String getOrderNo() {
		Format format = new SimpleDateFormat("yyyyMMdd");
		Integer order = Integer.parseInt(format.format(new Date()));
		Integer no = activationDao.getOrderNo();
		return order.toString()+no.toString();
	};
	
}
