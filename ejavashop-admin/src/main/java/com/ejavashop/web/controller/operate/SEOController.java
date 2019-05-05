package com.ejavashop.web.controller.operate;

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
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.pcindex.IPcTitleKeywordsDescriptionService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;
/**
 * SEO三要素设置
 *                       
 * @Filename: SEOController.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/admin/operate/seo")
public class SEOController extends BaseController{
    @Resource
    private IPcTitleKeywordsDescriptionService soeSerivice;
    
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String personalTailor(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/operate/seo/seolist";
    }
    
    
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcTitleKeywordsDescription>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
         System.out.println("SEO查询开始！！！！！");
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<PcTitleKeywordsDescription>> serviceResult = soeSerivice.page(queryMap,pager);
         HttpJsonResult<List<PcTitleKeywordsDescription>> jsonResult = new HttpJsonResult<List<PcTitleKeywordsDescription>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
    
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request,Map<String, Object> dataMap) {
        return "admin/operate/seo/seoadd";
    }
    
    @RequestMapping(value = "create", method = { RequestMethod.POST})
    public @ResponseBody String create(ProductBrand brand, MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        SystemAdmin user = WebAdminSession.getAdminUser(request);

        PcTitleKeywordsDescription seo = new PcTitleKeywordsDescription();
        String path = request.getParameter("path") == null ? "" : request.getParameter("path");
        String title = request.getParameter("title") == null ? "" : request.getParameter("title");
        String keywords = request.getParameter("keywords") == null ? "" : request.getParameter("keywords");
        String description = request.getParameter("description") == null ? "" : request.getParameter("description");
        String applyPage = request.getParameter("applyPage") == null ? "" : request.getParameter("applyPage");
        seo.setTitle(title);
        seo.setPath(path);
        seo.setTitle(title);
        seo.setKeywords(keywords);
        seo.setDescription(description);
        seo.setApplyPage(applyPage);
        seo.setCreateId(user.getId());
        soeSerivice.savePcTitleKeywordsDescription(seo);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/operate/seo/seolist";
    }
    
    @RequestMapping(value = "/edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        ServiceResult<PcTitleKeywordsDescription> serviceResult = soeSerivice.getPcTitleKeywordsDescriptionById(id);
        dataMap.put("titleKeywordsDescription", serviceResult.getResult());
        return "admin/operate/seo/seoedit";
    }
    
    @RequestMapping(value = "update", method = { RequestMethod.POST})
    public @ResponseBody String update(ProductBrand brand, MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        
        PcTitleKeywordsDescription seo = new PcTitleKeywordsDescription();
        String path = request.getParameter("path");
        String title = request.getParameter("title");
        String keywords = request.getParameter("keywords");
        String description = request.getParameter("description");
        String applyPage = request.getParameter("applyPage");
        Integer id = Integer.valueOf(request.getParameter("seo_id"));
        seo.setId(id);
        seo.setTitle(title);
        seo.setPath(path);
        seo.setKeywords(keywords);
        seo.setDescription(description);
        seo.setApplyPage(applyPage);
        
        soeSerivice.updatePcTitleKeywordsDescription(seo);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/operate/seo/seolist";
    }
    
}
