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
  <title>Aicar商家平台</title>
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
  <link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
  <style>
  body{background-color:#f3f3f3;}
  .mchLoginMsg_warp{position:relative;}
  .mchLoginMsg_content{position:absolute;top:-80px;left:10%;;width:80%;text-align:center;background-color:rgba(255,255,255,0.5);padding:5%;}
  .mchLoginMsg_content img{width:30%;}
  .mchLoginMsg_content h2{padding:20px 0;font-weight:bold;color:#010101;}
  .mchLoginMsg_content>div{color:#666;font-size:15px;}
  .mchLoginMsg_content h3 {font-size:30px;color:#333;}
  .mchLoginMsg_content>p{padding:10px 0 50px;color:#333;font-size:14px;}
  .mchLoginMsg_foot{text-align:center;margin-top:320px;color:#b2b2b2;font-size:13px;}

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
  </head>
  <body ontouchstart>
  <img src="<%=request.getContextPath() %>/png/share_title1.png" style="width:100%;"/>
  <div class="mchLoginMsg_warp">
  <div class="mchLoginMsg_content">
  <img src="<%=request.getContextPath() %>/png/share_mch-1.png"/>
  <h2>${MchMsgVO.mchName}</h2>
  <div>aicar商家号</div>
  <h3> ${MchMsgVO.mchNo}</h3>
  <p>${MchMsgVO.mchAddress}</p>
  <span class="weui-btn weui-btn_primary" id="next" style='background-color:#ff6666' onclick="_system._guide(true)"  >分享到工作群</span>
  </div>
  </div>
  <div class='mchLoginMsg_foot'>您可以收藏此页面，或分享到工作群中</div>

  <div id="cover"></div>
  <div id="guide"><img src="png/guide1.png"></div>

  </body>
  <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
  <script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
  <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
  <script type="text/javascript">
  var mchNo = "${MchMsgVO.mchNo}";
  var mchName = "${MchMsgVO.mchName}";

  var appId = "${appId}";
  var timestamp = "${timestamp}";
  var nonceStr = "${nonceStr}";
  var signature = "${signature}";

  var mchShareUrl = "<%=basePath %>shareProfit/mchShareUrl?mchNo="+mchNo+"&mchName="+mchName;



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




  wx.config({
  debug: false,
  appId:appId,
  timestamp:timestamp,
  nonceStr:nonceStr,
  signature:signature,
  jsApiList : [ 'checkJsApi','onMenuShareTimeline','onMenuShareAppMessage' ]
  });//end_config

  wx.error(function(res) {
  alert("出错了：" + res.errMsg);
  });

  wx.ready(function() {
  wx.checkJsApi({
  jsApiList : ['onMenuShareTimeline','onMenuShareAppMessage'],
  success : function(res) {
  }
  });

  // 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
  wx.onMenuShareTimeline({
  title: 'aicar商家编号'+mchNo, // 分享标题
  link: mchShareUrl,
  imgUrl: "http://oixlf0mzw.bkt.clouddn.com/kingPackage.png" // 分享图标
  });

  //发送给朋友设置
  wx.onMenuShareAppMessage({
  title: 'aicar商家编号'+mchNo, // 分享标题
  desc: '我们店已加入aicar了！店员们赶紧行动起来吧！', // 分享描述
  link: mchShareUrl,
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




  </script>
  </html>