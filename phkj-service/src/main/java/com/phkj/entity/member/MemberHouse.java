package com.phkj.entity.member;

import java.io.Serializable;
/**
 * 
 *                       
 * @Filename: MemberHouse.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class MemberHouse implements Serializable {
 
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.String region;
    private java.lang.String street;
    private java.lang.String community;
    private java.lang.String village;
    private java.lang.String building;
    private java.lang.String unit;
    private java.lang.String room;
    private java.lang.String identityInformation;
    private java.lang.String name;
    private java.lang.String idNumber;
    private java.lang.String phone;
    private java.util.Date createDate;
    private java.util.Date examineDate;
    private java.lang.Integer examineUserId;
    private java.lang.Integer modifyId;
    private java.lang.String modifyName;
    private java.lang.Integer status;
    private java.lang.String deleted;
    private String searchWord;
    private java.lang.String img;
    public java.lang.Integer getId() {
        return id;
    }
    public void setId(java.lang.Integer id) {
        this.id = id;
    }
    public java.lang.Integer getMemberId() {
        return memberId;
    }
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }
    public java.lang.String getRegion() {
        return region;
    }
    public void setRegion(java.lang.String region) {
        this.region = region;
    }
    public java.lang.String getStreet() {
        return street;
    }
    public void setStreet(java.lang.String street) {
        this.street = street;
    }
    public java.lang.String getCommunity() {
        return community;
    }
    public void setCommunity(java.lang.String community) {
        this.community = community;
    }
    public java.lang.String getVillage() {
        return village;
    }
    public void setVillage(java.lang.String village) {
        this.village = village;
    }
    public java.lang.String getBuilding() {
        return building;
    }
    public void setBuilding(java.lang.String building) {
        this.building = building;
    }
    public java.lang.String getUnit() {
        return unit;
    }
    public void setUnit(java.lang.String unit) {
        this.unit = unit;
    }
    public java.lang.String getRoom() {
        return room;
    }
    public void setRoom(java.lang.String room) {
        this.room = room;
    }
    public java.lang.String getIdentityInformation() {
        return identityInformation;
    }
    public void setIdentityInformation(java.lang.String identityInformation) {
        this.identityInformation = identityInformation;
    }
    public java.lang.String getName() {
        return name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(java.lang.String idNumber) {
        this.idNumber = idNumber;
    }
    public java.lang.String getPhone() {
        return phone;
    }
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }
    public java.util.Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    public java.util.Date getExamineDate() {
        return examineDate;
    }
    public void setExamineDate(java.util.Date examineDate) {
        this.examineDate = examineDate;
    }
    public java.lang.Integer getExamineUserId() {
        return examineUserId;
    }
    public void setExamineUserId(java.lang.Integer examineUserId) {
        this.examineUserId = examineUserId;
    }
    public java.lang.Integer getModifyId() {
        return modifyId;
    }
    public void setModifyId(java.lang.Integer modifyId) {
        this.modifyId = modifyId;
    }
    public java.lang.String getModifyName() {
        return modifyName;
    }
    public void setModifyName(java.lang.String modifyName) {
        this.modifyName = modifyName;
    }
    public java.lang.Integer getStatus() {
        return status;
    }
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }
    public java.lang.String getDeleted() {
        return deleted;
    }
    public void setDeleted(java.lang.String deleted) {
        this.deleted = deleted;
    }
    public java.lang.String getImg() {
        return img;
    }
    public void setImg(java.lang.String img) {
        this.img = img;
    }
    public String getSearchWord() {
        return searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    @Override
    public String toString() {
        return "MemberHouse [id=" + id + ", memberId=" + memberId + ", region=" + region
               + ", street=" + street + ", community=" + community + ", village=" + village
               + ", building=" + building + ", unit=" + unit + ", room=" + room
               + ", identityInformation=" + identityInformation + ", name=" + name + ", idNumber="
               + idNumber + ", phone=" + phone + ", createDate=" + createDate + ", examineDate="
               + examineDate + ", examineUserId=" + examineUserId + ", modifyId=" + modifyId
               + ", modifyName=" + modifyName + ", status=" + status + ", deleted=" + deleted
               + ", searchWord=" + searchWord + ", img=" + img + "]";
    }
    
 }