package com.ejavashop.service.order;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.entity.order.SendGoodsRecord;
import com.ejavashop.vo.order.OrderSuccessVO;
public interface IBookingSendGoodsService {

	/**
     * 根据id取得booking_send_goods对象
     * @param  bookingSendGoodsId
     * @return
     */
    ServiceResult<BookingSendGoods> getBookingSendGoodsById(Integer bookingSendGoodsId);
    
    /**
     * 保存booking_send_goods对象
     * @param  bookingSendGoods
     * @return
     */
     ServiceResult<Integer> saveBookingSendGoods(BookingSendGoods bookingSendGoods);
     
     /**
     * 更新booking_send_goods对象
     * @param  bookingSendGoods
     * @return
     */
     ServiceResult<Integer> updateBookingSendGoods(BookingSendGoods bookingSendGoods);
     
     
     /**
      * 分页查询商品预约发货
      */
     ServiceResult<List<BookingSendGoods>> page(Map<String, String> queryMap, PagerInfo pager); 
     
     /**
      * 商品预约审核
      */
     public void auditSendGoods(Integer id ,String type,String checkMan,String checkNote);
     
     /**
      * 删除商品预约发货
      */
     public void delGoods(Integer id);
     
     /**
      * 新增预约发货saveSendGoodsRecord
      */
     ServiceResult<Boolean> saveSendGoods(Map<String, String[]> map);
     
     
	ServiceResult<Boolean> returngoods(Integer id, Integer status, String checkNote);
	
	
	ServiceResult<Boolean> passReturnGoods(Integer id,String checkMan);
	
	/**
	 * fron 三方仓储发货
	 */
     ServiceResult<OrderSuccessVO>saveThreeSendOrders(Map<String, String []>map,Member member,String num,String productSku,String orderGoodsId,String transportType); 
     
     /**
      * 定时器定时取消24小时未支付的订单
      */
     ServiceResult<Boolean> jobSystemCancelBookOrder();
}