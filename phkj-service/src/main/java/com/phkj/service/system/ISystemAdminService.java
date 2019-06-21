package com.phkj.service.system;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.system.SystemAdmin;

/**
 * 平台管理员用户管理
 *                       
 * @Filename: ISystemAdminService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISystemAdminService {

    /**
     * 根据id取得系统管理员表
     * @param  systemAdminId
     * @return
     */
    ServiceResult<SystemAdmin> getSystemAdminById(Integer systemAdminId);

    /**
     * 保存系统管理员表
     * @param  systemAdmin
     * @return
     */
    ServiceResult<Integer> saveSystemAdmin(SystemAdmin systemAdmin);

    /**
    * 更新系统管理员表
    * @param  systemAdmin
    * @return
    */
    ServiceResult<Integer> updateSystemAdmin(SystemAdmin systemAdmin);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SystemAdmin>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    ServiceResult<SystemAdmin> getSystemAdminByNamePwd(String name, String password);

    /**
     * 根据用户名取用户
     * @param name
     * @return
     */
    ServiceResult<List<SystemAdmin>> getSystemAdminByName(String name);
    
    /**
     * 根据角色类型获取账户列表
     * @param roleId
     * @return
     */
    ServiceResult<List<SystemAdmin>> getSystemAdminByRoleId(String roleId);

    ServiceResult<List<SystemAdmin>> getRepairsByUser(String villageCode, List<Integer> ids);
}