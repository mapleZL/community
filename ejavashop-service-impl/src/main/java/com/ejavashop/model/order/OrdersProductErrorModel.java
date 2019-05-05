package com.ejavashop.model.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.order.OrdersProductErrorReadDao;
import com.ejavashop.dao.shop.write.order.OrdersProductErrorWriteDao;
import com.ejavashop.entity.order.OrdersProductError;

@Component
public class OrdersProductErrorModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(OrdersProductErrorModel.class);
    
    @Resource
    private OrdersProductErrorWriteDao ordersProductErrorWriteDao;
    @Resource
    private OrdersProductErrorReadDao ordersProductErrorReadDao;
    
    /**
     * 根据id取得网单表
     * @param  ordersProductErrorId
     * @return
     */
    public OrdersProductError getOrdersProductErrorById(Integer ordersProductErrorId) {
    	return ordersProductErrorReadDao.get(ordersProductErrorId);
    }
    
    public List<OrdersProductError> geterrorordersproduct(
			String ordersErroeSn) {
    	return ordersProductErrorReadDao.geterrorordersproduct(ordersErroeSn);
    }
    /**
     * 保存网单表
     * @param  ordersProductError
     * @return
     */
     public Integer saveOrdersProductError(OrdersProductError ordersProductError) {
     	this.dbConstrains(ordersProductError);
     	return ordersProductErrorWriteDao.insert(ordersProductError);
     }
     
     /**
     * 更新网单表
     * @param  ordersProductError
     * @return
     */
     public Integer updateOrdersProductError(OrdersProductError ordersProductError) {
     	this.dbConstrains(ordersProductError);
     	return ordersProductErrorWriteDao.update(ordersProductError);
     }
     
     private void dbConstrains(OrdersProductError ordersProductError) {
		ordersProductError.setOrdersSn(StringUtil.dbSafeString(ordersProductError.getOrdersSn(), false, 100));
		ordersProductError.setSpecInfo(StringUtil.dbSafeString(ordersProductError.getSpecInfo(), true, 255));
		ordersProductError.setProductName(StringUtil.dbSafeString(ordersProductError.getProductName(), false, 255));
		ordersProductError.setProductSku(StringUtil.dbSafeString(ordersProductError.getProductSku(), false, 60));
		ordersProductError.setLogisticsName(StringUtil.dbSafeString(ordersProductError.getLogisticsName(), true, 100));
		ordersProductError.setLogisticsNumber(StringUtil.dbSafeString(ordersProductError.getLogisticsNumber(), true, 100));
		ordersProductError.setSystemRemark(StringUtil.dbSafeString(ordersProductError.getSystemRemark(), true, 65535));
     }
}