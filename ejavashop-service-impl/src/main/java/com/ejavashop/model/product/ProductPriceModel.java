package com.ejavashop.model.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductPriceReadDao;
import com.ejavashop.dao.shop.write.product.ProductPriceWriteDao;
import com.ejavashop.entity.product.ProductPrice;

@Component
public class ProductPriceModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductPriceModel.class);
    
    @Resource
    private ProductPriceWriteDao productPriceWriteDao;
    @Resource
    private ProductPriceReadDao productPriceReadDao;
    
    /**
     * 根据id取得product_price对象
     * @param  productPriceId
     * @return
     */
    public ProductPrice getProductPriceById(Integer productPriceId) {
    	return productPriceReadDao.get(productPriceId);
    }
    
    /**
     * 保存product_price对象
     * @param  productPrice
     * @return
     */
     public Integer saveProductPrice(ProductPrice productPrice) {
     	this.dbConstrains(productPrice);
     	return productPriceWriteDao.insert(productPrice);
     }
     
     /**
     * 更新product_price对象
     * @param  productPrice
     * @return
     */
     public Integer updateProductPrice(ProductPrice productPrice) {
     	this.dbConstrains(productPrice);
     	return productPriceWriteDao.update(productPrice);
     }
     
     private void dbConstrains(ProductPrice productPrice) {
     }
}