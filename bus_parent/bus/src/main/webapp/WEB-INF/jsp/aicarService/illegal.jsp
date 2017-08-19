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
<style type="text/css">
 .weui-btn_primary {
    background-color: #1E90FF !important;
} 
 .weui-grid__label {
    font-size : 10px !important;
}
</style>
 </head>
 <body>
 <div class="page__hd" align="center">
     <h3>请添加需要查询的车辆</h3>
 </div>
 
  <form action="mobleCar/addIllegalMsg" method="post" id="fromData">
 <div class="page__bd">
 <div class="weui-cells weui-cells_form">
 <input  name="cityName" type="hidden" id="cityName" />
 <input  name="openid" type="hidden"  id="openid"  />
 <input  name="imsi" type="hidden" id="imsi" />
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">
                <label>车牌号码 </label>
                &nbsp;&nbsp;<label style="color:#0000FF; " onclick="gethphmHeard()" id="hphmHeard">京</label>&nbsp;&nbsp;
                <label style="color:#cccccc; ">></label>&nbsp;
                <input class="weui-input" style="width:auto; " type="text" placeholder="请输入" name="hphm" id="hphm">
                </div>
            </div>
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">
				<label>发动机号&nbsp;&nbsp;</label>
				<input class="weui-input" style="width:auto; " type="text" placeholder="完整发送机号" name="engineno" id="engineno">
                </div>
            </div>
             <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">
				<label>车架号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input class="weui-input" style="width:auto; " type="text" placeholder="完整车架号" name="classno"  id="classno">
                </div>
            </div>
       </div>
 </div>
 
 <div align="center" style="width:80%;text-align:center;margin-left:10%;margin-top:15%;    "> 
			<a class="weui-btn weui-btn_primary" onclick="isNullWithText();">查询并保存</a>
 </div>
 
 </form>
 
 <div id="showHphmHeard" style="display:none;margin-top:10%;  " >
 <div class="weui-flex">
 <div class="weui-flex__item">
<div class="weui-grids">
        <a onclick="HphmHeard(1)" class="weui-grid">
            <p class="weui-grid__label">辽</p>
        </a>
        <a onclick="HphmHeard(2)" class="weui-grid">
        <p class="weui-grid__label">吉</p>
        </a>
        <a onclick="HphmHeard(3)" class="weui-grid">
        <p class="weui-grid__label">苏</p>
        </a>
        <a onclick="HphmHeard(4)" class="weui-grid">
        <p class="weui-grid__label">浙</p>
        </a>
        <a onclick="HphmHeard(5)" class="weui-grid">
        <p class="weui-grid__label">鲁</p>
        </a>
        <a onclick="HphmHeard(6)" class="weui-grid">
        <p class="weui-grid__label">豫</p>
        </a>
        <a onclick="HphmHeard(7)" class="weui-grid">
        <p class="weui-grid__label">鄂</p>
        </a>
        <a onclick="HphmHeard(8)" class="weui-grid">
        <p class="weui-grid__label">湘</p>
        </a>
        <a onclick="HphmHeard(9)" class="weui-grid">
        <p class="weui-grid__label">粤</p>
        </a>
    </div>
    </div>
    
 <div class="weui-flex__item">
<div class="weui-grids">
        <a onclick="HphmHeard(10)" class="weui-grid">
        <p class="weui-grid__label">琼</p>
        </a>
        <a onclick="HphmHeard(11)" class="weui-grid">
        <p class="weui-grid__label">贵</p>
        </a>
        <a onclick="HphmHeard(12)" class="weui-grid">
        <p class="weui-grid__label">渝</p>
        </a>
        <a onclick="HphmHeard(13)" class="weui-grid">
        <p class="weui-grid__label">皖</p>
        </a>
        <a onclick="HphmHeard(14)" class="weui-grid">
        <p class="weui-grid__label">新</p>
        </a>
        <a onclick="HphmHeard(15)" class="weui-grid">
        <p class="weui-grid__label">藏</p>
        </a>
        <a onclick="HphmHeard(16)" class="weui-grid">
        <p class="weui-grid__label">京</p>
        </a>
        <a onclick="HphmHeard(17)" class="weui-grid">
        <p class="weui-grid__label">云</p>
        </a>
        <a onclick="HphmHeard(18)" class="weui-grid">
        <p class="weui-grid__label">闽</p>
        </a>
    </div>
 </div>
 </div>
 
 <div class="weui-flex">
  <div class="weui-flex__item">
  <div class="weui-grids">
        <a onclick="HphmHeard(19)" class="weui-grid">
        <p class="weui-grid__label">川</p>
        </a>
        <a onclick="HphmHeard(20)" class="weui-grid">
        <p class="weui-grid__label">甘</p>
        </a>
        <a onclick="HphmHeard(21)" class="weui-grid">
        <p class="weui-grid__label">宁</p>
        </a>
        <a onclick="HphmHeard(22)" class="weui-grid">
        <p class="weui-grid__label">蒙</p>
        </a>  
        <a onclick="HphmHeard(23)" class="weui-grid">
        <p class="weui-grid__label">沪</p>
        </a>
  </div>
  </div>
  
  <div class="weui-flex__item">
  <div class="weui-grids">
  <a onclick="HphmHeard(24)" class="weui-grid">
        <p class="weui-grid__label">翼</p>
        </a>
   <a onclick="HphmHeard(25)" class="weui-grid">
   <p class="weui-grid__label">陕</p>
        </a>
        <a onclick="HphmHeard(26)" class="weui-grid">
        <p class="weui-grid__label">津</p>
        </a>     
  </div>
  </div>
 </div>
 </div>
 </body>
 <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script type="text/javascript">
var openid = "${openid}";
var imsi = "${imsi}";
$("#openid").val(openid);
$("#imsi").val(imsi);
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
    jsApiList : [ 'getLocation' ]  
});//end_config  

wx.error(function(res) {  
    alert("出错了：" + res.errMsg);  
});  

wx.ready(function() {  
    wx.checkJsApi({  
        jsApiList : ['getLocation'],  
        success : function(res) {  
        	 if (res.checkResult.getLocation == false) {
                 alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                 return;
             }
        }  
    });  
    
  //  $("#sq").click(function(){
    	wx.getLocation({
    	    success: function (res) {
    	        var lat = res.latitude; // 纬度，浮点数，范围为90 ~ -90
    	        var lng = res.longitude; //经度，浮点数，范围为180 ~ -180。
    	       $.ajax({
    	          async:false,
    	          url:"mobleCar/getCoordinate",
    	          data:{"lat":lat,"lng":lng},
    	          dataType:'json',
    	          success:function(data){
    	        	  $("#cityName").val(data.result.addressComponent.city);
    	          }
    	          });
    	    },
    	    cancel: function (res) {
    	        alert('用户拒绝授权获取地理位置');
    	    }
    	});
});
  
gethphmHeard = function(){
	$("#showHphmHeard").show(); 
	};
	
	var  hphmHeardList = [ "辽","吉","苏","浙",
		                   "鲁","豫","鄂","湘",
		                   "粤","琼","贵","渝",
		                   "皖","新","藏","京",
		                   "云","闽","川","甘",
		                   "宁","蒙","沪","翼",
		                   "陕","津"
		                 ];
	
HphmHeard = function(index){
   var HphmHeard = hphmHeardList[index-1];
   $("#hphmHeard").text(HphmHeard);
   $("#showHphmHeard").hide();
}

isNullWithText = function(){
  var hphmHeard = $("#hphmHeard").text();
  $("#hphm").val(hphmHeard+$("#hphm").val());
  $("#fromData").submit();
};

  
</script>
</html>