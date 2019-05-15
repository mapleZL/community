package com.phkj.service.relate;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoHouses;

public interface IStBaseinfoHousesService {

	/**
     * 根据id取得房屋信息
     * @param  stBaseinfoHousesId
     * @return
     */
    ServiceResult<StBaseinfoHouses> getStBaseinfoHousesById(Long stBaseinfoHousesId);
    
    /**
     * 保存房屋信息
     * @param  stBaseinfoHouses
     * @return
     */
     ServiceResult<Integer> saveStBaseinfoHouses(StBaseinfoHouses stBaseinfoHouses);
     
     /**
     * 更新房屋信息
     * @param  stBaseinfoHouses
     * @return
     */
     ServiceResult<Integer> updateStBaseinfoHouses(StBaseinfoHouses stBaseinfoHouses);
}