<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/list-search.css"/>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/jquery-ui-1.12.0/jquery-ui.css"/>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/jquery-ui-1.12.0/jquery-ui.js"></script>

<script type="text/javascript">
	function add2cart(obj){
		//未登录不能添加进货单
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		var form_ = $(obj).closest("form[name='cartForm']");
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : form_.serialize(),
			dataType : "json",
			success : function(data) {
				if(data.success){
					jAlert("添加成功！");
					//window.location.href=domain+"/cart/add.html";
					window.location.href=domain+"/eosearch.html";
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("请稍后再试！");
			}
		}); 
	}
	//全部选中
	function checkedChangeAll(obj){
		$("input[name='subChk']").prop("checked",$(obj).prop("checked"));
	}
	//输入数量后进行校验
	//Math.ceil() 向上取整
	//Math.floor() 向下取整
	//Math.round() 四舍五入
	function checknum(obj){
		var val = $(obj).val();
		var productStockElement = obj.parentElement.parentElement.nextElementSibling;
		var countElement = obj.nextElementSibling.nextElementSibling;//第二个兄弟元素
		var productStock = $(productStockElement).val();
		var flagElement = obj.nextElementSibling;
		var flag = $(flagElement).val();
		productStock = parseInt(productStock)/parseInt(flag);
		var boolMin = parseInt(val) < 1 ;//如果购买数量小于1，则强制购买数量为1
		var boolMax = parseInt(val) > parseInt(productStock);//如果购买数量大于库存，则强制购买数量为库存
		if(boolMin){
			$(obj).val(1);
			$(countElement).val(parseInt($(obj).val())*parseInt(flag));//设置要传递到后台数量的值
		}else if(boolMax){
			$(obj).val(productStock);
			$(countElement).val(parseInt($(obj).val())*parseInt(flag));//设置要传递到后台数量的值
		}else{
			$(countElement).val(parseInt($(obj).val())*parseInt(flag));//设置要传递到后台数量的值
		}
	} 
	//end
	//添加数量  
	function addNum(obj){
		var t = obj.nextElementSibling;//下一个兄弟元素
		var productStockElement = obj.parentElement.parentElement.nextElementSibling;
		var countElement = obj.nextElementSibling.nextElementSibling.nextElementSibling;//第三个兄弟元素
		var productStock = $(productStockElement).val();
		var flagElement = obj.nextElementSibling.nextElementSibling;
		var flag = $(flagElement).val();
		productStock = parseInt(productStock)/parseInt(flag);
		var boolFlag = (parseInt($(t).val())+1) <= parseInt(productStock);//不可以比库存数量多，不然货从哪里来
		if(typeof($(this).attr("disabled"))=="undefined" && boolFlag){
			$(t).val(parseInt($(t).val())+1)
			$(countElement).val(parseInt($(t).val())*parseInt(flag));//设置要传递到后台数量的值
	  	}
	}
	//减少数量
	function reduceNum(obj){
		var t = obj.nextElementSibling.nextElementSibling;
		var countElement = obj.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;//第四个兄弟元素
		var productStockElement = obj.parentElement.parentElement.nextElementSibling;
		var productStock = $(productStockElement).val();
		var flagElement = obj.nextElementSibling.nextElementSibling.nextElementSibling;
		var flag = $(flagElement).val();
		productStock = parseInt(productStock)/parseInt(flag);
		var boolFlag = (parseInt($(t).val())-1) >= 1;//不能是负数吧，SB都知道吧
		if(typeof($(this).attr("disabled"))=="undefined" && boolFlag){
			$(t).val(parseInt($(t).val())-1);
			$(countElement).val(parseInt($(t).val())*parseInt(flag));//设置要传递到后台数量的值
	  	}
	}
	//end
	$(function(){
		$("#autocomplete").autocomplete({
			 minLength: 2,
			 source: function(request, response) {
			        $.ajax({
			            url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/autocomplete.html",
			            dataType: "json",
			            data: {
			            	term: request.term
			            },
			            success: function(data) {
			            	response($.map(data, function(item) {
			            		return {
		                             // 设置item信息
		                             label: item
		                        }
		                    }));
			            }
			        });
			    }
		});
		//新增JS控制代码写在这里【仝照美】
		$('.a-img img').zoomify();//放大镜
		// 选中二次加工按钮状态
		$("input[name='packageCheck']").click(function(){
			var processingImg = this.parentElement.parentElement.nextSibling.nextSibling.nextElementSibling;
			var itemList = this.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
			var pcinfo = this.nextElementSibling;
			var processingBrand = this.parentElement.parentElement.nextElementSibling.nextElementSibling.nextElementSibling;
			var productPackageId = this.parentElement.parentElement.nextElementSibling;
			var isSelfLabel = this.parentElement.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.children[0].children[0];
			var inpuNamePak = this.parentElement.parentElement.nextElementSibling.nextElementSibling.children[0];
			if($(this).is(":checked")){
				//$(itemList).height(360);
				//$(processingImg).show();
				var scrolltoppx = $(document).scrollTop();
				$(processingImg).css({"display":"block","left":"40%","top":scrolltoppx+"px"});
			}else{
				//$(itemList).height(104);
				$(processingImg).hide();
				$(processingBrand).hide();
				$(pcinfo).html("需要二次加工");
				$(productPackageId).val(null);
				$(isSelfLabel).attr("checked",false);
				$(inpuNamePak).find("input[name^='pak']").attr("checked",false);
			}
		});
		$(".processing-img input[name='pak']").bind("click",function(){
			var val = "";//parentElement children[0] childNodes[1] firstElementChild nextElementSibling
			var pcinfo = this.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.children[0].childNodes[1].firstElementChild.nextElementSibling;	
			var processingImg = this.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
			var processingBrand = this.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.nextElementSibling;
			var itemList = this.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
			var productPackageId = this.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling;
			if ($(this).is(':checked')) {
				var info = "加工方式【";
				var packname = $(this).data("name");
				info += packname + "】<br><span style=font-weight:100;color:#FF3C23;>订单提交后客服会和您联系选标。</span>";
				val = $(this).val();
				$(pcinfo).html(info);
				$(this).val($(this).val());
				$(productPackageId).val($(this).val());
			}
			$("input[name='pak']").each(function() {
				if ($(this).val() != val) {
					$(this).attr("checked",false);	
				}
			});
			$(processingImg).hide();
			$(processingBrand).show();
			//$(itemList).height(104);
		});
		//end
		
		//Terry
		$("#sumbitAllCheckbox").click(function() {
			//未登录不能添加进货单
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			var subChkObj = $("input[name='subChk']:checked");
			if(subChkObj.length==0){
				jAlert("请选择商品");
				return false;
			}
			$("input[name='subChk']:checked").each(function() {
				var form_ = $(this).closest("form[name='cartForm']");
				$('#productGoodsId').val($(this).val());
				$.ajax({
					type : "POST",
					url :  domain+"/cart/addtocart.html",
					data : form_.serialize(),
					dataType : "json",
					sync: false,
					success : function(data) {
						if(data.success){
							//jAlert("添加成功xxx！");
						}else{
							jAlert(data.message);
						}
					},
					error : function() {
						jAlert("请稍后再试！");
					}
				}); 
			});
			jAlert("添加成功！");
			//window.location.href=domain+"/cart/add.html";
			window.location.href=domain+"/eosearch.html";
		});
		
		
	});
