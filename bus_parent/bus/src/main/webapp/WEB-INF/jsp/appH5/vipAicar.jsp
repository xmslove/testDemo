<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style type="text/css">
.listOuter{border:1px solid #ddd;padding-bottom:0;margin-bottom:0;}
.listRight{color: #999;padding-left:0;text-align:right;}
#packageName{padding-left:0.5rem;padding-bottom:1rem;}
@media all and (max-width: 625px) { 
		.listFont{font-size: 0.65rem;}
	}
</style>
<head>
<base href="<%=basePath%>">    
<title>aicar会员</title>        
 </head>
 <body>
<section class="aui-content-padded" >
		<div class="aui-card-list">
			<div class="aui-card-list-content-padded listOuter" >
				<div class="form-group">
					<div class="row" >
						<div class="col-xs-12">
							<div class="form-group">
                
								<div class="row">
									<p id="packageName"></p>
								</div>

								<div class="row">
									<div class="col-xs-9">
										<img src="png/w-list-1.png" style='width: 0.8rem;display:inline-block;margin-right:0.75rem;' alt="">
										<span class="listFont">普通流量套餐</span> 
									</div>
									<div class="col-xs-3 listRight" id="trafficCeiling"></div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-xs-5" id="label" style="padding-right:0;">
										<img src="png/w-list-2.png" style='width: 0.8rem;display:inline-block;margin-right:0.75rem;' alt="">
										<span class="listFont">wifi热点共享</span> 
									</div>
									<div class="col-xs-7 listRight"  id="wifiCeiling" ></div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-xs-9">
										<img src="png/w-list-3.png" style='width: 0.8rem;display:inline-block;margin-right:0.75rem' alt="">
										<span class="listFont">视频流量</span> 
									</div>
									<div class="col-xs-3 listRight"  id="ifVideo"></div>
								</div>
							</div>
							
					</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		
		<div class="aui-content-padded" style="margin-top:1.5rem;">
		<div class="row">
			<div class="col-xs-8" style="height:50px; ">
				<h1 style="margin-top:8px; ">套餐剩余天数<font color="#ff7d55" id="packageDay" style="font-size:26px;padding-left:0.5rem"></font></h1>
			</div>
			<div class="col-xs-4" style="height:50px; ">
				<span class="aui-btn  aui-btn-block aui-btn-sm" id="pay" style="background-color:#ff7d55;color:#fff;" >套餐充值</span>
			</div>
		</div>
	</div>
	
	<div class="weui-skin_android" id="actionsheet" style="display: none">
        <div class="weui-mask"></div>
        <div class="weui-actionsheet">
            <div class="weui-actionsheet__menu" id="createQRCode">
               
            </div>
        </div>
    </div>
 </body>
</html>
<script type="text/javascript">
var $androidActionSheet = $('#actionsheet');
var $androidMask = $androidActionSheet.find('.weui-mask');
 var imsi = "${imsi}";
 var packageType;
	$.ajax({
     	async:false,
     	url:"appH5/getUserPackageMsg",
     	data:{"imsi":imsi},
     	type:'GET',
     	dataType:'json',
     	success:function(data){
        	$("#packageDay").text(data.packageDay+"天")
     		$("#packageName").css({'color':'#333','fontSize':'0.6rem'}).text("您在使用 "+data.packageName);
     		var wifiLength = data.wifiLength;
     		packageType = data.packageType;
     		$.ajax({
         	async:false,
         	url:"weiXinScan/getPackageMsg",
         	data:{"packageType":packageType},
         	type:'GET',
         	dataType:'json',
         	success:function(data){
            if(data.trafficCeiling=="-"){
                $("#trafficCeiling").text("无限制");
            }else{
                $("#trafficCeiling").text(data.trafficCeiling+"M");
            }; 
             if(data.wifiCeiling=="-"){
                $("#label").removeClass("col-xs-5");
                $("#wifiCeiling").removeClass("col-xs-7");
                $("#wifiCeiling").attr("align","");
                $("#label").addClass("col-xs-9");
                $("#wifiCeiling").addClass("col-xs-3");
                $("#wifiCeiling").text("无限制");
            }else{
              //  $("#wifiCeiling").text("剩余"+wifiLength+"M (共"+data.wifiCeiling+"M)");  
            	$("#wifiCeiling").text("剩余"+Math.round(((data.wifiCeiling*1024-wifiLength*1)*1/1024)*10)/10+"M (共"+data.wifiCeiling+"M)"); 
            }; 
          if(data.ifVideo=="2"){
                $("#ifVideo").text("不允许");
            }else{
                $("#ifVideo").text("允许");
            }; 
         	   }
          });
     	   }
      });
      
      var no = 1 ;
      $("#pay").click(function(){
      var url = "<%=basePath %>weiXinScan/topayPackage?imsi="+imsi;
      var code = "<img alt='加载失败' src='weiXinScan/createQRCodeByUrl?no="+no+"&payUrl="+url+"' id='qr'/>";
		        	no++;
		        	$("#createQRCode").append(code);
		            $androidActionSheet.fadeIn(200);
		            $androidMask.on('click',function () {
		            	$("#qr").remove();
		                $androidActionSheet.fadeOut(200);
		                window.location.reload();
		            });
      })
      
   

</script>