package com.phkj.entity.order;

import java.io.Serializable;
/**
 * 网单表
 * <p>Table: <strong>st_applet_orders_product</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>ordersId</td><td>{@link Integer}</td><td>orders_id</td><td>int</td><td>订单ID</td></tr>
 *   <tr><td>ordersSn</td><td>{@link String}</td><td>orders_sn</td><td>varchar</td><td>订单号</td></tr>
 *   <tr><td>sellerId</td><td>{@link Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>productCateId</td><td>{@link Integer}</td><td>product_cate_id</td><td>int</td><td>商品分类ID</td></tr>
 *   <tr><td>productId</td><td>{@link Integer}</td><td>product_id</td><td>int</td><td>商品id</td></tr>
 *   <tr><td>productGoodsId</td><td>{@link Integer}</td><td>product_goods_id</td><td>int</td><td>货品ID</td></tr>
 *   <tr><td>specInfo</td><td>{@link String}</td><td>spec_info</td><td>varchar</td><td>规格详情</td></tr>
 *   <tr><td>productName</td><td>{@link String}</td><td>product_name</td><td>varchar</td><td>商品名称</td></tr>
 *   <tr><td>productSku</td><td>{@link String}</td><td>product_sku</td><td>varchar</td><td>抽象商品sku</td></tr>
 *   <tr><td>packageGroupsId</td><td>{@link Integer}</td><td>package_groups_id</td><td>int</td><td>促销套装0、不是促销套装；大于0，促销套装ID，价格是促销套装的ID</td></tr>
 *   <tr><td>mallGroupsId</td><td>{@link Integer}</td><td>mall_groups_id</td><td>int</td><td>商城套装0，不是上次套装；大于0商城套装ID</td></tr>
 *   <tr><td>giftId</td><td>{@link Integer}</td><td>gift_id</td><td>int</td><td>赠品ID 0:不是赠品；大于0：是赠品，存赠品的ID</td></tr>
 *   <tr><td>isGift</td><td>{@link Integer}</td><td>is_gift</td><td>tinyint</td><td>是否是赠品，0、不是；1、是；</td></tr>
 *   <tr><td>moneyPrice</td><td>{@link java.math.BigDecimal}</td><td>money_price</td><td>decimal</td><td>商品单价</td></tr>
 *   <tr><td>number</td><td>{@link Integer}</td><td>number</td><td>int</td><td>商品数量</td></tr>
 *   <tr><td>moneyAmount</td><td>{@link java.math.BigDecimal}</td><td>money_amount</td><td>decimal</td><td>网单金额（减去立减优惠后的金额和）</td></tr>
 *   <tr><td>moneyActSingle</td><td>{@link java.math.BigDecimal}</td><td>money_act_single</td><td>decimal</td><td>立减优惠金额和</td></tr>
 *   <tr><td>actSingleId</td><td>{@link Integer}</td><td>act_single_id</td><td>int</td><td>单品立减活动ID，无设置0</td></tr>
 *   <tr><td>activityId</td><td>{@link Integer}</td><td>activity_id</td><td>int</td><td>团购ID，为0正常购买</td></tr>
 *   <tr><td>actFlashSaleId</td><td>{@link Integer}</td><td>act_flash_sale_id</td><td>int</td><td>限时抢购ID，为0正常购买</td></tr>
 *   <tr><td>actFlashSaleProductId</td><td>{@link Integer}</td><td>act_flash_sale_product_id</td><td>int</td><td>限时抢购活动商品ID</td></tr>
 *   <tr><td>actGroupId</td><td>{@link Integer}</td><td>act_group_id</td><td>int</td><td>团购ID，为0正常购买</td></tr>
 *   <tr><td>actBiddingId</td><td>{@link Integer}</td><td>act_bidding_id</td><td>int</td><td>集合竞价ID，为0正常购买</td></tr>
 *   <tr><td>logisticsId</td><td>{@link Integer}</td><td>logistics_id</td><td>int</td><td>物流</td></tr>
 *   <tr><td>logisticsName</td><td>{@link String}</td><td>logistics_name</td><td>varchar</td><td>物流name</td></tr>
 *   <tr><td>logisticsNumber</td><td>{@link String}</td><td>logistics_number</td><td>varchar</td><td>发票快递单号</td></tr>
 *   <tr><td>shippingTime</td><td>{@link java.util.Date}</td><td>shipping_time</td><td>datetime</td><td>发货时间</td></tr>
 *   <tr><td>closeTime</td><td>{@link java.util.Date}</td><td>close_time</td><td>datetime</td><td>网单完成关闭或取消关闭时间</td></tr>
 *   <tr><td>systemRemark</td><td>{@link String}</td><td>system_remark</td><td>text</td><td>系统备注，不给用户显示</td></tr>
 *   <tr><td>memberProductBackId</td><td>{@link Integer}</td><td>member_product_back_id</td><td>int</td><td>退货ID，默认为0</td></tr>
 *   <tr><td>memberProductExchangeId</td><td>{@link Integer}</td><td>member_product_exchange_id</td><td>int</td><td>换货ID，默认为0</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>packageFeeTotal</td><td>{@link java.math.BigDecimal}</td><td>package_fee_total</td><td>decimal</td><td>packageFeeTotal</td></tr>
 *   <tr><td>packageFee</td><td>{@link java.math.BigDecimal}</td><td>package_fee</td><td>decimal</td><td>套餐价格</td></tr>
 *   <tr><td>productPackageId</td><td>{@link Integer}</td><td>product_package_id</td><td>int</td><td>套餐id</td></tr>
 *   <tr><td>productLabelId</td><td>{@link Integer}</td><td>product_label_id</td><td>int</td><td>标签id</td></tr>
 *   <tr><td>labelFeeTotal</td><td>{@link java.math.BigDecimal}</td><td>label_fee_total</td><td>decimal</td><td>labelFeeTotal</td></tr>
 *   <tr><td>labelFee</td><td>{@link java.math.BigDecimal}</td><td>label_fee</td><td>decimal</td><td>标签价格</td></tr>
 *   <tr><td>isSelfLabel</td><td>{@link Integer}</td><td>is_self_label</td><td>int</td><td>是否自供标 1-是 0-否</td></tr>
 *   <tr><td>deliveredNum</td><td>{@link Integer}</td><td>delivered_num</td><td>int</td><td>已发货数量</td></tr>
 *   <tr><td>labelNumber</td><td>{@link Integer}</td><td>label_number</td><td>int</td><td>标签数量</td></tr>
 * </table>
 *
 */
