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
<title>违章记录</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
 </head>
 <body>
    <div class="row" id="hphm" align="center" style="height:80px;background-color:#cccccc;line-height:80px;"></div>
    
    <div class="row" id="result" align="center" style="margin-top:20%;display:none; ">没有违章记录</div>
 
 </body>
 <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
   var hphm = "${hphm}";
   $("#hphm").text(hphm);
   var data ={};
   data =  '${IllegalResultVO}';
   var result = data.result;
   if(result!=null){
	   
   }else{
	   $("#result").show();
   }
   
</script>
</html>