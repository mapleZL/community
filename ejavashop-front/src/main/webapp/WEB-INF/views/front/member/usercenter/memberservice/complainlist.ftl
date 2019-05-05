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
					<a href='javascript:void(0)'>申诉</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>申诉</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>订单编号</th>
							<th>商品名称</th>
							<th>申诉时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<#if complaintList??>
							<#list complaintList as complaint>
								<tr>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(complaint.orderId)!''}' target='_blank' class='ftx-05'>${(complaint.orderSn)!''}</a>
									</td>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(complaint.productId)!0}.html' target='_blank' class='ftx-05'>${(complaint.productName)!''}</a>
									</td>
									<td>${(complaint.complaintTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
									<td>
										<#if  complaint.state??>
							  				<#assign state = complaint.state/>
							  				<#if state==1>待审核
								  				<#elseif state==2>投诉不通过
								  				<#elseif state==3>投诉通过
								  				<#elseif state==4>卖家申诉待审核
								  				<#elseif state==5>卖家申诉不通过
								  				<#elseif state==6>卖家申诉通过
								  				<#else>审核中
							  				</#if>
					  		    		</#if>
									</td>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/complaindetail.html?infoId=${(complaint.id)!''}&&orderProductId=${(complaint.orderProductId)!''}&&orderId=${(complaint.orderId)!''}'  target='_self' class='acolorblue'>查看</a>
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
				$("#complainlist").addClass("currnet_page");
				
			});
		</script>

<#include "/front/commons/_endbig.ftl" />