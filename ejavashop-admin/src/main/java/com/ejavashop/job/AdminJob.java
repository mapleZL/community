package com.ejavashop.job;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.SyncWayUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IMemberCreditService;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.service.search.ISolrProductService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.settlement.ISettlementService;
import com.ejavashop.service.wmsinterface.IInterfaceWmsService;

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
    @Resource
    private IInterfaceWmsService  interfaceWmsService;
    

    /**
     * 会员赊账设置到期定时任务<br>
     * <li>系统会在每天将所有已到期的赊账状态设置为已到期</li>
     */
    public void jobCredit() {
        ServiceResult<Boolean> jobResult = memberCreditService.jobCredit();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobCredit] 会员赊账设置到期定时任务失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 商家结算账单生成定时任务<br>
     * <li>查询所有商家，每个商家每个结算周期生成一条结算账单
     * <li>计算周期内商家所有的订单金额合计
     * <li>计算所有订单下网单的佣金
     * <li>计算周期内发生的退货退款（当前周期结算的订单如果在下个结算周期才退款，退款结算在下个周期计算）
     * <li>每个商家一个事务，某个商家结算时发生错误不影响其他结算
     */
    public void jobSettlement() {
        ServiceResult<Boolean> jobResult = settlementService.jobSettlement();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSettlement] 商家结算账单生成时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统自动完成订单<br>
     * <li>对已发货状态的订单发货时间超过15个自然日的订单进行自动完成处理
     */
    public void jobSystemFinishOrder() {
        ServiceResult<Boolean> jobResult = ordersService.jobSystemFinishOrder();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSystemFinishOrder] 系统自动完成订单时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统定时任务清除7天之前添加的购物车数据
     */
    public void jobClearCart() {
        ServiceResult<Boolean> jobResult = cartService.jobClearCart();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobClearCart] 系统定时任务清除7天之前添加的购物车数据时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统定时更新solr索引
     */
    public void jobSearchSolr() {
    	//在更新solr之前 先下架商品
    	//this.downProduct();
    	
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        ServiceResult<Boolean> jobResult = solrProductService.jobCreateIndexesSolr(solrUrl, solrServer);
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSearchSolr] 系统定时任务定时生成Solr索引失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 更新敏感词的索引值
     */
    public void jobUpdateSearchRecordIndex() {
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        ServiceResult<Boolean> jobResult = solrProductService.jobUpdateSearchRecordIndex(solrUrl,
            solrServer);
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobUpdateSearchRecordIndex] 系统定时任务定时更新敏感词的索引值："
                      + jobResult.getMessage());
        }
    }

    /**
     * 定时任务设定商家的评分，用户评论各项求平均值设置为商家各项的综合评分
     * @return
     */
    public void jobSetSellerScore() {
        ServiceResult<Boolean> jobResult = sellerService.jobSetSellerScore();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSetSellerScore] 定时任务设定商家的评分时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统自动取消3小时没有付款订单
     * @return
     */
    public void jobSystemCancelOrder() {
        ServiceResult<Boolean> jobResult = ordersService.jobSystemCancelOrder();
        //新加的三方仓储订单返回发货量  tdx 20160728 三方仓储暂时不上
//        ServiceResult<Boolean> jobBookResult = bookingSendGoodsService.jobSystemCancelBookOrder();
        
//        if (!jobBookResult.getSuccess() || jobBookResult.getResult() == null || !jobBookResult.getResult()) {
//            log.error("[ejavashop-admin][AdminJob][jobSystemCancelBookOrder] 定时任务系统自动取消24小时三方仓储发货没有付款订单时失败："
//                      + jobResult.getMessage());
//        }
        
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSystemCancelOrder] 定时任务系统自动取消1小时没有付款订单时失败："  + jobResult.getMessage());
        }
    }
    
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
    
    /**
     * add by lushuai  2017-04-18
     * 系统给超过30天未登录且成功购买过商城的客户发送短信
     */
    public void sendMessageToEffectiveCunstomer() {
        ServiceResult<Boolean> jobResult = ordersService.sendMessageToEffectiveCunstomer();
        if(! jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()){
            log.error("[ejavashop-admin][AdminJob][sendMessageToEffectiveCunstomer] 定时任务系统自动发送30天未登录的客户短信失败："  + jobResult.getMessage());
        }
    }
    
    /**
     * 定时任务设定商家各项统计数据
     * @return
     */
    public void jobSellerStatistics() {
        ServiceResult<Boolean> jobResult = sellerService.jobSellerStatistics();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSellerStatistics] 定时任务设定商家各项统计数据时失败："
                      + jobResult.getMessage());
        }
    }
    
    /**
     * 定时任务推送待发货的订单给wms
     */
    public void putOutDoorOrders() {    	
    	if (SyncWayUtil.SYNC_WAY.equals(SyncWayUtil.SYNC_OMS)) {
    		//ILog.info("---------------------------------平台推送待发货订单给OMS开始：----------------------------");
    		//执行订单推送给oms开始
        	this.omsOrdersCreate();
        	//ILog.info("----------------------------------平台推送待发货订单给OMS结束：---------------------------");
    	}
    	else {
    		log.info("---------------------------------平台推送待发货订单给WMS开始：----------------------------");
	    	ServiceResult<Integer> jobResult = ordersService.putOutDoorOrders();
	    	Integer putOrdersSize = jobResult.getResult();
	        log.info("##################################本次平台共推送："+putOrdersSize+"条记录给 wms###########################################");
	    	if (!jobResult.getSuccess()) {
	            log.error("[ejavashop-admin][AdminJob][putOutDoorOrders] 定时任务定时推送wms待发货订单出错："
	                      + jobResult.getMessage());
	        }
	    	log.info("----------------------------------平台推送待发货订单给WMS结束：---------------------------");
    	}
    	//定时更新商品销量
//    	this.updateActualSales();
    }
    
    public void sendFailOrderMail () {
    	log.info("-----------------------------定时发送邮件开始：--------------------------------");
    	ServiceResult<Boolean> jobResult = interfaceWmsService.sendFailOrderMail();
    	if (!jobResult.getSuccess()) {
    		log.error("[ejavashop-admin][AdminJob][sendFailOrderMail] 定时任务定时发送邮件出错："
    				+ jobResult.getMessage());
    	}
    	log.info("-----------------------------定时发送邮件结束：--------------------------------");
    }
    
    
    /**
     * 定时更新商品销量
     */
    public void updateActualSales(){
    	log.info("-----------------------------更新商品销量开始：--------------------------------");
    	ServiceResult<Boolean> jobResult = productService.updateActualSales();
    	if (!jobResult.getSuccess()) {
    		log.error("[ejavashop-admin][AdminJob][updateActualSales] 定时任务定时更新商品销量出错："
    				+ jobResult.getMessage());
    	}
    	log.info("-----------------------------更新商品销量开始结束：--------------------------------");
    }
    
    /**
     * 定时任务推送待发货的订单给oms
     */
    public void omsOrdersCreate(){
    	ILog.info("-----------------------------平台推送待发货订单给oms开始：--------------------------------");
    	ServiceResult<Integer> jobResult = ordersService.omsOrdersCreate();
    	Integer putOrdersSize = jobResult.getResult();
    	ILog.info("##################################本次平台共推送："+putOrdersSize+"条记录给 oms###########################################");
    	if (!jobResult.getSuccess()) {
    		ILog.error("[ejavashop-admin][AdminJob][putOutDoorOrders] 定时任务定时推送oms待发货订单出错："
    				+ jobResult.getMessage());
    	}
    	ILog.info("-----------------------------平台推送待发货订单给oms结束：--------------------------------");
    }
    
    
    /**
     * 定时下架商品，下架规则：非猫头鹰货品spu库存<1手（根据实际情况判断1手的实际数量）自动下架
     * 猫头鹰和猫头鹰2商品spu库存<100自动下架
     */
    public void downProduct(){
    	log.info("----------------------------定时下架商品------start-------------------------------------------");
    	ServiceResult<Integer> jobResult = productService.downProduct();
    	Integer putOrdersSize = jobResult.getResult();
    	log.info("----------------------------本次共下架"+putOrdersSize+"款商品------------------------------------------");
    	if (!jobResult.getSuccess()) {
    		log.error("[ejavashop-admin][AdminJob][downProduct] 定时下架商品出错："
    				+ jobResult.getMessage());
    	}
    	log.info("----------------------------定时下架商品------end-------------------------------------------");
    }
    
    /**
     * 集合竞价定时器，结束活动，生成尾款订单 TODO 没有加定时器，需要时加上
     */
    public void jobBiddingBidding() {
        ServiceResult<Boolean> jobResult = actBiddingService.jobBiddingService();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobBiddingService] 集合竞价定时器，结束活动，生成尾款订单时失败："
                      + jobResult.getMessage());
        }
    }
}
