package com.ejavashop.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;
import com.ejavashop.model.promotion.ActBiddingModel;
import com.ejavashop.service.promotion.IActBiddingService;

@Service(value = "actBiddingService")
public class ActBiddingServiceImpl implements IActBiddingService {
    private static Logger   log = LogManager.getLogger(ActBiddingServiceImpl.class);

    @Resource
    private ActBiddingModel actBiddingModel;

    /**
     * 根据id取得集合竞价分类
     * @param  actBiddingTypeId
     * @return
     */
    @Override
    public ServiceResult<ActBiddingType> getActBiddingTypeById(Integer actBiddingTypeId) {
        ServiceResult<ActBiddingType> result = new ServiceResult<ActBiddingType>();
        try {
            result.setResult(actBiddingModel.getActBiddingTypeById(actBiddingTypeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][getActBiddingTypeById]根据id[" + actBiddingTypeId
                      + "]取得集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存集合竞价分类
     * @param  actBiddingType
     * @return
     */
    @Override
    public ServiceResult<Integer> saveActBiddingType(ActBiddingType actBiddingType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actBiddingModel.saveActBiddingType(actBiddingType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][saveActBiddingType]保存集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新集合竞价分类
    * @param  actBiddingType
    * @return
    * @see com.ejavashop.service.ActBiddingTypeService#updateActBiddingType(ActBiddingType)
    */
    @Override
    public ServiceResult<Integer> updateActBiddingType(ActBiddingType actBiddingType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actBiddingModel.updateActBiddingType(actBiddingType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][updateActBiddingType]更新集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询集合竞价分类
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingTypes(java.util.Map, com.ejavashop.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActBiddingType>> getActBiddingTypes(Map<String, String> queryMap,
                                                                  PagerInfo pager) {
        ServiceResult<List<ActBiddingType>> result = new ServiceResult<List<ActBiddingType>>();
        result.setPager(pager);
        try {
            result.setResult(actBiddingModel.getActBiddingTypes(queryMap, pager));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][getActBiddingTypes]查询集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 删除集合竞价分类
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#delActBiddingType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> delActBiddingType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.delActBiddingType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][delActBiddingType]删除集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 启用集合竞价分类
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#auditYesActBiddingType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> auditYesActBiddingType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.auditYesActBiddingType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][auditYesActBiddingType]启用集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 停用集合竞价分类
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#auditNoActBiddingType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> auditNoActBiddingType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.auditNoActBiddingType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][auditNoActBiddingType]停用集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有可用的集合竞价分类
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingTypesAll()
     */
    @Override
    public ServiceResult<List<ActBiddingType>> getActBiddingTypesAll() {
        ServiceResult<List<ActBiddingType>> result = new ServiceResult<List<ActBiddingType>>();
        try {
            result.setResult(actBiddingModel.getActBiddingTypesAll());
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][getActBiddingTypesAll]查询所有可用的集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有可用的集合竞价分类
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingTypesFront()
     */
    @Override
    public ServiceResult<List<ActBiddingType>> getActBiddingTypesFront() {
        ServiceResult<List<ActBiddingType>> result = new ServiceResult<List<ActBiddingType>>();
        try {
            result.setResult(actBiddingModel.getActBiddingTypesFront());
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActBiddingTypeService][getActBiddingTypesFront]查询所有可用的集合竞价分类时出现未知异常：", e);
        }
        return result;
    }

    ///////////////////////

    /**
     * 根据id取得集合竞价
     * @param actBiddingId
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingById(java.lang.Integer)
     */
    @Override
    public ServiceResult<ActBidding> getActBiddingById(Integer actBiddingId) {
        ServiceResult<ActBidding> result = new ServiceResult<ActBidding>();
        try {
            result.setResult(actBiddingModel.getActBiddingById(actBiddingId));
        } catch (Exception e) {
            log.error("[ActBiddingServiceImpl][getActBiddingById]根据id[" + actBiddingId
                      + "]取得集合竞价时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][getActBiddingById]根据id[" + actBiddingId
                              + "]取得集合竞价时出现未知异常");
        }
        return result;
    }

    /**
     * 保存集合竞价
     * @param actBidding
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#saveActBidding(com.ejavashop.entity.promotion.bidding.ActBidding)
     */
    @Override
    public ServiceResult<Integer> saveActBidding(ActBidding actBidding) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actBiddingModel.saveActBidding(actBidding));
        } catch (Exception e) {
            log.error("[ActBiddingServiceImpl][saveActBidding]保存集合竞价时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][saveActBidding]保存集合竞价时出现未知异常");
        }
        return result;
    }

    /**
     * 更新集合竞价
     * @param actBidding
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#updateActBidding(com.ejavashop.entity.promotion.bidding.ActBidding)
     */
    @Override
    public ServiceResult<Integer> updateActBidding(ActBidding actBidding) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actBiddingModel.updateActBidding(actBidding));
        } catch (Exception e) {
            log.error("[ActBiddingServiceImpl][updateActBidding]更新集合竞价时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][updateActBidding]更新集合竞价时出现未知异常");
        }
        return result;
    }

    /**
     * 查询集合竞价
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddings(java.util.Map, com.ejavashop.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActBidding>> getActBiddings(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<ActBidding>> serviceResult = new ServiceResult<List<ActBidding>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actBiddingModel.getActBiddingsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<ActBidding> list = actBiddingModel.getActBiddings(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ProductAskService][getActBiddings]查询集合竞价出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][getActBiddings]查询集合竞价发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 保存集合竞价和阶梯价格
     * @param actBidding
     * @param actBiddingPrices
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#saveActBiddingAndPrice(com.ejavashop.entity.promotion.bidding.ActBidding, java.util.List)
     */
    @Override
    public ServiceResult<Boolean> saveActBiddingAndPrice(ActBidding actBidding,
                                                         List<ActBiddingPrice> actBiddingPrices) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.saveActBiddingAndPrice(actBidding, actBiddingPrices));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][saveActBiddingAndPrice]保存集合竞价和阶梯价格时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][saveActBiddingAndPrice]保存集合竞价和阶梯价格时出现未知异常");
        }
        return result;
    }

    /**
     * 根据集合竞价ID取得阶梯价格
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingByIds(java.lang.Integer)
     */
    @Override
    public ServiceResult<List<ActBiddingPrice>> getActBiddingByIds(Integer id) {
        ServiceResult<List<ActBiddingPrice>> result = new ServiceResult<List<ActBiddingPrice>>();
        try {
            result.setResult(actBiddingModel.getActBiddingByIds(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][getActBiddingByIds]根据集合竞价ID取得阶梯价格时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][getActBiddingByIds]根据集合竞价ID取得阶梯价格时出现未知异常");
        }
        return result;
    }

    /**
     * 更新集合竞价和阶梯价格
     * @param actBidding
     * @param actBiddingPrices
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#updateActBiddingAndPrice(com.ejavashop.entity.promotion.bidding.ActBidding, java.util.List)
     */
    @Override
    public ServiceResult<Boolean> updateActBiddingAndPrice(ActBidding actBidding,
                                                           List<ActBiddingPrice> actBiddingPrices) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result
                .setResult(actBiddingModel.updateActBiddingAndPrice(actBidding, actBiddingPrices));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][updateActBiddingAndPrice]更新集合竞价和阶梯价格时出现未知异常：" + e);
            result.setSuccess(false);
            result
                .setMessage("[ActBiddingServiceImpl][updateActBiddingAndPrice]更新集合竞价和阶梯价格时出现未知异常");
        }
        return result;
    }

    /**
     * 更新集合竞价状态
     * @param id
     * @param state
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#updateActBiddingState(java.lang.Integer, int)
     */
    @Override
    public ServiceResult<Boolean> updateActBiddingState(Integer id, int state) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.updateActBiddingState(id, state));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][updateActBiddingState]更新集合竞价状态时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][updateActBiddingState]更新集合竞价状态时出现未知异常");
        }
        return result;
    }

    /**
     * 更新集合竞价发布状态
     * @param id
     * @param actState
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#updateActBiddingActState(java.lang.Integer, int)
     */
    @Override
    public ServiceResult<Boolean> updateActBiddingActState(Integer id, int actState) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.updateActBiddingActState(id, actState));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][updateActBiddingActState]更新集合竞价发布状态时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[ActBiddingServiceImpl][updateActBiddingActState]更新集合竞价发布状态时出现未知异常");
        }
        return result;
    }

    /**
     * 集合竞价定时器，结束活动，生成尾款订单
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#jobBiddingService()
     */
    @Override
    public ServiceResult<Boolean> jobBiddingService() {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.jobBiddingService());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][jobBiddingService]集合竞价定时器，结束活动，生成尾款订单出现未知异常：" + e);
            result.setSuccess(false);
            result
                .setMessage("[ActBiddingServiceImpl][jobBiddingService]集合竞价定时器，结束活动，生成尾款订单出现未知异常");
        }
        return result;
    }

    /**
     * 前台页面查询集合竞价
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingsFront(java.util.Map, com.ejavashop.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActBidding>> getActBiddingsFront(Map<String, String> queryMap,
                                                               PagerInfo pager) {
        ServiceResult<List<ActBidding>> serviceResult = new ServiceResult<List<ActBidding>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actBiddingModel.getActBiddingsFrontCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<ActBidding> list = actBiddingModel.getActBiddingsFront(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ProductAskService][getActBiddingsFront]查询集合竞价出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][getActBiddingsFront]查询集合竞价发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state3
     * @param auditOpinion
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#updateActBiddingStateAndAuditOpinion(java.lang.Integer, int, java.lang.String)
     */
    @Override
    public ServiceResult<Boolean> updateActBiddingStateAndAuditOpinion(Integer id, int state,
                                                                       String auditOpinion) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingModel.updateActBiddingStateAndAuditOpinion(id, state,
                auditOpinion));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ActBiddingServiceImpl][updateActBiddingStateAndAuditOpinion]审核出现未知异常：" + e);
            result.setSuccess(false);
            result
                .setMessage("[ActBiddingServiceImpl][updateActBiddingStateAndAuditOpinion]审核出现未知异常");
        }
        return result;
    }

    /**
     * 取得该团购所属分类下的前5个商品
     * @param type
     * @param number
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingService#getActBiddingsByType(int, int)
     */
    @Override
    public ServiceResult<List<ActBidding>> getActBiddingsByType(int type, int number) {
        ServiceResult<List<ActBidding>> serviceResult = new ServiceResult<List<ActBidding>>();
        try {
            List<ActBidding> list = actBiddingModel.getActBiddingsByType(type, number);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][getActBiddingsByType]取得该团购所属分类下的前5个商品发生异常:", e);
        }
        return serviceResult;
    }
}