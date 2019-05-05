package com.ejavashop.web.controller.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.ISellerManageCateService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 商家可以经营商品分类
 */
@Controller
@RequestMapping(value = "seller/product/manager")
public class SellerManagerCateController extends BaseController {

    @Resource
    private ISellerManageCateService sellerManageCateService;
    @Resource
    private IProductCateService      productCateService;

    private String                   baseUrlStr = "seller/product/manager/";

    /**
     * 默认，显示全部状态的品牌
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrlStr + "managerlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerManageCate>> list(HttpServletRequest request,
                                                                     Map<String, Object> dataMap) {
        HttpJsonResult<List<SellerManageCate>> jsonResult = new HttpJsonResult<List<SellerManageCate>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_FRONT_URL() + "/seller/login.html");
            return jsonResult;
        }
        queryMap.put("q_seller", String.valueOf(sellerUser.getSellerId()));
        ServiceResult<List<SellerManageCate>> serviceResult = sellerManageCateService
            .pageSellerManageCate(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        jsonResult.setRows((List<SellerManageCate>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    //    @RequestMapping(value = "add", method = { RequestMethod.GET })
    //    public String add(Map<String, Object> dataMap) {
    //        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(0);
    //        if (!serviceResult.getSuccess()) {
    //            log.error("");
    //        }
    //        dataMap.put("productCates", serviceResult.getResult());
    //        return baseUrlStr + "manageradd";
    //    }
    //
    //    @RequestMapping(value = "create", method = { RequestMethod.POST })
    //    @ResponseBody
    //    public HttpJsonResult<Object> create(SellerManageCate manager, HttpServletRequest request,
    //                                         Map<String, Object> dataMap) {
    //        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true,
    //            CsrfTokenManager.createTokenForSession(request.getSession()));
    //        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
    //        if (null == sellerUser) {
    //            jsonResult.setMessage("登录超时，请重新登录");
    //            jsonResult.setBackUrl(DomainUrlUtil.getEJS_FRONT_URL() + "/seller/login.html");
    //            return jsonResult;
    //        }
    //        manager.setCreateId(sellerUser.getId());
    //        manager.setSeller(sellerUser.getSellerId());
    //
    //        manager.setState(SellerManageCate.Status.DEFAULT.getValue());
    //        ServiceResult<Boolean> serviceResult = sellerManageCateService
    //            .saveSellerManageCate(manager);
    //        if (!serviceResult.getSuccess()) {
    //            jsonResult.setMessage(serviceResult.getMessage());
    //        }
    //        CodeManager.init();
    //        return jsonResult;
    //    }
    //
    //    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    //    public String edit(@RequestParam(value = "id", required = true) Integer id,
    //                       Map<String, Object> dataMap) {
    //        ServiceResult<SellerManageCate> serviceResult = sellerManageCateService
    //            .getSellerManageCateById(id);
    //        if (!serviceResult.getSuccess()) {
    //            throw new RuntimeException(serviceResult.getMessage());
    //        }
    //        dataMap.put("manager", serviceResult.getResult());
    //        return baseUrlStr + "manageredit";
    //    }
    //
    //    @RequestMapping(value = "update", method = { RequestMethod.POST })
    //    @ResponseBody
    //    public HttpJsonResult<Object> update(SellerManageCate manager, HttpServletRequest request) {
    //        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true,
    //            CsrfTokenManager.createTokenForSession(request.getSession()));
    //        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
    //        if (null == sellerUser) {
    //            jsonResult.setMessage("登录超时，请重新登录");
    //            jsonResult.setBackUrl(DomainUrlUtil.getEJS_FRONT_URL() + "/seller/login.html");
    //            return jsonResult;
    //        }
    //        ServiceResult<Boolean> serviceResult = sellerManageCateService
    //            .updateSellerManageCate(manager);
    //        if (!serviceResult.getSuccess()) {
    //            jsonResult.setMessage(serviceResult.getMessage());
    //        }
    //        CodeManager.init();
    //        return jsonResult;
    //    }
    //
    //    @RequestMapping(value = "del", method = RequestMethod.POST)
    //    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
    //                                                    @RequestParam("id") Integer id) {
    //        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true,
    //            CsrfTokenManager.createTokenForSession(request.getSession()));
    //        ServiceResult<Boolean> serviceResult = sellerManageCateService.delSellerManageCate(id);
    //        if (!serviceResult.getSuccess()) {
    //            jsonResult.setMessage(serviceResult.getMessage());
    //        }
    //        return jsonResult;
    //    }
    //
    //    /**
    //     * 提交审核
    //     * @param request
    //     * @param id
    //     * @return
    //     */
    //    @RequestMapping(value = "commit", method = RequestMethod.POST)
    //    public @ResponseBody HttpJsonResult<Object> commit(HttpServletRequest request,
    //                                                       @RequestParam("id") Integer id) {
    //        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true,
    //            CsrfTokenManager.createTokenForSession(request.getSession()));
    //        ServiceResult<Boolean> serviceResult = sellerManageCateService.commit(id);
    //        if (!serviceResult.getSuccess()) {
    //            jsonResult.setMessage(serviceResult.getMessage());
    //        }
    //        return jsonResult;
    //    }

}
