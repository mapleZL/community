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
					<a href='javascript:void(0)'>我的咨询</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>我的咨询</h3>
				<div class='mc'>
					<table class='tb-void'>
						<thead>
							<tr>
								<th >咨询商品</th>
								<th >商品名称</th>
								<th >咨询内容</th>
								<th>咨询回复</th>
								<th>咨询时间</th>
							</tr>
						</thead>
						
						<tbody>
						<#if askList??>
							<#list askList as askinfo >
						
							<!-- <tr>
								<td colspan="3">（暂无记录）</td>
							</tr> -->
							<tr>
								<td>
									<div class='img-list zixun-img'>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(askinfo.productId)!0}.html' target='_blank' class='img-box'>
											<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${askinfo.productLeadLittle}' width='50' height='50' class='err-product' title='${(askinfo.productName)!''}'>
										</a>
									</div>
								</td>
								<td>
									${(askinfo.productName)!''}
								</td>
								<td>
									${(askinfo.askContent)!''}
								</td>
								<td>
									${(askinfo.replyContent)!''}
								</td>
								<td>
									${(askinfo.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}
								</td>
							</tr>
								
							</#list>
						</#if>
						</tbody>
					</table>
				</div>
				
				<!-- 分页 -->
				<#include "/front/commons/_pagination.ftl" />
				
			</div>
		</div>
		<script type="text/javascript">
		$(function(){
			//控制左侧菜单选中
			$("#myconsultation").addClass("currnet_page");
		});
		
		</script>
<#include "/front/commons/_endbig.ftl" />
