package com.ejavashop.web.controller.backgoods;

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
import com.ejavashop.entity.backgoods.BadGoods;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.service.backgoods.IBadGoodsService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value="admin/product/badgoods")
public class AdminBadGoodsController extends BaseController{
	
	@Resource(name = "badGoodsService")
	IBadGoodsService badGoodsService;
	
	
	 /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/backgoods/badgoods/badgoods";
    }
    
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<BadGoods>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
    	 Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<BadGoods>> serviceResult = badGoodsService.page(queryMap,
             pager);
         List<BadGoods> list = serviceResult.getResult();

         HttpJsonResult<List<BadGoods>> jsonResult = new HttpJsonResult<List<BadGoods>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
	

}
