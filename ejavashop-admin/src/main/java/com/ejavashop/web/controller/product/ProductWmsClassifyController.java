package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.WmsClassify;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IWmsClassifyService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

@Controller
@RequestMapping(value = "admin/wmsclassify")
public class ProductWmsClassifyController extends BaseController {
    
    @Resource
    private IWmsClassifyService              wmsClassifyService;
    
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String personalTailor(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/wms/wmsclassify";
    }
    
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<WmsClassify>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
         System.out.println("wms分类查询开始！！！！！");
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<WmsClassify>> serviceResult = wmsClassifyService.page(queryMap,pager);
         HttpJsonResult<List<WmsClassify>> jsonResult = new HttpJsonResult<List<WmsClassify>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
    
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request,Map<String, Object> dataMap) {
        return "admin/product/wms/wmsclassifyadd";
    }
    
    
    @RequestMapping(value = "create", method = { RequestMethod.POST})
    public @ResponseBody String create(ProductBrand brand, MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        SystemAdmin user = WebAdminSession.getAdminUser(request);

        WmsClassify wms = new WmsClassify();
        String wms_category = request.getParameter("wms_category");
        Integer state = Integer.valueOf(request.getParameter("state"));
        wms.setCreateUser(user.getName());
        wms.setWmsCategory(wms_category);
        wms.setState(state);
        wmsClassifyService.saveWmsClassify(wms);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/wms/wmsclassify";
    }
    
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        ServiceResult<WmsClassify> serviceResult = wmsClassifyService.getWmsClassifyById(id);
        dataMap.put("wms", serviceResult.getResult());
        return "admin/product/wms/wmsclassifyedit";
    }
    
    @RequestMapping(value = "update", method = { RequestMethod.POST})
    public @ResponseBody String update(ProductBrand brand, MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        
        WmsClassify wms = new WmsClassify();
        String wms_category = request.getParameter("wms_category");
        Integer state = Integer.valueOf(request.getParameter("state"));
        Integer id = Integer.valueOf(request.getParameter("wms_id"));
        wms.setId(id);
        wms.setWmsCategory(wms_category);
        wms.setState(state);
        wmsClassifyService.updateWmsClassify(wms);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/wms/wmsclassify";
    }
}
