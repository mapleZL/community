<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

	var statusBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("USE_PRE")})');
		</#noescape>

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/seller/pcindex/recommend/add";
		});
		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/seller/pcindex/recommend/edit?pcSellerRecommendId="+selected.id;
		});
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status == 1) {
            	$.messager.alert('提示','使用中的推荐类型不能删除');
                return;
            }

            $.messager.confirm('提示', '删除推荐类型将同时删除推荐数据，确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/pcindex/recommend/delete",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
		
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择启用推荐类型。');
	            return;
	        }
	        if(selected.status == 1) {
	        	$.messager.alert('提示','已经是启用状态，请勿重复操作');
	            return;
	        }
	        if(selected.status == 3) {
	        	$.messager.alert('提示','不能从停用状态直接修改成使用状态');
	            return;
	        }

	        $.messager.confirm('提示', '确定启用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/pcindex/recommend/up",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
		$("#a-gridPre").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择预使用推荐类型。');
	            return;
	        }
	        if(selected.status == 2) {
	        	$.messager.alert('提示','已经是预使用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定预使用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/pcindex/recommend/pre",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
	    $("#a-gridDown").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择停用推荐类型。');
	            return;
	        }
	        if(selected.status == 3) {
	        	$.messager.alert('提示','已经是停用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定停用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/pcindex/recommend/down",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/storepreview/${(SESSION_SELLER_USER.sellerId)!0}.html");
		});
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return statusBox["USE_PRE"][value];
	}

</script>



<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">PC端推荐类型列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">推荐标题：<input type="text" class="txt" id="q_title" name="q_title" value="${q_title!''}"/></p>
				<p class="p4 p-item">状态：<@cont.select id="q_status" value="${(q_status)!''}" codeDiv="USE_PRE" style="width:80px" /></p>
			</div>
		</form>
		</div>
	</div>
</div>


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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/seller/pcindex/recommend/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="title" width="100" align="left" halign="center">推荐标题</th>
	            <th field="orderNo" width="50" align="center" halign="center">排序号</th>
	            <th field="status" width="70" align="center" halign="center" formatter="statusFormat">使用状态</th>
	            <th field="updateUserName" width="100" align="center">最后修改人</th>
	            <th field="updateTime" width="150" align="center">最后修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		
		<@shiro.hasPermission name="/seller/pcindex/recommend/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/pcindex/recommend/edit">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/pcindex/recommend/delete">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/pcindex/recommend/up">
		<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/pcindex/recommend/pre">
		<a id="a-gridPre" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">预使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/pcindex/recommend/down">
		<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">停用</a>
		</@shiro.hasPermission>
		
		<a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a>
	</div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />