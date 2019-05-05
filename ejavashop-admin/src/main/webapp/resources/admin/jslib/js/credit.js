$(function() {
    
    $('#btn-search').click(function(){
		$('#dataGrid').datagrid('reload',queryParamsHandler());
    });
    
    $("#btn-reset").click(function(){
    	document.queryForm.reset();
    	// 重置numberbox
    	$("#queryForm").find("input[type='hidden']").each(function(){
    		$(this).val('');
    	});
    	$('#dataGrid').datagrid('reload',queryParamsHandler());
    });
    
    $("#a-gridAdd").click(function(){
    	location.href= currentBaseUrl + "/edit";
    });
    
    $("#a-gridEdit").click(function(){
    	var selected = $('#dataGrid').datagrid('getSelected');
		if (!selected) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
    	location.href= currentBaseUrl + "/edit?id="+selected.id;
    });
    
    $("#a-creditLog").click(function(){
    	var selected = $('#dataGrid').datagrid('getSelected');
		if (!selected) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
    	$("#creditlogDialog").dialog({
			title:'赊账记录',
            width: 990,
            height: 550,
            modal: true,
            iconCls : "icon-tip",
            maximizable: true,
            resizable: true,
            href: currentBaseUrl + "/creditlogDialog?id="+selected.id
		});
    });
    
	$("#submitconfirm").window({
		width : 700,
		height : 300,
		title : "账期调账确认",
		closed : true,
		 iconCls : "icon-help",
		shadow : true,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		modal:true
	});
	
    $("#a-save-change").click(function(){
    	var data = new Array();
    	// 遍历行
    	$("tr[id^='datagrid-row']").each(function(){
    		var rowsdata = new Array();
    		var editableLen = $(this).find("input[type='hidden']").length;
    		if(editableLen > 0){
				$(this).find("td").each(function(){
					if($(this).attr("field")=="memberName"){
						rowsdata.push($(this).find(".datagrid-cell").html());
					}
					if($(this).attr("field")=="used"){
						rowsdata.push("&yen;"+$(this).find(".datagrid-cell").html());
					}
					if($(this).attr("field")=="surplus"){
						rowsdata.push("&yen;"+$(this).find(".datagrid-cell").html());
					}
					if($(this).attr("field")=="state"){
						rowsdata.push($(this).find(".datagrid-cell").html());
					}
					if($(this).attr("field")=="quotaChange"){
						rowsdata.push("&yen;"+$(this).find("input[type='hidden']").val());
					}
					if($(this).attr("field")=="cycleChange"){
						rowsdata.push($(this).find("input[type='hidden']").val()+"天");
					}
					if($(this).attr("field")=="expireDateChange"){
						rowsdata.push($(this).find(".datagrid-cell").html());
					}
				});
				data.push(rowsdata);
    		}
    	});
    	var len_ = data.length;
    	if(len_ <1){
    		$.messager.alert('提示', '没有修改数据');
    		return;
    	}
    	
    	// 生成html
		var html_ = "";
    	$.each(data,function(){
    		html_ += "<tr>";
    		$.each(this,function(){
    			html_ += "<td>";
    			html_ += this;
    			html_ += "</td>";
    		});
    		html_ += "</tr>";
    	});
		$("#tbodyinfo").html(html_);
    	
    	$("#submitconfirm").window('open');
    });
    
});

function submitform(){
	var data = new Array();
	// 遍历行
	$("tr[id^='datagrid-row']").each(function(){
		var rowsdata = new Array();
		var editableLen = $(this).find("input[type='hidden']").length;
		if(editableLen > 0){
			$(this).find("td").each(function(){
				if($(this).attr("field")=="id"){
					rowsdata.push($(this).find("input[type='hidden']").val());
				}
				if($(this).attr("field")=="memberBalance"){
					rowsdata.push($(this).find("input[type='hidden']").val());
				}
				if($(this).attr("field")=="memberName"){
					rowsdata.push($(this).find(".datagrid-cell").html());
				}
				if($(this).attr("field")=="quotaChange"){
					rowsdata.push($(this).find("input[type='hidden']").val());
				}
				if($(this).attr("field")=="cycleChange"){
					rowsdata.push($(this).find("input[type='hidden']").val());
				}
			});
			data.push(rowsdata);
		}
	});
	var len_ = data.length;
	var creditinfo = data.join(";");
	if(len_ <1){
		$.messager.alert('提示', '没有修改数据');
		return;
	}
	$.messager.confirm('确认', '确定提交'+len_+'条修改吗？', function(r) {
		if (r) {
			// 用户余额如果为负值，给出警告
			var rowdatas_ = creditinfo.split(";");
			// 保证顺序
			var count = 0;
			$.each(rowdatas_,function(idx2_,e2_){
				var dataone_ = this.split(",");
				// 余额小于0
				if(Number(dataone_[1])< 0){
					$.messager.confirm('确认', '用户['+dataone_[2]+']有赊账记录，确认这些赊账已结清并更新此次赊账额度吗？', 
							function(r2) {
						if (r2) {
							count ++;
							console.info(rowdatas_.length);
							console.info(count);
							if(rowdatas_.length == count){
								$("#dataGrid").datagrid("loading");
								saveinfo(creditinfo);
							} 
						}
					});
				}  else{
					count ++;
					if(rowdatas_.length == count){
						$("#dataGrid").datagrid("loading");
						saveinfo(creditinfo);
					} 
				}
			});
		}
	});
}

