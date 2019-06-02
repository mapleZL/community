package com.phkj.entity.order;

import java.io.Serializable;

/**
 * 订单
 * <p>Table: <strong>st_applet_orders</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 * <tr style="background-color:#ddd;Text-align:Left;">
 * <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 * </tr>
 * <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 * <tr><td>orderSn</td><td>{@link String}</td><td>order_sn</td><td>varchar</td><td>订单号</td></tr>
 * <tr><td>relationOrderSn</td><td>{@link String}</td><td>relation_order_sn</td><td>varchar</td><td>关联订单编号</td></tr>
 * <tr><td>orderType</td><td>{@link Integer}</td><td>order_type</td><td>tinyint</td><td>订单类型：1、普通订单，5.二次加工订单</td></tr>
 * <tr><td>sellerId</td><td>{@link Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 * <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 * <tr><td>memberName</td><td>{@link String}</td><td>member_name</td><td>varchar</td><td>会员name</td></tr>
 * <tr><td>orderState</td><td>{@link Integer}</td><td>order_state</td><td>tinyint</td><td>订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单；7、申请退货 8、可退货 9、退货驳回</td></tr>
 * <tr><td>payTime</td><td>{@link java.util.Date}</td><td>pay_time</td><td>datetime</td><td>付款时间</td></tr>
 * <tr><td>paymentStatus</td><td>{@link Integer}</td><td>payment_status</td><td>tinyint</td><td>付款状态：0 买家未付款 1 买家已付款 2 买家未付款(二次包装已确认)3 改价订单</td></tr>
 * <tr><td>invoiceStatus</td><td>{@link Integer}</td><td>invoice_status</td><td>tinyint</td><td>发票状态0、不要发票；1、单位；2个人</td></tr>
 * <tr><td>invoiceTitle</td><td>{@link String}</td><td>invoice_title</td><td>varchar</td><td>发票抬头</td></tr>
 * <tr><td>invoiceType</td><td>{@link String}</td><td>invoice_type</td><td>varchar</td><td>发票类型（内容）</td></tr>
 * <tr><td>moneyProduct</td><td>{@link java.math.BigDecimal}</td><td>money_product</td><td>decimal</td><td>商品金额，等于订单中所有的商品的单价乘以数量之和</td></tr>
 * <tr><td>moneyLogistics</td><td>{@link java.math.BigDecimal}</td><td>money_logistics</td><td>decimal</td><td>物流费用</td></tr>
 * <tr><td>moneyOrder</td><td>{@link java.math.BigDecimal}</td><td>money_order</td><td>decimal</td><td>订单总金额，等于商品总金额＋运费-优惠金额总额</td></tr>
 * <tr><td>moneyPaidBalance</td><td>{@link java.math.BigDecimal}</td><td>money_paid_balance</td><td>decimal</td><td>余额账户支付总金额</td></tr>
 * <tr><td>moneyPaidReality</td><td>{@link java.math.BigDecimal}</td><td>money_paid_reality</td><td>decimal</td><td>现金支付金额</td></tr>
 * <tr><td>moneyCoupon</td><td>{@link java.math.BigDecimal}</td><td>money_coupon</td><td>decimal</td><td>优惠券优惠金额</td></tr>
 * <tr><td>moneyActFull</td><td>{@link java.math.BigDecimal}</td><td>money_act_full</td><td>decimal</td><td>订单满减金额</td></tr>
 * <tr><td>moneyDiscount</td><td>{@link java.math.BigDecimal}</td><td>money_discount</td><td>decimal</td><td>优惠金额总额（满减、立减、优惠券等所有优惠额的和）</td></tr>
 * <tr><td>moneyBack</td><td>{@link java.math.BigDecimal}</td><td>money_back</td><td>decimal</td><td>退款的金额，订单没有退款则为0</td></tr>
 * <tr><td>moneyIntegral</td><td>{@link java.math.BigDecimal}</td><td>money_integral</td><td>decimal</td><td>积分换算金额</td></tr>
 * <tr><td>integral</td><td>{@link Integer}</td><td>integral</td><td>int</td><td>订单使用的积分数量</td></tr>
 * <tr><td>couponUserId</td><td>{@link Integer}</td><td>coupon_user_id</td><td>int</td><td>优惠码ID</td></tr>
 * <tr><td>actFullId</td><td>{@link Integer}</td><td>act_full_id</td><td>int</td><td>订单满减活动ID</td></tr>
 * <tr><td>activityId</td><td>{@link Integer}</td><td>activity_id</td><td>int</td><td>活动ID，为0正常购买</td></tr>
 * <tr><td>ip</td><td>{@link String}</td><td>ip</td><td>varchar</td><td>ip</td></tr>
 * <tr><td>paymentName</td><td>{@link String}</td><td>payment_name</td><td>varchar</td><td>支付方式名称</td></tr>
 * <tr><td>paymentCode</td><td>{@link String}</td><td>payment_code</td><td>varchar</td><td>支付方式code</td></tr>
 * <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>收货人</td></tr>
 * <tr><td>provinceId</td><td>{@link Integer}</td><td>province_id</td><td>int</td><td>Province</td></tr>
 * <tr><td>cityId</td><td>{@link Integer}</td><td>city_id</td><td>int</td><td>city</td></tr>
 * <tr><td>areaId</td><td>{@link Integer}</td><td>area_id</td><td>int</td><td>area</td></tr>
 * <tr><td>addressAll</td><td>{@link String}</td><td>address_all</td><td>varchar</td><td>省市区组合</td></tr>
 * <tr><td>addressInfo</td><td>{@link String}</td><td>address_info</td><td>varchar</td><td>详细地址</td></tr>
 * <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>mobile</td></tr>
 * <tr><td>email</td><td>{@link String}</td><td>email</td><td>varchar</td><td>email</td></tr>
 * <tr><td>zipCode</td><td>{@link String}</td><td>zip_code</td><td>varchar</td><td>邮编</td></tr>
 * <tr><td>remark</td><td>{@link String}</td><td>remark</td><td>varchar</td><td>订单备注</td></tr>
 * <tr><td>deliverTime</td><td>{@link java.util.Date}</td><td>deliver_time</td><td>datetime</td><td>发货时间</td></tr>
 * <tr><td>finishTime</td><td>{@link java.util.Date}</td><td>finish_time</td><td>datetime</td><td>订单完成时间</td></tr>
 * <tr><td>source</td><td>{@link Integer}</td><td>source</td><td>tinyint</td><td>会员来源1、pc；2、H5；3、Android；4、IOS</td></tr>
 * <tr><td>logisticsId</td><td>{@link Integer}</td><td>logistics_id</td><td>int</td><td>物流公司ID</td></tr>
 * <tr><td>logisticsName</td><td>{@link String}</td><td>logistics_name</td><td>varchar</td><td>物流公司</td></tr>
 * <tr><td>logisticsNumber</td><td>{@link String}</td><td>logistics_number</td><td>varchar</td><td>发票快递单号</td></tr>
 * <tr><td>isCodconfim</td><td>{@link Integer}</td><td>is_codconfim</td><td>tinyint</td><td>是否货到付款订单0、不是；1、是</td></tr>
 * <tr><td>codconfirmId</td><td>{@link Integer}</td><td>codconfirm_id</td><td>int</td><td>货到付款确认人</td></tr>
 * <tr><td>codconfirmName</td><td>{@link String}</td><td>codconfirm_name</td><td>varchar</td><td>货到付款确认Name</td></tr>
 * <tr><td>codconfirmTime</td><td>{@link java.util.Date}</td><td>codconfirm_time</td><td>datetime</td><td>货到付款确认时间</td></tr>
 * <tr><td>codconfirmRemark</td><td>{@link String}</td><td>codconfirm_remark</td><td>varchar</td><td>货到付款确认备注</td></tr>
 * <tr><td>codconfirmState</td><td>{@link Integer}</td><td>codconfirm_state</td><td>tinyint</td><td>货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消</td></tr>
 * <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * <tr><td>servicePrice</td><td>{@link java.math.BigDecimal}</td><td>service_price</td><td>decimal</td><td>服务费，所有网单的套餐费用之和</td></tr>
 * <tr><td>labelPrice</td><td>{@link java.math.BigDecimal}</td><td>label_price</td><td>decimal</td><td>标签费用总和</td></tr>
 * <tr><td>deliveredSum</td><td>{@link Integer}</td><td>delivered_sum</td><td>int</td><td>已发货数量</td></tr>
 * <tr><td>tradeNo</td><td>{@link String}</td><td>trade_no</td><td>varchar</td><td>交易编号一</td></tr>
 * <tr><td>tradeSn</td><td>{@link String}</td><td>trade_sn</td><td>varchar</td><td>交易编号二</td></tr>
 * <tr><td>syncState</td><td>{@link String}</td><td>sync_state</td><td>varchar</td><td>推送状态</td></tr>
 * <tr><td>outTime</td><td>{@link java.util.Date}</td><td>out_time</td><td>datetime</td><td>出库时间</td></tr>
 * <tr><td>sendType</td><td>{@link Integer}</td><td>send_type</td><td>int</td><td>0:直发，1：代发</td></tr>
 * <tr><td>sendArea</td><td>{@link Integer}</td><td>send_area</td><td>int</td><td>送货上门区域</td></tr>
 * <tr><td>logisticsNameOms</td><td>{@link String}</td><td>logistics_name_oms</td><td>varchar</td><td>logisticsNameOms</td></tr>
 * </table>
 */
