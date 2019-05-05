<#include "/h5v1/commons/_head.ftl" />

<style>
.am-header .am-header-title{margin:0 10%}
</style>
</head>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">
       <input type="text" class="em3 text" placeholder="请输入关键词">
  </h1>
   <div class="am-header-right am-header-nav">
      <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/search-index.html" class="" style="font-size:1.4rem; color:#fff">搜索</a>
  </div>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
  	<div class="da_box">
    	<div class="da_img">
        	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_error.png">
        </div>
        <#if info?? >
	        <p>&nbsp;</p>
	        <p>${(info)!''}</p>
        <#else>
        	<p>没有找到您想要访问的页面</p>
        </#if>
        <#if backUrl?? >
        	<a href="${(backUrl)!''}" class="sub_btn">返回</a>
        <#else>
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="sub_btn">返回首页</a>
        </#if>
    </div>
    
</article>
<!--内容end-->
	<#include "/h5v1/commons/_nav.ftl" />
	<#include "/h5v1/commons/_footer.ftl" />
	<#include "/h5v1/commons/_statistic.ftl" />

</body>
</html>