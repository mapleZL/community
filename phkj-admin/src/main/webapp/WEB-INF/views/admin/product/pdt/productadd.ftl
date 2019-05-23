<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/"/>

<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/skupicupload.js"></script>
<script language="javascript">
    $(function () {
        <#--加载图片上传控件-->
        $("[name=uploadImg]").multiupload();
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}create',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    alert("商品发布成功");
                    window.location.href='${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/add?cateId=1';
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                	$("#add").removeAttr("disabled");
                    refrushCSRFToken(data.csrfToken);
                    $.messager.alert('提示',data.message);
                }
                refrushCSRFToken(data.csrfToken);
            }
        };
        $("#back").click(function () {
            window.location.href = '${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/add?cateId=0';
        });
        $("#add").click(function () {
        	/* 本品会持续补货  选中状态则值为1前台显示。不选中状态则值为0前台不显示 */
        	var cxbhBool = $("#tzm_cxbh").attr("checked");
        	if(cxbhBool){
        		$("#tzm_cxbh_use").val(1);
        	}else{
        		$("#tzm_cxbh_use").val(0);
        	}
        	
        	//条码
        	var isRepeat = false;
        	$(".barCode").each(function() {
					if ($(this).val().trim() == "") {
						alert("商品条码不能为空");
						$(this).focus();
						isRepeat = true;
						return;
					}					
				});
        	
        	if(isRepeat){
        		return false;
        	}
        	
            <#--货品信息-->
            var isNorm = $('input[name="isNorm"]').filter(':checked').val();
            if (isNorm == 2){
            	//组装货品信息
                var normAttrTr = $('tr[name="normAttrTr"]');
                if(normAttrTr && normAttrTr.length > 0){
                    var productGoods = '';
                    for(var i = 0; i < normAttrTr.length; i ++){
                        var normAttrTrInput = $(normAttrTr[i]).find('input');
                        if(normAttrTrInput && normAttrTrInput.length > 0){
                            var normAttrTrInputVal = '';
                            for(var j = 0; j < normAttrTrInput.length; j++){
                                normAttrTrInputVal += $(normAttrTrInput[j]).val() + ' ,';
                            }
                            normAttrTrInputVal = normAttrTrInputVal.substr(0, normAttrTrInputVal.length -1);
                            productGoods += normAttrTrInputVal + ';';
                        }
                    }
                    productGoods = productGoods.substr(0, productGoods.length -1);
                    $('#productGoods').val(productGoods);
                }
            }else{
                var sku = $('#sku').val();
                if(sku == ''){
                    $.messager.alert('提示',"请填写sku");
                    return;
                }
            }
            //组装sku图片信息
            var skupics_ = $("#normDiv").find("input[name^='skupic_']");
            var skupicData = new Array();
            var normp_ = $(".normtype_color").find("p:first");
            var normname = normp_.attr('normname');
            var normid = normp_.attr('normid');
            var sep_ = "_@_";
            var skuuploaded = true;
           	$.each(skupics_,function(idx1_,pic_){
           		var checkedcolr_ = $(".normtype_color").
           			find("input[type='checkbox']:checked");
           		var attrid = $(this).attr('attrid');
           		var attrname = "";
           		var colortype = $(this).attr("colortype");
           		$.each(checkedcolr_,function(idx2_,obj2_){
           			if(attrid == $(this).val()){
           				var parentli_ = $(this).parent();
           				var textinput_ = parentli_.find("input[type='text']");
           				//自定义属性
           				if(textinput_.length > 0){
           					attrname = textinput_.val();
           				} else{
           					attrname = $(this).parent().text().trim();
           				}
           			}
           		});
           		
           		skupicData.push(normid+sep_+normname+sep_+
           			attrid+sep_+attrname+sep_+$(this).val()+sep_+colortype);
           	});
           	if(skupics_.length>0&&!skuuploaded){
           		return;
           	}
           	$("#skupics").val(skupicData);
//            	console.info(skupicData);
//            	return;
            $('#productBrandId').val($('#brandId').val());//品牌id
            var costPrice = $('#costPrice').val();//成本价
            var protectedPrice = $('#protectedPrice').val();//保护价
            var marketPrice = $('#marketPrice').val();//市场价
            var mallPcPrice = $('#mallPcPrice').val();//pc商城价
            var virtualSales = $('#virtualSales').val();//虚拟销量
            var actualSales = $('#actualSales').val();//实际销量
            var todayLimitNum = $('#todayLimitNum').val();//商品库存
            var upTime = $('#upTime').val();//上架时间
            var isInventedProduct = $("input[name='isInventedProduct'][checked]").val();//是否虚拟商品
            var isCxbh = $("#tzm_cxbh_use").val();//本品是否持续补货【仝照美】
            var refIds = $("#refIds").val();//推荐搭配
            var memberIds = $("#memberIds").val();//购买权限人
           // var sku_other = $("#sku_other").val();//sku辅料
            var description = UM.getEditor('myEditor').getContent();
            if(description == ''){
                $.messager.alert('提示',"请填写商品描述");
                return;
            }
            $('#description').val(description);//商品描述信息
            var packing = $('#packing').val();//包装清单
            if(packing == ''){
                $.messager.alert('提示',"请填写包装清单");
                return;
            }

            var sellerCateId = $("select[name^='sellerCate_']").last().val();//商家分类

			$("#productStockHidden").val($("#productStock").val());
			
			//Terry 20160712
			
			//Terry 20160712
			
            <#--商品图片-->
            var image = $('img[name^=prev_]');
            if (image && image.length > 0) {
                var imageSrc = '';
                for (var i = 0; i < image.length; i++) {
                    var imgSrc = $(image[i]).attr('src');
                    if (imgSrc.indexOf("resources") != -1)
                        continue;
                    imageSrc += imgSrc + ';';
                }
                if ('' != imageSrc) {
                    $('#imageSrc').val(imageSrc);//商品图片
                }
            }else{
                $.messager.alert('提示',"商品图片,至少传一张");
                return;
            }

            <#--商品属性-->
            var queryType = $('select[name=queryType]');
            var queryTypeVal = '';
            if (queryType && queryType.length > 0) {
            for (var i = 0; i < queryType.length; i++) {
                queryTypeVal += $(queryType[i]).val() + ';';
            	}
            }
         //   queryType = $('input[name=queryType]');
         //   if (queryType && queryType.length > 0) {
         //       for (var i = 0; i < queryType.length; i++) {
         //           queryTypeVal += ($(queryType[i]).attr('data') + ',' + $(queryType[i]).val() + ';');
         //       }
         //   }
             $('#queryType').val(queryTypeVal);//商品属性
            <#--自定义属性-->
            var custType = $('input[name=custType]');
            var custTypeVal = '';
            if (custType && custType.length > 0) {
                for (var i = 0; i < custType.length; i++) {
                	if($(custType[i]).attr('data') != '150,辅料' &&($(custType[i]).val()=="" || $(custType[i]).val()==null)){
                		var val = $(custType[i]).attr('data').substring(4);
                		$.messager.alert('提示', '自定义属性中 '+val+' 字段必填。');
                		return;
                	}
                    custTypeVal += $(custType[i]).attr('data') + ',' + $(custType[i]).val() + ';';
                }
            }
			custType = $('select[name=custType]');
			if (custType && custType.length > 0) {
	            for (var i = 0; i < custType.length; i++) {
	            	var val = $(custType[i]).val().split(',');
	            	if(val[2] == -1){
	            		$.messager.alert('提示', '自定义属性中 '+val[1]+' 为必选项。');
	            		return;
	            	}
	                custTypeVal += $(custType[i]).val() + ';';
	            }
            }
			$('#custAttr').val(custTypeVal);//自定义属性
            if (upTime == '') {
                $.messager.alert('提示', '上架时间必填。');
                return;
            }
            if (mallPcPrice < protectedPrice) {
                $.messager.alert('提示', '商城价不能低于保护价');
                return;
            }
           
            if ($('#addForm').form('validate')) {
	            $(this).attr("disabled",true);
                $('#addForm').ajaxSubmit(options);
            }
            
        });

        $('#mallPcPrice').blur(function () {
            var mallPcPrice = Number($(this).val());//pc商城价
            $('#protectedPrice').val(mallPcPrice);
           // $('#marketPrice').val(mallPcPrice);
            /*var protectedPrice = Number($('#protectedPrice').val());//保护价
            if (!isNaN(mallPcPrice) && !isNaN(protectedPrice) && mallPcPrice < protectedPrice) {
                $.messager.alert('提示', '商城价不能低于保护价');
                return;
            }*/
        });
