package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoHousesDao;
import com.phkj.entity.relate.StBaseinfoHouses;

@Component
public class StBaseinfoHousesModel {

    @Resource
    private StBaseinfoHousesDao stBaseinfoHousesDao;

    /**
     * 根据id取得房屋信息
     * @param  stBaseinfoHousesId
     * @return
     */
    public StBaseinfoHouses getStBaseinfoHousesById(Long stBaseinfoHousesId) {
        return stBaseinfoHousesDao.get(stBaseinfoHousesId);
    }

    /**
     * 保存房屋信息
     * @param  stBaseinfoHouses
     * @return
     */
    public Integer saveStBaseinfoHouses(StBaseinfoHouses stBaseinfoHouses) {
        return stBaseinfoHousesDao.insert(stBaseinfoHouses);
    }

    /**
    * 更新房屋信息
    * @param  stBaseinfoHouses
    * @return
    */
    public Integer updateStBaseinfoHouses(StBaseinfoHouses stBaseinfoHouses) {
        return stBaseinfoHousesDao.update(stBaseinfoHouses);
    }

    /**
     * 获取全部房屋信息
     * @return
     */
    public List<StBaseinfoHouses> getHouse() {
        return stBaseinfoHousesDao.getHouse();
    }

}