package com.phkj.service.relate;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.entity.relate.StBaseinfoParkingLotOrder;

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
     * @param userId
     * @return
     */
    ServiceResult<List<StBaseinfoParkingLot>> getSurplusParkingLot(String orgCode, String userId);

    /**
     * 获取剩余车位
     *
     * @param orgCode
     * @return
     */
    ServiceResult<List<Map<String, Object>>> getNearbyParkingLot(String orgCode);


    /**
     * 获取当前小区车位
     *
     * @param orgCode
     * @return
     */
    ServiceResult<Integer> getSurplusParkingLotNum(String orgCode);

    /**
     * @param parkingLotOrder
     * @return
     */
    boolean applyParkingLot(StBaseinfoParkingLotOrder parkingLotOrder);


    /**
     * @param parkingLot
     * @return
     */
    String applyParking(StBaseinfoParkingLotOrder parkingLot);

    /**
     * @param villageCode
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    PageInfo<Map<String, Object>> systemParkingLot(String villageCode, String userId, String page, String rows);

    /**
     * @param parkingLotOrder
     * @return
     */
    boolean systemApply(StBaseinfoParkingLotOrder parkingLotOrder);
}