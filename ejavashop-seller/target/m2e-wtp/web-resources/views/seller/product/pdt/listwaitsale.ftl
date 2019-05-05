<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>
<#include "inclistjs.ftl"/>
<script>
	$(function(){
		$('#a_goods_set').click(function(){
    	    var sel = $('#dataGrid').datagrid('getChecked');
            if(!sel||sel.length==0){
                $.messager.alert('提示','请选择操作行。');
                return;
            } else if(sel.length>1){
                $.messager.alert('提示','请选择一个商品以操作');
                return;
            }
            window.location.href="${currentBaseUrl}goodsSet?id="+sel[0].id+"&from=waitSale";
    	});
		
		//打标价格设置
		$('#a_goods_set2').click(function(){
    	    var sel = $('#dataGrid').datagrid('getChecked');
            if(!sel||sel.length==0){
                $.messager.alert('提示','请选择操作行。');
                return;
            } else if(sel.length>1){
                $.messager.alert('提示','请选择一个商品以操作');
                return;
            }else if(sel[0].productBrandName != "裸袜"){
                $.messager.alert('提示','只有裸袜才可以进行打标设置');
            	return;
            }
            window.location.href="${currentBaseUrl}goodsSet2?id="+sel[0].id+"&from=waitSale&cateId=1";
    	});
    	
    	$('#a_sku_add').click(function(){
    		var sel = $('#dataGrid').datagrid('getChecked');
    		if(!sel||sel.length==0){
                $.messager.alert('提示','请选择操作行。');
                return;
            } else if(sel.length>1){
                $.messager.alert('提示','请选择一个商品以操作');
                return;
            }
            window.location.href="${currentBaseUrl}addSKU?id="+sel[0].id;
    	});
	});
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
                        <input type="text" class="txt" id="q_name1" name="q_name1" value="${q_name!''}"/>
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
                            <label class="lab-item">商品品牌: </label>
                            <input type="hidden" name="parentId_0" id="parentId_0" value="1">
                            <#if brand?? && brand?size &gt; 0>
                                <select id="q_brandId" name="q_brandId" level="0" class="txt w210" >
                                	<option value="">请选择</option>
                                    <#list brand as brand>
                                        <option value="${(brand.id)!''}">${(brand.nameFirst)!''} ${(brand.name)!''}</option>
                                    </#list>
                                </select>
                            </#if>
                     </p>
                     <p class="p4 p-item">
                     	<label class="lab-item">促销状态: </label>
                     		<select id="q_name2" name="q_name2" level="0" class="txt w210">
                     			<option value="">请选择</option>
                     			<option value="1">促销中</option>
                     			<option value="2">无促销</option>
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
            <th field="productBrandId" width="90" align="center" hidden="hidden">商品品牌ID</th>
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
        <@shiro.hasPermission name="/seller/product/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/del">
        <a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/commit">
        <a id="a-gridCommit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">提交审核</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/waitSaleUp">
        <a id="a_pro_up" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">上架</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/addSKU">
        <a id="a_sku_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">增加sku</a>
     	</@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/goodsSet">
        <a id="a_goods_set" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit_task" plain="true">库存价格设置</a>
     	</@shiro.hasPermission>
     	<@shiro.hasPermission name="/seller/product/goodsSet2">
        <a id="a_goods_set2" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit_task" plain="true">打标价格设置</a>
     	</@shiro.hasPermission>
     	
     </div>
</div>
<#include "/seller/commons/_detailfooter.ftl" />