package com.phkj.service.repair;


import com.phkj.core.ServiceResult;
import com.phkj.entity.repair.StAppletRepair;

import java.util.List;

public interface IStAppletRepairService {

    /**
     * 根据id取得st_applet_repair对象
     *
     * @param stAppletRepairId
     * @return
     */
    ServiceResult<StAppletRepair> getStAppletRepairById(Integer stAppletRepairId);

    /**
     * 保存st_applet_repair对象
     *
     * @param stAppletRepair
     * @return
     */
    ServiceResult<Integer> saveStAppletRepair(StAppletRepair stAppletRepair);

    /**
     * 更新st_applet_repair对象
     *
     * @param stAppletRepair
     * @return
     */
    ServiceResult<Integer> updateStAppletRepair(StAppletRepair stAppletRepair);

    /**
     * 查询报修记录列表
     *
     * @param createUserId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServiceResult<List<StAppletRepair>> getStAppletRepairList(String createUserId, int pageNum, int pageSize);
}