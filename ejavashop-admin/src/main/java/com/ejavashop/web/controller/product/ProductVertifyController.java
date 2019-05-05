package com.ejavashop.web.controller.product;

import java.io.PrintWriter;
import java.util.HashMap;
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
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.OrderReceiptDetail;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.product.IOrderReceiptService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

@Controller              
@RequestMapping(value = "admin/product/vertify")
public class ProductVertifyController extends BaseController{
    
    @Resource
    private IOrderReceiptService        orderReceiptService;
    @Resource
    private ISellerService              sellerService;
    
    @RequestMapping(value = "" ,method = { RequestMethod.GET })
    public String add(HttpServletRequest request,Map<String, Object> dataMap){
        System.out.println("页面初始化开始......");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        Map<String, String> sellerMap = new HashMap<String, String>();
        sellerMap.put("q_auditStatus", "2");
        ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/product/probaby/productvertify";
    }
    
    
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrderReceipt>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
         System.out.println("商品列表查询");
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<OrderReceipt>> serviceResult = orderReceiptService.page(queryMap,
             pager);
         HttpJsonResult<List<OrderReceipt>> jsonResult = new HttpJsonResult<List<OrderReceipt>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
    
    @RequestMapping(value = "add", method = { RequestMethod.GET})
    public @ResponseBody HttpJsonResult<List<OrderReceiptDetail>> add(HttpServletRequest request,
                                                                             HttpServletResponse response,
                                                                             String ordersId) {
        System.out.println("OrderReceiptDetail开始查询...");
        ServiceResult<List<OrderReceiptDetail>> res = orderReceiptService
                .getReceiptProductDetailByOrdersId(ordersId);
        HttpJsonResult<List<OrderReceiptDetail>> jsonResult = new HttpJsonResult<List<OrderReceiptDetail>>();
        jsonResult.setRows(res.getResult());
        jsonResult.setTotal(res.getResult().size());
        return jsonResult;
    }
    
    @RequestMapping(value = "audit", method = { RequestMethod.GET})
    public void auditGoods(HttpServletRequest request, HttpServletResponse response, String type,
                           String orders_id ) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
            if ("".equals(orders_id))
                throw new BusinessException("请选择要操作的商品");
            if (type == null)
                throw new BusinessException("未知操作");
            pw = response.getWriter();
            orderReceiptService.auditSendGoods(orders_id, type);
            } catch (Exception e) {
                log.error("[admin][AdminBookingSendGoodsController] auditGoods exception", e);
                msg = e.getMessage();
                e.printStackTrace();
             }
                pw.print(msg);
             } 
    
}
