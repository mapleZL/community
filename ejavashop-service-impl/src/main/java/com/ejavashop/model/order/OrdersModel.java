package com.ejavashop.model.order;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.EjavashopSequence;
import com.ejavashop.core.Md5;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.SyncWayUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.sms.MobileMessage;
import com.ejavashop.dao.promotion.read.bidding.ActBiddingReadDao;
import com.ejavashop.dao.promotion.read.coupon.CouponReadDao;
import com.ejavashop.dao.promotion.read.coupon.CouponUserReadDao;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleProductReadDao;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleReadDao;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleStageReadDao;
import com.ejavashop.dao.promotion.read.full.ActFullReadDao;
import com.ejavashop.dao.promotion.read.group.ActGroupReadDao;
import com.ejavashop.dao.promotion.write.bidding.ActBiddingWriteDao;
import com.ejavashop.dao.promotion.write.coupon.CouponOptLogWriteDao;
import com.ejavashop.dao.promotion.write.coupon.CouponUserWriteDao;
import com.ejavashop.dao.promotion.write.coupon.CouponWriteDao;
import com.ejavashop.dao.promotion.write.flash.ActFlashSaleLogWriteDao;
import com.ejavashop.dao.promotion.write.flash.ActFlashSaleProductWriteDao;
import com.ejavashop.dao.promotion.write.full.ActFullLogWriteDao;
import com.ejavashop.dao.promotion.write.full.ActFullWriteDao;
import com.ejavashop.dao.promotion.write.group.ActGroupWriteDao;
import com.ejavashop.dao.promotion.write.single.ActSingleLogWriteDao;
import com.ejavashop.dao.shop.read.cart.CartReadDao;
import com.ejavashop.dao.shop.read.member.MemberAddressReadDao;
import com.ejavashop.dao.shop.read.member.MemberCreditReadDao;
import com.ejavashop.dao.shop.read.member.MemberReadDao;
import com.ejavashop.dao.shop.read.operate.ConfigReadDao;
import com.ejavashop.dao.shop.read.operate.ProductLabelReadDao;
import com.ejavashop.dao.shop.read.operate.ProductPackageReadDao;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.read.product.ProductAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.product.ProductPriceReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.read.seller.SellerTransportReadDao;
import com.ejavashop.dao.shop.read.system.RegionsReadDao;
import com.ejavashop.dao.shop.write.backgoods.BackGoodsRecordWriteDao;
import com.ejavashop.dao.shop.write.backgoods.BackGoodsWriteDao;
import com.ejavashop.dao.shop.write.cart.CartWriteDao;
import com.ejavashop.dao.shop.write.member.MemberBalanceLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberCreditLogWriteDao;
import com.ejavashop.dao.shop.write.member.MemberCreditWriteDao;
import com.ejavashop.dao.shop.write.member.MemberGradeIntegralLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.dao.shop.write.operate.ProductLabelWriteDao;
import com.ejavashop.dao.shop.write.operate.ProductPackageWriteDao;
import com.ejavashop.dao.shop.write.order.BookingSendGoodsWriteDao;
import com.ejavashop.dao.shop.write.order.OrderLogWriteDao;
import com.ejavashop.dao.shop.write.order.OrderPayLogWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersErrorWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductErrorWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductReturnWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersReturnWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersTradeSerialWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.dao.shop.write.order.SendGoodsRecordWriteDao;
import com.ejavashop.dao.shop.write.order.TerraceSelfCouponWriteDao;
import com.ejavashop.dao.shop.write.parter.ParterAreaWriteDao;
import com.ejavashop.dao.shop.write.parter.ParterPercentWriteDao;
import com.ejavashop.dao.shop.write.parter.ParterSignWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.dao.shop.write.product.ProductPriceWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.dao.shop.write.system.RegionsWriteDao;
import com.ejavashop.dto.OrderDayDto;
import com.ejavashop.dto.OrderFinanceDayDto;
import com.ejavashop.entity.backgoods.BackGoods;
import com.ejavashop.entity.backgoods.BackGoodsRecord;
import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.member.MemberCredit;
import com.ejavashop.entity.member.MemberCreditLog;
import com.ejavashop.entity.member.MemberGradeIntegralLogs;
import com.ejavashop.entity.operate.Config;
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.order.OrderLog;
import com.ejavashop.entity.order.OrderPayLog;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersAndOrdersProductVO;
import com.ejavashop.entity.order.OrdersError;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.order.OrdersProductError;
import com.ejavashop.entity.order.OrdersProductReturn;
import com.ejavashop.entity.order.OrdersReturn;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.entity.order.TerraceSelfCoupon;
import com.ejavashop.entity.parter.CategorySalesVO;
import com.ejavashop.entity.parter.SalesDetailsVO;
import com.ejavashop.entity.parter.SalesPersonVO;
import com.ejavashop.entity.parter.SumParterSaleVO;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.coupon.CouponOptLog;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleLog;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.promotion.full.ActFullLog;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.promotion.single.ActSingleLog;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerTransport;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.model.cart.CartModel;
import com.ejavashop.model.member.MemberModel;
import com.ejavashop.model.seller.SellerTransportModel;
import com.ejavashop.service.sms.ISenderService;
import com.ejavashop.util.FrontProductPictureUtil;
import com.ejavashop.vo.cart.CartInfoVO;
import com.ejavashop.vo.cart.CartListVO;
import com.ejavashop.vo.order.BookingSendGoodsVO;
import com.ejavashop.vo.order.OrderCommitVO;
import com.ejavashop.vo.order.OrderCouponVO;
import com.ejavashop.vo.order.OrderSuccessVO;
import com.ejavashop.vo.order.SendingGoodsVO;


@Component(value = "ordersModel")
public class OrdersModel {
    private static Logger                   log = LogManager.getLogger(OrdersModel.class);
    
    private static final Logger  ILog = LogManager.getLogger("oms_interface");

    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private OrdersReadDao                   ordersReadDao;
    @Resource
    private DataSourceTransactionManager    transactionManager;
    @Resource
    private ProductWriteDao                 productWriteDao;
    @Resource
    private SellerReadDao                   sellerReadDao;
    @Resource
    private SellerWriteDao                  sellerWriteDao;
    @Resource
    private ProductGoodsWriteDao            productGoodsWriteDao;
    @Resource
    private OrdersProductWriteDao           ordersProductWriteDao;
    @Resource
    private OrderLogWriteDao                orderLogWriteDao;
    @Resource
    private OrdersReadDao                   orderReadDao;
    @Resource
    private OrdersProductReadDao            ordersProductReadDao;
    @Resource
    private MemberWriteDao                  memberWriteDao;
    @Resource
    private MemberAddressReadDao           memberAddressReadDao;
    @Resource
    private CartWriteDao                    cartWriteDao;
    @Resource
    private ConfigReadDao                   configReadDao;
    @Resource
    private MemberGradeIntegralLogsWriteDao memberGradeIntegralLogsWriteDao;
    @Resource
    private FrontProductPictureUtil         frontProductPictureUtil;
    @Resource
    private CartModel                       cartModel;
    @Resource
    private MemberBalanceLogsWriteDao       memberBalanceLogsWriteDao;
    @Resource
    private MemberModel                     memberModel;
    @Resource
    private OrderPayLogWriteDao             orderPayLogWriteDao;
    @Resource
    private CouponUserReadDao               couponUserReadDao;
    @Resource
    private CouponUserWriteDao              couponUserWriteDao;
    @Resource
    private CouponReadDao                   couponReadDao;
    @Resource
    private CouponOptLogWriteDao            couponOptLogWriteDao;
    @Resource
    private ActFullLogWriteDao              actFullLogWriteDao;
    @Resource
    private ActSingleLogWriteDao            actSingleLogWriteDao;
    @Resource
    private ActFlashSaleReadDao             actFlashSaleReadDao;
    @Resource
    private ActFlashSaleStageReadDao        actFlashSaleStageReadDao;
    @Resource
    private ActFlashSaleProductReadDao      actFlashSaleProductReadDao;
    @Resource
    private ActFlashSaleProductWriteDao     actFlashSaleProductWriteDao;
    @Resource
    private SellerTransportModel            sellerTransportModel;
    @Resource
    private ProductReadDao                  productReadDao;
    @Resource
    private ProductGoodsReadDao             productGoodsReadDao;
    @Resource
    private ActFlashSaleLogWriteDao         actFlashSaleLogWriteDao;
    @Resource
    private ActGroupReadDao                 actGroupReadDao;
    @Resource
    private ActGroupWriteDao                actGroupWriteDao;
    @Resource
    private ActBiddingReadDao               actBiddingReadDao;
    @Resource
    private ActBiddingWriteDao              actBiddingWriteDao;
    @Resource
    private ProductLabelWriteDao            productLabelWriteDao;
    @Resource
    private ProductLabelReadDao            productLabelReadDao;
    @Resource
    private ProductPackageWriteDao          productPackageWriteDao;
    @Resource
    private ProductPackageReadDao          productPackageReadDao;
    @Resource
    private BookingSendGoodsWriteDao        bookingSendGoodsWriteDao;
    @Resource
    private SendGoodsRecordWriteDao         sendGoodsRecordWriteDao;
    @Resource
    private BackGoodsWriteDao               backGoodsWriteDao;
    @Resource
    private BackGoodsRecordWriteDao         backGoodsRecordWriteDao;
    @Resource
    private ProductPriceWriteDao            productPriceWriteDao;
    @Resource
    private ProductPriceReadDao            productPriceReadDao;
    @Resource
    private MemberCreditWriteDao            memberCreditWriteDao;
    @Resource
    private MemberCreditReadDao            memberCreditReadDao;
    @Resource
    private MemberCreditLogWriteDao         memberCreditLogWriteDao;
    @Resource
    private ProductAttrReadDao              productAttrReadDao;
    @Resource
    private RegionsWriteDao                 regionsWriteDao;
    @Resource
    private SellerTransportReadDao          sellerTransportReadDao;
    @Resource
    private MemberReadDao                   memberReadDao;
    @Resource
    private DataSourceTransactionManager    promotionTransactionManager;
    @Resource
    private CouponWriteDao                  couponWriteDao;
    @Resource
    private CartReadDao                     cartReadDao;

    @Resource
    private OrdersProductErrorWriteDao      ordersProductErrorWriteDao;

    @Resource
    private OrdersErrorWriteDao             ordersErrorWriteDao;

    @Resource
    private ActFullWriteDao                 actFullWriteDao;
    @Resource
    private ActFullReadDao                 actFullReadDao;

    @Resource
    private OrdersTradeSerialWriteDao       ordersTradeSerialWriteDao;

    @Resource
    private OrdersReturnWriteDao            ordersReturnWriteDao;
    
    @Resource
    private OrdersProductReturnWriteDao     ordersProductReturnWriteDao;
    
    @Resource
    private ParterSignWriteDao 				parterSignWriteDao;
    
    @Resource
    private ParterAreaWriteDao				parterAreaWriteDao;
    
    @Resource
    private ISenderService                  senderService;
    
    @Resource
    private ParterPercentWriteDao			parterPercentWriteDao;
    
    @Resource
    private RegionsReadDao                  regionsReadDao; 
    
    @Resource
    private TerraceSelfCouponWriteDao       terraceSelfCouponWriteDao;
    /**
     * 根据id取得订单
     * @param  ordersId
     * @return
     */
    public Orders getOrdersById(Integer ordersId) {
        return ordersReadDao.get(ordersId);
    }

    /**
     * 根据orderSn取得订单
     * @param  orderSn
     * @return
     */
    public Orders getOrdersBySn(String orderSn) {
    	Orders orders = ordersReadDao.getByOrderSn(orderSn);
    	orders.setOrderProductList(ordersProductReadDao.getOrdersProductByOrderSn(orderSn));
        return orders;
    }

    /**
     * 根据订单ID获取订单号
     * @param id
     * @return
     */
    public String getOrderSnById(Integer id) {
        if (id == null) {
            throw new BusinessException("id不能为null");
        }
        Orders orders = ordersReadDao.get(id);
        if (orders.getOrderSn() == null) {
            throw new BusinessException("没有该订单:" + orders.getId());
        }
        return orders.getOrderSn();
    }

    /**
     * 保存订单
     * @param  orders
     * @return
     */
    public Integer saveOrders(Orders orders) {
        return ordersWriteDao.insert(orders);
    }

    /**
    * 更新订单
    * @param  orders
    * @return
    */
    public Integer updateOrders(Orders orders) {
        return ordersWriteDao.update(orders);
    }

    /**
     * 根据订单ID删除订单
     * @param ordersId
     * @return
     */
    public boolean deleteOrder(Integer ordersId) {
        if (ordersId == null)
            throw new BusinessException("删除订单[" + ordersId + "]时出错");
        return ordersWriteDao.delete(ordersId) > 0;
    }

    public Integer getOrdersCount(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount(queryMap);
    }

    public Integer getOrdersCount2(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount2(queryMap);
    }

    public Integer getOrdersCount1(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount1(queryMap);
    }

    public List<Orders> getOrders(Map<String, String> queryMap, Integer start,
                                  Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getOrders(parammap, start, size);
        for (Orders o : list) {
            o.setSellerName(sellerReadDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    /**
     * 导出excel准备的
     * @param queryMap
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<OrdersAndOrdersProductVO> getOrdersAndOrdersProductVO(Map<String, String> queryMap,
                                                                      Integer start,
                                                                      Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<OrdersAndOrdersProductVO> list = ordersReadDao.getOrdersAndOrdersProductVO(parammap,
            start, size);
        for (OrdersAndOrdersProductVO o : list) {
            o.setSellerName(sellerReadDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    public List<Orders> getordersInStore(Map<String, String> queryMap, Integer start,
                                         Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getordersInStore(parammap, start, size);
        for (Orders o : list) {
            o.setSellerName(sellerReadDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    /**
     * 修改订单
     * @param orders 订单
     * @param type 商家操作订单类型
     * @param sellerUser 商家管理员对象
     * @param updateStore 是否需要修改库存
     * @return
     * @throws Exception
     */
    public Integer updateOrdersBySeller(Orders orders, int type, SellerUser sellerUser,
                                        boolean updateStore) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.更新订单
            result = ordersWriteDao.update(orders);
            Orders ordersDB = ordersWriteDao.get(orders.getId());
            //2.写订单日志
            writeOrderInfo(ordersDB, sellerUser, type);

            if (updateStore) {
                //3.更新货品和商品的库存
                this.updateProductStock(orders.getId(), false);
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("更新订单时出现未知异常：" + e);
            transactionManager.rollback(status);
            throw e;
        }
        return result;
    }

    /**
     * 写订单日志
     * @param orders
     * @throws Exception
     */
    private void writeOrderInfo(Orders orders, SellerUser sellerUser, int type) throws Exception {
        OrderLog log = new OrderLog(sellerUser.getId(), sellerUser.getName(), orders.getId(), orders.getOrderSn(), "", new Date());
        switch (type) {
            case Orders.DELIVER:
                log.setContent("商家已发货");
                break;
            case Orders.CANCEL:
                log.setContent("商家取消了此订单");
                break;
            case Orders.UPDATE_AMOUNT:
                log.setContent("商家修改订单金额为" + orders.getMoneyOrder());
                break;
            case Orders.SUBMIT_PAY:
                log.setContent("商家已确认收款");
                break;
            case Orders.CONFIRM:
                log.setContent("商家确认订单");
                break;
            case Orders.CANCEL_ADMIN:
                log.setContent("管理员取消订单");
                break;
            default:
                break;
        }
        orderLogWriteDao.save(log);
    }

    /**
     * 修改订单
     * @param orders 订单
     * @param type 商家操作订单类型
     * @param systemAdmin 平台管理员对象
     * @param updateStore 是否需要修改库存
     * @return
     * @throws Exception
     */
    public Integer updateOrdersByAdmin(Orders orders, int type, SystemAdmin systemAdmin,
                                       boolean updateStore) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.更新订单
            result = ordersWriteDao.update(orders);
            Orders ordersDB = ordersWriteDao.get(orders.getId());
            //2.写订单日志
            writeOrderInfo(ordersDB, systemAdmin, type);

            if (updateStore) {
                //3.更新货品和商品的库存
                this.updateProductStock(orders.getId(), false);
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("更新订单时出现未知异常：" + e);
            transactionManager.rollback(status);
            throw e;
        }
        return result;
    }

    /**
     * 写订单日志
     * @param orders
     * @throws Exception
     */
    private void writeOrderInfo(Orders orders, SystemAdmin systemAdmin, int type) throws Exception {
        OrderLog log = new OrderLog(systemAdmin.getId(), systemAdmin.getName(), orders.getId(), orders.getOrderSn(), "", new Date());
        switch (type) {
            case Orders.DELIVER:
                log.setContent("已由平台管理员发货");
                break;
            case Orders.CANCEL:
                log.setContent("平台管理员取消了此订单");
                break;
            case Orders.UPDATE_AMOUNT:
                log.setContent("平台管理员修改订单金额为" + orders.getMoneyOrder());
                break;
            case Orders.SUBMIT_PAY:
                log.setContent("平台管理员已确认收款");
                break;
            case Orders.CONFIRM:
                log.setContent("平台管理员确认订单");
                break;
            case Orders.CANCEL_ADMIN:
                log.setContent("平台管理员取消了此订单");
                break;
            default:
                break;
        }
        orderLogWriteDao.save(log);
    }

    /**
     * 修改商品和货品的库存<br>
     * <li>检验库存是否足够
     * <li>修改货品库存
     * <li>修改商品库存
     * @param ordersId 订单ID
     * @param isAddStock 是否是增加库存：如果是true，表示库存还原，增加库存；如果是false，表示减库存
     */
    private void updateProductStock(Integer ordersId, boolean isAddStock) {
        List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersId);
        for (OrdersProduct ordersProduct : opList) {
            // 如果是增加库存，不需要校验库存数量
            if (!isAddStock) {
                // 校验库存数量是否足够
                ProductGoods productGoods = productGoodsWriteDao.get(ordersProduct.getProductGoodsId());
                if ((productGoods.getProductStock() - ordersProduct.getNumber()) < 0) {
                    throw new BusinessException("商品【" + ordersProduct.getProductName() + "】的库存不足！");
                }
            }

            Integer updateStock = ordersProduct.getNumber();
            // 如果是true，把数量变为负数
            if (isAddStock) {
                updateStock = 0 - updateStock;
            }
            // 修改货品库存
            Integer updateGoodStock = productGoodsWriteDao.updateStock(ordersProduct.getProductGoodsId(), updateStock);
            if (updateGoodStock == 0) {
                log.error("[OrdersModel][updateProductStock]更新货品库存时失败。");
                throw new BusinessException("更新商品库存时失败，请重试！");
            }
            // 修改商品库存
            Integer updateProductStock = productWriteDao.updateStock(ordersProduct.getProductId(), updateStock);
            if (updateProductStock == 0) {
                log.error("[OrdersModel][updateProductStock]更新商品库存时失败。");
                throw new BusinessException("更新商品库存时失败，请重试！");
            }
        }
    }

    //Terry 20160813
    private void updateProductStockForPayAfter(int productId, int productGoodsId, int updateStock) {

        //每次更新货品库存前校验当前库存是否小于更新值
        //        ProductGoods goods = productGoodsWriteDao.get(productGoodsId);
        //        if (goods.getProductStock().intValue() < updateStock) {
        //            throw new BusinessException("库存不足");
        //        }
        // 修改货品库存
        Integer updateGoodStock = productGoodsWriteDao.updateStock(productGoodsId, updateStock);
        if (updateGoodStock == 0)
            log.error("[OrdersModel][updateProductStock]更新货品库存时失败，ordersProductId=" + productGoodsId
                      + "。");

        // 修改商品库存
        Integer updateProductStock = productWriteDao.updateStock(productId, updateStock);
        if (updateProductStock == 0)
            log.error(
                "[OrdersModel][updateProductStock]更新商品库存时失败，ordersProductId=" + productId + "。");
    }