public class StAppletOrders implements Serializable {

    private Integer id;
    private String orderSn;
    private String relationOrderSn;
    private Integer orderType;
    private Integer sellerId;
    private Integer memberId;
    private String memberName;
    private Integer orderState;
    private java.util.Date payTime;
    private Integer paymentStatus;
    private Integer invoiceStatus;
    private String invoiceTitle;
    private String invoiceType;
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
    private Integer integral;
    private Integer couponUserId;
    private Integer actFullId;
    private Integer activityId;
    private String ip;
    private String paymentName;
    private String paymentCode;
    private String name;
    private Integer provinceId;
    private Integer cityId;
    private Integer areaId;
    private String addressAll;
    private String addressInfo;
    private String mobile;
    private String email;
    private String zipCode;
    private String remark;
    private java.util.Date deliverTime;
    private java.util.Date finishTime;
    private Integer source;
    private Integer logisticsId;
    private String logisticsName;
    private String logisticsNumber;
    private Integer isCodconfim;
    private Integer codconfirmId;
    private String codconfirmName;
    private java.util.Date codconfirmTime;
    private String codconfirmRemark;
    private Integer codconfirmState;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private java.math.BigDecimal servicePrice;
    private java.math.BigDecimal labelPrice;
    private Integer deliveredSum;
    private String tradeNo;
    private String tradeSn;
    private String syncState;
    private java.util.Date outTime;
    private Integer sendType;
    private Integer sendArea;
    private Integer deleted;
    private String logisticsNameOms;


