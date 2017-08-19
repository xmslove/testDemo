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
    <title>aicar商家平台</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
  
 </head>
 <body ontouchstart>
 	<div class="page">
		<div class="weui-msg">
			<div class="weui-msg__text-area">
				<h2 class="weui-msg__title">请关注微信公众号</h2>
				<p class="weui-msg__desc">请还没有关注aicar微信公众号。请长按识别图中的二维码进行关注</p>
				<p>
			<img src="<%=request.getContextPath() %>/png/aicarScan-2.jpg" style="height: auto; width: auto\9; width: 100%;" />
				</p>
			</div>
		</div>
	</div>
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">



</script>
</html>