function saveinfo(creditinfo){
	$.ajax({
		type: 'post',
		url : currentBaseUrl+'/doEdit?creditinfo=' + creditinfo,
		success : function(data) {
			if (data.success) {
				$.messager.show({
					title : '提示',
					msg : "修改成功",
					showType : 'show'
				});
			} else {
				var msg = data.message;
				if(!msg){
					msg = "修改失败，请稍后再试";
				}
				$.messager.alert("提示",msg);
			}
			$('#dataGrid').datagrid('reload');
			$.messager.progress('close');
			$('#submitconfirm').window('close');
		}
	});
}

function stateformt(value,row,index){
	return codeBox["MEMBER_CREDIT_STATE"][value];
}

function sourceformt(value,row,index){
	return codeBox["MEMBER_CREDIT_SOURCE"][value];
}

function logtype(value,row,index){
	return codeBox["MEMBER_CG_TYPE"][value];
}

//--------------dialog formatter bg-------------------//
function ordersnfmt(value, row, index) {
	return value ? value : "-";
}

function ordersamountfmt(value, row, index) {
	return value ? value : "-";
}

function ordersdatefmt(value, row, index) {
	return value ? value : "-";
}

function orderspaystatefmt(value, row, index) {
	return value ? value : "-";
}

function orderspaybeforefmt(value, row, index) {
	return value ? value : "-";
}

function orderspayafterfmt(value, row, index) {
	return value ? value : "-";
}

function expdatefmt(value, row, index) {
	return value ? value : "-";
}

function periodfmt(value, row, index) {
	return value ? value : "-";
}

function expdateafterfmt(value, row, index) {
	return value ? value : "-";
}

//--------------dialog formatter ed-------------------//

function settleformt(value,row,index){
	var checked = "";
	if(value == 1){
		checked = "checked disabled";
	}
	var html = "<input type='checkbox' "+ checked +" onchange='settlechange(this)'/>";
	return html;
}

function statusformt(value,row,index){
	var html = "";
	if(value == 1){
		html = "<input type='button' value='关闭' onclick='sotpOrOpenThisAcc(1)'/>"
	}else if(value == 2){
		html = "<input type='button' value='开启' onclick='sotpOrOpenThisAcc(2)'/>"
	}
	return html;
}

var endIndex;
function settlechange(obj){
	var selected = $('#dataGrid').datagrid('getSelected');
	var rowIndex=$('#dataGrid').datagrid('getRowIndex',selected);
	if(!$(obj).is(':checked')){
		reject(rowIndex);
	} else{
		onClickRow(rowIndex);
		$(".datagrid-btable").find("tr").each(function(){
			if($(this).attr("datagrid-row-index")==rowIndex){
				$(this).find("td").each(function(){
					if($(this).attr("field")=="quotaChange"){
						$(this).find(".numberbox").numberbox('setValue', selected.quota);
						$('#dataGrid').datagrid('validateRow', rowIndex);
					}
					if($(this).attr("field")=="cycleChange"){
						$(this).find(".numberbox").numberbox('setValue', 30);
						$('#dataGrid').datagrid('validateRow', rowIndex);
						
						
					}
				});
			}
		});
		setDate(rowIndex,30);
	}
}

function setDate(rowIndex, day) {
	var grid = $('#dataGrid');
	var view = $('.datagrid-view');
	for (var i = 0; i < view.length; i++) {
		if ($(view[i]).children(grid.selector).length > 0) {
			var view = $(view[i]).children('.datagrid-view2');
			var td = $(view).find(
					'.datagrid-body td[field="expireDateChange"]')[rowIndex]
			var div = $(td).find('div')[0];

			var now = new Date();
			now.setDate(now.getDate() + Number(day));
			$(div).text(now.Format("yyyy-MM-dd"));
		}
	}
}

function daychange(v, p) {
	var index = $(this).closest("tr[id^='datagrid-row']").attr(
			"datagrid-row-index");
	setDate(index, v);
}

function onClickRow(index) {
	$('#dataGrid').datagrid('selectRow', index)
			.datagrid('beginEdit', index);
	endIndex = index;
}

function reject(index) {
	$('#dataGrid').datagrid('cancelEdit', index);
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function quotaEndChange(v,p){
	if(!$("#q_quota_start").val()){
		$("#q_quota_start").numberbox('setValue', 0.01);
	}
	if(v < Number($("#q_quota_start").val())){
		$(this).numberbox('setValue', Number($("#q_quota_start").val()));
	}
}