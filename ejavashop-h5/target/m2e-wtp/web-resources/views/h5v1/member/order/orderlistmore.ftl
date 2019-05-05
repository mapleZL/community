<#if ordersList?? && ordersList?size &gt; 0 >
		<#list ordersList as order>
			<div class="con ico" style="margin-top:10px;">
		    	<div class="ico overflow public">
		        	<em class="fl">订单号：<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if></em>
		            <em class="fr em4">
							<!-- 订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单 -->
				    		<#if order.orderState??>
		  						<#assign state = order.orderState>
				  				<#if state==1>
				  					<#if order.paymentStatus == 2>二次加工待确认<#else>等待付款</#if>
				  				<#elseif state==2>
				  					等待确认
				  				<#elseif state==3>
				  					备货中
				  				<#elseif state==4>
				  					订单已发货
				  				<#elseif state==5>
				  					订单已完成
				  				<#elseif state==6>
				  					订单已取消
				  				</#if>
				  		    </#if>
					</em>
		        </div>
		        <#list (order.orderProductList) as op>
		        <div data-am-widget="intro" class="am-intro am-cf am-intro-default default">
		             <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html?goodId=${(op.productGoodsId)!0}">
		             <div class="am-g am-intro-bd">
		                <div class="am-intro-left am-u-sm-4">
			                	<#if op.productGoods?? && op.productGoods.images != ''>
			                		<img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${op.productGoods.images!''}"/>
			                	<#else>
									<img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
								</#if>
		                </div>
		                <div class="am-intro-right am-u-sm-8">
		                       <h3 class="title1 em2">${(op.productName)!''}&nbsp;&nbsp;${(op.specInfo)!''}</h3>
		                </div>
		             </div>
		            </a>
         		</div>
		       <!-- end-->
		       </#list>
		        <div class="overflow ico tp2 public">
		            <em class="fr">共${order.remark}件商品  合计：¥${(order.moneyOrder)!''}</em>
		        </div>
		        <div class="btn_box ico public" id = "orderBtnDiv${(order.tradeNo)!0}">
		        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!0}">查看</a>
		        		<#if order.orderState??>
			  				<#assign state = order.orderState>
			  				<#if state == 1>
		        				<a class = "order_delete" href="javascript:void(0);" data-order-tradeno="${(order.tradeNo)!''}">取消订单</a>
		        				<#if order.paymentStatus != 2>
		            				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/order/pay.html?relationOrderSn=<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if>&rid=${commUtil.randomString(20)}">去付款</a>
		            			</#if>
		        			<#elseif state == 4>
		  						<a href="javascript:void(0);" class="order_confirm" data-order-id="${(order.id)!''}" >确认收货</a>
			  				<#elseif state== 5>
			  					<a class = "order_cancel" href="javascript:void(0);" data-order-orderid="${(order.id)!''}">删除订单</a>
			  					<#--  <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}" >评价晒单</a>
		  								 <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}" >申请退换货</a>
		  						-->
			  				<#elseif state == 6>
			  					<a class = "order_cancel" href="javascript:void(0);" data-order-orderid="${(order.id)!''}">删除订单</a>
			  				<#elseif state == 3>
			  				</#if>
			  			</#if>
		         </div> 
		     </div>
		   </#list>
		</#if>