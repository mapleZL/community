package com.phkj.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.phkj.entity.product.ProductPicture;

@Repository
public interface ProductPictureWriteDao {

    Integer insert(ProductPicture productPicture);

    Integer update(ProductPicture productPicture);

    Integer del(Integer id);

    Integer delByProductId(Integer productId);

}
