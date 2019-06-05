package com.phkj.service.order;


import com.phkj.core.ServiceResult;
import com.phkj.entity.order.StAppletOrders;
import com.phkj.entity.order.StAppletOrdersParam;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.entity.order.StAppletOrdersVO;

import java.util.List;

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
}