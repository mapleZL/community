<style>
	.mb20 ul li:not(.curp){
		cursor: pointer;
	}
</style>

<div class='nch-article-con'>
	<div class='title-bar'>
		<h3>${typename!}</h3>
	</div>
	<ul class='nch-article-list'>
		<#list newslist as news>
			<li><i></i> 
				<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index/article.html?id=${news.id!}'>
					${news.title!}
				</a> 
				<time>${news.createTimeStr!}</time>
			</li>
		</#list>
	</ul>
</div>
<div class='tc mb20'>
	<div class='article-pagination'>
		<ul>
			<#if page.num != 1>
			  <li onclick="typenews(5,'${page.prev}','${typeid}')"><span>上一页</span></li>
			<#else>
			   <li><span>上一页</span></li>
		    </#if>
			
			<li class="curp"><span class='currentpage'>${page.num!}</span></li>
			
			 <#if page.num != 0 && page.pageCount != 0 && page.num != page.pageCount>
				<li onclick="typenews(5,'${page.next}','${typeid}')"><span>下一页</span></li>
			<#else>
				<li><span>下一页</span></li>
		    </#if>
		    
		</ul>
	</div>
</div>