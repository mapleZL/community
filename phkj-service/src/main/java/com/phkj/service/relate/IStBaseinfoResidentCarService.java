package com.phkj.service.relate;

import java.util.List;

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

    /**
     * 根据人员id获取人员下所有车辆
     * @param id
     * @return
     */
    ServiceResult<List<StBaseinfoResidentCar>> getAllCarByOwner(Long id);
}