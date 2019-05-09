<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
	$(function (){
		$('#a-gridSearch').click(function (){
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});
	});
	
	function listParterSignNo(obj){
	       var grade = obj.options[obj.options.selectedIndex].value;
	       var ordersIdDivDom = $('#signNo');
			ordersIdDivDom.empty();
	       if(grade =='000'){
	    	   return;
	       }
		   $.ajax({
	 			type : "GET",
				url :  "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/getParterSignNo",
				data : {
					memberId:grade,
				}, 
				dataType : "json",
				success : function(data) {
					if (data.success) {
						var htmlStr = "";
						var ordersIdDivDom = $('#signNo');
						ordersIdDivDom.empty();
						 $.each(data.data, function(i, parter){
							htmlStr = htmlStr +"<option value="+parter.signNo+">"+parter.signNo+"</option>" ;
						 });
						 ordersIdDivDom.append(htmlStr);
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
	 		});
	   }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">会员列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">合伙人 :</label>
					<select name="q_parterSignMemberId" class="txt" onchange="listParterSignNo(this)">
					<option value="000">--请选择--</option>
					  <#if parterSignList ??>
					      <#list parterSignList as parterSign>
					           <option value="${parterSign.memberId}">${(parterSign.memberRealName)!''}</option>
					      </#list>
					  </#if>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">合同编号 :</label>
					<select name="q_signNo" class="txt" id="signNo">
					<option value="000">--请选择--</option>
					</select>
				</p>
			</div>
		</form>
		</div>
	</div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid" 
			data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:false
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/panerTotalDaysList'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="years" width="150" align="center" halign="center">日期</th>
				<th field="bussineeNo" width="150" align="center" halign="center">客户数</th>  
	            <th field="saleMoney" width="150" align="center" halign="center">交易额</th>  
	            <th field="newBussineeNo" width="150" align="center" halign="center">新增客户数</th>  
	            <th field="newSaleMoney" width="150" align="center" halign="center">新增交易额</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />