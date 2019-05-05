
		<#if exchangeList?? && exchangeList?size &gt; 0 >
		<#list  exchangeList as exchange>
		    <div class="oder-list sev-list">
		    	<h2 class="flex flex-pack-justify sev_regoods">
		    		<div>
		    		  <p class="mar-bt">订单编号：${(exchange.ordersProduct.ordersSn)!0}</p>
		    		  <p>申请时间：${(exchange.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</p>
		    		</div>
		    		<div>
		    			<font class="clr53">
		    				<#assign canComplain = 'false'/>
		  		    		<#if  exchange.state??>
				  				<#assign state = exchange.state>
				  				<#if state==1>未处理
					  				<#elseif state==2>审核通过待收货
					  				<#elseif state==3>已经收货
					  				<#elseif state==4>发货处理完成
					  				<#elseif state==5>不予处理原件退还
					  					<!-- 此时可以发起投诉 -->
					  					<#assign canComplain = 'true'/>
					  				<#elseif state==6>不处理
					  					<!-- 此时可以发起投诉 -->
					  					<#assign canComplain = 'true'/>
					  				<#else>
				  				</#if>
		  		    		</#if>
		    			</font>
		    		</div>
		    	</h2>
		    	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(exchange.ordersProduct.productId)!0}.html?goodId=${(exchange.ordersProduct.productGoodsId)!0}" class="block">
			    	<dl class="img-ul flex">
			    	  <dt style="width:80px; height:80px;"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${exchange.product.masterImg}"></dt>
			    	  <dd class="flex-2">
			    	    <div class="product_name">${(exchange.ordersProduct.productName)!''}&nbsp;${(exchange.ordersProduct.specInfo)!''}</div>
			    	    <div>x${(exchange.ordersProduct.number)!0}</div>
			    	  </dd>
			    	</dl>
		    	</a>
		    	<div class="order-status text-right">
		    		<a href="javascript:;" class="cla4" onclick="viewDetail(this)">查看</a>
		    		<#if canComplain??>
						&nbsp;
						<#if canComplain=='true'>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomplaint.html?productExchangeId=${(exchange.id)!''}&orderProductId=${(exchange.orderProductId)!''}&orderId=${exchange.orderId}" class="cla4">申诉</a>
						</#if>
					</#if>
				</div>
				
				<div class='bgff pad10 evalute-list' style="margin-top:0;">
					<div class='starbox'>
					    <div class='stararrow-up'></div>
						<div class='starlist flex pad10'>
						  <div class="pad-t6">问题描述：&nbsp;</div>
						  <div class='flex-2 expertxt'>
						  	  <textarea class='form-control' rows='3' id='question' name='question' disabled="disabled">${(exchange.question)!""}</textarea>
						  </div>
						</div>
						<div class='starlist flex pad10'>
						  <div class="pad-t6">处理意见：&nbsp;</div>
						  <div class='flex-2 expertxt'>
						  	  <textarea class='form-control' rows='3' id='remark' name='remark' disabled="disabled">${(exchange.remark)!'无'}</textarea>
						  </div>
						</div>
					</div>
				</div>
		    </div>
	    </#list>
		</#if>
