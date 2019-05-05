package com.ejavashop.web.controller.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductTypeService;
import com.ejavashop.service.product.ISellerManageCateService;
import com.ejavashop.service.product.ISellerTypeLogsService;
import com.ejavashop.vo.product.ProductCateVO;
import com.ejavashop.web.basic.CsrfTokenManager;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商品分类申请
 */
@Controller
@RequestMapping(value = "seller/product/cate")
public class SellerProductCateController extends BaseController {
    private String                   baseUrl = "seller/product/cate/";

    @Resource
    private IProductCateService      productCateService;
    @Resource
    private ISellerManageCateService sellerManageCateService;
    @Resource
    private IProductTypeService      productTypeService;
    @Resource
    private ISellerTypeLogsService   sellerTypeLogsService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "catelist";
    }

    //    @RequestMapping(value = "list", method = { RequestMethod.GET })
    //    public @ResponseBody HttpJsonResult<List<ProductCateVO>> list(HttpServletRequest request,
    //                                                                  HttpServletResponse response,
    //                                                                  Map<String, Object> dataMap) {
    //
    //        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
    //        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
    //        ServiceResult<List<ProductCateVO>> serviceResult = productCateService
    //            .pageProductCate(queryMap, pager);
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                throw new BusinessException(serviceResult.getMessage());
    //            }
    //        }
    //
    //        HttpJsonResult<List<ProductCateVO>> jsonResult = new HttpJsonResult<List<ProductCateVO>>();
    //        jsonResult.setRows((List<ProductCateVO>) serviceResult.getResult());
    //        jsonResult.setTotal(pager.getRowsCount());
    //        return jsonResult;
    //    }

    @RequestMapping(value = "getByPid", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody List<ProductCateVO> getByPid(HttpServletRequest request,
                                                      @RequestParam(value = "id", required = true) Integer pid) {
        HttpJsonResult<List<ProductCateVO>> jsonResult = new HttpJsonResult<List<ProductCateVO>>();
        ServiceResult<List<ProductCateVO>> serviceResult = productCateService.getByPidAndSeller(pid,
            WebSellerSession.getSellerUser(request).getSellerId());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        jsonResult.setRows((List<ProductCateVO>) serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return serviceResult.getResult();
    }

    /**
     * 提交审核
     */
    @RequestMapping(value = "commit", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<Object> commit(HttpServletRequest request, Integer cateId) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true,
            CsrfTokenManager.createTokenForSession(request.getSession()));
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_FRONT_URL() + "/seller/login.html");
            return jsonResult;
        }

        SellerManageCate manager = new SellerManageCate();
        manager.setCreateId(sellerUser.getId());
        manager.setSeller(sellerUser.getSellerId());
        manager.setProductCateId(cateId);
        manager.setState(SellerManageCate.Status.COMMIT.getValue());
        ServiceResult<Boolean> serviceResult = sellerManageCateService
            .saveSellerManageCate(manager);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        CodeManager.init();
        return jsonResult;
    }

    //    @RequestMapping(value = "productCaseTree", method = { RequestMethod.GET })
    //    public @ResponseBody List<ResourceTree> productCaseTree(HttpServletRequest request,
    //                                                            @RequestParam(value = "id", required = true) Integer pid) {
    //        List<ResourceTree> tree = new ArrayList<ResourceTree>();
    //        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(pid);
    //        generateTree(tree, serviceResult.getResult());
    //        return tree;
    //    }
    //
    //    /**
    //     * 递归生成树
    //     * @param treelist
    //     * @param data
    //     * @return
    //     */
    //    private List<ResourceTree> generateTree(List<ResourceTree> treelist, List<ProductCate> data) {
    //        for (ProductCate sr : data) {
    //            ResourceTree tree = new ResourceTree();
    //            tree.setId(sr.getId());
    //            tree.setText(sr.getName());
    //            tree.setChildren(generateTree(new ArrayList<ResourceTree>(),
    //                productCateService.getByPid(sr.getId()).getResult()));
    //            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
    //            treelist.add(tree);
    //        }
    //        return treelist;
    //    }
}
