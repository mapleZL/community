package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoPersonStock;

@Repository
public interface StBaseinfoPersonStockDao {

    StBaseinfoPersonStock get(java.lang.Long id);

    Integer insert(StBaseinfoPersonStock stBaseinfoPersonStock);

    Integer update(StBaseinfoPersonStock stBaseinfoPersonStock);

    StBaseinfoPersonStock getStBaseinfoPersonStock(@Param("phone") String phone);

    StBaseinfoPersonStock getStBaseinfoPerson(@Param("ids") List<Long> personStockIds,
                                              @Param("name") String name);
}