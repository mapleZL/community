package com.phkj.service.relate;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoPersonStock;

public interface IStBaseinfoPersonStockService {

    /**
     * 根据id取得人员库
     * @param  stBaseinfoPersonStockId
     * @return
     */
    ServiceResult<StBaseinfoPersonStock> getStBaseinfoPersonStockById(Long stBaseinfoPersonStockId);

    /**
     * 保存人员库
     * @param  stBaseinfoPersonStock
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoPersonStock(StBaseinfoPersonStock stBaseinfoPersonStock);

    /**
    * 更新人员库
    * @param  stBaseinfoPersonStock
    * @return
    */
    ServiceResult<Integer> updateStBaseinfoPersonStock(StBaseinfoPersonStock stBaseinfoPersonStock);

    /**
     * 获取人员信息
     * @param phone
     * @return
     */
    StBaseinfoPersonStock getStBaseinfoPersonStock(String phone);

    
    StBaseinfoPersonStock getStBaseinfoPerson(List<Long> personStockIds, String name);
}