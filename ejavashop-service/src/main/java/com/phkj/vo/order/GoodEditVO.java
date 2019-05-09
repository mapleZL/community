package com.phkj.vo.order;

public class GoodEditVO {
    private java.lang.Integer id;
    private java.lang.String sendAddress;
    private java.lang.String payType;
    private java.util.Date payTime;
    private java.lang.Integer sendNum;
    private java.math.BigDecimal weight;
    private java.math.BigDecimal takePrice;
    private java.lang.String bookingUser;
    private java.lang.String mobile;
    private java.lang.String phone;
    private java.lang.String checkMan;
    private java.util.Date checkTime;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private java.lang.String ordersId;
    private java.lang.Integer status;
    private java.lang.String logisticsNo;
    private java.lang.String payMan;
    private java.lang.Integer logisticsId;
    private java.lang.String logisticsName;
    private java.lang.String tradeSn;
    private java.lang.Integer provinceId;
    private java.lang.Integer cityId;
    private java.lang.Integer areaId;
    private java.lang.String addressDetail;
    private java.lang.Integer isCodconfim;
    private java.lang.Integer codconfirmId;
    private java.lang.String codconfirmName;
    private java.util.Date codconfirmTime;
    private java.lang.String codconfirmRemark;
    private java.lang.Integer codconfirmState;
    private java.lang.String remark;
    private java.util.Date sendTime;
    private java.lang.String name;
    private java.lang.String paymentName;
    private java.lang.String paymentCode;
    private java.math.BigDecimal moneyProduct;
    private java.math.BigDecimal moneyOrder;
    private java.math.BigDecimal moneyPaidBalance;
    private java.math.BigDecimal moneyPaidReality;
    private java.math.BigDecimal moneyBack;
    private java.lang.String checkNote;
    private java.lang.Integer invoiceStatus;
    private java.lang.String invoiceTitle;
    private java.lang.String invoiceType;
    private java.lang.Integer payStatus;
    private java.util.Date finishTime;
    private java.lang.Integer isDelete;
    private java.lang.String sku;
    public java.lang.String getSku() {
        return sku;
    }

    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }

    public java.lang.Integer getDeliveredSum() {
        return deliveredSum;
    }

    public void setDeliveredSum(java.lang.Integer deliveredSum) {
        this.deliveredSum = deliveredSum;
    }

    private java.lang.Integer deliveredSum;
        
    /**
     * 获取主键
     */
    public java.lang.Integer getId(){
        return this.id;
    }
        
    /**
     * 设置主键
     */
    public void setId(java.lang.Integer id){
        this.id = id;
    }
        
    /**
     * 获取省市区组合
     */
    public java.lang.String getSendAddress(){
        return this.sendAddress;
    }
        
    /**
     * 设置省市区组合
     */
    public void setSendAddress(java.lang.String sendAddress){
        this.sendAddress = sendAddress;
    }
        
    /**
     * 获取支付方式
     */
    public java.lang.String getPayType(){
        return this.payType;
    }
        
    /**
     * 设置支付方式
     */
    public void setPayType(java.lang.String payType){
        this.payType = payType;
    }
        
    /**
     * 获取支付时间
     */
    public java.util.Date getPayTime(){
        return this.payTime;
    }
        
    /**
     * 设置支付时间
     */
    public void setPayTime(java.util.Date payTime){
        this.payTime = payTime;
    }
        
    /**
     * 获取发货数量
     */
    public java.lang.Integer getSendNum(){
        return this.sendNum;
    }
        
    /**
     * 设置发货数量
     */
    public void setSendNum(java.lang.Integer sendNum){
        this.sendNum = sendNum;
    }
        
    /**
     * 获取weight
     */
    public java.math.BigDecimal getWeight(){
        return this.weight;
    }
        
    /**
     * 设置weight
     */
    public void setWeight(java.math.BigDecimal weight){
        this.weight = weight;
    }
        
    /**
     * 获取运费
     */
    public java.math.BigDecimal getTakePrice(){
        return this.takePrice;
    }
        
    /**
     * 设置运费
     */
    public void setTakePrice(java.math.BigDecimal takePrice){
        this.takePrice = takePrice;
    }
        
    /**
     * 获取预订人
     */
    public java.lang.String getBookingUser(){
        return this.bookingUser;
    }
        
    /**
     * 设置预订人
     */
    public void setBookingUser(java.lang.String bookingUser){
        this.bookingUser = bookingUser;
    }
        
    /**
     * 获取联系方式
     */
    public java.lang.String getMobile(){
        return this.mobile;
    }
        
    /**
     * 设置联系方式
     */
    public void setMobile(java.lang.String mobile){
        this.mobile = mobile;
    }
        
    /**
     * 获取手机
     */
    public java.lang.String getPhone(){
        return this.phone;
    }
        
    /**
     * 设置手机
     */
    public void setPhone(java.lang.String phone){
        this.phone = phone;
    }
        
    /**
     * 获取审核人
     */
    public java.lang.String getCheckMan(){
        return this.checkMan;
    }
        
    /**
     * 设置审核人
     */
    public void setCheckMan(java.lang.String checkMan){
        this.checkMan = checkMan;
    }
        
    /**
     * 获取审核时间
     */
    public java.util.Date getCheckTime(){
        return this.checkTime;
    }
        
    /**
     * 设置审核时间
     */
    public void setCheckTime(java.util.Date checkTime){
        this.checkTime = checkTime;
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
     * 获取订单id
     */
    public java.lang.String getOrdersId(){
        return this.ordersId;
    }
        
    /**
     * 设置订单id
     */
    public void setOrdersId(java.lang.String ordersId){
        this.ordersId = ordersId;
    }
        
    /**
     * 获取1:待审核，2：已审核，3：待发货，4：发货中，5：已发货
     */
    public java.lang.Integer getStatus(){
        return this.status;
    }
        
    /**
     * 设置1:待审核，2：已审核，3：待发货，4：发货中，5：已发货
     */
    public void setStatus(java.lang.Integer status){
        this.status = status;
    }
        
    /**
     * 获取快递单号
     */
    public java.lang.String getLogisticsNo(){
        return this.logisticsNo;
    }
        
    /**
     * 设置快递单号
     */
    public void setLogisticsNo(java.lang.String logisticsNo){
        this.logisticsNo = logisticsNo;
    }
        
    /**
     * 获取支付人
     */
    public java.lang.String getPayMan(){
        return this.payMan;
    }
        
    /**
     * 设置支付人
     */
    public void setPayMan(java.lang.String payMan){
        this.payMan = payMan;
    }
        
    /**
     * 获取物流公司id
     */
    public java.lang.Integer getLogisticsId(){
        return this.logisticsId;
    }
        
    /**
     * 设置物流公司id
     */
    public void setLogisticsId(java.lang.Integer logisticsId){
        this.logisticsId = logisticsId;
    }
        
    /**
     * 获取物流公司名称
     */
    public java.lang.String getLogisticsName(){
        return this.logisticsName;
    }
        
    /**
     * 设置物流公司名称
     */
    public void setLogisticsName(java.lang.String logisticsName){
        this.logisticsName = logisticsName;
    }
        
    /**
     * 获取在线支付交易流水号
     */
    public java.lang.String getTradeSn(){
        return this.tradeSn;
    }
        
    /**
     * 设置在线支付交易流水号
     */
    public void setTradeSn(java.lang.String tradeSn){
        this.tradeSn = tradeSn;
    }
        
    /**
     * 获取provinceId
     */
    public java.lang.Integer getProvinceId(){
        return this.provinceId;
    }
        
    /**
     * 设置provinceId
     */
    public void setProvinceId(java.lang.Integer provinceId){
        this.provinceId = provinceId;
    }
        
    /**
     * 获取cityId
     */
    public java.lang.Integer getCityId(){
        return this.cityId;
    }
        
    /**
     * 设置cityId
     */
    public void setCityId(java.lang.Integer cityId){
        this.cityId = cityId;
    }
        
    /**
     * 获取areaId
     */
    public java.lang.Integer getAreaId(){
        return this.areaId;
    }
        
    /**
     * 设置areaId
     */
    public void setAreaId(java.lang.Integer areaId){
        this.areaId = areaId;
    }
        
    /**
     * 获取详细地址
     */
    public java.lang.String getAddressDetail(){
        return this.addressDetail;
    }
        
    /**
     * 设置详细地址
     */
    public void setAddressDetail(java.lang.String addressDetail){
        this.addressDetail = addressDetail;
    }
        
    /**
     * 获取isCodconfim
     */
    public java.lang.Integer getIsCodconfim(){
        return this.isCodconfim;
    }
        
    /**
     * 设置isCodconfim
     */
    public void setIsCodconfim(java.lang.Integer isCodconfim){
        this.isCodconfim = isCodconfim;
    }
        
    /**
     * 获取是否货到付款订单0、不是；1、是
     */
    public java.lang.Integer getCodconfirmId(){
        return this.codconfirmId;
    }
        
    /**
     * 设置是否货到付款订单0、不是；1、是
     */
    public void setCodconfirmId(java.lang.Integer codconfirmId){
        this.codconfirmId = codconfirmId;
    }
        
    /**
     * 获取货到付款确认Name
     */
    public java.lang.String getCodconfirmName(){
        return this.codconfirmName;
    }
        
    /**
     * 设置货到付款确认Name
     */
    public void setCodconfirmName(java.lang.String codconfirmName){
        this.codconfirmName = codconfirmName;
    }
        
    /**
     * 获取货到付款确认时间
     */
    public java.util.Date getCodconfirmTime(){
        return this.codconfirmTime;
    }
        
    /**
     * 设置货到付款确认时间
     */
    public void setCodconfirmTime(java.util.Date codconfirmTime){
        this.codconfirmTime = codconfirmTime;
    }
        
    /**
     * 获取货到付款确认备注
     */
    public java.lang.String getCodconfirmRemark(){
        return this.codconfirmRemark;
    }
        
    /**
     * 设置货到付款确认备注
     */
    public void setCodconfirmRemark(java.lang.String codconfirmRemark){
        this.codconfirmRemark = codconfirmRemark;
    }
        
    /**
     * 获取货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
     */
    public java.lang.Integer getCodconfirmState(){
        return this.codconfirmState;
    }
        
    /**
     * 设置货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
     */
    public void setCodconfirmState(java.lang.Integer codconfirmState){
        this.codconfirmState = codconfirmState;
    }
        
    /**
     * 获取发货说明
     */
    public java.lang.String getRemark(){
        return this.remark;
    }
        
    /**
     * 设置发货说明
     */
    public void setRemark(java.lang.String remark){
        this.remark = remark;
    }
        
    /**
     * 获取发货时间
     */
    public java.util.Date getSendTime(){
        return this.sendTime;
    }
        
    /**
     * 设置发货时间
     */
    public void setSendTime(java.util.Date sendTime){
        this.sendTime = sendTime;
    }
        
    /**
     * 获取收货人
     */
    public java.lang.String getName(){
        return this.name;
    }
        
    /**
     * 设置收货人
     */
    public void setName(java.lang.String name){
        this.name = name;
    }
        
    /**
     * 获取支付方式名称
     */
    public java.lang.String getPaymentName(){
        return this.paymentName;
    }
        
    /**
     * 设置支付方式名称
     */
    public void setPaymentName(java.lang.String paymentName){
        this.paymentName = paymentName;
    }
        
    /**
     * 获取支付方式code
     */
    public java.lang.String getPaymentCode(){
        return this.paymentCode;
    }
        
    /**
     * 设置支付方式code
     */
    public void setPaymentCode(java.lang.String paymentCode){
        this.paymentCode = paymentCode;
    }
        
    /**
     * 获取moneyProduct
     */
    public java.math.BigDecimal getMoneyProduct(){
        return this.moneyProduct;
    }
        
    /**
     * 设置moneyProduct
     */
    public void setMoneyProduct(java.math.BigDecimal moneyProduct){
        this.moneyProduct = moneyProduct;
    }
        
    /**
     * 获取moneyOrder
     */
    public java.math.BigDecimal getMoneyOrder(){
        return this.moneyOrder;
    }
        
    /**
     * 设置moneyOrder
     */
    public void setMoneyOrder(java.math.BigDecimal moneyOrder){
        this.moneyOrder = moneyOrder;
    }
        
    /**
     * 获取moneyPaidBalance
     */
    public java.math.BigDecimal getMoneyPaidBalance(){
        return this.moneyPaidBalance;
    }
        
    /**
     * 设置moneyPaidBalance
     */
    public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance){
        this.moneyPaidBalance = moneyPaidBalance;
    }
        
    /**
     * 获取moneyPaidReality
     */
    public java.math.BigDecimal getMoneyPaidReality(){
        return this.moneyPaidReality;
    }
        
    /**
     * 设置moneyPaidReality
     */
    public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality){
        this.moneyPaidReality = moneyPaidReality;
    }
        
    /**
     * 获取moneyBack
     */
    public java.math.BigDecimal getMoneyBack(){
        return this.moneyBack;
    }
        
    /**
     * 设置moneyBack
     */
    public void setMoneyBack(java.math.BigDecimal moneyBack){
        this.moneyBack = moneyBack;
    }
        
    /**
     * 获取审核原因
     */
    public java.lang.String getCheckNote(){
        return this.checkNote;
    }
        
    /**
     * 设置审核原因
     */
    public void setCheckNote(java.lang.String checkNote){
        this.checkNote = checkNote;
    }
        
    /**
     * 获取发票状态0、不要发票；1、单位；2个人
     */
    public java.lang.Integer getInvoiceStatus(){
        return this.invoiceStatus;
    }
        
    /**
     * 设置发票状态0、不要发票；1、单位；2个人
     */
    public void setInvoiceStatus(java.lang.Integer invoiceStatus){
        this.invoiceStatus = invoiceStatus;
    }
        
    /**
     * 获取发票抬头
     */
    public java.lang.String getInvoiceTitle(){
        return this.invoiceTitle;
    }
        
    /**
     * 设置发票抬头
     */
    public void setInvoiceTitle(java.lang.String invoiceTitle){
        this.invoiceTitle = invoiceTitle;
    }
        
    /**
     * 获取发票类型（内容）
     */
    public java.lang.String getInvoiceType(){
        return this.invoiceType;
    }
        
    /**
     * 设置发票类型（内容）
     */
    public void setInvoiceType(java.lang.String invoiceType){
        this.invoiceType = invoiceType;
    }
        
    /**
     * 获取付款状态：0 买家未付款 1 买家已付款
     */
    public java.lang.Integer getPayStatus(){
        return this.payStatus;
    }
        
    /**
     * 设置付款状态：0 买家未付款 1 买家已付款
     */
    public void setPayStatus(java.lang.Integer payStatus){
        this.payStatus = payStatus;
    }
        
    /**
     * 获取完成时间
     */
    public java.util.Date getFinishTime(){
        return this.finishTime;
    }
        
    /**
     * 设置完成时间
     */
    public void setFinishTime(java.util.Date finishTime){
        this.finishTime = finishTime;
    }
    
    /**
     * 获取是否删除
     */
    public java.lang.Integer getIsDelete() {
        return isDelete;
    }
    
     /**
      * 设置是否删除
      * @param isDelete
      */
    public void setIsDelete(java.lang.Integer isDelete) {
        this.isDelete = isDelete;
    }
}
