package com.phkj.service.property;


import com.phkj.core.ServiceResult;
import com.phkj.entity.property.PropertiItem;
import com.phkj.entity.property.StBaseinfoStaffs;
import com.phkj.entity.system.SystemAdmin;

import java.util.List;

public interface IStBaseinfoStaffsService {

    /**
     * 根据id取得工作人员信息管理表
     *
     * @param stBaseinfoStaffsId
     * @return
     */
    ServiceResult<StBaseinfoStaffs> getStBaseinfoStaffsById(Integer stBaseinfoStaffsId);

    /**
     * 保存工作人员信息管理表
     *
     * @param stBaseinfoStaffs
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoStaffs(StBaseinfoStaffs stBaseinfoStaffs);

    /**
     * 更新工作人员信息管理表
     *
     * @param stBaseinfoStaffs
     * @return
     */
    ServiceResult<Integer> updateStBaseinfoStaffs(StBaseinfoStaffs stBaseinfoStaffs);

    /**
     * 获取值班人员列表
     *
     * @return
     */
    ServiceResult<List<PropertiItem>> getStaffsOnDutyList();

    /**
     * 校验是否是是物业人员
     * @param admin
     * @return
     */
    boolean checkAdminUser(SystemAdmin admin);
}