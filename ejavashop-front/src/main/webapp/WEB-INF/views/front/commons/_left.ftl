
<div class='wrapper_side uc_nav mbot_20'>
				<dl id='left-nav'>
					<dt><img style="width: 15px;height: 15px;position: relative;top: -3px;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/resources/front/img/ico/icon_order.png"/>&nbsp;我的订单</dt>
					<#--
						<dd id='myorder' >
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html'>我的订单</a>
						</dd>
					-->
					<dd id='allOrders' >
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html'>全部订单</a>
					</dd>
					<dd id='notpay' >
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1'>待支付</a>
					</dd>
					<dd id='nonconfirm' >
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=2'>待确认</a>
					</dd>
					<dd id='nonsend' >
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=3'>待发货</a>
					</dd>
					<dd id='consend' >
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=4'>已发货</a>
					</dd>
					<dd id='concolsed' >
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=6'>已关闭</a>
					</dd>
					<dd id='myconsultation' style="display: none;">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/question.html'>我的咨询</a>
					</dd>
					<dd id='myevaluation' style="display: none;">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/comment.html'>我的评价</a>
					</dd>
					<dd id='myecouponuser'>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/coupon-use.html'>我的红包</a>
					</dd>
					<dt><img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/resources/front/img/ico/icon_collection.png" style="width: 15px;height: 15px;position: relative;top: -3px;"/>&nbsp;我的收藏</dt>
					<dd id="collectionGoods">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/collectproduct.html'>收藏的商品</a>
					</dd>
					<dd id="collectionShop" style="display: none;">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/collectshop.html'>收藏的店铺</a>
					</dd>
					<dd id="productLookLog">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/viewed.html'>我的浏览记录</a>
					</dd>
					<!--影藏 客户服务-->
					<#--
					<dt>客户服务</dt>
					<dd id="productback">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/back.html'>退货</a>
					</dd>
					<dd id="productexchange">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/exchange.html'>换货</a>
					</dd>
					<dd id="withdrawdeposit">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/withdraw.html'>提现申请</a>
					</dd>
					<dd id="balance">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/balance.html'>余额</a>
					</dd>
					<dd id="complainlist">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/complain.html'>申诉</a>
					</dd>
					-->
					<dt><img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/resources/front/img/ico/icon_data.png" style="width: 15px;height: 15px;position: relative;top: -3px;"/>&nbsp;资料管理</dt>
					<dd id="balance">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/balance.html'>余额</a>
					</dd>
					<dd id="personalfile">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/info.html'>我的个人资料</a>
					</dd>
					<dd id="reciptAddress">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/address.html' >我的收货地址</a>
					</dd>
					<dd id="editPassword">
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/editpassword.html'>修改密码</a>
					</dd>
					
					<#if Session.is_parter_sign ?? && Session.is_parter_sign =='true'>
						<dt><img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/resources/front/img/ico/icon_order.png" style="width: 15px;height: 15px;position: relative;top: -3px;"/>&nbsp;合伙人日志</dt>
						<dd id="panterTotal">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotal.html'>累计销售汇总</a>
						</dd>
						<dd id="panterOrdersMonth">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotalMonth.html'>月度汇总</a>
						</dd>
						<dd id="panerTotalYears">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotalYears.html'>年度汇总</a>
						</dd>
						<dd id="panerTotalDays">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotalDays.html'>每日销售汇总</a>
						</dd>
						<dd id="panerTotalPerson">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotalPerson.html'>客户下单汇总</a>
						</dd>
						<dd id="categorySales">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/categorySales.html'>分类销售汇总</a>
						</dd>
						<dd id="getSalesDetails">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/getSalesDetails.html'>销售明细表</a>
						</dd>
						<dd id="parterSalesSum">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/parterSalesSum.html'>合伙人提点统计</a>
						</dd>
						<dd id="parterTuijian">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/parterTuijian.html'>合伙人推荐合伙人</a>
						</dd>
					</#if>
					
				</dl>
			</div>
