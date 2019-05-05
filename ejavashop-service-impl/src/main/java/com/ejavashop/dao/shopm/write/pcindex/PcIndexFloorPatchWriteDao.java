package com.ejavashop.dao.shopm.write.pcindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorPatch;

@Repository
public interface PcIndexFloorPatchWriteDao {

    //PcIndexFloorPatch get(java.lang.Integer id);

    Integer insert(PcIndexFloorPatch pcIndexFloorPatch);

    Integer update(PcIndexFloorPatch pcIndexFloorPatch);

    Integer delete(Integer id);
    
  
	//void sortAndInsert(@Param("id")String id, @Param("sort")String sort, @Param("floorCode")String floorCode);
}