<#include "/h5/commons/_head.ftl" />
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
  	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html">
  	  	 		<span class="fa fa-angle-left"></span>
  	  	 	</a>
	 	 </div>
   	  	 <div class="flex-2 text-center">我要退换货</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   	<@form.form  id ="productBackForm" action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/doproductexchange.html">
	<!-- 隐藏域 -->
	<input type="hidden" id="sellerId" name="sellerId" >
	<input type="hidden" id="seller" name="seller" >
	<input type="hidden" id="orderId" name="orderId" > 
	<input type="hidden" id="orderProductId" name="orderProductId" >
	<input type="hidden" id="productId" name="productId" >
	<div class=""  >
	    <#if order?? && order.orderProductList?? && order.orderProductList?size &gt; 0 >
		<#list  order.orderProductList as product>
	    <div class="mar-bt pos_relative">
	    	<dl class="flex list-dl">
	    		<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.productId)!0}.html?goodId=${(product.productGoodsId)!0}' class="block">
	    			<dt style="width:80px; height:80px;"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.productLeadLittle}"></dt>
	    		</a>
	    		<dd class="padl flex-2">
	    			<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.productId)!0}.html?goodId=${(product.productGoodsId)!0}' class="block">
	    				<div class="product_name mar_top">${(product.productName)!''}&nbsp;${(product.specInfo)!''}</div>
	    			</a>
	    			<div class="flex flex-pack-justify goods-sun">
	    			<div>购买时间：<font>${(order.createTime?string("yyyy-MM-dd"))!''}</font></div>
	    			<div><a href="javascript:;" class="cla4" onclick="addApply(this,${(order.id)!0},${(product.id)!0},${(product.productId)!0},${(order.sellerId)!0})">我要退换货</a></div>
	    			</div>
	    		</dd>
	    	</dl>

	    	<!-- 评价框 -->
	    	<div class="applyDetailDiv" style="display:none"></div>
	    </div>
	    </#list>
		</#if>
    </div>
    </@form.form>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
	$(function(){
		
	});

	function exchangePrd(obj) {
		$("#productBackForm").attr("action",domain+"/member/doproductexchange.html");
		$(obj).addClass("active").siblings().removeClass("active");
	}

	function backPrd(obj) {
		$("#productBackForm").attr("action",domain+"/member/doproductback.html");
		$(obj).addClass("active").siblings().removeClass("active");
	}

	function addApply(obj, orderId, orderProductId, productId, sellerId) {
		$("#sellerId").val(sellerId);
		$("#seller").val(sellerId);
		$("#orderId").val(orderId);
		$("#orderProductId").val(orderProductId);
		$("#productId").val(productId);
		// 当前按钮对应的申请详细区域
		var detailDiv = $(obj).parents(".list-dl").siblings('.applyDetailDiv');
		// 点击我要退换货，首先判断当前的是否显示，如果显示则隐藏后返回（不清除数据）
		if (!detailDiv.is(':hidden')) {
			detailDiv.hide();
			return;
		}
		// 如果当前的不显示，则判断当前applyDetailDiv是否为空，不为空则直接显示，返回
		if ((detailDiv.html()).length > 0) {
			detailDiv.show();
			return;
		}
		// 如果当前applyDetailDiv是空，则清除所有applyDetailDiv的值，并隐藏所有applyDetailDiv，ajax调用后加载applyDetailDiv内容，并显示
		$(".applyDetailDiv").each(function(){
			$(this).empty();
			$(this).hide();
		});
		$.ajax({
			type:"GET",
			url:domain+"/member/canbackorexchange.html",
			dataType:"json",
			async : false,
			data : {orderProductId:orderProductId,orderId:orderId},
			success:function(data){
				if(data.success){
					// 检查通过则加载申请区域
					var detailHtml = "";
					detailHtml = detailHtml + "<div class='bgff pad10 evalute-list2'>";
					detailHtml = detailHtml + "	<div class='starbox'>";
					detailHtml = detailHtml + "	    <div class='stararrow-up'></div>";
					detailHtml = detailHtml + "	    <div class='starlist flex pad10'>";
					detailHtml = detailHtml + "		  服务类型：&nbsp;";
					detailHtml = detailHtml + "		  <div class='s-exchange'>";
					detailHtml = detailHtml + "		  	<a class='btn btn-default active' onclick='exchangePrd(this)'>换货</a>";
					detailHtml = detailHtml + "		  	<a class='btn btn-default' onclick='backPrd(this)'>退货</a>";
					detailHtml = detailHtml + "		  </div>";
					detailHtml = detailHtml + "		</div>";
					detailHtml = detailHtml + "		<div class='starlist flex pad10'>";
					detailHtml = detailHtml + "		  <div >问题描述：&nbsp;</div>";
					detailHtml = detailHtml + "		  <div class='flex-2 expertxt' style='margin-top:-13px;'>";
					detailHtml = detailHtml + "		  <p class='text-right font12 cl70'>10~100字</p>";
					detailHtml = detailHtml + "		  	  <textarea class='form-control' rows='3' id='question' name='question' ></textarea>";
					detailHtml = detailHtml + "		  	  <div>";
					detailHtml = detailHtml + "		  	  <font id='errLabel' class='font12 clr53'></font>";
					detailHtml = detailHtml + "		  	 </div>";  
					
					detailHtml = detailHtml + "		  </div>";
					detailHtml = detailHtml + "		</div>";
					detailHtml = detailHtml + "		<div class='text-center mar-bt'>";
					detailHtml = detailHtml + "			<button type='button' class='btn o-d-btn1 o-d-btn2' id='applySubmitId' onclick='applySubmit()'>提交</button>";
					detailHtml = detailHtml + "		</div>";
					detailHtml = detailHtml + "	</div>";
					detailHtml = detailHtml + "</div>";
					
					detailDiv.empty();
					detailDiv.html(detailHtml);
					detailDiv.show();
				}else{
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
					return;
				}
			}
		});
	}
	
	function applySubmit() {
		
		var question = $("#question").val();
		if (question == null || question == "") {
			$("#errLabel").html("请输入问题描述");
			return;
		}
		
		if (question.length < 10) {
			$("#errLabel").html("请至少输入10个字符");
			return;
		}
		
		if (question.length > 100) {
			$("#errLabel").html("最多输入100个字符");
			return;
		}
		
		$("#applySubmitId").attr("disabled","disabled");
		var params = $('#productBackForm').serialize();
		$.ajax({
			type:"POST",
			url:$('#productBackForm').attr("action"),
			dataType:"json",
			data : params,
			success:function(data){
				if(data.success){
					// alert("保存成功");
					$.dialog('alert','提示','保存成功',2000);
					$(".applyDetailDiv").each(function(){
						$(this).empty();
						$(this).hide();
					});
				}else{
					// alert(data.message);
					// $("#applySubmitId").removeAttr("disabled");
					$.dialog('alert','提示',data.message,2000,function(){ $("#applySubmitId").removeAttr("disabled"); });
				}
			}
		}); 
	}
</script>
 
</body>
</html>