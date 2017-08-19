<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <style>
    .startMch_title{text-align:center;color:#fd5e62;}
    .startMch_page h2{ font-size:18px;color:#fd5e62;}
    .startMch_page{width:85%;margin:0 auto;}
    .startMch_page p{text-align:center;}
    .startMch_page p img{width:40%;text-align:center;}
    .startMch_page h3{font-size:14px;}
    .titleBall{display: inline-block;width: 10px;height: 10px;background-color: #ff6666;border-radius: 50%;margin-right: 10px;vertical-align: 5%;}
    </style>
 </head>
 <body ontouchstart>
    <div class="page article js_show">
    <div class="page__hd">
    <div class="page__bd">
    <article class="weui-article startMch_page" >
    <h1 class='startMch_title' style='color:#fd5e62'>赚钱3步走</h1>
    <section>
    <h2 class="title"><span class="titleBall"></span>注册为aicar销售</h2>
    <section>
    <h3>您需要获得<span>店面的aicar商家号</span></h3>
    <p>
    <img src="<%=request.getContextPath()%>/png/stap-1-1.png" alt="">
    </p>
    </section>
    </section>

    <section>
    <h2 class="title"><span class="titleBall"></span>为aicar 客户开通年费套餐</h2>
    <section>
    <h3>客户在激活aicar的SIM卡后，当场购买aicar 的年费流量套餐</h3>
    <p>
    <img src="<%=request.getContextPath()%>/png/stap-3-3.png" alt="">
    </p>
    </section>
    </section>

    <section>
    <h2 class="title"><span class="titleBall"></span>在aicar微信服务号获取返利</h2>
    <section>
    <h3>您可以每周五在微信公众号内进行提现</h3>
    <p>
    <img src="<%=request.getContextPath()%>/png/stap-2-2.png" alt="">
    </p>
    </section>
    </section>



    </article>
    </div>
    </div>


 <a class="weui-btn weui-btn_primary" style="background-color:#fd5e62;width:85%;" href="<%=request.getContextPath() %>/shareProfit/staffRegister?openid=<%=openid %>">开始注册</a>
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">



</script>
</html>