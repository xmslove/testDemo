package com.bus.illegal.api;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bus.illegal.vo.CityParamVO;
import com.bus.illegal.vo.CitysVO;
import com.bus.illegal.vo.IllegalResultVO;

public class illegalApi {
	
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
	/**
	 * 得到省份及城市的交管局配置
	 * @return
	 * 	province	string	N	默认全部，省份简写，如：ZJ、JS
	 *	dtype	string	N	返回数据格式：json或xml或jsonp,默认json
	 *	format	int	N	格式选择1或2，默认1
	 *	callback	String	N	返回格式选择jsonp时，必须传递
	 *	key	string	Y	你申请的key
	 * @throws Exception 
	 */
	public static CitysVO getProvinceSetting(CityParamVO vo) throws Exception{
    	CitysVO result = new CitysVO();
    	String cityName = vo.getCityName();
    	if(cityName.substring(cityName.length()-1, cityName.length()).equals("市")){
    		vo.setCityName(cityName.substring(0, cityName.length()-1));
    	}
		Map<String,String> params = new HashMap<String,String>();//请求参数
		params.put("province",vo.getProvince()!=null?vo.getProvince():"");//默认全部，省份简写，如：ZJ、JS
        params.put("dtype",vo.getDtype()!=null?vo.getDtype():"");//返回数据格式：json或xml或jsonp,默认json
        params.put("format",vo.getFormat()+"");//格式选择1或2，默认1
        params.put("callback",vo.getCallback()!=null?vo.getCallback():"");//返回格式选择jsonp时，必须传递
        params.put("key",vo.getKey());//你申请的key
        String s =net(apiUri.GET_PROVINCE_SETTING, params, "GET");
        JSONObject obj = JSONObject.parseObject(s);
        JSONObject or = (JSONObject)obj.get("result");
        LinkedHashMap<String, String> jsonMap = JSON.parseObject(or.toJSONString(),new TypeReference<LinkedHashMap<String, String>>(){});
        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            JSONObject obj2 = JSONObject.parseObject(entry.getValue());
            JSONArray city = obj2.getJSONArray("citys");
            Iterator<Object> it = city.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                if(ob.getString("city_name")!=null){
                	if(vo.getCityName().equals(ob.getString("city_name"))){
                		result.setClassno(ob.getString("classno"));
                		result.setClassa(ob.getString("classa"));
                		result.setAbbr(ob.getString("abbr"));
                		result.setCity_code(ob.getString("city_code"));
                		result.setEngine(ob.getString("engine"));
                		result.setEngineno(ob.getString("engineno"));
                		result.setRegist(ob.getString("regist"));
                		result.setRegistno(ob.getString("registno"));
                	}
                }
            }
        }
        return result;
	  };
	  
	
	  public static void main(String[] args) throws Exception {
		  CityParamVO vo = new CityParamVO();
		  vo.setCityName("武汉");
		  vo.setKey("7d25ba205b38c6c4fd2f92e0ce7b2634");
		  vo.setHphm("鄂A22SY0");//鄂A22SY0    翼BN813U
		  vo.setEngineno("1064045");//1064045   H40679
		  vo.setClassno("LVHFC1667G6064037");//LVHFC1667G6064037  LFV2B25G7F5137591
		  getIllegalResult(vo);
	}
	  
	  
	  
	  
	public static IllegalResultVO getIllegalResult(CityParamVO vo) throws Exception{
		IllegalResultVO result = null ;
		CitysVO cv = getProvinceSetting(vo);
		if(Integer.parseInt(cv.getClassa())!=0){
			if(Integer.parseInt(cv.getClassno())>0){
				vo.setClassno(vo.getClassno().substring(vo.getClassno().length()-Integer.parseInt(cv.getClassno()), vo.getClassno().length()));
			}
		}else{
			    vo.setClassno(null);
		};
		if(Integer.parseInt(cv.getEngine())!=0){
			if(Integer.parseInt(cv.getEngineno())>0){
				vo.setEngineno(vo.getEngineno().substring(vo.getEngineno().length()-Integer.parseInt(cv.getEngineno()), vo.getEngineno().length()));
			}
		}else{
			    vo.setEngineno(null);
		};
		Map<String,String> params = new HashMap<String,String>();//请求参数
        params.put("dtype","json");//返回数据格式：json或xml或jsonp,默认json
        params.put("callback","");//返回格式选择jsonp时，必须传递
        params.put("key",vo.getKey());//你申请的key
        params.put("city",cv.getCity_code());//城市代码 *
        params.put("hphm",URLEncoder.encode(vo.getHphm(), "utf8"));//号牌号码 完整7位 ,需要utf8 urlencode*
        params.put("hpzl","02");//号牌类型，默认02
        params.put("engineno",vo.getEngineno()!=null?vo.getEngineno():"");//发动机号 (根据城市接口中的参数填写)
        params.put("classno",vo.getClassno()!=null?vo.getClassno():"");//车架号 (根据城市接口中的参数填写)
        String illegalStr =net(apiUri.GET_ILLEGAL_RESULT, params, "GET");
        result = JSON.parseObject(illegalStr, IllegalResultVO.class);
        System.out.println(JSON.toJSONString(result));
        return result;
        }

	    /**
	     *
	     * @param strUrl 请求地址
	     * @param params 请求参数
	     * @param method 请求方法
	     * @return  网络请求字符串
	     * @throws Exception
	     */
	    public static String net(String strUrl, Map params,String method) throws Exception {
	        HttpURLConnection conn = null;
	        BufferedReader reader = null;
	        String rs = null;
	        try {
	            StringBuffer sb = new StringBuffer();
	            if(method==null || method.equals("GET")){
	                strUrl = strUrl+"?"+urlencode(params);
	            }
	            URL url = new URL(strUrl);
	            conn = (HttpURLConnection) url.openConnection();
	            if(method==null || method.equals("GET")){
	                conn.setRequestMethod("GET");
	            }else{
	                conn.setRequestMethod("POST");
	                conn.setDoOutput(true);
	            }
	            conn.setRequestProperty("User-agent", userAgent);
	            conn.setUseCaches(false);
	            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
	            conn.setReadTimeout(DEF_READ_TIMEOUT);
	            conn.setInstanceFollowRedirects(false);
	            conn.connect();
	            if (params!= null && method.equals("POST")) {
	                try {
	                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	                        out.writeBytes(urlencode(params));
	                } catch (Exception e) {
	                }
	            }
	            InputStream is = conn.getInputStream();
	            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
	            String strRead = null;
	            while ((strRead = reader.readLine()) != null) {
	                sb.append(strRead);
	            }
	            rs = sb.toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                reader.close();
	            }
	            if (conn != null) {
	                conn.disconnect();
	            }
	        }
	        return rs;
	    }
	 
	    //将map型转为请求参数型
	    public static String urlencode(Map<String,Object>data) {
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry i : data.entrySet()) {
	            try {
	                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }

}
