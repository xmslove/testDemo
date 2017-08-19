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
<title>车辆信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
 </head>
 <style type="text/css">
 .weui-btn_primary {
    background-color: #1E90FF !important;
} 
</style>
 <body ontouchstart>
 <div class="page__bd">
        <div class="weui-cells weui-cells_form">
        
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">车品牌</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="" name="busBrand" id="busBrand" readonly="readonly">
                </div>
            </div>
            
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">车型</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="" name="busType" id="busType">
                </div>
            </div>
            
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">年份</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="" name="aParticularYear" id="aParticularYear">
                </div>
            </div>

        </div>
        
        <div align="center" style="width:80%;text-align:center;margin-left:10%;margin-top:15%;"> 
			<a class="weui-btn weui-btn_primary">确定</a>
        </div>
        
        <div class="weui-skin_android" id="androidActionsheet" style="opacity: 0; display: none;">
        <div class="weui-mask"></div>
        <div class="weui-actionsheet">
            <div class="weui-actionsheet__menu">
                <div class="weui-actionsheet__cell">示例菜单</div>
                <div class="weui-actionsheet__cell">示例菜单</div>
                <div class="weui-actionsheet__cell">示例菜单</div>
            </div>
        </div>
    </div>
        
 </div>
 </body>
 <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
<script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
<script type="text/javascript">
var imsi =   "${imsi}";
var openid = "${openid}";
var $androidActionSheet = $('#androidActionsheet');
var $androidMask = $androidActionSheet.find('.weui-mask');

$("#busBrand").on('click', function(){
        $androidActionSheet.fadeIn(200);
        $androidMask.on('click',function () {
        $androidActionSheet.fadeOut(200);
        });
    });



</script>
</html>