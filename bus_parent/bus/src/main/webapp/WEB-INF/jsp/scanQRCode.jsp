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
<style>
    .scanQRCode_title{margin-top:10%;}
    .scanQRCode_title h3{text-align:center;font-size:4.2rem;font-weight: normal;}
    .scanQRCode_title p{text-align:center;font-size:3.6rem;}
    .scanQRCode_title span{color:#ff7d55;}
    .scanQRCode_btn{display:block;width:78.67%;text-align:center;text-decoration:none;color:#fff;background-color:#ff7d55;margin: 0 auto;font-size: 3.4rem;height:5rem;line-height: 5rem;}
    </style>
</head>
<body ontouchstart>
    <div class='scanQRCode_title'>
    <h3 >请打开车载设备</h3>
    <p>点击<span>aicar-会员中心-充值套餐</span></p>
    </div>


    <div><img alt="" src=""></div>

    <a href='javascript:;' class='scanQRCode_btn' id='scanQRCode_btn'>扫一扫</a>


    <script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
	<script type="text/javascript">

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
        jsApiList : [ 'checkJsApi', 'scanQRCode' ]  
    });
  
    wx.error(function(res) {  
        alert("出错了：" + res.errMsg);  
    });  
  
    wx.ready(function() {  
        wx.checkJsApi({  
            jsApiList : ['scanQRCode'],  
            success : function(res) {  
            }  
        });  
        //扫描二维码
    $('#scanQRCode_btn').on('click',function(){
        wx.scanQRCode({
            needResult : 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
            success : function(res) {
            var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
            document.getElementById("wm_id").value = result;//将扫描的结果赋予到jsp对应值上
            alert("扫描成功::扫描码=" + result);
        }
    });


    })

          
    });
    
    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                docEl.style.fontSize = 10 * (clientWidth / 750) + 'px';
            };
        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false)
    })(document, window);
</script>
</body>
</html>
