<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc-custom-made.css">
		<form action='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/savepersonaltailor.html'
					id='form_company_info' method='post' enctype="multipart/form-data">
    			<div class="demand-banner">
    			<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/purchase_ser_banner.jpg" height="530" width="1919" alt="">
    			</div>

    			<div class="custom-made">
    				<h1>定制采购流程</h1>

    				<div class="step-img">
    					<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/procurement_process.png" height="150" width="1158" alt="">
    				</div>
    			</div>


    			<div class="demand-content">
    				<h1>填写采购需求</h1>


							<div class="write-content">
				    				<div class="title-f1">
				    				<em>*</em><span>采购需求描述：</span>
				    				</div>
									<textarea name="description" id="description" cols="30" rows="10" placeholder="请描述你需要采购的袜子款式、材质、规格；您的采购数量；
要求交货的最晚时间。"></textarea>
							</div>
    			</div>
    			<#--
    			<div class="demand-content demand-content1 ">
    
							<div class="write-content">
				    				<div class="title-f1">
				    				<em>*</em><span>上传样款：</span>
				    				</div>
								<div class="contact-way1">
											可上传你需求的样品图片
											<span class="shangchuan" >浏览
									<input type="file"  value="浏览"  name="up_picture" id="up_picture"/>
									</span>
							</div>
								<i>
								支持jpg/gif/png格式，大小小于1M，请确保图片清晰。
								</i>	
								
								<div class="online-bottom"></div>						
							</div>
				</div>	-->
    			<div class="demand-content demand-content1 ">
    
							<div class="write-content" style="top:28px">
				    			<div  class="title-f1">
			    					<em>*</em><span>选择图片:</span>
								</div>
								<div class="contact-way1">
									<input id="up_picture" name="up_picture" style="width:224px" runat="server" name="pic" onchange="javascript:setImagePreview(this,localImag,preview);" type="file" />
									<#--<div id="localImag" ><img id="preview" alt="预览图片" onclick="over(preview,divImage,imgbig);" src="img/5691.jpg" style="width: 300px; height: 400px;"></div>-->
								</div>	
								<i>支持jpg/gif/png格式，大小小于1M，请确保图片清晰。</i>
								<div class="online-bottom"></div>						
							</div>
				</div>
				<div class="write-content1">	
							<div class="write-content contact-way">
				    				<div class="title-f1">
				    				<em>*</em><span>联系人：</span>
				    				</div>
									<input type="text" name="contacts" id="contacts">
				
								</div>
							<div class="write-content contact-way">
				    				<div class="title-f1">
				    				<em>*</em><span>联系电话：</span>
				    				</div>
									<input type="text" name="mobile" id="mobile" onblur="vertifymobile(this.value)">
							</div>    			
							<div class="write-content contact-way">
				    				<div class="title-f1">
				    				<em>*</em><span>QQ：</span>
				    				</div>
									<input type="text" name="qq" id="qq" onblur="vertifyqq(this.value)">
							</div>    			
							<div class="write-content contact-way">
				    				<div class="title-f1">
				    				<em>*</em><span>微信：</span>
				    				</div>
									<input type="text" name="weixin" id="weixin">
							</div>
									<div class="sure-submit">
										<input type="button" onclick="infoSubmit()" value="确认提交" class="preserve" style="margin-top: 0px;" id="sure-submit">
									</div>
							
								</div>
					</form>
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>

<script type="text/javascript">

	function infoSubmit(){
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		var description = $("#description").val();
		var picture = $("#picture").val();
		var contacts = $("#contacts").val();
		var mobile = $("#mobile").val();
		var qq = $("#qq").val();
		var weixin = $("#weixin").val();
		if(description==""||picture==""||contacts==""||mobile==""||qq==""||weixin==""){
			jAlert("上述信息均为必填项，请核实填写内容。");
			return;
		}
		
		$('#form_company_info').submit();
	}

	function vertifymobile(val){
		var regu = /^1\d{10}$/;
		var re = new RegExp(regu);
		if(val.match(regu)!=null){
			return true;
		}else{
			$("#mobile").val("");
			jAlert("联系电话格式不正确，请重新输入。")
		}
	}
	
	function vertifyqq(val){
		var regu = /^\d{5,10}$/;
		var re = new RegExp(regu);
		if(val.match(regu)!=null){
			return true;
		}else{
			$("#qq").val("");
			jAlert("QQ号格式不正确，请重新输入。")
		}
	}
	
	//检查图片的格式是否正确,同时实现预览
    function setImagePreview(obj, localImagId, imgObjPreview) {
        var array = new Array('gif', 'jpeg', 'png', 'jpg', 'bmp'); //可以上传的文件类型
        if (obj.value == '') {
            $.messager.alert("让选择要上传的图片!");
            return false;
        }
        else {
            var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用 
            ////布尔型变量
            var isExists = false;
            //循环判断图片的格式是否正确
            for (var i in array) {
                if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                    //图片格式正确之后，根据浏览器的不同设置图片的大小
                    if (obj.files && obj.files[0]) {
                        //火狐下，直接设img属性 
                        imgObjPreview.style.display = 'block';
                        imgObjPreview.style.width = '400px';
                        imgObjPreview.style.height = '400px';
                        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
                        imgObjPreview.src = window.URL.createObjectURL(obj.files[0]);
                    }
                    else {
                        //IE下，使用滤镜 
                        obj.select();
                        var imgSrc = document.selection.createRange().text;
                        //必须设置初始大小 
                        localImagId.style.width = "400px";
                        localImagId.style.height = "400px";
                        //图片异常的捕捉，防止用户修改后缀来伪造图片 
                        try {
                            localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader=").src = imgSrc;
                        }
                        catch (e) {
                            $.messager.alert("您上传的图片格式不正确，请重新选择!");
                            return false;
                        }
                        imgObjPreview.style.display = 'none';
                        document.selection.empty();
                    }
                    isExists = true;
                    return true;
                }
            }
            if (isExists == false) {
                $.messager.alert("上传图片类型不正确!");
                return false;
            }
            return false;
        }
    }
	
</script>

<#include "/front/commons/logindialog.ftl" />



