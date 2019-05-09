package com.phkj.dao.shop.write.system;

import org.springframework.stereotype.Repository;

import com.phkj.entity.system.SystemRoles;

@Repository
public interface SystemRolesWriteDao {

//    SystemRoles get(java.lang.Integer id);

    Integer save(SystemRoles systemRoles);

    Integer update(SystemRoles systemRoles);

//    Integer getCount(@Param("queryMap") Map<String, String> queryMap);

//    List<SystemRoles> page(@Param("queryMap") Map<String, String> queryMap,
//                           @Param("start") Integer start, @Param("size") Integer size);

    Integer del(Integer id);

}
