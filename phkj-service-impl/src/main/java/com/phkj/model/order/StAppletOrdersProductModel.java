package com.phkj.model.order;


import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.order.StAppletOrdersProductReadDao;
import com.phkj.dao.shop.write.order.StAppletOrdersProductWriteDao;
import com.phkj.entity.order.StAppletOrdersProduct;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StAppletOrdersProductModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(StAppletOrdersProductModel.class);
    
    @Resource
    private StAppletOrdersProductReadDao stAppletOrdersProductReadDao;
    @Resource
    private StAppletOrdersProductWriteDao stAppletOrdersProductWriteDao;

    /**
     * 根据id取得网单表
     * @param  stAppletOrdersProductId
     * @return
     */
    public StAppletOrdersProduct getStAppletOrdersProductById(Integer stAppletOrdersProductId) {
    	return stAppletOrdersProductReadDao.get(stAppletOrdersProductId);
    }
    
    /**
     * 保存网单表
     * @param  stAppletOrdersProduct
     * @return
     */
     public Integer saveStAppletOrdersProduct(StAppletOrdersProduct stAppletOrdersProduct) {
     	this.dbConstrains(stAppletOrdersProduct);
     	return stAppletOrdersProductWriteDao.insert(stAppletOrdersProduct);
     }
     
     /**
     * 更新网单表
     * @param  stAppletOrdersProduct
     * @return
     */
     public Integer updateStAppletOrdersProduct(StAppletOrdersProduct stAppletOrdersProduct) {
     	this.dbConstrains(stAppletOrdersProduct);
     	return stAppletOrdersProductWriteDao.update(stAppletOrdersProduct);
     }
     
     private void dbConstrains(StAppletOrdersProduct stAppletOrdersProduct) {
		stAppletOrdersProduct.setOrdersSn(StringUtil.dbSafeString(stAppletOrdersProduct.getOrdersSn(), false, 100));
		stAppletOrdersProduct.setSpecInfo(StringUtil.dbSafeString(stAppletOrdersProduct.getSpecInfo(), true, 255));
		stAppletOrdersProduct.setProductName(StringUtil.dbSafeString(stAppletOrdersProduct.getProductName(), false, 255));
		stAppletOrdersProduct.setProductSku(StringUtil.dbSafeString(stAppletOrdersProduct.getProductSku(), false, 60));
		stAppletOrdersProduct.setLogisticsName(StringUtil.dbSafeString(stAppletOrdersProduct.getLogisticsName(), true, 100));
		stAppletOrdersProduct.setLogisticsNumber(StringUtil.dbSafeString(stAppletOrdersProduct.getLogisticsNumber(), true, 100));
		stAppletOrdersProduct.setSystemRemark(StringUtil.dbSafeString(stAppletOrdersProduct.getSystemRemark(), true, 65535));
     }

    public List<StAppletOrdersProduct> getStAppletOrdersProductList(String orderSn) {
        return stAppletOrdersProductReadDao.getStAppletOrdersProductList(orderSn);
    }
}