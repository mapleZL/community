package com.phkj.service.relate;

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
}