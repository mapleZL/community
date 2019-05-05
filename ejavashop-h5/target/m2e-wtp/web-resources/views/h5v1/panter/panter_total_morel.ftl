<#if sumParterSale ?? >
  <#if sumParterSale?size &gt; 0>
	<#if type ? exists && type == '1'>
		<#list sumParterSale as sumParterSale>
			<tr class = "rem_tr">
					<td data-th="月份"><#if year ?? && year ? exists>${year}年${sumParterSale.month}月<#else>${sumParterSale.month}</#if></td>
					<td data-th="客户数">${sumParterSale.bussineeNo}</td>
					<td data-th="交易额">${sumParterSale.saleMoney}</td>
					<td data-th="新增客户数">${sumParterSale.newBussineeNo}</td>
					<td data-th="新增交易额">${sumParterSale.newSaleMoney}</td>
			</tr>
		</#list>
	</#if>
	<#if type ? exists && type == '2'>
		<#list sumParterSale as sumParterSale>
			<tr class = "rem_tr">
					<td data-th="年份">${sumParterSale.years}</td>
					<td data-th="客户数">${sumParterSale.bussineeNo}</td>
					<td data-th="交易额">${sumParterSale.saleMoney}</td>
					<td data-th="新增客户数">${sumParterSale.newBussineeNo}</td>
					<td data-th="新增交易额">${sumParterSale.newSaleMoney}</td>
			</tr>
		</#list>
	</#if>
	<#else>	
		<tr class = "rem_tr"><td data-th="很抱歉">暂时没有统计数据</td></tr>
	</#if>
</#if>

<#if salesPersonVO ?? >
   <#if salesPersonVO?size &gt; 0>
	<#if type ? exists && type == '3'>
		<#list salesPersonVO as salesPersonVO>
			<tr class = "rem_tr">
				<td data-th="类型">
					<#if salesPersonVO.brandName ? exists && salesPersonVO.brandName !=''>
								${(salesPersonVO.brandName)!''}
					<#else>
								${(salesPersonVO.brandName1)!''}
					</#if>
				</td>
				<td data-th="(销售)客户数">${salesPersonVO.memberNo}</td>
				<td data-th="(销售)数量">${salesPersonVO.skuNo}</td>
				<td data-th="(销售)商品金额">${salesPersonVO.salesNo}</td>
				<td data-th="(退货)客户数">${salesPersonVO.returnMemberNo}</td>
				<td data-th="(退货)数量">${salesPersonVO.returnSkuNo}</td>
				<td data-th="(退货)商品金额">${salesPersonVO.returnSalesNo}</td>
				<td data-th="总数量">${salesPersonVO.no}</td>
				<td data-th="商品总金额">${salesPersonVO.sales}</td>
			</tr>
		</#list>
	</#if>
	<#else>
		<tr class = "rem_tr"><td data-th="很抱歉">暂时没有统计数据</td></tr>	
	</#if>
</#if>

<#if parterTuijian ??>
  <#if parterTuijian?size &gt; 0>
	<#if type ? exists && type == '4' || type == '5'>
		<#list parterTuijian as parterTuijian>
			<tr class = "rem_tr">
				<td data-th= "合伙人">${(parterTuijian.parterName)!''}</td>
				<td data-th= "区域名称">${parterTuijian.areaName}</td>
				<td data-th= "品牌袜销售额">${parterTuijian.brandSales}</td>
				<td data-th= "品牌袜提点">${parterTuijian.brandPercent}</td>
				<td data-th= "裸袜销售额">${parterTuijian.luowaSales}</td>
				<td data-th= "裸袜提点">${parterTuijian.luowaPercent}</td>
				<td data-th= "总销售额">${parterTuijian.salesSum}</td>
				<td data-th= "总提点">${parterTuijian.percentSum}</td>
				<td data-th= "开始时间">${(parterTuijian.startTime)!''}</td>
				<td data-th= "结束时间">${(parterTuijian.endTime)!''}</td>
			</tr>
		</#list>
	</#if>
	<#else>
		<tr class = "rem_tr"><td data-th="很抱歉">暂时没有统计数据</td></tr>	
	</#if>
</#if>


