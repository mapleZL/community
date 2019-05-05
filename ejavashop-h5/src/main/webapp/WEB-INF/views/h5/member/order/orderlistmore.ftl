<#if ordersList?? && ordersList?size &gt; 0 >
		<#list ordersList as order>
		    <div class="oder-list">
		    	<h2 class="flex flex-pack-justify">
		    		<div>订单号：<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if></div>
		    		<#--<div>订单号：${(order.orderSn)!''}</div>-->
		    		<!-- 订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单 -->
		    		<#if order.orderState??>
		  				<#assign state = order.orderState>
		  				<#if state==1>
		  					<div><font id="orderStateFont${(order.id)!0}" class="clr53">等待付款</font></div>
		  				<#elseif state==2>
		  					<div><font id="orderStateFont${(order.id)!0}" class="clr53">等待确认</font></div>
		  				<#elseif state==3>
		  					<div><font id="orderStateFont${(order.id)!0}">备货中</font></div>
		  				<#elseif state==4>
		  					<div><font id="orderStateFont${(order.id)!0}">已发货</font></div>
		  				<#elseif state==5>
		  					<div><font id="orderStateFont${(order.id)!0}">完成</font></div>
		  				<#elseif state==6>
		  					<div><font id="orderStateFont${(order.id)!0}">取消</font></div>
		  				</#if>
		  		    </#if>
		    	</h2>
		    	<#if (order.orderProductList)?? && (order.orderProductList)?size &gt; 1 >
			    	<!-- <a href="" class="block"> -->
				    	<ul class="img-ul clear">
							<#list order.orderProductList as op>
								<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html?goodId=${(op.productGoodsId)!0}" class="block">
					    			<li>
					    			<#if op.productGoods?? && op.productGoods.images != ''>
									<img optype="goods" class="lazy${pageIndex}" data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productGoods.images!''}">
									<#else>
									<img class="lazy${pageIndex}" data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
									</#if>
					    			</li>
					    		</a>
				    		</#list>
				    	</ul>
			    	<!-- </a> -->
		    	<#elseif (order.orderProductList)?? && (order.orderProductList)?size == 1 >
		    		<#list (order.orderProductList) as op>
		    		<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html?goodId=${(op.productGoodsId)!0}" class="block">
				    	<dl class="img-ul flex">
				    	  <dt>
				    	  	<#if op.productGoods?? && op.productGoods.images!=''>
							<img optype="goods" class="lazy${pageIndex}" data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productGoods.images!''}">
							<#else>
							<img class="lazy${pageIndex}" data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
							</#if>
				    	  </dt>
				    	  <dd class="product_name flex-2">
				    	  ${(op.productName)!''}
				    	  	<span style="color: #888888;">${(op.specInfo)!''}</span>
				    	  </dd>
				    	</dl>
			    	</a>
			    	</#list>
		    	</#if>
		    	<div class="flex flex-pack-justify order-status">
		    		<div>实付款<font>¥${(order.moneyOrder)!''}</font></div>
		    		<div id="orderBtnDiv${(order.tradeNo)!0}">
		  				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!0}" class="cla4">查看</a>
			    		<#if order.orderState??>
			  				<#assign state = order.orderState>
			  				<#-- 订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单 -->
			  				<#if state==1>
		  						&nbsp;
		  						<a href="javascript:;" class="cla4" onclick="cancalOrder('${order.tradeNo}')">取消订单</a>
		  						&nbsp;
		  						<a class="paybtn" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/order/pay.html?relationOrderSn=<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if>&rid=${commUtil.randomString(20)}">去付款</a>
			  				<#--<#elseif state==2>
			  					&nbsp;
		  						<a href="javascript:;" class="cla4" onclick="cancalOrder('${order.tradeNo}')">取消订单</a>
			  				<#elseif state==3>
			  					&nbsp;
		  						<a href="javascript:;" class="cla4" onclick="cancalOrder('${order.tradeNo}')">取消订单</a>-->
			  				<#elseif state==4>
			  					&nbsp;
		  						<a href="javascript:;" class="cla4" onclick="goodsReceipt('${(order.id)!''}')">确认收货</a>
			  				<#elseif state==5>
			  					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}" class="cla4">评价晒单</a>
		  						&nbsp;
		  						<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}" class="cla4">申请退换货</a>
			  				<#elseif state==6>
			  					
			  				</#if>
			  		    </#if>
		  		    </div>
		    	</div>
		    </div>
	    </#list>
	    </#if>
