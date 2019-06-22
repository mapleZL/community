package com.phkj.entity.visit;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StAppletVisitor implements Serializable {
    private Long id;

    /**
     * 有效时间
     */
    private Date overTime;

    /**
     * 有效次数
     */
    private Long overNum;

    /**
     * 房屋号码
     */
    private String houseId;

    /**
     * 房屋名称
     */
    private String houseName;

    /**
     * 访问人姓名
     */
    private String visitorName;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 性别 1.男 0 女
     */
    private String gender;

    /**
     * 访客类型 1.亲戚 2.子女 3.朋友 4.其他
     */
    private String visitorType;

    /**
     * 预约时间
     */
    private Date applyTime;

    /**
     * 密码类型 1 二维码 2密码
     */
    private String passwordType;

    /**
     * 密码
     */
    private String password;

    /**
     * url
     */
    private String imgurl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人userId
     */
    private String createUserId;

    private String createUserName;

    private String modifyUserId;

    private String modifyUserName;

    private Date modifyTime;

    /**
     * 状态 0.删除 1.正常 2.失效
     */
    private String sts;

    /**
     * 社区编码
     */
    private String villageCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Long getOverNum() {
        return overNum;
    }

    public void setOverNum(Long overNum) {
        this.overNum = overNum;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
}