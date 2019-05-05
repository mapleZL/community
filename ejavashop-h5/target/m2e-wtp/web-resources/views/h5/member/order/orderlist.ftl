<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back();">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
		 </div>
   	  	 <div class="flex-2 text-center">订单中心</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
   	<input type="hidden" id="ordersCount" value="${ordersCount!'0'}"/>
    <input type="hidden" id="pageIndex" value="0"/>
    <input type="hidden" id="pageSize" value="${pageSize!'5'}"/>
    <input type="hidden" id="orderState" value="${orderState!''}"/>
	<div class="" id="ordersListDiv" style="margin-bottom: 10px;">
		<#include "/h5/member/order/orderlistmore.ftl" />
    </div>
    
    <#if ordersCount &gt; 0 >
	    <div id="addMoreOrderDiv">
	   		<a href="javaScript:;" onClick="addMoreOrder()">
				<div class="text-center font14">查看更多 <i class="fa fa-angle-double-down"></i></div>
			</a>
		</div>
	    <div id="loading" style="display:none">
			<div class="text-center font14">请稍等，正在努力加载中...</div>
		</div>
		<div id="noMoreOrderDiv" style="display:none;">
			<div class="text-center font14">已展示全部记录</div>
		</div>
	<#else>
		<div class="text-center font14">已展示全部记录</div>
	</#if>
    
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
	var sessionKey = "orderListSession";
	
	function pageIsHistory() {
		var list_page = $("#pageIndex").val();
		if (list_page == 0) sessionStorage.clear();
		else {
			var ssVal = sessionStorage.getItem(sessionKey);
			$("#ordersListDiv").append(ssVal);
		
	        $("img").lazyload({
	            effect:'fadeIn'
	        });
		
		}
	}

	$(function(){
		pageIsHistory();
		displayMoreBtn();
		addMoreOrder();
	});
	
	function displayMoreBtn() {
		// 总数
		var ordersCount = parseInt($("#ordersCount").val());
		// 当前加载的页数
		var pageIndex = parseInt($("#pageIndex").val());
		// 每页加载数量
		var pageSize = parseInt($("#pageSize").val());
		// 如果总数量小于等于已经加载的数量，则表示没有更多，隐藏加载更多按钮，显示没有更多
		if (ordersCount <= (pageSize * pageIndex)) {
			$("#addMoreOrderDiv").hide();
			$("#noMoreOrderDiv").show();
			return;
		}
	}
	
	// 加载更多
	function addMoreOrder() {
		$("#addMoreOrderDiv").hide();
		$("#loading").show();
		// 总数
		var ordersCount = parseInt($("#ordersCount").val());
		// 当前加载的页数
		var pageIndex = parseInt($("#pageIndex").val()) + 1;
		// 每页加载数量
		var pageSize = parseInt($("#pageSize").val());
		// 如果总数量小于等于已经加载的数量，则表示没有更多，隐藏加载更多按钮，显示没有更多
		if (ordersCount <= (pageSize * pageIndex) && pageIndex > 1) {
			$("#addMoreOrderDiv").hide();
			$("#loading").hide();
			$("#noMoreOrderDiv").show();
			return;
		}
		$.ajax({
			type:"GET",
			url:"${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/moreorder.html",
			dataType:"html",
			data:{orderState:$("#orderState").val(),pageIndex:pageIndex},
			success:function(data){
				//加载数据
				$("#ordersListDiv").append(data);
				
                $("img.lazy" + pageIndex).lazyload({
                    effect:'fadeIn'
                })
				var ssVal = sessionStorage.getItem(sessionKey);
				if (ssVal == null) ssVal = "";
	            sessionStorage.setItem(sessionKey, ssVal + data);
				// 页码加1
				$("#pageIndex").val(pageIndex);
				if (ordersCount <= (pageSize * (pageIndex))) {
					$("#addMoreOrderDiv").hide();
					$("#noMoreOrderDiv").show();
				}
				else {
					$("#addMoreOrderDiv").show();
				}
				$("#loading").hide();
			}
		});
	}
	
	//取消订单
	function cancalOrder(tradeNo){
		$.dialog('confirm','提示','确定要取消该订单吗？',0,function(){
			$.closeDialog();
			$.ajax({
				type : "GET",
				url :  domain+"/member/cancalorder.html",
				data : {tradeNo:tradeNo},
				dataType : "json",
				success : function(data) {
					if(data.success){
						// 修改显示状态
						$("#orderStateFont"+tradeNo).removeClass("clr53");
						$("#orderStateFont"+tradeNo).html("取消");
						
						// 修改显示按钮
						var btnDiv = "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=" + tradeNo + "' class='cla4'>查看</a>";
						$("#orderBtnDiv"+tradeNo).empty();
						$("#orderBtnDiv"+tradeNo).append(btnDiv);
					}else {
						// alert(data.message);
						$.dialog('alert','提示',data.message,2000);
					}
				}
			});
		});
	}
	
	//确认收货
	function goodsReceipt(ordersId){
		$.ajax({
			type : "GET",
			url :  domain+"/member/goodreceive.html",
			data : {ordersId:ordersId},
			dataType : "json",
			success : function(data) {
				if(data.success){
					// 修改显示状态
					$("#orderStateFont"+ordersId).html("完成");
					
					// 修改显示按钮
					var btnDiv = "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=" + ordersId + "' class='cla4'>查看</a>";
					btnDiv = btnDiv + "&nbsp;";
					//btnDiv = btnDiv + "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=" + ordersId + "' class='cla4'>评价晒单</a>";
					//btnDiv = btnDiv + "&nbsp;";
					//btnDiv = btnDiv + "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=" + ordersId + "' class='cla4'>申请退换货</a>";
					$("#orderBtnDiv"+ordersId).empty();
					$("#orderBtnDiv"+ordersId).append(btnDiv);
				}else {
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
				}
			}
		});
	}
</script>
</body>
</html>