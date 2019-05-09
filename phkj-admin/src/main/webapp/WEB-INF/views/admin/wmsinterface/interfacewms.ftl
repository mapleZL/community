<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/wmsinterface"/>

<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script src='${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js'></script>

<script type="text/javascript">
  $(function(){
    $('#btn-search').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
  });
</script>

			<div id="devWin"></div>
			
			<div id="searchbar" data-options="region:'north'"
				style="margin: 0 auto;" border="false">
				<h2 class="h2-title">
					订单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
				</h2>
				<div id="searchbox" class="head-seachbox">
					<form class="form-search" action="doForm" method="post" id="queryForm"
						name="queryForm">
						<div class="fluidbox">
							<p class="p4 p-item">
								<label class="lab-item">推送类型:</label>
								<select name="q_syncType" id="q_syncType">
									  <option value="">--请选择--</option>
									  <option value="ShipmentRequest">&nbsp;&nbsp;出库单&nbsp;&nbsp;</option>
									  <option value="ReceiptRequest">&nbsp;&nbsp;入库单&nbsp;&nbsp;</option>
									  <option value="ItemRequest">商品信息同步</option>
									  <option value="CompanyRequest">货主信息同步</option>
								</select>
							</p>
							<p class="p4 p-item">
								<label class="lab-item">关联表名:</label> 
								<select name="q_ralationTable" id="q_ralationTable">
								      <option value="">--请选择--</option>
								      <option value="seller">seller</option>
								      <option value="orders">orders</option>
								      <option value="product">product</option>
								      <option value="product_goods">product_goods</option>
								      <option value="booking_send_goods">booking_send_goods</option>
								      <option value="member">member</option>
								      <option value="order_receipt">order_receipt</option>
								</select>
							</p>
							<p class="p4 p-item">
								<label class="lab-item">推送结果:</label>
								<select name="send_result" id="send_result">
								      <option value="">--请选择--</option>
								      <option value="true">成功</option>
								      <option value="false">失败</option>
								</select>
							</p>
						</div>
						<div class="fluidbox">
						    <p class="p12 p-item">
						        <label class="lab-item">推送时间:</label>
						       <input type="text" id="q_startTime" name="q_startTime" value="${(q_startTime)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w200" data-options="required:true"/>-
						       <input type="text" id="q_endTime" name="q_endTime" value="${(q_endTime)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w200" data-options="required:true"/>
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
								,fitColumns:false
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
						<th field="ck" checkbox="true"></th>
						<th field="relationId" width="100" align="left" halign="center">关联主键</th>
						<th field="ralationTable" width="100" align="left" halign="center">关联表名</th>
						<th field="sendNo" width="100" align="left" halign="center">发送次数</th>
						<th field="sendResult" width="100" align="left" halign="center">发送结果</th>
						<th field="syncType" width="150" align="left" halign="center">同步类型</th>
						<th field="syncTime" width="200" align="left" halign="center">同步时间</th>
						<th field="errorMsg" width="400" align="left" halign="center">返回信息</th>
				   </tr>
				</thead>
			</table>
			<div id="gridTools">
				<@shiro.hasPermission name="/admin/wmsinterface/list">
				<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
				</@shiro.hasPermission>
			</div>
		</div>

<#include "/admin/commons/_detailfooter.ftl" />
					
