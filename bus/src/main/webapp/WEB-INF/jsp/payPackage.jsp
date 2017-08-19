<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String openid = request.getAttribute("openid").toString();
String nickname = request.getAttribute("nickname")!=null?request.getAttribute("nickname").toString():null;
String headimgurl = request.getAttribute("headimgurl")!=null?request.getAttribute("headimgurl").toString():null;
String imsi = request.getAttribute("imsi").toString();
//String phone = request.getAttribute("phone").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>aicar会员</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
	.promiteColor{display:block;width:85%;margin:0 auto;padding:0 0 50px;color:#ff7d55}
	  body{background-color: #f6f6f6;}
    .content{width: 92%;height: 17.2rem;margin: 0 auto;background-color: #fff;border: 1px solid #ddd;margin-top: 2rem;}
    .content_left{height: 100%}
    .center{display: flex;justify-content: center;align-items: center;}
    .content_left>div:nth-of-type(1){height: 100%}
    .content_left>div:nth-of-type(1) img{width: 4.5rem;}
    .menuBtn{width: 2.4rem;height: 2.4rem;border-radius: 50%;background-color: #f6f6f6;border: 1px solid #ddd;}
    .menuBtn>div{width: 1.6rem;height: 1.6rem;border-radius: 50%;background-color: #ff7d55;display: none;}
    .content_mid>h3{font-size: 1.8rem;color: #333;padding-top: 0.5rem;}
    .content_mid>p{font-size: 1.4rem;color: #333;padding: 0.5rem 0;}
    .content_mid>p>span{color: #ff7d55;}
    .content_list img{width: 1.3rem;margin-right: 0.75rem;vertical-align: middle;}
    .content_list span{font-size: 1.2rem;color: #666;}
    .content_list b{float: right;font-size: 1.2rem;color: #999;font-weight: normal;margin-top: 0.2rem;}
    .payPackgeBtn{width:85%;margin:0 auto;}

  
</style>
</head>
<body class="gray-bg">

<div id="packageList"></div>
<br>
<a href='http://www.xccnet.com/bus/limiteCar.html' class='promiteColor' style="display:block;">所有套餐均不支持运营车辆使用</a>
 <div class="aui-content-padded payPackgeBtn" >
		<div class="row">
			<div class="col-xs-6" style="padding-left:0;">
				<span class="aui-btn aui-btn-success aui-btn-block" style='background-color:#ff7d55;font-size: 1.7rem;height: 5rem;line-height: 5rem;padding: 0;' id="pay">微信付款</span>
			</div>
			<div class="col-xs-6" style="padding-right:0;">
				<span class="aui-btn aui-btn-block " id="otherPay" style='font-size: 1.7rem;color:#ff7d55;background-color:white;border:1px solid #ddd;height: 5rem;line-height: 5rem;padding: 0;'>找人代付</span>
			</div>
		</div>
	</div>
	<div class="weui-skin_android" id="actionsheet" style="display: none">
        <div class="weui-mask"></div>
        <div class="weui-actionsheet">
            <div class="weui-actionsheet__menu" id="createQRCode">
               
            </div>
        </div>
    </div>
 
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">
 var indexs = 0;
	 apiready = function(){
	        api.parseTapmode();
	    };
	    
	    $(function(){
	    	var $androidActionSheet = $('#actionsheet');
	        var $androidMask = $androidActionSheet.find('.weui-mask');
	        var no = 1 ;
	        //套餐个数
	        var packageLength ;
	        //获取套餐列表
	        $.ajax({
	   			type : "GET",
	   			url : "weiXinScan/getAllPackage",
	   			data : null,
	   			success : function(data) {
	   				console.log(data);
	   			packageLength = data.length;
	   			$.each(data,function(i,item){
	   				var wifiCeilingLable;
	   				var ifVideoLable;
	   				var wifiCeiling = item.wifiCeiling;
	   				var ifVideo = item.ifVideo;
	   				var trafficCeiling = item.trafficCeiling;
	   				if(wifiCeiling=="无限制"){
	   					wifiCeiling = "-";
	   					wifiCeilingLable = "无限制";
	   				}else{
	   					wifiCeilingLable = wifiCeiling +"M";
	   				};
	   				if(ifVideo=="不支持"){
	   					//ifVideo = "2";
	   					ifVideoLable = "2";
	   				}else{
	   					ifVideo = "无限制";
	   					ifVideoLable = "1";
	   				};
	   				if(trafficCeiling=="无限制"){
	   					trafficCeiling = "-";
	   				};
       var packageHtml = "<form  method='post' id='form" +
		i +
		"'><input type='hidden' name='openid' value='<%=openid%>'/>"   +
		"<input type='hidden' name='nickname' value='<%=nickname%>'/>"  +
		"<input type='hidden' name='headimgurl' value='<%=headimgurl%>'/>"  +
		"<input type='hidden' name='imsi'    value='<%=imsi%>'/>"  +
		<%-- "<input type='hidden' name='phone'    value='<%=phone%>'/>" + --%>
		"<input type='hidden' name='packageType'    value='" +item.packageType+"'/><input type='hidden' name='payModel'  />" +
		'<div class="container content"><div class="row content_left"><div class="col-xs-3 center">'+
		"<img src='" +item.packagePhoto +"'/>"+
        '</div><div class="col-xs-7 content_mid"><h3>'+ item.packageName+'</h3>'+'<p>'+item.packagePrice+'/年 <span>相当于每月'+Math.round(((item.packagePrice)*1/12)*10)/10+'元</span><input type="hidden" name="packagePrice" value="'+"0.01"+'"/></p><ul class="list-unstyled content_list" ><li><img src="<%=request.getContextPath() %>/png/list-1.png"> <span>普通流量消费</span> <b>'+item.trafficCeiling+'</b><input type="hidden" name="trafficCeiling" value="' +trafficCeiling +'"/></li><li><img src="<%=request.getContextPath() %>/png/list-2.png"> <span>wifi热点共享</span> <b>'+wifiCeilingLable+'</b><input type="hidden" name="trafficCeiling" value="' +trafficCeiling +'"/></li><li><img src="<%=request.getContextPath() %>/png/list-3.png"> <span>视频流量</span> <b>'+ifVideo+'</b><input type="hidden" name="ifVideo" value="' +ifVideoLable +'"/></li></ul></div><div class="col-xs-2 center" style="height: 100%"><div class="menuBtn center"><div></div></div></div></div></div>';
		+"</form>";
		$("#packageList").append(packageHtml);
	   			});
		$('.menuBtn:eq(0) div').css('display','block');		
		$('.menuBtn').on('click',function(){
			$('.menuBtn div').css('display','none');
			$(this).children().css('display','block');
		})  
		 //找人代付
		 $("#otherPay").on('click', function(){
		 	$('.menuBtn div').each(function(i,e){
				if($(e).css('display')=='block'){
					indexs = i;
				}
			})
	        	$("input[name='payModel']").val("2");
				$("#form"+indexs).attr("action","weiXinScan/createQRCode");
				$("#form"+indexs).submit();
		
	        });


		   //自己支付
	    	$("#pay").click(function(){
	    		$("input[name='payModel']").val("1");
	    		$('.menuBtn div').each(function(i,e){
				if($(e).css('display')=='block'){
					indexs = i;
				}
			})
				var pram =$("#form"+indexs).serialize();
				$.ajax({
		   			type : "POST",
		   			url : "weiXinScan/pay",
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
		   		                	window.location.href="http://www.xccnet.com/bus/weiXinScan/otherPaySuccess?imsi_id="+imsi_id+
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
		   		                	window.location.href="http://www.xccnet.com/bus/weiXinScan/paySuccess?imsi_id="+imsi_id+
		   		                			"&packageType="+packageType+
		   		                			"&packagePrice="+packagePrice+
		   		                			"&orderNo="+orderNo;
		   		                }
		   		                }else{ 
		   		                var openid = $("input[name='openid']").val();	
		   		                  var nickname = $("input[name='nickname']").val();
		   		                	 var headimgurl = $("input[name='headimgurl']").val();
		   		                		 var imsi = $("input[name='imsi']").val();
		   		                	window.location.href="http://www.xccnet.com/bus/weiXinScan/topayPackageTemp?imsi="+imsi+
		   		                	"&openid="+openid+
   		                			"&nickname="+nickname+
   		                			"&headimgurl="+headimgurl;
		   		                			;  
		   		                }  
		   		    		});
		   		    	});
		   				 },
		   				error : function(e) {
		   			}
		   		})
				//$("#form"+index).submit();
	    	});
	    	
		
	   			},

	   				error : function(e) {
	   			}
	   		});

	    	
	        //自己支付
	    	$("#pay").click(function(){
	    		$("input[name='payModel']").val("1");
	    		var index;
	    		for ( var i = 0; i < packageLength; i++) {
	    			var payType = $("input:radio[name='payType"+i+"']:checked").val();
	    			if(payType!=null){
	    				index = i ;
	    			};	
				};
				//$("#form"+index).attr("action","weiXinScan/pay");
				var pram =$("#form"+index).serialize();
				$.ajax({
		   			type : "POST",
		   			url : "weiXinScan/pay",
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
		   		                	window.location.href="http://www.xccnet.com/bus/weiXinScan/otherPaySuccess?imsi_id="+imsi_id+
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
		   		                	window.location.href="http://www.xccnet.com/bus/weiXinScan/paySuccess?imsi_id="+imsi_id+
		   		                			"&packageType="+packageType+
		   		                			"&packagePrice="+packagePrice+
		   		                			"&orderNo="+orderNo;
		   		                }
		   		                }else{ 
		   		                var openid = $("input[name='openid']").val();	
		   		                  var nickname = $("input[name='nickname']").val();
		   		                	 var headimgurl = $("input[name='headimgurl']").val();
		   		                		 var imsi = $("input[name='imsi']").val();
		   		                	window.location.href="http://www.xccnet.com/bus/weiXinScan/topayPackageTemp?imsi="+imsi+
		   		                	"&openid="+openid+
   		                			"&nickname="+nickname+
   		                			"&headimgurl="+headimgurl;
		   		                			;  
		   		                }  
		   		    		});
		   		    	});
		   				 },
		   				error : function(e) {
		   			}
		   		})
				//$("#form"+index).submit();
	    	});
	    	
	    	datech = function(dom){
	    		var name = $(dom).attr("name");
	    		var index = name.substr(name.length-1,1);
	    		for ( var i = 0; i < packageLength; i++) {
					if(index!=i){
						$("input[name='payType"+i+"']").removeAttr("checked");
					};
				};
	    	}
	    });
	    
	</script>
</html>