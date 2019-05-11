package com.phkj.dao.index.read.repair;


import com.phkj.entity.repair.StAppletRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StAppletRepairDao {
 
	StAppletRepair get(Integer id);

	Integer insert(StAppletRepair stAppletRepair);
	
	Integer update(StAppletRepair stAppletRepair);

    List<StAppletRepair> getStAppletRepairList(@Param("createUserId")String createUserId, @Param("start") int pageNum, @Param("size") int pageSize);

    int getRepairCount(@Param("queryMap")Map<String, String> queryMap);

    List<StAppletRepair> getRepairList(@Param("queryMap")Map<String, String> queryMap, @Param("start")Integer start,
                                             @Param("size")Integer size);
}