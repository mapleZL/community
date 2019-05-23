package com.phkj.service.property;


import com.phkj.core.ServiceResult;
import com.phkj.entity.property.StBaseinfoDepartment;

public interface IStBaseinfoDepartmentService {

	/**
     * 根据id取得部门维护表
     * @param  stBaseinfoDepartmentId
     * @return
     */
    ServiceResult<StBaseinfoDepartment> getStBaseinfoDepartmentById(Integer stBaseinfoDepartmentId);
    
    /**
     * 保存部门维护表
     * @param  stBaseinfoDepartment
     * @return
     */
     ServiceResult<Integer> saveStBaseinfoDepartment(StBaseinfoDepartment stBaseinfoDepartment);
     
     /**
     * 更新部门维护表
     * @param  stBaseinfoDepartment
     * @return
     */
     ServiceResult<Integer> updateStBaseinfoDepartment(StBaseinfoDepartment stBaseinfoDepartment);
}