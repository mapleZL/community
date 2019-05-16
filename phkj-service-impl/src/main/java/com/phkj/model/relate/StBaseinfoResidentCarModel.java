package com.phkj.model.relate;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoResidentCarDao;
import com.phkj.entity.relate.StBaseinfoResidentCar;

@Component
public class StBaseinfoResidentCarModel {

    
    @Resource
    private StBaseinfoResidentCarDao stBaseinfoResidentCarDao;
    
    /**
     * 根据id取得人员车辆登记表
     * @param  stBaseinfoResidentCarId
     * @return
     */
    public StBaseinfoResidentCar getStBaseinfoResidentCarById(Long stBaseinfoResidentCarId) {
    	return stBaseinfoResidentCarDao.get(stBaseinfoResidentCarId);
    }
    
    /**
     * 保存人员车辆登记表
     * @param  stBaseinfoResidentCar
     * @return
     */
     public Integer saveStBaseinfoResidentCar(StBaseinfoResidentCar stBaseinfoResidentCar) {
     	return stBaseinfoResidentCarDao.insert(stBaseinfoResidentCar);
     }
     
     /**
     * 更新人员车辆登记表
     * @param  stBaseinfoResidentCar
     * @return
     */
     public Integer updateStBaseinfoResidentCar(StBaseinfoResidentCar stBaseinfoResidentCar) {
     	return stBaseinfoResidentCarDao.update(stBaseinfoResidentCar);
     }
     
}