package com.ejavashop.dao.promotion.read.full;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.full.ActFullLog;

@Repository
public interface ActFullLogReadDao {

    ActFullLog get(java.lang.Integer id);

}