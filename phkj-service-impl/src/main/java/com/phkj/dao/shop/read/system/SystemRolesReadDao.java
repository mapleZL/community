package com.phkj.dao.shop.read.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.system.SystemRoles;

@Repository
public interface SystemRolesReadDao {

    SystemRoles get(java.lang.Integer id);

    Integer getCount(@Param("queryMap") Map<String, String> queryMap);

    List<SystemRoles> page(@Param("queryMap") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    List<SystemRoles> getRepairs();


}
