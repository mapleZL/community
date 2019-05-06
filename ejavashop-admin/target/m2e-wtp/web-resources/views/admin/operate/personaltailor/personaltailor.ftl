<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/personaltailor"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
$(function(){
	$('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
     });
     
     $("#a-gridRemove").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            $.messager.confirm('Confirm', '确定删除吗？', function(r){
                if (r) {
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}/del?id="+selectedCode.id,
                        success:function(e) {
                         $('#dataGrid').datagrid('reload');
                         $.messager.progress('close');
		                 $.messager.show({
			                    title : '提示',
			                    msg : '删除成功',
		                        showType : 'show'
	                      });
                        }
                    });
                }
            });
        });
        
});

function getPicture(value, row, index) {
		var box = "<img src='${domainUrlUtil.EJS_URL_RESOURCES}"+value+"'/>";
		return box;
}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">私人订制列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">用户名 :</label>
                        <input type="text" class="txt" id="q_memberName" name="q_memberName" value="${q_bookingUser!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">提交时间:</label>
                        <input type="text" class="txt" id="q_createTimes" name="q_createTimes" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w110" data-options="required:true"/>
                        -
                        <input type="text" class="txt" id="q_createTimee" name="q_createTimes" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w110" data-options="required:true"/>
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
		    					,url:'${currentBaseUrl}/list'
		    					,queryParams:queryParamsHandler()
		    					,onLoadSuccess:dataGridLoadSuccess
		    					,method:'get'">
				<thead>
					<tr>
						<th field="id" hidden="hidden">编号</th>
						<th field="createTime" align="center">提交时间</th>
						<th field="memberName" align="center">用户名</th>
						<th field="name" align="center">联系人</th>
						<th field="mobile" align="center">联系电话</th>
						<th field="weixin" align="center">微信</th>
						<th field="qq" align="center">QQ</th>
						<th field="tailorDescription" align="center" formatter="getPicture">样款</th>
						<th field="description"  align="center">采购需求</th>
					</tr>
				</thead>
			</table>
			
			<div id="gridTools">
				<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				<@shiro.hasPermission name="/admin/personaltailor/del">
        		<a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        		</@shiro.hasPermission>
			</div>
</div>


<#include "/admin/commons/_detailfooter.ftl" />
