<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>

<script language="javascript">
	$(function (){
		$('#a-gridSearch').click(function (){
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});
	});
	
	function listParterSignNo(obj){
	       var grade = obj.options[obj.options.selectedIndex].value;
	       var ordersIdDivDom = $('#signNo');
			ordersIdDivDom.empty();
	       if(grade =='000'){
	    	   return;
	       }
		   $.ajax({
	 			type : "GET",
				url :  "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/getParterSignNo",
				data : {
					memberId:grade,
				}, 
				dataType : "json",
				success : function(data) {
					if (data.success) {
						var htmlStr = "<option value='000'>--请选择--</option>";
						 $.each(data.data, function(i, parter){
							htmlStr = htmlStr +"<option value="+parter.signNo+">"+parter.signNo+"</option>" ;
						 });
						 ordersIdDivDom.append(htmlStr);
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
	 		});
	   }
	
	function listParterArea(obj){
		var grade = obj.options[obj.options.selectedIndex].value;
	       var ordersIdDivDom = $('#areaId');
			ordersIdDivDom.empty();
	       if(grade =='000'){
	    	   return;
	       }
		   $.ajax({
	 			type : "GET",
				url :  "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/getParterSignArea",
				data : {
					signNo:grade,
				}, 
				dataType : "json",
				success : function(data) {
					if (data.success) {
						var htmlStr = "<option value='000'>--请选择--</option>";
						 $.each(data.data, function(i, regions){
							htmlStr = htmlStr +"<option value="+regions.id+">"+regions.regionName+"</option>" ;
						 });
						 ordersIdDivDom.append(htmlStr);
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
	 		});
		
		
	}
	
	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
	function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/getSalesDetailsByOrdersId?ordersId='+row.ordersId,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'orderSn',
				title : 'SKU',
				width : 30,
				align : 'left',
				halign : 'center'
			}, {
				field : 'moneyLogistics',
				title : '单价',
				width : 20,
				align : 'left',
				halign : 'center'
			}, {
				field : 'servicePrice',
				title : '加工费',
				width : 20,
				align : 'left',
				halign : 'center'
			}, {
				field : 'labelPrice',
				title : '辅料费',
				width : 20,
				align : 'center'
			}, {
				field : 'number',
				title : '数量',
				width : 30,
				align : 'center'
			},{
				field : 'moneyOrder',
				title : '总金额',
				width : 30,
				align : 'center'
			},{
				field : 'returnNumber',
				title : '退货数量',
				width : 30,
				align : 'center'
			}, {
				field : 'endMoney',
				title : '退货金额',
				width : 30,
				align : 'center'
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

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">会员列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">合伙人 :</label>
					<select name="q_parterSignMemberId" class="txt" onchange="listParterSignNo(this)" id="q_parterSignMemberId">
					<option value="000">--请选择--</option>
					  <#if parterSignList ??>
					      <#list parterSignList as parterSign>
					           <option value="${parterSign.memberId}">${(parterSign.memberRealName)!''}</option>
					      </#list>
					  </#if>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">合同编号 :</label>
					<select name="q_signNo" class="txt" id="signNo"  onchange="listParterArea(this)" >
						<option value="000">--请选择--</option>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">出库时间:</label>
                        <input type="text" id="q_startTime" name="q_startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt w110" data-options="required:true"/>
                        	—
                    	<input type="text" id="q_endTime" name="q_endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt w110" data-options="required:true"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">地区 :</label>
					<select name="q_areaId" class="txt" id="areaId">
						<option value="000">--请选择--</option>
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
						,idField :'id'
						,singleSelect:true
						,view: detailview
						,autoRowHeight:false
						,fitColumns:false
						,collapsible:true
						,toolbar:'#gridTools'
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/getSalesDetailsList'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="ordersId" hidden="hidden"></th>
				<th field="orderSn" width="150" align="center" halign="center">订单号</th>
				<th field="ordersTime" width="180" align="center" halign="center">出库时间</th>  
	            <th field="name" width="120" align="center" halign="center">买家用户名</th>  
	            <th field="addressAll" width="180" align="center" halign="center">省市区</th>  
	            <th field="moneyProduct" width="150" align="center" halign="center">商品金额</th>  
	            <th field="servicePrice" width="150" align="center" halign="center">加工费</th>  
	            <th field="labelPrice" width="150" align="center" halign="center">辅料费</th>  
	            <th field="moneyLogistics" width="150" align="center" halign="center">运费</th>  
	            <th field="moneyOrder" width="150" align="center" halign="center">订单总金额</th>  
	            <th field="returnMoneyOrder" width="150" align="center" halign="center">退货总金额</th>  
	            <th field="endMoney" width="150" align="center" halign="center">最终结算金额</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />