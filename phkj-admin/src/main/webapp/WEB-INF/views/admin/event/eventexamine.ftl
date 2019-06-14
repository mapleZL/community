<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/event/"/>
<script language="javascript">
$(function(){
	<#noescape>
	    codeBox = eval('(${initJSCodeContainer("HOT_EVENT_STATUS")})');
	</#noescape>
	
	$('#a-gridSearch').click(function() {
        $('#dataGrid').datagrid('reload',queryParamsHandler());
    });
	
	// 审核通过
	$('#btn_pass').click(function () {
		var data = $('#dataGrid').datagrid('getChecked');
		console.log(data);
        if(!data||data.length==0){
            $.messager.alert('提示','请选择操作行。');
            return;
        }
 		var ids = new Array();
        var flag = true;
        $.each(data,function(idx,e){
        	if(e.status == 2) {
        		$.messager.alert('提示','只有新建状态才能上架。');
                return;
        	}
              ids.push(e.id);
              changeStatus(ids, 2);
        });
	});
	
	$("#newstypeWin").window({
		width : 750,
		height : 420,
		title : "商品主图",
		closed : true,
		shadow : false,
		modal : true,
		collapsible : false,
		minimizable : false,
		maximizable : false
	});
	
});

function changeStatus(id, status){
	$.messager.progress({text:"提交中..."});
	$.ajax({
		type:"POST",
	    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/event/handler",
	    data: "id=" + id + "&status=" + status,
	    cache:false,
		success:function(e){
			$.messager.progress('close');
            $('#dataGrid').datagrid('reload',queryParamsHandler());
            $.messager.show({
                title:'提示',
                msg:e,
                showType:'show'
            });
		}
	});
}

function stateFormat(value,row,index){
    return codeBox["HOT_EVENT_STATUS"][value];
}

function proTitle(value,row,index){
    return "<font style='color:blue;cursor:pointer' title='"+
            value+"'>"+value+"</font>";
}

function imageFormat(value, row, index) {
	return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
			+ value + "'>点击查看</a>";
}

function showimg(href) {
	if (href && href != 'null') {
		var imgs = href.split(";");
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
<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		热门活动列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<!-- 不分隔 -->
					<p class="p4 p-item">
						<label class="lab-item">活动标题 :</label> <input type="text"
							class="txt" id="q_title" name="q_title" value="${q_title!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">来源 :</label>
						<select name="q_sourceId" id="q_sourceId" level="0" class="txt w140 easyui-combobox">
                           	<option value="0">请选择</option>
							<option value="1">街道</option>
							<option value="2">社区</option>
							<option value="3">物业</option>
						</select>
					</p>
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
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}list?'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="title" width="400" align="left" halign="center">标题</th>
				<th field="sourceName" width="100" align="center">来源</th>
				<th field="postBegin" width="150" align="center">开始时间</th>
				<th field="postEnd" width="150" align="center">结束时间</th>
				<th field="img" width="100" align="center" formatter="imageFormat">商品主图</th>
				<th field="status" width="90" align="center" formatter="stateFormat">状态</th>
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a> 
		<a id="btn_pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">上架</a> 
	</div>
</div>
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;">
		</ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
