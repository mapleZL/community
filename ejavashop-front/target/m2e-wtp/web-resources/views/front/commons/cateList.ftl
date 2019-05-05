
<div class=''>
	<#if cateList??> 
		<#list cateList as cate1>
			<#list cate1.childs as cate2>
			<div class='list-cashcade forel'>
				<span>
					<h3>
						<!--${cate1.name}  -->
						${cate2.name}
					</h3> <s></s>
				</span>
				<div class='i-mc' style='top: 4px;width: 200px;'>
					<div class='subitem'>
						<#list cate2.childs as cate3> 
						<dl class='fore1'>
							<!--<dt>
								<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/list/${cate2.id!0}.html' 
									target="_blank">${cate2.name }</a>
							</dt>  -->
							<dd>
									<em>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${cate3.id!0}.html' 
											target="_blank">${cate3.name }</a>
									</em>
							</dd>
						</dl>
						</#list>
					</div>
				</div>
			</div>
			</#list>
		</#list> 
	</#if>
</div>


<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>

