package com.phkj.service.impl.cart;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.cart.StAppletCart;
import com.phkj.model.cart.StAppletCartModel;
import com.phkj.service.cart.IStAppletCartService;

/**
 * 
 *                       
 * @Filename: StAppletCartServiceImpl.java
 * @Version: 1.0
 * @date: 2019年6月3日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "stAppletCartService")
public class StAppletCartServiceImpl implements IStAppletCartService {
    private static Logger     log = LogManager.getLogger(StAppletCartServiceImpl.class);

    @Autowired
    private StAppletCartModel stAppletCartModel;

    /**
    * 根据id取得商城购物车
    * @param  stAppletCartId
    * @return
    */
    @Override
    public ServiceResult<StAppletCart> getStAppletCartById(Integer stAppletCartId) {
        ServiceResult<StAppletCart> result = new ServiceResult<StAppletCart>();
        try {
            result.setResult(stAppletCartModel.getStAppletCartById(stAppletCartId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCartService][getStAppletCartById]根据id[" + stAppletCartId
                      + "]取得商城购物车时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCartService][getStAppletCartById]根据id[" + stAppletCartId
                      + "]取得商城购物车时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存商城购物车
     * @param  stAppletCart
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStAppletCart(StAppletCart stAppletCart) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletCartModel.saveStAppletCart(stAppletCart));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCartService][saveStAppletCart]保存商城购物车时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCartService][saveStAppletCart]保存商城购物车时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新商城购物车
    * @param  stAppletCart
    * @return
    * @see com.phkj.service.StAppletCartService#updateStAppletCart(StAppletCart)
    */
    @Override
    public ServiceResult<Integer> updateStAppletCart(StAppletCart stAppletCart) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletCartModel.updateStAppletCart(stAppletCart));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCartService][updateStAppletCart]更新商城购物车时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCartService][updateStAppletCart]更新商城购物车时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 修改购物车某商品的数量
     * @param id
     * @param num
     * @return
     * @see com.phkj.service.cart.IStAppletCartService#changeCart(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> changeCart(Integer id, Integer num) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            StAppletCart stAppletCart = stAppletCartModel.getStAppletCartById(id);
            stAppletCart.setCount(stAppletCart.getCount() + num);
            stAppletCartModel.updateStAppletCart(stAppletCart);
            result.setSuccess(true);
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCartService][changeCart]更新商城购物车时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCartService][changeCart]更新商城购物车时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 删除购物车中的商品
     * @param id
     * @return
     * @see com.phkj.service.cart.IStAppletCartService#delCartById(java.lang.Integer)
     */
    @Override
    public Boolean delCartById(Integer id) {
        Boolean success = true;
        try {
            stAppletCartModel.delCartById(id);
        } catch (Exception e) {
            success = false;
            log.error("[IStAppletCartService][delCartById]删除商品发生异常：" + e.getMessage());
        }
        return success;
    }

    /**
     * 查询购物车列表
     * @param memberId
     * @param villageCode
     * @param start
     * @param pageSize
     * @return
     * @see com.phkj.service.cart.IStAppletCartService#list(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public ServiceResult<List<StAppletCart>> list(Integer memberId, String villageCode,
                                                  Integer start, Integer pageSize) {
        ServiceResult<List<StAppletCart>> serviceResult = new ServiceResult<>();
        try {
            start = (start - 1) * pageSize;
            List<StAppletCart> list = stAppletCartModel.list(memberId, villageCode, start, pageSize);
            serviceResult.setResult(list);
        } catch (Exception e) {
            log.error("[IStAppletCartService][list]查询商品列表发生异常：", e);
            serviceResult.setSuccess(false);
        }
        return serviceResult;
    }

    @Override
    public Integer getCount(Integer memberId, String villageCode) {
        return stAppletCartModel.count(memberId, villageCode);
    }

    @Override
    public StAppletCart getCountByProduct(Integer productId, String villageCode, Integer memberId) {
        return stAppletCartModel.getCountByProduct(memberId, productId, villageCode);
    }
}