package com.phkj.dao.shop.read.repaire;

import com.phkj.entity.repair.StAppletRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Filename: StAppletRepairReadDao.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 */
@Repository
public interface StAppletRepairReadDao {

    StAppletRepair get(Integer id);

    List<StAppletRepair> getStAppletRepairList(@Param("createUserId") String createUserId,
                                               @Param("repairId") String repairId,
                                               @Param("villageCode") String villageCode,
                                               @Param("start") int pageNum,
                                               @Param("size") int pageSize,
                                               @Param("status") Integer status);

    int getRepairCount(@Param("queryMap") Map<String, String> queryMap);

    List<StAppletRepair> getRepairList(@Param("queryMap") Map<String, String> queryMap,
                                       @Param("start") Integer start, @Param("size") Integer size);
}