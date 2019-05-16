package com.phkj.service.relate;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoResidentCar;

public interface IStBaseinfoResidentCarService {

	/**
     * 根据id取得人员车辆登记表
     * @param  stBaseinfoResidentCarId
     * @return
     */
    ServiceResult<StBaseinfoResidentCar> getStBaseinfoResidentCarById(Long stBaseinfoResidentCarId);
    
    /**
     * 保存人员车辆登记表
     * @param  stBaseinfoResidentCar
     * @return
     */
     ServiceResult<Integer> saveStBaseinfoResidentCar(StBaseinfoResidentCar stBaseinfoResidentCar);
     
     /**
     * 更新人员车辆登记表
     * @param  stBaseinfoResidentCar
     * @return
     */
     ServiceResult<Integer> updateStBaseinfoResidentCar(StBaseinfoResidentCar stBaseinfoResidentCar);
}