<script>
var trans_index = "${trans_index}";
var trans_city_type = "${trans_city_type}";
</script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/seller/allarea.js"></script>

<div class="area_box">
	<div class="area_box_title">
		<span class="area_box_title_left">选择区域<font style="color:red">(选择地区后请点击"确定"以返回)</font></span> <span
			class="area_box_title_right"><a href="javascript:void(0);"
			onclick="javascript:jQuery('.area_box').remove();">×</a></span>
	</div>
	<!--蓝色背景:area_bg_blue 白色背景:area_bg_white-->
	<#list allArea as pros>
	<div class="area_bg_white">
		<div class="area_before">
			<#-- <input name="province_${pros.id}" id="province_${pros.id}" type="checkbox"
				value="${pros.id}" /> -->
			<label style="padding-left: 3px;"
				for="province_${pros.id}">${(pros.regionName)!''}</label>
		</div>
		<div class="area_box_main">
			<ul>
				<#list pros.children as citys>
				<li><input name="city_${citys.id}"
					id="city_${citys.id}" city_name="${citys.regionName}" type="checkbox" value="${citys.id}" /> <span><label
						for="city_${citys.id}" style="float: left;">${(citys.regionName)!''}</label></span> 
					
					</li> 
				</#list>
			</ul>
		</div>
	</div>
	</#list>
	<div class="area_box_bottom">
		<input type="button" value="取消"
			onclick="jQuery('.area_box').remove();" /> <input type="button"
			value="确定" onclick="generic_area();" /> <input name="trans_index"
			type="hidden" id="trans_index" value="${trans_index}" /> <input
			name="trans_city_type" type="hidden" id="trans_city_type"
			value="${trans_city_type}" />
	</div>
</div>