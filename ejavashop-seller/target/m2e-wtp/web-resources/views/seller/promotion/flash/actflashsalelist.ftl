<#include "/seller/commons/_detailheader.ftl" />

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

	var codeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("FLASH_SALE_STATUS","CHANNEL")})');
		</#noescape>

		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridApply").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/seller/promotion/flash/apply?actFlashSaleId="+selected.id;
		});
		
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/previewindex.html");
		});
        
	    $("#newstypeWin").window({
			width : 666,
			height : 420,
			title : "楼层片",
			closed : true,
			shadow : false,
			modal : true,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
	    
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return codeBox["FLASH_SALE_STATUS"][value];
	}

	function channelFormat(value,row,index){
		return codeBox["CHANNEL"][value];
	}
	
	function imageFormat(value, row, index) {
		return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
				+ value + "'>点击查看</a>";
	}

	function showimg(href) {
		$("#newstypeTree").html("<img src='${domainUrlUtil.EJS_IMAGE_RESOURCES}/"+href+"' >");
		$("#newstypeWin").window('open');
	}

</script>

<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">限时抢购活动列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox">
				<p class="p4 p-item"><label class="lab-item">活动名称：</label><input type="text" class="txt" id="q_actFlashSaleName" name="q_actFlashSaleName" value="${q_actFlashSaleName!''}"/></p>
				<p class="p4 p-item">
					<label class="lab-item">日期：</label>
                    <input type="text" id="q_actDate" name="q_actDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt w180"/>
                </p>
				<p class="p4 p-item"><label class="lab-item">渠道：</label><@cont.select id="q_channel" value="${(q_channel)!''}" codeDiv="CHANNEL" style="width:80px" /></p>
				<p class="p4 p-item"><label class="lab-item">状态：</label><@cont.select id="q_status" value="${(q_status)!''}" codeDiv="FLASH_SALE_STATUS" style="width:80px" /></p>
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/seller/promotion/flash/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="actFlashSaleName" width="100" align="left" halign="center">活动名称</th>
				<th field="actDate" width="150" align="center" halign="center">活动日期</th>
				<th field="pcImage" width="80" align="center" halign="center" formatter="imageFormat">PC图片</th>
				<th field="mobileImage" width="80" align="center" halign="center" formatter="imageFormat">移动图片</th>
				<th field="channel" width="80" align="center" halign="center" formatter="channelFormat">应用渠道</th>
				<th field="status" width="80" align="center" halign="center" formatter="statusFormat">活动状态</th>
				<th field="auditRule" width="150" align="left" halign="center">申请规则</th>
				<th field="remark" width="150" align="left" halign="center">活动描述</th>
				<th field="auditOpinion" width="100" align="left" halign="center">作废原因</th>
	            <th field="updateUserName" width="100" align="center">最后修改人</th>
	            <th field="updateTime" width="150" align="center">最后修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/seller/promotion/flash/apply">
		<a id="a-gridApply" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">申请活动商品</a>
		</@shiro.hasPermission>
		<!-- <a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a> -->
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>

<#include "/seller/commons/_detailfooter.ftl" />