package com.bus.quartz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bus.api.util.SignatureUtil;
import com.bus.util.CollectionUtil;
import com.bus.util.HttpUtils;
import com.bus.util.PayUtil;
import com.bus.util.XmlUtil;
import com.bus.vo.Constant;
import com.bus.vo.RebateVO;
import com.bus.web.services.IRebateService;

public class MchRebateService {
	@Resource
	private IRebateService rebates;
	
	Logger logger = LoggerFactory.getLogger(MchRebateService.class);
	
	public void rebate(){
		logger.info("商家返利开始");
		List<RebateVO> cardList = rebates.findStaffList();
		for (RebateVO rebateVO : cardList) {
			String ids = rebateVO.getId();
			String [] ss= ids.split(",");
			List<String> list = Arrays.asList(ss);
			//该商家下面的销售当月激活的SM卡数量
			int actionCount =list.size(); 
			//查询该商家下面的总的销售
			int staffCount = rebates.staffNum(rebateVO.getMchNo());
			
			int money = 0;
			
			if(staffCount/actionCount >= 0.4 && staffCount/actionCount < 0.45){
				money = staffCount*20;
			};
			if(staffCount/actionCount >= 0.45 && staffCount/actionCount < 0.5){
				money = staffCount*30;
			};
			if(staffCount/actionCount >= 0.5){
				money = staffCount*35;
			};
            
			//查询商家的openid
			String openid = rebates.findMch(rebateVO.getMchNo()); 
			
			logger.info("返利給："+openid+";金額為："+money);
			
			Map<String, String> restmap = null;
			try {
				Map<String, String> parm = new HashMap<String, String>();
				parm.put("mch_appid", Constant.WEIXIN_APPID); //公众账号appid
				parm.put("mchid", Constant.WEIXIN_MCH_ID); //商户号
				parm.put("nonce_str", PayUtil.getNonceStr()); //随机字符串
				parm.put("partner_trade_no", PayUtil.getTransferNo()); //商户订单号
				parm.put("openid", openid); //用户openid	
				parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
				//parm.put("re_user_name", "安迪"); //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
			//	parm.put("amount", String.valueOf(money)); //转账金额
				
				parm.put("amount", "100"); //转账金额
				
				parm.put("desc", "aicar商家返利"); //企业付款描述信息
				//parm.put("spbill_create_ip", PayUtil.getLocalIp(request)); //Ip地址
				parm.put("spbill_create_ip", "127.0.0.1"); //Ip地址
				parm.put("sign",SignatureUtil.generateSign(parm,Constant.WEIXIN_PAY_KEY));
				String restxml = HttpUtils.posts(Constant.WEIXIN_TRANSFERS_PAY, XmlUtil.xmlFormat(parm, false));
				restmap = XmlUtil.xmlParse(restxml);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
				logger.info("转账成功：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			      //model.addAttribute("partner_trade_no", restmap.get("partner_trade_no"));//商户转账订单号
				 //model.addAttribute("payment_no", restmap.get("payment_no"));//微信订单号
				//model.addAttribute("payment_time", restmap.get("payment_time"));//微信支付成功时间
				rebates.updateMchRebateStatus(list);
				
				
			}else {
				if (CollectionUtil.isNotEmpty(restmap)) {
					logger.info("转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
				}
			}
			
		
			
		}
	}

}