public class StAppletOrdersProduct implements Serializable {
 
 	private Integer id;
 	private Integer ordersId;
 	private String ordersSn;
 	private Integer sellerId;
 	private Integer productCateId;
 	private Integer productId;
 	private Integer productGoodsId;
 	private String specInfo;
 	private String productName;
 	private String productSku;
 	private Integer packageGroupsId;
 	private Integer mallGroupsId;
 	private Integer giftId;
 	private Integer isGift;
 	private java.math.BigDecimal moneyPrice;
 	private Integer number;
 	private java.math.BigDecimal moneyAmount;
 	private java.math.BigDecimal moneyActSingle;
 	private Integer actSingleId;
 	private Integer activityId;
 	private Integer actFlashSaleId;
 	private Integer actFlashSaleProductId;
 	private Integer actGroupId;
 	private Integer actBiddingId;
 	private Integer logisticsId;
 	private String logisticsName;
 	private String logisticsNumber;
 	private java.util.Date shippingTime;
 	private java.util.Date closeTime;
 	private String systemRemark;
 	private Integer memberProductBackId;
 	private Integer memberProductExchangeId;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.math.BigDecimal packageFeeTotal;
 	private java.math.BigDecimal packageFee;
 	private Integer productPackageId;
 	private Integer productLabelId;
 	private java.math.BigDecimal labelFeeTotal;
 	private java.math.BigDecimal labelFee;
 	private Integer isSelfLabel;
 	private Integer deliveredNum;
 	private Integer labelNumber;
 	
 		
	/**
     * 获取id
     */
	public Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(Integer id){
		this.id = id;
	}
 		
