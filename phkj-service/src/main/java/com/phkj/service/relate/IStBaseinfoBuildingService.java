package com.phkj.service.relate;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoBuilding;

public interface IStBaseinfoBuildingService {

	/**
     * 根据id取得楼幢信息
     * @param  stBaseinfoBuildingId
     * @return
     */
    ServiceResult<StBaseinfoBuilding> getStBaseinfoBuildingById(Long stBaseinfoBuildingId);
    
    /**
     * 保存楼幢信息
     * @param  stBaseinfoBuilding
     * @return
     */
     ServiceResult<Integer> saveStBaseinfoBuilding(StBaseinfoBuilding stBaseinfoBuilding);
     
     /**
     * 更新楼幢信息
     * @param  stBaseinfoBuilding
     * @return
     */
     ServiceResult<Integer> updateStBaseinfoBuilding(StBaseinfoBuilding stBaseinfoBuilding);
}