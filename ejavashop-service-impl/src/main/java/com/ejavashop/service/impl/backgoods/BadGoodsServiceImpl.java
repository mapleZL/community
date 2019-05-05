package com.ejavashop.service.impl.backgoods;

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
import com.ejavashop.entity.backgoods.BadGoods;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.model.backgoods.BadGoodsModel;
import com.ejavashop.service.backgoods.IBadGoodsService;

@Service(value = "badGoodsService")
public class BadGoodsServiceImpl implements IBadGoodsService {
	private static Logger      log = LogManager.getLogger(BadGoodsServiceImpl.class);
	
	@Resource
	private BadGoodsModel badGoodsModel;
    
     /**
     * 根据id取得bad_goods对象
     * @param  badGoodsId
     * @return
     */
    @Override
    public ServiceResult<BadGoods> getBadGoodsById(Integer badGoodsId) {
        ServiceResult<BadGoods> result = new ServiceResult<BadGoods>();
        try {
            result.setResult(badGoodsModel.getBadGoodsById(badGoodsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBadGoodsService][getBadGoodsById]根据id["+badGoodsId+"]取得bad_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBadGoodsService][getBadGoodsById]根据id["+badGoodsId+"]取得bad_goods对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存bad_goods对象
     * @param  badGoods
     * @return
     */
     @Override
     public ServiceResult<Integer> saveBadGoods(BadGoods badGoods) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(badGoodsModel.saveBadGoods(badGoods));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBadGoodsService][saveBadGoods]保存bad_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBadGoodsService][saveBadGoods]保存bad_goods对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新bad_goods对象
     * @param  badGoods
     * @return
     * @see com.ejavashop.service.BadGoodsService#updateBadGoods(BadGoods)
     */
     @Override
     public ServiceResult<Integer> updateBadGoods(BadGoods badGoods) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(badGoodsModel.updateBadGoods(badGoods));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBadGoodsService][updateBadGoods]更新bad_goods对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBadGoodsService][updateBadGoods]更新bad_goods对象时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<BadGoods>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<BadGoods>> serviceResult = new ServiceResult<List<BadGoods>>();
		serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(badGoodsModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<BadGoods> list = badGoodsModel.page(param);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBadGoodsService][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IBadGoodsService][page] exception:" + e.getMessage());
        }
        return serviceResult;
	}
}