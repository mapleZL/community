package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.entity.seller.SellerTypeLogs;
import com.ejavashop.entity.system.ResourceTree;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductTypeService;
import com.ejavashop.service.product.ISellerTypeLogsService;
import com.ejavashop.vo.product.ProductCateVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商品分类
 */
@Controller
@RequestMapping(value = "admin/product/cate")
public class CateController extends BaseController {
    private String baseUrl = "admin/product/cate/";

    @Resource
    private IProductCateService    productCateService;
    @Resource
    private IProductTypeService    productTypeService;
    @Resource
    private ISellerTypeLogsService sellerTypeLogsService;


    @RequestMapping(value = "productCaseTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> productCaseTree(HttpServletRequest request,
                                                            @RequestParam(value = "id", required = true) Integer pid) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(pid);
        generateTree(tree, serviceResult.getResult());
        return tree;
    }

    /**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     */
    private List<ResourceTree> generateTree(List<ResourceTree> treelist, List<ProductCate> data) {
        for (ProductCate sr : data) {
            ResourceTree tree = new ResourceTree();
            tree.setId(sr.getId());
            tree.setText(sr.getName());
            tree.setChildren(generateTree(new ArrayList<ResourceTree>(),
                productCateService.getByPid(sr.getId()).getResult()));
            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
            treelist.add(tree);
        }
        return treelist;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = productCateService.delProductCate(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 根据pid构造上级path
     * @param pid
     * @return
     */
    private String buildPath(Integer pid) {
        String path = "";
        ServiceResult<ProductCate> result = productCateService.getProductCateById(pid);
        if (result.getResult() == null) {
            path = "/";//一级分类
        } else {
            if ("/".equals(result.getResult().getPath())) {
                path = result.getResult().getPath() + pid;
            } else {
                path = result.getResult().getPath() + "/" + pid;
            }
        }
        return path;
    }

}
