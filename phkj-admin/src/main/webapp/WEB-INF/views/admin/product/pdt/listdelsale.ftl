<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>
<#include "inclistjs.ftl"/>
<script language="javascript">
$(function(){
	<#noescape>
	    codeBox = eval('(${initJSCodeContainer("PRODUCT_CATEGORY","PRODUCT_STATE","PRODUCT_IS_TOP")})');
	</#noescape>
	
	$('#a-gridSearch').click(function() {
        $('#dataGrid').datagrid('reload',queryParamsHandler());
    });
	
    $('#btn_audit').click(function () {
		
		var selected = $('#dataGrid').datagrid('getSelected');
 		if(!selected){
			$.messager.alert('提示','请选择操作行。');
			return;
		}
 		$.messager.confirm('确认', '确认恢复该商品吗？', function(r){
			if (r){
				$.messager.progress({text:"提交中..."});
				$.ajax({
					type:"POST",
				    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/changeStatus",
					dataType: "json",
				    data: "id=" + selected.id + "&state=" + 1,
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
		});
	});
	
});

function stateFormat(value,row,index){
    return codeBox["PRODUCT_STATE"][value];
}

function sellerIsTopFormat(value,row,index){
    return codeBox["PRODUCT_IS_TOP"][value];
}

function productCateFormat(value,row,index){
    return codeBox["PRODUCT_CATEGORY"][value];
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
		var imgs = JSON.parse(href);
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
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">已删除商品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
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
    					,url:'${currentBaseUrl}list?q_state1=5'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="id" hidden="hidden"></th>
            <th field="name1" width="400" align="left" halign="center" formatter="proTitle">商品名称</th>
            <th field="productCateName" width="100" align="center" formatter="productCateFormat">商品分类</th>
            <th field="name2" width="150" align="center">促销信息</th>
            <th field="costPrice" width="70" align="center">成本价</th>
            <th field="mallPcPrice" width="70" align="center">商城价</th>
            <th field="productStock" width="70" align="center">库存</th>
            <#--<th field="actualSales" width="70" align="center">销量</th>-->
            <th field="createTime" width="150" align="center">创建时间</th>
            <th field="upTime" width="150" align="center">上架时间</th>
            <th field="sellerIsTop" width="70" align="center" formatter="sellerIsTopFormat">是否店铺推荐</th>
            <th field="state" width="90" align="center" formatter="stateFormat">状态</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/seller/product/revertProduct">
        <a id="btn_audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">恢复</a>
        </@shiro.hasPermission>
	</div>
</div>
<#include "/seller/commons/_detailfooter.ftl" />