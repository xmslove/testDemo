<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String mchNo = request.getParameter("mchNo");
String mchName = request.getParameter("mchName");
String openid = request.getParameter("openid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<base href="<%=basePath%>">    
<title>Aicar</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
 <style>
 body{background-color:#f3f3f3;}
 .mchLoginMsg_warp{position:relative;}
 .mchLoginMsg_content{position:absolute;top:-80px;left:10%;;width:80%;text-align:center;background-color:rgba(255,255,255,0.5);padding:5%;}
 .mchLoginMsg_content img{width:30%;}
 .mchLoginMsg_content h2{padding:20px 0;font-weight:bold;color:#010101;}
 .mchLoginMsg_content>div{color:#666;font-size:15px;}
 .mchLoginMsg_content h3 {font-size:30px;color:#333;}
 .mchLoginMsg_content>p{padding:10px 0 50px;color:#333;font-size:14px;}
 .mchLoginMsg_foot{text-align:center;margin-top:320px;color:#b2b2b2;font-size:13px;}
 </style>
 </head>
 <body ontouchstart>

 <img src="<%=request.getContextPath() %>/png/share_title1.png" style="width:100%;"/>
 <div class="mchLoginMsg_warp">
 <div class="mchLoginMsg_content">
 <img src="<%=request.getContextPath() %>/png/share_mch-1.png"/>
 <h2>${mchName}</h2>
 <div>aicar商家号</div>
 <h3> ${mchNo}</h3>
 <p>${mchAddress}</p>
 <a class="weui-btn weui-btn_primary" id="staffRegister" style='background-color:#ff6666'  >注册为店内销售</a>
 </div>
 </div>
 <div class='mchLoginMsg_foot'>您可以收藏此页面，或分享到工作群中</div>

 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
  var openid = "${openid}";
  var mchNo = "${mchNo}";
  $("#staffRegister").attr("href","<%=basePath %>shareProfit/staffRegister1?mchNo="+mchNo+"&openid="+openid);
  


</script>
</html>