var mail_city_count = 0;
var express_city_count = 0
var ems_city_count = 0;
var mail_batch = 0;
var express_batch = 0;
var ems_batch = 0;
$(function() {
	//编辑页面初始化重量模板
	if (id && transType == "2") {
		$.messager.progress({
			text : "加载中..."
		});
		jQuery.ajax({
			url : currentBaseUrl+'/transportType',
			data:{
				id:id,
				type:"weight"
			},
			success : function(data) {
				jQuery("#trans_detail").empty().html(data);
				$.messager.progress('close');
			}
		});
	}
	
	$("#back").click(function() {
				location.href = currentBaseUrl;
			});

	$("#add").click(function() {
		var isChecked = false;
		$(":checkbox[id^='transtype_']").each(function(idx,e){
			if($(this).attr('checked')=='checked'){
				isChecked = true;
				return false;
			}
		});
		if(!isChecked){
			$.messager.alert('提示', '请至少选择一种运送方式');
			return false;
		}
		if ($('#addForm').form('validate')) {
			$.messager.progress({
				text : "加载中..."
			});
			$('#addForm').submit();
		}
	});

	//索引从0开始，长度-2去掉table表头
	mail_city_count = jQuery("#mail_trans_city_info table tr").length - 1;
	express_city_count = jQuery("#express_trans_city_info table tr").length - 1;
	ems_city_count = jQuery("#ems_trans_city_info table tr").length - 1;
	
	jQuery("#mail_city_count").val(mail_city_count);
	jQuery("#express_city_count").val(express_city_count);
	jQuery("#ems_city_count").val(ems_city_count);

	jQuery(":checkbox").live("click", function() {
				var ck = jQuery(this).attr("checked");
				if (ck == "checked" || ck == true) {
					var id = jQuery(this).attr("id");
					jQuery("#" + id + "_info").show();
				} else {
					var id = jQuery(this).attr("id");
					jQuery("#" + id + "_info").hide();
				}
			});
	jQuery("a[id^=batch_set_]").live("click", function() {
				jQuery(this).parent().parent().find(":checkbox").show();
				var type = jQuery(this).attr("transType");
				jQuery("#" + type + "_trans_city_op").show();
				jQuery(this).hide();
				jQuery("#batch_cancle_" + type).show();
				if (type == "mail") {
					mail_batch = 1;
				}
				if (type == "express") {
					express_batch = 1;
				}
				if (type == "ems") {
					ems_batch = 1;
				}
			});
	jQuery("a[id^=batch_cancle_]").live("click", function() {
				jQuery(this).parent().parent().find(":checkbox").hide();
				var type = jQuery(this).attr("transType");
				jQuery("#" + type + "_trans_city_op").hide();
				jQuery(this).hide();
				jQuery("#batch_set_" + type).show();
				if (type == "mail") {
					mail_batch = 0;
				}
				if (type == "express") {
					express_batch = 0;
				}
				if (type == "ems") {
					ems_batch = 0;
				}
			});
	jQuery("a[id^=batch_del_]").live("click", function() {
		jQuery(this).parent().parent()
				.find(":checkbox[checked=true][id^=trans_ck]").each(function() {
							jQuery(this).parent().parent().parent().parent()
									.remove();
						});
		jQuery("#mail_trans_all").attr("checked", false);
	});
	jQuery("a[id^=batch_config_]").click(function() {

			});
	jQuery(":checkbox[id$=mail_trans_all]").live("click", function() {
		var ck = jQuery(this).attr("checked");
		if (ck == "checked" || ck == true) {
			jQuery(this).parent().parent().parent().find(":checkbox").attr(
					"checked", true);
		} else {
			jQuery(this).parent().parent().parent().find(":checkbox").attr(
					"checked", false);
		}
	});
	jQuery(":radio[id=transType]").change(function() {
		var this_ = this;
		$.messager.confirm('确认', '正在切换计价方式，确定继续么？', function(r) {
			if (r) {
				$.messager.progress({
					text : "请等待..."
				});
				var type = $(this_).val()==2?"weight":"";
				jQuery.ajax({
					url : currentBaseUrl+'/transportType',
					data:{
						id:id,
						type:type
					},
					success : function(data) {
						jQuery("#trans_detail").empty().html(data);
						$.messager.progress('close');
					}
				});
			}else{
				//恢复原选中状态
				$(this_).parent().siblings("label").find(":radio").attr("checked","checked");
			}
		});
	});
});

