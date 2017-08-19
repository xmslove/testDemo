<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String openid = request.getAttribute("openid").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>视频</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/video/css/video-js.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/video/css/video.css">
</head>
<body>
<div class="wrap">
  <div class="m">
    <video
        id="my-player"
        class="video-js vjs-big-play-centered"
        controls
        preload="auto"
         x-webkit-airplay="true"
         webkit-playsinline="true" 
         playsinline="true"
         x5-video-player-type="h5"
         x5-video-player-fullscreen="true"
        poster="http://vjs.zencdn.net/v/oceans.png"
        data-setup='{}'>
        <source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4"></source>
       <source src="http://vjs.zencdn.net/v/oceans.webm" type="video/webm"></source>
      <source src="http://vjs.zencdn.net/v/oceans.ogv" type="video/ogg"></source>
      <p class="vjs-no-js">
        To view this video please enable JavaScript, and consider upgrading to a
        web browser that
        <a href="http://videojs.com/html5-video-support/" target="_blank">
          supports HTML5 video
        </a>
      </p>
    </video>
    <div id="shade">
        <div id="message">
           <p>觉得还不错？给他一点鼓励吧！</p>
           <button type="button" class="btn btn-info" id="btn">打赏一元</button>
        </div>
    </div>
  </div>
</div>
</body>
<script src="<%=request.getContextPath() %>/video/js/flexible.js"></script>
<script src="<%=request.getContextPath() %>/video/js/video.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">
$(function(){
	var openid = "${openid}";
    var myPlayer = videojs('my-player');
    var key = false;
    myPlayer.on('timeupdate',function(){
      var currentTime = this.currentTime();
      if(currentTime > 5 && !key){
          this.pause();
          $('#shade').show();
          $("#btn").click(playMoney)
      }
    })
    // 调用微信支付接口
    function playMoney(){
    	$.ajax({
   			type : "POST",
   			url : "smallPay/pay",
   			data : {"openid":openid},
   			success : function(data) {
   				var x_json = data.json;
   				x_json = eval("(" + x_json + ")");
   				var imsi_id =data.imsi;
   				var packageType =data.packageType;
   				var packagePrice =data.packagePrice;
   				var orderNo =data.orderNo;
   				var nickname =data.nickname;
   				var headimgurl =data.headimgurl;
   				var otherNickname =data.otherNickname;
   				var otherHeadimgurl =data.otherHeadimgurl;
   				var payModels = data.payModel;
   				var openid = data.openid;
   				wx.config({
   		    	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
   		    	    appId:x_json.appId, //必填，公众号的唯一标识
   		    	    timestamp:x_json.timeStamp, // 必填，生成签名的时间戳
   		    	    nonceStr:x_json.nonceStr, // 必填，生成签名的随机串
   		    	    signature:x_json.paySign,// 必填，签名，见附录1
   		    	    jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
   		    	});
   		    	
   		    	wx.ready(function(){
   		    		WeixinJSBridge.invoke('getBrandWCPayRequest',x_json,function(res){
   		                if(res.err_msg == "get_brand_wcpay_request:ok"){  
   		                 // 支付成功后执行以下代码
   		                 key = true;
   		                 myPlayer.play();
   		                 $('#shade').hide()
   		                }else{ 

   		                }  
   		    		});
   		    	});
   				 },
   				error : function(e) {
   			}
   		});   	
    };
});
	</script>
</html>