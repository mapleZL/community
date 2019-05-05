package com.ejavashop.service.wmsinterface;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.wmsinterface.BankCard;

public interface IBankCardService {

	/**
     *根据id取得BankCard对象
     * @param  bankCardId
     * @return
     */
    ServiceResult<BankCard> getBankCardById(Integer bankCardId);
    
    /**
     * ����bank_card����
     * @param  bankCard
     * @return
     */
     ServiceResult<Integer> saveBankCard(BankCard bankCard);
     
     /**
     * ����bank_card����
     * @param  bankCard
     * @return
     */
     ServiceResult<Integer> updateBankCard(BankCard bankCard);
     
     
     
     ServiceResult<String>getBankCodeByBankCard(String cardNo);
     
}