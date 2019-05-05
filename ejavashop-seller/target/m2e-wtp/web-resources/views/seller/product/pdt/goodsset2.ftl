<#import "/seller/commons/_macro_controller.ftl" as cont/>
<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>
<#include "productimgcss.ftl"/>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/skupicupload.js"></script>
<script language="javascript">
    var protectedPrice = "${(product.protectedPrice)!0}";
    var isNorm = Number("${(product.isNorm)!1}");
    var domain = "${domainUrlUtil.EJS_URL_RESOURCES}";
    var from = "${from}";
	$(function(){
		<#--加载图片上传控件-->
		$("[name=uploadImg]").multiupload();
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
        
        //提交
        var backUrl = "${currentBaseUrl}";
        backUrl = backUrl + from ;
        var options = {
            url: '${currentBaseUrl}goodssetSumit2',
            type: 'post',
            success: function (data) {
            	alert("打标价格设置成功");
                window.location.href='${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/${from}';
            }
        };
        
		$("#add").click(function(){
			<#--商品图片-->
	        var image = $('img[name^=prev_]');
	        if (image && image.length > 0) {
	            var imageSrc = '';
	            for (var i = 0; i < image.length; i++) {
	                var imgSrc = $(image[i]).attr('src');
	                if (imgSrc.indexOf("resources") != -1)
	                    continue;
	                imageSrc += imgSrc;
	            }
	            if ('' != imageSrc) {
	                $('#imageSrc').val(imageSrc);//商品图片
	            }
	        }else{
	            $.messager.alert('提示',"商品图片,至少传一张");
	            return;
	        }	
            //$(this).attr("disabled",true);
            $('#addForm').ajaxSubmit(options);
		})
	
	})
