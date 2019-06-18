package com.phkj.service.relate;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoResidentHouse;

public interface IStBaseinfoResidentHouseService {

    /**
     * 根据id取得居民房屋关系表
     * @param  stBaseinfoResidentHouseId
     * @return
     */
    ServiceResult<StBaseinfoResidentHouse> getStBaseinfoResidentHouseById(Long stBaseinfoResidentHouseId);

    /**
     * 保存居民房屋关系表
     * @param  stBaseinfoResidentHouse
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoResidentHouse(StBaseinfoResidentHouse stBaseinfoResidentHouse);

    /**
    * 更新居民房屋关系表
    * @param  stBaseinfoResidentHouse
    * @return
    */
    ServiceResult<Integer> updateStBaseinfoResidentHouse(StBaseinfoResidentHouse stBaseinfoResidentHouse);

    /**
     * 按条件查询房屋信息
     * @param queryMap
     * @return
     */
    List<StBaseinfoResidentHouse> getResidentBouseByParam(@Param("queryMap")Map<String, Object> queryMap);
}