package com.phkj.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrdersAndOrdersProductVO implements Serializable {

	  /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -2300947204355677927L;
    
    private java.lang.Integer    id;
    private java.lang.Integer    ordersId;
    private java.lang.String     ordersSn;
    private java.lang.Integer    sellerId;
    private java.lang.Integer    productCateId;
    private java.lang.Integer    productId;
    private java.lang.Integer    productGoodsId;                          //货品ID
    private java.lang.String     specInfo;                                //规格详情
    private java.lang.String     productName;
    private java.lang.String     productSku;
    private java.lang.Integer    packageGroupsId;
    private java.lang.Integer    mallGroupsId;
    private java.lang.Integer    giftId;
    private java.lang.Integer    isGift;
    private java.math.BigDecimal moneyPrice;
    private java.lang.Integer    number;
    private java.math.BigDecimal moneyAmount;
    private java.math.BigDecimal moneyActSingle;
    private java.lang.Integer    actSingleId;
    private java.lang.Integer    activityId;
    private java.lang.Integer    actFlashSaleId;
    private java.lang.Integer    actFlashSaleProductId;
    private java.lang.Integer    actGroupId;
    private java.lang.Integer    actBiddingId;
    private java.util.Date       shippingTime;
    private java.util.Date       closeTime;
    private java.lang.String     systemRemark;
    private java.lang.Integer    memberProductBackId;
    private java.lang.Integer    memberProductExchangeId;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;
    private java.lang.Integer    productPackageId;                        //套餐id
    private java.math.BigDecimal packageFee;                              //套餐费
    private java.lang.Integer    productLabelId;                          //标签id
    private BigDecimal           labelFee;                                //标签价格
    private java.lang.Integer    isSelfLabel;                             //是否自供标 1-是 0-否
    private java.math.BigDecimal packageFeeTotal;                         //packageFeeTotal
    private java.math.BigDecimal labelFeeTotal;                           //labelFeeTotal
    private java.lang.Integer labelNumber;//标签数量

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String               productLeadPicpath;                      //主图大图
    private String               productLeadMiddle;                       //主图中图
    private String               productLeadLittle;                       //主图小图

    private BigDecimal           protectedPrice;                          //商品保护价

    private String               labelName;                               //标签名称
    private String               packageName;                             //套餐名称
    private String               packingType;                             //包装方式
    private String               unitType;                                //包装单位
    private String               packOther;                               //辅料需求

    private String               packprice;                               //当前套餐价
    private String               labelprice;                              //当前标签价

    private String               packDescribe;                            //套餐描述

    private Integer              deliveredNum     = 0;

    // --------额外属性（entity对应表结构之外的属性） end--------------------------------
    
    private String               productUrl;                                // 商品的百度云盘地址

    private java.lang.String     orderSn;
    private java.lang.String     relationOrderSn;
    private java.lang.Integer    orderType;
    private java.lang.Integer    memberId;
    private java.lang.String     memberName;
    private java.lang.Integer    orderState;
    private java.util.Date       payTime;
    private java.lang.Integer    paymentStatus;
    private java.lang.Integer    invoiceStatus;
    private java.lang.String     invoiceTitle;
    private java.lang.String     invoiceType;
    private java.math.BigDecimal moneyProduct;
    private java.math.BigDecimal moneyLogistics;
    private java.math.BigDecimal moneyOrder;
    private java.math.BigDecimal moneyPaidBalance;
    private java.math.BigDecimal moneyPaidReality;
    private java.math.BigDecimal moneyCoupon;
    private java.math.BigDecimal moneyActFull;
    private java.math.BigDecimal moneyDiscount;
    private java.math.BigDecimal moneyBack;
    private java.math.BigDecimal moneyIntegral;
    private java.lang.Integer    integral;
    private java.lang.Integer    couponUserId;
    private java.lang.Integer    actFullId;
    private java.lang.String     ip;
    private java.lang.String     paymentName;
    private java.lang.String     paymentCode;
    private java.lang.String     name;
    private java.lang.Integer    provinceId;
    private java.lang.Integer    cityId;
    private java.lang.Integer    areaId;
    private java.lang.String     addressAll;
    private java.lang.String     addressInfo;
    private java.lang.String     mobile;
    private java.lang.String     email;
    private java.lang.String     zipCode;
    private java.lang.String     remark;
    private java.util.Date       deliverTime;
    private java.util.Date       finishTime;
    private java.lang.String     tradeSn;
    private java.lang.Integer    source;
    private java.lang.Integer    logisticsId;
    private java.lang.String     logisticsName;
    private java.lang.String     logisticsNumber;
    private java.lang.Integer    isCodconfim;
    private java.lang.Integer    codconfirmId;
    private java.lang.String     codconfirmName;
    private java.util.Date       codconfirmTime;
    private java.lang.String     codconfirmRemark;
    private java.lang.Integer    codconfirmState;

    private java.lang.Integer    deliveredSum         = 0;
    private java.math.BigDecimal servicePrice;                              //服务费，所有网单的套餐费用之和
    private BigDecimal           labelPrice;                                //标签费
    private String               tradeNo;                                    //交易订单号
    private String				syncState = "未推送";                                   //推送状态

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String               sellerName;                                // 商家名称

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(java.lang.Integer ordersId) {
		this.ordersId = ordersId;
	}

	public java.lang.String getOrdersSn() {
		return ordersSn;
	}

	public void setOrdersSn(java.lang.String ordersSn) {
		this.ordersSn = ordersSn;
	}

	public java.lang.Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(java.lang.Integer sellerId) {
		this.sellerId = sellerId;
	}

	public java.lang.Integer getProductCateId() {
		return productCateId;
	}

	public void setProductCateId(java.lang.Integer productCateId) {
		this.productCateId = productCateId;
	}

	public java.lang.Integer getProductId() {
		return productId;
	}

	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}

	public java.lang.Integer getProductGoodsId() {
		return productGoodsId;
	}

	public void setProductGoodsId(java.lang.Integer productGoodsId) {
		this.productGoodsId = productGoodsId;
	}

	public java.lang.String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(java.lang.String specInfo) {
		this.specInfo = specInfo;
	}

	public java.lang.String getProductName() {
		return productName;
	}

	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}

	public java.lang.String getProductSku() {
		return productSku;
	}

	public void setProductSku(java.lang.String productSku) {
		this.productSku = productSku;
	}

	public java.lang.Integer getPackageGroupsId() {
		return packageGroupsId;
	}

	public void setPackageGroupsId(java.lang.Integer packageGroupsId) {
		this.packageGroupsId = packageGroupsId;
	}

	public java.lang.Integer getMallGroupsId() {
		return mallGroupsId;
	}

	public void setMallGroupsId(java.lang.Integer mallGroupsId) {
		this.mallGroupsId = mallGroupsId;
	}

	public java.lang.Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(java.lang.Integer giftId) {
		this.giftId = giftId;
	}

	public java.lang.Integer getIsGift() {
		return isGift;
	}

	public void setIsGift(java.lang.Integer isGift) {
		this.isGift = isGift;
	}

	public java.math.BigDecimal getMoneyPrice() {
		return moneyPrice;
	}

	public void setMoneyPrice(java.math.BigDecimal moneyPrice) {
		this.moneyPrice = moneyPrice;
	}

	public java.lang.Integer getNumber() {
		return number;
	}

	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}

	public java.math.BigDecimal getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(java.math.BigDecimal moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public java.math.BigDecimal getMoneyActSingle() {
		return moneyActSingle;
	}

	public void setMoneyActSingle(java.math.BigDecimal moneyActSingle) {
		this.moneyActSingle = moneyActSingle;
	}

	public java.lang.Integer getActSingleId() {
		return actSingleId;
	}

	public void setActSingleId(java.lang.Integer actSingleId) {
		this.actSingleId = actSingleId;
	}

	public java.lang.Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(java.lang.Integer activityId) {
		this.activityId = activityId;
	}

	public java.lang.Integer getActFlashSaleId() {
		return actFlashSaleId;
	}

	public void setActFlashSaleId(java.lang.Integer actFlashSaleId) {
		this.actFlashSaleId = actFlashSaleId;
	}

	public java.lang.Integer getActFlashSaleProductId() {
		return actFlashSaleProductId;
	}

	public void setActFlashSaleProductId(java.lang.Integer actFlashSaleProductId) {
		this.actFlashSaleProductId = actFlashSaleProductId;
	}

	public java.lang.Integer getActGroupId() {
		return actGroupId;
	}

	public void setActGroupId(java.lang.Integer actGroupId) {
		this.actGroupId = actGroupId;
	}

	public java.lang.Integer getActBiddingId() {
		return actBiddingId;
	}

	public void setActBiddingId(java.lang.Integer actBiddingId) {
		this.actBiddingId = actBiddingId;
	}

	public java.util.Date getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(java.util.Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	public java.util.Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(java.util.Date closeTime) {
		this.closeTime = closeTime;
	}

	public java.lang.String getSystemRemark() {
		return systemRemark;
	}

	public void setSystemRemark(java.lang.String systemRemark) {
		this.systemRemark = systemRemark;
	}

	public java.lang.Integer getMemberProductBackId() {
		return memberProductBackId;
	}

	public void setMemberProductBackId(java.lang.Integer memberProductBackId) {
		this.memberProductBackId = memberProductBackId;
	}

	public java.lang.Integer getMemberProductExchangeId() {
		return memberProductExchangeId;
	}

	public void setMemberProductExchangeId(java.lang.Integer memberProductExchangeId) {
		this.memberProductExchangeId = memberProductExchangeId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.Integer getProductPackageId() {
		return productPackageId;
	}

	public void setProductPackageId(java.lang.Integer productPackageId) {
		this.productPackageId = productPackageId;
	}

	public java.math.BigDecimal getPackageFee() {
		return packageFee;
	}

	public void setPackageFee(java.math.BigDecimal packageFee) {
		this.packageFee = packageFee;
	}

	public java.lang.Integer getProductLabelId() {
		return productLabelId;
	}

	public void setProductLabelId(java.lang.Integer productLabelId) {
		this.productLabelId = productLabelId;
	}

	public BigDecimal getLabelFee() {
		return labelFee;
	}

	public void setLabelFee(BigDecimal labelFee) {
		this.labelFee = labelFee;
	}

	public java.lang.Integer getIsSelfLabel() {
		return isSelfLabel;
	}

	public void setIsSelfLabel(java.lang.Integer isSelfLabel) {
		this.isSelfLabel = isSelfLabel;
	}

	public java.math.BigDecimal getPackageFeeTotal() {
		return packageFeeTotal;
	}

	public void setPackageFeeTotal(java.math.BigDecimal packageFeeTotal) {
		this.packageFeeTotal = packageFeeTotal;
	}

	public java.math.BigDecimal getLabelFeeTotal() {
		return labelFeeTotal;
	}

	public void setLabelFeeTotal(java.math.BigDecimal labelFeeTotal) {
		this.labelFeeTotal = labelFeeTotal;
	}

	public java.lang.Integer getLabelNumber() {
		return labelNumber;
	}

	public void setLabelNumber(java.lang.Integer labelNumber) {
		this.labelNumber = labelNumber;
	}

	public String getProductLeadPicpath() {
		return productLeadPicpath;
	}

	public void setProductLeadPicpath(String productLeadPicpath) {
		this.productLeadPicpath = productLeadPicpath;
	}

	public String getProductLeadMiddle() {
		return productLeadMiddle;
	}

	public void setProductLeadMiddle(String productLeadMiddle) {
		this.productLeadMiddle = productLeadMiddle;
	}

	public String getProductLeadLittle() {
		return productLeadLittle;
	}

	public void setProductLeadLittle(String productLeadLittle) {
		this.productLeadLittle = productLeadLittle;
	}

	public BigDecimal getProtectedPrice() {
		return protectedPrice;
	}

	public void setProtectedPrice(BigDecimal protectedPrice) {
		this.protectedPrice = protectedPrice;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackingType() {
		return packingType;
	}

	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getPackOther() {
		return packOther;
	}

	public void setPackOther(String packOther) {
		this.packOther = packOther;
	}

	public String getPackprice() {
		return packprice;
	}

	public void setPackprice(String packprice) {
		this.packprice = packprice;
	}

	public String getLabelprice() {
		return labelprice;
	}

	public void setLabelprice(String labelprice) {
		this.labelprice = labelprice;
	}

	public String getPackDescribe() {
		return packDescribe;
	}

	public void setPackDescribe(String packDescribe) {
		this.packDescribe = packDescribe;
	}

	public Integer getDeliveredNum() {
		return deliveredNum;
	}

	public void setDeliveredNum(Integer deliveredNum) {
		this.deliveredNum = deliveredNum;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public java.lang.String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(java.lang.String orderSn) {
		this.orderSn = orderSn;
	}

	public java.lang.String getRelationOrderSn() {
		return relationOrderSn;
	}

	public void setRelationOrderSn(java.lang.String relationOrderSn) {
		this.relationOrderSn = relationOrderSn;
	}

	public java.lang.Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(java.lang.Integer orderType) {
		this.orderType = orderType;
	}

	public java.lang.Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(java.lang.Integer memberId) {
		this.memberId = memberId;
	}

	public java.lang.String getMemberName() {
		return memberName;
	}

	public void setMemberName(java.lang.String memberName) {
		this.memberName = memberName;
	}

	public java.lang.Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(java.lang.Integer orderState) {
		this.orderState = orderState;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	public java.lang.Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(java.lang.Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public java.lang.Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(java.lang.Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public java.lang.String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(java.lang.String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public java.lang.String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(java.lang.String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public java.math.BigDecimal getMoneyProduct() {
		return moneyProduct;
	}

	public void setMoneyProduct(java.math.BigDecimal moneyProduct) {
		this.moneyProduct = moneyProduct;
	}

	public java.math.BigDecimal getMoneyLogistics() {
		return moneyLogistics;
	}

	public void setMoneyLogistics(java.math.BigDecimal moneyLogistics) {
		this.moneyLogistics = moneyLogistics;
	}

	public java.math.BigDecimal getMoneyOrder() {
		return moneyOrder;
	}

	public void setMoneyOrder(java.math.BigDecimal moneyOrder) {
		this.moneyOrder = moneyOrder;
	}

	public java.math.BigDecimal getMoneyPaidBalance() {
		return moneyPaidBalance;
	}

	public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance) {
		this.moneyPaidBalance = moneyPaidBalance;
	}

	public java.math.BigDecimal getMoneyPaidReality() {
		return moneyPaidReality;
	}

	public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality) {
		this.moneyPaidReality = moneyPaidReality;
	}

	public java.math.BigDecimal getMoneyCoupon() {
		return moneyCoupon;
	}

	public void setMoneyCoupon(java.math.BigDecimal moneyCoupon) {
		this.moneyCoupon = moneyCoupon;
	}

	public java.math.BigDecimal getMoneyActFull() {
		return moneyActFull;
	}

	public void setMoneyActFull(java.math.BigDecimal moneyActFull) {
		this.moneyActFull = moneyActFull;
	}

	public java.math.BigDecimal getMoneyDiscount() {
		return moneyDiscount;
	}

	public void setMoneyDiscount(java.math.BigDecimal moneyDiscount) {
		this.moneyDiscount = moneyDiscount;
	}

	public java.math.BigDecimal getMoneyBack() {
		return moneyBack;
	}

	public void setMoneyBack(java.math.BigDecimal moneyBack) {
		this.moneyBack = moneyBack;
	}

	public java.math.BigDecimal getMoneyIntegral() {
		return moneyIntegral;
	}

	public void setMoneyIntegral(java.math.BigDecimal moneyIntegral) {
		this.moneyIntegral = moneyIntegral;
	}

	public java.lang.Integer getIntegral() {
		return integral;
	}

	public void setIntegral(java.lang.Integer integral) {
		this.integral = integral;
	}

	public java.lang.Integer getCouponUserId() {
		return couponUserId;
	}

	public void setCouponUserId(java.lang.Integer couponUserId) {
		this.couponUserId = couponUserId;
	}

	public java.lang.Integer getActFullId() {
		return actFullId;
	}

	public void setActFullId(java.lang.Integer actFullId) {
		this.actFullId = actFullId;
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	public java.lang.String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(java.lang.String paymentName) {
		this.paymentName = paymentName;
	}

	public java.lang.String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(java.lang.String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}

	public java.lang.Integer getCityId() {
		return cityId;
	}

	public void setCityId(java.lang.Integer cityId) {
		this.cityId = cityId;
	}

	public java.lang.Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(java.lang.Integer areaId) {
		this.areaId = areaId;
	}

	public java.lang.String getAddressAll() {
		return addressAll;
	}

	public void setAddressAll(java.lang.String addressAll) {
		this.addressAll = addressAll;
	}

	public java.lang.String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(java.lang.String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getZipCode() {
		return zipCode;
	}

	public void setZipCode(java.lang.String zipCode) {
		this.zipCode = zipCode;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.util.Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(java.util.Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public java.util.Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}

	public java.lang.String getTradeSn() {
		return tradeSn;
	}

	public void setTradeSn(java.lang.String tradeSn) {
		this.tradeSn = tradeSn;
	}

	public java.lang.Integer getSource() {
		return source;
	}

	public void setSource(java.lang.Integer source) {
		this.source = source;
	}

	public java.lang.Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(java.lang.Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public java.lang.String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(java.lang.String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public java.lang.String getLogisticsNumber() {
		return logisticsNumber;
	}

	public void setLogisticsNumber(java.lang.String logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}

	public java.lang.Integer getIsCodconfim() {
		return isCodconfim;
	}

	public void setIsCodconfim(java.lang.Integer isCodconfim) {
		this.isCodconfim = isCodconfim;
	}

	public java.lang.Integer getCodconfirmId() {
		return codconfirmId;
	}

	public void setCodconfirmId(java.lang.Integer codconfirmId) {
		this.codconfirmId = codconfirmId;
	}

	public java.lang.String getCodconfirmName() {
		return codconfirmName;
	}

	public void setCodconfirmName(java.lang.String codconfirmName) {
		this.codconfirmName = codconfirmName;
	}

	public java.util.Date getCodconfirmTime() {
		return codconfirmTime;
	}

	public void setCodconfirmTime(java.util.Date codconfirmTime) {
		this.codconfirmTime = codconfirmTime;
	}

	public java.lang.String getCodconfirmRemark() {
		return codconfirmRemark;
	}

	public void setCodconfirmRemark(java.lang.String codconfirmRemark) {
		this.codconfirmRemark = codconfirmRemark;
	}

	public java.lang.Integer getCodconfirmState() {
		return codconfirmState;
	}

	public void setCodconfirmState(java.lang.Integer codconfirmState) {
		this.codconfirmState = codconfirmState;
	}

	public java.lang.Integer getDeliveredSum() {
		return deliveredSum;
	}

	public void setDeliveredSum(java.lang.Integer deliveredSum) {
		this.deliveredSum = deliveredSum;
	}

	public java.math.BigDecimal getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(java.math.BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

	public BigDecimal getLabelPrice() {
		return labelPrice;
	}

	public void setLabelPrice(BigDecimal labelPrice) {
		this.labelPrice = labelPrice;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
    
    
    
}
