package com.ejavashop.dao.shop.write.system;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.system.SystemResources;

@Repository
public interface SystemResourcesWriteDao {

    //SystemResources get(java.lang.Integer id);

    Integer save(SystemResources systemResources);

    Integer update(SystemResources systemResources);

    //Integer getCount(Map<String, String> queryMap);

    //List<SystemResources> page(Map<String, String> queryMap);

    //List<SystemResources> list(Map<String, Object> queryMap);

    Integer del(Integer id);

}
