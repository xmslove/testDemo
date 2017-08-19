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
<title>aicar会员</title>
<style type="text/css">
	.customerTitle{text-align: left;}
	.customerTitle span{font-size: 0.7rem;color: #333;}
	.customerTitle h3{font-size: 0.9rem;color: #333;}
</style>        
 </head>
 <body>
 <div class="form-group" style="margin-top:10%; ">
<div class="row" style="margin-left:0;margin-right:0;">
<div class="col-xs-5" align="right"><img alt="image" src="png/wechat.png" ></div>
<div class="col-xs-7 customerTitle" ><span>微信客服</span><h3>aicar服务</h3></div>
</div >

 </div>
 <div style="text-align:center;">
 	<img alt="image" src="png/aicarScan-2.jpg" style="width:48%;" >
 </div>
 </body>
</html>