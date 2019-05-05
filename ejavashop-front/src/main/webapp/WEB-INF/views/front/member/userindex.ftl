<#include "/front/commons/_headbig.ftl" />


<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/userindex.css"/>
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
					<a href='javascript:void(0)'>用户中心</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>用户中心</h3>
				<div class='mc'>
						
						<div class="moneyblock">
								<ul>
									<li id="pre-deposit">
											<h5>账户余额&nbsp;</h5>
											<span class="icon"></span> <span class="value">可用余额：￥<em>${(member.balance)!'0.00' } </em>元
											</span> 
									</li>
									<li id="points">
											 <h5>可用积分</h5>
											 <span class="icon"></span>
											 <span class="value"><em>${(member.integral)!'0' }</em>分</span>
									</li> 
								</ul>
							</div>
					<div class="transaction">
								<h3>交易提醒</h3>
								<ul>
									<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1">待付款(<b class="red">${(toBepaidOrders)!'0' }</b>)
									</a></li>
									<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=4">待收货(<b class="red">${(toBeReceivedOrders)!'0' }</b>)
									</a></li>
									<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=5">待评价(<b class="black">${(toBeCommentedOrders)!'0' }</b>)
									</a></li>
								</ul>
							</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
		
</script>

<#include "/front/commons/_endbig.ftl" />
