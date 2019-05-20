package com.phkj.entity.repair;

import java.io.Serializable;

/**
 * 
 *
 */
public class StAppletComment implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private Long              id;
    private Long              rId;
    private String            rType;
    private Integer           parentId;
    private Long              createUserId;
    private String            createUserName;
    private String            content;
    private java.util.Date    createTime;
    private Integer           sts;

    /**
     * 获取id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取关联id
     */
    public Long getRId() {
        return this.rId;
    }

    /**
     * 设置关联id
     */
    public void setRId(Long rId) {
        this.rId = rId;
    }

    /**
     * 获取评论用户
     */
    public Long getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置评论用户
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建人
     */
    public String getCreateUserName() {
        return this.createUserName;
    }

    /**
     * 设置创建人
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取评论内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置评论时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取状态0表示删除，1表示未删除
     */
    public Integer getSts() {
        return this.sts;
    }

    /**
     * 设置状态0表示删除，1表示未删除
     */
    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }
    
    
}