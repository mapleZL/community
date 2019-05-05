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
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.model.parter.ParterSignModel;
import com.ejavashop.service.parter.IParterSignService;

@Service(value = "parterSignService")
public class ParterSignServiceImpl implements IParterSignService {
	private static Logger      log = LogManager.getLogger(ParterSignServiceImpl.class);
	
	@Resource
	private ParterSignModel parterSignModel;
    
     /**
     * ���idȡ��parter_sign����
     * @param  parterSignId
     * @return
     */
    @Override
    public ServiceResult<ParterSign> getParterSignById(Integer parterSignId) {
        ServiceResult<ParterSign> result = new ServiceResult<ParterSign>();
        try {
            result.setResult(parterSignModel.getParterSignById(parterSignId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterSignService][getParterSignById]���id["+parterSignId+"]ȡ��parter_sign����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterSignService][getParterSignById]���id["+parterSignId+"]ȡ��parter_sign����ʱ����δ֪�쳣��",
                e);
        }
        return result;
    }
    
    /**
     * ����parter_sign����
     * @param  parterSign
     * @return
     */
     @Override
     public ServiceResult<Integer> saveParterSign(ParterSign parterSign) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parterSignModel.saveParterSign(parterSign));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterSignService][saveParterSign]����parter_sign����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterSignService][saveParterSign]����parter_sign����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     
     @Override
     public ServiceResult<ParterSign>  getByMemeberSignNo(String signNo) {
     	ServiceResult<ParterSign> result = new ServiceResult<ParterSign>();
        try {
            result.setResult(parterSignModel.getByMemeberSignNo(signNo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterSignService][saveParterSign]����parter_sign����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterSignService][saveParterSign]����parter_sign����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     
     @Override
     public ServiceResult<List<ParterSign>>  getByMemeberId(String memberId) {
     	ServiceResult<List<ParterSign>> result = new ServiceResult<List<ParterSign>>();
        try {
            result.setResult(parterSignModel.getByMemeberId(memberId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterSignService][saveParterSign]����parter_sign����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterSignService][saveParterSign]����parter_sign����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     /**
     * ����parter_sign����
     * @param  parterSign
     * @return
     * @see com.ejavashop.service.ParterSignService#updateParterSign(ParterSign)
     */
     @Override
     public ServiceResult<Integer> updateParterSign(ParterSign parterSign) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parterSignModel.updateParterSign(parterSign));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterSignService][updateParterSign]����parter_sign����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterSignService][updateParterSign]����parter_sign����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<ParterSign>> getParterSign(Map<String, String> queryMap,PagerInfo pager,String type) {
		ServiceResult<List<ParterSign>> serviceResult = new ServiceResult<List<ParterSign>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(parterSignModel, "Property 'parterSignModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(parterSignModel.getParterSignCount(queryMap,type));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(parterSignModel.getParterSign(queryMap, start, size,type));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ParterSignService][getParterSign]查询会员表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ParterSignService][getParterSign]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[MemberService][getMembers]查询会员信息发生异常:", e);
        }
        return serviceResult;
	}
}