    /**
     * 获取id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单号
     */
    public String getOrderSn() {
        return this.orderSn;
    }

    /**
     * 设置订单号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取关联订单编号
     */
    public String getRelationOrderSn() {
        return this.relationOrderSn;
    }

    /**
     * 设置关联订单编号
     */
    public void setRelationOrderSn(String relationOrderSn) {
        this.relationOrderSn = relationOrderSn;
    }

    /**
     * 获取订单类型：1、普通订单，5.二次加工订单
     */
    public Integer getOrderType() {
        return this.orderType;
    }

    /**
     * 设置订单类型：1、普通订单，5.二次加工订单
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取商家ID
     */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取会员ID
     */
    public Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员name
     */
    public String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置会员name
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单；7、申请退货 8、可退货 9、退货驳回
     */
    public Integer getOrderState() {
        return this.orderState;
    }

    /**
     * 设置订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单；7、申请退货 8、可退货 9、退货驳回
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取付款时间
     */
    public java.util.Date getPayTime() {
        return this.payTime;
    }

    /**
     * 设置付款时间
     */
    public void setPayTime(java.util.Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取付款状态：0 买家未付款 1 买家已付款 2 买家未付款(二次包装已确认)3 改价订单
     */
    public Integer getPaymentStatus() {
        return this.paymentStatus;
    }

    /**
     * 设置付款状态：0 买家未付款 1 买家已付款 2 买家未付款(二次包装已确认)3 改价订单
     */
    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 获取发票状态0、不要发票；1、单位；2个人
     */
    public Integer getInvoiceStatus() {
        return this.invoiceStatus;
    }

    /**
     * 设置发票状态0、不要发票；1、单位；2个人
     */
    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 获取发票抬头
     */
    public String getInvoiceTitle() {
        return this.invoiceTitle;
    }

    /**
     * 设置发票抬头
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * 获取发票类型（内容）
     */
    public String getInvoiceType() {
        return this.invoiceType;
    }

    /**
     * 设置发票类型（内容）
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 获取商品金额，等于订单中所有的商品的单价乘以数量之和
     */
    public java.math.BigDecimal getMoneyProduct() {
        return this.moneyProduct;
    }

    /**
     * 设置商品金额，等于订单中所有的商品的单价乘以数量之和
     */
    public void setMoneyProduct(java.math.BigDecimal moneyProduct) {
        this.moneyProduct = moneyProduct;
    }

    /**
     * 获取物流费用
     */
    public java.math.BigDecimal getMoneyLogistics() {
        return this.moneyLogistics;
    }

    /**
     * 设置物流费用
     */
    public void setMoneyLogistics(java.math.BigDecimal moneyLogistics) {
        this.moneyLogistics = moneyLogistics;
    }

    /**
     * 获取订单总金额，等于商品总金额＋运费-优惠金额总额
     */
    public java.math.BigDecimal getMoneyOrder() {
        return this.moneyOrder;
    }

    /**
     * 设置订单总金额，等于商品总金额＋运费-优惠金额总额
     */
    public void setMoneyOrder(java.math.BigDecimal moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    /**
     * 获取余额账户支付总金额
     */
    public java.math.BigDecimal getMoneyPaidBalance() {
        return this.moneyPaidBalance;
    }

    /**
     * 设置余额账户支付总金额
     */
    public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance) {
        this.moneyPaidBalance = moneyPaidBalance;
    }

    /**
     * 获取现金支付金额
     */
    public java.math.BigDecimal getMoneyPaidReality() {
        return this.moneyPaidReality;
    }

    /**
     * 设置现金支付金额
     */
    public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality) {
        this.moneyPaidReality = moneyPaidReality;
    }

