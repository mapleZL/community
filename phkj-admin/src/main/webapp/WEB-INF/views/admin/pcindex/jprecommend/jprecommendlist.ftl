<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript">
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		$("#a-gridAdd").click(function(){
			$('#goodsDialog').dialog('open');
			$('#gd_dataGrid').datagrid('unselectAll');
			
			//动态设置easyui datagrid URL
			$('#gd_dataGrid').datagrid({
				url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/listnopage',
			    queryParams:queryParamsHandler()
			   });
		});
		
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('提示', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/jprecommend/delete?id="+selectedCode.id+"&isTop=1",
                        success:function(e){
                        	$('#dataGrid').datagrid('reload');
                            $.messager.progress('close');
                            $.messager.show({
                                title : '提示',
                                msg : e,
                                showType : 'show'
                            });
                        }
                    });
                }
            });
        });
		
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})
</script>

<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;display: none;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">PC端推荐商品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					日期：
                    <input type="text" id="q_time" name="q_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd'})" class="txt w180"/>
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
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/pcindex/jprecommend/list'
    					,queryParams:queryParamsHandler()
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" width="100" align="left" halign="center">ID</th>
				<th field="name1" width="500" align="left" halign="center">商品名称</th>
	            <th field="productStock" width="100" align="center" halign="center">库存</th>
	            <th field="updateTime" width="200" align="center">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		<@shiro.hasPermission name="/admin/pcindex/jprecommend/delete">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
</div>

<div style="display: none">
<#include "goodsDialog.ftl"/>
</div>
<#include "/admin/commons/_detailfooter.ftl" />