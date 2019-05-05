package com.ejavashop.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.service.member.IMemberProductBackService;
import com.ejavashop.service.member.IMemberProductExchangeService;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.seller.ISellerComplaintService;
import com.ejavashop.vo.seller.FrontSellerComplaintVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 客户服务：申诉
 *                       
 * @Filename: MemberComplainController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberComplainController extends BaseController {

    @Resource
    private IOrdersService                ordersService;

    @Resource
    private IOrdersProductService         ordersProductService;

    @Resource
    private IProductService               productService;

    @Resource
    private IMemberProductBackService     memberProductBackService;

    @Resource
    private IMemberProductExchangeService memberProductExchangeService;

    @Resource
    private ISellerComplaintService       sellerComplaintService;

    /**
     * 跳转到 申诉列表界面
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/complaint.html", method = { RequestMethod.GET })
    public String toComplainApply(HttpServletRequest request, HttpServletResponse response,
                                  Map<String, Object> dataMap) {

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<FrontSellerComplaintVO>> serviceResult = sellerComplaintService
            .getComplaintListWithPrdAndOp(pager, member.getId());

        dataMap.put("complaintList", serviceResult.getResult());
        dataMap.put("complaintCount", pager.getRowsCount());
        dataMap.put("pageSize", pager.getPageSize());

        return "h5v1/member/service/complaint/complaint";
    }

    /**
     * ajax异步加载更多
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/morecomplaint.html", method = { RequestMethod.GET })
    public String moreComplainApply(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap, Integer pageIndex) {

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<FrontSellerComplaintVO>> serviceResult = sellerComplaintService
            .getComplaintListWithPrdAndOp(pager, member.getId());

        dataMap.put("complaintList", serviceResult.getResult());

        return "h5v1/member/service/complaint/complaintlist";
    }

    /**
     * 跳转到 申诉申请录入界面 
     * @param request
     * @param response
     * @param dataMap
     * @param orderProductId 网单ID
     * @param productBackId  退货申请ID
     * @param productExchangeId 换货申请ID
     * @param orderId 订单ID
     * @return
     */
    @RequestMapping(value = "/addcomplaint.html", method = { RequestMethod.GET })
    public String toComplainApply(HttpServletRequest request, HttpServletResponse response,
                                  Map<String, Object> dataMap,
                                  @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                  Integer productBackId, Integer productExchangeId,
                                  @RequestParam(value = "orderId", required = true) Integer orderId) {

        ServiceResult<OrdersProduct> serviceResult = ordersProductService
            .getOrdersProductWithImgById(orderProductId);

        //查询订单信息
        ServiceResult<Orders> orderResult = ordersService.getOrderWithOPById(orderId,"front");

        OrdersProduct ordersProduct = serviceResult.getResult();
        if (ordersProduct != null) {
            ServiceResult<Product> productResult = productService
                .getProductById(ordersProduct.getProductId());
            dataMap.put("product", productResult.getResult());
        }

        dataMap.put("order", orderResult.getResult());
        dataMap.put("ordersProduct", serviceResult.getResult());
        dataMap.put("productBackId", productBackId);
        dataMap.put("productExchangeId", productExchangeId);

        return "h5v1/member/service/complaint/complaintadd";
    }

    /**
     * 申诉提交
     * @param request
     * @param response
     * @param dataMap
     * @param sellerComplaint
     * @return
     */
    @RequestMapping(value = "/savecomplaint.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<SellerComplaint> complainSubmit(MultipartHttpServletRequest request,
                                                                        HttpServletResponse response,
                                                                        Map<String, Object> dataMap,
                                                                        SellerComplaint sellerComplaint) {
        Member member = WebFrontSession.getLoginedUser(request);
        HttpJsonResult<SellerComplaint> jsonResult = new HttpJsonResult<SellerComplaint>();

        sellerComplaint.setImage(UploadUtil.getInstance().uploadFile2ImageServer("pic", request));
        //保存申诉
        ServiceResult<SellerComplaint> serviceResult = sellerComplaintService
            .saveSellerComplaint(member, sellerComplaint);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<SellerComplaint>(serviceResult.getMessage());
            }
        }

        return jsonResult;
    }

    /**
     * 跳转到 申诉详情页面
     * @param request
     * @param response
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "/complaintdetail.html", method = { RequestMethod.GET })
    public String toComplainApplyDetail(HttpServletRequest request, HttpServletResponse response,
                                        Map<String, Object> dataMap,
                                        @RequestParam(value = "id", required = true) Integer id) {

        //查询申诉信息
        ServiceResult<SellerComplaint> scResult = sellerComplaintService.getSellerComplaintById(id);

        //根据申诉信息取退换货信息
        if (scResult.getResult() != null) {
            SellerComplaint sellerComplaint = scResult.getResult();
            //            Integer backId = sellerComplaint.getProductBackId();
            //            Integer exchangeId = sellerComplaint.getProductExchangeId();
            //            if (backId != null && backId != 0) {
            //                MemberProductBack memberProductBack = (memberProductBackService
            //                    .getMemberProductBackById(backId)).getResult();
            //                dataMap.put("backInfo", memberProductBack);
            //            } else if (exchangeId != null && exchangeId != 0) {
            //                MemberProductExchange memberProductExchange = (memberProductExchangeService
            //                    .getMemberProductExchangeById(exchangeId)).getResult();
            //                dataMap.put("exchangeInfo", memberProductExchange);
            //            }

            ServiceResult<OrdersProduct> serviceResult = ordersProductService
                .getOrdersProductWithImgById(sellerComplaint.getOrderProductId());

            OrdersProduct ordersProduct = serviceResult.getResult();
            if (ordersProduct != null) {
                ServiceResult<Product> productResult = productService
                    .getProductById(ordersProduct.getProductId());
                dataMap.put("product", productResult.getResult());
            }

            dataMap.put("ordersProduct", ordersProduct);
            dataMap.put("complaint", sellerComplaint);
        }

        return "h5v1/member/service/complaint/complaintdetail";
    }

}
