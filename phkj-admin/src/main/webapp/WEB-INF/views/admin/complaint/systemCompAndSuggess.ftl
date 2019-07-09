<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("COM_TYPE")})');
		</#noescape>

        $("#newstypeWin").window({
            width : 750,
            height : 420,
            title : "车辆图片",
            closed : true,
            shadow : false,
            modal : true,
            collapsible : false,
            minimizable : false,
            maximizable : false
        });


        // 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$('#btn_edit').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$("#editWin").window({
				width : 600,
				height : 295,
				href : "${currentBaseUrl}/edit?id="+selected.id,
				title : "修改",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});

        // 删除信息
        $('#a_submit_share').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }

            if(selected.sts == '已审核'){
                $.messager.alert('提示','该条信息已被审核。');
                return;
            }
            $.messager.confirm('确认', '确定审核该条发布信息吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/complaint/system/updateCom",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=1",
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
		var box = codeBox["SELLER_AUDIT_STATE"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		投诉列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item" >
						<label class="lab-item">内容类型 :</label> <@cont.select id="type"
						codeDiv="COM_TYPE" name="q_type" style="width:100px" value = "1"/>
					</p>
					<p class="p4 p-item" >
						<label class="lab-item">审核结果 :</label> <@cont.select id="sts"
						codeDiv="COM_STS" name="q_sts" style="width:100px"/>
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
						,pageSize:${pageSize}
						,fit:true
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/complaint/system/getAllComAndSugg'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
                <th field="userId" hidden="hidden"></th>
				<th field="id" hidden="hidden"></th>
				<th field="type" width="70" align="center">类型</th>
				<th field="complaintTarget" width="70" align="center">投诉对象</th>
				<th field="content" width="150" align="center">详细内容</th>
				<th field="email" width="70" align="center">电子邮箱</th>
				<th field="createTime" width="70" align="center">投诉时间</th>
				<th field="createName" width="70" align="center" >投诉人</th>
				<th field="sts" width="70" align="center" >处理状态</th>
                <th field="imgUrl" width="40" align="center" formatter="imageFormat">图片</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>

        <a id="a_submit_share" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">审核处理</a>
	</div>

	<div class="wrapper" id="editWin">
		
	</div>
</div>
<div id="newstypeWin">
    <form id="newstypeForm" method="post">
        <ul id="newstypeTree"
            style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
    </form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
