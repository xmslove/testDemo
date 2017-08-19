<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String headimgurl = request.getParameter("headimgurl");
String nickname = request.getParameter("nickname");
String otherHeadimgurl = request.getParameter("otherHeadimgurl");
String otherNickname = request.getParameter("otherNickname");
String packageType = request.getParameter("packageType");
String orderNo = request.getParameter("orderNo");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>代付详情</title>        
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
 <body ontouchstart>
 <input type="hidden" name="packageType" value="<%=packageType %>"/>
 <input type="hidden" name="orderNo" value="<%=orderNo %>"/>
 <div class="form-group" style="background-color:#cccccc;">

 <div class="row" style="padding:10px;">
 <div class='col-xs-2'><img alt='image' class='img-circle' src="<%=headimgurl %>" style='width:48px;height:48px;margin-top:5px; '></div>
 <div class='col-xs-10'><h1 style="font-size:30px;">${nickname}</h1>请您帮忙支付</div>
 </div>

 </div>
 <div class="form-group" style="background-color:#fff;padding:10px 0;">
 <div class="row" align="center"><h2 style="color:#62f728">已付款</h2></div>
 <div class="row" align="center" id="packagePrice"></div>
 <div class="row" style="margin-left:10px;margin-top: 30px; ">
 <div class='col-xs-2'><img alt='image' class='img-circle' src="<%=otherHeadimgurl %>" style='width:48px;height:48px;margin-top:5px;'></div>
 <div class='col-xs-10'>
 <p style="margin-top:10px;">代付人:<%=otherNickname %></p>
 <p>代付时间:<font id="time"></font></p>
 </div>
 </div>
 </div>
 
<div style="padding:10px;background-color:#fff;margin-top:20px;">
   <h2 style="font-size:20px;">商品信息</h2>
   <p id="packageName" style="font-size:15px;padding:20px 0;"></p>
</div>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/util.js"></script>
<script type="text/javascript">
/*  var nickname = "${nickname}";
	var headimgurl = "${headimgurl}";
	var openid = "${openid}";
	var paidTime = "${paidTime}";
	var otherNickname = "${otherNickname}";
	var otherHeadimgurl = "${otherHeadimgurl}";
	var imsi = "${imsi}"; */
	var packageType = $("input[name='packageType']").val();
	var orderNo = $("input[name='orderNo']").val();
	//查询代付套餐信息
	$.ajax({
         	async:false,
         	url:"weiXinScan/getPackageMsg",
         	data:{"packageType":packageType},
         	type:'GET',
         	dataType:'json',
         	success:function(data){
         		var money = data.packagePrice;
         		var name = data.packageName;
         		$("#packagePrice").text(money+"￥");
         		$("#packageName").text(name);
         		$("input[name='packagePrice']").val(money);
         	   }
          });
	//查询代付时间
	$.ajax({
         	async:false,
         	url:"weiXinScan/getOtherTime",
         	data:{"orderNo":orderNo},
         	type:'GET',
         	dataType:'json',
         	success:function(data){ 
                 var d = new Date(data);
                 var time = $.getFormateDate("yyyy-MM-dd HH:mm:ss",d);
                 $("#time").text(time);
         	   }
          });
 </script>
</html>