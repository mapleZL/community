package com.phkj.entity.repair;

import java.io.Serializable;

/**
 * @author ：zl
 * @date ：Created in 2019/5/10 16:33
 * @description：参数
 * @modified By：
 * @version: 0.0.1$
 */
public class StAppletCommentParam implements Serializable {
    private Long rId;
    private Long createUserId;
    private String createUserName;
    private String content;

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
