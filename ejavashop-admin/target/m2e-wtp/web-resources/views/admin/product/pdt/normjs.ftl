<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/mask.js"></script>

<script language="javascript">
	var attrMap = {};
	var skuMap = {};
    $(function () {
        <#if product?? && (product.isNorm)?string == "2">
        $('#normDiv').show();
        $(".sku").hide();
        </#if>
        //是否启用规格
        $("input[name^='isNorm']").change(function () {
            if (2 == $(this).val()) {
                $('#normDiv').show();
                $('.sku').hide();
            } else {
                $('#normDiv').hide();
                $('.sku').show();
            }
        });
        
        //init sku list terry 20160706
        $('input[name="isNorm"]').eq(1).attr("checked",true);
        $('#normDiv').show();
        $('.sku').hide();
        //init sku list terry 20160706 end
        
        $('.styleStock').live('blur', function () {
            if($(this).val() == ''){
                $(this).val(0);
            }
        });
        $('.stylePrice').live('blur', function () {
            if(parseFloat($(this).val()) < parseFloat($('#protectedPrice').val())){
                alert("货品价格不能小于保护价");
                $(this).focus();
            }
			stayTwo(this);
        	digitOnly(this);
			var index = $(".stylePrice").index(this);
			if (index == 0) {
				syncValueWithClass($(this).val(), ".stylePrice");
			}
        });
        $('.styleStock').live('focus', function () {
            if($(this).val() == '0'){
                $(this).val('');
            }
        });
        $('.stylePrice').live('focus', function () {
            if($(this).val() == '0.0'){
                $(this).val('');
            }
        });

        //自定义规格事件
        <#if normList?? && normList?size == 2>
        <#assign map0 = normList[0]>
        <#assign map1 = normList[1]>
        <#assign title0 = map0.name>
        <#assign title1 = map1.name>
        <#assign list0 = map0.attrList>
        <#assign list1 = map1.attrList>
        <#list list0 as list0>
        <#assign id0 = list0.id>
        <#assign name0 = list0.name>
        <#if list0.checked?? && (list0.checked)?string=='true'>
        if (0 in attrMap) {
            attrMap[0]["names"].push('attr_${list0_index}');
        } else {
            attrMap[0] = {};
            attrMap[0]["title"] = '${title0!''}';
            attrMap[0]["names"] = new Array();
            attrMap[0]["names"].push('attr_${list0_index}');
        }
        </#if>
        </#list>

        <#list list1 as list1>
        <#assign id1 = list1.id>
        <#assign name1 = list1.name>
            <#if list1.checked?? && (list1.checked)?string=='true'>
            if (1 in attrMap) {
                attrMap[1]["names"].push('attr_${list1_index}');
            } else {
                attrMap[1] = {};
                attrMap[1]["title"] = '${title1!''}';
                attrMap[1]["names"] = new Array();
                attrMap[1]["names"].push('attr_${list1_index}');
            }
            </#if>
        </#list>

        </#if>
        
        $('input[name^="attr_"]').live('change', function () {
        	//颜色属性
        	if($(this).closest('.normtype_color').length>0){
        		//如果是自定义属性，必须先输入值
        		if($(this).attr('colortype')&& $(this).attr('colortype') == 'custom'){
        			var sibval_ = $(this).siblings().val();
        			if(!sibval_){
        				$(this).attr("checked",false);
        				$.messager.alert('提示','请先输入颜色名称');
        				return;
        			} else{
        				if($(this).is(":checked")){
	        				var normidx_ = Number($(this).attr('attrtype'));
	        				var attridx_ = Number($(this).attr('attrindex'))+1;
	        				var colval_ = Number($(this).val())+1;
	        				//生成下一个输入框
	        				var nextinput_ = "<li style='float: left;list-style: none;width: 100px;display: inline-flex;'>" +
	        						"<input name='attr_"+attridx_+"' type='checkbox' colortype='custom'" +
	        						"	id='attr_"+attridx_+"' attrindex='"+attridx_+"' attrtype='"+normidx_+"' value='"+colval_+"' />" +
	        						"<input name='cuscolr' placeholder='其它颜色' style='width: 60px;' type='text'/>" +
	        						"</li>";
	        				$(this).parent().after(nextinput_);
        				} else{
        					//移除最后一个元素
        					$(this).parent().siblings("li:last").remove();
        					$(this).siblings().val('');
        				}
        			}
        		}
        		//生成sku上传图片页面代码
        		generateSKUHtml(this);
        		//如果选择了其它属性，则加入规格列表
//         		var others_ = $('.normtype_others').find("input[type='checkbox']:checked");
//         		if(others_.length>0){
//         			$.each(others_,function(idx1_,this1_){
        				//模拟点击
        				generateNormHTML(this);
//         			});
//         		}
        		
        		//加载图片上传控件
        	    $("[name=skupicfile]").skuupload();
        	} else{
        		generateNormHTML(this);
        	}
        });


        <#--规格属性编辑,只有一列选中的时候,第二列禁用-->
        var attrType0 = $("input[attrtype='0'");
        var attrType1 = $("input[attrtype='1'");
        var isReadOnly0 = false;
        var isReadOnly1 = false;
        if(attrType0 && attrType0.length > 0){
            for(var i = 0; i < attrType0.length; i ++){
                isReadOnly0 = $(attrType0[i]).is(':checked');
                if (isReadOnly0){
                    break;
                }
            }
        }
        if(attrType1 && attrType1.length > 0){
            for(var i = 0; i < attrType1.length; i ++){
                isReadOnly1 = $(attrType1[i]).is(':checked');
                if (isReadOnly1){
                    break;
                }
            }
        }

        if(!isReadOnly0 && isReadOnly1){
            for(var i=0; i < $("input[attrtype='0'").length; i ++){
                var attrtype = $("input[attrtype='0'")[i];
                $(attrtype).attr('disabled','true');
            }
        }

        if(isReadOnly0 && !isReadOnly1){
            for(var i=0; i < $("input[attrtype='1'").length; i ++){
                var attrtype = $("input[attrtype='1'")[i];
                $(attrtype).attr('disabled','true');
            }
        }
        
        $(".styleStock").live("keyup", function() {
			/* terry 20160712为了初始化方便
			var index = $(".styleStock").index(this);
			if (index == 0) {
				syncValueWithClass($(this).val(), ".styleStock");
			}
			*/
			var totalStock = 0;
			$(".styleStock").each(function() {
				var currVal = parseInt($(this).val());
				if (isNaN(currVal)) currVal = 0;
				totalStock += currVal;
			});
			$("#productStockHidden").val(totalStock);
			$("#productStock").val(totalStock);
		});
        
        $(".stylePrice").live("keyup", function() {
        	digitOnly(this);
			var index = $(".stylePrice").index(this);
			if (index == 0) {
				syncValueWithClass($(this).val(), ".stylePrice");
			}
		});
		
		//Terry add 20160712
		$("#productStock").click(function() {
			var totalStock = 0;
			$(".styleStock").each(function() {
				var currVal = parseInt($(this).val());
				if (isNaN(currVal)) currVal = 0;
				totalStock += currVal;
			});
			$("#productStock").val(totalStock);
			$("#productStockHidden").val(totalStock);
		});
		
		//Terry add 20160712 临时增加，方便初始化数据
		//$(".styleSku").live("keyup", function() {
		//	var index = $(".styleSku").index(this);
		//	var skuVal = $(this).val();
		//	$(".barCode").each(function() {
		//		var curreentIndex = $(".barCode").index(this);
		//		if (index == curreentIndex) {
		//			$(this).val(skuVal);
		//			return;				
		//		}
		//	});
		//});
		
        $(".styleSku").live("blur", function() {
        	var obj = this;
	    	var value = $(obj).val().trim();
	    	
	    	if(value!="") {
	    		var isRepeat = false;
				var curreentIndex = $(".styleSku").index(obj);
		    	$(".styleSku").each(function() {
					index = $(".styleSku").index(this);
					if (index != curreentIndex) {
						if ($(this).val().trim() == value) {
							alert("重复的sku");
							isRepeat = true;
							return false;
						}					
					}
				});
				
				if (isRepeat) {
					$(this).val("");
					$(this).focus(); 
					return;
				}
				$.ajax({
					type : "POST",
					url :  '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/validatesku',
					data : {
						sku:value,type:"",
						sellerId:$("#sellerId").val()
					},
					dataType : "json",
					success : function(data) {
						if(data.message!=""){
							$.messager.show({
								title:"提示",
								msg:data.message
							});
							$(obj).val("");
							//$(obj).focus();
						}
					},
					error : function() {
						alert("数据加载失败！");
					}
				});    		
	    	}
		});
		
        $(".barCode").live("blur", function() {
			checkBarCode(this, "重复的商品条码");
		});
    });
    
    function checkBarCode(obj, msg) {
		var value = $(obj).val().trim();
		if (value!="") {
			var isRepeat = false;
			var curreentIndex = $(".barCode").index(obj);
	    	$(".barCode").each(function() {
				index = $(".barCode").index(this);
				if (index != curreentIndex) {
					if ($(this).val().trim() == value) {
						alert(msg);
						isRepeat = true;
						return false;
					}					
				}
			});
		}
		if (isRepeat) {
			$(obj).val("") ;
			$(obj).focus();
			return false;
		}
		validateBarCode(value, obj);
    }
    
    function validateBarCode(value, obj){
    	$.ajax({
			type : "POST",
			url :  '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/validatesku',
			data : {
				barCode:value, sku:"",
				sellerId:$("#sellerId").val()
			},
			dataType : "json",
			success : function(data) {
				if(data.message!=""){
					alert(data.message);
					$(obj).val("");
					$(obj).focus();
				}
			},
			error : function() {
				alert("数据加载失败！");
			}
		});  
    }
    
    
