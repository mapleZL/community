package com.ejavashop.service.impl.parter;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.parter.ParterArea;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.model.parter.ParterAreaModel;
import com.ejavashop.service.parter.IParterAreaService;

@Service(value = "parterAreaService")
public class ParterAreaServiceImpl implements IParterAreaService {
	private static Logger      log = LogManager.getLogger(ParterAreaServiceImpl.class);
	
	@Resource
	private ParterAreaModel parterAreaModel;
    
     /**
     * ���idȡ��parter_area����
     * @param  parterAreaId
     * @return
     */
    @Override
    public ServiceResult<ParterArea> getParterAreaById(Integer parterAreaId) {
        ServiceResult<ParterArea> result = new ServiceResult<ParterArea>();
        try {
            result.setResult(parterAreaModel.getParterAreaById(parterAreaId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterAreaService][getParterAreaById]���id["+parterAreaId+"]ȡ��parter_area����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterAreaService][getParterAreaById]���id["+parterAreaId+"]ȡ��parter_area����ʱ����δ֪�쳣��",
                e);
        }
        return result;
    }
    
    /**
     * ����parter_area����
     * @param  parterArea
     * @return
     */
     @Override
     public ServiceResult<Integer> saveParterArea(ParterArea parterArea) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parterAreaModel.saveParterArea(parterArea));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterAreaService][saveParterArea]����parter_area����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterAreaService][saveParterArea]����parter_area����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     
     /**
     * ����parter_area����
     * @param  parterArea
     * @return
     * @see com.ejavashop.service.ParterAreaService#updateParterArea(ParterArea)
     */
     @Override
     public ServiceResult<Integer> updateParterArea(ParterArea parterArea) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(parterAreaModel.updateParterArea(parterArea));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterAreaService][updateParterArea]����parter_area����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterAreaService][updateParterArea]����parter_area����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<ParterArea>> getParterAreaByParterSignId(
			Integer parterSignId,String memberAreaId) {
		ServiceResult<List<ParterArea>> result = new ServiceResult<List<ParterArea>>();
        try {
            result.setResult(parterAreaModel.getParterAreaByParterSignId(parterSignId,memberAreaId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IParterAreaService][getParterAreaByParterSignId]查询合伙人地域信息出现未知异常" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IParterAreaService][getParterAreaByParterSignId]查询合伙人地域信息出现未知异常",
                e);
        }
        return result;
	}
	
	@Override
    public ServiceResult<Boolean> save(String roleId, String[] resArr, SystemAdmin adminUser,String memberName) {
        ServiceResult<Boolean> serRes = new ServiceResult<Boolean>();
        try {

            serRes.setResult(parterAreaModel.save(roleId, resArr,adminUser,memberName));
            serRes.setMessage("保存成功。");
        } catch (Exception e) {
            serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
            e.printStackTrace();
        }
        return serRes;
    }
}