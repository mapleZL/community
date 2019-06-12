package com.phkj.service.order;


import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.order.StAppletOrders;
import com.phkj.entity.order.StAppletOrdersParam;
import com.phkj.entity.order.StAppletOrdersVO;

public interface IStAppletOrdersService {

    /**
     * 根据id取得订单
     *
     * @param stAppletOrdersId
     * @return
     */
    ServiceResult<StAppletOrders> getStAppletOrdersById(Integer stAppletOrdersId);

    /**
     * 保存订单
     *
     * @param stAppletOrders
     * @return
     */
    ServiceResult<Integer> saveStAppletOrders(List<StAppletOrdersParam> stAppletOrders);

    /**
     * 更新订单
     *
     * @param stAppletOrders
     * @return
     */
    ServiceResult<Integer> updateStAppletOrders(StAppletOrders stAppletOrders);

    /**
     * 获取订单列表
     *
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServiceResult<List<StAppletOrdersVO>> getStAppletOrdersList(Integer memberId, int pageNum, int pageSize);

    /**
     * 订单详情
     *
     * @param orderSn
     * @return
     */
    ServiceResult<List<StAppletOrdersVO>> detail(String orderSn);

    /**
     * 获取订单列表
     *
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<StAppletOrders>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 查询用户常用列表
     * @param memberId
     * @return
     */
    StAppletOrders getNormalAddress(Integer memberId);
}