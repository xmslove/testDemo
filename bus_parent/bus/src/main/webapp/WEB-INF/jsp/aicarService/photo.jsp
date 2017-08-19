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
<title>aicar会员</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
 </head>
 <style>
 .spinner {
  margin: 100px auto 0;
  width: 150px;
  text-align: center;
}
 
.spinner > div {
  width: 30px;
  height: 30px;
  background-color: #67CF22;
 
  border-radius: 100%;
  display: inline-block;
  -webkit-animation: bouncedelay 1.4s infinite ease-in-out;
  animation: bouncedelay 1.4s infinite ease-in-out;
  /* Prevent first frame from flickering when animation starts */
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}
 
.spinner .bounce1 {
  -webkit-animation-delay: -0.32s;
  animation-delay: -0.32s;
}
 
.spinner .bounce2 {
  -webkit-animation-delay: -0.16s;
  animation-delay: -0.16s;
}
 
@-webkit-keyframes bouncedelay {
  0%, 80%, 100% { -webkit-transform: scale(0.0) }
  40% { -webkit-transform: scale(1.0) }
}
 
@keyframes bouncedelay {
  0%, 80%, 100% {
    transform: scale(0.0);
    -webkit-transform: scale(0.0);
  } 40% {
    transform: scale(1.0);
    -webkit-transform: scale(1.0);
  }
}
</style>
 <body>
   <div class="spinner" id="logind" style="margin-top:50%;">
  <div class="bounce1"></div>
  <div class="bounce2"></div>
  <div class="bounce3"></div>
</div>
<div align="center" id="logindFont">照片拍摄中请稍后...</div>
 
   <div class="row"  style="display:none;" id="showPhoto">
   <section class="aui-content-padded" >
   <div class="aui-card-list">
   <div class="aui-card-list-content-padded listOuter" >
   <div class="form-group">
   <div class="row"  style="display:none; " id="photoDate">
       拍摄时间：<font id="photoOp"></font>
   </div>
   <div class="row">
   <img  id="photo">
   </div>
   </div>
   </div>
   </div>
   </section>
  </div>

  <div align="center" style="width:80%;text-align:center;margin-left:10%;margin-top:10%;display:none; " id="agoinPhoto"> 
			<a class="weui-btn weui-btn_primary" href="<%=request.getContextPath() %>/mobleCar/getService4">重新拍照</a>
  </div>
  
   <div align="center" style="width:80%;text-align:center;margin-left:10%;margin-top:5%;display:none;" id="savePhoto"> 
			<a class="weui-btn weui-btn_default">保存到手机</a>
  </div>
  
 
 </body>
 <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
 var imsi = "${imsi}" ;
setTimeout(function() {
	$.ajax({
	    async:false,
	    url:"services/api/mobleCar/getPhoto",
	    data:{"imsi":imsi},
	    dataType:'json',
	    success:function(data){
	    	$("#logind").remove();
	        $("#logindFont").remove();
	    	$("#showPhoto").show();
	    	$("#photoDate").show();
	    	$("#agoinPhoto").show();
	    	$("#savePhoto").show();
	    	$("#photoOp").text(data.photoOp);
	        $("#photo").attr("src",data.photoUrl);
	       }
	    });
}, 5000);


</script>
</html>