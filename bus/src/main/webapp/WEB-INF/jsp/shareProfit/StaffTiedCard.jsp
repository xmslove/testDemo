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
    <title>aicar卡绑定</title>  
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
  <style>
    .staffTied_title h1{font-size:20px;color:black;}
    .staffTied_title span{color:#fd7a7d;font-size:30px;}
    </style>
 </head>
 <body ontouchstart>
 
 <div class="form-group staffTied_title" align="center" style="margin-top:5% " >
 <p><h1 >您好，<span>${StaffMsgVO.staffName}</span></h1></p>
  <p><h1 >请点击设备aicar应用-设置</h1></p>
    <p><h1 >扫设备上的二维码</h1></p>
 </div>
 <br>
 <div class="form-group">
 <div class="row">
  <div class="col-xs-1"></div> 
  <div class="col-xs-10" align="center"> 
 <img class="weui-media-box__thumb" src="png/bus_login.png" alt="" style="width:200px;height:200px;  "/><br><br>
 <p>请在sim卡客户端激活成功后24小时内绑定</p>
 </div>
<div class="col-xs-1"></div> 
 </div>
 </div>
 <br>
 <div class="form-group">
  <div class="row">
  <div class="col-xs-1"></div> 
  <div class="col-xs-10"> 
 <a class="weui-btn weui-btn_default"  id="sq" style='background-color:#fd5e62;color:#fff'>点击扫描二维码</a>
 </div>
<div class="col-xs-1"></div> 
 </div>
 </div>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">
var openid ="${openid}";
var mchNo ="${mchNo}";
var staffPhone ="${staffPhone}";
var staffName ="${staffName}";

var appId = "${appId}";
var timestamp = "${timestamp}";
var nonceStr = "${nonceStr}";
var signature = "${signature}";

wx.config({  
    debug: false,  
    appId:appId,  
    timestamp:timestamp,  
    nonceStr:nonceStr,  
    signature:signature,  
    jsApiList : [ 'checkJsApi', 'scanQRCode' ]  
});//end_config  

wx.error(function(res) {  
    alert("出错了：" + res.errMsg);  
});  

wx.ready(function() {  
    wx.checkJsApi({  
        jsApiList : ['scanQRCode'],  
        success : function(res) {  
        }  
    });  
    
    $("#sq").click(function(){
    	//扫描二维码   
        wx.scanQRCode({  
            needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
            scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
            success : function(res) {  
         var imsi = res.resultStr; // 当needResult 为 1 时，扫码返回的结果   
         //判断销售是注册完成绑定卡 还是销售直接绑卡
         if(staffPhone!=null && staffPhone!=""){
        	 window.location.href="<%=basePath %>shareProfit/staffRegister6?imsi="+imsi+
             "&openid="+openid+
             "&mchNo="+mchNo+
             "&staffName="+staffName+
             "&staffPhone="+staffPhone; 
         }else{
        	 window.location.href="<%=basePath %>shareProfit/staffTiedCard?imsi="+imsi+
             "&openid="+openid;
         }
            }  
        }); 
    });
    
      
      
});

</script>
</html>