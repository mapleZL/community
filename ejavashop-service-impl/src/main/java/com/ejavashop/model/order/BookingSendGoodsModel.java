package com.ejavashop.model.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberAddressReadDao;
import com.ejavashop.dao.shop.read.operate.ConfigReadDao;
import com.ejavashop.dao.shop.read.order.BookingSendGoodsReadDao;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.read.order.SendGoodsRecordReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerTransportReadDao;
import com.ejavashop.dao.shop.write.backgoods.BackGoodsRecordWriteDao;
import com.ejavashop.dao.shop.write.backgoods.BackGoodsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberAddressWriteDao;
import com.ejavashop.dao.shop.write.order.BookingSendGoodsWriteDao;
import com.ejavashop.dao.shop.write.order.OrderLogWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.dao.shop.write.order.SendGoodsRecordWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.entity.backgoods.BackGoods;
import com.ejavashop.entity.backgoods.BackGoodsRecord;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.operate.Config;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.entity.order.OrderLog;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.order.SendGoodsRecord;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.seller.SellerTransport;
import com.ejavashop.vo.order.OrderSuccessVO;

@Component
public class BookingSendGoodsModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(BookingSendGoodsModel.class);
    
    @Resource
    private BookingSendGoodsWriteDao bookingSendGoodsWriteDao;
    @Resource
    private BookingSendGoodsReadDao bookingSendGoodsReadDao;
    
    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private OrdersReadDao                  ordersReadDao;
    
    @Resource
    private OrdersProductWriteDao   ordersProductWriteDao;
    @Resource
    private OrdersProductReadDao   ordersProductReadDao;
    
    @Resource
    private SendGoodsRecordWriteDao sendGoodsRecordWriteDao;
    @Resource
    private SendGoodsRecordReadDao sendGoodsRecordReadDao;
    
    @Resource
    private ProductGoodsWriteDao productGoodsWriteDao;
    @Resource
    private ProductGoodsReadDao productGoodsReadDao;
    
    @Resource
    private ProductWriteDao productWriteDao;
    @Resource
    private ProductReadDao productReadDao;
    
    @Resource
    private BackGoodsWriteDao backGoodsWriteDao;
    
    @Resource
    private BackGoodsRecordWriteDao backGoodsRecordWriteDao;
    
    @Resource
    private DataSourceTransactionManager    transactionManager;
    
    @Resource
    private MemberAddressWriteDao memberAddressWriteDao;
    @Resource
    private MemberAddressReadDao memberAddressReadDao;
    
    @Resource
    private ConfigReadDao                   configReadDao;
    
    @Resource
    private OrderLogWriteDao                orderLogWriteDao;
    
    @Resource
    private SellerTransportReadDao      sellerTransportReadDao;
    
    /**
     * 根据id取得booking_send_goods对象
     * @param  bookingSendGoodsId
     * @return
     */
    public BookingSendGoods getBookingSendGoodsById(Integer bookingSendGoodsId) {
    	return bookingSendGoodsReadDao.get(bookingSendGoodsId);
    }
    
    /**
     * 保存booking_send_goods对象
     * @param  bookingSendGoods
     * @return
     */
     public Integer saveBookingSendGoods(BookingSendGoods bookingSendGoods) {
     	this.dbConstrains(bookingSendGoods);
     	return bookingSendGoodsWriteDao.insert(bookingSendGoods);
     }
     
     /**
     * 更新booking_send_goods对象
     * @param  bookingSendGoods
     * @return
     */
     public Integer updateBookingSendGoods(BookingSendGoods bookingSendGoods) {
     	this.dbConstrains(bookingSendGoods);
     	return bookingSendGoodsWriteDao.update(bookingSendGoods);
     }
     
     public Integer pageCount(Map<String, Object> queryMap) {
         return bookingSendGoodsReadDao.getCount(queryMap);
     }

     public List<BookingSendGoods> page(Map<String, Object> queryMap) throws Exception {
    	 return bookingSendGoodsReadDao.page(queryMap);
     }
     
     
     public Boolean returngoods(Integer id,Integer status,String checkNote){
    	 return bookingSendGoodsWriteDao.returngoods(id,status,checkNote) > 1;
     }
     
     
     
     public void auditSendGoods(Integer id,String type,String checkMan,String checkNote){
    	 int status = 1;
    	 if(type != null){
    		    	 if(type.equals("pass")){
    		    		 status = 3;
    		    	 }
    		    	  if(type.equals("unpase")){
    		    		 status = 6;
    		    	 }
    	 }
    	 Map<String,Object>map = new HashMap<>();
    	 map.put("id", id);
    	 map.put("status", status);
    	 map.put("checkMan", checkMan);
    	 map.put("checkNote", checkNote);
    	 bookingSendGoodsWriteDao.auditSendGoods(map);
    	 
     }
     
     
     public void delGoods(Integer id){
    	 bookingSendGoodsWriteDao.delGoods(id);
     }
     
     public Boolean saveSendGoods(Map<String, String[]> map){
    	 Integer result = 0;
         DefaultTransactionDefinition def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         TransactionStatus status = transactionManager.getTransaction(def);
         try {
    	 String [] num = map.get("num");
         String [] orderGoodsId =map.get("orderGoodsId");
        // String [] productName =map.get("productName"); 
        // String [] normName =map.get("normName"); 
         String [] productSku =map.get("productSku"); 
         String [] deliveredNum =map.get("deliveredNum"); 
         String orderSn =map.get("orderSn")[0];
         String memberName =map.get("memberName")[0];
         String addressAll =map.get("addressAll")[0];
         String addressInfo =map.get("addressInfo")[0];
         String zipCode =map.get("zipCode")[0];
         String mobile =map.get("mobile")[0];
         String orderId = map.get("orderId")[0];
         String [] number = map.get("number");
         //验证 本次发货数量要小于剩余数量
         if(num != null){
        	 for (int i = 0; i < num.length; i++) {
				if( (Integer.parseInt(number[i])-Integer.parseInt(deliveredNum[i])) <  Integer.parseInt(num[i])){
					throw new BusinessException("本次发货数量不能大于剩余数量");
				}
			}
         }
         int sendNum=0;
         for(int i=0;i<num.length;i++){
        	 int numNow = 0;
        	 if(num[i] != null && !num[i].equals("")){
        		 numNow = Integer.parseInt(num[i]);
        	 }
        	 sendNum = sendNum+numNow;
         }
         BookingSendGoods bsGoods = new BookingSendGoods();
         bsGoods.setIsCodconfim(0);
         bsGoods.setMobile(mobile);
         bsGoods.setStatus(1);
         bsGoods.setSendAddress(addressAll);
         bsGoods.setAddressDetail(addressInfo);
         bsGoods.setSendNum(sendNum);
         bsGoods.setOrdersId(orderId);
         bsGoods.setName(memberName);
         bsGoods.setBookOrdersSn(RandomUtil.getOrderSn());
         bsGoods.setZipCode(Integer.parseInt(zipCode));
    	 bookingSendGoodsWriteDao.insert(bsGoods);
    	 int bookGoodsId = bsGoods.getId();
    	 Integer ordersId = Integer.parseInt(orderId);
    	 
    	 Orders orders = ordersReadDao.get(ordersId);
    	 //设置订单已发货数量
    	 if(orders != null){
    		 Integer deliveredSum = orders.getDeliveredSum();
    		 orders.setDeliveredSum(deliveredSum+sendNum);
    		 ordersWriteDao.update(orders);
    	 }else{
    		 log.error("[BookingSendGoodsModel][saveSendGoods]查询订单信息时失败。");
    		 throw new BusinessException("查询订单信息为空");
    	 }
         for(int i=0;i<num.length;i++){
        	 SendGoodsRecord sgGoods = new SendGoodsRecord();
             sgGoods.setBookingGoodsId(bookGoodsId);
             int numNow1 = 0;
        	 if(num[i] != null && !num[i].equals("")){
        		 numNow1 = Integer.parseInt(num[i]);
        	 }
        	 sgGoods.setDeliveredSum(numNow1);
        	 sgGoods.setSku(productSku[i]);
        	 sgGoods.setOrderGoodsId(Integer.parseInt(orderGoodsId[i]));
        	 //更新 订单商品已发货的数量
        	 OrdersProduct orderProduct = ordersProductReadDao.get(Integer.parseInt(orderGoodsId[i]));
        	 if(orderProduct != null){
         		orderProduct.setDeliveredNum(Integer.parseInt(deliveredNum[i])+Integer.parseInt(num[i]));
         		ordersProductWriteDao.update(orderProduct);
         	}else{
         		log.error("[BookingSendGoodsModel][saveSendGoods]查询订单商品信息时失败。");
       		 throw new BusinessException("查询订单商品信息为空");
       	 }
        	 sendGoodsRecordWriteDao.insert(sgGoods);
         }
         transactionManager.commit(status);
         } catch (Exception e) {
             log.error("保存三方仓储订单时出现未知异常：" + e);
             transactionManager.rollback(status);
             throw e;
         }
    	 return result >1;
    	 
     }
     
     public Boolean updateSellerStack(BookingSendGoods  bsGoods , List<SendGoodsRecord> recList,String checkMan){
    	 DefaultTransactionDefinition def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         TransactionStatus status = transactionManager.getTransaction(def);
         BackGoods backGoods = new BackGoods();
         BackGoodsRecord backGoodsRecord = new BackGoodsRecord();
         try {
    	 if(bsGoods != null){
    		 String ordersId = bsGoods.getOrdersId();
    		 Orders orders =  ordersReadDao.get(Integer.parseInt(ordersId));
    		 if(orders != null){
    			 Integer sellerId = orders.getSellerId();
    			 Integer productId =null;
    			 int backNum = 0;
    			 backGoods.setOrdersId(Integer.parseInt(ordersId));
    			 backGoods.setOrdersSn(orders.getOrderSn());
    			 backGoods.setStatus(1);
    			 for (SendGoodsRecord sendGoodsRecord : recList) {
    				 int deliveredSum = sendGoodsRecord.getDeliveredSum();
    				 backNum = backNum + deliveredSum;    				 
    			 }
    			 
    			 backGoods.setBackNum(backNum);
    			 backGoods.setMobile(orders.getMobile());
    			 backGoods.setEmail(orders.getEmail());
    			 backGoods.setWellNum(backNum);
    			 backGoods.setBadNum(0);
    			 backGoods.setBackMan(bsGoods.getBookingUser());
    			 backGoods.setBackMemberId(sellerId);
    			 backGoods.setCheckTime(new Date());
    			 backGoods.setCheckMan(checkMan);
    			 
    			 
    			 backGoodsWriteDao.insert(backGoods);
    			 Integer backGoodsId = backGoods.getId();
    			 
    			 //更新商品详情库存
    			 for (SendGoodsRecord sendGoodsRecord : recList) {
					Integer orderGoodsId = sendGoodsRecord.getOrderGoodsId();
					String sku = sendGoodsRecord.getSku();
					int deliveredSum = sendGoodsRecord.getDeliveredSum();
					
					OrdersProduct ordersProduct = ordersProductWriteDao.get(orderGoodsId);
					Integer productGoodsId = null;
					if(ordersProduct != null ){
							productId = ordersProduct.getProductId();
							productGoodsId = ordersProduct.getProductGoodsId();
							ProductGoods productGoods = productGoodsWriteDao.get(productGoodsId);
							productGoods.setProductStock((productGoods.getProductId()+deliveredSum));
							productGoodsWriteDao.update(productGoods);
					}
					
					backGoodsRecord.setBackNum(deliveredSum);
					backGoodsRecord.setProductSku(sku);
					backGoodsRecord.setBackGoodsId(backGoodsId);
					backGoodsRecord.setBadNum(0);
					backGoodsRecord.setWellNum(deliveredSum);
					backGoodsRecord.setProductId(productGoodsId);
					backGoodsRecordWriteDao.insert(backGoodsRecord);
					
				}
    			 //更新商品总库存
    			 Product product = productReadDao.get(productId);
    			 if(product != null ){
    				 product.setProductStock((product.getProductStock()+backNum));
    				 productWriteDao.update(product);
    			 }
    			 bsGoods.setStatus(8);
    			 bsGoods.setCheckMan(checkMan);
    			 bsGoods.setCheckTime(new Date());
    			 bsGoods.setUpdateTime(new Date());
    			 bookingSendGoodsWriteDao.update(bsGoods);
    			 
    		 }else{
        		 throw new BusinessException("数据有误");
        	 }
    	 }else{
    		 throw new BusinessException("数据有误");
    	 }
    	 transactionManager.commit(status);
         } catch (Exception e) {
             log.error("退货更新库存时出现未知异常：" + e);
             transactionManager.rollback(status);
             throw e;
         }
    	 return true;
     }
     
     public OrderSuccessVO saveThreeSendOrders(Map<String, String[]>map,Member member,String nums,String productSkus,String orderGoodsIds ,Integer logisticsId){
    	 DefaultTransactionDefinition def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         TransactionStatus status = transactionManager.getTransaction(def);
         // 初始化返回的参数
         OrderSuccessVO orderSuccVO = new OrderSuccessVO();
         
         List<BookingSendGoods> ordersList = new ArrayList<>();
        /// Config config = null;
         try {
	    	 if(map != null && map.size() >0){
		         // 初始默认为订单没有支付，如果余额支付全款则重设为true
		         orderSuccVO.setIsPaid(false);
		          // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
		         orderSuccVO.setGoJumpPayfor(true);
		          // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
		         // orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
		          //orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());
    		 
	    		 String ordersId =  map.get("ordersId")[0];
	    		 //String orderSn = map.get("orderSn")[0];
	    		 String memberName = map.get("memberName1")[0];
	    		 String zipCode = map.get("zipCode1")[0];
	    		 String mobile = map.get("mobile1")[0];
	    		 //收货地址
	    		 String memberAddressId = map.get("addressId")[0];
	    		 
	    		 String orderGoodsId [] = orderGoodsIds.split("\\,");
	    		 //String productName [] = map.get("productName");
	    		 //String normName [] = map.get("normName");
	    		 String productSku [] = productSkus.split("\\,");
	    		 //商品总数量
	    		// String number [] = map.get("number");
	    		 //已发货数量
	    		// String deliveredNum [] = map.get("deliveredNum");
	    		 //本次发货数量
	    		 String num [] =nums.split("\\,");
	    		 
	    		 //运费
	    		 String takePrice = map.get("takePrice")[0];
	    		 
	    		 
	    		 //余额账户支付总金额
	    		 //String moneyPaidBalance = map.get("balance")[0];
	    		 //现金支付金额
	    		// String moneyPaidReality = map.get("moneyPaidReality")[0];
	    		 
	    		 //发票状态
	    		// String invoiceStatus = map.get("invoiceStatus")[0];
	    		 //发票类型
	    		// String invoiceType = map.get("invoiceType")[0];
	    		 //发票抬头
	    		 //String invoiceTitle = map.get("invoiceTitle")[0];
	    		 //积分
	    		 //String integral = map.get("integral")[0];
	    		 
	    		 BookingSendGoods bookingSendGoods = new BookingSendGoods();
	    		 
	    		 //商品价格为0
	    		 bookingSendGoods.setMoneyProduct(new BigDecimal(0));
	    		 //设置运费
	    		 bookingSendGoods.setTakePrice(new BigDecimal(takePrice));
	    		 //设置订单总金额 等于商品总金额＋运费-优惠金额总额
	    		 bookingSendGoods.setMoneyOrder(new BigDecimal(takePrice));
	    		 //余额账户支付总金额
		    	//if(!"".equals(moneyPaidBalance)){
	    			// bookingSendGoods.setMoneyPaidBalance(new BigDecimal(moneyPaidBalance));
	    		// }else{
	    			// bookingSendGoods.setMoneyPaidBalance(new BigDecimal(0));
	    		// }
	    		 //现金支付金额
	    		 //bookingSendGoods.setMoneyPaidReality(new BigDecimal(moneyPaidReality));
	    		 //发票状态
//			    		 if (invoiceStatus != null  && !"".equals(invoiceStatus)) {
//			    			 bookingSendGoods.setInvoiceStatus(Integer.parseInt(invoiceStatus));
//			    		 }
//			    		 bookingSendGoods.setInvoiceTitle(invoiceTitle);
//			    		 bookingSendGoods.setInvoiceType(invoiceType);
	    		
	    		 bookingSendGoods.setMoneyPaidReality(new BigDecimal(takePrice));
	    		 //支付方式
	    		 String paymentCode = map.get("paymentCode")[0];
	    		 //支付名称
	    		 String paymentName = map.get("paymentName")[0];
	    		 
	    		 
	    		 bookingSendGoods.setPaymentName(paymentName);
	    		 bookingSendGoods.setPayMan(member.getName());
	    		 bookingSendGoods.setPaymentCode(paymentCode);
	    		 
	    		 if(paymentCode !=null && !"".equals(paymentCode)){
	    			    //货到付款 ONLINE TAKESELF
	    			 	if(paymentCode.equals("OFFLINE")){
	    			 		bookingSendGoods.setPayType("货到付款");
	    			 		orderSuccVO.setGoJumpPayfor(false);
	    			 	    orderSuccVO.setPaymentCode("货到付款");
	    			 	}
	    			 	if(paymentCode.equals("ONLINE")){
	    			 		bookingSendGoods.setPayType("在线支付");
	    			 		orderSuccVO.setGoJumpPayfor(true);
	    			 		orderSuccVO.setPaymentCode("在线支付");
	    			 	}
	    			 	if(paymentCode.equals("TAKESELF")){
	    			 		bookingSendGoods.setPayType("上门自提");
	    			 		orderSuccVO.setGoJumpPayfor(false);
	    			 		 orderSuccVO.setPaymentCode("上门自提");
	    			 	}
	    		 }
	    		 
	    		 orderSuccVO.setBanlancePayMoneyVO(new BigDecimal(0));
	    		 
	    		 orderSuccVO.setBanlancePayVO(1);
	    		 
	    		 orderSuccVO.setPayOrderAllsVO(new BigDecimal(takePrice));
	    		 
	    		 orderSuccVO.setIsPaid(false);
	    		 
	    		 orderSuccVO.setPaymentName(paymentName);
	    		 
	    		 orderSuccVO.setIsBanlancePay(false);
	    		 
	    		 orderSuccVO.setPayAmount(new BigDecimal(takePrice));
	    		 
	    		 //根据memberAddressId 查询地址信息
	    		 MemberAddress memberAddress = memberAddressReadDao.get(Integer.parseInt(memberAddressId));
	    		 if(memberAddress != null ){
	    			 bookingSendGoods.setProvinceId(memberAddress.getProvinceId());
	    			 bookingSendGoods.setCityId(memberAddress.getCityId());
	    			 bookingSendGoods.setAreaId(memberAddress.getAreaId());
	    			 bookingSendGoods.setAddressDetail(memberAddress.getAddressInfo());
	    		 }else{
	    			 throw new BusinessException("查询会员地址信息为空");
	    		 }
	    		 
	    		 if(!"".equals(logisticsId) && logisticsId!=0){
	    		     SellerTransport sTransport = sellerTransportReadDao.getInUseBySellerId(1, logisticsId);
	    		     bookingSendGoods.setLogisticsId(logisticsId);
	    		     bookingSendGoods.setLogisticsName(sTransport.getTransName());
	    		 }else{
	    		     bookingSendGoods.setLogisticsId(0);
                     bookingSendGoods.setLogisticsName("");
	    		 }
	    		 int nowSend = 0;
	    		 //统计本次发货总数量
	    		 for (int i = 0; i < num.length; i++) {
	    			 if(num[i] != null && !num[i].equals("")){
	    				 nowSend = nowSend+Integer.parseInt(num[i]);
	    			 }
				}
	    		 bookingSendGoods.setBookingUser(member.getName());
	    		 bookingSendGoods.setCodconfirmId(0);
	    		 bookingSendGoods.setCodconfirmState(0);
	    		 bookingSendGoods.setCreateTime(new Date());
	    		 bookingSendGoods.setMobile(mobile);
	    		 bookingSendGoods.setName(memberName);
	    		 bookingSendGoods.setOrdersId(ordersId);
	    		 bookingSendGoods.setStatus(1);
	    		 bookingSendGoods.setIsDelete(0);
	    		 if(zipCode != null && !zipCode.equals("")){
	    			 bookingSendGoods.setZipCode(Integer.parseInt(zipCode));
	    		 }
	    		 
	    		 
	    		 bookingSendGoods.setSendNum(nowSend);
	    		 
	    		 bookingSendGoods.setUpdateTime(new Date());
	    		 
	    		 bookingSendGoods.setSendTime(new Date());
	    		 
	    		 bookingSendGoods.setBookOrdersSn(RandomUtil.getOrderSn());
	    		 
	    		 Integer bookingSendGoodsId = bookingSendGoodsWriteDao.insert(bookingSendGoods);
	    		 
	    		 ordersList.add(bookingSendGoods);
	    		 
	    		 orderSuccVO.setBookList(ordersList);
	    		 
	    		 orderSuccVO.setRelationOrderSn(bookingSendGoods.getBookOrdersSn());
	    		 
	    		 //更新订单中已发货总数量
	    		 Orders orders = ordersReadDao.get(Integer.parseInt(ordersId));
	    		 if(orders != null){
	    			orders.setDeliveredSum((orders.getDeliveredSum()+nowSend));
	    			 ordersWriteDao.update(orders);
	    		 }else{
	    			 throw new BusinessException("数据有误");
	    		 }
	    		 
	    		 //更新ordersProduct中单个sku 商品的已发货数量
	    		 for (int i = 0; i < orderGoodsId.length; i++) {
					OrdersProduct ordersProduct = ordersProductReadDao.get(Integer.parseInt(orderGoodsId[i]));
					if(ordersProduct != null){
						ordersProduct.setDeliveredNum((ordersProduct.getDeliveredNum()+Integer.parseInt(num[i])));
						ordersProductWriteDao.update(ordersProduct);
					}else{
						throw new BusinessException("查询订单商品详情时出错");
					}
				}
	    		 
	    	   //保存 GoodsSendRecord 发货记录
	    	   for (int i = 0; i < num.length; i++) {
	    		   if(num[i] != null  && !num[i].equals("")){
	    			   SendGoodsRecord sendGoodsRecord = new SendGoodsRecord();
	    			   sendGoodsRecord.setBookingGoodsId(bookingSendGoodsId);
	    			   sendGoodsRecord.setDeliveredSum(Integer.parseInt(num[i]));
	    			   sendGoodsRecord.setOrderGoodsId(Integer.parseInt(orderGoodsId[i]));
	    			   sendGoodsRecord.setSku(productSku[i]);
	    			   sendGoodsRecordWriteDao.insert(sendGoodsRecord);
	    		   }
			  }	
	    	   
//			    	   // 至少消耗积分换算比例的积分数量
//		                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
//			    	    // 转换总金额
//		               int moneyIntegral = 0;
//		                // 计算订单使用积分金额（都计算成整数）
//			    	   if(!"".equals(integral) && integral !="" && integral != null){
//			    		// 计算转换总金额
//		                    moneyIntegral =Integer.parseInt(integral) / config.getIntegralScale();
//			    		   
//			    	   }
	    	   //只能使用余额
	    	   
		    	   
	    	 }else{
	    		 throw new BusinessException("参数有误");
	    	 }
    	 transactionManager.commit(status);
         } catch (Exception e) {
        	 e.printStackTrace();
             log.error("front 三方仓储发货保存订单时出现未知异常：" + e);
             transactionManager.rollback(status);
             throw e;
         }
    	 return orderSuccVO;
     } 
     
     public boolean jobSystemCancelBookOrder() {

         // 获取当前时间1天之前的时间
         Calendar calendar = Calendar.getInstance();
         calendar.add(Calendar.DAY_OF_MONTH, -1);

         String cancelTime = TimeUtil.getDateTimeString(calendar.getTime());

         // 获取下单24小时还未付款的订单
         List<BookingSendGoods> bookList = bookingSendGoodsReadDao.getBookList(cancelTime);
         if (bookList != null && bookList.size() > 0) {
             // 单条数据处理异常不影响其他数据执行
             for (BookingSendGoods bookingSendGoods : bookList) {
		         DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		         TransactionStatus status = transactionManager.getTransaction(def);
		         try {
		        	 BookingSendGoods bgGoods = new BookingSendGoods();
		        	 bgGoods.setId(bookingSendGoods.getId());
		        	 bgGoods.setPayStatus(0);
		        	 //10 已作废
		        	 bgGoods.setStatus(10);
		        	 Integer backNum = null;
		        	 List<SendGoodsRecord>  recordList =  sendGoodsRecordReadDao.getRecordByGoodsId(bookingSendGoods.getId());
		        	 if(recordList != null  && recordList.size() >0){
		        		 for (SendGoodsRecord sendGoodsRecord : recordList) {
		        			 backNum = backNum+sendGoodsRecord.getDeliveredSum();
		        			 OrdersProduct ordersProduct =   ordersProductReadDao.get(sendGoodsRecord.getOrderGoodsId());
		        			 if(ordersProduct != null){
		        				 ordersProduct.setDeliveredNum(ordersProduct.getDeliveredNum()-sendGoodsRecord.getDeliveredSum());
		        				 ordersProductWriteDao.update(ordersProduct);
		        			 }else{
		        				 throw new BusinessException("查询订单商品失败");
		        			 }
						}
		        	 }
		        	 
		        	Orders orders =  ordersReadDao.get(Integer.parseInt(bgGoods.getOrdersId()));
		        	 if(orders != null){
		        		 orders.setDeliveredSum(backNum);
		        		 ordersWriteDao.update(orders);
		        	 }else{
		        		 throw new BusinessException("查询订单失败");
		        	 }
		        	 Integer update =  bookingSendGoodsWriteDao.update(bookingSendGoods);
		        	 if (update == 0) {
	                        throw new BusinessException("系统自动取消订单时失败。");
	                    }
		        	 OrderLog log = new OrderLog(0, "system", bookingSendGoods.getId(), bookingSendGoods.getBookOrdersSn(),
		                        "系统自动取消订单。", new Date());

		             int orderlogCount = orderLogWriteDao.save(log);
		             if (orderlogCount == 0) {
		                 throw new BusinessException("系统自动取消订单，订单日志保存失败，请重试！");
		             }
		        	 transactionManager.commit(status);
				} catch (Exception e) {
					e.printStackTrace();
					transactionManager.rollback(status);
					log.error("[bookingSendGoodsModel][jobSystemCancelBookOrder]系统自动取消订单时发生异常:", e);
		            log.error("[bookingSendGoodsModel][jobSystemCancelBookOrder]发生异常的订单："
		                      + JSON.toJSONString(bookingSendGoods));
				}
             }
         }
         return true;
     }
     
     private void dbConstrains(BookingSendGoods bookingSendGoods) {
	/*	bookingSendGoods.setSendAddress(StringUtil.dbSafeString(bookingSendGoods.getSendAddress(), false, 255));
		bookingSendGoods.setPayType(StringUtil.dbSafeString(bookingSendGoods.getPayType(), true, 50));
		bookingSendGoods.setBookingUser(StringUtil.dbSafeString(bookingSendGoods.getBookingUser(), false, 50));
		bookingSendGoods.setMobile(StringUtil.dbSafeString(bookingSendGoods.getMobile(), false, 50));
		bookingSendGoods.setPhone(StringUtil.dbSafeString(bookingSendGoods.getPhone(), false, 50));
		bookingSendGoods.setCheckMan(StringUtil.dbSafeString(bookingSendGoods.getCheckMan(), false, 20));
		bookingSendGoods.setOrdersId(StringUtil.dbSafeString(bookingSendGoods.getOrdersId(), false, 20));*/
     }
}