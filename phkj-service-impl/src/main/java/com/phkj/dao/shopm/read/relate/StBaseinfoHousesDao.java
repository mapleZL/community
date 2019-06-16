package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoHouses;

@Repository
public interface StBaseinfoHousesDao {

    StBaseinfoHouses get(java.lang.Long id);

    Integer insert(StBaseinfoHouses stBaseinfoHouses);

    Integer update(StBaseinfoHouses stBaseinfoHouses);

    List<StBaseinfoHouses> getHouse();

    List<StBaseinfoHouses> getHouseSpriner(@Param("buildingId") Integer buildingId,
                                           @Param("unitId") String unitId);
}