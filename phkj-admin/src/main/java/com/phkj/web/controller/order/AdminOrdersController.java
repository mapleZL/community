package com.phkj.web.controller.order;

import com.phkj.core.*;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.order.StAppletOrderBO;
import com.phkj.entity.order.StAppletOrders;
import com.phkj.entity.order.StAppletOrdersParam;
import com.phkj.entity.order.StAppletOrdersVO;
import com.phkj.service.order.IStAppletOrdersService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Filename: AdminOrdersController.java
 * @Version: 1.0
 * @date: 2019年5月13日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 */
@Controller
@RequestMapping(value = "/admin/order/orders")
public class AdminOrdersController extends BaseController {
    @Autowired
    private IStAppletOrdersService stAppletOrdersService;
    Logger log = Logger.getLogger(this.getClass());

    /**
     * 登录用户角色
     */
    private static final String USER_TYPE_1 = "seller";

    /**
     * 默认页面
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/seller/orders/listorders";
    }

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
        stAppletOrders.setOrderState(5);
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
        ServiceResult<List<StAppletOrdersVO>> result = stAppletOrdersService.getStAppletOrdersList(memberId, pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }


    /**
     * gridDatalist数据
     *
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "all", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<List<StAppletOrders>> list(HttpServletRequest request,
                                              ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String userType = request.getParameter("q_userType");
        // 登陆者为商户的时候才加上商品查询条件
        if (USER_TYPE_1.equals(userType)) {
            queryMap.put("q_sellerId", WebAdminSession.getAdminUser(request).getId() + "");
        }
        ServiceResult<List<StAppletOrders>> serviceResult = stAppletOrdersService.page(queryMap,
                pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletOrders>> jsonResult = new HttpJsonResult<List<StAppletOrders>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * gridDatalist数据
     *
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "confirmOrders", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<List<StAppletOrders>> confirmOrders(HttpServletRequest request,
                                                       ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String userType = request.getParameter("q_userType");
        // 登陆者为商户的时候才加上商品查询条件
        if (USER_TYPE_1.equals(userType)) {
            queryMap.put("q_sellerId", WebAdminSession.getAdminUser(request).getId() + "");
        } else {

        }
        queryMap.put("q_orderState", "1");
        ServiceResult<List<StAppletOrders>> serviceResult = stAppletOrdersService.page(queryMap,
                pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletOrders>> jsonResult = new HttpJsonResult<List<StAppletOrders>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
//        dataMap.put("jsonResult", jsonResult);
        return jsonResult;
    }

    /**
     * 订单详情
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
        ServiceResult<List<StAppletOrdersVO>> result = stAppletOrdersService.detail(orderSn);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    /**
     * 新增订单
     *
     * @param appletOrderBO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil addOrder(@RequestBody StAppletOrderBO appletOrderBO) {
        List<StAppletOrdersParam> orders = appletOrderBO.getOrders();
        ResponseUtil responseUtil = checkParam(orders);
        if (null != responseUtil) {
            return responseUtil;
        }
        ServiceResult<Integer> result = stAppletOrdersService.saveStAppletOrders(orders);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    private ResponseUtil checkParam(List<StAppletOrdersParam> orders) {
        if (orders == null || orders.isEmpty()) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "param is blank", false, null);
        }
        for (StAppletOrdersParam ordersParam : orders) {
            if (StringUtils.isBlank(ordersParam.getMemberName()) || ordersParam.getMemberId() == null || ordersParam.getMemberId() == 0) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberName or memberId is blank", false, null);
            }
            if (ordersParam.getMoneyProduct() == null || ordersParam.getSellerId() == null || ordersParam.getSellerId() == 0) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "moneyProduct or sellerId is blank", false, null);
            }
            if (StringUtils.isBlank(ordersParam.getProductName()) || ordersParam.getProductId() == null || ordersParam.getProductId() == 0) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "productName or productId is blank", false, null);
            }
            if (ordersParam.getNumber() == null || ordersParam.getNumber() == 0 || ordersParam.getMoneyPrice() == null) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "moneyPrice or number is blank", false, null);
            }
            if (StringUtils.isBlank(ordersParam.getAddressInfo())) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "addressInfo is blank", false, null);
            }
        }
        return null;
    }
}
