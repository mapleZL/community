package com.ejavashop.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.promotion.group.ActGroupBanner;
import com.ejavashop.entity.promotion.group.ActGroupType;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActGroupBannerService;
import com.ejavashop.service.promotion.IActGroupService;
import com.ejavashop.web.controller.BaseController;

/**
 * 团购
 *                       
 * @Filename: ActGroupListController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ActGroupListController extends BaseController {

    @Resource
    private IActGroupService       actGroupService;

    @Resource
    private IActGroupBannerService actGroupBannerService;

    @Resource
    private IProductService        productService;

    private final static int       TUAN_PAGESIZE = 20;

    @RequestMapping(value = "/tuan.html", method = RequestMethod.GET)
    public String group(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, TUAN_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        ServiceResult<List<ActGroup>> serviceResultGroup = actGroupService.getActGroupsFront(pager,
            type, ConstantsEJS.CHANNEL_3);
        List<ActGroup> actGroups = serviceResultGroup.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productService.getProductById(actGroup
                .getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());
        }

        ServiceResult<List<ActGroupBanner>> serviceResult = actGroupBannerService
            .getActGroupBannersPcMobile(ConstantsEJS.PC_MOBILE_MOBILE);
        List<ActGroupBanner> actGroupBanners = serviceResult.getResult();

        ServiceResult<List<ActGroupType>> serviceResultType = actGroupService
            .getActGroupTypesFront();
        List<ActGroupType> actGroupTypes = serviceResultType.getResult();
        String typeName = "全部";
        if (type != 0) {
            ActGroupType actGroupType = null;
            for (int i = 0; i < actGroupTypes.size(); i++) {
                actGroupType = actGroupTypes.get(i);
                if (actGroupType.getId().intValue() == type) {
                    typeName = actGroupType.getName();
                }
            }
        }

        dataMap.put("actGroups", actGroups);
        dataMap.put("actGroupBanners", actGroupBanners);
        dataMap.put("actGroupTypes", actGroupTypes);
        dataMap.put("type", type);
        dataMap.put("typeName", typeName);
        dataMap.put("pagesize", TUAN_PAGESIZE);

        return "h5v1/promotion/actgrouplist";
    }

    /**
     * 返回json结果
     * @param request
     * @param stack
     * @return
     */
    @RequestMapping(value = "/tuanJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<ActGroup>> searchJson(HttpServletRequest request,
                                                                   Map<String, Object> dataMap) {
        HttpJsonResult<List<ActGroup>> jsonResult = new HttpJsonResult<List<ActGroup>>();
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, TUAN_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        ServiceResult<List<ActGroup>> serviceResultGroup = actGroupService.getActGroupsFront(pager,
            type, ConstantsEJS.CHANNEL_3);
        List<ActGroup> actGroups = serviceResultGroup.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productService.getProductById(actGroup
                .getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());
        }

        jsonResult.setRows(actGroups);
        jsonResult.setTotal(actGroups.size());
        return jsonResult;
    }
}
