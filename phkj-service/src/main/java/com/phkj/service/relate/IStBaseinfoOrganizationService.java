package com.phkj.service.relate;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StBaseinfoOrganization;

public interface IStBaseinfoOrganizationService {

    /**
     * 根据id取得街道小区组织表
     * @param  stBaseinfoOrganizationId
     * @return
     */
    ServiceResult<StBaseinfoOrganization> getStBaseinfoOrganizationById(Long stBaseinfoOrganizationId);

    /**
     * 保存街道小区组织表
     * @param  stBaseinfoOrganization
     * @return
     */
    ServiceResult<Integer> saveStBaseinfoOrganization(StBaseinfoOrganization stBaseinfoOrganization);

    /**
    * 更新街道小区组织表
    * @param  stBaseinfoOrganization
    * @return
    */
    ServiceResult<Integer> updateStBaseinfoOrganization(StBaseinfoOrganization stBaseinfoOrganization);

    /**
     * 查询所有小区列表
     * @param string
     * @return
     */
    List<StBaseinfoOrganization> getOrganizationByRegion(String string);

    /**
     * 查询小区上级社区和街道code
     * @param villageCode
     * @return
     */
    List<String> getRelationOrgations(String villageCode);
}