<footer class="text-center" style="padding: 0;margin: 0;">
<#--  不要显示  体现app风格类型
	<#if memberSession?? && memberSession.member??>
		<input type="hidden" name="ordersCoount" id="ordersCount" value="${(memberSession.status)!}">
		<div class="ft_top mar-bt flex" id="loginFootDiv">
			<div class="flex-1"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html" class="block">${(memberSession.member.name)!''}</a></div>
			<div class="flex-1"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="block">返回首页</a></div>
			<div class="flex-1"><a href="#" class="block">返回顶部</a></div>
		</div>
	<#else>
		<div class="ft_top mar-bt flex" id="loginFootDiv" style="border-top: #ced0d1 solid .1rem;margin-bottom: 0;">
			<div class="flex-1">
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html" class="block" style="color: #686868;">登录</a>
			</div>
			<div class="flex-1">
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html" class="block" style="color: #686868;">注册</a>
			</div>
		</div>
	</#if>
	<div style="border-top: #ced0d1 solid .1rem;height: 4.5rem;
    display: -webkit-box;
    -webkit-box-pack: center;
    -webkit-box-align: center;color: #686868;">
	    Copyright©2016 小麦（浙江）网络科技有限公司<br />
	    浙ICP备16019581号
	</div>
	-->
	<!-- 下面渲染底部导航按钮 -->
	<div class="h70"></div>
	<ul class="bottom_nav w tc">
        <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/productTypeList.html">商品分类</a></li>
        <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html">进货单<#if memberCartCount?? && memberCartCount!=0><span class="fa fa-circle" style="color: red;position: relative;top: -26px;"></span><#else></#if></a></li>
        <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html"></a></li>
        <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html">我的订单</a></li>
        <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html">会员中心</li>
	</ul>
</footer>
<!-- footer -->

<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.hDialog.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/wallet.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.lazyload.min.js"></script>
<!-- <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/checkedCss.js"></script> -->