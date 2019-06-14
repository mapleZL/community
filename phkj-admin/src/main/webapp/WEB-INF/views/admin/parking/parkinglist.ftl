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

        // 添加
        $('#btn_add').click(function() {
            $("#devWin").window({
                width :700,
                height : 500,
                href : '${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/system/add',
                title : "添加支付类型",
                closed : true,
                shadow : false,
                modal : true,
                collapsible : false,
                minimizable : false,
                maximizable : false
            }).window('open');
        });
	 		
		$("#newstypeWin").window({
				width : 750,
				height : 500,
				title : "报修图片",
				closed : true,
				shadow : false,
				modal : true,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});

		// 启用信息
        $('#btn_stop').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '停用'){
                $.messager.alert('提示','已停用。');
                return;
			}
            $.messager.confirm('确认', '确定停用该支付信息吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=0",
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

		// 启用信息
        $('#btn_sure').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '启用'){
                $.messager.alert('提示','已启用。');
                return;
            }
            $.messager.confirm('确认', '确定启用该支付信息吗,并且停用同类支付?请核对好支付信息', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=1",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示',"启用失败!");
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });

        // 删除支付
        $('#btn_del').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('确认', '确定删除该支付信息吗?', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=2",
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

<div id="devWin"></div>

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
					<#--<p class="p4 p-item">-->
						<#--<label class="lab-item">维修详情 :</label> <input type="text"-->
							<#--class="txt" id="q_detail" name="q_detail" value="${q_detail!''}" />-->
					<#--</p>-->
					<input type="hidden" id="q_sts" name="q_sts" value="2"/>
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
                <th field="bankName" width="90" align="center">银行名称</th>
				<th field="paymentCode" width="90" align="center">支付账号</th>
                <th field="qrCode" width="40" align="center" formatter="imageFormat">二维码</th>
                <th field="sts" width="90" align="center">状态</th>
				<th field="createUserName" width="70" align="center">创建人</th>
				<th field="modifyUserName" width="70" align="center">修改人</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/payment/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加支付</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/payment/stop">
		<a id="btn_stop" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">停用支付</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/property/pass">
		<a id="btn_sure" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">启用支付</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/property/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除支付</a>
		</@shiro.hasPermission>
	</div>
	
</div>
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
