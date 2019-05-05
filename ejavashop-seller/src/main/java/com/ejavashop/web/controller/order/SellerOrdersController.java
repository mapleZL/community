package com.ejavashop.web.controller.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.operate.CourierCompany;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerApply;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.operate.ICourierCompanyService;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.seller.ISellerApplyService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.vo.order.BookingSendGoodsVO;
import com.ejavashop.vo.order.SendingGoodsVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 订单管理controller
 *                       
 * @Filename: SellerOrdersController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/order/orders")
public class SellerOrdersController extends BaseController {

    Logger                         log = Logger.getLogger(this.getClass());

    @Resource(name = "ordersService")
    private IOrdersService         orderService;
    @Resource
    private ICourierCompanyService courierCompanyService;
    @Resource
    private ISellerService         sellerService;
    @Resource
    private ISellerApplyService    sellerApplyService;
    @Resource
    private IRegionsService        resionsService;
    @Resource(name = "bookingSendGoodsService")
	 IBookingSendGoodsService bookingSendGoodsService;

    /**
     * 列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist";
    }

    /**
     * 未付款订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state1", method = { RequestMethod.GET })
    public String listState1(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist1";
    }

    /**
     * 价格设置
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setPrice", method = { RequestMethod.GET })
    public String setPrice(HttpServletRequest request, ModelMap dataMap, Integer orderId)
                                                                                         throws Exception {
        ServiceResult<Orders> orderResult = orderService.getOrderWithOPById(orderId,"admin");
        dataMap.put("orders", orderResult.getResult());
        return "seller/order/orders/priceset";
    }

    /**
     * 保存价格设置
     * @param request
     * @param dataMap
     * @param opinfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setPriceSvae", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> setPriceSvae(HttpServletRequest request,
                                                              ModelMap dataMap, String opinfo,
                                                              Integer orderId) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            ServiceResult<Boolean> serviceResult = orderService.saveOrdersProductPrice(orderId,
                opinfo,"");
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                jsonResult.setMessage(e.getMessage());
            } else {
                jsonResult.setMessage(ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
                e.printStackTrace();
            }
        }
        return jsonResult;
    }

    /**
     * 标签设置
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setLabel", method = { RequestMethod.GET })
    public String setLabel(HttpServletRequest request, ModelMap dataMap, Integer orderId)
                                                                                         throws Exception {
        ServiceResult<Orders> orderResult = orderService.getOrderWithOPById(orderId,"admin");
        dataMap.put("orders", orderResult.getResult());
        return "seller/order/orders/labelset";
    }

    @RequestMapping(value = "setLabelSave", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> setLabelSvae(HttpServletRequest request,
                                                              ModelMap dataMap, String opinfo,
                                                              Integer orderId) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            ServiceResult<Boolean> serviceResult = orderService.saveOrdersProductLabel(orderId,
                opinfo);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                jsonResult.setMessage(e.getMessage());
            } else {
                jsonResult.setMessage(ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
                e.printStackTrace();
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "labeldialog", method = { RequestMethod.GET })
    public String labeldialog(HttpServletRequest request, ModelMap dataMap, Integer orderId)
                                                                                            throws Exception {
        String memberId = request.getParameter("memberId");
        String isSelfLabel = request.getParameter("isSelfLabel");
        if (isSelfLabel.equals("1")) {
            //自供标
            dataMap.put("memberId", memberId);
        } else {
            dataMap.put("memberId", 0);
        }
        return "seller/order/orders/labeldialog";
    }

    /**
     * 待确认订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state2", method = { RequestMethod.GET })
    public String listState2(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist2";
    }

    /**
     * 待发货订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state3", method = { RequestMethod.GET })
    public String listState3(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist3";
    }

    /**
     * 已发货订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state4", method = { RequestMethod.GET })
    public String listState4(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist4";
    }

    /**
     * 已完成订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state5", method = { RequestMethod.GET })
    public String listState5(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist5";
    }

    /**
     * 已取消订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state6", method = { RequestMethod.GET })
    public String listState6(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist6";
    }

    /**
     * 列表页数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Orders>> list(HttpServletRequest request,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap
            .put("q_sellerId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        ServiceResult<List<Orders>> serviceResult = orderService.getOrders(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Orders>> jsonResult = new HttpJsonResult<List<Orders>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 确认订单，占库存
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "confirm", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> confirm(HttpServletRequest request,
                                                         HttpServletResponse response, Integer id) {

        ServiceResult<Orders> orderResult = orderService.getOrdersById(id);
        if (!orderResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(orderResult.getCode())) {
                throw new RuntimeException(orderResult.getMessage());
            } else {
                throw new BusinessException(orderResult.getMessage());
            }
        }
        Orders ordersDb = orderResult.getResult();
        if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_2)) {
            return new HttpJsonResult<Boolean>("只有待确认状态的订单可以执行此操作！");
        }

        Orders orders = new Orders();
        orders.setId(id);
        //待发货
        orders.setOrderState(Orders.ORDER_STATE_3);

        ServiceResult<Integer> serviceResult = orderService.updateOrdersBySeller(orders,
            Orders.CONFIRM, WebSellerSession.getSellerUser(request), true);

        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        return jsonResult;
    }

    /**
     * 订单编辑，只能修改订单金额
     * 
     * @param request
     * @param dataMap
     * @param id
     * @param source 请求来源标记，用于判断需要返回的页面
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id,
                       Integer source) {
        Orders orders = orderService.getOrdersById(id).getResult();
        dataMap.put("orders", orders);
        dataMap.put("source", source);
        return "seller/order/orders/ordersedit";
    }

    /**
     * 更新订单金额
     * @param request
     * @param id
     */
    @RequestMapping(value = "changeOrdersMoney", method = { RequestMethod.POST })
    public String changeOrdersMoney(HttpServletRequest request, HttpServletResponse response,
                                    Integer orderid, String moneyOrder, Integer source) {
        ServiceResult<Orders> serviceResult = orderService.getOrdersById(orderid);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Orders ordersDb = serviceResult.getResult();

        if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_1)
            && !ordersDb.getOrderState().equals(Orders.ORDER_STATE_2)) {
            if (source.equals(1)) {
                return "redirect:/seller/order/orders/state1";
            } else if (source.equals(2)) {
                return "redirect:/seller/order/orders/state2";
            } else {
                return "redirect:/seller/order/orders";
            }
        }

        Orders orders = new Orders();
        orders.setId(orderid);
        orders.setMoneyOrder(new BigDecimal(moneyOrder));
        orderService.updateOrdersBySeller(orders, Orders.UPDATE_AMOUNT,
            WebSellerSession.getSellerUser(request), false);

        if (source != null) {
            if (source.equals(1)) {
                return "redirect:/seller/order/orders/state1";
            } else if (source.equals(2)) {
                return "redirect:/seller/order/orders/state2";
            }
        }
        return "redirect:/seller/order/orders";
    }

    /**
     * 发货页面
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "delivery", method = { RequestMethod.GET })
    public String delivery(HttpServletRequest request, Map<String, Object> dataMap, Integer id,
                           Integer source) {
        List<CourierCompany> list = courierCompanyService.list();
        ServiceResult<Orders> serviceResult = orderService.getOrdersById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Orders orders = serviceResult.getResult();
        dataMap.put("orders", orders);
        dataMap.put("source", source);
        dataMap.put("courierCompanylist", list);
        return "seller/order/orders/devlivery";
    }

    /**
     * 卖家发货
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "doDelivery", method = { RequestMethod.POST })
    public String doDelivery(HttpServletRequest request, String ccName, String ccId,
                             Integer ordersId, String giftNum, Integer source) {
        ServiceResult<Orders> serviceResult = orderService.getOrdersById(ordersId);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Orders ordersDb = serviceResult.getResult();
        if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_3)) {
            if (source != null && source.equals(3)) {
                return "redirect:/seller/order/orders/state3";
            } else {
                return "redirect:/seller/order/orders";
            }
        }
        Orders orders = new Orders();
        orders.setId(ordersId);
        orders.setOrderState(Orders.ORDER_STATE_4);
        orders.setLogisticsId(ccId != null && !"".equals(ccId) ? Integer.valueOf(ccId) : -1);
        orders.setLogisticsName(ccName != null && !"".equals(ccName) ? ccName.trim() : "");
        orders.setLogisticsNumber(giftNum != null && !"".equals(giftNum) ? giftNum : "");
        orders.setDeliverTime(new Date());

        orderService.updateOrdersBySeller(orders, Orders.DELIVER,
            WebSellerSession.getSellerUser(request), false);

        if (source != null && source.equals(3)) {
            return "redirect:/seller/order/orders/state3";
        }

        return "redirect:/seller/order/orders";
    }

    /**
     * 取消订单
     * @param request
     * @param id
     */
    @RequestMapping(value = "cancelorder", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cancelOrder(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Integer id) {

        ServiceResult<Orders> orderResult = orderService.getOrdersById(id);
        if (!orderResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(orderResult.getCode())) {
                throw new RuntimeException(orderResult.getMessage());
            } else {
                throw new BusinessException(orderResult.getMessage());
            }
        }
        Orders ordersDb = orderResult.getResult();

        if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_1)
            && !ordersDb.getOrderState().equals(Orders.ORDER_STATE_2)) {
            return new HttpJsonResult<Boolean>("只有待付款或待确认的订单可以执行取消操作！");
        }

        ServiceResult<Boolean> serviceResult = orderService.cancelOrderBySeller(id,
            WebSellerSession.getSellerUser(request));

        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        return jsonResult;
    }

    /**
     * 订单打印
     * 
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "print", method = { RequestMethod.GET })
    public String print(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        Orders orders = orderService.getOrderWithOPById(id,"admin").getResult();
        dataMap.put("orders", orders);
        return "seller/order/orders/ordersprint";
    }

    /**
     * 订单打印
     * 
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "printcourier", method = { RequestMethod.GET })
    public String printCourier(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {

        Orders orders = orderService.getOrderWithOPById(id,"admin").getResult();

        Seller seller = new Seller();
        SellerApply sellerApply = null;
        if (orders != null) {
            seller = sellerService.getSellerById(orders.getSellerId()).getResult();
            if (seller != null) {
                sellerApply = sellerApplyService.getSellerApplyByUser(seller.getMemberId())
                    .getResult();
            }
        }

        orders = orders == null ? new Orders() : orders;

        CourierCompany courierCompany = courierCompanyService.getCourierCompanyById(
            orders.getLogisticsId()).getResult();
        dataMap.put("courierCompany", courierCompany);

        dataMap.put("sendName", seller.getSellerName());
        courierCompany.setContent(printString(courierCompany.getContent(), "sendName",
            seller.getSellerName()));
        dataMap.put("sendUnit", "");
        courierCompany.setContent(printString(courierCompany.getContent(), "sendUnit", ""));

        String province = "";
        String city = "";
        if (!StringUtil.isEmpty(sellerApply.getCompanyProvince(), true)) {
            Regions provinceRegion = resionsService.getRegionsById(
                ConvertUtil.toInt(sellerApply.getCompanyProvince(), 0)).getResult();
            province = provinceRegion == null ? "" : provinceRegion.getRegionName();

            Regions cityRegion = resionsService.getRegionsById(
                ConvertUtil.toInt(sellerApply.getCompanyCity(), 0)).getResult();
            city = cityRegion == null ? "" : cityRegion.getRegionName();
        }
        dataMap.put("sendProvince", province);
        courierCompany
            .setContent(printString(courierCompany.getContent(), "sendProvince", province));

        dataMap.put("sendCity", city);
        courierCompany.setContent(printString(courierCompany.getContent(), "sendCity", city));

        dataMap.put("sendAdds", sellerApply.getCompanyAdd());
        courierCompany.setContent(printString(courierCompany.getContent(), "sendAdds",
            sellerApply.getCompanyAdd()));

        dataMap.put("consigneeName", orders.getName());
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeName",
            orders.getName()));

        dataMap.put("sendPhone", "");
        courierCompany.setContent(printString(courierCompany.getContent(), "sendPhone", ""));

        dataMap.put("orders", orders);

        province = "";
        city = "";
        String area = "";
        Regions provinceRegion = resionsService.getRegionsById(
            ConvertUtil.toInt(orders.getProvinceId(), 0)).getResult();
        province = provinceRegion == null ? "" : provinceRegion.getRegionName();

        Regions cityRegion = resionsService
            .getRegionsById(ConvertUtil.toInt(orders.getCityId(), 0)).getResult();
        city = cityRegion == null ? "" : cityRegion.getRegionName();

        Regions areaRegion = resionsService
            .getRegionsById(ConvertUtil.toInt(orders.getAreaId(), 0)).getResult();
        area = areaRegion == null ? "" : areaRegion.getRegionName();

        dataMap.put("consigneeProvince", province);
        dataMap.put("consigneeCity", city);
        dataMap.put("consigneeArea", area);
        dataMap.put("consigneeAdds", orders.getAddressAll() + "" + orders.getAddressInfo());
        dataMap.put("sendGoods", "商品");

        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeProvince",
            province));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeCity", city));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeArea", area));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneeAdds",
            orders.getAddressAll() + "" + orders.getAddressInfo()));
        courierCompany.setContent(printString(courierCompany.getContent(), "sendGoods", "商品"));

        List<OrdersProduct> opList = orders.getOrderProductList();

        int number = 0;
        if (opList != null && opList.size() > 0) {
            for (OrdersProduct op : opList) {
                number += op.getNumber();
            }
        }

        dataMap.put("sendNumber", number);
        dataMap.put("consigneePhone", orders.getMobile());

        courierCompany.setContent(printString(courierCompany.getContent(), "sendNumber", number
                                                                                         + ""));
        courierCompany.setContent(printString(courierCompany.getContent(), "consigneePhone",
            orders.getMobile()));

        return "seller/order/orders/ordersprintorder";
    }

    /**
     * 
     * @param content 打印的文档
     * @param str1 要替换的值
     * @param str2 替换的值
     */
    private String printString(String content, String str1, String str2) {
        String string = "\\$\\{" + str1 + "\\}";
        if (content == null || str1 == null || str1 == null) {
            return "";
        }
        return content.replaceAll(string, str2);
    }

    public static void main(String[] args) {
        String ss = "3245234523445${asd}123132";
        ss = ss.replaceAll("\\$\\{asd\\}", "3456");
        System.out.println(ss);
    }
    
    
    /**
     * 三方仓储订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state7", method = { RequestMethod.GET })
    public String listState7(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                     throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/order/orders/orderslist7";
    }
    
    /**
     * 三方仓储申请发货
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "applysendgoods", method = { RequestMethod.GET })
    public  String applysendgoods(HttpServletRequest request,
                                                           Map<String, Object> dataMap,Integer orderId) {
    	 ServiceResult<BookingSendGoodsVO> serviceResult = orderService.listUserInfo(orderId);
    	 ServiceResult<List<SendingGoodsVO>> serviceResult1 = orderService.listGoodsInfo(orderId);
    	 if (!serviceResult.getSuccess() || !serviceResult1.getSuccess() ) {
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                 throw new RuntimeException(serviceResult.getMessage());
             } else {
                 throw new BusinessException(serviceResult.getMessage());
             }
         }
    	 dataMap.put("orderId", orderId);
    	 dataMap.put("userInfo", serviceResult.getResult());
    	 dataMap.put("goodsInfo", serviceResult1.getResult());
    	return "seller/order/orders/applysend";
    }
    
    
    /**
     * 保存 发货订单
     */
    @RequestMapping(value="create",method = { RequestMethod.POST})
    public @ResponseBody HttpJsonResult<Object> saveSendGoods(HttpServletRequest request,
    		 Map<String, Object> dataMap){
    	 HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
    	 SellerUser sellerUser = WebSellerSession.getSellerUser(request);
         Map<String, String[]> map =request.getParameterMap();
         if (null == sellerUser) {
             jsonResult.setMessage("登录超时，请重新登录");
             jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
             return jsonResult;
         }
         ServiceResult<Boolean > serviceResult = bookingSendGoodsService.saveSendGoods(map);
         if (!serviceResult.getSuccess()) {
             jsonResult.setMessage(serviceResult.getMessage());
             return jsonResult;
         }
    	return jsonResult;
    }
    
    /**
     * 查询三方仓储的订单
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "listInStore", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Orders>> listInStore(HttpServletRequest request,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        ServiceResult<List<Orders>> serviceResult = orderService.getSellerOrdersInStore(queryMap, pager,sellerUser);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Orders>> jsonResult = new HttpJsonResult<List<Orders>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }
}
