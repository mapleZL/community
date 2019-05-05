package com.ejavashop.web.controller.promotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActSingleService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;
/**
 * 单品立减管理controller
 *                       
 * @Filename: AdminActSingleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/single")
public class AdminActSingleController extends BaseController {

    @Resource
    private IActSingleService actSingleService;
    @Resource
    private IProductService   productService;
    @Resource
    private ISellerService    sellerService;

    /**
     * 单品立减列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/single/actsinglelist";
    }
    
    @RequestMapping(value = "singleset", method = { RequestMethod.GET })
    public String singleset(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/single/actsinglelist2";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActSingle>> list(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActSingle>> serviceResult = actSingleService.getActSingles(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActSingle> list = serviceResult.getResult();

        HttpJsonResult<List<ActSingle>> jsonResult = new HttpJsonResult<List<ActSingle>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    /**
     * 分页查出所有活动
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list2", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActSingle>> list2(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActSingle>> serviceResult = actSingleService.getActSingles(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActSingle> list = serviceResult.getResult();

        HttpJsonResult<List<ActSingle>> jsonResult = new HttpJsonResult<List<ActSingle>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("sellerId", 1);
        return "admin/promotion/single/actsingleadd";
    }
    
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActSingle actSingle, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        Boolean tFlag = true;
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        actSingle.setCreateUserId(userId);
        actSingle.setCreateUserName(adminUser.getName());
        actSingle.setUpdateUserId(adminUser.getId());
        actSingle.setUpdateUserName(adminUser.getName());

        actSingle.setStatus(ActSingle.STATUS_1);

        Map<String , String> seller_map = new HashMap<String , String>();
         // 组装活动商品
        String[] productIds = request.getParameterValues("ids");
        if (productIds == null || productIds.length == 0) {
            dataMap.put("sellerId", "1");
            dataMap.put("actSingle", actSingle);
            dataMap.put("message", "请选择参加活动的商品。");
            return "admin/promotion/single/actsingleadd";
        }
        Map<String ,String> map = this.getIds2(productIds,seller_map);
        Set<String> keys = map.keySet();
        //循环处理商品，按商家进行拆分
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        for(String key:keys){
            actSingle.setSellerId(Integer.valueOf(key));
            actSingle.setProductIds("," + map.get(key) + ",");
            serviceResult = actSingleService.saveActSingle(actSingle);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    tFlag = false;
                    throw new RuntimeException(serviceResult.getMessage());
                } 
            }
        }
        if(tFlag){
            dataMap.put("actSingle", actSingle);
            dataMap.put("message", "设置成功");
            return "admin/promotion/single/actsingleadd";
        }
        return "redirect:/admin/promotion/single";
    }
    
    private String getIds(String[] productIds) {
        Set<String> idSet = new HashSet<String>();
        String ids = ",";
        for (String id : productIds) {
            if (!StringUtil.isEmpty(id, true) && idSet.add(id)) {
                ids += id + ",";
            }
        }
        return ids;
    }
    
    private Map<String,String> getIds2(String[] productIds, Map<String, String> seller_map) {
        String[] new_id;
        String sellerIds ="";
        for (String id : productIds) {
            new_id = id.split(":");
            try {
                sellerIds = seller_map.get(new_id[0]);
            } catch (Exception e) {
            }
            if(sellerIds != null && !sellerIds.equals("")){
                seller_map.put(new_id[0], sellerIds + "," + new_id[1]);
            }else{
                seller_map.put(new_id[0], new_id[1]);
            }
        }
        return seller_map;
    }

    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request, int actSingleId, Map<String, Object> dataMap) {

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(actSingleId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/single/actsinglelist";
            }
        }
        ActSingle actSingle = serviceResult.getResult();

        String ids = actSingle.getProductIds().substring(1, actSingle.getProductIds().length() - 1);
        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));

        dataMap.put("actSingle", actSingle);
        dataMap.put("productList", productsByIds.getResult());
        return "admin/promotion/single/actsingledetail";
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(HttpServletRequest request, int actSingleId, Map<String, Object> dataMap) {

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(actSingleId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/single/actsinglelist";
            }
        }
        ActSingle actSingle = serviceResult.getResult();

        String ids = actSingle.getProductIds().substring(1, actSingle.getProductIds().length() - 1);
        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));

        dataMap.put("actSingle", actSingle);
        dataMap.put("productList", productsByIds.getResult());
        return "admin/promotion/single/actsingleaudit";
    }

    private List<Integer> getIds(String ids) {
        List<Integer> list = new ArrayList<Integer>();
        if (ids != null) {
            String[] split = ids.split(",");
            for (String id : split) {
                if (!StringUtil.isEmpty(id, true)) {
                    list.add(ConvertUtil.toInt(id, 0));
                }
            }
        }
        return list;
    }
    
    /**
     * 商品列表无分页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "listnopage2", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> listNoPage(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", null);
        ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult; 
    }

    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAudit(HttpServletRequest request,
                                                         @RequestParam("id") Integer id,
                                                         @RequestParam("status") Integer status) {

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(id);

        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        ActSingle actSingleDb = serviceResult.getResult();
        if (actSingleDb == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }

        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_2) {
            return new HttpJsonResult<Boolean>("非提交审核状态的活动不能执行审核操作。");
        }

        String auditOpinion = request.getParameter("auditOpinion");
        if (status.intValue() == ActSingle.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Boolean>("请填写审核失败原因。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActSingle actSingle = new ActSingle();
        actSingle.setId(id);
        actSingle.setStatus(status);
        actSingle.setAuditOpinion(auditOpinion);
        actSingle.setAuditUserId(adminUser.getId());
        actSingle.setAuditUserName(adminUser.getName());
        actSingle.setAuditTime(new Date());
        actSingle.setSellerId(actSingleDb.getSellerId());
        ServiceResult<Boolean> updateResult = actSingleService.updateActSingleStatus(actSingle);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!updateResult.getSuccess()) {
            jsonResult.setMessage(updateResult.getMessage());
        }
        return jsonResult;
    }
    
    
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> audit(HttpServletRequest request,
                                                       @RequestParam("id") Integer id) {

        ServiceResult<ActSingle> actSingleRlt = actSingleService.getActSingleById(id);

        if (!actSingleRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(actSingleRlt.getMessage());
        }
        if (actSingleRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }
        ActSingle actSingleDb = actSingleRlt.getResult();
        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_1
            && actSingleDb.getStatus().intValue() != ActSingle.STATUS_4) {
            return new HttpJsonResult<Boolean>("非新建或审核失败的活动不能提交审核。");
        }

        SystemAdmin sellerUser = WebAdminSession.getAdminUser(request);

        ActSingle actSingle = new ActSingle();
        actSingle.setId(id);
        actSingle.setStatus(ActSingle.STATUS_2);
        actSingle.setUpdateUserId(sellerUser.getId());
        actSingle.setUpdateUserName(sellerUser.getName());
        actSingle.setUpdateTime(new Date());
        actSingle.setSellerId(1);
        ServiceResult<Boolean> serviceResult = actSingleService.updateActSingleStatus(actSingle);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    /**
     * 删除
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<ActSingle> actSingleResult = actSingleService.getActSingleById(id);
        if (!actSingleResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(actSingleResult.getMessage());
        }
        if (actSingleResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("单品立减信息获取失败。");
        }
        ActSingle actSingle = actSingleResult.getResult();
        if (actSingle.getStatus().intValue() != ActSingle.STATUS_1
            && actSingle.getStatus().intValue() != ActSingle.STATUS_4) {
            return new HttpJsonResult<Boolean>("只能删除新建或审核失败的活动。");
        }

        SystemAdmin sellerUser = WebAdminSession.getAdminUser(request);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = actSingleService.deleteActSingle(id,
            sellerUser.getId(), sellerUser.getName());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    /**
     * 上架
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> up(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {

        ServiceResult<ActSingle> actSingleRlt = actSingleService.getActSingleById(id);

        if (!actSingleRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(actSingleRlt.getMessage());
        }
        if (actSingleRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }
        ActSingle actSingleDb = actSingleRlt.getResult();
        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_3) {
            return new HttpJsonResult<Boolean>("非审核通过状态的活动不能上架。");
        }

        SystemAdmin sellerUser = WebAdminSession.getAdminUser(request);

        ActSingle actSingle = new ActSingle();
        actSingle.setId(id);
        actSingle.setStatus(ActSingle.STATUS_5);
        actSingle.setUpdateUserId(sellerUser.getId());
        actSingle.setUpdateUserName(sellerUser.getName());
        actSingle.setUpdateTime(new Date());
        actSingle.setSellerId(1);
        ServiceResult<Boolean> serviceResult = actSingleService.updateActSingleStatus(actSingle);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 下架
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> down(HttpServletRequest request,
                                                      @RequestParam("id") Integer id) {

        ServiceResult<ActSingle> actSingleRlt = actSingleService.getActSingleById(id);

        if (!actSingleRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(actSingleRlt.getMessage());
        }
        if (actSingleRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }
        ActSingle actSingleDb = actSingleRlt.getResult();
        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_5) {
            return new HttpJsonResult<Boolean>("非上架状态的活动不能执行下架操作。");
        }

        SystemAdmin sellerUser = WebAdminSession.getAdminUser(request);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        ActSingle actSingle = new ActSingle();
        actSingle.setId(id);
        actSingle.setStatus(ActSingle.STATUS_6);
        actSingle.setUpdateUserId(sellerUser.getId());
        actSingle.setUpdateUserName(sellerUser.getName());
        actSingle.setUpdateTime(new Date());
        actSingle.setSellerId(1);
        ServiceResult<Boolean> serviceResult = actSingleService.updateActSingleStatus(actSingle);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, int actSingleId, Map<String, Object> dataMap) {

        SystemAdmin sellerUser = WebAdminSession.getAdminUser(request);
        dataMap.put("sellerId", sellerUser.getId().toString());

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(actSingleId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/single/actsinglelist2";
            }
        }
        ActSingle actSingle = serviceResult.getResult();

        String ids = actSingle.getProductIds().substring(1, actSingle.getProductIds().length() - 1);
        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));

        dataMap.put("actSingle", actSingle);
        dataMap.put("productList", productsByIds.getResult());
        dataMap.put("productNum",
            productsByIds.getResult() == null ? 0 : productsByIds.getResult().size());
        return "admin/promotion/single/actsingleedit";
    }
    
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActSingle actSingle, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin sellerUser = WebAdminSession.getAdminUser(request);
        String actSingleId = request.getParameter("actSingleId");
        actSingle = actSingleService.getActSingleById(Integer.valueOf(actSingleId)).getResult();
        actSingle.setUpdateUserId(sellerUser.getId());
        actSingle.setUpdateUserName(sellerUser.getName());
//      actSingle.setSellerId(sellerUser.getId());

        Map<String , String> seller_map = new HashMap<String , String>();
        // 组装活动商品
        String[] productIds = request.getParameterValues("ids");
        Map<String ,String> map = this.getIds2(productIds,seller_map);
        Set<String> keys = map.keySet();
        
        if (productIds == null || productIds.length == 0) {
            dataMap.put("sellerId", 1);
            dataMap.put("actSingle", actSingle);
            dataMap.put("message", "请选择参加活动的商品。");
            return "admin/promotion/single/actsingleedit";
        }
        
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        Integer sellerId;
        for(String key:keys){
            sellerId = Integer.valueOf(key);
            if(sellerId == actSingle.getSellerId()){
                actSingle.setProductIds("," + map.get(key) + ",");
                serviceResult = actSingleService.updateActSingle(actSingle);
            }else{
                actSingle.setSellerId(sellerId);
                actSingle.setProductIds("," + map.get(key) + ",");
                serviceResult = actSingleService.saveActSingle(actSingle);
            }
        }
        
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actSingle", actSingle);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/single/actsingleedit";
            }
        }
        return "redirect:/admin/promotion/single/singleset";
    }
    
    //    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    //    public String doAudit(HttpServletRequest request, Map<String, Object> dataMap,
    //                          @RequestParam("id") Integer id, @RequestParam("status") Integer status) {
    //
    //        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(id);
    //
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                dataMap.put("message", serviceResult.getMessage());
    //                return "admin/promotion/single/actsinglelist";
    //            }
    //        }
    //        ActSingle actSingleDb = serviceResult.getResult();
    //
    //        String ids = actSingleDb.getProductIds().substring(1,
    //            actSingleDb.getProductIds().length() - 1);
    //        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));
    //
    //        dataMap.put("actSingle", actSingleDb);
    //        dataMap.put("productList", productsByIds.getResult());
    //
    //        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_2) {
    //            dataMap.put("message", "非提交审核状态的活动不能执行审核操作。");
    //            return "admin/promotion/single/actsingleaudit";
    //        }
    //
    //        String auditOpinion = request.getParameter("auditOpinion");
    //        if (status.intValue() == ActSingle.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
    //            dataMap.put("message", "请填写审核失败原因。");
    //            return "admin/promotion/single/actsingleaudit";
    //        }
    //
    //        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
    //
    //        ActSingle actSingle = new ActSingle();
    //        actSingle.setId(id);
    //        actSingle.setStatus(status);
    //        actSingle.setAuditOpinion(auditOpinion);
    //        actSingle.setAuditUserId(adminUser.getId());
    //        actSingle.setAuditUserName(adminUser.getName());
    //        actSingle.setAuditTime(new Date());
    //        actSingle.setSellerId(actSingleDb.getSellerId());
    //        ServiceResult<Boolean> updateResult = actSingleService.updateActSingleStatus(actSingle);
    //        if (!updateResult.getSuccess()) {
    //            dataMap.put("message", updateResult.getMessage());
    //            return "admin/promotion/single/actsingleaudit";
    //        }
    //
    //        return "redirect://promotion/single";
    //    }

}
