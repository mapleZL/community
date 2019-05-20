package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoParkingLotDao;
import com.phkj.entity.relate.StBaseinfoParkingLot;

@Component
public class StBaseinfoParkingLotModel {

    @Resource
    private StBaseinfoParkingLotDao stBaseinfoParkingLotDao;

    /**
     * 根据id取得车位信息
     * @param  stBaseinfoParkingLotId
     * @return
     */
    public StBaseinfoParkingLot getStBaseinfoParkingLotById(Long stBaseinfoParkingLotId) {
        return stBaseinfoParkingLotDao.get(stBaseinfoParkingLotId);
    }

    /**
     * 保存车位信息
     * @param  stBaseinfoParkingLot
     * @return
     */
    public Integer saveStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
        return stBaseinfoParkingLotDao.insert(stBaseinfoParkingLot);
    }

    /**
    * 更新车位信息
    * @param  stBaseinfoParkingLot
    * @return
    */
    public Integer updateStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
        return stBaseinfoParkingLotDao.update(stBaseinfoParkingLot);
    }

    /**
     * 获取车位信息
     * @param residentinfoId
     * @return
     */
    public List<StBaseinfoParkingLot> getRelatedParkingLot(Long residentinfoId) {
        return stBaseinfoParkingLotDao.getRelatedParkingLot(residentinfoId);
    }
}