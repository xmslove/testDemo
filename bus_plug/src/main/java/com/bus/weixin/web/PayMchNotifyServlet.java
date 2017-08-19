package com.bus.weixin.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bus.api.util.SignatureUtil;
import com.bus.api.util.StreamUtils;
import com.bus.api.util.XMLConverUtil;
import com.bus.weixin.vo.MchBaseResult;
import com.bus.weixin.vo.MchPayNotify;

/**
 * 支付回调通知
 */
public class PayMchNotifyServlet extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String key;	//mch key

	//重复通知过滤
    private static Set<String> expireKey = new HashSet<String>();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求数据
		String xmlData = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
		//将XML转为MAP,确保所有字段都参与签名验证
		Map<String,String> mapData = XMLConverUtil.convertToMap(xmlData);
		//转换数据对象
		MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class,xmlData);
		//已处理 去重
		if(expireKey.contains(payNotify.getTransaction_id())){
			return;
		}
		//签名验证
		if(SignatureUtil.validateSign(mapData,key)){
			expireKey.add(payNotify.getTransaction_id());
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("SUCCESS");
			baseResult.setReturn_msg("OK");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}else{
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}
	}

}