    /**
     * 修改商品和货品的库存<br>
     * <li>修改货品库存
     * <li>修改商品库存
     * @param ordersId 订单ID
     */
    private void updateProductStockForPayAfterTerry(Integer ordersId) {
        List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersId);
        for (OrdersProduct ordersProduct : opList) {
            Integer updateStock = ordersProduct.getNumber();
            // 修改货品库存
            Integer updateGoodStock = productGoodsWriteDao
                .updateStock(ordersProduct.getProductGoodsId(), updateStock);
            if (updateGoodStock == 0) {
                log.error("[OrdersModel][updateProductStock]更新货品库存时失败，ordersProductId="
                          + ordersProduct.getId() + "。");
            }
            // 修改商品库存
            Integer updateProductStock = productWriteDao.updateStock(ordersProduct.getProductId(),
                updateStock);
            if (updateProductStock == 0) {
                log.error("[OrdersModel][updateProductStock]更新商品库存时失败，ordersProductId="
                          + ordersProduct.getId() + "。");
            }
        }
    }

    /**
     * 取消订单，目前只有订单状态为 1、2、3的可以取消（其中3的只能用户自己取消，不能由商家取消）
     * @param ordersId 订单ID
     * @param seller 商家对象
     * @return
     * @throws Exception
     */
    public boolean cancelOrderBySeller(Integer ordersId, SellerUser sellerUser) throws Exception {
        Integer result = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Orders orderDb = ordersReadDao.get(ordersId);
            // 只能取消未付款或者待确认的订单
            if (Orders.ORDER_STATE_1 != orderDb.getOrderState().intValue() && Orders.ORDER_STATE_2 != orderDb.getOrderState().intValue()) {
                throw new BusinessException("对不起，只能取消未付款或者待确认状态的订单！");
            }

            Orders ordersNew = new Orders();
            ordersNew.setId(ordersId);
            ordersNew.setOrderState(Orders.ORDER_STATE_6);
            // 1.更新订单
            result = ordersWriteDao.update(ordersNew);
            // 2.写订单日志
            this.writeOrderInfo(orderDb, sellerUser, Orders.CANCEL);
            // 3.更新货品和商品的库存（可以取消的订单还没有占用库存，不用还原库存）
            // this.updateProductStock(ordersNew.getId(), true);
            // 4.返还积分
            this.cancelOrderBackIntegral(orderDb);
            // 5.还原销量
            // 普通订单需要还原销量，限时抢购订单的销量在还原库存时一起还原
            if (orderDb.getOrderType().intValue() == Orders.ORDER_TYPE_1) {
                this.restoreActualSales(ordersProductReadDao.getByOrderId(ordersId));
            }
            // 6.返回优惠券
            this.cancelOrderBackCoupon(orderDb, sellerUser.getId(), sellerUser.getName());
            // 7.退回付款金额
            this.cancelOrderBackMoney(orderDb, sellerUser.getId(), sellerUser.getName());

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("取消订单时出现未知异常：" + e);
            throw e;
        }
        return result > 0;
    }

    /**
     * 根据会员ID，订单状态获取 子订单 数量
     * @param memberId
     * @param orderState
     * @return
     */
    public Integer getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        return ordersReadDao.getOrderNumByMIdAndState(memberId, orderState);
    }

    /**
     * 根据条件取得订单并封装网单
     * @param queryMap
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Orders> getOrderWithOrderProduct(Map<String, String> queryMap, Integer start,
                                                 Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getOrders2(parammap, start, size);
        //查询是否有合并订单
        for (Orders orders : list) {
            String ids = orders.getId() + "";
            if (!StringUtil.isEmpty(orders.getTradeNo())) {
                List<Orders> newListOrders = ordersReadDao.getOrdersByTradeNo(orders.getTradeNo());
                for (Orders orders2 : newListOrders) {
                    if (orders.getTradeNo().equals(orders2.getTradeNo()) && !orders.getId().equals(orders2.getId())) {
//                        List<OrdersProduct> newOrdersProductList = orders.getOrderProductList();
//                        //合并
//                        newOrdersProductList.addAll(orders2.getOrderProductList());
//                        orders.setOrderProductList(newOrdersProductList);
                        orders.setMoneyOrder(orders.getMoneyOrder().add(orders2.getMoneyOrder()));
                        ids += "," + orders2.getId();
                    }
                }
            }

//            if (ids.indexOf(",") > 0) ids = ids.substring(0, ids.length() - 1);
            List<OrdersProduct> orderProductList = ordersProductReadDao.getByOrderIds(ids);

            if (orderProductList.size() == 0) {
                log.error("网单信息获取失败。");
                throw new BusinessException("网单信息获取失败，请联系管理员！");
            } else {
                //根据产品id查小图路径
//                for (OrdersProduct op : orderProductList) {
//                    //货品
//                    ProductGoods goods = productGoodsReadDao.get(op.getProductGoodsId());
//                    op.setProductGoods(goods);
////                    
//                    if (goods == null) {
//                        String productLeadLittle = frontProductPictureUtil.getproductLeadLittle(op.getProductId());
//                        op.setProductLeadLittle(productLeadLittle);
//                    }
//                    else {
//                        op.setProductLeadLittle(goods.getImages());
//                    }
//                    //商品
////                    Product pro = productReadDao.get(op.getProductId());
////                    op.setProduct(pro);
//
//                }
                orders.setOrderProductList(orderProductList);
            }
        }

        return list;
    }

    /**
     * 取消订单 目前只有订单状态为 1、2、3的可以取消（其中3的只能用户自己取消，不能由商家取消）
     * @param ordersId 订单ID
     * @param optId 操作人ID
     * @param optName 操作人名称
     * @return
     */
    public boolean cancelOrder(String tradeNo, Integer optId, String optName) {
        //Terry need repair
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //参数校验
            if (StringUtil.isEmpty(tradeNo)) {
                throw new BusinessException("订单支付码为空，请重试！");
            }
            //获取订单
            List<Orders> ordersList = ordersReadDao.getOrdersByTradeNo(tradeNo);
            if (ordersList == null || ordersList.size() <= 0) {
                throw new BusinessException("获取订单信息失败，请重试！");
            } 
            for (Orders ordersDb : ordersList) {
            	if (!(ordersDb.getOrderState().equals(Orders.ORDER_STATE_1)
                        || (ordersDb.getOrderState().equals(Orders.ORDER_STATE_2)) || (ordersDb.getOrderState().equals(Orders.ORDER_STATE_3)))) {
            			throw new BusinessException("订单已发货不能取消！");
            	//Terry 20160825
            	}
            	else if (ordersDb.getOrderState().equals(Orders.ORDER_STATE_6)) {
	               throw new BusinessException("订单已取消，不能再次取消！");
	            }
            	Integer ordersId = ordersDb.getId();
            	//设置订单状态
                Orders orders = new Orders();
                orders.setId(ordersId);
                orders.setOrderState(Orders.ORDER_STATE_6);
                orders.setFinishTime(new Date());
                int count = ordersWriteDao.update(orders);
                if (count == 0) {
                    throw new BusinessException("订单更新失败，请重试！");
                }
                //记录订单日志
                OrderLog orderLog = new OrderLog();
                orderLog.setContent("订单已被用户主动取消");
                orderLog.setOperatingId(optId);
                orderLog.setOrdersId(ordersId);
                orderLog.setOrdersSn(ordersDb.getOrderSn());
                orderLog.setOperatingName(optName);

                int logCount = orderLogWriteDao.save(orderLog);
                if (logCount == 0) {
                    throw new BusinessException("订单日志保存失败，请重试！");
                }
                // 返还积分
                this.cancelOrderBackIntegral(ordersDb);

                List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersId);
                // 普通订单需要还原销量，限时抢购订单的销量在还原库存时一起还原
                //            if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_1) {
                // 还原销量
                // 限时抢购订单时还原活动商品的库存
                this.restoreActualSales(opList);
                //            }

                // 返回优惠券
                this.cancelOrderBackCoupon(ordersDb, optId, optName);

                // 退回付款金额
                this.cancelOrderBackMoney(ordersDb, optId, optName);
                
             // 如果订单是待发货状态的订单，则退回付款金额到账户
                if (ordersDb.getOrderState().equals(Orders.ORDER_STATE_3)) {
                    if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_2) {
                        // 限时抢购只有一个网单
                        this.backFlashProductStockAndActualSales(opList.get(0).getActFlashSaleProductId(), opList.get(0).getNumber());
                    } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_8) {
                        // 团购只有一个网单
                        this.backGroupStockAndActualSales(opList.get(0).getActGroupId(), opList.get(0).getNumber());
                    } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_9 || ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_10) {
                        // 竞价定金订单时不会进入此逻辑，因为定金订单支付后订单状态直接设置为已完成
                        // 竞价尾款订单时不会进入此逻辑，因为尾款订单不能取消
                        // 此处为逻辑清晰、代码易读增加此逻辑
                    } else {
                        // 普通订单
                        // 更新货品和商品的库存
//                        this.updateProductStock(ordersDb.getId(), true);
                    }
                }
                
                //现在取消商品就会回滚库存扣减  add by Ls 2016/08/09
                this.updateProductStock(ordersId, true);
			}
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 取消待发货状态订单时，把现金支付和余额支付的钱退到用户余额中
     * @param order
     * @param optId
     * @param optName
     */
    private void cancelOrderBackMoney(Orders order, Integer optId, String optName) {

        Member memberDB = memberReadDao.get(order.getMemberId());

        // 付款的金额（现金支付+余额支付）
        BigDecimal money = order.getMoneyPaidReality().add(order.getMoneyPaidBalance());
        // 如果付款金额大于0，则退回并记录日志
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            // 修改用户的余额
            Member updateBalanceObj = new Member();
            updateBalanceObj.setId(order.getMemberId());
            updateBalanceObj.setBalance(money);
            Integer updateBalance = memberWriteDao.updateBalance(updateBalanceObj);
            if (updateBalance == 0) {
                log.error("退回余额时失败。");
                throw new BusinessException("退回余额时失败，请重试！");
            }

            // 记录【会员账户余额变化日志表】
            MemberBalanceLogs logs = new MemberBalanceLogs();
            logs.setMemberId(memberDB.getId());
            logs.setMemberName(memberDB.getName());
            logs.setMoneyBefore(memberDB.getBalance());
            logs.setMoneyAfter(memberDB.getBalance().add(money));
            logs.setMoney(money);
            logs.setCreateTime(new Date());
            logs.setState(MemberBalanceLogs.STATE_2);
            logs.setRemark("取消订单，订单号" + order.getOrderSn());
            logs.setOptId(optId);
            logs.setOptName(optName);

            Integer balanceLog = memberBalanceLogsWriteDao.save(logs);
            if (balanceLog == 0) {
                log.error("记录会员余额变化日志时出错。");
                throw new BusinessException("退回余额时失败，请重试！");
            }
        }
    }

    /**
     * 取消订单时退回用户优惠券
     * @param order
     * @param optId
     * @param optName
     */
    private void cancelOrderBackCoupon(Orders order, Integer optId, String optName) {

        if (order.getCouponUserId() != null && order.getCouponUserId() > 0) {
            CouponUser couponUser = couponUserReadDao.get(order.getCouponUserId());
            if (couponUser == null) {
                log.error("用户优惠券获取失败。");
                throw new BusinessException("返还用户优惠券时失败，请重试！");
            }
            Integer backCouponUser = couponUserWriteDao.backCouponUser(order.getMemberId(), couponUser.getId());
            if (backCouponUser < 1) {
                log.error("修改用户优惠券使用次数失败。");
                throw new BusinessException("返还用户优惠券时失败，请重试！");
            }
            // 设定优惠券使用日志
            CouponOptLog couponOptLog = new CouponOptLog();
            couponOptLog.setCouponUserId(couponUser.getId());
            couponOptLog.setMemberId(couponUser.getMemberId());
            couponOptLog.setSellerId(couponUser.getSellerId());
            couponOptLog.setCouponId(couponUser.getCouponId());
            couponOptLog.setOptType(CouponOptLog.OPT_TYPE_3);
            couponOptLog.setOrderId(order.getId());
            couponOptLog.setCreateUserId(optId);
            couponOptLog.setCreateUserName(optName);
            couponOptLog.setCreateTime(new Date());
            couponOptLogWriteDao.insert(couponOptLog);
        }
    }

    private void restoreActualSales(List<OrdersProduct> opList) {
        if (opList != null && opList.size() > 0) {
            for (OrdersProduct op : opList) {
                this.updateProductActualSales(op.getProductId(), op.getProductGoodsId(), 0 - op.getNumber());
            }
        }
    }

    /**
     * 订单取消时，返还该订单消耗的积分
     * @param order
     */
    private void cancelOrderBackIntegral(Orders order) {
        // 消耗了积分才返还
        if (order.getIntegral() > 0) {
            // 修改用户积分数，记录积分消耗日志
            Member memberNew = new Member();
            memberNew.setId(order.getMemberId());
            memberNew.setIntegral(order.getIntegral());
            Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
            if (updateIntegral == 0) {
                throw new BusinessException("返还用户积分时失败，请重试！");
            }
            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
            memberGradeIntegralLogs.setMemberId(order.getMemberId());
            memberGradeIntegralLogs.setMemberName(order.getMemberName());
            memberGradeIntegralLogs.setValue(order.getIntegral());
            memberGradeIntegralLogs.setOptType(ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_5);
            memberGradeIntegralLogs.setOptDes("取消订单" + order.getOrderSn() + "返还积分");
            memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_2);
            memberGradeIntegralLogs.setCreateTime(new Date());
            Integer save = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
            if (save == 0) {
                throw new BusinessException("记录用户积分消费日志失败，请重试！");
            }
        }
    }

    /**
     * 根据订单id 取订单、网单及产品图片信息
     * @param request
     * @return
     */
    public Orders getOrderWithOPById(Integer orderId, String type) {
        Orders orders = ordersReadDao.get(orderId);
        if (orders == null) {
            throw new BusinessException("订单信息获取失败，请重试！");
        }
        this.makeOrdersProduct(orders);
        if (!"admin".equals(type)) {
            String tradeNo = orders.getTradeNo();
            if (!StringUtil.isEmpty(tradeNo)) {
                List<Orders> ordersList = ordersReadDao.getOrdersByTradeNo(tradeNo);
                for (Orders orders2 : ordersList) {
                    if (!orders.getId().equals(orders2.getId())) {
                        orders.setMoneyProduct(
                            orders.getMoneyProduct().add(orders2.getMoneyProduct()));
                        orders.setMoneyLogistics(
                            orders.getMoneyLogistics().add(orders2.getMoneyLogistics()));
                        orders.setMoneyOrder(orders.getMoneyOrder().add(orders2.getMoneyOrder()));
                        orders.setMoneyPaidBalance(
                            orders.getMoneyPaidBalance().add(orders2.getMoneyPaidBalance()));
                        orders.setMoneyPaidReality(
                            orders.getMoneyPaidReality().add(orders2.getMoneyPaidReality()));
                        orders
                            .setMoneyCoupon(orders.getMoneyCoupon().add(orders2.getMoneyCoupon()));
                        orders.setMoneyActFull(
                            orders.getMoneyActFull().add(orders2.getMoneyActFull()));
                        orders.setMoneyBack(orders.getMoneyBack().add(orders2.getMoneyBack()));
                        orders.setMoneyIntegral(
                            orders.getMoneyIntegral().add(orders2.getMoneyIntegral()));
                        /*orders.setServicePrice(orders.getServicePrice().add(orders2.getServicePrice()));
                        orders.setLabelPrice(orders.getLabelPrice().add(orders2.getLabelPrice()));*/
                        this.makeOrdersProduct(orders2);
                        List<OrdersProduct> newOrdersProductList = orders.getOrderProductList();
                        newOrdersProductList.addAll(orders2.getOrderProductList());
                        orders.setOrderProductList(newOrdersProductList);
                    }
                }
            }
        }
        return orders;
    }

    //根据订单id查询网单 组装网单
    public Orders makeOrdersProduct(Orders orders) {
        List<OrdersProduct> orderProductList = ordersProductReadDao.getByOrderId(orders.getId());

        if (orderProductList.size() == 0) {
            throw new BusinessException("网单信息获取失败，请联系管理员！");
        }
        //根据产品id查小图路径
        for (OrdersProduct op : orderProductList) {
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadLittle(op.getProductId());
            op.setProtectedPrice(productReadDao.get(op.getProductId()).getProtectedPrice());
            op.setProductLeadLittle(productLeadLittle);
            op.setProductUrl(productReadDao.get(op.getProductId()).getProductUrl());
            //商品
            Product pro = productReadDao.get(op.getProductId());
            op.setProduct(pro);

            //货品
            ProductGoods goods = productGoodsReadDao.get(op.getProductGoodsId());
            op.setProductGoods(goods);

            //套餐
            if (op.getProductPackageId() != null) {
                ProductPackage pp = productPackageReadDao.get(op.getProductPackageId());
                if (pp != null) {
                    op.setPackprice(pp.getPrice().toString());
                    op.setPackageName(pp.getName());

                    StringBuffer sb = new StringBuffer();
                    if (!StringUtil.isNullOrZero(pp.getIsHook())) {
                        sb.append("钩子").append("、");
                    }
                    if (!StringUtil.isNullOrZero(pp.getIsGlue())) {
                        sb.append("不干胶").append("、");
                    }
                    if (!StringUtil.isNullOrZero(pp.getIsLiner())) {
                        sb.append("衬板").append("、");
                    }
                    if (!StringUtil.isNullOrZero(pp.getIsBag())) {
                        sb.append("中包袋").append("、");
                    }
                    if (!StringUtil.isNullOrZero(pp.getIsLabel())) {
                        sb.append("防伪标").append("、");
                    }
                    if (!StringUtil.isNullOrZero(pp.getIsGirdle())) {
                        sb.append("腰封").append("、");
                    }

                    if (sb.length() > 0) {
                        op.setPackOther(sb.substring(0, sb.length() - 1));
                    }
                    op.setPackDescribe(pp.getDescribe());
                }
            }

            //标签
            if (op.getProductLabelId() != null) {
                ProductLabel label = productLabelReadDao.get(op.getProductLabelId());
                if (label != null) {
                    op.setLabelprice(label.getMarketPrice().toString());
                    op.setLabelName(label.getName());
                }
            }
        }
        orders.setOrderProductList(orderProductList);
        Seller seller = sellerReadDao.get(orders.getSellerId());
        if (seller != null)
            orders.setSellerName(
                seller.getName() == null ? seller.getSellerName() : seller.getName());

        return orders;
    }

    /**
     * 用户提交订单<br>
     * 1、判断是否使用余额、判断支付密码<br>
     * 2、按商家拆分订单<br>
     * 3、保存网单<br>
     * 4、清除购物车<br>
     * 5、如果使用余额，并且余额足够支付所有订单，修改支付状态、修改库存<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     * Terry
     */
    @SuppressWarnings("all")
    public OrderSuccessVO orderCommit(OrderCommitVO orderCommitVO, String ordersType) throws Exception {
        TerraceSelfCoupon terraceSelfCoupon = new TerraceSelfCoupon();
        Integer twoJGFlag = orderCommitVO.getTwoJGFlag();
        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
            //上门自提和三方仓储不需要指定地址
        } /*else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("单提交信息中收货地址ID为空，请重试！");
          }*/else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressReadDao.get(orderCommitVO.getAddressId());

            // 根据登录用户取得购物车信息，数据都从写库获取，数据已经根据商家封装好
            CartInfoVO cartInfoVO = cartModel.getCartInfoByMId(orderCommitVO.getMemberId(), address, orderCommitVO.getSource(), 2, orderCommitVO.getLogisticsId());
            // 获取提交订单的用户
            Member member = memberReadDao.get(orderCommitVO.getMemberId());

            //如果使用了余额支付 ，判断支付密码
            if (orderCommitVO.getIsBalancePay() != null && orderCommitVO.getIsBalancePay()) {
                if (StringUtil.isEmpty(orderCommitVO.getBalancePwd())) {
                    throw new BusinessException("请输入支付密码后重试！");
                }
                if (!Md5.getMd5String(orderCommitVO.getBalancePwd()).equals(member.getBalancePwd())) {
                    throw new BusinessException("支付密码不正确，请重试！");
                }
                //                // 余额为零直接抛出
                //                if (member.getBalance() == null
                //                    || member.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
                //                    throw new BusinessException("您没有余额，不能使用余额支付！");
                //                }

                // 如果订单是货到付款，且余额不足以支付所有的金额则抛出，如果足够支付所有金额，则继续
                if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode()) && cartInfoVO.getFinalAmount().compareTo(member.getBalance()) > 0) {
                    throw new BusinessException("余额不足，请重新选择支付方式，或拆分订单后再下单！");
                }
            }

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                // 判断用户的积分是否够填入的积分数
                if (integral.compareTo(member.getIntegral()) > 0) {
                    throw new BusinessException("积分不够了，请重新填写积分数量！");
                }
                // 至少消耗积分换算比例的积分数量
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                if (config == null) {
                    throw new BusinessException("积分转换金额失败，请联系系统管理员！");
                }
                if (integral.compareTo(config.getIntegralScale()) < 0) {
                    throw new BusinessException("对不起，请至少使用" + config.getIntegralScale() + "个积分！");
                }
            }

            // 根据来源判断渠道，默认渠道为PC
            int channel = ConstantsEJS.CHANNEL_2;
            if (orderCommitVO.getSource() != null && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                    || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID) || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
                channel = ConstantsEJS.CHANNEL_3;
            }

            if (cartInfoVO != null && cartInfoVO.getCartListVOs().size() > 0) {
                // 使用商家优惠券信息
                Map<Integer, OrderCouponVO> sellerCouponMap = orderCommitVO.getSellerCouponMap();
                // 优惠券使用日志信息（用于记录优惠券用户表记录的操作日志）
                List<CouponOptLog> couponOptLogList = new ArrayList<CouponOptLog>();
                // 满减活动参加日志表
                List<ActFullLog> actFullLogList = new ArrayList<ActFullLog>();
                // 单品立减活动参加日志表
                List<ActSingleLog> actSingleLogList = new ArrayList<ActSingleLog>();

                List<Orders> orderList = new ArrayList<Orders>();
                String relationOrderSn = "";
                // 所有订单总金额（用于计算每个订单分摊的积分金额）
                BigDecimal allMoneyOrder = BigDecimal.ZERO;
                List<Integer> rids = new ArrayList<Integer>();
                // 循环各个商家，每个商家是一个订单
                String logisticsNameOms = "";
                for (CartListVO cartListVO : cartInfoVO.getCartListVOs()) {
                    //Terry 20160816
                    List<Cart> cartList = cartListVO.getCartList();
                    if (cartList != null && cartList.size() > 0) {
                        Seller seller = cartListVO.getSeller();
                        if (seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
                            throw new BusinessException("商家[" + seller.getSellerName() + "]已被冻结，请把该商家的商品移出购物车后再下单，谢谢！");
                        }
                        // 如果使用了当前商家的优惠券，校验优惠券信息
                        OrderCouponVO orderCouponVO = sellerCouponMap.get(seller.getId());
                        if (orderCouponVO != null) {
                            this.checkSellerCoupon(orderCouponVO, member.getId(), cartListVO, channel);
                        }
                        // 保存订单及日志信息
                        Orders order = this.saveOrderInfo(cartListVO, member, orderCommitVO, address, orderCouponVO, couponOptLogList, actFullLogList, ordersType, logisticsNameOms);
                        rids.add(order.getId());
                        
                        //Terry 只记录第一张订单的快递方式，其余快递方式以外加上订单数量
                        if ("".equals(logisticsNameOms))
                            logisticsNameOms = order.getLogisticsNameOms() + "_A";

                        orderList.add(order);
                        relationOrderSn += order.getOrderSn() + ",";
                        allMoneyOrder = allMoneyOrder.add(order.getMoneyOrder());

                        // 保存网单信息
                        this.saveOrderProductInfo(order, cartList, member, actSingleLogList);
                    }
                }

                //删除购物车数据，不判断删除的成功与否，购物车的数据不影响，只打日志，不抛异常
                int count = cartWriteDao.deleteByMemberId(member.getId());
                if (count == 0) {
                    log.error("删除购物车失败！");
                }

                // 转换总金额
                int moneyIntegral = 0;
                // 计算订单使用积分金额（都计算成整数）
                if (integral != null && integral > 0) {
                    // 计算转换总金额
                    moneyIntegral = integral / config.getIntegralScale();
                    // 已经分摊的金额
                    int moneyShared = 0;
                    // 已经分摊的积分
                    int integralShared = 0;

                    for (int i = 0; i < orderList.size(); i++) {
                        Orders order = orderList.get(i);
                        // 订单金额为0则不分摊
                        if (order.getMoneyOrder().compareTo(BigDecimal.ZERO) <= 0) {
                            continue;
                        }

                        // 用一个新对象来更新数据库
                        Orders orderNew = new Orders();
                        orderNew.setId(order.getId());

                        // 当前订单分摊金额、积分数
                        int orderMoneyIntegral = 0;
                        int orderIntegral = 0;
                        if ((i + 1) == orderList.size()) {
                            // 如果是最后一个订单，按总的数量减去已经分摊的数量
                            orderMoneyIntegral = moneyIntegral - moneyShared;
                            orderIntegral = integral - integralShared;
                            orderNew.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            orderNew.setIntegral(orderIntegral);
                            order.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            order.setIntegral(orderIntegral);
                        } else {
                            // 计算分摊比例
                            BigDecimal scale = order.getMoneyOrder().divide(allMoneyOrder, 2, BigDecimal.ROUND_HALF_UP);
                            // 计算分摊金额
                            orderMoneyIntegral = (new BigDecimal(moneyIntegral)).multiply(scale).intValue();
                            // 计算分摊积分数
                            orderIntegral = orderMoneyIntegral * config.getIntegralScale();
                            orderNew.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            orderNew.setIntegral(orderIntegral);
                            order.setMoneyIntegral(new BigDecimal(orderMoneyIntegral));
                            order.setIntegral(orderIntegral);
                        }
                        // 记录已经分摊的金额和积分
                        moneyShared += orderMoneyIntegral;
                        integralShared += orderIntegral;

                        // 当前订单分摊金额、积分数大于0则更改订单的积分转换金额和积分数
                        if (orderMoneyIntegral > 0 || orderIntegral > 0) {
                            Integer update = ordersWriteDao.update(orderNew);
                            if (update == 0) {
                                throw new BusinessException("设置订单积分金额时失败，请重试！");
                            }
                        }
                    }

                    // 修改用户积分数，记录积分消耗日志
                    Member memberNew = new Member();
                    memberNew.setId(member.getId());
                    memberNew.setIntegral(0 - integral);
                    Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
                    if (updateIntegral == 0) {
                        throw new BusinessException("扣除用户积分时失败，请重试！");
                    }
                    MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                    memberGradeIntegralLogs.setMemberId(member.getId());
                    memberGradeIntegralLogs.setMemberName(member.getName());
                    memberGradeIntegralLogs.setValue(integral);
                    memberGradeIntegralLogs.setOptType(ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_7);
                    memberGradeIntegralLogs.setOptDes("订单" + relationOrderSn + "消费积分");
                    memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_2);
                    memberGradeIntegralLogs.setCreateTime(new Date());
                    Integer save = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                    if (save == 0) {
                        throw new BusinessException("记录用户积分消费日志失败，请重试！");
                    }

                }

                if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
                    // 只要是货到付款，则不跳转到支付页面
                    orderSuccVO.setGoJumpPayfor(false);
                }

                if (Orders.PAYMENT_CODE_OFFSEND.equals(orderCommitVO.getPaymentCode())) {
                    // 只要是线下打款，则不跳转到支付页面
                    orderSuccVO.setGoJumpPayfor(false);
                }
                
                if (orderCommitVO.getTwoJGFlag() == 1) {
                	// 有二次加工的需要等待 后台确认
                    orderSuccVO.setGoJumpPayfor(false);
                }

                //如果使用了余额支付 ，更新余额并记录日志
                if (orderCommitVO.getIsBalancePay() != null && orderCommitVO.getIsBalancePay()) {
                    // 为减小并发的概率，此处再次取member对象获取最新的余额信息
                    Member memberNew = memberReadDao.get(orderCommitVO.getMemberId());

                    // 是否修改订单flg，如果余额足够支付所有订单的金额，则直接用余额全部支付
                    boolean updateFlg = false;

                    if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
                        // 如果订单是货到付款，且余额不足以支付所有的金额则抛出，如果足够支付所有金额，则继续
                        if (cartInfoVO.getFinalAmount().compareTo(memberNew.getBalance()) > 0) {
                            throw new BusinessException("余额不足，请重新选择支付方式，或拆分订单后再下单！");
                        }
                        // 余额足以支付所有订单，则flg置true
                        updateFlg = true;
                    } else {
                        // 如果是在线支付，且余额足以支付所有的金额，则flg置true
                        MemberCredit mc = memberCreditReadDao.getByMemberId(member.getId());
                        if (mc == null)
                            mc = new MemberCredit();
                        BigDecimal creditAmount = (mc == null || mc.getSurplus() == null)
                            ? BigDecimal.ZERO : mc.getSurplus();
                        //余额不足以支付订单且订单金额小于等于赊账剩余额度时，使用赊账
                        if (memberNew.getBalance().compareTo(cartInfoVO.getFinalAmount()) < 0
                            && cartInfoVO.getFinalAmount().compareTo(creditAmount) <= 0) {
                            updateFlg = true;
                        } else {
                            throw new BusinessException("您的余额不足以支付此订单，请充值");
                        }
                    }

                    // 根据flg判断是否需要修改订单
                    if (updateFlg) {
                        // 修改用户的余额，记录余额使用日志
                        // 修改订单的状态为已支付状态，修改支付方式信息，修改余额支付金额信息
                        // 修改商品和货品的库存
                        this.balancePay(memberNew, orderCommitVO, orderList);

                        // 记录是否已支付，返回判断跳往什么页面
                        orderSuccVO.setIsPaid(true);
                        orderSuccVO.setPaymentCode("余额支付");
                        orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                        orderSuccVO.setGoJumpPayfor(false);
                    }
                }

                // 如果订单还没有支付
                // 循环判断订单应支付的金额，如果应支付金额小于等于0则修改订单的状态为支付状态
                if (!orderSuccVO.getIsPaid()) {
                    // 返回true说明修改了支付状态
                    if (this.checkNeedPay(orderList, member)) {
                        orderSuccVO.setIsPaid(true);
                        orderSuccVO.setGoJumpPayfor(false);
                    }
                }

                relationOrderSn = saveOrderTradeSerial(rids, orderSuccVO.getIsPaid());
                //存在全场红包
                OrderCouponVO couponVo = orderCommitVO.getSellerCouponMap().get(0);
                if(couponVo != null){
                    CouponUser couponUser = couponUserReadDao.getCouponUserOnlyByCouponSn(orderCommitVO.getSellerCouponMap().get(0).getCouponSn());
                    Coupon coupon  = couponReadDao.get(couponUser.getCouponId());
                    
                    terraceSelfCoupon.setCouponId(coupon.getId());
                    terraceSelfCoupon.setCouponSn(couponUser.getCouponSn());
                    terraceSelfCoupon.setCouponValue(coupon.getCouponValue());
                    terraceSelfCoupon.setMemberId(member.getId());
                    terraceSelfCoupon.setStatus(1);
                    terraceSelfCoupon.setTradeNo(relationOrderSn);
                    Integer terraceId = terraceSelfCouponWriteDao.insert(terraceSelfCoupon);
                    
                    OrderCouponVO orderCouponVO = new OrderCouponVO();
                    orderCouponVO.setCoupon(coupon);
                    orderCouponVO.setCouponUser(couponUser);
                    orderCouponVO.setCouponSn(couponUser.getCouponSn());
                    orderCouponVO.setCouponType(1);
                    
                    // 设定优惠券使用日志
                    CouponOptLog couponOptLog = new CouponOptLog();
                    couponOptLog.setCouponUserId(couponUser.getId());
                    couponOptLog.setMemberId(member.getId());
                    couponOptLog.setSellerId(0);
                    couponOptLog.setCouponId(orderCouponVO.getCoupon().getId());
                    couponOptLog.setOptType(CouponOptLog.OPT_TYPE_2);
                    couponOptLog.setOrderId(terraceId);
                    couponOptLog.setCreateUserId(member.getId());
                    couponOptLog.setCreateUserName(member.getName());
                    couponOptLog.setCreateTime(new Date());
                    couponOptLogList.add(couponOptLog);
                }
                
                // 订单操作完毕，操作优惠券、满减、立减的使用和参与日志
                if (couponOptLogList.size() > 0) {
                    // 记录用户优惠券操作日志
                    couponOptLogWriteDao.batchInsertCouponOptLog(couponOptLogList);
                    // 修改用户优惠券可使用次数
                    for (CouponOptLog couponOptLog : couponOptLogList) {
                        couponUserWriteDao.updateCanUse(couponOptLog.getMemberId(), couponOptLog.getCouponUserId(), couponOptLog.getOrderId());
                    }
                }
                // 记录满减活动参与日志
                if (actFullLogList.size() > 0) {
                    actFullLogWriteDao.batchInsertActFullLog(actFullLogList);
                }
                // 记录单品立减参与日志
                if (actSingleLogList.size() > 0) {
                    actSingleLogWriteDao.batchInsertActSingleLog(actSingleLogList);
                }

                //封装返回对象 
                orderSuccVO.setOrdersList(orderList);
