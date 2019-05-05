<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/groupCate.css">
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/groupSingPage.css">

		<div id='root-nav'>
			<div class='container'>
				<div class='subheader'>
					<strong>
						<#if productCatePP?? >
							${(productCatePP.name)!''}
						</#if>
					</strong>
					<span>
						&nbsp;>&nbsp;<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/list/${(productCateP.id)!0}.html'>${(productCateP.name)!''}</a>
						&nbsp;>&nbsp;<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate.id)!0}.html'>${(productCate.name)!''}</a>
						&nbsp;>&nbsp;
					</span>
					<span>
					<!-- 品牌的链接 -->
						<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/brand/${(productBrand.id)!0}.html">${(productBrand.name)!''}</a>
						&nbsp;>&nbsp;
						${(product.name1)!''}
					</span>
				</div>
			</div>	
		</div>
		
		<!-- S 商品主图介绍 -->
		<form action="" method="GET"  name="cartForm" id="cartForm">
		<!-- 产品ID -->
		<input  type="hidden" name="productId" value="${(product.id)!''}">
		<input  type="hidden" name="sellerId" value="${(seller.id)!''}">
		<input  type="hidden" name="productGoodsId" id="productGoodsId" value="${(goods.id)!''}">
		<input  type="hidden"  id='goodsNormAttrId' value="${(goods.normAttrId)!''}">
		<div id='p-box'>
			<div class='container'>
			
				<div class="clearfix container-xg">
				<div class="left-box">
					<div class="mainImg">
						<a href="javascript:;">
							<img style="width:360px;height:360px" jqimg="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actBidding.image)!''}" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actBidding.image)!''}" />
						</a>
					</div>
				</div>
				<div class="right-box">
					<div class="right-box-pad">
						<h1 class="shop-name" style="color:#000">${(product.name1)!''}</h1>
						<div class="description">
							<ul>
								<li class="first-col-red">${(actBidding.name)!''}</li>
								<li>活动开始时间：${(actBidding.startTime)?string("yyyy-MM-dd HH:mm:ss")!}，活动结束时间：${(actBidding.endTime)?string("yyyy-MM-dd HH:mm:ss")!}</li>
								<li>首付款截止时间：${(actBidding.firstEndTime)?string("yyyy-MM-dd HH:mm:ss")!}，尾款截止时间：${(actBidding.lastEndTime)?string("yyyy-MM-dd HH:mm:ss")!}</li>
								<li>首付款截止之后，系统根据总购买数量，计算价格并生成尾款订单，请在规定时间内完成付款。</li>
							</ul>
						</div>
						<div class="timeinfo">
	          				<#if stageType?? && stageType == 1 >
								<!-- 即将开始 -->
								<div class="time-item">即将开始：
									<strong class="day_show">0天</strong>
									<strong class="hour_show">0时</strong>
									<strong class="minute_show">0分</strong>
									<strong class="second_show">0秒</strong>
		          				</div>
							<#elseif stageType?? && stageType == 2 >
								<!-- 正在进行 -->
								<div class="time-item">距离结束：
									<strong class="day_show">0天</strong>
									<strong class="hour_show">0时</strong>
									<strong class="minute_show">0分</strong>
									<strong class="second_show">0秒</strong>
		          				</div>
							<#else>
								<!-- 已经结束 -->
								<div class="time-item">团购结束
		          				</div>
							</#if>
	          				<div class="number-item">
								<span class="num">${(actBidding.virtualSaleNum + actBidding.saleNum)!0}</span>件已被抢<#if stageType!=3>，剩余库存：${(actBidding.stock)!0}</#if>
							</div>
						</div>
						
						<div id="Choose" class='p-choose-wrap tuan-choose-wrap'>
							<#if norms?? && norms?size &gt; 0>
							<#list norms as norm>
								<div id='ChooseNorm${norm_index}' class='li choosenorms'>
									<div class='dt'>${norm.name}：</div>
									<div class='dd norms' >
										<#list norm.attrList as normattr>
											<div class='item' val="${normattr.id}">
												<b></b>
												<a href='javascript:void(0);'  title='${normattr.name}' >${normattr.name}</a>
											</div>
										</#list>
										<!-- 规格值ID  -->
										<input  type="hidden" name="specId" class="attrid" >
										<!-- 规格详情， 用逗号分隔 ，例如颜色：黑色 -->
										<input  type="hidden" name="specInfo" class="attrname" >
									</div>
								</div>
							</#list>
							</#if>
						</div>
						
						<div class="ladder-price">
							<span class="ladder-price-tit">阶梯价格：</span><span class="ladder-price-sum">
								<#if stageType==1>
									活动马上开始，赶快推荐朋友购买，购买人数越多，价格越低呦！
								<#elseif stageType==2>
									现在购买人数为<em>${(actBidding.virtualSaleNum + actBidding.saleNum)!0}</em>，价格为<em>￥${(priceNow)!0}</em>，快来推荐朋友购买，购买人数越多，价格越低呦！
								<#else>
									最终购买人数为<em>${(actBidding.virtualSaleNum + actBidding.saleNum)!0}</em>，价格为<em>￥${(priceNow)!0}</em>，快来推荐朋友购买，购买人数越多，价格越低呦！
								</#if>
							</span>
							<div class="ladder-price-p clearfix">
								<p>初始价格：<span>￥${(actBidding.price)!''}</span></p>
								<#if actBiddingPrices??>
									<#list actBiddingPrices as actBiddingPrice>
										<p>${(actBiddingPrice.saleNum)!''}人购买价格：<span>￥${(actBiddingPrice.price)!''}</span></p>
									</#list>
								</#if>
							</div>
						</div>
						
						<div class="item-purch item-purch-tuan <#if stageType != 2 || actBidding.stock lt 1>over</#if>">
							<div class="item-flxed <#if stageType != 2 || actBidding.stock lt 1>item-flxed-over</#if>">
								<div class="newPrice"><span class="price-font">首付款</span>¥${(actBidding.firstPrice)?string('0.00')!'99999999'}</div>
								<a class="oldPrice oldPrice-span" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.id)!0}.html">原价购买<span class="simsun">>></span></a>
								<div class="btn-box">
									<#if stageType?? && stageType == 1 >
										<!-- 即将开始 -->
										<a href="javascript:;" class="btn btn-gray">即将开抢</a>
									<#elseif stageType?? && stageType == 2 >
										<!-- 正在进行 -->
										<a href="javascript:;" class="btn btn-red click-btn <#if actBidding.stock lt 1>btn-gray</#if>">马上抢</a>
									<#else>
										<!-- 已经结束 -->
										<a href="javascript:;" class="btn btn-gray">已结束</a>
									</#if>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				
				
			</div>
			</div>
		</div>
		</form>
		<!-- E 商品主图介绍 -->
		<!--S 商品主体 -->
		<div class='container'>
			<div class='left'>
				<div id='browse-browse-pop' class='m m2 related-buy'>
					<div class='mt'>
						<h2>相关活动</h2>
					</div>
					<div class='mc'>
						<ul>
							<#if actBiddingTop?? && actBiddingTop?size &gt; 0>
								<#list actBiddingTop as top>
									<li class='fore1'>
										<div class='p-img'>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan/${top.id!0}.html' title='${(top.name)!""}'>
												<img width='160' height='160' alt='${(top.name)!""}' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(top.image)!""}'>
											</a>
										</div>
										<div class='p-name lift-font-line'>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan/${top.id!0}.html' target='_blank' title=''>${(top.name)!""}</a>
										</div>
										<div class='p-price'>
											<strong>￥${(top.price)?string("0.00")!""}</strong>
										</div>
									</li>
								</#list>
							</#if>
						</ul>
					</div>
				</div>
			</div>
			<div class='right'>
				<div id='product-detail' class='m m1'>
					<div class='mt' id='pro-detail-hd'>
						<div class='mt-inner tab-trigger-wrap clearfix'>
							<ul class='m-tab-trigger'>
								<li class='li-curr curr trig-item' data-table='1'>
									<a href='javascript:void(0);'>商品详情</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- 商品介绍 -->
					<div class='b-table bcent-table' id='table1'>
						<div class='mc'>
							<div class='p-parameter'>
								<ul id='parameter2' class='p-parameter-list'>
									<li title=''>商品名称：${(product.name1)!'' }</li>
									<li title=''>店铺： <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank'>${(seller.sellerName)!''}</a></li>
									<li title='2015-04-01 20:35:24'>上架时间：${(product.upTime?string("yyyy-MM-dd HH:mm:ss"))!'' }</li>
								
									<#if productAttr?? && productAttr?size &gt; 0>
										<#list productAttr as attr>
											<li title=''>${(attr.name)!''}：${(attr.value)!''}</li>
										</#list>
									</#if>
								</ul>
							</div>
						</div>
						<div class='detail-content clearfix'>
							<div class='detail-content-wrap'>
								<div class='detail-content-item'>
									<p align='center'>
										 <#noescape> ${(actBidding.descinfo)!''}</#noescape>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--E 商品主体 -->

		<!-- 彈出框 -->
		<div class="groupon-box ">
			<div class="groupon-Popup">
				<div class="attribute_buy_num">
		      <span class="attribute_buy_num_tit">购买数量：</span>
		      <div class="attribute_buy_num_val">
		        <span class="un_minus_icon" id="minus_num"></span>
		        <input type="text" value="1" id="buyNum" onkeyup="checknum(this)" autocomplete="off">
		        <span class="add_icon" id="add_num"></span>
		        <div class="limit_num">
		          单次限购
		          <span>${(actBidding.purchase)!1}</span>
		          件
		        </div>
		        <button class="statement" id="buynowBtn">去结算</button>
		      </div>
		      <p class="tuan-num-check" id="checkError"></p>
		      <span class="groupon-box-close">×</span>
		    </div>
			</div>
		</div>

