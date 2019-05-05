<#assign url4page="${url4page}" />
<#assign url4page=(url4page?index_of("?")==-1)?string((url4page+"?num="),(url4page+"&num="))>

<#if page??>
	<div class='mt10' style='height:30px;'>
	<div class='pagin fr'>
		<#if page.num != 1>
			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${url4page}${page.first}">首页</a> 
			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${url4page}${page.prev}">前一页</a>
		<#else>
			
		</#if>
		
		<#list page.pageNumbers as pageNumber>
			<#if page.num != pageNumber?number>
				<a class="prev-disabled" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${url4page}${pageNumber}">${pageNumber!}</a> 
			<#else>
				<a class="active" href="javascript:void(0)">${pageNumber!}</a>
			</#if>
		</#list>
		
		<#if page.num != 0 && page.pageCount != 0 && page.num != page.pageCount>
			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${url4page}${page.next}">后一页</a> 
			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${url4page}${page.last}">末页</a>  
		<#else>
			<a class="prev-disabled">后一页</a>
			<a class="prev-disabled">末页</a>
		</#if>
		<span>共${page.pageCount}页</span>
	</div>
	</div>
</#if>