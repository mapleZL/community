package com.phkj.entity.complaint;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StAppletComSugges implements Serializable {
    private Long id;

    /**
     * 标题
     */
    private String titel;

    /**
     * 投诉对象
     */
    private String complaintTarget;

    /**
     * 投诉内容
     */
    private String content;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String sts;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createName;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    private static final long serialVersionUID = 1L;

    public StAppletComSugges() {
    }

    public StAppletComSugges(Long id, String titel, String complaintTarget, String content, String email, String type, String sts, Date createTime, String createUserId, String createName, String modifyUserId, String modifyUserName, Date modifyTime) {
        this.id = id;
        this.titel = titel;
        this.complaintTarget = complaintTarget;
        this.content = content;
        this.email = email;
        this.type = type;
        this.sts = sts;
        this.createTime = createTime;
        this.createUserId = createUserId;
        this.createName = createName;
        this.modifyUserId = modifyUserId;
        this.modifyUserName = modifyUserName;
        this.modifyTime = modifyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getComplaintTarget() {
        return complaintTarget;
    }

    public void setComplaintTarget(String complaintTarget) {
        this.complaintTarget = complaintTarget;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}