<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productPackage"/>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	var codeBox;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("PRO_PACKAGE_STATE","PRO_PACKAGE_TYPE","PRO_PACKAGE_UNIT")})');
	    </#noescape>
	    
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
	    });
		
		$("#a-gridAdd").click(function(){
		    	location.href="${currentBaseUrl}/edit";
	    });
	    
	    $("#a-gridEdit").click(function(){
	    	var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
	    	location.href="${currentBaseUrl}/edit?id="+selected.id;
	    });
	});
	
	function stateformat(value,row,index){
		return codeBox["PRO_PACKAGE_STATE"][value];
	}
	
	function unitformat(value,row,index){
		return codeBox["PRO_PACKAGE_UNIT"][value];
	}
	
	function typeformat(value,row,index){
		return codeBox["PRO_PACKAGE_TYPE"][value];
	}
	
	function otherformat(value,row,index){
		return value == 1?'是':'否';
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		套餐设定列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">套餐名 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${queryMap['q_name']!}" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:false
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
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
				<th field="id" hidden="hidden"></th>
				<th field="name" width="100" align="center">套餐名称</th>
				<th field="price" width="60" align="center">套餐价格</th>
				<th field="state" width="30" align="center" formatter="stateformat">状态</th>
				<th field="packingType" width="50" align="center" formatter="typeformat">包装方式</th>
				<th field="unitType" width="50" align="center" formatter="unitformat">包装单位</th>
				<th field="isHook" width="30" align="center" formatter="otherformat">钩子</th>
				<th field="isGlue" width="30" align="center" formatter="otherformat">不干胶</th>
				<th field="isLiner" width="30" align="center" formatter="otherformat">衬板</th>
				<th field="isBag" width="30" align="center" formatter="otherformat">中包袋</th>
				<th field="isLabel" width="30" align="center" formatter="otherformat">防伪标</th>
				<th field="isGirdle" width="30" align="center" formatter="otherformat">腰封</th>
				<th field="describe" width="90" align="center">描述</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/operate/productPackage/edit">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/productPackage/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
	</div>
	
</div>

<#include "/admin/commons/_detailfooter.ftl" />