<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>

<#include "productimgcss.ftl"/>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/skupicupload.js"></script>
<script language="javascript">
	//add by lushuai 增加对尺寸格式的校验
	function vertifyThisVal(val){
		var regu = /^[1-9]\d*(\.\d+)?\*[1-9]*(\.\d+)?\*[1-9]*(\.\d+)?$/;
		var re = new RegExp(regu);
		if(val.match(regu)!=null){
			return true;
		}else{
			$("#chicun").val("");
			alert ("请输入正确的格式 ，只能输入 数字*数字*数字 的格式，支持小数。")
		}
	}

    $(function () {
    
   	//判定如果是裸袜刚显示商标信息
   	var flag_val = $("#productBrandId").val();
   	if(flag_val==8){
   		$("#tzm_flagid1").css("display","block");
		$("#tzm_flagid2").css("display","block");
   	}
   	//发送异步ajax请求，动态更新SKU辅料显示的值
   	<#--
   	var sku_other_val = $("#sku_other").val();
   	if(sku_other_val!=""){
	   	$.ajax({
	   		type : "GET",
			url :  "/seller/product/getProductLabelName",
			data : {
				skuOther:sku_other_val
			},
			dataType : "json",
			success : function(data) {
				var strs="";
				for(var i=0;i<data.length;i++){
					strs += data[i].name +",";
				}
				strs = strs.substring(0,strs.lastIndexOf(","));
				$("#sku_other_label").val(strs);
			},
			error : function() {
				jAlert("数据加载失败！");
			}
	   	})
   	}
  //辅料浏览功能easyui实现
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
    	}],
    	onLoad:function(){
    		//获得所有 name 为productLabelName的checkbox数组
    		var productLabelNames = $("input[name='productLabelName']");
    		var skuOthers = sku_other_val.split(",");
    		for(var i=0;i<productLabelNames.length;i++){
    			if(productLabelNames[i].value==skuOthers[i]){
    				//设置选择状态
    				productLabelNames[i].checked=true;
    			}else{
    				return;
    			}
    		}
    	}
    });
    -->
   	//end
    //货品默认值
    $('tr[name="normAttrTr"]').find("input[name='normAttrId'][type='hidden']").each(function(idx_,e_){
    	var this_ = $(this);
    	var checkedattr_ = $("#normDiv").find("input[type='checkbox']:checked").each(function(){
    		if($(this).val()==this_.val()){
	    		var text_ = $(this).parent().text().trim();
	        	var normname_ = $(this).closest("ul").prev().attr("normname").trim();
	        	$(this_).val(this_.val()+"!@#"+normname_+"!@#"+text_);
    		}
    	});
    	
    });
    	
    <#if (product.description)??>
        UM.getEditor('myEditor').setContent(<#noescape>'${(product.description)}'</#noescape>, true);
    </#if>

    	//加载图片上传控件
        $("[name=uploadImg]").multiupload();
	    $("[name=skupicfile]").skuupload();
        
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}update',
            type: 'post',
            success: function (data) {
            if (data && null != data.success && data.success == true) {
                alert("修改商品成功");
                window.location.href='${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/waitSale';
            }else if(data.backUrl != null){
                alert(data.message);
                window.location.href=data.backUrl;
            }else{
            	$("#add").removeAttr("disabled");
                refrushCSRFToken(data.csrfToken);
                $.messager.alert('提示',data.message);
            }
            }
        };
        $("#back").click(function () {
            window.location.href = '${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/waitSale';
        });
        $("#add").click(function () {
        	/* 本品会持续补货  选中状态则值为1前台显示。不选中状态则值为0前台不显示 */
        	var cxbhBool = $("input[name='isCxbhTemp']").attr("checked");
        	if(cxbhBool){
        		$("#tzm_cxbh").val(1);
        	}else{
        		$("#tzm_cxbh").val(0);
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
//            	console.info($("#skupics").val());
//            	return;
            
            //var name1 = $('#name1').val();//商品名称
            //$('#productBrandId').val($('#brandId').val());//品牌id
            //var productCateId = $('#productCateId').val();//商品分类id
            //var name2 = $('#name2').val();//促销信息
            //var keyword = $('#keyword1').val();//关键字
            var costPrice = $('#costPrice').val();//成本价
            var protectedPrice = $('#protectedPrice').val();//保护价
            var marketPrice = $('#marketPrice').val();//市场价
            var mallPcPrice = $('#mallPcPrice').val();//pc商城价
            var virtualSales = $('#virtualSales').val();//虚拟销量
            var actualSales = $('#actualSales').val();//实际销量
            var todayLimitNum = $('#todayLimitNum').val();//商品库存
            var upTime = $('#upTime').val();//上架时间
            var isInventedProduct = $("input[name='isInventedProduct'][checked]").val();//是否虚拟商品
            var isCxbh = $("#tzm_cxbh").val();//本品是否持续补货【仝照美】
            var refIds = $("#refIds").val();//推荐搭配
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
            if (queryType && queryType.length > 0) {
                var queryTypeVal = '';
                for (var i = 0; i < queryType.length; i++) {
                    queryTypeVal += $(queryType[i]).val() + ';';
                }
                $('#queryType').val(queryTypeVal);//商品属性
            }
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
            var mallPcPrice = $(this).val();//pc商城价
            $('#protectedPrice').val(mallPcPrice);
          //  $('#marketPrice').val(mallPcPrice);
            /*var protectedPrice = $('#protectedPrice').val();//保护价
            if (mallPcPrice && protectedPrice && mallPcPrice < protectedPrice) {
                $.messager.alert('提示', '商城价不能低于保护价');
                return;
            }*/
        });


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
    <#if pic?? && pic?size &gt; 0>
        $('#previewImgBox').show();
    </#if>

    })
    
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
    
    function callback1(result) {
        //console.log("1"+result.names[0]);
    }
    
    function vertifyistop(val){
    	var regu = /^\d{1,4}$/;
		var re = new RegExp(regu);
		if(val.match(regu)!=null){
			return true;
		}else{
			$("#is_top").val("");
			alert("请输入1到9999之内的数字，只能为整数。")
		}
    }