</script>

<!-- S 主体区域 -->
<div class="container" style="margin-top: 20px;">
	<div class="search-form-box">
		<form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html" method="post">
			<div class='search-form'>
				<label for="autocomplete" class="label-info">请输入商品编号:</label> <input
					type='text' class="input-search" id="autocomplete" name="sku" value="${sku!''}"> <input
					type="submit" value='查询' class='input-btn'>
			</div>
		</form>
	</div>
	<!-- 头 -->
	<div style="margin-bottom: 10px;margin-left: 10px;">
		
		<input type="checkbox" checked="checked" style="vertical-align: middle;" id="checkAllHead" onchange="checkedChangeAll(this)"><label style="margin-left:10px;display: inline-block;padding-top: 5px;vertical-align: middle;">全选</label>
		<input type="button" id="sumbitAllCheckbox" name="提交" value="快速下单" class="btn btn-default" style="margin-left: 50px;">
	</div>
	
	<div class='cart-thead' style="border: 0px solid blue;width: 1208px;">
		<div class='column t-proInfo'>商品信息</div>
		<div class='column t-proBand'>品牌</div>
		<div class='column t-proSKU'>商品编号 </div>
		<div class='column t-proCount'>库存数量(手)</div>
		<div class='column t-proPrice'>单价(￥)</div>
		<div class='column t-proPackage'>二次加工(加工费不含辅料费)</div>
		<div class='column t-proOpt'>操作</div>
	</div>
	<!-- end -->
	<div class="search-item-box" style="border: 0px solid red;">
		
		<!-- 搜索列表 bg -->
		<#if pglist?? && pglist?size &gt; 0>
		<#list pglist as pg>
		
			<form action="" method="POST"  name="cartForm">
			<input  type="hidden" name="productId" value="${(pg.productId)!}">
			<input  type="hidden" name="productGoodId" value="${(pg.id)!}" id = "productGoodsId">
			<!-- <input  type="hidden" name="count" value="10"> -->
			<input  type="hidden" name="sellerId" value="${(pg.sellerId)!0}">
			<!-- 库存大于100进行渲染显示  -->
			<#--<#if pg.productStock?? && pg.productStock &gt; 100>-->
			<div class='item-list clearfix'>
				<!-- 多选按钮 -->
				<div style="float: left;">
					<input type="checkbox" checked="checked" style="margin-top: 40px;" name="subChk" value="${(pg.id)!}"/>
				</div>
				<!-- 商品信息 -->
				<div class='item-list-tit fl'>
					<div class="item-list-img fl" style="margin-right: 10px;">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(pg.productId)!0}.html' target='_blank'> 
							<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pg.images)!}'
								original-url="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pg.images)!}"
								onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'">
						</a>
					</div>
					<div class='p-name' style="width: 100%;">
						<strong>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(pg.productId)!0}.html' target='_blank'>${(pg.productName)!} </a>
						</strong>
						<br>
						<span>${(pg.normName)!}</span>
					</div>
				</div>
				<!-- 品牌 -->
				<div class="item-list-proce fl">
					<div class="p-proce">
						<span>
							<#if pg.productBrandId?? && pg.productBrandId==1>伊兹密尔
							<#elseif pg.productBrandId?? && pg.productBrandId==2>木几良品
							<#elseif pg.productBrandId?? && pg.productBrandId==3>南极人
							<#elseif pg.productBrandId?? && pg.productBrandId==4>北极绒
							<#elseif pg.productBrandId?? && pg.productBrandId==5>俞兆林
							<#elseif pg.productBrandId?? && pg.productBrandId==6>开花屋
							<#elseif pg.productBrandId?? && pg.productBrandId==7>浪莎
							<#elseif pg.productBrandId?? && pg.productBrandId==8>裸袜
							<#elseif pg.productBrandId?? && pg.productBrandId==9>Kaikaya
							<#elseif pg.productBrandId?? && pg.productBrandId==10>柳成行
							<#elseif pg.productBrandId?? && pg.productBrandId==11>哲步
							<#elseif pg.productBrandId?? && pg.productBrandId==12>启星
							<#elseif pg.productBrandId?? && pg.productBrandId==13>5IX/CCUMI
							</#if>
						</span>
					</div>
				</div>
				<!-- 商品编号 -->
				<div class="item-list-proce fl">
					<div class="p-proce">
						<span>${(pg.sku)!}</span>
					</div>
				</div>
				<!-- 库存数量 -->
				<div class="item-list-proce fl">
					<div class="p-proce" style="padding-left: 15px;">
						<div class='choose-amount'>
							<div class='wrap-input'>
								<input type=button class='btn-reduce' value='-' onclick="reduceNum(this)">
								<a class='btn-add' onclick="addNum(this)">+</a>
								<input type='text' id='buy-num' value='1' data-now="1"  onchange="checknum(this)">
								<input type="hidden" value="${(pg.barCodePL)!}"/>
								<input type="hidden" name="number" value="${(pg.barCodePL)!}" />
							</div>
						</div>
						<input type="hidden" value="${pg.productStock}"/>
						<span <#if pg.productStock?? && pg.productStock &gt; 1000> style="color:rgb(0,153,0);" <#elseif pg.productStock?? && pg.productStock &lt; 1000> style="color:rgb(255,115,115);" </#if> >库存${(pg.productStock)!}双</span>
						<br>
						<span style="font-weight: 600;">
							一手=${(pg.barCodePL)!'10'}双 
						</span>
						<input type="hidden" value="${(pg.barCodePL)!'10'}" />
					</div>
				</div>
				<!-- 单价 -->
				<div class="item-list-proce fl">
					<div class="p-proce" style="padding-left: 45px;">
						<strong style="font-size:18px">${(pg.mallPcPrice)!}</strong>
						<#if pg.mallOriginalPrice ?? && pg.mallOriginalPrice != pg.mallMobilePrice>
						<span style="color: black;text-decoration: line-through;font-size: 12px;"><span>¥</span>${(pg.mallOriginalPrice)!""}</span>
						</#if>
					</div>
				</div>
				<!-- 二次加工（加工费不含辅料费） -->
				<div class="item-list-proce fl" style="width: 240px;">
					<div class="p-proce" style="width: 240px;">
						<#if productPackage?? && pg.productBrandId== 8 && pg.productBrandId !=10>
							<!-- 需要二次加工选项 -->
							<div class="processing-box" >
								<p class="processing-p">
									<label>
									<!-- 源头一 -->
									<input type="checkbox" name="packageCheck" class="process-item"/>&nbsp;
									<span id="pcinfo">需要二次加工</span>
									</label>
								</p>
								<input type="hidden" name="productPackageId" id="productPackageId" value=""/>
								<div class="processing-img" style="border: 1px solid #ccc;">
									<ul>
										<#list productPackage as pka>
									 	<li data-product-pageck-id="${pka.id}">
									 		<a href="javascript:;" class="a-img">
									 			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pka.image)!''}" 
									 				onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"
									 				alt="">
									 			<p data-original-title="<p style='text-align: left'>描述：${pka.describe}</p>" class="atip">
									 				<label>
									 					<!-- 源头二 -->
									 					<input style="float:none;vertical-align:middle; margin-top:-2px;" type="checkbox" name="pak" value="${pka.id}" data-name="${(pka.name)!''}"/>
									 					${(pka.name)!''}
									 				</label>
								 				</p>
									 		</a>
									 		<p>加工费<i>${(pka.price)!0}</i>元</p>
									 	</li>
									 	</#list>
									</ul>
								</div>
								<p class="processing-brand" style="margin-left:-20px;">
									<label style="width: 95%;">
									<!-- 源头三 -->
									<input type="checkbox" name="isSelfLabel" id="isSelfLabel" value="1" class="process-item"/>&nbsp;
									<span>是否自供商标</span>
									</label>
								</p>
							</div>
							<#else>
							无
						</#if>
					</div>
				</div>
				<!-- 操作 -->
				<div class="item-list-btn fl">
					<div class="p-btn">
						<button class="btn-over addcart" type="button" onclick="add2cart(this)">加入进货单</button>
					</div>
				</div>
			</div>
			<#--</#if>-->
		</form>
		</#list>
		<#else>
		<div class='item-list clearfix'>
			<div class="no-products">
				请输入商品编号进行查询
			</div>
		</div>
		</#if>
		<!-- 搜索列表 ed -->
		
	</div>

	<div class='page'>
		<!-- 分页 -->
		<#include "/front/commons/_paginationutil.ftl" />
	</div>
</div>
<!-- E 主体区域 -->
<link rel='stylesheet' type='text/css' href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/zoomify/zoomify.min.css' />
<script type='text/javascript' src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/zoomify/zoomify.min.js'></script>
<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" /> 
<#include  "/front/commons/_endbig.ftl" />
<script type="text/javascript">
document.write('<img width="1" height="1" style="position:absolute;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product_look_log.html?memberId='+ memberId + '&productId='+ ${productId!0} + '" />');
</script>