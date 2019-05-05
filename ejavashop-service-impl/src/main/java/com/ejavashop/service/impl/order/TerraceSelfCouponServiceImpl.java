package com.ejavashop.service.impl.order;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.TerraceSelfCoupon;
import com.ejavashop.model.order.TerraceSelfCouponModel;
import com.ejavashop.service.order.ITerraceSelfCouponService;

@Service(value = "terraceSelfCouponService")
public class TerraceSelfCouponServiceImpl implements ITerraceSelfCouponService {
    private static Logger      log = LogManager.getLogger(OrdersReturnServiceImpl.class);
	@Resource
	private TerraceSelfCouponModel terraceSelfCouponModel;
    
     /**
     * 根据id取得terrace_self_coupon对象
     * @param  terraceSelfCouponId
     * @return
     */
    @Override
    public ServiceResult<TerraceSelfCoupon> getTerraceSelfCouponById(Integer terraceSelfCouponId) {
        ServiceResult<TerraceSelfCoupon> result = new ServiceResult<TerraceSelfCoupon>();
        try {
            result.setResult(terraceSelfCouponModel.getTerraceSelfCouponById(terraceSelfCouponId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ITerraceSelfCouponService][getTerraceSelfCouponById]根据id["+terraceSelfCouponId+"]取得terrace_self_coupon对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ITerraceSelfCouponService][getTerraceSelfCouponById]根据id["+terraceSelfCouponId+"]取得terrace_self_coupon对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存terrace_self_coupon对象
     * @param  terraceSelfCoupon
     * @return
     */
     @Override
     public ServiceResult<Integer> saveTerraceSelfCoupon(TerraceSelfCoupon terraceSelfCoupon) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(terraceSelfCouponModel.saveTerraceSelfCoupon(terraceSelfCoupon));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ITerraceSelfCouponService][saveTerraceSelfCoupon]保存terrace_self_coupon对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ITerraceSelfCouponService][saveTerraceSelfCoupon]保存terrace_self_coupon对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新terrace_self_coupon对象
     * @param  terraceSelfCoupon
     * @return
     * @see com.ejavashop.service.TerraceSelfCouponService#updateTerraceSelfCoupon(TerraceSelfCoupon)
     */
     @Override
     public ServiceResult<Integer> updateTerraceSelfCoupon(TerraceSelfCoupon terraceSelfCoupon) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(terraceSelfCouponModel.updateTerraceSelfCoupon(terraceSelfCoupon));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ITerraceSelfCouponService][updateTerraceSelfCoupon]更新terrace_self_coupon对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ITerraceSelfCouponService][updateTerraceSelfCoupon]更新terrace_self_coupon对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<TerraceSelfCoupon> getTerraceSelfCouponByTradeNo(String tradeNo) {
        ServiceResult<TerraceSelfCoupon> result = new ServiceResult<TerraceSelfCoupon>();
        try {
            result.setResult(terraceSelfCouponModel.getTerraceSelfCouponByTradeNo(tradeNo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ITerraceSelfCouponService][getTerraceSelfCouponById]根据tradeNo["+tradeNo+"]取得terrace_self_coupon对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ITerraceSelfCouponService][getTerraceSelfCouponById]根据tradeNo["+tradeNo+"]取得terrace_self_coupon对象时出现未知异常：",
                e);
        }
        return result;
    }

}