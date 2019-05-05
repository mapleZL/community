package com.ejavashop.model.parter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.parter.ParterSignReadDao;
import com.ejavashop.dao.shop.write.parter.ParterSignWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.parter.ParterSign;

@Component
public class ParterSignModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ParterSignModel.class);
    
    @Resource
    private ParterSignWriteDao parterSignWriteDao;
    @Resource
    private ParterSignReadDao parterSignReadDao;
    
    /**
     * ���idȡ��parter_sign����
     * @param  parterSignId
     * @return
     */
    public ParterSign getParterSignById(Integer parterSignId) {
    	return parterSignReadDao.get(parterSignId);
    }
    
    /**
     * ����parter_sign����
     * @param  parterSign
     * @return
     */
     public Integer saveParterSign(ParterSign parterSign) {
     	return parterSignWriteDao.insert(parterSign);
     }
     
     public ParterSign getByMemeberSignNo(String signNo) {
    	 return parterSignReadDao.getByMemeberSignNo(signNo);
     }
     
     public List<ParterSign> getByMemeberId(String memberId) {
    	 return parterSignReadDao.getByMemeberId(memberId);
     }
     
     /**
     * ����parter_sign����
     * @param  parterSign
     * @return
     */
     public Integer updateParterSign(ParterSign parterSign) {
     	this.dbConstrains(parterSign);
     	return parterSignWriteDao.update(parterSign);
     }
     
     public Integer getParterSignCount(Map<String, String> queryMap,String type) {
         return parterSignReadDao.getParterSignCount(queryMap,type);
     }
     
     public List<ParterSign> getParterSign(Map<String, String> queryMap, Integer start, Integer size,String type) {
         return parterSignReadDao.getParterSign(queryMap, start, size,type);
     }
     
     
     private void dbConstrains(ParterSign parterSign) {
		parterSign.setMemberId(StringUtil.dbSafeString(parterSign.getMemberId(), false, 11));
		parterSign.setStatus(StringUtil.dbSafeString(parterSign.getStatus(), false, 10));
		parterSign.setCreateMan(StringUtil.dbSafeString(parterSign.getCreateMan(), false, 100));
     }
}