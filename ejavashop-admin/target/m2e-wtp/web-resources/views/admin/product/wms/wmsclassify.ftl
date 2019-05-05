<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/wmsclassify/"/>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">

	$(function() {
	
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("WMS_CATEGORY_STATE")})');
		</#noescape>
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
    <h2 class="h2-title">wms分类列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
            
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">wms分类:</label>
                        <input type="text" class="txt" id="q_wms_category" name="q_wms_category" value=""/>
                    </p>
                    <#--
                    <p class="p4 p-item">
                        <label class="lab-item">预约入库时间人 :</label>
                        <input type="text" id="q_date_probaby" name="q_date_probaby" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt w120" data-options="required:true"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">商家ID :</label>
							<select name="q_seller_id" id="q_seller_id">
								<option value=""> 请选择  </option>
						<#if sellers??>
	                     	<#list sellers as seller>
							<option value="${(seller.id)!}">${(seller.sellerName)!}</option>
							</#list>
						</#if>
							</select>
                    </p>
                    -->
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
            <th field="wms_category" width="150" align="center">wms分类</th>
            <th field="create_user" width="150" align="center">创建人</th>
            <th field="state" width="150" align="center" formatter="getState">使用状态</th>
            <th field="create_time" width="150" align="center">创建时间</th>
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