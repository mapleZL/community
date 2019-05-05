
		<#if backList?? && backList?size &gt; 0 >
		<#list  backList as back>
		    <div class="oder-list sev-list">
		    	<h2 class="flex flex-pack-justify sev_regoods">
		    		<div>
		    		  <p class="mar-bt">订单编号：${(back.ordersProduct.ordersSn)!0}</p>
		    		  <p>申请时间：${(back.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</p>
		    		</div>
		    		<div>
		    			<font class="clr53">
		    				<#assign canComplain = 'false'/>
		    				<#if  back.stateReturn??>
				  				<#assign state = back.stateReturn/>
				  				<#if state==1>未处理
				  				<#elseif state==2>审核通过待收货
				  				<#elseif state==3>已经收货
				  				<#elseif state==4>不予处理
				  					<!-- 此时可以发起投诉 -->
				  					<#assign canComplain = 'true'/>
				  				<#else>
				  				</#if>
		  		    		</#if>
		    			</font>
		    		</div>
		    	</h2>
		    	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(back.ordersProduct.productId)!0}.html?goodId=${(back.ordersProduct.productGoodsId)!0}" class="block">
			    	<dl class="img-ul flex">
			    	  <dt style="width:80px; height:80px;"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${back.product.masterImg}"></dt>
			    	  <dd class="flex-2">
			    	    <div class="product_name">${(back.ordersProduct.productName)!''}&nbsp;${(back.ordersProduct.specInfo)!''}</div>
			    	    <div>x${(back.ordersProduct.number)!0}</div>
			    	  </dd>
			    	</dl>
		    	</a>
		    	<div class="order-status text-right">
		    		<a href="javascript:;" class="cla4" onclick="viewDetail(this)">查看</a>
		    		<#if canComplain??>
						&nbsp;
						<#if canComplain=='true'>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomplaint.html?productBackId=${(back.id)!''}&orderProductId=${(back.orderProductId)!''}&orderId=${back.orderId}" class="cla4">申诉</a>
						</#if>
					</#if>
				</div>
				
				<div class='bgff pad10 evalute-list' style="margin-top:0;">
					<div class='starbox'>
					    <div class='stararrow-up'></div>
						<div class='starlist flex pad10'>
						  <div class="pad-t6">问题描述：&nbsp;</div>
						  <div class='flex-2 expertxt'>
						  	  <textarea class='form-control' rows='3' id='question' name='question' disabled="disabled">${(back.question)!""}</textarea>
						  </div>
						</div>
						<#if back??>
							<div class='starlist flex pad10'>
								<div class="pad-t6">退款金额：&nbsp;</div>
								<div class='pad-t5 expertxt'>
							  		￥${(back.backMoney)!}
								</div>
							</div>
							<div class='starlist flex pad10'>
								<div class="pad-t6">返回积分：&nbsp;</div>
								<div class='pad-t5 expertxt'>
							  		${(back.backIntegral)!}
								</div>
							</div>
							<#if back.couponUser?? >
								<div class='starlist flex pad10'>
									<div class="pad-t6">返回红包：&nbsp;</div>
									<div class='pad-t5 expertxt'>
								  		${(back.couponUser.couponSn)!}
									</div>
								</div>
							</#if>
					  		<#if back.stateReturn==1>
								<div class='starlist flex pad10'>
									<div class="pad-t6">退货状态：&nbsp;</div>
									<div class='pad-t5 expertxt'>
								  		未处理
									</div>
								</div>
					  		<#elseif back.stateReturn==2>
								<div class='starlist flex pad10'>
									<div class="pad-t6">退货状态：&nbsp;</div>
									<div class='pad-t5 expertxt'>
								  		审核通过待收货
									</div>
								</div>
					  		<#elseif back.stateReturn==3>
								<div class='starlist flex pad10'>
									<div class="pad-t6">退货状态：&nbsp;</div>
									<!-- <div class='flex-2 expertxt'> -->
									<div class='pad-t5 expertxt'>
								  		已经收货
									</div>
								</div>
								<div class='starlist flex pad10'>
									<div class="pad-t6">退款状态：&nbsp;</div>
									<div class='pad-t5 expertxt'>
								  		<#if back.stateMoney==1>未退款</#if>
										<#if back.stateMoney==2>退款到账户</#if>
										<#if back.stateMoney==3>退款到银行</#if>
									</div>
								</div>
								<div class='starlist flex pad10'>
									<div class="pad-t6">退款时间：&nbsp;</div>
									<div class='pad-t5 expertxt'>
								  		<#if back.backMoneyTime??>
											${(back.backMoneyTime)?string("yyyy-MM-dd HH:mm:ss")}
										<#else>
											正在处理
										</#if>
									</div>
								</div>
					  		<#else>
					  			<div class='starlist flex pad10'>
									<div class="pad-t6">退货状态：&nbsp;</div>
									<div class='pad-t5 expertxt'>
								  		不予处理
									</div>
								</div>
					  		</#if>
					  	</#if>
						<div class='starlist flex pad10'>
						  <div class="pad-t6">处理意见：&nbsp;</div>
						  <div class='flex-2 expertxt'>
						  	  <textarea class='form-control' rows='3' id='remark' name='remark' disabled="disabled">${(back.remark)!'无'}</textarea>
						  </div>
						</div>
					</div>
				</div>
		    </div>
	    </#list>
		</#if>
