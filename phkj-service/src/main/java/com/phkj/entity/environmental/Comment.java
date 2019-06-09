package com.phkj.entity.environmental;

public class Comment {

    private String id;

    private String parentId;

    private String createUserId;

    private String createUserName;

    private String content;

    private String villageCode ;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public Comment() {
    }

    public Comment(String id, String parentId, String createUserId, String createUserName, String content) {
        this.id = id;
        this.parentId = parentId;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
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
