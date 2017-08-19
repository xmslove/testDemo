<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imsi = request.getParameter("imsi");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>??</title> 
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">       
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/swiper-3.4.2.min.css" />
    <style>
    body {
        background: #fff;
        font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
        font-size: 14px;
        color:#000;
        margin: 0;
        padding: 0;
    }
    .swiper-container {
        width: 100%;
        padding-top: 50px;
        padding-bottom: 50px;
    }
    .swiper-slide {
        background-position: center;
        background-size: cover;
        width: 300px;
        height: 300px;

    }
    </style>
 </head>
 <body>
 <div class="swiper-container swiper-container-horizontal swiper-container-3d swiper-container-coverflow" style="cursor: -webkit-grab;">
        <div class="swiper-wrapper" style="transform: translate3d(381.5px, 0px, 0px); transition-duration: 0ms;">
            <div onclick="weizhangchaxun()" class="swiper-slide swiper-slide-prev" style="width:150px;height:200px;background-image: url(&quot;img/service_1.png&quot;); transform: translate3d(0px, 0px, -100px) rotateX(0deg) rotateY(50deg); z-index: 0; transition-duration: 0ms;"><div class="swiper-slide-shadow-left" style="opacity: 1; transition-duration: 0ms;"></div><div class="swiper-slide-shadow-right" style="opacity: 0; transition-duration: 0ms;"></div></div>
            <div class="swiper-slide swiper-slide-active" style="width:150px;height:200px;background-image: url(&quot;img/service_2.png&quot;); transform: translate3d(0px, 0px, 0px) rotateX(0deg) rotateY(0deg); z-index: 1; transition-duration: 0ms;"><div class="swiper-slide-shadow-left" style="opacity: 0; transition-duration: 0ms;"></div><div class="swiper-slide-shadow-right" style="opacity: 0; transition-duration: 0ms;"></div></div>
            <div  onclick="weixinjieren()" class="swiper-slide swiper-slide-next" style="width:150px;height:200px;background-image: url(&quot;img/service_3.png&quot;); transform: translate3d(0px, 0px, -100px) rotateX(0deg) rotateY(-50deg); z-index: 0; transition-duration: 0ms;"><div class="swiper-slide-shadow-left" style="opacity: 0; transition-duration: 0ms;"></div><div class="swiper-slide-shadow-right" style="opacity: 1; transition-duration: 0ms;"></div></div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination swiper-pagination-bullets"><span class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span><span class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span></div>
    </div>
 </body>
<script src="<%=request.getContextPath() %>/js/swiper-3.4.2.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>

<script type="text/javascript">

var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    loop:true,
    effect: 'coverflow',
    grabCursor: true,
    centeredSlides: true,
    slidesPerView: 3,
    coverflow: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true
    }
});

weixinjieren = function(){
	window.location.href=baobao.StartWeixinPickup();
};

weizhangchaxun = function(){
	window.location.href=baobao.StartIllegalCar();
};

</script>
</html>