<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imsi = request.getParameter("imsi");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">    
<title>aicar会员</title>
<style type="text/css">
	.customerTitle{text-align: left;}
	.customerTitle span{font-size: 0.7rem;color: #333;}
	.customerTitle h3{font-size: 0.9rem;color: #333;}
</style>        
 </head>
 <body>
 <div class="form-group" style="margin-top:20px; ">
<div class="row" style="margin-left:0;margin-right:0;" align="center">
<h2>您还未购买任何流量套餐</h2>
</div >
<div class="row" style="margin-left:0;margin-right:0;margin-top:10px;" align="center">
<p>请使用微信扫一扫二维码</p>
</div >
 </div>
 <div style="text-align:center;">
 	<img alt="image"  id="pay" style="width:40%;" >
 </div>
 
 <div style="text-align:center;margin-top:20px;">
 <div class="col-xs-2"></div>
  <div class="col-xs-8"><span class="aui-btn  aui-btn-block aui-btn-sm" id="goVip" style="background-color:#ff7d55;color:#fff;" >我已充值</span></div>
   <div class="col-xs-2"></div>
 </div>
 
 <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <p class="weui-toast__content" id="toast_error" style='margin-top:40%;'>您还未购买任何流量套餐</p>
        </div>
        </div>
 
 </body>
 <script type="text/javascript" >
var imsi = "${imsi}";
var url = "<%=basePath %>weiXinScan/topayPackage?imsi="+imsi;
var codeUrl = "weiXinScan/createQRCodeByUrl?payUrl="+url;
$("#pay").attr("src",codeUrl);
$("#goVip").click(function(){
	$.ajax({
	   	async:false,
	   	url:"appH5/isUserActivation",
	   	data:{"imsi":imsi},
	   	type:'GET',
	   	dataType:'json',
	   	success:function(data){
	   		alert(data.annualFee);
	   		if(data.annualFee){
	   		 window.location.href="<%=basePath %>appH5/aicarHome?imsi="+imsi;	
	   		}else{
	   			toastTip();	
	   		}
	   	},error:function(){
				toastTip(); 
	   	   } 
	    });
});

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