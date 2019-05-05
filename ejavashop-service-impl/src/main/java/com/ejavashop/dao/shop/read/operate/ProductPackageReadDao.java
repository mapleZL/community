package com.ejavashop.dao.shop.read.operate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.operate.ProductPackage;

@Repository
public interface ProductPackageReadDao {

    ProductPackage get(java.lang.Integer id);

    Integer getCount(Map<String, Object> queryMap);

    List<ProductPackage> page(Map<String, Object> queryMap);


}