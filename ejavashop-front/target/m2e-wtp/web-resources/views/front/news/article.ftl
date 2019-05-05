 <#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet"
	href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/article.css'>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/article.js"></script>

<script>
	var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
	var content = '';
	var preURL = '';
	var nextURL = '';
	$(function() {
		<#noescape>
		content = "${news.content}";
		preURL = "${news.preURL}";
		nextURL = "${news.nextURL}";
		</#noescape>

		$(".wp1200 .container").removeClass("w");

		$(".wp1200 .footer").removeClass("w");
		$(".wp1200 .wraper").removeClass("w");

		$("#cont_").html(content);

		$("#pre").attr("href", preURL);
		$("#next").attr("href", nextURL);
	});
</script>

<div class='container'>
	<div class='subheader pb10'>
		<strong> <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
		</strong> <span>></span> <strong> <a href=''>售后服务</a>
		</strong> <span> ><span class='arrow'>文章内容</span>
		</span>
	</div>
</div>
<div class='container'>
	<div class='article-left'>
		<div class='nch-module nch-module-style01'>
			<div class='title'>
				<h3>文章分类</h3>
			</div>
			<div class='article-content'>
				<ul class='nch-sidebar-article-class'>
				<#if newsTypes??>
					<#list newsTypes as newstype>
						<li>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/news/type_${newstype.id!}.html'>
								${newstype.name!} 
							</a>
						</li>
					</#list>
				</#if>
				</ul>
			</div>
		</div>
		<div class='nch-module'>
			<div class='title'>
				<h3>最新文章</h3>
			</div>
			<div class='article-content'>
				<ul class='nch-sidebar-article-list'>
				 <#if lastedNews??>
					<#list lastedNews as ln>
					<li><i></i> <a
						href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/news/${ln.id!}.html'>
							${ln.title!} </a></li> 
					</#list>
				</#if>
				</ul>
			</div>
		</div>
	</div>
	
	<div class='article-right'>
		<div class='nch-article-con'>
			<h1>${news.title!}</h1>
			<h2>${news.createTimeStr}</h2>
			<div class='default' id="cont_"></div>
			<div class='more_article'>
			<#--
				<span class='pre-a'> 上一篇： <a href='' id="pre">${news.preTitle!}</a>
					<time>${news.preTime!}</time>
				</span> <span style='float: right'> 下一篇： <a href='' id="next">${news.nextTitle!}</a>
					<time>${news.nextTime!}</time>
				</span>-->
			</div>
		</div>
	</div>
	
</div>
<#include "/front/commons/_endbig.ftl" />