//         $('#protectedPrice').blur(function () {
//             var mallPcPrice = Number($('#mallPcPrice').val());//pc商城价
//             var protectedPrice = Number($(this).val());//保护价
//             if (!isNaN(mallPcPrice) && !isNaN(protectedPrice) && mallPcPrice < protectedPrice) {
//                 $.messager.alert('提示', '商城价不能低于保护价');
//                 return;
//             }
//         });

        <#--鼠标移入移出图片-->
        $('.img').live('mouseover', function () {
            $(this).find('.img-box').show();
        }).live('mouseout', function () {
            $(this).find('.img-box').hide();
        })
        <#--删除图片-->
        $('.del-img').live('click', function () {
            $(this).parent().parent().parent().remove();
            $('[name=fileIndex]').val($('[name=fileIndex]').val() - 1);
            if ($('[name=fileIndex]').val() == 0) {
                $('#previewImgBox').hide();
            }
        })
        $("select[name^='sellerCate_']").live("change", function () {
            var level = $(this).attr("level");
            var id = $(this).attr("id");
            var parentId = $(this).val();

            if(parentId == ''){
                return;
            }

            $("select[name^='sellerCate_']").each(function () {
                var subLevel = $(this).attr("level");
                if (parseInt(subLevel) > parseInt(level)) {
                    $(this).remove();
                }
            })
            $.ajax({
                type: "get",
                url: "${currentBaseUrl}sellerCate/getByPid?id=" + parentId,
                dataType: "json",
                cache: false,
                success: function (data) {
                    if (data && data.length > 0) {
                        var children = "<select id='sellerCate_" + parseInt(level + 1) + "' name='sellerCate_" + parseInt(level + 1) + "' level=" + parseInt(level + 1) + " class='w210'>";
                        children += "<option value=''>请选择</option>";
                        $.each(data, function (i, n) {
                            children += "<option value=" + n.id + ">" + n.name + "</option>";
                        })
                        children += "</select>"
                        $('#' + id).after('&nbsp;&nbsp;&nbsp;' + children);
                    }
                }
            });
        });
        
        
        $("select[name^='parentId_']").live("change", function(){
            var level = $(this).attr("level");
            //控制最多只有三级分类
            var id = $(this).attr("id");
            var parentId = $(this).val();
            
            if(id == "parentId_1") {
            	$("#productCateId").val(parentId);
            	return;
            }

            $("select[name^='parentId_']").each(function(){
                var subLevel = $(this).attr("level");
                if (parseInt(subLevel) > parseInt(level)) {
                    $(this).remove();
                }
            })
            $.ajax({
                type:"get",
                url: "${currentBaseUrl}getCateById?id=" + parentId,
                dataType: "json",
                cache:false,
                success:function(data){
                    if (data && data.success && data.rows && data.rows.length > 0) {
                        var children = "<select id='parentId_" + parseInt(level +1) + "' name='parentId_"+parseInt(level +1)+"' level="+parseInt(level +1) +" class='txt w210'>";
                        children += "<option value=''>请选择</option>";
                        $.each(data.rows, function(i, n){
                            children += "<option value=" + n.id + ">" + n.name + "</option>";
                        })
                        children += "</select>"
                        $('#'+id).after('&nbsp;&nbsp;&nbsp;' +children);
                    }
                }
            });
        });
        //辅料浏览功能easyui实现
        <#--
        $("#fuliao_dialog_id").dialog({
        	title:"选择辅料",
        	width:600,
        	height:400,
        	modal:true,
        	closed:true,
        	href:"/seller/product/getProductLabelName",
        	buttons:[{
        		text:"确认",
        		iconCls:"icon-ok",
        		handler:function(){
        			var strVal="";//sku_other 隐藏input赋值
        			var strLabel="";//sku_other_label 显示input赋值
        			 $("[name='productLabelName']:checked").each(function(){
        				 strVal += $(this).val()+",";
        				// strLabel += $(this)[0].labels[0].innerHTML+",";
        				   strLabel += this.nextSibling.data+",";
        			 });
        			 strVal = strVal.substring(0,strVal.lastIndexOf(","));
        			 strLabel = strLabel.substring(0,strLabel.lastIndexOf(","));
        			 $("#sku_other").val(strVal);
        			 $("#sku_other_label").val(strLabel);
        			 
        			 $("#fuliao_dialog_id").dialog("close");
        		}
        	},{
        		text:"关闭",
        		iconCls:"icon-cancel",
        		handler:function(){
        			$("#fuliao_dialog_id").dialog("close");
        		}
        	}]
        });
     -->
    //end add by tongzhaomei 
    });
    function callback1(result) {
        //console.log("1"+result.names[0]);
    }
    //如果选择裸袜刚不显示辅料
    function luowo_display(val){
    	if(val==8){
    		//隐藏sku辅料
    		$("#tzm_flagid1").css("display","none");
    		$("#tzm_flagid2").css("display","none");
    	}else{
    		//显示sku辅料
    		$("#tzm_flagid1").css("display","block");
    		$("#tzm_flagid2").css("display","block");
    	}
    }
    
    
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">发布商品-填写商品详细信息
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/add?cateId=0">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品基本信息</dt>
                    <#--
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商品分类: </label>
		                    <#if cate?? && cate?size &gt; 0>
	                            <select id="parentId_0" name="parentId_0" level="0" class="txt w210">
	                                <#list cate as cc>
	                                    <option value="${(cc.id)!''}"> ${(cc.name)!''}</option>
	                                </#list>
	                            </select>
		                    </#if>
                        </p>
                    </div>
					
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>供应商名称: </label>
							<select name="sellerId" id="sellerId" class="txt w210">
								<#if sellers??>
			                     	<#list sellers as seller>
									<option value="${(seller.id)!}">${(seller.sellerName)!}</option>
									</#list>
								</#if>
							</select>            
						</p>
                    </div>-->
                    <input type="hidden" id="productCateId" name="productCateId" value="${(cateId)!'1'}"/>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品名称: </label>
                            <input type="text" id="name1" name="name1" value="${(product.name1)!''}" class="txt w400 easyui-validatebox" missingMessage="商品名称必填，3-100个字符" data-options="required:true,validType:'length[3,100]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>关键字: </label>
                            <input type="text" id="keyword1" name="keyword1" value="${(product.keyword)!''}" class="txt w400 easyui-validatebox" missingMessage="关键字必填，2-50个字符" data-options="required:true,validType:'length[2,50]'"/>
                        </p>
                    </div>
					<div class="fluidbox">
						<p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>SPU: </label>
                            <input type="text" id="productCode" name="productCode" value="${(product.productCode)!''}" class="txt w200 easyui-validatebox" missingMessage="SPU必填，3-20个字符" data-options="required:true,validType:'length[3,20]'" onblur="valiate_SPU()">
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">促销信息: </label>
                            <input type="text" id="name2" name="name2" value="${(product.name2)!''}" class="txt w200 easyui-validatebox" missingMessage="促销信息必填，2-100个字符" data-options="validType:'length[2,100]'"/>
                        </p>
                    </div>
                    <!-- 其他分类 数据字典中读取内容 -->
					<div class="fluidbox">
						<p class="p6 p-item">
                            <label class="lab-item">其他分类</label>
                            <@cont.select id="otherCategory" name="otherCategory" codeDiv="PRODUCT_QUERY_WORD" mode="1"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                             	商品关键字，用户检索检索商品，多个用逗号分割
                            </font>
                        </p>
					</div>

                    <!--手机价-->
                    <input type="hidden" id="malMobilePrice" name="malMobilePrice" value="${(product.malMobilePrice)!''}" />
                    
                    <input type="hidden" id="sellerCateId" name="sellerCateId" value="1"/>
					
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商城价: </label>
                            <input type="text" id="mallPcPrice" name="mallPcPrice" value="${(product.mallPcPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true" onblur="change_Mobile()"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>成本价: </label>
                            <input type="text" id="costPrice" name="costPrice" value="${(product.costPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
					
                    <div class="fluidbox">
                    <#if seller??>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>虚拟销量: </label>
                            <input type="text" id="virtualSales" name="virtualSales" value="${(product.virtualSales)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
                        </p>
                    </#if>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品库存: </label>
                            <input type="text" id="productStock" name="productStock1"
                                   value="${(product.productStock)!''}" class="txt w200 easyui-numberbox" readonly="true"/> <font style="color: #808080">无需输入，系统自动累加 </font>
                        </p>
                   	 	<input type="hidden" id="productStockHidden" name="productStock" value="${(product.productStock)!''}"/>
                    </div>

                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>预计上架时间: </label>
                            <input type="text" id="upTime" name="upTime" value="${(product.upTime)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w200" data-options="required:true"/>
                        </p>
                    </div>
                    <div id="fuliao_dialog_id">
                    </div>
                    <!-- 商品原价 和  套餐 -->
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">&nbsp;</font>商品原价: </label>
                            <input type="text" id="marketPrice" name="marketPrice" value="${(product.marketPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:false" />
                        </p>
                    </div>
                    <!-- end -->
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>是否商家推荐: </label>
                            <@cont.radio id="sellerIsTop" value="${(product.sellerIsTop)!''}" codeDiv="PRODUCT_IS_TOP" />
                        </p>
                    </div>
                </dd>
            </dl>
            <#if queryTypeAttr?? && queryTypeAttr?size &gt; 0>
                <dl class="dl-group" style="margin:20px 10px 20px 10px">
                <dt class="dt-group"><span class="s-icon"></span>商品属性</dt>
                <input type="hidden" id="queryType" name="queryType"/>
                <dd class="dd-group">
                    <div class="fluidbox">
                <#list queryTypeAttr as queryTypeAttr>
                	<#if queryTypeAttr_index % 2 == 0>
                    </div>
                    <div class="fluidbox">
                	</#if>
                    <p class="p6 p-item">
                        <label class="lab-item">${(queryTypeAttr.attrName)!''}: </label>
                        <#if queryTypeAttr.attrValList ?size &gt; 0>
                        <select name="queryType" level="0" class="w210">
                            <option value="${(queryTypeAttr.attrId)!''},${(queryTypeAttr.attrName)!''},-1">请选择</option>
                            <#list queryTypeAttr.attrValList as attr>
                                <option value="${(queryTypeAttr.attrId)!''},${(queryTypeAttr.attrName)!''},${(attr)!''}" <#if (queryTypeAttr.dbAttr)?? && attr == (queryTypeAttr.dbAttr)>selected</#if>>${(attr)!''}</option>
                            </#list>
                        </select>
                        <#else>
                        		<input type="text" name="queryType" data="${(queryTypeAttr.attrId)!''},${(queryTypeAttr.attrName)!''}" value="" class="txt w200"/>
                        </#if>
                    </p>
                </#list>
                    </div>
                </dd>
            </dl>
            </#if>
            <#if custTypeAttr?? && custTypeAttr?size &gt; 0>
                <dl class="dl-group" style="margin:20px 10px 10px 10px">
                    <dt class="dt-group"><span class="s-icon"></span>自定义属性</dt>
                    <input type="hidden" id="custAttr" name="custAttr"/>
                        <dd class="dd-group">
                            <div class="fluidbox">
                    <#list custTypeAttr as custTypeAttr>
                	<#if custTypeAttr_index % 2 == 0>
                    </div>
                    <div class="fluidbox">
                	</#if>
                	<p class="p6 p-item">
                	<label class="lab-item">${(custTypeAttr.attrName)!''}:</label>
                	<#if custTypeAttr.attrValList ?size == 0 >
	                        <#if custTypeAttr.attrId==179||custTypeAttr.attrId==180>
	                           <input name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200" data-options="min:0.01,max:99999,precision:2,required:true"/>
	                        <#elseif custTypeAttr.attrId==178>
	                        	<input name="custType" id="chicun" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200" onchange="vertifyThisVal(this.value)"/>
	                        <#elseif custTypeAttr.attrId==182>
	                        	<input name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200" data-options="min:1,max:99999,precision:0,required:true"/>
	                        <#else>
	                           <input name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200"/>
	                        </#if>
                    <#else>
                    	<select name="custType" level="0" class="w210">
                            <option value="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''},-1">请选择</option>
                            <#list custTypeAttr.attrValList as attr>
                                <option value="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''},${(attr)!''}" <#if (custTypeAttr.dbAttr)?? && attr == (custTypeAttr.dbAttr)>selected</#if>>${(attr)!''}</option>
                            </#list>
                        </select>
                    </#if>
                    </p>
                    </#list>
                            </div>
                        </dd>
                </dl>
            </#if>
            </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品详情描述</dt>
                <input type="hidden" id="description" name="description"/>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商品描述: </label>
                        <div style="padding-left: 140px;padding-top: 2px;"><#include "productdesc.ftl"/></div>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品图片</dt>
                <dd class="dd-group">
                    <span class="s-showbtn">
                        <div name="uploadImg" action="" index="${waitShow_index!''}" multiparam='{"url":"${currentBaseUrl}uploadFiles","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":5,"errMsg":"上传图片最多不能超过5张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
                            <a href="#" class="txt_white" style="margin-left:3px">添加图片</a>
                            <input type="hidden" name="fileIndex" value="0"/>
                        </div>
                        <div class="fluidbox">
	                        <p class="p12 p-item">
	                            <label class="lab-item">&nbsp;</label>
	                            <font style="color: #808080">
	                                图片建议像素（或保持该比例）：宽800，高800
	                            </font>
	                        </p>
	                    </div>
                    </span>
                </dd>
                <!-- 预览图片 -->
                <dd id='previewImgBox' style="display: none">
                    <input type="hidden" id="imageSrc" name="imageSrc"/>
                    <ul class='preview-img' id="preview-img">
                        <li style="display: none" id="prewtemplage">
                            <div class='img'>
                                <img width='150' height='150'>
                                <div class='img-box'>
                                    <a class='del-img' href='javascript:void(0);'>删除</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </dd>
                <!-- end -->
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item">帮助信息。</label>
                    </div>
                </dd>
            </dl>

            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="新增"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>
