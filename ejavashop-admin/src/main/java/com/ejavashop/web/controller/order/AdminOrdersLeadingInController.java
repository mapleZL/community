package com.ejavashop.web.controller.order;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;

/**
 * 订单导入controller
 *                       
 * @Filename: AdminOrdersLeadingInController.java
 * @Version: 1.0
 * @Author: 陆帅
 * @Email: chenwanhai@sina.com
 *
 */

@Controller
@RequestMapping(value = "/admin/order/leadingin")
public class AdminOrdersLeadingInController extends BaseController{
    @Resource(name = "ordersService")
    private IOrdersService orderService;
    
    
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        return "admin/order/leadingin/ordersleadingin";
    }
    
    @RequestMapping(value = "leadinginxls", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<String> leadinginxls(HttpServletRequest request,
                                                              ModelMap dataMap, String excelurl) throws Exception {
        HttpJsonResult<String> jsonResult = new HttpJsonResult<String>();
        try {
            File newFile = UploadUtil.getInstance().getTempFile(request,"leadxls");
            ServiceResult<String> serviceResult = orderService.saveOrdersLeadingXls(newFile);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }else{
                jsonResult.setMessage(serviceResult.getResult());
                return jsonResult;
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                jsonResult.setMessage(e.getMessage());
            } else {
                jsonResult.setMessage(ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
                e.printStackTrace();
            }
        }
        return jsonResult;
    }
    
}
