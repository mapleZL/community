<#if page??>
	<div class='mt10' style='height:30px;'>
	<div class='pagin fr'>
		<#if page.num != 1>
			<a class="prev-disabled" href="javascript:void(0)" onclick="filterPage(${page.first})">首页</a> 
			<a class="prev-disabled" href="javascript:void(0)" onclick="filterPage(${page.prev})">前一页</a>
		<#else>
			<a class="prev-disabled">首页</a>
			<a class="prev-disabled">前一页</a>
		</#if>
		
		<#list page.pageNumbers as pageNumber>
			<#if page.num != pageNumber?number>
				<a class="prev-disabled" href="javascript:void(0)" onclick="filterPage(${pageNumber})">${pageNumber!}</a> 
			<#else>
				<a class="active" href="javascript:void(0)">${pageNumber!}</a>
			</#if>
		</#list>
		
		<#if page.num != 0 && page.pageCount != 0 && page.num != page.pageCount>
			<a class="prev-disabled" href="javascript:void(0)" onclick="filterPage(${page.next})">后一页</a> 
			<a class="prev-disabled" href="javascript:void(0)" onclick="filterPage(${page.last})">末页</a>  
		<#else>
			<a>后一页</a>
			<a>末页</a>
		</#if>
		<span>共${page.pageCount}页</span>
	</div>
	</div>
</#if>

<script type="text/javascript">
	function filterPage(page) {
		var urlPath = "${(urlPath)!}";
		var urlPaths = urlPath.split("-");
		var url = "";
		for(var i=0; i<urlPaths.length; i++) {
		    if(i == 2) {
		    	url += page;
		    } else {
		    	url += urlPaths[i];
		    }
			if((i+1) != urlPaths.length) {
				url += "-";
			}
		}
		self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
	}
</script>