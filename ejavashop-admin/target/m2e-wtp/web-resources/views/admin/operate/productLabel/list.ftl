<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productLabel"/>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	var codeBox;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("PRODUCT_LABEL_STATE","PRODUCT_LABEL_MEMBER")})');
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
	    
	    $("#sel_member").change(function(){
	    	if($(this).val()== '0'){
	    		$("#q_memberId").val(0);
	    		$("#members").hide();
	    		$("#membername").html('');
	    	} else if($(this).val()=='1'){
	    		$("#members").show();
	    	} else{
	    		$("#q_memberId").val('');
	    		$("#members").hide();
	    		$("#membername").html('');
	    	}
	    });
	});
	
	function selmembers(){
 		$('#memberDialog').dialog('open');
	}
	
	function memberCallBack(data) {
		$("#membername").html(data.name);
		$("#q_memberId").val(data.id);
	}
	
	function memberformat(value,row,index){
		if(value == 0)
			return codeBox["PRODUCT_LABEL_MEMBER"][value];
		else
			return row.memberName;
	}
	
	function stateformat(value,row,index){
		return codeBox["PRODUCT_LABEL_STATE"][value];
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		标签管理列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">标签名称:</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${queryMap['q_name']!}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">所属客户:</label> 
						
						<@cont.select id="sel_member"
								codeDiv="PRODUCT_LABEL_MEMBER"
								class="txt"/>
						
						<span id="membername"></span>
						<a href="javascript:;" onclick="selmembers()" 
							style="color:blue;display: none"
							id="members">点击选择</a>
								
						<input type="hidden" name="q_memberId" id="q_memberId">
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
						,toolbar:'#labelgridTools'
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
				<th field="name" width="80" align="center">标签名称</th>
				<th field="memberId" width="80" align="center" formatter="memberformat">所属客户</th>
				<th field="state" width="80" align="center" formatter="stateformat">状态</th>
				<th field="sku" width="80" align="center">sku</th>
				<th field="norms" width="80" align="center">规格</th>
				<th field="netWeight" width="80" align="center">净量</th>
				<th field="grossWeight" width="80" align="center">毛重</th>
				<th field="costPrice" width="80" align="center">成本价</th>
				<th field="marketPrice" width="80" align="center">市场价</th>
				<th field="stock" width="80" align="center">库存</th>
				<th field="brand" width="80" align="center">品牌</th>
				<th field="describe" width="80" align="center">说明</th>
			</tr>
		</thead>
	</table>

	<div id="labelgridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
			
		<@shiro.hasPermission name="/admin/operate/productLabel/edit">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/productLabel/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
	</div>
	
</div>

<#include "memberdialog.ftl" />
<#include "/admin/commons/_detailfooter.ftl" />
