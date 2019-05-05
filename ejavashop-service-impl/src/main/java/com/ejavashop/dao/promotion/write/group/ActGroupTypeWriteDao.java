package com.ejavashop.dao.promotion.write.group;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.group.ActGroupType;

@Repository
public interface ActGroupTypeWriteDao {

    Integer insert(ActGroupType actGroupType);

    Integer update(ActGroupType actGroupType);

    Integer audit(@Param("id") Integer id, @Param("state") Integer state);

    Integer del(Integer id);
}