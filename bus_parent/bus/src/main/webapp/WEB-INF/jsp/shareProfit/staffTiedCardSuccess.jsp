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
    <title>aicar卡绑定</title>        
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <style type="text/css">
    .aicarTiedTitle h1{font-size: 30px;}
    .aicarTiedTitle h1,.aicarTiedTitle h3{text-align: center;}
  </style>
 </head>
 <body>
 <div class="aicarTiedTitle">
    <h1>恭喜您绑定成功！</h1>
      <h3>aicar将每7个工作日给您进行结算</h3>
      <h3>届时将以粉丝红包的形式发送到您的微信账户</h3>
 </div>
     

   
 </body>
</html>