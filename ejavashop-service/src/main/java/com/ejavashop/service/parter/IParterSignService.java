package com.ejavashop.service.parter;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.parter.ParterSign;

public interface IParterSignService {

	/**
     * ���idȡ��parter_sign����
     * @param  parterSignId
     * @return
     */
    ServiceResult<ParterSign> getParterSignById(Integer parterSignId);
    
    /**
     * ����parter_sign����
     * @param  parterSign
     * @return
     */
     ServiceResult<Integer> saveParterSign(ParterSign parterSign);
     
     /**
     * ����parter_sign����
     * @param  parterSign
     * @return
     */
     ServiceResult<Integer> updateParterSign(ParterSign parterSign);
     
     
     ServiceResult<List<ParterSign>> getParterSign( Map<String, String> queryMap,PagerInfo pager,String type);

	ServiceResult<ParterSign> getByMemeberSignNo(String signNo);
	
	ServiceResult<List<ParterSign>> getByMemeberId(String memberId);
}