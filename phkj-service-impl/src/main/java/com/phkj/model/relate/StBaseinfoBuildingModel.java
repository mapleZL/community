package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoBuildingDao;
import com.phkj.entity.relate.StBaseinfoBuilding;

@Component
public class StBaseinfoBuildingModel {

    @Resource
    private StBaseinfoBuildingDao stBaseinfoBuildingDao;

    /**
     * 根据id取得楼幢信息
     * @param  stBaseinfoBuildingId
     * @return
     */
    public StBaseinfoBuilding getStBaseinfoBuildingById(Long stBaseinfoBuildingId) {
        return stBaseinfoBuildingDao.get(stBaseinfoBuildingId);
    }

    /**
     * 保存楼幢信息
     * @param  stBaseinfoBuilding
     * @return
     */
    public Integer saveStBaseinfoBuilding(StBaseinfoBuilding stBaseinfoBuilding) {
        return stBaseinfoBuildingDao.insert(stBaseinfoBuilding);
    }

    /**
    * 更新楼幢信息
    * @param  stBaseinfoBuilding
    * @return
    */
    public Integer updateStBaseinfoBuilding(StBaseinfoBuilding stBaseinfoBuilding) {
        return stBaseinfoBuildingDao.update(stBaseinfoBuilding);
    }

    /**
     * 获取楼幢信息
     * @return
     */
    public List<StBaseinfoBuilding> getBuildings() {
        return stBaseinfoBuildingDao.getBuildings();
    }

}