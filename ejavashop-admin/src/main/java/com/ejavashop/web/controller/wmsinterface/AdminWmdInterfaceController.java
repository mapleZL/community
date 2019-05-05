package com.ejavashop.web.controller.wmsinterface;

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
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.entity.wmsinterface.InterfaceWms;
import com.ejavashop.service.wmsinterface.IInterfaceWmsService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "admin/wmsinterface")
public class AdminWmdInterfaceController extends BaseController{
	
	@Resource
	private IInterfaceWmsService  interfaceWmsService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
    public String adminUser(HttpServletRequest request,
                            Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/wmsinterface/interfacewms";
    }
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<InterfaceWms>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
//		System.out.println("-------------------");
		    Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
	        ServiceResult<List<InterfaceWms>> serviceResult = interfaceWmsService.page(queryMap, pager);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }

	        HttpJsonResult<List<InterfaceWms>> jsonResult = new HttpJsonResult<List<InterfaceWms>>();
	        jsonResult.setRows(serviceResult.getResult());
	        jsonResult.setTotal(pager.getRowsCount());

	        return jsonResult;
	}

}
