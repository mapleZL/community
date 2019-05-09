package com.phkj.dao.shop.read.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.system.SystemResources;
import com.phkj.entity.system.SystemResourcesRoles;

@Repository
public interface SystemResourcesRolesReadDao {

    SystemResourcesRoles get(java.lang.Integer id);

    Integer getCount(Map<String, String> queryMap);

    List<SystemResourcesRoles> page(Map<String, String> queryMap);

    /**
     * 此角色下的资源
     * @param roleId
     * @return
     */
    List<SystemResources> getResourceByRoleId(@Param("roleId") Integer roleId,
                                              @Param("scope") Integer scope);

    /**
     * 此资源下的有权限的子资源
     * @param queryMap
     * @return
     */
    List<SystemResources> getResourceByPid(Map<String, Object> queryMap);

}
