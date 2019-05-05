package com.ejavashop.web.controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.shopm.pcindex.PcRecommend;
import com.ejavashop.service.operate.IProductPackageService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.pcindex.IPcRecommendService;
import com.ejavashop.service.product.IProductAttrService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.web.controller.BaseController;
import com.google.gson.Gson;

/**
 * 快速下单
 *                       
 * @Filename: OrdersQuickController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
public class OrdersQuickController extends BaseController {
    @Resource
    private IProductService      productService;
    @Resource
    private IProductGoodsService productGoodsService;
    @Resource
    private IOrdersService       ordersService;
    @Resource
    private IPcRecommendService  pcRecommendService;
    @Resource
    private IProductPackageService     productPackageService;
    @Resource
    private IProductAttrService        productAttrService;
    
    @RequestMapping(value = "autocomplete.html", method = RequestMethod.GET)
    public void autocomplete(HttpServletRequest request, HttpServletResponse response,
                             ModelMap dataMap, String term) {
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            Map<String, String> map = new HashMap<String, String>();
            map.put("term", term);
            ServiceResult<List<ProductGoods>> sr = productGoodsService.quickSearch(map, null);
            List<ProductGoods> pglist = sr.getResult();
            Gson gson = new Gson();
            String[] data = new String[pglist.size()];
            if (pglist != null && pglist.size() > 0) {
                for (int i = 0; i < pglist.size(); i++) {
                    data[i] = pglist.get(i).getSku();
                }
            }
            pw.print(gson.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "eosearch.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String eosearch(HttpServletRequest request, HttpServletResponse response,
                           ModelMap dataMap, String sku) {
    	//套餐
    	Map<String, String> packMap = new HashMap<String, String>();
    	packMap.put("state", "1");
    	ServiceResult<List<ProductPackage>> packsr = productPackageService.page(packMap, null);
    	dataMap.put("productPackage", packsr.getResult());
        //分页对象
        PaginationUtil page = WebUtil.handlerPaginationUtil(request);
        page.setSize(10);
        Map<String, String> queryMap = new HashMap<String, String>();
        List<ProductGoods> pglist = null;
        try {
            if (isNull(sku)) {
                // 默认商城推荐
                ServiceResult<List<PcRecommend>> hotRecommendResult = pcRecommendService.getPcRecommendForView(PcRecommend.RECOMMEND_TYPE_1, false);
                if (!hotRecommendResult.getSuccess()) {
                    log.error(hotRecommendResult.getMessage());
                }
                List<PcRecommend> recommenlist = hotRecommendResult.getResult();
                List<Integer> proids = new ArrayList<Integer>(recommenlist.size());
                for (PcRecommend pc : recommenlist) {
                    if (pc.getProduct() != null)
                        proids.add(pc.getProductId());
                }
                if (proids.size() < 1)
                    proids.add(-1);
                queryMap.put("in_products", StringUtil.listToString(proids, ","));
            }
            queryMap.put("sku", sku);
            ServiceResult<List<ProductGoods>> sr = productGoodsService.quickSearch(queryMap, page);
            pglist = sr.getResult();
            for (ProductGoods pg : pglist) {
                Product pro = productService.getProductById(pg.getProductId()).getResult();
                if (!isNull(pro)) {
                	pg.setProductBrandId(pro.getProductBrandId());
                    pg.setProductName(pro.getName1());
                    pg.setSellerId(pro.getSellerId());
                    //add by Ls 2016/08/06 新增属性查询，规格包装的双数
                    ProductAttr productAttr = new ProductAttr();
                    try {
                    	ServiceResult<ProductAttr> attrResult = productAttrService.getByProductIdAndName(pg.getProductId(),"包装规格"); 
                    	productAttr = attrResult.getResult();
                    	String[] attr_n = productAttr.getValue().split("双");
                    	pg.setBarCodePL(attr_n[0]);
                    } catch (Exception e) {
                    	pg.setBarCodePL(10+"");
                    }
                    //end
                }
            }
        } catch (Exception e) {
            if (e instanceof BusinessException)
                log.info("没有sku");
            else
                e.printStackTrace();
        }
        dataMap.put("page", page);
        dataMap.put("sku", isNull(sku) ? "" : sku);
        dataMap.put("pglist", pglist);
        dataMap.put("url4page", "eosearch.html?sku=" + (isNull(sku) ? "" : sku));
        return "front/order/eosearch";
    }
}
