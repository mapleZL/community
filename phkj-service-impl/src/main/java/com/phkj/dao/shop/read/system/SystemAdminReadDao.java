package com.phkj.dao.shop.read.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.system.SystemAdmin;

@Repository
public interface SystemAdminReadDao {

    SystemAdmin get(Integer id);

    Integer getCount(@Param("queryMap") Map<String, String> queryMap);

    List<SystemAdmin> page(@Param("queryMap") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    List<SystemAdmin> getByName(@Param("name") String name);

    SystemAdmin getByNamePwd(Map<String, Object> queryMap);

    List<SystemAdmin> getSystemAdminByRoleId(@Param("roleId") String roleId);

    List<SystemAdmin> getRepairsByUser(@Param("villageCode") String villageCode,
                                       @Param("ids") List<Integer> ids);

}
