<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/cate/"/>

<script language="javascript">
	var codeBox;
	$(function() {

	     $("#btn-audit").click(function(){
             var selectedCode = $('#dataGrid').datagrid('getSelected');
             if(!selectedCode){
                 $.messager.alert('提示','请选择操作行。');
                 return;
             }
             var path = selectedCode.path;
             if(path.indexOf('/') == -1 || path.split('/').length != 3){
                 $.messager.alert('提示','只能选择三级分类进行提交。');
                 return;
             }

             var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
             $.messager.confirm('Confirm', '确定提交审核吗？', function(r){
                 if (r){
                     $.messager.progress({text:"提交中..."});
                     $.ajax({
                         type:"get",
                         url: "${currentBaseUrl}commit",
                         dataType: "json",
                         data: "cateId="+selectedCode.id+"&"+getCSRFTokenParam(),
                         cache:false,
                         success:function(data, textStatus){
                             refrushCSRFToken(data.csrfToken);
                             $.messager.progress('close');
                             if (data.success) {
                                 $.messager.alert('提示','提交申请成功。');
                             }else{
                                 $.messager.alert('提示', data.message);
                             }

                             $('#dataGrid').datagrid('reload',queryParamsHandler());
                         }
                     });
                 }
             });
         });

    })

	function afterDataGridLoaded() {
	}

	function dataGridLoadFail() {
		alert("服务器异常");
	}

	function dataGridLoadSuccess(row, data) {

	}

	function cc(row) {
	}

	function bl(row, param) {
		if (!row) {
			param.id = 0;
		}
	}

	function checkbox(name,rowData){
		if(rowData.checked=="true"){
			return name;
		} else{
			return "<input type='checkbox' id='ckx_"+rowData.id+"' value='"+
				rowData.id+"' onclick='changeStatus(this)'/>" + name;
		}
	}
	
	function changeStatus(o){
		if($(o).attr('checked')=='checked'){
			$(o).removeAttr('checked');
		} else{
			$(o).attr('checked','checked');
		}
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">平台商品分类列表</h2>
	<div id="searchbox" class="head-seachbox" style="display: none;">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">分类名称 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${q_name!''}" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-treegrid"
		data-options="rownumbers:false
						,singleSelect:true
						,animate:true
						,autoRowHeight:true
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:false
						,pageSize:'2'
						,fit:true
    					,url:'${currentBaseUrl}getByPid'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,onLoadError:dataGridLoadFail
						,onBeforeExpand:cc
						,treeField:'name'
						,idField:'id'
    					,method:'get'
						,onBeforeLoad:bl">
		<thead>
			<tr>
				<th field="name" width="200" align="left">分类名称</th>
				<th field="scaling" width="60" align="center">分佣比例(%)</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/seller/product/cate/commit">
		<a id="btn-audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">提交审核</a>
		</@shiro.hasPermission>
	</div>
</div>
		<#include "/seller/commons/_detailfooter.ftl" />