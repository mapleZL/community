package com.phkj.entity.share;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StAppletShareInfo implements Serializable {
    private Long id;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 发布人类型 . 1居民 2.物业/社区
     */
    private String shareType;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类
     */
    private String taskType;

    /**
     * 门禁 0无 1.有
     */
    private String doorLock;

    /**
     * 内容描述
     */
    private String content;

    /**
     * 共享开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 共享结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 车位锁 0.无 1.有
     */
    private String carLock;

    /**
     * 价钱
     */
    private String price;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 出发地
     */
    private String departPlace;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 性别
     */
    private String gender;

    /**
     * 职业
     */
    private String profession;

    /**
     * 技能
     */
    private String skill;

    /**
     * 状态
     */
    private String sts;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 修改人id
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 申请状态 0.未申请 1.已申请
     */
    private String shareStatus;

    /**
     * 预约数量
     */
    private int apply_num;

    public int getApply_num() {
        return apply_num;
    }

    public void setApply_num(int apply_num) {
        this.apply_num = apply_num;
    }

    /**
     * 图片路径
     */
    private String imgUrl;

    private static final long serialVersionUID = 1L;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDoorLock() {
        return doorLock;
    }

    public void setDoorLock(String doorLock) {
        this.doorLock = doorLock;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCarLock() {
        return carLock;
    }

    public void setCarLock(String carLock) {
        this.carLock = carLock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDepartPlace() {
        return departPlace;
    }

    public void setDepartPlace(String departPlace) {
        this.departPlace = departPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    public String getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(String shareStatus) {
        this.shareStatus = shareStatus;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}