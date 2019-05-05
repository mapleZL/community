<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/userindex.css"/>
<style>

.wrapper_main strong {
    font-size: 16px;
    font-family: '\5fae\8f6f\96c5\9ed1';
}

</style>
<script>
   $(function (){
	   $('#querySales').click(function(){
		   var years = $('#years').val();
		   window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotalMonth.html?year="+years;
	   });
   })
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
					<a href='javascript:void(0)'><#if type ? exists && type == 1>每日销售汇总<#else>年度销售汇总</#if></a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<form role="form" action="/member/panerTotalYears.html" method="post" >
						<div class="form-group">
							<label for="name" style ="width:100px;font-size: 16px">合同编号：</label>
							<select class="form-control" style= "width:220px;display: initial;margin-left:-20px;" name="signNo">
									<option >--请选择--</option>
								<#list parterSignList as parterSign> 
							        <option value="${parterSign.signNo}"  <#if signNo  ? exists && signNo =parterSign.signNo > selected="selected" </#if>>${parterSign.signNo}</option>
								</#list>
							</select>
							<input type="submit" class="btn btn-default" style="display: initial;margin-top: -5px;margin-left: 10px;" value="查 询"/>
						</div>
					</form>
				<table class="table_1" id="refushtable" cellspacing="0" cellpadding="0" border="1">
					<tbody>
						<tr>
							<th><#if type ? exists && type == 1>日期<#else>年份</#if></th>
							<th>客户数</th>
							<th>交易额</th>
							<th>新增客户数</th>
							<th>新增交易额</th>
						</tr>
						<#if sumParterSale ??>
							<#list sumParterSale as sumParterSale>
								<tr>
									<#if sumParterSale.years ? exists>
									<td>${sumParterSale.years}</td>
									<#else>
									<td>${sumParterSale.month}</td>
									</#if>
									<td>${sumParterSale.bussineeNo}</td>
									<td>${sumParterSale.saleMoney}</td>
									<td>${sumParterSale.newBussineeNo}</td>
									<td>${sumParterSale.newSaleMoney}</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
			<#if type ? exists && type == 1>
				<#include "/front/commons/_pagination.ftl" />
			</#if>
		</div>
		<script type="text/javascript">
			$(function(){
				//控制左侧菜单选中
				$("#balance").addClass("currnet_page");
			});
		</script>

<#include "/front/commons/_endbig.ftl" />