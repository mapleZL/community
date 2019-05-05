package com.ejavashop.model.parter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.parter.ParterPercentReadDao;
import com.ejavashop.dao.shop.write.parter.ParterPercentWriteDao;
import com.ejavashop.entity.parter.ParterPercent;
import com.ejavashop.entity.parter.ParterSign;

@Component
public class ParterPercentModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ParterPercentModel.class);
    
    @Resource
    private ParterPercentWriteDao parterPercentWriteDao;
    @Resource
    private ParterPercentReadDao parterPercentReadDao;
    
    /**
     * ���idȡ��parter_percent����
     * @param  parterPercentId
     * @return
     */
    public ParterPercent getParterPercentById(Integer parterPercentId) {
    	return parterPercentReadDao.get(parterPercentId);
    }
    
    /**
     * ����parter_percent����
     * @param  parterPercent
     * @return
     */
     public Integer saveParterPercent(ParterPercent parterPercent) {
     	return parterPercentWriteDao.insert(parterPercent);
     }
     
     /**
     * ����parter_percent����
     * @param  parterPercent
     * @return
     */
     public Integer updateParterPercent(ParterPercent parterPercent) {
     	this.dbConstrains(parterPercent);
     	return parterPercentWriteDao.update(parterPercent);
     }
     
     public Integer getParterPercentCount(Map<String, String> queryMap) {
         return parterPercentReadDao.getParterPercentCount(queryMap);
     }
     
     public List<ParterPercent> getParterPercent(Map<String, String> queryMap, Integer start, Integer size) {
         return parterPercentReadDao.getParterPercent(queryMap, start, size);
     }
     
     public Boolean percentDelete(Integer parterPercentId){
    	 return parterPercentWriteDao.percentDelete(parterPercentId) > 0 ? true:false;
     }
     
     public List<ParterPercent>getParterPercentByParterSignId(Integer parterSignId){
    	 return parterPercentReadDao.getParterPercentByParterSignId(parterSignId,"");
     }
     
     private void dbConstrains(ParterPercent parterPercent) {
		parterPercent.setType(StringUtil.dbSafeString(parterPercent.getType(), false, 100));
		parterPercent.setParterSignId(StringUtil.dbSafeString(parterPercent.getParterSignId(), false, 11));
     }
}