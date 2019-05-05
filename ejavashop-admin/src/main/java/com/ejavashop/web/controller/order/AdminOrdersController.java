package com.ejavashop.web.controller.order;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.excel.CellConfig;
import com.ejavashop.core.excel.ExcelConfig;
import com.ejavashop.core.excel.ExcelConfig.CellType;
import com.ejavashop.core.excel.ExcelManager;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersAndOrdersProductVO;
import com.ejavashop.entity.order.OrdersError;
import com.ejavashop.entity.order.OrdersProductError;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrdersErrorService;
import com.ejavashop.service.order.IOrdersProductErrorService;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.ISendGoodsRecordService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.vo.order.BookingSendGoodsVO;
import com.ejavashop.vo.order.SendingGoodsVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 卖家订单controller
 *                       
 * @Filename: AdminOrdersController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/order/orders")
public class AdminOrdersController extends BaseController {
    @Resource(name = "ordersService")
    private IOrdersService orderService;
    Logger                 log = Logger.getLogger(this.getClass());
    
    @Resource(name = "bookingSendGoodsService")
	 IBookingSendGoodsService bookingSendGoodsService;
    
    @Resource(name = "sendGoodsRecordService")
     ISendGoodsRecordService sendGoodsRecordService;
    
    @Resource(name = "ordersProductService")
    IOrdersProductService ordersProductService;
    
    @Resource(name = "ordersErrorService")
    IOrdersErrorService  ordersErrorService;
    
    @Resource(name = "ordersProductErrorService")
    IOrdersProductErrorService  ordersProductErrorService;

