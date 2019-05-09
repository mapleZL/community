<input type="hidden" name="skupics" id="skupics">
<div id="normDiv" style="display: none">
<#if normList?? && normList?size &gt; 0>
    <#list normList as norm>
    <#assign i = 0>
 	<#assign isColor = false>
 	<#-- 记录最后一次normid和attrid-->
 	<#assign normindex = 0>
 	<#assign attrindex = 0>
 	<#--记录最大属性id-->
 	<#assign maxattrid = 0>
    <#if (norm.name)?contains('颜色')>
  	  <#assign isColor = true>
    </#if>
    <div class="fluidbox <#if isColor>normtype_color<#else>normtype_others</#if>">
        <p class="p12 p-item" style="width: 5%;" normname="${(norm.name)!''}" normid="${(norm.id)!''}">
            <label class="lab-item">${(norm.name)!''}: </label>
        </p>
        <#if norm.attrList?? && norm.attrList?size &gt; 0>
        <ul style="padding-left: 82px;float:left;">
            <#list norm.attrList as attr>
	            <li style="float: left;list-style: none;width: 100px;">
	            	<#assign normindex = norm_index>
	 				<#assign attrindex = attr_index>
	 				<#assign maxattrid = attr.id>
	                <label>
		                <input name="attr_${attr_index}" type="checkbox" 
		                	id="attr_${attr_index}" colortype="default"
		                	attrtype="${norm_index}" value="${(attr.id)!''}" 
		                	<#if edit??> disabled </#if> <#if (attr.checked)?? && (attr.checked)?string == "true">checked</#if>>${(attr.name)!''}
	                </label>
	            </li>
            </#list>
            <#if (norm.name)?contains('颜色') && edit??>
            	<#if customAttr??>
            		<#list customAttr as cusattr>
	            		<#if cusattr.typeAttr == 2>
		            		<li style="float: left;list-style: none;width: 100px;">
		            			<input value="${cusattr.attrId}" disabled checked type="checkbox" />
		            			${cusattr.name}
		            		</li>
	             	    </#if>
            		</#list>
            	</#if>
            </#if>
        </ul>
        <!-- 自定义颜色 -->
         <#if !edit?? && isColor>
      <ul style="padding-left: 137px;float: left;width: 80%;" id="custom-color-ul">
            <li style="float: left;list-style: none;width: 100px;display: inline-flex;">
                <input name="attr_${attrindex+1}" type="checkbox" 
                	colortype="custom"
                	id="attr_${attrindex+1}" attrindex="${attrindex+1}" 
                	attrtype="${normindex}" value="${maxattrid+1}" />
                <input name="cuscolr" placeholder="其它颜色" style="width: 60px;" type="text"/>
            </li>
      </ul>
         </#if>
        </#if>
    </div>
    
    <#if edit??>
    	<#list customAttr as attr>
    	<input name="skupic_${attr.attrId}" attrid="${attr.attrId}" value="${attr.image}" 
    		colortype="<#if (attr.typeAttr)==1>default<#else>custom</#if>" type="hidden">
    	</#list>
    </#if>
    
    <#-- 只有颜色规格时-->
    <#if isColor>
    <!-- sku图片start -->
<div class="fluidbox" name="skudiv">
	<table name="normTable" style="margin-left: 140px" border="0"
cellpadding="0" cellspacing="0" width="86%">
<tbody id="skuattrTbody">
	<tr
		style="background: #CCC; border: 1px solid #e2e1e1; font-size: 12px;">
<td style="padding: 6px;" width="10%">颜色分类</td>
<td style="padding: 6px;" width="35%">规格图片</td>
	<td>&nbsp;</td>
</tr>
<#if edit??>
<#list customAttr as attr>
<#assign colortype='custom'>
<#if (attr.typeAttr)==1>
	<#assign colortype='default'>
<#else>
	<#assign colortype='custom'>
</#if>
<tr style="border: 1px solid #e2e1e1" id="skuAttrTr_${attr.attrId}">
<td style="padding: 10px">${attr.name}
	<input name="skuattrid_${attr.attrId}" value="${attr.attrId}" type="hidden">