<script>
function valiate_SPU(){
		var spu = $("#productCode").val();
		var sku = $("#sku").val();
		if(spu!=""){
			$.ajax({
				type : "POST",
				url :  '${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/validate',
				data : {spu:spu,sku:sku},
				dataType : "json",
				success : function(data) {
				if(data.message!=""){
					$.messager.show({
						title:"提示信息",
						msg:data.message
						});
					$("#productCode").val("");	
					}
					},
				error : function() {
					jAlert("数据加载失败！");
					}
				});
			}
		if(sku!=""){
			$.ajax({
				type : "POST",
				url :  '${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/validate',
				data : {spu:spu,sku:sku},
				dataType : "json",
				success : function(data) {
				if(data.message!=""){
					$.messager.show({
						title:"提示信息",
						msg:data.message
						});
					$("#sku").val("");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}
	//add by lushuai 增加对尺寸格式的校验
	function vertifyThisVal(val){
		var regu = /^[1-9]\d*(\.\d+)?\*[1-9]*(\.\d+)?\*[1-9]*(\.\d+)?$/;
		var re = new RegExp(regu);
		if(val.match(regu)!=null){
			return true;
		}else{
			$("#chicun").val("");
			jAlert("请输入正确的格式 ，只能输入 数字*数字*数字 的格式，支持小数。");
		}
	}
	
	
	function change_Mobile(){
		var price = $("#mallPcPrice").val();
		if(price!=""){
			$("#malMobilePrice").val(price);
		}
	}
	//add by tongzhaomei 增加推荐搭配
	$('#pro').click(function(){
		$('#goodsDialog').dialog('open');
		$('#gd_dataGrid').datagrid('unselectAll');
		//动态设置easyui datagrid URL
		$('#gd_dataGrid').datagrid({
			url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/listnopage',
		    queryParams:queryParamsHandler()
		   });
	});
	//add by tongzhaomei 二次加工
	$('#pro2').click(function(){
		$('#packageDialog').dialog('open');
		$('#dataGrid').datagrid('unselectAll');
		//动态设置easyui datagrid URL
		$('#dataGrid').datagrid({
			url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productPackage/list',
		    queryParams:queryParamsHandler()
		   });
	});
	//add by tongzhaomei 选择辅料
	$('#pro3').click(function(){
		$('#labelDialog').dialog('open');
		$('#labeldataGrid').datagrid('unselectAll');
		//动态设置easyui datagrid URL
		$('#labeldataGrid').datagrid({
			url:'${domainUrlUtil.EJS_URL_RESOURCES}/seller/operate/productLabel/list',
		    queryParams:queryParamsHandler()
		   });
	});
	//add by tongzhaomei 指定具有购买权限的用户
	$('#pro4').click(function(){
		$('#memberDialog').dialog('open');
		$('#memberdataGrid').datagrid('unselectAll');
		//动态设置easyui datagrid URL
		$('#memberdataGrid').datagrid({
			url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/list',
		    queryParams:queryParamsHandler()
		   });
	});
</script>

</div>
<#include "/admin/commons/_detailfooter.ftl" />