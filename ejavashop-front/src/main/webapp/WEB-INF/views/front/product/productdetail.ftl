<#include "/front/commons/_headbig.ftl" />

<#if Session.memberSession??>
  <#assign user = Session.memberSession.member>
</#if>
   <style>
		.em-errMes{
			padding-left: 0px;
			display: block;
			line-height: 26px;
			margin-top: 10px;
		}
		/*商品下架样式*/
		.m-itemover-title {
		  height: 38px;
		  line-height: 38px;
		  border: 1px solid #ddd;
		  background: #f5f5f5;
		}
		.m-itemover-title h3 {
		  padding-top: 10px;
		  padding-left: 10px;
		}
		.go-flash-sale {
			display:inline-block;
			line-height:18px;
		}
	</style>
	<script type="text/javascript">
			(function(){
				var sUserAgent = navigator.userAgent;
				if (sUserAgent.indexOf('Android') > -1 
						|| sUserAgent.indexOf('iPhone') > -1 
						|| sUserAgent.indexOf('iPad') > -1 
						|| sUserAgent.indexOf('iPod') > -1 
						|| sUserAgent.indexOf('Symbian') > -1) {
					var hrefstr = location.href;
					hrefstr = hrefstr.replace("www","m");
	                location.href = hrefstr;           
				} else {
					//console.dir(location.href);
				}    
			})();
		</script>
	
	 <div id='root-nav' style="display: none;">
			<div class='container'>
				<div class='subheader'>
					<strong>
						<#if productCatePP?? >
							${(productCatePP.name)!''}
						</#if>
					</strong>
					<span>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/list/${(productCateP.id)!0}.html'>${(productCateP.name)!''}</a>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate.id)!0}.html'>${(productCate.name)!''}</a>
						&nbsp;>&nbsp;
					</span>
					<span>
					<!-- 品牌的链接 TODO -->
						<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(product.productCateId)!0}.html">${(productBrand.name)!''}</a>
						&nbsp;>&nbsp;
						${(product.name1)!''}
					</span>
				</div>
			</div>	
		</div>
		<!-- 河姆渡 面包屑导航样式 -->
		<div id='root-nav'>
			<div class="container">
				<div class="currentmap">
			        <a class="homeas" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">${(productCate.name)!''}</a>
		            <span>&gt;</span><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate.id)!0}.html">${(productBrand.name)!''}</a>
		            <span>&gt;</span><span>${(product.name1)!''}</span>
			    </div>
			</div>
		</div>
		<!-- end -->
		<div id='p-box'>
			<div class='container'>
				<div class='product-intro m-item-grid'>
					<div class="right-extra">
						<div>
							<div id="preview" class="spec-preview">
								<!-- 默认第一张图 -->
								<#if productLeadPicList?? && productLeadPicList?size &gt; 0>
							        		<#list productLeadPicList as img>
							        			<#if img_index == 0>
							        				<span class="jqzoom">
														<img style="width:350px;height:350px" jqimg="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" 
															src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" />
															<!-- 图片可放大小图标 -->
							        						<i class="icon-mag" title="图片可放大"></i>
													</span> 
								            		<#break>
											    </#if>
							        		</#list>
							        </#if>
							</div>
   							 <!--缩图开始-->
						    <div class="spec-scroll"> 
						    	<!-- <a class="prev">&lt;</a> 
						    	<a class="next">&gt;</a> -->
						    	<span class="fa fa-angle-left fa-4x prev2" style="float: left;cursor: pointer;"></span>
						    	<span class="fa fa-angle-right fa-4x next2" style="float: right;cursor: pointer;"></span>
						      	<div class="items">
							        <ul>
							        	<#if productLeadPicList?? && productLeadPicList?size &gt; 0>
							        		<#list productLeadPicList as img>
							        			<li>
							          				<img  bimg="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" onmousemove="preview(this);" onmouseout="leaveScroll(this);">
							            		</li>
							        		</#list>
							        	</#if>
							        </ul>
						        </div>
						    </div>
   							<!--缩图结束-->
						<!--  分享商品 收藏商品按钮-->
						<div class="jqzoom_next">
							<div id="shareProduct" class="jqzoom_next_div">
								<i class="fa fa-share-alt fa-lg"></i><span>&nbsp;分享商品</span>
							</div>
							<#if statisticsVO??> 
								<#if statisticsVO.collectedProduct=true> 
									<div id="collectProductalready" class="jqzoom_next_div">
										<i class="fa fa-star fa-lg"></i><span>&nbsp;已收藏</span>
									</div>
								<#else>
									<div id="collectProduct" onclick="collectProductMy('${productId!''}')" class="jqzoom_next_div">
										<i class="fa fa-star fa-lg"></i><span>&nbsp;收藏商品</span>
									</div>
								</#if>
							</#if>
						</div>
						<div class="jqzoom_next_2">
							<div class="bdsharebuttonbox jqzoom_share">
								<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
								<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
								<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
								<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
								<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
								<a href="#" class="bds_more" data-cmd="more"></a>
							</div>
						</div>
						</div>
						<!--  分享商品 收藏商品按钮-->
					</div>
					<#if product.productBrandId?? && product.productBrandId==8 >
						<#include "luowaform.ftl" />
						<#else>
						<#include "ppwform.ftl" />
					</#if>
					<!-- right -->
					<div class='m-item-ext' style="border: 1px solid red;display: none;">
						<div class='extInfo' id='extInfo'>
							<div class='seller-infor' style="display: none;">
								<a target="_blank" href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' title='${(seller.sellerName)!''}' class='infor-name'>${(seller.sellerName)!''}</a>
							</div>
							<div class='seller-pop-box' style="display: none;">
								<div class='z-pop-desc-show'>
									<dl class='pop-score-detail'>
										<dt class='score-title'>
											<span class='rating-name'>商家满意度</span>
										</dt>
										<dd class='score-infor'>
											<div class='score-part'>
												<span class='score-desc'>商品描述：<em class='score'>${(seller.scoreDescription)!'0'}</em>分</span>
											</div>
											<div class='score-part'>
												<span class='score-desc'>服务态度：<em class='score'>${(seller.scoreService)!'0'}</em>分</span>
											</div>
											<div class='score-part'>
												<span class='score-desc'>发货速度：<em class='score'>${(seller.scoreDeliverGoods)!'0'}</em>分</span>
											</div>
										</dd>
									</dl>
									<div class='pop-shop-detail'>
										<div class='item'>
											<span class='label'>店铺名称：</span>
											<span class='text'><a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html">${(seller.sellerName)!''}</a></span>
										</div>
										<div class='item'>
											<span class='label'>所&nbsp;在&nbsp;地&nbsp;：</span>
											<span class='text'> ${(sellerLocation)!''}</span>
										</div>
									</div>
								</div>
							</div>
							<dl class='customer-service clearfix' style="border-bottom: 0px solid red;display: none;">
								<dd class='service'>
									<span class='item'>
										<b style='font-weight:700'>联 系 客 服 </b>
									</span>
									<br>
									<div class='custom-service'>
										<#if sellerQqList?? && sellerQqList?size &gt; 0>
											<#list sellerQqList as qq>
												<span class='item'>
													<b>${(qq.name)!''}：</b>
													<a href='http://wpa.qq.com/msgrd?v=3&uin=${(qq.qq)!''}&Site=${(qq.qq)!''}&Menu=yes' target='_blank'><img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/qq.jpg' width='25' height='25'></a>
												</span>
											</#list>
										</#if>
									</div>
									<br><br>
									<div class='custom-service'>
										<span class='item'>
											<b style="font-weight: bold;">联&nbsp;系&nbsp;电&nbsp;话：</b>
											<br>
											<span>4008-456-002</span>
										</span>
									</div>
								</dd>
							</dl>
							<div class='pop-shop-enter' style="display: none;">
								<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank' class='btn btn-default'>进入店铺</a>
								&nbsp;&nbsp;
								<#if statisticsVO??> 
										<#if statisticsVO.collectedShop=true>&nbsp;已收藏  
										<#else>
											<a id="collectShop" href='javascript:void(0)' onclick="collectShop('${(seller.id)!''}')" class='btn btn-default'>收藏店铺</a>
										</#if>
								</#if> 
								
							</div>
						</div>
					</div>
					<!-- 上面信息不显示，此处动态加载热卖推荐的商品 -->
					<div class='m-item-ext' style="border:0px solid red;text-align: center;">
						<div class='left' >
							<div class='m m2'>
								<div class='mt' style="border-bottom:0px; padding-left:60px;">
									<h2 style="font-weight:bold;">热卖推荐</h2>
								</div>
								<div class='mc'>
									<div id="cateTopId"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- end -->
				</div>
			</div>
			<!-- 渲染推荐搭配商品开始  -->
			<#if producListVOs??>
			<div class="shop_bd_2 clearfix" style="background-color: white;">
				<div class="shop_bd_tuijian">
	                    <ul class="tuijian_tabs">
	                        <li class="hover" ><a href="javascript:void(0);">推荐搭配</a></li>
						</ul>
						<div class="tuijian_content">
	                        <div id="tuijian_content_1" class="tuijian_shangpin" style="display: block;">
	                            <ul>
	                            	<#list producListVOs as producListVO>
	                                <li>
	                                    <dl>
	                                        <dt><a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html"><img width="180" height="200" src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(producListVO.masterImg)!""}'></a></dt>
	                                        <dd><a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html">${(producListVO.name1)!""}</a></dd>
	                                        <dd><b>¥</b><em>${(producListVO.mallPcPrice)!""}</em>元</dd>
	                                    </dl>
	                                </li>
	                                </#list>
	                            </ul>
	                        </div>
	                     </div>
				</div>
			</div>
			</#if>
			<!-- 渲染推荐搭配商品结束 -->
		<div class='container'>
			<div class='left'>
				<div id='browse-browse-pop' class='m m2 related-buy'>
					<div class='mt' style="border-bottom:0px; text-align:center;padding-top:5px;">
						<b>精品推荐</b>
					</div>
					<div class='mc class_recommend'>
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
								<li class='li-curr trig-item' data-table='2' style="display: none;">
									<a href='javascript:void(0);'>商品评价（${(statisticsVO.productCommentsAllCount)!'' }）</a>
								</li>
								<li class='li-curr trig-item' data-table='3' style="display: none;">
									<a href='javascript:void(0);'>咨询（${(statisticsVO.productAskCount)!'' }）</a>
								</li>
								<!-- <li class='li-curr trig-item' data-table='4'>
									<a href='javascript:void(0);'>规格属性</a>
								</li> -->
							</ul>
						</div>
					</div>
					<!-- 商品介绍 -->
					<div class='b-table bcent-table' id='table1'>
						<div class='mc'>
							<div class='p-parameter'>
								<ul id='parameter2' class='p-parameter-list'>
									<li title='${(product.name1)!'' }'>商品名称：${(product.name1)!'' }</li>
									<li title='' style="display:none;">店铺： <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank'>${(seller.sellerName)!''}</a></li>
									<li title='2015-04-01 20:35:24'>上架时间：${(product.upTime?string("yyyy-MM-dd"))!'' }</li>
								
									<#if productAttr?? && productAttr?size &gt; 0>
										<#list productAttr as attr>
											<li title='${(attr.value)!''}'>${(attr.name)!''}：${(attr.value)!''}</li>
										</#list>
									</#if>
								</ul>
							</div>
						</div>
						<div class='detail-content clearfix' style="border: 0px;">
							<div class='detail-content-wrap'>
								<div class='detail-content-item'>
									<p align='center'>
										 <#noescape> ${(product.description)!''}</#noescape>
										<!--<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/center.jpg' style='margin-top:10px;margin-bottom:10px;'>
										<img data-original'${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/center1.jpg' style='margin-top:10px;margin-bottom:10px;'> -->
									</p>
								</div>
							</div>
						</div>
						<!-- 
						添加售后保障  每个商品详情都一样存在  此处使用include 来引入 
						<#include "/front/commons/_After-sale-protection.ftl" />
						-->
						<!-- end -->
					</div>
					<!-- 商品评价 -->
					<div class='b-table'  id='table2'>
						<div id='state'>
							<strong>权利声明：</strong>
							<br>
							大袜网上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是大袜网重要的经营资源，未经许可，禁止非法转载使用。
                			<p>
                				<b>注：</b>
                				本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。
                			</p>
						</div>
						<div id='comment' class='m'>
							<div class='mt'>
								<h2>商品评价</h2>
							</div>
							<div class='mc'>
								<div id='i-comment'>
									<div class='rate'>
										<strong>${(statisticsVO.productCommentsHighProportion)!'' }<span>%</span></strong><br>
										<span>好评度</span>
									</div>
									<div class='percent'>
										<dl>
											<dt>好评<span>(${(statisticsVO.productCommentsHighProportion)!'' }%)</span></dt>
											<dd><div class='progress'></div></dd>
										</dl>
										<dl class='dl'>
											<dt>中评<span>(${(statisticsVO.productCommentsMiddleProportion)!'' }%)</span></dt>
											<dd><div class='progress' style='width:0%'></div></dd>
										</dl>
										<dl class='dl'>
											<dt>差评<span>(${(statisticsVO.productCommentsLowProportion)!'' }%)</span></dt>
											<dd><div class='progress' style='width:0%'></div></dd>
										</dl>
									</div>
								</div>
							</div>
						</div>
						<div id='comments-list' class='m'>
							<div class='mt'>
								<div class='mt-inner tab-trigger-wrap clearfix'>
									<ul class='m-tab-trigger'>
										<li class='li-curr curr comment-li' data-box='1'>
											<a href='javascript:void(0);' onclick="showAllComments('all','${productId}')" >全部评价<em>(${(statisticsVO.productCommentsAllCount)!'' })</em></a>
										</li>
										<li class='li-curr comment-li' data-box='2'>
											<a href='javascript:void(0);' onclick="showAllComments('high','${productId}')">好评<em>(${(statisticsVO.productCommentsHighCount)!'' })</em></a>
										</li>
										<li class='li-curr comment-li' data-box='3'>
											<a href='javascript:void(0);' onclick="showAllComments('middle','${productId}')">中评<em>(${(statisticsVO.productCommentsMiddleCount)!'' })</em></a>
										</li>
										<li class='li-curr comment-li' data-box='4'>
											<a href='javascript:void(0);' onclick="showAllComments('low','${productId}')">差评<em>(${(statisticsVO.productCommentsLowCount)!'' })</em></a>
										</li>
									</ul>
								</div>
							</div>
							
							<!-- 评价列表（全部、好、中、差 -->
							<div id = "commentsList"></div>
							
						</div>
					</div>
					<!-- 咨询 -->
					<div class='b-table' id='table3'>
						<div class='cs-main-wrap mt10 m'>
							<div class='mt'>
								<div class='mt-inner tab-trigger-wrap clearfix'>
									<ul class='m-tab-trigger'>
										<li class="li-curr curr advice-li" data-number='1'>
											<a href='javascript:void(0);' onclick="showProductAskList('${productId!''}')">全部咨询<em>(${(statisticsVO.productAskCount)!'' })</em></a>
										</li>
									</ul>
								</div>
							</div>
							<!--  咨询  列表区域 begin-->
								<div id = "productAskList"></div>
							<!--  咨询  列表区域 end-->
							
						<div id="editConsultaiion" style="height:500px;margin-top:20px;">
							<a style="color:blue" href='javascript:void(0);' onclick="editConsultaiion('${productId!''}','${(seller.id)!''}')">我要咨询</a>
						</div>
						</div>
						
					</div>
					
					 </div>
				</div>
			</div>

		<!-- S优惠券隐藏页面页面 -->
		<div class="toolbar-wrap" style="display: none">
			<h3 class="tbar-panel-header">
				<span class="title"><i></i><em>红包</em></span>
				<span class="close-panel">×</span>
			</h3>
			<div class="coupon-box">
				<div class="coupon-wrap">
					<div class="coupon-type">可领取的券
						<span class="line-left"></span>
						<span class="line-right"></span>
					</div>
					<div id="couponListDiv"></div>
				</div>
			</div>
		</div>
		<!-- E优惠券隐藏页面页面 -->
		
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/details.js?version=v1.01'></script>
<link rel='stylesheet' type='text/css' href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/zoomify/zoomify.min.css' />
<script type='text/javascript' src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/zoomify/zoomify.min.js'></script>
<script type="text/javascript">
//循环多个SKU实现多选功能准备条件
var noomidsarr = new Array();
var noomids = '${noomids!0}';
noomids = noomids.substring(0,noomids.length-1);
noomidsarr = noomids.split(",");
//为价格类型保存一个判断 条件，即如果是1 则是一口价，如果是2 则是阶梯价 ，如果是3 则是整箱价
var priceStatus = $("#priceStatusId").val();
var price2Status = $("#price2StatusId").val();
//思路，设置两个全局打标袜数量和打标袜总金额  当裸袜和二次加工点击时，去加上此值。当打标袜时，去取裸袜和二次加工的总计数量和金额的数值
var dabiaowaTotalCount = 0;
var dabiaowaTotalPrice = 0;
//点击左上角关闭弹出的二次加工窗口
function closeProcessingImg(id){
	$(".processing-img_"+id).hide();
	$(".processing-brand_"+id).hide();
	$("#pcinfo_"+id).html("需要二次加工（加工费不含辅料费）");
	$("#productPackageId_"+id+"_four").val(null);
	$("#isSelfLabel_"+id+"_four").attr("checked",false);
	$("input[name='pak']").attr("checked",false);
	$("#packageCheck_"+id).attr("checked",false);
}
//end
for(var i=0;i<noomidsarr.length;i++){
	// 选中二次加工按钮状态
	$("#packageCheck_"+noomidsarr[i]).click(function(){
		var thisid = $(this).attr("id");
		thisid = thisid.split("_")[1];
		if($(this).is(":checked")){
			$(".processing-img_"+thisid).show();
		}else{
			$(".processing-img_"+thisid).hide();
			$(".processing-brand_"+thisid).hide();
			$("#pcinfo_"+thisid).html("需要二次加工（加工费不含辅料费）");
			$("#productPackageId_"+thisid+"_four").val(null);
			$("#isSelfLabel_"+thisid+"_four").attr("checked",false);
			$("input[name='pak']").attr("checked",false);
		}
	})
	$(".processing-img_"+noomidsarr[i]+" input[name='pak']").bind("click",function(){
			var val = "";
			var thisid = $(this).attr("id");
			thisid = thisid.split("_")[1];
			if ($(this).is(':checked')) {
				var info = "您选择了【";
				var packname = $(this).data("name");
				info += packname + "】,二次加工订单,客服会第一时间与您联系";
				var packname = $(this).data("name");
				val = $(this).val();
				$("#pcinfo_"+thisid).html(info);
				$(this).val($(this).val());
				$("#productPackageId_"+thisid+"_four").val($(this).val());
			}
			$(".processing-img_"+thisid+"input[name='pak']").each(function() {
				if ($(this).val() != val) {
					$(this).attr("checked",false);	
				}
			});
			$(".processing-img_"+thisid).hide();
			$(".processing-brand_"+thisid).show();
		});
	//① 品牌袜  点击每个SKU图片绑定事件11111
	$("#ppwitemid_"+noomidsarr[i]).click(function(){
		clearAutoSlideDetail();
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		if($(this).data('iscustom') == true){
			//加载图片
			var pic_ = $(this).data('pic-url');
			if(pic_){
				var url_ = ";s"
				if(pic_ == "-1"){
					url_ = "${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg";
				} else{
					url_ = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}"+pic_;
				}
				$(".jqzoom img").attr("src",url_).attr("jqimg",url_);
			}
		}
		$(this).addClass("selected").siblings().removeClass("selected");
		//对应的购买数量动态显示与隐藏
		$("#ppwdd_"+thisid).css("display","block");
		$("#ppwdd_"+thisid).siblings().css("display","none");
		$("#mallPcPrice_"+thisid).css("display","inline-block");
		$("#mallPcPrice_"+thisid).siblings("strong").css("display","none");
	})
	//调用本方法实现选中对应SKU
	function skuClickFunction(obj){
		var thisid = $(obj).attr('id');
		thisid = thisid.split("_")[1];
		if($(obj).data('iscustom') == true){
			//加载图片
			var pic_ = $(obj).data('pic-url');
			if(pic_){
				var url_ = ";s"
				if(pic_ == "-1"){
					url_ = "${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg";
				} else{
					url_ = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}"+pic_;
				}
				$(".jqzoom img").attr("src",url_).attr("jqimg",url_);
			}
		}
		$(obj).addClass("selected").siblings().removeClass("selected");
		//对应的购买数量动态显示与隐藏
		$("#ppwdd_"+thisid).css("display","block");
		$("#ppwdd_"+thisid).siblings().css("display","none");
		$("#mallPcPrice_"+thisid).css("display","inline-block");
		$("#mallPcPrice_"+thisid).siblings("strong").css("display","none");
	}
	//② 品牌袜  点击减号 绑定事件监听22222
	$("#productMinus_"+noomidsarr[i]).click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid).val());//购买数量 
		if (number <= 1) {
			number = 0;
			//设置减号为不可点击效果和设置加号为可以点击 效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			$("#productPlus_"+thisid).removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		} else {
			number -= 1;
			if(number < goodsStock){
				//设置加号可以点击效果
				$("#productPlus_"+thisid).removeClass("countbtn unclick").addClass("countbtn countaddbtn");
			}
		}
		$("#ppwnumber_"+thisid).val(parseInt(number));
		//start================
		if(priceStatus!="" && priceStatus==1){//一口价
			//计算所有name="ppwpop_number"的数值之和作为判定条件
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag = 0;
			var ppwTotalPrices = 0;
			for(var i=0;i<$ppwpop_number.length;i++){
				valflag += parseInt($ppwpop_number[i].value);
				ppwTotalPrices += (parseInt($ppwpop_number[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			//购买的总数量
			var ppwTotalCounts = valflag * stockNums;
			var ppwTotalPrices2 = parseFloat(ppwTotalPrices);
			if(isNaN(ppwTotalPrices2)){
				return;
			}
			ppwTotalPrices2 = Math.round(ppwTotalPrices * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(ppwTotalPrices2.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var valflag_ppw = 0;//存放满足阶梯价一的数量
			var jtiprica_ppw = 0;
			var ppwTotalPrice = 0;
			for(var i=0;i<$ppwpop_number.length;i++){//品牌袜阶梯价计算
				valflag_ppw += parseInt($ppwpop_number[i].value) * stockNums;
			}
			if(valflag_ppw <= parseInt(jtiqpl1)){
				jtiprica_ppw = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_ppw && valflag_ppw < parseInt(jtiqpl3)){
				jtiprica_ppw = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_ppw){
				jtiprica_ppw = jtiprice3;
			}
			var ppwTotalCounts = valflag_ppw;
			ppwTotalPrice = ppwTotalCounts * jtiprica_ppw;
			var ppwTotalPrice2 = parseFloat(ppwTotalPrice);
			if(isNaN(ppwTotalPrice2)){
				return;
			}
			ppwTotalPrice2 = Math.round(ppwTotalPrice * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(ppwTotalPrice2.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_ppw;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价
			var ppw_mallPcPrice = $("#ppw_mallPcPrice_"+thisid).val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val()
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var zxjtotalprice = 0;
			var ppwTotalCounts = 0;
			for(var i=0;i<$ppwpop_number.length;i++){
				var tempppwpopnumber  = parseInt($ppwpop_number[i].value) * stockNums;
				ppwTotalCounts += tempppwpopnumber;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice += tempppwpopnumber * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice += (tempppwpopnumber-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(zxjtotalprice.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = null;
				var mathfloorflag = Math.floor(number / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					jiageone += number * stockNums * zxj_mallPcPrice_s;
				}else{
					jiageone += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					jiageone += (number-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}
	})
	//③  品牌袜 点击加号  绑定事件监听 33333
	$("#productPlus_"+noomidsarr[i]).click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid).val());//购买数量 
		if (number >= goodsStock) {
			number = goodsStock;
			//设置加号不可点击效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		} else {
			number += 1;
			if(number >= goodsStock){
				number = goodsStock;
				//设置加号不可点击效果
				$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			}
		}
		//如果购买数量大于0则可以进行减号操作
		if(number >0 ){
			$("#productMinus_"+thisid).removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		}
		$("#ppwnumber_"+thisid).val(parseInt(number));
		//start================
		if(priceStatus!="" && priceStatus==1){//一口价
			//计算所有name="ppwpop_number"的数值之和作为判定条件
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag = 0;
			var ppwTotalPrices = 0;
			for(var i=0;i<$ppwpop_number.length;i++){
				valflag += parseInt($ppwpop_number[i].value);
				ppwTotalPrices += (parseInt($ppwpop_number[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			var ppwTotalCounts = valflag * stockNums;
			var ppwTotalPrices2 = parseFloat(ppwTotalPrices);
			if(isNaN(ppwTotalPrices2)){
				return;
			}
			ppwTotalPrices2 = Math.round(ppwTotalPrices * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(ppwTotalPrices2.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var valflag_ppw = 0;//存放满足阶梯价一的数量
			var jtiprica_ppw = 0;
			var ppwTotalPrice = 0;
			for(var i=0;i<$ppwpop_number.length;i++){//品牌袜阶梯价计算
				valflag_ppw += parseInt($ppwpop_number[i].value) * stockNums;
			}
			if(valflag_ppw <= parseInt(jtiqpl1)){
				jtiprica_ppw = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_ppw && valflag_ppw < parseInt(jtiqpl3)){
				jtiprica_ppw = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_ppw){
				jtiprica_ppw = jtiprice3;
			}
			var ppwTotalCounts = valflag_ppw;
			ppwTotalPrice = ppwTotalCounts * jtiprica_ppw;
			var ppwTotalPrice2 = parseFloat(ppwTotalPrice);
			if(isNaN(ppwTotalPrice2)){
				return;
			}
			ppwTotalPrice2 = Math.round(ppwTotalPrice * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(ppwTotalPrice2.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_ppw;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价
			var ppw_mallPcPrice = $("#ppw_mallPcPrice_"+thisid).val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val()
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var zxjtotalprice = 0;
			var ppwTotalCounts = 0;
			for(var i=0;i<$ppwpop_number.length;i++){
				var tempppwpopnumber  = parseInt($ppwpop_number[i].value) * stockNums;
				ppwTotalCounts += tempppwpopnumber;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice += tempppwpopnumber * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice += (tempppwpopnumber-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(zxjtotalprice.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = null;
				var mathfloorflag = Math.floor(number / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					jiageone += number * stockNums * zxj_mallPcPrice_s;
				}else{
					jiageone += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					jiageone += (number-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}
	})
	//为裸袜中  裸袜的减号绑定监听事件 two44444
	$("#productMinus_"+noomidsarr[i]+"_two").click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid+"_two").val());//购买数量 
		if (number <= 1) {
			number = 0;
			//设置减号为不可点击效果和设置加号为可以点击 效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			$("#productPlus_"+thisid+"_two").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		} else {
			number -= 1;
			if(number < goodsStock){
				//设置加号可以点击效果
				$("#productPlus_"+thisid+"_two").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
			}
		}
		$("#ppwnumber_"+thisid+"_two").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_two").val(parseInt(number));
		//start================
		if(priceStatus!="" && priceStatus==1){//一口价
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value);
				valflag_four += parseInt($ppwpop_number_four[i].value);
				ppwTotalPrices_two += (parseInt($ppwpop_number_two[i].value) * stockNums * $ppw_mallPcPrice[i].value);
				ppwTotalPrices_four += (parseInt($ppwpop_number_four[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			var ppwTotalCounts_two = valflag_two * stockNums;
			var ppwTotalCounts_four = valflag_four * stockNums;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((ppwTotalPrices2_two+ppwTotalPrices2_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((ppwTotalPrices2_two+ppwTotalPrices2_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			var jtiprica_two = 0;
			var jtiprica_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value) * stockNums;
				valflag_four += parseInt($ppwpop_number_four[i].value) * stockNums;
			}
			if(valflag_two <= parseInt(jtiqpl1)){
				jtiprica_two = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_two && valflag_two < parseInt(jtiqpl3)){
				jtiprica_two = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_two){
				jtiprica_two = jtiprice3;
			}
			if(valflag_four <= parseInt(jtiqpl1)){
				jtiprica_four = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_four && valflag_four < parseInt(jtiqpl3)){
				jtiprica_four = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_four){
				jtiprica_four = jtiprice3;
			}
			var ppwTotalCounts_two = valflag_two;
			var ppwTotalCounts_four = valflag_four;
			ppwTotalPrices_two = ppwTotalCounts_two * jtiprica_two;
			ppwTotalPrices_four = ppwTotalCounts_four * jtiprica_four;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100;
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			var ppwTotalPriceTwoFour = ppwTotalPrices2_two + ppwTotalPrices2_four + dabiaowaTotalPrice;
			$(".checked-price-value").text(ppwTotalPriceTwoFour.toFixed(2));
			$("#checked-price-value-id").val(ppwTotalPriceTwoFour.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_two;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价 只针对裸袜
			var ppw_mallPcPrice = $("#ppw_mallPcPrice").val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val();
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var zxjtotalprice_two = 0;
			var zxjtotalprice_four = 0;
			var ppwTotalCounts_two = 0;
			var ppwTotalCounts_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){
				var tempppwpopnumber_two  = parseInt($ppwpop_number_two[i].value) * stockNums;
				ppwTotalCounts_two += tempppwpopnumber_two;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber_two / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice_two += tempppwpopnumber_two * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice_two += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice_two += (tempppwpopnumber_two-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			for(var i=0;i<$ppwpop_number_four.length;i++){
				var tempppwpopnumber_four  = parseInt($ppwpop_number_four[i].value) * stockNums;
				ppwTotalCounts_four += tempppwpopnumber_four;
				zxjtotalprice_four += tempppwpopnumber_four * ppw_mallPcPrice;
			}
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((zxjtotalprice_two+zxjtotalprice_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((zxjtotalprice_two+zxjtotalprice_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = null;
				var mathfloorflag = Math.floor(number / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					jiageone += number * zxj_mallPcPrice_s;
				}else{
					jiageone += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					jiageone += (number-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}
		//end================
	})
	//为裸袜中  裸袜的加号绑定监听事件two55555
	$("#productPlus_"+noomidsarr[i]+"_two").click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid+"_two").val());//购买数量 
		if (number >= goodsStock) {
			number = goodsStock;
			//设置加号不可点击效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		} else {
			number += 1;
			if(number >= goodsStock){
				number = goodsStock;
				//设置加号不可点击效果
				$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			}
		}
		//如果购买数量大于0则可以进行减号操作
		if(number >0 ){
			$("#productMinus_"+thisid+"_two").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		}
		$("#ppwnumber_"+thisid+"_two").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_two").val(parseInt(number));
		if(priceStatus!="" && priceStatus==1){
			//按照一口价的价格计算
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value);
				valflag_four += parseInt($ppwpop_number_four[i].value);
				ppwTotalPrices_two += (parseInt($ppwpop_number_two[i].value) * stockNums * $ppw_mallPcPrice[i].value);
				ppwTotalPrices_four += (parseInt($ppwpop_number_four[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			var ppwTotalCounts_two = valflag_two * stockNums;
			var ppwTotalCounts_four = valflag_four * stockNums;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((ppwTotalPrices2_two+ppwTotalPrices2_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((ppwTotalPrices2_two+ppwTotalPrices2_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			var jtiprica_two = 0;
			var jtiprica_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value) * stockNums;
				valflag_four += parseInt($ppwpop_number_four[i].value) * stockNums;
			}
			if(valflag_two <= parseInt(jtiqpl1)){
				jtiprica_two = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_two && valflag_two < parseInt(jtiqpl3)){
				jtiprica_two = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_two){
				jtiprica_two = jtiprice3;
			}
			if(valflag_four <= parseInt(jtiqpl1)){
				jtiprica_four = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_four && valflag_four < parseInt(jtiqpl3)){
				jtiprica_four = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_four){
				jtiprica_four = jtiprice3;
			}
			var ppwTotalCounts_two = valflag_two;
			var ppwTotalCounts_four = valflag_four;
			ppwTotalPrices_two = ppwTotalCounts_two * jtiprica_two;
			ppwTotalPrices_four = ppwTotalCounts_four * jtiprica_four;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100;
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			var ppwTotalPriceTwoFour = ppwTotalPrices2_two + ppwTotalPrices2_four + dabiaowaTotalPrice;
			$(".checked-price-value").text(ppwTotalPriceTwoFour.toFixed(2));
			$("#checked-price-value-id").val(ppwTotalPriceTwoFour.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_two;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价
			var ppw_mallPcPrice = $("#ppw_mallPcPrice").val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val();
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var zxjtotalprice_two = 0;
			var zxjtotalprice_four = 0;
			var ppwTotalCounts_two = 0;
			var ppwTotalCounts_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){
				var tempppwpopnumber_two  = parseInt($ppwpop_number_two[i].value) * stockNums;
				ppwTotalCounts_two += tempppwpopnumber_two;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber_two / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice_two += tempppwpopnumber_two * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice_two += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice_two += (tempppwpopnumber_two-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			for(var i=0;i<$ppwpop_number_four.length;i++){
				var tempppwpopnumber_four  = parseInt($ppwpop_number_four[i].value) * stockNums;
				ppwTotalCounts_four += tempppwpopnumber_four;
				zxjtotalprice_four += tempppwpopnumber_four * ppw_mallPcPrice;
			}
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((zxjtotalprice_two+zxjtotalprice_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((zxjtotalprice_two+zxjtotalprice_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = null;
				number = number * stockNums;
				var mathfloorflag = Math.floor(number / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					jiageone += number * zxj_mallPcPrice_s;
				}else{
					jiageone += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					jiageone += (number-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}
	})
	//为裸袜中  打标袜减号绑定监听事件three66666
	$("#productMinus_"+noomidsarr[i]+"_three").click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid+"_three").val());//购买数量 
		if (number <= 1) {
			number = 0;
			//设置减号为不可点击效果和设置加号为可以点击 效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			$("#productPlus_"+thisid+"_three").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		} else {
			number -= 1;
			if(number < goodsStock){
				//设置加号可以点击效果
				$("#productPlus_"+thisid+"_three").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
			}
		}
		$("#ppwnumber_"+thisid+"_three").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_three").val(parseInt(number));
		if(price2Status!="" && price2Status==1){//一口价
			//按照一口价的价格计算
			var $ppwpop_number_three = $("input[name=ppwpop_number_three]");
			var valflag_three = 0;
			for(var i=0;i<$ppwpop_number_three.length;i++){//裸袜计算
				valflag_three += parseInt($ppwpop_number_three[i].value);
			}
			var ppwTotalCounts_three = valflag_three * stockNums;
			var ppwTotalPrices_three = ppwTotalCounts_three * $("#dabiaowaPriceOnlyId").val();
			var ppwTotalPrices2_three = parseFloat(ppwTotalPrices_three);
			if(isNaN(ppwTotalPrices2_three)){
				return;
			}
			ppwTotalPrices2_three = Math.round(ppwTotalPrices_three * 100)/100; 
			var checkedamountvalue = $("#checked-amount-value-id").val();//数量
			var checkedpricevalue = $("#checked-price-value-id").val();//价格
			dabiaowaTotalCount = ppwTotalCounts_three;
			dabiaowaTotalPrice = ppwTotalPrices2_three;
			$(".checked-amount-value").text(Number(ppwTotalCounts_three)+Number(checkedamountvalue));
			$(".checked-price-value").text(parseFloat(ppwTotalPrices2_three.toFixed(2))+parseFloat(checkedpricevalue));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_three").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_three").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_three").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_three").css("display","none");
			}
		}else if(price2Status!="" && price2Status==2){//阶梯价
			var jtiqpl1 = $("#dabiaowa_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#dabiaowa_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#dabiaowa_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#dabiaowa_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#dabiaowa_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#dabiaowa_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_three = $("input[name=ppwpop_number_three]");
			var valflag_three_1 = 0;//存放满足阶梯价一的数量
			var valflag_three_2 = 0;//存放满足阶梯价二的数量
			var valflag_three_3 = 0;//存放满足阶梯价三的数量
			for(var i=0;i<$ppwpop_number_three.length;i++){//裸袜计算
				var tempppwpopnumber_three  = parseInt($ppwpop_number_three[i].value) * stockNums ;
				if(tempppwpopnumber_three <= parseInt(jtiqpl1)){
					valflag_three_1 += tempppwpopnumber_three;
				}else if(parseInt(jtiqpl2) <= tempppwpopnumber_three < parseInt(jtiqpl3)){
					valflag_three_2 += tempppwpopnumber_three;
				}else if(parseInt(jtiqpl3) <= tempppwpopnumber_three){
					valflag_three_3 += tempppwpopnumber_three;
				}
			}
			var ppwTotalCounts_three = valflag_three_1 + valflag_three_2 + valflag_three_3;
			var checkedamountvalue = $("#checked-amount-value-id").val();//数量
			var checkedpricevalue = $("#checked-price-value-id").val();//价格
			dabiaowaTotalCount = ppwTotalCounts_three;
			$(".checked-amount-value").text(Number(ppwTotalCounts_three)+Number(checkedamountvalue));
			//计算总价格
			var ppwTotalPrices_three_1 = valflag_three_1 * jtiprice1;
			var ppwTotalPrices_three_2 = valflag_three_2 * jtiprice2;
			var ppwTotalPrices_three_3 = valflag_three_3 * jtiprice3;
			var ppwTotalPrices2_three_1 = parseFloat(ppwTotalPrices_three_1);
			var ppwTotalPrices2_three_2 = parseFloat(ppwTotalPrices_three_2);
			var ppwTotalPrices2_three_3 = parseFloat(ppwTotalPrices_three_3);
			if(isNaN(ppwTotalPrices2_three_1)){
				return;
			}
			if(isNaN(ppwTotalPrices2_three_2)){
				return;
			}
			if(isNaN(ppwTotalPrices2_three_3)){
				return;
			}
			ppwTotalPrices2_three_1 = Math.round(ppwTotalPrices_three_1 * 100)/100; 
			ppwTotalPrices2_three_2 = Math.round(ppwTotalPrices_three_2 * 100)/100; 
			ppwTotalPrices2_three_3 = Math.round(ppwTotalPrices_three_3 * 100)/100; 
			var ppwTotalPriceThree = ppwTotalPrices2_three_1 + ppwTotalPrices2_three_2 + ppwTotalPrices2_three_3;
			$(".checked-price-value").text(parseFloat(ppwTotalPriceThree.toFixed(2))+parseFloat(checkedpricevalue));
			dabiaowaTotalPrice = ppwTotalPriceThree;
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_three").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_three").text(number * stockNums);
				var jiageone = null;
				if(number * stockNums <= parseInt(jtiqpl1)){
					jiageone = number * stockNums * jtiprice1;
				}else if(parseInt(jtiqpl2) <= number * stockNums < parseInt(jtiqpl3)){
					jiageone = number * stockNums * jtiprice2;
				}else if(parseInt(jtiqpl3) <= number * stockNums){
					jiageone = number * stockNums * jtiprice3;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_three").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_three").css("display","none");
			}
		}
	})
	//为裸袜中  打标袜加号绑定监听事件three77777
	$("#productPlus_"+noomidsarr[i]+"_three").click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid+"_three").val());//购买数量 
		if (number >= goodsStock) {
			number = goodsStock;
			//设置加号不可点击效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		} else {
			number += 1;
			if(number >= goodsStock){
				number = goodsStock;
				//设置加号不可点击效果
				$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			}
		}
		//如果购买数量大于0则可以进行减号操作
		if(number >0 ){
			$("#productMinus_"+thisid+"_three").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		}
		$("#ppwnumber_"+thisid+"_three").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_three").val(parseInt(number));
		if(price2Status!="" && price2Status==1){//一口价
			//按照一口价的价格计算
			var $ppwpop_number_three = $("input[name=ppwpop_number_three]");
			var valflag_three = 0;
			for(var i=0;i<$ppwpop_number_three.length;i++){//裸袜计算
				valflag_three += parseInt($ppwpop_number_three[i].value);
			}
			var ppwTotalCounts_three = valflag_three * stockNums;
			var ppwTotalPrices_three = ppwTotalCounts_three * $("#dabiaowaPriceOnlyId").val();
			var ppwTotalPrices2_three = parseFloat(ppwTotalPrices_three);
			if(isNaN(ppwTotalPrices2_three)){
				return;
			}
			ppwTotalPrices2_three = Math.round(ppwTotalPrices_three * 100)/100; 
			var checkedamountvalue = $("#checked-amount-value-id").val();//数量
			var checkedpricevalue = $("#checked-price-value-id").val();//价格
			dabiaowaTotalCount = ppwTotalCounts_three;
			dabiaowaTotalPrice = ppwTotalPrices2_three;
			$(".checked-amount-value").text(Number(ppwTotalCounts_three)+Number(checkedamountvalue));
			$(".checked-price-value").text(parseFloat(ppwTotalPrices2_three.toFixed(2))+parseFloat(checkedpricevalue));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_three").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_three").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_three").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_three").css("display","none");
			}
		}else if(price2Status!="" && price2Status==2){//阶梯价
			var jtiqpl1 = $("#dabiaowa_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#dabiaowa_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#dabiaowa_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#dabiaowa_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#dabiaowa_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#dabiaowa_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_three = $("input[name=ppwpop_number_three]");
			var valflag_three_1 = 0;//存放满足阶梯价一的数量
			var valflag_three_2 = 0;//存放满足阶梯价二的数量
			var valflag_three_3 = 0;//存放满足阶梯价三的数量
			for(var i=0;i<$ppwpop_number_three.length;i++){//裸袜计算
				var tempppwpopnumber_three  = parseInt($ppwpop_number_three[i].value) * stockNums ;
				if(tempppwpopnumber_three <= parseInt(jtiqpl1)){
					valflag_three_1 += tempppwpopnumber_three;
				}else if(parseInt(jtiqpl2) <= tempppwpopnumber_three < parseInt(jtiqpl3)){
					valflag_three_2 += tempppwpopnumber_three;
				}else if(parseInt(jtiqpl3) <= tempppwpopnumber_three){
					valflag_three_3 += tempppwpopnumber_three;
				}
			}
			var ppwTotalCounts_three = valflag_three_1 + valflag_three_2 + valflag_three_3;
			var checkedamountvalue = $("#checked-amount-value-id").val();//数量
			var checkedpricevalue = $("#checked-price-value-id").val();//价格
			dabiaowaTotalCount = ppwTotalCounts_three;
			$(".checked-amount-value").text(Number(ppwTotalCounts_three)+Number(checkedamountvalue));
			//计算总价格
			var ppwTotalPrices_three_1 = valflag_three_1 * jtiprice1;
			var ppwTotalPrices_three_2 = valflag_three_2 * jtiprice2;
			var ppwTotalPrices_three_3 = valflag_three_3 * jtiprice3;
			var ppwTotalPrices2_three_1 = parseFloat(ppwTotalPrices_three_1);
			var ppwTotalPrices2_three_2 = parseFloat(ppwTotalPrices_three_2);
			var ppwTotalPrices2_three_3 = parseFloat(ppwTotalPrices_three_3);
			if(isNaN(ppwTotalPrices2_three_1)){
				return;
			}
			if(isNaN(ppwTotalPrices2_three_2)){
				return;
			}
			if(isNaN(ppwTotalPrices2_three_3)){
				return;
			}
			ppwTotalPrices2_three_1 = Math.round(ppwTotalPrices_three_1 * 100)/100; 
			ppwTotalPrices2_three_2 = Math.round(ppwTotalPrices_three_2 * 100)/100; 
			ppwTotalPrices2_three_3 = Math.round(ppwTotalPrices_three_3 * 100)/100; 
			var ppwTotalPriceThree = ppwTotalPrices2_three_1 + ppwTotalPrices2_three_2 + ppwTotalPrices2_three_3;
			$(".checked-price-value").text(parseFloat(ppwTotalPriceThree.toFixed(2))+parseFloat(checkedpricevalue));
			dabiaowaTotalPrice = ppwTotalPriceThree;
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_three").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_three").text(number * stockNums);
				var jiageone = null;
				if(number * stockNums <= parseInt(jtiqpl1)){
					jiageone = number * stockNums * jtiprice1;
				}else if(parseInt(jtiqpl2) <= number * stockNums < parseInt(jtiqpl3)){
					jiageone = number * stockNums * jtiprice2;
				}else if(parseInt(jtiqpl3) <= number * stockNums){
					jiageone = number * stockNums * jtiprice3;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_three").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_three").css("display","none");
			}
		}
	})
	
	
	//为裸袜中  二次加工减号绑定监听事件four88888
	$("#productMinus_"+noomidsarr[i]+"_four").click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid+"_four").val());//购买数量 
		if (number <= 1) {
			number = 0;
			//设置减号为不可点击效果和设置加号为可以点击 效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			$("#productPlus_"+thisid+"_four").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		} else {
			number -= 1;
			if(number < goodsStock){
				//设置加号可以点击效果
				$("#productPlus_"+thisid+"_four").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
			}
		}
		$("#ppwnumber_"+thisid+"_four").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_four").val(parseInt(number));
		if(priceStatus!="" && priceStatus==1){
			//按照一口价的价格计算
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value);
				valflag_four += parseInt($ppwpop_number_four[i].value);
				ppwTotalPrices_two += (parseInt($ppwpop_number_two[i].value) * stockNums * $ppw_mallPcPrice[i].value);
				ppwTotalPrices_four += (parseInt($ppwpop_number_four[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			var ppwTotalCounts_two = valflag_two * stockNums;
			var ppwTotalCounts_four = valflag_four * stockNums;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((ppwTotalPrices2_two+ppwTotalPrices2_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((ppwTotalPrices2_two+ppwTotalPrices2_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_four").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_four").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			var jtiprica_two = 0;
			var jtiprica_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value) * stockNums;
				valflag_four += parseInt($ppwpop_number_four[i].value) * stockNums;
			}
			if(valflag_two <= parseInt(jtiqpl1)){
				jtiprica_two = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_two && valflag_two < parseInt(jtiqpl3)){
				jtiprica_two = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_two){
				jtiprica_two = jtiprice3;
			}
			if(valflag_four <= parseInt(jtiqpl1)){
				jtiprica_four = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_four && valflag_four < parseInt(jtiqpl3)){
				jtiprica_four = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_four){
				jtiprica_four = jtiprice3;
			}
			var ppwTotalCounts_two = valflag_two;
			var ppwTotalCounts_four = valflag_four;
			ppwTotalPrices_two = ppwTotalCounts_two * jtiprica_two;
			ppwTotalPrices_four = ppwTotalCounts_four * jtiprica_four;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100;
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			var ppwTotalPriceTwoFour = ppwTotalPrices2_two + ppwTotalPrices2_four + dabiaowaTotalPrice;
			$(".checked-price-value").text(ppwTotalPriceTwoFour.toFixed(2));
			$("#checked-price-value-id").val(ppwTotalPriceTwoFour.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_four").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_four;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_four").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价
			var ppw_mallPcPrice = $("#ppw_mallPcPrice").val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val();
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var zxjtotalprice_two = 0;
			var zxjtotalprice_four = 0;
			var ppwTotalCounts_two = 0;
			var ppwTotalCounts_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){
				var tempppwpopnumber_two  = parseInt($ppwpop_number_two[i].value) * stockNums;
				ppwTotalCounts_two += tempppwpopnumber_two;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber_two / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice_two += tempppwpopnumber_two * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice_two += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice_two += (tempppwpopnumber_two-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			for(var i=0;i<$ppwpop_number_four.length;i++){
				var tempppwpopnumber_four  = parseInt($ppwpop_number_four[i].value) * stockNums;
				ppwTotalCounts_four += tempppwpopnumber_four;
				zxjtotalprice_four += tempppwpopnumber_four * ppw_mallPcPrice;
			}
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((zxjtotalprice_two+zxjtotalprice_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((zxjtotalprice_two+zxjtotalprice_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_four").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
				var jiageone = null;
				jiageone += number * stockNums * ppw_mallPcPrice;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_four").css("display","none");
			}
		}
	})
	//为裸袜中  二次加工加号绑定监听事件four99999
	$("#productPlus_"+noomidsarr[i]+"_four").click(function(e){
		var thisid = $(this).attr('id');
		thisid = thisid.split("_")[1];
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var goodsStock = parseInt($("#productStock_"+thisid).val())/stockNums;//库存数量 注意是除以数量单位后的值
		var number = parseInt($("#ppwnumber_"+thisid+"_four").val());//购买数量 
		if (number >= goodsStock) {
			number = goodsStock;
			//设置加号不可点击效果
			$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		} else {
			number += 1;
			if(number >= goodsStock){
				number = goodsStock;
				//设置加号不可点击效果
				$(this).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			}
		}
		//如果购买数量大于0则可以进行减号操作
		if(number >0 ){
			$("#productMinus_"+thisid+"_four").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		}
		$("#ppwnumber_"+thisid+"_four").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_four").val(parseInt(number));
		if(priceStatus!="" && priceStatus==1){
			//按照一口价的价格计算
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value);
				valflag_four += parseInt($ppwpop_number_four[i].value);
				ppwTotalPrices_two += (parseInt($ppwpop_number_two[i].value) * stockNums * $ppw_mallPcPrice[i].value);
				ppwTotalPrices_four += (parseInt($ppwpop_number_four[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			var ppwTotalCounts_two = valflag_two * stockNums;
			var ppwTotalCounts_four = valflag_four * stockNums;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((ppwTotalPrices2_two+ppwTotalPrices2_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((ppwTotalPrices2_two+ppwTotalPrices2_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_four").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_four").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			var jtiprica_two = 0;
			var jtiprica_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value) * stockNums;
				valflag_four += parseInt($ppwpop_number_four[i].value) * stockNums;
			}
			if(valflag_two <= parseInt(jtiqpl1)){
				jtiprica_two = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_two && valflag_two < parseInt(jtiqpl3)){
				jtiprica_two = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_two){
				jtiprica_two = jtiprice3;
			}
			if(valflag_four <= parseInt(jtiqpl1)){
				jtiprica_four = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_four && valflag_four < parseInt(jtiqpl3)){
				jtiprica_four = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_four){
				jtiprica_four = jtiprice3;
			}
			var ppwTotalCounts_two = valflag_two;
			var ppwTotalCounts_four = valflag_four;
			ppwTotalPrices_two = ppwTotalCounts_two * jtiprica_two;
			ppwTotalPrices_four = ppwTotalCounts_four * jtiprica_four;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100;
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			var ppwTotalPriceTwoFour = ppwTotalPrices2_two + ppwTotalPrices2_four + dabiaowaTotalPrice;
			$(".checked-price-value").text(ppwTotalPriceTwoFour.toFixed(2));
			$("#checked-price-value-id").val(ppwTotalPriceTwoFour.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_four").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_four;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_four").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价
			var ppw_mallPcPrice = $("#ppw_mallPcPrice").val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val();
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var zxjtotalprice_two = 0;
			var zxjtotalprice_four = 0;
			var ppwTotalCounts_two = 0;
			var ppwTotalCounts_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){
				var tempppwpopnumber_two  = parseInt($ppwpop_number_two[i].value) * stockNums;
				ppwTotalCounts_two += tempppwpopnumber_two;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber_two / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice_two += tempppwpopnumber_two * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice_two += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice_two += (tempppwpopnumber_two-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			for(var i=0;i<$ppwpop_number_four.length;i++){
				var tempppwpopnumber_four  = parseInt($ppwpop_number_four[i].value) * stockNums;
				ppwTotalCounts_four += tempppwpopnumber_four;
				zxjtotalprice_four += tempppwpopnumber_four * ppw_mallPcPrice;
			}
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((zxjtotalprice_two+zxjtotalprice_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((zxjtotalprice_two+zxjtotalprice_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_four").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
				var jiageone = null;
				jiageone += number * stockNums * ppw_mallPcPrice;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_four").css("display","none");
			}
		}
	})
	//============下面}是一个for循环的结束================================
}
//已选清单DIV，点击其他区域也要实现隐藏操作
function stopPropagation(e) { 
　　if (e.stopPropagation) 
　　　　e.stopPropagation(); 
　　else 
　　　　e.cancelBubble = true; 
} 
$(document).bind("click",function(){ 
	var bool = $($(".obj-list .list-selected")[0].firstElementChild).hasClass("fa fa-angle-up");
	if(!bool){
		$(".obj-list .list-selected").css({"background-color":"#f9f9f9","border-bottom":"1px solid #e5e5e5"});
		$($(".obj-list .list-selected")[0].firstElementChild).removeClass("fa fa-angle-down").addClass("fa fa-angle-up");
		//隐藏列表
		$(".list-info").css("display","none");
	}else{
	}
}); 
$(".list-info").bind('click',function(e){ 
    stopPropagation(e); 
}); 
//已选清单	鼠标点击事件绑定 
$(".obj-list .list-selected").click(function(e){
    stopPropagation(e); 
	var bool = $($(this)[0].firstElementChild).hasClass("fa fa-angle-up");
	if(bool){
		$(this).css({"background-color":"#fff","border-bottom":"1px solid #e5e5e5"});
		$($(this)[0].firstElementChild).removeClass("fa fa-angle-up").addClass("fa fa-angle-down");
		//显示列表
		$(".list-info").css("display","block");
	}else{
		$(this).css({"background-color":"#f9f9f9","border-bottom":"1px solid #e5e5e5"});
		$($(this)[0].firstElementChild).removeClass("fa fa-angle-down").addClass("fa fa-angle-up");
		//隐藏列表
		$(".list-info").css("display","none");
	}
})
//品牌袜 输出框输入购买数量后触发事件
function checknum(obj){
		var val = $(obj).val();
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(0);
			//设置减号不可点击
			$("#productMinus_"+thisid).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		}
		var thisid = $(obj).attr("id");
		thisid = thisid.split("_")[1];//取得此时SKU对应的ID
		var stockNums = parseInt($("#stockNums").val());//取得数量单位10 12这种
		var number = $(obj).val() * stockNums;//获取数量 
		var goodsStock = parseInt($("#productStock_"+thisid).val());//获取库存数量
		if (number == null || parseInt(number) < goodsStock) {//购买数量小于库存
			number = number/stockNums;
			$(obj).val(parseInt(number));
			//激活减号可以点击效果
			$("#productMinus_"+thisid).removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		} else if (number != null && parseInt(number) >= goodsStock) {//购买数量大于等于库存
			number = goodsStock/stockNums;
			$(obj).val(parseInt(number));
			//设置加号为不可点击效果
			$("#productPlus_"+thisid).removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			//激活减号可以点击效果
			$("#productMinus_"+thisid).removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		}
		//start=======================
		if(priceStatus!="" && priceStatus==1){
			//计算所有name="ppwpop_number"的数值之和作为判定条件
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag = 0;
			var ppwTotalPrices = 0;
			for(var i=0;i<$ppwpop_number.length;i++){
				valflag += parseInt($ppwpop_number[i].value);
				ppwTotalPrices += (parseInt($ppwpop_number[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			//购买的总数量
			var ppwTotalCounts = valflag * stockNums;
			var ppwTotalPrices2 = parseFloat(ppwTotalPrices);
			if(isNaN(ppwTotalPrices2)){
				return;
			}
			ppwTotalPrices2 = Math.round(ppwTotalPrices * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(ppwTotalPrices2.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var valflag_ppw = 0;//存放满足阶梯价一的数量
			var jtiprica_ppw = 0;
			var ppwTotalPrice = 0;
			for(var i=0;i<$ppwpop_number.length;i++){//品牌袜阶梯价计算
				valflag_ppw += parseInt($ppwpop_number[i].value) * stockNums;
			}
			if(valflag_ppw <= parseInt(jtiqpl1)){
				jtiprica_ppw = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_ppw && valflag_ppw < parseInt(jtiqpl3)){
				jtiprica_ppw = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_ppw){
				jtiprica_ppw = jtiprice3;
			}
			var ppwTotalCounts = valflag_ppw;
			ppwTotalPrice = ppwTotalCounts * jtiprica_ppw;
			var ppwTotalPrice2 = parseFloat(ppwTotalPrice);
			if(isNaN(ppwTotalPrice2)){
				return;
			}
			ppwTotalPrice2 = Math.round(ppwTotalPrice * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(ppwTotalPrice2.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_ppw;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){
			var ppw_mallPcPrice = $("#ppw_mallPcPrice_"+thisid).val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val()
			var $ppwpop_number = $("input[name=ppwpop_number]");
			var zxjtotalprice = 0;
			var ppwTotalCounts = 0;
			for(var i=0;i<$ppwpop_number.length;i++){
				var tempppwpopnumber  = parseInt($ppwpop_number[i].value) * stockNums;
				ppwTotalCounts += tempppwpopnumber;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice += tempppwpopnumber * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice += (tempppwpopnumber-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			$(".checked-amount-value").text(ppwTotalCounts);
			$(".checked-price-value").text(zxjtotalprice.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid).css("display","block");
				$("#tablelisttdspanA_"+thisid).text(number * stockNums);
				var jiageone = null;
				var mathfloorflag = Math.floor(number / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					jiageone += number * stockNums * zxj_mallPcPrice_s;
				}else{
					jiageone += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					jiageone += (number-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid).text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid).css("display","none");
			}
		}
} 
//裸袜 输出框输入购买数量后触发事件   two
function checknumtwo(obj){
		var val = $(obj).val();
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(0);
			//设置减号不可点击
			$("#productMinus_"+thisid+"_two").removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		}
		var thisid = $(obj).attr("id");
		thisid = thisid.split("_")[1];//取得此时SKU对应的ID
		var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
		var number = $(obj).val() * stockNums;//获取数量
		var goodsStock = parseInt($("#productStock_"+thisid).val());//获取库存数量
		if (number == null || parseInt(number) < goodsStock) {//购买数量小于库存
			number = number/stockNums;
			$(obj).val(parseInt(number));
			//设置减号为不可点击效果和设置加号为可以点击 效果
			//激活减号可以点击效果
			$("#productMinus_"+thisid+"_two").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		} else if(number != null && parseInt(number) >= goodsStock){//购买数量大于等于库存
			number = goodsStock/stockNums;
			$(obj).val(parseInt(number));
			//设置加号为不可点击效果
			$("#productPlus_"+thisid+"_two").removeClass("countbtn countaddbtn").addClass("countbtn unclick");
			//激活减号可以点击效果
			$("#productMinus_"+thisid+"_two").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
		}
		$("#ppwnumber_"+thisid+"_two").val(parseInt(number));
		$("#buy-num-hidden_"+thisid+"_two").val(parseInt(number));
		//start================
		if(priceStatus!="" && priceStatus==1){//一口价
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value);
				valflag_four += parseInt($ppwpop_number_four[i].value);
				ppwTotalPrices_two += (parseInt($ppwpop_number_two[i].value) * stockNums * $ppw_mallPcPrice[i].value);
				ppwTotalPrices_four += (parseInt($ppwpop_number_four[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			}
			var ppwTotalCounts_two = valflag_two * stockNums;
			var ppwTotalCounts_four = valflag_four * stockNums;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100; 
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((ppwTotalPrices2_two+ppwTotalPrices2_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((ppwTotalPrices2_two+ppwTotalPrices2_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==2){//阶梯价
			var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
			var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
			var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
			var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
			var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
			var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var valflag_two = 0;
			var valflag_four = 0;
			var ppwTotalPrices_two = 0;
			var ppwTotalPrices_four = 0;
			var jtiprica_two = 0;
			var jtiprica_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
				valflag_two += parseInt($ppwpop_number_two[i].value) * stockNums;
				valflag_four += parseInt($ppwpop_number_four[i].value) * stockNums;
			}
			if(valflag_two <= parseInt(jtiqpl1)){
				jtiprica_two = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_two && valflag_two < parseInt(jtiqpl3)){
				jtiprica_two = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_two){
				jtiprica_two = jtiprice3;
			}
			if(valflag_four <= parseInt(jtiqpl1)){
				jtiprica_four = jtiprice1;
			}else if(parseInt(jtiqpl2) <= valflag_four && valflag_four < parseInt(jtiqpl3)){
				jtiprica_four = jtiprice2;
			}else if(parseInt(jtiqpl3) <= valflag_four){
				jtiprica_four = jtiprice3;
			}
			var ppwTotalCounts_two = valflag_two;
			var ppwTotalCounts_four = valflag_four;
			ppwTotalPrices_two = ppwTotalCounts_two * jtiprica_two;
			ppwTotalPrices_four = ppwTotalCounts_four * jtiprica_four;
			var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
			var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
			if(isNaN(ppwTotalPrices2_two)){
				return;
			}
			if(isNaN(ppwTotalPrices2_four)){
				return;
			}
			ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
			ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100;
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			var ppwTotalPriceTwoFour = ppwTotalPrices2_two + ppwTotalPrices2_four + dabiaowaTotalPrice;
			$(".checked-price-value").text(ppwTotalPriceTwoFour.toFixed(2));
			$("#checked-price-value-id").val(ppwTotalPriceTwoFour.toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = null;
				jiageone = number * stockNums * jtiprica_two;
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}else if(priceStatus!="" && priceStatus==3){//整箱价 只针对裸袜
			var ppw_mallPcPrice = $("#ppw_mallPcPrice").val();
			var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
			var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
			var fullContainerQuantity = $("#fullContainerQuantity_id").val();
			var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
			var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
			var zxjtotalprice_two = 0;
			var zxjtotalprice_four = 0;
			var ppwTotalCounts_two = 0;
			var ppwTotalCounts_four = 0;
			for(var i=0;i<$ppwpop_number_two.length;i++){
				var tempppwpopnumber_two  = parseInt($ppwpop_number_two[i].value) * stockNums;
				ppwTotalCounts_two += tempppwpopnumber_two;
				//如果值大于500 则按照1.19每双计价 小于500则按1.24
				var mathfloorflag = Math.floor(tempppwpopnumber_two / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					zxjtotalprice_two += tempppwpopnumber_two * zxj_mallPcPrice_s;
				}else{
					zxjtotalprice_two += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					zxjtotalprice_two += (tempppwpopnumber_two-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
			}
			for(var i=0;i<$ppwpop_number_four.length;i++){
				var tempppwpopnumber_four  = parseInt($ppwpop_number_four[i].value) * stockNums;
				ppwTotalCounts_four += tempppwpopnumber_four;
				zxjtotalprice_four += tempppwpopnumber_four * ppw_mallPcPrice;
			}
			$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
			$(".checked-price-value").text((zxjtotalprice_two+zxjtotalprice_four+dabiaowaTotalPrice).toFixed(2));
			$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
			$("#checked-price-value-id").val((zxjtotalprice_two+zxjtotalprice_four).toFixed(2));
			//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
			if(number>0){
				$("#tablelisttr_"+thisid+"_two").css("display","block");
				$("#tablelisttdspanA_"+thisid+"_two").text(number * stockNums);
				var jiageone = null;
				var mathfloorflag = Math.floor(number / fullContainerQuantity);
				if(mathfloorflag==0){//取1.24即散价计算
					jiageone += number * zxj_mallPcPrice_s;
				}else{
					jiageone += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
					jiageone += (number-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
				}
				var jiagetwo = parseFloat(jiageone);
				if(isNaN(jiagetwo)){
					return;
				}
				jiagetwo = Math.round(jiageone * 100)/100; 
				$("#tablelisttdspanB_"+thisid+"_two").text(jiagetwo.toFixed(2));
			}else if(number==0){
				$("#tablelisttr_"+thisid+"_two").css("display","none");
			}
		}
		//end================
}
//裸袜 输出框输入购买数量后触发事件   three
function checknumthree(obj){
	var val = $(obj).val();
	//判断是否为正整数
	if(!isIntege1(val)){
		$(obj).val(0);
		//设置减号不可点击
		$("#productMinus_"+thisid+"_three").removeClass("countbtn countaddbtn").addClass("countbtn unclick");
	}
	var thisid = $(obj).attr("id");
	thisid = thisid.split("_")[1];//取得此时SKU对应的ID
	var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
	var number = $(obj).val() * stockNums;//获取数量
	var goodsStock = parseInt($("#productStock_"+thisid).val());//获取库存数量
	if (number == null || parseInt(number) < goodsStock) {//购买数量小于库存
		number = number/stockNums;
		$(obj).val(parseInt(number));
		//设置减号为不可点击效果和设置加号为可以点击 效果
		//激活减号可以点击效果
		$("#productMinus_"+thisid+"_three").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
	} else if(number != null && parseInt(number) >= goodsStock){//购买数量大于等于库存
		number = goodsStock/stockNums;
		$(obj).val(parseInt(number));
		//设置加号为不可点击效果
		$("#productPlus_"+thisid+"_three").removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		//激活减号可以点击效果
		$("#productMinus_"+thisid+"_three").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
	}
	$("#ppwnumber_"+thisid+"_three").val(parseInt(number));
	$("#buy-num-hidden_"+thisid+"_three").val(parseInt(number));
	//start================
	if(price2Status!="" && price2Status==1){//一口价
		//按照一口价的价格计算
		var $ppwpop_number_three = $("input[name=ppwpop_number_three]");
		var valflag_three = 0;
		for(var i=0;i<$ppwpop_number_three.length;i++){//裸袜计算
			valflag_three += parseInt($ppwpop_number_three[i].value);
		}
		var ppwTotalCounts_three = valflag_three * stockNums;
		var ppwTotalPrices_thre = ppwTotalCounts_three * $("#dabiaowaPriceOnlyId").val();
		var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
		if(isNaN(ppwTotalPrices2_two)){
			return;
		}
		ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
		var checkedamountvalue = $("#checked-amount-value-id").val();//数量
		var checkedpricevalue = $("#checked-price-value-id").val();//价格
		dabiaowaTotalCount = ppwTotalCounts_four;
		dabiaowaTotalPrice = ppwTotalPrices2_two;
		$(".checked-amount-value").text(Number(ppwTotalCounts_four)+Number(checkedamountvalue));
		$(".checked-price-value").text(parseFloat(ppwTotalPrices2_two.toFixed(2))+parseFloat(checkedpricevalue));
		//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
		if(number>0){
			$("#tablelisttr_"+thisid+"_three").css("display","block");
			$("#tablelisttdspanA_"+thisid+"_three").text(number * stockNums);
			var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
			var jiagetwo = parseFloat(jiageone);
			if(isNaN(jiagetwo)){
				return;
			}
			jiagetwo = Math.round(jiageone * 100)/100; 
			$("#tablelisttdspanB_"+thisid+"_three").text(jiagetwo.toFixed(2));
		}else if(number==0){
			$("#tablelisttr_"+thisid+"_three").css("display","none");
		}
	}else if(price2Status!="" && price2Status==2){//阶梯价
		var jtiqpl1 = $("#dabiaowa_mallPcPrice1_qpl").val();//第一阶梯数量
		var jtiqpl2 = $("#dabiaowa_mallPcPrice2_qpl").val();//第二阶梯数量
		var jtiqpl3 = $("#dabiaowa_mallPcPrice3_qpl").val();//第三阶梯数量
		var jtiprice1 = $("#dabiaowa_mallPcPrice1").val();//第一阶梯价格
		var jtiprice2 = $("#dabiaowa_mallPcPrice2").val();//第二阶梯价格
		var jtiprice3 = $("#dabiaowa_mallPcPrice3").val();//第三阶梯价格
		var $ppwpop_number_three = $("input[name=ppwpop_number_three]");
		var valflag_three_1 = 0;//存放满足阶梯价一的数量
		var valflag_three_2 = 0;//存放满足阶梯价二的数量
		var valflag_three_3 = 0;//存放满足阶梯价三的数量
		for(var i=0;i<$ppwpop_number_three.length;i++){//裸袜计算
			var tempppwpopnumber_three  = parseInt($ppwpop_number_three[i].value) * stockNums ;
			if(tempppwpopnumber_three <= parseInt(jtiqpl1)){
				valflag_three_1 += tempppwpopnumber_three;
			}else if(parseInt(jtiqpl2) <= tempppwpopnumber_three < parseInt(jtiqpl3)){
				valflag_three_2 += tempppwpopnumber_three;
			}else if(parseInt(jtiqpl3) <= tempppwpopnumber_three){
				valflag_three_3 += tempppwpopnumber_three;
			}
		}
		var ppwTotalCounts_three = valflag_three_1 + valflag_three_2 + valflag_three_3;
		var checkedamountvalue = $("#checked-amount-value-id").val();//数量
		var checkedpricevalue = $("#checked-price-value-id").val();//价格
		dabiaowaTotalCount = ppwTotalCounts_three;
		$(".checked-amount-value").text(Number(ppwTotalCounts_three)+Number(checkedamountvalue));
		//计算总价格
		var ppwTotalPrices_three_1 = valflag_three_1 * jtiprice1;
		var ppwTotalPrices_three_2 = valflag_three_2 * jtiprice2;
		var ppwTotalPrices_three_3 = valflag_three_3 * jtiprice3;
		var ppwTotalPrices2_three_1 = parseFloat(ppwTotalPrices_three_1);
		var ppwTotalPrices2_three_2 = parseFloat(ppwTotalPrices_three_2);
		var ppwTotalPrices2_three_3 = parseFloat(ppwTotalPrices_three_3);
		if(isNaN(ppwTotalPrices2_three_1)){
			return;
		}
		if(isNaN(ppwTotalPrices2_three_2)){
			return;
		}
		if(isNaN(ppwTotalPrices2_three_3)){
			return;
		}
		ppwTotalPrices2_three_1 = Math.round(ppwTotalPrices_three_1 * 100)/100; 
		ppwTotalPrices2_three_2 = Math.round(ppwTotalPrices_three_2 * 100)/100; 
		ppwTotalPrices2_three_3 = Math.round(ppwTotalPrices_three_3 * 100)/100; 
		var ppwTotalPriceThree = ppwTotalPrices2_three_1 + ppwTotalPrices2_three_2 + ppwTotalPrices2_three_3;
		$(".checked-price-value").text(parseFloat(ppwTotalPriceThree.toFixed(2))+parseFloat(checkedpricevalue));
		dabiaowaTotalPrice = ppwTotalPriceThree;
		//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
		if(number>0){
			$("#tablelisttr_"+thisid+"_three").css("display","block");
			$("#tablelisttdspanA_"+thisid+"_three").text(number * stockNums);
			var jiageone = null;
			if(number * stockNums <= parseInt(jtiqpl1)){
				jiageone = number * stockNums * jtiprice1;
			}else if(parseInt(jtiqpl2) <= number * stockNums < parseInt(jtiqpl3)){
				jiageone = number * stockNums * jtiprice2;
			}else if(parseInt(jtiqpl3) <= number * stockNums){
				jiageone = number * stockNums * jtiprice3;
			}
			var jiagetwo = parseFloat(jiageone);
			if(isNaN(jiagetwo)){
				return;
			}
			jiagetwo = Math.round(jiageone * 100)/100; 
			$("#tablelisttdspanB_"+thisid+"_three").text(jiagetwo.toFixed(2));
		}else if(number==0){
			$("#tablelisttr_"+thisid+"_three").css("display","none");
		}
	}
	//end================
}
//裸袜 输出框输入购买数量后触发事件   four
function checknumfour(obj){
	var val = $(obj).val();
	//判断是否为正整数
	if(!isIntege1(val)){
		$(obj).val(0);
		//设置减号不可点击
		$("#productMinus_"+thisid+"_four").removeClass("countbtn countaddbtn").addClass("countbtn unclick");
	}
	var thisid = $(obj).attr("id");
	thisid = thisid.split("_")[1];//取得此时SKU对应的ID
	var stockNums = parseInt($("#stockNums").val());//一手10双即10这个数量单位
	var number = $(obj).val() * stockNums;//获取数量
	var goodsStock = parseInt($("#productStock_"+thisid).val());//获取库存数量
	if (number == null || parseInt(number) < goodsStock) {//购买数量小于库存
		number = number/stockNums;
		$(obj).val(parseInt(number));
		//激活减号可以点击效果
		$("#productMinus_"+thisid+"_four").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
	} else if(number != null && parseInt(number) >= goodsStock){//购买数量大于等于库存
		number = goodsStock/stockNums;
		$(obj).val(parseInt(number));
		//设置加号为不可点击效果
		$("#productPlus_"+thisid+"_four").removeClass("countbtn countaddbtn").addClass("countbtn unclick");
		//激活减号可以点击效果
		$("#productMinus_"+thisid+"_four").removeClass("countbtn unclick").addClass("countbtn countaddbtn");
	}
	$("#ppwnumber_"+thisid+"_four").val(parseInt(number));
	$("#buy-num-hidden_"+thisid+"_four").val(parseInt(number));
	//start==========================
	if(priceStatus!="" && priceStatus==1){
		//按照一口价的价格计算
		var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
		var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
		var $ppw_mallPcPrice = $("input[name=ppw_mallPcPrice]");
		var valflag_two = 0;
		var valflag_four = 0;
		var ppwTotalPrices_two = 0;
		var ppwTotalPrices_four = 0;
		for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
			valflag_two += parseInt($ppwpop_number_two[i].value);
			valflag_four += parseInt($ppwpop_number_four[i].value);
			ppwTotalPrices_two += (parseInt($ppwpop_number_two[i].value) * stockNums * $ppw_mallPcPrice[i].value);
			ppwTotalPrices_four += (parseInt($ppwpop_number_four[i].value) * stockNums * $ppw_mallPcPrice[i].value);
		}
		var ppwTotalCounts_two = valflag_two * stockNums;
		var ppwTotalCounts_four = valflag_four * stockNums;
		var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
		var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
		if(isNaN(ppwTotalPrices2_two)){
			return;
		}
		if(isNaN(ppwTotalPrices2_four)){
			return;
		}
		ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
		ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100; 
		$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
		$(".checked-price-value").text((ppwTotalPrices2_two+ppwTotalPrices2_four+dabiaowaTotalPrice).toFixed(2));
		$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
		$("#checked-price-value-id").val((ppwTotalPrices2_two+ppwTotalPrices2_four).toFixed(2));
		//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
		if(number>0){
			$("#tablelisttr_"+thisid+"_four").css("display","block");
			$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
			var jiageone = number * stockNums * $("#ppw_mallPcPrice_"+thisid).val();
			var jiagetwo = parseFloat(jiageone);
			if(isNaN(jiagetwo)){
				return;
			}
			jiagetwo = Math.round(jiageone * 100)/100; 
			$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
		}else if(number==0){
			$("#tablelisttr_"+thisid+"_four").css("display","none");
		}
	}else if(priceStatus!="" && priceStatus==2){//阶梯价
		var jtiqpl1 = $("#jti_mallPcPrice1_qpl").val();//第一阶梯数量
		var jtiqpl2 = $("#jti_mallPcPrice2_qpl").val();//第二阶梯数量
		var jtiqpl3 = $("#jti_mallPcPrice3_qpl").val();//第三阶梯数量
		var jtiprice1 = $("#jti_mallPcPrice1").val();//第一阶梯价格
		var jtiprice2 = $("#jti_mallPcPrice2").val();//第二阶梯价格
		var jtiprice3 = $("#jti_mallPcPrice3").val();//第三阶梯价格
		var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
		var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
		var valflag_two = 0;
		var valflag_four = 0;
		var ppwTotalPrices_two = 0;
		var ppwTotalPrices_four = 0;
		var jtiprica_two = 0;
		var jtiprica_four = 0;
		for(var i=0;i<$ppwpop_number_two.length;i++){//裸袜计算
			valflag_two += parseInt($ppwpop_number_two[i].value) * stockNums;
			valflag_four += parseInt($ppwpop_number_four[i].value) * stockNums;
		}
		if(valflag_two <= parseInt(jtiqpl1)){
			jtiprica_two = jtiprice1;
		}else if(parseInt(jtiqpl2) <= valflag_two && valflag_two < parseInt(jtiqpl3)){
			jtiprica_two = jtiprice2;
		}else if(parseInt(jtiqpl3) <= valflag_two){
			jtiprica_two = jtiprice3;
		}
		if(valflag_four <= parseInt(jtiqpl1)){
			jtiprica_four = jtiprice1;
		}else if(parseInt(jtiqpl2) <= valflag_four && valflag_four < parseInt(jtiqpl3)){
			jtiprica_four = jtiprice2;
		}else if(parseInt(jtiqpl3) <= valflag_four){
			jtiprica_four = jtiprice3;
		}
		var ppwTotalCounts_two = valflag_two;
		var ppwTotalCounts_four = valflag_four;
		ppwTotalPrices_two = ppwTotalCounts_two * jtiprica_two;
		ppwTotalPrices_four = ppwTotalCounts_four * jtiprica_four;
		var ppwTotalPrices2_two = parseFloat(ppwTotalPrices_two);
		var ppwTotalPrices2_four = parseFloat(ppwTotalPrices_four);
		if(isNaN(ppwTotalPrices2_two)){
			return;
		}
		if(isNaN(ppwTotalPrices2_four)){
			return;
		}
		ppwTotalPrices2_two = Math.round(ppwTotalPrices_two * 100)/100; 
		ppwTotalPrices2_four = Math.round(ppwTotalPrices_four * 100)/100;
		$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
		$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
		var ppwTotalPriceTwoFour = ppwTotalPrices2_two + ppwTotalPrices2_four + dabiaowaTotalPrice;
		$(".checked-price-value").text(ppwTotalPriceTwoFour.toFixed(2));
		$("#checked-price-value-id").val(ppwTotalPriceTwoFour.toFixed(2));
		//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
		if(number>0){
			$("#tablelisttr_"+thisid+"_four").css("display","block");
			$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
			var jiageone = null;
			jiageone = number * stockNums * jtiprica_four;
			var jiagetwo = parseFloat(jiageone);
			if(isNaN(jiagetwo)){
				return;
			}
			jiagetwo = Math.round(jiageone * 100)/100; 
			$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
		}else if(number==0){
			$("#tablelisttr_"+thisid+"_four").css("display","none");
		}
	}else if(priceStatus!="" && priceStatus==3){//整箱价
		var ppw_mallPcPrice = $("#ppw_mallPcPrice").val();
		var zxj_mallPcPrice_t = $("#zxj_mallPcPrice_t").val();
		var zxj_mallPcPrice_s = $("#zxj_mallPcPrice_s").val();
		var fullContainerQuantity = $("#fullContainerQuantity_id").val();
		var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
		var $ppwpop_number_four = $("input[name=ppwpop_number_four]");
		var zxjtotalprice_two = 0;
		var zxjtotalprice_four = 0;
		var ppwTotalCounts_two = 0;
		var ppwTotalCounts_four = 0;
		for(var i=0;i<$ppwpop_number_two.length;i++){
			var tempppwpopnumber_two  = parseInt($ppwpop_number_two[i].value) * stockNums;
			ppwTotalCounts_two += tempppwpopnumber_two;
			//如果值大于500 则按照1.19每双计价 小于500则按1.24
			var mathfloorflag = Math.floor(tempppwpopnumber_two / fullContainerQuantity);
			if(mathfloorflag==0){//取1.24即散价计算
				zxjtotalprice_two += tempppwpopnumber_two * zxj_mallPcPrice_s;
			}else{
				zxjtotalprice_two += mathfloorflag * fullContainerQuantity * zxj_mallPcPrice_t;
				zxjtotalprice_two += (tempppwpopnumber_two-mathfloorflag * fullContainerQuantity) * zxj_mallPcPrice_s;
			}
		}
		for(var i=0;i<$ppwpop_number_four.length;i++){
			var tempppwpopnumber_four  = parseInt($ppwpop_number_four[i].value) * stockNums;
			ppwTotalCounts_four += tempppwpopnumber_four;
			zxjtotalprice_four += tempppwpopnumber_four * ppw_mallPcPrice;
		}
		$(".checked-amount-value").text(ppwTotalCounts_two+ppwTotalCounts_four+dabiaowaTotalCount);
		$(".checked-price-value").text((zxjtotalprice_two+zxjtotalprice_four+dabiaowaTotalPrice).toFixed(2));
		$("#checked-amount-value-id").val(ppwTotalCounts_two+ppwTotalCounts_four);
		$("#checked-price-value-id").val((zxjtotalprice_two+zxjtotalprice_four).toFixed(2));
		//已选清单列表显示 如果有数量不为0则就显示清单列表中，如果数量为0则不显示出来
		if(number>0){
			$("#tablelisttr_"+thisid+"_four").css("display","block");
			$("#tablelisttdspanA_"+thisid+"_four").text(number * stockNums);
			var jiageone = null;
			jiageone += number * stockNums * ppw_mallPcPrice;
			var jiagetwo = parseFloat(jiageone);
			if(isNaN(jiagetwo)){
				return;
			}
			jiagetwo = Math.round(jiageone * 100)/100; 
			$("#tablelisttdspanB_"+thisid+"_four").text(jiagetwo.toFixed(2));
		}else if(number==0){
			$("#tablelisttr_"+thisid+"_four").css("display","none");
		}
	}
	//end==========================
}
//品牌袜   加入进货单
$("#ppwform_docart").click(function(){
	//未登录不能添加进货单
	if (!isUserLogin()) {
		showid('ui-dialog');
		return;
	}
	//计算所有name="ppwpop_number"的数值之和作为判定条件
	var $ppwpop_number = $("input[name=ppwpop_number]");
	var $ppwpop_productGoodsId = $("input[name=ppwpop_productGoodsId]");
	var productId = $("#productId").val();
	var stockNums = parseInt($("#stockNums").val());
	var valflag = 0;
	for(var i=0;i<$ppwpop_number.length;i++){
		valflag += parseInt($ppwpop_number[i].value);
	}
	if(valflag==0){
		//如果订购数量为0，则提示用户不能为0
		$("#itemInfo .tip-container").css("display","block");
		setTimeout(function(){//3秒提示后自动清除
			$("#itemInfo .tip-container").css("display","none");
		},3000);
		return;
	}
	//上面条件都满足了，下面就可以传值到后台了
	for(var i=0;i<$ppwpop_number.length;i++){
		//购买数量不为0的商品后台处理
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
						jAlert("添加成功","提示",function(){
							window.location.reload(true);
						});
					}else{
						//jAlert(data.message);
						jConfirm(data.message, "提示", function(r){
							if(r){
								//跳转结算页面
								window.location.href=domain+"/cart/detail.html";
							}
						});
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			}); 
			//ajax end----------==================
		}
	}
	//for end=========================
})
//品牌袜   立即订购 
$("#ppwform_dopurchase").click(function(){
	//未登录不能添加进货单
	if (!isUserLogin()) {
		showid('ui-dialog');
		return;
	}
	//计算所有name="ppwpop_number"的数值之和作为判定条件
	var $ppwpop_number = $("input[name=ppwpop_number]");
	var $ppwpop_productGoodsId = $("input[name=ppwpop_productGoodsId]");
	var productId = $("#productId").val();
	var stockNums = parseInt($("#stockNums").val());
	var valflag = 0;
	for(var i=0;i<$ppwpop_number.length;i++){
		valflag += parseInt($ppwpop_number[i].value);
	}
	if(valflag==0){
		//如果订购数量为0，则提示用户不能为0
		$("#itemInfo .tip-container").css("display","block");
		setTimeout(function(){//3秒提示后自动清除
			$("#itemInfo .tip-container").css("display","none");
		},3000);
		return;
	}
	//上面条件都满足了，下面就可以传值到后台了
	for(var i=0;i<$ppwpop_number.length;i++){
		//购买数量不为0的商品后台处理
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
						jAlert("订购成功","提示",function(){
							window.location.href=domain+"/cart/detail.html";
						});
					}else{
						//jAlert(data.message);
						jConfirm(data.message, "提示", function(r){
							if(r){
								//跳转结算页面
								window.location.href=domain+"/cart/detail.html";
							}
						});
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			}); 
			//ajax end----------==================
		}
	}
	//for end=========================
})
/**
*品牌袜 颜色全选
*/
function selectAllSKUForPPW(id){
	//未登录不能颜色全选
	if (!isUserLogin()) {
		showid('ui-dialog');
		return;
	}
	var productId = $("#productId").val();
	var stockNums = parseInt($("#stockNums").val());
	var $ppwpop_number = $("input[name=ppwpop_number]");
	var $ppwpop_productGoodsId = $("input[name=ppwpop_productGoodsId]");
	jConfirm("确定要选中所有袜子吗？", "提示", function(r){
		if(r){
			//请求后台，将此货品下所有sku存在的库存都添加到该用户的进货单中
			var $ppwProductStock = $("input[name=ppwProductStock]");
			for(var i=0;i<$ppwProductStock.length;i++){
				if(parseInt($ppwProductStock[i].value)/stockNums >= 1 ){
					$ppwpop_number[i].value = parseInt($ppwProductStock[i].value)/stockNums;
				}
				//console.dir($ppwpop_number[i].value+"|"+$ppwpop_productGoodsId[i].value+"|"+productId);
			}
			//上面条件都满足了，下面就可以传值到后台了
			for(var i=0;i<$ppwpop_number.length;i++){
				//购买数量不为0的商品后台处理
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
								jAlert("订购成功","提示",function(){
									window.location.href=domain+"/cart/detail.html";
								});
							}else{
								jAlert(data.message);
							}
						},
						error : function() {
							jAlert("数据加载失败！");
						}
					}); 
					//ajax end----------==================
				}
			}
		}
	});
}
//=========================
//裸袜    加入进货单
$("#luowaform_docart").click(function(){
	//未登录不能添加进货单
	if (!isUserLogin()) {
		showid('ui-dialog');
		return;
	}
	//计算所有name="ppwpop_number"的数值之和作为判定条件
	var $luowacount = $("input[name=luowacount]");
	var $luowapop_productGoodsId = $("input[name=ppwpop_productGoodsId]");//sku的id
	var $luowapop_dabiaowaFlag = $("input[name=luowapop_dabiaowaFlag]");//是否为打标袜标识
	var $productPackageId = $("input[name=productPackageId]");//二次加工套餐ID
	var $isSelfLabel = $("input[name=isSelfLabel]");//二次加工是否自供标
	var $ppwpop_number_four = $("input[name=ppwpop_number_four]");//所有二次加工数量
	var productId = $("#productId").val();//spu商品ID
	var stockNums = parseInt($("#stockNums").val());//销量单位
	var valflag = 0;
	for(var i=0;i<$luowacount.length;i++){
		valflag += parseInt($luowacount[i].value);
	}
	if(valflag==0){
		//如果订购数量为0，则提示用户不能为0
		$("#itemInfo .tip-container").css("display","block");
		$("#itemInfo .tip-container").html("<div class=\"ppwtip\">订购数量必须为大于0的整数</div>");
		setTimeout(function(){//3秒提示后自动清除
			$("#itemInfo .tip-container").css("display","none");
		},3000);
		return;
	}
	//如果有二次加工的SKU，则一定要选择加工方式，否则提示用户。
	for(var i=0;i<$ppwpop_number_four.length;i++){
		if(parseInt($ppwpop_number_four[i].value) > 0){
			var thisid = $($ppwpop_number_four[i]).attr("id");
			thisid = thisid.split("_")[1];
			if($("#productPackageId_"+thisid+"_four").val()==""){
				$("#itemInfo .tip-container").css("display","block");
				$("#itemInfo .tip-container").html("<div class=\"ppwtip\">二次加工商品必须选择加工方式</div>");
				setTimeout(function(){//3秒提示后自动隐藏
					$("#itemInfo .tip-container").css("display","none");
				},3000);
				return;
			}
		}
	}
	for(var i=0;i<$luowacount.length;i++){
		if(parseInt($luowacount[i].value) > 0){
			var thisid = $($luowacount[i]).attr("id");
			thisid = thisid.split("_")[1];
			$.ajax({
				type : "POST",
				url :  domain+"/cart/addtocart.html",
				data : {
					productId:parseInt(productId), 
					productGoodId:parseInt($("#ppwpopproductGoodsId_"+thisid).val()),
					number:parseInt($luowacount[i].value*stockNums),
					dabiaowaFlag:$luowapop_dabiaowaFlag[i].value,
					isSelfLabel:$($isSelfLabel[i]).is(":checked")?1:0,
					productPackageId:$productPackageId[i].value
				},
				dataType : "json",
				success : function(data) {
					if(data.success){
						jAlert("添加成功","提示",function(){
							window.location.reload(true);
						});
					}else{
						//jAlert(data.message);
						jConfirm(data.message, "提示", function(r){
							if(r){
								//跳转结算页面
								window.location.href=domain+"/cart/detail.html";
							}
						});
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}
	
})
//裸袜 立即订购 luowaform_dopurchase
$("#luowaform_dopurchase").click(function(){
	//未登录不能添加进货单
	if (!isUserLogin()) {
		showid('ui-dialog');
		return;
	}
	//计算所有name="ppwpop_number"的数值之和作为判定条件
	var $luowacount = $("input[name=luowacount]");
	var $luowapop_productGoodsId = $("input[name=ppwpop_productGoodsId]");//sku的id
	var $luowapop_dabiaowaFlag = $("input[name=luowapop_dabiaowaFlag]");//是否为打标袜标识
	var $productPackageId = $("input[name=productPackageId]");//二次加工套餐ID
	var $isSelfLabel = $("input[name=isSelfLabel]");//二次加工是否自供标
	var $ppwpop_number_four = $("input[name=ppwpop_number_four]");//所有二次加工数量
	var productId = $("#productId").val();//spu商品ID
	var stockNums = parseInt($("#stockNums").val());//销量单位
	var valflag = 0;
	for(var i=0;i<$luowacount.length;i++){
		valflag += parseInt($luowacount[i].value);
	}
	if(valflag==0){
		//如果订购数量为0，则提示用户不能为0
		$("#itemInfo .tip-container").css("display","block");
		$("#itemInfo .tip-container").html("<div class=\"ppwtip\">订购数量必须为大于0的整数</div>");
		setTimeout(function(){//3秒提示后自动清除
			$("#itemInfo .tip-container").css("display","none");
		},3000);
		return;
	}
	//如果有二次加工的SKU，则一定要选择加工方式，否则提示用户。
	for(var i=0;i<$ppwpop_number_four.length;i++){
		if(parseInt($ppwpop_number_four[i].value) > 0){
			var thisid = $($ppwpop_number_four[i]).attr("id");
			thisid = thisid.split("_")[1];
			if($("#productPackageId_"+thisid+"_four").val()==""){
				$("#itemInfo .tip-container").css("display","block");
				$("#itemInfo .tip-container").html("<div class=\"ppwtip\">二次加工商品必须选择加工方式</div>");
				setTimeout(function(){//3秒提示后自动隐藏
					$("#itemInfo .tip-container").css("display","none");
				},3000);
				return;
			}
		}
	}
	for(var i=0;i<$luowacount.length;i++){
		if(parseInt($luowacount[i].value) > 0){
			var thisid = $($luowacount[i]).attr("id");
			thisid = thisid.split("_")[1];
			$.ajax({
				type : "POST",
				url :  domain+"/cart/addtocart.html",
				data : {
					productId:parseInt(productId), 
					productGoodId:parseInt($("#ppwpopproductGoodsId_"+thisid).val()),
					number:parseInt($luowacount[i].value*stockNums),
					dabiaowaFlag:$("#dabiaowaFlagId_"+thisid).val(),
					isSelfLabel:$($isSelfLabel[i]).is(":checked")?1:0,
					productPackageId:$productPackageId[i].value
				},
				dataType : "json",
				success : function(data) {
					if(data.success){
						jAlert("订购成功","提示",function(){
							window.location.href=domain+"/cart/detail.html";
						});
					}else{
						//jAlert(data.message);
						jConfirm(data.message, "提示", function(r){
							if(r){
								//跳转结算页面
								window.location.href=domain+"/cart/detail.html";
							}
						});
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}
	
})
/**
 * 裸袜 颜色全选
 */
function selectAllSKUForLuowa(id){
	//未登录不能颜色全选
	if (!isUserLogin()) {
		showid('ui-dialog');
		return;
	}
	var productId = $("#productId").val();
	var stockNums = parseInt($("#stockNums").val());
	var $ppwpop_number_two = $("input[name=ppwpop_number_two]");
	var $ppwProductStock = $("input[name=ppwProductStock]");
	var $luowacount = $("input[name=luowacount]");
	var $luowapop_productGoodsId = $("input[name=ppwpop_productGoodsId]");//sku的id
	var $luowapop_dabiaowaFlag = $("input[name=luowapop_dabiaowaFlag]");//是否为打标袜标识
	var $productPackageId = $("input[name=productPackageId]");//二次加工套餐ID
	var $isSelfLabel = $("input[name=isSelfLabel]");//二次加工是否自供标
	var $ppwpop_number_four = $("input[name=ppwpop_number_four]");//所有二次加工数量
	
	jConfirm("确定要选中所有袜子吗？", "提示", function(r){
		if(r){
			//请求后台，将此货品下所有sku存在的库存都添加到该用户的进货单中
			for(var i=0;i<$ppwProductStock.length;i++){
				if(parseInt($ppwProductStock[i].value)/stockNums >= 1 ){
					$ppwpop_number_two[i].nextElementSibling.nextElementSibling.value = parseInt($ppwProductStock[i].value)/stockNums;
				}
				//console.dir($ppwpop_number[i].value+"|"+$ppwpop_productGoodsId[i].value+"|"+productId);
			}
			
			for(var i=0;i<$luowacount.length;i++){
				if(parseInt($luowacount[i].value) > 0){
					var thisid = $($luowacount[i]).attr("id");
					thisid = thisid.split("_")[1];
					$.ajax({
						type : "POST",
						url :  domain+"/cart/addtocart.html",
						data : {
							productId:parseInt(productId), 
							productGoodId:parseInt($("#ppwpopproductGoodsId_"+thisid).val()),
							number:parseInt($luowacount[i].value*stockNums),
							dabiaowaFlag:$("#dabiaowaFlagId_"+thisid).val(),
							isSelfLabel:$($isSelfLabel[i]).is(":checked")?1:0,
							productPackageId:$productPackageId[i].value
						},
						dataType : "json",
						success : function(data) {
							if(data.success){
								jAlert("订购成功","提示",function(){
									window.location.href=domain+"/cart/detail.html";
								});
							}else{
								jAlert(data.message);
							}
						},
						error : function() {
							jAlert("数据加载失败！");
						}
					});
				}
			}
		}
	});
}
/********end*********/
//保存销售单位 盒或者双
var unitVal = '${product.unit}';
function cateTopAjax() {
	var productId = $("#productId").val();
	ajaxRecommend($("#cateTopId"), 805, 2, productId);
	
	ajaxRecommend($(".class_recommend"), 804, 4, productId);
}

function ajaxRecommend(obj, id, size, productId) {
	var cateTopHtml = "<ul>";
	var m_price	= "";
	$.ajax({
        type:"get",
        url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/recommend.html?q_recommendType=" + id + "&q_size=" + size + "&q_product_id=" + productId,
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
               	//data.data.splice(3,1);
                $.each(data.rows, function(i, recommend){
                	var product = recommend.product;
                	m_price = product.mallPcPrice;
                	/*if(product.priceStatus == 2){
                		m_price = product.description;
                	}*/
                	cateTopHtml += "<li><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' class='recommend-img' target='_blank'>";
                    cateTopHtml += "<img class='img-thumbnail' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "'>";
                    cateTopHtml += "<div title='" + product.name1 + "'>" + product.name1 + "</div>";
                    cateTopHtml += "<em style=color:red;>￥" + m_price + "</em></strong></a></li>";
                })
                cateTopHtml += "</ul>";
                obj.append(cateTopHtml);
            }
        }
    });
    return cateTopHtml;
}

//点击 立即购买触发
function addCart(productId,sellerId) {
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		<#--
		<#if Session.memberSession??>
			 <#assign user = Session.memberSession.member>
		</#if>
		<#if !user??>
			jAlert("请登录之后再添加进货单！");   
			return false;
		</#if>
		-->
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if(data.success){
						//跳转到添加进货单成功页面
						window.open(domain+"/cart/add.html");  
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
//================end
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
	
	$(function(){
		//根据当前的goodId的数值修改默认选中SKU=============================
			var selectedNormAttrId = '${selectedNormAttrId}';
			if(selectedNormAttrId!=""){
				skuClickFunction($("#ppwitemid_"+selectedNormAttrId));
			}
		//根据当前的goodId的数值修改默认选中SKU=============================
		$("#dabiaoxinxieclipss").ellipsis({row: 2});
		//=================
		<#if user??>
		$(".selected-tit i").tooltip({
			animation:true,
			trigger:'hover' //触发tooltip的事件
		});
		</#if>
		
		$(".atip").tooltip({
			animation:true,
			html:true,
			trigger:'hover' //触发tooltip的事件
		});
		 
		// 已选清单状态
		$(".selected-list-box").on("click",function(){
			$(".selected-list-hide").toggleClass("selected-list-show");
		});
		
		$("#packageCheck").attr("checked",false);
		$("#isSelfLabel").attr("checked",false);

		$('.a-img img').zoomify();
	
		//和渲染热卖推荐商品对应的JS
		cateTopAjax();
		/****************
		// 点击二次加工套餐系列
		$(".processing-img li").on("click",function(){
			var info = "需要二次加工，订单提交后，客服会第一时间与您联系，您选择了【";
			var packname = $(this).find("p").html().trim();
			info += packname + "】";
			$(this).parent().parent().hide();
			$(".processing-brand").show();
			
			$("#productPackageId").val($(this).data("product-pageck-id"));
			
			$("#pcinfo").html(info);
		});
		*****************/
		
		$('.close-panel').on('click',function(){
			$('.toolbar-wrap').hide(500);
		});
		
		//异步加载评价及咨询列表
		showAllComments("all","${productId!''}");
		showProductAskList("${productId!''}");
		showProductActInfo('${productId!0}','${(seller.id)!0}');
		showProductFlashSaleInfo('${productId!0}');
		
		//默认将规格选中
		/* var norms = $("#goodsNormAttrId").val();
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
		} */
		
		//选择规格
		/* $(".choosenorms .item").click(function(){
			//Terry add 20160805 若库存小于100则不能选择
			var stock = $(this).data('stock');
			if (stock == 1) return;
			//Terry add end
			if($(this).data('iscustom') == true){
				//加载图片
				var pic_ = $(this).data('pic-url');
				if(pic_){
					var url_ = ";s"
					if(pic_ == "-1"){
						url_ = "${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg";
					} else{
						url_ = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}"+pic_;
					}
					$(".jqzoom img").attr("src",url_).attr("jqimg",url_);
				}
			}
			//为隐藏域赋值
			$(this).siblings(".attrid").val("").val($(this).attr("val"));
			//规格详情
			var norminfo = $(this).parent().siblings(".dt").html();
			var attrinfo = $(this).find("a").attr("title");
			$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
			$(this).addClass("selected").siblings().removeClass("selected");
			//异步加载价格及库存信息
			queryPrice();
		}); */
		
		//校验
		jQuery.validator.addMethod("balance", function(value,element) {
			var productStock = Number($("#productStock").val());
			   var  stockNums = $("#stockNums").val;
	        // value = parseInt(value)*10;
	           value = parseInt(value)*parseInt(stockNums);
	           productStock = parseInt(productStock);
	         return this.optional(element) || value<= productStock; 
	    }, $.validator.format(" 不能大于库存 "));
	          
	    jQuery.validator.addMethod("checkBalance", function(value,element) {
	    	 var  stockNums = $("#stockNums").val;
	         var  balance=0;
	         //value = parseInt(value)*10;
	         value = parseInt(value)*parseInt(stockNums);
	         return this.optional(element) || value>balance;   
	    }, $.validator.format(" 购买数量必须大于0 "));
	    
		jQuery("#cartForm").validate({
			errorPlacement : function(error, element) {
				var obj = element.parent().parent().
					siblings(".em-errMes").css('display', 'block');
				error.appendTo(obj).css("background","#FFEEEE");
			},
	        rules : {
	            "count":{required:true,number:true,balance:true,checkBalance:true}
	        },
	        messages:{
	            "count":{required:"请输入数量",number:"请输入数字"}
	        }
	    }); 
		
		//添加进货单  立即购买点击事件
		$(".buynow,.addcart").click(function(){
			var isAddcart = $(this).hasClass("addcart");
			//未登录不能添加进货单
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			
			var status = $("#status").val();
			var productId = $("#productId").val();
			if(status>0 && productId==10){
				jAlert("尊敬的用户，该商品仅供首次购买本商城商品时体验购买，请选择其它商品！");
				return;
			}
			
			//如果有规格，判断是否选择了规格，如果没有规格则判断是否有货品ID
			//默认只有两个规格
			var Selectcolor = $(this).parents("#itemInfo").find("#ChooseNorm0 .item");
			var SelectVersion = $(this).parents("#itemInfo").find("#ChooseNorm1 .item");
			if(Selectcolor.hasClass("selected") && SelectVersion.hasClass("selected")){
				$(this).parents("#itemInfo").find(".tzm-border").css("display",'none');
			}else{
				// 如果是没有规格的商品，则判断隐藏的货品ID，如果有则通过，没有则报错
				var goodId = $("#productGoodsId").val();
				if (goodId == null || goodId == "") {
					$(this).parents("#itemInfo").find(".tzm-border").css("display",'block');
					return false;
				}
			}
			if($("#cartForm").valid()){
				//重新赋值  计算方式：*10
				var stockNums = $("#stockNums").val();
				$("#buy-num-hidden").val($("#buy-num").val()*parseInt(stockNums));
				//先做购买数校验
				var inputstock_ = Number($("#buy-num-hidden").val());
				var maxStock_ = Number($("#maxStock").val());
				if(inputstock_ > maxStock_){
					if(confirm("很抱歉，您目前最大只能购买"+maxStock_+unitVal+
							"商品，系统将会修改您下单的数量，是否继续提交？")){
						$("#buy-num-hidden").val(maxStock_);
					} else{
						return false;
					}
				}
				
				var params = $("#cartForm").serialize();
				 $.ajax({
					type : "POST",
					url :  domain+"/cart/addtocart.html",
					data : params,
					dataType : "json",
					success : function(data) {
						if(data.success){
							if(isAddcart){
								//不跳转
								window.location.reload(true);
							}else{
								//跳转到提交订单页面
								window.location.href=domain+"/order/info.html";
							}
						}else{
							jAlert(data.message);
						}
					},
					error : function() {
						jAlert("数据加载失败！");
					}
				}); 
			} 
		});
	}); 
	//页面初始加载完毕
	
	
	/**
    * 输入购买数量后进行校验
	*/
 /* function checknum(obj){
		var val = $(obj).val();
		var datanow = $(obj).attr("data-now");
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(datanow);
			return false;
		}
		//如果值为10 不能点-
		var decrement = $(obj).siblings(".btn-reduce");
	    if (parseInt(val)==10){
	    	$(decrement).attr('disabled',true);
	    }else if(parseInt(val) < 10){
			$(decrement).attr('disabled',true);
			val = parseInt(val) + 10;
			$(obj).val(val);
			return false;
		} else{
	    	$(decrement).removeAttr("disabled");
	    }
		$(obj).attr("data-now",val);
	}  */
	//显示所有评价列表
	function showAllComments(type,productId){
		$.ajax({
			type : "POST",
			url :  domain+"/commentsList.html",
			data : {type:type,productId:productId,targetDiv:"commentsList"},
			dataType : "html",
			success : function(data) {
				$('#commentsList').html(data);
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
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
						//商城价格
						$("#mallPcPrice").html("￥"+goods.mallPcPrice);
						//库存
						//var stockinfo_ = "货源充足";
						var stockinfo_ = "库存"+goods.productStock+unitVal;
						var color_ = "green";
						var ps_ = Number(goods.productStock);
						var warn_ = Number(goods.productStockWarning);
						if(ps_ < warn_){
							//stockinfo_ = "货源紧张";
							stockinfo_ = "库存"+goods.productStock+unitVal;
							color_ = "red";
						}
						$("#productStockWarning").html(stockinfo_).css("color",color_);
						$("#productStock").val(goods.productStock);
						//货品ID
						$("#productGoodsId").val(goods.id);
						//最大购买数
						$("#maxStock").val(goods.maxStock);
					}else{
						//无货品信息 则不能添加进货单和购买
						jAlert("货品信息为空，请与管理员联系");
						$(".buynow").attr("disabled","disabled");
						$(".addcart").attr("disabled","disabled");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}
	
	
	//显示咨询列表
	function showProductAskList(productId){
		$.ajax({
			type : "POST",
			url :  domain+"/productAskList.html",
			data : {productId:productId,targetDiv:"productAskList"},
			dataType : "html",
			success : function(data) {
				$('#productAskList').html(data);
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
	/**
	 * 关注产品
	 */
	function collectProductMy(id){
		//未登录不能关注产品
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{productId:id},
			url:domain+'/member/docollectproduct.html',
			success:function(data){
				if(data.success){
					// jAlert("收藏成功");
					//window.location.href=domain+"/member/collectproduct.html";
					$("#collectProduct").html("<i class=\"fa fa-star fa-lg\" style=\"color:#ff7e18;\"></i><span style=\"color:#ff7e18;\">&nbsp;已收藏</span>");
					//$("#collectProduct").attr("disabled","disabled");
				}else{
					jAlert(data.message);
				}
			}
		});
	}
	
	/**
	 * 关注店铺
	 */
	function collectShop(id){
		//未登录不能关注店铺
		if (!isUserLogin()) {
			showid('ui-dialog');
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
					//jAlert("收藏成功");
					$("#collectShop").html("已收藏");
					$("#collectShop").attr("disabled","disabled");
				}else{
					jAlert(data.message);
				}
			}
		});
	}
	
	
	/**
	 * 添加咨询
	 */
	function editConsultaiion(productId,sellerId){
		//未登录不能添加咨询
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
	 	 $.ajax({
			type:"GET",
			url:domain+"/member/addquestion.html",
			dataType:"html",
			async : false,
			data:{productId:productId,sellerId:sellerId},
			success:function(data){
				//加载数据
				$("#editConsultaiion").html(data);
			},
			error:function(){
				jAlert("异常，请重试！");
			}
		});
	}
	
	// 显示优惠券列表
	function showCouponList() {
		if($('.toolbar-wrap').css('display')=='none'){
			$('.toolbar-wrap').show(500);
		}else {
			$('.toolbar-wrap').hide(500);
		}
	}
	//倒计时JS代码==============
	// 结束时间  通过后台获取
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
		var stockNums = $("#stockNums").val();
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
						// 都是空不作操作
					} else {
						var actHtml = '<div class="dt">促销信息：</div>';
						actHtml += '<div class="dd">';
						var actSingle = productActVO.actSingle;
						if (actSingle != null) {
							actHtml += '	<span class="fullCut">';
							if (actSingle.type == 1) {
								actHtml += '		<em>立减</em> 下单即享' + actSingle.discount + '元优惠';
							} else if (actSingle.type == 2) {
							//	var dis = parseInt(parseFloat(actSingle.discount)*10);
								var dis = parseInt(parseFloat(actSingle.discount)*parseInt(stockNums));
								actHtml += '		<em>立减</em> 下单即享' + dis + '折优惠';
							}
							actHtml += '	</span>';
						}
						var actFull = productActVO.actFull;
						if (actFull != null) {
							actHtml += '	<span class="fullCut">';
							actHtml += '		<em>满减</em> ';
							// 满999.00减10.00，满4999.00减100.00，满12999.00减300.00
							if (actFull.firstFull != null && actFull.firstFull > 0) {
								actHtml += '满' + actFull.firstFull + '减' + actFull.firstDiscount;
							}
							if (actFull.secondFull != null && actFull.secondFull > 0) {
								actHtml += '，满' + actFull.secondFull + '减' + actFull.secondDiscount;
							}
							if (actFull.thirdFull != null && actFull.thirdFull > 0) {
								actHtml += '，满' + actFull.thirdFull + '减' + actFull.thirdDiscount;
							}
							actHtml += '	</span>';
						}
						actHtml += '</div>';
						$("#actInfoDiv").html(actHtml);
						var productId = $("#productId").val();
						if (productId == 2104) return;
						//倒计时效果=========================
						endTime = new Date(Date.parse(actSingle.endTime.replace(/-/g,"/")));
						var daojishiHtml = "";
						daojishiHtml += "<div id=\"daojishiDiv\" style=\"position: absolute;top:6px;right: 155px;color:#222;\">";
						daojishiHtml +=	"<span class=\"fa fa-clock-o\"><span>&#12288;剩余<strong id=\"RemainD\"></strong>天<strong id=\"RemainH\"></strong>时<strong id=\"RemainM\"></strong>分<strong id=\"RemainS\"></strong>秒";
						daojishiHtml += "</div>";
						$("#actInfoDiv").append(daojishiHtml);
						var timer_rt = window.setInterval("GetRTime()", 1000);
						//倒计时效果=========================
					}
					
					// 加载优惠券信息
					var couponList = productActVO.couponList;
					if (couponList != null && couponList.length > 0) {
						var couponBtnHtml = '<span >领　　券：</span>'
										  + '<a href="javascript:;" class="J-open-tb receive" onclick="showCouponList()"><span>我要领券</span></a>';
						//$("#couponInfoDiv").html(couponBtnHtml);
						var couponListHtml = "";
						for (var i=0; i < couponList.length; i++) {
							var coupon = couponList[i];
							couponListHtml += '<div class="coupon-item">';
							couponListHtml += '	<div class="coupon-price">';
							couponListHtml += '		<em>￥</em><span>'+ coupon.couponValue +'</span>';
							couponListHtml += '	</div>';
							couponListHtml += '	<div class="coupon-info">';
							couponListHtml += '		<span class="coupon-info-tit">仅限'+ sellerName +'使用</span>';
							couponListHtml += '		<span class="condition">满'+coupon.minAmount+'元可用</span>';
							couponListHtml += '	</div>';
							couponListHtml += '	<a href="javascript:;" class="btn-get" onclick=receiveCoupon(' + coupon.id + ')>';
							couponListHtml += '		立即领取';
							couponListHtml += '	</a>';
							couponListHtml += '	<p class="coupon-time">有效期:'+coupon.useStartTime.substring(0,10)+' - '+coupon.useEndTime.substring(0,10)+'</p>';
							couponListHtml += '</div>';
						}
						$("#couponListDiv").html(couponListHtml);
					}
					
				} else {
					
				}
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
						var flashHtml = '<div class="dt">整点秒杀：</div>';
						flashHtml += '<div class="dd">';
						flashHtml += '<strong class="p-price" style="font-weight:400;font-size:12px">';
						var stageType = productActVO.stageType;
						// 如果是正在进行
						if (stageType == 2) {
							flashHtml += actFlashSaleProduct.price + '元秒杀正在进行中&nbsp;&nbsp;';
							flashHtml += '<a href="javascript:;" class="J-open-tb receive" onclick="gotoFlashSale()"><span class="go-flash-sale">我要秒杀</span></a>';
						} else if (stageType == 3) {
							// 如果是即将开始
							flashHtml += actFlashSaleProduct.price + '元秒杀即将开始&nbsp;&nbsp;';
							flashHtml += '<a href="javascript:;" class="J-open-tb receive" onclick="gotoFlashSale()"><span class="go-flash-sale">去看看</span></a>';
						} else if (stageType == 1) {
							// 如果是已经结束
							flashHtml += actFlashSaleProduct.price + '元秒杀结束了~~~&nbsp;&nbsp;';
							flashHtml += '<a href="javascript:;" class="J-open-tb receive" onclick="gotoFlashSale()"><span class="go-flash-sale">去看看</span></a>';
						}
						flashHtml += '</strong>';
						$("#flashSaleInfoDiv").html(flashHtml);
					}
				}
			}
		});
	}
	
	// 领取优惠券
	function receiveCoupon(couponId) {
		//未登录不能领取
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
	 	$.ajax({
			type:"POST",
			url:domain+"/member/reveivecoupon.html",
			dataType:"json",
			data:{couponId:couponId},
			success:function(data){
				if (data.success) {
					jAlert("领取成功，您可在用户中心查看您的红包！");
				} else {
					jAlert(data.message);
				}
			},
			error:function(){
				jAlert("领取失败，请稍后再试！");
			}
		});
	}
	
	// 跳转到限时抢购页面
	function gotoFlashSale() {
		window.location.href=domain+"/product/${(productId)!0}.html?type=1";  
	}
	//进入商品详情页，默认滚动页面到指定位置，保证显示商品的相关重要信息
	$("html, body").animate({
            scrollTop: '130px'
        },1500);
        
	function baiduShare() {
		$("#shareProduct").click(function() {
			if ($(".jqzoom_share").is(":hidden")) 
				$(".jqzoom_share").css("display", "inline-block");
			else 
				$(".jqzoom_share").hide();
		});
	
		window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
	}
</script>
<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.ellipsis.min.js'></script>
<script type="text/javascript">
document.write('<img width="1" height="1" style="position:absolute;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product_look_log.html?memberId='+ memberId + '&productId='+ ${productId!0} + '" />');
</script>