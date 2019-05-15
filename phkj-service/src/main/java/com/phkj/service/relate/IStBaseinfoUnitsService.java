package com.phkj.service.relate;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoUnits;

public interface IStBaseinfoUnitsService {

	/**
     * 根据id取得楼幢单元信息
     * @param  stBaseinfoUnitsId
     * @return
     */
    ServiceResult<StBaseinfoUnits> getStBaseinfoUnitsById(Long stBaseinfoUnitsId);
    
    /**
     * 保存楼幢单元信息
     * @param  stBaseinfoUnits
     * @return
     */
     ServiceResult<Integer> saveStBaseinfoUnits(StBaseinfoUnits stBaseinfoUnits);
     
     /**
     * 更新楼幢单元信息
     * @param  stBaseinfoUnits
     * @return
     */
     ServiceResult<Integer> updateStBaseinfoUnits(StBaseinfoUnits stBaseinfoUnits);
}