package com.ejavashop.service.impl.wmsinterface;

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
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.entity.wmsinterface.InterfaceWms;
import com.ejavashop.model.wmsinterface.InterfaceWmsModel;
import com.ejavashop.service.wmsinterface.IInterfaceWmsService;

@Service(value = "interfaceWmsService")
public class InterfaceWmsServiceImpl implements IInterfaceWmsService {
	private static Logger      log = LogManager.getLogger(InterfaceWmsServiceImpl.class);
	
	@Resource
	private InterfaceWmsModel interfaceWmsModel;
    
     /**
     * 根据id取得interface_wms对象
     * @param  interfaceWmsId
     * @return
     */
    @Override
    public ServiceResult<InterfaceWms> getInterfaceWmsById(Integer interfaceWmsId) {
        ServiceResult<InterfaceWms> result = new ServiceResult<InterfaceWms>();
        try {
            result.setResult(interfaceWmsModel.getInterfaceWmsById(interfaceWmsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IInterfaceWmsService][getInterfaceWmsById]根据id["+interfaceWmsId+"]取得interface_wms对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IInterfaceWmsService][getInterfaceWmsById]根据id["+interfaceWmsId+"]取得interface_wms对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存interface_wms对象
     * @param  interfaceWms
     * @return
     */
     @Override
     public ServiceResult<Integer> saveInterfaceWms(InterfaceWms interfaceWms) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(interfaceWmsModel.saveInterfaceWms(interfaceWms));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IInterfaceWmsService][saveInterfaceWms]保存interface_wms对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IInterfaceWmsService][saveInterfaceWms]保存interface_wms对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新interface_wms对象
     * @param  interfaceWms
     * @return
     * @see com.ejavashop.service.InterfaceWmsService#updateInterfaceWms(InterfaceWms)
     */
     @Override
     public ServiceResult<Integer> updateInterfaceWms(InterfaceWms interfaceWms) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(interfaceWmsModel.updateInterfaceWms(interfaceWms));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IInterfaceWmsService][updateInterfaceWms]更新interface_wms对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IInterfaceWmsService][updateInterfaceWms]更新interface_wms对象时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<InterfaceWms> getInterfaceByRelationIdAndRelationTable(String relationId,
			String relationTable) {
		ServiceResult<InterfaceWms> result = new ServiceResult<InterfaceWms>();
        try {
            result.setResult(interfaceWmsModel.getInterfaceByRelationIdAndRelationTable(relationId,relationTable));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IInterfaceWmsService][getInterfaceByRelationIdAndRelationTable]查询interface_wms对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IInterfaceWmsService][getInterfaceByRelationIdAndRelationTable]查询interface_wms对象时出现未知异常：",
                e);
        }
        return result;
	}

	@Override
	public ServiceResult<List<InterfaceWms>> page(Map<String, String> queryMap, PagerInfo pager) {
		 ServiceResult<List<InterfaceWms>> serviceResult = new ServiceResult<List<InterfaceWms>>();
	        try {
	            Integer start = 0, size = 0;
	            if (pager != null) {
	                pager.setRowsCount(interfaceWmsModel.pageCount(queryMap));
	                start = pager.getStart();
	                size = pager.getPageSize();
	            }
	            List<InterfaceWms>  interfaceWms = interfaceWmsModel.page(queryMap, start, size);

	            serviceResult.setResult(interfaceWms);
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("[InterfaceWmsServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
	                      + " &param2:" + JSON.toJSONString(pager));
	            log.error("[InterfaceWmsServiceImpl][page] exception:" + e.getMessage());
	        }
	        return serviceResult;
	}

	@Override
	public ServiceResult<Boolean> sendFailOrderMail() {
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(interfaceWmsModel.sendFailOrderMail());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[InterfaceWmsServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
	}
}