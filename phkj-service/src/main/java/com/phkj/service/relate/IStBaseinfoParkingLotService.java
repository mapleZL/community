package com.phkj.service.relate;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoParkingLot;

public interface IStBaseinfoParkingLotService {

    /**
     * 根据id取得车位信息
     *
     * @param stBaseinfoParkingLotId
     * @return
     */
    ServiceResult<StBaseinfoParkingLot> getStBaseinfoParkingLotById(Long stBaseinfoParkingLotId);

    /**
     * 保存车位信息
     *
     * @param stBaseinfoParkingLot
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot);

    /**
     * 更新车位信息
     *
     * @param stBaseinfoParkingLot
     * @return
     */
    ServiceResult<Integer> updateStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot);

    /**
     * 根据用户信息获取该用户名下车位
     *
     * @param id
     * @return
     */
    ServiceResult<List<StBaseinfoParkingLot>> getRelatedParkingLot(Long id);

    /**
     * 获取剩余车位
     *
     * @param orgCode
     * @return
     */
    ServiceResult<List<StBaseinfoParkingLot>> getSurplusParkingLot(String orgCode);
}