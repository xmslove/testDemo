
<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String code = request.getAttribute("code").toString();
	String imsi = request.getAttribute("imsi").toString();
	String imei = request.getAttribute("imei").toString();
	String phone = request.getAttribute("phone").toString();
	String openid = request.getAttribute("openid").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>aicar会员</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/weui/dist/style/weui.css" />
<style type="text/css">
.testControBF:before {
	height: 0;
	border: 0;
}

.testControBF:after {
	height: 0;
	border: 0;
}
</style>
</head>
<body ontouchstart>
	<div class="page" style="margin-top: 20px;" align="left">
		<h4 style="color: #666; width: 78.67%; margin: 0 auto;">请查看手机接收的短信验证码</h4>
		<div class="weui-cells weui-cells_form testControBF">
			<form action="activation/register" method="post" id="register">
				<input type="hidden" name="code" value="<%=code%>" /> <input
					type="hidden" name="imsi" value="<%=imsi%>" /> <input type="hidden"
					name="imei" value="<%=imei%>" /> <input type="hidden" name="phone"
					value="<%=phone%>" /> <input type="hidden" name="openid"
					value="<%=openid%>" />

				<div class="weui-cell weui-cell_vcode testControBF"
					style='background-color: #f6f6f6; width: 85%; margin: 0 auto; padding-left: 10px;'>
					<input class="weui-input" type="tel" placeholder="请输入验证码"
						name="phoneCode" style='width: 60%; padding: 20px 0;'> <a
						id="code"
						style="color: #999; width: 25%; margin: 0 auto; text-align: center;">59s</a>
				</div>

			</form>
		</div>
	</div>
	<div id="toast" style="display: none;">
		<div class="weui-mask_transparent"></div>
		<div class="weui-toast">
			<p class="weui-toast__content" id="toast_error"
				style='margin-top: 40%;'>发送失败</p>
		</div>
	</div>

	<div class="weui-btn-area" style="margin-left: 0; margin-right: 0;">
		<a class="weui-btn weui-btn_warn" href="javascript:" id="showTooltips"
			style='background-color: #ff7d55; width: 85%; margin: 0 auto;'>确定</a>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/weui/dist/example/weui.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/M/jquery.1.9.1.min.js"></script>
	<script type="text/javascript">
	var obj = $("#code");
		window.onload = function() {
			
			settime();
		}
		

var countdown=59;
var offsetFun = false;
function settime(){
 var timer =    setInterval(function(){
        obj.text(countdown+'s').css('color','#999');
        countdown--;
        if (countdown<=-1) {
      clearInterval(timer);
            obj.text('重新获取').css({'color':'#ff7d55'});
            offsetFun=true;
        }
    },1000)
}
obj.on('click',function(){
  var phone = $("input[name='phone']").val();
    if (offsetFun) {
         countdown=59
         offsetFun = false
        settime();
      $.ajax({
      async:false,
      url:"activation/sendSNS",
      data:{"phone":phone},
      type:'GET',
      dataType:'json',
      success:function(data){
        if(!data){
          toastTip();
        }
         },error:function(){
      
         } 
      });
    }
})







		var phone = $('input[name="phone"]').val();
		var imsi = $("input[name='imsi']").val();
		$('#showTooltips').on('click', function() {
			var phoneCode = $('input[name="phoneCode"]').val();
			if (phoneCode == "" || phoneCode == null) {
				$("#toast_error").text("请输入验证码！");
				toastTip();
				return false;
			}
			;
			$.ajax({
				async : false,
				url : "shareProfit/vlidPhoneCode",
				data : {
					"phone" : phone,
					"phoneCode" : phoneCode
				},
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					if (data) {
						$.ajax({
							async : false,
							url : "activation/vildPhone",
							data : {
								"phone" : phone,
								"imsi" : imsi
							},
							type : 'GET',
							dataType : 'json',
							success : function(data) {
								if (!data) {
									$("#toast_error").text("该设备已被激活！");
									toastTip();
								} else {
									$("#register").submit();
								}
							}
						})
					} else {
						$("#toast_error").text("验证码错误！");
						toastTip();
					}
				},
				error : function(sss) {
					console.log(sss)
				}
			});
	<%--$("#register").submit();--%>
		});
		$(function() {
			var $tooltips = $('.js_tooltips');
			$('#showTooltips').on('click', function() {
				if ($tooltips.css('display') != 'none')
					return;
				// toptips的fixed, 如果有`animation`, `position: fixed`不生效
				$('.page.cell').removeClass('slideIn');
				$tooltips.css('display', 'block');
				setTimeout(function() {
					$tooltips.css('display', 'none');
				}, 2000);
			});
		});

		function toastTip() {
			var $toast = $('#toast');
			if ($toast.css('display') != 'none')
				return;
			$toast.fadeIn(100);
			setTimeout(function() {
				$toast.fadeOut(100);
			}, 2000);
		};
	</script>
</body>
</html>