<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript">
document.write('<img width="1" height="1" style="position:absolute;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product_look_log.html?memberId='+ memberId + '&productId='+ ${productId!0} + '" />');
</script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/details.js'></script>
<script type="text/javascript">
	function timer(intDiff){
		window.setInterval(function(){
		var day=0,
		    hour=0,
		    minute=0,
		    second=0;//时间默认值
		if(intDiff > 0){
		    day = Math.floor(intDiff / (60 * 60 * 24));
		    hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		    minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		    second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		}
		if (minute <= 9) minute = '0' + minute;
		if (second <= 9) second = '0' + second;
		$('.day_show').html(day+"天");
		$('.hour_show').html('<s id="h"></s>'+hour+'时');
		$('.minute_show').html('<s></s>'+minute+'分');
		$('.second_show').html('<s></s>'+second+'秒');
		intDiff--;
		}, 1000);
	}
	
	$(function(){
		var intDiff = parseInt(${countTime!0});//倒计时总秒数量
        timer(intDiff);
		// 点击马上团弹出框
		$(".click-btn").on("click",function(){
			// 未登录不能团购
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			$(".groupon-box").addClass("show-groupon-box");
		});
		// 关闭弹出框
		$(".groupon-box-close").on("click",function(){
			$(".groupon-box").removeClass("show-groupon-box");
		});

		//默认将规格选中
		var norms = $("#goodsNormAttrId").val();
		if(!isEmpty(norms)){
			var strs= new Array(); //定义数组 
			strs=norms.split(","); //字符分割 
			for(i=0;i<strs.length;i++){
				 $("#ChooseNorm"+i).find(".item").each(function(){
						var attrid = $(this).attr("val");
						if(attrid==strs[i]){
							 //规格详情
							var norminfo = $(this).parent().siblings(".dt").html();
							var attrinfo = $(this).find("a").attr("title");
							$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
							$(this).siblings(".attrid").val("").val($(this).attr("val"));
							$(this).addClass("selected").siblings().removeClass("selected");
							return;
						}
					});
			}
		}
		
		//选择规格
		$(".choosenorms .item").click(function(){
			//为隐藏域赋值
			$(this).siblings(".attrid").val("").val($(this).attr("val"));
			//规格详情
			var norminfo = $(this).parent().siblings(".dt").html();
			var attrinfo = $(this).find("a").attr("title");
			$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
			$(this).addClass("selected").siblings().removeClass("selected");
			//异步加载价格及库存信息
			queryPrice();
		});
		
		// 购买数量减少
		$("#minus_num").click(function() {
			var buyNum = $("#buyNum").val();
			if (parseInt(buyNum) <= 1) {
				return;
			} else {
				$("#buyNum").val(parseInt(buyNum) - 1);
				$("#checkError").html("");
				$("#checkError").hide();
			}
		});
		
		// 购买数量增加
		$("#add_num").click(function() {
			var limitNum = ${(actBidding.purchase)!0};
			var stock = ${(actBidding.stock)!0};
			var buyNum = $("#buyNum").val();
			if (parseInt(buyNum) >= parseInt(limitNum) || parseInt(buyNum) >= parseInt(stock)) {
				return;
			} else {
				$("#buyNum").val(parseInt(buyNum) + 1);
				$("#checkError").html("");
				$("#checkError").hide();
			}
		});
		
		// 马上团
		$("#buynowBtn").click(function(){
			// 未登录不能团购
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			
			var stock = ${(actBidding.stock)!0};
			if (parseInt(stock) < 1) {
				$("#checkError").html("已经被抢光了，下次要赶早哦！");
				$("#checkError").show();
				return false;
			}
			
			var buyNum = $("#buyNum").val();
			var limitNum = ${(actBidding.purchase)!0};
			var stock = ${(actBidding.stock)!0};
			// 判断是否为正整数
			if(!isIntege1(buyNum)){
				$("#buyNum").val(1);
				$("#checkError").html("请输入小于限购数量及库存量的正整数。");
				$("#checkError").show();
				return false;
			}
			if (parseInt(buyNum) < 1) {
				$("#buyNum").val(1);
				$("#checkError").html("请输入小于限购数量及库存量的正整数。");
				$("#checkError").show();
				return false;
			} else if (parseInt(buyNum) > parseInt(limitNum) || parseInt(buyNum) > parseInt(stock)) {
				$("#checkError").html("请输入小于限购数量及库存量的正整数。");
				$("#checkError").show();
				if (parseInt(limitNum) < parseInt(stock)) {
					$("#buyNum").val(parseInt(limitNum));
				} else {
					$("#buyNum").val(parseInt(stock));
				}
				return false;
			}
			
			/* //如果有规格，判断是否选择了规格，如果没有规格则判断是否有货品ID
			//默认只有两个规格
			var Selectcolor = $("#Choose").find("#ChooseNorm0 .item");
			var SelectVersion = $("#Choose").find("#ChooseNorm1 .item");
			if(Selectcolor.hasClass("selected") && SelectVersion.hasClass("selected")){
				$("#checkError").html("请选择购买的商品。");
				$("#checkError").show();
				return false;
			}else{
				// 如果是没有规格的商品，则判断隐藏的货品ID，如果有则通过，没有则报错
				var goodId = $("#productGoodsId").val();
				if (goodId == null || goodId == "") {
					$("#checkError").html("请选择购买的商品。");
					$("#checkError").show();
					return false;
				}
			} */
			
			var goodsId = $("#productGoodsId").val();
			if (goodsId == null || goodsId == "" || goodsId == 0) {
				$("#checkError").html("请选择购买的商品。");
				$("#checkError").show();
				return false;
			}
			window.location.href=domain+"/order/bidding-" + ${(product.id)!''}+ "-" + goodsId + "-" + ${(seller.id)!''} + "-" + ${(actBidding.id)!''} + "-" + buyNum + ".html";
		});
		
	});

	/**
     * 输入购买数量后进行校验
	 */
	function checknum(obj){
		var buyNum = $(obj).val();
		var limitNum = ${(actBidding.purchase)!0};
		var stock = ${(actBidding.stock)!0};
		//判断是否为正整数
		if(!isIntege1(buyNum)){
			$(obj).val(1);
			$("#checkError").html("请输入小于限购数量及库存量的正整数。");
			$("#checkError").show();
			return false;
		}
		if (parseInt(buyNum) < 1) {
			$(obj).val(1);
			$("#checkError").html("请输入小于限购数量及库存量的正整数。");
			$("#checkError").show();
			return false;
		} else if (parseInt(buyNum) > parseInt(limitNum) || parseInt(buyNum) > parseInt(stock)) {
			$("#checkError").html("请输入小于限购数量及库存量的正整数。");
			$("#checkError").show();
			if (parseInt(limitNum) < parseInt(stock)) {
				$(obj).val(parseInt(limitNum));
			} else {
				$(obj).val(parseInt(stock));
			}
			return false;
		}
		$("#checkError").html("");
		$("#checkError").hide();
	}

	//点击规格信息查询
	function queryPrice(){
		var flag = true;
		$("input[name='specId']").each(function(){
				if($(this).val().length<1){
					flag = false;
					return;
				}
			}
		);
		
		var params = $("#cartForm").serialize();
		if(flag){
			$.ajax({
				type : "POST",
				url :  domain+"/getGoodsInfo.html",
				data : params,
				dataType : "json",
				success : function(data) {
					var goods = data.data;
					if(goods.id!=null){
						/* //商城价格
						$("#mallPcPrice").html("￥"+goods.mallPcPrice);
						//库存
						$("#productStock").html(goods.productStock); */
						//货品ID
						$("#productGoodsId").val(goods.id);
					}else{
						//无货品信息 则不能添加购物车和购买
						jAlert("货品信息为空，请与管理员联系");
						$(".buynow").attr("disabled","disabled");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}

</script>
