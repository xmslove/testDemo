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
	.setList1{font-size: 0.65rem;color: #333;padding-top:0.2rem;padding-right: 0;}
	.setListRight{font-size: 0.65rem;color: #333;padding-top:0.2rem;text-align: left;padding-left: 0;}
	.setFoot{margin-top:0.7rem; }
	.setFoot li{text-align: center;font-size: 0.6rem;color: #333;}
	@media all and (max-width: 625px) { 
		.setListRight{font-size: 0.65rem;padding-top: 0.2rem}
	 }
	
</style>    
 </head>
 <body>
<section class="aui-content-padded" >
		<div class="aui-card-list" style="border:1px solid #ddd;">
			<div class="aui-card-list-content-padded">
				<div class="form-group" style="margin-bottom:0;">
					<div class="row" >
						<div class="col-xs-12">
							<div class="form-group" style="margin-bottom:0;">
								<div class="row">
									<div class="col-xs-3 setList1" id="label" style="padding-top:0.3rem;">持卡人</div>
									<div class="col-xs-9 setListRight" id="userPhone" ></div>
								</div>
								<div class="row">
									<div class="col-xs-3 setList1" style="padding-top:0.3rem;">sim卡号</div>
									<div class="col-xs-9 setListRight">${imsi}</div>
								</div>
								<div class="row">
									<div class="col-xs-3 setList1" style="padding-top:0.3rem;"></div>
									<div class="col-xs-9 setListRight" align="left">
									<img alt="加载失败" src="weiXinScan/createQRCodeByUrl?payUrl=<%=imsi %>" style='width: 50%;'>
									</div>
								</div>
							</div>	
					</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	<ul class="setFoot" style="padding-top:0.3rem;">
		<li>当前版本 <span id="localEdition"></span></li>
		<li>copyright © 2017</li>
	</ul>
		
	
	
 </body>
</html>

<script type="text/javascript" >
var imsi = "${imsi}";
$.ajax({
 	async:false,
 	url:"appH5/getUserPhone",
 	data:{"imsi":imsi},
 	type:'GET',
 	dataType:'json',
 	success:function(data){
        var strs = data.toString();
         $('#userPhone').html(strs.substring(0,3)+"****"+strs.substring(7,11));
 	   }
  });

$.ajax({
 	async:false,
 	url:"appH5/getAppVersion",
 	type:'GET',
 	dataType:'json',
 	success:function(data){
         $('#localEdition').html(data);
 	   }
  });


</script>