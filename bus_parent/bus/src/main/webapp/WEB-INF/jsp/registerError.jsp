<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
       /*  String code = request.getAttribute("code").toString();
		String imsi = request.getAttribute("imsi").toString();
		String imei = request.getAttribute("imei").toString();
		String busType =request.getAttribute("busType").toString();
		String phone =request.getAttribute("phone").toString(); */
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>aicar会员</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
</head>
<body ontouchstart>

	<div class="page" align='center'>
		<p style='width:78%;margin-top:100px;'>长按识别图二维码，<span style='color:#ff7d55;'>关注aicar服务微信公众号，</span>之后在公众号内进行会员注册</p>
			<div class="weui-msg__text-area">
				
			<img src="<%=request.getContextPath() %>/png/aicarScan-2.jpg" style="height: auto; width: auto\9; width: 100%;" />
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
   </script>
</body>
</html>
