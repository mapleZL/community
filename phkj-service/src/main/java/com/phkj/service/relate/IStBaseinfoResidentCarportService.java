package com.phkj.service.relate;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoResidentCarport;

public interface IStBaseinfoResidentCarportService {

    /**
     * 根据id取得居民车位表
     * @param  stBaseinfoResidentCarportId
     * @return
     */
    ServiceResult<StBaseinfoResidentCarport> getStBaseinfoResidentCarportById(Long stBaseinfoResidentCarportId);

    /**
     * 保存居民车位表
     * @param  stBaseinfoResidentCarport
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoResidentCarport(StBaseinfoResidentCarport stBaseinfoResidentCarport);

    /**
    * 更新居民车位表
    * @param  stBaseinfoResidentCarport
    * @return
    */
    ServiceResult<Integer> updateStBaseinfoResidentCarport(StBaseinfoResidentCarport stBaseinfoResidentCarport);

    /**
     * 查询居民拥有的车位列表
     * @param id
     * @return
     */
    ServiceResult<List<StBaseinfoResidentCarport>> getCarportList(Long id);
}