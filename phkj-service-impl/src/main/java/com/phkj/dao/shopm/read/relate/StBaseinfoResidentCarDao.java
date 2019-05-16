package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoResidentCar;

@Repository
public interface StBaseinfoResidentCarDao {

    StBaseinfoResidentCar get(java.lang.Long id);

    Integer insert(StBaseinfoResidentCar stBaseinfoResidentCar);

    Integer update(StBaseinfoResidentCar stBaseinfoResidentCar);

    List<StBaseinfoResidentCar> getAllCarByOwner(@Param("personStockId") Long personStockId);
}