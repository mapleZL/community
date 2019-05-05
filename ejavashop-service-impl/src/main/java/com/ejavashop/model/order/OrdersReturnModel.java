package com.ejavashop.model.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.order.OrdersReturnReadDao;
import com.ejavashop.dao.shop.write.order.OrdersReturnWriteDao;
import com.ejavashop.entity.order.OrdersReturn;

@Component
public class OrdersReturnModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(OrdersReturnModel.class);
    
    @Resource
    private OrdersReturnWriteDao ordersReturnWriteDao;
    @Resource
    private OrdersReturnReadDao ordersReturnReadDao;
    
    /**
     * 根据id取得orders_return对象
     * @param  ordersReturnId
     * @return
     */
    public com.ejavashop.entity.order.OrdersReturn getOrdersReturnById(Integer ordersReturnId) {
    	return ordersReturnReadDao.get(ordersReturnId);
    }
    
    /**
     * 保存orders_return对象
     * @param  ordersReturn
     * @return
     */
     public Integer saveOrdersReturn(OrdersReturn ordersReturn) {
     	this.dbConstrains(ordersReturn);
     	return ordersReturnWriteDao.insert(ordersReturn);
     }
     
     /**
     * 更新orders_return对象
     * @param  ordersReturn
     * @return
     */
     public Integer updateOrdersReturn(OrdersReturn ordersReturn) {
     	this.dbConstrains(ordersReturn);
     	return ordersReturnWriteDao.update(ordersReturn);
     }
     
     private void dbConstrains(OrdersReturn ordersReturn) {
		ordersReturn.setOrderSn(StringUtil.dbSafeString(ordersReturn.getOrderSn(), false, 50));
		ordersReturn.setRelationOrderSn(StringUtil.dbSafeString(ordersReturn.getRelationOrderSn(), false, 255));
		ordersReturn.setMemberName(StringUtil.dbSafeString(ordersReturn.getMemberName(), false, 100));
		ordersReturn.setInvoiceTitle(StringUtil.dbSafeString(ordersReturn.getInvoiceTitle(), true, 255));
		ordersReturn.setInvoiceType(StringUtil.dbSafeString(ordersReturn.getInvoiceType(), true, 50));
		ordersReturn.setIp(StringUtil.dbSafeString(ordersReturn.getIp(), false, 50));
		ordersReturn.setPaymentName(StringUtil.dbSafeString(ordersReturn.getPaymentName(), false, 100));
		ordersReturn.setPaymentCode(StringUtil.dbSafeString(ordersReturn.getPaymentCode(), false, 100));
		ordersReturn.setName(StringUtil.dbSafeString(ordersReturn.getName(), true, 60));
		ordersReturn.setAddressAll(StringUtil.dbSafeString(ordersReturn.getAddressAll(), true, 255));
		ordersReturn.setAddressInfo(StringUtil.dbSafeString(ordersReturn.getAddressInfo(), true, 255));
		ordersReturn.setMobile(StringUtil.dbSafeString(ordersReturn.getMobile(), true, 50));
		ordersReturn.setEmail(StringUtil.dbSafeString(ordersReturn.getEmail(), true, 255));
		ordersReturn.setZipCode(StringUtil.dbSafeString(ordersReturn.getZipCode(), true, 20));
		ordersReturn.setRemark(StringUtil.dbSafeString(ordersReturn.getRemark(), false, 255));
		ordersReturn.setLogisticsName(StringUtil.dbSafeString(ordersReturn.getLogisticsName(), false, 100));
		ordersReturn.setLogisticsNumber(StringUtil.dbSafeString(ordersReturn.getLogisticsNumber(), false, 100));
		ordersReturn.setCodconfirmName(StringUtil.dbSafeString(ordersReturn.getCodconfirmName(), false, 100));
		ordersReturn.setCodconfirmRemark(StringUtil.dbSafeString(ordersReturn.getCodconfirmRemark(), false, 255));
		ordersReturn.setTradeNo(StringUtil.dbSafeString(ordersReturn.getTradeNo(), true, 255));
		ordersReturn.setTradeSn(StringUtil.dbSafeString(ordersReturn.getTradeSn(), true, 255));
		ordersReturn.setReturnInfo(StringUtil.dbSafeString(ordersReturn.getReturnInfo(), true, 255));
     }
}