</td>
<td style="padding: 10px 0px;">
	<div name="skupicfile" action="" index="${waitShow_index!''}" 
		multiparam='{"url":"${currentBaseUrl}uploadSKUImage?attrid=${attr.attrId}&uploadtype=2&productId=${product.id}&colortype=${colortype}","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":5,"errMsg":"上传图片最多不能超过5张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"skuuploadCallback"}'>
                   </div>
</td>
<td>
	<img style="max-width: 40px;" 
		onerror="this.src='${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/nopic.jpg'"
		original-url="${domainUrlUtil.EJS_IMAGE_RESOURCES}${attr.image}"
		src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${attr.image}"/>
				</td>
			</tr>
			</#list>
		</#if>
		</tbody>
	</table>
</div>
</#if>
<!-- sku图片end -->
    </#list>
</#if>

    <div class="fluidbox" name="dyTable">
        <table width="86%" border="0" cellspacing="0" cellpadding="0" style="margin-left:140px">
            <tbody>
            <tr style="background:#CCC;border:1px solid #e2e1e1;font-size:12px;">
                <#if column?? && column?size &gt; 0>
                <#list column as col>
                    <td width="15%" style="padding:6px;">${col!''}</td>
                </#list>
                </#if>
                <td width="15%" style="padding:6px;">sku</td>
                <td width="15%" style="padding:6px;">库存</td>
                <td width="15%">PC价格</td>
                <td width="15%" style="padding:6px;">商品条码（EA）</td>
                <td width="15%" style="padding:6px;">商品条码（PL）</td>
                <td width="15%" style="padding:6px;">商品条码（CS）</td>
                <td width="15%" style="padding:6px;">是否虚拟组套（0:否，1：是）</td>
            <#--<td width="15%">mobile价格</td>-->
            </tr>
            <#if goods?? && goods?size &gt; 0>
            <#list goods as good>
            <tr style="border:1px solid #e2e1e1" name="normAttrTr">
                <#if good.normId1??>
                <td style="padding:6px">
                    ${(good.normName1)!''}<input name="normAttrId" type="hidden" value="${(good.normId1)!''}">
                </td>
                </#if>
                <#if good.normId2??>
                    <td style="padding:6px">${(good.normName2)!''}<input name="normAttrId" type="hidden" value="${(good.normId2)!''}">
                    </td>
                </#if>
                <td style="padding:6px;">
                    <input name="" type="text" id="inventory_details_count_" 
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:15px;" value="${(good.sku)!''}" class="styleSku">
                </td>
                <td>
                    <input name="" type="text" id="inventory_details_count_"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.stock)!''}" class="styleStock">
                </td>
                <td>
                    <input name="" type="text" id="inventory_details_pprice_${good_index}" onblur="change_mobile_proce(${good_index})"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.pcPrice)!''}" class="stylePrice">
                   <input name="" type="hidden" id="inventory_details_mprice_${good_index}"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.mobilePrice)!''}" class="stylePrice">
                </td>
                <td>
                    <input name="" type="text" id="inventory_details_barCode__${good_index}"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.barCode)!''}" class="barCode1">
                </td>
                <td>
                    <input name="" type="text" id="inventory_details_barCodePL__${good_index}"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.barCodePL)!''}" class="barCode">
                </td>
                <td>
                    <input name="" type="text" id="inventory_details_barCodeCS__${good_index}"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.barCodeCS)!''}" class="barCode">
                </td>
                <td>
                    <input name="" type="text" id="inventory_details_isVirtualBom__${good_index}"
                    	style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="${(good.isVirtualBom)!'0'}">
                </td>
                </tr>
                </#list>
                </#if>
                </tbody>
            </table>
        </div>

</div>

<div class="fluidbox sku">
    <p class="p12 p-item">
        <label class="lab-item"><font class="red">*</font>sku: </label>
        <input type="text" id="sku" name="sku" value="${(product.sku)!''}" class="txt w200 " onblur="valiate_SPU()"/>
    </p>
</div>
<script>
                
function change_mobile_proce(i){
	var price = $("#inventory_details_pprice_"+i).val();
	if(price!=null){
		$("#inventory_details_mprice_"+i).val(price);
	}
}
                </script>