package com.ejavashop.service.impl.wmsinterface;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.wmsinterface.BankCard;
import com.ejavashop.model.wmsinterface.BankCardModel;
import com.ejavashop.service.wmsinterface.IBankCardService;
 

@Service(value = "bankCardService")
public class BankCardServiceImpl implements IBankCardService {
	private static Logger      log = LogManager.getLogger(BankCardServiceImpl.class);
	
	@Resource
	private BankCardModel bankCardModel;
    
     /**
     * ���idȡ��bank_card����
     * @param  bankCardId
     * @return
     */
    @Override
    public ServiceResult<BankCard> getBankCardById(Integer bankCardId) {
        ServiceResult<BankCard> result = new ServiceResult<BankCard>();
        try {
            result.setResult(bankCardModel.getBankCardById(bankCardId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBankCardService][getBankCardById]���id["+bankCardId+"]ȡ��bank_card����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBankCardService][getBankCardById]���id["+bankCardId+"]ȡ��bank_card����ʱ����δ֪�쳣��",
                e);
        }
        return result;
    }
    
    /**
     * ����bank_card����
     * @param  bankCard
     * @return
     */
     @Override
     public ServiceResult<Integer> saveBankCard(BankCard bankCard) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(bankCardModel.saveBankCard(bankCard));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBankCardService][saveBankCard]����bank_card����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBankCardService][saveBankCard]����bank_card����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }
     
     /**
     * ����bank_card����
     * @param  bankCard
     * @return
     * @see com.ejavashop.service.BankCardService#updateBankCard(BankCard)
     */
     @Override
     public ServiceResult<Integer> updateBankCard(BankCard bankCard) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(bankCardModel.updateBankCard(bankCard));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBankCardService][updateBankCard]����bank_card����ʱ����δ֪�쳣��" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBankCardService][updateBankCard]����bank_card����ʱ����δ֪�쳣��",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<String> getBankCodeByBankCard(String cardNo) {
		ServiceResult<String> result = new ServiceResult<String>();
        try {
            result.setResult(bankCardModel.getBankCodeByBankCard(cardNo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBankCardService][getBankCodeByBankCard]根据银行卡号查询银行编码出现未知异常" + e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBankCardService][getBankCodeByBankCard]根据银行卡号查询银行编码出现未知异常",
                e);
        }
        return result;
	}
}