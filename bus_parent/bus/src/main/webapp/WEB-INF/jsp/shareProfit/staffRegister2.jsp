    <%@ page language="Java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
            <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String mchNo = request.getParameter("mchNo");
String openid = request.getParameter("openid");
%>
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
        <html>
        <head>
        <base href="<%=basePath%>">
        <title>销售注册</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" />
        <%-- <link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
        <link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet"> --%>
        <style>
        .staffRegister_title{padding:20px;font-size:14px;font-weight:normal;background-color:#f6f6f6;}
        .reStyle{width:85%;margin:5% auto 0;}
        .restyle .col-xs-12{padding: 0;}
        </style>
        </head>
        <body ontouchstart>
        <form method="post" action="shareProfit/staffRegister3">
        <input type="hidden" name="mchNo" value="<%=mchNo%>"/>
        <input type="hidden" name="openid" value="<%=openid %>"/>
        <div class="form-group reStyle">
       
        <div class="col-xs-12 ">
        <div>
        <div class="weui-cells__title">您的手机号码</div>
        <input class="weui-input staffRegister_title"  type="text" name='staffPhone' placeholder="请输入您的手机号码" >
        </div>

        <div style="margin-top:20% ">
        <a class="weui-btn weui-btn_primary" id="next" style='background-color:#ff6666;text-decoration:none;color:#fff'>确定</a>
        </div>


        </div>
      
        </div>

        </form>

        <!-- 验证码发送失败提示 begin -->
        <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">

        <p class="weui-toast__content" id="toast_error" style='margin-top:40%;'>发送失败</p>
        </div>
        </div>
        <!-- 验证码发送失败提示 end -->
        </body>
        <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
        <script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
        <script type="text/javascript">
        $("#next").click(function(){
        var staffPhone = $("input[name='staffPhone']").val();
        if(staffPhone=="" || staffPhone==null){
        $("#toast_error").text("请输入手机号");
        toastTip();
        return false ;
        };
        if(!(/^1[34578]\d{9}$/.test(staffPhone))){
        $("#toast_error").text("请输入正确的手机号格式");
        toastTip();
        return false;
        };

        $.ajax({
        async:false,
        url:"activation/sendSNS",
        data:{"phone":staffPhone},
        type:'GET',
        dataType:'json',
        success:function(data){
        if(!data){
        toastTip();
        }else{
        $("form").submit();
        }

        },error:function(){
        toastTip();
        }
        });

        })

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