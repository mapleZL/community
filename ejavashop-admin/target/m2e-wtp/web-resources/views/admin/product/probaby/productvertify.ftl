<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/vertify/"/>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
var codeBox;
<#noescape>
			codeBox = eval('(${initJSCodeContainer("ORDER_RECEIPT_STATUS")})');
</#noescape>

	function getState1(value, row, index) {
		var box = codeBox["ORDER_RECEIPT_STATUS"][value];
		return box;
	}
	
	
	$(function(){
		$("#a-gridAdd").click(function() {
			location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/probaby/add";
		});
		$('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
	$("#btn_audit").click(function(){
	         var selectedCode = $('#dataGrid').datagrid('getSelected');
	            if(!selectedCode){
	                $.messager.alert('提示','请选择操作行。');
	                return;
	            }
            $.ajax({
                type:"GET",
                url: "${currentBaseUrl}audit?type=pase&orders_id="+selectedCode.ordersId,
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
        });
        
       $("#btn_audit1").click(function(){
	         var selectedCode = $('#dataGrid').datagrid('getSelected');
	            if(!selectedCode){
	                $.messager.alert('提示','请选择操作行。');
	                return;
	            }
            $.ajax({
                type:"GET",
                url: "${currentBaseUrl}audit?type=unpase&orders_id="+selectedCode.ordersId,
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
        });
	
	});
	
	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
	
    function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/vertify/add?ordersId='+row.ordersId,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'sku',
				title : '商品SKU',
				width : 100,
				align : 'left',
				halign : 'center'
			}, {
				field : 'numberProbaby',
				title : '预约入库数量',
				width : 100,
				align : 'left',
				halign : 'center'
			}]],
			onResize : function() {
				$('#dataGrid').datagrid('fixDetailRowHeight',index);
			},
			onLoadSuccess : function() {
				setTimeout(function() {
					$('#dataGrid').datagrid('fixDetailRowHeight',index);
				}, 0);
			}
			});
	}
</script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品预发货列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">预约入库单号 :</label>
                        <input type="text" class="txt" id="q_orders_id" name="q_orders_id" value=""/>
                    </p>
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
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
        	<th field="id" hidden="hidden"></th>
            <th field="ordersId" width="100" align="left">预约入库单号</th>
            <th field="sellerName" width="100" align="left">商家</th>
            <th field="SPU" width="100" align="center">品牌</th>
            <th field="totalAmount" width="100" align="center">预约入库总量</th>
            <th field="dateProbaby" width="100" align="center">预约入库时间</th>
            <th field="createtime" width="100" align="center">创建时间</th>
            <th field="systemUserName" width="100" align="center">创建用户ID</th>
            <th field="status" width="100" align="center" formatter="getState1">审核状态</th>
        </tr>
        </thead>
    </table>

    <#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">预约收货</a>
        <@shiro.hasPermission name="/admin/product/vertify/audit">
        <a id="btn_audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核通过</a>
        <a id="btn_audit1" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">审核不通过</a>
        </@shiro.hasPermission>
        </div>
	
	<div id="allotResourceWin">
		
	</div>
</div>


<#include "/admin/commons/_detailfooter.ftl" />