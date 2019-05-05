<#include "/h5/commons/_head.ftl" />
<body >
   
    <div style="height:80%;" class="flex flex-pack-center flex-align-center">
		<div class="text-center"  >
		    <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/404.png"  width="150"><br>
		    对不起，您访问的页面不存在！请返回
		    <p class="mar_top">
			     <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="a_btn">首页</a>
			     <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html" class="a_btn">用户中心</a>
		    </p>
	    </div>

	</div>

	<#include "/h5/commons/_footer.ftl" />
</body>
</html>