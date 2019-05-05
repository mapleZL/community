package com.ejavashop.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.read.system.SystemAdminReadDao;
import com.ejavashop.dao.shop.write.product.OrderReceiptDetailWriteDao;
import com.ejavashop.dao.shop.write.product.OrderReceiptWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.dao.shop.write.system.SystemAdminWriteDao;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.OrderReceiptDetail;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.util.interfacewms.EnteringWarehouse;
import com.ejavashop.util.interfacewms.dawa_ttx_config;

@Component
public class OrderReceiptModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(OrderReceiptModel.class);

	@Resource
	private OrderReceiptWriteDao orderReceiptWriteDao;
	@Resource
	private OrderReceiptDetailWriteDao orderReceiptDetailWriteDao;
	@Resource
	private SellerWriteDao sellerWriteDao;
	@Resource
	private DataSourceTransactionManager transactionManager;
	@Resource
	private SellerReadDao sellerReadDao;
	@Resource
	private SystemAdminWriteDao systemAdminWriteDao;
	@Resource
	private SystemAdminReadDao systemAdminReadDao;

	/**
	 * 根据id取得order_receipt对象
	 * 
	 * @param orderReceiptId
	 * @return
	 */
	public OrderReceipt getOrderReceiptById(Integer orderReceiptId) {
		return orderReceiptWriteDao.get(orderReceiptId);
	}

	/**
	 * 保存order_receipt对象
	 * 
	 * @param orderReceipt
	 * @return
	 */
	public Integer saveOrderReceipt(OrderReceipt orderReceipt) {
		this.dbConstrains(orderReceipt);
		return orderReceiptWriteDao.insert(orderReceipt);
	}

	/**
	 * 更新order_receipt对象
	 * 
	 * @param orderReceipt
	 * @return
	 */
	public Integer updateOrderReceipt(OrderReceipt orderReceipt) {
		this.dbConstrains(orderReceipt);
		return orderReceiptWriteDao.update(orderReceipt);
	}

	public Integer pageCount(Map<String, Object> queryMap) {
		return orderReceiptWriteDao.getCount(queryMap);
	}

	public List<OrderReceipt> page(Map<String, Object> queryMap) throws Exception {
		List<OrderReceipt> list = orderReceiptWriteDao.page(queryMap);
		if (null != list && list.size() > 0) {
			for (OrderReceipt or : list) {
				SystemAdmin admin = systemAdminReadDao.get(or.getSystemId());
				if (null != admin && !StringUtil.isEmpty(admin.getName())) {
					or.setSystemUserName(admin.getName());
				}
				Seller seller = sellerReadDao.get(or.getSellerId());
				if (null != seller && !StringUtil.isEmpty(seller.getName())) {
					or.setSellerName(seller.getSellerName());
				}
				if (or.getUpdateId() != null) {
					admin = systemAdminReadDao.get(or.getSystemId());
					or.setUpdateName(admin.getName());
				}
			}
		}
		return list;
	}

	private void dbConstrains(OrderReceipt orderReceipt) {
	}

	public List<OrderReceiptDetail> getReceiptProductDetailByOrdersId(String ordersId) {
		return orderReceiptWriteDao.getReceiptProductDetailByOrdersId(ordersId);
	}

	public void auditSendGoods(String ordersId, String type) {
		Integer status = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		if (type.equals("pase")) {// 通过
			status = 1;
		}
		if (type.equals("unpase")) {// 不通过
			status = 2;
		}
		map.put("status", status);
		map.put("ordersId", ordersId);
		orderReceiptWriteDao.auditSendGoods(map);
		// 审核通过 推送商品给wms
		Map<String, Object> xmlMap = new HashMap<>();
		// 入库单同步 wms字段
		// 货主编码,仓库编码,入库单入库总数量,订单明细行数,入库单编码,入库类型（采购入库、退货入库）,预计到货日期（如：2010-01-01）,
		// 供应商编码,供应商名称,货品编码（商品需要提前同步）,货品名称,入库数量,商品单价,入库批号,备注,自定义字段1,自定义字段2,自定义字段3,自定义字段4
		// public static final String TTX_OUT__SYNC_WORDS =
		// "Company,WareHouse,TotalQty,TotalLines,ReceiptId,ReceiptType,OrderDate,VendorCode"
		// +
		// "VendorName,Item,ItemName,ItemCount,Sprice,Lot,Remark,UserDef1,UserDef2,UserDef3,UserDef4";
		OrderReceipt orderReceipt = orderReceiptWriteDao.getByOrdersId(ordersId);
		List<Map<String, Object>> list = new ArrayList<>();
		List<OrderReceiptDetail> orderReceiptDetailList = orderReceiptWriteDao
				.getReceiptProductDetailByOrdersId(ordersId);
		for (OrderReceiptDetail ord : orderReceiptDetailList) {
			Map<String, Object> lm = new HashMap<>();
			Map<String, Object> mmm = new HashMap<>();
			// 货品编码
			lm.put("Item", ord.getSKU());
			// 货品名称
			lm.put("ItemName", ord.getSKU() + "");
			// 货品数量
			lm.put("ItemCount", "" + ord.getNumberProbaby().intValue());
			// 商品单价
			// lm.put("Sprice", "1");
			// 入库批号
			lm.put("Lot", "1");
			// 备注
			// lm.put("Remark", "");
			mmm.put("Item", lm);
			list.add(mmm);
		}
		// 货品编码
		xmlMap.put("Items", list);
		xmlMap.put(dawa_ttx_config.RELATION_ID, "" + orderReceipt.getOrdersId());
		xmlMap.put(dawa_ttx_config.RELATION_TABLE, dawa_ttx_config.ORDER_RECEIPT);
		if (orderReceipt != null) {
			Integer sellerId = orderReceipt.getSellerId();
			Seller seller = sellerReadDao.get(sellerId);
			if (seller != null) {
				// xmlMap.put("Company", seller.getSellerName());
				if("10001".equals(seller.getSellerCode())){
					xmlMap.put("Company", "MTY");
				}else{
					xmlMap.put("Company", seller.getSellerCode());
				}
				xmlMap.put("WareHouse", dawa_ttx_config.WAREHOUSE_CODE);
				xmlMap.put("TotalQty", "" + orderReceipt.getTotalAmount().intValue());
				xmlMap.put("TotalLines", "" + orderReceiptDetailList.size());
				xmlMap.put("ReceiptId", orderReceipt.getOrdersId());
				xmlMap.put("ReceiptType", "正品入库");
				xmlMap.put("OrderDate", orderReceipt.getDateProbaby().substring(0, 10));
				// 供应商编码
				// xmlMap.put("VendorCode", "");
				// 供应商名称
				// xmlMap.put("VendorName", "");

				try {
					EnteringWarehouse.goodsOwnerSync(xmlMap, dawa_ttx_config.TTX_OUT__SYNC_WORDS,
							dawa_ttx_config.DAWA_OUT_SYNC_WORDS, dawa_ttx_config.SUBJECT_HEADER15);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				throw new BusinessException("查询商家为空");
			}
		} else {
			throw new BusinessException("查询预约入库单为空");
		}
	}
	
	public OrderReceipt getByOrdersId(String ordersSn){
		return orderReceiptWriteDao.getByOrdersId(ordersSn);
	}
}