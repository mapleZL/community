package com.ejavashop.web.controller.promotion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.promotion.IActFullService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 满减活动管理controller
 *                       
 * @Filename: SellerActFullController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/promotion/full")
public class SellerActFullController extends BaseController {

    @Resource
    private IActFullService actFullService;

    /**
     * 满减活动列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/promotion/full/actfulllist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActFull>> list(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId().toString());

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActFull>> serviceResult = actFullService.getActFulls(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActFull> list = serviceResult.getResult();

        HttpJsonResult<List<ActFull>> jsonResult = new HttpJsonResult<List<ActFull>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "seller/promotion/full/actfulladd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActFull actFull, HttpServletRequest request, Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Integer userId = sellerUser.getId();
        actFull.setCreateUserId(userId);
        actFull.setCreateUserName(sellerUser.getName());
        actFull.setUpdateUserId(sellerUser.getId());
        actFull.setUpdateUserName(sellerUser.getName());

        actFull.setStatus(ActFull.STATUS_1);
        actFull.setSellerId(sellerUser.getSellerId());

        // 如果用户不设定二、三档次，则默认赋值为0
        if (actFull.getSecondFull() == null) {
            actFull.setSecondFull(BigDecimal.ZERO);
            actFull.setSecondDiscount(BigDecimal.ZERO);
            actFull.setThirdFull(BigDecimal.ZERO);
            actFull.setThirdDiscount(BigDecimal.ZERO);
        } else if (actFull.getThirdFull() == null) {
            actFull.setThirdFull(BigDecimal.ZERO);
            actFull.setThirdDiscount(BigDecimal.ZERO);
        }

        ServiceResult<Boolean> serviceResult = actFullService.saveActFull(actFull);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFull", actFull);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/promotion/full/actfulladd";
            }
        }
        return "redirect:/seller/promotion/full";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int actFullId, Map<String, Object> dataMap) {
        ServiceResult<ActFull> serviceResult = actFullService.getActFullById(actFullId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "seller/promotion/full/actfulllist";
            }
        }
        ActFull actFull = serviceResult.getResult();

        dataMap.put("actFull", actFull);
        return "seller/promotion/full/actfulledit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActFull actFull, HttpServletRequest request, Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        actFull.setUpdateUserId(sellerUser.getId());
        actFull.setUpdateUserName(sellerUser.getName());

        actFull.setSellerId(sellerUser.getSellerId());

        // 如果用户不设定二、三档次，则默认赋值为0
        if (actFull.getSecondFull() == null
            || actFull.getSecondFull().compareTo(BigDecimal.ZERO) < 1) {
            actFull.setSecondFull(BigDecimal.ZERO);
            actFull.setSecondDiscount(BigDecimal.ZERO);
            actFull.setThirdFull(BigDecimal.ZERO);
            actFull.setThirdDiscount(BigDecimal.ZERO);
        } else if (actFull.getThirdFull() == null
                   || actFull.getThirdFull().compareTo(BigDecimal.ZERO) < 1) {
            actFull.setThirdFull(BigDecimal.ZERO);
            actFull.setThirdDiscount(BigDecimal.ZERO);
        }

        ServiceResult<Boolean> serviceResult = actFullService.updateActFull(actFull);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFull", actFull);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/promotion/full/actfulledit";
            }
        }
        return "redirect:/seller/promotion/full";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<ActFull> actFullResult = actFullService.getActFullById(id);
        if (!actFullResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(actFullResult.getMessage());
        }
        if (actFullResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("满减活动信息获取失败。");
        }
        ActFull actFull = actFullResult.getResult();
        if (actFull.getStatus().intValue() != ActFull.STATUS_1
            && actFull.getStatus().intValue() != ActFull.STATUS_4) {
            return new HttpJsonResult<Boolean>("只能删除新建或审核失败的活动。");
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (sellerUser.getSellerId().intValue() != actFull.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能删除自己店铺的活动。");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = actFullService.deleteActFull(id, sellerUser.getId(),
            sellerUser.getName());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> audit(HttpServletRequest request,
                                                       @RequestParam("id") Integer id) {

        ServiceResult<ActFull> actFullRlt = actFullService.getActFullById(id);

        if (!actFullRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(actFullRlt.getMessage());
        }
        if (actFullRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }
        ActFull actFullDb = actFullRlt.getResult();
        if (actFullDb.getStatus().intValue() != ActFull.STATUS_1
            && actFullDb.getStatus().intValue() != ActFull.STATUS_4) {
            return new HttpJsonResult<Boolean>("非新建或审核失败的活动不能提交审核。");
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (sellerUser.getSellerId().intValue() != actFullDb.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能操作自己店铺的活动。");
        }

        ActFull actFull = new ActFull();
        actFull.setId(id);
        actFull.setStatus(ActFull.STATUS_2);
        actFull.setUpdateUserId(sellerUser.getId());
        actFull.setUpdateUserName(sellerUser.getName());
        actFull.setUpdateTime(new Date());
        actFull.setSellerId(sellerUser.getSellerId());
        ServiceResult<Boolean> serviceResult = actFullService.updateActFullStatus(actFull);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> up(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {

        ServiceResult<ActFull> actFullRlt = actFullService.getActFullById(id);

        if (!actFullRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(actFullRlt.getMessage());
        }
        if (actFullRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }
        ActFull actFullDb = actFullRlt.getResult();
        if (actFullDb.getStatus().intValue() != ActFull.STATUS_3) {
            return new HttpJsonResult<Boolean>("非审核通过状态的活动不能上架。");
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (sellerUser.getSellerId().intValue() != actFullDb.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能操作自己店铺的活动。");
        }

        ActFull actFull = new ActFull();
        actFull.setId(id);
        actFull.setStatus(ActFull.STATUS_5);
        actFull.setUpdateUserId(sellerUser.getId());
        actFull.setUpdateUserName(sellerUser.getName());
        actFull.setUpdateTime(new Date());
        actFull.setSellerId(sellerUser.getSellerId());
        ServiceResult<Boolean> serviceResult = actFullService.updateActFullStatus(actFull);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> down(HttpServletRequest request,
                                                      @RequestParam("id") Integer id) {

        ServiceResult<ActFull> actFullRlt = actFullService.getActFullById(id);

        if (!actFullRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(actFullRlt.getMessage());
        }
        if (actFullRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }
        ActFull actFullDb = actFullRlt.getResult();
        if (actFullDb.getStatus().intValue() != ActFull.STATUS_5) {
            return new HttpJsonResult<Boolean>("非上架状态的活动不能执行下架操作。");
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (sellerUser.getSellerId().intValue() != actFullDb.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能操作自己店铺的活动。");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        ActFull actFull = new ActFull();
        actFull.setId(id);
        actFull.setStatus(ActFull.STATUS_6);
        actFull.setUpdateUserId(sellerUser.getId());
        actFull.setUpdateUserName(sellerUser.getName());
        actFull.setUpdateTime(new Date());
        actFull.setSellerId(sellerUser.getSellerId());
        ServiceResult<Boolean> serviceResult = actFullService.updateActFullStatus(actFull);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
