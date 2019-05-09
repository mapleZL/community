<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<link href="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/lang/zh-cn/zh-cn.js"></script>


<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">查看团购<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/actbidding">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>商品名称：</label>
							${(actBidding.productName)!}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>所属商家：</label>
							${(actBidding.sellerName)!}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>促销标题：</label>
							${(actBidding.name)!}
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>分类名称：</label>
							${(actBidding.typeName)!}
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>虚拟销量：</label>
							${(actBidding.virtualSaleNum)!}
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>实际销量：</label>
							${(actBidding.virtualSaleNum)!}
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>限购数量：</label>
							${(actBidding.purchase)!}
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>活动图片：</label>
							<#if (actBidding.image)??> 
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actBidding.image)!''}" target="_blank">查看图片</a>
							</#if>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>市场价: </label>
                            ${(actBidding.marketPrice)!''}
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>初始价格: </label>
                            ${(actBidding.price)!''}
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>首付款项: </label>
                            ${(actBidding.firstPrice)!''}
                        </p>
                    </div>
                    <br/>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
	                    	<label class="lab-item">阶梯价格：</label>
                        </p>
                    </div>
                    <div class="fluidbox">
                    	<#if actBiddingPrices??>
	                    	<#list actBiddingPrices as actBiddingPrice>
	                    		<p class='p12 p-item'>
		 						<label class='lab-item'>&nbsp;</label>
	 							销量：${(actBiddingPrice.saleNum)!} &nbsp;&nbsp;&nbsp;&nbsp;
	 							价格：${(actBiddingPrice.price)!}
								</p>	
							</#list>
						</#if>
                    </div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>库存：</label>
							${(actBidding.stock)!''}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red"></font>活动时间：</label>
						${(actBidding.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}
						~
						${(actBidding.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>首付款截止时间：</label>
							${(actBidding.firstEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>尾款款截止时间：</label>
							${(actBidding.lastEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>活动描述: </label>
                        <div style="padding-left: 140px;padding-top: 2px;">
                        	<#noescape>
                        		${(actBidding.descinfo)!}
                        	</#noescape>
                        </div>
                        </p>
                    </div>
					<br/>
				</dd>
			</dl>

		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />