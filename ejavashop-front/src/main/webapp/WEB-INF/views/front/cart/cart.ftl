<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css">
<div class='container' style="border: 0px solid red;margin-bottom:30px">
	<div class='w container'>
		<div class='cart-filter-bar' style="border: 1px solid blue;display: none;">
			<ul class='switch-cart'>
				<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
					<li class='switch-cart-item curr'>
						<em style='color: #e4393c;'>全部商品</em>
						<span class='number' id="totalCount">(${cartInfoVO.totalNumber!0})</span>
					</li>
				</#if>
			</ul>
			<div class='cart-store'></div>
			<div class='clr'></div>
			<div class='w-line'>
				<div class='floater'></div>
			</div>
		</div>
		<span style="font-family:MicrosoftYaHei-Bold;
					font-size:14px;
					color:#666666;
					line-height:14px;
					text-align:left;
					padding-top: 10px;
					display: block;
					font-weight:bold;
					border: 0px solid black;">进货单信息</span>
		<hr style="border:2px solid #bdbdbd; ">
	</div>
	<div class='cart-warp'>
		<div class='w container'>
			<div id="cart-list-ajax">
				<#include "/front/cart/cart_list.ftl" />
			</div>
		</div>
	</div>
</div>
		
<script type="text/javascript">
	function goorderinfo(){
		var t_id = $("#productId_T").val();
		var p_id = $("#productId_P").val();
		if(t_id == 10 && p_id == 0){
			jAlert("很抱歉，进货单中存在一分钱体验支付商品，\n该商品不能参与正常订单的购买流程，请单独购买1分钱体验商品。");
			return;
		}
		var submitable = true;
		var tFlag;
		$(".cart-item-list").each(function(){
			var prolist = $(this).find("div.item-selected");
			prolist.each(function(idx_,this_){
				//先做购买数校验
				var inputstock_ = Number($(this).find(".buy-num").val());
				var maxStock_ = Number($(this).find("input[type='hidden'][name='maxStock']").val());
				var pname_ = $(this).find(".p-name a").html().trim();
				var norm_ = $(this).find(".p-props span").html().trim();
				var productId = Number($(this).find("input[type='hidden'][name='productId']").val());
				var productGoodsId = Number($(this).find("input[type='hidden'][name='productGoodsId']").val());
				//进行限购校验  add by lushuai 2017-02-16
				tFlag = checklimitations(productId,inputstock_,productGoodsId);
				if(inputstock_ > maxStock_){
					submitable = false;
					var msg = "很抱歉，商品【"+ pname_ +"("+norm_+")】目前允许您最多只能购买"+maxStock_+
					"双，系统将会修改您下单的数量，是否继续提交？";
					if(confirm(msg)){
						$(this).find(".buy-num").val(maxStock_);
						//更新进货单某某件商品的数量
						var cartId_ = $(this).find(".cartId").val();
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
								submitable = true;
							},
							error : function() {
								jAlert("数据加载失败！");
							}
						});
					} else{
						submitable = false;
						return false;
					}
				}
			});
		});
		if(submitable && tFlag){
			location.href = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/order/info.html";
		}
		
	}
	
	function checklimitations(productId,num,productGoodsId){
		var tFlag = true;
		$.ajax({
			type : "GET",
			url :  domain+"/cart/checklimitations.html",
			data : {
				productId:productId, 
				productGoodsId:productGoodsId,
				number:num
			},
			async:false,
			dataType : "json",
			success : function(data) {
				var msg = data.message;
				if(msg != '' && msg != null){
					jAlert(msg);
					tFlag = false;
				}
			},
			error : function() {
				jAlert("校验限购商品数据失败！");
				tFlag = false;
			}
		});
		return tFlag; 
	}

	function numchange(obj){
		var stockNums = parseInt($(obj).parent().children("#stockNums").val());
		//只允许10的倍数
		var val_ = Number($(obj).val());
		if(val_ % stockNums != 0){
			var mod = val_ % stockNums;
			var floorVal = Number(val_ - mod);
			var remainder_ = Number(stockNums - mod);
			//四舍五入
			if(mod >= (stockNums/2)){
				$(obj).val(val_ + remainder_);
			} else{
				$(obj).val(floorVal);
			}
		} 
		
		//判断是否大于库存量
		var productStock = $(obj).parent().parent().find(".productStock");
		if(productStock){
		    var pstock = $(productStock).html();
		    if(parseInt(val_) > parseInt(pstock)){
		    	$(obj).val(pstock);
		    }
		}
		
		//更新进货单数据
		//获得进货单id
		var cartId = $(obj).siblings('.cartId').val();
		updateSingle(cartId,$(obj).val());
		//重新加载单品信息
		getNewCartInfo();
	}

	$(function(){
		$(".atip").tooltip({
			animation:true,
			html:true,
			trigger:'hover' //触发tooltip的事件
		});
	
	});
	/**
	 * 异步加载货品信息
	*/
	function getNewCartInfo(){
		$.ajax({
			type : "POST",
			url  : domain+"/cart/getcartinfo.html?rd=" + Math.random(),
			async:false,
			success : function(data) {
				$("#cart-list-ajax").empty();
				$("#cart-list-ajax").append(data);
				$("#totalCount").html($("#totalNumber").val());
		
		        $("img").lazyload({
		            effect:'fadeIn'
		        });
			}
		});
	}

	/**
	* 数量增加
	*/
	function increment(obj){
		var stockNums = parseInt($(obj).parent().children("#stockNums").val());
		//数量增加操作 ,计算 小计
		var buynum = $(obj).siblings(".buy-num");
		buynum.val(parseInt(buynum.val())+stockNums);
		checknum(buynum, stockNums);
	}
	/**
	* 数量减少操作
	*/
	function decrement(obj){
		var stockNums = parseInt($(obj).parent().children("#stockNums").val());
		var buynum = $(obj).siblings(".buy-num");
		buynum.val(parseInt(buynum.val())-stockNums);
		checknum(buynum, stockNums);
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
				jAlert("数据加载失败！");
			}
		});
	}
	
	//删除 
	function deleteSingle(obj,id){
		if(confirm("是否确定删除")){
			$.ajax({
				type : "GET",
				url :  domain+"/cart/deleteCartById.html",
				data : {id:id},
				dataType : "json",
				success : function(data) {
					if(data.success){
						getNewCartInfo();
					}else {
						jAlert("删除失败");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});	
		}
	}
	/**
	 * 输入购买数量后进行校验
	 */
	function checknum(obj, stockNums){
		var val = $(obj).val();
		var datanow = $(obj).attr("data-now");
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(datanow);
			return false;
		}
		//如果值为1 不能点-
		var decrement = $(obj).parent().find(".decrement");
		if (parseInt(val) == stockNums){
		    $(decrement).attr('disabled',true);
		} else if(parseInt(val) < stockNums){
			$(decrement).attr('disabled',true);
			val = stockNums;
			$(obj).val(val);
			return false;
		} else{
		    $(decrement).removeAttr("disabled");
		}
		//判断是否大于库存量
		var productStock = $(obj).parent().parent().find(".productStock");
		if(productStock){
		    var pstock = $(productStock).html();
		    if(parseInt(val)>parseInt(pstock)){
		    	jAlert("库存量不足");
		    	//val = pstock;
		    	$(obj).val(datanow);
		    	return false;
		    }
		}else{
			jAlert("库存量为0，不能购买");
		    return false;
		}
		$(obj).attr("data-now",val);
		
		//更新购物车数据
		//获得购物车id
		var cartId = $(obj).siblings('.cartId').val();
		updateSingle(cartId,val);
		
		//重新加载单品信息
		getNewCartInfo();
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
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("操作失败！");
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
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("操作失败！");
			}
		});	
	}
	
</script>		
 <#include "/front/commons/_endbig.ftl" />
