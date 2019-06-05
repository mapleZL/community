package com.phkj.service.impl.seller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.seller.StAppletSeller;
import com.phkj.model.seller.StAppletSellerModel;
import com.phkj.service.seller.IStAppletSellerService;

@Service(value = "stAppletSellerService")
public class StAppletSellerServiceImpl implements IStAppletSellerService {
	private static Logger      log = LogManager.getLogger(StAppletSellerServiceImpl.class);
	
	@Resource
	private StAppletSellerModel stAppletSellerModel;
    
     /**
     * 根据id取得商家表
     * @param  stAppletSellerId
     * @return
     */
    @Override
    public ServiceResult<StAppletSeller> getStAppletSellerById(Integer stAppletSellerId) {
        ServiceResult<StAppletSeller> result = new ServiceResult<StAppletSeller>();
        try {
            result.setResult(stAppletSellerModel.getStAppletSellerById(stAppletSellerId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletSellerService][getStAppletSellerById]根据id["+stAppletSellerId+"]取得商家表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletSellerService][getStAppletSellerById]根据id["+stAppletSellerId+"]取得商家表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存商家表
     * @param  stAppletSeller
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletSeller(StAppletSeller stAppletSeller) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletSellerModel.saveStAppletSeller(stAppletSeller));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletSellerService][saveStAppletSeller]保存商家表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletSellerService][saveStAppletSeller]保存商家表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新商家表
     * @param  stAppletSeller
     * @return
     * @see com.phkj.service.StAppletSellerService#updateStAppletSeller(StAppletSeller)
     */
     @Override
     public ServiceResult<Integer> updateStAppletSeller(StAppletSeller stAppletSeller) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletSellerModel.updateStAppletSeller(stAppletSeller));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletSellerService][updateStAppletSeller]更新商家表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletSellerService][updateStAppletSeller]更新商家表时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public StAppletSeller getSellerByMemberId(Integer memebrId) {
        return stAppletSellerModel.getSellerByMemberId(memebrId);
    }
}