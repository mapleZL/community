package com.phkj.model.relate;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoResidentHouseDao;
import com.phkj.entity.relate.StBaseinfoResidentHouse;

@Component
public class StBaseinfoResidentHouseModel {

    @Resource
    private StBaseinfoResidentHouseDao stBaseinfoResidentHouseDao;

    /**
     * 根据id取得居民房屋关系表
     * @param  stBaseinfoResidentHouseId
     * @return
     */
    public StBaseinfoResidentHouse getStBaseinfoResidentHouseById(Long stBaseinfoResidentHouseId) {
        return stBaseinfoResidentHouseDao.get(stBaseinfoResidentHouseId);
    }

    /**
     * 保存居民房屋关系表
     * @param  stBaseinfoResidentHouse
     * @return
     */
    public Integer saveStBaseinfoResidentHouse(StBaseinfoResidentHouse stBaseinfoResidentHouse) {
        return stBaseinfoResidentHouseDao.insert(stBaseinfoResidentHouse);
    }

    /**
    * 更新居民房屋关系表
    * @param  stBaseinfoResidentHouse
    * @return
    */
    public Integer updateStBaseinfoResidentHouse(StBaseinfoResidentHouse stBaseinfoResidentHouse) {
        return stBaseinfoResidentHouseDao.update(stBaseinfoResidentHouse);
    }

    public StBaseinfoResidentHouse getResidentBouseByParam(Map<String, Object> queryMap) {
        return stBaseinfoResidentHouseDao.getResidentBouseByParam(queryMap);
    }

}