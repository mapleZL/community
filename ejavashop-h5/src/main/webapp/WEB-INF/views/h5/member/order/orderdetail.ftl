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
   	  	 <div class="flex-2 text-center">订单详情</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
   <#if errorMsg!="">
		<h1 style="margin: 30px;text-align: center;color:red">	
			${errorMsg}
		</h1>
	<#else>
	<div class=""  >
		<div class="order-d-box">
	   <p>订单编号:<font><#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if></font></p>
	   <#-- <p>订单编号:<font>${(order.orderSn)!''}</font></p>-->
        <p>订单金额:<font>￥${(order.moneyOrder)!'' }</font></p>
        <p>订单日期:<font>${(order.createTime)?string("yyyy-MM-dd")!'' }</font></p>
        <p>订单备注:<font>${(order.remark)!''}</font></p>
        <p class="text-center mar_top" id="detailBtnP">
			<!-- <a class="btn o-d-btn1 o-d-btn2" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderlog.html?id=${(order.id)!0}" role="button">订单跟踪</a> -->
			<#if order.orderState??>
 				<#assign state = order.orderState>
 				<!-- 订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单 -->
 				<#if state==1>
					&nbsp;
					<a class="btn o-d-btn1 o-d-btn2" href="javascript:;" role="button" onclick="cancalOrder('${order.id}')">取消订单</a>
					&nbsp;
					<a class="btn o-d-btn1" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/order/pay.html?relationOrderSn=${(order.orderSn)!''}&rid=${commUtil.randomString(20)}" role="button">去付款</a>
 			<#--
 				<#elseif state==2>
 					&nbsp;
					<a class="btn o-d-btn1 o-d-btn2" href="javascript:;" role="button" onclick="cancalOrder('${order.id}')">取消订单</a>
 				<#elseif state==3>
 					&nbsp;
					<a class="btn o-d-btn1 o-d-btn2" href="javascript:;" role="button" onclick="cancalOrder('${order.id}')">取消订单</a>
			-->
 				<#elseif state==4>
 					&nbsp;
 					<a class="btn o-d-btn1 o-d-btn2" href="javascript:;" role="button" onclick="goodsReceipt('${(order.id)!''}')">确认收货</a>
 				<#elseif state==5>
 					&nbsp;
 					<a class="btn o-d-btn1 o-d-btn2" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}" role="button" >评价晒单</a>
					&nbsp;
					<a class="btn o-d-btn1 o-d-btn2" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}" role="button" >申请退换货</a>
 				<#elseif state==6>
 					
 				</#if>
 		    </#if>
        </p>
		</div>
		<#if (order.orderProductList)??>
		<#list (order.orderProductList) as op>
		<div class="order-d-box">
			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html?goodId=${(op.productGoodsId)!0}" class="block">
				<dl class="flex order-d-dl">
					<dt style="width:80px; height:80px;">
						<#if (op.product)?? && op.product.isNorm == 2 && (op.productGoods)?? && (op.productGoods.images!='')>
						<img optype="goods" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productGoods.images!''}">
						<#else>
						<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
						</#if>
					</dt>
					<dd class="padl flex-2">
						<div class="product_name">${(op.productName)!''}&nbsp;${(op.specInfo)!''}</div>
						<div class="clr53 font12">￥<font>${(op.moneyPrice)!''}</font></div>
						<div class="font16">x<font>${(op.number)!''}</font></div>
					</dd>
				</dl>
			</a>
		</div>
		</#list>
		</#if>

     <div class="order-d-box">
         <h2 class="flex flex-pack-justify pad-bt">
           <#if order.paymentStatus??>
				<#if (order.paymentStatus==1)>
		 			<div>付款方式:</div>
           			<div>${(order.paymentName)!'' }</div>
				<#else>
					<div>付款方式:</div>
           			<div>未付款</div>
				</#if>
			</#if>
         </h2>
         <div class="flex flex-pack-justify ">
            <div>商品金额:</div>
            <div class="clr53">+ ￥${(order.moneyProduct)?string("0.00")!'0.00' }</div>
         </div>
         <div class="flex flex-pack-justify">
            <div>运费:</div>
            <div class="clr53">+ ￥${(order.moneyLogistics)!'0.00'}</div>
         </div>
         <div class="flex flex-pack-justify">
            <div>套餐服务费:</div>
            <div class="clr53">+ ￥${(order.servicePrice)?string('0.00')!'' }</div>
         </div>
         <div class="flex flex-pack-justify ">
            <div>优惠总额:</div>
            <div class="clr53">- ￥${(order.moneyDiscount)?string("0.00")!'0.00' }</div>
         </div>
         <div class="flex flex-pack-justify ">
            <div>积分支付:</div>
            <div class="clr53">- ￥${(order.moneyIntegral)?string("0.00")!'0.00' }</div>
         </div>
         <div class="flex flex-pack-justify ">
            <div>余额支付:</div>
            <div class="clr53">- ￥${(order.moneyPaidBalance)?string("0.00")!'0.00' }</div>
         </div>
         <div class="flex flex-pack-justify ">
            <div>现金支付:</div>
            <div class="clr53">- ￥${(order.moneyPaidReality)?string("0.00")!'0.00' }</div>
         </div>
         <div class="flex flex-pack-justify ">
            <div>订单金额:</div>
            <div class="clr53">￥${(order.moneyOrder)?string("0.00")!'0.00' }</div>
         </div>
         <div class="flex flex-pack-justify bort">
            <#if order.paymentStatus??>
				<#if (order.paymentStatus==1)>
					<div>实付金额:</div>
            		<div class="clr53">&nbsp;&nbsp;￥${(order.moneyPaidReality + order.moneyPaidBalance)?string('0.00')!'' }</div>
				<#else>
					<div>应付金额:</div>
            		<div class="clr53">&nbsp;&nbsp;￥${(order.moneyOrder - order.moneyIntegral)?string('0.00')!'' }</div>
				</#if>
			</#if>
			
         </div>

     </div>

     <div class="order-d-box">
         <h2 class="pad-bt cl0">
            收货信息
         </h2>
         <p>姓名：${(order.name)!'' }</font></p>
         <p>联系方式：${commUtil.hideMiddleStr(order.mobile,3,4)!''}</font></p>
         <p>收货地址：${(order.addressAll)!''}&nbsp;${(order.addressInfo)!''}</font></p>
     </div>
     <div class="order-d-box">
         <h2 class="pad-bt cl0">
            配送信息
         </h2>
         <p>配送方式：<font>${(order.logisticsName)!""}</font></p>
         <p>快递单号：<font>${(order.logisticsNumber)!""}</font></p>
         <p>发货时间：<font><#if order.deliverTime?? >${(order.deliverTime)?string("yyyy-MM-dd HH:mm:ss")!""}</#if></font></p>
         <#if orderLogList?? && orderLogList?size &gt; 0 >
		 <#list orderLogList as orderLog >
		 <div class="logisbox mar_top">
			${orderLog.createTime?string("yyyy-MM-dd HH:mm:ss") }
		  	<br>
		  	${orderLog.content}
		 </div>
		 </#list>
		 </#if>
     </div>

     <div class="order-d-box" style="display: none;">
         <h2 class="pad-bt cl0">
            发票信息
         </h2>
         <#if order.invoiceStatus?? && order.invoiceStatus == 0 >
			 <p>发票类型：<font>不要发票</font></p>
         <#elseif order.invoiceStatus?? && order.invoiceStatus == 1 >
	         <p>发票类型：<font>单位</font></p>
	         <p>发票抬头：<font>${(order.invoiceTitle)!""}</font></p>
	         <p>发票内容：<font>${(order.invoiceType)!""}</font></p>
         <#elseif order.invoiceStatus?? && order.invoiceStatus == 2 >
			 <p>发票类型：<font>个人</font></p>
	         <p>发票抬头：<font>${(order.invoiceTitle)!""}</font></p>
	         <p>发票内容：<font>${(order.invoiceType)!""}</font></p>
         </#if>
     </div>

  </div>
  </#if>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
	$(function(){
		
	});
	
	//取消订单
	function cancalOrder(ordersId){
		/* if(confirm("确定要取消该订单吗？")){
			$.ajax({
				type : "GET",
				url :  domain+"/member/cancalorder.html",
				data : {id:ordersId},
				dataType : "json",
				success : function(data) {
					if(data.success){
						// 修改显示按钮
						// var btnDiv = "<a class='btn o-d-btn1 o-d-btn2' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderlog.html?id=${(order.id)!0}' role='button'>订单跟踪</a>";
						$("#detailBtnP").empty();
						// $("#detailBtnP").append(btnDiv);
					}else {
						// alert(data.message);
						$.dialog('alert','提示',data.message,2000);
					}
				}
			});
		} */
		$.dialog('confirm','提示','确定要取消该订单吗？',0,function(){
			$.closeDialog();
			$.ajax({
				type : "GET",
				url :  domain+"/member/cancalorder.html",
				data : {id:ordersId},
				dataType : "json",
				success : function(data) {
					if(data.success){
						// 修改显示按钮
						$("#detailBtnP").empty();
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
					// 修改显示按钮
					/* var btnDiv = "<a class='btn o-d-btn1 o-d-btn2' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderlog.html?id=${(order.id)!0}' role='button'>订单跟踪</a>"; */
					var btnDiv = "";
					btnDiv = btnDiv + "&nbsp;";
					btnDiv = btnDiv + "<a class='btn o-d-btn1 o-d-btn2' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}' role='button' >评价晒单</a>";
					btnDiv = btnDiv + "&nbsp;";
					btnDiv = btnDiv + "<a class='btn o-d-btn1 o-d-btn2' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}' role='button' >申请退换货</a>"
					$("#detailBtnP").empty();
					$("#detailBtnP").append(btnDiv);
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