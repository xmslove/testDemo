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
 </head>
 <body>
 <div class="row" align="center" style="margin-top:10% ">
<input  name="addrsProvence" id="addrsProvence" /><br/>
<input  name="addrs" id="addrs" />
<button onclick="getShreah()">搜索</button>
  </div> 
  
<section class="aui-content-padded" >
		<div class="aui-card-list">
			<div class="aui-card-list-content-padded listOuter" >
				<div class="form-group">
					<div class="row" id="list" align="center">
                        
					</div>
				</div>
			</div>
		</div>
</section>  
 
 </body>
 <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">

   var imsi = "${imsi}";
    
   getShreah = function(){
	   $("#list div").remove();
	     var addrsProvence = $("#addrsProvence").val();
	     var addrs =  $("#addrs").val();
	     
	     $.ajax({
	          async:false,
	          url:"mobleCar/getShreah",
	          data:{"addrsProvence":addrsProvence,"addrs":addrs},
	          dataType:'json',
	          success:function(data){
                    var result = data.results;
                    for ( var i = 0; i < result.length; i++) {
						name = result[i].address;
						lat = result[i].location.lat;
						lng = result[i].location.lng;
						 var html = '<div class="row" onclick=\'toclient("'+i+'")\' style="margin-top:10%;"><input type="hidden" name="lat" id="lat'+i+'" value="'+lat+
	  	        			'"><input type="hidden" name="lng" id="lng'+i+'" value="' +lng+
	  	        			'"><input type="hidden" name="classno" id="classno" value="' +name+
	  	        			'"><p><input type="hidden" name="cityName" id="cityName" value="' +name+
	  	        			'" />' +name+
	  	        			'</p></div>';
	  	        			$("#list").append(html); 
					
					}
	          }
	          });
	     
   }
   
   toclient = function(i){
	   var lng = $("#lng"+i).val();
	   var lat = $("#lat"+i).val();
window.location.href="<%=basePath %>mobleCar/toclient?lng="+lng+"&lat="+lat+"&imsi="+imsi;
   }
   
  
</script>
</html>