package com.ejavashop.service.impl.backgoods;

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
import com.ejavashop.entity.backgoods.BackGoods;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.model.backgoods.BackGoodsModel;
import com.ejavashop.service.backgoods.IBackGoodsService;

@Service(value = "backGoodsService")
public class BackGoodsServiceImpl implements IBackGoodsService {
	private static Logger      log = LogManager.getLogger(BackGoodsServiceImpl.class);
	
	@Resource
	private BackGoodsModel backGoodsModel;
    
     /**
     * 根据id取得back_goods对象
     * @param  backGoodsId
     * @return
     */
    @Override
    public ServiceResult<BackGoods> getBackGoodsById(Integer backGoodsId) {
        ServiceResult<BackGoods> result = new ServiceResult<BackGoods>();
        try {
            result.setResult(backGoodsModel.getBackGoodsById(backGoodsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsService][getBackGoodsById]根据id["+backGoodsId+"]取得back_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsService][getBackGoodsById]根据id["+backGoodsId+"]取得back_goods对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存back_goods对象
     * @param  backGoods
     * @return
     */
     @Override
     public ServiceResult<Integer> saveBackGoods(BackGoods backGoods) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(backGoodsModel.saveBackGoods(backGoods));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsService][saveBackGoods]保存back_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsService][saveBackGoods]保存back_goods对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新back_goods对象
     * @param  backGoods
     * @return
     * @see com.ejavashop.service.BackGoodsService#updateBackGoods(BackGoods)
     */
     @Override
     public ServiceResult<Integer> updateBackGoods(BackGoods backGoods) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(backGoodsModel.updateBackGoods(backGoods));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsService][updateBackGoods]更新back_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsService][updateBackGoods]更新back_goods对象时出现未知异常：",
                e);
        }
        return result;
     }

	/*@Override
	public ServiceResult<List<BackGoods>> page(Map<String, String> queryMap, PagerInfo pager) {
		 ServiceResult<List<BackGoods>> serviceResult = new ServiceResult<List<BackGoods>>();
	        serviceResult.setPager(pager);
	        try {
	            Integer start = 0, size = 0;
	            if (pager != null) {
	                pager.setRowsCount(backGoodsModel.pageCount(queryMap));
	                start = pager.getStart();
	                size = pager.getPageSize();
	            }

	            List<BackGoods> list = backGoodsModel.page(queryMap, start, size);
	            serviceResult.setResult(list);
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("[MemberProductBackServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
	                      + " &param2:" + JSON.toJSONString(pager));
	            log.error("[MemberProductBackServiceImpl][page] exception:" + e.getMessage());
	        }
	        return serviceResult;
	}*/
}