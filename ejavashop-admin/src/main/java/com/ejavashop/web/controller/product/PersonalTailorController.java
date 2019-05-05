package com.ejavashop.web.controller.product;

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
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.PersonalTailor;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.member.IPersonalTailorPictureService;
import com.ejavashop.service.member.IPersonalTailorService;
import com.ejavashop.web.controller.BaseController;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

@Controller
@RequestMapping(value = "admin/personaltailor")
public class PersonalTailorController extends BaseController {
    
    @Resource
    private IPersonalTailorService           personalTailorService;
    @Resource
    private IPersonalTailorPictureService    personalTailorPictureService;
    
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String personalTailor(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/operate/personaltailor/personaltailor";
    }
    
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PersonalTailor>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<PersonalTailor>> serviceResult = personalTailorService.page(queryMap,
             pager);
         HttpJsonResult<List<PersonalTailor>> jsonResult = new HttpJsonResult<List<PersonalTailor>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
    
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PersonalTailor>> del(HttpServletRequest request,
                                                                   Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        String tailor_id = request.getParameter("id");
        personalTailorService.deleteTailor(Integer.valueOf(tailor_id));
        personalTailorPictureService.delete(Integer.valueOf(tailor_id));
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<PersonalTailor>> serviceResult = personalTailorService.page(queryMap,
            pager);
        HttpJsonResult<List<PersonalTailor>> jsonResult = new HttpJsonResult<List<PersonalTailor>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
}
