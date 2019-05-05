<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/productback.css">

		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>换货申请</a>
				</span>
			</div>
		</div>
		
<form id ="productBackForm" action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/doproductexchange.html">
<!-- 隐藏域 -->
<input type="hidden" name="sellerId" value="${(orderProduct.sellerId)!''}">
<input type="hidden" name="seller" value="${(orderProduct.sellerId)!''}">
<input type="hidden" name="orderId" value="${(order.id)!''}"> 
<!-- 网单id -->
<input type="hidden" name="orderProductId" value="${(orderProduct.id)!''}">
<input type="hidden" name="productId" value="${(orderProduct.productId)!''}">

		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>换货信息</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>商品名称</th>
							<th>购买数量</th>
						</tr>
						<tr>
							<td>
								<ul class='list-proinfo'>
									<li>
										<#if orderProduct??>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target="_blank">
												<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${orderProduct.productLeadLittle}' width='50' height='50' title='${(orderProduct.productName)!''}'>
											</a>
											<div class='p-info-name'>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target='_blank'>${(orderProduct.productName)!''}</a>
											</div>
										</#if>
									</li>
								</ul>
							</td>
							<td>${(orderProduct.number)!''}</td>
						</tr>
					</tbody>
				</table>
				<div class='apply-form' id='consignee-form'>
					<div class='repair-steps'>
						<div class='repair-step repair-step-curr'>
							<!-- 问题描述 -->
							<div class='miaoshuDiv'>
								<!-- 问题描述 -->
								<div class='sellerPrompt'>
									<span class='label'>
										<em>*</em>
										<span>问题描述：</span> 
									</span>
									<div >
										 ${(info.question)!'' }
									</div>
								</div>
								
								
								<div class='sellerPrompt'>
									<span class='label'>
										<span>状态：</span> 
									</span>
									<div>
										 <#if  info.state??>
							  				<#assign state = info.state>
							  				<#if state==1>未处理
								  				<#elseif state==2>审核通过待收货
								  				<#elseif state==3>已经收货
								  				<#elseif state==4>发货处理完成
								  				<#elseif state==5>不予处理原件退还
								  				<#elseif state==6>不处理
								  				<#else>
							  				</#if>
					  		    		</#if>
									</div>
									<em class='em-errMes'></em>	
								</div>
								
								<div class='sellerPrompt'>
									<span class='label'>
										<span>处理意见：</span> 
									</span>
									<div>
										${(info.remark)!'无'}
									</div>
									<em class='em-errMes'></em>	
								</div>
							</div>
							
							<div class='miaoshuDiv'>
								<!-- 状态 -->
								
								<!-- end -->
							</div>
							
							<!-- end -->
							
							
						</div>
					</div>
				</div>
			</div>
 </div>
 
</form>
<script type="text/javascript">
	

	$(function(){
		//控制左侧菜单选中
		$("#myorder").addClass("currnet_page");

	});
	
</script>

<#include "/front/commons/_endbig.ftl" />
