package com.phkj.entity.property;

import java.io.Serializable;

/**
 * @author ：zl
 * @date ：Created in 2019/5/23 19:37
 * @description：物业人员信息
 * @modified By：
 * @version: 0.0.1$
 */
public class PropertiItem implements Serializable {

    private String name;
    private String leaderName;
    private String department;
    private String phone;
    private String leaderPhone;
    private Long jobsId;
    private Long topId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public Long getJobsId() {
        return jobsId;
    }

    public void setJobsId(Long jobsId) {
        this.jobsId = jobsId;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    @Override
    public String toString() {
        return "PropertiItem{" +
                "name='" + name + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", leaderPhone='" + leaderPhone + '\'' +
                ", jobsId=" + jobsId +
                ", topId=" + topId +
                '}';
    }
}
