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
    <!-- <div class="page" style="margin-top:20px;margin-left:15px;margin-right:15px; " align="left">
    <h4 style="color:#666; ">请查看手机接收的短信验证码</h4>
    <div class="weui-cells weui-cells_form">
    <form action="shareProfit/staffRegister4" method="post" id="register">
    <input type="hidden" name="mchNo" value="<%=mchNo %>">
    <input type="hidden" name="staffPhone" value="<%=staffPhone%>">
    <input type="hidden" name="openid" value="<%=openid %>"/>
    <div class="weui-cell weui-cell_vcode" style='background-color:#f6f6f6'>
    <span>
    <input class="weui-input" type="tel" placeholder="请输入验证码" name="phoneCode" style='width:60%;padding: 20px 0; '>
    </span>
    <span id="code"  style="color:#999;width:25%;margin:0 auto;  text-align:center;">59s</span>
    </div>

    </form>
    </div>
    </div>
    <div class="weui-btn-area">
    <span class="weui-btn weui-btn_warn" href="javascript:" id="next"  style='background-color:#ff6666'>确定</a>
    </div> -->





 <div class="page" style="margin-top: 20px;" align="left">
    <h4 style="color: #666; width: 85%; margin: 0 auto;">请查看手机接收的短信验证码</h4>
    <div class="weui-cells weui-cells_form testControBF">
      <form action="shareProfit/staffRegister4" method="post" id="register">
        <input type="hidden" name="mchNo" value="<%=mchNo %>">
        <input type="hidden" name="staffPhone" value="<%=staffPhone%>">
        <input type="hidden" name="openid" value="<%=openid %>"/>


        <div class="weui-cell weui-cell_vcode testControBF"
          style='background-color: #f6f6f6; width: 85%; margin: 0 auto; padding-left: 10px;'>
          <input class="weui-input" type="tel" placeholder="请输入验证码"
            name="phoneCode" style='width: 60%; padding: 20px 0;'> <span  id="code" style="color: #999; width: 25%; margin: 0 auto; text-align: center;">60S</span>
        </div>

      </form>
    </div>
  </div>
 <!-- 验证码发送失败提示 begin -->
    <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
           
            <p class="weui-toast__content" id="toast_error" style="margin-top:40%;">验证码错误</p>
        </div>
        </div>
  <!-- 验证码发送失败提示 end -->

  <div class="weui-btn-area" style="margin-left: 0; margin-right: 0;">
    <a class="weui-btn weui-btn_warn" href="javascript:" id="next"
      style='background-color: #ff6666; width: 85%; margin: 0 auto;'>确定</a>
  </div>





 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
var obj = $("#code");
window.onload = function(){
    settime();
}


var countdown=59;
var offsetFun = false;
function settime(){
 var timer =    setInterval(function(){
        obj.text(countdown+'S').css('color','#999');
        countdown--;
        if (countdown<=-1) {
      clearInterval(timer);
            obj.text('重新获取').css({'color':'#ff6666'});
            offsetFun=true;
        }
    },1000)
}
obj.on('click',function(){
   var phone = $("input[name='mchUserPhone']").val();
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

$("#next").click(function(){
	var phoneCode = $("input[name='phoneCode']").val();
	var staffPhone =  $("input[name='staffPhone']").val();
	if(phoneCode=="" || phoneCode==null){
		$("#toast_error").text("请输入验证码");
			toastTip();
			return false ;
	};
	
	    $.ajax({
           	async:false,
           	url:"shareProfit/vlidPhoneCode",
           	data:{"phone":staffPhone,"phoneCode":phoneCode},
           	type:'GET',
           	dataType:'json',
           	success:function(data){
           		if(!data){
                  $("#toast_error").text("验证码错误");
            			toastTip(); 
           	   }else{
           		$("#next").attr("disabled",true);  
           		$("form").submit();
           	   }
           		
           	},error:function(){
                   $("#toast_error").text("验证失败");
        			toastTip(); 
           	   } 
            });
	
})

function toastTip(){
        var $toast = $('#toast');
            if ($toast.css('display') != 'none') return;
            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
    };

$(function(){
    var $tooltips = $('.js_tooltips');
    $('#showTooltips').on('click', function(){
        if ($tooltips.css('display') != 'none') return;
        // toptips的fixed, 如果有`animation`, `position: fixed`不生效
        $('.page.cell').removeClass('slideIn');
        $tooltips.css('display', 'block');
        setTimeout(function () {
            $tooltips.css('display', 'none');
        }, 2000);
    });        
});
</script>
</html>