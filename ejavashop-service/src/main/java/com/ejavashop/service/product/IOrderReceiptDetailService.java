package com.ejavashop.service.product;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.OrderReceiptDetail;

public interface IOrderReceiptDetailService {

	/**
     * 根据id取得order_receipt_detail对象
     * @param  orderReceiptDetailId
     * @return
     */
    ServiceResult<OrderReceiptDetail> getOrderReceiptDetailById(Integer orderReceiptDetailId);
    
    /**
     * 保存order_receipt_detail对象
     * @param  orderReceiptDetail
     * @return
     */
     ServiceResult<Integer> saveOrderReceiptDetail(OrderReceiptDetail orderReceiptDetail);
     
     /**
     * 更新order_receipt_detail对象
     * @param  orderReceiptDetail
     * @return
     */
     ServiceResult<Integer> updateOrderReceiptDetail(OrderReceiptDetail orderReceiptDetail);
}