	/**
     * 获取订单ID
     */
	public Integer getOrdersId(){
		return this.ordersId;
	}
 		
	/**
     * 设置订单ID
     */
	public void setOrdersId(Integer ordersId){
		this.ordersId = ordersId;
	}
 		
	/**
     * 获取订单号
     */
	public String getOrdersSn(){
		return this.ordersSn;
	}
 		
	/**
     * 设置订单号
     */
	public void setOrdersSn(String ordersSn){
		this.ordersSn = ordersSn;
	}
 		
	/**
     * 获取商家ID
     */
	public Integer getSellerId(){
		return this.sellerId;
	}
 		
	/**
     * 设置商家ID
     */
	public void setSellerId(Integer sellerId){
		this.sellerId = sellerId;
	}
 		
	/**
     * 获取商品分类ID
     */
	public Integer getProductCateId(){
		return this.productCateId;
	}
 		
	/**
     * 设置商品分类ID
     */
	public void setProductCateId(Integer productCateId){
		this.productCateId = productCateId;
	}
 		
	/**
     * 获取商品id
     */
	public Integer getProductId(){
		return this.productId;
	}
 		
	/**
     * 设置商品id
     */
	public void setProductId(Integer productId){
		this.productId = productId;
	}
 		
	/**
     * 获取货品ID
     */
	public Integer getProductGoodsId(){
		return this.productGoodsId;
	}
 		
	/**
     * 设置货品ID
     */
	public void setProductGoodsId(Integer productGoodsId){
		this.productGoodsId = productGoodsId;
	}
 		
	/**
     * 获取规格详情
     */
	public String getSpecInfo(){
		return this.specInfo;
	}
 		
	/**
     * 设置规格详情
     */
	public void setSpecInfo(String specInfo){
		this.specInfo = specInfo;
	}
 		
	/**
     * 获取商品名称
     */
	public String getProductName(){
		return this.productName;
	}
 		
	/**
     * 设置商品名称
     */
	public void setProductName(String productName){
		this.productName = productName;
	}
 		
	/**
     * 获取抽象商品sku
     */
	public String getProductSku(){
		return this.productSku;
	}
 		
	/**
     * 设置抽象商品sku
     */
	public void setProductSku(String productSku){
		this.productSku = productSku;
	}
 		
	/**
     * 获取促销套装0、不是促销套装；大于0，促销套装ID，价格是促销套装的ID
     */
	public Integer getPackageGroupsId(){
		return this.packageGroupsId;
	}
 		
	/**
     * 设置促销套装0、不是促销套装；大于0，促销套装ID，价格是促销套装的ID
     */
	public void setPackageGroupsId(Integer packageGroupsId){
		this.packageGroupsId = packageGroupsId;
	}
 		
	/**
     * 获取商城套装0，不是上次套装；大于0商城套装ID
     */
	public Integer getMallGroupsId(){
		return this.mallGroupsId;
	}
 		
	/**
     * 设置商城套装0，不是上次套装；大于0商城套装ID
     */
	public void setMallGroupsId(Integer mallGroupsId){
		this.mallGroupsId = mallGroupsId;
	}
 		
	/**
     * 获取赠品ID 0:不是赠品；大于0：是赠品，存赠品的ID
     */
	public Integer getGiftId(){
		return this.giftId;
	}
 		
	/**
     * 设置赠品ID 0:不是赠品；大于0：是赠品，存赠品的ID
     */
	public void setGiftId(Integer giftId){
		this.giftId = giftId;
	}
 		
	/**
     * 获取是否是赠品，0、不是；1、是；
     */
	public Integer getIsGift(){
		return this.isGift;
	}
 		
	/**
     * 设置是否是赠品，0、不是；1、是；
     */
	public void setIsGift(Integer isGift){
		this.isGift = isGift;
	}
 		
	/**
     * 获取商品单价
     */
	public java.math.BigDecimal getMoneyPrice(){
		return this.moneyPrice;
	}
 		
	/**
     * 设置商品单价
     */
	public void setMoneyPrice(java.math.BigDecimal moneyPrice){
		this.moneyPrice = moneyPrice;
	}
 		