    @Resource(name = "sellerService")
    ISellerService sellerService;
    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        //查询供应商列表
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellerListAll();
        dataMap.put("sellerList", serviceResult.getResult());
        return "admin/order/orders/orderslist";
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
        return "admin/order/orders/orderslist1";
    }

    /**
     * 标签设置
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setLabel", method = { RequestMethod.GET })
    public String setLabel(HttpServletRequest request, ModelMap dataMap, Integer orderId,String type)
                                                                                         throws Exception {
        ServiceResult<Orders> orderResult = orderService.getOrderWithOPById(orderId,"admin");
        dataMap.put("orders", orderResult.getResult());
        dataMap.put("type", type);
        return "admin/order/orders/labelset";
    }
    
    /**
     * 修改订单支付方式和支付时间
     * @param request
     * @param dataMap
     * @param ordersId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "modifyInfo", method = { RequestMethod.GET })   
    public String modifyInfo(HttpServletRequest request, ModelMap dataMap, Integer ordersId)
                                                                                        throws Exception {
        ServiceResult<Orders> orderResult = orderService.getOrdersById(ordersId);
        dataMap.put("orders", orderResult.getResult());
        return "admin/order/orders/modifyInfo";
    }
    
    @RequestMapping(value = "modifyOrdersInfo", method = { RequestMethod.GET })
    public String modifyOrdersInfo(HttpServletRequest request, ModelMap dataMap ,Integer ordersId ,String q_paymentCode,String q_createTime)throws Exception {
        Orders orders = new Orders();
        orders.setId(ordersId);
        orders.setPaymentCode(q_paymentCode);
        if(q_paymentCode.equals(Orders.PAYMENT_CODE_ONLINE)){
            orders.setPaymentName("在线支付");
            orders.setOrderState(Orders.ORDER_STATE_1);
            orders.setPaymentStatus(Orders.PAYMENT_STATUS_0);
        }else{
            orders.setPaymentName("线下打款");
            orders.setOrderState(Orders.ORDER_STATE_2);
            orders.setPaymentStatus(Orders.PAYMENT_STATUS_3);
        }
        orders.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(q_createTime));
        try {
            ServiceResult<Integer> serviceResult = orderService.updateOrders(orders);
        } catch (Exception e) {
            throw new BusinessException("更新失败，请联系后台管理员");
        }
        ServiceResult<Orders> orderResult = orderService.getOrdersById(ordersId);
        dataMap.put("orders", orderResult.getResult());
        return "admin/order/orders/modifyInfo";
}
    
    @RequestMapping(value = "ordersdetail", method = { RequestMethod.GET })
    public String ordersdetail(HttpServletRequest request, ModelMap dataMap, Integer orderId,String type)
                                                                                         throws Exception {
        ServiceResult<Orders> orderResult = orderService.getOrderWithOPById(orderId,"admin");
        dataMap.put("orders", orderResult.getResult());
        dataMap.put("type", type);
        return "admin/order/orders/ordersdetail";
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
        return "admin/order/orders/labeldialog";
    }

    /**
     * 标签设置
     * @param request
     * @param dataMap
     * @param orderId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setPrice", method = { RequestMethod.GET })
    public String setPrice(HttpServletRequest request, ModelMap dataMap, Integer orderId)
                                                                                         throws Exception {
        ServiceResult<Orders> orderResult = orderService.getOrderWithOPById(orderId,"admin");
        dataMap.put("orders", orderResult.getResult());
        return "admin/order/orders/priceset";
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
                                                              Integer orderId,String submitType) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            ServiceResult<Boolean> serviceResult = orderService.saveOrdersProductPrice(orderId,
                opinfo,submitType);
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
     * 退货保存
     * @param request
     * @param dataMap
     * @param opinfo
     * @param orderId
     * @param submitType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setNumberSave", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> setNumberSave(HttpServletRequest request,
                                                              ModelMap dataMap, String opinfo,
                                                              Integer orderId,String returnInfo) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        try {
            ServiceResult<Boolean> serviceResult = orderService.saveOrdersReturn(orderId,opinfo,returnInfo,user.getId());
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
        return "admin/order/orders/orderslist2";
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
        return "admin/order/orders/orderslist3";
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
        return "admin/order/orders/orderslist4";
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
        return "admin/order/orders/orderslist5";
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
        return "admin/order/orders/orderslist6";
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
        return "admin/order/orders/orderslist7";
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
    	return "admin/order/orders/applysend";
    }
    
    
    /**
     * 保存 发货订单
     */
    @RequestMapping(value="create",method = { RequestMethod.POST})
    public @ResponseBody HttpJsonResult<Object> saveSendGoods(HttpServletRequest request,
    		 Map<String, Object> dataMap){
    	 HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
         SystemAdmin user = WebAdminSession.getAdminUser(request);
         Map<String, String[]> map =request.getParameterMap();
         if (null == user) {
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
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Orders>> list(HttpServletRequest request,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String order = request.getParameter("order") == null ? "" : (String)request.getParameter("order");
        String sort = request.getParameter("sort") == null ? "" : (String)request.getParameter("sort");
        queryMap.put("sort", sort);
        queryMap.put("order", order);
        if(queryMap.get("q_moneyDiscount")!=null&&queryMap.get("q_moneyDiscount").equals("1")){
            queryMap.put("q_moneyDiscount", "1");
        }else{
            queryMap.put("q_moneyDiscount", null);
        }
        if((queryMap.get("q_number_s")!=null&&!queryMap.get("q_number_s").equals(""))||(queryMap.get("q_number_e")!=null&&!queryMap.get("q_number_e").equals(""))){
            queryMap.put("num_state", "1");
        }
        if(queryMap.get("q_logistics")!=null&&queryMap.get("q_logistics").equals("1")&&queryMap.get("q_logistics").equals("5")){
            queryMap.put("q_logisticsIdType", "1");
        }
        if(queryMap.get("q_logistics")!=null&&!"".equals(queryMap.get("q_logistics"))){
            queryMap.put("q_logisticsId", queryMap.get("q_logistics"));
        }
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
     * 根据异常单 查询原单
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "errororders", method = { RequestMethod.GET })
    public String  errororders(HttpServletRequest request,
                                                           Map<String, Object> dataMap,String ordersSn) {
    	dataMap.put("ordersSn", ordersSn);
    	return "admin/order/orders/orderserror";
    }
    
    @RequestMapping(value = "errororderslist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrdersError>>  errororderslist(HttpServletRequest request,
                                                           Map<String, Object> dataMap,String ordersSn) {
    	Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
    	ServiceResult<List<OrdersError>> ordersErrorListServiceResult = ordersErrorService.getOrdersErrorByOrdersSn(ordersSn, queryMap);
    	List<OrdersError> ordersErrorList =  ordersErrorListServiceResult.getResult();
    	HttpJsonResult<List<OrdersError>> jsonResult = new HttpJsonResult<List<OrdersError>>();
        jsonResult.setRows(ordersErrorList);
        return jsonResult;
    }
    
    
    @RequestMapping(value = "geterrorordersproduct", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrdersProductError>> geterrorordersproduct(HttpServletRequest request,
                                                                              HttpServletResponse response,
                                                                              String orderserrorsn) {
        ServiceResult<List<OrdersProductError>> res = ordersProductErrorService
            .geterrorordersproduct(orderserrorsn);
        HttpJsonResult<List<OrdersProductError>> json = new HttpJsonResult<List<OrdersProductError>>();
        json.setRows(res.getResult());
        json.setTotal(res.getResult().size());
        return json;
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
        ServiceResult<List<Orders>> serviceResult = orderService.getordersInStore(queryMap, pager);
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
     * 确认收款
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "submitpay", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> submitPay(HttpServletRequest request,
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

        if (ordersDb.getPaymentStatus().equals(Orders.PAYMENT_STATUS_1)) {
            return new HttpJsonResult<Boolean>("该订单已经付款，请勿重复操作！");
        }

        if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_4)
            && !ordersDb.getOrderState().equals(Orders.ORDER_STATE_5)) {
            return new HttpJsonResult<Boolean>("已发货或者已完成的订单才能确认收款。");
        }

        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
        Orders orders = new Orders();
        orders.setId(id);
        //已付款
        orders.setPaymentStatus(Orders.PAYMENT_STATUS_1);
        //Terry 20160825
//        if (orders.getPayTime() == null)
//            orders.setPayTime(new Date());
        orders.setCodconfirmId(systemAdmin.getId());
        orders.setCodconfirmName(systemAdmin.getName());
        orders.setCodconfirmState(Orders.CODCONFIRM_STATE_2);
        orders.setMoneyPaidReality(ordersDb.getMoneyOrder()
            .subtract(ordersDb.getMoneyPaidBalance()).subtract(ordersDb.getMoneyIntegral()));

        ServiceResult<Integer> serviceResult = orderService.updateOrdersByAdmin(orders,
            Orders.SUBMIT_PAY, systemAdmin, false);

        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        return jsonResult;
    }
    /**
     * 确认收款
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "gotoSubmitpay", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> gotoSubmitpay(HttpServletRequest request,
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
    	if(ordersDb.getPaymentCode().equals("OFFSEND")){//线下打款订单
    		SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    		Orders orders = new Orders();
    		orders.setId(id);
    		orders.setOrderState(Orders.ORDER_STATE_2);//修改订单状态为：待确认订单
    		orders.setCodconfirmId(systemAdmin.getId());
    		orders.setCodconfirmName(systemAdmin.getName());
    		orders.setCodconfirmState(Orders.CODCONFIRM_STATE_1);
    		ServiceResult<Integer> serviceResult = orderService.updateOrdersByAdmin(orders,
    				Orders.SUBMIT_PAY, systemAdmin, false);
    		
    		HttpJsonResult<Boolean> jsonResult = null;
    		if (serviceResult.getSuccess()) {
    			jsonResult = new HttpJsonResult<Boolean>();
    		} else {
    			jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    		}
    		return jsonResult;
    	}else if(ordersDb.getPaymentCode().equals("BALANCE")){//余额支付
    		SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    		Orders orders = new Orders();
    		orders.setId(id);
    		orders.setOrderState(Orders.ORDER_STATE_2);//修改订单状态为：待确认订单
    		orders.setCodconfirmId(systemAdmin.getId());
    		orders.setCodconfirmName(systemAdmin.getName());
    		orders.setCodconfirmState(Orders.CODCONFIRM_STATE_1);
    		
    		ServiceResult<Integer> serviceResult = orderService.updateOrdersByAdmin(orders,
    				Orders.SUBMIT_PAY, systemAdmin, false);
    		
    		HttpJsonResult<Boolean> jsonResult = null;
    		if (serviceResult.getSuccess()) {
    			jsonResult = new HttpJsonResult<Boolean>();
    		} else {
    			jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    		}
    		return jsonResult;
    	}else{
    		SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    		Orders orders = new Orders();
    		orders.setId(id);
    		//待付款
    		orders.setPaymentStatus(Orders.PAYMENT_STATUS_0);
    		orders.setCodconfirmId(systemAdmin.getId());
    		orders.setCodconfirmName(systemAdmin.getName());
    		orders.setCodconfirmState(Orders.CODCONFIRM_STATE_1);
//    	orders.setMoneyPaidReality(ordersDb.getMoneyOrder()
//    			.subtract(ordersDb.getMoneyPaidBalance()).subtract(ordersDb.getMoneyIntegral()));
    		
    		ServiceResult<Integer> serviceResult = orderService.updateOrdersByAdmin(orders,
    				Orders.SUBMIT_PAY, systemAdmin, false);
    		
    		HttpJsonResult<Boolean> jsonResult = null;
    		if (serviceResult.getSuccess()) {
    			jsonResult = new HttpJsonResult<Boolean>();
    		} else {
    			jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    		}
    		return jsonResult;
    	}
    	
    }
    /**
     * 线下打款   确认收款
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "confirm", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> confirm(HttpServletRequest request,
    		HttpServletResponse response, Integer id,String offsendNum) {
    	
    	ServiceResult<Orders> orderResult = orderService.getOrdersById(id);
    	if (!orderResult.getSuccess()) {
    		if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(orderResult.getCode())) {
    			throw new RuntimeException(orderResult.getMessage());
    		} else {
    			throw new BusinessException(orderResult.getMessage());
    		}
    	}
    	Orders ordersDb = orderResult.getResult();
    	/* Terry 20160802
    	if (ordersDb.getPaymentStatus().equals(Orders.PAYMENT_STATUS_1)) {
    		return new HttpJsonResult<Boolean>("该订单已经付款，请勿重复操作！");
    	}*/
    	
    	SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    	Orders orders = new Orders();
    	orders.setId(id);
    	//已付款
    	//待发货
        orders.setOrderState(Orders.ORDER_STATE_3);
        orders.setTradeSn(offsendNum);
        
    	orders.setPaymentStatus(Orders.PAYMENT_STATUS_1);
        //Terry 20160825
        if (ordersDb.getPayTime() == null){
            orders.setPayTime(new Date());
        }
    	orders.setCodconfirmId(systemAdmin.getId());
    	orders.setCodconfirmName(systemAdmin.getName());
    	orders.setCodconfirmState(Orders.CODCONFIRM_STATE_2);
    	orders.setMoneyPaidReality(ordersDb.getMoneyOrder()
    			.subtract(ordersDb.getMoneyPaidBalance()).subtract(ordersDb.getMoneyIntegral()));
    	
    	ServiceResult<Integer> serviceResult = orderService.updateOrdersByAdmin(orders,
    			Orders.SUBMIT_PAY, systemAdmin, false);
    	
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
        return "admin/order/orders/ordersprint";
    }
    
     
     @RequestMapping(value = "setNumber", method = { RequestMethod.GET })
     public String setNumber(HttpServletRequest request, ModelMap dataMap, Integer orderId)
                                                                                          throws Exception {
         ServiceResult<Orders> orderResult = orderService.getOrderWithOPById(orderId,"admin");
         dataMap.put("orders", orderResult.getResult());
         return "admin/order/orders/numberset";
     }
     
     /**
      * 导出订单信息为Excel文件
      * @param request
      * @param response
      * @param userAgent
      */
     @RequestMapping(value = "doexport", method = { RequestMethod.GET })
     public void doExportExcel(HttpServletRequest request, HttpServletResponse response,
                               @RequestHeader(value = "user-agent") String userAgent) {

         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

         String busiErrMsg = "";
         if(queryMap.get("q_moneyDiscount")!=null&&queryMap.get("q_moneyDiscount").equals("1")){
             queryMap.put("q_moneyDiscount", "1");
         }else{
             queryMap.put("q_moneyDiscount", null);
         }
         if((queryMap.get("q_number_s")!=null&&!queryMap.get("q_number_s").equals(""))||(queryMap.get("q_number_e")!=null&&!queryMap.get("q_number_e").equals(""))){
             queryMap.put("num_state", "1");
         }
         if(queryMap.get("q_logistics")!=null&&queryMap.get("q_logistics").equals("1")&&queryMap.get("q_logistics").equals("5")){
             queryMap.put("q_logisticsIdType", "1");
         }
         if(queryMap.get("q_logistics")!=null&&!"".equals(queryMap.get("q_logistics"))){
             queryMap.put("q_logisticsId", queryMap.get("q_logistics"));
         }
         
         ServiceResult<List<OrdersAndOrdersProductVO>> ordersResult = orderService.getOrdersAndOrdersProductVO(queryMap,null);
         if (!ordersResult.getSuccess()) {
             busiErrMsg = ordersResult.getMessage();
         }
         if (ordersResult.getResult() == null) {
             busiErrMsg = "订单信息为空。";
         }

         if (!StringUtil.isEmpty(busiErrMsg, true)) {
             try {
                 Cookie msgCookie = new Cookie("busiErrMsg", URLEncoder.encode(busiErrMsg, "utf-8"));
                 msgCookie.setPath("/");
                 response.addCookie(msgCookie);
                 Cookie fileDownloadCookie = new Cookie("fileDownload", "false");
                 fileDownloadCookie.setPath("/");
                 response.addCookie(fileDownloadCookie);
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             }
         } else {
             List<OrdersAndOrdersProductVO> list = ordersResult.getResult();
             //订单总表
             this.exportOrders(response, userAgent, list, null);
//           订单明细
//           this.exportOrdersProduct(response, userAgent, list, null);
         }
     }
     
     
     private void exportOrdersProduct(HttpServletResponse response, String userAgent,
                                     List<OrdersAndOrdersProductVO> list, Object object) {
        
         ExcelConfig<OrdersAndOrdersProductVO> config = new ExcelConfig<OrdersAndOrdersProductVO>();
         config.setData(list);
         config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
         config.setFileName("订单总表列表"+ "-"
                            + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         config.setLineConfig(getLineConfigOrdersProductDetails());
         config.setSheetName("订单明细表");
         config.setUserAgent(userAgent);
         ExcelManager.export(response, config);
    }

    /**
      * 导出excel数据
      * @param response
      * @param userAgent
      * @param result
      * @return
      */
     private void exportOrders(HttpServletResponse response, String userAgent,
                         List<OrdersAndOrdersProductVO> couponUserList, Map<String, String> queryMap) {
         ExcelConfig<OrdersAndOrdersProductVO> config = new ExcelConfig<OrdersAndOrdersProductVO>();
         config.setData(couponUserList);
         config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
         config.setFileName("全部明细列表"+ "-"
                            + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         config.setLineConfig(getLineConfig());
//         config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         config.setSheetName("订单总表");
         config.setUserAgent(userAgent);
         ExcelManager.export(response, config);
     }

     /**
      * 取得行配置
      * @return
      */
     private LinkedHashMap<String, CellConfig> getLineConfig() {
         LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
         //用户ID
         CellConfig memberIdConfig = new CellConfig("用户ID");
         config.put("memberId", memberIdConfig);
         //手机号
         CellConfig memberNameConfig = new CellConfig("手机号");
         config.put("memberName", memberNameConfig);
         //订单号
         CellConfig orderSnConfig = new CellConfig("订单号");
         config.put("orderSn", orderSnConfig);
         //创建时间
         CellConfig createTimeConfig = new CellConfig("创建时间");
         createTimeConfig.setCellType(CellType.DATE);
         config.put("createTime", createTimeConfig);
         //付款时间
         CellConfig payTimeConfig = new CellConfig("付款时间");
         payTimeConfig.setCellType(CellType.DATE);
         config.put("payTime", payTimeConfig);
         //订单类型
         CellConfig orderTypeConfig = new CellConfig("订单类型");
         config.put("orderType", orderTypeConfig);
         //订单状态
         CellConfig orderStateConfig = new CellConfig("订单状态");
         config.put("orderState", orderStateConfig);
         //订单数量
         CellConfig numberConfig = new CellConfig("订单数量");
         config.put("number", numberConfig);
         //订单金额
         CellConfig moneyOrderConfig = new CellConfig("订单金额");
         config.put("moneyOrder", moneyOrderConfig);
         //商品总金额
         CellConfig moneyProductConfig = new CellConfig("商品总金额");
         config.put("moneyProduct", moneyProductConfig);
         //红包金额
         CellConfig moneyCouponConfig = new CellConfig("红包金额");
         config.put("moneyCoupon", moneyCouponConfig);
         //商品单价
         CellConfig moneyPriceConfig = new CellConfig("商品单价");
         config.put("moneyPrice", moneyPriceConfig);
         //网单金额
         CellConfig moneyAmountConfig = new CellConfig("网单金额");
         config.put("moneyAmount", moneyAmountConfig);
         //单品立减
         CellConfig moneyActSingleConfig = new CellConfig("单品立减金额");
         config.put("moneyActSingle", moneyActSingleConfig);
         //运费
         CellConfig moneyLogisticsConfig = new CellConfig("运费");
         config.put("moneyLogistics", moneyLogisticsConfig);
         //套餐费
         CellConfig servicePriceConfig = new CellConfig("套餐费");
         config.put("servicePrice", servicePriceConfig);
         //套餐金额和
         CellConfig packageFeeTotalConfig = new CellConfig("套餐金额和");
         config.put("packageFeeTotal", packageFeeTotalConfig);
         //标签费
         CellConfig labelPriceConfig = new CellConfig("标签费");
         config.put("labelPrice", labelPriceConfig);
         //标签金额和
         CellConfig labelFeeTotalConfig = new CellConfig("标签费金额和");
         config.put("labelFeeTotal", labelFeeTotalConfig);
         //支付方式
         CellConfig paymentNameConfig = new CellConfig("支付方式");
         config.put("paymentName", paymentNameConfig);
         //配送方式
         CellConfig logisticsNameConfig = new CellConfig("配送方式");
         config.put("logisticsName", logisticsNameConfig);
         //住址
         CellConfig addressInfoConfig = new CellConfig("住址");
         config.put("addressInfo", addressInfoConfig);
         //用户名
         CellConfig nameConfig = new CellConfig("用户名");
         config.put("name", nameConfig);
         //联系电话
         CellConfig mobileConfig = new CellConfig("联系电话");
         config.put("mobile", mobileConfig);
         //商家ID
         CellConfig sellerIdConfig = new CellConfig("商家ID");
         config.put("sellerId", sellerIdConfig);
         //商品SKU
         CellConfig productSkuConfig = new CellConfig("商品SKU");
         config.put("productSku", productSkuConfig);
         //商品名称
         CellConfig productNameConfig = new CellConfig("商品SKU");
         config.put("productName", productNameConfig);
         
         return config;
     }
     
     private LinkedHashMap<String, CellConfig> getLineConfigOrdersProductDetails() {
         LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
         //订单号
         CellConfig ordersSnConfig = new CellConfig("订单号");
         config.put("ordersSn", ordersSnConfig);
         //买家用户名
         CellConfig memberNameConfig = new CellConfig("买家用户名");
         config.put("memberName", memberNameConfig);
         //货主
         CellConfig sellerIdConfig = new CellConfig("货主");
         config.put("sellerId", sellerIdConfig);
         //SKU
         CellConfig skuConfig = new CellConfig("SKU");
         config.put("sku", skuConfig);
         //数量
         CellConfig numberConfig = new CellConfig("数量");
         config.put("number", numberConfig);
         //单价
         CellConfig moneyPriceConfig = new CellConfig("单价");
         config.put("moneyPrice", moneyPriceConfig);
         //商品金额
         CellConfig moneyAmountConfig = new CellConfig("商品金额");
         config.put("moneyAmount", moneyAmountConfig);
         //标签费用
         CellConfig labelFeeTotalConfig = new CellConfig("标签费用");
         config.put("labelFeeTotal", labelFeeTotalConfig);
         //加工费用
         CellConfig packageFeeTotalConfig = new CellConfig("加工费用");
         config.put("packageFeeTotal", packageFeeTotalConfig);
         //立减金额
         CellConfig moneyActSingleConfig = new CellConfig("立减金额");
         config.put("moneyActSingle", moneyActSingleConfig);
         //下单时间
         CellConfig createTimeConfig = new CellConfig("下单时间");
         createTimeConfig.setCellType(CellType.DATE);
         config.put("createTime", createTimeConfig);
         //支付时间
         CellConfig payTimeConfig = new CellConfig("支付时间");
         payTimeConfig.setCellType(CellType.DATE);
         config.put("payTime", payTimeConfig);
         //配送方式
         CellConfig logisticsNameConfig = new CellConfig("快递方式");
         config.put("logisticsName", logisticsNameConfig);
         //联系人
         CellConfig nameConfig = new CellConfig("联系人");
         config.put("name", nameConfig);
         //联系电话
         CellConfig mobileConfig = new CellConfig("联系电话");
         config.put("mobile", mobileConfig);
         //地址
         CellConfig addressInfoConfig = new CellConfig("地址");
         config.put("addressInfo", addressInfoConfig);
         
         return config;
     }
}
