package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoOrganizationDao;
import com.phkj.entity.relate.StBaseinfoOrganization;

@Component
public class StBaseinfoOrganizationModel {

    @Resource
    private StBaseinfoOrganizationDao stBaseinfoOrganizationDao;
    
    /**
     * 根据id取得街道小区组织表
     * @param  stBaseinfoOrganizationId
     * @return
     */
    public StBaseinfoOrganization getStBaseinfoOrganizationById(Long stBaseinfoOrganizationId) {
    	return stBaseinfoOrganizationDao.get(stBaseinfoOrganizationId);
    }
    
    /**
     * 保存街道小区组织表
     * @param  stBaseinfoOrganization
     * @return
     */
     public Integer saveStBaseinfoOrganization(StBaseinfoOrganization stBaseinfoOrganization) {
     	return stBaseinfoOrganizationDao.insert(stBaseinfoOrganization);
     }
     
     /**
     * 更新街道小区组织表
     * @param  stBaseinfoOrganization
     * @return
     */
     public Integer updateStBaseinfoOrganization(StBaseinfoOrganization stBaseinfoOrganization) {
     	return stBaseinfoOrganizationDao.update(stBaseinfoOrganization);
     }

     /**
      * 获取全部
      * @return
      */
    public List<StBaseinfoOrganization> getOranizations() {
        return stBaseinfoOrganizationDao.getOranizations();
    }

    /**
     * 获取所有小区
     * @param region
     * @return
     */
    public List<StBaseinfoOrganization> getOrganizationByRegion(String region) {
        return stBaseinfoOrganizationDao.getOrganizationByRegion(region);
    }

    public List<String> getRelationOrgations(String villageCode) {
        return stBaseinfoOrganizationDao.getRelationOrgations(villageCode);
    }
     
}