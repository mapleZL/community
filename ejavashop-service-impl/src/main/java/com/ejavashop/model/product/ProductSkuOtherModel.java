package com.ejavashop.model.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.product.ProductSkuOtherReadDao;
import com.ejavashop.dao.shop.write.product.ProductSkuOtherWriteDao;
import com.ejavashop.entity.product.ProductSkuOther;

@Component
public class ProductSkuOtherModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductSkuOtherModel.class);
    
    @Resource
    private ProductSkuOtherWriteDao productSkuOtherWriteDao;
    @Resource
    private ProductSkuOtherReadDao productSkuOtherReadDao;
    
    /**
     * 根据id取得product_sku_other对象
     * @param  productSkuOtherId
     * @return
     */
    public ProductSkuOther getProductSkuOtherById(Long productSkuOtherId) {
    	return productSkuOtherReadDao.get(productSkuOtherId);
    }
    
    public List<ProductSkuOther> queryProductSkuOtherByProductId(int productId) {
        return productSkuOtherReadDao.queryProductSkuOtherByProductId(productId);
    }
    
    /**
     * 保存product_sku_other对象
     * @param  productSkuOther
     * @return
     */
     public Integer saveProductSkuOther(ProductSkuOther productSkuOther) {
     	this.dbConstrains(productSkuOther);
     	return productSkuOtherWriteDao.insert(productSkuOther);
     }
     
     /**
     * 更新product_sku_other对象
     * @param  productSkuOther
     * @return
     */
     public Integer updateProductSkuOther(ProductSkuOther productSkuOther) {
     	this.dbConstrains(productSkuOther);
     	return productSkuOtherWriteDao.update(productSkuOther);
     }
     
     private void dbConstrains(ProductSkuOther productSkuOther) {
		productSkuOther.setSkuOther(StringUtil.dbSafeString(productSkuOther.getSkuOther(), true, 20));
     }
}