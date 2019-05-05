<#include "/front/commons/_headbig.ftl" />

<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>

		<div class='w w1 header container'>
			<!-- <div id='logo'>
				<a href='' target='_blank' class='link1'>
					<img src=''>
				</a>
			</div> -->
			<div class='stepflex'>
				<dl class='first done'>
					<dt class='s-num'>1</dt>
					<dd class='s-text'>
						1.我的进货单
						<s></s>
						<b></b>
					</dd>
				</dl>
				<dl class='normal done'>
					<dt class='s-num'>2</dt>
					<dd class='s-text'>
						2.填写核对订单信息
						<s></s>
						<b></b>
					</dd>
				</dl>
				<dl class='normal last doing'>
					<dt class='s-num'>3</dt>
					<dd class='s-text'>
						3.成功提交订单
						<s></s>
						<b></b>
					</dd>
				</dl>
			</div>
		</div>
		<!-- 订单成功信息 -->
		<div class='container w'>
			<div class='msop mts'>
				<s class='icon-mts'></s>
	     <#if paymentStatus==3>
				<h3>亲，当前订单经过修改后，需要财务进行审核，大袜哥将第一时间与您联系沟通。谢谢配合。</h3>
         <#else>
				<h3>亲，当前订单是二次加工订单，大袜哥将第一时间与您联系沟通。谢谢配合。</h3>
         </#if>
				<h3 class='ftc-02'>感谢您，订单提交成功！请到<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html'>订单中心</a>查看详情。</h3>
				
			</div>
		</div>
<#include "/front/commons/_endbig.ftl" />