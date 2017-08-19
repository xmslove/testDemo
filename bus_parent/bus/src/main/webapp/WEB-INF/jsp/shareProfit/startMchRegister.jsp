<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String brandNo = request.getParameter("brandNo");
String openid = request.getParameter("openid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>aicar商家平台</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
  <style>
    body{background-color:#f6f6f6;}
    .startStaff_font{margin-top:60px;}
    .startStaff_font p{text-align:center;padding:10px 0; color:#fd5e62 ;font-size:14px;}
    .startStaff_font a{width:85%;}
    .startStaff_font a:after{border-color:#fd5e62}
    </style>
 </head>
 <body ontouchstart>
    <div class="page">
    <div class="page__hd">
    <img src="<%=request.getContextPath() %>/png/sale-2.png" style="height:auto;width:auto\9;width:100%;"/>
    </div>
    <div class="page__bd startStaff_font" >
    <a class="weui-btn weui-btn_primary" style="width:85%;background-color:#fff;color:#fd5e62;border-color:#fd5e62;" href="javascript:;" id='startMchRegister'>注册为商家</a>

    <a href="javascript:;" class="weui-btn weui-btn_default"style='background-color:#fd5e62;color:#fff;width:85%;'  id="staffTiedCard"  > 查看我的商家信息</a>
    <div class="weui-cells weui-cells_form">

    <input type="hidden" name="brandNo"  id="brandNo"  value="<%=brandNo%>"/>
    <input type="hidden" name="openid"   id="openid"   value="<%=openid%>"/>

    <div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
    <p class="weui-toast__content" id="toast_error" style='margin-top:35%; font-size:20px;color:#fff;'>请注册</p>
    </div>
    </div>

    </div>
    </div>
    </div>
       
    <!-- 授权使用避免参数的拼接导致微信签名失败 -->
    <form  method="post" id="mchMsg">
    <input type="hidden" name="brandNo" value="<%=brandNo%>"/>
    <input type="hidden" name="openid"  value="<%=openid%>"/>
    </form>
    
     <!-- 授权使用避免参数的拼接导致微信签名失败 -->
    <form  method="post" id="form1" action="shareProfit/mchRegister">
    <input type="hidden" name="brandNo" value="<%=brandNo%>"/>
    <input type="hidden" name="openid"  value="<%=openid%>"/>
    </form>
    
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
    $('#staffTiedCard').on('click',function(){
    var brandNo = $("#brandNo").val();
    var openid = $("#openid").val();
    $.ajax({
    async:false,
    url:"shareProfit/vildMch",
    data:{"brandNo":brandNo,"openid":openid},
    type:'GET',
    dataType:'json',
    success:function(data){
    if(data){
    var url = "shareProfit/mchLoginMsg";
    $("#mchMsg").attr("action",url);
    $("#mchMsg").submit();
    }else{
    toastTip();
    }
    },error:function(data){
    toastTip();
    }
    });

    })
    $('#startMchRegister').on('click',function(){
    var brandNo = $("#brandNo").val();
    var openid = $("#openid").val();
    $.ajax({
    async:false,
    url:"shareProfit/vildMch",
    data:{"brandNo":brandNo,"openid":openid},
    type:'GET',
    dataType:'json',
    success:function(data){
    if(!data){
    	$("#form1").submit();
    }else{
    $('#toast_error').text('您已注册')
    toastTip();
    }
    },error:function(data){
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



</script>
</html>