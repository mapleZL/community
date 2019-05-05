package com.ejavashop.model.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.order.OrdersErrorReadDao;
import com.ejavashop.dao.shop.write.order.OrdersErrorWriteDao;
import com.ejavashop.entity.order.OrdersError;

@Component
public class OrdersErrorModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(OrdersErrorModel.class);
    
    @Resource
    private OrdersErrorWriteDao ordersErrorWriteDao;
    @Resource
    private OrdersErrorReadDao ordersErrorReadDao;
    
    /**
     * 根据id取得订单
     * @param  ordersErrorId
     * @return
     */
    public OrdersError getOrdersErrorById(Integer ordersErrorId) {
    	return ordersErrorReadDao.get(ordersErrorId);
    }
    
    /**
     * 保存订单
     * @param  ordersError
     * @return
     */
     public Integer saveOrdersError(OrdersError ordersError) {
     	this.dbConstrains(ordersError);
     	return ordersErrorWriteDao.insert(ordersError);
     }
     
     /**
     * 更新订单
     * @param  ordersError
     * @return
     */
     public Integer updateOrdersError(OrdersError ordersError) {
     	this.dbConstrains(ordersError);
     	return ordersErrorWriteDao.update(ordersError);
     }
     
     public List<OrdersError>getOrdersErrorByOrdersSn(String ordersSn,
 			Map<String, String> queryMap) {
      	return ordersErrorReadDao.getOrdersErrorByOrdersSn(ordersSn,queryMap);
     }
     
     private void dbConstrains(OrdersError ordersError) {
		ordersError.setOrderSn(StringUtil.dbSafeString(ordersError.getOrderSn(), false, 50));
		ordersError.setRelationOrderSn(StringUtil.dbSafeString(ordersError.getRelationOrderSn(), false, 255));
		ordersError.setMemberName(StringUtil.dbSafeString(ordersError.getMemberName(), false, 100));
		ordersError.setInvoiceTitle(StringUtil.dbSafeString(ordersError.getInvoiceTitle(), true, 255));
		ordersError.setInvoiceType(StringUtil.dbSafeString(ordersError.getInvoiceType(), true, 50));
		ordersError.setIp(StringUtil.dbSafeString(ordersError.getIp(), false, 50));
		ordersError.setPaymentName(StringUtil.dbSafeString(ordersError.getPaymentName(), false, 100));
		ordersError.setPaymentCode(StringUtil.dbSafeString(ordersError.getPaymentCode(), false, 100));
		ordersError.setName(StringUtil.dbSafeString(ordersError.getName(), true, 60));
		ordersError.setAddressAll(StringUtil.dbSafeString(ordersError.getAddressAll(), true, 255));
		ordersError.setAddressInfo(StringUtil.dbSafeString(ordersError.getAddressInfo(), true, 255));
		ordersError.setMobile(StringUtil.dbSafeString(ordersError.getMobile(), true, 50));
		ordersError.setEmail(StringUtil.dbSafeString(ordersError.getEmail(), true, 255));
		ordersError.setZipCode(StringUtil.dbSafeString(ordersError.getZipCode(), true, 20));
		ordersError.setRemark(StringUtil.dbSafeString(ordersError.getRemark(), false, 255));
		ordersError.setLogisticsName(StringUtil.dbSafeString(ordersError.getLogisticsName(), false, 100));
		ordersError.setLogisticsNumber(StringUtil.dbSafeString(ordersError.getLogisticsNumber(), false, 100));
		ordersError.setCodconfirmName(StringUtil.dbSafeString(ordersError.getCodconfirmName(), false, 100));
		ordersError.setCodconfirmRemark(StringUtil.dbSafeString(ordersError.getCodconfirmRemark(), false, 255));
		ordersError.setTradeNo(StringUtil.dbSafeString(ordersError.getTradeNo(), true, 255));
		ordersError.setTradeSn(StringUtil.dbSafeString(ordersError.getTradeSn(), true, 255));
     }
}