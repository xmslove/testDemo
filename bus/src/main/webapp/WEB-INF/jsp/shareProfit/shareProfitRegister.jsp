<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String brandNo = request.getParameter("brandNo");
String openid = request.getParameter("openid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>aicar商家平台</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<style type="text/css">
	.reStyle{width:76%;margin:5% auto 0;}
    .restyle .col-xs-12{padding: 0;}
</style>
  
 </head>
 <body ontouchstart>
 <div class="form-group" align="center">
 <img class="weui-media-box__thumb" src="<%=request.getContextPath()%>/png/share_home_bg2.png" alt="" style="position:absolute;top:-30px;left:0;bottom:0;right:0;width:100%;"/>
 </div>
 <br> 
 <div class="form-group reStyle">
 <div class="row">
 
  <div class="col-xs-12 "> 
 <a class="weui-btn" style='background-color:#fff;color:#fd5e62;margin-top:60%;' id="staffRegister">我是销售人员</a>
 </div>

 </div>

 </div>
 <br>
 <div class="form-group reStyle">
  <div class="row">
 
  <div class="col-xs-12 "> 
 <a class="weui-btn weui-btn_plain-default"  style='color:#fff;border-color:#fff;' id="mchregister">我是商家</a>
 <%-- <a class="weui-btn weui-btn_default" href="<%=request.getContextPath() %>/shareProfit/mchRegister?brandNo=<%=brandNo %>">商家注册</a> --%>
 
 </div>

 </div>
 </div>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
var brandNo = "${brandNo}"
var openid =  "${openid}";
$("#mchregister").attr("href","<%=basePath %>shareProfit/mchAuthorize?brandNo="+brandNo+"&openid="+openid);
$("#staffRegister").attr("href","<%=basePath %>shareProfit/staffAuthorize?openid="+openid);

</script>
</html>