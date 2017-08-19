
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
<style>
.register_res {
	color: #ff7d55;
	font-weight: normal;
}

.register_res {
	margin-top: 80px;
}

.register_res span {
	font-size: 25px;
}
</style>
</head>
<style>
</style>
<body ontouchstart>
	<img src="<%=request.getContextPath()%>/png/1_new.png"
		style="height: auto; width: auto\9; width: 100%;" />
	<div align="center">
		<h4 class='register_res'>
			现在注册，立享30天<span>免费试用</span>特权
		</h4>
	</div>
	<div class="page">
		<div class=" weui-cells_form">
			<!--  <form action="activation/register" method="post" id="register"> -->
			<form action="activation/register1" method="post" id="register">
				<input type="hidden" name="code" value="<%=code%>" /> <input
					type="hidden" name="imsi" value="<%=imsi%>" /> <input type="hidden"
					name="imei" value="<%=imei%>" /> <input type="hidden" name="openid"
					value="<%=openid%>" />

				<div align="center"
					style='margin: 50px auto 0; padding: 10px 0 10px 20px; background-color: #f6f6f6; width: 73%;'>
					<input class="weui-input" type="number" pattern="[0-9]*"
						placeholder="请您输入手机号码" name="phone">
				</div>
			</form>
		</div>
		<div class="weui-btn-area" style='margin-left: 0; margin-right: 0;'>
			<a class="weui-btn weui-btn_warn" href="javascript:"
				id="showTooltips" style='background-color: #ff7d55; width: 76.67%;'
				onclick="sendSNS()">下一步</a>
		</div>
		<div align='center' class='weui-agree'>
			<span class="weui-agree__text">注册即代表同意<a
				href="<%=basePath%>smallCarPro.html" style='color: #ff7d55'>《aicar用户协议》</a></span>
		</div>

		<!-- 验证码发送失败提示 begin -->
		<div id="toast" style="display: none;">
			<div class="weui-mask_transparent"></div>
			<div class="weui-toast">
				<p class="weui-toast__content" id="toast_error"
					style='margin-top: 40%;'>发送失败</p>
			</div>
		</div>
		<!-- 验证码发送失败提示 end -->

	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/weui/dist/example/weui.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/M/jquery.1.9.1.min.js"></script>
	<script type="text/javascript">
		var countdown = 60;
		var sns = 0;
		function settime1() {
			var phone = $("input[name='phone']").val();
			$.ajax({
				async : false,
				url : "activation/sendSNS",
				data : {
					"phone" : phone
				},
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					if (!data) {
						toastTip();
					}
					sns++;
				},
				error : function() {
					toastTip();
					sns++;
				}
			});
		}

		function settime(obj) {
			if (countdown == 0) {
				sns = 0;
				$(obj).css("color", "#3CC51F");
				obj.removeAttribute("disabled");
				$(obj).text("获取验证码");
				countdown = 60;
				return;
			} else {
				if (sns == 0) {
					settime1();
				}
				;
				obj.setAttribute("disabled", true);
				$(obj).css("color", "#cccccc");
				$(obj).text("重新发送(" + countdown + ")");
				countdown--;
			}
			;
			setTimeout(function() {
				settime(obj);
			}, 1000);
		};

		function sendSNS() {
			var phone = $("input[name='phone']").val();
			var imsi = $("input[name='imsi']").val();
			if (phone == "" || phone == null) {
				$("#toast_error").text("请输入手机号");
				toastTip();
				return false;
			}
			;
			if (!(/^1[34578]\d{9}$/.test(phone))) {
				$("#toast_error").text("请输入正确的手机号格式");
				toastTip();
				return false;
			}
			;
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
						$.ajax({
							async : false,
							url : "activation/sendSNS",
							data : {
								"phone" : phone
							},
							type : 'GET',
							dataType : 'json',
							success : function(data) {
								if (!data) {
									toastTip();
								} else {
									$("#register").submit();
								}

							},
							error : function() {
								toastTip();
							}
						});
					}
				}
			});
		}

		function toastTip() {
			var $toast = $('#toast');
			if ($toast.css('display') != 'none')
				return;
			$toast.fadeIn(100);
			setTimeout(function() {
				$toast.fadeOut(100);
			}, 2000);
		};

		function activation() {
			$("#register").submit();
		}

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
	</script>
</body>
</html>