	/**
     * 获取商品数量
     */
	public Integer getNumber(){
		return this.number;
	}
 		
	/**
     * 设置商品数量
     */
	public void setNumber(Integer number){
		this.number = number;
	}
 		
	/**
     * 获取网单金额（减去立减优惠后的金额和）
     */
	public java.math.BigDecimal getMoneyAmount(){
		return this.moneyAmount;
	}
 		
	/**
     * 设置网单金额（减去立减优惠后的金额和）
     */
	public void setMoneyAmount(java.math.BigDecimal moneyAmount){
		this.moneyAmount = moneyAmount;
	}
 		
	/**
     * 获取立减优惠金额和
     */
	public java.math.BigDecimal getMoneyActSingle(){
		return this.moneyActSingle;
	}
 		
	/**
     * 设置立减优惠金额和
     */
	public void setMoneyActSingle(java.math.BigDecimal moneyActSingle){
		this.moneyActSingle = moneyActSingle;
	}
 		
	/**
     * 获取单品立减活动ID，无设置0
     */
	public Integer getActSingleId(){
		return this.actSingleId;
	}
 		
	/**
     * 设置单品立减活动ID，无设置0
     */
	public void setActSingleId(Integer actSingleId){
		this.actSingleId = actSingleId;
	}
 		
	/**
     * 获取团购ID，为0正常购买
     */
	public Integer getActivityId(){
		return this.activityId;
	}
 		
	/**
     * 设置团购ID，为0正常购买
     */
	public void setActivityId(Integer activityId){
		this.activityId = activityId;
	}
 		
	/**
     * 获取限时抢购ID，为0正常购买
     */
	public Integer getActFlashSaleId(){
		return this.actFlashSaleId;
	}
 		
	/**
     * 设置限时抢购ID，为0正常购买
     */
	public void setActFlashSaleId(Integer actFlashSaleId){
		this.actFlashSaleId = actFlashSaleId;
	}
 		
	/**
     * 获取限时抢购活动商品ID
     */
	public Integer getActFlashSaleProductId(){
		return this.actFlashSaleProductId;
	}
 		
	/**
     * 设置限时抢购活动商品ID
     */
	public void setActFlashSaleProductId(Integer actFlashSaleProductId){
		this.actFlashSaleProductId = actFlashSaleProductId;
	}
 		
	/**
     * 获取团购ID，为0正常购买
     */
	public Integer getActGroupId(){
		return this.actGroupId;
	}
 		
	/**
     * 设置团购ID，为0正常购买
     */
	public void setActGroupId(Integer actGroupId){
		this.actGroupId = actGroupId;
	}
 		
	/**
     * 获取集合竞价ID，为0正常购买
     */
	public Integer getActBiddingId(){
		return this.actBiddingId;
	}
 		
	/**
     * 设置集合竞价ID，为0正常购买
     */
	public void setActBiddingId(Integer actBiddingId){
		this.actBiddingId = actBiddingId;
	}
 		
	/**
     * 获取物流
     */
	public Integer getLogisticsId(){
		return this.logisticsId;
	}
 		
	/**
     * 设置物流
     */
	public void setLogisticsId(Integer logisticsId){
		this.logisticsId = logisticsId;
	}
 		
	/**
     * 获取物流name
     */
	public String getLogisticsName(){
		return this.logisticsName;
	}
 		
	/**
     * 设置物流name
     */
	public void setLogisticsName(String logisticsName){
		this.logisticsName = logisticsName;
	}
 		
	/**
     * 获取发票快递单号
     */
	public String getLogisticsNumber(){
		return this.logisticsNumber;
	}
 		
	/**
     * 设置发票快递单号
     */
	public void setLogisticsNumber(String logisticsNumber){
		this.logisticsNumber = logisticsNumber;
	}
 		
	/**
     * 获取发货时间
     */
	public java.util.Date getShippingTime(){
		return this.shippingTime;
	}
 		
	/**
     * 设置发货时间
     */
	public void setShippingTime(java.util.Date shippingTime){
		this.shippingTime = shippingTime;
	}
 		
	/**
     * 获取网单完成关闭或取消关闭时间
     */
	public java.util.Date getCloseTime(){
		return this.closeTime;
	}
 		
