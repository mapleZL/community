<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/"/>
<#include "inclistjs.ftl"/>
<script>
	$(function(){
		<#noescape>
	        codeBox = eval('(${initJSCodeContainer("PRODUCT_STATE","PRODUCT_IS_TOP")})');
	    </#noescape>
		// 删除
		$('#a-gridRemove').click(function () {
			
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定删除该商品吗？删除后，此商品将从商城下架，此操作不可恢复。？', function(r){
				if (r){
					changeStatus(selected.id, 5);
			    }
			});
		});
		
		// 提交审核
		$('#a-gridCommit').click(function () {
			var data = $('#dataGrid').datagrid('getChecked');
            if(!data||data.length==0){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
	 		var ids = new Array();
            var flag = true;
            $.each(data,function(idx,e){
                if(e.state !=1 && e.state !=4 && e.state !=7){
                    flag = false;
                    return false;
                } else{
                    ids.push(e.id);
                }
            });
	 		changeStatus(selected.id, 2);
		});
		
		// 上架
		$('#a_pro_up').click(function () {
			var data = $('#dataGrid').datagrid('getChecked');
            if(!data||data.length==0){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
	 		var ids = new Array();
            var flag = true;
            $.each(data,function(idx,e){
                if(e.state !=3){
                    flag = false;
                    return false;
                } else{
                    ids.push(e.id);
                }
            });
            if(!flag){
                $.messager.alert('提示','必须是审核通过的商品才能上架,请检查。');
                return;
            }
	 		changeStatus(selected.id, 6);
		});
		
		// 商品编辑
		$("#a-gridEdit").click(function(){
            var data = $('#dataGrid').datagrid('getChecked');
            if(!data||data.length==0){
                $.messager.alert('提示','请选择操作行。');
                return;
            } else if(data.length>1){
                $.messager.alert('提示','请选择一个商品以编辑');
                return;
            }
            var selected = data[0];
            //提交审核的商品不允许在编辑
            if(2 == selected.state){
                $.messager.alert('提示','该商品已提交审核，暂不允许编辑。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selected.id;
        });
	});
	
	// 提交的统一方法
	function changeStatus(ids, status){
		$.messager.progress({text:"提交中..."});
		$.ajax({
			type:"GET",
		    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/changeStatus",
			dataType: "json",
		    data: "ids=" + id + "&status=" + status,
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
	
	function stateFormat(value,row,index){
        return codeBox["PRODUCT_STATE"][value];
    }
    
    function sellerIsTopFormat(value,row,index){
        return codeBox["PRODUCT_IS_TOP"][value];
    }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">待售列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox"><!-- 不分隔 -->
                    <p class="p4 p-item">
                        <label class="lab-item">商品名称 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">商品SPU :</label>
                        <input type="text" class="txt" id="q_product_code" name="q_product_code" value="${q_product_code!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                        <@cont.select id="q_state1" name="q_state1" value="${q_state1!''}" codeDiv="PRODUCT_STATE" mode="1"/>
                    </p>
                    <p class="p4 p-item">
                            <label class="lab-item">商品分类: </label>
                            <#if productCategory?? && productCategory?size &gt; 0>
                                <select id="q_cateId" name="q_cateId" level="0" class="txt w210 easyui-combobox" >
                                	<option value="">请选择</option>
                                    <#list productCategory as category>
                                        <option value="${(category.codeCd)!}">${(category.codeText)!}</option>
                                    </#list>
                                </select>
                            </#if>
                     </p>
                </div>
            </form>
        </div>
    </div>
</div>

<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,singleSelect:false
						,autoRowHeight:false
						,fitColumns:false
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
    					,url:'${currentBaseUrl}list?q_state=${q_state!''}'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="ck" checkbox="true"></th>
            <th field="name1" width="400" align="left" halign="center" formatter="proTitle">商品名称</th>
            <th field="productCateName" width="100" align="center">商品分类</th>
            <th field="name2" width="150" align="center">促销信息</th>
            <th field="productBrandName" width="90" align="center">商品品牌</th>
            <th field="costPrice" width="50" align="center">成本价</th>
            <th field="mallPcPrice" width="70" align="center">商城价</th>
            <th field="productStock" width="50" align="center">库存</th>
            <#--<th field="actualSales" width="70" align="center">销量</th>-->
            <th field="createTime" width="150" align="center">创建时间</th>
            <th field="upTime" width="150" align="center">上架时间</th>
            <th field="sellerCateName" width="70" align="center">店铺分类</th>
            <th field="sellerIsTop" width="70" align="center" formatter="sellerIsTopFormat">是否店铺推荐</th>
            <th field="state" width="90" align="center" formatter="stateFormat">状态</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/product/edit">
        	<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
        <a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a id="a-gridCommit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">提交审核</a>
        <a id="a_pro_up" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">上架</a>
     </div>
</div>
<#include "/seller/commons/_detailfooter.ftl" />