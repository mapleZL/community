package com.phkj.model.order;


import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.order.StAppletOrdersReadDao;
import com.phkj.dao.shop.write.order.StAppletOrdersWriteDao;
import com.phkj.entity.order.StAppletOrders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StAppletOrdersModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(StAppletOrdersModel.class);
    
    @Resource
    private StAppletOrdersReadDao stAppletOrdersDao;

    @Resource
    private StAppletOrdersWriteDao stAppletOrdersWriteDao;

    /**
     * 根据id取得订单
     * @param  stAppletOrdersId
     * @return
     */
    public StAppletOrders getStAppletOrdersById(Integer stAppletOrdersId) {
    	return stAppletOrdersDao.get(stAppletOrdersId);
    }
    
    /**
     * 保存订单
     * @param  stAppletOrders
     * @return
     */
     public Integer saveStAppletOrders(StAppletOrders stAppletOrders) {
     	this.dbConstrains(stAppletOrders);
     	return stAppletOrdersWriteDao.insert(stAppletOrders);
     }
     
     /**
     * 更新订单
     * @param  stAppletOrders
     * @return
     */
     public Integer updateStAppletOrders(StAppletOrders stAppletOrders) {
     	this.dbConstrains(stAppletOrders);
     	return stAppletOrdersWriteDao.update(stAppletOrders);
     }
     
     private void dbConstrains(StAppletOrders stAppletOrders) {
		stAppletOrders.setOrderSn(StringUtil.dbSafeString(stAppletOrders.getOrderSn(), false, 50));
		stAppletOrders.setRelationOrderSn(StringUtil.dbSafeString(stAppletOrders.getRelationOrderSn(), false, 255));
		stAppletOrders.setMemberName(StringUtil.dbSafeString(stAppletOrders.getMemberName(), false, 100));
		stAppletOrders.setInvoiceTitle(StringUtil.dbSafeString(stAppletOrders.getInvoiceTitle(), true, 255));
		stAppletOrders.setInvoiceType(StringUtil.dbSafeString(stAppletOrders.getInvoiceType(), true, 50));
		stAppletOrders.setIp(StringUtil.dbSafeString(stAppletOrders.getIp(), false, 50));
		stAppletOrders.setPaymentName(StringUtil.dbSafeString(stAppletOrders.getPaymentName(), false, 100));
		stAppletOrders.setPaymentCode(StringUtil.dbSafeString(stAppletOrders.getPaymentCode(), false, 100));
		stAppletOrders.setName(StringUtil.dbSafeString(stAppletOrders.getName(), true, 60));
		stAppletOrders.setAddressAll(StringUtil.dbSafeString(stAppletOrders.getAddressAll(), true, 255));
		stAppletOrders.setAddressInfo(StringUtil.dbSafeString(stAppletOrders.getAddressInfo(), true, 255));
		stAppletOrders.setMobile(StringUtil.dbSafeString(stAppletOrders.getMobile(), true, 50));
		stAppletOrders.setEmail(StringUtil.dbSafeString(stAppletOrders.getEmail(), true, 255));
		stAppletOrders.setZipCode(StringUtil.dbSafeString(stAppletOrders.getZipCode(), true, 20));
		stAppletOrders.setRemark(StringUtil.dbSafeString(stAppletOrders.getRemark(), false, 255));
		stAppletOrders.setLogisticsName(StringUtil.dbSafeString(stAppletOrders.getLogisticsName(), false, 100));
		stAppletOrders.setLogisticsNumber(StringUtil.dbSafeString(stAppletOrders.getLogisticsNumber(), false, 100));
		stAppletOrders.setCodconfirmName(StringUtil.dbSafeString(stAppletOrders.getCodconfirmName(), false, 100));
		stAppletOrders.setCodconfirmRemark(StringUtil.dbSafeString(stAppletOrders.getCodconfirmRemark(), false, 255));
		stAppletOrders.setTradeNo(StringUtil.dbSafeString(stAppletOrders.getTradeNo(), true, 255));
		stAppletOrders.setTradeSn(StringUtil.dbSafeString(stAppletOrders.getTradeSn(), true, 255));
		stAppletOrders.setSyncState(StringUtil.dbSafeString(stAppletOrders.getSyncState(), true, 50));
		stAppletOrders.setLogisticsNameOms(StringUtil.dbSafeString(stAppletOrders.getLogisticsNameOms(), true, 100));
     }

	public List<StAppletOrders> getStAppletOrdersList(Integer memberId, int pageNum, int pageSize) {
		return stAppletOrdersDao.getStAppletOrdersList(memberId,pageNum,pageSize);
	}
}