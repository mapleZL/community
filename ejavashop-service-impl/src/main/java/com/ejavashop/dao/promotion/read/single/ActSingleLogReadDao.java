package com.ejavashop.dao.promotion.read.single;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.single.ActSingleLog;

@Repository
public interface ActSingleLogReadDao {

    ActSingleLog get(java.lang.Integer id);

}