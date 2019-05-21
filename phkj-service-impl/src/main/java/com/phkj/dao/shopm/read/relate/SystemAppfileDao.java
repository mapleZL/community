package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.SystemAppfile;

@Repository
public interface SystemAppfileDao {

    SystemAppfile get(java.lang.Long id);

    Integer insert(SystemAppfile systemAppfile);

    Integer update(SystemAppfile systemAppfile);

    List<SystemAppfile> getPicList(@Param("objModule") String module, @Param("objId") Long id,
                                   @Param("objType") String type);
}