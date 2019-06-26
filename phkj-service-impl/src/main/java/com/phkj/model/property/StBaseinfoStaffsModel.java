package com.phkj.model.property;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.StringUtil;
import com.phkj.dao.shopm.read.relate.StBaseinfoStaffsDao;
import com.phkj.entity.property.StBaseinfoStaffs;

@Component
public class StBaseinfoStaffsModel {

    @Resource
    private StBaseinfoStaffsDao stBaseinfoStaffsDao;

    /**
     * 根据id取得工作人员信息管理表
     *
     * @param stBaseinfoStaffsId
     * @return
     */
    public StBaseinfoStaffs getStBaseinfoStaffsById(Integer stBaseinfoStaffsId) {
        return stBaseinfoStaffsDao.get(stBaseinfoStaffsId);
    }

    /**
     * 保存工作人员信息管理表
     *
     * @param stBaseinfoStaffs
     * @return
     */
    public Integer saveStBaseinfoStaffs(StBaseinfoStaffs stBaseinfoStaffs) {
        this.dbConstrains(stBaseinfoStaffs);
        return stBaseinfoStaffsDao.insert(stBaseinfoStaffs);
    }

    /**
     * 更新工作人员信息管理表
     *
     * @param stBaseinfoStaffs
     * @return
     */
    public Integer updateStBaseinfoStaffs(StBaseinfoStaffs stBaseinfoStaffs) {
        this.dbConstrains(stBaseinfoStaffs);
        return stBaseinfoStaffsDao.update(stBaseinfoStaffs);
    }

    private void dbConstrains(StBaseinfoStaffs stBaseinfoStaffs) {
        stBaseinfoStaffs.setName(StringUtil.dbSafeString(stBaseinfoStaffs.getName(), true, 50));
        stBaseinfoStaffs.setSex(StringUtil.dbSafeString(stBaseinfoStaffs.getSex(), true, 2));
        stBaseinfoStaffs.setIdNumber(StringUtil.dbSafeString(stBaseinfoStaffs.getIdNumber(), true, 20));
        stBaseinfoStaffs.setIdAddress(StringUtil.dbSafeString(stBaseinfoStaffs.getIdAddress(), true, 50));
        stBaseinfoStaffs.setNation(StringUtil.dbSafeString(stBaseinfoStaffs.getNation(), true, 10));
        stBaseinfoStaffs.setAgeSegment(StringUtil.dbSafeString(stBaseinfoStaffs.getAgeSegment(), true, 10));
        stBaseinfoStaffs.setEver(StringUtil.dbSafeString(stBaseinfoStaffs.getEver(), true, 10));
        stBaseinfoStaffs.setPoliticalStatus(StringUtil.dbSafeString(stBaseinfoStaffs.getPoliticalStatus(), true, 10));
        stBaseinfoStaffs.setDegree(StringUtil.dbSafeString(stBaseinfoStaffs.getDegree(), true, 10));
        stBaseinfoStaffs.setMaritalStatus(StringUtil.dbSafeString(stBaseinfoStaffs.getMaritalStatus(), true, 10));
        stBaseinfoStaffs.setCarNo(StringUtil.dbSafeString(stBaseinfoStaffs.getCarNo(), true, 20));
        stBaseinfoStaffs.setMobile(StringUtil.dbSafeString(stBaseinfoStaffs.getMobile(), true, 20));
        stBaseinfoStaffs.setContactor(StringUtil.dbSafeString(stBaseinfoStaffs.getContactor(), true, 10));
        stBaseinfoStaffs.setContactorMobile(StringUtil.dbSafeString(stBaseinfoStaffs.getContactorMobile(), true, 20));
        stBaseinfoStaffs.setResidence(StringUtil.dbSafeString(stBaseinfoStaffs.getResidence(), true, 50));
        stBaseinfoStaffs.setRemark(StringUtil.dbSafeString(stBaseinfoStaffs.getRemark(), true, 50));
        stBaseinfoStaffs.setStaffType(StringUtil.dbSafeString(stBaseinfoStaffs.getStaffType(), true, 20));
        stBaseinfoStaffs.setSts(StringUtil.dbSafeString(stBaseinfoStaffs.getSts(), true, 1));
        stBaseinfoStaffs.setEncryptionIdNumber(StringUtil.dbSafeString(stBaseinfoStaffs.getEncryptionIdNumber(), true, 100));
        stBaseinfoStaffs.setOrgCode(StringUtil.dbSafeString(stBaseinfoStaffs.getOrgCode(), true, 50));
        stBaseinfoStaffs.setTopOrgCode(StringUtil.dbSafeString(stBaseinfoStaffs.getTopOrgCode(), true, 50));
    }

    public List<StBaseinfoStaffs> getStaffsOnDutyList() {
        return stBaseinfoStaffsDao.getStaffsOnDutyList();
    }

    /**
     * create by: zl
     * description: 根据jobs_id获取数据
     * create time:
     *
     * @return
     * @Param: list
     */
    public List<StBaseinfoStaffs> getStaffsByJobsId(List<Long> list) {
        return stBaseinfoStaffsDao.getStaffsByJobsId(list);
    }

    public int getStaffsByParam(Map<String, Object> param) {
        return stBaseinfoStaffsDao.getStaffsByParam(param);
    }
}