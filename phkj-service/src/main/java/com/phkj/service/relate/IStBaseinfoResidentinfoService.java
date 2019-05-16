package com.phkj.service.relate;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoResidentinfo;

public interface IStBaseinfoResidentinfoService {

    /**
     * 根据id取得居民信息
     * @param  stBaseinfoResidentinfoId
     * @return
     */
    ServiceResult<StBaseinfoResidentinfo> getStBaseinfoResidentinfoById(Long stBaseinfoResidentinfoId);

    /**
     * 保存居民信息
     * @param  stBaseinfoResidentinfo
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoResidentinfo(StBaseinfoResidentinfo stBaseinfoResidentinfo);

    /**
    * 更新居民信息
    * @param  stBaseinfoResidentinfo
    * @return
    */
    ServiceResult<Integer> updateStBaseinfoResidentinfo(StBaseinfoResidentinfo stBaseinfoResidentinfo);

    /**
     * 获取居民信息
     * @param phone
     * @return
     */
    StBaseinfoResidentinfo getResidentinfo(String phone);
}