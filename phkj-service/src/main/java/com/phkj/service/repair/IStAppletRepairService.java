package com.phkj.service.repair;


import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.repair.StAppletRepair;

import java.util.List;
import java.util.Map;

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
    ServiceResult<List<StAppletRepair>> getStAppletRepairList(String createUserId, String repairId, String villageCode, int pageNum, int pageSize, Integer status);

    /**
     * 后台查询维修记录列表
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<StAppletRepair>> page(Map<String, String> queryMap, PagerInfo pager);
}