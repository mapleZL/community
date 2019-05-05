package com.ejavashop.model.wmsinterface;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.wmsinterface.BankCardReadDao;
import com.ejavashop.dao.shop.write.wmsinterface.BankCardWriteDao;
import com.ejavashop.entity.wmsinterface.BankCard;

@Component
public class BankCardModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(BankCardModel.class);
    
    @Resource
    private BankCardWriteDao bankCardWriteDao;
    @Resource
    private BankCardReadDao bankCardReadDao;
    
    /**
     * ���idȡ��bank_card����
     * @param  bankCardId
     * @return
     */
    public BankCard getBankCardById(Integer bankCardId) {
    	return bankCardReadDao.get(bankCardId);
    }
    
    /**
     * ����bank_card����
     * @param  bankCard
     * @return
     */
     public Integer saveBankCard(BankCard bankCard) {
     	this.dbConstrains(bankCard);
     	return bankCardWriteDao.insert(bankCard);
     }
     
     /**
     * ����bank_card����
     * @param  bankCard
     * @return
     */
     public Integer updateBankCard(BankCard bankCard) {
     	this.dbConstrains(bankCard);
     	return bankCardWriteDao.update(bankCard);
     }
     
     
     public String getBankCodeByBankCard(String cardNo){
    	 String bankCode = "";
    	 if(!"".equals(cardNo) || !StringUtil.isEmpty(cardNo)){
    		 //卡bin为银行卡号的前5位，或前6位，或前7位，或前8位，暂只支持这几种
    		 for (int i = 5; i < 9; i++) {
				String cardBin = cardNo.substring(0, i);
				BankCard  bankCard = bankCardReadDao.getBankCodeByCardBin(cardBin);
				if(bankCard != null){
					bankCode = bankCard.getBankCode();
					break;
				}else{
					continue;
				}
			}
    		 if("".equals(bankCode) || StringUtil.isEmpty(bankCode)){
    			 throw new BusinessException("抱歉，暂不支持该银行支付，请选择其他银行");
    		 }
    		 
    	 }else{
    		 throw new BusinessException("银行卡号为空");
    	 }  	 
    	 
    	 return bankCode;
     }
     
     private void dbConstrains(BankCard bankCard) {
		bankCard.setBankName(StringUtil.dbSafeString(bankCard.getBankName(), false, 200));
		bankCard.setBankCode(StringUtil.dbSafeString(bankCard.getBankCode(), false, 20));
		bankCard.setCardBin(StringUtil.dbSafeString(bankCard.getCardBin(), false, 20));
     }
}