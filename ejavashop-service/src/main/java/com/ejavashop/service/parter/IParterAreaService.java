package com.ejavashop.service.parter;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.parter.ParterArea;
import com.ejavashop.entity.system.SystemAdmin;

public interface IParterAreaService {

	/**
     * ���idȡ��parter_area����
     * @param  parterAreaId
     * @return
     */
    ServiceResult<ParterArea> getParterAreaById(Integer parterAreaId);
    
    /**
     * ����parter_area����
     * @param  parterArea
     * @return
     */
     ServiceResult<Integer> saveParterArea(ParterArea parterArea);
     
     /**
     * ����parter_area����
     * @param  parterArea
     * @return
     */
     ServiceResult<Integer> updateParterArea(ParterArea parterArea);
     
     
     ServiceResult<List<ParterArea>> getParterAreaByParterSignId(Integer parterSignId,String memberAreaId);
     
     
     ServiceResult<Boolean> save(String roleId, String[] resArr, SystemAdmin adminUser,String memberName);
     
}