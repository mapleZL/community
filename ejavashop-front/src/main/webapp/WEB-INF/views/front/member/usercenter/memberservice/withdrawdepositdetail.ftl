<#include "/front/commons/_headbig.ftl" />
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
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
			<div class='wrapper_main'>
				<h3>提现申请</h3>
				
				<form class='myinfo' id='form'>
					<input type="hidden" id="balance" value="${balance!'0.00'}">
					<dl class='dl_col1'>
						<dt>
							<label>银行名称：</label>
						</dt>
						<dd class='p-item'>
							${(info.bank)!'' }
						</dd>
						<dt>
							<label>银行卡号：</label>
						</dt>
						<dd class='p-item'>
							${(info.bankCode)!'' }
						</dd>
						<dt>
							<label>申请提现金额：</label>
						</dt>
						<dd class='p-item'>
							${(info.money)!'' }
						</dd>
						<dt>
							<label>状态：</label>
						</dt>
						<dd class='p-item'>
							<#if  info.state??>
							  				<#assign state = info.state/>
							  				<#if state==1>待审核
								  				<#elseif state==2>通过
								  				<#elseif state==3>已打款
								  				<#elseif state==4>处理失败（${(info.failReason)!'' }）
								  				<#else>
							  				</#if>
					  		    		</#if>
						</dd>
					</dl>
				</form>
			</div>

			<!-- end -->
		</div>
	<script type="text/javascript">
		$(function(){
			//控制左侧菜单选中
			$("#withdrawdeposit").addClass("currnet_page");
		});
		
		
	</script>
	
<#include "/front/commons/_endbig.ftl" />
