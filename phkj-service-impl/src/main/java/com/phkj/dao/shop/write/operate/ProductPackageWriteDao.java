package com.phkj.dao.shop.write.operate;

import org.springframework.stereotype.Repository;

import com.phkj.entity.operate.ProductPackage;

@Repository
public interface ProductPackageWriteDao {

    //ProductPackage get(java.lang.Integer id);

    Integer save(ProductPackage productPackage);

    Integer update(ProductPackage productPackage);

    //Integer getCount(Map<String, Object> queryMap);

    //List<ProductPackage> page(Map<String, Object> queryMap);

    Integer del(Integer id);

}