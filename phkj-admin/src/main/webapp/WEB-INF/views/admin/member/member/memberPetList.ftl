<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("PET_STATUS")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		// 审核通过
		$('#btn_pass').click(function () {
			// debugger;
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		// 判断是否是已经审核通过的数据
			if(selected.sts != '待审核'){
				$.messager.alert('提示','该条申请已处理,无法审核。');
				return;
			}
	 		$.messager.confirm('确认', '确定审核通过该条申请吗', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/pet/system/updatePet",
						dataType: "json",
					    data: "id=" + selected.id + "&type=pass",
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示','通过失败!');
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});


        // 审核不通过
        $('#btn_noPass').click(function () {
            // debugger;
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            // 判断是否是已经审核通过的数据
            if(selected.sts != '待审核'){
                $.messager.alert('提示','该条申请已处理,无法审核。');
                return;
            }
            $.messager.confirm('确认', '确定审核通过该条申请吗', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/pet/system/updatePet",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=noPass",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示','通过失败!');
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });

        // 审核不通过
        $('#btn_del').click(function () {
            // debugger;
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            // 判断是否是已经审核通过的数据
            if(selected.sts != '申请删除登记'){
                $.messager.alert('提示','该条数据未申请删除登记,无法通过删除。');
                return;
            }
            $.messager.confirm('确认', '确定审核通过该条申请吗', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/pet/system/updatePet",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=del",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示','通过失败!');
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
			title : "车辆图片",
			closed : true,
			shadow : false,
			modal : true,
			collapsible : false,
			minimizable : false,
			maximizable : false
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
		var box = codeBox["PET_STATUS"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		宠物登记列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">登记状态 :</label> <@cont.select id="sts"
							codeDiv="PET_STATUS" name="q_status" style="width:100px"/>
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
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/pet/system/getAllPetList'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="raiser" width="40" align="center">饲养人</th>
				<th field="petBreed" width="40" align="center">宠物品种</th>
				<th field="petName" width="70" align="center">昵称</th>
				<th field="gender" width="20" align="center">性别</th>
				<th field="sterilization" width="40" align="center">绝育情况</th>
				<th field="weight" width="40" align="center">体重</th>
				<th field="address" width="70" align="center">住址</th>
				<th field="exemptionNum" width="70" align="center">健康免疫证编号</th>
				<th field="exemptionTime" width="70" align="center">免疫时间</th>
				<th field="vaccineType" width="70" align="center">疫苗类型</th>
				<th field="petRegisNum" width="70" align="center">养犬登记编码</th>
                <th field="exemptionImg" width="40" align="center" formatter="imageFormat">健康免疫证</th>
                <th field="petRegisImg" width="40" align="center" formatter="imageFormat">养犬登记证</th>
                <th field="createTime" width="70" align="center">登记时间</th>
				<th field="sts" width="70" align="center">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
	<@shiro.hasPermission name="/admin/member/pet/pass">
		<a id="btn_pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">登记通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/pet/noPass">
		<a id="btn_noPass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">拒绝登记</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/pet/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除通过</a>
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
