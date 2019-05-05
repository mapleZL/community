package com.ejavashop.web.controller.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.Product;
import com.ejavashop.service.member.IMemberProductExchangeService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 卖家换货controller
 * 
 * @Filename: SellerProductExchangeController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/order/productExchange")
public class SellerProductExchangeController extends BaseController {

    @Resource
    private IMemberProductExchangeService memberProductExchangeService;
    @Resource
    private IProductService               productservice;
    @Resource
    private IOrdersService                ordersService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "seller/order/productexchange/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductExchange>> list(HttpServletRequest request,
                                                                          Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        ServiceResult<List<MemberProductExchange>> serviceResult = memberProductExchangeService
            .getMemberProductExchanges(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberProductExchange>> jsonResult = new HttpJsonResult<List<MemberProductExchange>>();
        jsonResult.setRows((List<MemberProductExchange>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 审核
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        ServiceResult<MemberProductExchange> result = memberProductExchangeService
            .getMemberProductExchangeById(id);
        if (!result.getSuccess() || result.getResult() == null) {
            return "seller/order/productexchange/list";
        }
        MemberProductExchange exchange = result.getResult();
        ServiceResult<Product> prdResult = productservice.getProductById(exchange.getProductId());
        if (prdResult.getSuccess() && prdResult.getResult() != null) {
            exchange.setProductName(prdResult.getResult().getName1());
        }

        ServiceResult<Orders> orderResult = ordersService.getOrdersById(exchange.getOrderId());
        if (orderResult.getSuccess() && orderResult.getResult() != null) {
            exchange.setOrderSn(orderResult.getResult().getOrderSn());
        }
        dataMap.put("obj", exchange);
        return "seller/order/productexchange/edit";
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public void audit(HttpServletRequest request, HttpServletResponse response, Integer id,
                      Integer type, String remark) {
        //        if (type == null || "".equals(type))
        //            throw new IllegalArgumentException("type is null");
        //        ServiceResult<MemberProductExchange> serviceResult = memberProductExchangeService
        //            .getMemberProductExchangeById(id);
        //
        //        if (!serviceResult.getSuccess()) {
        //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
        //                throw new RuntimeException(serviceResult.getMessage());
        //            } else {
        //                throw new BusinessException(serviceResult.getMessage());
        //            }
        //        }

        MemberProductExchange ex = new MemberProductExchange();
        ex.setId(id);
        ex.setRemark(remark);
        ex.setState(type);

        memberProductExchangeService.updateMemberProductExchange(ex);

    }
}
