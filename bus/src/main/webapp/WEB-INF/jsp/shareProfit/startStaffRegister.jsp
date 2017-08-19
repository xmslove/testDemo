    <%@ page language="Java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
            <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String openid = request.getParameter("openid");
%>
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
        <html>
        <head>
        <base href="<%=basePath%>">
        <title>aicar商家平台</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
        <link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
        <link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
        <style>
        body{background-color:#f6f6f6;}
        .startStaff_font{margin-top:60px;}
        .startStaff_font p{text-align:center;padding:10px 0; color:#fd5e62 ;font-size:14px;}
        .startStaff_font a{width:85%;}
        .startStaff_font a:after{border-color:#fd5e62}

        </style>
        </head>
        <body ontouchstart>

        <div class="page">
        <div class="page__hd">
        <img src="<%=request.getContextPath() %>/png/sale1.png" style="height:auto;width:auto\9;width:100%;"/>
        </div>
        <div class="page__bd startStaff_font" >
        <a class="weui-btn"  style='background-color:#fff;color:#fd5e62;' id="staffRegister">销售注册</a>
        <p>您需要知道您所在店面的商家编号</p>
        <a href="javascript:;" class="weui-btn weui-btn_default"style='background-color:#fd5e62;color:#fff;'  id="staffTiedCard"  >我已注册，现在绑卡</a>
        <p>开卡返现将发送到您当前使用的微信账号上</p>
        </div>
        </div>

        <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
    
        <p class="weui-toast__content" id="toast_error" style="margin-top:35%;font-size:15px;">您当前使用的微信账户已注册</p>
        </div>
        </div>

        <form action="shareProfit/toStaffTiedCard" method="post" >
        <input type="hidden" name="openid" value="<%=openid %>" />
        </form>

        </body>
        <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
        <script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
        <script type="text/javascript">
        var openid = "${openid}";

        $("#staffRegister").on('click',function(){
        // 验证用户是否已经注册过
        $.ajax({
        async:false,
        url:"shareProfit/selectStaffRegister",
        data:{"openid":openid},
        type:'GET',
        dataType:'json',
        success:function(data){
        if(data>=1){
        toastTip();
        }else{
        //去销售注册页面
        window.location.href="<%=basePath %>shareProfit/introduceRegister?openid="+openid;
        }
        },error:function(){
        //去销售注册页面
        window.location.href="<%=basePath %>shareProfit/introduceRegister?openid="+openid;
        }
        });
        });

        $("#staffTiedCard").on('click',function(){
        // 验证用户是否已经注册过
        $.ajax({
        async:false,
        url:"shareProfit/selectStaffRegister",
        data:{"openid":openid},
        type:'GET',
        dataType:'json',
        success:function(data){
        if(data>=1){
        //去销售注册页面
        $("form").submit();
        }else{
            $('#toast_error').text('请先注册')
           toastTip(); 
        }
        }
        });
        });

        function toastTip(){
        var $toast = $('#toast');
        if ($toast.css('display') != 'none') return;
        $toast.fadeIn(100);
        setTimeout(function () {
        $toast.fadeOut(100);
        }, 2000);
        };
        </script>
        </html>