package com.phkj.entity.notice;

import java.io.Serializable;

/**
 * 
 * <p>Table: <strong>st_applet_activity_sign</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>rActivityId</td><td>{@link java.lang.Long}</td><td>r_activity_id</td><td>bigint</td><td>关联活动id</td></tr>
 *   <tr><td>houseInfo</td><td>{@link java.lang.String}</td><td>house_info</td><td>varchar</td><td>房屋信息</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>报名人姓名</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>联系方式</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>examineId</td><td>{@link java.lang.Integer}</td><td>examine_id</td><td>int</td><td>审核人</td></tr>
 *   <tr><td>examineTime</td><td>{@link java.util.Date}</td><td>examine_time</td><td>datetime</td><td>examineTime</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.Integer}</td><td>sts</td><td>tinyint</td><td>任务状态</td></tr>
 * </table>
 *
 */
public class StAppletActivitySign implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.lang.Long    rActivityId;
    private String            activityTitle;
    private java.lang.String  houseInfo;
    private java.lang.String  memberName;
    private java.lang.String  phone;
    private java.lang.Integer createId;
    private java.util.Date    createTime;
    private java.lang.Integer examineId;
    private java.util.Date    examineTime;
    private java.lang.Integer sts;

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
     * 获取房屋信息
     */
    public java.lang.String getHouseInfo() {
        return this.houseInfo;
    }

    /**
     * 设置房屋信息
     */
    public void setHouseInfo(java.lang.String houseInfo) {
        this.houseInfo = houseInfo;
    }

    /**
     * 获取报名人姓名
     */
    public java.lang.String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置报名人姓名
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取联系方式
     */
    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 设置联系方式
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
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
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取审核人
     */
    public java.lang.Integer getExamineId() {
        return this.examineId;
    }

    /**
     * 设置审核人
     */
    public void setExamineId(java.lang.Integer examineId) {
        this.examineId = examineId;
    }

    /**
     * 获取examineTime
     */
    public java.util.Date getExamineTime() {
        return this.examineTime;
    }

    /**
     * 设置examineTime
     */
    public void setExamineTime(java.util.Date examineTime) {
        this.examineTime = examineTime;
    }

    /**
     * 获取任务状态
     */
    public java.lang.Integer getSts() {
        return this.sts;
    }

    /**
     * 设置任务状态
     */
    public void setSts(java.lang.Integer sts) {
        this.sts = sts;
    }

    public java.lang.Long getrActivityId() {
        return rActivityId;
    }

    public void setrActivityId(java.lang.Long rActivityId) {
        this.rActivityId = rActivityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

}