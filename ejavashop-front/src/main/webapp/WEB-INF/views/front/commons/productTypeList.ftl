

	<ul class='dd-inner'>
	<#if typeList??> 
		<#list typeList as type>
			<li class='odd' data-index='${type_index+1}'>
				<h3><a href="javascript:void(0)"><i></i>${type.name!""}</a></h3>
				<i>&gt;</i>
			</li>
		</#list> 
	</#if>
	</ul>
	<div class='dorpdown-layer'>
		<!-- 一级分类 -->
		<#list typeList as type>
		<div class='item-sub' id='index${type_index+1}'>
			<div class='subitems'>
				<!-- 二级分类  -->    
				<dl class='fore-dl' style="overflow-y:auto;overflow-x:hidden;">
					<#list type.childList as type2> 
					<dd>
						<#if type_index == 0>
						<a href='0-1-0-0-0-0-${(type2.id)!}-0-0-0.html'><i>${type2.name!""}</i></a>
						<#else>
						<a href='0-1-0-0-0-0-0-0-0-0-${(type2.id)!}_${(type2_index)!}.html'><i>${type2.name!""}</i></a>
						</#if>
					</dd>
					</#list>
				</dl>
				<!-- 二级分类  end -->
			</div>
		</div>
		</#list> 
		<!-- 一级分类 end -->
	</div>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>