    /**
     * 获取优惠券优惠金额
     */
    public java.math.BigDecimal getMoneyCoupon() {
        return this.moneyCoupon;
    }

    /**
     * 设置优惠券优惠金额
     */
    public void setMoneyCoupon(java.math.BigDecimal moneyCoupon) {
        this.moneyCoupon = moneyCoupon;
    }

    /**
     * 获取订单满减金额
     */
    public java.math.BigDecimal getMoneyActFull() {
        return this.moneyActFull;
    }

    /**
     * 设置订单满减金额
     */
    public void setMoneyActFull(java.math.BigDecimal moneyActFull) {
        this.moneyActFull = moneyActFull;
    }

    /**
     * 获取优惠金额总额（满减、立减、优惠券等所有优惠额的和）
     */
    public java.math.BigDecimal getMoneyDiscount() {
        return this.moneyDiscount;
    }

    /**
     * 设置优惠金额总额（满减、立减、优惠券等所有优惠额的和）
     */
    public void setMoneyDiscount(java.math.BigDecimal moneyDiscount) {
        this.moneyDiscount = moneyDiscount;
    }

    /**
     * 获取退款的金额，订单没有退款则为0
     */
    public java.math.BigDecimal getMoneyBack() {
        return this.moneyBack;
    }

    /**
     * 设置退款的金额，订单没有退款则为0
     */
    public void setMoneyBack(java.math.BigDecimal moneyBack) {
        this.moneyBack = moneyBack;
    }

