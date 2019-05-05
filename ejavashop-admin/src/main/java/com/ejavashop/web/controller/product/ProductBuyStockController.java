package com.ejavashop.web.controller.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductBuyStock;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductBuyStockService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 
 *                       
 * @Filename: ProductBuyStockController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/productbuystock")
public class ProductBuyStockController extends BaseController {

    @Resource
    private IProductBuyStockService productBuyStockService;

    @Resource
    private IProductService         productService;

    @Resource
    private IProductGoodsService    productGoodsService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "/admin/product/productbuystock/productbuystocklist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductBuyStock>> list(HttpServletRequest request,
                                                                    ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductBuyStock>> serviceResult = productBuyStockService.page(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductBuyStock>> jsonResult = new HttpJsonResult<List<ProductBuyStock>>();
        jsonResult.setRows((List<ProductBuyStock>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            ProductBuyStock productBuyStock = productBuyStockService.getProductBuyStockById(id)
                .getResult();
            dataMap.put("productBuyStock", productBuyStock);
        }
        return "/admin/product/productbuystock/productbuystockedit";
    }

    /**
     * 编辑
     * @param request
     * @param response
     * @param productBuyStock
     * @return
     */
    @RequestMapping(value = "doEdit", method = { RequestMethod.POST })
    public String doEdit(HttpServletRequest request, HttpServletResponse response,
                         ProductBuyStock productBuyStock) {
        try {
            ServiceResult<Integer> serviceResult = productBuyStockService
                .updateProductBuyStock(productBuyStock);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/productbuystock";
    }

    /**
     * 启用/停用
     * @param request
     * @param response
     * @param type
     * @return
     */
    @RequestMapping(value = "handler", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductBuyStock> handler(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Integer id, Integer type) {
        HttpJsonResult<ProductBuyStock> jsonResult = new HttpJsonResult<ProductBuyStock>();
        try {
            if (isNull(type))
                throw new BusinessException("未知操作");
            ServiceResult<ProductBuyStock> serviceResult = productBuyStockService
                .getProductBuyStockById(id);
            ProductBuyStock pbs = serviceResult.getResult();
            pbs.setState(type);
            productBuyStockService.updateProductBuyStock(pbs);

            jsonResult.setData(pbs);
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping(value = "editstock", method = { RequestMethod.GET })
    public String editstock(int id, HttpServletRequest request, Map<String, Object> dataMap) {
        ServiceResult<Product> serviceResult = productService.getProductById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Product product = serviceResult.getResult();

        ServiceResult<List<ProductGoods>> resultProductGoods = productGoodsService
            .getGoodSByProductId(product.getId());
        List<ProductGoods> productGoodss = resultProductGoods.getResult();
        product.setGoodsList(productGoodss);
        dataMap.put("product", product);

        ServiceResult<Boolean> existsr = productBuyStockService.ifexists(productGoodss);
        if (existsr.getResult() == Boolean.TRUE) {
            dataMap.put("exists", true);
        }
        return "admin/product/productbuystock/productbuystockeditstock";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ProductBuyStock productBuyStock, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        String[] goodsIdAll = request.getParameterValues("goodsIdAll");
        String productIdStr = request.getParameter("productId");
        int productId = ConvertUtil.toInt(productIdStr, 0);

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        productBuyStock.setCreateId(adminUser.getId());
        productBuyStock.setCreateName(adminUser.getName());

        productBuyStock.setUpdateId(adminUser.getId());
        productBuyStock.setUpdateName(adminUser.getName());

        List<ProductBuyStock> productBuyStocks = new ArrayList<ProductBuyStock>();

        final net.sf.cglib.beans.BeanCopier bc = net.sf.cglib.beans.BeanCopier.create(
            ProductBuyStock.class, ProductBuyStock.class, false);

        ProductBuyStock productBuyStockNow = null;
        for (int i = 0; i < goodsIdAll.length; i++) {
            productBuyStockNow = new ProductBuyStock();
            String goodsIdStr = goodsIdAll[i];
            int goodsId = ConvertUtil.toInt(goodsIdStr, 0);
            bc.copy(productBuyStock, productBuyStockNow, null);
            productBuyStockNow.setGoodsId(goodsId);
            productBuyStockNow.setProductId(productId);
            productBuyStocks.add(productBuyStockNow);
        }

        productBuyStockService.saveProductBuyStockAll(productBuyStocks);

        return "redirect:/admin/product/onSale";
    }
}
