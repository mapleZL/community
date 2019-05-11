package com.phkj.dao.index.read.repair;


import com.phkj.entity.repair.StAppletRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletRepairDao {
 
	StAppletRepair get(Integer id);

	Integer insert(StAppletRepair stAppletRepair);
	
	Integer update(StAppletRepair stAppletRepair);

    List<StAppletRepair> getStAppletRepairList(@Param("createUserId")String createUserId, @Param("start") int pageNum, @Param("size") int pageSize);
}