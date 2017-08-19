    <%@ page language="Java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
            <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String brandNo = request.getParameter("brandNo");
String openid = request.getParameter("openid");
%>
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
        <html>
        <head>
        <base href="<%=basePath%>">
        <title>aicar商家注册</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/weui/dist/style/weui.css" />
       <%--  <link rel="stylesheet" href="<%=request.getContextPath() %>/weui/autosearch.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/aui/css/bootstrap.min14ed.css?v=3.3.6" /> --%>
        <%-- <link href="<%=request.getContextPath() %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
        <link href="<%=request.getContextPath() %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet"> --%>
        	<style type="text/css">
       		.weui-cells {
		     margin-top: 0em !important;  
		    background-color: #FFFFFF;
		    line-height: 1.41176471;
		    font-size: 17px;
		    overflow: hidden;
		    position: relative;
		}
       	</style> 
        </head>
        <body ontouchstart>
        <form method="post" action="shareProfit/mchRegister1">
        <input type="hidden" name="brandNo" value="<%=brandNo%>"/>
        <input type="hidden" name="openid" value="<%=openid%>"/>

        <div style="margin-top:10% ">
        <label>请先确认您所在的店面未在aicar注册，</label>
        <p><label>重复注册会导致店面结算损失</label></p>
        </div>

        <div class="weui-search-bar" id="searchBar" style="margin-top:5%;height:7%;  ">
        <div class="weui-search-bar__form">
            <div class="weui-search-bar__box">
                <i class="weui-icon-search"></i>
                <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" name="mchName" />
                <input type="hidden" name="mchAddress"></input>
                <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
            </div>
            <label for="search_input" class="weui-search-bar__label" id="searchText">
                <i class="weui-icon-search"></i>
                <span>请输入店铺名称</span>
            </label>
        </div>
        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
    </div>
    
    <div class="weui-cells searchbar-result" id="searchResult" style="display:none">
    </div>

        <!-- <div style="margin-top:10% ">
        <label>店铺地址</label>
        
        </div> -->

        <div style="margin-top:20% ">
        <span class="weui-btn weui-btn_primary" id="next" style='background-color:#fd5e62;'>确定</span>
        </div>

        </form>
        
        <div id="dialogs">
        <!--BEGIN dialog1-->
        <div class="js_dialog" id="iosDialog1" style="display: none;">
        <div class="weui-mask"></div>
        <div class="weui-dialog">
        <div class="weui-dialog__hd"><strong class="weui-dialog__title">您是否是此商家</strong></div>
        <div class="weui-dialog__bd">
        <p id="mchName"></p>
        <p id="mchAddress"></p>
        </div>
        <div class="weui-dialog__ft" >
        <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" id="yes">是</a>
        <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary" id="no">不是</a>
        </div>
        </div>
        </div>
        <!--END dialog1-->
        </div>


            <div id="toast" style="display: none;">
            <div class="weui-mask_transparent"></div>
            <div class="weui-toast">
            <p class="weui-toast__content" id="toast_error" style='margin-top:35%; font-size:20px;color:#fff;'>请注册</p>
            </div>
            </div>
        </body>
        <script type="text/javascript" src="<%=request.getContextPath()%>/weui/dist/example/zepto.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/weui/dist/example/weui.min.js"></script>
         <script src="<%=request.getContextPath() %>/hplus/js/jquery.min.js"></script>
         <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
         <%-- <script type="text/javascript" src="<%=request.getContextPath() %>/weui/autosearch.js" ></script>  --%>
         <script type="text/javascript">
 	        var $searchBar = $('#searchBar'),
 	            $searchResult = $('#searchResult'),
 	            $searchText = $('#searchText'),
 	            $searchInput = $('#searchInput'),
 	            $searchClear = $('#searchClear'),
 	            $searchCancel = $('#searchCancel');

 	        function hideSearchResult(){
 	            $searchResult.hide();
 	            $searchInput.val('');
 	        }
 	        function cancelSearch(){
 	            hideSearchResult();
 	            $searchBar.removeClass('weui-search-bar_focusing');
 	            $searchText.show();
 	        }

 	        $searchText.on('click', function(){
 	            $searchBar.addClass('weui-search-bar_focusing');
 	            $searchInput.focus();
 	            $searchResult.show();
 	        });
 	        $searchInput
 	             .on('blur', function () {
 	              if(!this.value.length){
 	            	$searchBar.removeClass('weui-search-bar_focusing');
 	 	           $searchText.show();
 	 	            };
 	            }) 
 	            .on('input', function(){
 	                if(this.value.length) {
 	                   $searchResult.show();
 	                } else {
 	                   $searchResult.hide();
 	                }
 	            })
 	        ; 
 	        
 	        $searchClear.on('click', function(){
 	          hideSearchResult();
 	          $searchInput.focus();
 	        });
 	        
 	         $searchCancel.on('click', function(){
 	            cancelSearch();
 	            $searchInput.blur();
 	        }); 
 	   // });
         
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
             	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
             	        var longitude = res.longitude; //经度，浮点数，范围为180 ~ -180。
             	        $("input[name='lat']").val(latitude);
             	        $("input[name='lng']").val(longitude);
             	       $.ajax({
             	          async:false,
             	          url:"shareProfit/getCarList",
             	          data:{"lat":latitude,"lng":longitude},
             	          type:'GET',
             	          dataType:'json',
             	          success:function(data){
             	        	$(data).each(function(i,item){
             	        	var searchDataHtml = "<div class='weui-cell weui-cell_access'>" +
             	        	"<div class='weui-cell__bd weui-cell_primary' id='result" +i+
             	        	"' onclick='setDate(this)'>" +
             	        	"<p id='mch" +i+
             	        	"'>" + item.name+
             	        	"</p>" +
             	        	"<p id='address" +i+
             	        	"'>" +item.id+
             	        	"</p></div></div>";
             	        	$("#searchResult").append(searchDataHtml);
             	        	});
             	          }
             	          });
             	    },
             	    cancel: function (res) {
             	        alert('用户拒绝授权获取地理位置');
             	    }
             	});
          //   });
         });
         
        var $iosDialog1 = $('#iosDialog1');
        var mchMsg ;
        $('#dialogs').on('click', '.weui-dialog__btn', function(){
        $(this).parents('.js_dialog').fadeOut(200);
        });

        $("#next").on('click',function(){
        var mchName = $("input[name='mchName']").val();
        var mchAddress = $("input[name='mchAddress']").val();
        if(mchName=="" || mchName==null){
            $('#toast_error').text('请输入店铺名称')
            toastTip();
        return false;
        }
        /* if(mchAddress=="" || mchAddress==null){
            $('#toast_error').text('请输入店铺地址')
            toastTip();
        return false;
        } */
        $("form").submit();
        })

        $("input[name='mchName']").blur(function(){
        var mchName = $("input[name='mchName']").val();
        $.ajax({
        async:false,
        url:"shareProfit/findMchMsgByName",
        data:{"mchName":mchName},
        type:'GET',
        dataType:'json',
        success:function(data){
        if(data!=null){
        mchMsg = data;
        $("#mchName").text(data.mchName);
        $("#mchAddress").text(data.mchAddress);
        $iosDialog1.fadeIn(200);
        }
        }
        });
        });


        $("#no").click(function(){
        var mchName = $("input[name='mchName']").val();
        var mchAddress = $("input[name='mchAddress']").val();
        if(mchName=="" || mchName==null){
        return false;
        }
        if(mchAddress=="" || mchAddress==null){
        return false;
        }
        $("form").submit();
        })

        $("#yes").click(function(){
        if(mchMsg!="" || mchMsg!=null){


        }

        })

            function toastTip(){
            var $toast = $('#toast');
            if ($toast.css('display') != 'none') return;
            $toast.fadeIn(100);
            setTimeout(function () {
            $toast.fadeOut(100);
            }, 2000);
            };
            
            function setDate(dom){
            var resultId = $(dom).attr("id");
            var setid = resultId.substring(6,resultId.length);
            var mchName = $("#mch"+setid).text();
            var mchAddress = $("#address"+setid).text();
            $("input[name='mchName']").val(mchName);
            $("input[name='mchAddress']").val(mchAddress);
            $searchResult.hide();
            $searchBar.addClass('weui-search-bar_focusing'); 
             };
      
        </script>
        </html>