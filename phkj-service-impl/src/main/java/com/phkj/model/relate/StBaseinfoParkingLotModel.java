package com.phkj.model.relate;

import java.util.List;
import java.util.Map;

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
     *
     * @param stBaseinfoParkingLotId
     * @return
     */
    public StBaseinfoParkingLot getStBaseinfoParkingLotById(Long stBaseinfoParkingLotId) {
        return stBaseinfoParkingLotDao.get(stBaseinfoParkingLotId);
    }

    /**
     * 保存车位信息
     *
     * @param stBaseinfoParkingLot
     * @return
     */
    public Integer saveStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
        return stBaseinfoParkingLotDao.insert(stBaseinfoParkingLot);
    }

    /**
     * 更新车位信息
     *
     * @param stBaseinfoParkingLot
     * @return
     */
    public Integer updateStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
        return stBaseinfoParkingLotDao.update(stBaseinfoParkingLot);
    }

    /**
     * 获取车位信息
     *
     * @param residentinfoId
     * @return
     */
    public List<StBaseinfoParkingLot> getRelatedParkingLot(Long residentinfoId) {
        return stBaseinfoParkingLotDao.getRelatedParkingLot(residentinfoId);
    }

    /**
     * 获取剩余车位
     *
     * @param orgCode
     * @return
     */
    public List<StBaseinfoParkingLot> getSurplusParkingLot(String orgCode) {
        List<StBaseinfoParkingLot> surplusParkingLot = stBaseinfoParkingLotDao.getSurplusParkingLot(orgCode);
        return surplusParkingLot;
    }

    /**
     * 获取剩余车位
     *
     * @param orgCode
     * @return
     */
    public List<Map<String, Object>> getNearbyParkingLot(String orgCode) {
        List<Map<String, Object>> nearbyParkingLot = stBaseinfoParkingLotDao.getNearbyParkingLot(orgCode);
        return nearbyParkingLot;
    }

    public int getSurplusParkingLotNum(String orgCode) {
        int num = stBaseinfoParkingLotDao.getNearbyParkingLotNum(orgCode);
        return num;
    }


    /**
     * @param orgCode
     * @param userId
     * @return
     */
    public List<StBaseinfoParkingLot> getSurplusParkingLotAndMeParking(String orgCode, String userId) {
        List<StBaseinfoParkingLot> surplusParkingLot = stBaseinfoParkingLotDao.getSurplusParkingLotAndMeParking
                (orgCode, userId);
        return surplusParkingLot;
    }
}