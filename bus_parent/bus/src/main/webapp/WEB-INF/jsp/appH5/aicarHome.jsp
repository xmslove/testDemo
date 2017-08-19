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
<body ontouchstart>
<div class="page flex js_show">   
<div class="page__bd page__bd_spacing">
<div class="row" style="margin:0;">
<div class="col-xs-3" style="height:100%; background-color:#21242b;">
<div align="center" style="margin-top:25%; "><h1 style="color:white; ">aicar</h1></div>
<div align="center" style="margin-top:15%;text-align: center; " id="vip"><img alt="image" id="vippng" src="png/vip.png" style="width:66px;height:66px;  "></div>
<div align="center" style="margin-top:12%; " id="people"><img alt="image" id="peoplepng"  src="png/peopleclose.png" style="width:66px;height:66px;  "></div>
<div align="center" style="margin-top:50%; "><h4 style="color:white;font-size: 0.8rem;font-weight: normal; " id="set">设置</h4></div>
</div>

<div class="col-xs-9" style="margin:0;height:100%;padding:0;" id="conetent">

</div>
</div>
</div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
   var imsi = "${imsi}";


	$("#conetent").load("<%=basePath %>appH5/vipAicar?imsi="+imsi);
	
	$("#people").click(function(){
	     <%-- $("#conetent").load("<%=basePath %>appH5/customerService?imsi="+imsi); --%>
	     $("#conetent").load("<%=basePath %>appH5/aicarService?imsi="+imsi);
	     $("#peoplepng").attr("src","png/people.png");
	     $("#vippng").attr("src","png/vipclose.png");
	})
	
	$("#vip").click(function(){
	    $("#conetent").load("<%=basePath %>appH5/vipAicar?imsi="+imsi); 
		$("#peoplepng").attr("src","png/peopleclose.png");
	     $("#vippng").attr("src","png/vip.png");
		
	})
	
	$("#set").click(function(){
		$("#conetent").load("<%=basePath %>appH5/set?imsi="+imsi);
		 $("#peoplepng").attr("src","png/peopleclose.png");
	     $("#vippng").attr("src","png/vipclose.png");
	})
	
})


</script>
</html>
