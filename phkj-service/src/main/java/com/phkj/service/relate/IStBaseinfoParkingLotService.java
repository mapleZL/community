package com.phkj.service.relate;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoParkingLot;

public interface IStBaseinfoParkingLotService {

	/**
     * 根据id取得车位信息
     * @param  stBaseinfoParkingLotId
     * @return
     */
    ServiceResult<StBaseinfoParkingLot> getStBaseinfoParkingLotById(Long stBaseinfoParkingLotId);
    
    /**
     * 保存车位信息
     * @param  stBaseinfoParkingLot
     * @return
     */
     ServiceResult<Integer> saveStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot);
     
     /**
     * 更新车位信息
     * @param  stBaseinfoParkingLot
     * @return
     */
     ServiceResult<Integer> updateStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot);
}