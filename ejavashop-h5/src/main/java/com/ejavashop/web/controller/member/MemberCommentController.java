package com.ejavashop.web.controller.member;

import java.io.IOException;
import java.util.HashMap;
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
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.ProductComments;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductCommentsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 用户中心：我的评价
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

        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", String.valueOf(member.getId()));
        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductCommentsWithInfo(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            throw new RuntimeException(serviceResult.getMessage());
        }
        dataMap.put("commentsList", serviceResult.getResult());
        dataMap.put("commentsCount", pager.getRowsCount());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        return "h5v1/member/comment/comment";
    }

    /**
     * 加载更多评论
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/morecomment.html", method = { RequestMethod.GET })
    public String moreComment(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_userId", String.valueOf(member.getId()));

        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = ConvertUtil.toInt(pageIndexStr, 1);
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);

        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductCommentsWithInfo(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            throw new RuntimeException(serviceResult.getMessage());
        }
        dataMap.put("commentsList", serviceResult.getResult());

        return "h5v1/member/comment/commentlist";
    }

    /**
     * 添加或编辑商品评价界面
     * @param request
     * @param response
     * @param dataMap
     * @param orderSn 订单id
     * @return
     */
    @RequestMapping(value = "/addcomment.html", method = { RequestMethod.GET })
    public String addComment(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap,
                             @RequestParam(value = "id", required = true) Integer id) {
        ServiceResult<Orders> orderServiceResult = ordersService.getOrderWithOPById(id,"front");
        dataMap.put("order", orderServiceResult.getResult());
        return "h5v1/member/comment/commentedit";
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
    @RequestMapping(value = "/addcommentdetail.html", method = { RequestMethod.GET })
    public String addCommentDetail(HttpServletRequest request, HttpServletResponse response,
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

        return "h5v1/member/comment/commenteditdetail";
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