	/**
     * 设置网单完成关闭或取消关闭时间
     */
	public void setCloseTime(java.util.Date closeTime){
		this.closeTime = closeTime;
	}
 		
	/**
     * 获取系统备注，不给用户显示
     */
	public String getSystemRemark(){
		return this.systemRemark;
	}
 		
	/**
     * 设置系统备注，不给用户显示
     */
	public void setSystemRemark(String systemRemark){
		this.systemRemark = systemRemark;
	}
 		
	/**
     * 获取退货ID，默认为0
     */
	public Integer getMemberProductBackId(){
		return this.memberProductBackId;
	}
 		
	/**
     * 设置退货ID，默认为0
     */
	public void setMemberProductBackId(Integer memberProductBackId){
		this.memberProductBackId = memberProductBackId;
	}
 		
	/**
     * 获取换货ID，默认为0
     */
	public Integer getMemberProductExchangeId(){
		return this.memberProductExchangeId;
	}
 		
	/**
     * 设置换货ID，默认为0
     */
	public void setMemberProductExchangeId(Integer memberProductExchangeId){
		this.memberProductExchangeId = memberProductExchangeId;
	}
 		
	/**
     * 获取createTime
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置createTime
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取updateTime
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置updateTime
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 		
	/**
     * 获取packageFeeTotal
     */
	public java.math.BigDecimal getPackageFeeTotal(){
		return this.packageFeeTotal;
	}
 		
	/**
     * 设置packageFeeTotal
     */
	public void setPackageFeeTotal(java.math.BigDecimal packageFeeTotal){
		this.packageFeeTotal = packageFeeTotal;
	}
 		
	/**
     * 获取套餐价格
     */
	public java.math.BigDecimal getPackageFee(){
		return this.packageFee;
	}
 		
	/**
     * 设置套餐价格
     */
	public void setPackageFee(java.math.BigDecimal packageFee){
		this.packageFee = packageFee;
	}
 		
	/**
     * 获取套餐id
     */
	public Integer getProductPackageId(){
		return this.productPackageId;
	}
 		
	/**
     * 设置套餐id
     */
	public void setProductPackageId(Integer productPackageId){
		this.productPackageId = productPackageId;
	}
 		
	/**
     * 获取标签id
     */
	public Integer getProductLabelId(){
		return this.productLabelId;
	}
 		
	/**
     * 设置标签id
     */
	public void setProductLabelId(Integer productLabelId){
		this.productLabelId = productLabelId;
	}
 		
	/**
     * 获取labelFeeTotal
     */
	public java.math.BigDecimal getLabelFeeTotal(){
		return this.labelFeeTotal;
	}
 		
	/**
     * 设置labelFeeTotal
     */
	public void setLabelFeeTotal(java.math.BigDecimal labelFeeTotal){
		this.labelFeeTotal = labelFeeTotal;
	}
 		
	/**
     * 获取标签价格
     */
	public java.math.BigDecimal getLabelFee(){
		return this.labelFee;
	}
 		
	/**
     * 设置标签价格
     */
	public void setLabelFee(java.math.BigDecimal labelFee){
		this.labelFee = labelFee;
	}
 		
	/**
     * 获取是否自供标 1-是 0-否
     */
	public Integer getIsSelfLabel(){
		return this.isSelfLabel;
	}
 		
	/**
     * 设置是否自供标 1-是 0-否
     */
	public void setIsSelfLabel(Integer isSelfLabel){
		this.isSelfLabel = isSelfLabel;
	}
 		
	/**
     * 获取已发货数量
     */
	public Integer getDeliveredNum(){
		return this.deliveredNum;
	}
 		
	/**
     * 设置已发货数量
     */
	public void setDeliveredNum(Integer deliveredNum){
		this.deliveredNum = deliveredNum;
	}
 		
	/**
     * 获取标签数量
     */
	public Integer getLabelNumber(){
		return this.labelNumber;
	}
 		
	/**
     * 设置标签数量
     */
	public void setLabelNumber(Integer labelNumber){
		this.labelNumber = labelNumber;
	}
 }