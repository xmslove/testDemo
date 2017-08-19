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
    <title>Aicar商家返利说明</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
  
 </head>
 <body ontouchstart>
 <div align="center">
 <p>1、以商家每个月激活SIM卡的总量来计算，3档返利</p>
  <p>2、24小时内续年费率如果>40%，每个反20，如果大于45%。每月续费反30。如果大于50每个反35元</p>
   <p>3、按月结算，每月15日</p>
    <p>4、商家注册人在微信公众号发起提现需求</p>
     <p>5、返现给商家注册人</p>
 </div>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">



</script>
</html>