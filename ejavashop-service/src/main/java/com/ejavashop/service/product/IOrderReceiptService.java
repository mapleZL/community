package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.OrderReceiptDetail;

public interface IOrderReceiptService {

	/**
     * 根据id取得order_receipt对象
     * @param  orderReceiptId
     * @return
     */
    ServiceResult<OrderReceipt> getOrderReceiptById(Integer orderReceiptId);
    
    /**
     * 保存order_receipt对象
     * @param  orderReceipt
     * @return
     */
     ServiceResult<Integer> saveOrderReceipt(OrderReceipt orderReceipt);
     
     /**
     * 更新order_receipt对象
     * @param  orderReceipt
     * @return
     */
     ServiceResult<Integer> updateOrderReceipt(OrderReceipt orderReceipt);
     
     /**
      * 商品查询分页处理
      * @param queryMap
      * @param pager
      * @return
      */
     ServiceResult<List<OrderReceipt>> page(Map<String, String> queryMap, PagerInfo pager);

     /**
      * 列表明细项查询
      * @param ordersId
      * @return
      */
     ServiceResult<List<OrderReceiptDetail>> getReceiptProductDetailByOrdersId(String ordersId);

    void auditSendGoods(String ordersId, String type);
    
    ServiceResult<OrderReceipt> getOrderReceiptByOrdersSn(String OrdersSn);
}