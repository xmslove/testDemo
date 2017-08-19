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
    <title>返利</title>        
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
 </head>
 <body>
 <form method="post" action='transfer/pay'>
 <input type="hidden" name="openid" value='osXfHvqXOttZULKJrOc8oMN2O_Tc'>
 </form>
 
 
 </body>
 <script type="text/javascript" src='<%=request.getContextPath()%>/hplus/js/jquery.min.js'></script>
 <script>
 	$('form').submit();
 </script>
 
</html>