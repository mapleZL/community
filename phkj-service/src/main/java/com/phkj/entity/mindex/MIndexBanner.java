package com.phkj.entity.mindex;

import java.io.Serializable;

/**
 * 移动端首页轮播图
 * <p>Table: <strong>m_index_banner</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>title</td><td>{@link String}</td><td>title</td><td>varchar</td><td>标题</td></tr>
 *   <tr><td>linkUrl</td><td>{@link String}</td><td>link_url</td><td>varchar</td><td>链接地址</td></tr>
 *   <tr><td>image</td><td>{@link String}</td><td>image</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>orderNo</td><td>{@link Integer}</td><td>order_no</td><td>int</td><td>排序</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>展示开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>展示结束时间</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>tinyint</td><td>状态1使用0不使用</td></tr>
 *   <tr><td>createUserId</td><td>{@link Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class MIndexBanner implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6791654627989308910L;

    /** 状态：1使用 */
    public static final int   STATUS_1         = 1;
    /** 状态：0不使用 */
    public static final int   STATUS_0         = 0;

    private Integer id;
    private String  title;
    private String  linkUrl;
    private String  image;
    private Integer orderNo;
    private java.util.Date    startTime;
    private java.util.Date    endTime;
    private Integer status;
    private Integer createUserId;
    private String  createUserName;
    private java.util.Date    createTime;
    private Integer updateUserId;
    private String  updateUserName;
    private java.util.Date    updateTime;
    private String type="0";

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
     * 获取标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取链接地址
     */
    public String getLinkUrl() {
        return this.linkUrl;
    }

    /**
     * 设置链接地址
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取图片
     */
    public String getImage() {
        return this.image;
    }

    /**
     * 设置图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取排序
     */
    public Integer getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置排序
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取展示开始时间
     */
    public java.util.Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置展示开始时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取展示结束时间
     */
    public java.util.Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置展示结束时间
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取状态1使用0不使用
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态1使用0不使用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取createUserId
     */
    public Integer getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置createUserId
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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
     * 获取updateUserId
     */
    public Integer getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * 设置updateUserId
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}