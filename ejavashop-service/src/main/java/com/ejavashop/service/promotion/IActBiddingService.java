package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;

public interface IActBiddingService {

    /**
     * 根据id取得集合竞价分类
     * @param  actBiddingTypeId
     * @return
     */
    ServiceResult<ActBiddingType> getActBiddingTypeById(Integer actBiddingTypeId);

    /**
     * 保存集合竞价分类
     * @param  actBiddingType
     * @return
     */
    ServiceResult<Integer> saveActBiddingType(ActBiddingType actBiddingType);

    /**
    * 更新集合竞价分类
    * @param  actBiddingType
    * @return
    */
    ServiceResult<Integer> updateActBiddingType(ActBiddingType actBiddingType);

    /**
     * 查询集合竞价分类
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActBiddingType>> getActBiddingTypes(Map<String, String> queryMap,
                                                           PagerInfo pager);

    /**
     * 删除集合竞价分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> delActBiddingType(Integer id);

    /**
     * 启用集合竞价分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> auditYesActBiddingType(Integer id);

    /**
     * 停用集合竞价分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> auditNoActBiddingType(Integer id);

    /**
     * 查询所有可用的集合竞价分类
     * @return
     */
    ServiceResult<List<ActBiddingType>> getActBiddingTypesAll();

    /**
     * 查询所有可用的集合竞价分类
     * @return
     */
    ServiceResult<List<ActBiddingType>> getActBiddingTypesFront();

    //////////////
    /**
     * 根据id取得集合竞价
     * @param  actBiddingId
     * @return
     */
    ServiceResult<ActBidding> getActBiddingById(Integer actBiddingId);

    /**
     * 保存集合竞价
     * @param  actBidding
     * @return
     */
    ServiceResult<Integer> saveActBidding(ActBidding actBidding);

    /**
    * 更新集合竞价
    * @param  actBidding
    * @return
    */
    ServiceResult<Integer> updateActBidding(ActBidding actBidding);

    /**
     * 查询集合竞价
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActBidding>> getActBiddings(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 保存集合竞价和阶梯价格
     * @param actBidding
     * @param actBiddingPrices
     * @return
     */
    ServiceResult<Boolean> saveActBiddingAndPrice(ActBidding actBidding,
                                                  List<ActBiddingPrice> actBiddingPrices);

    /**
     * 根据集合竞价ID取得阶梯价格
     * @param id
     * @return
     */
    ServiceResult<List<ActBiddingPrice>> getActBiddingByIds(Integer id);

    /**
     * 更新集合竞价和阶梯价格
     * @param actBidding
     * @param actBiddingPrices
     * @return
     */
    ServiceResult<Boolean> updateActBiddingAndPrice(ActBidding actBidding,
                                                    List<ActBiddingPrice> actBiddingPrices);

    /**
     * 更新集合竞价状态
     * @param id
     * @param state2
     * @return
     */
    ServiceResult<Boolean> updateActBiddingState(Integer id, int state2);

    /**
     * 更新集合竞价发布状态
     * @param id
     * @param actState2
     * @return
     */
    ServiceResult<Boolean> updateActBiddingActState(Integer id, int actState2);

    /**
     * 集合竞价定时器，结束活动，生成尾款订单
     * @return
     */
    ServiceResult<Boolean> jobBiddingService();

    /**
     * 前台页面查询集合竞价
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActBidding>> getActBiddingsFront(Map<String, String> queryMap,
                                                        PagerInfo pager);

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state 更改的状态
     * @param auditOpinion 原因
     * @return
     */
    ServiceResult<Boolean> updateActBiddingStateAndAuditOpinion(Integer id, int state3,
                                                                String auditOpinion);

    /**
     * 取得该团购所属分类下的前5个商品
     * @param type
     * @param number
     * @return
     */
    ServiceResult<List<ActBidding>> getActBiddingsByType(int type, int number);

}