package com.ejavashop.dao.shop.write.seller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerTransport;

@Repository
public interface SellerTransportWriteDao {

    //SellerTransport get(java.lang.Integer id);

    Integer save(SellerTransport sellerTransport);

    Integer update(SellerTransport sellerTransport);

    //Integer getCount(Map<String, Object> queryMap);

    //List<SellerTransport> page(Map<String, Object> queryMap);

    Integer del(Integer id);

    Integer updateUnUseBySellerId(Integer sellerId);

    Integer updateInUseById(Integer id);
}
