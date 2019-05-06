<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function closeWin(){
		$("#ordersdialog,window.parent.document").window("close");
	}
</script>

<form id="roleResForm1" method="post"  class="form-contbox">
	 <dl class="dl-group">
		<dt class="dt-group"><span class="s-icon"></span>佣金信息</dt>
		<div style="margin-top: 5px;"class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item">用户: </label><span id="memberNameSpan">${parterSign.memberName!}</span>
			</p>
		</div>
	    <table id="tt" class="easyui-datagrid" style="width:530px;height:200px;">  
	    	<thead>
            <tr>
                <th field="name1" align="center" >佣金类型</th>
                <th field="name2" width="80" align="center" >袜类型</th>
                <th field="name3" width="90" align="center" >开始时间</th>
                <th field="name4" width="90" align="center" >结束时间</th>
                <th field="name5" width="80" align="center" >佣金比例</th>
            </tr>                   
		    </thead>     
			    <tbody> 
		<#if ParterPercent ??>
		<#list ParterPercent as ParterPercent>
		        <tr>
		        	<td align="center">
						<#if ParterPercent.percentType == 'self' >
							合伙人佣金
						<#else>
							推荐合伙人佣金
						</#if>
					</td>
		        	<td align="center">
						<#if ParterPercent.type == 'brand' >品牌袜
						<#else>裸袜</#if>
					 </td>
		        	<td align="center">
		        		${(ParterPercent.startTime?string('yyyy-MM-dd'))!''}
					</td>
		        	<td align="center">
		        		${(ParterPercent.endTime?string('yyyy-MM-dd'))!''}
		        	</td>
		        	<td align="center">
		        		${ParterPercent.percent}
		        	</td>
		        </tr>  
		</#list>
		</#if>
				</tbody>
			</table>
		<div>
			<p class="p-item p-btn">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete"
					onclick="closeWin();">关闭</a>
			</p>
		</div>
</form>