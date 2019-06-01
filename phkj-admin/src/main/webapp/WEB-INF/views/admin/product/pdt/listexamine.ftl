<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/"/>
<script language="javascript">
$(function(){
	<#noescape>
	    codeBox = eval('(${initJSCodeContainer("PRODUCT_CATEGORY","PRODUCT_STATE","PRODUCT_IS_TOP")})');
	</#noescape>
	
	$('#a-gridSearch').click(function() {
        $('#dataGrid').datagrid('reload',queryParamsHandler());
    });
	
	// 审核通过
	$('#btn_pass').click(function () {
		var data = $('#dataGrid').datagrid('getChecked');
		console.log(data);
        if(!data||data.length==0){
            $.messager.alert('提示','请选择操作行。');
            return;
        }
 		var ids = new Array();
        var flag = true;
        $.each(data,function(idx,e){
              ids.push(e.id);
        });
 		changeStatus(ids, 3);
	});
	
	// 审核不通过
	$('#btn_onpass').click(function () {
		var data = $('#dataGrid').datagrid('getChecked');
		console.log(data);
        if(!data||data.length==0){
            $.messager.alert('提示','请选择操作行。');
            return;
        }
 		var ids = new Array();
        var flag = true;
        $.each(data,function(idx,e){
             ids.push(e.id);
        });
 		changeStatus(ids, 4);
	});
	
	$("#newstypeWin").window({
		width : 750,
		height : 420,
		title : "商品主图",
		closed : true,
		shadow : false,
		modal : true,
		collapsible : false,
		minimizable : false,
		maximizable : false
	});
	
});

function changeStatus(ids, status){
	$.messager.progress({text:"提交中..."});
	$.ajax({
		type:"POST",
	    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/handler",
	    data: "ids=" + ids + "&type=" + status,
	    cache:false,
		success:function(e){
			$.messager.progress('close');
            $('#dataGrid').datagrid('reload',queryParamsHandler());
            $.messager.show({
                title:'提示',
                msg:e,
                showType:'show'
            });
		}
	});
}

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
<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		待审核商品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<!-- 不分隔 -->
					<p class="p4 p-item">
						<label class="lab-item">商品名称 :</label> <input type="text"
							class="txt" id="q_name1" name="q_name1" value="${q_name!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">商品SPU :</label> <input type="text"
							class="txt" id="q_product_code" name="q_product_code"
							value="${q_product_code!''}" />
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
    					,url:'${currentBaseUrl}list?q_state=2'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="name1" width="400" align="left" halign="center"
					formatter="proTitle">商品名称</th>
				<th field="productCateId" width="100" align="center"
					formatter="productCateFormat">商品分类</th>
				<th field="name2" width="150" align="center">促销信息</th>
				<th field="costPrice" width="70" align="center">成本价</th>
				<th field="mallPcPrice" width="70" align="center">商城价</th>
				<th field="productStock" width="70" align="center">库存</th> <#--
				<th field="actualSales" width="70" align="center">销量</th>-->
				<th field="createTime" width="150" align="center">创建时间</th>
				<th field="upTime" width="150" align="center">上架时间</th>
				<th field="sellerIsTop" width="70" align="center"
					formatter="sellerIsTopFormat">是否店铺推荐</th>
				<th field="state" width="90" align="center" formatter="stateFormat">状态</th>
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a> 
		<a id="btn_pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核通过</a> 
		<a id="btn_onpass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">审核不通过</a>
	</div>
</div>
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;">
		</ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
