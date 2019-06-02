package com.phkj.web.controller.order;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.order.StAppletOrders;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.service.order.IStAppletOrdersService;
import com.phkj.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Filename: AdminOrdersController.java
 * @Version: 1.0
 * @date: 2019年5月13日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 */
@Controller
@RequestMapping(value = "admin/order/orders")
public class AdminOrdersController extends BaseController {
    @Autowired
    private IStAppletOrdersService stAppletOrdersService;
    Logger log = Logger.getLogger(this.getClass());

    /**
     * 商家确认订单
     *
     * @param orderSn
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/confirm", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil confirmOrder(String orderSn) {
        if (StringUtils.isBlank(orderSn)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orderSn is blank", false, null);
        }
        StAppletOrders stAppletOrders = new StAppletOrders();
        stAppletOrders.setOrderSn(orderSn);
        stAppletOrders.setOrderState(3);
        stAppletOrders.setUpdateTime(new Date());
        ServiceResult<Integer> result = stAppletOrdersService.updateStAppletOrders(stAppletOrders);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    /**
     * 取消订单
     *
     * @param orderSn
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cancel", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil cancelOrder(String orderSn) {
        if (StringUtils.isBlank(orderSn)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orderSn is blank", false, null);
        }
        StAppletOrders stAppletOrders = new StAppletOrders();
        stAppletOrders.setOrderSn(orderSn);
        stAppletOrders.setOrderState(6);
        stAppletOrders.setUpdateTime(new Date());
        ServiceResult<Integer> result = stAppletOrdersService.updateStAppletOrders(stAppletOrders);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    /**
     * 删除订单
     *
     * @param orderSn
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil deleteOrder(String orderSn) {
        if (StringUtils.isBlank(orderSn)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orderSn is blank", false, null);
        }
        StAppletOrders stAppletOrders = new StAppletOrders();
        stAppletOrders.setOrderSn(orderSn);
        stAppletOrders.setDeleted(0);
        stAppletOrders.setUpdateTime(new Date());
        ServiceResult<Integer> result = stAppletOrdersService.updateStAppletOrders(stAppletOrders);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    /**
     * 订单列表
     *
     * @param memberId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil list(Integer memberId, int pageNum, int pageSize) {
        if (memberId == null || memberId == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberId is blank", false, null);
        }
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<List<StAppletOrders>> result = stAppletOrdersService.getStAppletOrdersList(memberId, pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    /**
     * 订单列表
     *
     * @param orderSn
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil detail(String orderSn) {
        if (StringUtils.isBlank(orderSn)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orderSn is blank", false, null);
        }
        ServiceResult<List<StAppletOrdersProduct>> result = stAppletOrdersService.detail(orderSn);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }
}
