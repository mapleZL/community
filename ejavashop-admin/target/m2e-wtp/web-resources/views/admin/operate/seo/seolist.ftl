<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/seo/"/>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">

	$(function() {
	
		$('#a-gridSearch').click(function() {
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        
        $("#a-gridAdd").click(function() {
            window.location.href="${currentBaseUrl}/add";
        });
        
        $("#a-gridedit").click(function() {
        	var selected = $('#dataGrid').datagrid('getSelected');
        	if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
        	window.location.href="${currentBaseUrl}/edit?id="+selected.id;
        });
	
	})
	
	function getState(value, row, index) {
		var box = codeBox["WMS_CATEGORY_STATE"][value];
		return box;
	}
	
</script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">SEO分类列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
            
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">wms分类:</label>
                        <input type="text" class="txt" id="q_wms_category" name="q_wms_category" value=""/>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="path" width="150" align="left">页面路径</th>
            <th field="title" width="80" align="left">标题</th>
            <th field="keywords" width="150" align="left">关键字</th>
            <th field="description" width="250" align="center">描述</th>
            <th field="applyPage" width="60" align="center">应用页面</th>
        </tr>
        </thead>
    </table>

    <#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a id="a-gridedit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </div>
		
	</div>
</div>


<#include "/admin/commons/_detailfooter.ftl" />