</script>
<#include "normjs.ftl"/>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">发布商品-修改商品详细信息
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/waitSale">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <input type="hidden" name="id" value="#{product.id}"/>
            <!-- 搭配推荐商品 -->
            <dl class="dl-group">
            	<dt class="dt-group"><span class="s-icon"></span>推荐搭配</dt>
            	<dd class="dd-group">
                    <div class="fluidbox">
                   		<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商品: </label> 
							<input type="button" value="选择商品" id="pro"/>
							<input type="text" id="productName" name="productName" style="width: 900px;" value="${(product.refIds)!''}" readonly="readonly">
							<input type="hidden" id="refIds" name="refIds" value="${(product.refIds)!''}">
						</p>
                    </div>
                </dd>
            </dl>
            <!-- end -->
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品基本信息</dt>
                <dd class="dd-group">
                    <#--
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商品分类: </label>
		                    <#if cate?? && cate?size &gt; 0>
	                            <select id="parentId_0" name="parentId_0" level="0" class="txt w210">
	                                <#list cate as cc>
	                                    <option value="${(cc.id)!''}" <#if productCateTwo?? && (cc.id) == productCateTwo>selected</#if>> ${(cc.name)!''}</option>
	                                </#list>
	                            </select>
		                    </#if>
		                    <#if cateThree?? && cateThree?size &gt; 0>
	                            <select id="parentId_1" name="parentId_1" level="1" class="txt w210">
	                                <#list cateThree as cc>
	                                    <option value="${(cc.id)!''}" <#if productCateThree?? && (cc.id) == productCateThree>selected</#if>> ${(cc.name)!''}</option>
	                                </#list>
	                            </select>
		                    </#if>
                            <input type="hidden" id="productCateId" name="productCateId" value="${(product.productCateId)!''}"/>
                        </p>
                    </div>
					-->
                    <input type="hidden" id="productCateId" name="productCateId" value="${(product.productCateId)!''}"/>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品品牌: </label>
                            <#if brand??>
                                <select id="brandId" name="brandId" level="0" class="w210">
                                        <option>${brand!''}</option>
                                </select>
                            </#if>
                            <input type="hidden" id="productBrandId" name="productBrandId" value="${(product.productBrandId)!''}"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>促销信息: </label>
                            <input type="text" id="name2" name="name2" value="${(product.name2)!''}" class="txt w200 easyui-validatebox" missingMessage="促销信息必填，2-100个字符" data-options="required:true,validType:'length[2,100]'"/>
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品名称: </label>
                            <input type="text" id="name1" name="name1" value="${(product.name1)!''}" class="txt w400 easyui-validatebox" missingMessage="商品名称必填，3-100个字符" data-options="required:true,validType:'length[3,100]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>SPU: </label>
                            <input type="text" id="productCode" name="productCode" value="${(product.productCode)!''}" class="txt w200 easyui-validatebox" missingMessage="SPU必填，3-20个字符" data-options="required:true,validType:'length[3,20]'"/>
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>关键字: </label>
                            <input type="text" id="keyword1" name="keyword1" value="${(product.keyword)!''}" class="txt w400 easyui-validatebox" missingMessage="关键字必填，2-50个字符" data-options="required:true,validType:'length[2,50]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>wms分类: </label>
                            <input type="text" id="wmsCategory" name="wmsCategory" value="${(product.wmsCategory)!''}" class="txt w200" disabled="disabled"/>
                        </p>
                    </div>
					<!-- 其他分类 数据字典中读取内容 -->
					<div class="fluidbox">
						<p class="p6 p-item">
                            <label class="lab-item">其他分类</label>
                            <@cont.select id="otherCategory" name="otherCategory"  value="${(product.otherCategory)!''}" codeDiv="PRODUCT_QUERY_WORD" mode="1"/>
                        </p>
                        <p class="p6 p-item"> 
                            <label class="lab-item"><font class="red"></font>购买权限: </label>
							<input type="text" id="memberName" 
							  value="${(product.memberIds)!''}" readonly="readonly" class="txt w200 easyui-validatebox" missingMessage="加工方式必填" data-options="required:false" />
							<input type="hidden" id="memberIds" name="memberIds" value="${(product.memberIds)!''}" />
                            <input type="button" value="选择会员" id="pro4"/>
                        </p>
					</div>
					<!-- end -->
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                             	商品关键字，用户检索检索商品，多个用逗号分割
                            </font>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>销售单位: </label>
                            <select name="unit" level="0" class="w210">
                                 <option value="双" <#if product.unit?? && product.unit=='双'>selected</#if> >双</option>
                                 <option value="盒" <#if product.unit?? && product.unit=='盒'>selected</#if> >盒</option>
                                 <option value="箱" <#if product.unit?? && product.unit=='箱'>selected</#if> >箱</option>
                            </select>
                        </p>
                    </div>

                    <!--保护价-->
                    <input type="hidden" id="protectedPrice" name="protectedPrice" value="${(product.protectedPrice)!''}"/>
                    <!--市场价
                    <input type="hidden" id="marketPrice" name="marketPrice" value="${(product.marketPrice)!''}"/>
                    -->
                    <!--手机价-->
                    <input type="hidden" id="malMobilePrice" name="malMobilePrice" value="${(product.malMobilePrice)!''}" />
                    <input type="hidden" name="sellerId" id="sellerId"/>
                    <input type="hidden" id="sellerCateId" name="sellerCateId" value="${(product.sellerCateId)!''}"/>
                    
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商城价: </label>
                            <input type="text" id="mallPcPrice" name="mallPcPrice" value="${(product.mallPcPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>成本价: </label>
                            <input type="text" id="costPrice" name="costPrice" value="${(product.costPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>虚拟销量: </label>
                            <input type="text" id="virtualSales" name="virtualSales" value="${(product.virtualSales)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品库存: </label>
                            <input type="text" id="productStock" name="productStock"
                                   missingMessage="请输入商品库存"
                                   data-options="required:true"
                                   value="${(product.productStock)!''}" class="txt w200 easyui-numberbox" readonly="true"/> <font style="color: #808080">无需输入，系统自动累加 </font>
                        </p>
                   	 		<input type="hidden" id="productStockHidden" name="productStockHidden" value="${(product.productStock)!''}"/>
                    </div>

                    <#--<div class="fluidbox">-->
                        <#--<p class="p12 p-item">-->
                            <#--<label class="lab-item"><font class="red">*</font>实际销量: </label>-->
                            <#--<input type="text" id="actualSales" name="actualSales"-->
                                   <#--value="${(product.actualSales)!''}" class="txt w200"/>-->
                        <#--</p>-->
                    <#--</div>-->
                    <input type="hidden" id="actualSales" name="actualSales" value="${(product.actualSales)!''}">
                    <input type="hidden" id="commentsNumber" name="commentsNumber" value="${(product.commentsNumber)!''}">

                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>预计上架时间: </label>
                            <input type="text" id="upTime" name="upTime" value="${(product.upTime)?string('yyy-MM-dd HH:mm:ss')!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w200" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>分佣比例: </label>
                            <input type="text" id="percentageScale" name="percentageScale" value="${(product.percentageScale)!''}" class="txt w200 easyui-numberbox" data-options="min:0.001,max:0.999,precision:3,required:true" missingMessage="分佣比例必填必填，0-1之间" />
                        </p>
                    </div>
                     <div id="fuliao_dialog_id">
                     </div>
                     <div class="fluidbox">
                    	<p class="p6 p-item">
                    		<label class="lab-item"></font>链接: </label>
                    		<input type="text" id="productUrl" name="productUrl" value="${(product.productUrl)!''}" class="txt w200 easyui-textbox"  missingMessage="百度网盘的路径" />
                    	</p>
                    	<p>
                    		<label class="lab-item"><font class="red">*</font>内部库存预警值: </label>
                            <input type="text" id="inStockWarning" name="inStockWarning"
                                   value="${(product.inStockWarning)!''}" 
                                   class="txt w200 easyui-numberbox" 
                                   data-options="min:1,max:999999,precision:0,required:true"/>
                    	</p>
                    </div>
                    <div class="fluidbox">
                    	<p class="p6 p-item">
                    		<label class="lab-item"></font>自定义排序: </label>
                    		<input type="text" id="is_top" name="is_top" class="txt w200 easyui-textbox" value="${(product.isTop)!'1'}" onblur="vertifyistop(this.value)"/>
                    		<font style="color: #808080">数字越大，排序越靠前 </font>
                    	</p>
                    </div>
              		 <!-- 商品原价 和  套餐 -->
                    <div class="fluidbox">
                    <#--
                    	<p class="p6 p-item">
                            <label class="lab-item"><font class="red">&nbsp;</font>加工方式: </label> 
							<input type="button" value="选择方式" id="pro2"/>
							<input type="text" id="productPackageName" name="productPackageName" 
							  value="${(product.taocanId)!''}" readonly="readonly" class="txt w200 easyui-validatebox" missingMessage="加工方式必填" data-options="required:false" />
							<input type="hidden" id="taocanId" name="taocanId" value="${(product.taocanId)!''}" />
                        </p>
                    -->
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商品原价: </label>
                            <input type="text" id="marketPrice" name="marketPrice" value="${(product.marketPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:false" />
                        </p>
                    </div>
                    <!-- end -->
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>是否商家推荐: </label>
                            <@cont.radio id="sellerIsTop" value="${(product.sellerIsTop)!''}" codeDiv="PRODUCT_IS_TOP" />
                        </p>
                        <#--
                        <p class="p6 p-item" id="tzm_flagid1">
                            <label class="lab-item"><font class="red"></font>商标: </label>
                            <input type="text" id="sku_other_name" 
							  value="${(product.sku_other)!''}" readonly="readonly" class="txt w200 easyui-validatebox" missingMessage="加工方式必填" data-options="required:false" />
							<input type="hidden" id="sku_other" name="sku_other" value="${(product.sku_other)!''}" />
                            <input type="button" value="选择商标" id="pro3"/>
                        </p>
                        -->
                    </div>

                    <#--<div class="fluidbox">-->
                        <#--<p class="p12 p-item">-->
                            <#--<label class="lab-item"><font class="red">*</font>是否虚拟商品: </label>-->
                            <#--<@cont.radio id="isInventedProduct" value="${(product.isInventedProduct)!''}" codeDiv="YES_NO" />-->
                        <#--</p>-->
                    <#--</div>-->

                    <#--<div class="fluidbox">-->
                        <#--<p class="p12 p-item">-->
                            <#--<label class="lab-item">&nbsp;</label>-->
                            <#--<font style="color: #808080">-->
                                <#--虚拟商品不以实物方式出售，例如手机费-->
                            <#--</font>-->
                        <#--</p>-->
                    <#--</div>-->
					<div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                              	 被推荐的商品会显示在店铺首页
                            </font>
                        </p>
                        <p class="p6 p-item" id="tzm_flagid2">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                              	品牌袜辅料必填，多个辅用逗号","隔开 
                            </font>
                        </p>
                    </div>


					<div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>是否启用规格: </label>
                            <#if edit??>
                            <@cont.radio1 id="isNorm" value="${(product.isNorm)!''}" codeDiv="PRODUCT_IS_NORM"/>
                            <input type="hidden" name="isNormHidden" id="isNormHidden" value="${(product.isNorm)!''}"/>
                            <#else>
                            <@cont.radio id="isNorm" value="${(product.isNorm)!''}" codeDiv="PRODUCT_IS_NORM"/>
                            </#if>
                            <input type="hidden" name="productGoods" id="productGoods" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>库存预警值: </label>
                            <input type="text" id="stockWarning" name="stockWarning"
                                   value="${(product.stockWarning)!''}" 
                                   class="txt w200 easyui-numberbox" 
                                   data-options="min:1,max:999999,precision:0,required:true"/> 
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                	商品新建之后规格永久固定，不能编辑规格。
                            </font>
                        </p>
                         <p class="p6 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                当sku库存低于此阀值后，商品将显示“货源紧张”
                            </font>
                        </p>
                    </div>
                    <#include "incnorm.ftl"/>

                </dd>
            </dl>
        <dl class="dl-group">
            <dt class="dt-group"><span class="s-icon"></span>商品类型描述</dt>
        <dd class="dd-group">
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
                                <select name="queryType" level="0" class="w210">
                                    <option value="${(queryTypeAttr.attrId)!''},${(queryTypeAttr.attrName)!''},-1">不限</option>
                                    <#list queryTypeAttr.attrValList as attr>
                                        <option value="${(queryTypeAttr.attrId)!''},${(queryTypeAttr.attrName)!''},${(attr)!''}" <#if (queryTypeAttr.dbAttr)?? && attr == (queryTypeAttr.dbAttr)>selected</#if>>${(attr)!''}</option>
                                    </#list>
                                </select>
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
                            <label class="lab-item">${(custTypeAttr.attrName)!''}: </label>
			                    <#if custTypeAttr.attrValList ?size == 0 >
			                    	<#if custTypeAttr.attrId==178>
			                           <input type="text" name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" onblur="vertifyThisVal(this.value)" id="chicun" class="txt w200"/>
			                         <#elseif custTypeAttr.attrId==179||custTypeAttr.attrId==180> 
			                           <input type="text" name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200" data-options="min:0.01,max:99999,precision:2,required:true"/>
			                         <#elseif custTypeAttr.attrId==182>
	                        		   <input type="text" name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200" data-options="min:1,max:99999,precision:0,required:true"/>
			                         <#else>
			                           <input type="text" name="custType" data="${(custTypeAttr.attrId)!''},${(custTypeAttr.attrName)!''}" value="${(custTypeAttr.dbAttr)!''}" class="txt w200"/>
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
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>包装清单: </label>
                        <div style="padding-left: 140px">
                            <textarea id="packing" name="packing" rows="8" cols="83" value="${(product.packing)!''}">${(product.packing)!''}</textarea>
                        </div>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品图片</dt>
                <dd class="dd-group">
                    <span class="s-showbtn">
                        <div name="uploadImg" action="" index="${waitShow_index!''}" multiparam='{"url":"${currentBaseUrl}uploadFiles","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":5,"errMsg":"上传图片最多不能超过5张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
                            <a href="#" class="txt_white">添加图片</a>
                            <input type="hidden" name="fileIndex" value="<#if pic?? && pic?size &gt; 0>${pic?size}<#else>0</#if>"/>
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
                        <#if pic?? && pic?size &gt; 0>
                            <#list pic as pic>
                                <li>
                                    <div class='img'>
                                        <img id="prev_${pic_index}" name="prev_${pic_index}" src='${(pic.imagePath)!''}' width='150' height='150'>
                                        <div class='img-box'>
                                            <a class='del-img' href='javascript:void(0);'>删除</a>
                                        </div>
                                    </div>
                                </li>
                            </#list>
                        </#if>
                    </ul>
                </dd>
                <!-- end -->
            </dl>
			<!-- 补货提示 -->
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>补货提示</dt>
                <dd class="dd-group">
                    <div class="fluidbox" style="margin-left: 75px;">
                    <input type="hidden" id="tzm_cxbh" name="isCxbh"/>
                    <#if product.isCxbh ??>
                        <input type="checkbox" id="tzm_cxbh_temp" name="isCxbhTemp" value="${(product.isCxbh)!''}" <#if product.isCxbh == 1> checked="checked" </#if> style="vertical-align: middle;">
                    </#if>
                       		<label for="tzm_cxbh_temp" style="cursor: pointer;">本品会持续补货</label>
                    </div>
                </dd>
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
                <input type="button" id="add" class="btn" value="修改"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>
<script type="text/javascript">
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
<div style="display: none;">
<#include "goodsDialog.ftl"/>
</div>
<!-- 二次加工 -->
<div style="display: none;">
<#include "productPackageList.ftl"/>
</div>
<!-- sku辅料 -->
<div style="display: none;">
<#include "productLabelList.ftl"/>
</div>
<!-- 指定具有购买权限的用户 -->
<div style="display: none;">
<#include "memberlist.ftl"/>
</div>
<#include "/seller/commons/_detailfooter.ftl" />