//                if (relationOrderSn.length() > 0) {
//                    // 截去最后的逗号
//                    relationOrderSn = relationOrderSn.substring(0, relationOrderSn.length() - 1);
//                }
                orderSuccVO.setRelationOrderSn(relationOrderSn);
                //              orderSuccVO.setPayAmount(cartInfoVO.getFinalAmount().subtract(
                //                   new BigDecimal(moneyIntegral)));
                orderSuccVO.setPayAmount(allMoneyOrder);
                orderSuccVO.setIsBanlancePay(orderCommitVO.getIsBalancePay());
                orderSuccVO.setBalancePwd(orderCommitVO.getBalancePwd());
            } else {
                throw new BusinessException("购物车中无商品，请选择购买的商品。");
            }
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            be.printStackTrace();
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
    }

    private String saveOrderTradeSerial(List<Integer> rids, Boolean ispay) {
        if (rids == null || rids.size() < 1) {
            return null;
        }
        if (ispay == null)
            ispay = Boolean.FALSE;

        String tradesn = RandomUtil.getOrderSn();

        String timestr_ = String.valueOf((new Date()).getTime());

        //余额标志固定0
        String tradeSerial = timestr_ + EjavashopConfig.ORDER_SEPARATOR + "0"
                             + EjavashopConfig.ORDER_SEPARATOR
                             + StringUtils.join(rids, EjavashopConfig.ORDER_SEPARATOR);
        log.debug("tradeSerial:" + tradeSerial);

        //保存总订单号和商家子订单的关系
        OrdersTradeSerial ots = new OrdersTradeSerial();
        ots.setTradeSn(tradesn);
        ots.setPaymentState(ispay ? 1 : 0);
        ots.setRelationOrderSn(tradeSerial);
        ordersTradeSerialWriteDao.save(ots);

        //Terry 20160920 update orders tradeNo if recount transfer fee in system platform 
        for (int orderId : rids) {
            Orders ordersNew = new Orders();
            ordersNew.setId(orderId);
            ordersNew.setTradeNo(tradesn);
            // 1.更新订单
            ordersWriteDao.update(ordersNew);
        }
        return tradesn;
    }

    /**
     * 订单使用优惠券校验
     * @param orderCouponVO 使用店铺优惠券信息
     * @param memberId 下单用户ID
     * @param cartListVO 订单购物车信息
     * @param channel 渠道
     */
    private void checkSellerCoupon(OrderCouponVO orderCouponVO, Integer memberId, CartListVO cartListVO, Integer channel) {
        Seller seller = cartListVO.getSeller();
        Integer couponType = orderCouponVO.getCouponType();

        if (couponType != null && couponType > 0) {

            List<CouponUser> couponUserList = couponUserReadDao
                .getCouponUserByCouponSn(orderCouponVO.getCouponSn(), seller.getId());
            if (couponUserList == null || couponUserList.size() == 0) {
                log.error("获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误，请检查优惠券序列号、所属店铺是否正确。");
                throw new BusinessException(
                    "获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误，请检查优惠券序列号、所属店铺是否正确。");
            }
            CouponUser couponUser = couponUserList.get(0);

            if (couponType == OrderCouponVO.COUPON_TYPE_1) {
                // 检查优惠券所属用户
                if (!memberId.equals(couponUser.getMemberId())) {
                    throw new BusinessException(
                        "优惠券【" + orderCouponVO.getCouponSn() + "】不是属于您的优惠券，不能使用。");
                }
            } else if (couponType == OrderCouponVO.COUPON_TYPE_2) {
                // 校验密码
                if (couponUser.getPassword() == null || !couponUser.getPassword()
                    .equals(Md5.getMd5String(orderCouponVO.getCouponPassword()))) {
                    throw new BusinessException(
                        "优惠券【" + orderCouponVO.getCouponSn() + "】密码不对，请重新输入。");
                }
                // 检查优惠券所属用户
                if (couponUser.getMemberId() > 0 && !couponUser.getMemberId().equals(memberId)) {
                    throw new BusinessException(
                        "优惠券【" + orderCouponVO.getCouponSn() + "】不是属于您的优惠券，不能使用。");
                }
            } else {
                throw new BusinessException("优惠券信息错误，请重新输入。");
            }

            // 优惠券可使用次数
            if (couponUser.getCanUse() < 1) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】已使用过，不能再次使用。");
            }

            // 优惠券用户关联的优惠券信息校验
            Coupon coupon = couponReadDao.get(couponUser.getCouponId());
            if (coupon == null) {
                log.error("获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误（couponId="
                          + couponUser.getCouponId() + " ）。");
                throw new BusinessException(
                    "获取优惠券【" + orderCouponVO.getCouponSn() + "】信息错误，请检查优惠券序列号、所属店铺是否正确。");
            }

            // 适用最低金额校验
            if (coupon.getMinAmount()
                .compareTo(cartListVO.getSellerCheckedDiscountedAmount()) > 0) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】最低适用订单金额不得小于"
                                            + coupon.getMinAmount() + "元。");
            }
            // 优惠券使用时间校验
            if (coupon.getUseStartTime().after(new Date())) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】还没有到可使用时间。");
            }
            if (coupon.getUseEndTime().before(new Date())) {
                throw new BusinessException("优惠券【" + orderCouponVO.getCouponSn() + "】已过期。");
            }

            // 使用渠道校验
            if (coupon.getChannel().intValue() != ConstantsEJS.CHANNEL_1
                && coupon.getChannel().intValue() != channel.intValue()) {
                String channelStr = channel.intValue() == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException(
                    "优惠券【" + orderCouponVO.getCouponSn() + "】只能在" + channelStr + "使用。");
            }

            orderCouponVO.setCoupon(coupon);
            orderCouponVO.setCouponUser(couponUser);
        }

    }

    /**
     * 循环判断订单应支付的金额，如果应支付金额小于等于0则修改订单的状态为支付状态<br>
     * 处理例如使用积分足够支付整个订单金额等的特殊情况
     * @param orderList
     * @param member
     * @return 不满足修改状态的条件则返回false，否则返回true
     */
    private boolean checkNeedPay(List<Orders> orderList, Member member) {

        for (Orders order : orderList) {
            BigDecimal payMoney = order.getMoneyOrder().subtract(order.getMoneyIntegral());
            if (payMoney.compareTo(BigDecimal.ZERO) <= 0) {

                // 更新订单付款状态
                Orders newOrder = new Orders();
                newOrder.setId(order.getId());
                newOrder.setOrderState(Orders.ORDER_STATE_3);
                newOrder.setPayTime(new Date()); //Terry mark
                newOrder.setPaymentStatus(Orders.PAYMENT_STATUS_1);

                int updateRow = ordersWriteDao.update(newOrder);
                if (updateRow == 0) {
                    log.error("修改订单支付状态时失败。");
                    throw new BusinessException("下单时发生异常，请重试！");
                }

                // 支付成功，需要修改每个货品的库存
                this.updateProductStock(order.getId(), false);

                OrderLog orderLog = new OrderLog();
                orderLog.setContent("您的订单已支付");
                orderLog.setOperatingId(member.getId());
                orderLog.setOrdersId(order.getId());
                orderLog.setOrdersSn(order.getOrderSn());
                orderLog.setOperatingName(member.getName());
                orderLogWriteDao.save(orderLog);

                // 记录订单支付日志
                OrderPayLog payLog = new OrderPayLog();
                payLog.setOrdersId(order.getId());
                payLog.setOrdersSn(order.getOrderSn());
                payLog.setPayStatus(order.getPaymentCode());
                payLog.setPayContent("");
                payLog.setPayMoney(BigDecimal.ZERO);
                payLog.setMemberId(member.getId());
                payLog.setMemberName(member.getName());
                payLog.setCreateTime(new Date());
                orderPayLogWriteDao.save(payLog);
            } else {
                // 如果有一个订单不满这种情况，则整个订单就不会满足这种情况，直接返回false
                return false;
            }
        }
        return true;
    }

    /**
     * 修改用户的余额，记录余额使用日志<br>
     * 修改订单的状态为已支付状态，修改支付方式信息，修改余额支付金额信息<br>
     * 修改商品和货品的库存
     * @param memberNew
     * @param orderCommitVO
     * @param orderList
     */
    private void balancePay(Member memberNew, OrderCommitVO orderCommitVO, List<Orders> orderList) {
        for (Orders order : orderList) {
            // 需要付款的金额（订单金额 - 积分换算金额）
            BigDecimal money = order.getMoneyOrder().subtract(order.getMoneyIntegral());
            // 修改用户的余额
            Member updateBalanceObj = new Member();
            updateBalanceObj.setId(orderCommitVO.getMemberId());
            updateBalanceObj.setBalance(money.multiply(new BigDecimal(-1)));
            Integer updateBalance = memberWriteDao.updateBalance(updateBalanceObj);
            if (updateBalance == 0) {
                log.error("扣除余额时失败。");
                throw new BusinessException("扣除余额时失败，请重试！");
            }

            //赊账信息
            //用户余额小于等于应付金额，则使用赊账
            if (memberNew.getBalance().compareTo(money) <= 0) {
                MemberCredit mc = memberCreditReadDao.getByMemberId(memberNew.getId());
                if (mc == null)
                    mc = new MemberCredit();
                //如果订单金额大于赊账额度，抛出异常
                if (money.compareTo(mc.getSurplus()) > 0) {
                    throw new BusinessException("您的余额已不足以支付此笔订单，请先还清欠款");
                }
                //未还清欠款
                if (memberNew.getBalance().compareTo(BigDecimal.ZERO) <= 0
                    && mc.getHasSettled().intValue() == 0) {
                    //未结清欠款，则余额+本次消费不能超过赊账总额度
                    BigDecimal userCredit = memberNew.getBalance().multiply(new BigDecimal(-1));
                    if (userCredit.add(money).compareTo(mc.getQuota()) > 0) {
                        throw new BusinessException(
                            "您账户的赊账总额度为" + mc.getQuota() + "元，您当前已欠款" + userCredit
                                                    + "元，本次透支额度超过最大赊账额度，请还清欠款或充值后重新下单。");
                    }
                }
                BigDecimal creditBefore = mc.getSurplus();

                BigDecimal curCreditPay = money;
                //如果订单金额大于剩余额度
                if (money.compareTo(mc.getSurplus()) > 0) {
                    curCreditPay = mc.getSurplus();
                }
                //已使用
                mc.setUsed(mc.getUsed().add(curCreditPay));
                //剩余额度
                mc.setSurplus(mc.getSurplus().subtract(curCreditPay));
                memberCreditWriteDao.update(mc);

                //赊账日志
                MemberCreditLog mclog = new MemberCreditLog(mc.getId(), memberNew.getId(),
                    order.getOrderSn(), money, order.getPayTime(), order.getPaymentStatus(),
                    memberNew.getBalance(), memberNew.getBalance().subtract(money), curCreditPay,
                    creditBefore, mc.getSurplus(), memberNew.getId(), memberNew.getName(),
                    new Date(), memberNew.getName());
                memberCreditLogWriteDao.save(mclog);
            }

            // 记录【会员账户余额变化日志表】
            MemberBalanceLogs logs = new MemberBalanceLogs();
            logs.setMemberId(memberNew.getId());
            logs.setMemberName(memberNew.getName());
            logs.setMoneyBefore(memberNew.getBalance());
            logs.setMoneyAfter(memberNew.getBalance().subtract(money));
            logs.setMoney(money);
            logs.setCreateTime(new Date());
            logs.setState(MemberBalanceLogs.STATE_3);
            logs.setRemark("消费，订单号" + order.getOrderSn());
            logs.setOptId(memberNew.getId());
            logs.setOptName(memberNew.getName());

            Integer balanceLog = memberBalanceLogsWriteDao.save(logs);
            if (balanceLog == 0) {
                log.error("记录会员余额变化日志时出错。");
                throw new BusinessException("扣除余额时失败，请重试！");
            }

            // 记录金额变更
            memberNew.setBalance(memberNew.getBalance().subtract(money));

            // 更新订单付款状态
            Orders newOrder = new Orders();
            newOrder.setId(order.getId());
            //使用余额支付 订单类型：未付款订单，需要后台确认操作。【仝照美】
            //            newOrder.setOrderState(Orders.ORDER_STATE_1);
            //Terry 20161022 余额支付订单 修改为待确认订单状态
            newOrder.setOrderState(Orders.ORDER_STATE_2);
            newOrder.setPayTime(new Date()); //Terry mark
            newOrder.setPaymentStatus(Orders.PAYMENT_STATUS_1);
            newOrder.setMoneyPaidBalance(money);
            newOrder.setPaymentName("余额支付");
            newOrder.setPaymentCode(Orders.PAYMENT_CODE_BALANCE);

            int updateRow = ordersWriteDao.update(newOrder);
            if (updateRow == 0) {
                log.error("余额支付修改订单支付状态时失败。");
                throw new BusinessException("下单时发生异常，请重试！");
            }

            // 支付成功，需要修改每个货品的库存
            this.updateProductStock(order.getId(), false);

            OrderLog orderLog = new OrderLog();
            orderLog.setContent("您使用余额支付了订单");
            orderLog.setOperatingId(memberNew.getId());
            orderLog.setOrdersId(order.getId());
            orderLog.setOrdersSn(order.getOrderSn());
            orderLog.setOperatingName(memberNew.getName());
            orderLogWriteDao.save(orderLog);

            // 记录订单支付日志
            OrderPayLog payLog = new OrderPayLog();
            payLog.setOrdersId(order.getId());
            payLog.setOrdersSn(order.getOrderSn());
            payLog.setPayStatus(order.getPaymentCode());
            payLog.setPayContent("");
            payLog.setPayMoney(money);
            payLog.setMemberId(memberNew.getId());
            payLog.setMemberName(memberNew.getName());
            payLog.setCreateTime(new Date());
            orderPayLogWriteDao.save(payLog);
        }
    }

    /**
     * 保存商家的订单，以及订单日志<br>
     * 
     * @param cartListVO
     * @param member
     * @param orderCommitVO
     * @param address
     * @param orderCouponVO 使用的优惠券信息
     * @param actFullLogList
     * @param actSingleLogList
     * @return
     */
    private Orders saveOrderInfo(CartListVO cartListVO, Member member, OrderCommitVO orderCommitVO, MemberAddress address, OrderCouponVO orderCouponVO, 
                                     List<CouponOptLog> couponOptLogList, List<ActFullLog> actFullLogList, String ordersType, String logisticsNameOms) {
        Seller seller = cartListVO.getSeller();
        // 生成订单编号
        String orderSn = RandomUtil.getOrderSn();
        // 生成订单
        Orders order = new Orders();
        // 设为普通订单
        order.setOrderType(Integer.parseInt(ordersType));
        order.setOrderSn(orderSn);
        //送货上门区域
        order.setSendArea(orderCommitVO.getSendArea());
        //设置是直发 还是代发
        order.setSendType(Integer.parseInt(orderCommitVO.getSendType()));
        // 关联订单编号
        order.setRelationOrderSn("");
        order.setSellerId(seller.getId());
        order.setMemberId(member.getId());
        order.setMemberName(member.getName());
        // 判断发票状态，记录发票信息
        order.setInvoiceStatus(orderCommitVO.getInvoiceStatus());
        if (Orders.INVOICE_STATUS_0 != orderCommitVO.getInvoiceStatus().intValue()) {
            order.setInvoiceTitle(orderCommitVO.getInvoiceTitle());
            order.setInvoiceType(orderCommitVO.getInvoiceType());
        }

        order.setIp(orderCommitVO.getIp());
        // 支付信息
        order.setPaymentName(orderCommitVO.getPaymentName());
        order.setPaymentCode(orderCommitVO.getPaymentCode());

        // 收货地址信息设置
        //普通订单设置地址
        if (orderCommitVO.getLogisticsId() != null && orderCommitVO.getLogisticsId() != 6
            && orderCommitVO.getLogisticsId() != 7) {
            order.setName(address.getmemberName());
            order.setProvinceId(address.getProvinceId());
            order.setCityId(address.getCityId());
            order.setAreaId(address.getAreaId());
            order.setAddressAll(address.getAddAll());
            order.setAddressInfo(address.getAddressInfo());
            order.setZipCode(address.getZipCode());
            order.setMobile(address.getMobile());
            order.setEmail(address.getEmail());
        } else {
            //自提订单，将真实姓名存库 add by tongzhaomei
            order.setName(member.getRealName());
        }

        // 设置订单备注
        order.setRemark(orderCommitVO.getRemark());
        // 在线交易支付流水号
        order.setTradeSn("");
        // 订单来源：1、pc；2、H5；3、Android；4、IOS
        order.setSource(orderCommitVO.getSource());
        // 物流信息
        if (!"".equals(orderCommitVO.getLogisticsId()) && orderCommitVO.getLogisticsId() != 0) {
            Integer logisticsId = orderCommitVO.getLogisticsId();
            //            System.out.println(logisticsId);
            if (logisticsId > 50) {
                logisticsId = Integer.valueOf(logisticsId.toString().substring(0, 1));
            }
            SellerTransport sTransport = sellerTransportReadDao.getInUseBySellerId(1, logisticsId);
            order.setLogisticsId(logisticsId);
            order.setLogisticsName(sTransport.getTransName());
            if ("".equals(logisticsNameOms))
                logisticsNameOms = sTransport.getTransName();
            order.setLogisticsNameOms(logisticsNameOms);                    //Terry 20170228
        } else {
            order.setLogisticsId(0);
            order.setLogisticsName("");
        }

        order.setLogisticsNumber("");

        // 是否货到付款订单，根据payMentCode判断
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            // 是否货到付款订单0、不是；1、是
            order.setIsCodconfim(Orders.IS_CODCONFIM_1);
            // 货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
            order.setCodconfirmState(Orders.CODCONFIRM_STATE_1);
        } else {
            order.setIsCodconfim(Orders.IS_CODCONFIM_0);
            order.setCodconfirmState(Orders.CODCONFIRM_STATE_0);
        }
        order.setCodconfirmId(0);
        order.setCodconfirmName("");
        order.setCodconfirmRemark("");

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        /**
         * 金额的计算
         */
        // 判断0.01支付体验
        // 金额信息
        Integer productId = 0;
        BigDecimal packageFee = BigDecimal.ZERO;
        BigDecimal labelFee = BigDecimal.ZERO;
        //服务费
        for (Cart cart : cartListVO.getCartList()) {
            //总套餐服务费
            packageFee = packageFee.add(cart.getPackageFee() == null ? BigDecimal.ZERO : cart.getPackageFee());
            productId = cart.getProductId();
        }

        //总套餐费
        order.setServicePrice(packageFee);
        //辅料费
        for (Cart cart : cartListVO.getCartList()) {
            //总套餐服务费
            labelFee = labelFee.add(cart.getLabelFee() == null ? BigDecimal.ZERO : cart.getLabelFee());
            productId = cart.getProductId();
        }
        //总辅料费
        order.setLabelPrice(labelFee);
        // 商品总额（只是商品价格*数量的金额之和）
        order.setMoneyProduct(cartListVO.getSellerCheckedAmount());
        if (!"".equals(productId) && productId == 10) {
            order.setMoneyProduct(new BigDecimal(0.01));
        }
        //上门自提和三方仓储是没有物流费用的
        // 判断物流费用
        if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            order.setMoneyLogistics(BigDecimal.ZERO);
        }

        //        order.setMoneyLogistics(cartListVO.getSellerLogisticsFee());

        //modify By Ls 此处特殊处理，因为货品会根据seller_id拆单，所以这里邮费取总后按重量进行分配邮费
        //          -----------------------begin------------------------
        Integer logisticsId = orderCommitVO.getLogisticsId();
        if (logisticsId > 50) {
            Integer sendArea = logisticsId % 10;
            Integer count = cartReadDao.getCountByMemberId(order.getMemberId(), null, null);
            Integer orderCount = cartReadDao.getCountByMemberId(order.getMemberId(), null, seller.getId());
            BigDecimal moneyLogistics = this.getMoneyLogistics(sendArea, count);
            BigDecimal scale = new BigDecimal(orderCount * 1.00 / count);
            order.setMoneyLogistics(moneyLogistics.multiply(scale).setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            if (logisticsId == 8) {//物流中转收费大于1000双不收费否则收费20元
                //提交订单时的商品总数量
                //Integer count = cartReadDao.getCountByMemberId(order.getMemberId(), null, null);
                //拆单后每个分单的商品数量
                //Integer orderCount = cartReadDao.getCountByMemberId(order.getMemberId(), null, seller.getId());
                //BigDecimal moneyLogistics = this.getLogisticsFee(count);
                //BigDecimal scale = new BigDecimal(orderCount * 1.00 / count);
                //order.setMoneyLogistics(moneyLogistics.multiply(scale).setScale(2, BigDecimal.ROUND_HALF_UP));
                order.setMoneyLogistics(new BigDecimal(0.00));
            } else {
                Double weight_total = cartReadDao.getWeightByMemberId(orderCommitVO.getMemberId(), null);
                Double weight_order = cartReadDao.getWeightByMemberId(orderCommitVO.getMemberId(), seller.getId());
                Integer cityId = 1;
                if (address != null && !"".equals(address.getCityId())) {
                    cityId = address.getCityId();
                }
                BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(1, weight_total, cityId, orderCommitVO.getLogisticsId());
                BigDecimal order_transportFee = new BigDecimal(weight_order / weight_total);
                order.setMoneyLogistics(calculateTransFee.multiply(order_transportFee).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        }
        
        //          ----------------------end-----------------------------
        // 余额支付金额（此处暂时设定为0，支付之后再修改）
        order.setMoneyPaidBalance(BigDecimal.ZERO);
        // 现金支付金额
        order.setMoneyPaidReality(new BigDecimal(0));

        // 优惠券优惠金额、优惠券ID（coupon_user的ID）
        // 如果使用了优惠券
        if (orderCouponVO != null && orderCouponVO.getCoupon() != null) {
            order.setMoneyCoupon(orderCouponVO.getCoupon().getCouponValue());
            order.setCouponUserId(orderCouponVO.getCouponUser().getId());
        } else {
            order.setMoneyCoupon(new BigDecimal(0));
            order.setCouponUserId(0);
        }
        // 订单满减金额
        if (cartListVO.getOrderDiscount() != null
            && cartListVO.getOrderDiscount().compareTo(BigDecimal.ZERO) > 0) {
            order.setMoneyActFull(cartListVO.getOrderDiscount());
            order.setActFullId(cartListVO.getActFull().getId());
        } else {
            order.setMoneyActFull(BigDecimal.ZERO);
            order.setActFullId(0);
        }
        order.setActivityId(0);
        // 优惠金额总额（满减、立减、优惠券和）
        // 如果从购物车中计算出的优惠总额为空，则赋0，然后加上商家优惠券的额度，计算出所有的优惠总额
        BigDecimal moneyDiscount = cartListVO.getSellerCheckedDiscounted() == null ? BigDecimal.ZERO : cartListVO.getSellerCheckedDiscounted();
        // 现在是单品立减、订单满减、商家优惠券三个优惠，以后如果加其他优惠政策则加上新优惠的金额
        order.setMoneyDiscount(moneyDiscount.add(order.getMoneyCoupon()));
        // 新订单退款的金额为0
        order.setMoneyBack(new BigDecimal(0));
        // 订单使用积分金额，所有订单入库后再计算该金额
        order.setMoneyIntegral(BigDecimal.ZERO);
        order.setIntegral(0);

        // 订单总金额，等于商品总金额＋运费-优惠金额总额（这个金额是最后结算给商家的金额）
        //如果是三方仓储和上门自提则没有运费
        if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            order.setMoneyOrder(order.getMoneyProduct().subtract(order.getMoneyDiscount()));
        } else {
            //Terry add .add(order.getServicePrice() 20160801
            order.setMoneyOrder((order.getMoneyProduct().add(order.getMoneyLogistics()).add(order.getServicePrice())).add(order.getLabelPrice()).subtract(order.getMoneyDiscount()));
        }
        if (!"".equals(productId) && productId == 10) {
            order.setMoneyOrder(new BigDecimal(0.01));
        }
        if (order.getMoneyOrder().compareTo(new BigDecimal(0)) <= 0) {
            // 如果订单金额为0 直接认为已付款 
            order.setOrderState(Orders.ORDER_STATE_3);
            order.setPayTime(new Date()); //Terry mark
            if (orderCommitVO.getTwoJGFlag() == 1) {//需要二次加工订单
                order.setPaymentStatus(Orders.PAYMENT_STATUS_2);
            } else {
                order.setPaymentStatus(Orders.PAYMENT_STATUS_1);
            }
        } else {
            // 其他情况
            if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode()) || Orders.PAYMENT_CODE_BALANCE.equals(orderCommitVO.getPaymentCode())) {
                // 如果是货到付款
                order.setOrderState(Orders.ORDER_STATE_2);
            } else if (Orders.PAYMENT_CODE_ONLINE.equals(orderCommitVO.getPaymentCode())) {
                // 如果不是货到付款
                order.setOrderState(Orders.ORDER_STATE_1);
            } else {
                //线下支付
                order.setOrderState(Orders.ORDER_STATE_1);
            }
            if (orderCommitVO.getTwoJGFlag() == 1) {//需要二次加工订单
                order.setPaymentStatus(Orders.PAYMENT_STATUS_2);
            } else {
                order.setPaymentStatus(Orders.PAYMENT_STATUS_0);
            }
        }

        // 1、保存订单
        int count = ordersWriteDao.insert(order);
        if (count == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }
        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setContent("您提交了订单，请等待系统确认");
        orderLog.setOperatingId(member.getId());
        orderLog.setOrdersId(order.getId());
        orderLog.setOrdersSn(order.getOrderSn());
        orderLog.setOperatingName(member.getName());

        int orderlogCount = orderLogWriteDao.save(orderLog);
        if (orderlogCount == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }

        if (orderCouponVO != null && orderCouponVO.getCoupon() != null) {
            CouponUser couponUser = orderCouponVO.getCouponUser();
            // 设定优惠券使用日志
            CouponOptLog couponOptLog = new CouponOptLog();
            couponOptLog.setCouponUserId(couponUser.getId());
            couponOptLog.setMemberId(member.getId());
            couponOptLog.setSellerId(seller.getId());
            couponOptLog.setCouponId(orderCouponVO.getCoupon().getId());
            couponOptLog.setOptType(CouponOptLog.OPT_TYPE_2);
            couponOptLog.setOrderId(order.getId());
            couponOptLog.setCreateUserId(member.getId());
            couponOptLog.setCreateUserName(member.getName());
            couponOptLog.setCreateTime(new Date());
            couponOptLogList.add(couponOptLog);
        }

        // 订单满减金额
        if (cartListVO.getOrderDiscount() != null
            && cartListVO.getOrderDiscount().compareTo(BigDecimal.ZERO) > 0) {
            // 设定订单满减参与日志
            ActFullLog actFullLog = new ActFullLog();
            actFullLog.setActFullId(cartListVO.getActFull().getId());
            actFullLog.setMemberId(member.getId());
            actFullLog.setSellerId(seller.getId());
            actFullLog.setOrderId(order.getId());
            actFullLog.setCreateUserId(member.getId());
            actFullLog.setCreateUserName(member.getName());
            actFullLog.setCreateTime(new Date());
            actFullLogList.add(actFullLog);
        }

        return order;
    }

    private BigDecimal getLogisticsFee(Integer count) {
        BigDecimal transFee = BigDecimal.ZERO;
        if (count < 1000) {
            transFee = new BigDecimal(20.00);
        }
        return transFee;
    }

    private BigDecimal getMoneyLogistics(Integer sendArea, Integer count) {
        BigDecimal moneyLogistics = BigDecimal.ZERO;
        double transportFee = 0.00;
        int boxCount = 0;
        if (count % 500 > 0) {
            boxCount = count / 500 + 1;
        }
        //一区配送价格
        if (sendArea == 2) {
            transportFee = 4.00 * boxCount;
            if (transportFee < 20) {
                transportFee = 20.00;
            }
            if (count >= 1000) {
                transportFee = 0.00;
            }
        }
        //二区配送价格
        if (sendArea == 3) {
            transportFee = 4.50 * boxCount;
            if (transportFee < 180) {
                transportFee = 180.00;
            }
            if (count >= 5000) {
                transportFee = 0.00;
            }
        }
        //三区配送价格
        if (sendArea == 4) {
            transportFee = 5.50 * boxCount;
            if (transportFee < 330) {
                transportFee = 330.00;
            }
            if (count >= 20000) {
                transportFee = 0.00;
            }
        }
        //四区配送价格
        if (sendArea == 5) {
            transportFee = 5.00 * boxCount;
            if (transportFee < 250) {
                transportFee = 250.00;
            }
            if (count >= 20000) {
                transportFee = 0.00;
            }
        }
        moneyLogistics = new BigDecimal(transportFee);
        return moneyLogistics;
    }

    /**
     * 保存网单信息
     * @param order
     * @param cartList
     */
    private void saveOrderProductInfo(Orders order, List<Cart> cartList, Member member, List<ActSingleLog> actSingleLogList) {
        for (Cart cart : cartList) {
            ProductGoods goods = cart.getProductGoods();
            Product product = cart.getProduct();
            // 判断商品状态
            if (product.getState().intValue() != Product.STATE_6) {
                throw new BusinessException("商品[" + product.getName1() + "]已下架，请重新选择商品！");
            }
            // 判断分类状态
            if (product.getProductCateState().intValue() != Product.PRODUCT_CATE_STATE_1) {
                throw new BusinessException("商品[" + product.getName1() + "]已下架，请重新选择商品！");
            }
            // 判断库存
            if (goods.getProductStock() <= 0 || goods.getProductStock() < cart.getCount()) {
                throw new BusinessException(
                    "商品[" + product.getName1() + " " + cart.getSpecInfo() + "]的库存不足，请重新选择商品！");
            }
            //Terry add 20160816
            ProductGoods pg = productGoodsReadDao.get(cart.getProductGoodsId());
            if (pg.getProductStock() < cart.getCount())
                throw new BusinessException(
                    "商品[" + product.getName1() + " " + cart.getSpecInfo() + "]的库存不足，请重新选择商品！");

            //保存网单信息
            OrdersProduct op = new OrdersProduct();
            //整箱价进行拆单，分为整箱价和散价两条数据进行存储
            BigDecimal sacle = BigDecimal.ZERO;
            BigDecimal product_amount = BigDecimal.ZERO;
            int count = 0;
            if (product.getPriceStatus() == 3
                && (cart.getPackageFee() == null || "".equals(cart.getPackageFee())
                    || cart.getPackageFee() == BigDecimal.ZERO)) {
                //整箱价总价
                product_amount = new BigDecimal(
                    (cart.getCount() / product.getFullContainerQuantity())
                                                * product.getFullContainerQuantity())
                                                    .multiply(product.getMallPcPrice());
                //散价
                BigDecimal scattered_money = new BigDecimal(
                    cart.getCount() % product.getFullContainerQuantity())
                        .multiply(product.getScatteredPrice());
                BigDecimal amtProduct_money = product_amount.add(scattered_money);
                if (cart.getCurrDiscounted() != null
                    && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
                    //区分是折扣还是减扣单品金额
                    if (cart.getActSingleType() != null && cart.getActSingleType() == 1) {
                        //比例折扣
                        sacle = new BigDecimal(1)
                            .subtract(cart.getCurrDiscountedAmount().divide((amtProduct_money), 2));
                    } else {
                        //立减金额
                        sacle = cart.getCurrAmount().subtract(cart.getCurrDiscountedAmount())
                            .divide((new BigDecimal(cart.getCount())), 2);
                    }
                }

                if (cart.getIsDabiaowa() != null && cart.getIsDabiaowa() == 1) {

                } else {
                    //散价单独保存成为一条数据
                    op = this.getOrdersProduct(order, cart, product, goods, sacle);
                    if (op.getNumber() > 0) {
                        count = ordersProductWriteDao.insert(op);
                        //因为整箱价和散价会生成两条数据，所以两条数据都要进行减库存操作 add by Ls 2016/08/31
                        this.updateProductStockForPayAfter(op.getProductId(),
                            op.getProductGoodsId(), op.getNumber());
                    }
                }
            }

            op.setOrdersId(order.getId());
            op.setOrdersSn(order.getOrderSn());
            op.setSellerId(cart.getSellerId());
            op.setProductCateId(product.getProductCateId());
            op.setProductId(product.getId());
            op.setProductGoodsId(cart.getProductGoodsId());
            op.setSpecInfo(cart.getSpecInfo());
            op.setProductName(product.getName1());
            op.setProductSku(goods.getSku());
            op.setPackageGroupsId(0);
            op.setMallGroupsId(0);
            op.setGiftId(0);
            op.setIsGift(OrdersProduct.IS_GIFT_0);

            op.setProductPackageId(cart.getProductPackageId());
            op.setIsSelfLabel(cart.getIsSelfLabel() == null ? 0 : cart.getIsSelfLabel());
            //套餐单价
            op.setPackageFee(cart.getPackageUnitFee());
            //套餐总价 = 套餐单价*数量
            op.setPackageFeeTotal(
                cart.getPackageFee() == null ? BigDecimal.ZERO : cart.getPackageFee());
            //辅料单价
            op.setLabelFee(cart.getLabelUnitFee());
            //辅料总价 = 辅料单价*数量
            op.setLabelFeeTotal(cart.getLabelFee() == null ? BigDecimal.ZERO : cart.getLabelFee());
            //置为0
            // op.setLabelFee(BigDecimal.ZERO);
            //   op.setLabelFeeTotal(BigDecimal.ZERO);

            // 根据来源确定使用PC价格或者移动端价格，默认使用PC端价格
            BigDecimal price = goods.getMallPcPrice();
            if (order.getSource() != null
                && (order.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                    || order.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID) || order.getSource()
                        .equals(ConstantsEJS.SOURCE_4_IOS))) {
                price = goods.getMallMobilePrice();
            }
            op.setMoneyPrice(price);

            Integer num = 0;
            if (cart.getIsDabiaowa() != null && cart.getIsDabiaowa() == 1) {
                //打标袜 
                //除去整箱价的都是一个价位，只存在一条记录
                int cou = cart.getCount();
                ProductPrice p_Price = productPriceReadDao.get(product.getPrice2Pid());
                if (product.getPrice2Status() == 2) {//阶梯价
                    Integer coun = cartReadDao.getCountByMemberId(member.getId(), product.getId(),
                        null);
                    if (coun >= p_Price.getPrice1S() && coun <= p_Price.getPrice1E()) {
                        price = p_Price.getPrice1();
                    }
                    if (coun >= p_Price.getPrice2S() && coun <= p_Price.getPrice2E()) {
                        price = p_Price.getPrice2();
                    }
                    if (coun >= p_Price.getPrice3S()) {
                        price = p_Price.getPrice3();
                    }
                    //阶梯价 网单商品单价 重新复制为阶梯价
                    op.setMoneyPrice(price);
                } else {
                    op.setMoneyPrice(p_Price.getPriceOnly());
                }
                op.setNumber(cou);
                op.setMoneyAmount(cart.getCurrDiscountedAmount());

            } else {
                //不是打标袜
                //整箱价非二次包装
                if (product.getPriceStatus() == 3
                    && (cart.getPackageFee() == null || "".equals(cart.getPackageFee())
                        || cart.getPackageFee() == BigDecimal.ZERO)) {
                    num = cart.getCount() - op.getNumber();
                    op.setNumber(num);
                    //此处整箱价支持二次加工操作，两条记录存储对应数量的标签价格
                    BigDecimal p_price = cart.getPackageUnitFee() == null ? BigDecimal.ZERO
                        : cart.getPackageUnitFee();
                    op.setPackageFeeTotal(p_price.multiply(new BigDecimal(num)));
                    // 网单金额（减去立减优惠后的金额和）
                    //              double m = cart.getCurrDiscountedAmount().doubleValue() - op.getMoneyAmount().doubleValue();
                    //              BigDecimal money = new BigDecimal(m);
                    op.setMoneyAmount(op.getMoneyPrice().multiply(new BigDecimal(num))
                        .add(op.getPackageFeeTotal()));
                    //阶梯价
                } else {//除去整箱价的都是一个价位，只存在一条记录
                    int cou = cart.getCount();
                    if (product.getPriceStatus() == 2) {//阶梯价
                        Integer coun = cartReadDao.getCountByMemberId(member.getId(), product.getId(), null);
                        ProductPrice p_Price = productPriceReadDao.get(product.getPricePid());
                        if (coun >= p_Price.getPrice1S() && coun <= p_Price.getPrice1E()) {
                            price = p_Price.getPrice1();
                        }
                        if (coun >= p_Price.getPrice2S() && coun <= p_Price.getPrice2E()) {
                            price = p_Price.getPrice2();
                        }
                        if (coun >= p_Price.getPrice3S()) {
                            price = p_Price.getPrice3();
                        }
                        //阶梯价 网单商品单价 重新复制为阶梯价
                        op.setMoneyPrice(price);
                    }
                    op.setNumber(cou);
                    op.setMoneyAmount(cart.getCurrDiscountedAmount());
                }
            }
            // 立减优惠金额和
            if (cart.getCurrDiscounted() != null
                && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal act_money = BigDecimal.ZERO;
                if (cart.getActSingleType() == 1) {
                    act_money = product_amount.multiply(sacle);
                } else {
                    act_money = sacle.multiply(new BigDecimal(num));
                }
                if (product.getPriceStatus() != 3) {
                    //非整箱价折扣只有一个值不需拆开重新计算
                    op.setMoneyActSingle(cart.getCurrDiscounted());
                } else {
                    op.setMoneyActSingle(act_money);
                    op.setMoneyAmount(op.getMoneyAmount().subtract(act_money).setScale(2,
                        BigDecimal.ROUND_HALF_UP));
                }
                op.setActSingleId(cart.getActSingle().getId());
            } else {
                op.setMoneyActSingle(BigDecimal.ZERO);
                op.setActSingleId(0);
            }
            op.setActivityId(0);
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActGroupId(0);
            op.setActBiddingId(0);
            op.setMemberProductBackId(0);
            op.setMemberProductExchangeId(0);
            //整箱价二次加工只有一条整箱价的记录
            if (product.getPriceStatus() == 3
                && !StringUtil.isNullOrZero(cart.getProductPackageId())) {
                op = this.getOrdersProductTwice(order, cart, product, goods);
            }
            // 1、保存网单
            if (op.getNumber() == 0) {
                continue;
            }
            count = ordersProductWriteDao.insert(op);

            //修改此处逻辑，更新product表的库存信息 add by Ls 2016/08/09
            this.updateProductStockForPayAfter(op.getProductId(), op.getProductGoodsId(),
                op.getNumber());

            if (count == 0) {
                throw new BusinessException("网单保存失败，请重试！");
            }

            // 更新商品的销量
            this.updateProductActualSales(cart.getProductId(), cart.getProductGoodsId(),
                cart.getCount());

            // 设定网单的立减活动参与日志
            if (cart.getCurrDiscounted() != null
                && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
                ActSingleLog actSingleLog = new ActSingleLog();
                actSingleLog.setActSingleId(cart.getActSingle().getId());
                actSingleLog.setMemberId(member.getId());
                actSingleLog.setSellerId(cart.getSellerId());
                actSingleLog.setOrderId(order.getId());
                actSingleLog.setOrderProductId(op.getId());
                actSingleLog.setProductId(product.getId());
                actSingleLog.setCreateUserId(member.getId());
                actSingleLog.setCreateUserName(member.getName());
                actSingleLog.setCreateTime(new Date());
                actSingleLogList.add(actSingleLog);
            }
        }
    }

    //二次加工整箱价单独处理
    private OrdersProduct getOrdersProductTwice(Orders order, Cart cart, Product product,
                                                ProductGoods goods) {
        OrdersProduct op = new OrdersProduct();
        op.setOrdersId(order.getId());
        op.setOrdersSn(order.getOrderSn());
        op.setSellerId(cart.getSellerId());
        op.setProductCateId(product.getProductCateId());
        op.setProductId(product.getId());
        op.setProductGoodsId(cart.getProductGoodsId());
        op.setSpecInfo(cart.getSpecInfo());
        op.setProductName(product.getName1());
        op.setProductSku(goods.getSku());
        op.setPackageGroupsId(0);
        op.setMallGroupsId(0);
        op.setGiftId(0);
        op.setIsGift(OrdersProduct.IS_GIFT_0);
        op.setActGroupId(0);
        op.setActBiddingId(0);
        op.setProductPackageId(cart.getProductPackageId());
        op.setIsSelfLabel(cart.getIsSelfLabel() == null ? 0 : cart.getIsSelfLabel());
        //套餐单价
        op.setPackageFee(cart.getPackageUnitFee());
        //套餐总价 = 套餐单价*数量
        op.setPackageFeeTotal(
            cart.getPackageFee() == null ? BigDecimal.ZERO : cart.getPackageFee());

        //置为0
        op.setLabelFee(BigDecimal.ZERO);
        op.setLabelFeeTotal(BigDecimal.ZERO);

        op.setMoneyPrice(product.getMallPcPrice());
        int count = cart.getCount();
        op.setNumber(count);
        // 网单金额（减去立减优惠后的金额和）
        double m = count * product.getMallPcPrice().doubleValue();
        BigDecimal my = new BigDecimal(m);
        // 立减优惠金额和
        if (cart.getCurrDiscounted() != null
            && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
            op.setMoneyActSingle(cart.getCurrDiscounted());
            op.setActSingleId(cart.getActSingle().getId());
        } else {
            op.setMoneyActSingle(BigDecimal.ZERO);
            op.setActSingleId(0);
        }
        //商品整箱价*数量+加工费+辅料费-单品立减
        op.setMoneyAmount(my.add(op.getLabelFeeTotal()).add(op.getPackageFeeTotal()).subtract(op.getMoneyActSingle()));
        op.setActivityId(0);
        op.setActFlashSaleId(0);
        op.setActFlashSaleProductId(0);
        op.setMemberProductBackId(0);
        op.setMemberProductExchangeId(0);
        return op;
    }

    private OrdersProduct getOrdersProduct(Orders order, Cart cart, Product product,
                                           ProductGoods goods, BigDecimal sacle) {

        OrdersProduct op = new OrdersProduct();
        op.setOrdersId(order.getId());
        op.setOrdersSn(order.getOrderSn());
        op.setSellerId(cart.getSellerId());
        op.setProductCateId(product.getProductCateId());
        op.setProductId(product.getId());
        op.setProductGoodsId(cart.getProductGoodsId());
        op.setSpecInfo(cart.getSpecInfo());
        op.setProductName(product.getName1());
        op.setProductSku(goods.getSku());
        op.setPackageGroupsId(0);
        op.setMallGroupsId(0);
        op.setGiftId(0);
        op.setIsGift(OrdersProduct.IS_GIFT_0);
        op.setActGroupId(0);
        op.setActBiddingId(0);
        op.setProductPackageId(cart.getProductPackageId());
        op.setIsSelfLabel(cart.getIsSelfLabel() == null ? 0 : cart.getIsSelfLabel());

        //置为0
        //op.setLabelFee(BigDecimal.ZERO);
        // op.setLabelFeeTotal(BigDecimal.ZERO);

        op.setMoneyPrice(product.getScatteredPrice());
        int count = cart.getCount() - (cart.getCount() / product.getFullContainerQuantity())
                                      * product.getFullContainerQuantity();
        op.setNumber(count);
        // 网单金额（减去立减优惠后的金额和）
        //        op.setMoneyAmount(cart.getCurrDiscountedAmount());
        double m = count * product.getScatteredPrice().doubleValue();
        BigDecimal my = new BigDecimal(m);
        //套餐单价
        op.setPackageFee(cart.getPackageUnitFee());
        //套餐总价 = 套餐单价*数量
        BigDecimal count_z = cart.getPackageUnitFee() == null ? BigDecimal.ZERO
            : cart.getPackageUnitFee();
        //        op.setPackageFeeTotal(cart.getPackageFee() == null ? BigDecimal.ZERO : cart.getPackageFee());
        op.setPackageFeeTotal(
            count_z.multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP));
        //add by tongzhaomei 打标袜  辅料单价
        op.setLabelFee(cart.getLabelUnitFee());
        //辅料总价 = 辅料单价 * 数量
        BigDecimal count_y = cart.getLabelUnitFee() == null ? BigDecimal.ZERO
            : cart.getLabelUnitFee();
        op.setLabelFeeTotal(
            count_y.multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP));
        //end
        // 立减优惠金额和
        if (cart.getCurrDiscounted() != null
            && cart.getCurrDiscounted().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal act_money = BigDecimal.ZERO;
            if (cart.getActSingleType() == 1) {
                //比例
                act_money = my.multiply(sacle);
            } else {
                act_money = sacle.multiply(new BigDecimal(count));
            }
            op.setMoneyActSingle(act_money);
            op.setActSingleId(cart.getActSingle().getId());
        } else {
            op.setMoneyActSingle(BigDecimal.ZERO);
            op.setActSingleId(0);
        }
        op.setMoneyAmount(my.add(op.getPackageFeeTotal()).add(op.getLabelFeeTotal())
            .subtract(op.getMoneyActSingle()));
        op.setActivityId(0);
        op.setActFlashSaleId(0);
        op.setActFlashSaleProductId(0);
        op.setMemberProductBackId(0);
        op.setMemberProductExchangeId(0);
        return op;
    }

    /**
     * 根据商品ID和货品ID更新商品货品的实际销量
     * @param productId
     * @param productGoodsId
     * @param number
     */
    private void updateProductActualSales(Integer productId, Integer productGoodsId,
                                          Integer number) {
    	ProductGoods productGoods = productGoodsWriteDao.get(productGoodsId);
    	Product product = productWriteDao.get(productId);
        // 商品销量 累加
    	int pcount;
    	if (productGoods.getActualSales() < 0) {
    		productGoods.setActualSales(0);
    		pcount = productGoodsWriteDao.update(productGoods);
        }
        	pcount = productGoodsWriteDao.updateActualSales(productGoodsId, number);
            //pcount = productGoodsWriteDao.updateActualSales(productGoodsId, 0);
        if (pcount == 0) {
            log.error("商品实际销量更新失败。");
            throw new BusinessException("商品销量更新失败！");
        }
        
        if (product.getActualSales() < 0 ) {
        	product.setActualSales(0);
        	pcount = productWriteDao.update(product);
        }
        	pcount = productWriteDao.updateActualSales(productId, number);
        	//pcount = productWriteDao.updateActualSales(productId, 0);
        
        if (pcount == 0) {
            log.error("货品实际销量更新失败。");
            throw new BusinessException("货品销量更新失败！");
        }
    }

    /**
     * 确认收货
     * @param ordersId
     * @param request
     * @return
     * @throws Exception 
     */
    public boolean goodsReceipt(Integer ordersId) throws Exception {

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //参数校验
            if (ordersId == null || ordersId == 0) {
                log.error("订单ID为空。");
                throw new BusinessException("订单ID为空，请重试！");
            }

            //获取订单
            Orders orders = ordersReadDao.get(ordersId);
            if (orders == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请重试！");
            } else if (!orders.getOrderState().equals(Orders.ORDER_STATE_4)) {
                log.error("订单不处于已发货状态，不能确认收货。");
                throw new BusinessException("订单不处于已发货状态，不能确认收货！");
            }

            orders.setOrderState(Orders.ORDER_STATE_5);
            orders.setFinishTime(new Date());
            int count = ordersWriteDao.update(orders);
            if (count == 0) {
                log.error("订单更新失败。");
                throw new BusinessException("订单更新失败！");
            }

            // 销量在下单时便增加
            //            //根据订单ID 取网单信息
            //            List<OrdersProduct> ordersProductList = ordersProductWriteDao.getByOrderId(ordersId);
            //            for (OrdersProduct bean : ordersProductList) {
            //                // 商品销量 累加
            //                int pcount = productWriteDao.updateActualSales(bean.getProductId(),
            //                    bean.getNumber());
            //                if (pcount == 0) {
            //                    log.error("商品实际销量更新失败。");
            //                    throw new BusinessException("商品实际销量更新失败！");
            //                }
            //            }

            transactionManager.commit(status);

            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:", e);
            throw e;
        }
    }

    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    public List<OrderDayDto> getOrderDayDto(Map<String, String> queryMap) {
        return ordersReadDao.getOrderDayDto(queryMap);
    }

    /**
     * 统计每天订单量  
     * @param queryMap
     * @return
     */
    public List<OrderDayDto> getOrderDay0913Dto(Map<String, String> queryMap) {
        return ordersReadDao.getOrderDay0913Dto(queryMap);
    }

    /**
     * 统计每天收款数据  
     * @param queryMap
     * @return
     */
    public List<OrderFinanceDayDto> getOrderFinanceDayDto(Map<String, String> queryMap) {
        return ordersReadDao.getOrderFinanceDayDto(queryMap);
    }

    public boolean jobSystemFinishOrder() {

        // 获取当前时间15天之前的时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -15);

        String deliverTime = TimeUtil.getDateTimeString(calendar.getTime());

        //获取发货时间超过15天的订单
        List<Orders> ordersList = ordersReadDao.getUnfinishedOrders(deliverTime);

        if (ordersList != null && ordersList.size() > 0) {
            // 单条数据处理异常不影响其他数据执行
            for (Orders orders : ordersList) {
                // 事务管理
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                TransactionStatus status = transactionManager.getTransaction(def);
                try {
                    Orders orderNew = new Orders();
                    orderNew.setId(orders.getId());
                    orderNew.setOrderState(Orders.ORDER_STATE_5);
                    orderNew.setFinishTime(new Date());

                    Integer update = ordersWriteDao.update(orderNew);
                    if (update == 0) {
                        throw new BusinessException("系统自动完成订单时失败。");
                    }

                    OrderLog log = new OrderLog(0, "system", orders.getId(), orders.getOrderSn(),
                        "系统自动完成订单。", new Date());

                    int orderlogCount = orderLogWriteDao.save(log);
                    if (orderlogCount == 0) {
                        throw new BusinessException("系统自动完成订单，订单日志保存失败，请重试！");
                    }

                    transactionManager.commit(status);
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    log.error("[OrderModel][jobSystemFinishOrder]系统自动完成订单时发生异常:", e);
                    log.error(
                        "[OrderModel][jobSystemFinishOrder]发生异常的订单：" + JSON.toJSONString(orders));
                }
            }
        }

        return true;
    }

    /**
     * 系统自动取消24小时没有付款订单
     * @return
     */
    public boolean jobSystemCancelOrder() {

        // 获取当前时间1天之前的时间
        Calendar calendar = Calendar.getInstance();
        //        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.add(Calendar.HOUR_OF_DAY, -3);

        String cancelTime = TimeUtil.getDateTimeString(calendar.getTime());
        // 获取下单24小时还未付款的订单
        List<Orders> ordersList = ordersReadDao.getUnPaiedOrders(cancelTime);

        if (ordersList != null && ordersList.size() > 0) {
            // 单条数据处理异常不影响其他数据执行
            for (Orders orders : ordersList) {
                if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_9
                    || orders.getOrderType().intValue() == Orders.ORDER_TYPE_10) {
                    // 竞价定金订单和竞价尾款订单都不自动取消
                    continue;
                }
                //Terry 20160825 start
                else if (orders.getOrderState().equals(Orders.ORDER_STATE_6)) {
                    log.error("[OrderModel][jobSystemCancelOrder]已取消订单，不能再次取消！订单号:"
                              + orders.getOrderSn());
                    continue;
                }
                //Terry 20160825 end
                //add by Ls 2016/08/11
                //判断是否为二次加工订单，是二次加工订单延长三小时
                /*Date create_time = orders.getCreateTime();
                Boolean twice_service = this.compareTwice(create_time);
                if (twice_service == false && orders.getServicePrice().compareTo(BigDecimal.ZERO) > 0) {
                    continue;
                }*/
                // 事务管理
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                TransactionStatus status = transactionManager.getTransaction(def);
                try {
                    Orders orderNew = new Orders();
                    orderNew.setId(orders.getId());
                    orderNew.setOrderState(Orders.ORDER_STATE_6);

                    Integer update = ordersWriteDao.update(orderNew);
                    if (update == 0) {
                        throw new BusinessException("系统自动取消订单时失败。");
                    }

                    OrderLog log = new OrderLog(0, "system", orders.getId(), orders.getOrderSn(), "系统自动取消订单。", new Date());

                    int orderlogCount = orderLogWriteDao.save(log);
                    if (orderlogCount == 0) {
                        throw new BusinessException("系统自动取消订单，订单日志保存失败，请重试！");
                    }

                    // 返还积分
                    this.cancelOrderBackIntegral(orders);
                    // 普通订单需要还原销量，限时抢购、团购订单、集合竞价的销量在还原库存时一起还原（因为增加销量和减库存是同时进行的）
                    //                    if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_1) {
                    // 还原销量
                    this.restoreActualSales(ordersProductReadDao.getByOrderId(orders.getId()));
                    //                    }
                    // 返回优惠券
                    this.cancelOrderBackCoupon(orders, 0, "system");

                    // 退回付款金额
                    this.cancelOrderBackMoney(orders, 0, "system");

                    // 返回优惠券
//                    this.cancelOrderBackCoupon(orders, 0, "system");
//
//                    // 退回付款金额
//                    this.cancelOrderBackMoney(orders, 0, "system");

                    //返还库存 add by Ls 2016/08/09
                    this.updateProductStock(orders.getId(), true);
                    transactionManager.commit(status);
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    log.error("[OrderModel][jobSystemCancelOrder]系统自动取消订单时发生异常:", e);
                    log.error(
                        "[OrderModel][jobSystemCancelOrder]发生异常的订单：" + JSON.toJSONString(orders));
                }
            }
        }

        return true;
    }

    //2016-08-11 10:42:33
    private Boolean compareTwice(Date create_time) {
        Boolean tFlag = false;
        Date d_now = new Date();
        SimpleDateFormat sdf_c = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String c = sdf_c.format(create_time);
        String n = sdf_c.format(d_now);
        Integer c_date = Integer.valueOf(c.substring(11, 13));
        Integer n_date = Integer.valueOf(n.substring(11, 13));
        if ((c_date + 3) <= n_date) {
            tFlag = true;
        }
        return tFlag;
    }

    /**
     * 根据关联订单 查询订单信息
     * @param relationOrderSn
     * @param request
     * @return
     */
    public OrderSuccessVO getOrdersByRelationOrderSn(String relationOrderSn, Integer memberId) {
        OrderSuccessVO orderSuccVO = new OrderSuccessVO();
        //参数校验
        if (StringUtil.isEmpty(relationOrderSn)) {
            log.error("关联订单编号为空。");
            throw new BusinessException("关联订单编号为空，请重试！");
        }

        List<Orders> ordersList = new ArrayList<Orders>();
        // 订单总价之和
        BigDecimal totalPrice = BigDecimal.ZERO;
        // 积分使用和
        BigDecimal totalIntegral = BigDecimal.ZERO;
        // 余额使用和
        BigDecimal totalBalance = BigDecimal.ZERO;
        // 现金使用和
        BigDecimal totalReality = BigDecimal.ZERO;
        //小田天   由原来的根据关联的订单号拆分订单去组装OrderSuccessVO改成由现在的根据tradeNo相同的去查询订单 组装OrderSuccessVO
        //String[] orderSns = relationOrderSn.split(",");
        //if (orderSns.length > 0) {
        //for (String orderSn : orderSns) {
        List<Orders> newOrdersList = ordersReadDao.getOrdersByTradeNo(relationOrderSn);
        for (Orders order : newOrdersList) {
            //Orders order = ordersWriteDao.getByOrderSn(orderSn);
            if (order != null) {
                if (!order.getMemberId().equals(memberId)) {
                    throw new BusinessException("该订单不是您的订单，您不能操作，谢谢！");
                }
                ordersList.add(order);
                totalPrice = totalPrice.add(order.getMoneyOrder());//计算总金额
                totalIntegral = totalIntegral.add(order.getMoneyIntegral());
                totalBalance = totalBalance.add(order.getMoneyPaidBalance());
                totalReality = totalReality.add(order.getMoneyPaidReality());

                totalBalance = totalBalance.add(order.getMoneyPaidBalance());
                totalReality = totalReality.add(order.getMoneyPaidReality());

                List<Product> prolist = ordersReadDao.getProductByOrderId(order.getId());
                orderSuccVO.setProductName(
                    prolist.size() > 1 ? prolist.get(0).getName1() + "等" + prolist.size() + "个商品"
                        : prolist.get(0).getName1());
            }
            // }
        }

        BigDecimal payMount = totalPrice.subtract(totalIntegral).subtract(totalBalance)
            .subtract(totalReality);
        payMount = payMount.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : payMount;
        orderSuccVO.setPayAmount(payMount);
        orderSuccVO.setOrdersList(ordersList);
        orderSuccVO.setRelationOrderSn(relationOrderSn);

        return orderSuccVO;
    }

    /**
     * 订单支付前处理<br>
     * <li>支付状态校验等
     * <li>校验库存
     * @param orderId
     * @param orderSn
     */
    public void payBefore(List<Orders> ordersList) {

        for (Orders orders : ordersList) {
            // 校验
            //只有订单状态为 1（未付款的订单）   买家付款状态 payment_status 为未付款，才能支付
            Integer orderState = orders.getOrderState();//订单状态
            Integer paymentStatus = orders.getPaymentStatus();//买家支付状态
            String paymentCode = orders.getPaymentCode();//支付类型

            if (orderState.intValue() != Orders.ORDER_STATE_1) {
                log.error("订单" + orders.getOrderSn() + "不是未付款状态，请选择未支付的订单！");
                throw new BusinessException("订单" + orders.getOrderSn() + "不是未付款状态，请选择未支付的订单！");
            }

            if (paymentStatus.intValue() == Orders.PAYMENT_STATUS_2) {
                log.error("订单" + orders.getOrderSn() + "是二次加工订单，需要客服与您确认后进行支付！");
                throw new BusinessException("订单" + orders.getOrderSn() + "是二次加工订单，需要客服与您确认后进行支付！");
            } else if (paymentStatus.intValue() == Orders.PAYMENT_STATUS_3) {
                log.error("订单" + orders.getOrderSn() + "是改价订单，需要客服与您确认后进行支付！");
                throw new BusinessException("订单" + orders.getOrderSn() + "是改价订单，需要客服与您确认后进行支付！");
            } else if (paymentStatus.intValue() != Orders.PAYMENT_STATUS_0) {
                log.error("订单" + orders.getOrderSn() + "已经支付过了，请选择未支付的订单！");
                throw new BusinessException("订单" + orders.getOrderSn() + "已经支付过了，请选择未支付的订单！");
            }

            if (!paymentCode.equals(ConstantsEJS.PAYMENT_CODE_ONLINE)
                && !paymentCode.equals("BALANCE")) {
                log.error("订单" + orders.getOrderSn() + "不是在线支付订单，不能进行在线支付！");
                throw new BusinessException("订单" + orders.getOrderSn() + "不是在线支付订单，不能进行在线支付！");
            }

            // 支付前校验库存，从写库读网单防止由于网络原因导致的延迟
            List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(orders.getId());

            // 限时抢购订单校验活动商品的库存
            if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_2) {
                // 限时抢购订单校验活动商品的库存

                // 限时抢购只有一个网单，校验库存从写库读取数据
                ActFlashSaleProduct actFlashSaleProduct = actFlashSaleProductReadDao
                    .get(opList.get(0).getActFlashSaleProductId());
                // 校验阶段是否已经过了时间点
                ActFlashSaleStage actFlashSaleStage = actFlashSaleStageReadDao
                    .get(actFlashSaleProduct.getActFlashSaleStageId());
                // 获取当前时间点
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (hour >= actFlashSaleStage.getEndTime()) {
                    throw new BusinessException("对不起，您参加的秒杀活动已结束，不能支付了！");
                }
                if (actFlashSaleProduct.getStock() < opList.get(0).getNumber()) {
                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                }
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_8) {
                // 团购订单校验活动商品的库存

                // 团购只有一个网单，校验库存从写库读取数据
                ActGroup actGroup = actGroupReadDao.get(opList.get(0).getActGroupId());
                // 获取当前时间
                Date date = new Date();
                if (date.after(actGroup.getEndTime())) {
                    throw new BusinessException("对不起，您参加的团购活动已结束，不能支付了！");
                }
                if (actGroup.getStock() < opList.get(0).getNumber()) {
                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                }
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_9) {
                // 竞价定金订单校验活动商品的库存

                // 竞价定金订单只有一个网单，校验库存从写库读取数据
                ActBidding actBidding = actBiddingReadDao.get(opList.get(0).getActBiddingId());
                // 获取当前时间
                Date date = new Date();
                if (date.after(actBidding.getFirstEndTime())) {
                    log.error("订单" + orders.getOrderSn() + "是定金订单，定金付款时间已结束！");
                    throw new BusinessException(
                        "订单" + orders.getOrderSn() + "是定金订单，定金付款时间已结束不能再支付！");
                }
                if (actBidding.getStock() < opList.get(0).getNumber()) {
                    throw new BusinessException("商品【" + opList.get(0).getProductName() + "】库存不足！");
                }
            } else if (orders.getOrderType().intValue() == Orders.ORDER_TYPE_10) {
                // 竞价尾款订单校验活动商品的库存

                // 竞价尾款订单只有一个网单
                ActBidding actBidding = actBiddingReadDao.get(opList.get(0).getActBiddingId());
                // 获取当前时间
                Date date = new Date();
                if (date.before(actBidding.getFirstEndTime())) {
                    log.error("订单" + orders.getOrderSn() + "是尾款订单，付款时间还没有开始！");
                    throw new BusinessException("订单" + orders.getOrderSn() + "是尾款订单，付款时间还没有开始！");
                }
                if (date.after(actBidding.getLastEndTime())) {
                    log.error("订单" + orders.getOrderSn() + "是尾款订单，付款时间已结束！");
                    throw new BusinessException("订单" + orders.getOrderSn() + "是尾款订单，付款时间已结束不能再支付！");
                }
                // 尾款订单不扣减库存，所以不校验库存量
            } else {
                // 普通订单校验每个网单的库存
                //                for (OrdersProduct op : opList) {
                //                    ProductGoods productGoods = productGoodsWriteDao.get(op.getProductGoodsId());
                //                    if (productGoods.getProductStock() < op.getNumber()) {
                //                        throw new BusinessException("商品【" + opList.get(0).getProductName() + " "
                //                                                    + opList.get(0).getSpecInfo() + "】库存不足！");
                //                    }
                //                }
            }
        }

    }

    /**
     * 订单支付后处理<br>
     * <li>修改订单支付状态
     * <li>记录订单日志
     * <li>记录订单支付日志
     * @param trade_no 
     * 
     * @param ordersList
     * @param paidMoney 现金支付的金额
     * @param member
     * @param tradeSn 支付流水号
     * @param tradeContent 支付返回信息
     * @param paymentCode 支付方式code
     * @param paymentName 支付方式名称
     * @param isPaidBefore 是否是支付前调用（余额支付所有订单时会调用）
     */
    public void payAfter(String trade_no, List<Orders> ordersList, BigDecimal paidMoney,
                         Member member, String tradeSn, String tradeContent, String paymentCode, String paymentName, boolean isPaidBefore) {
        //********************************************************************************************
        for (int i = 0; i < ordersList.size(); i++) {
            Orders ordersDb = ordersList.get(i);
            // 修改订单支付状态
            Orders orders = new Orders();
            orders.setId(ordersDb.getId());
            if (!StringUtil.isEmpty(trade_no))
                orders.setTradeNo(trade_no);

            // 计算余额账户支付总金额、现金支付金额
            // 订单应该支付的金额：订单金额-积分换算金额-余额支付金额-现金支付金额
            BigDecimal payMoney = ordersDb.getMoneyOrder().subtract(ordersDb.getMoneyIntegral()).subtract(ordersDb.getMoneyPaidBalance()).subtract(ordersDb.getMoneyPaidReality());
            
            if (CalculateUtil.isInActiveTime() && paymentCode.equals("bestpay")) {
                payMoney = payMoney.multiply(Orders.BESTPAY_DISCOUNT).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            // 订单实际分配的支付金额
            BigDecimal actualPaid = BigDecimal.ZERO;
            if (payMoney.compareTo(BigDecimal.ZERO) <= 0) {
                // 如果应付金额小于等于0
                // 不计算金额
            } else {
                // 订单循环分配现金支付的金额
                if ((i + 1) == ordersList.size()) {
                    // 如果是最后一个订单，则记录剩下的金额
                    orders.setMoneyPaidReality(ordersDb.getMoneyPaidReality().add(paidMoney));
                    actualPaid = paidMoney;
                } 
                else {
                    if (paidMoney.compareTo(BigDecimal.ZERO) > 0) {
                        if (paidMoney.compareTo(payMoney) >= 0) {
                            // 支付的金额大于等于订单待支付金额
                            orders.setMoneyPaidReality(ordersDb.getMoneyPaidReality().add(payMoney));
                            actualPaid = payMoney;
                        } else {
                            orders.setMoneyPaidReality(ordersDb.getMoneyPaidReality().add(paidMoney));
                            actualPaid = paidMoney;
                        }
                    }
                }
                // 现金支付金额减少
                paidMoney = paidMoney.subtract(actualPaid);
            }

            // 修改金额后应支付金额，如果该金额小于等于0，则支付完成，修改状态，记录支付日志，占库存
            // DB订单金额-DB积分支付金额-DB余额支付金额-NEW现金支付金额
            BigDecimal sholdOrderMoney = ordersDb.getMoneyOrder();
            if (CalculateUtil.isInActiveTime() && paymentCode.equals("bestpay")) {
                sholdOrderMoney = sholdOrderMoney.multiply(Orders.BESTPAY_DISCOUNT).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            
            BigDecimal needPay = sholdOrderMoney.subtract(ordersDb.getMoneyIntegral()).subtract(ordersDb.getMoneyPaidBalance()).subtract(ordersDb.getMoneyPaidReality().add(actualPaid));

            if (needPay.compareTo(BigDecimal.ZERO) <= 0) {
                // 支付完成
                if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_9) {
                    // 如果是竞价定金订单，支付完成即设置订单状态为已完成（因为不需要发货）
                    orders.setOrderState(Orders.ORDER_STATE_5);
                } else {
                    //所有付款订单均需通过财务确认,包括已付款订单
                    //Terry 20160918 mark
                    //                    if (ordersDb.getPayTime() == null || "".equals(ordersDb.getPayTime()))
                    //                        orders.setOrderState(Orders.ORDER_STATE_2);
                    //Terry 20161014 
                    //没有进行改价，不是二次加工，余额支付、线下打款、已付款订单不需要财务再次确认，直接进入待发或订单
                    if (ordersDb.getPayTime() == null || "".equals(ordersDb.getPayTime())) {
                        if (ordersDb.getOrderType() == Orders.ORDER_TYPE_5 || paymentCode == Orders.PAYMENT_CODE_BALANCE || ordersDb.getPaymentCode() == Orders.PAYMENT_CODE_OFFLINE
                            || ordersDb.getPaymentCode() == Orders.PAYMENT_CODE_OFFSEND || !StringUtils.isNumeric(ordersDb.getOrderSn())) {
                            orders.setOrderState(Orders.ORDER_STATE_2);
                        } else {
                            orders.setOrderState(Orders.ORDER_STATE_3);
                        }
                    }
                    //Terry 20161014 end
                }
                if (ordersDb.getPayTime() == null || "".equals(ordersDb.getPayTime()))
                    orders.setPayTime(new Date()); //Terry mark
                orders.setSyncState(ordersDb.getSyncState());
                orders.setPaymentStatus(Orders.PAYMENT_STATUS_1);
                orders.setPaymentCode(paymentCode);
                orders.setPaymentName(paymentName);
                orders.setTradeSn(tradeSn);

                Integer update = ordersWriteDao.update(orders);
                if (update == 0) {
                    throw new BusinessException("修改订单状态时失败！");
                }

                // 记录订单日志
                OrderLog orderLog = new OrderLog(member.getId(), member.getName(), ordersDb.getId(), ordersDb.getOrderSn(), "订单完成支付。", new Date());
                Integer save = orderLogWriteDao.save(orderLog);
                if (save == 0) {
                    throw new BusinessException("记录订单日志时失败！");
                }

                // 占库存
                if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_2) {
                    // 限时抢购订单的情况占限时抢购活动商品的库存
                    List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersDb.getId());
                    // 限时抢购只有一个网单
                    this.updateFlashProductStockAndActualSales(opList.get(0).getActFlashSaleProductId(), opList.get(0).getNumber());
                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_8) {
                    // 团购订单的情况占团购活动的库存
                    List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersDb.getId());
                    // 团购只有一个网单
                    this.updateGroupStockAndActualSales(opList.get(0).getActGroupId(), opList.get(0).getNumber());
                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_9) {
                    // 竞价定金订单的情况占竞价活动的库存
                    List<OrdersProduct> opList = ordersProductReadDao.getByOrderId(ordersDb.getId());
                    // 竞价定金订单只有一个网单
                    this.updateBiddingStockAndActualSales(opList.get(0).getActBiddingId(), opList.get(0).getNumber());
                } else if (ordersDb.getOrderType().intValue() == Orders.ORDER_TYPE_10) {
                    // 竞价尾款订单的情况不占库存，因为库存在定金订单支付时已占
                    // 此处使逻辑清晰代码易读增加此判断
                } else {
                	
                	/**
                	 * 由于在下单的时候已经减了库存 并且加了销量 因此 在支付成功之后就不在增加销量和减库存了 2016.12.30 小甜甜
                	 */
                	
                	//List<OrdersProduct> opList = ordersProductWriteDao
                     //       .getByOrderId(ordersDb.getId());
                	//for (OrdersProduct ordersProduct : opList) {
                		//TODO
                		//普通订单减库存 加销量
                		//this.updateProductActualSales(ordersProduct.getProductId(), ordersProduct.getId(), ordersProduct.getNumber());
                		/*this.updateProductStockForPayAfter(ordersProduct.getProductId(),ordersProduct.getId(),ordersProduct.getNumber());
                		productGoodsWriteDao.updateActualSales(ordersProduct.getProductGoodsId(), ordersProduct.getNumber());
                		productWriteDao.updateActualSales(ordersProduct.getProductId(),  ordersProduct.getNumber());
                		*/
                	//	}
                }

                // orderPayBefore中调用时不记录日志，因为在扣除余额时已经记录过了
                // 只有在payAfter中调用时才记录此日志
                if (!isPaidBefore) {
                    // 记录订单支付日志
                    OrderPayLog payLog = new OrderPayLog();
                    payLog.setOrdersId(ordersDb.getId());
                    payLog.setOrdersSn(ordersDb.getOrderSn());
                    payLog.setPayStatus(paymentCode);
                    payLog.setPayContent(tradeContent);
                    payLog.setPayMoney(actualPaid);
                    payLog.setMemberId(member.getId());
                    payLog.setMemberName(member.getName());
                    payLog.setCreateTime(new Date());
                    orderPayLogWriteDao.save(payLog);
                }

                // 购物送积分，出错时吞掉异常不影响支付
                try {
                    memberModel.memberOrderSendValueNoTrans(member.getId(), member.getName(), ordersDb.getId());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            } else {
                // 订单支付的总金额不够订单金额，表示订单未支付完成，商城现有逻辑正常不会进入此逻辑
                // 记录部分支付信息
                orders.setTradeSn(tradeSn);
                Integer update = ordersWriteDao.update(orders);
                if (update == 0) {
                    throw new BusinessException("修改订单时失败！");
                }

                // 记录订单日志
                OrderLog orderLog = new OrderLog(member.getId(), member.getName(), ordersDb.getId(), ordersDb.getOrderSn(), "订单部分支付。", new Date());
                Integer save = orderLogWriteDao.save(orderLog);
                if (save == 0) {
                    throw new BusinessException("记录订单日志时失败！");
                }

                // orderPayBefore中调用时不记录日志，因为在扣除余额时已经记录过了
                // 只有在payAfter中调用时才记录此日志
                if (!isPaidBefore) {
                    // 记录订单支付日志
                    OrderPayLog payLog = new OrderPayLog();
                    payLog.setOrdersId(ordersDb.getId());
                    payLog.setOrdersSn(ordersDb.getOrderSn());
                    payLog.setPayStatus(paymentCode);
                    payLog.setPayContent(tradeContent);
                    payLog.setPayMoney(actualPaid);
                    payLog.setMemberId(member.getId());
                    payLog.setMemberName(member.getName());
                    payLog.setCreateTime(new Date());
                    orderPayLogWriteDao.save(payLog);
                }
            }
        }
    }

    public void sendMessageToSeller(Orders orders) {
        Integer sellerId = orders.getSellerId();
        boolean sendFlag = false;
        //发给猫头鹰和麦琪进行客户试用
        if (SyncWayUtil.SEND_MESSAGE_SELLER) {//(sellerId == 1 || sellerId == 2) {
            String mobile = "";
            Member member = memberReadDao.getMemberBySellerId(sellerId);
            if (member.getMobile()!=null && !"".equals(member.getMobile())) {
                mobile = member.getMobile();
                sendFlag = true;
            }
            else {
                mobile = member.getName();
                Pattern p = Pattern.compile(Member.MOBILE_VALIDATA);
                Matcher m = p.matcher(mobile);
                boolean flag = m.matches();
                if(!flag){
                    log.error("########电话号码为空，短信发送失败，失败供应商ID:" + sellerId);
                    sendFlag = false;
                }
                else
                    sendFlag = true;
            }
            if (sendFlag) {
                Map<String, Object> smsparam = new HashMap<String, Object>();
                smsparam.put("mobile", mobile);
                MobileMessage mMessage = new MobileMessage();
                StringBuffer sb = mMessage.getMessageToSeller(orders.getMoneyProduct());
//              new StringBuffer("【大袜网】亲,您有宝贝下单,金额为:").append(orders.getMoneyProduct()).append(",商品号:");
                List<OrdersProduct> opList = orders.getOrderProductList();
                for (OrdersProduct op : opList) {
                    if ((sb.length() + op.getProductSku().length()) >= 70) {
                        if (sb.lastIndexOf(",") == (sb.length() - 1)) {
                            sb.delete(sb.length() - 1, sb.length());
                        }
                        sb.append("等");
                        break;
                    } else {
                        sb.append(op.getProductSku()).append(",");
                    }
                }
                if (sb.lastIndexOf(",") == sb.length() - 1) {
        			sb.delete(sb.length() - 1, sb.length());
        		}
                smsparam.put("content", sb);
                senderService.sendSMSWidthCallable(smsparam);
            }
        }
    }

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param relationOrderSn 订单号以逗号隔开
     * @param isBalancePay 是否用余额支付
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    public OrderSuccessVO orderPayBefore(String relationOrderSn, boolean isBalancePay, String balancePassword, Member member) {
        OrderSuccessVO orderSuccessVO;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //参数校验
            if (StringUtil.isEmpty(relationOrderSn, true)) {
                log.error("订单号为空。");
                throw new BusinessException("订单号不能为空，请重试！");
            }

            //获取订单
            orderSuccessVO = this.getOrdersByRelationOrderSn(relationOrderSn, member.getId());

            List<Orders> ordersList = orderSuccessVO.getOrdersList();

            if (ordersList == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请重试！");
            }
            // 支付前验证操作
            this.payBefore(ordersList);

            //计算订单总金额
            BigDecimal orderMoneyAlls = orderMoneyAlls(ordersList);
            orderSuccessVO.setPayOrderAllsVO(orderMoneyAlls);
            Member memberNew = memberReadDao.get(member.getId());                  //Terry 
            if (orderMoneyAlls.compareTo(BigDecimal.ZERO) <= 0) {
                // 如果支付金额为0，则直接更改订单状态（主要针对使用余额后支付中断的情况）
                this.payAfter(null, ordersList, BigDecimal.ZERO, memberNew, "", "", Orders.PAYMENT_CODE_BALANCE, "余额支付", true);
                orderSuccessVO.setBanlancePayMoneyVO(orderMoneyAlls);
                orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_2);
            } else {
                if (isBalancePay) {//余额支付
                    if (!memberNew.getBalancePwd().equals(Md5.getMd5String(balancePassword))) {
                        throw new BusinessException("支付密码错误，请重新输入");
                    }
                    // 使用余额，先扣除余额，防止用户并发支付时余额被多次使用
                    // 用户的账户余额
                    BigDecimal balance = memberNew.getBalance();
                    // 订单使用的余额总计（用于修改用户账户余额）
                    BigDecimal paidMoney = BigDecimal.ZERO;

                    MemberCredit mc = memberCreditReadDao.getByMemberId(memberNew.getId());
                    if (mc == null)
                        mc = new MemberCredit();
                    BigDecimal surplus = mc.getSurplus();

                    for (Orders orders : ordersList) {
                        // 如果余额大于0，继续扣减
                        if (balance.compareTo(BigDecimal.ZERO) > 0) {
                            // 订单应支付金额
                            BigDecimal needPay = orders.getMoneyOrder().subtract(orders.getMoneyIntegral()).subtract(orders.getMoneyPaidBalance()).subtract(orders.getMoneyPaidReality());
                            needPay = needPay.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : needPay;
                            // 本次余额实际能支付的金额
                            BigDecimal actualPaid = BigDecimal.ZERO;

                            // 修改订单余额支付金额
                            Orders newOrder = new Orders();
                            newOrder.setId(orders.getId());
                            if (balance.compareTo(needPay) >= 0) {
                                // 如果余额大于等于应付金额
                                actualPaid = needPay;
                            } else {
                                // 如果余额小于应付金额
                                actualPaid = balance;
                            }
                            // 余额支付金额累加
                            newOrder.setMoneyPaidBalance(orders.getMoneyPaidBalance().add(actualPaid));
                            // 把新的余额支付金额赋值给list中的订单对象，因为有可能调用payAfter方法，需要使用该值
                            orders.setMoneyPaidBalance(orders.getMoneyPaidBalance().add(actualPaid));
                            // 余额累减
                            balance = balance.subtract(actualPaid);
                            // 已支付金额累加
                            paidMoney = paidMoney.add(actualPaid);

                            // 实际支付金额大于0才记录日志及修改订单
                            if (actualPaid.compareTo(BigDecimal.ZERO) > 0) {
                                Integer update = ordersWriteDao.update(newOrder);
                                if (update == 0) {
                                    throw new BusinessException("修改订单余额支付金额时失败！");
                                }

                                // 记录余额日志
                                MemberBalanceLogs logs = new MemberBalanceLogs();
                                logs.setMemberId(member.getId());
                                logs.setMemberName(member.getName());
                                logs.setMoneyBefore(balance.add(actualPaid));
                                logs.setMoneyAfter(balance);
                                logs.setMoney(actualPaid);
                                logs.setCreateTime(new Date());
                                logs.setState(MemberBalanceLogs.STATE_3);
                                logs.setRemark("消费，订单号" + orders.getOrderSn());
                                logs.setOptId(member.getId());
                                logs.setOptName(member.getName());
                                Integer save = memberBalanceLogsWriteDao.save(logs);
                                if (save == 0) {
                                    throw new BusinessException("记录余额日志时失败！");
                                }

                                // 记录订单支付日志
                                OrderPayLog payLog = new OrderPayLog();
                                payLog.setOrdersId(orders.getId());
                                payLog.setOrdersSn(orders.getOrderSn());
                                payLog.setPayStatus(Orders.PAYMENT_CODE_BALANCE);
                                payLog.setPayContent("");
                                payLog.setPayMoney(actualPaid);
                                payLog.setMemberId(member.getId());
                                payLog.setMemberName(member.getName());
                                payLog.setCreateTime(new Date());
                                save = orderPayLogWriteDao.save(payLog);
                                if (save == 0) {
                                    throw new BusinessException("记录订单支付日志时失败！");
                                }
                            }
                        } else {
                            // 订单应支付金额
                            BigDecimal money = orders.getMoneyOrder().subtract(orders.getMoneyIntegral()).subtract(orders.getMoneyPaidBalance()).subtract(orders.getMoneyPaidReality());
                            money = money.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : money;

                            //赊账信息
                            //用户余额小于等于应付金额，则使用赊账

                            //如果订单金额大于赊账额度，抛出异常
                            if (money.compareTo(mc.getSurplus()) > 0) {
                                throw new BusinessException("您的余额已不足以支付此笔订单，请先还清欠款");
                            }
                            //用户余额不足支付订单且未结清时，已赊账额度不能大于总额度
                            if (memberNew.getBalance().compareTo(money) < 0 && mc.getHasSettled().intValue() == 0) {
                                //未结清欠款，则余额+本次消费不能超过赊账总额度
                                BigDecimal userCredit = memberNew.getBalance().multiply(new BigDecimal(-1));
                                if (userCredit.add(money).compareTo(mc.getQuota()) > 0) {
                                    throw new BusinessException("您账户的赊账总额度为" + mc.getQuota() + "元，您当前已欠款" + userCredit + "元，本次透支额度超过最大赊账额度，请还清欠款或充值后重新下单。");
                                }
                            }
                            BigDecimal creditBefore = mc.getSurplus();

                            // 余额支付金额累加
                            orders.setMoneyPaidBalance(orders.getMoneyPaidBalance().add(money));

                            // 余额累减
                            balance = balance.subtract(money);
                            // 已支付金额累加
                            paidMoney = paidMoney.add(money);

                            // 记录余额日志
                            MemberBalanceLogs logs = new MemberBalanceLogs();
                            logs.setMemberId(member.getId());
                            logs.setMemberName(member.getName());
                            logs.setMoneyBefore(balance.add(money));
                            logs.setMoneyAfter(balance);
                            logs.setMoney(money);
                            logs.setCreateTime(new Date());
                            logs.setState(MemberBalanceLogs.STATE_3);
                            logs.setRemark("消费，订单号" + orders.getOrderSn());
                            logs.setOptId(member.getId());
                            logs.setOptName(member.getName());
                            Integer save = memberBalanceLogsWriteDao.save(logs);
                            if (save == 0) {
                                throw new BusinessException("记录余额日志时失败！");
                            }

                            // 记录订单支付日志
                            OrderPayLog payLog = new OrderPayLog();
                            payLog.setOrdersId(orders.getId());
                            payLog.setOrdersSn(orders.getOrderSn());
                            payLog.setPayStatus(Orders.PAYMENT_CODE_BALANCE);
                            payLog.setPayContent("");
                            payLog.setPayMoney(money);
                            payLog.setMemberId(member.getId());
                            payLog.setMemberName(member.getName());
                            payLog.setCreateTime(new Date());
                            save = orderPayLogWriteDao.save(payLog);
                            if (save == 0) {
                                throw new BusinessException("记录订单支付日志时失败！");
                            }

                            BigDecimal curCreditPay = money;
                            //如果订单金额大于剩余额度
                            if (money.compareTo(mc.getSurplus()) > 0) {
                                curCreditPay = mc.getSurplus();
                            }
                            //已使用
                            mc.setUsed(mc.getUsed().add(curCreditPay));
                            //剩余额度
                            mc.setSurplus(mc.getSurplus().subtract(curCreditPay));
                            memberCreditWriteDao.update(mc);

                            //赊账日志 Terry
                            MemberCreditLog mclog = new MemberCreditLog(mc.getId(), memberNew.getId(), orders.getOrderSn(), money, orders.getPayTime(), 
                            		orders.getPaymentStatus(), memberNew.getBalance(), memberNew.getBalance().subtract(money), curCreditPay, creditBefore, 
                            		mc.getSurplus(), memberNew.getId(), memberNew.getName(), new Date(), memberNew.getName());
                            memberNew.setBalance(memberNew.getBalance().subtract(money));       //Terry 20170218 当有多笔订单使用余额支付时，支付前余额需要进行将之前一笔剪掉
                            memberCreditLogWriteDao.save(mclog);
                        }
                    }
                    //更改余额
                    Member memberBalance = new Member();
                    memberBalance.setId(member.getId());
                    memberBalance.setBalance(paidMoney.multiply(new BigDecimal(-1)));
                    Integer updateBalance = memberWriteDao.updateBalance(memberBalance);
                    if (updateBalance == 0) {
                        throw new BusinessException("修改会员余额金额时失败！");
                    }
                    //余额够支付
                    if (orderMoneyAlls.compareTo(memberNew.getBalance()) <= 0) {
                        memberBalance.setId(memberNew.getId());
                        memberBalance.setBalance(orderMoneyAlls.multiply(new BigDecimal(-1)));

                        orderSuccessVO.setBanlancePayMoneyVO(orderMoneyAlls);
                        memberWriteDao.updateBalance(memberBalance);//更改余额
                        orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_2);

                        // 更改订单状态
                        this.payAfter(null, ordersList, BigDecimal.ZERO, memberNew, "", "", Orders.PAYMENT_CODE_BALANCE, "余额支付", true);

                    } else if (orderMoneyAlls.compareTo(surplus) <= 0) {
                        //赊账
                        if (memberNew.getBalance().compareTo(BigDecimal.ZERO) <= 0
                            && mc.getHasSettled().intValue() == 0) {

                            //未结清欠款，则余额+本次消费不能超过赊账总额度
                            BigDecimal userCredit = memberNew.getBalance().multiply(new BigDecimal(-1));
                            if (userCredit.add(orderMoneyAlls).compareTo(mc.getQuota()) > 0) {
                                throw new BusinessException("您账户的赊账总额度为" + mc.getQuota() + "元，您当前已欠款" + userCredit + "元，本次透支额度超过最大赊账额度，请还清欠款或充值后重新下单。");
                            }
                        }

                        orderSuccessVO.setBanlancePayMoneyVO(orderMoneyAlls);
                        orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_2);

                        // 更改订单状态
                        this.payAfter(null, ordersList, BigDecimal.ZERO, memberNew, "", "", Orders.PAYMENT_CODE_BALANCE, "余额支付", true);
                    } else {//余额不够支付
                        orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_3);
                        orderSuccessVO.setBanlancePayMoneyVO(memberNew.getBalance());
                    }
                } else {
                    orderSuccessVO.setBanlancePayVO(OrderSuccessVO.BANLANCEPAYVO_1);
                }
            }

            transactionManager.commit(status);
            return orderSuccessVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][orderPayBefore]获取订单数据发生异常:", e);
            throw e;
        }
    }

    /**
     * 计算订单实际需要支付的金额
     * @param ordersList
     * @return
     */
    private BigDecimal orderMoneyAlls(List<Orders> ordersList) {
        BigDecimal orderMoneyAlls = new BigDecimal(0);
        for (Orders orders : ordersList) {
            // 订单应支付金额：money_order - money_integral - money_paid_balance - money_paid_reality
            // 如果订单勾选余额支付则先扣除余额，所以存在用户第一次支付未成功或者放弃支付时余额已经扣除，订单的余额支付字段已有值，所以应支付金额应减去余额支付的部分
            BigDecimal needPay = orders.getMoneyOrder().subtract(orders.getMoneyIntegral())
                .subtract(orders.getMoneyPaidBalance()).subtract(orders.getMoneyPaidReality());
            needPay = needPay.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : needPay;
            orderMoneyAlls = orderMoneyAlls.add(needPay);
        }
        return orderMoneyAlls;
    }

    /**
     * 支付成功之后更改订单的状态
     * @param trade_no 订单
     * @param total_fee 金额
     * @param paycode 支付方式
     * @param payname 支付方式
     * @param tradeSn 交易流水号
     * @param tradeContent 交易返回信息
     * @return
     */
    public Boolean orderPayAfter(String trade_no, String total_fee, String paycode, String payname, String tradeSn, String tradeContent) {
        // 事务管理  trade_no  1469601463363 aa1aa119  原始 ：16072714341641752
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        BigDecimal totalPrice = BigDecimal.ZERO;
        int memberID = 0;
        try {
            /*
            //Terry 20160920 commit
            String[] trade_nos = trade_no.split(EjavashopConfig.ORDER_SEPARATOR);
            for (String string : trade_nos) {
                System.out.println("*******" + string);
            }
            if (trade_nos.length < 3) {
                throw new BusinessException("订单号错误");
            }
            
            List<Orders> ordersList = new ArrayList<Orders>();
            for (int i = 2; i < trade_nos.length; i++) {
                int orderId = ConvertUtil.toInt(trade_nos[i], 0);
                if (orderId != 0) {
                    Orders orders = ordersWriteDao.get(orderId);
                    totalPrice = orders.getMoneyProduct();
                    if (orders != null) {
                        ordersList.add(orders);
                    }
                }
            }
            */

            //Terry 20160920 add 
            //根据订单号更新关联订单合并表，支付状态
            OrdersTradeSerial ots = new OrdersTradeSerial();
            ots.setTradeSn(trade_no);
            ots.setPaymentState(1);
            ordersTradeSerialWriteDao.update(ots);

            List<Orders> ordersList = ordersReadDao.getOrdersByTradeNo(trade_no);
            for (Orders order : ordersList) {
                totalPrice = totalPrice.add(order.getMoneyProduct());
            }
            memberID = ordersList.get(0).getMemberId();
            Member member = memberReadDao.get(memberID);

            //计算订单总金额

            // 更改订单状态、记录余额日志、支付日志

            //Terry
            // TODO 测试代码 正式环境中删除这段代码使用下面的正式代码  开始-------------
            //            BigDecimal orderMoneyAlls = orderMoneyAlls(ordersList);
            //            this.payAfter(ordersList, orderMoneyAlls, member, tradeSn, tradeContent, paycode,
            //                payname, false);
            // 测试代码  结束-------------

            // 正式代码  开始-------------
            this.payAfter(trade_no, ordersList, new BigDecimal(total_fee), member, tradeSn, tradeContent, paycode, payname, false);
            // 正式代码  结束-------------
            //end结束
            transactionManager.commit(status);
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[OrderService][orderPayAfter]订单确认收货时发生异常:", e);
            throw e;
        }
        //Terry 20160823
        /* 20161001起没有红包了
        try {
            String currentTime = OrdersModel.getCurrentTime();
            //begin 根据总商品金额 赠送红包【仝照美】444
            int result = OrdersModel.timeCompare("2016-09-30 23:00:00", currentTime);
            //判断当前时间小于2016-09-30 13:13:42  才可以进行红包赠送
            if (result == 1) {
                int[] oPrices = new int[] { 400, 800, 1600, 3200, 8000, 16000, 24000 };//用户商品总金额需要满足的条件
                int[] coupon_ids = new int[] { 17, 18, 19, 20, 21, 22, 23 };//红包ID
                //              List<Coupon>  coupons = couponReadDao.getCouponsByPrice(totalPrice);
        
                //Orders orders = orderReadDao.getOrderByOrderSn(trade_no);//根据订单号获取商家ID
                //System.out.print(coupons.size()+"========="+orders.getMemberId());
                int totalPrice_toint = totalPrice.intValue();//用户购买商品的有效金额
                int coupon_id = 0;
                for (int i = 0; i < oPrices.length; i++) {
                    if (totalPrice_toint >= oPrices[i]) {
                        coupon_id = coupon_ids[i];
                    }
                }
                if (coupon_id != 0) {
                    this.receiveCoupon(coupon_id, memberID);
                }
            }
        } catch (Exception e) {
            log.error("[OrderService][orderPayAfter]订单确认收货时发生异常:", e);
            throw e;
        }
        */
        return true;
    }

    // ------------------------限时抢购开始----------------------------------------------------------------------------------

    /**
     * 用户限时抢购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForFlash(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            throw new BusinessException("对不起，限时抢购活动不支持货到付款！");
        }

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID) || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        Calendar calendar = Calendar.getInstance();
        Integer currHour = calendar.get(Calendar.HOUR_OF_DAY);
        String actDate = TimeUtil.getToday() + " 00:00:00";
        // 取当天有效的活动
        ActFlashSale actFlashSale = actFlashSaleReadDao.getEffectiveActFlashSale(actDate, channel);
        // 当前阶段
        ActFlashSaleStage actFlashSaleStage = null;
        // 当前下单商品的活动
        ActFlashSaleProduct actFlashSaleProduct = null;
        // 取当前有效活动的时间段信息
        if (actFlashSale != null) {
            // 如果有活动取阶段
            actFlashSaleStage = actFlashSaleStageReadDao.getStageByTime(actFlashSale.getId(), currHour);
            if (actFlashSaleStage != null) {
                // 如果阶段不空，继续取活动商品，需要校验库存，所以从写库读取
                actFlashSaleProduct = actFlashSaleProductReadDao.getByStageIdAndProductId(actFlashSaleStage.getId(), orderCommitVO.getProductId());
                if (actFlashSaleProduct != null) {
                    // 如果商品不为空，则继续
                } else {
                    // 如果为空，直接抛异常
                    throw new BusinessException("该商品当前时间没有限时抢购活动！");
                }
            } else {
                // 如果阶段为空，直接抛异常
                throw new BusinessException("当前时间没有限时抢购活动！");
            }
        } else {
            // 如果没有活动，直接抛异常
            throw new BusinessException("当前时间没有限时抢购活动！");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        if (actFlashSale.getStatus().intValue() != ActFlashSale.STATUS_5) {
            throw new BusinessException("该秒杀活动未上架，不能下单！");
        }
        if (actFlashSaleProduct.getStatus().intValue() != ActFlashSaleProduct.STATUS_2) {
            throw new BusinessException("该商品未通过审核，不能下单！");
        }
        // 判断库存
        if (actFlashSaleProduct.getStock() <= 0) {
            throw new BusinessException("该商品已被抢光了，下次赶早哦！");
        }

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressReadDao.get(orderCommitVO.getAddressId());

            // 获取提交订单的用户
            Member member = memberReadDao.get(orderCommitVO.getMemberId());

            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(orderCommitVO.getSellerId(), orderCommitVO.getNumber(), address.getCityId(), orderCommitVO.getLogisticsId());
            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO : calculateTransFee;

            //如果使用了余额支付 ，判断支付密码
            if (orderCommitVO.getIsBalancePay() != null && orderCommitVO.getIsBalancePay()) {
                this.checkBalance(orderCommitVO, member, actFlashSaleProduct.getPrice().multiply(new BigDecimal(orderCommitVO.getNumber())));
            }

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActivity(seller, Orders.ORDER_TYPE_2, actFlashSaleProduct, null, null, calculateTransFee, member, orderCommitVO, address, integral, config);
            orderList.add(order);

            // 保存网单信息
            this.saveOrderProductInfoForActivity(order, product, productGoods, Orders.ORDER_TYPE_2, actFlashSaleProduct, null, null, member, seller, orderCommitVO.getNumber());

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode("余额支付");
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象 
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setRelationOrderSn(order.getOrderSn());
            orderSuccVO.setPayAmount(order.getMoneyOrder().subtract(order.getMoneyIntegral()));
            orderSuccVO.setIsBanlancePay(orderCommitVO.getIsBalancePay());
            orderSuccVO.setBalancePwd(orderCommitVO.getBalancePwd());
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 修改限时抢购活动商品的库存和销量，库存减saleNum，销量加saleNum
     * @param actFlashSaleProductId
     * @param saleNum 修改数量
     */
    private Integer updateFlashProductStockAndActualSales(Integer actFlashSaleProductId, Integer saleNum) {
        return actFlashSaleProductWriteDao.updateStockAndActualSales(actFlashSaleProductId, saleNum);
    }

    /**
     * 还原限时抢购活动商品的库存和销量，库存加saleNum，销量减saleNum
     * @param actFlashSaleProductId
     * @param saleNum 修改数量
     */
    private Integer backFlashProductStockAndActualSales(Integer actFlashSaleProductId, Integer saleNum) {
        return actFlashSaleProductWriteDao.backStockAndActualSales(actFlashSaleProductId, saleNum);
    }

    // ------------------------限时抢购结束----------------------------------------------------------------------------------

    // ------------------------活动公用方法开始-------------------------------------------------------------------------------

    /**
     * 使用积分校验
     * @param member
     * @param config 系统配置
     * @param integral 使用的积分数量
     */
    private void checkIntegral(Member member, Config config, Integer integral) {
        // 判断用户的积分是否够填入的积分数
        if (integral.compareTo(member.getIntegral()) > 0) {
            throw new BusinessException("积分不够了，请重新填写积分数量！");
        }
        // 至少消耗积分换算比例的积分数量
        if (config == null) {
            throw new BusinessException("积分转换金额失败，请联系系统管理员！");
        }
        if (integral.compareTo(config.getIntegralScale()) < 0) {
            throw new BusinessException("对不起，请至少使用" + config.getIntegralScale() + "个积分！");
        }
    }

    /**
     * 支付密码、余额量校验
     * @param orderCommitVO
     * @param member
     * @param payAmount 需要支付的金额
     */
    private void checkBalance(OrderCommitVO orderCommitVO, Member member, BigDecimal payAmount) {
        if (StringUtil.isEmpty(orderCommitVO.getBalancePwd())) {
            throw new BusinessException("请输入支付密码后重试！");
        }
        if (!Md5.getMd5String(orderCommitVO.getBalancePwd()).equals(member.getBalancePwd())) {
            throw new BusinessException("支付密码不正确，请重试！");
        }
        // 余额为零直接抛出
        if (member.getBalance() == null || member.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("您没有余额，不能使用余额支付！");
        }

        // 如果订单是货到付款，且余额不足以支付所有的金额则抛出，如果足够支付所有金额，则继续
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode()) && payAmount.compareTo(member.getBalance()) > 0) {
            throw new BusinessException("余额不足，请重新选择支付方式，或拆分订单后再下单！");
        }
    }

    /**
     * 保存商家的限时抢购、团购、集合竞价定金订单，以及订单日志
     * @param seller 商家信息
     * @param orderType 订单类型：Orders.ORDER_TYPE_2为限时抢购订单，Orders.ORDER_TYPE_3为团购订单，Orders.ORDER_TYPE_4为竞价定金订单
     * @param actFlashSaleProduct 抢购活动商品
     * @param actGroup 团购活动
     * @param actBidding 集合竞价活动
     * @param transFee 运费
     * @param member 会员
     * @param orderCommitVO 订单提交信息
     * @param address 地址
     * @param integral 积分
     * @param config 积分配置
     * @return
     */
    private Orders saveOrderInfoForActivity(Seller seller, Integer orderType, ActFlashSaleProduct actFlashSaleProduct,
                                            ActGroup actGroup, ActBidding actBidding, BigDecimal transFee, Member member,
                                            OrderCommitVO orderCommitVO, MemberAddress address, Integer integral, Config config) {
        // 生成订单编号
        String orderSn = RandomUtil.getOrderSn();
        // 生成订单
        Orders order = new Orders();
        // 设为限时抢购、团购订单、竞价定金订单
        order.setOrderType(orderType);
        order.setOrderSn(orderSn);
        // 关联订单编号
        order.setRelationOrderSn("");
        order.setSellerId(seller.getId());
        order.setMemberId(member.getId());
        order.setMemberName(member.getName());
        // 判断发票状态，记录发票信息
        order.setInvoiceStatus(orderCommitVO.getInvoiceStatus());
        if (Orders.INVOICE_STATUS_0 != orderCommitVO.getInvoiceStatus().intValue()) {
            order.setInvoiceTitle(orderCommitVO.getInvoiceTitle());
            order.setInvoiceType(orderCommitVO.getInvoiceType());
        }

        order.setIp(orderCommitVO.getIp());
        // 支付信息
        order.setPaymentName(orderCommitVO.getPaymentName());
        order.setPaymentCode(orderCommitVO.getPaymentCode());

        // 收货地址信息设置
        order.setName(address.getmemberName());
        order.setProvinceId(address.getProvinceId());
        order.setCityId(address.getCityId());
        order.setAreaId(address.getAreaId());
        order.setAddressAll(address.getAddAll());
        order.setAddressInfo(address.getAddressInfo());
        order.setMobile(address.getMobile());
        order.setEmail(address.getEmail());
        order.setZipCode(address.getZipCode());

        // 设置订单备注
        order.setRemark(orderCommitVO.getRemark());
        // 在线交易支付流水号
        order.setTradeSn("");
        // 订单来源：1、pc；2、H5；3、Android；4、IOS
        order.setSource(orderCommitVO.getSource());
        // 物流信息
        order.setLogisticsId(0);
        order.setLogisticsName("");
        order.setLogisticsNumber("");

        // 是否货到付款订单0、不是；1、是
        order.setIsCodconfim(Orders.IS_CODCONFIM_0);
        // 货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
        order.setCodconfirmState(Orders.CODCONFIRM_STATE_0);
        order.setCodconfirmId(0);
        order.setCodconfirmName("");
        order.setCodconfirmRemark("");

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        /**
         * 金额的计算
         */

        // 金额信息

        // 商品总额（只是商品价格*数量的金额之和）
        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            BigDecimal moneyProduct = actFlashSaleProduct.getPrice().multiply(new BigDecimal(orderCommitVO.getNumber()));
            moneyProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setMoneyProduct(moneyProduct);
            // 活动id（与订单类型对应），限时抢购订单存抢购活动商品表ID
            order.setActivityId(actFlashSaleProduct.getId());
        } else if (orderType.intValue() == Orders.ORDER_TYPE_8) {
            BigDecimal moneyProduct = actGroup.getPrice().multiply(new BigDecimal(orderCommitVO.getNumber()));
            moneyProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setMoneyProduct(moneyProduct);
            // 活动id（与订单类型对应），团购订单存团购活动表ID
            order.setActivityId(actGroup.getId());
        } else if (orderType.intValue() == Orders.ORDER_TYPE_9) {
            BigDecimal moneyProduct = actBidding.getFirstPrice().multiply(new BigDecimal(orderCommitVO.getNumber()));
            moneyProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setMoneyProduct(moneyProduct);
            // 活动id（与订单类型对应），集合竞价订单存集合竞价活动表ID
            order.setActivityId(actBidding.getId());
        }

        // 判断物流费用
        order.setMoneyLogistics(transFee);
        // 余额支付金额（此处暂时设定为0，支付之后再修改）
        order.setMoneyPaidBalance(BigDecimal.ZERO);
        // 现金支付金额
        order.setMoneyPaidReality(new BigDecimal(0));

        // 优惠券优惠金额、优惠券ID（coupon_user的ID），大活动不能使用优惠券
        order.setMoneyCoupon(new BigDecimal(0));
        order.setCouponUserId(0);
        // 订单满减金额，大活动不参加满减
        order.setMoneyActFull(BigDecimal.ZERO);
        order.setActFullId(0);
        // 优惠金额总额（满减、立减、优惠券和），大活动不参加活动、不使用优惠券
        order.setMoneyDiscount(BigDecimal.ZERO);
        // 新订单退款的金额为0
        order.setMoneyBack(new BigDecimal(0));
        // 订单使用积分金额
        if (integral > 0) {
            // 计算转换总金额
            int moneyIntegral = integral / config.getIntegralScale();
            order.setMoneyIntegral(new BigDecimal(moneyIntegral));
            order.setIntegral(integral);

            // 修改用户积分数，记录积分消耗日志
            Member memberNew = new Member();
            memberNew.setId(member.getId());
            memberNew.setIntegral(0 - integral);
            Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
            if (updateIntegral == 0) {
                throw new BusinessException("扣除用户积分时失败，请重试！");
            }
            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
            memberGradeIntegralLogs.setMemberId(member.getId());
            memberGradeIntegralLogs.setMemberName(member.getName());
            memberGradeIntegralLogs.setValue(integral);
            memberGradeIntegralLogs.setOptType(ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_7);
            memberGradeIntegralLogs.setOptDes("订单" + order.getOrderSn() + "消费积分");
            memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_2);
            memberGradeIntegralLogs.setCreateTime(new Date());
            Integer save = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
            if (save == 0) {
                throw new BusinessException("记录用户积分消费日志失败，请重试！");
            }
        } else {
            order.setMoneyIntegral(BigDecimal.ZERO);
            order.setIntegral(0);
        }

        // 订单总金额，等于商品总金额＋运费-优惠金额总额（这个金额是最后结算给商家的金额）
        order.setMoneyOrder(((order.getMoneyProduct().add(order.getMoneyLogistics()))
            .subtract(order.getMoneyDiscount())));

        // 服务费
        order.setServicePrice(BigDecimal.ZERO);

        OrderLog orderLogForPaid = null;
        OrderPayLog payLog = null;
        if ((order.getMoneyOrder().subtract(order.getMoneyIntegral())).compareTo(new BigDecimal(0)) <= 0) {
            // 如果订单金额减去积分支付金额小于等于0，则直接设定为已付款
            if (orderType.intValue() == Orders.ORDER_TYPE_9) {
                // 竞价定金订单付款之后直接设定状态为已完成（定金订单不需要发货）
                order.setOrderState(Orders.ORDER_STATE_5);
            } else {
                order.setOrderState(Orders.ORDER_STATE_3);
            }
            order.setPayTime(new Date()); //Terry mark
            order.setPaymentStatus(Orders.PAYMENT_STATUS_1);

            orderLogForPaid = new OrderLog();
            orderLogForPaid.setContent("您使用积分支付了订单");
            orderLogForPaid.setOperatingId(member.getId());
            // 订单保存后设定订单ID
            // orderLogForPaid.setOrdersId(order.getId());
            orderLogForPaid.setOrdersSn(order.getOrderSn());
            orderLogForPaid.setOperatingName(member.getName());

            // 记录订单支付日志
            payLog = new OrderPayLog();
            // 订单保存后设定订单ID
            // payLog.setOrdersId(order.getId());
            payLog.setOrdersSn(order.getOrderSn());
            payLog.setPayStatus(order.getPaymentCode());
            payLog.setPayContent("");
            payLog.setPayMoney(BigDecimal.ZERO);
            payLog.setMemberId(member.getId());
            payLog.setMemberName(member.getName());
            payLog.setCreateTime(new Date());

        } else {
            // 其他情况

            // 付款状态
            order.setOrderState(Orders.ORDER_STATE_1);
            order.setPaymentStatus(Orders.PAYMENT_STATUS_0);

            // 如果使用余额
            if (orderCommitVO.getIsBalancePay()) {
                // 需要付款的金额（订单金额 - 积分换算金额）
                BigDecimal money = order.getMoneyOrder().subtract(order.getMoneyIntegral());
                // 如果是在线支付，且余额足以支付所有的金额，则使用余额支付，修改订单的支付状态
                if (money.compareTo(member.getBalance()) <= 0) {

                    // 修改用户的余额
                    Member updateBalanceObj = new Member();
                    updateBalanceObj.setId(orderCommitVO.getMemberId());
                    updateBalanceObj.setBalance(money.multiply(new BigDecimal(-1)));
                    Integer updateBalance = memberWriteDao.updateBalance(updateBalanceObj);
                    if (updateBalance == 0) {
                        log.error("扣除余额时失败。");
                        throw new BusinessException("扣除余额时失败，请重试！");
                    }

                    // 记录【会员账户余额变化日志表】
                    MemberBalanceLogs logs = new MemberBalanceLogs();
                    logs.setMemberId(member.getId());
                    logs.setMemberName(member.getName());
                    logs.setMoneyBefore(member.getBalance());
                    logs.setMoneyAfter(member.getBalance().subtract(money));
                    logs.setMoney(money);
                    logs.setCreateTime(new Date());
                    logs.setState(MemberBalanceLogs.STATE_3);
                    logs.setRemark("消费，订单号" + order.getOrderSn());
                    logs.setOptId(member.getId());
                    logs.setOptName(member.getName());

                    Integer balanceLog = memberBalanceLogsWriteDao.save(logs);
                    if (balanceLog == 0) {
                        log.error("记录会员余额变化日志时出错。");
                        throw new BusinessException("扣除余额时失败，请重试！");
                    }

                    // 修改订单付款状态
                    if (orderType.intValue() == Orders.ORDER_TYPE_9) {
                        // 竞价定金订单付款之后直接设定状态为已完成（定金订单不需要发货）
                        order.setOrderState(Orders.ORDER_STATE_5);
                    } else {
                        order.setOrderState(Orders.ORDER_STATE_3);
                    }
                    order.setPayTime(new Date()); //Terry mark
                    order.setPaymentStatus(Orders.PAYMENT_STATUS_1);
                    order.setMoneyPaidBalance(money);
                    order.setPaymentName("余额支付");
                    order.setPaymentCode(Orders.PAYMENT_CODE_BALANCE);

                    orderLogForPaid = new OrderLog();
                    orderLogForPaid.setContent("您使用余额支付了订单");
                    orderLogForPaid.setOperatingId(member.getId());
                    // 订单保存后设定订单ID
                    // orderLogForPaid.setOrdersId(order.getId());
                    orderLogForPaid.setOrdersSn(order.getOrderSn());
                    orderLogForPaid.setOperatingName(member.getName());

                    // 记录订单支付日志
                    payLog = new OrderPayLog();
                    // 订单保存后设定订单ID
                    // payLog.setOrdersId(order.getId());
                    payLog.setOrdersSn(order.getOrderSn());
                    payLog.setPayStatus(order.getPaymentCode());
                    payLog.setPayContent("");
                    payLog.setPayMoney(money);
                    payLog.setMemberId(member.getId());
                    payLog.setMemberName(member.getName());
                    payLog.setCreateTime(new Date());
                }
            }
        }

        // 1、保存订单
        int count = ordersWriteDao.insert(order);
        if (count == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }
        //保存订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setContent("您提交了订单，请等待系统确认");
        orderLog.setOperatingId(member.getId());
        orderLog.setOrdersId(order.getId());
        orderLog.setOrdersSn(order.getOrderSn());
        orderLog.setOperatingName(member.getName());

        int orderlogCount = orderLogWriteDao.save(orderLog);
        if (orderlogCount == 0) {
            throw new BusinessException("订单保存失败，请重试！");
        }

        // 如果订单已经支付，则占库存，增加销量
        if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
            Integer stockRow = 0;
            if (orderType.intValue() == Orders.ORDER_TYPE_2) {
                stockRow = this.updateFlashProductStockAndActualSales(actFlashSaleProduct.getId(),
                    orderCommitVO.getNumber());
            } else if (orderType.intValue() == Orders.ORDER_TYPE_8) {
                stockRow = this.updateGroupStockAndActualSales(actGroup.getId(),
                    orderCommitVO.getNumber());
            } else if (orderType.intValue() == Orders.ORDER_TYPE_9) {
                stockRow = this.updateBiddingStockAndActualSales(actBidding.getId(),
                    orderCommitVO.getNumber());
            }
            if (stockRow == 0) {
                throw new BusinessException("修改商品库存时失败，请重试！");
            }

            // 订单支付时记录订单日志
            if (orderLogForPaid != null) {
                orderLogForPaid.setOrdersId(order.getId());
                orderLogWriteDao.save(orderLogForPaid);
            }

            // 订单支付日志
            if (payLog != null) {
                payLog.setOrdersId(order.getId());
                orderPayLogWriteDao.save(payLog);
            }
        }

        return order;
    }

    /**
     * 保存限时抢购、团购、集合竞价定金网单信息
     * @param order 订单
     * @param product 商品
     * @param goods 货品
     * @param orderType 订单类型
     * @param actFlashSaleProduct 限时抢购活动
     * @param actGroup 团购活动
     * @param actBidding 集合竞价活动
     * @param member 会员
     * @param seller 商家
     * @param number 数量
     */
    private void saveOrderProductInfoForActivity(Orders order, Product product, ProductGoods goods, Integer orderType,
                                                 ActFlashSaleProduct actFlashSaleProduct, ActGroup actGroup, ActBidding actBidding, Member member, Seller seller, Integer number) {

        //保存网单信息
        OrdersProduct op = new OrdersProduct();
        op.setOrdersId(order.getId());
        op.setOrdersSn(order.getOrderSn());
        op.setSellerId(seller.getId());
        op.setProductCateId(product.getProductCateId());
        op.setProductId(product.getId());
        op.setProductGoodsId(goods.getId());
        op.setSpecInfo(goods.getNormName());
        op.setProductName(product.getName1());
        op.setProductSku(goods.getSku());
        op.setPackageGroupsId(0);
        op.setMallGroupsId(0);
        op.setGiftId(0);
        op.setIsGift(OrdersProduct.IS_GIFT_0);
        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            op.setMoneyPrice(actFlashSaleProduct.getPrice());
            // 网单金额
            BigDecimal moneyAmount = actFlashSaleProduct.getPrice()
                .multiply(new BigDecimal(number));
            moneyAmount = moneyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            op.setMoneyAmount(moneyAmount);

            op.setActGroupId(0);
            op.setActFlashSaleId(actFlashSaleProduct.getActFlashSaleId());
            op.setActFlashSaleProductId(actFlashSaleProduct.getId());
            op.setActBiddingId(0);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_8) {
            op.setMoneyPrice(actGroup.getPrice());
            // 网单金额
            BigDecimal moneyAmount = actGroup.getPrice().multiply(new BigDecimal(number));
            moneyAmount = moneyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            op.setMoneyAmount(moneyAmount);

            op.setActGroupId(actGroup.getId());
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActBiddingId(0);
        } else if (orderType.intValue() == Orders.ORDER_TYPE_9) {
            op.setMoneyPrice(actBidding.getFirstPrice());
            // 网单金额
            BigDecimal moneyAmount = actBidding.getFirstPrice().multiply(new BigDecimal(number));
            moneyAmount = moneyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            op.setMoneyAmount(moneyAmount);

            op.setActGroupId(0);
            op.setActFlashSaleId(0);
            op.setActFlashSaleProductId(0);
            op.setActBiddingId(actBidding.getId());
        }
        op.setNumber(number);

        // 立减优惠金额和，不参加活动
        op.setMoneyActSingle(BigDecimal.ZERO);
        op.setActSingleId(0);
        op.setActivityId(0);
        op.setMemberProductBackId(0);
        op.setMemberProductExchangeId(0);

        //套餐单价
        // op.setPackageFee(BigDecimal.ZERO);
        //套餐总价 = 套餐单价*数量
        op.setPackageFeeTotal(BigDecimal.ZERO);
        //置为0
        // op.setLabelFee(BigDecimal.ZERO);
        op.setLabelFeeTotal(BigDecimal.ZERO);

        // 1、保存网单
        int count = ordersProductWriteDao.insert(op);
        if (count == 0) {
            throw new BusinessException("网单保存失败，请重试！");
        }

        if (orderType.intValue() == Orders.ORDER_TYPE_2) {
            // 设定网单的限时抢购活动参与日志
            ActFlashSaleLog actFlashSaleLog = new ActFlashSaleLog();
            actFlashSaleLog.setActFlashSaleId(actFlashSaleProduct.getActFlashSaleId());
            actFlashSaleLog.setActFlashSaleProductId(actFlashSaleProduct.getId());
            actFlashSaleLog.setMemberId(member.getId());
            actFlashSaleLog.setSellerId(seller.getId());
            actFlashSaleLog.setOrderId(order.getId());
            actFlashSaleLog.setOrderProductId(op.getId());
            actFlashSaleLog.setProductId(product.getId());
            actFlashSaleLog.setCreateUserId(member.getId());
            actFlashSaleLog.setCreateUserName(member.getName());
            actFlashSaleLog.setCreateTime(new Date());
            actFlashSaleLogWriteDao.insert(actFlashSaleLog);
        }
    }

    /**
     * 检查商品和商家信息
     * @param product
     * @param productGoods
     * @param seller
     */
    private void checkProductAndSellerForActivity(Product product, ProductGoods productGoods,
                                                  Seller seller) {
        if (product == null) {
            throw new BusinessException("商品信息获取失败！");
        }
        if (productGoods == null) {
            throw new BusinessException("货品信息获取失败！");
        }
        if (seller == null) {
            throw new BusinessException("商家信息获取失败！");
        }
        if (!productGoods.getProductId().equals(product.getId())) {
            throw new BusinessException("货品信息和商品信息不匹配！");
        }
        if (!seller.getId().equals(product.getSellerId())) {
            throw new BusinessException("商品信息和商家信息不匹配！");
        }
        if (seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
            throw new BusinessException("商家[" + seller.getSellerName() + "]已被冻结，请把该商家的商品移出购物车后再下单，谢谢！");
        }
        if (product.getState().intValue() != Product.STATE_6) {
            throw new BusinessException("该商品未上架，不能下单！");
        }
        // 判断分类状态
        if (product.getProductCateState().intValue() != Product.PRODUCT_CATE_STATE_1) {
            throw new BusinessException("该商品已下架，请重新选择商品！");
        }
    }

    // ------------------------活动公用方法结束-------------------------------------------------------------------------------

    // ------------------------团购开始-------------------------------------------------------------------------------------

    /**
     * 用户团购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForGroup(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }
        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            throw new BusinessException("对不起，团购活动不支持货到付款！");
        }

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID) || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        // 获取团购活动
        ActGroup actGroup = actGroupReadDao.get(orderCommitVO.getActGroupId());
        this.checkActGroupEffect(actGroup);
        // 校验渠道
        if (actGroup.getChannel().intValue() != ConstantsEJS.CHANNEL_1) {
            if (actGroup.getChannel().intValue() != channel) {
                String channelName = channel == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException("该团购活动不能在" + channelName + "下单。");
            }
        }
        // 校验库存
        if (actGroup.getStock() < orderCommitVO.getNumber()) {
            throw new BusinessException("对不起，商品库存不足了，请修改购买数量后再下单！");
        }
        // 单次购买限制
        if (orderCommitVO.getNumber() > actGroup.getPurchase()) {
            throw new BusinessException("对不起，单次最多购买" + actGroup.getPurchase() + "个该商品！");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressReadDao.get(orderCommitVO.getAddressId());

            // 获取提交订单的用户
            Member member = memberReadDao.get(orderCommitVO.getMemberId());

            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(orderCommitVO.getSellerId(), orderCommitVO.getNumber(), address.getCityId(), 1);
            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO : calculateTransFee;

            //如果使用了余额支付 ，判断支付密码
            if (orderCommitVO.getIsBalancePay() != null && orderCommitVO.getIsBalancePay()) {
                this.checkBalance(orderCommitVO, member, actGroup.getPrice().multiply(new BigDecimal(orderCommitVO.getNumber())));
            }

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActivity(seller, Orders.ORDER_TYPE_8, null, actGroup, null, calculateTransFee, member, orderCommitVO, address, integral, config);
            orderList.add(order);

            // 保存网单信息
            this.saveOrderProductInfoForActivity(order, product, productGoods, Orders.ORDER_TYPE_8,
                null, actGroup, null, member, seller, orderCommitVO.getNumber());

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode("余额支付");
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象 
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setRelationOrderSn(order.getOrderSn());
            orderSuccVO.setPayAmount(order.getMoneyOrder().subtract(order.getMoneyIntegral()));
            orderSuccVO.setIsBanlancePay(orderCommitVO.getIsBalancePay());
            orderSuccVO.setBalancePwd(orderCommitVO.getBalancePwd());
            transactionManager.commit(status);
            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 团购活动有效性校验
     * @param actGroup
     */
    private void checkActGroupEffect(ActGroup actGroup) {
        if (actGroup.getTypeState() == null || actGroup.getTypeState() != ActGroup.TYPE_STATE_1) {
            log.error("团购活动" + actGroup.getName() + "的分类状态为不显示，下单失败。");
            throw new BusinessException("对不起，该团购活动已下线！");
        }
        if (actGroup.getState() == null || actGroup.getState() != ActGroup.STATE_3) {
            throw new BusinessException("对不起，团购活动不存在！");
        }
        if (actGroup.getActivityState() == null
            || actGroup.getActivityState() != ActGroup.ACTIVITY_STATE_2) {
            throw new BusinessException("对不起，该团购活动还没有发布！");
        }
        Date date = new Date();
        if (date.before(actGroup.getStartTime())) {
            throw new BusinessException("对不起，该团购活动还没有开始！");
        }
        if (date.after(actGroup.getEndTime())) {
            throw new BusinessException("对不起，该团购活动已结束！");
        }
        if (actGroup.getStock() < 1) {
            throw new BusinessException("对不起，该商品已经被抢光了！");
        }
        if (actGroup.getActivityState().intValue() != ActGroup.ACTIVITY_STATE_2
            || actGroup.getState() != ActGroup.STATE_3) {
            throw new BusinessException("对不起，该团购活动还未发布！");
        }
    }

    /**
     * 修改团购活动商品的库存和销量，库存减number，销量加number
     * @param actGroupId
     * @param number
     */
    private Integer updateGroupStockAndActualSales(Integer actGroupId, Integer number) {
        return actGroupWriteDao.updateStockAndActualSales(actGroupId, number);
    }

    /**
     * 还原团购活动商品的库存和销量，库存加number，销量减number
     * @param actGroupId
     * @param number
     */
    private Integer backGroupStockAndActualSales(Integer actGroupId, Integer number) {
        return actGroupWriteDao.backStockAndActualSales(actGroupId, number);
    }

    // ------------------------团购结束-------------------------------------------------------------------------------------

    // ------------------------集合竞价开始----------------------------------------------------------------------------------

    /**
     * 用户提交集合竞价订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    public OrderSuccessVO orderCommitForBidding(OrderCommitVO orderCommitVO) throws Exception {

        //参数校验
        if (orderCommitVO == null) {
            log.error("订单提交信息为空。");
            throw new BusinessException("订单提交信息为空，请重试！");
        } else if (orderCommitVO.getAddressId() == null || orderCommitVO.getAddressId() == 0) {
            log.error("订单提交信息中收货地址ID为空。");
            throw new BusinessException("订单提交信息中收货地址ID为空，请重试！");
        } else if (StringUtil.isEmpty(orderCommitVO.getPaymentName())) {
            log.error("订单提交信息中支付方式为空。");
            throw new BusinessException("订单提交信息中支付方式为空，请重试！");
        }

        if (Orders.PAYMENT_CODE_OFFLINE.equals(orderCommitVO.getPaymentCode())) {
            throw new BusinessException("对不起，当前活动订单不支持货到付款！");
        }

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (orderCommitVO.getSource() != null && (orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_2_H5)
                || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_3_ANDROID) || orderCommitVO.getSource().equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        // 获取活动
        ActBidding actBidding = actBiddingReadDao.get(orderCommitVO.getActBiddingId());
        // 校验活动
        this.checkActBidding(actBidding);

        // 校验渠道
        if (actBidding.getChannel().intValue() != ConstantsEJS.CHANNEL_1) {
            if (actBidding.getChannel().intValue() != channel) {
                String channelName = channel == ConstantsEJS.CHANNEL_2 ? "电脑端" : "移动端";
                throw new BusinessException("当前活动不能在" + channelName + "下单。");
            }
        }
        // 校验库存
        if (actBidding.getStock() < orderCommitVO.getNumber()) {
            throw new BusinessException("对不起，商品库存不足了，请修改购买数量后再下单！");
        }
        // 单次购买限制
        if (orderCommitVO.getNumber() > actBidding.getPurchase()) {
            throw new BusinessException("对不起，单次最多购买" + actBidding.getPurchase() + "个该商品！");
        }

        // 取得商品
        Product product = productReadDao.get(orderCommitVO.getProductId());
        // 获取货品
        ProductGoods productGoods = productGoodsReadDao.get(orderCommitVO.getProductGoodsId());
        // 获取商家
        Seller seller = sellerReadDao.get(orderCommitVO.getSellerId());

        this.checkProductAndSellerForActivity(product, productGoods, seller);

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 初始化返回的参数
            OrderSuccessVO orderSuccVO = new OrderSuccessVO();
            // 初始默认为订单没有支付，如果余额支付全款则重设为true
            orderSuccVO.setIsPaid(false);
            // 初始默认为跳往支付页面，如果订单是货到付款或者余额全额支付了，设定为false
            orderSuccVO.setGoJumpPayfor(true);
            // 支付方式默认与页面选择的一致，如果余额全额支付后，修改为Orders.PAYMENT_CODE_BALANCE，余额支付
            orderSuccVO.setPaymentCode(orderCommitVO.getPaymentCode());
            orderSuccVO.setPaymentName(orderCommitVO.getPaymentName());

            // 获取地址
            MemberAddress address = memberAddressReadDao.get(orderCommitVO.getAddressId());

            // 获取提交订单的用户
            Member member = memberReadDao.get(orderCommitVO.getMemberId());

            BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(orderCommitVO.getSellerId(), orderCommitVO.getNumber(), address.getCityId(), 1);
            calculateTransFee = calculateTransFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO : calculateTransFee;

            // 如果使用了余额支付 ，判断支付密码
            if (orderCommitVO.getIsBalancePay() != null && orderCommitVO.getIsBalancePay()) {
                this.checkBalance(orderCommitVO, member, actBidding.getFirstPrice().multiply(new BigDecimal(orderCommitVO.getNumber())));
            }

            // 如果订单使用积分，判断用户积分是否够填入的积分，是否够转换的最低金额
            Integer integral = orderCommitVO.getIntegral();
            Config config = null;
            if (integral != null && integral > 0) {
                config = configReadDao.get(ConstantsEJS.CONFIG_ID);
                this.checkIntegral(member, config, integral);
            }

            List<Orders> orderList = new ArrayList<Orders>();

            // 保存订单及日志信息
            Orders order = this.saveOrderInfoForActivity(seller, Orders.ORDER_TYPE_9, null, null, actBidding, calculateTransFee, member, orderCommitVO, address, integral, config);
            orderList.add(order);

            // 保存网单信息
            this.saveOrderProductInfoForActivity(order, product, productGoods, Orders.ORDER_TYPE_9, null, null, actBidding, member, seller, orderCommitVO.getNumber());

            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                // 记录是否已支付，返回判断跳往什么页面
                orderSuccVO.setIsPaid(true);
                orderSuccVO.setPaymentCode("余额支付");
                orderSuccVO.setPaymentName(Orders.PAYMENT_CODE_BALANCE);
                orderSuccVO.setGoJumpPayfor(false);
            }

            //封装返回对象 
            orderSuccVO.setOrdersList(orderList);
            orderSuccVO.setRelationOrderSn(order.getOrderSn());
            orderSuccVO.setPayAmount(order.getMoneyOrder().subtract(order.getMoneyIntegral()));
            orderSuccVO.setIsBanlancePay(orderCommitVO.getIsBalancePay());
            orderSuccVO.setBalancePwd(orderCommitVO.getBalancePwd());
            transactionManager.commit(status);

            return orderSuccVO;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 校验集合竞价活动
     * @param actBidding
     */
    private void checkActBidding(ActBidding actBidding) {
        if (actBidding == null) {
            throw new BusinessException("集合竞价活动不存在，请稍后再试！");
        }
        if (actBidding.getTypeState() == null
            || actBidding.getTypeState() != ActBidding.TYPE_STATE_1) {
            log.error("活动" + actBidding.getName() + "的分类状态为不显示，下单失败。");
            throw new BusinessException("对不起，该活动已下线！");
        }
        Date date = new Date();
        if (date.before(actBidding.getStartTime())) {
            throw new BusinessException("活动还没有开始，请活动开始后再下单！");
        }
        if (date.after(actBidding.getEndTime())) {
            throw new BusinessException("活动已经结束了，请参加下次活动吧！");
        }
        if (actBidding.getStock() < 1) {
            throw new BusinessException("活动名额已经抢完了，请参加下次活动吧！");
        }
        if (!actBidding.getActivityState().equals(ActBidding.ACTIVITY_STATE_2)) {
            throw new BusinessException("活动未发布，请参加其他活动！");
        }
        if (!actBidding.getState().equals(ActBidding.STATE_3)) {
            throw new BusinessException("活动未被审核通过，请参加其他活动！");
        }
        if (!actBidding.getExecuteState().equals(ActBidding.EXECUTE_STATE_0)) {
            throw new BusinessException("活动已经结束了，请参加下次活动吧！");
        }
    }

    /**
     * 修改集合竞价活动商品的库存和销量，库存减number，销量加number
     * @param actBiddingId
     * @param number
     */
    private Integer updateBiddingStockAndActualSales(Integer actBiddingId, Integer number) {
        return actBiddingWriteDao.updateStockAndActualSales(actBiddingId, number);
    }

    //    此方法因为集合竞价定金订单支付即完成所以不会发生还原库存，尾款订单不能取消且尾款订单不占库存同样不会发生还原库存的情况，所以不会被调用
    //    /**
    //     * 还原集合竞价活动商品的库存和销量，库存加number，销量减number
    //     * @param actBiddingId
    //     * @param number
    //     */
    //    private Integer backBiddingStockAndActualSales(Integer actBiddingId, Integer number) {
    //        return actBiddingWriteDao.backStockAndActualSales(actBiddingId, number);
    //    }

    // ------------------------集合竞价结束----------------------------------------------------------------------------------

    public Boolean saveOrdersProductPrice(Integer orderId, String opinfo, String submitType) {
        boolean flag = false;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            //保存网单及订单
            saveOrdersProduct(orderId, opinfo, submitType);
            flag = true;
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
    
    
    /**
     * 保存退货信息
     * @param orderId
     * @param opinfo
     * @param usedId 
     * @param submitType
     * @return
     */
    public Boolean saveOrdersReturn(Integer orderId, String opinfo, String returnInfo, Integer usedId) {
        boolean flag = false;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            //保存网单及订单
            saveOrdersProductReturn(orderId, opinfo ,returnInfo ,usedId);
            flag = true;
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    private void saveOrdersProductReturn(Integer orderId, String opinfo, String returnInfo, Integer usedId) throws Exception {
        String[] opinfos = opinfo.split("!@\\|@!");
        //所有网单总价
        BigDecimal sum = BigDecimal.ZERO;
        //套餐总价
        BigDecimal packnum = BigDecimal.ZERO;
        //套餐价格
        BigDecimal return_packPrice = BigDecimal.ZERO;
        //标签价格
        BigDecimal return_labelprice = BigDecimal.ZERO;
        //标签总价
        BigDecimal labelsum = BigDecimal.ZERO;
        //商品立减的总金额
        BigDecimal reduceAmount = BigDecimal.ZERO;
        //针对退货return表的立减金额
        BigDecimal return_reduceAmount = BigDecimal.ZERO;
        //商品总金额
        BigDecimal return_money_product = BigDecimal.ZERO;
        //退货商品总金额
        BigDecimal return_moneyorder = BigDecimal.ZERO;
        //重新计算订单的价格
        Orders orders = ordersReadDao.get(orderId);
        for (String str : opinfos) {
            BigDecimal opprice = BigDecimal.ZERO;
            //商品总金额
            BigDecimal productAmount = BigDecimal.ZERO;
            //针对sku的单品立减金额
            BigDecimal op_reduceAmount = BigDecimal.ZERO;
            //退货总金额
            BigDecimal return_moneyAmount = BigDecimal.ZERO;
            String[] opArr = str.split(",");
            OrdersProduct ordersProduct = ordersProductReadDao.get(Integer.valueOf(opArr[0]));
//            OrdersProductError ordersProductError = new OrdersProductError();
            //先保存一条备份数据
//            if (ordersProduct != null) {
//                ordersProductError = this.getOrdersProductError(ordersProduct);
//                ordersProductErrorWriteDao.insert(ordersProductError);
//            }
            if(opArr[1] != null && !opArr[1].equals("0")){
                OrdersProductReturn ordersProductReturn = new OrdersProductReturn();
                //先封装退货信息中与ordersproduct相同信息的字段
                ordersProductReturn = this.getOrdersProductReturn(ordersProduct);
                BigDecimal proNumber = BigDecimal.ZERO;
                
                //前台传入的本次退货数量
                BigDecimal reduceNumber = new BigDecimal(opArr[1]);
                ordersProductReturn.setNumber(Integer.valueOf(opArr[1]));
                //剩余商品数量
                proNumber = new BigDecimal(ordersProduct.getNumber()).subtract(reduceNumber);
                
                //在此处把退货的销量 加上去 2016.12.30 小甜甜
                Integer returnActualSales = -1*(reduceNumber.intValue());
                //返还商品销量
                productWriteDao.updateActualSales(ordersProduct.getProductId(), returnActualSales);
                //返还货品销量
                productGoodsWriteDao.updateActualSales(ordersProduct.getId(), returnActualSales);
                
                productAmount = productAmount.add(proNumber.multiply(ordersProduct.getMoneyPrice()));
                //退货时纯粹的退货数量*单价
                return_moneyAmount = return_moneyAmount.add(reduceNumber.multiply(ordersProduct.getMoneyPrice()));
                return_money_product = return_money_product.add(reduceNumber.multiply(ordersProduct.getMoneyPrice()));
                //重新计算sku二次加工的盒子数量
                Integer packageId = ordersProduct.getProductPackageId();
                ProductPackage productPackage = productPackageReadDao.get(packageId);
                if (productPackage != null) {
                      Integer unitType = productPackage.getUnitType();
                      if (unitType != 0) {
//                          Double labelNumber = Math.ceil(Integer.parseInt(proNumber.toString()) / unitType);
                          Double return_labelNum = Math.ceil(Integer.parseInt(reduceNumber.toString()) / unitType);
//                          ordersProduct.setLabelNumber(Integer.valueOf(labelNumber.intValue()));
                          ordersProductReturn.setLabelNumber(Integer.valueOf(return_labelNum.intValue()));
                      }
                 }

                //重新计算立减的优惠金额 
                if (!StringUtil.isNullOrZero(ordersProduct.getActSingleId())) {
                   BigDecimal scle = ordersProduct.getMoneyActSingle().divide(new BigDecimal(ordersProduct.getNumber())).setScale(4, BigDecimal.ROUND_HALF_UP);
                   op_reduceAmount = scle.multiply(proNumber);
                   reduceAmount = reduceAmount.add(op_reduceAmount);
                   return_reduceAmount= return_reduceAmount.add(scle.multiply(reduceNumber));
                   //数量变化后单品立减的金额也会变化，重新覆盖
//                   ordersProduct.setMoneyActSingle(op_reduceAmount);
                   ordersProductReturn.setMoneyActSingle(scle.multiply(reduceNumber));
                   
                   //减去立减
                   return_moneyAmount = return_moneyAmount.subtract(scle.multiply(reduceNumber));
                }else{
//                    ordersProduct.setActSingleId(0);
//                    ordersProduct.setMoneyActSingle(new BigDecimal(0.00));
                    ordersProductReturn.setMoneyActSingle(new BigDecimal(0.00));
                }

                //套餐总价＝ 套餐单价*数量
                //即加工费
                if(ordersProduct.getPackageFee() != null && !ordersProduct.getPackageFee().equals("0.00")){
//                    ordersProduct.setPackageFeeTotal(ordersProduct.getPackageFee().multiply(proNumber));
                    ordersProductReturn.setPackageFeeTotal(ordersProduct.getPackageFee().multiply(reduceNumber));
                    return_packPrice = return_packPrice.add(ordersProductReturn.getPackageFeeTotal());
                    return_moneyAmount = return_moneyAmount.add(ordersProductReturn.getPackageFeeTotal());
                }else{
//                	ordersProduct.setPackageFeeTotal(new BigDecimal(0.00));
                	ordersProductReturn.setPackageFeeTotal(new BigDecimal(0.00));
                }
                
                //标签总价 ＝ 标签单价*数量(盒子数量)即辅料费
                if(ordersProduct.getLabelFee() != null && !ordersProduct.getLabelFee().equals("0.00")){
//                    ordersProduct.setLabelFeeTotal(ordersProduct.getLabelFee().multiply(new BigDecimal(
//                        ordersProduct.getLabelNumber() == null ? 0 : ordersProduct.getLabelNumber())));
                    ordersProductReturn.setLabelFeeTotal(ordersProduct.getLabelFee().multiply(new BigDecimal(ordersProductReturn.getLabelNumber() == null ? 0 : ordersProductReturn.getLabelNumber())));
                    return_labelprice = return_labelprice.add(ordersProductReturn.getLabelFeeTotal());
                    return_moneyAmount = return_moneyAmount.add(ordersProductReturn.getLabelFeeTotal());
                }else{
//                	ordersProduct.setLabelFeeTotal(new BigDecimal(0.00));
                	ordersProductReturn.setLabelFeeTotal(new BigDecimal(0.00));		
                }
                //网单价格 ＝ 商品价格总和+标签价格和+套餐价格和
                opprice = productAmount.add(ordersProduct.getLabelFeeTotal()).add(ordersProduct.getPackageFeeTotal().subtract(ordersProduct.getMoneyActSingle()));
            
                //商品数量
//                ordersProduct.setNumber(proNumber.intValue());
                ordersProductReturn.setNumber(reduceNumber.intValue());
                ordersProductReturn.setMoneyAmount(return_moneyAmount);
                
                //累计退货的标签费和加工费
                packnum = packnum.add(ordersProductReturn.getPackageFeeTotal()==null?new BigDecimal(0.00):ordersProductReturn.getPackageFeeTotal());
                labelsum = labelsum.add(ordersProductReturn.getLabelFeeTotal()==null?new BigDecimal(0.00):ordersProductReturn.getLabelFeeTotal());
                
                return_moneyorder = return_moneyorder.add(return_moneyAmount);
                ordersProductReturnWriteDao.insert(ordersProductReturn);
                
                //设置网单价格
//                ordersProduct.setMoneyAmount(opprice);
//                ordersProductWriteDao.update(ordersProduct);

                //总金额 ＝ 所有网单金额和
                sum = sum.add(opprice);
            }

        }
        OrdersReturn ordersReturn = new OrdersReturn(); 
        if (orders != null) {
//            OrdersError ordersError = new OrdersError();
//            ordersError = this.getOrdersError(orders, orders.getOrderSn());
            ordersReturn= this.getOrdersReturn(orders);
//            ordersErrorWriteDao.insert(ordersError);
        }

        //商品金额 = 原始订单减去退货商品总和
        orders.setMoneyProduct(orders.getMoneyProduct().subtract(return_money_product));
        ordersReturn.setMoneyProduct(return_money_product);
        ordersReturn.setCreateId(usedId);
        ordersReturn.setServicePrice(return_packPrice);
        ordersReturn.setLabelPrice(return_labelprice);
        ordersReturn.setReturnInfo(returnInfo);
        ordersReturn.setReturnState(1);
        ordersReturn.setMoneyDiscount(return_reduceAmount);
        ordersReturn.setMoneyOrder(return_moneyorder);
        ordersReturn.setMoneyLogistics(new BigDecimal(0.00));
        ordersReturnWriteDao.insert(ordersReturn);
        
        //重新计算订单满减的金额(现在无满减的业务，暂时保留此处逻辑 为日后进行扩展)
//        if (!StringUtil.isNullOrZero(orders.getActFullId())) {
//            ActFull actFull_vo = new ActFull();
//            actFull_vo = actFullWriteDao.get(orders.getActFullId());
            //满减第一等级
//            if (actFull_vo.getFirstFull() != null
//                && actFull_vo.getFirstFull().compareTo(BigDecimal.ZERO) > 0
//                && sum.compareTo(actFull_vo.getFirstFull()) >= 0) {
//                orders.setMoneyActFull(actFull_vo.getFirstDiscount());
//            }
//            if (actFull_vo.getSecondFull() != null
//                && actFull_vo.getSecondFull().compareTo(BigDecimal.ZERO) > 0
//                && sum.compareTo(actFull_vo.getSecondFull()) >= 0) {
//                orders.setMoneyActFull(actFull_vo.getSecondDiscount());
//            }
//            if (actFull_vo.getThirdFull() != null
//                && actFull_vo.getThirdFull().compareTo(BigDecimal.ZERO) > 0
//                && sum.compareTo(actFull_vo.getThirdFull()) >= 0) {
//                orders.setMoneyActFull(actFull_vo.getThirdDiscount());
//            }
//        }
//        orders.setMoneyDiscount(orders.getMoneyDiscount().subtract(return_reduceAmount));
//        orders.setMoneyOrder(orders.getMoneyOrder().subtract(return_moneyorder));
//        orders.setServicePrice(orders.getServicePrice().subtract(packnum));
//        orders.setLabelPrice(orders.getLabelPrice().subtract(labelsum));
//        orders.setUpdateTime(new Date());
//        ordersWriteDao.update(orders);
        }
        
    
    private OrdersReturn getOrdersReturn(Orders orders) {
        OrdersReturn ordersReturn = new OrdersReturn();
        ordersReturn.setOrderSn(orders.getOrderSn());
        ordersReturn.setRelationOrderSn(orders.getRelationOrderSn());
        ordersReturn.setOrderType(orders.getOrderType());
        ordersReturn.setSellerId(orders.getSellerId());
        ordersReturn.setMemberId(orders.getMemberId());
        ordersReturn.setMemberName(orders.getMemberName());
        ordersReturn.setOrderState(orders.getOrderState());
        ordersReturn.setPayTime(orders.getPayTime());
        ordersReturn.setPaymentStatus(orders.getPaymentStatus());
        ordersReturn.setInvoiceStatus(orders.getInvoiceStatus());
        ordersReturn.setInvoiceTitle(orders.getInvoiceTitle());
        ordersReturn.setInvoiceType(orders.getInvoiceType());
        ordersReturn.setLogisticsId(orders.getLogisticsId());
        ordersReturn.setLogisticsName(orders.getLogisticsName());
        ordersReturn.setLogisticsNumber(orders.getLogisticsNumber());
        ordersReturn.setMoneyPaidBalance(orders.getMoneyPaidBalance());
        ordersReturn.setMoneyPaidReality(orders.getMoneyPaidReality());
        ordersReturn.setMoneyCoupon(orders.getMoneyCoupon());
        ordersReturn.setMoneyActFull(new BigDecimal(0.00));
        ordersReturn.setMoneyBack(orders.getMoneyBack());
        ordersReturn.setMoneyIntegral(orders.getMoneyIntegral());
        ordersReturn.setIntegral(orders.getIntegral());
        ordersReturn.setCouponUserId(orders.getCouponUserId());
        ordersReturn.setActFullId(orders.getActFullId());
        ordersReturn.setActivityId(orders.getActivityId());
        ordersReturn.setIp(orders.getIp());
        ordersReturn.setPaymentCode(orders.getPaymentCode());
        ordersReturn.setPaymentName(orders.getPaymentName());
        ordersReturn.setName(orders.getName());
        ordersReturn.setProvinceId(orders.getProvinceId());
        ordersReturn.setCityId(orders.getCityId());
        ordersReturn.setAreaId(orders.getAreaId());
        ordersReturn.setAddressAll(orders.getAddressAll());
        ordersReturn.setAddressInfo(orders.getAddressInfo());
        ordersReturn.setMobile(orders.getMobile());
        ordersReturn.setEmail(orders.getEmail());
        ordersReturn.setZipCode(orders.getZipCode());
        ordersReturn.setRemark(orders.getRemark());
        ordersReturn.setDeliverTime(orders.getDeliverTime());
        ordersReturn.setFinishTime(orders.getFinishTime());
        ordersReturn.setSource(orders.getSource());
        ordersReturn.setIsCodconfim(orders.getIsCodconfim());
        ordersReturn.setCodconfirmId(orders.getCodconfirmId());
        ordersReturn.setCodconfirmName(orders.getCodconfirmName());
        ordersReturn.setCodconfirmTime(orders.getCodconfirmTime());
        ordersReturn.setCodconfirmRemark(orders.getCodconfirmRemark());
        ordersReturn.setCodconfirmState(orders.getCodconfirmState());
        ordersReturn.setTradeNo(orders.getTradeNo());
        ordersReturn.setTradeSn(orders.getTradeSn());
        return ordersReturn;
    }

    private OrdersProductReturn getOrdersProductReturn(OrdersProduct ordersProduct) {
        OrdersProductReturn opr = new OrdersProductReturn();
        opr.setOrdersId(ordersProduct.getOrdersId());
        opr.setOrdersSn(ordersProduct.getOrdersSn());
        opr.setSellerId(ordersProduct.getSellerId());
        opr.setProductCateId(ordersProduct.getProductCateId());
        opr.setProductId(ordersProduct.getProductId());
        opr.setProductGoodsId(ordersProduct.getProductGoodsId());
        opr.setSpecInfo(ordersProduct.getSpecInfo());
        opr.setProductName(ordersProduct.getProductName());
        opr.setProductSku(ordersProduct.getProductSku());
        opr.setMoneyPrice(ordersProduct.getMoneyPrice());
        opr.setPackageGroupsId(ordersProduct.getPackageGroupsId());
        opr.setMallGroupsId(ordersProduct.getMallGroupsId());
        opr.setGiftId(ordersProduct.getGiftId());
        opr.setIsGift(ordersProduct.getIsGift());
        opr.setActivityId(ordersProduct.getActivityId());
        opr.setActSingleId(ordersProduct.getActSingleId());
        opr.setActFlashSaleId(ordersProduct.getActFlashSaleId());
        opr.setActFlashSaleProductId(ordersProduct.getActFlashSaleProductId());
        opr.setActGroupId(ordersProduct.getActGroupId());
        opr.setActBiddingId(ordersProduct.getActBiddingId());
        opr.setLogisticsId(null);
        opr.setLogisticsName(null);
        opr.setLogisticsNumber(null);
        opr.setShippingTime(null);
        opr.setProductPackageId(ordersProduct.getProductPackageId());
        opr.setProductLabelId(ordersProduct.getProductLabelId());
        opr.setPackageFee(ordersProduct.getPackageFee());
        opr.setLabelFee(ordersProduct.getLabelFee());
        opr.setIsSelfLabel(ordersProduct.getIsSelfLabel());
        opr.setDeliveredNum(ordersProduct.getDeliveredNum());
        opr.setCloseTime(null);
        opr.setSystemRemark(null);
        opr.setMemberProductBackId(ordersProduct.getMemberProductBackId());
        opr.setMemberProductExchangeId(ordersProduct.getMemberProductExchangeId());
        return opr;
    }

    private void saveOrdersProduct(Integer orderId, String opinfo, String submitType) throws Exception {
        //opid,packprice,labelprice,money_price
        String[] opinfos = opinfo.split("!@\\|@!");
        //所有网单总价
        BigDecimal sum = BigDecimal.ZERO;
        //套餐总价
        BigDecimal packnum = BigDecimal.ZERO;
        //标签总价
        BigDecimal labelsum = BigDecimal.ZERO;
        //商品立减的总金额
        BigDecimal reduceAmount = BigDecimal.ZERO;
        //商品总金额
        BigDecimal money_product = BigDecimal.ZERO;
        String newOrdersSn = "";

        //重新计算订单的价格
        Orders orders = ordersWriteDao.get(orderId);
        //        if (orders != null && orders.getOrderState() != Orders.ORDER_STATE_1
        //            && !"newOrders".equals(submitType)) {
        //            throw new BusinessException("只有未付款的订单可以修改价格");
        //        }

        for (String str : opinfos) {
            BigDecimal opprice = BigDecimal.ZERO;
            //套餐价格
            BigDecimal packPrice = BigDecimal.ZERO;
            //标签价格
            BigDecimal labelprice = BigDecimal.ZERO;
            //商品总金额
            BigDecimal productAmount = BigDecimal.ZERO;
            //针对sku的单品立减金额
            BigDecimal op_reduceAmount = BigDecimal.ZERO;
            String[] opArr = str.split(",");
            //priceSet.ftl页面多了一个隐藏域 导致传过来的数组多了一位，需要将第三给去除掉
            String[] newOpArr = new String[5];
            for (int i = 0; i < 5; i++) {
                if (i >= 2) {
                    newOpArr[i] = opArr[i + 1];
                } else {
                    newOpArr[i] = opArr[i];
                }
            }
            opArr = newOpArr;
            if (opArr.length != 5) {
                throw new BusinessException("数据格式错误");
            }
            OrdersProduct ordersProduct = ordersProductWriteDao.get(Integer.valueOf(opArr[0]));

            //          if ("newOrders".equals(submitType)) {
            OrdersProductError ordersProductError = new OrdersProductError();
            if (ordersProduct != null) {
                ordersProductError = this.getOrdersProductError(ordersProduct);
                ordersProductErrorWriteDao.insert(ordersProductError);
            }
            //          }

            BigDecimal proNumber = BigDecimal.ZERO;
            //商品数量
            if (null != opArr[1] && !"".equals(opArr[1])) {
                proNumber = new BigDecimal(opArr[1]);

                //重新计算sku二次加工的盒子数量

                Integer packageId = ordersProduct.getProductPackageId();
                ProductPackage productPackage = productPackageReadDao.get(packageId);
                if (productPackage != null) {
                    Integer unitType = productPackage.getUnitType();
                    if (unitType != 0) {
                        Double labelNumber = Math.ceil(Integer.parseInt(opArr[1]) / unitType);
                        ordersProduct.setLabelNumber(Integer.valueOf(labelNumber.intValue()));
                    }
                }

                //重新计算立减的优惠金额  add by Ls 2016/09/13
                //修改过单价后不再单品立减  add by Ls 2016/11/15
                if (!StringUtil.isNullOrZero(ordersProduct.getActSingleId())) {
                    BigDecimal scle = ordersProduct.getMoneyActSingle().divide(new BigDecimal(ordersProduct.getNumber())).setScale(4, BigDecimal.ROUND_HALF_UP);
                    if(!opArr[4].equals(ordersProduct.getMoneyPrice())){
                        op_reduceAmount = scle.multiply(proNumber);
                        reduceAmount = reduceAmount.add(op_reduceAmount);
                        //数量变化后单品立减的金额也会变化，重新覆盖
                        ordersProduct.setMoneyActSingle(op_reduceAmount);
                    }else{
                        ordersProduct.setActSingleId(0);
                        ordersProduct.setMoneyActSingle(new BigDecimal(0.00));
                    }
                }
            }

            //套餐单价
            if (null != opArr[2] && !"".equals(opArr[2])) {
                packPrice = packPrice.add(new BigDecimal(opArr[2]));
                //设置套餐单价
                ordersProduct.setPackageFee(packPrice);
                //套餐总价＝ 套餐单价*数量
                //即加工费
                ordersProduct.setPackageFeeTotal(packPrice.multiply(proNumber));
            }

            //标签单价
            if (null != opArr[3] && !"".equals(opArr[3])) {
                labelprice = labelprice.add(new BigDecimal(opArr[3]));
                //设置标签价格
                ordersProduct.setLabelFee(labelprice);
                //标签总价 ＝ 标签单价*数量(盒子数量)即辅料费
                ordersProduct.setLabelFeeTotal(labelprice.multiply(new BigDecimal(
                    ordersProduct.getLabelNumber() == null ? 0 : ordersProduct.getLabelNumber())));
            }

            //商品单价
            if (null != opArr[4] && !"".equals(opArr[4])) {
                //商品单价
                BigDecimal moneyPrice = new BigDecimal(opArr[4]);
                //如果单品立减修改价格则不再单品立减按修改后的价格计算，抹掉折扣和活动信息
                if (!StringUtil.isNullOrZero(ordersProduct.getActSingleId()) && !moneyPrice.equals(ordersProduct.getMoneyPrice())) {
                    ordersProduct.setMoneyActSingle(new BigDecimal(0.00));
                    ordersProduct.setActSingleId(0);
                }
                //更新此网单商品价格
                ordersProduct.setMoneyPrice(moneyPrice);
                //商品总金额 ＝ 商品单价*商品数量
                productAmount = productAmount.add(moneyPrice.multiply(proNumber));

            }
            //网单价格 ＝ 商品价格总和+标签价格和+套餐价格和
            opprice = productAmount.add(ordersProduct.getLabelFeeTotal()).add(ordersProduct.getPackageFeeTotal());

            //还原库存及销量：库存增加，销量减少
            //更改的数量 页面控制保证商品数量不会增加
            Integer changeNum = ordersProduct.getNumber() - proNumber.intValue();
            log.debug("更改数量：" + changeNum);

            //还原操作：库存增加，销量减少
            changeNum = -1 * changeNum;
            //理论上设置订单价格 修改订单商品数量 只会改小 不能改大 因此 在此时changeNum 一定< 0
            //if (changeNum > 0) {
                //还原货品销量
                productGoodsWriteDao.updateActualSales(ordersProduct.getProductGoodsId(),changeNum);
                //还原货品库存 B哥让注释的 2016.12.30
               // productGoodsWriteDao.updateStock(ordersProduct.getProductGoodsId(), changeNum);

                //还原商品销量
                productWriteDao.updateActualSales(ordersProduct.getProductId(), changeNum);
                //还原商品库存  B哥让注释的 2016.12.30
                //productWriteDao.updateStock(ordersProduct.getProductId(), changeNum);
            //}

            //商品数量
            ordersProduct.setNumber(proNumber.intValue());
            //累计纯粹的商品总金额
            money_product = money_product.add(new BigDecimal(ordersProduct.getNumber()).multiply(ordersProduct.getMoneyPrice()));
            //设置网单价格
            ordersProduct.setMoneyAmount(opprice);
            String ordersSn = ordersProduct.getOrdersSn();
            char firstWords = checkOrdersFirstWords(ordersSn);
            String fWords = firstWords + "";
            if (orders.getOrderState() == Orders.ORDER_STATE_1) {
                //terry 20161021 如果未付款订单修改订单，则需要财务进行审核
                orders.setPaymentStatus(Orders.PAYMENT_STATUS_3);
                fWords = fWords.toLowerCase();
            }
            newOrdersSn = fWords + ordersSn.substring(1, ordersSn.length());
            //            if ("newOrders".equals(submitType)) {
            ordersProduct.setOrdersSn(newOrdersSn);
            //            }
            ordersProductWriteDao.update(ordersProduct);

            packnum = packnum.add(ordersProduct.getPackageFeeTotal());
            labelsum = labelsum.add(ordersProduct.getLabelFeeTotal());

            //总金额 ＝ 所有网单金额和
            sum = sum.add(opprice);
        }

        //        
        if (orders != null) {
            OrdersError ordersError = new OrdersError();
            ordersError = this.getOrdersError(orders, newOrdersSn);
            ordersErrorWriteDao.insert(ordersError);
            //              if ("newOrders".equals(submitType)) {
            //重新生成新的订单号
            orders.setOrderSn(newOrdersSn);
            if (orders.getOrderState() == Orders.ORDER_STATE_4)
                orders.setOrderState(3);
            orders.setSyncState("未推送");
            //              }
        }

        //总金额＝所有网单金额和 - 优惠金额
        //        orders.setMoneyProduct(sum.add(orders.getMoneyLogistics())); //Terry add 20160802
        //商品金额 = 所有网单纯粹的商品金额和
        orders.setMoneyProduct(money_product);
        //重新计算订单满减的金额
        if (!StringUtil.isNullOrZero(orders.getActFullId())) {
            ActFull actFull_vo = new ActFull();
            actFull_vo = actFullReadDao.get(orders.getActFullId());
            //满减第一等级
            if (actFull_vo.getFirstFull() != null && actFull_vo.getFirstFull().compareTo(BigDecimal.ZERO) > 0 && sum.compareTo(actFull_vo.getFirstFull()) >= 0) {
                orders.setMoneyActFull(actFull_vo.getFirstDiscount());
            }
            if (actFull_vo.getSecondFull() != null && actFull_vo.getSecondFull().compareTo(BigDecimal.ZERO) > 0 && sum.compareTo(actFull_vo.getSecondFull()) >= 0) {
                orders.setMoneyActFull(actFull_vo.getSecondDiscount());
            }
            if (actFull_vo.getThirdFull() != null && actFull_vo.getThirdFull().compareTo(BigDecimal.ZERO) > 0 && sum.compareTo(actFull_vo.getThirdFull()) >= 0) {
                orders.setMoneyActFull(actFull_vo.getThirdDiscount());
            }
        }
        orders.setMoneyDiscount(reduceAmount.add(orders.getMoneyActFull()));
        orders.setMoneyOrder(sum.subtract(orders.getMoneyDiscount()).add(orders.getMoneyLogistics()));
        orders.setServicePrice(packnum);
        orders.setLabelPrice(labelsum);
        orders.setUpdateTime(new Date());
        ordersWriteDao.update(orders);

        //数量更新完后，重新计算订单的邮费 add by Ls 2016/09/13
        //=========================begin===========================
        //进行关联订单合并计算(除去送货上门的订单)
        double total_weight = 0.00;
        BigDecimal calculateTransFee = BigDecimal.ZERO;
        //判断该订单中数量是否为0,避免订单累加而产生的的精度问题
        Integer ordersNum = 0;
        ordersNum = ordersProductWriteDao.getOrdersNumByOrdersSn(orders.getOrderSn());
        if(ordersNum > 0){
            if (orders.getTradeNo() != null && !"".equals(orders.getTradeNo())
                && orders.getLogisticsId() != 5) {
                List<Orders> ordersList = new ArrayList<Orders>();
                BigDecimal reduceLogistisFee = BigDecimal.ZERO;
                //获取此订单所有的关联拆单订单
                ordersList = ordersWriteDao.getOrderList(orders.getTradeNo());
                if (ordersList.size() > 0) {
                  //获得所有关联订单的净重，准备计算邮费传递参数
                    total_weight = ordersWriteDao.getWeightAddLabelNumTotal(orders.getTradeNo());
                    for (int i = 0; i < ordersList.size(); i++) {
                        //除去修改订单的其余同一购物车下拆单的邮费和
                        if (!orders.getOrderSn().equals(ordersList.get(i).getOrderSn())) {
                            reduceLogistisFee = reduceLogistisFee.add(ordersList.get(i).getMoneyLogistics());
                        }
                    }
                } else {
                    //若orders_trade_serial没有数据，表明为旧数据，没有存储到orders_trade_serial表，需要单独计算；
                    total_weight = ordersWriteDao.getWeightAddLabelNum(orders.getOrderSn());
                    calculateTransFee = sellerTransportModel.calculateTransFee(1, total_weight, orders.getCityId(), orders.getLogisticsId());
                }
                //重新计算本单和关联拆单的总计邮费
                calculateTransFee = sellerTransportModel.calculateTransFee(1, total_weight, orders.getCityId(), orders.getLogisticsId());
                calculateTransFee = calculateTransFee.subtract(reduceLogistisFee);
                //判断拆单后的邮费是否为0，小于0取0存储
                if (calculateTransFee.compareTo(BigDecimal.ZERO) < 0) {
                    calculateTransFee = BigDecimal.ZERO;
                }
            } else {//否则该订单为独单，不需要进行拆单处理，直接计算邮费
                total_weight = ordersWriteDao.getWeightByOrdersSn(orders.getOrderSn());
                calculateTransFee = sellerTransportModel.calculateTransFee(1, total_weight, orders.getCityId(), orders.getLogisticsId());
            }
        }
        orders.setMoneyLogistics(calculateTransFee.setScale(2, BigDecimal.ROUND_HALF_UP));
        //重新赋值订单金额
        orders.setMoneyOrder(sum.subtract(orders.getMoneyDiscount()).add(orders.getMoneyLogistics()));
        ordersWriteDao.update(orders);
        //===========================end=============================
    }

    //封装ordersError对象
    private OrdersError getOrdersError(Orders orders, String newOrdersSn) {
        OrdersError ordersError = new OrdersError();
        ordersError.setActFullId(orders.getActFullId());
        ordersError.setActivityId(orders.getActivityId());
        ordersError.setAddressAll(orders.getAddressAll());
        ordersError.setAddressInfo(orders.getAddressInfo());
        ordersError.setAreaId(orders.getAreaId());
        ordersError.setCityId(orders.getCityId());
        ordersError.setCodconfirmId(orders.getCodconfirmId());
        ordersError.setCodconfirmName(orders.getCodconfirmName());
        ordersError.setCodconfirmRemark(orders.getCodconfirmRemark());
        ordersError.setCodconfirmState(orders.getCodconfirmState());
        ordersError.setCodconfirmTime(orders.getCodconfirmTime());
        ordersError.setCouponUserId(orders.getCouponUserId());
        ordersError.setCreateTime(orders.getCreateTime());
        ordersError.setDeliveredSum(orders.getDeliveredSum());
        ordersError.setDeliverTime(orders.getDeliverTime());
        ordersError.setEmail(orders.getEmail());
        ordersError.setFinishTime(orders.getFinishTime());
        ordersError.setIntegral(orders.getIntegral());
        ordersError.setInvoiceStatus(orders.getInvoiceStatus());
        ordersError.setInvoiceTitle(orders.getInvoiceTitle());
        ordersError.setInvoiceType(orders.getInvoiceType());
        ordersError.setIp(orders.getIp());
        ordersError.setIsCodconfim(orders.getIsCodconfim());
        ordersError.setLabelPrice(orders.getLabelPrice());
        ordersError.setLogisticsId(orders.getLogisticsId());
        ordersError.setLogisticsName(orders.getLogisticsName());
        ordersError.setLogisticsNumber(orders.getLogisticsNumber());
        ordersError.setMemberId(orders.getMemberId());
        ordersError.setMemberName(orders.getMemberName());
        ordersError.setMobile(orders.getMobile());
        ordersError.setMoneyActFull(orders.getMoneyActFull());
        ordersError.setMoneyBack(orders.getMoneyBack());
        ordersError.setMoneyCoupon(orders.getMoneyCoupon());
        ordersError.setMoneyDiscount(orders.getMoneyDiscount());
        ordersError.setMoneyIntegral(orders.getMoneyIntegral());
        ordersError.setMoneyLogistics(orders.getMoneyLogistics());
        ordersError.setMoneyOrder(orders.getMoneyOrder());
        ordersError.setMoneyPaidBalance(orders.getMoneyPaidBalance());
        ordersError.setMoneyPaidReality(orders.getMoneyPaidReality());
        ordersError.setMoneyProduct(orders.getMoneyProduct());
        ordersError.setName(orders.getName());
        ordersError.setOrderSn(orders.getOrderSn());
        ordersError.setOrderState(orders.getOrderState());
        ordersError.setOrderType(orders.getOrderType());
        ordersError.setPaymentCode(orders.getPaymentCode());
        ordersError.setPaymentName(orders.getPaymentName());
        ordersError.setPaymentStatus(orders.getPaymentStatus());
        ordersError.setPayTime(orders.getPayTime());
        ordersError.setProvinceId(orders.getProvinceId());
        ordersError.setRelationOrderSn(newOrdersSn);
        ordersError.setRemark(orders.getRemark());
        ordersError.setSellerId(orders.getSellerId());
        ordersError.setServicePrice(orders.getServicePrice());
        ordersError.setSource(orders.getSource());
        ordersError.setTradeNo(orders.getTradeNo());
        ordersError.setTradeSn(orders.getTradeSn());
        ordersError.setUpdateTime(orders.getUpdateTime());
        ordersError.setZipCode(orders.getZipCode());
        return ordersError;
    }

    //封装ordersProductError对象
    private OrdersProductError getOrdersProductError(OrdersProduct ordersProduct) {
        OrdersProductError ordersProductError = new OrdersProductError();
        ordersProductError.setActBiddingId(ordersProduct.getActBiddingId());
        ordersProductError.setActFlashSaleId(ordersProduct.getActFlashSaleId());
        ordersProductError.setActFlashSaleProductId(ordersProduct.getActFlashSaleProductId());
        ordersProductError.setActGroupId(ordersProduct.getActGroupId());
        ordersProductError.setActivityId(ordersProduct.getActivityId());
        ordersProductError.setActSingleId(ordersProduct.getActSingleId());
        ordersProductError.setCloseTime(ordersProduct.getCloseTime());
        ordersProductError.setCreateTime(ordersProduct.getCreateTime());
        ordersProductError.setDeliveredNum(ordersProduct.getDeliveredNum());
        ordersProductError.setGiftId(ordersProduct.getGiftId());
        ordersProductError.setIsGift(ordersProduct.getIsGift());
        ordersProductError.setIsSelfLabel(ordersProduct.getIsSelfLabel());
        ordersProductError.setLabelFee(ordersProduct.getLabelFee());
        ordersProductError.setLabelFeeTotal(ordersProduct.getLabelFeeTotal());
        ordersProductError.setLogisticsId(ordersProduct.getLogisticsId());
        ordersProductError.setLogisticsName(ordersProduct.getLogisticsName());
        ordersProductError.setLogisticsNumber(ordersProduct.getLogisticsNumber());
        ordersProductError.setMallGroupsId(ordersProduct.getMallGroupsId());
        ordersProductError.setMemberProductBackId(ordersProduct.getMemberProductBackId());
        ordersProductError.setMemberProductExchangeId(ordersProduct.getMemberProductExchangeId());
        ordersProductError.setMoneyActSingle(ordersProduct.getMoneyActSingle());
        ordersProductError.setMoneyAmount(ordersProduct.getMoneyAmount());
        ordersProductError.setMoneyPrice(ordersProduct.getMoneyPrice());
        ordersProductError.setNumber(ordersProduct.getNumber());
        ordersProductError.setOrdersId(ordersProduct.getOrdersId());
        ordersProductError.setOrdersSn(ordersProduct.getOrdersSn());
        ordersProductError.setPackageFee(ordersProduct.getPackageFee());
        ordersProductError.setPackageFeeTotal(ordersProduct.getPackageFeeTotal());
        ordersProductError.setPackageGroupsId(ordersProduct.getPackageGroupsId());
        ordersProductError.setProductCateId(ordersProduct.getProductCateId());
        ordersProductError.setProductGoodsId(ordersProduct.getProductGoodsId());
        ordersProductError.setProductId(ordersProduct.getProductId());
        ordersProductError.setProductLabelId(ordersProduct.getProductLabelId());
        ordersProductError.setProductName(ordersProduct.getProductName());
        ordersProductError.setProductPackageId(ordersProduct.getProductPackageId());
        ordersProductError.setProductSku(ordersProduct.getProductSku());
        ordersProductError.setSellerId(ordersProduct.getSellerId());
        ordersProductError.setShippingTime(ordersProduct.getShippingTime());
        ordersProductError.setSpecInfo(ordersProduct.getSpecInfo());
        ordersProductError.setSystemRemark(ordersProduct.getSystemRemark());
        ordersProductError.setUpdateTime(ordersProduct.getUpdateTime());
        return ordersProductError;
    }

    //顺序生成26位大写字母
    public static char checkOrdersFirstWords(String ordersSn) {
        char newWords = '1';
        String firstWords = ordersSn.substring(0, 1);
        try {
            firstWords = firstWords.toUpperCase();
        } catch (Exception e) {
            return 'A';
        }
        for (char M = 'A'; M <= 'Z'; M++) {
            newWords = M;
            //取订单号的第一位
            if (firstWords.equals(newWords)) {
                continue;
            } else {
                if (newWords > firstWords.charAt(0)) {
                    break;
                } else {
                    continue;
                }
            }
        }
        return newWords;
    }

    public Boolean saveOrdersProductLabel(Integer orderId, String opinfo) {
        //所有网单总价
        BigDecimal sum = BigDecimal.ZERO;
        //套餐总价
        BigDecimal packnum = BigDecimal.ZERO;
        //标签总价
        BigDecimal labelsum = BigDecimal.ZERO;

        String[] opinfos = opinfo.split("!@\\|@!");
        for (String str : opinfos) {
            String[] opArr = str.split(",");
            if (opArr.length != 2) {
                throw new BusinessException("数据格式错误");
            }
            OrdersProduct ordersProduct = ordersProductReadDao.get(Integer.valueOf(opArr[0]));
            if (null != opArr[1] && !"".equals(opArr[1])) {
                ordersProduct.setProductLabelId(Integer.valueOf(opArr[1]));
                ProductLabel label = productLabelReadDao.get(ordersProduct.getProductLabelId());
                if (label != null) {
                    if (label.getMarketPrice() == null)
                        label.setMarketPrice(BigDecimal.ZERO);
                    ordersProduct.setLabelFee(label.getMarketPrice());

                    Integer packageId = ordersProduct.getProductPackageId();
                    ProductPackage productPackage = productPackageReadDao.get(packageId);
                    if (productPackage != null) {
                        Integer unitType = productPackage.getUnitType();
                        Double nowNumber = Math.ceil(ordersProduct.getNumber() / unitType);
                        ordersProduct.setLabelNumber(nowNumber.intValue());
                    }

                    //标签费：当用户选择为平台标时，需要记录选择的平台标的市场价
                    //                    if (label.getMemberId().intValue() == 0) {
                    //总价 = 市场价*数量
                    ordersProduct.setLabelFeeTotal(label.getMarketPrice().multiply(new BigDecimal(ordersProduct.getLabelNumber() == null ? 0 : ordersProduct.getLabelNumber())));

                    //网单价格 ＝ 商品价格总和+标签价格和+套餐价格和 - 优惠总金额
                    BigDecimal productAmount = ordersProduct.getMoneyPrice().multiply(new BigDecimal(ordersProduct.getNumber()));
                    BigDecimal opprice = productAmount.add(ordersProduct.getLabelFeeTotal()).add(ordersProduct.getPackageFeeTotal()).subtract(ordersProduct.getMoneyActSingle());
                    ordersProduct.setMoneyAmount(opprice);
                    //                    }
                    ordersProductWriteDao.update(ordersProduct);
                }
            }
            //重新设置价格
            //总套餐费
            packnum = packnum.add(ordersProduct.getPackageFeeTotal() == null ? BigDecimal.ZERO : ordersProduct.getPackageFeeTotal());
            //总标签费
            labelsum = labelsum.add(ordersProduct.getLabelFeeTotal() == null ? BigDecimal.ZERO : ordersProduct.getLabelFeeTotal());

            //总金额 ＝ 所有网单金额和+总套餐费+总标签费- 优惠总金额
            sum = sum.add(ordersProduct.getMoneyAmount());//.add(packnum).add(labelsum);//.subtract(ordersProduct.getMoneyActSingle());
        }

        //重新计算订单的价格，或者支付状态为，二次加工未付款
        Orders orders = ordersReadDao.get(orderId);
        if (orders.getOrderState() != Orders.ORDER_STATE_1) {
            throw new BusinessException("只有未付款的订单可以修改价格");
        }

        //总金额＝所有网单金额和 - 优惠金额
        //Terry remark 20160802
        //        orders.setMoneyOrder(sum.subtract(orders.getMoneyDiscount()));
        //      Terry add 20160802
        orders.setMoneyOrder(sum.add(orders.getMoneyLogistics()));
        orders.setServicePrice(packnum);
        orders.setLabelPrice(labelsum);

        //add by Lushuai 2016/12/16
        //将标签重量计入邮费的计算中        
        //-------------------begin--------------------
        List<Orders> orderlist = ordersReadDao.getOrdersByTradeNo(orders.getTradeNo());
        //没有关联订单，确定该订单为独单，不需要进行拆单计算
        double onlyWeight = ordersWriteDao.getWeightAddLabelNum(orders.getOrderSn());
        if(orderlist.size()==1){
            BigDecimal fee = sellerTransportModel.calculateTransFee(1,onlyWeight, orders.getCityId(),orders.getLogisticsId());
            orders.setMoneyOrder(orders.getMoneyOrder().subtract(orders.getMoneyLogistics()).add(fee).setScale(2, BigDecimal.ROUND_HALF_UP));
            orders.setMoneyLogistics(fee);
        }else{
            //关联订单一并进行拆单计算
            BigDecimal otherWeightFee = BigDecimal.ZERO;
            double totalWeight = ordersWriteDao.getWeightAddLabelNumTotal(orders.getTradeNo());
            BigDecimal totalFee = sellerTransportModel.calculateTransFee(1,totalWeight, orders.getCityId(),orders.getLogisticsId());
            for(Orders order : orderlist){
                if(!order.getOrderSn().equals(orders.getOrderSn())){
                    otherWeightFee = otherWeightFee.add(order.getMoneyLogistics());
                }
                BigDecimal tLogisticFee = totalFee.subtract(otherWeightFee);
                orders.setMoneyOrder(orders.getMoneyOrder().subtract(orders.getMoneyLogistics()).add(tLogisticFee).setScale(2, BigDecimal.ROUND_HALF_UP));
                orders.setMoneyLogistics(tLogisticFee);
            }
        }
        //--------------------end---------------------
        
        orders.setUpdateTime(new Date());
        ordersWriteDao.update(orders);
        return true;
    }

    public BookingSendGoodsVO listUserInfo(Integer orderId) {
        //先查询是否有默认地址
        BookingSendGoodsVO bookingSendGoodsVO = ordersReadDao.listUserInfo(orderId);
        if (bookingSendGoodsVO == null) {
            bookingSendGoodsVO = ordersReadDao.listUserInfoLimit1(orderId);
        }
        return bookingSendGoodsVO;
    }

    public List<SendingGoodsVO> listGoodsInfo(Integer ordersId) {
        return ordersReadDao.listGoodsInfo(ordersId);
    }

    /**
     * 三方仓储未发货退货
     * @param id
     * @return
     */
    public Boolean returninstore(Integer id, String checkMan) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (id != null) {
                Orders orders = ordersReadDao.get(id);
                //获取该三方仓储订单已发货的总数量
                Integer sum = orders.getDeliveredSum();
                //接下来去ordersProduct中统计该三方仓储订单总下单数量
                List<OrdersProduct> ordersProductList = ordersProductReadDao.getByOrderId(id);
                int number = 0;
                Integer productId = null;
                for (OrdersProduct ordersProduct : ordersProductList) {
                    number = number + ordersProduct.getNumber();
                    productId = ordersProduct.getProductId();
                }
                //得到要返回库存未发货的数量
                int backNum = 0;
                if (number > 0) {
                    backNum = number - sum;
                }
                if (productId != null && backNum > 0) {
                    Product product = productReadDao.get(productId);
                    //返还库存
                    product.setProductStock((product.getProductStock() + backNum));
                    productWriteDao.update(product);
                }

                BackGoods backGoods = new BackGoods();
                backGoods.setBackMan(orders.getName());
                backGoods.setBackMemberId(orders.getMemberId());
                backGoods.setBackNum(backNum);
                backGoods.setBadNum(0);
                backGoods.setCheckTime(new Date());
                backGoods.setCreateTime(new Date());
                backGoods.setEmail(orders.getEmail());
                backGoods.setMobile(orders.getMobile());
                backGoods.setOrdersId(id);
                backGoods.setOrdersSn(orders.getOrderSn());
                backGoods.setWellNum(backNum);
                backGoods.setUpdateTime(new Date());
                backGoods.setStatus(2);
                backGoods.setCheckMan(checkMan);
                backGoodsWriteDao.insert(backGoods);

                //接下来单独返回某一个productGoodsId 的库存
                for (OrdersProduct ordersProduct : ordersProductList) {
                    //每个sku 已发货的数量
                    Integer deliveredNum = ordersProduct.getDeliveredNum();
                    Integer bookNumber = ordersProduct.getNumber();
                    //要返回的数量
                    Integer willBackNum = bookNumber - deliveredNum;
                    if (willBackNum > 0) {
                        ProductGoods productGoods = productGoodsReadDao.get(ordersProduct.getProductGoodsId());
                        productGoods.setProductStock((productGoods.getProductStock() + willBackNum));
                        productGoodsWriteDao.update(productGoods);
                    }
                    BackGoodsRecord backGoodsRecord = new BackGoodsRecord();
                    backGoodsRecord.setBackGoodsId(backGoods.getId());
                    backGoodsRecord.setBackNum(willBackNum);
                    backGoodsRecord.setBadNum(0);
                    backGoodsRecord.setProductId(productId);
                    backGoodsRecord.setWellNum(willBackNum);
                    backGoodsRecord.setProductSku(ordersProduct.getProductSku());
                    backGoodsRecordWriteDao.insert(backGoodsRecord);
                }
                orders.setOrderState(6);
                ordersWriteDao.update(orders);
            } else {
                throw new BusinessException("参数错误");
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
        return true;
    }

    public Boolean returngoods(Integer id, SystemAdmin systemAdmin) throws Exception {
        // 事务管理   Terry 20160825
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (id != null) {
//                Orders orders = ordersWriteDao.get(id);
                List<Orders> ordersList = ordersReadDao.getAllRelationOrdersBgId(id);
                for(Orders orders:ordersList){
                
                //Terry 20160825 start
                if (orders.getOrderState().equals(Orders.ORDER_STATE_6)) {
                    throw new BusinessException("已取消订单，不能再次取消！订单号：" + orders.getOrderSn());
                }
                //Terry 20160825 end

                List<OrdersProduct> ordersProductList = ordersProductReadDao.getByOrderId(id);
                int productId = 0;
                int bookNum = 0;
                for (OrdersProduct ordersProduct : ordersProductList) {
                    if (ordersProduct != null) {
                        Integer nowNumber = ordersProduct.getNumber();
                        bookNum = bookNum + nowNumber;
                    }
                }
                BackGoods backGoods = new BackGoods();
                backGoods.setBackMan(orders.getName());
                backGoods.setBackMemberId(orders.getMemberId());
                backGoods.setBackNum(bookNum);
                backGoods.setBadNum(0);
                backGoods.setCheckTime(new Date());
                backGoods.setCreateTime(new Date());
                backGoods.setEmail(orders.getEmail());

                if (!StringUtil.isEmpty(orders.getMobile()) && !"".equals(orders.getMobile())) {
                    backGoods.setMobile(orders.getMobile());
                } else {
                    backGoods.setMobile(orders.getMemberName());
                }
                backGoods.setOrdersId(id);
                backGoods.setOrdersSn(orders.getOrderSn());
                backGoods.setWellNum(bookNum);
                backGoods.setUpdateTime(new Date());
                backGoods.setStatus(2);

                //Terry 20160918 拆解checkMan为 checkMan 和 returnStock
                String checkMan = systemAdmin.getName();
                String[] cm = checkMan.split("&_");
                checkMan = cm[0];
                systemAdmin.setName(checkMan);
                backGoods.setCheckMan(checkMan);
                backGoodsWriteDao.insert(backGoods);

                String returnStock = "";
                try {
                    returnStock = cm[1];
                } catch (Exception e) {
                }

                if ("".equals(returnStock)) {
                    for (OrdersProduct ordersProduct : ordersProductList) {
                        productId = ordersProduct.getProductId();
                        Integer productGoodsId = ordersProduct.getProductGoodsId();
                        ProductGoods productGoods = productGoodsReadDao.get(productGoodsId);
                       productGoods.setProductStock(
                           (productGoods.getProductStock() + ordersProduct.getNumber()));
                        
                        
                        //将订单中的销量从实际销量中减去  add by Ls 2016-08-23
                        if (productGoods.getActualSales() > ordersProduct.getNumber()) {
                            productGoods.setActualSales(
                                productGoods.getActualSales() - ordersProduct.getNumber());
                        } else {
                            productGoods.setActualSales(0);
                        }
                        productGoodsWriteDao.update(productGoods);

                        BackGoodsRecord backGoodsRecord = new BackGoodsRecord();
                        backGoodsRecord.setBackGoodsId(backGoods.getId());
                        backGoodsRecord.setBackNum(ordersProduct.getNumber());
                        backGoodsRecord.setBadNum(0);
                        backGoodsRecord.setProductId(productId);
                        backGoodsRecord.setWellNum(ordersProduct.getNumber());
                        backGoodsRecord.setProductSku(ordersProduct.getProductSku());
                        backGoodsRecordWriteDao.insert(backGoodsRecord);

                    }
                    if (productId != 0) {
                        Product product = productReadDao.get(productId);
                        if (product != null) {

                        } else {
                            throw new BusinessException("数据有误");
                        }
                        product.setProductStock((product.getProductStock() + bookNum));
                        if (product.getActualSales() > bookNum) {
                            product.setActualSales(product.getActualSales() - bookNum);
                        } else {
                            product.setActualSales(0);
                        }
                        productWriteDao.update(product);
                    } else {
                        throw new BusinessException("数据有误");
                    }
                }

                orders.setOrderState(6);

                try {
                    String reason = cm[2];
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    orders.setRemark(orders.getRemark() + " \n" + df.format(date) + ": " + reason);
                } catch (Exception e) {
                }
                ordersWriteDao.update(orders);

                //2.写订单日志 Terry  20160922
                writeOrderInfo(orders, systemAdmin, 6);
            }
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
        return true;
    }

    public BigDecimal returnTransFee(String productGoodsIds, String numstrs, String transportType,
                                     String memberAddress_id) {
        String[] productGoodsIds_arr = productGoodsIds.split(",");
        String[] numbers_arr = numstrs.split(",");
        MemberAddress memberAddress = memberAddressReadDao.get(Integer.valueOf(memberAddress_id));
        BigDecimal logisticsFeeAmount = BigDecimal.ZERO;
        int seller_id = 0;
        int num = 0;
        Product product = new Product();
        ProductAttr productAttr = new ProductAttr();
        for (int i = 0; i < productGoodsIds_arr.length; i++) {
            if (!productGoodsIds_arr[i].equals("") && !numbers_arr[i].equals("0")
                && !numbers_arr[i].equals("")) {
                num = Integer.parseInt(numbers_arr[i]);
                product = productReadDao
                    .getProductByProductsId(Integer.valueOf(productGoodsIds_arr[i]));
                seller_id = product.getSellerId();//商家ID
                productAttr = productAttrReadDao.getWeight(product.getId(), "克重（净重）");
                String str = productAttr.getValue();
                double product_weight = 45.00;
                if (str != null && !"".equals(str) && !"/".equals(str)) {
                    product_weight = Double.valueOf(str) * 1.2;
                }
                logisticsFeeAmount = logisticsFeeAmount
                    .add(sellerTransportModel.calculateTransFee(seller_id, product_weight * num,
                        memberAddress.getCityId(), Integer.valueOf(transportType)));
            }
        }
        return logisticsFeeAmount;
    }

    public Integer getOrdersByMemberId(Integer memberId) {
        return ordersReadDao.getCountByMemberId(memberId);
    }

    
    

    //获得当前时间
    public static String getCurrentTime() {
        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = formatter.format(currentTime);

        return dateString;

    }

    //时间比较大小

    public static int timeCompare(String t1, String t2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(formatter.parse(t1));
            c2.setTime(formatter.parse(t2));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = c1.compareTo(c2);

        return result;
    }

    /**
    * 用户领取优惠"
    * @param couponId 优惠券ID
    * @param memberId 用户ID
    * @return
    */
    public boolean receiveCoupon(Integer couponId, Integer memberId) {
        Coupon coupon = couponReadDao.get(couponId);
        if (coupon == null) {
            throw new BusinessException("优惠券信息取得失败");
        }
        if (coupon.getType().intValue() != Coupon.TYPE_1) {
            throw new BusinessException("该优惠券不能直接领取");
        }
        if (coupon.getStatus().intValue() != Coupon.STATUS_5) {
            throw new BusinessException("该优惠券还没有上架");
        }
        if (coupon.getSendStartTime().after(new Date())) {
            throw new BusinessException("优惠券还没有到领取时间");
        }
        if (coupon.getSendEndTime().before(new Date())) {
            throw new BusinessException("优惠券已经过了领取时间");
        }
        // 默认一次领""
        if ((coupon.getReceivedNum() + 1) > coupon.getTotalLimitNum()) {
            throw new BusinessException("优惠券已经被领完了");
        }
        // 读库取数据，避免数据库同步的时间"
        Integer receivedNum = couponUserReadDao.getNumByMemberIdAndCouponId(memberId, couponId);
        if (coupon.getPersonLimitNum() > 0 && receivedNum != null
            && receivedNum >= coupon.getPersonLimitNum()) {
            throw new BusinessException("您已经领取过该优惠券了");
        }
        Member member = memberReadDao.get(memberId);
        if (member == null) {
            throw new BusinessException("用户信息获取失败，请稍后再试");
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            CouponUser couponUser = new CouponUser();

            couponUser.setMemberId(memberId);
            couponUser.setSellerId(coupon.getSellerId());
            couponUser.setCouponId(coupon.getId());
            couponUser.setCouponSn(coupon.getPrefix() + "-" + EjavashopSequence.getKey());
            // 在线领取默认密码123456（在线领取的密码不会用到"
            couponUser.setPassword(Md5.getMd5String("123456"));
            // 默认使用一"
            couponUser.setCanUse(1);
            couponUser.setReceiveTime(new Date());
            couponUser.setOrderId(0);
            couponUser.setUseTime(null);
            couponUser.setUseStartTime(coupon.getUseStartTime());
            couponUser.setUseEndTime(coupon.getUseEndTime());
            couponUser.setCreateUserId(member.getId());
            couponUser.setCreateUserName(member.getName());
            couponUser.setCreateTime(new Date());
            couponUser.setUpdateUserId(member.getId());
            couponUser.setUpdateUserName(member.getName());
            couponUser.setUpdateTime(new Date());

            Integer count = couponWriteDao.updateReceivedNum(couponId, 1);
            if (count != 1) {
                log.error("修改优惠券领取数量时失败：memberId=" + memberId + ",couponId=" + couponId);
                throw new BusinessException("优惠券领取失败，请稍后再试");
            }

            count = couponUserWriteDao.insert(couponUser);

            promotionTransactionManager.commit(status);
            return count > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("领取优惠券时出现未知异常", e);
            throw e;
        }

    }
    
    public List<SumParterSaleVO>getNewSumParterSales(Integer memberId,Integer year,String signNo){
    	List<SumParterSaleVO> list = ordersReadDao.getSumParterSales(memberId,year,signNo);
    	return list;
    }
    
    public List<SumParterSaleVO>getNewSumParterSalesYears(Integer memberId,Integer type,Integer start,Integer size,String signNo){
    	List<SumParterSaleVO> list = ordersReadDao.getNewSumParterSalesYears(memberId,type,start,size,signNo);
    	if(type.intValue() == 2){
    		SumParterSaleVO sumParterSaleVO = new SumParterSaleVO();
        	Integer bussnessNo = 0;
        	Integer bussnessNoNew = 0 ;
        	BigDecimal sales = new BigDecimal(0);
        	BigDecimal salesNew = new BigDecimal(0);
        	for (SumParterSaleVO sumParterSaleVO2 : list) {
        		bussnessNo += sumParterSaleVO2.getBussineeNo();
        		bussnessNoNew += sumParterSaleVO2.getNewBussineeNo();
        		sales =sales.add(sumParterSaleVO2.getSaleMoney()) ;
        		salesNew =salesNew.add(sumParterSaleVO2.getNewSaleMoney()) ;
    		}
        	sumParterSaleVO.setBussineeNo(bussnessNo);
        	sumParterSaleVO.setNewBussineeNo(bussnessNoNew);
        	sumParterSaleVO.setYears("合计");
        	sumParterSaleVO.setSaleMoney(sales);
        	sumParterSaleVO.setNewSaleMoney(salesNew);
        	list.add(sumParterSaleVO);
    	}
    	return list;
    }
    
    public Integer getSalesCount2(Integer memberId,String signNo){
    	return  ordersReadDao.getSalesCount2(memberId,signNo);
    }
    
    public Integer getSalesPersonCount(Integer memberId,String startTime,String endTime,String signNo,String areaId){
    	return  ordersReadDao.getSalesPersonCount(memberId,startTime,endTime,signNo,areaId);
    }
    
    public List<SalesPersonVO>getSalesPerson(Integer memberId,String startTime,String endTime,Integer start,Integer size,
    		String signNo,String areaId){
    	return ordersReadDao.getSalesPerson(memberId, startTime, endTime, start, size,signNo,areaId);
    }
    
    public List<CategorySalesVO>getCategorySales(Integer memberId,String startTime,String endTime,String signNo){
    	List<CategorySalesVO> list = ordersReadDao.getCategorySales(memberId, startTime, endTime,signNo);
    	CategorySalesVO categorySalesVO = new CategorySalesVO();
    	categorySalesVO.setBrandName("合计");
    	Integer no = 0;
    	BigDecimal sales = new BigDecimal(0);
    	for (CategorySalesVO categorySalesVO1 : list) {
    		no = categorySalesVO1.getSkuNo()-categorySalesVO1.getReturnSkuNo();
    		sales = categorySalesVO1.getSalesNo().subtract(categorySalesVO1.getReturnSalesNo());
    		categorySalesVO1.setNo(no);
    		categorySalesVO1.setSales(sales);
    		categorySalesVO.setMemberNo(categorySalesVO.getMemberNo()+categorySalesVO1.getMemberNo());
    		categorySalesVO.setNo(categorySalesVO.getNo()+categorySalesVO1.getNo());
    		categorySalesVO.setReturnMemberNo(categorySalesVO.getReturnMemberNo() +categorySalesVO1.getReturnMemberNo() );
    		categorySalesVO.setReturnSalesNo(categorySalesVO.getReturnSalesNo().add(categorySalesVO1.getReturnSalesNo()));
    		categorySalesVO.setReturnSkuNo(categorySalesVO.getReturnSkuNo() +categorySalesVO1.getReturnSkuNo() );
    		categorySalesVO.setSales(categorySalesVO.getSales().add(categorySalesVO1.getSales()));
    		categorySalesVO.setSalesNo(categorySalesVO.getSalesNo().add(categorySalesVO1.getSalesNo()));
    		categorySalesVO.setSkuNo(categorySalesVO.getSkuNo() +categorySalesVO1.getSkuNo() );
		}
    	list.add(categorySalesVO);
    	return list;
    }
    
    public Integer getSalesDetailsCount(Integer memberId,String startTime,String endTime,String signNo,String areaId){
    	return  ordersReadDao.getSalesDetailsCount(memberId,startTime,endTime,signNo,areaId);
    }
    
    public List<SalesDetailsVO>getSalesDetails(Integer memberId,String startTime,String endTime,Integer start,Integer size,String signNo,String areaId){
    	return ordersReadDao.getSalesDetails(memberId, startTime, endTime, start, size,signNo,areaId);
    }
    
    public List<SalesDetailsVO> getSalesDetailsByOrdersId(Integer ordersId){
    	return ordersReadDao.getSalesDetailsByOrdersId(ordersId);
    }
    

    public Boolean sendMessageToMember() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -150);

        String limitTime = TimeUtil.getDateTimeString(calendar.getTime());
        calendar.add(Calendar.MINUTE, -5);
        String cancelTime = TimeUtil.getDateTimeString(calendar.getTime());
        //log.debug("现在"50分钟时间为："+ limitTime + "现在"55分钟时间为：" + cancelTime);
        //获取下单2.5小时还未付款,的订"
        List<Orders> ordersList = ordersReadDao.getUnPaiedOrdersLs(cancelTime,limitTime);
        Map<String, Object> smsparam = new HashMap<String, Object>();
        //短信模板统一管理
        MobileMessage mMessage = new MobileMessage();
        if (ordersList != null && ordersList.size() > 0) {
            for(Orders order:ordersList){
            //短信提示
            smsparam.put("mobile", order.getMobile());
            smsparam.put("content", mMessage.getCancelMessage(order.getTradeNo()));
            senderService.sendSMSWidthCallable(smsparam);
            }
        }
        return true;
    }
    
    public String error_orders = "";
    public String saveOrdersLeadingXls(File file) {
        Workbook wb = null;
        try {
            BigDecimal price = BigDecimal.ZERO;
            ProductGoods pg = new ProductGoods();
            wb = WorkbookFactory.create(file); 
            String orderSn = readExcel(wb, 0, 0, 0);
            if(orderSn.length() > 0){
                BigDecimal moneyPrice = BigDecimal.ZERO;
                String oSn[] = orderSn.split(",");
                //循环订单处理价格和邮费
                for(String sn : oSn){
                    Orders order = ordersReadDao.getOrderByOrderSn(sn);
                    if(order == null){
                        continue;
                    }
                    List<OrdersProduct> ordersProductList = ordersProductReadDao.getOrdersProductByOrderSn(sn);
                    for(int i = 0 ; i<ordersProductList.size() ; i++){
                        OrdersProduct oProduct = ordersProductList.get(i);
                        Product p = productReadDao.getProductByLeadingSku(oProduct.getProductSku());
                        oProduct.setOrdersId(order.getId());
                        //一口价
                        if(p.getPriceStatus() == 1){
                            pg = productGoodsReadDao.getProductGoodsBySKU(oProduct.getProductSku());
                            price = pg.getMallPcPrice();
                            oProduct.setMoneyPrice(price);
                            oProduct.setMoneyAmount(price.multiply(new BigDecimal(oProduct.getNumber())).setScale(2, BigDecimal.ROUND_HALF_UP));
                            moneyPrice = moneyPrice.add(oProduct.getMoneyAmount());
                        }
                        //阶梯价
                        if(p.getPriceStatus() == 2){
                            Integer productCount = ordersProductReadDao.getCountBgSnAndSku(p.getId(),sn);
                            ProductPrice pPrice = productPriceReadDao.get(p.getPricePid());
                            if(productCount>=pPrice.getPrice1S() && productCount<=pPrice.getPrice1E()){
                                price = pPrice.getPrice1();
                            }
                            if(productCount>=pPrice.getPrice2S() && productCount<=pPrice.getPrice2E()){
                                price = pPrice.getPrice2();
                            }
                            if(productCount>=pPrice.getPrice3S()){
                                price = pPrice.getPrice2();
                            }
                            oProduct.setMoneyPrice(price);
                            oProduct.setMoneyAmount(price.multiply(new BigDecimal(oProduct.getNumber())).setScale(2, BigDecimal.ROUND_HALF_UP));
                            moneyPrice = moneyPrice.add(oProduct.getMoneyAmount());
                        }
                        //整箱价
                        if(p.getPriceStatus() == 3){
                            Integer fullContainerQuantity = p.getFullContainerQuantity();
                            Integer number = oProduct.getNumber();
                            if(number > fullContainerQuantity){
                                OrdersProduct ordersProduct = oProduct;
                                ordersProduct.setMoneyPrice(p.getScatteredPrice());
                                ordersProduct.setNumber(number%fullContainerQuantity);
                                ordersProduct.setMoneyAmount(ordersProduct.getMoneyPrice().multiply(new BigDecimal(ordersProduct.getNumber())).setScale(2, BigDecimal.ROUND_HALF_UP));
                                moneyPrice = moneyPrice.add(oProduct.getMoneyAmount());
                                oProduct.setMoneyPrice(p.getMallPcPrice());
                                oProduct.setNumber((number/fullContainerQuantity)*fullContainerQuantity);
                                oProduct.setMoneyAmount(price.multiply(new BigDecimal(oProduct.getNumber())).setScale(2, BigDecimal.ROUND_HALF_UP));
                                moneyPrice = moneyPrice.add(oProduct.getMoneyAmount());
                                this.updateProductStock(ordersProduct.getId(),false);
                                this.updateProductActualSales(ordersProduct.getProductId(), ordersProduct.getProductGoodsId(),  ordersProduct.getNumber());
                                ordersProductWriteDao.insert(ordersProduct);
                            }else{
                                oProduct.setMoneyPrice(p.getMallPcPrice());
                                oProduct.setMoneyAmount(price.multiply(new BigDecimal(oProduct.getNumber())).setScale(2, BigDecimal.ROUND_HALF_UP));
                                moneyPrice = moneyPrice.add(oProduct.getMoneyAmount());
                            }
                        }
                        ordersProductWriteDao.update(oProduct);
                        //扣减库存
                        this.updateProductStock(oProduct.getId(),false);
                        //增加销量
                        this.updateProductActualSales(oProduct.getProductId(), oProduct.getProductGoodsId(), oProduct.getNumber());
                    }
                    order.setMoneyProduct(moneyPrice);
                    //重新计算订单的邮费
                    double total_weight = ordersWriteDao.getWeightAddLabelNumTotal(order.getTradeNo());
                    double weight = ordersWriteDao.getWeightAddLabelNum(order.getOrderSn());
                    BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(1, total_weight, order.getCityId(), 1);
                    BigDecimal scale = new BigDecimal(weight).divide(new BigDecimal(total_weight),4,BigDecimal.ROUND_HALF_UP);
                    order.setMoneyLogistics(calculateTransFee.multiply(scale).setScale(2, BigDecimal.ROUND_HALF_UP));
                    order.setMoneyOrder(moneyPrice.add(order.getMoneyLogistics()));
                    ordersWriteDao.update(order);
                }
            }
        } catch (Exception e) {
            log.debug("导入订单失败！！！！！！！！！！！！！！！！");
            e.printStackTrace();
            return "运费计算失败";
        }
        //未匹配上的订单号
        if(error_orders.length()>0){
            return "error_orders";
        }else{
            return "订单导入成功！";
        }
    }

    private String readExcel(Workbook wb,int sheetIndex, int startReadLine, int tailLine) throws ParseException {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = null;
        Orders orders = new Orders();
        Orders orders_base = new Orders();
        OrdersProduct ordersProduct = new OrdersProduct();
        Map<Integer , String> map = new HashMap<Integer , String>();
        String errorMessage = "";
        String order_sn = "";
        String orderSnAll = "";
        String orderSn = "";
        String cell = "";
        Integer seller_id = null;
        List<Orders> orderList = new ArrayList<Orders>();
        List<OrdersProduct> orderProductList = new ArrayList<OrdersProduct>();
        List<Orders> orderListAll = new ArrayList<Orders>();
        List<OrdersProduct> orderProductListAll = new ArrayList<OrdersProduct>();
        boolean errorFlag = true;
        boolean orderFirst = true;
        boolean orderset = false;
        //逐行解析
        for(int i=startReadLine; i<sheet.getLastRowNum()-tailLine+1; i++) {
            System.out.println("\n第"+ i +"行");
            //跳过第一行的说明
            if(i == 0){
                continue;
            }
            row = sheet.getRow(i);
            //解析excel单元格，逐行解析
            //问题 1.判断是否一个订单信息已经解析结束
            //   2.sku是否需要拆单
            //   3.重新计算运费
          //j为判断该参数为第几列
            int j = 1;
            for(Cell c : row) {
                //判断是否存在合并单元格
                cell = getCellValue(c);
                System.out.print(cell + '-');
                //第一列不为空说明是一个新的订单
                if(j == 1 && cell != null && !"".equals(cell)){
                    map.clear();
                    if(errorFlag){
                        //将上一个订单处理完的结果放入list中
                        orderListAll.addAll(orderList);
                        orderProductListAll.addAll(orderProductList);
                        orderSnAll += order_sn;
                        orderList = new ArrayList<Orders>();
                        orderProductList = new ArrayList<OrdersProduct>();
                    }else{
                        error_orders = error_orders + errorMessage+",";
                    }
                    errorMessage = cell;
                }
                //说明只是明细项
                if(j == 1 && cell == null && "".equals(cell)){
                    orderFirst = false;
                    orders_base = null;
                }
                //赋值操作
                //第19至25列为商品明细项需要进行拆单处理
                if(j >= 19 && j <= 25){
                    //根据sku查询判断是否需要进行拆单处理
                    if(j == 23){
                        Product p = new Product();
                        try {
                           p  = productReadDao.getProductByLeadingSku(cell);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //判断该明细是否已存在对应的拆分订单
                        if(p != null){
                            seller_id = p.getSellerId();
                            if(map.get(seller_id)==null){
                                orderset = true;
                                //说明不是同一个供应商，需要用新的订单号进行拆单处理
                                orderSn = RandomUtil.getOrderSn();
                                map.put(seller_id, orderSn); 
                                orders.setOrderSn(orderSn);
                                orders.setSellerId(seller_id);
                                ordersProduct.setSellerId(seller_id);
                                order_sn = order_sn + orderSn + ",";
                            }
                        }else{
                            errorFlag = false;
                            //跳过本条订单的处理
                            continue;
                        }
                        ordersProduct.setOrdersSn(orderSn);
                    }
                    ordersProduct = this.setOrdersProduct(cell,j,ordersProduct);
                }else if(cell != null && !"".equals(cell)){
                    orders = this.setOrders(cell,j,orders);
                    if(orderFirst){
                      //将orders表备份拆单时直接赋值给新表更新ordersSn即可
                        orders_base = orders;
                    }
                }
                if(j == 35){
                    orderProductList.add(ordersProduct);
                    ordersProduct = new OrdersProduct();
                }
                j++;
            }
             if(orderset){
                 orderset = false;
                 orderList.add(orders);
                 orders = new Orders();
                 orders = this.setOrdersValue(orders_base,orders);
                 errorFlag = true;
             }
             //将最后一组ordersSn放入字符串
             if(i == sheet.getLastRowNum()-tailLine){
                 if(errorFlag){
                     //将上一个订单处理完的结果放入list中
                     orderListAll.addAll(orderList);
                     orderProductListAll.addAll(orderProductList);
                     orderSnAll += order_sn;
                 }
             }
         }
        //特殊情况，最后一条如果出错直接continue，所以最后处理
        if(!errorFlag){
            error_orders = error_orders + errorMessage+",";
        }
        //将封装好的对象先存入数据库在进行二次处理
        if(orderListAll.size()>0){
            for(Orders order : orderListAll){
                ordersWriteDao.insert(order);
            }
            for(OrdersProduct orderProduct : orderProductListAll){
                ordersProductWriteDao.insert(orderProduct);
            }
            if(orderSnAll.length() ==0){
                orderSnAll = order_sn;
            }
        }
        return orderSnAll;
    }

    private Orders setOrdersValue(Orders orders_base, Orders orders) {
        orders.setMemberId(0);
        orders.setMoneyCoupon(new BigDecimal(0.00));
        orders.setMoneyActFull(new BigDecimal(0.00));
        orders.setMoneyDiscount(new BigDecimal(0.00));
        orders.setMoneyBack(new BigDecimal(0.00));
        orders.setOrderState(3);
        orders.setPayTime(orders_base.getPayTime());
        orders.setPaymentStatus(1);
        orders.setInvoiceStatus(0);
        orders.setMoneyProduct(new BigDecimal(0.00));
        orders.setMoneyOrder(new BigDecimal(0.00));
        orders.setMoneyPaidBalance(new BigDecimal(0.00));
        orders.setMoneyPaidReality(new BigDecimal(0.00));
        orders.setMoneyIntegral(new BigDecimal(0));
        orders.setIntegral(0);
        orders.setCouponUserId(0);
        orders.setActFullId(0);
        orders.setActivityId(0);
        orders.setIp("192.168.1.1");
        orders.setSource(1);
        orders.setIsCodconfim(0);
        orders.setCodconfirmId(0);
        orders.setCodconfirmName("");
        orders.setCodconfirmRemark("");
        orders.setCodconfirmState(0);
        orders.setRemark("");
        orders.setPaymentName("余额支付");
        orders.setPaymentCode("BALANCE");
        orders.setName(orders_base.getName());
        orders.setLogisticsId(1);
        orders.setLogisticsName("中通");
        orders.setLogisticsNumber("");
        orders.setProvinceId(orders_base.getProvinceId());
        orders.setCityId(orders_base.getCityId());
        orders.setAreaId(orders_base.getAreaId());
        orders.setAddressAll(orders_base.getAddressAll());
        orders.setAddressInfo(orders_base.getAddressInfo());
        orders.setZipCode(orders_base.getZipCode());
        orders.setMobile(orders_base.getMobile());
        orders.setRelationOrderSn(orders_base.getRelationOrderSn());
        orders.setTradeNo(orders_base.getTradeNo());
        orders.setTradeSn(orders_base.getTradeSn());
        orders.setOrderType(1);
        orders.setMemberName(orders_base.getMemberName());
        return orders;
    }

    private OrdersProduct setOrdersProduct(String cell, int j, OrdersProduct ordersProduct) {
        //根据第23列sku查询出该商品的信息
        ProductGoods pg = new ProductGoods();
        Product p = new Product();
        if(j == 23){
            try {
                pg = productGoodsReadDao.getProductGoodsBySKU(cell);
                p = productReadDao.get(pg.getProductId());
            } catch (Exception e) {
                e.printStackTrace();
                return ordersProduct;
            }
            ordersProduct.setProductCateId(p.getProductCateId());
            ordersProduct.setProductId(pg.getProductId());
            ordersProduct.setProductGoodsId(pg.getId());
            ordersProduct.setSpecInfo(pg.getNormName());
            ordersProduct.setProductSku(pg.getSku());
            ordersProduct.setProductName(p.getName1());
            ordersProduct.setPackageGroupsId(0);
            ordersProduct.setMallGroupsId(0);
            ordersProduct.setGiftId(0);
            ordersProduct.setIsGift(0);
            ordersProduct.setMoneyActSingle(new BigDecimal(0.00));
            ordersProduct.setActivityId(0);
            ordersProduct.setActSingleId(0);
            ordersProduct.setActFlashSaleId(0);
            ordersProduct.setActFlashSaleProductId(0);
            ordersProduct.setActGroupId(0);
            ordersProduct.setActBiddingId(0);
            ordersProduct.setMemberProductBackId(0);
            ordersProduct.setMemberProductExchangeId(0);
            ordersProduct.setPackageFeeTotal(new BigDecimal(0.00));
            ordersProduct.setPackageFee(new BigDecimal(0.00));
            ordersProduct.setProductPackageId(0);
            ordersProduct.setProductLabelId(0);
            ordersProduct.setLabelFeeTotal(new BigDecimal(0.00));
            ordersProduct.setIsSelfLabel(0);
            ordersProduct.setDeliveredNum(0);
            ordersProduct.setDeliveredNum(0);
            ordersProduct.setOrdersId(0);
            ordersProduct.setMoneyPrice(pg.getMallPcPrice());
            ordersProduct.setSellerId(p.getSellerId());
            ordersProduct.setMoneyAmount(ordersProduct.getMoneyPrice().multiply(new BigDecimal(ordersProduct.getNumber())).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(j == 20){
            ordersProduct.setMoneyPrice(new BigDecimal(cell));
        }
        if(j == 21){
            ordersProduct.setNumber(Integer.valueOf(cell.substring(0, cell.length()-2)));
        }
        return ordersProduct;
    }

    private Orders setOrders(String cell, int j, Orders orders) throws ParseException {
        if(j == 1){
            orders.setTradeNo(cell);
            orders.setOrderSn(cell);
            orders.setRelationOrderSn(cell);
            orders.setOrderType(1);
        }
        if(j == 3){
            orders.setMemberName(cell);
        }
        if(j == 11){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String strDate = cell;
            Date date = sdf.parse(strDate);
            orders.setMemberId(0);
            orders.setMoneyCoupon(new BigDecimal(0.00));
            orders.setMoneyActFull(new BigDecimal(0.00));
            orders.setMoneyDiscount(new BigDecimal(0.00));
            orders.setMoneyBack(new BigDecimal(0.00));
            orders.setOrderState(3);
            orders.setPayTime(date);
            orders.setPaymentStatus(1);
            orders.setInvoiceStatus(0);
            orders.setMoneyProduct(new BigDecimal(0.00));
            orders.setMoneyOrder(new BigDecimal(0.00));
            orders.setMoneyPaidBalance(new BigDecimal(0.00));
            orders.setMoneyPaidReality(new BigDecimal(0.00));
            orders.setMoneyIntegral(new BigDecimal(0));
            orders.setIntegral(0);
            orders.setCouponUserId(0);
            orders.setActFullId(0);
            orders.setActivityId(0);
            orders.setIp("192.168.1.1");
            orders.setSource(1);
            orders.setIsCodconfim(0);
            orders.setCodconfirmId(0);
            orders.setCodconfirmName("");
            orders.setCodconfirmRemark("");
            orders.setCodconfirmState(0);
            orders.setRemark("");
        }
        if(j == 10){
            orders.setPaymentName("余额支付");
            orders.setPaymentCode("BALANCE");
        }
        if(j == 14){
            orders.setName(cell);
            orders.setLogisticsId(1);
            orders.setLogisticsName("中通");
            orders.setLogisticsNumber("");
        }
        //解析收货地址快递信息
        if(j == 15){
            if(!"".equals(cell)){
                String str[] = cell.split(" ");
                Regions reProvice = new Regions();
                Regions reCity = new Regions();
                
                //直辖市单独处理，本地数据库不带市
                if("上海".equals(str[0])||"北京".equals(str[0])||"重庆".equals(str[0])||"天津".equals(str[0])){
                    reProvice = regionsReadDao.getRegionsByRegionName1(str[0]);
                    reCity = regionsReadDao.getRegionsByRegionName2(str[0] , reProvice.getId());
                }else{
                    reProvice = regionsReadDao.getRegionsByRegionName1(str[0]);
                    String cityName = str[1];
                    if(cityName.substring(cityName.length()-1).equals("市")){
                        cityName = cityName.substring(0, cityName.length()-1);
                    }
                    reCity = regionsReadDao.getRegionsByRegionName2(cityName , reProvice.getId());
                }
                Regions reArea = regionsReadDao.getRegionsByRegionName2(str[2] , reCity.getId());
                orders.setProvinceId(reProvice.getId());
                orders.setCityId(reCity.getId());
                orders.setAreaId(reArea.getId());
                orders.setAddressAll(reProvice.getRegionName()+""+reCity.getRegionName()+""+reArea.getRegionName());
                if(str.length == 5){
                    orders.setAddressInfo(str[4]);
                }else if(str.length > 5){
                    orders.setAddressInfo(str[4]+""+str[5]);
                }
            }
            if(j == 16){
                orders.setZipCode(cell);
            }
            if(j == 18){
                orders.setMobile(cell);
            }
        }
        return orders;
    }

    private String getCellValue(Cell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        if(cell == null) return "";    
        if(cell.getCellType() == Cell.CELL_TYPE_STRING){    
            return cell.getStringCellValue();    
        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){    
            return String.valueOf(cell.getBooleanCellValue());    
        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){    
            return cell.getCellFormula() ;    
        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){ 
            if (DateUtil.isCellDateFormatted(cell)) {   
                Date theDate = cell.getDateCellValue();  
                return sdf.format(theDate);  
            } else {   
                return String.valueOf(cell.getNumericCellValue());
            }
        }    
        return "";
    }

    public Boolean deleteOrder(String orderId, Integer id, String name) {
        Integer result = 0;
        Orders ordersNew = new Orders();
        ordersNew.setId(Integer.valueOf(orderId));
        ordersNew.setOrderState(Orders.ORDER_STATE_10);
        // 1.更新订单
        result = ordersWriteDao.update(ordersNew);
        return result>0;
    }
    
    
    public List<Orders> getOrdersByTradeNo (String tradeNo) {
    	List<Orders> list = ordersReadDao.getOrdersByTradeNo(tradeNo);
    	return list;
    }

    public Boolean sendMessageToEffectiveCunstomer() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        String lastLoginTime = TimeUtil.getDateTimeString(calendar.getTime());
        List<Member> memberList = ordersReadDao.getLastLoginMemberList(lastLoginTime.substring(0, 10));
        Map<String, Object> smsparam = new HashMap<String, Object>();
        //短信模板统一管理
        MobileMessage mMessage = new MobileMessage();
        Matcher m;
        String mobile = "";
        if (memberList != null && memberList.size() > 0) {
            for(Member member:memberList){
                mobile = member.getMobile();
                //短信提示
                Pattern p = Pattern.compile(Member.MOBILE_VALIDATA);
                m = p.matcher(mobile);
                if(!m.matches()){
                   continue;
                }
                smsparam.put("mobile", mobile);
                smsparam.put("content", mMessage.getMemberLoginMessage());
                senderService.sendSMSWidthCallable(smsparam);
            }
        }
        return true; 
    }


}
