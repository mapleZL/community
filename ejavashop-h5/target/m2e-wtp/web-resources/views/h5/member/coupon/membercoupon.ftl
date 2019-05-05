<#include "/h5/commons/_head.ftl" />

<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/scroll.css">

<body>
	<!-- S 头部 -->
	<header id="header" class="headerbox">
		<div class="flex flex-align-center head-bar">
			<div class="flex-1 text-left">
				<a href="javascript:history.back();">
				<span class="fa fa-angle-left"></span>
			</div>
			<div class="flex-2 text-center">红包</div>
			<div class="flex-1 text-right" id="fa-bars">
				
			</div>
		</div>
		 <#include "/h5/commons/_hidden_menu.ftl" />
	</header>
	<!-- E 头部 -->
	<main id="wrapper">
	<div class="pepper-con" id="thelist">
		<#--<div class="pepper-w tips">温馨提示:点击红包直接使用</div>-->
		<#assign hasCoupon = false>
		<#if couponUsers?? && couponUsers?size &gt; 0>
		<#assign hasCoupon = true>
		<#list couponUsers as couponUser>
		<div class="pepper-w" <#if !couponUser.timeout && !couponUser.isuse><#--onclick="location.href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(couponUser.sellerId)!0}.html'"--></#if>>
			<div class="pepper" <#if couponUser.timeout || couponUser.isuse>style="background: rgb(242, 238, 238)"</#if>>
				<div class="pepper-l">
					<p class="pepper-l-num" <#if couponUser.timeout || couponUser.isuse>style="color:gray"</#if>>
						<i>¥</i><span>${(couponUser.couponValue)!0}</span><i>.00</i>
						(满${(couponUser.minAmount)!}元可用)
					</p>
					<#--<p class="pepper-l-con">适用商家：${(couponUser.sellerName)!}</p>-->
				</div>
				<div class="pepper-r">
					<span <#if couponUser.timeout || couponUser.isuse>style="color:gray;background: rgb(242, 238, 238)"</#if>>${(couponUser.couponName)!}</span>
					<div>
						<#if couponUser.useStartTime??>${couponUser.useStartTime?string("yyyy-MM-dd")}</#if>
						<p>~</p>
						<#if couponUser.useEndTime??>${couponUser.useEndTime?string("yyyy-MM-dd")}</#if>
					</div>
				</div>
				<div class="pepper-b clearfix">
					<div class="pb-con"></div>
					<#if couponUser.timeout>
					<div class="overdue-icon"></div>
					</#if>
					<#if couponUser.isuse>
					<div class="used-icon"></div>
					</#if>
					<div class="pb-border"></div>
				</div>
			</div>
		</div>
		</#list>
		<#else>
		<#assign hasCoupon = false>
		<div class="nocoupon">
			没有红包
		</div>
		</#if>	
	</div>
	</main>
<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
	<script>
		var domain = "${(domainUrlUtil.EJS_URL_RESOURCES)!}";
		var loadurl = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/coupon/list.html?rownum=${page.size}";
	</script>
	<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
	<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js"></script>
	<#if hasCoupon>
	<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/iscroll-probe.js"></script>
	<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/scrollload.js"></script>
	<#else>
	<script>
		$(".tips").remove();
	</script>
	</#if>
</body>
</html>