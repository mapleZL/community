<#include "/front/commons/_headbig.ftl" />

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
					<a href='javascript:void(0)'>换货</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>换货</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>订单编号</th>
							<th>商品名称</th>
							<th>申请时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<#if exchangeList??>
							<#list exchangeList as backinfo>
								<tr>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(backinfo.orderId)!''}' target='_blank' class='ftx-05'>${(backinfo.orderSn)!''}</a>
									</td>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(backinfo.productId)!0}.html' target='_blank' class='ftx-05'>${(backinfo.productName)!''}</a>
									</td>
									<td>${(backinfo.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
									<td>
										<#assign canComplain = 'false'/>
										<#if  backinfo.state??>
							  				<#assign state = backinfo.state>
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
									</td>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/exchangedetail.html?backid=${(backinfo.id)!''}&&orderProductId=${(backinfo.orderProductId)!''}&&orderId=${(backinfo.orderId)!''}''  target='_self' class='acolorblue'>查看</a>
										<#if canComplain??>
											<#if canComplain=='true'>
												| <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomplain.html?productExchangeId=${(backinfo.id)!''}&&orderProductId=${(backinfo.orderProductId)!''}&&orderId=${backinfo.orderId}' target='_self' class='acolorblue'>申诉</a>
											</#if>
										</#if>
									</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
				<!-- 分页 -->
				<#include "/front/commons/_pagination.ftl" />
			</div>
			
		</div>
		<script type="text/javascript">
			$(function(){
				//控制左侧菜单选中
				$("#productexchange").addClass("currnet_page");
			});
		</script>

<#include "/front/commons/_endbig.ftl" />