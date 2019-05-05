package com.ejavashop.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
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

    private final static int       PAGE_NUMBER = 20;

    @RequestMapping(value = "/tuan.html", method = RequestMethod.GET)
    public String group(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, PAGE_NUMBER);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        ServiceResult<List<ActGroup>> serviceResultGroup = actGroupService.getActGroupsFront(pager,
            type, ConstantsEJS.CHANNEL_2);
        List<ActGroup> actGroups = serviceResultGroup.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productService.getProductById(actGroup
                .getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());
        }

        ServiceResult<List<ActGroupBanner>> serviceResult = actGroupBannerService
            .getActGroupBannersPcMobile(ConstantsEJS.PC_MOBILE_PC);
        List<ActGroupBanner> actGroupBanners = serviceResult.getResult();

        ServiceResult<List<ActGroupType>> serviceResultType = actGroupService
            .getActGroupTypesFront();
        List<ActGroupType> actGroupTypes = serviceResultType.getResult();

        String url = request.getRequestURI() + "";
        if (type != 0) {
            url = url + "?type=" + type;
        }

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), url);

        dataMap.put("actGroups", actGroups);
        dataMap.put("actGroupBanners", actGroupBanners);
        dataMap.put("actGroupTypes", actGroupTypes);
        dataMap.put("page", pm);
        dataMap.put("type", type);

        return "front/promotion/actgrouplist";
    }
}
