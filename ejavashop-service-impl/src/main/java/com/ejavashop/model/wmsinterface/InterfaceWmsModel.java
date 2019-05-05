package com.ejavashop.model.wmsinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.email.SendMail;
import com.ejavashop.dao.shop.read.wmsinterface.InterfaceWmsReadDao;
import com.ejavashop.dao.shop.write.wmsinterface.InterfaceWmsWriteDao;
import com.ejavashop.entity.wmsinterface.InterfaceWms;
import com.ejavashop.util.interfacewms.dawa_ttx_config;

@Component
public class InterfaceWmsModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(InterfaceWmsModel.class);
    
    @Resource
    private InterfaceWmsReadDao interfaceWmsReadDao;
    @Resource
    private InterfaceWmsWriteDao interfaceWmsWriteDao;
    
    /**
     * 根据id取得interface_wms对象
     * @param  interfaceWmsId
     * @return
     */
    public InterfaceWms getInterfaceWmsById(Integer interfaceWmsId) {
    	return interfaceWmsReadDao.get(interfaceWmsId);
    }
    
    /**
     * 保存interface_wms对象
     * @param  interfaceWms
     * @return
     */
     public Integer saveInterfaceWms(InterfaceWms interfaceWms) {
     	this.dbConstrains(interfaceWms);
     	return interfaceWmsWriteDao.insert(interfaceWms);
     }
     
     /**
     * 更新interface_wms对象
     * @param  interfaceWms
     * @return
     */
     public Integer updateInterfaceWms(InterfaceWms interfaceWms) {
     	this.dbConstrains(interfaceWms);
     	return interfaceWmsWriteDao.update(interfaceWms);
     }
     
     public InterfaceWms getInterfaceByRelationIdAndRelationTable(String relationId,String relationTable){
      	return interfaceWmsReadDao.getInterfaceByRelationIdAndRelationTable(relationId,relationTable);
     }
     
     public Integer pageCount(Map<String, String> queryMap) {
         return interfaceWmsReadDao.getCount(queryMap);
     }
     
     public List<InterfaceWms> page(Map<String, String> queryMap,Integer start,Integer size) {
         return interfaceWmsReadDao.page(queryMap,start,size);
     }
     
     public Boolean sendFailOrderMail(){
    	 List<InterfaceWms> list = interfaceWmsReadDao.getIntegerfaceWmsWithSendNoUp3(dawa_ttx_config.ORDERS,3);
    	 StringBuffer body = new StringBuffer();
    	 body.append("尊敬的领导：");
    	 body.append("<br/>");
    	 body.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	 body.append("您好，很抱歉通知您，您家商城的订单又有问题了！");
    	 body.append("<br/>");
    	 if (list != null && list.size() > 0) {
    		 for (InterfaceWms interfaceWms : list) {
    			 body.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    			 body.append("订单号为：").append(interfaceWms.getRelationId()).append(",同步时间为：").append(interfaceWms.getSyncTime());
    			 body.append(",失败次数：").append(interfaceWms.getSendNo()).append(",失败原因：").append(interfaceWms.getErrorMsg());
    			 body.append("<br/>");
    		 }
    		 SendMail sendMail = new SendMail();
    		 List<String> recipientList = new ArrayList<String>();
    		 recipientList.add("lvxiaojun@dawawang.com");
    		 recipientList.add("tiandexu@dawawang.com");
    		 sendMail.send163Email("zhangbin@dawawang.com", "推送失败订单", body.toString(), recipientList);
    	 }
    	 return true;
     }
     
     
     private void dbConstrains(InterfaceWms interfaceWms) {
		interfaceWms.setRalationTable(StringUtil.dbSafeString(interfaceWms.getRalationTable(), false, 200));
		interfaceWms.setSendResult(StringUtil.dbSafeString(interfaceWms.getSendResult(), false, 30));
		interfaceWms.setSyncType(StringUtil.dbSafeString(interfaceWms.getSyncType(), false, 100));
     }
}