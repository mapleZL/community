package com.ejavashop.service.impl.parter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.parter.ParterPercent;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.model.parter.ParterPercentModel;
import com.ejavashop.service.parter.IParterPercentService;

@Service(value = "parterPercentService")
public class ParterPercentServiceImpl implements IParterPercentService {
	private static Logger      log = LogManager.getLogger(ParterPercentServiceImpl.class);
	
	@Resource
	private ParterPercentModel parterPercentModel;
    
     /**
     * ���idȡ��parter_percent����
     * @param  parterPercentId
     * @return
     */
    @Override
    public ServiceResult<ParterPercent> getParterPercentById(Integer parterPercentId) {
        ServiceResult<ParterPercent> result = new ServiceResult<ParterPercent>();
        try {
            result.setResult(parterPercentModel.getParterPercentById(parterPercentId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterPercentService][getParterPercentById]���id["+parterPercentId+"]ȡ��parter_percent����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterPercentService][getParterPercentById]���id["+parterPercentId+"]ȡ��parter_percent����ʱ����δ֪�쳣��",
                e);
        }
        return result;
    }
    
    /**
     * ����parter_percent����
     * @param  parterPercent
     * @return
     */
     @Override
     public ServiceResult<Integer> saveParterPercent(ParterPercent parterPercent) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parterPercentModel.saveParterPercent(parterPercent));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterPercentService][saveParterPercent]����parter_percent����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterPercentService][saveParterPercent]����parter_percent����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     
     /**
     * ����parter_percent����
     * @param  parterPercent
     * @return
     * @see com.ejavashop.service.ParterPercentService#updateParterPercent(ParterPercent)
     */
     @Override
     public ServiceResult<Integer> updateParterPercent(ParterPercent parterPercent) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parterPercentModel.updateParterPercent(parterPercent));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterPercentService][updateParterPercent]����parter_percent����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterPercentService][updateParterPercent]����parter_percent����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     
     @Override
 	public ServiceResult<List<ParterPercent>> getParterPercent(Map<String, String> queryMap,PagerInfo pager) {
 		ServiceResult<List<ParterPercent>> serviceResult = new ServiceResult<List<ParterPercent>>();
         serviceResult.setPager(pager);
         try {
             Assert.notNull(parterPercentModel, "Property 'parterSignModel' is required.");
             Integer start = 0, size = 0;
             if (pager != null) {
                 pager.setRowsCount(parterPercentModel.getParterPercentCount(queryMap));
                 start = pager.getStart();
                 size = pager.getPageSize();
             }
             serviceResult.setResult(parterPercentModel.getParterPercent(queryMap, start, size));
         } catch (BusinessException e) {
             serviceResult.setSuccess(false);
             serviceResult.setMessage(e.getMessage());
             log.error("[ParterPercentService][getParterPercent]查询会员表时出现异常：" + e.getMessage());
         } catch (Exception e) {
             serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
             log.error("[ParterPercentService][getParterPercent]param1:" + JSON.toJSONString(queryMap)
                       + " &param2:" + JSON.toJSONString(pager));
             log.error("[ParterPercentService][getParterPercent]查询会员信息发生异常:", e);
         }
         return serviceResult;
 	}

	@Override
	public ServiceResult<Boolean> percentDelete(Integer parterPercentId) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(parterPercentModel.percentDelete(parterPercentId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterPercentService][percentDelete]查询会员佣金比例表时出现异常" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterPercentService][percentDelete]查询会员佣金比例表时出现异常",
                e);
        }
        return result;
	}

	@Override
	public ServiceResult<List<ParterPercent>> getParterPercentByParterSignId(
			Integer parterSignId) {
		ServiceResult<List<ParterPercent>> result = new ServiceResult<List<ParterPercent>>();
        try {
            result.setResult(parterPercentModel.getParterPercentByParterSignId(parterSignId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterPercentService][percentDelete]查询会员佣金比例表时出现异常" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterPercentService][percentDelete]查询会员佣金比例表时出现异常",
                e);
        }
        return result;
	}
}