package com.ejavashop.model.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.write.product.OrderReceiptDetailWriteDao;
import com.ejavashop.entity.product.OrderReceiptDetail;

@Component
public class OrderReceiptDetailModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(OrderReceiptDetailModel.class);
    
    @Resource
    private OrderReceiptDetailWriteDao orderReceiptDetailWriteDao;
    
    /**
     * 根据id取得order_receipt_detail对象
     * @param  orderReceiptDetailId
     * @return
     */
    public OrderReceiptDetail getOrderReceiptDetailById(Integer orderReceiptDetailId) {
    	return orderReceiptDetailWriteDao.get(orderReceiptDetailId);
    }
    
    /**
     * 保存order_receipt_detail对象
     * @param  orderReceiptDetail
     * @return
     */
     public Integer saveOrderReceiptDetail(OrderReceiptDetail orderReceiptDetail) {
     	this.dbConstrains(orderReceiptDetail);
     	return orderReceiptDetailWriteDao.insert(orderReceiptDetail);
     }
     
     /**
     * 更新order_receipt_detail对象
     * @param  orderReceiptDetail
     * @return
     */
     public Integer updateOrderReceiptDetail(OrderReceiptDetail orderReceiptDetail) {
     	this.dbConstrains(orderReceiptDetail);
     	return orderReceiptDetailWriteDao.update(orderReceiptDetail);
     }
     
     private void dbConstrains(OrderReceiptDetail orderReceiptDetail) {
     }
}