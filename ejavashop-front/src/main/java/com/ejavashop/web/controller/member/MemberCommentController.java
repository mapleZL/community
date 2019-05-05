package com.ejavashop.web.controller.member;

import java.io.IOException;
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

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.ProductComments;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductCommentsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 用户中心：我的收藏  及我的评价、我的咨询 、单品页
 *                       
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberCommentController extends BaseController {

    @Resource
    private IProductCommentsService productCommentsService;
    @Resource
    private IOrdersService          ordersService;
    @Resource
    private IMemberService          memberService;

    /**
     * 跳转到 我的评价界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/comment.html", method = { RequestMethod.GET })
    public String toMyEvaluation(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", String.valueOf(member.getId()));
        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductCommentsWithInfo(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            throw new RuntimeException(serviceResult.getMessage());
        }
        dataMap.put("commentsList", serviceResult.getResult());

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");
        dataMap.put("page", pm);
        return "front/member/usercenter/ordercenter/myevaluation";
    }

    /**
     * 添加或编辑商品评价界面（父页面）
     * @param request
     * @param response
     * @param dataMap
     * @param orderSn 订单id
     * @return
     */
    @RequestMapping(value = "/addcomment.html", method = { RequestMethod.GET })
    public String addEvaluation(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap,
                                @RequestParam(value = "id", required = true) Integer id) {

        if (id == null) {
            throw new RuntimeException("订单id为空");
        }

        ServiceResult<Orders> orderServiceResult = ordersService.getOrderWithOPById(id,"front");

        if (orderServiceResult.getResult() == null) {
            throw new RuntimeException("订单不存在");
        }

        dataMap.put("order", orderServiceResult.getResult());

        return "front/member/usercenter/ordercenter/addevaluation";
    }

    /**
     * 添加或查看商品评价界面（子页面）
     * @param request
     * @param response
     * @param dataMap
     * @param orderSn 订单编号
     * @param productId 产品id
     * @return
     */
    @RequestMapping(value = "/editcomment.html", method = { RequestMethod.GET })
    public String getProductCommentsByProductId(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Map<String, Object> dataMap,
                                                @RequestParam(value = "orderSn", required = true) String orderSn,
                                                @RequestParam(value = "productId", required = true) String productId,
                                                @RequestParam(value = "productGoodsId", required = true) String productGoodsId) {

        if (StringUtil.isEmpty(orderSn)) {
            throw new RuntimeException("订单编号为空");
        }
        if (StringUtil.isEmpty(productId)) {
            throw new RuntimeException("产品id为空");
        }
        if (StringUtil.isEmpty(productGoodsId)) {
            throw new RuntimeException("货品id为空");
        }
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<ProductComments> serviceResult = productCommentsService
            .getProductCommentsByOrderSn(orderSn, productId, productGoodsId, member.getId());
        dataMap.put("comment", serviceResult.getResult());
        dataMap.put("orderSn", orderSn);
        dataMap.put("productId", productId);
        dataMap.put("productGoodsId", productGoodsId);

        return "front/member/usercenter/ordercenter/editevaluation";
    }

    /**
     * 商品评价提交
     * @param request
     * @param response
     * @param productComments
     * @throws IOException 
     */
    @RequestMapping(value = "/savecomment.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductComments> evaluationSubmit(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          ProductComments productComments) throws Exception {
        HttpJsonResult<ProductComments> jsonResult = new HttpJsonResult<ProductComments>();
        Member member = WebFrontSession.getLoginedUser(request);

        productComments.setUserId(member.getId());
        productComments.setUserName(member.getName());

        ServiceResult<Orders> ordersBySnRlt = ordersService
            .getOrdersBySn(productComments.getOrderSn());
        if (!ordersBySnRlt.getSuccess()) {
            jsonResult = new HttpJsonResult<ProductComments>(ordersBySnRlt.getMessage());
            return jsonResult;
        }
        if (ordersBySnRlt.getResult() != null) {
            productComments.setSellerId(ordersBySnRlt.getResult().getSellerId());
        }

        productComments.setState(ProductComments.STATE_1);

        ServiceResult<Boolean> serviceResult = productCommentsService
            .saveProductComments(productComments);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<ProductComments>(serviceResult.getMessage());
            }
        }

        // 评论送积分
        memberService.memberEvaluateSendValue(member.getId(), member.getName(),
            productComments.getProductId());
        return jsonResult;
    }

}
