<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">进货单</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"><span class="fa fa-bars" style="color: white;"></span></div>
   	  </div>
   </header>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
	<div id="cartListDiv"></div>   	  
	<div id="loading">
		<div class="text-center font14">请稍等，正在努力加载中...</div>
	</div>
	
	<!-- footer -->
	<#include "/h5/commons/_footer2.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
<script>
	function goorderinfo(){
		var t_id = $("#productId_T").val();
		var p_id = $("#productId_P").val();
		if(t_id == 10 && p_id == 0){
			alert("很抱歉，进货单中存在一分钱体验支付商品，\n该商品不能参与正常订单的购买流程，请单独购买1分钱体验商品。");
			return;
		}
		var submitable = true;
		$(".oder-list").each(function(){
			var prolist = $(this).find("dl.cart-ul");
			prolist.each(function(idx_,this_){
				//先做购买数校验
				var inputnumber = $(this).find(".quantity");
				var inputstock_ = Number(inputnumber.val());
				var maxStock_ = Number($(this).find("input[type='hidden'][name='maxStock']").val());
				var pname_ = $(this).find(".product_name a").html().trim();
				var norm_ = $(this).find(".product_name a span").html().trim();
				var cartId_ = $(this).find("input[type='hidden'][name='cartId']").val();
				if(inputstock_ > maxStock_){
					submitable = false;
					var msg_ = "很抱歉，商品【"+ pname_ +"("+norm_+")】目前允许您最多只能购买"+maxStock_+
					"双，系统将会修改您下单的数量，是否继续提交？";
					$.dialog('confirm','提示',msg_,0,function(){
						inputnumber.val(maxStock_);
						//更新进货单某某件商品的数量
						$.ajax({
							type : "POST",
							url :  domain+"/cart/updateCartById.html",
							data : {
								id:cartId_,
								count:maxStock_
							},
							dataType : "json",
							async:false,
							success : function(data) {
								location.href = "${(domainUrlUtil.EJS_URL_RESOURCES)!''}/order/info.html";
							},
							error : function() {
						    	$.dialog('alert','提示','数据加载失败',2000);
							}
						});
						
					});
					return false;
				}
			});
		});
		if(submitable){
			location.href = "${(domainUrlUtil.EJS_URL_RESOURCES)!''}/order/info.html";
		}
	}

	$(document).ready(function () {
		
		$.ajax({
			type:"GET",
			url:"${(domainUrlUtil.EJS_URL_RESOURCES)!''}/cart/tomydetail.html",
			dataType:"html",
			success:function(data){
				//加载数据
				$("#loading").hide();
				$("#cartListDiv").append(data);
		
		        $("img").lazyload({
		            effect:'fadeIn'
		        });
			}
		});
		
		$("#addToCart").click(function(){
			//未登录不能添加进货单
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
		});
		
	});
	
	function cartminus(obj) {
		var numberObj = $(obj).parent().children("#number");
		var stockNums = parseInt($(obj).parent().children("#stockNums").val());
		var number = parseInt(numberObj.val(), 10);
		var cartId = $(obj).parent().children("#cartId").val();
		if (number <= stockNums) {
			deleteCart(cartId);
		} else {
//			number -= 10;
			number -= stockNums;
		}
		numberObj.val(number);
		updateSingle(cartId, number);
		getNewCartInfo();
	}

	function cartplus(obj) {
		var numberObj = $(obj).parent().children("#number");
		var stockNums = parseInt($(obj).parent().children("#stockNums").val());
		var number = parseInt(numberObj.val(), 10) + stockNums;
		var productStock = parseInt($(obj).parent().children("#productStock").val());
		if (number <= productStock) {
			numberObj.val(number);
			var cartId = $(obj).parent().children("#cartId").val();
			updateSingle(cartId, number);
			getNewCartInfo();
		} else {
//			number += 10; 库存不足
			//number += stockNums;
			$.dialog('alert','提示','库存不足',2000);
			return;
		}
	}
	
	// 数量输入框失去焦点
	function modify(obj) {
		var number = parseInt($(obj).val(), 10);
		var stockNums = parseInt($(obj).parent().children("#stockNums").val());
		var productStock = $(obj).parent().children("#productStock").val();
		if (number == null || parseInt(number) < stockNums) {
//			number = 10;
			number = stockNums;
		} else {
			if (number > parseInt(productStock)) {
				number = parseInt(productStock);
			}
		}

		//只允许10的倍数
		var val_ = Number($(obj).val());
		if(val_ % stockNums != 0){
			var mod = val_ % stockNums;
			var floorVal = Number(val_ - mod);
			var remainder_ = Number(stockNums - mod);
			//四舍五入
			if(mod >= (stockNums/2)){
				number = val_ + remainder_;
			} else{
				number = floorVal;
			}
		}
		$(obj).val(number);
		
		var cartId = $(obj).parent().children("#cartId").val();
		updateSingle(cartId, number);
		getNewCartInfo();
	}
	
	//更新进货单某某件商品的数量
	function updateSingle(id,count){
		$.ajax({
			type : "POST",
			url :  domain+"/cart/updateCartById.html",
			data : {id:id,count:count},
			dataType : "json",
			async:false,
			success : function(data) {
			},
			error : function() {
				// alert("数据加载失败！");
		    	$.dialog('alert','提示','数据加载失败',2000);
			}
		});
	}
	
	// 异步加载进货单信息
	function getNewCartInfo(){
		$.ajax({
			type : "POST",
			url  : domain+"/cart/getcartinfo.html?rd=" + Math.random(),
			async:false,
			success : function(data) {
				$("#cartListDiv").empty();
				$("#cartListDiv").append(data);
				
				$(".cartAmountFont").html("￥" + $("#cartAmount").val());
				$(".totalNumberFont").html("(" + $("#totalCheckedNumber").val() + ")");
				
				var checkedNum = parseInt($("#totalCheckedNumber").val());
				var totalNum = parseInt($("#totalNumber").val());
				if (checkedNum != null && checkedNum > 0 && totalNum != null && totalNum > 0 && checkedNum == totalNum) {
					$("#checkAllFoot").prop("checked", true);
				} else {
					$("#checkAllFoot").prop("checked", false);
				}
		
		        $("img").lazyload({
		            effect:'fadeIn'
		        });
			}
		});
	}
	
	// 删除进货单数据
	function deleteCart(cartId) {
		/* if(confirm("是否确定删除!")){
			$.ajax({
				type : "GET",
				url :  domain+"/cart/deleteCartById.html",
				data : {id:cartId},
				dataType : "json",
				success : function(data) {
					if(data.success){
						getNewCartInfo();
					}else {
						// alert(data.message);
				    	$.dialog('alert','提示',data.message,2000);
					}
				}
			});	
		} */
		$.dialog('confirm','提示','是否确定删除!',0,function(){
			$.closeDialog();
			$.ajax({
				type : "GET",
				url :  domain+"/cart/deleteCartById.html",
				data : {id:cartId},
				dataType : "json",
				success : function(data) {
					if(data.success){
						getNewCartInfo();
					}else {
						// alert(data.message);
				    	$.dialog('alert','提示',data.message,2000);
					}
		
			        $("img").lazyload({
			            effect:'fadeIn'
			        });
				}
			});
		});
	}
	
	//选中
	function checkedChange(obj){
		var checked = 0;
		if ($(obj).prop('checked')) {
			checked = 1;
		}
		var id = $(obj).val();
		$.ajax({
			type : "GET",
			url :  domain+"/cart/cartchecked.html",
			data : {id:id,checked:checked},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//重新加载单品信息
					getNewCartInfo();
				}else {
					$.dialog('alert','提示',data.message,2000);
				}
			},
			error : function() {
				$.dialog('alert','提示','数据加载失败',2000);
			}
		});	
	}
	
	//全部选中
	function checkedChangeAll(obj){
		var checked = 0;
		if ($(obj).prop('checked')) {
			checked = 1;
		}
		$.ajax({
			type : "GET",
			url :  domain+"/cart/cartcheckedall.html",
			data : {checked:checked},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//重新加载单品信息
					getNewCartInfo();
				}else {
					$.dialog('alert','提示',data.message,2000);
				}
			},
			error : function() {
				$.dialog('alert','提示','数据加载失败',2000);
			}
		});	
	}
</script>
</body>
</html>