<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${(domainUrlUtil.EJS_URL_RESOURCES)!}/seller/member/productcomments"/>

<script language="javascript">
	var statusBox;

	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("PRODUCT_COMM_STATE")})');
		</#noescape>
		// 查询按钮
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
	});

	function statusFormat(value,row,index){
		return statusBox["PRODUCT_COMM_STATE"][value];
	}
	

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">商品评论列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">用户名 :</label>
					<input type="text" class="txt" id="q_userName" name="q_userName" value="${q_userName!''}"/>
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
						,fitColumns:true
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="userName" width="100" align="left" halign="center">用户名</th>  
	            <th field="sellerName" width="80" align="left" halign="center" >商家</th>  
	            <th field="productName" width="50" align="left" halign="center">产品名称</th> 
	            <th field="orderSn" width="130" align="left" halign="center">订单编号</th> 
	            <th field="content" width="180" align="left" halign="center">评价内容</th>  
	            <th field="createTime" width="150" align="left" halign="center">评价时间</th> 
	            <th field="state" width="100" align="left" halign="center" formatter="statusFormat">状态</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>


<#include "/seller/commons/_detailfooter.ftl" />