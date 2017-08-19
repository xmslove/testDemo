package com.bus.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bus.model.JsonResult;
import org.bus.model.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.bus.api.util.SignatureUtil;
import com.bus.util.CollectionUtil;
import com.bus.util.HttpUtils;
import com.bus.util.PayUtil;
import com.bus.util.SerializerFeatureUtil;
import com.bus.util.StringUtil;
import com.bus.util.WebUtil;
import com.bus.util.XmlUtil;
import com.bus.vo.Constant;
import com.bus.web.services.IActivationSerivce;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.vo.SnsToken;
/**
 * 企业付款
 * @author xms
 *
 */
@Controller
@RequestMapping("/transfer")
public class TransferController {
	
	private static final Logger LOG = Logger.getLogger(TransferController.class);
	
	@Resource 
	private IActivationSerivce activationSerivce;
	
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String transferPay(HttpServletRequest request,Model model){
		String openid = request.getParameter("openid");
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
			parm.put("amount", "100"); //转账金额
			parm.put("desc", "测试转账到个人"); //企业付款描述信息
			parm.put("spbill_create_ip", PayUtil.getLocalIp(request)); //Ip地址
			//parm.put("sign", PayUtil.getSign(parm, Constant.WEIXIN_PAY_KEY));
			parm.put("sign",SignatureUtil.generateSign(parm,Constant.WEIXIN_PAY_KEY));
			String restxml = HttpUtils.posts(Constant.WEIXIN_TRANSFERS_PAY, XmlUtil.xmlFormat(parm, false));
			restmap = XmlUtil.xmlParse(restxml);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
			LOG.info("转账成功：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			model.addAttribute("partner_trade_no", restmap.get("partner_trade_no"));//商户转账订单号
			model.addAttribute("payment_no", restmap.get("payment_no"));//微信订单号
			model.addAttribute("payment_time", restmap.get("payment_time"));//微信支付成功时间
			return "a";
		}else {
			if (CollectionUtil.isNotEmpty(restmap)) {
				LOG.info("转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
			return "b";
		}
		
	}
	
	/**
	 * 企业向个人转账查询
	 * @param request
	 * @param response
	 * @param tradeno 商户转账订单号
	 * @param callback
	 */
	@RequestMapping(value = "/pay/query", method = RequestMethod.POST)
	public void orderPayQuery(HttpServletRequest request, HttpServletResponse response, String tradeno,
			String callback) {
		LOG.info("[/transfer/pay/query]");
		if (StringUtil.isEmpty(tradeno)) {
			WebUtil.response(response, WebUtil.packJsonp(callback, JSON
					.toJSONString(new JsonResult(-1, "转账订单号不能为空", new ResponseData()), SerializerFeatureUtil.FEATURES)));
		}

		Map<String, String> restmap = null;
		try {
			Map<String, String> parm = new HashMap<String, String>();
			parm.put("appid", Constant.WEIXIN_APPID);
			parm.put("mch_id", Constant.WEIXIN_MCH_ID);
			parm.put("partner_trade_no", tradeno);
			parm.put("nonce_str", PayUtil.getNonceStr());
			parm.put("sign", PayUtil.getSign(parm, Constant.WEIXIN_SECRET));

			String restxml = HttpUtils.posts(Constant.WEIXIN_TRANSFERS_PAY_QUERY, XmlUtil.xmlFormat(parm, true));
			restmap = XmlUtil.xmlParse(restxml);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
			// 订单查询成功 处理业务逻辑
			LOG.info("订单查询：订单" + restmap.get("partner_trade_no") + "支付成功");
			Map<String, String> transferMap = new HashMap<>();
			transferMap.put("partner_trade_no", restmap.get("partner_trade_no"));//商户转账订单号
			transferMap.put("openid", restmap.get("openid")); //收款微信号
			transferMap.put("payment_amount", restmap.get("payment_amount")); //转账金额
			transferMap.put("transfer_time", restmap.get("transfer_time")); //转账时间
			transferMap.put("desc", restmap.get("desc")); //转账描述
			WebUtil.response(response, WebUtil.packJsonp(callback, JSON
					.toJSONString(new JsonResult(1, "订单转账成功", new ResponseData(null, transferMap)), SerializerFeatureUtil.FEATURES)));
		}else {
			if (CollectionUtil.isNotEmpty(restmap)) {
				LOG.info("订单转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
			WebUtil.response(response, WebUtil.packJsonp(callback, JSON
					.toJSONString(new JsonResult(-1, "订单转账失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
		}
	}
	
	/**
	 * 测试使用删除个人资料start
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/deleteMe", method = RequestMethod.GET) 
	public void toRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String backUri = Constant.WEIXIN_REDIRECT_DELETE_ME;
		backUri = URLEncoder.encode(backUri);
		String url = Constant.WEIXIN_AUTHORIZATION_URI+"appid=" +Constant.WEIXIN_APPID+"&redirect_uri="+backUri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}  
	
	
	/**
	 * 删除开始
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temp", method = RequestMethod.GET)
	public String  temp(HttpServletRequest request,Model model){
		String result  = "deleteMe" ;
		String code = request.getParameter("code");
		SnsToken token = SnsAPI.oauth2AccessToken(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET,code);
	    String openid = token.getOpenid();
	    activationSerivce.deleteMe(openid);
		return result;
	}  
	
}
