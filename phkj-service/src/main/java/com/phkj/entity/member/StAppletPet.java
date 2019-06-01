package com.phkj.entity.member;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class StAppletPet implements Serializable {
    private Long id;

    /**
     * 饲养人
     */
    private String raiser;

    /**
     * 宠物品种
     */
    private String petBreed;

    /**
     * 宠物名称
     */
    private String petName;

    /**
     * 宠物性别
     */
    private String gender;

    /**
     * 是否绝育
     */
    private String sterilization;

    /**
     * 体重
     */
    private String weight;

    /**
     * 住址
     */
    private String address;

    /**
     * 免检编号
     */
    private String exemptionNum;

    /**
     * 免检时间
     */
    private Date exemptionTime;

    /**
     * 疫苗种类
     */
    private String vaccineType;

    /**
     * 养犬登记编码
     */
    private String petRegisNum;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 创建人Id
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 发布状态:0.认证中,1.认证成功,2.拒绝认证,3.
     */
    private String sts;

    /**
     * 失败信息
     */
    private String msg;

    private String villageCode;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaiser() {
        return raiser;
    }

    public void setRaiser(String raiser) {
        this.raiser = raiser;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSterilization() {
        return sterilization;
    }

    public void setSterilization(String sterilization) {
        this.sterilization = sterilization;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExemptionNum() {
        return exemptionNum;
    }

    public void setExemptionNum(String exemptionNum) {
        this.exemptionNum = exemptionNum;
    }

    public Date getExemptionTime() {
        return exemptionTime;
    }

    public void setExemptionTime(Date exemptionTime) {
        this.exemptionTime = exemptionTime;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getPetRegisNum() {
        return petRegisNum;
    }

    public void setPetRegisNum(String petRegisNum) {
        this.petRegisNum = petRegisNum;
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}