//Terry add 20160706
function syncValueWithClass(val, cls) {
	$(cls).each(function() {
		$(this).val(val);
	});
}
    
function digitOnly(obj) {
	if(!$(obj).val().match(/^\d{1,5}\.?\d?\d?$/)) {
		$(obj).val($(obj).val().substring(0, $(obj).val().length - 1));
	}
}
//Terry add 20160706 end

function stayTwo(obj) {
    if($(obj).val() == ''){
        $(obj).val('0.0');
    }
    var val = $(obj).val().split(".");
    if (val.length > 1) {
    	var vv = val[1] + "00";
    	return $(obj).val(val[0] + '.' + vv.substring(0, 2));
    }
    else {
    	return $(obj).val(val[0] + '.00');
    }
}

    function generateSKUHtml(obj){
       	var attrType = $(obj).attr("attrtype");
        var name = $(obj).attr("name");
        if ($(obj).attr("checked")) {
        	//图片默认值
        	//移除之前的
        	var existspics_ = $("#normDiv").find("input[name='skupic_"+$(obj).val()+"']");
        	if(existspics_.length>0){
        		existspics_.remove();
        	}
        	
        	var html_ = "<input name='skupic_"+$(obj).val()+"' attrid='"+
        		$(obj).val()+"' type='hidden' value='-1' colortype='"+$(obj).attr('colortype')+"'>";
        	$("#custom-color-ul").after(html_);
            if (attrType in skuMap) {
                skuMap[attrType]["names"].push(name);
            } else {
                skuMap[attrType] = {};
                var colorinput = $(obj).siblings("input[name='cuscolr']");
                var title = "";
                if(colorinput.length > 0){
                	title = $(obj).parent().parent().parent().find("p").attr("normname");
                } else{
	                title = $($(obj).parent().parent().parent().
	                		parent().children()[0]).text().trim();
	                title = title.substring(0, title.length - 1);
                }
                skuMap[attrType]["title"] = title;
                skuMap[attrType]["names"] = new Array();
                skuMap[attrType]["names"].push(name);
            }
        } else {
        	//删除其图片
        	$("#normDiv").find("input[name='skupic_"+$(obj).val()+"']").remove();
            if (attrType in skuMap) {
                //删除sku信息
                skuMap[attrType]["names"].remove(name);
                if (skuMap[attrType]["names"].length == 0) {
                    delete skuMap[attrType];
                }
            }
        }
        var columnName = '';//循环构造列名
        var keys = Object.keys(skuMap);
        if (keys && keys.length > 0) {
            for (var i = 0; i < keys.length; i++) {
                columnName += "<td width=\"10%\" style=\"padding:6px;\">" + skuMap[keys[i]]["title"] + "</td>";
            }
        }

        var htmlArray = new Array();
        getSKUTrArray(htmlArray, '', skuMap, 0);
      
        $('div[name="skudiv"]').remove();//删除动态表格
        var skutableHtml = "<div class=\"fluidbox\" name=\"skudiv\">" +
                "<table name=\"skuTable\" width=\"86%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-left:140px\">" +
                "<tbody>" +
                "<tr style=\"background:#CCC;border:1px solid #e2e1e1;font-size:12px;\">" +
                columnName +
                "<td width=\"35%\">规格图片</td><td>&nbsp;</td></tr>";
        if (htmlArray && htmlArray.length > 0) {
            for (var i = 0; i < htmlArray.length; i++) {
            	skutableHtml += htmlArray[i];
            }
        }
        
        skutableHtml += "</tbody></table></div>";
        $('.normtype_color').append(skutableHtml);
    }
    
    function generateNormHTML(obj){
        var attrType = $(obj).attr("attrtype");
        var name = $(obj).attr("name");
        if ($(obj).attr("checked")) {
            if (attrType in attrMap) {
                attrMap[attrType]["names"].remove(name);
                attrMap[attrType]["names"].push(name);
            } else {
                attrMap[attrType] = {};
                var colorinput = $(obj).siblings("input[name='cuscolr']");
                var title = "";
                if(colorinput.length > 0){
                	title = $(obj).parent().parent().parent().find("p").attr("normname");
                } else{
	                title = $($(obj).parent().parent().parent().
	                		parent().children()[0]).text().trim();
	                title = title.substring(0, title.length - 1);
                }
                attrMap[attrType]["title"] = title;
                attrMap[attrType]["names"] = new Array();
                attrMap[attrType]["names"].push(name);
            }
        } else {
            if (attrType in attrMap) {
                attrMap[attrType]["names"].remove(name);
                if (attrMap[attrType]["names"].length == 0) {
                    delete attrMap[attrType];
                }
            }
        }
        var columnName = '';//循环构造列名
        var keys = Object.keys(attrMap);
        if (keys && keys.length > 0) {
            for (var i = 0; i < keys.length; i++) {
                columnName += "<td width=\"3%\" style=\"padding:3px;\">" + attrMap[keys[i]]["title"] + "</td>";
            }
        }

        var htmlArray = new Array();
        getTrArray(htmlArray, '', attrMap, 0);
        
        $('div[name="dyTable"]').remove();//删除动态表格
        var tableHtml = "<div class=\"fluidbox\" name=\"dyTable\">" +
                "<table name=\"normTable\" width=\"86%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-left:140px\">" +
                "<tbody>" +
                "<tr style=\"background:#CCC;border:1px solid #e2e1e1;font-size:12px;\">" +
                columnName +
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">sku</td>" +
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">库存</td>" +
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">PC价格</td>" +
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">商品条码（EA）</td>"+
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">商品条码（PL）</td>"+
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">商品条码（CS）</td>"+
                "<td width=\"3%\" style=\"padding:3px;text-align:center;\">是否虚拟组套(0:否，1：是)</td>";
             // "<td width=\"15%\">mobile价格</td></tr>";

        if (htmlArray && htmlArray.length > 0) {
            for (var i = 0; i < htmlArray.length; i++) {
                tableHtml += htmlArray[i];
            }
        }
        
        tableHtml += "</tbody></table></div>";
        $('#normDiv').append(tableHtml);
    }

    function getSKUTrArray(html, tds, map, level) {
        var keys = Object.keys(map);
        if (keys && keys.length > 0) {
            var names = map[keys[level].toString()]["names"];
            for (var i = 0; i < names.length; i++) {
                var li_ = $("[name=" + names[i] + "][attrtype=" + keys[level].toString() + "]").parent();
                var textinput_ = li_.find("input[type='text']");
                var text = textinput_.length<1?li_.text().trim():textinput_.val();
                var selected_ = $("[name=" + names[i] + "][attrtype=" + keys[level].toString() + "]").parent().find("input[type='checkbox']");
                var value = selected_.val();
                var colortype = selected_.attr('colortype');
                if (level == keys.length - 1) {
                    var tmptds = tds + "<td style='padding:10px'>" + text +
                            "<input name=\"skuattrid_"+value+"\" type=\"hidden\" value=\""+value+"\">" +
                            "</td>";
                    var skufilehtml_ = "<tr style=\"border:1px solid #e2e1e1\" id=\"skuAttrTr_"+value+"\">" + tmptds +
		                    "<td style=\"padding:10px 0px;\">" +
		                    "<div name='skupicfile' action='' multiparam='{\"url\":\"${currentBaseUrl}uploadSKUImage?attrid="+
		                    		value+"&colortype="+colortype+"&uploadtype=1\"," +
		                    "\"validate\":{\"fileSize\":{\"value\":2048000,\"errMsg\":\"上传图片不允许超过2M\"}, " +
		                    "\"fileMaxNum\":{\"value\":1,\"errMsg\":\"只能上传一张图片\"},\"fileType\":" +
		                    "{\"value\":\"img\",\"errMsg\":\"上传图片后缀只支持:image、gif、jpeg、jpg、png\"}}," +
		                    "\"callback\":\"skuuploadCallback\"}'>"+
		                "</td>";
		            var thiscolorpic_ = $("#normDiv").find("input[name='skupic_"+value+"']");
		            if(thiscolorpic_.length > 0 && thiscolorpic_.val()!=-1){
		            	  skufilehtml_ += "<td><img style='max-width: 40px;' src='${domainUrlUtil.EJS_IMAGE_RESOURCES}"+thiscolorpic_.val()+"'/></td>";
		            }
		            skufilehtml_ += "</tr>";
                    html.push(skufilehtml_);
                } else {
                    tds +=  "<td style='padding:10px'>" + text +
                            "<input name=\"skuattrid_"+value+"\" type=\"hidden\" value=\""+value+"\">" +
                            "</td>";
                   	getSKUTrArray(html, tds, map, level + 1);
                }
                if (level == 0) {
                    tds = '';
                }
            }
        }
    }
    
    function skuuploadCallback(obj){
    	//移除之前的
    	var existspics_ = $("#normDiv").find("input[name='skupic_"+obj.attrid+"']");
    	if(existspics_.length>0){
    		existspics_.remove();
    	}
    	
    	var html_ = "<input name='skupic_"+obj.attrid+"' attrid='"+obj.attrid+
    		"' value='"+obj.url+"' colortype='"+obj.colortype+"' type='hidden'>";
    	$("div[name='skudiv']").before(html_);
    }
    
    function getTrArray(html, tds, map, level) {
        var keys = Object.keys(map);
        if (keys && keys.length > 0) {
            var names = map[keys[level].toString()]["names"];
            for (var i = 0; i < names.length; i++) {
  				var li_ = $("[name=" + names[i] + "][attrtype=" + keys[level].toString() + "]").parent();
                var textinput_ = li_.find("input[type='text']");
                var text = textinput_.length<1?li_.text().trim():textinput_.val();
                var value = $("[name=" + names[i] + "][attrtype=" + keys[level].toString() + "]").parent().find("input[type='checkbox']").val();
               	var curentselect_ = $("[name=" + names[i] + "][attrtype=" + 
                    	keys[level].toString() + "]")
                var currentNormname_ = curentselect_.siblings().length>0?
				 	curentselect_.parent().parent().prev().prev().attr("normname"):
				 	curentselect_.parent().parent().parent().prev().attr("normname");
				var curVal_ = value+"!@#"+currentNormname_+"!@#"+text;
				//Terry add 20160706
				var mallPcPriceVal = $('#mallPcPrice').val();
				
                if (level == keys.length - 1) {
                    var tmptds = tds + "<td style='padding:10px'>" + text +
                            "<input name=\"normAttrId\" type=\"hidden\" value=\""+curVal_+"\">" +
                            "</td>";
                    html.push(
                            "<tr style=\"border:1px solid #e2e1e1\" name=\"normAttrTr\">" + tmptds +
                                "<td style=\"padding:10px;\">" +
                                    "<input name=\"\" type=\"text\" id=\"inventory_details_sku_"+i+"\" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"\" class=\"styleSku\" onkeyup=\"javascript:this.value=this.value.replaceDot();\" onblur=\"this.value=this.value.trim()\">" +
                                "</td>" +
                                "<td style=\"padding:10px;\">" +
                                    "<input name=\"\" type=\"text\" id=\"inventory_details_stock_"+i+"\" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"0\" class=\"styleStock\" onkeyup=\"javascript:isNaN(this.value)?this.value='':this.value=this.value\">" +
                                "</td>" +
                                "<td style=\"padding:10px;\">" +
                                    "<input name=\"\" type=\"text\" id=\"inventory_details_pprice_"+i+"\" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"" + mallPcPriceVal + "\" class=\"stylePrice\">" +
                                    "<input name=\"\" type=\"hidden\" id=\"inventory_details_mprice_"+i+"\" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"" + mallPcPriceVal + "\" class=\"stylePrice\">" +
                                "</td>" +
                                "<td style=\"padding:10px;\">" +
                                    "<input name=\"\" type=\"text\" id=\"inventory_details_barCode_"+i+"\" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"\" class=\"barCode1\" onkeyup=\"javascript:this.value=this.value.replaceDot();\">" +
                                "</td>" +
                                "<td style=\"padding:10px;\">" +
                                "<input name=\"\" type=\"text\" id=\"inventory_details_barCodePL__"+i+"\" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"\" class=\"barCode\">"+
                            "</td>"+
                            "<td style=\"padding:10px;\">" +
                              "  <input name=\"\" type=\"text\" id=\"inventory_details_barCodeCS__"+i+" \" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"\" class=\"barCode\">"+
                            "</td>" +
                           " <td style=\"padding:10px;\">" +
                                "<input name=\"\" type=\"text\" id=\"inventory_details_isVirtualBom__"+i+" \" style=\"border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;width:100px;\" value=\"0\" >"+
                            "</td>" +
                            "</tr>");
                } else {
                    tds +=  "<td style='padding:10px'>" + text +
                            "<input name=\"normAttrId\" type=\"hidden\" value=\""+curVal_+"\">" +
                            "</td>";
                    getTrArray(html, tds, map, level + 1);
                }
                if (level == 0) {
                    tds = '';
                }
            }
        }
    }

    String.prototype.replaceDot = function (){
	    return this.replace(",","").replace(";","").replace("!@#",""); 
    } 
    
    Array.prototype.remove = function (val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    };
    Array.prototype.indexOf = function (val) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) return i;
        }
        return -1;
    };
    
    function change_mobile_proce(i){
    	var price = $("#inventory_details_pprice_"+i).val();
    	if(price!=null){
    		$("#inventory_details_mprice_"+i).val(price);
    	}
    }
    
</script>