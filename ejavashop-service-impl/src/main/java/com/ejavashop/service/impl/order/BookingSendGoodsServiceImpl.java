package com.ejavashop.service.impl.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.SendGoodsRecord;
import com.ejavashop.model.order.BookingSendGoodsModel;
import com.ejavashop.model.order.OrdersModel;
import com.ejavashop.model.order.SendGoodsRecordModel;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.vo.order.OrderSuccessVO;

@Service(value = "bookingSendGoodsService")
public class BookingSendGoodsServiceImpl implements IBookingSendGoodsService {
	private static Logger      log = LogManager.getLogger(BookingSendGoodsServiceImpl.class);
	
	@Resource
	private BookingSendGoodsModel bookingSendGoodsModel;
	
	@Resource
	private SendGoodsRecordModel sendGoodsRecordModel;
	
	@Resource
	private OrdersModel ordersModel;
    
     /**
     * 根据id取得booking_send_goods对象
     * @param  bookingSendGoodsId
     * @return
     */
    @Override
    public ServiceResult<BookingSendGoods> getBookingSendGoodsById(Integer bookingSendGoodsId) {
        ServiceResult<BookingSendGoods> result = new ServiceResult<BookingSendGoods>();
        try {
            result.setResult(bookingSendGoodsModel.getBookingSendGoodsById(bookingSendGoodsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBookingSendGoodsService][getBookingSendGoodsById]根据id["+bookingSendGoodsId+"]取得booking_send_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBookingSendGoodsService][getBookingSendGoodsById]根据id["+bookingSendGoodsId+"]取得booking_send_goods对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存booking_send_goods对象
     * @param  bookingSendGoods
     * @return
     */
     @Override
     public ServiceResult<Integer> saveBookingSendGoods(BookingSendGoods bookingSendGoods) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(bookingSendGoodsModel.saveBookingSendGoods(bookingSendGoods));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBookingSendGoodsService][saveBookingSendGoods]保存booking_send_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBookingSendGoodsService][saveBookingSendGoods]保存booking_send_goods对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新booking_send_goods对象
     * @param  bookingSendGoods
     * @return
     * @see com.ejavashop.service.BookingSendGoodsService#updateBookingSendGoods(BookingSendGoods)
     */
     @Override
     public ServiceResult<Integer> updateBookingSendGoods(BookingSendGoods bookingSendGoods) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(bookingSendGoodsModel.updateBookingSendGoods(bookingSendGoods));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBookingSendGoodsService][updateBookingSendGoods]更新booking_send_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBookingSendGoodsService][updateBookingSendGoods]更新booking_send_goods对象时出现未知异常：",
                e);
        }
    return result;
     }
        
	@Override
	public ServiceResult<List<BookingSendGoods>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<BookingSendGoods>> serviceResult = new ServiceResult<List<BookingSendGoods>>();
		serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(bookingSendGoodsModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<BookingSendGoods> list = bookingSendGoodsModel.page(param);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBookingSendGoodsService][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IBookingSendGoodsService][page] exception:" + e.getMessage());
        }
        return serviceResult;
	}

	@Override
	public void auditSendGoods(Integer id, String type,String checkMan,String checkNote) {
		 bookingSendGoodsModel.auditSendGoods(id, type,checkMan,checkNote);
	}

	@Override
	public void delGoods(Integer id) {
		bookingSendGoodsModel.delGoods(id);
	}

	@Override
	public ServiceResult<Boolean> saveSendGoods(Map<String, String[]> map) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
	        try {
	        	  result.setResult(bookingSendGoodsModel.saveSendGoods(map));
	        } catch (Exception e) {
	            log.error(
	                "IBookingSendGoodsService saveSendGoods param:" + JSON.toJSONString(map));
	            log.error("IBookingSendGoodsService saveSendGoods exception:", e);
	            result.setMessage(e.getMessage());
	            result.setSuccess(Boolean.FALSE);
	            e.printStackTrace();
	        }
	        return result;		
	}

	@Override
	public ServiceResult<Boolean> returngoods(Integer id,Integer status,String checkNote) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
        	  result.setResult(bookingSendGoodsModel.returngoods(id,status,checkNote));
        } catch (Exception e) {
            log.error(
                "IBookingSendGoodsService returngoods param:" + JSON.toJSONString(id));
            log.error("IBookingSendGoodsService returngoods exception:", e);
            result.setMessage(e.getMessage());
            result.setSuccess(Boolean.FALSE);
            e.printStackTrace();
        }
        return result;	
	}

	@Override
	public ServiceResult<Boolean> passReturnGoods(Integer id,String checkMan) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		try {
				BookingSendGoods  bsGoods =  bookingSendGoodsModel.getBookingSendGoodsById(id);
				List<SendGoodsRecord> recList =  sendGoodsRecordModel.getRecordByGoodsId(id);
				result.setResult(bookingSendGoodsModel.updateSellerStack(bsGoods,recList,checkMan));
		 } catch (Exception e) {
	            log.error(
	                "IBookingSendGoodsService passReturnGoods param:" + JSON.toJSONString(id));
	            log.error("IBookingSendGoodsService passReturnGoods exception:", e);
	            result.setMessage(e.getMessage());
	            result.setSuccess(Boolean.FALSE);
	            e.printStackTrace();
	        }
		return result;
	}

	@Override
	public ServiceResult<OrderSuccessVO> saveThreeSendOrders(Map<String, String[]> map, Member member,String num,String productSku,String orderGoodsId,String transportType) {
		ServiceResult<OrderSuccessVO> result = new ServiceResult<OrderSuccessVO>();
        try {
              Integer logisticsId = Integer.valueOf(transportType);
        	  result.setResult(bookingSendGoodsModel.saveThreeSendOrders(map,member,num,productSku,orderGoodsId,logisticsId));
        } catch (Exception e) {
            log.error(
                "IBookingSendGoodsService returngoods param:" + JSON.toJSONString(map));
            log.error("IBookingSendGoodsService returngoods exception:", e);
            result.setMessage(e.getMessage());
            result.setSuccess(Boolean.FALSE);
            e.printStackTrace();
        }
        return result;	
	}

	@Override
	public ServiceResult<Boolean> jobSystemCancelBookOrder() {
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(bookingSendGoodsModel.jobSystemCancelBookOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[IBookingSendGoodsService][jobSystemCancelBookOrder]系统取消订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IBookingSendGoodsService][jobSystemCancelBookOrder]系统取消订单时发生异常:", e);
        }
        return serviceResult;
	}
}