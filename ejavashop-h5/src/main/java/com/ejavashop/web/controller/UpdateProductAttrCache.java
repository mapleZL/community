package com.ejavashop.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpClientUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.SyncWayUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.service.product.IProductFrontService;

@Controller
@RequestMapping(value = "cache")
public class UpdateProductAttrCache extends BaseController{
	@Resource
    private IProductFrontService productFrontService;
	
	@RequestMapping(value = "updateProductAttrCache", method = { RequestMethod.GET })
    public void updateProductAttrCache(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap,Integer typeId) throws Exception {
        StringBuffer bf = new StringBuffer();
        bf.append("<center>");
        if (typeId == null) {
            bf.append("<input type='button' name='' value='更新袜属性' style='width: 150px;height: 40px;' onclick='syncOms()'>");
        } else {
	        ServiceResult<Boolean> result = productFrontService.updateProductAttrCache(typeId,request.getSession().getServletContext());
			if (result.getSuccess() && result.getResult()) {
				bf.append("<br/><br/><br/><br/><font color='green' size='6'>操作成功，已更新商品属性缓存</font>");
			} else {
				bf.append("<br/><br/><br/><br/><font color='red' size='6'>" +  result.getMessage() + "</font>");
			} 
        }
        bf.append("</center><script>");
        bf.append("function syncOms(){if (confirm('确定更新商品属性缓存吗?')) {location.href = '/cache/updateProductAttrCache?typeId=1';}}");
        bf.append("</script>");
		response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            pw.close();
        }
        pw.print(bf.toString());
        pw.close();
    }
	
	@RequestMapping(value = "doIndexCache.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> reStaticIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sendGet = HttpClientUtil.sendGet(DomainUrlUtil.getEJS_URL_RESOURCES() + "/cacheIndex.html");
        request.getServletContext().setAttribute(ConstantsEJS.M_INDEX_HTML_CACHE, sendGet);
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        serviceResult.setSuccess(true);
        serviceResult.setSuccess(true);
        serviceResult.setMessage("初始化成功");
        jsonResult = new HttpJsonResult<Boolean>();
        return jsonResult;
    }

}
