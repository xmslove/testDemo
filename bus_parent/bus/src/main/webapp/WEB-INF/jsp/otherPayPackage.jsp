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
    <title>代付详情</title>        
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
 <body ontouchstart>
 <div class="form-group" style="background-color:#cccccc;">

 <div class="row" style="padding:10px;">
 <div class='col-xs-2'><img alt='image' class='img-circle' src="${headimgurl}" style='width:48px;height:48px;margin-top:5px; '></div>
 <div class='col-xs-10'><h1 style="font-size:30px;">${nickname}</h1>请您帮忙支付</div>
 </div>

 </div>
 <form action="weiXinScan/otherPayPackage" method="post">
<input type="hidden" name="payModel" value="2"/> 
<input type="hidden" name="packagePrice"/> 
<input type="hidden" name="packageType" value="${packageType}"/>
<input type="hidden" name="imsi" value="${imsi}"/>
<input type="hidden" name="nickname" value="${nickname}"/>
<input type="hidden" name="headimgurl" value="${headimgurl}"/>
<input type="hidden" name="openid" value="${openid}"/>
<input type="hidden" name="paidTime" value="${paidTime}"/>
<input type="hidden" name="otherNickname" value="${otherNickname}"/>
<input type="hidden" name="otherHeadimgurl" value="${otherHeadimgurl}"/>

<div class="form-group" style="padding:10px;background-color:#fff;">
 <div class="row" align="center"><h2>付款金额</h2></div>
 <div class="row" align="center" id="packagePrice" style="font-size:30px;"></div>
 <div class="row" style="margin-left:10px; ">
<p>好友代付说明</p>
<p>1.代付发起后24小时无人付款，订单将自动取消；</p>
<p>2.如果系统原因退款，已支付金额将原路返回给代付人。</p>
 </div>
 </div>


<div style="padding:10px;background-color:#fff;"">
   <h2 style="font-size:20px;">商品信息</h2>
   <p id="packageName" style="font-size:15px;padding:20px 0;"></p>
</div>
 
  <div class="form-group" style="margin-top:100px; ">
  <div class="weui-btn-area">
			<a class="weui-btn weui-btn_warn" onclick="otherPayPackage()">微信支付</a>
		</div>
  </div>
</form>  
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">
/*  var nickname = "${nickname}";
	var headimgurl = "${headimgurl}";
	var openid = "${openid}";
	var paidTime = "${paidTime}";
	var otherNickname = "${otherNickname}";
	var otherHeadimgurl = "${otherHeadimgurl}";
	var imsi = "${imsi}"; */
	
	var packageType = "${packageType}";
	
	     var imsi =	$("input[name='imsi']").val();
    	 var nickname =	$("input[name='nickname']").val();
 	 	 var headimgurl =	$("input[name='headimgurl']").val();
		 var openid =	$("input[name='openid']").val();
		 var paidTime =	$("input[name='paidTime']").val();
		 var otherNickname =	$("input[name='otherNickname']").val();
		 var otherHeadimgurl =	$("input[name='otherHeadimgurl']").val();
	
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
         		//测试使用
         		//$("input[name='packagePrice']").val(money);
         		$("input[name='packagePrice']").val("0.01");
         	   }
          });
	
	function otherPayPackage(){
		var pram =$("form").serialize();
		$.ajax({
   			type : "POST",
   			url : "weiXinScan/otherPayPackage",
   			data : pram,
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
   		                if(payModels=="2"){//代付成功页面
   		                	window.location.href="<%=basePath %>weiXinScan/otherPaySuccess?imsi_id="+imsi_id+
   		                			"&packageType="+packageType+
   		                			"&packagePrice="+packagePrice+
   		                			"&orderNo="+orderNo+
   		                			"&nickname="+nickname+
   		                			"&headimgurl="+headimgurl+
   		                			"&otherNickname="+otherNickname+
   		                			"&otherHeadimgurl="+otherHeadimgurl+
   		                			"&payModels="+payModels+
   		                			"&openid="+openid;
   		                }else{
   		                	window.location.href="<%=basePath %>weiXinScan/paySuccess?imsi_id="+imsi_id+
   		                			"&packageType="+packageType+
   		                			"&packagePrice="+packagePrice+
   		                			"&orderNo="+orderNo;
   		                }
   		                }else{ 
   		                window.location.href="<%=basePath %>weiXinScan/toOtherPayPackageTemp?imsi="+imsi+
   		                                     "&packageType="+packageType+
   		        							 "&nickname="+nickname+
   		     								 "&openid="+openid+
   		  									 "&paidTime="+paidTime+
   											 "&headimgurl="+headimgurl+
   											 "&otherNickname="+otherNickname+
   											 "&otherHeadimgurl="+otherHeadimgurl;  
   		                }  
   		    		});
   		    	});
   				 },
   				error : function(e) {
   			}
   		})
		/*  $("form").submit(); */
	};
 </script>
</html>