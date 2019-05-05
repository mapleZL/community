package com.ejavashop.web.controller.operate;

import java.util.Date;
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
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.operate.IProductPackageService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 二次加工套餐设定相关action
 *                       
 * @Filename: ProductPackageController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "/admin/operate/productPackage")
public class ProductPackageController extends BaseController {
    @Resource
    private IProductPackageService productPackageService;

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
        return "/admin/operate/productPackage/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductPackage>> list(HttpServletRequest request,
                                                                   ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductPackage>> serviceResult = productPackageService.page(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductPackage>> jsonResult = new HttpJsonResult<List<ProductPackage>>();
        jsonResult.setRows((List<ProductPackage>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            ProductPackage productPackage = productPackageService.getProductPackageById(id)
                .getResult();
            dataMap.put("obj", productPackage);
        }

        return "/admin/operate/productPackage/edit";
    }

    /**
     * 新增/编辑
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response,
                        ProductPackage productPackage) {
        try {
            String image = (String) UploadUtil.getInstance().advUploadFile2ImageServer("imagepic",
                request);

            if (!isNull(image)) {
                productPackage.setImage(image);
            }

            if (productPackage.getId() != null && 0 != productPackage.getId()) {
                productPackageService.updateProductPackage(productPackage);
            } else {
                SystemAdmin admin = WebAdminSession.getAdminUser(request);
                productPackage.setCreateId(admin.getId());
                productPackage.setCreateName(admin.getName());
                productPackage.setCreateTime(new Date());
                productPackageService.saveProductPackage(productPackage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/operate/productPackage";
    }

}
