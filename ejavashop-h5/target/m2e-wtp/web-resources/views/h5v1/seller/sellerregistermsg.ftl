<#include "/h5v1/commons/_head.ftl" />

<style>
.am-header .am-header-title{margin:0 10%}
</style>
</head>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:history.go(-1);" class="">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">注册成功</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
  	<div class="da_box">
    	<div class="da_img" >
    		<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_success.png">
        </div>
        <p></p>
        <p>${(message)!""}</p>
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="a_btn">去逛逛</a>
    </div>
    
</article>
<!--内容end-->
	<#include "/h5v1/commons/_nav.ftl" />
	<#include "/h5v1/commons/_footer.ftl" />
	<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>