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
					<a href='javascript:void(0)'>提现申请</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>提现申请</h3>
				
				<h3>
					<a  class='add-address ftx-05' href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/withdrawapply.html">申请提现</a>
				</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>序号</th>
							<th>申请时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<#if infoList??>
							<#list infoList as info>
								<tr>
									<td>
										<a href='' target='_blank' class='ftx-05'>${info_index+1 }</a>
									</td>
									<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
									<td>
										<#if  info.state??>
							  				<#assign state = info.state/>
							  				<#if state==1>待审核
								  				<#elseif state==2>通过
								  				<#elseif state==3>已打款
								  				<#elseif state==4>处理失败
								  				<#else>
							  				</#if>
					  		    		</#if>
									</td>
									<td>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/withdrawdetail.html?infoId=${(info.id)!''}' target='_blank' class='acolorblue'>查看</a>
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
				$("#withdrawdeposit").addClass("currnet_page");
				
			});
		
		</script>
		

<#include "/front/commons/_endbig.ftl" />