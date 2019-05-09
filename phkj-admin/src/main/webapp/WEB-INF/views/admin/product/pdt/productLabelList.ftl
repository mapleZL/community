<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	var codeBox3;
	$(function() {
		<#noescape>
			//初始化需要的字典数据
	        codeBox3 = eval('(${initJSCodeContainer("PRODUCT_LABEL_STATE","PRODUCT_LABEL_MEMBER")})');
	    </#noescape>
	    
		$('#btn-search3').click(function(){
			$('#labeldataGrid').datagrid('reload',queryParamsHandler());
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
	
	function memberformat3(value,row,index){
		if(value == 0)
			return codeBox3["PRODUCT_LABEL_MEMBER"][value];
		else
			return row.memberName;
	}
	
	function stateformat3(value,row,index){
		return codeBox3["PRODUCT_LABEL_STATE"][value];
	}
	
	function gdSumit3(){
    	var selected = $('#labeldataGrid').datagrid('getSelections');
    	var selectedid="";
    	var selectedname1="";
    	for(var i=0;i<selected.length;i++){
    		selectedid += selected[i].id+",";
    		selectedname1 += selected[i].name+",";
    	}
    	selectedid = selectedid.substring(0,selectedid.lastIndexOf(","));
    	selectedname1 = selectedname1.substring(0,selectedname1.lastIndexOf(","));
    	$('#sku_other').val(selectedid);
    	$('#sku_other_name').val(selectedname1);
    	$('#labelDialog').dialog('close');
    }
	
</script>
<div id="labelDialog" class="easyui-dialog" title="标签管理列表"
	style="width: 1000px; height: 520px;overflow: hidden;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-brand3">
<div class="easyui-layout" data-options="fit:true" style="height: 500px;">
<div id="searchbar3" data-options="region:'north'"
	style="margin: 0 auto;height: 45px;" border="false">
	<div id="searchbox3" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm3" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">标签名称:</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${q_name!''}" />
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
	<table id="labeldataGrid" class="easyui-datagrid"
		data-options="rownumbers:false
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#labelgridTools'
						,striped:true
						,pagination:true
						,pageSize:20
						,fit:true
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th field="id" hidden="hidden"></th>
				<th field="name" width="80" align="center">标签名称</th>
				<th field="memberId" width="80" align="center" formatter="memberformat3">所属客户</th>
				<th field="state" width="80" align="center" formatter="stateformat3">状态</th>
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
		<a id="btn-search3"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
	</div>
</div>
</div>
</div>
<div id="dlg-buttons-brand3" style="text-align: right">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="gdSumit3()">确定</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel"
		onclick="javascript:$('#labelDialog').dialog('close')">取消</a>
</div>
