
<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>aicar会员</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/weui/dist/style/weui.css" />
</head>
<body ontouchstart>
<div style="margin-top:5% ">
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
        <div class="weui-cell weui-cell_access">
            <div class="weui-cell__bd weui-cell_primary">
                 <p>深圳宝安区4S点</p>
                <p>地点：深圳宝安区</p>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/weui/dist/example/zepto.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/weui/dist/example/weui.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/M/jquery.1.9.1.min.js"></script>
	<script type="text/javascript">
	$(function(){
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
	        });
	        $searchInput
	            .on('blur', function () {
	                if(!this.value.length) cancelSearch();
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
	    });
	</script>
</body>
</html>
