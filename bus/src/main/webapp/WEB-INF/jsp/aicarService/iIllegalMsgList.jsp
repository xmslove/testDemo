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
<title>违章查询</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
 </head>
 <body>
 
<div id="list" class="row" style="margin-top:10%;">

</div>

<div class="row">
<div class="col-xs-2"></div>
<div class="col-xs-8"><a href="javascript:;" class="weui-btn weui-btn_primary">添加车辆</a></div>
<div class="col-xs-2"></div>
</div>		
		
 </body>
 <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">

var openid = "${openid}";
var imsi = "${imsi}";
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
    	        var lat = res.latitude; // 纬度，浮点数，范围为90 ~ -90
    	        var lng = res.longitude; //经度，浮点数，范围为180 ~ -180。
    	       $.ajax({
    	          url:"mobleCar/getCoordinate",
    	          data:{"lat":lat,"lng":lng},
    	          dataType:'json',
    	          success:function(data){
    	        	  var datas =  '${iIllegalMsgList}';
    	        	  var s = JSON.parse(datas);
    	        	  $(s).each(function(index,element){
    	        		var html = '<section class="aui-content-padded" ><div class="aui-card-list"><div class="aui-card-list-content-padded listOuter" ><div class="form-group"><div class="row" align="center">'+
    	      			'<div class="row" onclick="toiIllegal(this)" id="'+index+'"   style="margin-top:5%;"><input type="hidden" name="cityName" id="cityName" value="'+data.result.addressComponent.city+
  	        			'"><input type="hidden" name="engineno" value="' +element.engineno+
  	        			'"><input type="hidden" name="classno" id="classno" value="' +element.classno+
  	        			'"><div class="col-xs-8" style="font-weight:bold;"><input type="hidden" name="hphm" id="hphm" value="' +element.hphm+
  	        			'" /><h2>' +element.hphm+
  	        			'</h2></div><div class="col-xs-4"><a href="mobleCar/removeIllegal?id='+element.id+'&hphm='+element.hphm+'" class="weui-btn weui-btn_mini weui-btn_default">删除</a></div></div></div></div></section>';
  	        			$("#list").append(html);  
    	        		  
    	        	  });
    	          }
    	          });
    	    },
    	    cancel: function (res) {
    	        alert('用户拒绝授权获取地理位置');
    	    }
    	});
});
  
     //查询违章记录
     toiIllegal = function(dom){
    	    var id = $(dom).attr("id");
    	    var cityName = $("#"+id+" input[name='cityName']").val();
    	    var engineno = $("#"+id+" input[name='engineno']").val();
    	    var hphm = $("#"+id+" input[name='hphm']").val();    
    	    var classno = $("#"+id+" input[name='classno']").val();
window.location.href="<%=basePath %>mobleCar/toiIllegal?cityName="+cityName+"&engineno="+engineno+"&hphm="+hphm+"&classno="+classno; 
    	 };
    	 
  <%--   removeIllegal = function(dom){
    	debugger;
    	var id = $(dom).attr("id");
	    var hphm = $("#"+id+" input[name='hphm']").val();    
	    $.ajax({
	          url:"mobleCar/removeIllegal",
	          type : "GET",
	          data:{"imsi":imsi,"hphm":hphm},
	          success:function(data){
	        	  if(data){
	        		 window.location.href="<%=basePath %>mobleCar/getService";
	        	 }
	          }
	    });
    	}; --%>
</script>
</html>