</script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/js/product/pdt/goodsset2.js"></script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">打标价格设置
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/${from}">返回</a>
            </span>
        </h2>

    <#--1.addForm-------action="${currentBaseUrl}goodssetSumit2"---------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm"  name="addForm">
            <input type="hidden" name="id" value="#{(product.id)!''}"/>
            <input type="hidden" name="goodsinfo" id="goodsinfo"/>
            <input type="hidden" name="from" value="${from}"/>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品基本信息</dt>
                <dd class="dd-group">
             		 <!-- 商品保护价 -->
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">商品保护价: </label>
                            <label>${(product.protectedPrice)!0}</label>
                        </p>
                    </div>
                    <!-- 打标价设置 -->
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>打标价设置: </label>
							<label><input type="radio" name="price" id="price_1" value="1" onchange="stepOne()" <#if product.price2Status?? && product.price2Status==1> checked="checked" </#if> />一口价</label>
							<label><input type="radio" name="price" id="price_2" value="2" onchange="stepTwo()" <#if product.price2Status?? && product.price2Status==2> checked="checked" </#if> />阶梯价</label>
						</p>
					</div>
					<div id="sx_one">
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格(￥)<input type="text" id="price_only" name="price_only" value="${(productPrice.priceOnly)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
						</p>
					</div>
					<div id="sx_two">
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格1(￥)<input type="text" id="price_step_1" name="price_step_1" value="${(productPrice.price1)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
							数量阶梯<input type="text" id="stone_step_1s" name="stone_step_1s" value="${(productPrice.price1S)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/> -
								    <input type="text" id="stone_step_1e" name="stone_step_1e" value="${(productPrice.price1E)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格2(￥)<input type="text" id="price_step_2" name="price_step_2" value="${(productPrice.price2)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
							数量阶梯<input type="text" id="stone_step_2s" name="stone_step_2s" value="${(productPrice.price2S)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/> -
								    <input type="text" id="stone_step_2e" name="stone_step_2e" value="${(productPrice.price2E)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格3(￥)<input type="text" id="price_step_3" name="price_step_3" value="${(productPrice.price3)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
							数量阶梯<input type="text" id="stone_step_3s" name="stone_step_3s" value="${(productPrice.price3S)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/> -
									<input type="text" id="stone_step_3e" name="stone_step_3e" value="${(productPrice.price3E)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
						</p>
					</div>
					<!-- 打标方式  和  商标 -->
					<div class="fluidbox">
                    	<p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>打标方式: </label> 
							<input type="text" id="productPackageName" name="productPackageName" 
							  value="${(productPackage.name)!''}" readonly="readonly" class="txt w200 easyui-validatebox" missingMessage="打标方式必填" data-options="required:true" />
							<input type="hidden" id="taocanId" name="taocanId" value="${(product.taocanId)!''}" />
							<input type="button" value="选择方式" id="pro2"/>
                        </p>
                        <p class="p6 p-item" id="tzm_flagid1"> 
                            <label class="lab-item"><font class="red">*</font>商标: </label>
							<input type="text" id="sku_other_name" 
							  value="${(productLabel.name)!''}" readonly="readonly" class="txt w200 easyui-validatebox" missingMessage="商标必填" data-options="required:true" />
							<input type="hidden" id="sku_other" name="sku_other" value="${(product.skuOther)!''}" />
                            <input type="button" value="选择商标" id="pro3"/>
                        </p>
                    </div>
		            
                   <#if product.isNorm==2>
                    <div id="normDiv">
                          <div class="fluidbox" name="dyTable">
                              <table width="86%" border="0" cellspacing="0" cellpadding="0" style="margin-left:60px">
                                  <tbody>
                                  <tr style="background:#CCC;border:1px solid #e2e1e1;font-size:12px;">
                                      <td width="15%" style="padding:6px;">规格值</td>
                                      <td width="15%" style="padding:6px;">sku</td>
                                      <td width="15%" style="padding:6px;">库存</td>
                                      <td width="15%">PC价格</td>
                                      <#--
                                      <td width="15%">mobile价格</td>-->
                                  </tr>
                                  <#if goodslist?? && goodslist?size &gt; 0>
                                  <#list goodslist as good>
                                  <tr style="border:1px solid #e2e1e1" name="goodstr">
                                      <td style="display: none"><input name="goodsid" type="hidden" value="${(good.id)!''}"></td>
                                      
                                      <td>
                                          <label>${(good.normName)!''}</label>
                                      </td>
                                      <td style="padding:6px;">
                                          <label>${(good.sku)!''}</label>
                                      </td>
                                      <td>
                                          <input name="" type="text" id="inventory_details_stock_${good_index}"  readonly="readonly"
                                           style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;"
                                            value="${(good.productStock)!''}" class="styleStock">
                                      </td>
                                      <td>
                                          <input name="" type="text" id="inventory_details_pprice_${good_index}" readonly="readonly"
                                           style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" 
                                           value="${(good.mallPcPrice)!''}" class="stylePrice">
                                      </td>
                                  </tr>
                                  </#list>
                                  </#if>
                                  </tbody>
                              </table>
                          </div>
                    </div>
                    </#if>
                </dd>
            </dl>
            <#if flagforaddorupdateIMG?? && flagforaddorupdateIMG=="yes">
	             <!-- 编辑选择图片 -->
	             <dl class="dl-group">
	                <dt class="dt-group"><span class="s-icon"></span>商品图片</dt>
	                <dd class="dd-group">
	                    <span class="s-showbtn">
	                        <div name="uploadImg" action="" index="${waitShow_index!''}" multiparam='{"url":"${currentBaseUrl}uploadFiles","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":1,"errMsg":"上传图片最多不能超过1张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
	                            <a href="#" class="txt_white">添加图片</a>
	                            <input type="hidden" name="fileIndex" value="1"/>
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
	                            	<#if pic.sort?? && pic.sort==5>
	                                <li>
	                                    <div class='img'>
	                                        <img id="prev_${pic_index}" name="prev_${pic_index}" src='${(pic.imagePath)!''}' width='150' height='150'>
	                                        <div class='img-box'>
	                                            <a class='del-img' href='javascript:void(0);'>删除</a>
	                                        </div>
	                                    </div>
	                                </li>
	                                <#else>
	                                </#if>
	                            </#list>
	                        </#if>
	                    </ul>
	                </dd>
	                <!-- end -->
	            </dl>
            <#else>
            <!-- 新增图片 -->
            	<dl class="dl-group">
	                <dt class="dt-group"><span class="s-icon"></span>商品图片</dt>
	                <dd class="dd-group">
	                    <span class="s-showbtn">
	                        <div name="uploadImg" action="" index="${waitShow_index!''}" multiparam='{"url":"${currentBaseUrl}uploadFiles","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":1,"errMsg":"上传图片最多不能超过1张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
	                            <a href="#" class="txt_white">添加图片</a>
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
            </#if>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
				</dd>
            </dl>

            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="确认" />
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>
<script>
function stepOne(){
	$("#sx_two").hide();
	$("#sx_three").hide();
	$("#sx_one").show();   
}

function stepTwo(){
	$("#sx_one").hide();
	$("#sx_three").hide();
	$("#sx_two").show();
}

function stepThree(){
	$("#sx_one").hide();
	$("#sx_two").hide();
	$("#sx_three").show();
}

	var temp = $("input[name='price']:checked").val();
	if(temp==1){
		stepOne();
	}else if(temp==2){
		stepTwo();
	}else{
		stepThree();
	}
	
	//add by tongzhaomei 打标方式
	$('#pro2').click(function(){
		$('#packageDialog').dialog('open');
		$('#dataGrid').datagrid('unselectAll');
		//动态设置easyui datagrid URL
		$('#dataGrid').datagrid({
			url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productPackage/list',
		    queryParams:queryParamsHandler()
		   });
	});
	//add by tongzhaomei 选择商标
	$('#pro3').click(function(){
		$('#labelDialog').dialog('open');
		$('#labeldataGrid').datagrid('unselectAll');
		//动态设置easyui datagrid URL
		$('#labeldataGrid').datagrid({
			url:'${domainUrlUtil.EJS_URL_RESOURCES}/seller/operate/productLabel/list',
		    queryParams:queryParamsHandler()
		   });
	});
	
</script>
<!-- 打标方式 -->
<div style="display: none;">
<#include "productPackageList.ftl"/>
</div>
<!-- 商标 -->
<div style="display: none;">
<#include "productLabelList.ftl"/>
</div>

<#include "/seller/commons/_detailfooter.ftl" />