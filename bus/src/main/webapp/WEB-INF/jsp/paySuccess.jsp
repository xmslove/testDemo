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
<title>小车车会员</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/util.js"></script>
</head>
<style type="text/css">
     *{padding: 0;margin: 0}
     ul,li{list-style-type: none;}
     .payContent{text-align: center;margin-top: 15%;}
     .payContent>h2{color: #ff7d55;font-weight: normal;padding-bottom: 20px;}
     .payContent>div{color: #999;}
     .payContent>div>span{color:  #ff7d55;}
</style>
<body ontouchstart>

<div class="payContent">
<h2>恭喜您购买成功!</h2>
<div id="packageName"></div>
<div>套餐截止日志至<span id="packageFailureTime"></span></div>
</div>

<script type="text/javascript">
$(function(){
     //查询代付套餐信息
     var packageType = "${packageType}";
     var imsi_id = "${imsi_id}";
     $.ajax({
          async:false,
          url:"weiXinScan/getUserPackageMsg",
          data:{"packageType":packageType,"imsi_id":imsi_id},
          type:'GET',
          dataType:'json',
          success:function(data){
               $("#packageName").text(data.packageName);
               var time = data.packageFailureTime ; 
               var d = new Date(time);
               var packageFailureTime = $.getFormateDate("yyyy-MM-dd",d);
               $("#packageFailureTime").text(packageFailureTime);
             }
      });
})
</script>
</body>
</html>
