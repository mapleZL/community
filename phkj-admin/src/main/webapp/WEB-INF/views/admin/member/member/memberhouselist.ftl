<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("MEMBER_PROPERY_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		// 审核通过
		$('#btn_pass').click(function () {
			debugger;
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经审核通过的数据
			if(selected.status != 1){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定审核通过该条申请吗', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/house/passInfo",
						dataType: "json",
					    data: "id=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
		$("#newstypeWin").window({
			width : 750,
			height : 420,
			title : "房屋图片",
			closed : true,
			shadow : false,
			modal : true,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
		
		// 审核不通过
		$('#btn_noPass').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经审核通过的数据
			if(selected.status != 1){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定驳回该条申请吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/house/noPassInfo",
						dataType: "json",
					    data: "id=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
	});

	function getState(value, row, index) {
		var box = codeBox["MEMBER_PROPERY_STATE"][value];
		return box;
	}
	
	function imageFormat(value, row, index) {
		return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
				+ value + "'>点击查看</a>";
	}
	
	function showimg(href) {
		if (href && href != 'null') {
			var imgs = JSON.parse(href);
			var html = '';
			for (var i = 0; i < imgs.length; i++) {
				html += "<img src='" + imgs[i] + "' >"
			}
			$("#newstypeTree").html(html);
			$("#newstypeWin").window('open');
		} else {
			$.messager.alert('提示','该条记录暂无图片。');
			return;
		}
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		房屋信息列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">业主名 :</label> <input type="text"
							class="txt" id="q_member_name" name="q_member_name" value="${q_member_name!''}" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/house/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="name" width="30" align="center">业主姓名</th>
				<th field="region" width="60" align="center">区域</th>
				<th field="street" width="60" align="center">街道</th>
				<th field="community" width="60" align="center">社区</th>
				<th field="village" width="60" align="center">小区</th>
				<th field="building" width="60" align="center">楼幢</th>
				<th field="unit" width="60" align="center">单元</th>
				<th field="room" width="60" align="center">室</th>
				<th field="identityInformation" width="60" align="center">身份信息</th>
				<th field="idNumber" width="60" align="center">身份证号</th>
				<th field="phone" width="60" align="center">联系电话</th>
				<th field="img" width="70" align="center" formatter="imageFormat">图片</th>
				<th field="status" width="70" align="center" formatter="getState">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
	<@shiro.hasPermission name="/admin/seller/manage/unfreeze">
		<a id="btn_pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage/freeze">
		<a id="btn_noPass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">审核不通过</a>
		</@shiro.hasPermission>
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	
</div>
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
