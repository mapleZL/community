package com.ejavashop.web.controller.member;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.analysis.ProductLookLog;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberCollectionProduct;
import com.ejavashop.entity.member.MemberCollectionSeller;
import com.ejavashop.entity.product.Product;
import com.ejavashop.service.analysis.IAnalysisService;
import com.ejavashop.service.member.IMemberCollectionProductService;
import com.ejavashop.service.member.IMemberCollectionSellerService;
import com.ejavashop.service.product.IProductAskService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.vo.member.FrontMemberCollectionProductVO;
import com.ejavashop.vo.member.FrontMemberCollectionSellerVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 用户中心：我的收藏、我的咨询
 * 
 * @Filename: MyattentionController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MyattentionController extends BaseController {

    @Resource
    private IMemberCollectionSellerService  memberCollectionSellerService;

    @Resource
    private IMemberCollectionProductService memberCollectionProductService;

    @Resource
    private IProductAskService              productAskService;

    @Resource
    private IAnalysisService                analysisService;
    
    @Resource
    private IProductService                productService;

    /**
     * 跳转到 收藏的商品界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/collectproduct.html", method = { RequestMethod.GET })
    public String toCollectionGoods(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap) {

        Map<String, Object> queryMap = WebUtil.handlerQueryMapNoQ(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<List<FrontMemberCollectionProductVO>> serviceResult = new ServiceResult<List<FrontMemberCollectionProductVO>>();
        serviceResult = memberCollectionProductService.getCollectionProductByMemberId(queryMap,
            member.getId(), pager);

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");
        
        List<FrontMemberCollectionProductVO> collectionProductList = serviceResult.getResult();
        List<FrontMemberCollectionProductVO> newCollectionProductList = new ArrayList<FrontMemberCollectionProductVO>();
        if(collectionProductList != null && collectionProductList.size() > 0){
        	for (FrontMemberCollectionProductVO frontMemberCollectionProductVO : collectionProductList) {
        		Integer productId = frontMemberCollectionProductVO.getProductId();
        		Product product = productService.getProductById(productId).getResult();
        		if(product != null){
        			frontMemberCollectionProductVO.setProductState(product.getState());
        		}
			}
        }
        dataMap.put("productList", collectionProductList);
        dataMap.put("page", pm);

        return "front/member/usercenter/collectiongoods";
    }

    /**
     * 跳转到 收藏的店铺界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/collectshop.html", method = { RequestMethod.GET })
    public String toCollectionShop(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {

        //Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<List<FrontMemberCollectionSellerVO>> serviceResult = new ServiceResult<List<FrontMemberCollectionSellerVO>>();
        // 店铺总关注用户数量
        serviceResult = memberCollectionSellerService.getCollectionSellerByMemberid(member.getId(),
            pager);

        dataMap.put("sellerList", serviceResult.getResult());

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("page", pm);

        return "front/member/usercenter/collectionshop";
    }

    /**
     * 关注店铺
     * @param request
     * @param response
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/docollectshop.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> collectionShop(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                @RequestParam(value = "sellerId", required = true) Integer sellerId) {
        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Boolean> serviceResult = memberCollectionSellerService
            .collectionSeller(sellerId, member.getId());

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;

    }

    /**
     * 取消收藏商铺
     * @param request
     * @param response
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/cancelcollectshop.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cancelCollectionShop(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      @RequestParam(value = "sellerId", required = true) Integer sellerId) {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Boolean> serviceResult = memberCollectionSellerService
            .cancelCollectionSeller(sellerId, member.getId());

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 关注商品
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/docollectproduct.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<MemberCollectionSeller> collectionProduct(HttpServletRequest request,
                                                                                  HttpServletResponse response,
                                                                                  @RequestParam(value = "productId", required = true) Integer productId) throws Exception {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();
        serviceResult = memberCollectionProductService.saveMemberCollectionProduct(productId,
            member.getId());

        HttpJsonResult<MemberCollectionSeller> jsonResult = new HttpJsonResult<MemberCollectionSeller>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<MemberCollectionSeller>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 取消收藏商品
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/cancelcollectproduct.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<MemberCollectionProduct> cancelCollectionProduct(HttpServletRequest request,
                                                                                         HttpServletResponse response,
                                                                                         @RequestParam(value = "productId", required = true) Integer productId) throws Exception {
        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();

        serviceResult = memberCollectionProductService.cancelCollectionProduct(productId,
            member.getId());

        HttpJsonResult<MemberCollectionProduct> jsonResult = new HttpJsonResult<MemberCollectionProduct>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<MemberCollectionProduct>(
                    serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 跳转到 商品浏览界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/viewed.html", method = { RequestMethod.GET })
    public String viewed(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ProductLookLog>> serviceResult = analysisService
            .getProductLookLogs(queryMap, pager);

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("lookLogList", serviceResult.getResult());
        dataMap.put("page", pm);

        return "front/member/usercenter/productlooklog";
    }
    
    
    /**
     * 删除浏览记录
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/deleteproductlog.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> deleteproductlog(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap,
                                                             @RequestParam(value = "productId", required = true) String productId) {
        Member member = WebFrontSession.getLoginedUser(request);
        ProductLookLog productLookLog = new ProductLookLog();
        productLookLog.setState(2);
        productLookLog.setMemberId(member.getId());
        productLookLog.setProductId(Integer.valueOf(productId));
        //取消订单
        ServiceResult<Boolean> serviceResult = analysisService.updateProductLookLog(productLookLog);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;

    }
}
