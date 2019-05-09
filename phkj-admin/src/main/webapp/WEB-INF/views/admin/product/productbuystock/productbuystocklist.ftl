<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/productbuystock"/>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	var codeBox;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("USE_YN")})');
	    </#noescape>

		$("#a-gridEdit").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			location.href = currentBaseUrl + "/edit?id="+selected.id;
		});
		
		$("#a-gridEable").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			if(selected.state == 1){
				$.messager.alert('提示', '该规则已被启用');
				return;
			}
			$.ajax({
				url:currentBaseUrl+'/handler?type=1&id='+selected.id,
				type:'post',
				success:function(e){
					var msg = e.success == true?"操作成功":e.message;
					$.messager.show({
						title : '提示',
						msg : msg,
						showType : 'show'
					});
					$('#dataGrid').datagrid('reload');
				}
			});
		});
		
		$("#a-gridDisable").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行');
				return;
			}
			if(selected.state == 0){
				$.messager.alert('提示', '该规则已被停用');
				return;
			}
			$.messager.confirm('确认','确定停用该设定吗，停用后，该规则将不再生效？', function(r) {
				if (r) {
					$.ajax({
						url:currentBaseUrl+'/handler?type=0&id='+selected.id,
						type:'post',
						success:function(e){
							var msg = e.success == true?"操作成功":e.message;
							$.messager.show({
								title : '提示',
								msg : msg,
								showType : 'show'
							});
							$('#dataGrid').datagrid('reload');
						}
					});
				}
			});
		});
		
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
	    });
		   
	});
	
	function stateformt(value,row,index){
		return codeBox["USE_YN"][value];
	}
	
	function normfmt(value,row,index){
		return (Number(value)*100)+"%";		
	}
	
	function tongfmt(value,row,index){
		return (Number(value)*100)+"%";		
	}
	
	function yinfmt(value,row,index){
		return (Number(value)*100)+"%";		
	}
	
	function jinfmt(value,row,index){
		return (Number(value)*100)+"%";		
	}
	
	function zuanfmt(value,row,index){
		return (Number(value)*100)+"%";		
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		库存销售限制列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">SKU :</label> <input type="text"
							class="txt" id="q_sku" name="q_sku" />
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
				<th field="productName" width="120" align="center">商品</th>
				<th field="normName" width="120" align="center">规格</th>
				<th field="sku" width="80" align="center">sku</th>
				<th field="stock" width="80" align="center">限制阈值</th>
				<th field="state" width="60" align="center" formatter="stateformt">状态</th>
				<th field="grade1" width="80" align="center" formatter="normfmt">注册会员</th>
				<th field="grade2" width="80" align="center" formatter="tongfmt">铜牌会员</th>
				<th field="grade3" width="80" align="center" formatter="yinfmt">银牌会员</th>
				<th field="grade4" width="80" align="center" formatter="jinfmt">金牌会员</th>
				<th field="grade5" width="80" align="center" formatter="zuanfmt">钻石会员</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        <a id="a-gridEable" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-accept" plain="true">启用</a>
        <a id="a-gridDisable" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">停用</a>
	</div>
	
</div>

<#include "/admin/commons/_detailfooter.ftl" />
