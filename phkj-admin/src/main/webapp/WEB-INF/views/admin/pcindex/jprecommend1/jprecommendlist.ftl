<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript">
	 $(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		/*$("#a-gridAdd").click(function(){
			$('#goodsDialog').dialog('open');
			$('#gd_dataGrid').datagrid('unselectAll');
			
			//动态设置easyui datagrid URL
			$('#gd_dataGrid').datagrid({
				url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/listnopage',
			    queryParams:queryParamsHandler()
			   });
		});*/
		
		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		//刪除
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
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/jprecommend1/delete?id="+selectedCode.id,
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
		//<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	}) 
	
	function proTitle(value,row,index){
    	try {
        	return "<font style='color:blue;cursor:pointer' title='" + value + "' onclick='openwin("+row.productId+")'>"+row.product.name1+"</font>";
        }
        catch (e) {
        	return row.productId;
        }
    }
    
    function proCode(value,row,index){
    	try {
        	return row.product.productCode;
        }
        catch (e) {
        	return "";
        }
    }
    
    function proSeller(value,row,index){
    	try {
        	return row.product.sellerId;
        }
        catch (e) {
        	return "";
        }
    }
	 
	function openwin(id){
		 window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/product/"+ id +".html?preview=1");
	    }
	
	 //操作
    function handler(value,row,index){
		 //alert(row.id);
        return "<a href='javascript:;' onclick='del("+row.id+")'>删除</a>";
    }
    function del(id){
        $.messager.confirm('确认', '确定删除吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"get",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/jprecommend1/delete?id="+id,
                    success:function(e){
                        $.messager.show({
                            title:'提示',
                            msg:e,
                            showType:'show'
                        });
                        $.messager.progress('close');
                        $('#dataGrid').datagrid('reload');
                    }
                });
            }
        });
    }
	
</script>


<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">PC端首页楼层商品排序列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">楼层：</label>
					<select name="q_floorCode" id="q_floorCode" >
                    	<option value="">--全部--</option>
	                        <#if floors ?? && floors?size &gt; 0>
	                        	<#list floors as floor>
									<option value="${(floor.sortType)!}">${(floor.sortType)!}</option>
								</#list>
							</#if>
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
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,queryParams:queryParamsHandler()
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/pcindex/jprecommend1/list'
    					,method:'get'">
		<thead>
			<tr>
				<th field="productId" width="100" align="left" >ID</th>
				<th field="name1" width="500" align="left" formatter="proTitle">商品名称</th>
				<th field="productCode" width="150" formatter="proCode" align="left">SPU</th>
				<th field="proSeller" width="150" formatter="proSeller" align="left">商家序号</th>				
	            <th field="sortType" width="100" align="center" halign="center">楼层编号</th>
	            <th field="sort" width="200" align="center">排序号</th>
	            <th field="handler" width="80" align="center" formatter="handler">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/pcindex/jprecommend1/delete">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
</div>

<div style="display: none">
</div>
<#include "/admin/commons/_detailfooter.ftl" />