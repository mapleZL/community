package com.phkj.entity.relate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StNoticeBulletinReleaseManage implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private Long              id;
    private String            title;
    private String            content;
    private String            type;
    private String            subType;
    private String            sourceType;
    private Integer           lvl;
    private String            status;
    private String            receiveType;
    private Date              postBegin;
    private Date              postEnd;
    private String            orgCode;
    private String            topOrgCode;
    private Long              createUserId;
    private Date              createTime;
    private Long              modifyUserId;
    private Date              modifyTime;
    private String            sts;
    // 流量
    private Long              rate = 0L;
    // 收藏人数
    private Long              collect = 0L;
    // 评论人数
    private Long              comment = 0L;
    // 头条对应的图片路径
    private List<String>      img;
    // 信息来源
    private String            sourceName;
    // 是否收藏过
    private boolean           hasCollect = false;
    // 是否已评论
    private boolean           hasComment = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public Date getPostBegin() {
        return postBegin;
    }

    public void setPostBegin(Date postBegin) {
        this.postBegin = postBegin;
    }

    public Date getPostEnd() {
        return postEnd;
    }

    public void setPostEnd(Date postEnd) {
        this.postEnd = postEnd;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTopOrgCode() {
        return topOrgCode;
    }

    public void setTopOrgCode(String topOrgCode) {
        this.topOrgCode = topOrgCode;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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
