<#include "/h5v1/commons/_head.ftl" />
<style>
.am-nav-tabs>li.am-active>a, .am-nav-tabs>li.am-active>a:focus, .am-nav-tabs>li.am-active>a:hover{color:#ff3c23; border:0; border-bottom:2px solid #ff3c23; background:#f8f8f8; width:50%; margin:0 auto}
.am-nav-tabs>li{width:50%; text-align:center}
.am-slider-default .am-direction-nav .am-prev{left:-30px}
.am-slider-default .am-direction-nav .am-next{right:-30px}
</style>
<body >
   <!-- 头部 -->
	<header data-am-widget="header" class="am-header am-header-default tp2 header">
	  <div class="am-header-left am-header-nav">
	      <a href="javascript:void(0);">
	          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
	      </a>
	  </div>
	
	  <h1 class="am-header-title">商品详情</h1>
	</header>
   <!-- 头部 end-->
   
	<div class="clear"></div>
	<div data-am-widget="slider" class="am-slider am-slider-a1" data-am-slider='{"directionNav":false}'>
	  <ul class="am-slides">
	  	<#if productLeadPicList?? && productLeadPicList?size &gt; 0 >
			<#list productLeadPicList as pic >
				<li><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${pic!''}"></li>
			</#list>
		</#if>
	  </ul>
	  
	</div>

		<!-- 详情列表 -->
		<#if product?? && product.state?? && product.state == 6>
			<div class="ico public price">
				<p>${(product.name1)!''}</p>
			    <div class="overflow">
			    	<#if product.priceStatus==3>
			    		<div class="fl mr10">
				            <span>¥${(product.scatteredPrice)?string('0.00')!'999999'}</span>
				            <div class="em3 f12">散价(10*n)${(product.unit)!''}</div>
				        </div>
				        <div class="fl mr10">
				            <span>¥${(product.mallPcPrice)?string('0.00')!'999999'}</span>
				            <!--  
				            <#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
								&nbsp;&nbsp;<span>¥${(goods.mallOriginalPrice)?string('0.00')!'999999'}</span>
							</#if>
							-->
				            <div class="em3 f12">单色整箱价(${(product.fullContainerQuantity)}*n)${(product.unit)!''}</div>
				        </div>
					<#elseif product.priceStatus==2>
						<div class="fl mr10 price_num">
				            <span>¥${(productPrice.price1)?string('0.00')!'999999'}</span>
				            <p class="em3 f12">${(productPrice.price1S)}-${(productPrice.price1E)}${(product.unit)!''}</p>
				        </div>
				        <div class="fl mr10 price_num">
				            <span>¥${(productPrice.price2)?string('0.00')!'999999'}</span>
				            <p class="em3 f12">${(productPrice.price2S)}-${(productPrice.price2E)}${(product.unit)!''}</p>
				        </div>
				        <div class="fl mr10 price_num">
				            <span>¥${(productPrice.price3)?string('0.00')!'999999'}</span>
				            <p class="em3 f12">≥${(productPrice.price3S)}${(product.unit)!''}</p>
				        </div>
					<#elseif product.priceStatus==1>
						<span>¥${(goods.mallMobilePrice)?string('0.00')!'999999'}</span>
		    			<del>${(goods.mallOriginalPrice)!""}</del>
					</#if>
			    </div>
			</div>
			<!--banner end-->
		
			<!--选项卡-->
			<div class="clear"></div>
			<div class="ico mt10">
			    <div class="am-tabs" data-am-tabs>
			      <ul class="am-tabs-nav am-nav am-nav-tabs" style="background:#f8f8f8">
			        <li class="am-active"><a href="#tab1">商品</a></li>
			        <li><a href="#tab2">详情</a></li>
			      </ul>
			    
			      <div class="am-tabs-bd mt10">
			        <div class="am-tab-panel am-fade am-in am-active" id="tab1">
			            <div class="img wap">
			            	<p><#noescape> ${(product.description)!''}</#noescape></p>
			                <#-- <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/img1.jpg"> -->
			            </div>
			        </div>
			        <div class="am-tab-panel am-fade" id="tab2" style="padding-top:0">
			          	<div class="wap">
			            	<table cellspacing="0" class="parame">
			                	<tr>
			                    	<td class="td1">商品名称</td>
			                        <td class="td2">${(product.name1)!''}</td>
			                    </tr>
			                    <tr>
			                    	<td class="td1">上架时间</td>
			                        <td class="td2">${(product.upTime?string("yyyy-MM-dd HH:mm:ss"))!'' }</td>
			                    </tr>
			                    <#if productAttr?? && productAttr?size &gt; 0>
								 	<#list productAttr as attr>
								 	 	<tr>
								  	 		<td class="td1">${(attr.name)!''}</td>
								  	 		<td class="td2">${(attr.value)!''}</td>
								  	 	</tr>
								 	</#list>
								 </#if>
			                </table>
			            </div>
			        </div>
			       
			      </div>
			      <div class="coupon-redemption" style="display:none" id=flashSaleInfoDiv>
			        <!-- 限时抢购 -->
			      </div>
			      <!-- E 领券 促销 -->
	

				<#-- <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/info/${(product.id)!0}.html" class="block" >
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >详细介绍</div>
						<div ><span class="fa fa-angle-right"></span></div>
					</div>
				</a>
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/spec/${(product.id)!0}.html" class="block">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >规格参数</div>
						<div ><span class="fa fa-angle-right"></span></div>
					</div>
				</a> -->
			
				<!-- begain -->
				<div class="flex goods-nav">
					<div class="flex-1 active"><a class="block" onclick="changeTabstyle(1)">详细介绍</a></div>
					<div class="flex-1"><a class="block" onclick="changeTabstyle(2)">规格参数</a></div>
					<#if product.productBrandId?? && product.productBrandId==8 && product.dabiaowaFlag?? && product.dabiaowaFlag==1>
					<div class="flex-1"><a class="block" onclick="changeTabstyle(3)">打标信息</a></div>
						<#else>
						<div></div>
					</#if>
				</div>
				<!-- 详细介绍 -->
				<div class="s-container" id="container_detailinfo">
				    <div class="pad10" style="display:block;">
					  <#noescape> ${(product.description)!''}</#noescape>
					</div>
			    </div>
			</div>
			<!--选项卡end-->
				<!-- 主体结束 -->
				<!-- 打标信息 -->
				<style>
					.dabiao-price li{
						width:33.333333%;
						float:left;
						text-align: center;
					}
				
				.dabiao-price li span{
				color:red;
				display:block;
				width: 80px;
   				text-align: center;
   				margin-bottom: 3px;
   				font-size:18px;}
				
				.dabiao-price li i{
				display:block;
				width: 85px;
   				text-align: center;
   				font-size:12px;
   				
				}
				
				
				</style>
				
<#--	
<!-- 打标信息区域 -->			
<div style="border:0px solid red;display: none;" id="container_detaildabiao">
	<div class="s-container" id="container">
	    <div class="pad10">
		  <table width="100%" class="goods-table"  cellspacing="0" cellpadding="0" >
		  	 <tr>
		  	 	<td>打标价</td>
		  	 	<#if product.price2Status?? && product.price2Status ==2 >
		  	 		<!-- 阶梯价 -->
			  	 	<td>
			  	 		<ul class="dabiao-price">
			  	 			<li ><span>¥${(productPrice2.price1)!'999999'}</span><i>${(productPrice2.price1S)}-${(productPrice2.price1E)}${(product.unit)!''}</i></li>
			  	 			<li><span>¥${(productPrice2.price2)!'999999'}</span><i>${(productPrice2.price2S)}-${(productPrice2.price2E)}${(product.unit)!''}</i></li>						
			  	 			<li ><span>¥${(productPrice2.price3)!'999999'}</span><i>${(productPrice2.price3S)}-${(productPrice2.price3E)}${(product.unit)!''}</i></li>
						</ul>
			  	 	</td>
		  	 	<#else>
		  	 		<!--  一口价-->
			  	 	<td>
			  	 		<ul class="dabiao-price">
			  	 			<li><span>¥${(productPrice2.priceOnly)!'999999'}</span><i></i></li>
						</ul>
			  	 	</td>
		  	 	</#if>
		  	 </tr>
		  	 <#if productPackage2?? && product.productBrandId == 8 &&  product.id!=10>
			  	 <tr>
			  	 	<td>包装单位</td>
			  	 	<td>${(productPackage2.packageUnitStr)!''}</td>
			  	 </tr>
			  	 <tr>
			  	 	<td>商标</td>
			  	 	<td>${(shangbiaoInfo)!''}</td>
			  	 </tr>
			  	 <tr>
			  	 	<td>辅料</td>
			  	 	<td>${(productPackage2.fuliaoStr)!''}</td>
			  	 </tr>
			  	 <tr>
			  	 	<td>打标说明</td>
			  	 	<td>${(productPackage2.describe)!''}</td>
			  	 </tr>
		  	</#if>
		  </table>
		</div>
    </div>
</div>
-->			
				<!-- 主题结束 -->
				<#-- end
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/comment/${(product.id)!0}.html" class="block" style="display: none;">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >商品评价&nbsp;<font class="clr53">${(statisticsVO.productCommentsHighProportion)!"100"}%</font>好评</div>
						<div >${(statisticsVO.productCommentsAllCount)!"0"}人评价&nbsp;<span class="fa fa-angle-right"></span></div>
					</div>
				</a>
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/ask/${(product.id)!0}.html" class="block" style="display: none;">
					<div class="flex flex-pack-justify bor-btom pad-bt mar-bt">
						<div >商品咨询&nbsp;<font class="clr53">${(statisticsVO.productAskCount)!"0"}</font>条</div>
						<div ><span class="fa fa-angle-right"></span></div>
					</div>
				</a> -->
	
			</div>
	        <!--  -详情列表end-->
	        
	        <#-- 店铺详情
	        <div class="pad10 bgff" style="display: none;">
		    	<h2 style="display: none;">
		    		<div><i class="fa fa-institution"></i>${(seller.sellerName)!''}</div>
		    	</h2>
		    	<div class="flex flex-pack-justify s-score">
		    		<div>商品评分：<font class="clr53">${(seller.scoreDescription)!'0'}</font></div>
		    		<div>服务态度：<font class="clr53">${(seller.scoreService)!'0'}</font></div>
		    	</div>
		    	<div class="flex flex-pack-justify s-score">
		    		<div>物流速度：<font class="clr53">${(seller.scoreDeliverGoods)!'0'}</font></div>
		    		<div>关注：<font class="clr53">${(seller.collectionNumber)!'0'}</font></div>
		    	</div>
		    	<div class="text-center pad-top"  style="display: none;">
		    	   <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html" class="btn btn-default s-btn" role="button">进入店铺</a>
		    	   <#if statisticsVO??> 
						<#if statisticsVO.collectedShop=true>
							<a href="javascript:;" class="btn btn-default s-btn" role="button" disabled="disabled">&nbsp;已收藏&nbsp;</a>
						<#else>
							<a id="collectShop" href="javascript:;" class="btn btn-default s-btn" role="button" onclick="collectShop('${(seller.id)!''}')">收藏店铺</a>
						</#if>
				   </#if> 
		    	</div>
		    </div>
		     -->
	        <!-- 底部固定菜单 -->
	        <div class="fixed-nav">
	        	<div class="flex">
		        	<div class="flex-1" style="font-size: 12px;flex:0.5;">
		        		<a href="javascript:;" onclick="collectProduct()" class="block">
		        			<#if statisticsVO?? && statisticsVO.collectedProduct>
		        				<span class="fa fa-heart current"></span><font id="collectSpan">已收藏</font>
		        			<#else>
		        				<span class="fa fa-heart"></span><font id="collectSpan">收藏</font>
		        			</#if>
		        		</a>
		        	</div>
	        		
		        	<div class="flex-1" style="font-size: 12px;">
		        		<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/cart/detail.html" class="block pos_relative">
		        			<span class="fa fa-cart-plus"></span>进货单
		        			<font class="cart-num">${(cartNumber)!0}</font>
		        		</a>
		        	</div>
		        	<#if product.productBrandId?? && product.productBrandId==8 >
		        		<!--  裸袜清仓-->
						<div class="flex-1" style="font-size: 12px;padding-top: 5px;">
			        		<a href="javascript:;" id="skuAllSelectforLuowa"class="block pos_relative">
			        			<span class="fa fa-cube" ></span>一键清仓
			        		</a>
			        	</div>
						<#else>
						<!--  品牌袜清仓-->
						<div class="flex-1" style="font-size: 12px;padding-top: 5px;">
			        		<a href="javascript:;" id="skuAllSelectforPPW"  class="block pos_relative">
			        			<span class="fa fa-cube" ></span>一键清仓
			        		</a>
			        	</div>
					</#if>
		        	<div class="flex-2 yellow-cart">
		        		<a id="addToCart" class="block">
		        			加入进货单
		        		</a>
		        	</div>
	        		<div class="flex-2 red-cart" >
	        			<a id="buyNow" class="block" href="javascript:;">
	        				立即购买
	        			</a>
	        		</div>
	        	</div>
	        </div>
	       <!--  弹出层 -->
	       <#if product.productBrandId?? && product.productBrandId==8 >
				<#include "luowapop.ftl" />
				<#else>
				<#include "ppwpop.ftl" />
			</#if>
        <#else>
			<div class="mar-bt" id="mmmm">
		        <h3 style="padding:20px 0; background:#fdf5f5; text-align:center;"><strong>即将上架，敬请期待！</strong></h3>
		    </div>
		</#if>
		                    <input type="hidden" value="${(product.state)!''}" id="tzm_productState"/>
		                    <input type="hidden" value="${(product.upTime)?string('yyyy-MM-dd HH:mm:ss')}" id="tzm_productUpTime"/>
		<!-- footer -->
	    <#include "/h5/commons/_footer2.ftl" />
    </div>
    <input type="hidden" id="isCollectProduct" value="${(statisticsVO.collectedProduct)?string('true','false')!'false'}"/>
	<!-- 主体结束 -->
					
				
				
	
	<#include "/h5/commons/_statistic.ftl" />
    
	
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.hDialog.js"></script>
<!-- 轮播 -->
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
<script>
	function delcart(obj){
		var id_ = $(obj).data("cartid");
		if($(obj).data("cartcount")>1){
			if(confirm("您选择了多个该规格货品，是否确定全部删除？")){
				delbyid(id_);
			}
		} else{
			delbyid(id_);
		}
	}
	//保存销售单位 盒或者双
	var unitVal = '${product.unit}';
	function delbyid(id){
		$.ajax({
			type : "GET",
			url :  domain+"/cart/deleteCartById.html",
			data : {id:id},
			dataType : "json",
			success : function(data) {
				if(data.success){
					window.location.reload(true);
				}else {
					jAlert("删除失败");
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});	
	}

	$(document).ready(function () {
		  // 点击已选清单显示信息
		  $(".selected-list-box").on("click",function(){
		    $(".selected-list-hide").toggleClass("selected-list-show");
		  });

		  // 选中二次加工按钮状态
		$("#packageCheck").click(function(){
			if($(this).is(":checked")){
				$(".processing-img").show();
			}else{
				$(".processing-img").hide();
				$(".processing-brand").hide();
				$("#pcinfo").html("需要二次加工（备注：加工费不含辅料费）");
				$("#productPackageId").val(null);
				$("#isSelfLabel").attr("checked",false);
			}
		});

		 /*  $(".a-img>p").click(function(){
			  $.dialog('alert','套餐详情',$(this).data("describe"),4000);
		  }); */
		  
		  // 点击二次加工套餐系列
		 /*  $(".a-img>img").click(function(){
				var info = "您选择了【";
				var packname = $(this).closest("li").find("p").html().trim();
				info += packname + "】，订单提交后，客服会第一时间与您联系，加工费不含辅料费。";
				$(this).closest("li").parent().parent().hide();
				$(".processing-brand").show();
				
				$("#productPackageId").val($(this).closest("li").data("product-pageck-id"));
				
				$("#pcinfo").html(info);
		  }); */
		
		var mySwiper = new Swiper ('.swiper-container', {
			autoplay: 4000,
			loop: true,
			// 如果需要分页器
			pagination: '.swiper-pagination',
		});
		
		// 加载活动信息
		showProductActInfo('${productId!0}','${(seller.id)!0}');
		showProductFlashSaleInfo('${productId!0}');
		//使用循环  为每个不同【减号】绑定点击事件 --> 针对品牌袜
		var noomidsarr = new Array();
		var noomids = '${noomids!0}';
		noomids = noomids.substring(0,noomids.length-1);
		noomidsarr = noomids.split(",");
		//文字内容过程多出部分做隐藏处理begin
		for(var i=0;i<noomidsarr.length;i++){
			//$("#"+noomidsarr[i]).ellipsis({row: 1});
		}
		//商品名称过长也需要做处理 productnamePS1
		$(".productnamePS1").ellipsis({row: 2});
		//end
		for(var i=0;i<noomidsarr.length;i++){
			$("#productMinus_"+noomidsarr[i]).click(function(e) {
				var thisid = $(this).attr('id');
				thisid = thisid.split("_")[1];
				var number = parseInt($("#number"+thisid).val()); //parseInt($("#number").val(), 10);
				if (number <= 1) {
					number = 0;
				} else {
					number -= 1;
				}
				$("#number"+thisid).val(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				if(parseInt($("#number"+thisid).val())>0){
					$("#badgespan_"+thisid).text($("#number"+thisid).val());
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				$("#amount"+noomidsarr[i]).html(number);
				//计算所有name="ppwpop_number"的数值之和作为判定条件
				var $ppwpop_number = $("input[name=ppwpop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$ppwpop_number.length;i++){
					//如果所有购买数量加起来为0则说明不购买任务商品，此时确定是灰色
					valflag += parseInt($ppwpop_number[i].value);
				}
				if(valflag == 0){
					$(".block-liji").css("background-color","#ccc");
				}
			});
			//使用循环  为每个不同【加号】绑定点击事件 --> 针对品牌袜
			$("#productPlus_"+noomidsarr[i]).click(function() {
				var thisid = $(this).attr('id');
				thisid = thisid.split("_")[1];
				var stockNums = parseInt($("#stockNums").val());
				var goodsStock = parseInt($("#goodsStock_"+thisid).val())/stockNums;//parseInt($("#goodsStock").val(), stockNums)/stockNums;
				var number = parseInt($("#number"+thisid).val());//parseInt($("#number").val(), stockNums);
				console.dir(number >= goodsStock);
				console.dir(goodsStock);
				if (number >= goodsStock) {
					number = goodsStock;
				} else {
					number += 1;
					if(number >= goodsStock){
						number = goodsStock;
					}
				}
				$("#number"+thisid).val(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				if(parseInt($("#number"+thisid).val())>0){
					$("#badgespan_"+thisid).text($("#number"+thisid).val());
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				$("#amount"+noomidsarr[i]).html(number);
				//计算所有name="ppwpop_number"的数值之和作为判定条件
				var $ppwpop_number = $("input[name=ppwpop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$ppwpop_number.length;i++){
					//如果所有购买数量加起来大于0则说明有购买商品，此时确定按钮为可点击状态
					valflag += parseInt($ppwpop_number[i].value);
				}
				if(valflag > 0){
					$(".block-liji").css("background-color","#27cdf2");
				}
			});
			
			//使用循环 为每个SKU颜色添加点击事件  -->针对裸袜
			/* $("#licss_"+noomidsarr[i]).click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				$(this).addClass("current").siblings().removeClass("current");
				//加工方式显示与隐藏
				$("#jiagongdiv_"+thisid).css("display","block");
				$(this).siblings().find("span").css("display","none");
				//购买数量显示与隐藏，默认针对裸袜 
				$("#shuliangul_"+thisid+"_all").css("display","block");
				$("#shuliangul_"+thisid+"_all").siblings().css("display","none");
			}); */
			//使用循环  每个加工方式添加不同点击事件  -->针对裸袜
			$("#jiagong_"+noomidsarr[i]+"_two").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				//当前添加样式  兄弟节点移除样式
				$(this).addClass("two").siblings().removeClass("two");
				//购买数量DIV是two的进行显示，其他隐藏
				$("#shuliangdiv_"+thisid+"_two").css("display","block");
				$("#shuliangdiv_"+thisid+"_three").css("display","none");
				$("#shuliangdiv_"+thisid+"_four").css("display","none");
				//隐藏二次加工图片
				$("#processingimg_"+thisid).hide();
				//点击裸袜按钮时，可购买数量 = 总库存 - 裸袜数量 - 打标袜数量 - 二次加工数量
				var stockNums = parseInt($("#stockNums").val());
				var changeAfterProductStock = parseInt($("#goodsStock_"+thisid).val()) - (parseInt($("#number"+thisid+"_two").val()) + parseInt($("#number"+thisid+"_three").val()) + parseInt($("#number"+thisid+"_four").val()))*stockNums;
				$("#productStockGreenSpan_"+thisid).html(changeAfterProductStock);
				$("#productStockRedSpan_"+thisid).html(changeAfterProductStock);
				$("#productStockSpan_"+thisid).val(changeAfterProductStock);
				//隐藏打标袜信息 
				$("#detaildabiao_"+thisid).css("display","none");
			});
			//使用循环  每个加工方式添加不同点击事件  -->针对打标袜
			$("#jiagong_"+noomidsarr[i]+"_three").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				//当前添加样式  兄弟节点移除样式
				$(this).addClass("two").siblings().removeClass("two");
				//购买数量DIV是three的进行显示，其他隐藏
				$("#shuliangdiv_"+thisid+"_three").css("display","block");
				$("#shuliangdiv_"+thisid+"_two").css("display","none");
				$("#shuliangdiv_"+thisid+"_four").css("display","none");
				//隐藏二次加工图片
				$("#processingimg_"+thisid).hide();
				//为打标袜保存一个标识符，提交后台，获得此SKU有此标识，则说是为打标袜，要走打标袜计费流程 1则说明是打标袜
				$("#dabiaowaFlag_"+thisid+"_three").val(1);
				//点击打标袜按钮时，可购买数量 = 总库存 - 裸袜数量 - 打标袜数量 - 二次加工数量
				var stockNums = parseInt($("#stockNums").val());
				var changeAfterProductStock = parseInt($("#goodsStock_"+thisid).val()) - (parseInt($("#number"+thisid+"_two").val()) + parseInt($("#number"+thisid+"_three").val()) + parseInt($("#number"+thisid+"_four").val()))*stockNums;
				$("#productStockGreenSpan_"+thisid).html(changeAfterProductStock);
				$("#productStockRedSpan_"+thisid).html(changeAfterProductStock);
				$("#productStockSpan_"+thisid).val(changeAfterProductStock);
				//显示打标袜信息 
				$("#detaildabiao_"+thisid).css("display","block");
			});
			//使用循环  每个加工方式添加不同点击事件  -->针对二次加工
			$("#jiagong_"+noomidsarr[i]+"_four").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				//当前添加样式  兄弟节点移除样式
				$(this).addClass("two").siblings().removeClass("two");
				//购买数量DIV是four的进行显示，其他隐藏
				$("#shuliangdiv_"+thisid+"_four").css("display","block");
				$("#shuliangdiv_"+thisid+"_two").css("display","none");
				$("#shuliangdiv_"+thisid+"_three").css("display","none");
				//二次加工 显示加工方式  供用户选择
				$("#processingimg_"+thisid).show();
				//默认设置加工方式 为第一个套餐 条件 默认为空值则说明并没有设置套餐ID
				if($("#productPackageId_"+thisid+"_four").val()==""){
					$("#productPackageId_"+thisid+"_four").val($("#processingimg_"+thisid).children().children().first().data("product-pageck-id"));
					//$("#processingimg_"+thisid).children().children().first().css({"border-top":"2px solid blue"});
					$("#pkaimageid_"+thisid+"_1").css("display","block");
				}
				//点击二次加工按钮时，可购买数量 = 总库存 - 裸袜数量 - 打标袜数量 - 二次加工数量
				var stockNums = parseInt($("#stockNums").val());
				var changeAfterProductStock = parseInt($("#goodsStock_"+thisid).val()) - (parseInt($("#number"+thisid+"_two").val()) + parseInt($("#number"+thisid+"_three").val()) + parseInt($("#number"+thisid+"_four").val()))*stockNums;
				$("#productStockGreenSpan_"+thisid).html(changeAfterProductStock);
				$("#productStockRedSpan_"+thisid).html(changeAfterProductStock);
				$("#productStockSpan_"+thisid).val(changeAfterProductStock);
				//隐藏打标袜信息 
				$("#detaildabiao_"+thisid).css("display","none");
			});
			// 点击二次加工套餐系列
			  $("#aimg_"+noomidsarr[i]+">img").click(function(){
				  	var thisid = $(this).parent().attr("id");
					thisid = thisid.split("_")[1];
					//为二次加工 赋值  即绑定一个套餐ID
					$("#productPackageId_"+thisid+"_four").val($(this).closest("li").data("product-pageck-id"));
					//改变选择状态样式
					//$(this).parent().parent().css("border-top","2px solid blue");
					//$(this).parent().parent().siblings().css("border","0px solid red");
					var pkaid = $(this).parent().parent().attr("data-product-pageck-id");
					$("#pkaimageid_"+thisid+"_"+pkaid).css("display","block");
					var pkaimagearry = $("#pkaimageid_"+thisid+"_"+pkaid).parent().parent().siblings().find("a").children();
					pkaimagearry.each(function(i){
						//获得所有奇数的img即是需要隐藏的img
						if(i%2!=0){
							$(this).css("display","none");
						}
					});
					//$("#pkaimageid_"+thisid+"_"+pkaid).parent().parent().children(":first").find("img:eq(2)").css("display","none");
			  });
			//是否自供商标 点击事件 选中状态值为1  未选中值为0  
			$("#isSelfLabel_"+noomidsarr[i]).click(function(){
				var thisid = $(this).parent().attr("id");
				thisid = thisid.split("_")[1];
			  	if($(this).is(":checked")){
			  		$("#isSelfLabeltxt_"+thisid+"_four").val(1);
			  	}else{
			  		$("#isSelfLabeltxt_"+thisid+"_four").val(0);
			  	}
			})
			
			//为减号绑定点击事件 --> 针对裸袜 中  裸袜 类型
			$("#productMinus_"+noomidsarr[i]+"_two").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				var number = parseInt($("#number"+thisid+"_two").val()); //parseInt($("#number").val(), 10);
				if (number <= 1) {
					number = 0;
				} else {
					number -= 1;
				}
				$("#number"+thisid+"_two").val(parseInt(number));
				$("#amount"+noomidsarr[i]).html(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
				if(badgeflag>0){
					$("#badgespan_"+thisid).text(badgeflag);
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				//计算所有name="ppwpop_number"的数值之和作为判定条件
				var $luowapop_number = $("input[name=luowapop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$luowapop_number.length;i++){
					//如果所有购买数量加起来为0则说明不购买任务商品，此时确定是灰色
					valflag += parseInt($luowapop_number[i].value);
				}
				if(valflag == 0){
					$(".block-liji").css("background-color","#ccc");
				}
				
			});
			//为减号绑定点击事件 --> 针对裸袜 中 打标袜 类型
			$("#productMinus_"+noomidsarr[i]+"_three").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				var number = parseInt($("#number"+thisid+"_three").val()); //parseInt($("#number").val(), 10);
				if (number <= 1) {
					number = 0;
				} else {
					number -= 1;
				}
				$("#number"+thisid+"_three").val(parseInt(number));
				$("#amount"+noomidsarr[i]).html(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
				if(badgeflag>0){
					$("#badgespan_"+thisid).text(badgeflag);
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				//计算所有name="ppwpop_number"的数值之和作为判定条件
				var $luowapop_number = $("input[name=luowapop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$luowapop_number.length;i++){
					//如果所有购买数量加起来为0则说明不购买任务商品，此时确定是灰色
					valflag += parseInt($luowapop_number[i].value);
				}
				if(valflag == 0){
					$(".block-liji").css("background-color","#ccc");
				}
			});
			//为减号绑定点击事件 --> 针对裸袜 中  二次加工 类型
			$("#productMinus_"+noomidsarr[i]+"_four").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				var number = parseInt($("#number"+thisid+"_four").val()); //parseInt($("#number").val(), 10);
				if (number <= 1) {
					number = 0;
				} else {
					number -= 1;
				}
				$("#number"+thisid+"_four").val(parseInt(number));
				$("#amount"+noomidsarr[i]).html(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
				if(badgeflag>0){
					$("#badgespan_"+thisid).text(badgeflag);
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				//计算所有name="ppwpop_number"的数值之和作为判定条件
				var $luowapop_number = $("input[name=luowapop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$luowapop_number.length;i++){
					//如果所有购买数量加起来为0则说明不购买任务商品，此时确定是灰色
					valflag += parseInt($luowapop_number[i].value);
				}
				if(valflag == 0){
					$(".block-liji").css("background-color","#ccc");
				}
			});
			//为加号绑定点击事件 --> 针对裸袜 中  裸袜 类型
			$("#productPlus_"+noomidsarr[i]+"_two").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				var stockNums = parseInt($("#stockNums").val());
				var number = parseInt($("#number"+thisid+"_two").val());//parseInt($("#number").val(), stockNums);
				var goodsStock = parseInt($("#productStockSpan_"+thisid).val())/stockNums;//parseInt($("#goodsStock").val(), stockNums)/stockNums;
				goodsStock = parseInt(goodsStock);
				if (number >= goodsStock) {
					number = goodsStock;
				} else {
					number += 1;
				}
				console.dir(number);
				$("#number"+thisid+"_two").val(parseInt(number));
				$("#amount"+noomidsarr[i]).html(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
				if(badgeflag>0){
					$("#badgespan_"+thisid).text(badgeflag);
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				//计算所有name="luowapop_number"的数值之和作为判定条件
				var $luowapop_number = $("input[name=luowapop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$luowapop_number.length;i++){
					//如果所有购买数量加起来大于0则说明有购买商品，此时确定按钮为可点击状态
					valflag += parseInt($luowapop_number[i].value);
				}
				if(valflag > 0){
					$(".block-liji").css("background-color","#27cdf2");
				}
			});
			//为加号绑定点击事件 --> 针对裸袜 中  打标袜 类型
			$("#productPlus_"+noomidsarr[i]+"_three").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				var stockNums = parseInt($("#stockNums").val());
				var number = parseInt($("#number"+thisid+"_three").val());//parseInt($("#number").val(), stockNums);
				var goodsStock = parseInt($("#productStockSpan_"+thisid).val())/stockNums;//parseInt($("#goodsStock").val(), stockNums)/stockNums;
				goodsStock = parseInt(goodsStock);
				if (number >= goodsStock) {
					number = goodsStock;
				} else {
					number += 1;
				}
				$("#number"+thisid+"_three").val(parseInt(number));
				$("#amount"+noomidsarr[i]).html(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
				if(badgeflag>0){
					$("#badgespan_"+thisid).text(badgeflag);
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				//计算所有name="luowapop_number"的数值之和作为判定条件
				var $luowapop_number = $("input[name=luowapop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$luowapop_number.length;i++){
					//如果所有购买数量加起来大于0则说明有购买商品，此时确定按钮为可点击状态
					valflag += parseInt($luowapop_number[i].value);
				}
				if(valflag > 0){
					$(".block-liji").css("background-color","#27cdf2");
				}
			});
			//为加号绑定点击事件 --> 针对裸袜 中  二次加工 类型
			$("#productPlus_"+noomidsarr[i]+"_four").click(function() {
				var thisid = $(this).attr("id");
				thisid = thisid.split("_")[1];
				var stockNums = parseInt($("#stockNums").val());
				var number = parseInt($("#number"+thisid+"_four").val());//parseInt($("#number").val(), stockNums);
				var goodsStock = parseInt($("#productStockSpan_"+thisid).val())/stockNums;//parseInt($("#goodsStock").val(), stockNums)/stockNums;
				goodsStock = parseInt(goodsStock);
				if (number >= goodsStock) {
					number = goodsStock;
				} else {
					number += 1;
				}
				$("#number"+thisid+"_four").val(parseInt(number));
				$("#amount"+noomidsarr[i]).html(parseInt(number));
				//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
				var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
				if(badgeflag>0){
					$("#badgespan_"+thisid).text(badgeflag);
					$("#badgespan_"+thisid).css("display","inline");
				}else{
					$("#badgespan_"+thisid).css("display","none");
				}
				//计算所有name="luowapop_number"的数值之和作为判定条件
				var $luowapop_number = $("input[name=luowapop_number]");
				var bool = false;
				var valflag = 0;
				for(var i=0;i<$luowapop_number.length;i++){
					//如果所有购买数量加起来大于0则说明有购买商品，此时确定按钮为可点击状态
					valflag += parseInt($luowapop_number[i].value);
				}
				if(valflag > 0){
					$(".block-liji").css("background-color","#27cdf2");
				}
			});
		}
		
		// 默认将规格选中
		var norms = $("#goodsNormAttrId").val();
		if(norms != null && norms != ""){
			$("#amount").html($("#number").val());
			var strs= new Array(); //定义数组 
			strs=norms.split(","); //字符分割 
			for(i=0;i<strs.length;i++){
				$("#normsDiv"+i).find("a").each(function(){
					var attrid = $(this).attr("id");
					if(attrid==strs[i]){
						$("#normAttr"+i).html($(this).html());
						$("#specId"+i).val(attrid);
						$(this).addClass("active").siblings().removeClass("active");
						return;
					}
				});
			}
		}
			
//仝照美  如果商品状态为7并且上架时间大于当前时间 则特殊处理
	var d2=new Date();//取今天的日期
	var tzm_productState = $("#tzm_productState").val();
	var tzm_productUpTime = $("#tzm_productUpTime").val();
	var showUpTime = tzm_productUpTime.replace(tzm_productUpTime[4],"年");
	showUpTime = showUpTime.replace(showUpTime[7],"月");
	showUpTime = showUpTime.replace(" ","日  ");
	showUpTime = showUpTime.substring(0,showUpTime.lastIndexOf(":"));
	var d1 = new Date(Date.parse(tzm_productUpTime));
	var bool = d1>d2 && tzm_productState==7;
	if(bool){//如果为true则满足条件，则执行相应JS显示指定内容
		var html = "";
		html += "<h3 style='color:#FF3C23;font-size:14px; text-align:center;'><strong>该商品将于";
		html += showUpTime;
		html += "出售，敬请期待！";
		html += "</strong></h3>";
		$("#mmmm").html(html);
		//显示文字调整结束 
		$(".spec-scroll").hide();
		$(".jqzoom img").attr('src',"${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ccumi.png");
		$(".jqzoom").removeClass("jqzoom");
		$(".right").hide();
		//显示文字调整结束 
	}
		
		$("#addToCart").click(function(){
			//未登录不能添加进货单
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			//弹出前，将加入进货单和立即购买那行DIV隐藏掉  底部导航栏也要隐藏掉
			$(".fixed-nav").css("display","none");
			$(".ft-bottom").css("display","none");
	        event.preventDefault();
	        $('.cd-popup2').addClass('is-visible2');
	        //因为有弹出窗口，后台的body不要显示，避免影响页面滚动。
	        $("html").css({"height":"100%","overflow":"hidden"});
	        $("body").css({"height":"100%","overflow":"hidden"});
	        //1 标识 加入进货单按钮
	        $("#luowaopo_flagforcartorbuy").val(1);
	        
			/* 
			var productId = $("#productId").val();
			var count = $("#orders_count").val();
			if(count>0 && productId==10){
				alert("尊敬的用户，该商品仅供首次购买本商城商品时体验购买，请选择其它商品！");
				return;
			}
			var stockNums = parseInt($("#stockNums").val());
			//先做购买数校验
//			var inputstock_ = Number($("#number").val()*10);
			var inputstock_ = Number($("#number").val()*stockNums);
			var maxStock_ = Number($("#maxStock").val());
			if(inputstock_ > maxStock_){
				var msg_ = "很抱歉，您目前最大只能购买"+maxStock_+unitVal+
				"商品，系统将会修改您下单的数量，是否继续提交？";
				$.dialog('confirm','提示',msg_,0,function(){
					$.closeDialog();
					$("#number").val(maxStock_);
					addToCart();
				});
				return;
			}
			addToCart(); */
		});
		//品牌袜点击确定按钮触发事件
		$("#ppwpop_Confirm").click(function(){
			//未登录不能立即购买
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			
			var productId = $("#productId").val();
			var count = $("#orders_count").val();
			if(count>0 && productId==10){
				alert("尊敬的用户，该商品仅供首次购买本商城商品时体验购买，请选择其它商品！");
				return;
			}
			//先做购买数校验
			var inputstock_ = Number($("#number").val());
			var maxStock_ = Number($("#maxStock").val());
			if(inputstock_ > maxStock_){
				var msg_ = "很抱歉，您目前最大只能购买"+maxStock_+unitVal+
				"商品，系统将会修改您下单的数量，是否继续提交？";
				$.dialog('confirm','提示',msg_,0,function(){
					$.closeDialog();
					$("#number").val(maxStock_);
					buyNow();
				});
				return;
			}
			
			//获取购买数量不为0的SKU的数量和其商品ID
			var flagforcartorbuy = $("#luowaopo_flagforcartorbuy").val();
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var $ppwpop_productGoodsId = $("input[name=ppwpop_productGoodsId]");
			var productId = $("#productId").val();
			var stockNums = parseInt($("#stockNums").val());
			for(var i=0;i<$ppwpop_number.length;i++){
				//购买数量不为0的商品后台处理
				if(parseInt($ppwpop_number[i].value) > 0 && flagforcartorbuy==0){
					$.ajax({
						type : "POST",
						url :  domain+"/cart/addtocart.html",
						data : {
							productId:parseInt(productId), 
							productGoodId:parseInt($ppwpop_productGoodsId[i].value),
							number:parseInt($ppwpop_number[i].value*stockNums)
						},
						dataType : "json",
						success : function(data) {
							if(data.success){
								// 跳转到订单结算页
								window.location.href=domain+"/order/info.html";
							}else{
								// alert(data.message);
								$.dialog('alert','提示',data.message,2000);
							}
						},
						error : function() {
							// alert("数据加载失败！");
							$.dialog('alert','提示','数据加载失败！',2000);
						}
					});
					
				}else if(parseInt($ppwpop_number[i].value) > 0 && flagforcartorbuy==1){
					$.ajax({
						type : "POST",
						url :  domain+"/cart/addtocart.html",
						data : {
							productId:parseInt(productId), 
							productGoodId:parseInt($ppwpop_productGoodsId[i].value),
							number:parseInt($ppwpop_number[i].value*stockNums)
						},
						dataType : "json",
						success : function(data) {
							if(data.success){
								//跳转到添加进货单成功页面
								$.dialog('alert','提示','添加成功',2000,function(){
									window.location.reload(true);
								});
							}else{
								// alert(data.message);
								$.dialog('alert','提示',data.message,2000);
							}
						},
						error : function() {
							// alert("数据加载失败！");
							$.dialog('alert','提示','数据加载失败！',2000);
						}
					});
				}
			}
			//buyNow();
		});
		//品牌袜颜色全选功能 
		$("#skuAllSelectforPPW").click(function(){
			//未登录不能颜色全选
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			var productId = $("#productId").val();
			var flagforcartorbuy = $("#luowaopo_flagforcartorbuy").val();
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var $ppwpop_productGoodsId = $("input[name=ppwpop_productGoodsId]");
			var productId = $("#productId").val();
			var stockNums = parseInt($("#stockNums").val());
			var $ppwProductStock = $("input[name=ppwProductStock]");
			var tipHtmlstr = "";
			tipHtmlstr += "点击确认可以添加到进货单。<br/>";
			for(var i=0;i<$ppwpop_number.length;i++){
				var strid = $ppwpop_number[i].id;
				var tempId = strid.substring(6,strid.length);
				if($ppwProductStock[i].value/stockNums>0){
					$ppwpop_number[i].value=$ppwProductStock[i].value/stockNums;
					tipHtmlstr += $("#"+tempId).text() +" X "+$ppwProductStock[i].value + unitVal +"<br/>";
				}
			}
			$.dialog('confirm','确认购买商品',tipHtmlstr,0,function(r){
				for(var i=0;i<$ppwpop_number.length;i++){
					if(parseInt($ppwpop_number[i].value) > 0){
						$.ajax({
							type : "POST",
							url :  domain+"/cart/addtocart.html",
							data : {
								productId:parseInt(productId), 
								productGoodId:parseInt($ppwpop_productGoodsId[i].value),
								number:parseInt($ppwpop_number[i].value*stockNums)
							},
							dataType : "json",
							success : function(data) {
								if(data.success){
									// 跳转到订单结算页
									window.location.href=domain+"/cart/detail.html";
								}else{
									// alert(data.message);
									$.dialog('alert','提示',data.message,2000);
								}
							},
							error : function() {
								// alert("数据加载失败！");
								$.dialog('alert','提示','数据加载失败！',2000);
							}
						});
					}
				}
			})
		})
		//end===========
		//裸袜点击确定 触发事件
		$("#luowapop_Confirm").click(function(){
			//未登录不能立即购买
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			var productId = $("#productId").val();
			var count = $("#orders_count").val();
			if(count>0 && productId==10){
				alert("尊敬的用户，该商品仅供首次购买本商城商品时体验购买，请选择其它商品！");
				return;
			}
			//先做购买数校验
			var inputstock_ = Number($("#number").val());
			var maxStock_ = Number($("#maxStock").val());
			if(inputstock_ > maxStock_){
				var msg_ = "很抱歉，您目前最大只能购买"+maxStock_+unitVal+
				"商品，系统将会修改您下单的数量，是否继续提交？";
				$.dialog('confirm','提示',msg_,0,function(){
					$.closeDialog();
					$("#number").val(maxStock_);
					buyNow();
				});
				return;
			}
			//获取购买数量不为0的SKU的数量和其商品ID
			var flagforcartorbuy = $("#luowaopo_flagforcartorbuy").val();
			var $luowapop_number = $("input[name=luowapop_number]");
			var $luowapop_productGoodsId = $("input[name=luowapop_productGoodsId]");
			var $productPackageId = $("input[name=productPackageId]");
			var $isSelfLabel = $("input[name=isSelfLabel]");
			var $luowapop_dabiaowaFlag = $("input[name=luowapop_dabiaowaFlag]");
			var stockNums = parseInt($("#stockNums").val());
			for(var i=0;i<$luowapop_number.length;i++){
				//购买数量不为0的商品后台处理  并且是点击了立即购买按钮 
				if(parseInt($luowapop_number[i].value) > 0 && flagforcartorbuy==0){
					$.ajax({
						type : "POST",
						url :  domain+"/cart/addtocart.html",
						data : {
							productId:parseInt(productId), 
							productGoodId:parseInt($luowapop_productGoodsId[i].value),
							number:parseInt($luowapop_number[i].value*stockNums),
							productPackageId:$productPackageId[i].value,
							isSelfLabel:$isSelfLabel[i].value,
							dabiaowaFlag:$luowapop_dabiaowaFlag[i].value
						},
						dataType : "json",
						success : function(data) {
							if(data.success){
								// 跳转到订单结算页
								window.location.href=domain+"/order/info.html";
							}else{
								$.dialog('alert','提示',data.message,2000);
							}
						},
						error : function() {
							$.dialog('alert','提示','数据加载失败！',2000);
						}
					});
					//else if 是点击加入进货单按钮 
				}else if(parseInt($luowapop_number[i].value) > 0 && flagforcartorbuy==1){
					$.ajax({
						type : "POST",
						url :  domain+"/cart/addtocart.html",
						data : {
							productId:parseInt(productId), 
							productGoodId:parseInt($luowapop_productGoodsId[i].value),
							number:parseInt($luowapop_number[i].value*stockNums),
							productPackageId:$productPackageId[i].value,
							isSelfLabel:$isSelfLabel[i].value,
							dabiaowaFlag:$luowapop_dabiaowaFlag[i].value
						},
						dataType : "json",
						success : function(data) {
							if(data.success){
								//跳转到添加进货单成功页面
								$.dialog('alert','提示','添加成功',2000,function(){
									window.location.reload(true);
								});
							}else{
								$.dialog('alert','提示',data.message,2000);
							}
						},
						error : function() {
							$.dialog('alert','提示','数据加载失败！',2000);
						}
					});
				}
			}
		});
		
	});
	//裸袜颜色全选功能
	$("#skuAllSelectforLuowa").click(function(){
		//未登录不能颜色全选
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		//点击确定后进行处理的方法console.dir(r);
		//获取购买数量不为0的SKU的数量和其商品ID
		var productId = $("#productId").val();
		var $luowapop_number = $("input[name=luowapop_number]");
		var $luowapop_productGoodsId = $("input[name=luowapop_productGoodsId]");
		var $productPackageId = $("input[name=productPackageId]");
		var $isSelfLabel = $("input[name=isSelfLabel]");
		var $luowapop_dabiaowaFlag = $("input[name=luowapop_dabiaowaFlag]");
		var stockNums = parseInt($("#stockNums").val());
		var $ppwProductStock = $("input[name=ppwProductStock]");
		var tipHtmlstr = "";
		tipHtmlstr += "点击确认可以添加到进货单。<br/>";
		for(var i=0;i<$luowapop_number.length;i++){//
			if($luowapop_number[i].id.indexOf("_two")>0){
				var strid = $luowapop_number[i].id;
				var tempId = strid.substring(6,strid.indexOf("_"));
				if($("#productStockSpan_"+tempId).val()/stockNums>0){
					$luowapop_number[i].value=$("#productStockSpan_"+tempId).val()/stockNums;
					tipHtmlstr += $("#"+tempId).text() +" X "+$("#productStockSpan_"+tempId).val() + unitVal +"<br/>";
				}
			}
			if($luowapop_number[i].id.indexOf("_three")>0){
				$luowapop_number[i].value=0;
			}
			if($luowapop_number[i].id.indexOf("_four")>0){
				$luowapop_number[i].value=0;
			}
		}
		$.dialog('confirm','确认购买商品',tipHtmlstr,0,function(r){
			for(var i=0;i<$luowapop_number.length;i++){
				if(parseInt($luowapop_number[i].value) > 0){
					$.ajax({
						type : "POST",
						url :  domain+"/cart/addtocart.html",
						data : {
							productId:parseInt(productId), 
							productGoodId:parseInt($luowapop_productGoodsId[i].value),
							number:parseInt($luowapop_number[i].value*stockNums),
							productPackageId:$productPackageId[i].value,
							isSelfLabel:$isSelfLabel[i].value,
							dabiaowaFlag:$luowapop_dabiaowaFlag[i].value
						},
						dataType : "json",
						success : function(data) {
							if(data.success){
								// 跳转到订单结算页
								window.location.href=domain+"/cart/detail.html";
							}else{
								$.dialog('alert','提示',data.message,2000);
							}
						},
						error : function() {
							$.dialog('alert','提示','数据加载失败！',2000);
						}
					});
				}
			}
		});
	});
	//end====================
	function buyNow(){
		var stockNums = parseInt($("#stockNums").val());
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : {
				productId:$("#productId").val(), 
				productGoodId:$("#productGoodsId").val(),
				number:$("#number").val()*stockNums,
				productPackageId:$("#productPackageId").val(),
				isSelfLabel:$("#isSelfLabel").is(":checked")?1:0
			},
			dataType : "json",
			success : function(data) {
				if(data.success){
					// 跳转到订单结算页
					window.location.href=domain+"/order/info.html";
				}else{
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
				}
			},
			error : function() {
				// alert("数据加载失败！");
				$.dialog('alert','提示','数据加载失败！',2000);
			}
		});
	}
	
	function addToCart(){
		var stockNums = parseInt($("#stockNums").val());
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : {
				productId:$("#productId").val(), 
				productGoodId:$("#productGoodsId").val(),
				number:$("#number").val()*stockNums,
				productPackageId:$("#productPackageId").val(),
				isSelfLabel:$("#isSelfLabel").is(":checked")?1:0
			},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//跳转到添加进货单成功页面
					$.dialog('alert','提示','添加成功',2000,function(){
						window.location.reload(true);
					});
//						var cartNum = parseInt($(".cart-num").html());
//						cartNum = cartNum + parseInt($("#number").val());
//						$(".cart-num").html(cartNum);
				}else{
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
				}
			},
			error : function() {
				// alert("数据加载失败！");
				$.dialog('alert','提示','数据加载失败！',2000);
			}
		});
	}
	
	//品牌袜中  数量输入框失去焦点
	function modify(obj) {
		var thisid = $(obj).attr("id");
		thisid = thisid.substring(6,thisid.length);
		var stockNums = parseInt($("#stockNums").val());
		var number = $(obj).val() * stockNums;
//		var goodsStock = parseInt($("#goodsStock").val(), 10);
		var goodsStock = parseInt($("#goodsStock_"+thisid).val());
		/* if (number == null || parseInt(number) < stockNums) {
			number = stockNums;
			$(obj).val(number);
		} else */ if (number != null && parseInt(number) > goodsStock) {
			number = goodsStock/stockNums;
			$(obj).val(number);
		}
		//只允许10的倍数
		var val_ = Number(number);
		if(val_ % stockNums != 0){
			var mod = val_ % stockNums;
			var floorVal = Number(val_ - mod);
			var remainder_ = Number(10 - mod);
			//四舍五入
			if(mod >= 5){
				$(obj).val(val_ + remainder_);
			} else{
				$(obj).val(floorVal);
			}
		}
		//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
		if(parseInt($("#number"+thisid).val())>0){
			$("#badgespan_"+thisid).text($("#number"+thisid).val());
			$("#badgespan_"+thisid).css("display","inline");
		}else{
			$("#badgespan_"+thisid).css("display","none");
		}
		//计算所有name="ppwpop_number"的数值之和作为判定条件
		var $ppwpop_number = $("input[name=ppwpop_number]");
		var bool = false;
		var valflag = 0;
		for(var i=0;i<$ppwpop_number.length;i++){
			//如果所有购买数量加起来大于0则说明有购买商品，此时确定按钮为可点击状态
			valflag += parseInt($ppwpop_number[i].value);
		}
		if(valflag > 0){
			$(".block-liji").css("background-color","#27cdf2");
		}
		
		$("#amount").html(number);
	}
	// 裸袜-->裸袜数量失去焦点 校验
	function modify_two(obj) {
		var thisid = $(obj).attr("id");
		thisid = thisid.substring(6,thisid.indexOf("_"));
		var stockNums = parseInt($("#stockNums").val());
		var number = $(obj).val() * stockNums;
//		var goodsStock = parseInt($("#goodsStock").val(), 10);
		var goodsStock = parseInt($("#productStockSpan_"+thisid).val());
		/* if (number == null || parseInt(number) < stockNums) {
			number = stockNums;
			$(obj).val(number);
		} else */ if (number != null && parseInt(number) > goodsStock) {
			number = goodsStock/stockNums;
			$(obj).val(number);
		}
			//点击二次加工按钮时，可购买数量 = 总库存 - 裸袜数量 - 打标袜数量 - 二次加工数量
			var changeAfterProductStock = parseInt($("#goodsStock_"+thisid).val()) - (parseInt($("#number"+thisid+"_two").val()) + parseInt($("#number"+thisid+"_three").val()) + parseInt($("#number"+thisid+"_four").val()))*stockNums;
			$("#productStockGreenSpan_"+thisid).html(changeAfterProductStock);
			$("#productStockRedSpan_"+thisid).html(changeAfterProductStock);
			$("#productStockSpan_"+thisid).val(changeAfterProductStock);
		//只允许10的倍数
		var val_ = Number(number);
		if(val_ % stockNums != 0){
			var mod = val_ % stockNums;
			var floorVal = Number(val_ - mod);
			var remainder_ = Number(10 - mod);
			//四舍五入
			if(mod >= 5){
				$(obj).val(val_ + remainder_);
			} else{
				$(obj).val(floorVal);
			}
		}
		//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
		var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
		if(badgeflag>0){
			$("#badgespan_"+thisid).text(badgeflag);
			$("#badgespan_"+thisid).css("display","inline");
		}else{
			$("#badgespan_"+thisid).css("display","none");
		}
		$("#amount").html(number);
	}
	// 裸袜-->打标袜数量失去焦点 校验
	function modify_three(obj) {
		var thisid = $(obj).attr("id");
		thisid = thisid.substring(6,thisid.indexOf("_"));
		var stockNums = parseInt($("#stockNums").val());
		var number = $(obj).val() * stockNums;
//		var goodsStock = parseInt($("#goodsStock").val(), 10);
		var goodsStock = parseInt($("#productStockSpan_"+thisid).val());
		/* if (number == null || parseInt(number) < stockNums) {
			number = stockNums;
			$(obj).val(number);
		} else */ if (number != null && parseInt(number) > goodsStock) {
			number = goodsStock/stockNums;
			$(obj).val(number);
		}
		//点击二次加工按钮时，可购买数量 = 总库存 - 裸袜数量 - 打标袜数量 - 二次加工数量
		var changeAfterProductStock = parseInt($("#goodsStock_"+thisid).val()) - (parseInt($("#number"+thisid+"_two").val()) + parseInt($("#number"+thisid+"_three").val()) + parseInt($("#number"+thisid+"_four").val()))*stockNums;
		$("#productStockGreenSpan_"+thisid).html(changeAfterProductStock);
		$("#productStockRedSpan_"+thisid).html(changeAfterProductStock);
		$("#productStockSpan_"+thisid).val(changeAfterProductStock);
		//只允许10的倍数
		var val_ = Number(number);
		if(val_ % stockNums != 0){
			var mod = val_ % stockNums;
			var floorVal = Number(val_ - mod);
			var remainder_ = Number(10 - mod);
			//四舍五入
			if(mod >= 5){
				$(obj).val(val_ + remainder_);
			} else{
				$(obj).val(floorVal);
			}
		}
		//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
		var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
		if(badgeflag>0){
			$("#badgespan_"+thisid).text(badgeflag);
			$("#badgespan_"+thisid).css("display","inline");
		}else{
			$("#badgespan_"+thisid).css("display","none");
		}
		$("#amount").html(number);
	}
	// 裸袜-->二次加工数量失去焦点 校验
	function modify_four(obj) {
		var thisid = $(obj).attr("id");
		thisid = thisid.substring(6,thisid.indexOf("_"));
		var stockNums = parseInt($("#stockNums").val());
		var number = $(obj).val() * stockNums;
//		var goodsStock = parseInt($("#goodsStock").val(), 10);
		var goodsStock = parseInt($("#productStockSpan_"+thisid).val());
		/* if (number == null || parseInt(number) < stockNums) {
			number = stockNums;
			$(obj).val(number);
		} else  */if (number != null && parseInt(number) > goodsStock) {
			number = goodsStock/stockNums;
			$(obj).val(number);
		}
		//点击二次加工按钮时，可购买数量 = 总库存 - 裸袜数量 - 打标袜数量 - 二次加工数量
		var changeAfterProductStock = parseInt($("#goodsStock_"+thisid).val()) - (parseInt($("#number"+thisid+"_two").val()) + parseInt($("#number"+thisid+"_three").val()) + parseInt($("#number"+thisid+"_four").val()))*stockNums;
		$("#productStockGreenSpan_"+thisid).html(changeAfterProductStock);
		$("#productStockRedSpan_"+thisid).html(changeAfterProductStock);
		$("#productStockSpan_"+thisid).val(changeAfterProductStock);
		//只允许10的倍数
		var val_ = Number(number);
		if(val_ % stockNums != 0){
			var mod = val_ % stockNums;
			var floorVal = Number(val_ - mod);
			var remainder_ = Number(10 - mod);
			//四舍五入
			if(mod >= 5){
				$(obj).val(val_ + remainder_);
			} else{
				$(obj).val(floorVal);
			}
		}
		//判定如果此时购买数量不为0则需要在对应的SKU颜色位置使用徽章的形式显示其购买数量
		var badgeflag = parseInt($("#number"+thisid+"_two").val())+parseInt($("#number"+thisid+"_three").val())+parseInt($("#number"+thisid+"_four").val());
		if(badgeflag>0){
			$("#badgespan_"+thisid).text(badgeflag);
			$("#badgespan_"+thisid).css("display","inline");
		}else{
			$("#badgespan_"+thisid).css("display","none");
		}
		$("#amount").html(number);
	}
	/* function chooseNorm(obj, normIndex, normAttrId, normAttrName) {
		$("#normAttr"+normIndex).html(normAttrName);
		$("#specId"+normIndex).val(normAttrId);
		$(obj).addClass("active").siblings().removeClass("active");
		
		var normAttrId0 = $("#specId0").val();
		var normAttrId1 = $("#specId1").val();
		var normAttrIds = "";
		if (normAttrId0 != null && normAttrId0 != "") {
			normAttrIds = normAttrId0;
		}
		if (normAttrId1 != null && normAttrId1 != "") {
			normAttrIds = normAttrIds + "," + normAttrId1;
		}
		$.ajax({
			type : "POST",
			url :  domain+"/getGoodsInfo.html",
			data : {productId:$("#productId").val(), normAttrId:normAttrIds},
			dataType : "json",
			success : function(data) {
				var productGood = data.data;
				if(productGood.id!=null){
					//商城价格
					$("#productPrice").html("￥"+productGood.mallMobilePrice);
					//库存
					//var stockinfo_ = "货源充足";
					var stockinfo_ = "库存"+productGood.productStock+unitVal;
					var color_ = "green";
					var ps_ = Number(productGood.productStock);
					var warn_ = Number(productGood.productStockWarning);
					if(ps_ < warn_){
					//	stockinfo_ = "货源紧张";
						color_ = "red";
					}
					$("#productStockWarning").html(stockinfo_).css("color",color_);
					$("#productStockSpan").val(productGood.productStock);
					//货品ID
					$("#productGoodsId").val(productGood.id);
					$("#goodsNormAttrId").val(productGood.normAttrId);
					// 货品库存
					$("#goodsStock").val(productGood.productStock);
					//最大购买数
					$("#maxStock").val(productGood.maxStock);
				}else{
					//无货品信息 则不能添加进货单和购买
					// alert("货品信息为空，请与管理员联系");
					$.dialog('alert','提示','货品信息为空，请与管理员联系',2000);
					$("#buyNow").attr("disabled","disabled");
					$("#addToCart").attr("disabled","disabled");
				}
			},
			error : function() {
				// alert("数据加载失败！");
				$.dialog('alert','提示','数据加载失败！',2000);
			}
		});
	} */
	
	function collectProduct() {
		//未登录不能添加收藏
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		var isCollectProduct = $("#isCollectProduct").val();
		if (isCollectProduct == "true") {
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{productId:${(productId)!0}},
			url:domain+'/member/docollectproduct.html',
			success:function(data){
				if(data.success){
					$("#collectSpan").html("已关注");
					$("#isCollectProduct").val("true");
					$("#collectSpan").siblings("span.fa-heart").addClass("current");
				}else{
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
				}
			}
		});
	}
	
	// 关注店铺
	function collectShop(id){
		// 未登录不能关注店铺
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{sellerId:id},
			url:domain+'/member/docollectshop.html',
			success:function(data){
				if(data.success){
					// alert("收藏成功");
					$.dialog('alert','提示','收藏成功',2000);
					$("#collectShop").html("&nbsp;已收藏&nbsp;");
					$("#collectShop").attr("disabled","disabled");
				}else{
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
				}
			}
		});
	}
	//倒计时JS代码==============
	// 结束时间
	var endTime = new Date(Date.parse("2016/12/05 12:34:39".replace(/-/g,"/")));
	function GetRTime(){
		// 当前时间
		var nowTime = new Date().getTime();
		// 2016/12/22 hh:mm:ee
		// 相差的时间	
		var t = endTime.getTime() - nowTime;
		if(t<=0){
			$("#daojishiDiv").hide();
			 return false;
		}
		var d = Math.floor(t/1000/60/60/24);
		var h = Math.floor(t/1000/60/60%24);
		var i = Math.floor(t/1000/60%60);
		var s = Math.floor(t/1000%60);
		$("#RemainD").text(d);
	    $("#RemainH").text(h);
	    $("#RemainM").text(i);
	    $("#RemainS").text(s); 
	}
	//倒计时JS代码==============
	// 异步加载商品促销信息
	function showProductActInfo(productId, sellerId){
		var sellerName = "${(seller.sellerName)!''}";
		$.ajax({
			type : "POST",
			url :  domain+"/getproductactinfo.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if (data.success && data.data != null) {
					var productActVO = data.data;
					// 加载单品立减和满减
					if (productActVO.actSingle == null && productActVO.actFull == null) {
						// 都是空去掉下边线
						$(".coupon-redemption").css({"border-bottom":"0"});
					} else {
						var actHtml = '<div class="sales-box clearfix">';
						//actHtml += '	<span class="sales-tit">促销：</span>';
						actHtml += '	<div class="sales-content">';
						
						// 加载立减
						var actSingle = productActVO.actSingle;
						if (actSingle != null) {
							actHtml += '		<div class="sales-txt-price">';
							actHtml += '			<span class="sales-txt">立减</span>';
							if (actSingle.type == 1) {
								actHtml += '			<span>下单即享' + actSingle.discount + '元优惠</span>';
							} else if (actSingle.type == 2) {
								var dis = parseInt(parseFloat(actSingle.discount)*10);
								actHtml += '			<span>下单即享' + dis + '折优惠</span>';
							}
							actHtml += '		</div>';
						}
						
						// 加载满减
						var actFull = productActVO.actFull;
						if (actFull != null) {
							actHtml += '		<div class="sales-txt-price">';
							actHtml += '			<span class="sales-txt">满减</span>';
							// 满999.00减10.00，满4999.00减100.00，满12999.00减300.00
							var fullInfo = "";
							if (actFull.firstFull != null && actFull.firstFull > 0) {
								fullInfo += '满' + actFull.firstFull + '减' + actFull.firstDiscount;
							}
							if (actFull.secondFull != null && actFull.secondFull > 0) {
								fullInfo += '，满' + actFull.secondFull + '减' + actFull.secondDiscount;
							}
							if (actFull.thirdFull != null && actFull.thirdFull > 0) {
								fullInfo += '，满' + actFull.thirdFull + '减' + actFull.thirdDiscount;
							}
							actHtml += '			<span>' + fullInfo + '</span>';
							actHtml += '		</div>';
						}
						actHtml += '	</div>';
						actHtml += '</div>';
						
						$("#actInfoDiv").html(actHtml);
						$("#couponActDiv").show();
						//倒计时效果================================
						endTime = new Date(Date.parse(actSingle.endTime.replace(/-/g,"/")));
						var daojishiHtml = "";
						daojishiHtml += "<div id=\"daojishiDiv\" style=\"position: releative;color:#222;\">";
						daojishiHtml +=	"<span class=\"fa fa-clock-o\"><span>&#12288;剩余<strong id=\"RemainD\"></strong>天<strong id=\"RemainH\"></strong>时<strong id=\"RemainM\"></strong>分<strong id=\"RemainS\"></strong>秒";
						daojishiHtml += "</div>";
						$("#actInfoDiv").append(daojishiHtml);
						var timer_rt = window.setInterval("GetRTime()", 1000);
						//倒计时效果================================
					}
					
					// 加载优惠券信息
					var couponList = productActVO.couponList;
					if (couponList != null && couponList.length > 0) {
						var couponListHtml = '<div class="coupon-redemption-box">';
						couponListHtml += '	<div class="coupon-box">';
						couponListHtml += '		<span class="part-note-msg">领券</span>';
						couponListHtml += '		<span class="coupon-total-nums">共' +couponList.length + '张</span>';
						couponListHtml += '	</div>';
						couponListHtml += '	<div class="vouchers-box">';
						couponListHtml += '		<ul class="slider-container">';
						
						for (var i=0; i < couponList.length; i++) {
							var coupon = couponList[i];
							couponListHtml += '			<a onclick=receiveCoupon(' + coupon.id + ')>';
							couponListHtml += '			<li class="slider-item">';
							couponListHtml += '				<div class="slider-item-box">';
							couponListHtml += '					<div class="item-left">';
							couponListHtml += '						<div class="expeNum">';
							couponListHtml += '							<span class="rmb">￥</span>';
							couponListHtml += '							<span class="actual-number">'+ coupon.couponValue +'</span>';
							couponListHtml += '						</div>';
							couponListHtml += '						<div class="condi_msg">满'+coupon.minAmount+'元可用</div>';
							couponListHtml += '					</div>';
							couponListHtml += '					<div class="item-right">';
							couponListHtml += '						<div class="item-right-up">领</div>';
							couponListHtml += '						<div class="item-right-down">取</div>';
							couponListHtml += '					</div>';
							couponListHtml += '				</div>';
							couponListHtml += '			</li>';
							couponListHtml += '			</a>';
						}
						couponListHtml += '		</ul>';
						couponListHtml += '	</div>';
						couponListHtml += '</div>';
						
					//	$("#couponListDiv").html(couponListHtml);
						$("#couponActDiv").show();
						initLiWidth(3);
					    leftSwipe();
					}
				} else {
					
				}
			}
		});
	}
	
	// 领取优惠券
	function receiveCoupon(couponId) {
		// 未登录不能领取
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
	 	$.ajax({
			type:"POST",
			url:domain+"/member/coupon/reveivecoupon.html",
			dataType:"json",
			data:{couponId:couponId},
			success:function(data){
				if (data.success) {
					$.dialog('alert','提示',"领取成功，您可在用户中心查看您的红包！",2000);
				} else {
					$.dialog('alert','提示',data.message,2000);
				}
			},
			error:function(){
				$.dialog('alert','提示',"领取失败，请稍后再试！",2000);
			}
		});
	}
	
	
	// 异步加载限时抢购活动信息
	function showProductFlashSaleInfo(productId) {
		$.ajax({
			type : "POST",
			url :  domain+"/getproductflashinfo.html",
			data : {productId:productId},
			dataType : "json",
			success : function(data) {
				if (data.success && data.data != null) {
					var productActVO = data.data;
					// 加载限时抢购信息
					if (productActVO.actFlashSaleProduct != null) {
						var actFlashSaleProduct = productActVO.actFlashSaleProduct;
						
						var flashHtml = '<div class="sales-box clearfix">';
						flashHtml += '  <div class="sales-content">';
						flashHtml += '    <div class="sales-txt-price">';
						flashHtml += '      <span class="sales-txt">秒杀</span>';
						var stageType = productActVO.stageType;
						// 如果是正在进行
						if (stageType == 2) {
							flashHtml += '      <span>' + actFlashSaleProduct.price + '元秒杀正在进行中&nbsp;&nbsp;<a onclick="gotoFlashSale()" style="color:red;text-decoration:underline">我要秒杀>></a></span>';
						} else if (stageType == 3) {
							// 如果是即将开始
							flashHtml += '      <span>' + actFlashSaleProduct.price + '元秒杀即将开始&nbsp;&nbsp;<a onclick="gotoFlashSale()" style="color:red;text-decoration:underline">去看看>></a></span>';
						} else if (stageType == 1) {
							// 如果是已经结束
							flashHtml += '      <span>' + actFlashSaleProduct.price + '元秒杀结束了~~~&nbsp;&nbsp;<a onclick="gotoFlashSale()" style="color:red;text-decoration:underline">去看看>></a></span>';
						}
						flashHtml += '    </div>';
						flashHtml += '  </div>';
						flashHtml += '</div>';
						$("#flashSaleInfoDiv").html(flashHtml);
						$("#flashSaleInfoDiv").show();
					}
				}
			}
		});
	}
	//改变Tab样式
	function changeTabstyle(v){
		//详细描述
		if(v==1){
			$("#container_detailspec").hide();
			$("#container_detaildabiao").hide();
			$("#container_detailinfo").show();
		}
		//规格参数
		if(v==2){
			$("#container_detailinfo").hide();
			$("#container_detaildabiao").hide();
			$("#container_detailspec").show();
		}
		//打标信息
		if(v==3){
			$("#container_detailinfo").hide();
			$("#container_detailspec").hide();
			$("#container_detaildabiao").show();
			
		}
		$('.flex-1').click(function(){
			$(this).addClass('active').siblings('.flex-1').removeClass('active');
			
			});
		
	}
	// 跳转到限时抢购页面
	function gotoFlashSale() {
		window.location.href=domain+"/product/${(productId)!0}.html?type=1";  
	}
	
	
	
	//弹出层
	$('#buyNow').on('click', function(event){
		//未登录不能立即购买
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/product/${(productId)!0}.html?goodId=" + $("#productGoodsId").val();
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		//弹出前，将加入进货单和立即购买那行DIV隐藏掉  底部导航栏也要隐藏掉
		$(".fixed-nav").css("display","none");
		$(".ft-bottom").css("display","none");
        event.preventDefault();
        $('.cd-popup2').addClass("is-visible2");
       //因为有弹出窗口，后台的body不要显示，避免影响页面滚动。
        $("html").css({"height":"100%","overflow":"hidden"});
        $("body").css({"height":"100%","overflow":"hidden"});
        //$(".dialog-addquxiao").hide()
    });
    //关闭窗口
    $('.cd-popup2').on('click', function(event){
        if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup2') ) {
        	//如果关闭了就显示出来底部导航和立即购买按钮
    		$(".fixed-nav").css("display","block");
    		$(".ft-bottom").css("display","block");
            event.preventDefault();
            $(this).removeClass('is-visible2');
            document.body.style.overflow = "auto";
            //0 标识  立即购买 按钮
            $("#luowaopo_flagforcartorbuy").val(0);
        }
    });
    //ESC关闭
    $(document).keyup(function(event){
        if(event.which=='27'){
            $('.cd-popup2').removeClass('is-visible2');
            //0 标识  立即购买 按钮
            $("#luowaopo_flagforcartorbuy").val(0);
        }
    });
	
	
	//tab栏
	$(function(){
		
		 /* var $li = $('#tab li');
			var $ul = $('#content ul');
			
			 $li.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$li.removeClass();
				$this.addClass('current');
				$ul.css('display','none');
				$ul.eq($t).css('display','block');
			})   */
		$("#focus-img").changeImg({
			'boxWidth':275,
			'changeLen':3,
			'changeSpend':false,
			'autoChange':true,
			'changeHandle':true
		});
		//点击小的SKU缩略图片后弹出原图显示即放大显示看的更加清楚
		 $('#hovertreephoto img').bigic();
		//点击主图时触发事件
		 $(".swiper-slide img").click(function(){
			 //$("html").css({"height":"100%","overflow":"hidden"});
	         //$("body").css({"height":"100%","overflow":"hidden"});
			 //var imgarr = $(".swiper-slide img");
			 //有重复的二个不要
			 /* for(var i=0;i<imgarr.length-2;i++){
				 console.dir(imgarr[i].currentSrc);
			 } */
			 //$('body').append($(".swiper-wrapper").html());
		 });
		 
		 $(".topClose").click(function() {
		 	$(this).parent().hide();
		 });
		 //图片懒加载 
		 $("img").lazyload({
		  effect : "fadeIn"
		 });
	}); 
	//品牌袜中点击不同的SKU颜色事件监听
	function colornameClick(obj){
		/* 不再使用这种处理方法，但是保留下来以便后面参考
		var $li = $('#tab li');
		var $ul = $('#content ul');
		var $this = $(obj);
		var $t = $this.index();
		$li.removeClass();
		$this.addClass("licss-list");
		$this.css("border-bottom","2px solid #ff3c23");
		$this.siblings().addClass("licss-list");
		$this.siblings().css("border-bottom","0px solid #ff3c23");
		$ul.css('display','none');
		$ul.eq($t).css('display','block'); */
		//这里是控制颜色下面的红色选中条样式的显示与隐藏
		var $this = $(obj);
		$this.css("border-bottom","2px solid #ff3c23");
		$this.children(":first").css("color","red");
		$this.siblings().css("border-bottom","0px solid #ff3c23");
		//console.dir($this.siblings().find("a"));
		$this.siblings().find("a").css("color","black");
		//这里是控制购买数量内容区域的显示与隐藏
		var thisid = $(obj).attr("id");
		thisid = thisid.split("_")[1];
		$("#contentul_"+thisid).siblings().hide();
		$("#contentul_"+thisid).show();
		//这里控制对应的缩略图片的显示与隐藏
		$("#imgthumbnail_"+thisid).css("display","block");
		$("#imgthumbnail_"+thisid).siblings().css("display","none");
		//这里控制对应的价格的显示与隐藏
		$("#xxxspan_"+thisid).css("display","inline-block");
		$("#xxxspan_"+thisid).siblings().css("display","none");
	}
	//裸袜中点击SKU颜色事件监听
	function luowaColornameClick(obj){
		//这里是控制颜色下面的红色选中条样式的显示与隐藏
		var $this = $(obj);
		$this.css("border-bottom","2px solid #ff3c23");
		$this.children(":first").css("color","red");
		$this.siblings().css("border-bottom","0px solid #ff3c23");
		$this.siblings().find("a").css("color","black");
		//这里控制对应的缩略图片的显示与隐藏
		var thisid = $(obj).attr("id");
		thisid = thisid.split("_")[1];
		$("#imgthumbnail_"+thisid).css("display","block");
		$("#imgthumbnail_"+thisid).siblings().css("display","none");
		//这里控制对应的加工方式的显示与隐藏
		//$("#jiagongdiv_"+thisid).css("display","block");
		//$this.siblings().find("span").css("display","none");
		//这里控制对应的购买数量的显示与隐藏
		$("#shuliangul_"+thisid+"_all").siblings().hide();
		$("#shuliangul_"+thisid+"_all").show();
		//这里控制对应库存数量的显示与隐藏
		//$("#luowaProductStock_"+thisid).css("display","block");
		//$("#luowaProductStock_"+thisid).siblings().css("display","none");
		//这里控制对应的价格的显示与隐藏
		$("#xxxspan_"+thisid).css("display","inline-block");
		$("#xxxspan_"+thisid).siblings().css("display","none");
	}
	//点击关闭小图标关闭弹出框
	function closepopdiv(){
		$('.cd-popup2').removeClass('is-visible2');
		//如果关闭了就显示出来底部导航和立即购买按钮
		$(".fixed-nav").css("display","block");
		$(".ft-bottom").css("display","block");
		$("html").css({"height":"100%","overflow":"auto"});
        $("body").css({"height":"100%","overflow":"auto"});
      //0 标识  立即购买 按钮
        $("#luowaopo_flagforcartorbuy").val(0);
	}
</script>
</body>
</html>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/changeImg.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquey-bigic.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.ellipsis.min.js'></script>
<script type="text/javascript">
document.write('<img width="1" height="1" style="style:none" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product_look_log.html?memberId='+ memberId + '&productId='+ ${productId!0} + '" />');
</script>




