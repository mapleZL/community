package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductPictureReadDao;
import com.ejavashop.dao.shop.write.product.ProductPictureWriteDao;
import com.ejavashop.entity.product.ProductPicture;

@Component(value = "productPictureModel")
public class ProductPictureModel {

    @Resource
    private ProductPictureWriteDao productPictureWriteDao;
    @Resource
    private ProductPictureReadDao productPictureReadDao;

    public Boolean saveProductPicture(ProductPicture productPicture) {
        return productPictureWriteDao.insert(productPicture) > 0;
    }

    public Boolean updateProductPicture(ProductPicture productPicture) {
        return productPictureWriteDao.update(productPicture) > 0;
    }

    public Boolean delProductPicture(Integer productPictureId) {
        //1. business check
        if (null == productPictureId || 0 == productPictureId)
            throw new BusinessException("根据id删除商品对应图片表失败，id为空");

        //2. dbOper
        return productPictureWriteDao.del(productPictureId) > 0;
    }

    public ProductPicture getProductPictureById(Integer productPictureId) {
        if (null == productPictureId || 0 == productPictureId)
            throw new BusinessException("根据id获取商品对应图片表失败，id为空");

        return productPictureReadDao.get(productPictureId);
    }

    public Integer pageProductPictureCount(Map<String, String> queryMap) {
        return productPictureReadDao.count(queryMap);
    }

    public List<ProductPicture> pageProductPicture(Map<String, String> queryMap, Integer start,
                                                   Integer size) {
        return productPictureReadDao.page(queryMap, start, size);
    }

    public List<ProductPicture> getProductPictureByProductId(Integer productId) {
        return productPictureReadDao.getByProductId(productId);
    }

}