function trans_city(id) {
	var the_id = "";
	var s = "";
	if (id == "express") {
		the_id = "express" + express_city_count;
		if (express_batch == 1) {
			s = '<tr index="'
					+ express_city_count
					+ '"><td><span class="width2"><i><input id="trans_ck_'
					+ express_city_count
					+ '" name="trans_ck_'
					+ express_city_count
					+ '" type="checkbox" value="" /></i><input id="express_city_ids'
					+ express_city_count
					+ '" name="express_city_ids'
					+ express_city_count
					+ '" type="hidden" value="" /><input id="express_city_names'
					+ express_city_count
					+ '" name="express_city_names'
					+ express_city_count
					+ '" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'
					+ id
					+ '">编辑</a></span><span class="width1" id="'
					+ the_id
					+ '">未添加地区</span></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_weight'
					+ express_city_count
					+ '" name="express_trans_weight'
					+ express_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_fee'
					+ express_city_count
					+ '" name="express_trans_fee'
					+ express_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_add_weight'
					+ express_city_count
					+ '" name="express_trans_add_weight'
					+ express_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_add_fee'
					+ express_city_count
					+ '" name="express_trans_add_fee'
					+ express_city_count
					+ '" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
		} else {
			s = '<tr index="'
					+ express_city_count
					+ '"><td><span class="width2"><i><input id="trans_ck_'
					+ express_city_count
					+ '" name="trans_ck_'
					+ express_city_count
					+ '" type="checkbox" value="" style="display:none;" /></i><input id="express_city_ids'
					+ express_city_count
					+ '" name="express_city_ids'
					+ express_city_count
					+ '" type="hidden" value="" /><input id="express_city_names'
					+ express_city_count
					+ '" name="express_city_names'
					+ express_city_count
					+ '" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'
					+ id
					+ '">编辑</a></span><span class="width1" id="'
					+ the_id
					+ '">未添加地区</span></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_weight'
					+ express_city_count
					+ '" name="express_trans_weight'
					+ express_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_fee'
					+ express_city_count
					+ '" name="express_trans_fee'
					+ express_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_add_weight'
					+ express_city_count
					+ '" name="express_trans_add_weight'
					+ express_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="express_trans_add_fee'
					+ express_city_count
					+ '" name="express_trans_add_fee'
					+ express_city_count
					+ '" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
		}
		jQuery("#" + id + "_trans_city_info table tr:last").after(s);
		jQuery("#" + id + "_trans_city_info").show();
		express_city_count = express_city_count + 1;
		jQuery("#express_city_count").val(express_city_count);
	}
	if (id == "ems") {
		the_id = "ems" + ems_city_count;
		if (ems_batch == 1) {
			s = '<tr index="'
					+ ems_city_count
					+ '"><td><span class="width2"><i><input id="trans_ck_'
					+ ems_city_count
					+ '" name="trans_ck_'
					+ ems_city_count
					+ '" type="checkbox" value="" /></i><input id="ems_city_ids'
					+ ems_city_count
					+ '" name="ems_city_ids'
					+ ems_city_count
					+ '" type="hidden" value="" /><input id="ems_city_names'
					+ ems_city_count
					+ '" name="ems_city_names'
					+ ems_city_count
					+ '" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'
					+ id
					+ '">编辑</a></span><span class="width1" id="'
					+ the_id
					+ '">未添加地区</span></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_weight'
					+ ems_city_count
					+ '" name="ems_trans_weight'
					+ ems_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_fee'
					+ ems_city_count
					+ '" name="ems_trans_fee'
					+ ems_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_add_weight'
					+ ems_city_count
					+ '" name="ems_trans_add_weight'
					+ ems_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_add_fee'
					+ ems_city_count
					+ '" name="ems_trans_add_fee'
					+ ems_city_count
					+ '" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
		} else {
			s = '<tr index="'
					+ ems_city_count
					+ '"><td><span class="width2"><i><input id="trans_ck_'
					+ ems_city_count
					+ '" name="trans_ck_'
					+ ems_city_count
					+ '" type="checkbox" value="" style="display:none;" /></i><input id="ems_city_ids'
					+ ems_city_count
					+ '" name="ems_city_ids'
					+ ems_city_count
					+ '" type="hidden" value="" /><input id="ems_city_names'
					+ ems_city_count
					+ '" name="ems_city_names'
					+ ems_city_count
					+ '" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'
					+ id
					+ '">编辑</a></span><span class="width1" id="'
					+ the_id
					+ '">未添加地区</span></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_weight'
					+ ems_city_count
					+ '" name="ems_trans_weight'
					+ ems_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_fee'
					+ ems_city_count
					+ '" name="ems_trans_fee'
					+ ems_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_add_weight'
					+ ems_city_count
					+ '" name="ems_trans_add_weight'
					+ ems_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="ems_trans_add_fee'
					+ ems_city_count
					+ '" name="ems_trans_add_fee'
					+ ems_city_count
					+ '" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
		}
		jQuery("#" + id + "_trans_city_info table tr:last").after(s);
		jQuery("#" + id + "_trans_city_info").show();
		ems_city_count = ems_city_count + 1;
		jQuery("#ems_city_count").val(ems_city_count);
	}
	if (id == "mail") {
		the_id = "mail" + mail_city_count;
		if (mail_batch == 1) {
			s = '<tr index="'
					+ mail_city_count
					+ '"><td><span class="width2"><i><input id="trans_ck_'
					+ mail_city_count
					+ '" name="trans_ck_'
					+ mail_city_count
					+ '" type="checkbox" value="" /></i><input id="mail_city_ids'
					+ mail_city_count
					+ '" name="mail_city_ids'
					+ mail_city_count
					+ '" type="hidden" value="" /><input id="mail_city_names'
					+ mail_city_count
					+ '" name="mail_city_names'
					+ mail_city_count
					+ '" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'
					+ id
					+ '">编辑</a></span><span class="width1" id="'
					+ the_id
					+ '">未添加地区</span></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_weight'
					+ mail_city_count
					+ '" name="mail_trans_weight'
					+ mail_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_fee'
					+ mail_city_count
					+ '" name="mail_trans_fee'
					+ mail_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_add_weight'
					+ mail_city_count
					+ '" name="mail_trans_add_weight'
					+ mail_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_add_fee'
					+ mail_city_count
					+ '" name="mail_trans_add_fee'
					+ mail_city_count
					+ '" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
		} else {
			s = '<tr index="'
					+ mail_city_count
					+ '"><td><span class="width2"><i><input id="trans_ck_'
					+ mail_city_count
					+ '" name="trans_ck_'
					+ mail_city_count
					+ '" type="checkbox" value="" style="display:none;" /></i><input id="mail_city_ids'
					+ mail_city_count
					+ '" name="mail_city_ids'
					+ mail_city_count
					+ '" type="hidden" value="" /><input id="mail_city_names'
					+ mail_city_count
					+ '" name="mail_city_names'
					+ mail_city_count
					+ '" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'
					+ id
					+ '">编辑</a></span><span class="width1" id="'
					+ the_id
					+ '">未添加地区</span></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_weight'
					+ mail_city_count
					+ '" name="mail_trans_weight'
					+ mail_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_fee'
					+ mail_city_count
					+ '" name="mail_trans_fee'
					+ mail_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_add_weight'
					+ mail_city_count
					+ '" name="mail_trans_add_weight'
					+ mail_city_count
					+ '" /></td><td><input type="text" value="1" class="in easyui-numberbox" id="mail_trans_add_fee'
					+ mail_city_count
					+ '" name="mail_trans_add_fee'
					+ mail_city_count
					+ '" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
		}
		jQuery("#" + id + "_trans_city_info table tr:last").after(s);
		jQuery("#" + id + "_trans_city_info").show();
		mail_city_count = mail_city_count + 1;
		jQuery("#mail_city_count").val(mail_city_count);
	}
	
	$(":text[class='in easyui-numberbox']").change(function(obj){
		if(isNaN(obj.target.value)){
			$(this).val(1);
			$(this).focus();
		}
	});
}
function edit_trans_city(obj) {
	var trans_city_type = jQuery(obj).attr("trans_city_type");
	var trans_index = jQuery(obj).parent().parent().parent().attr("index");
	jQuery.ajax({
		type : 'POST',
		url : domain + '/seller/operate/sellerTransport/transport_area',
		data : {
			"trans_city_type" : trans_city_type,
			"trans_index" : trans_index
		},
		success : function(data) {
			jQuery(".wrapper").append(data);
			var left = jQuery(obj).offset().left - 275;
			var top = jQuery(obj).offset().top + 32;
			jQuery(".area_box").css({
						"top" : top + "px",
						"left" : left + "px"
					}).show();
		}
	});
}

function remove_trans_city(obj) {
	jQuery(obj).parent().parent().parent().remove();
}

function closeWin() {
	$('#newstypeWin').window('close');
}