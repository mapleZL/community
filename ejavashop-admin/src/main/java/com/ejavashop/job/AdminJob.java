package com.ejavashop.job;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IMemberCreditService;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.service.search.ISolrProductService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.settlement.ISettlementService;

public class AdminJob {
    private static final Logger  log = LogManager.getLogger(AdminJob.class);
    
    private static final Logger  ILog = LogManager.getLogger("oms_interface");
    @Resource
    private ISettlementService   settlementService;
    @Resource
    private IOrdersService       ordersService;
    @Resource
    private ICartService         cartService;
    @Resource
    private ISellerService       sellerService;
    @Resource
    private ISolrProductService  solrProductService;
    @Resource
    private IMemberCreditService memberCreditService;
    @Resource
    private IBookingSendGoodsService bookingSendGoodsService;
    @Resource
    private IActBiddingService  actBiddingService;
    @Resource
    private IProductService  productService;
    
    
    /**
     * add by Ls 2016-12-05
     * 系统在取消订单前30分钟提醒还没付款的订单
     */
    public void sendMessageToMember() {
        ServiceResult<Boolean> jobResult = ordersService.sendMessageToMember();
        if(! jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()){
            log.error("[ejavashop-admin][AdminJob][sendMessageToMember] 定时任务系统自动取消提醒客户还有30分钟取消订单时失败："  + jobResult.getMessage());
        }
    }
   
}
