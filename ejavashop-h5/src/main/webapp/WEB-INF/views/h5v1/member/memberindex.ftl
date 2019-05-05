<#include "/h5v1/commons/_head.ftl" />
<body>
<!--头部-->
<header>
	<dl class="overflow admin">
    	<dt class="fl"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/photo2.png"></dt>
        <dd class="fl">
        	<p class="em11">${(member.name)!""}</p>
            <span class="welcome">欢迎你</span>
        </dd>
    </dl>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<section>
    <div class="ico overflow admin_box tp2">
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1" class="line1">
            <i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon9.png"></i>
            <em>待付款</em>
            <sup>${toBepaidOrders}</sup>
        </a>
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=4">
            <i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon10.png"></i>
            <em>待收货</em>
            <sup>${toBeReceivedOrders}</sup>
        </a>
    </div>
	<ul class="admin_item mt10 ico">
    	<li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/member/order.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a1.png"></i>订单中心</a>
        </li>
        <li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a2.png"></i>快速下单</a>
        </li>
    </ul>
    <ul class="admin_item mt10 ico">
    	<li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/collect.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a3.png"></i>我的收藏</a>
        </li>
        <li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/viewed.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a4.png"></i>浏览记录</a>
        </li>
    </ul>
    <ul class="admin_item mt10 ico">
    	<li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/balance.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a5.png"></i>余额</a>
        </li>
        <li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/info.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a6.png"></i>个人资料</a>
        </li>
        <li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/address.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a7.png"></i>收货地址</a>
        </li>
        <li>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/editpassword.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a8.png"></i>修改账号密码</a>
        </li>
        <#if Session.is_parter_sign ?? && Session.is_parter_sign =='true'>
	        <li>
	        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panterIndex.html"><i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/a1.png"></i>合伙人报表</a>
	        </li>
        </#if>
    </ul>
    <div class="ico admins" >
    	<a class="stop1" style="background-color:#FF7E18;">退出登录</a>
    </div>

	
</section>
<!--内容end-->
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/index.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
