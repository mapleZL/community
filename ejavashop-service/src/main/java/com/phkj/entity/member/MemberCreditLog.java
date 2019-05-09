package com.phkj.entity.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 赊账记录
 * <p>Table: <strong>member_credit_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>creditId</td><td>{@link java.lang.Integer}</td><td>credit_id</td><td>int</td><td>赊账管理id</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.Integer}</td><td>type</td><td>tinyint</td><td>赊账类型 1-订单余额支付 2-系统调整 3-退款</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>orderSn</td><td>{@link java.lang.String}</td><td>order_sn</td><td>varchar</td><td>订单号</td></tr>
 *   <tr><td>orderMoney</td><td>{@link java.math.BigDecimal}</td><td>order_money</td><td>decimal</td><td>订单金额</td></tr>
 *   <tr><td>orderDay</td><td>{@link java.util.Date}</td><td>order_day</td><td>datetime</td><td>订单日期</td></tr>
 *   <tr><td>orderPayState</td><td>{@link java.lang.Integer}</td><td>order_pay_state</td><td>tinyint</td><td>订单支付状态，现在只是存储赊账订单</td></tr>
 *   <tr><td>balanceBefore</td><td>{@link java.math.BigDecimal}</td><td>balance_before</td><td>decimal</td><td>支付前余额</td></tr>
 *   <tr><td>balanceAfter</td><td>{@link java.math.BigDecimal}</td><td>balance_after</td><td>decimal</td><td>支付后余额</td></tr>
 *   <tr><td>creditAmount</td><td>{@link java.math.BigDecimal}</td><td>credit_amount</td><td>decimal</td><td>此次赊账变动金额</td></tr>
 *   <tr><td>creditBefore</td><td>{@link java.math.BigDecimal}</td><td>credit_before</td><td>decimal</td><td>此次赊账前剩余额度</td></tr>
 *   <tr><td>creditAfter</td><td>{@link java.math.BigDecimal}</td><td>credit_after</td><td>decimal</td><td>此次赊账后剩余额度</td></tr>
 *   <tr><td>expireDateBefore</td><td>{@link java.util.Date}</td><td>expire_date_before</td><td>datetime</td><td>调整前到期日（仅当type=2时）</td></tr>
 *   <tr><td>periodAccount</td><td>{@link java.lang.Integer}</td><td>period_account</td><td>int</td><td>账期周期（仅当type=2时）</td></tr>
 *   <tr><td>expireDateAfter</td><td>{@link java.util.Date}</td><td>expire_date_after</td><td>datetime</td><td>调整后到期日（仅当type=2时）</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 *   <tr><td>creatTime</td><td>{@link java.util.Date}</td><td>creat_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class MemberCreditLog implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = 2062637417354916210L;
    private java.lang.Integer    id;                                     //id
    private java.lang.Integer    creditId;                               //赊账管理id
    private java.lang.Integer    type;                                   //赊账类型 1-订单余额支付 2-系统调整 3-退款
    private java.lang.Integer    memberId;                               //会员ID
    private java.lang.String     orderSn;                                //订单号
    private java.math.BigDecimal orderMoney;                             //订单金额
    private java.util.Date       orderDay;                               //订单日期
    private java.lang.Integer    orderPayState;                          //订单支付状态，现在只是存储赊账订单
    private java.math.BigDecimal balanceBefore;                          //支付前余额
    private java.math.BigDecimal balanceAfter;                           //支付后余额
    private java.math.BigDecimal creditAmount;                           //此次赊账变动金额
    private java.math.BigDecimal creditBefore;                           //此次赊账前剩余额度
    private java.math.BigDecimal creditAfter;                            //此次赊账后剩余额度
    private java.util.Date       expireDateBefore;                       //调整前到期日（仅当type=2时）
    private java.lang.Integer    periodAccount;                          //账期周期（仅当type=2时）
    private java.util.Date       expireDateAfter;                        //调整后到期日（仅当type=2时）
    private java.lang.Integer    createId;                               //createId
    private java.lang.String     createName;                             //createName
    private java.util.Date       creatTime;                              //创建时间
    private java.lang.String     createTime;                             //创建时间
    private String               memberName;

    
    public java.lang.String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public MemberCreditLog() {
    }

    /**
     * 后台新增构造器
     */
    public MemberCreditLog(Integer creditId, Integer memberId, BigDecimal creditAmount,
                           BigDecimal creditAfter, Integer periodAccount, Date expireDateAfter,
                           Integer createId, String createName, Date creatTime) {
        this.creditId = creditId;
        this.type = 2;
        this.memberId = memberId;
        this.creditAmount = creditAmount;
        this.creditBefore = BigDecimal.ZERO;
        this.creditAfter = creditAfter;
        this.periodAccount = periodAccount;
        this.expireDateAfter = expireDateAfter;
        this.createId = createId;
        this.createName = createName;
        this.creatTime = creatTime;
    }

    /**
     * 调整
     */
    public MemberCreditLog(Integer creditId, Integer memberId, BigDecimal balanceBefore,
                           BigDecimal balanceAfter, BigDecimal creditAmount,
                           BigDecimal creditBefore, BigDecimal creditAfter, Date expireDateBefore,
                           Integer periodAccount, Date expireDateAfter, Integer createId,
                           String createName, Date creatTime) {
        this.creditId = creditId;
        this.type = 2;
        this.memberId = memberId;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.creditAmount = creditAmount;
        this.creditBefore = creditBefore;
        this.creditAfter = creditAfter;
        this.expireDateBefore = expireDateBefore;
        this.periodAccount = periodAccount;
        this.expireDateAfter = expireDateAfter;
        this.createId = createId;
        this.createName = createName;
        this.creatTime = creatTime;
    }

    /**
     * 订单支付
     */
    public MemberCreditLog(Integer creditId, Integer memberId, String orderSn,
                           BigDecimal orderMoney, Date orderDay, Integer orderPayState,
                           BigDecimal balanceBefore, BigDecimal balanceAfter,
                           BigDecimal creditAmount, BigDecimal creditBefore,
                           BigDecimal creditAfter, Integer createId, String createName,
                           Date creatTime, String memberName) {
        this.creditId = creditId;
        this.type = 1;
        this.memberId = memberId;
        this.orderSn = orderSn;
        this.orderMoney = orderMoney;
        this.orderDay = orderDay;
        this.orderPayState = orderPayState;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.creditAmount = creditAmount;
        this.creditBefore = creditBefore;
        this.creditAfter = creditAfter;
        this.createId = createId;
        this.createName = createName;
        this.creatTime = creatTime;
        this.memberName = memberName;
    }

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取赊账管理id
     */
    public java.lang.Integer getCreditId() {
        return this.creditId;
    }

    /**
     * 设置赊账管理id
     */
    public void setCreditId(java.lang.Integer creditId) {
        this.creditId = creditId;
    }

    /**
     * 获取赊账类型 1-订单余额支付 2-系统调整 3-退款
     */
    public java.lang.Integer getType() {
        return this.type;
    }

    /**
     * 设置赊账类型 1-订单余额支付 2-系统调整 3-退款
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    /**
     * 获取会员ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取订单号
     */
    public java.lang.String getOrderSn() {
        return this.orderSn;
    }

    /**
     * 设置订单号
     */
    public void setOrderSn(java.lang.String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取订单金额
     */
    public java.math.BigDecimal getOrderMoney() {
        return this.orderMoney;
    }

    /**
     * 设置订单金额
     */
    public void setOrderMoney(java.math.BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 获取订单日期
     */
    public java.util.Date getOrderDay() {
        return this.orderDay;
    }

    /**
     * 设置订单日期
     */
    public void setOrderDay(java.util.Date orderDay) {
        this.orderDay = orderDay;
    }

    /**
     * 获取订单支付状态，现在只是存储赊账订单
     */
    public java.lang.Integer getOrderPayState() {
        return this.orderPayState;
    }

    /**
     * 设置订单支付状态，现在只是存储赊账订单
     */
    public void setOrderPayState(java.lang.Integer orderPayState) {
        this.orderPayState = orderPayState;
    }

    /**
     * 获取支付前余额
     */
    public java.math.BigDecimal getBalanceBefore() {
        return this.balanceBefore;
    }

    /**
     * 设置支付前余额
     */
    public void setBalanceBefore(java.math.BigDecimal balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    /**
     * 获取支付后余额
     */
    public java.math.BigDecimal getBalanceAfter() {
        return this.balanceAfter;
    }

    /**
     * 设置支付后余额
     */
    public void setBalanceAfter(java.math.BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    /**
     * 获取此次赊账变动金额
     */
    public java.math.BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    /**
     * 设置此次赊账变动金额
     */
    public void setCreditAmount(java.math.BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * 获取此次赊账前剩余额度
     */
    public java.math.BigDecimal getCreditBefore() {
        return this.creditBefore;
    }

    /**
     * 设置此次赊账前剩余额度
     */
    public void setCreditBefore(java.math.BigDecimal creditBefore) {
        this.creditBefore = creditBefore;
    }

    /**
     * 获取此次赊账后剩余额度
     */
    public java.math.BigDecimal getCreditAfter() {
        return this.creditAfter;
    }

    /**
     * 设置此次赊账后剩余额度
     */
    public void setCreditAfter(java.math.BigDecimal creditAfter) {
        this.creditAfter = creditAfter;
    }

    /**
     * 获取调整前到期日（仅当type=2时）
     */
    public java.util.Date getExpireDateBefore() {
        return this.expireDateBefore;
    }

    /**
     * 设置调整前到期日（仅当type=2时）
     */
    public void setExpireDateBefore(java.util.Date expireDateBefore) {
        this.expireDateBefore = expireDateBefore;
    }

    /**
     * 获取账期周期（仅当type=2时）
     */
    public java.lang.Integer getPeriodAccount() {
        return this.periodAccount;
    }

    /**
     * 设置账期周期（仅当type=2时）
     */
    public void setPeriodAccount(java.lang.Integer periodAccount) {
        this.periodAccount = periodAccount;
    }

    /**
     * 获取调整后到期日（仅当type=2时）
     */
    public java.util.Date getExpireDateAfter() {
        return this.expireDateAfter;
    }

    /**
     * 设置调整后到期日（仅当type=2时）
     */
    public void setExpireDateAfter(java.util.Date expireDateAfter) {
        this.expireDateAfter = expireDateAfter;
    }

    /**
     * 获取createId
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置createId
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取createName
     */
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 设置createName
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreatTime() {
        return this.creatTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreatTime(java.util.Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}