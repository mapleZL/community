package com.ejavashop.model.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.order.OrdersProductReturnReadDao;
import com.ejavashop.dao.shop.write.order.OrdersProductReturnWriteDao;
import com.ejavashop.entity.order.OrdersProductReturn;

@Component
public class OrdersProductReturnModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(OrdersProductReturnModel.class);
    
    @Resource
    private OrdersProductReturnWriteDao ordersProductReturnWriteDao;
    @Resource
    private OrdersProductReturnReadDao ordersProductReturnReadDao;
    
    /**
     * 根据id取得orders_product_return对象
     * @param  ordersProductReturnId
     * @return
     */
    public OrdersProductReturn getOrdersProductReturnById(Integer ordersProductReturnId) {
    	return ordersProductReturnReadDao.get(ordersProductReturnId);
    }
    
    /**
     * 保存orders_product_return对象
     * @param  ordersProductReturn
     * @return
     */
     public Integer saveOrdersProductReturn(OrdersProductReturn ordersProductReturn) {
     	this.dbConstrains(ordersProductReturn);
     	return ordersProductReturnWriteDao.insert(ordersProductReturn);
     }
     
     /**
     * 更新orders_product_return对象
     * @param  ordersProductReturn
     * @return
     */
     public Integer updateOrdersProductReturn(OrdersProductReturn ordersProductReturn) {
     	this.dbConstrains(ordersProductReturn);
     	return ordersProductReturnWriteDao.update(ordersProductReturn);
     }
     
     private void dbConstrains(OrdersProductReturn ordersProductReturn) {
		ordersProductReturn.setOrdersSn(StringUtil.dbSafeString(ordersProductReturn.getOrdersSn(), false, 100));
		ordersProductReturn.setSpecInfo(StringUtil.dbSafeString(ordersProductReturn.getSpecInfo(), true, 255));
		ordersProductReturn.setProductName(StringUtil.dbSafeString(ordersProductReturn.getProductName(), false, 255));
		ordersProductReturn.setProductSku(StringUtil.dbSafeString(ordersProductReturn.getProductSku(), false, 60));
		ordersProductReturn.setLogisticsName(StringUtil.dbSafeString(ordersProductReturn.getLogisticsName(), true, 100));
		ordersProductReturn.setLogisticsNumber(StringUtil.dbSafeString(ordersProductReturn.getLogisticsNumber(), true, 100));
		ordersProductReturn.setSystemRemark(StringUtil.dbSafeString(ordersProductReturn.getSystemRemark(), true, 65535));
     }
}