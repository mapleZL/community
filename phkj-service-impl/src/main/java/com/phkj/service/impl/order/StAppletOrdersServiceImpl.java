package com.phkj.service.impl.order;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.order.StAppletOrders;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.model.order.StAppletOrdersModel;
import com.phkj.model.order.StAppletOrdersProductModel;
import com.phkj.service.order.IStAppletOrdersService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "stAppletOrdersService")
public class StAppletOrdersServiceImpl implements IStAppletOrdersService {
    private static Logger log = LogManager.getLogger(StAppletOrdersServiceImpl.class);

    @Resource
    private StAppletOrdersModel stAppletOrdersModel;

    @Resource
    private StAppletOrdersProductModel stAppletOrdersProductModel;

    /**
     * 根据id取得订单
     *
     * @param stAppletOrdersId
     * @return
     */
    @Override
    public ServiceResult<StAppletOrders> getStAppletOrdersById(Integer stAppletOrdersId) {
        ServiceResult<StAppletOrders> result = new ServiceResult<StAppletOrders>();
        try {
            result.setResult(stAppletOrdersModel.getStAppletOrdersById(stAppletOrdersId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][getStAppletOrdersById]根据id[" + stAppletOrdersId + "]取得订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][getStAppletOrdersById]根据id[" + stAppletOrdersId + "]取得订单时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 保存订单
     *
     * @param stAppletOrders
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStAppletOrders(StAppletOrders stAppletOrders) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletOrdersModel.saveStAppletOrders(stAppletOrders));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][saveStAppletOrders]保存订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][saveStAppletOrders]保存订单时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 更新订单
     *
     * @param stAppletOrders
     * @return
     */
    @Override
    public ServiceResult<Integer> updateStAppletOrders(StAppletOrders stAppletOrders) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletOrdersModel.updateStAppletOrders(stAppletOrders));
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("ok");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][updateStAppletOrders]更新订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][updateStAppletOrders]更新订单时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StAppletOrders>> getStAppletOrdersList(Integer memberId, int pageNum, int pageSize) {
        ServiceResult<List<StAppletOrders>> result = new ServiceResult<>();
        try {
            result.setResult(stAppletOrdersModel.getStAppletOrdersList(memberId, pageNum, pageSize));
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("ok");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][getStAppletOrdersList]获取订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][getStAppletOrdersList]获取订单时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StAppletOrdersProduct>> detail(String orderSn) {
        ServiceResult<List<StAppletOrdersProduct>> result = new ServiceResult<>();
        try {
            result.setResult(stAppletOrdersProductModel.getStAppletOrdersProductList(orderSn));
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("ok");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][detail]获取订单详情时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][detail]获取订单详情时出现未知异常：",
                    e);
        }
        return result;
    }
}