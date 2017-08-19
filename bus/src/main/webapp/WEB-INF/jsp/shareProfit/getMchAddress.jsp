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
    <title>商家地理位置</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
 </head>
 <body ontouchstart>
 
 <form action="shareProfit/getCarList" method="get">
 <input type="hidden" name="lat">
 <input type="hidden" name="lng">
 <input type="text" name="address">
 <input type="submit" value="搜索">
 </form>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">

var appId = "${appId}";
var timestamp = "${timestamp}";
var nonceStr = "${nonceStr}";
var signature = "${signature}";

wx.config({  
    debug: false,  
    appId:appId,  
    timestamp:timestamp,  
    nonceStr:nonceStr,  
    signature:signature,  
    jsApiList : [ 'getLocation' ]  
});//end_config  

wx.error(function(res) {  
    alert("出错了：" + res.errMsg);  
});  

wx.ready(function() {  
    wx.checkJsApi({  
        jsApiList : ['getLocation'],  
        success : function(res) {  
        	 if (res.checkResult.getLocation == false) {
                 alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                 return;
             }
        }  
    });  
    
  //  $("#sq").click(function(){
    	wx.getLocation({
    	    success: function (res) {
    	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
    	        var longitude = res.longitude; //经度，浮点数，范围为180 ~ -180。
    	        $("input[name='lat']").val(latitude);
    	        $("input[name='lng']").val(longitude);
    	    },
    	    cancel: function (res) {
    	        alert('用户拒绝授权获取地理位置');
    	    }
    	});
 //   });
    
      
      
});

</script>
</html>