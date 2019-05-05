package com.ejavashop.service.parter;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.parter.ParterPercent;
import com.ejavashop.entity.parter.ParterSign;

public interface IParterPercentService {

	/**
     * ���idȡ��parter_percent����
     * @param  parterPercentId
     * @return
     */
    ServiceResult<ParterPercent> getParterPercentById(Integer parterPercentId);
    
    /**
     * ����parter_percent����
     * @param  parterPercent
     * @return
     */
     ServiceResult<Integer> saveParterPercent(ParterPercent parterPercent);
     
     /**
     * ����parter_percent����
     * @param  parterPercent
     * @return
     */
     ServiceResult<Integer> updateParterPercent(ParterPercent parterPercent);
     
     
     ServiceResult<List<ParterPercent>> getParterPercent( Map<String, String> queryMap,PagerInfo pager);
     
     ServiceResult<List<ParterPercent>> getParterPercentByParterSignId(Integer parterSignId);
     
     ServiceResult<Boolean>percentDelete(Integer parterPercentId);
}