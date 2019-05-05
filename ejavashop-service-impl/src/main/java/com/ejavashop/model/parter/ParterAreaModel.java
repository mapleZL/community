package com.ejavashop.model.parter;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.parter.ParterAreaReadDao;
import com.ejavashop.dao.shop.write.parter.ParterAreaWriteDao;
import com.ejavashop.entity.parter.ParterArea;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.entity.system.SystemResourcesRoles;

@Component
public class ParterAreaModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ParterAreaModel.class);
    
    @Resource
    private ParterAreaWriteDao parterAreaWriteDao;
    @Resource
    private ParterAreaReadDao parterAreaReadDao;
    
    @Resource
    private DataSourceTransactionManager transactionManager;
    
    /**
     * ���idȡ��parter_area����
     * @param  parterAreaId
     * @return
     */
    public ParterArea getParterAreaById(Integer parterAreaId) {
    	return parterAreaReadDao.get(parterAreaId);
    }
    
    /**
     * ����parter_area����
     * @param  parterArea
     * @return
     */
     public Integer saveParterArea(ParterArea parterArea) {
     	return parterAreaWriteDao.insert(parterArea);
     }
     
     /**
     * ����parter_area����
     * @param  parterArea
     * @return
     */
     public Integer updateParterArea(ParterArea parterArea) {
     	this.dbConstrains(parterArea);
     	return parterAreaWriteDao.update(parterArea);
     }
     
     public List<ParterArea>getParterAreaByParterSignId(Integer parterSignId,String memberAreaId){
    	 return parterAreaReadDao.getParterAreaByParterSignId(parterSignId,memberAreaId);
     }
     
     public boolean save(String roleId, String[] resArr,SystemAdmin adminUser,String memberName) throws Exception {
         DefaultTransactionDefinition def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         TransactionStatus status = transactionManager.getTransaction(def);
         try {
        	 parterAreaWriteDao.deleteParterAreaByParterSignId(roleId);

             //保存选中的资源
             for (String resId : resArr) {
            	 ParterArea srr = new ParterArea();
            	 srr.setCreateMan(adminUser.getName());
            	 srr.setParterSignId(roleId);
            	 srr.setParterSignName(memberName);
            	 srr.setProvinceId(resId);
            	 srr.setCityId(resId);
            	 srr.setAreaId(resId);
                 parterAreaWriteDao.insert(srr);
             }
             
             transactionManager.commit(status);
         } catch (Exception e) {
             e.printStackTrace();
             transactionManager.rollback(status);
             throw e;
         }
         return true;
     }
     
     private void dbConstrains(ParterArea parterArea) {
		parterArea.setParterSignId(StringUtil.dbSafeString(parterArea.getParterSignId(), false, 11));
		parterArea.setProvinceId(StringUtil.dbSafeString(parterArea.getProvinceId(), false, 11));
		parterArea.setCityId(StringUtil.dbSafeString(parterArea.getCityId(), false, 11));
		parterArea.setAreaId(StringUtil.dbSafeString(parterArea.getAreaId(), false, 11));
		parterArea.setCreateMan(StringUtil.dbSafeString(parterArea.getCreateMan(), false, 100));
     }
}