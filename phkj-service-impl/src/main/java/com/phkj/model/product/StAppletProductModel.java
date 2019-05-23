package com.phkj.model.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.product.StAppletProductReadDao;
import com.phkj.dao.shop.write.product.StAppletProductWriteDao;
import com.phkj.entity.product.StAppletProduct;

@Component
public class StAppletProductModel {

    @Autowired
    private StAppletProductReadDao stAppletProductReadDao;
    @Autowired
    private StAppletProductWriteDao stAppletProductWriteDao;
    
    /**
     * 根据id取得商品表
     * @param  stAppletProductId
     * @return
     */
    public StAppletProduct getStAppletProductById(Integer stAppletProductId) {
    	return stAppletProductReadDao.get(stAppletProductId);
    }
    
    /**
     * 保存商品表
     * @param  stAppletProduct
     * @return
     */
     public Integer saveStAppletProduct(StAppletProduct stAppletProduct) {
     	return stAppletProductWriteDao.insert(stAppletProduct);
     }
     
     /**
     * 更新商品表
     * @param  stAppletProduct
     * @return
     */
     public Integer updateStAppletProduct(StAppletProduct stAppletProduct) {
     	return stAppletProductWriteDao.update(stAppletProduct);
     }
     
}