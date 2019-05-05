<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/userindex.css"/>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/My97DatePicker/WdatePicker.js'></script>
<style>

.wrapper_main strong {
    font-size: 16px;
    font-family: '\5fae\8f6f\96c5\9ed1';
}

</style>
<script>
</script>

		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>分类销售汇总</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
			    <div>
			    <form class="form-inline" role="form" action ="/member/categorySales.html" method="post" >
			    		<div class="form-group">
							<label for="name" style ="width:100px;font-size: 16px">合同编号：</label>
							<select class="form-control" style= "width:220px;display: initial;margin-left:-20px;" name="signNo">
									<option >--请选择--</option>
								<#list parterSignList as parterSign> 
							        <option value="${parterSign.signNo}"  <#if signNo ? exists && signNo =parterSign.signNo > selected="selected" </#if>>${parterSign.signNo}</option>
								</#list>
							</select>
						</div>
						<label for="name" style ="width:100px;font-size: 16px;margin-left:30px;">日期：</label>
			    		<input type='text' name='startTime' id="startTime"  style="margin-left:-50px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								   class='form-control ' value = "${startTime}">
									~
						<input type='text' name='endTime' id="endTime" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"
					    class='form-control'  value = "${endTime}">
						<input type="submit" name="querySales1" id="querySales1" value="查 询"  class="btn btn-default">
					</form>
			    </div>
				<table class="table_1" id="refushtable" cellspacing="0" cellpadding="0" border="1" style="margin-top: 10px;">
					<tbody>
						<tr>
							<th rowspan="2">类型</th>
							
							<th colspan="3">销售情况</th>
							<th colspan="3">退货情况</th>
							<th colspan="2">销售总额</th>
						</tr>
						<tr>
							<th >客户数</th>
							<th>数量</th>
							<th>商品金额</th>
							
							<th>客户数</th>
							<th>数量</th>
							<th>商品金额</th>
							
							<th>数量</th>
							<th>商品金额</th>
						</tr>
						<#if salesPersonVO ??>
							<#list salesPersonVO as salesPersonVO>
								<tr>
									<td>
										<#if salesPersonVO.brandName ? exists && salesPersonVO.brandName !=''>
													${(salesPersonVO.brandName)!''}
										<#else>
													${(salesPersonVO.brandName1)!''}
										</#if>
									</td>
									<td>${salesPersonVO.memberNo}</td>
									<td>${salesPersonVO.skuNo}</td>
									<td>${salesPersonVO.salesNo}</td>
									<td>${salesPersonVO.returnMemberNo}</td>
									<td>${salesPersonVO.returnSkuNo}</td>
									<td>${salesPersonVO.returnSalesNo}</td>
									<td>${salesPersonVO.no}</td>
									<td>${salesPersonVO.sales}</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				//控制左侧菜单选中
				$("#balance").addClass("currnet_page");
			});
		</script>

<#include "/front/commons/_endbig.ftl" />