<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微信支付</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart>
	<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
	<script type="text/javascript">
	var x_json =${json};
	var imsi_id ="${imsi_id}";
	var packageType ="${packageType}";
	var packagePrice ="${packagePrice}";
	var orderNo ="${orderNo}";
	var nickname ="${nickname}";
	var headimgurl ="${headimgurl}";
	var otherNickname ="${otherNickname}";
	var otherHeadimgurl ="${otherHeadimgurl}";
	var payModels = "${payModel}";
	var openid = "${openid}";
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId:x_json.appId, //必填，公众号的唯一标识
	    timestamp:x_json.timeStamp, // 必填，生成签名的时间戳
	    nonceStr:x_json.nonceStr, // 必填，生成签名的随机串
	    signature:x_json.paySign,// 必填，签名，见附录1
	    jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	
	wx.ready(function(){
		WeixinJSBridge.invoke('getBrandWCPayRequest',x_json,function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok"){  
            if(payModels=="2"){//代付成功页面
            	window.location.href="<%=basePath %>weiXinScan/otherPaySuccess?imsi_id="+imsi_id+
            			"&packageType="+packageType+
            			"&packagePrice="+packagePrice+
            			"&orderNo="+orderNo+
            			"&nickname="+nickname+
            			"&headimgurl="+headimgurl+
            			"&otherNickname="+otherNickname+
            			"&otherHeadimgurl="+otherHeadimgurl+
            			"&payModels="+payModels+
            			"&openid="+openid;
            }else{
            	window.location.href="<%=basePath %>weiXinScan/paySuccess?imsi_id="+imsi_id+
            			"&packageType="+packageType+
            			"&packagePrice="+packagePrice+
            			"&orderNo="+orderNo;
            }
            }else{  
            window.location.href="<%=basePath %>weiXinScan/payError?imsi_id="+imsi_id+
            		    "&packageType="+packageType;  
            }  
		});
	});
</script>
</body>
</html>