    /**
     * 获取积分换算金额
     */
    public java.math.BigDecimal getMoneyIntegral() {
        return this.moneyIntegral;
    }

    /**
     * 设置积分换算金额
     */
    public void setMoneyIntegral(java.math.BigDecimal moneyIntegral) {
        this.moneyIntegral = moneyIntegral;
    }

    /**
     * 获取订单使用的积分数量
     */
    public Integer getIntegral() {
        return this.integral;
    }

    /**
     * 设置订单使用的积分数量
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取优惠码ID
     */
    public Integer getCouponUserId() {
        return this.couponUserId;
    }

    /**
     * 设置优惠码ID
     */
    public void setCouponUserId(Integer couponUserId) {
        this.couponUserId = couponUserId;
    }

    /**
     * 获取订单满减活动ID
     */
    public Integer getActFullId() {
        return this.actFullId;
    }

    /**
     * 设置订单满减活动ID
     */
    public void setActFullId(Integer actFullId) {
        this.actFullId = actFullId;
    }

    /**
     * 获取活动ID，为0正常购买
     */
    public Integer getActivityId() {
        return this.activityId;
    }

    /**
     * 设置活动ID，为0正常购买
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取支付方式名称
     */
    public String getPaymentName() {
        return this.paymentName;
    }

    /**
     * 设置支付方式名称
     */
    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    /**
     * 获取支付方式code
     */
    public String getPaymentCode() {
        return this.paymentCode;
    }

    /**
     * 设置支付方式code
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * 获取收货人
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置收货人
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取Province
     */
    public Integer getProvinceId() {
        return this.provinceId;
    }

    /**
     * 设置Province
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取city
     */
    public Integer getCityId() {
        return this.cityId;
    }

    /**
     * 设置city
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取area
     */
    public Integer getAreaId() {
        return this.areaId;
    }

    /**
     * 设置area
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取省市区组合
     */
    public String getAddressAll() {
        return this.addressAll;
    }

    /**
     * 设置省市区组合
     */
    public void setAddressAll(String addressAll) {
        this.addressAll = addressAll;
    }

    /**
     * 获取详细地址
     */
    public String getAddressInfo() {
        return this.addressInfo;
    }

    /**
     * 设置详细地址
     */
    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     * 获取mobile
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取邮编
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * 设置邮编
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取订单备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置订单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取发货时间
     */
    public java.util.Date getDeliverTime() {
        return this.deliverTime;
    }

