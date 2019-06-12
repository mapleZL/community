<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" 
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("REPAIRE_RECORD_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		// 接收报修
		$('#btn_accept').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经启动
			if(selected.sts != 2){
				$.messager.alert('提示','请确定该条维修记录为已下发。');
				return;
			}
	 		$.messager.confirm('确认', '确认接收该条维修信息', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/property/repair/changeStatus",
						dataType: "json",
					    data: "id=" + selected.id +"&sts=4",
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
	 		
	 		$("#newstypeWin").window({
				width : 750,
				height : 420,
				title : "报修图片",
				closed : true,
				shadow : false,
				modal : true,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		// 确认完成
		$('#btn_sure').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经审核通过的数据
			if(selected.status == 4){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定禁用该维修人员？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/property/repair/changeStatus",
						dataType: "json",
					    data: "id=" + selected.id + "&sts=5",
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

	function getState(value, row, index) {
		var box = codeBox["REPAIRE_RECORD_STATE"][value];
		return box;
	}
</script>


<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		支付列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">维修详情 :</label> <input type="text"
							class="txt" id="q_detail" name="q_detail" value="${q_detail!''}" />
					</p>
					<!-- <input type="hidden" id="q_sts" name="q_sts" value="2"/> -->
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
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/system/getAllPayment'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="paymentType" width="90" align="center">支付类型</th>
				<th field="paymentCode" width="90" align="center">支付账号</th>
				<th field="qrCode" width="90" align="center">二维码</th>
				<th field="sts" width="90" align="center">状态</th>
				<th field="createUserName" width="70" align="center">创建人</th>
				<th field="modifyUserName" width="70" align="center">修改人</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/property/repair/delivery">
		<a id="btn_accept" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">确认接收</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/property/repair/nopass">
		<a id="btn_sure" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">确认完成</a>
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
