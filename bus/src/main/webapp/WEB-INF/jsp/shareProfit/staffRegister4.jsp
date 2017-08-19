<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String mchNo = request.getParameter("mchNo");
String staffPhone = request.getParameter("staffPhone");
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


 </head>
<body ontouchstart>
<form method="post" action="shareProfit/staffRegister5" id="form1">
<input type="hidden" name="mchNo" value="<%=mchNo %>">
<input type="hidden" name="staffPhone" value="<%=staffPhone%>">
 <input type="hidden" name="openid" value="<%=openid %>"/>
 <div class="form-group">
 <div class="col-xs-1"></div>
 <div class="col-xs-10">

<div style="margin-top:10% ">
<label style="font-size:20px;">欢迎您成为aicar一员！</label><br>
<label style="color:#666;">希望您能同aicar一起，给客户提供最周到的服务</label>
 </div>
 
<div style="margin-top:10% ">
<label>请问您怎么称呼？</label>
<input type="text"  class="form-control staffRegister_title" placeholder="请输入您的真是姓名" name="staffName" style="height:48px;background-color:#f6f6f6;border:0; "/>
 </div>

<div style="margin-top:10% ">
<span class="weui-btn weui-btn_primary" id="next1" style="background-color:#ff6666;text-decoration:none;color:#fff">完成注册，离开</span>
 </div>
 
 <div style="margin-top:10% ">
<span class="weui-btn weui-btn_primary" id="next2" style="background-color:#ff6666;text-decoration:none;color:#fff">开始绑定aicar卡</span>
 </div>
 
 </div>
 <div class="col-xs-1"></div>
 </div>

</form> 

<!-- 验证码发送失败提示 begin -->
		<div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <p class="weui-toast__content" id="toast_error" style='margin-top:40%'>发送失败</p>
        </div>
        </div>
        <!-- 验证码发送失败提示 end -->

        <form action="shareProfit/toStaffTiedCard" id="form2" method="post">
			<input type="hidden" name="mchNo" value="<%=mchNo %>">
			<input type="hidden" name="staffPhone" value="<%=staffPhone%>">
			<input type="hidden" name="openid" value="<%=openid %>"/>
			<input type="hidden" name="staffName"/>
        </form>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">
var mchNo = "${mchNo}";
var staffPhone = "${staffPhone}";

    $("#next2").click(function(){

    	var staffName = $("#form1 input[name='staffName']").val();
    	if(staffName=="" || staffName==null){
    		$("#toast_error").text("请输入您的姓名");
    			toastTip();
    			return false ;
    	};
    	//扫描二维码   
        $("#form2 input[name='staffName']").val(staffName);
$("#form2").submit();
    
    });
    
      
      




$("#next1").click(function(){
	var staffName = $("input[name='staffName']").val();
	if(staffName=="" || staffName==null){
		$("#toast_error").text("请输入您的姓名");
			toastTip();
			return false ;
	};
	    
	$("#form1").submit();
	
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