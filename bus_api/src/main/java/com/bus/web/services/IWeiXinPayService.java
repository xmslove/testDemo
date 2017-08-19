package com.bus.web.services;

import com.bus.vo.WeiXinPayVO;
import com.bus.weixin.vo.Closeorder;
import com.bus.weixin.vo.DownloadbillResult;
import com.bus.weixin.vo.MchBaseResult;
import com.bus.weixin.vo.MchDownloadbill;
import com.bus.weixin.vo.MchOrderInfoResult;
import com.bus.weixin.vo.MchOrderquery;
import com.bus.weixin.vo.UnifiedorderResult;
/**
 * 微信支付接口
 * @author xms
 *
 */


public interface IWeiXinPayService {

	/**
	 * app发起支付
	 * 统一下单（微信支付；或者扫码支付【code_url：二维码链接地址】）
	 * @param vo
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public UnifiedorderResult  pay(WeiXinPayVO vo,String key)throws Exception;
	
	
	/**
	 * 查询订单
	 * @param mchOrderquery
	 * @param key
	 * @return
	 */
	public MchOrderInfoResult queryTheOrder(MchOrderquery mchOrderquery,String key);
	
	/**
	 * 关闭订单
	 * @param mchOrderquery
	 * @param key
	 * @return
	 */
	public MchBaseResult payCloseorder(Closeorder closeorder,String key);
	
	/**
	 * 下载对账单
	 * @param downloadbill
	 * @param key
	 * @return
	 */
	public DownloadbillResult payDownloadbill(MchDownloadbill downloadbill, String key);

    /**
     * 订单号生成规则
     * @return
     */
	public String getOrderNo();
}
