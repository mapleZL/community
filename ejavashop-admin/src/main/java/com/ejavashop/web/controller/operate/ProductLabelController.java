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
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.operate.IProductLabelService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 标签管理相关action
 *                       
 * @Filename: ProductLabelController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "/admin/operate/productLabel")
public class ProductLabelController extends BaseController {
    @Resource
    private IProductLabelService productLabelService;

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
        return "/admin/operate/productLabel/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductLabel>> list(HttpServletRequest request,
                                                                 ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("state", "1");
        ServiceResult<List<ProductLabel>> serviceResult = productLabelService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductLabel>> jsonResult = new HttpJsonResult<List<ProductLabel>>();
        jsonResult.setRows((List<ProductLabel>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            ProductLabel productLabel = productLabelService.getProductLabelById(id).getResult();
            dataMap.put("obj", productLabel);
        }

        return "/admin/operate/productLabel/edit";
    }

    /**
     * 新增/编辑
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response,
                        ProductLabel productLabel) {
        try {
            String image = (String) UploadUtil.getInstance().advUploadFile2ImageServer("imagepic",
                request);

            if (!isNull(image)) {
                productLabel.setImage(image);
            }

            if (productLabel.getId() != null && 0 != productLabel.getId()) {
                productLabelService.updateProductLabel(productLabel);
            } else {
                SystemAdmin admin = WebAdminSession.getAdminUser(request);
                productLabel.setCreateId(admin.getId());
                productLabel.setCreateName(admin.getName());
                productLabel.setCreateTime(new Date());
                productLabelService.saveProductLabel(productLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/operate/productLabel";
    }

}
