package com.bus.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bus.vo.Constant;
import com.bus.weixin.api.APPTokenAPI;
import com.bus.weixin.api.SnsAPI;
import com.bus.weixin.vo.APPToken;
import com.bus.weixin.vo.jsapiVO;

/**
 * 
 * @author xms
 *
 */
@Controller  
@RequestMapping("/jsapi") 
public class jsapiController {
	
	
	public static String accessToken = null;
	//public static String url = "http://www.xccnet.com/bus/jsapi/scanQRCode";
	//public static String url2 = "http://www.xccnet.com/bus/jsapi/scanQRCode2";
	/**
	 * 閼惧嘲褰囧顔讳繆JS SDK閻ㄥ嫭娼堥梽锟�
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/scanQRCode", method = RequestMethod.GET) 
	public String scanQRCode(HttpServletRequest request,Model model) throws IOException, ServletException{
		 APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
		accessToken = tokens.getAccess_token();
		jsapiVO jsapi =  SnsAPI.getjsapi(accessToken);
		String jsapi_ticket = jsapi.getTicket(); 
        String nonce_str = create_nonce_str();  
        String timestamp = create_timestamp();  
        String string1;  
        String signature = ""; 
        //濞夈劍鍓版潻娆撳櫡閸欏倹鏆熼崥宥呯箑妞よ鍙忛柈銊ョ毈閸愭瑱绱濇稉鏂跨箑妞ょ粯婀佹惔锟�
        string1 ="jsapi_ticket="+jsapi_ticket+
        		 "&noncestr="+nonce_str +
                 "&timestamp=" +timestamp+
                 "&url=" + Constant.WEIXIN_JSAPI_URL; 
        System.out.println("string1="+string1);  
        try  
        {  
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");  
            crypt.reset();  
            crypt.update(string1.getBytes("UTF-8"));  
            signature = byteToHex(crypt.digest());  
        }  
        catch (NoSuchAlgorithmException e)  
        {  
            e.printStackTrace();  
        }  
        catch (UnsupportedEncodingException e)  
        {  
            e.printStackTrace();  
        }  
  
        model.addAttribute("url", Constant.WEIXIN_JSAPI_URL);  
        model.addAttribute("jsapi_ticket", jsapi_ticket);  
        model.addAttribute("nonceStr", nonce_str);  
        model.addAttribute("timestamp", timestamp);  
        model.addAttribute("signature", signature);  
        model.addAttribute("appId", Constant.WEIXIN_APPID);  
		return "scanQRCode";
	}
	
	@RequestMapping(value = "/scanQRCode2", method = RequestMethod.GET) 
	public String scanQRCode2(HttpServletRequest request,Model model) throws IOException, ServletException{
		 APPToken tokens = APPTokenAPI.token(Constant.WEIXIN_APPID,Constant.WEIXIN_SECRET);
		accessToken = tokens.getAccess_token();
		jsapiVO jsapi =  SnsAPI.getjsapi(accessToken);
		String jsapi_ticket = jsapi.getTicket(); 
        String nonce_str = create_nonce_str();  
        String timestamp = create_timestamp();  
        String string1;  
        String signature = ""; 
        //濞夈劍鍓版潻娆撳櫡閸欏倹鏆熼崥宥呯箑妞よ鍙忛柈銊ョ毈閸愭瑱绱濇稉鏂跨箑妞ょ粯婀佹惔锟�
        string1 ="jsapi_ticket="+jsapi_ticket+
        		 "&noncestr="+nonce_str +
                 "&timestamp=" +timestamp+
                 "&url=" + Constant.WEIXIN_JSAPI_URL2; 
        System.out.println("string1="+string1);  
        try  
        {  
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");  
            crypt.reset();  
            crypt.update(string1.getBytes("UTF-8"));  
            signature = byteToHex(crypt.digest());  
        }  
        catch (NoSuchAlgorithmException e)  
        {  
            e.printStackTrace();  
        }  
        catch (UnsupportedEncodingException e)  
        {  
            e.printStackTrace();  
        }  
  
        model.addAttribute("url", Constant.WEIXIN_JSAPI_URL2);  
        model.addAttribute("jsapi_ticket", jsapi_ticket);  
        model.addAttribute("nonceStr", nonce_str);  
        model.addAttribute("timestamp", timestamp);  
        model.addAttribute("signature", signature);  
        model.addAttribute("appId", Constant.WEIXIN_APPID);  
		return "scanQRCode2";
	}
	
	/** 
     * 闂呭繑婧�崝鐘茬槕 
     * @param hash 
     * @return 
     */  
    private static String byteToHex(final byte[] hash) {  
        Formatter formatter = new Formatter();  
        for (byte b : hash)  
        {  
            formatter.format("%02x", b);  
        }  
        String result = formatter.toString();  
        formatter.close();  
        return result;  
    }  
	
	
	/** 
     * 娴溠呮晸闂呭繑婧�稉锟�閻㈣京鈻兼惔蹇氬殰瀹搁亶娈㈤張杞伴獓閻拷
     * @return 
     */  
    private static String create_nonce_str() {  
        return UUID.randomUUID().toString();  
    }  
  
    /** 
     * 閻㈣京鈻兼惔蹇氬殰瀹歌精骞忛崣鏍х秼閸撳秵妞傞梻锟�
     * @return 
     */  
    private static String create_timestamp() {  
        return Long.toString(System.currentTimeMillis() / 1000);  
    }
    

}
