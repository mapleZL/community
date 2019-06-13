package com.phkj.entity.event;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>Table: <strong>st_applet_hot_events</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>title</td><td>{@link java.lang.String}</td><td>title</td><td>varchar</td><td>标题</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>text</td><td>正文内容</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>类型</td></tr>
 *   <tr><td>sourceType</td><td>{@link java.lang.String}</td><td>source_type</td><td>varchar</td><td>来源</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>状态1：待发布，2：已发布</td></tr>
 *   <tr><td>receiveType</td><td>{@link java.lang.String}</td><td>receive_type</td><td>varchar</td><td>接收目标类型 无、 人、社区</td></tr>
 *   <tr><td>postBegin</td><td>{@link java.util.Date}</td><td>post_begin</td><td>datetime</td><td>开始时间</td></tr>
 *   <tr><td>postEnd</td><td>{@link java.util.Date}</td><td>post_end</td><td>datetime</td><td>结束时间</td></tr>
 *   <tr><td>villageCode</td><td>{@link java.lang.String}</td><td>village_code</td><td>varchar</td><td>小区编码</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class StAppletHotEvents implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.lang.String  title;
    private java.lang.String  content;
    private java.lang.String  type;
    private java.lang.String  sourceType;
    private java.lang.Integer status;
    private java.lang.String  receiveType;
    private java.util.Date    postBegin;
    private java.util.Date    postEnd;
    private java.lang.String  villageCode;
    private String            img;
    private java.lang.Integer createUserId;
    private java.util.Date    createTime;

    /**
     * 对应微信端展示字段
     */
    // 流量
    private Long              rate             = 0L;
    // 收藏人数
    private Long              collect          = 0L;
    // 评论人数
    private Long              comment          = 0L;
    // 头条对应的图片路径
    private List<String>      imgs;
    // 信息来源
    private String            sourceName;
    // 是否浏览过
    private boolean           hasBrowse        = false;
    // 是否收藏过
    private boolean           hasCollect       = false;
    // 是否已评论
    private boolean           hasComment       = false;

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
     * 获取标题
     */
    public java.lang.String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    /**
     * 获取正文内容
     */
    public java.lang.String getContent() {
        return this.content;
    }

    /**
     * 设置正文内容
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    /**
     * 获取类型
     */
    public java.lang.String getType() {
        return this.type;
    }

    /**
     * 设置类型
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    /**
     * 获取来源
     */
    public java.lang.String getSourceType() {
        return this.sourceType;
    }

    /**
     * 设置来源
     */
    public void setSourceType(java.lang.String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 获取状态1：待发布，2：已发布
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态1：待发布，2：已发布
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取接收目标类型 无、 人、社区
     */
    public java.lang.String getReceiveType() {
        return this.receiveType;
    }

    /**
     * 设置接收目标类型 无、 人、社区
     */
    public void setReceiveType(java.lang.String receiveType) {
        this.receiveType = receiveType;
    }

    /**
     * 获取开始时间
     */
    public java.util.Date getPostBegin() {
        return this.postBegin;
    }

    /**
     * 设置开始时间
     */
    public void setPostBegin(java.util.Date postBegin) {
        this.postBegin = postBegin;
    }

    /**
     * 获取结束时间
     */
    public java.util.Date getPostEnd() {
        return this.postEnd;
    }

    /**
     * 设置结束时间
     */
    public void setPostEnd(java.util.Date postEnd) {
        this.postEnd = postEnd;
    }

    /**
     * 获取小区编码
     */
    public java.lang.String getVillageCode() {
        return this.villageCode;
    }

    /**
     * 设置小区编码
     */
    public void setVillageCode(java.lang.String villageCode) {
        this.villageCode = villageCode;
    }

    /**
     * 获取创建人
     */
    public java.lang.Integer getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置创建人
     */
    public void setCreateUserId(java.lang.Integer createUserId) {
        this.createUserId = createUserId;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getCollect() {
        return collect;
    }

    public void setCollect(Long collect) {
        this.collect = collect;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public boolean isHasBrowse() {
        return hasBrowse;
    }

    public void setHasBrowse(boolean hasBrowse) {
        this.hasBrowse = hasBrowse;
    }

    public boolean isHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(boolean hasCollect) {
        this.hasCollect = hasCollect;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }

}