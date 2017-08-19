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
		<title>销售注册</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
		<%-- <link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
        <link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet"> --%>
		<style>
		.staffRegister_title{padding:20px;font-size:20px;background-color:#f6f6f6;}
		#mchMsg div{text-align:center;}
		.staffRegister1 p{color:#666;}
		#mchName{font-size:30px;font-weight:bold;}
		#mchAddress{font-size:20px;font-weight:normal;}
		.reStyle{width:85%;margin:5% auto 0;}
       	.restyle .col-xs-12{padding: 0;}
		</style>
		</head>
		<body ontouchstart>
		<form method="post" action="shareProfit/staffRegister2">
		<input type="hidden" name="openid" value="<%=openid %>"/>
		<div class="form-group reStyle">
		
		<div class="col-xs-12 ">
		<div>
		<div class="weui-cells__title">您所在的店面 aicar商家号</div>
		<input class="weui-input staffRegister_title"  type="text" name='mchNo' placeholder="如:AB12" >
		</div>

		<div style="margin-top:10%;height:40%" >
		<div class="form-group" id="mchMsg">
		<div class='staffRegister1'> <img src="<%=request.getContextPath() %>/png/location.png" style="height:auto;width:auto\9;padding-bottom:20px;"/><p>商家对应的页面将显示在这里</p> </div>
		<div><h1 id="mchName"></h1></div>
		<div><h1 id="mchAddress"></h1></div>
		</div>
		</div>

		<div id="loadingToast" style="display: none;">
		<div class="weui-mask_transparent"></div>
		<div class="weui-toast">
		<i class="weui-loading weui-icon_toast"></i>
		<p class="weui-toast__content">数据加载中</p>
		</div>
		</div>

		<div id="toast" style="display: none;" >
		<div class="weui-mask_transparent"></div>
		<div class="weui-toast">
		<p class="weui-toast__content" id='#toast_error1' style='margin-top:40%;'>请输入aicar商家号</p>
		</div>
		</div>

		<div style="margin-top:20% ">
		<a class="weui-btn weui-btn_primary" id="next" style='background-color:#ff6666;text-decoration:none;color:#fff'>确定</a>
		</div>

		</div>
		
		</div>

		</form>

		</body>
		<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
		<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
		<script type="text/javascript">
		var mchNo = "${mchNo}";
		$("input[name='mchNo']").val(mchNo);
		var submit = false;

		$("input[name='mchNo']").blur(function(){
		findMchMsgByNo();
		});
		var mchNo1 = $("input[name='mchNo']").val();
		if($("input[name='mchNo']").val()!=''){
		$.ajax({
		async:false,
		url:"shareProfit/findMchMsgByNo",
		data:{"mchNo":mchNo1},
		type:'GET',
		dataType:'json',
		success:function(data){
		if(data!=null){
		$('.staffRegister1 img').attr('src','<%=request.getContextPath() %>/png/staff-1.png');
		$('.staffRegister1 p').hide();
		$("#mchMsg").show();
		$("#mchName").text(data.mchName);
		$("#mchAddress").text(data.mchAddress);
		$('#loadingToast').hide();
		submit = true;
		}
		},error:function(){
		$('.staffRegister1 img').attr('src','<%=request.getContextPath() %>/png/alert.png');
		$('.staffRegister1 p').hide();
		$("#mchName").text("未找到商家信息");
		$("#mchAddress").text("请检查是否输入正确的商家编号");
		submit = false;
		}
		});
		}
		function findMchMsgByNo(){
		var mchNo1 = $("input[name='mchNo']").val();
		if(mchNo1!="" || mchNo1!=null){
		//查询商家信息
		$.ajax({
		async:false,
		url:"shareProfit/findMchMsgByNo",
		data:{"mchNo":mchNo1},
		type:'GET',
		dataType:'json',
		success:function(data){
		if(data!=null){
		$('.staffRegister1 img').attr('src','<%=request.getContextPath() %>/png/staff-1.png');
		$('.staffRegister1 p').hide();
		$("#mchMsg").show();
		$("#mchName").text(data.mchName);
		$("#mchAddress").text(data.mchAddress);
		$('#loadingToast').hide();
		submit = true;
		}

		},error:function(){
		$('.staffRegister1 img').attr('src','<%=request.getContextPath() %>/png/alert.png');
		$('.staffRegister1 p').hide();
		$("#mchName").text("未找到商家信息");
		$("#mchAddress").text("请检查是否输入正确的商家编号");
		submit = false;
		}
		});
		}
		};


		$("#next").on('click',function(){
		var mchNo1 = $("input[name='mchNo']").val();
		if(mchNo1=="" || mchNo1==null){
		$('#toast_error1').text("请输入账号");
		toastTip();
		return false;
		};
		if(submit){
		$("form").submit();
		}
		})
		function toastTip(){
		var $toast = $('#toast');
		if ($toast.css('display') != 'none') return;
		$toast.fadeIn(100);
		setTimeout(function () {
		$toast.fadeOut(100);
		}, 2000);
		};



		</script>
		</html>