    /**
     * 设置发货时间
     */
    public void setDeliverTime(java.util.Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * 获取订单完成时间
     */
    public java.util.Date getFinishTime() {
        return this.finishTime;
    }

    /**
     * 设置订单完成时间
     */
    public void setFinishTime(java.util.Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取会员来源1、pc；2、H5；3、Android；4、IOS
     */
    public Integer getSource() {
        return this.source;
    }

    /**
     * 设置会员来源1、pc；2、H5；3、Android；4、IOS
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取物流公司ID
     */
    public Integer getLogisticsId() {
        return this.logisticsId;
    }

    /**
     * 设置物流公司ID
     */
    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 获取物流公司
     */
    public String getLogisticsName() {
        return this.logisticsName;
    }

    /**
     * 设置物流公司
     */
    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    /**
     * 获取发票快递单号
     */
    public String getLogisticsNumber() {
        return this.logisticsNumber;
    }

    /**
     * 设置发票快递单号
     */
    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    /**
     * 获取是否货到付款订单0、不是；1、是
     */
    public Integer getIsCodconfim() {
        return this.isCodconfim;
    }

    /**
     * 设置是否货到付款订单0、不是；1、是
     */
    public void setIsCodconfim(Integer isCodconfim) {
        this.isCodconfim = isCodconfim;
    }

    /**
     * 获取货到付款确认人
     */
    public Integer getCodconfirmId() {
        return this.codconfirmId;
    }

    /**
     * 设置货到付款确认人
     */
    public void setCodconfirmId(Integer codconfirmId) {
        this.codconfirmId = codconfirmId;
    }

    /**
     * 获取货到付款确认Name
     */
    public String getCodconfirmName() {
        return this.codconfirmName;
    }

    /**
     * 设置货到付款确认Name
     */
    public void setCodconfirmName(String codconfirmName) {
        this.codconfirmName = codconfirmName;
    }

    /**
     * 获取货到付款确认时间
     */
    public java.util.Date getCodconfirmTime() {
        return this.codconfirmTime;
    }

    /**
     * 设置货到付款确认时间
     */
    public void setCodconfirmTime(java.util.Date codconfirmTime) {
        this.codconfirmTime = codconfirmTime;
    }

    /**
     * 获取货到付款确认备注
     */
    public String getCodconfirmRemark() {
        return this.codconfirmRemark;
    }

    /**
     * 设置货到付款确认备注
     */
    public void setCodconfirmRemark(String codconfirmRemark) {
        this.codconfirmRemark = codconfirmRemark;
    }

    /**
     * 获取货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
     */
    public Integer getCodconfirmState() {
        return this.codconfirmState;
    }

    /**
     * 设置货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
     */
    public void setCodconfirmState(Integer codconfirmState) {
        this.codconfirmState = codconfirmState;
    }

    /**
     * 获取createTime
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取服务费，所有网单的套餐费用之和
     */
    public java.math.BigDecimal getServicePrice() {
        return this.servicePrice;
    }

    /**
     * 设置服务费，所有网单的套餐费用之和
     */
    public void setServicePrice(java.math.BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    /**
     * 获取标签费用总和
     */
    public java.math.BigDecimal getLabelPrice() {
        return this.labelPrice;
    }

    /**
     * 设置标签费用总和
     */
    public void setLabelPrice(java.math.BigDecimal labelPrice) {
        this.labelPrice = labelPrice;
    }

    /**
     * 获取已发货数量
     */
    public Integer getDeliveredSum() {
        return this.deliveredSum;
    }

    /**
     * 设置已发货数量
     */
    public void setDeliveredSum(Integer deliveredSum) {
        this.deliveredSum = deliveredSum;
    }

    /**
     * 获取交易编号一
     */
    public String getTradeNo() {
        return this.tradeNo;
    }

    /**
     * 设置交易编号一
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 获取交易编号二
     */
    public String getTradeSn() {
        return this.tradeSn;
    }

    /**
     * 设置交易编号二
     */
    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
    }

    /**
     * 获取推送状态
     */
    public String getSyncState() {
        return this.syncState;
    }

    /**
     * 设置推送状态
     */
    public void setSyncState(String syncState) {
        this.syncState = syncState;
    }

    /**
     * 获取出库时间
     */
    public java.util.Date getOutTime() {
        return this.outTime;
    }

    /**
     * 设置出库时间
     */
    public void setOutTime(java.util.Date outTime) {
        this.outTime = outTime;
    }

    /**
     * 获取0:直发，1：代发
     */
    public Integer getSendType() {
        return this.sendType;
    }

    /**
     * 设置0:直发，1：代发
     */
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    /**
     * 获取送货上门区域
     */
    public Integer getSendArea() {
        return this.sendArea;
    }

    /**
     * 设置送货上门区域
     */
    public void setSendArea(Integer sendArea) {
        this.sendArea = sendArea;
    }

    /**
     * 获取logisticsNameOms
     */
    public String getLogisticsNameOms() {
        return this.logisticsNameOms;
    }

    /**
     * 设置logisticsNameOms
     */
    public void setLogisticsNameOms(String logisticsNameOms) {
        this.logisticsNameOms = logisticsNameOms;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}