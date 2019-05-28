<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/"/>

<#include "productimgcss.ftl"/>
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
                    window.location.href='${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/add?cateId=1';
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

        $("#add").click(function () {
            
            $('#productBrandId').val($('#cateId').val());//品牌id
            var costPrice = $('#costPrice').val();//成本价
            var protectedPrice = $('#protectedPrice').val();//保护价
            var marketPrice = $('#marketPrice').val();//市场价
            var mallPcPrice = $('#mallPcPrice').val();//pc商城价
            var virtualSales = $('#virtualSales').val();//虚拟销量
            var actualSales = $('#actualSales').val();//实际销量
            var todayLimitNum = $('#todayLimitNum').val();//商品库存
            var description = UM.getEditor('myEditor').getContent();
            if(description == ''){
                $.messager.alert('提示',"请填写商品描述");
                return;
            }
            $('#description').val(description);//商品描述信息
			
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
        
    });
    function callback1(result) {
        //console.log("1"+result.names[0]);
    }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">发布商品-填写商品详细信息
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品基本信息</dt>
                    <input type="hidden" id="productCateId" name="productCateId" value="${(cateId)!'1'}"/>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品名称: </label>
                            <input type="text" id="name1" name="name1" value="${(product.name1)!''}" class="txt w300 easyui-validatebox" missingMessage="商品名称必填，3-100个字符" data-options="required:true,validType:'length[3,100]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>关键字: </label>
                            <input type="text" id="keyword1" name="keyword1" value="${(product.keyword)!''}" class="txt w300 easyui-validatebox" missingMessage="关键字必填，2-50个字符" data-options="required:true,validType:'length[2,50]'"/>
                        </p>
                        <input type="hidden" id="id" name="id" value="${(product.id)!''}"/>
                        <input type="hidden" id="sellerId" name="sellerId" value="${(product.sellerId) }"/>
                    </div>
					<div class="fluidbox">
						<p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>SPU: </label>
                            <input type="text" id="productCode" name="productCode" value="${(product.productCode)!''}" class="txt w300 easyui-validatebox" missingMessage="SPU必填，3-20个字符" data-options="required:true,validType:'length[3,20]'" onblur="valiate_SPU()">
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                             	商品关键字，用户检索检索商品，多个用逗号分割
                            </font>
                        </p>
                    </div>
                    <!-- 商品分类 数据字典中读取内容 -->
					<div class="fluidbox">
						<p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品分类:</label>
                            <#if productCategory??>
                            	<select name="cateId" id="cateId" level="0" class="w210">
                            	<option>请选择</option>
		                     	<#list productCategory as category>
									<option value="${(category.codeCd)!}">${(category.codeText)!}</option>
								</#list>
								</select>
							</#if>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item">促销信息: </label>
                            <input type="text" id="name2" name="name2" value="${(product.name2)!''}" class="txt w300 easyui-validatebox" missingMessage="促销信息必填，2-100个字符" data-options="validType:'length[2,100]'"/>
                        </p>
					</div>

                    <!--手机价-->
                    <input type="hidden" id="malMobilePrice" name="malMobilePrice" value="${(product.malMobilePrice)!''}" />
					
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商城价: </label>
                            <input type="text" id="mallPcPrice" name="mallPcPrice" value="${(product.mallPcPrice)!''}" class="txt w300 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true" onblur="change_Mobile()"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>成本价: </label>
                            <input type="text" id="costPrice" name="costPrice" value="${(product.costPrice)!''}" class="txt w300 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
					
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>虚拟销量: </label>
                            <input type="text" id="virtualSales" name="virtualSales" value="${(product.virtualSales)!''}" class="txt w300 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>预计上架时间: </label>
                            <input type="text" id="upTime" name="upTime" value="${(product.upTime)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w300" data-options="required:true"/>
                        </p>
                    </div>

                    <div class="fluidbox">
                    	<p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>商品库存: </label>
                            <input type="text" id="productStock" name="productStock1"
                                   value="${(product.productStock)!''}" class="txt w300 easyui-numberbox" />
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">&nbsp;</font>商品原价: </label>
                            <input type="text" id="marketPrice" name="marketPrice" value="${(product.marketPrice)!''}" class="txt w300 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:false" />
                        </p>
                    </div>
                    <div id="fuliao_dialog_id">
                    </div>
                    <!-- 商品原价 和  套餐 -->
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>是否商家推荐: </label>
                            <@cont.radio id="sellerIsTop" value="${(product.sellerIsTop)!''}" codeDiv="PRODUCT_IS_TOP" />
                        </p>
                    </div>
                </dd>
            </dl>
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
                        <div name="uploadImg" action="" index="${waitShow_index!''}" multiparam='{"url":"/admin/file/uploadPics","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":5,"errMsg":"上传图片最多不能超过5张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
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
</div>
<#include "/admin/commons/_detailfooter.ftl" />