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
    <title>代付详情</title>        
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
<link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui.2.0.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/aui-pull-refresh.css" />
<style type="text/css">
button{width:100%;text-align:center;border-radius:3px;}
.button2{font-size:16px;padding:8px 0;border:1px solid #adadab;color:#000000;background-color: #e8e8e8;background-image:linear-gradient(to top, #dbdbdb, #f4f4f4);background-image:-webkit-gradient(linear, 0 100%, 0 0, from(#dbdbdb),to(#f4f4f4));box-shadow: 0 1px 1px rgba(0,0,0,0.45), inset 0 1px 1px #efefef; text-shadow: 0.5px 0.5px 1px #ffffff;}
.button2:active{background-color: #dedede;background-image: linear-gradient(to top, #cacaca, #e0e0e0);background-image:-webkit-gradient(linear, 0 100%, 0 0, from(#cacaca),to(#e0e0e0));}
#mess_share{margin:15px 0;}
#share_1{float:left;width:49%;}
#share_2{float:right;width:49%;}
#mess_share img{width:22px;height:22px;}
#cover{display:none;position:absolute;left:0;top:0;z-index:18888;background-color:#000000;opacity:0.7;}
#guide{display:none;position:absolute;right:18px;top:5px;z-index:19999;}
#guide img{width:260px;height:180px;}
</style>
 <body ontouchstart>
 <div class="form-group" style="background-color:#cccccc;">
 <div class="row" style="padding:10px;">
 <div class='col-xs-2'><img alt='image' class='img-circle' src="${headimgurl}" style='width:48px;height:48px;margin-top:5px; '></div>
 <div class='col-xs-10'><h1 style="font-size:30px;">${nickname}</h1>发给朋友的代付邀请</div>
 </div>
 </div>
 
 <div class="form-group" style="padding:10px;background-color:#fff;">
 <div class="row" align="center"><h2>付款金额</h2></div>
 <div class="row" align="center" id="packagePrice" style="font-size:30px;"></div>
 <div class="row" style="margin-left:10px; ">
<p>好友代付说明</p>
<p>1.代付发起后24小时无人付款，订单将自动取消；</p>
<p>2.如果系统原因退款，已支付金额将原路返回给代付人。</p>
 </div>
 </div>
 
<div style="padding:10px;background-color:#fff;"">
   <h2 style="font-size:20px;">商品信息</h2>
   <p id="packageName" style="font-size:15px;padding:20px 0;"></p>
</div>
           
	
 
  <div class="form-group" style="margin-top:100px; ">
  <div class="weui-btn-area">
			<a class="weui-btn weui-btn_warn" onclick="_system._guide(true)">发送给朋友</a>
		</div>
  </div>
  
  <div id="cover"></div>
<div id="guide"><img src="png/guide1.png"></div>
 
 </body>
<script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/api.js"></script>
<script src="<%=request.getContextPath() %>/aui/script/aui-pull-refresh.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
 <script type="text/javascript">
	var nickname = "${nickname}";
	var headimgurl = "${headimgurl}";
	var packageType = "${packageType}";
	var openid = "${openid}";
	var paidTime = "${paidTime}";
	var imsi = "${imsi}";
 
    var appId = "${appId}";
	var timestamp = "${timestamp}";
	var nonceStr = "${nonceStr}";
	var signature = "${signature}";
	
	//查询代付套餐信息
	$.ajax({
         	async:false,
         	url:"weiXinScan/getPackageMsg",
         	data:{"packageType":packageType},
         	type:'GET',
         	dataType:'json',
         	success:function(data){
         		var money = data.packagePrice;
         		var name = data.packageName;
         		$("#packagePrice").text(money+"￥");
         		$("#packageName").text(name);
         	   }
          });
	
//代付链接	
var otherPay = "<%=basePath %>weiXinScan/otherPay?nickname="+nickname+
		       "&headimgurl="+headimgurl+"&packageType="+packageType+"&openid="+openid+
		       "&paidTime="+paidTime+"&imsi="+imsi;

	wx.config({  
        debug: false,  
        appId:appId,  
        timestamp:timestamp,  
        nonceStr:nonceStr,  
        signature:signature,  
        jsApiList : [ 'checkJsApi', 'onMenuShareAppMessage' ]  
    });//end_config  
  
    wx.error(function(res) {  
        alert("出错了：" + res.errMsg);  
    });  
  
    wx.ready(function() {  
        wx.checkJsApi({  
            jsApiList : ['onMenuShareAppMessage'],  
            success : function(res) {  
            }  
        });  
        //发送给朋友设置
            wx.onMenuShareAppMessage({  
            	title: '请帮我付款', // 分享标题  
                desc: '我正在购买aicar会员，全年车载流量不限制，请帮我付款吧！', // 分享描述  
                link: otherPay,  
                imgUrl: 'http://oixlf0mzw.bkt.clouddn.com/kingPackage.png',   // 分享图标  
                type: 'link', // 分享类型,music、video或link，不填默认为link  
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空  
                success: function () {   
                    // 用户确认分享后执行的回调函数  
                },  
                cancel: function () {   
                    // 用户取消分享后执行的回调函数  
                }
            });   
          
    });
    
    var _system={
            $:function(id){return document.getElementById(id);},
       _client:function(){
          return {w:document.documentElement.scrollWidth,h:document.documentElement.scrollHeight,bw:document.documentElement.clientWidth,bh:document.documentElement.clientHeight};
       },
       _scroll:function(){
          return {x:document.documentElement.scrollLeft?document.documentElement.scrollLeft:document.body.scrollLeft,y:document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop};
       },
       _cover:function(show){
          if(show){
         this.$("cover").style.display="block";
         this.$("cover").style.width=(this._client().bw>this._client().w?this._client().bw:this._client().w)+"px";
         this.$("cover").style.height=(this._client().bh>this._client().h?this._client().bh:this._client().h)+"px";
      }else{
         this.$("cover").style.display="none";
      }
       },
       _guide:function(click){
          this._cover(true);
          this.$("guide").style.display="block";
          this.$("guide").style.top=(_system._scroll().y+5)+"px";
          window.onresize=function(){_system._cover(true);_system.$("guide").style.top=(_system._scroll().y+5)+"px";};
      if(click){_system.$("cover").onclick=function(){
             _system._cover();
             _system.$("guide").style.display="none";
     _system.$("cover").onclick=null;
     window.onresize=null;
      };}
       },
       _zero:function(n){
          return n<0?0:n;
       }
    }
 </script>
</html>