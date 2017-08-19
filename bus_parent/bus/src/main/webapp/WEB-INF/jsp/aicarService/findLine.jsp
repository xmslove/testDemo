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
 <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/> 
 </head>
 <body>
 <div align="center" style="height:10%;background-color:#cccccc;text-align:center;">
 
<input id="date" class="laydate-icon" align="center" style="width:200px;text-align:center;margin-top:3%;  " />

</div>
 
 
 
 
 <div id="container" style="height:90%;margin-top:16%;"></div> 
 
 </body>

<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=a7e02b77430c5867cf93b263234dbeba"></script>  
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script> 
<script src="<%=request.getContextPath() %>/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript">

var openid = "${openid}";

laydate({
	  elem: '#date',
	  format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
	  festival: true, //显示节日
	  choose: function(datas){ //选择日期完毕的回调
	      var selectDate = datas;
	      $.ajax({
	          async:false,
	          url:"services/api/mobleCar/getAllLine",
	          data:{"openid":openid,"selectDate":selectDate},
	          dataType:'json',
	          success:function(data){
	   	    	   if(data.length!=0){
	   	    		   var lineArr =new Array();
	   	       	    for ( var i = 0; i < data.length; i++) {
	   	       	    	lineArr[i] = new Array();
	   	       	    	lineArr[i][0] =data[i].lng;
	   	       	    	lineArr[i][1] =data[i].lat;
	   	   			};
	   	   			var  lngCount = 0;
	   		   			var  latCount = 0;
	   		   			for ( var i = 0; i < data.length; i++) {
	   		   				lngCount = lngCount + data[i].lng*1 ;
	   		   				latCount = latCount + data[i].lat*1 ;
	   					};
	   					lngCount = lngCount/data.length;
	   					latCount = latCount/data.length;
	   		   			 var map = new AMap.Map('container', {  
	   		   	    	       resizeEnable: true,  
	   		   	    	       center: [lngCount,latCount],  
	   		   	    	       zoom: 13  
	   		   	    	   }); 
	   	   			 var map = new AMap.Map('container', {  
	   	   	    	       resizeEnable: true,  
	   	   	    	       center: [data[0].lng, data[0].lat],  
	   	   	    	       zoom: 13  
	   	   	    	   }); 
	   	   			 var polyline = new AMap.Polyline({  
	   	   	    	       path: lineArr,          //设置线覆盖物路径  
	   	   	    	       strokeColor: "#000000", //线颜色  
	   	   	    	       strokeOpacity: 2,       //线透明度  
	   	   	    	       strokeWeight: 2,        //线宽  
	   	   	    	       strokeStyle: "solid",   //线样式  
	   	   	    	       strokeDasharray: [10, 5] //补充线样式  
	   	   	    	   });  
	   	   	    	   polyline.setMap(map);
	   	    	   }else{
	   	    		$("#container").text("暂无行车记录");   
	   	    	   };
	   	    	   
	   	    	   
	   	    	   
	              }
	          });
	      
	  }
	});

   $.ajax({
       async:false,
       url:"services/api/mobleCar/getAllLine",
       data:{"openid":openid},
       dataType:'json',
       success:function(data){
	    	   if(data.length!=0){
	    		   var lineArr =new Array();
	       	    for ( var i = 0; i < data.length; i++) {
	       	    	lineArr[i] = new Array();
	       	    	lineArr[i][0] =data[i].lng;
	       	    	lineArr[i][1] =data[i].lat;
	   			};
	   			var  lngCount = 0;
		   			var  latCount = 0;
		   			for ( var i = 0; i < data.length; i++) {
		   				lngCount = lngCount + data[i].lng*1 ;
		   				latCount = latCount + data[i].lat*1 ;
					};
					lngCount = lngCount/data.length;
					latCount = latCount/data.length;
		   			 var map = new AMap.Map('container', {  
		   	    	       resizeEnable: true,  
		   	    	       center: [lngCount,latCount],  
		   	    	       zoom: 13  
		   	    	   }); 
	   			 var map = new AMap.Map('container', {  
	   	    	       resizeEnable: true,  
	   	    	       center: [data[0].lng, data[0].lat],  
	   	    	       zoom: 13  
	   	    	   }); 
	   			 var polyline = new AMap.Polyline({  
	   	    	       path: lineArr,          //设置线覆盖物路径  
	   	    	       strokeColor: "#000000", //线颜色  
	   	    	       strokeOpacity: 2,       //线透明度  
	   	    	       strokeWeight: 2,        //线宽  
	   	    	       strokeStyle: "solid",   //线样式  
	   	    	       strokeDasharray: [10, 5] //补充线样式  
	   	    	   });  
	   	    	   polyline.setMap(map);
	    	   }else{
	    		$("#container").text("暂无行车记录");   
	    	   };
	    	   
	    	   
	    	   
           }
       });
   
  
   

</script>
</html>