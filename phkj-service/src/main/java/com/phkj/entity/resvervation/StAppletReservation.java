package com.phkj.entity.resvervation;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StAppletReservation implements Serializable {
    private Long id;

    /**
     * 预约类型
     */
    private String type;

    /**
     * 房屋名称
     */
    private String house;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 预约人
     */
    private String createUserName;

    /**
     * 联系人
     */
    private String telephone;

    /**
     * 预约开始时间
     */
    private Date startTime;

    /**
     * 预约结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String content;

    /**
     * 审核人id
     */
    private String examineId;

    /**
     * 审核人姓名
     */
    private String examineName;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 指派人
     */
    private String repairId;

    /**
     * 指派人姓名
     */
    private String repairName;

    /**
     * 状态 0删除 1正常
     */
    private String sts;

    /**
     * 任务类型 0待处理 2处理中 3.维修中 4处理完成 5 审核不通过
     */
    private String status;

    private Date createTime;

    private String villageCode;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExamineId() {
        return examineId;
    }

    public void setExamineId(String examineId) {
        this.examineId = examineId;
    }

    public String getExamineName() {
        return examineName;
    }

    public void setExamineName(String examineName) {
        this.examineName = examineName;
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

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}