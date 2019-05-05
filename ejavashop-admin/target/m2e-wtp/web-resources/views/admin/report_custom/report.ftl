<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate/"/>
<head>  
    <title>报表</title>
</head>
<style>
.report_table {width:100%}
.report_table th {background: #e4e4e4; padding:5px; font-weight:bolder}
.report_table td {border-bottom: 1px #ccc dotted; padding:5px;}
</style>
<body class="easyui-layout" data-options="fit:true">
<div id="search_div" data-options="region:'north',border:false,title:'查询条件'" style="overflow: auto;height:400px;">
	<form name="searchForm" method="post" target="dataFrame" action="report">
		<input type="hidden" id="export" name="export">
		<table class="sag_inputtable">
			<tr>
				<td>
					SQL:<textarea id="sql" name="sql" rows="22" cols="83" value=""></textarea>
					<#--<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchSubmitReport()">查询</a>-->
					<a href="#" class="easyui-linkbutton" iconCls="icon-database" onclick="exportExcel()">导出</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div data-options="region:'center',border:false, title:'报表'" style="overflow: hidden;height:100%">
    <iframe name="dataFrame" style="width:100%; height:100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
</div>
<script type="text/javascript">
	
	function exportExcel() {
		$("#export").val("true");
		searchForm.submit();
	}

	function searchSubmitReport() {
		$("#export").val("");
		searchForm.submit();
	}
	
</script>
</body>
</html>