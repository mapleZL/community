package com.phkj.entity.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 赊账管理
 * <p>Table: <strong>member_credit</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>quota</td><td>{@link java.math.BigDecimal}</td><td>quota</td><td>decimal</td><td>账期额度</td></tr>
 *   <tr><td>used</td><td>{@link java.math.BigDecimal}</td><td>used</td><td>decimal</td><td>已使用额度</td></tr>
 *   <tr><td>surplus</td><td>{@link java.math.BigDecimal}</td><td>surplus</td><td>decimal</td><td>剩余额度</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>状态 1-未到期 2-已到期</td></tr>
 *   <tr><td>expireDate</td><td>{@link java.util.Date}</td><td>expire_date</td><td>datetime</td><td>到期日</td></tr>
 *   <tr><td>hasSettled</td><td>{@link java.lang.Integer}</td><td>has_settled</td><td>tinyint</td><td>是否已结清 1-是 0-否</td></tr>
 *   <tr><td>cycle</td><td>{@link java.lang.Integer}</td><td>cycle</td><td>int</td><td>账期周期（天）</td></tr>
 *   <tr><td>quotaDate</td><td>{@link java.util.Date}</td><td>quota_date</td><td>datetime</td><td>下次账期到期时间</td></tr>
 *   <tr><td>source</td><td>{@link java.lang.Integer}</td><td>source</td><td>tinyint</td><td>额度来源 1-退款 2-管理员调整</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateId</td><td>{@link java.lang.Integer}</td><td>update_id</td><td>int</td><td>updateId</td></tr>
 *   <tr><td>updateName</td><td>{@link java.lang.String}</td><td>update_name</td><td>varchar</td><td>updateName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员id</td></tr>
 * </table>
 *
 */
public class MemberCredit implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -674658261354287344L;
    private java.lang.Integer    id               = 0;                   //id
    private java.math.BigDecimal quota            = BigDecimal.ZERO;     //账期额度
    private java.math.BigDecimal used             = BigDecimal.ZERO;     //已使用额度
    private java.math.BigDecimal surplus          = BigDecimal.ZERO;     //剩余额度
    private java.lang.Integer    state            = 1;                   //状态 1-未到期 2-已到期
    private java.util.Date       expireDate       = new Date();          //到期日
    private java.lang.Integer    hasSettled       = 0;                   //是否已结清 1-是 0-否
    private java.lang.Integer    cycle            = 1;                   //账期周期（天）
    private java.lang.Integer    source;                                 //额度来源 1-退款 2-管理员调整
    private java.lang.Integer    createId         = 0;                   //createId
    private java.lang.String     createName;                             //createName
    private java.util.Date       createTime;                             //createTime
    private java.lang.Integer    updateId;                               //updateId
    private java.lang.String     updateName;                             //updateName
    private java.util.Date       updateTime;                             //updateTime
    private java.lang.Integer    memberId         = 0;                   //会员id
    private String               memberName;
    private String               memberBalance    = "0";
    private java.lang.String        realName;                   //真实姓名

    //-------------------------vo bg-----------------------------//
    private String               expireDateStr;
    private java.lang.Integer    status            = 1;
    private String               statusExp;         //赊账状态的中文表述

    //-------------------------vo ed-----------------------------//

    public String getStatusExp() {
        return statusExp;
    }

    public void setStatusExp(String statusExp) {
        this.statusExp = statusExp;
    }

    public java.lang.Integer getStatus() {
        return status;
    }

    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    public java.lang.String getRealName() {
        return realName;
    }

    public void setRealName(java.lang.String realName) {
        this.realName = realName;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取账期额度
     */
    public java.math.BigDecimal getQuota() {
        return this.quota;
    }

    /**
     * 设置账期额度
     */
    public void setQuota(java.math.BigDecimal quota) {
        this.quota = quota;
    }

    /**
     * 获取已使用额度
     */
    public java.math.BigDecimal getUsed() {
        return this.used;
    }

    /**
     * 设置已使用额度
     */
    public void setUsed(java.math.BigDecimal used) {
        this.used = used;
    }

    /**
     * 获取剩余额度
     */
    public java.math.BigDecimal getSurplus() {
        return this.surplus;
    }

    /**
     * 设置剩余额度
     */
    public void setSurplus(java.math.BigDecimal surplus) {
        this.surplus = surplus;
    }

    /**
     * 获取状态 1-未到期 2-已到期
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置状态 1-未到期 2-已到期
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取到期日
     */
    public java.util.Date getExpireDate() {
        return this.expireDate;
    }

    /**
     * 设置到期日
     */
    public void setExpireDate(java.util.Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取是否已结清 1-是 0-否
     */
    public java.lang.Integer getHasSettled() {
        return this.hasSettled;
    }

    /**
     * 设置是否已结清 1-是 0-否
     */
    public void setHasSettled(java.lang.Integer hasSettled) {
        this.hasSettled = hasSettled;
    }

    /**
     * 获取账期周期（天）
     */
    public java.lang.Integer getCycle() {
        return this.cycle;
    }

    /**
     * 设置账期周期（天）
     */
    public void setCycle(java.lang.Integer cycle) {
        this.cycle = cycle;
    }

    /**
     * 获取额度来源 1-退款 2-管理员调整
     */
    public java.lang.Integer getSource() {
        return this.source;
    }

    /**
     * 设置额度来源 1-退款 2-管理员调整
     */
    public void setSource(java.lang.Integer source) {
        this.source = source;
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
     * 获取updateId
     */
    public java.lang.Integer getUpdateId() {
        return this.updateId;
    }

    /**
     * 设置updateId
     */
    public void setUpdateId(java.lang.Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取updateName
     */
    public java.lang.String getUpdateName() {
        return this.updateName;
    }

    /**
     * 设置updateName
     */
    public void setUpdateName(java.lang.String updateName) {
        this.updateName = updateName;
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
     * 获取会员id
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员id
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    public String getExpireDateStr() {
        if (this.expireDate != null)
            expireDateStr = new SimpleDateFormat("yyyy-MM-dd").format(this.expireDate);
        return expireDateStr;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberBalance() {
        return memberBalance;
    }

    public void setMemberBalance(String memberBalance) {
        this.memberBalance = memberBalance;
    }
}