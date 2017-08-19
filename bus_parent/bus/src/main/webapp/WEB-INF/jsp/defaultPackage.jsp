<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String imsi = request.getAttribute("imsi").toString();
//String phone =request.getAttribute("phone").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>aicar会员</title>  
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
    <style>
    .buyItem{margin-top:-20px;margin-bottom: 4rem}
    .buyItem>h3{font-size:4.2rem;font-weight:600;text-align:center;}
    .clear_a a{ text-decoration:none;color:white;}
    ul,li{list-style-type: none;}
    .defaultList{width: 91.73%;margin: 0 auto;border:1px solid #ddd;margin-bottom:7rem;}
    .defaultList li{padding: 1.5rem;border-bottom: 1px solid #ddd; }
     .defaultList img{width: 2rem;margin-right: 0.75rem;vertical-align: -10%;}
    .defaultList span{font-size: 3.2rem;color: #333;}
    .defaultList b{float: right;font-size: 2.8rem;color: #999;font-weight: normal;margin-top: 0.2rem;}
    </style>
	</head>
	<body ontouchstart>
	<div class="page">

    <div class="page__hd" align="center">
        <h3 class="page__title" style='color:#ff7d55;font-size:25px;padding:20px;' >恭喜您成为aicar会员</h3>
        <!-- <p class="page__desc" style='font-size:14px;color:#999; ' >您将免费<span style='color:#ff7d55 '>试用30天</span>免费试用流量</p> -->
    </div>


    </div>

    <%-- <ul class="defaultList">
        <li><img src="<%=request.getContextPath() %>/png/list-1.png"><span>普通流量消费</span><b>${packagess.trafficCeiling}</b></li>
        <li><img src="<%=request.getContextPath() %>/png/list-2.png"><span>wifi热点共享</span><b>${packagess.wifiCeiling}M</b></li>
        <li style="border-bottom:0;"><img src="<%=request.getContextPath() %>/png/list-3.png"><span>视频流量</span><b>${packagess.ifVideo}</b></li>
    </ul> --%>




<div class='buyItem'>
    <h3>购买年费流量套餐<br>享受更多服务</h3>
</div>

<p style='display:flex;justify-content:space-between;margin-top:-10px;'>
<img src="<%=request.getContextPath() %>/png/wifi.png" style="width:20%;height:12%;margin-left:12%;"/><img src="<%=request.getContextPath() %>/png/avi.png" style="width:20%;height:12%;margin-right:15%;"/>
</p>

    <div class="col-xs-6" align="center">
    <p><font>1张卡全家共享！</font><br><font>wifi热点不限流</font></p>
    </div>
<div class="col-xs-6" align="center">
<p><font>视频流量无限制</font></p>
</div>

<div class="col-xs-12">
<div class="weui-btn-area clear_a" >
       <a href="<%=basePath %>weiXinScan/topayPackage?imsi=<%=imsi %>" class="weui-btn weui-btn_warn" style='background-color:#ff7d55'>了解年费套餐</a>
</div>
</div>
        
	</body>
	<script type="text/javascript">
  (function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize = 10 * (clientWidth / 750) + 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false)
})(document, window);
	</script>
</html>
