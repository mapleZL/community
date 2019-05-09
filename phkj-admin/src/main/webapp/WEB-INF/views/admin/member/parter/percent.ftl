<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter"/>
<form id="roleResForm1" method="post" class="form-contbox">
     <input type="hidden" name="parterSignId"  value="${ParterSign.id}"/>
	 <dl class="dl-group">
		<dt class="dt-group"><span class="s-icon"></span>佣金信息</dt>
		<dd class="dd-group">
		<div style="margin-top: 5px;"class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item"><b>&nbsp;&nbsp;&nbsp;合伙人: </b></label><span id="memberNameSpan">${ParterSign.memberName!}</span>
			</p>
		</div>
		<div class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item"><b>佣金类型: </b></label>
			    <label><input type="radio" style="vertical-align:middle; margin-top:-2px;" name="percentType" value="self" id="self" checked="checked"/>合伙人佣金</label>
			    <label><input type="radio" style="vertical-align:middle; margin-top:-2px;" name="percentType" value="recommond" id="recommond"/>推荐合伙人佣金</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="lab-item"><b>袜类型: </b></label>
			    <label><input type="radio" style="vertical-align:middle; margin-top:-2px;" name="waType" value="brand" id="brand" checked="checked"/>品牌袜</label>
			    <label><input type="radio" style="vertical-align:middle; margin-top:-2px;" name="waType" value="luowa" id="luowa"/>裸&nbsp;袜</label>
			</p>
		</div>
		<hr>
		
    	    <table style="width:100%;height:auto;">
    	    	<thead>
	            <tr>
	                <th align="center">/</th>
	                <th align="center">开始时间</th>
	                <th align="center">结束时间</th>
	                <th align="center">佣金比例</th>
	            </tr>                   
			    </thead>                              
			    <tbody> 
		        <tr>
		        	<td align="center">第一阶段</td>
		        	<td align="center">
			        	<input type="text" id="startTime1" name="startTime1"
						class="txt w100 easyui-validatebox" missingMessage="开始时间必填"
						data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${(ParterSign.startTime?string('yyyy-MM-dd'))!''}' ,maxDate:'${(ParterSign.endTime?string('yyyy-MM-dd'))!''}'});"
						 readonly="readonly"/>
					 </td>
		        	<td align="center">
		        		<input type="text" id="endTime1" name="endTime1"
						class="txt w100 easyui-validatebox" missingMessage="结束时间必填"
						data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${(ParterSign.startTime?string('yyyy-MM-dd'))!''}' ,maxDate:'${(ParterSign.endTime?string('yyyy-MM-dd'))!''}'});"
						readonly="readonly"/>
					</td>
		        	<td align="center">
		        		<input type="text" name = "percent1" class="txt w100 easyui-validatebox"  data-options="required:true"/>
		        	</td>
		        </tr>       
		        <tr id= "second1">
		        	<td align="center">第二阶段</td>
		        	<td align="center">
						<input type="text" id="startTime2" name="startTime2"
						class="txt w100 easyui-validatebox" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime1\')}',maxDate:'${(ParterSign.endTime?string('yyyy-MM-dd'))!''}'});"
						readonly="readonly"/>
					 </td>
		        	<td align="center">
						<input type="text" id="endTime2" name="endTime2"
						class="txt w100 easyui-validatebox" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime2\')}',maxDate:'${(ParterSign.endTime?string('yyyy-MM-dd'))!''}'});"
						readonly="readonly"/>
					</td>
		        	<td align="center">
						<input type="text" name = "percent2" class="txt w100 easyui-validatebox"  />
		        	</td>
		        </tr>
		        <tr id= "second2">
		        	<td align="center">第三阶段</td>
		        	<td align="center">
			        	<input type="text" id="startTime3" name="startTime3"
						class="txt w100 easyui-validatebox" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime2\')}',maxDate:'${(ParterSign.endTime?string('yyyy-MM-dd'))!''}'});"
					 	readonly="readonly"/>
					 </td>
		        	<td align="center">
						<input type="text" id="endTime3" name="endTime3"
						class="txt w100 easyui-validatebox" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime3\')}',maxDate:'${(ParterSign.endTime?string('yyyy-MM-dd'))!''}'});"
						readonly="readonly"/>
					</td>
		        	<td align="center">
						<input type="text" name = "percent3" class="txt w100 easyui-validatebox" />
		        	</td>
		        </tr>
		        </tbody>
		    </table>
		    
		<div id="footer" data-options="region:'west'" style="margin:0 auto;" border="false">
			<p class="p-item p-btn">
				<a id="saveRuleRes1" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete"
					onclick="closeWin();">关闭</a>
			</p>
		</div>
</form>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		$('#saveRuleRes1').click(function() {
			$("#roleResForm1").form('submit', {
				url : "${currentBaseUrl}/saveParterPercent",
				success : function(e) {
					$(this).attr('disabled',false);
					$.messager.progress('close');
					$.messager.show({
						title : '提示',
						msg : e,
						showType : 'show'
					});
					closeWin();
				}
			});
		});
		
		$('#self').click(function (){
			$('#second1').show();
			$('#second2').show();
		});
		$('#recommond').click(function (){
			$('#second1').hide();
			$('#second2').hide();
		});
	});

	function closeWin(){
		$("#makePercent,window.parent.document").window("close");
	}
</script>