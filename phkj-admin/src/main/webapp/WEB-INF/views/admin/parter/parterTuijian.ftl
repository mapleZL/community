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
	
	function listParterTuiJian(obj){
	       var grade = obj.options[obj.options.selectedIndex].value;
	       var ordersIdDivDom = $('#q_tuiJIanMemberId');
			ordersIdDivDom.empty();
	       if(grade =='000'){
	    	   return;
	       }
		   $.ajax({
	 			type : "GET",
				url :  "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/getParterTuiJian",
				data : {
					memberId:grade,
				}, 
				dataType : "json",
				success : function(data) {
					if (data.success) {
						var htmlStr = "<option value='000'>--请选择--</option>";
						 $.each(data.data, function(i, member){
							htmlStr = htmlStr +"<option value="+member.id+">"+member.realName+"</option>" ;
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
					<select name="q_parterSignMemberId" class="txt" onchange="listParterTuiJian(this)" id="q_parterSignMemberId">
					<option value="000">--请选择--</option>
					  <#if parterSignList ??>
					      <#list parterSignList as parterSign>
					           <option value="${parterSign.memberId}">${(parterSign.memberRealName)!''}</option>
					      </#list>
					  </#if>
					</select>
				</p>
				
				<p class="p4 p-item">
					<label class="lab-item">关联合伙人 :</label>
					<select name="q_tuiJIanMemberId" class="txt" onchange="listParterSignNo(this)" id="q_tuiJIanMemberId">
					<option value="000">--请选择--</option>
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
						,autoRowHeight:false
						,fitColumns:false
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/getParterTuijianList'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="parterName" width="150" align="center" halign="center">合伙人</th>
				<th field="areaName" width="180" align="center" halign="center">区域名称</th>  
	            <th field="brandSales" width="120" align="center" halign="center">品牌袜销售额</th>  
	            <th field="brandPercent" width="180" align="center" halign="center">品牌袜提点</th>  
	            <th field="luowaSales" width="150" align="center" halign="center">裸袜销售额</th>  
	            <th field="luowaPercent" width="150" align="center" halign="center">裸袜提点</th>  
	            <th field="salesSum" width="150" align="center" halign="center">总销售额</th>  
	            <th field="percentSum" width="150" align="center" halign="center">总提点</th>  
	            <th field="startTime" width="150" align="center" halign="center">开始时间</th>  
	            <th field="endTime" width="150" align="center" halign="center">结束时间</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />