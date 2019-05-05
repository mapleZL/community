package com.ejavashop.web.controller.promotion;

import java.util.Date;
import java.util.HashMap;
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
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 优惠券管理controller
 *                       
 * @Filename: AdminCouponController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/coupon")
public class AdminCouponController extends BaseController {

    @Resource
    private ICouponService couponService;
    @Resource
    private ISellerService sellerService;

    /**
     * 订单满减列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/promotion/coupon/couponlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Coupon>> list(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<Coupon>> serviceResult = couponService.getCoupons(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<Coupon> list = serviceResult.getResult();

        HttpJsonResult<List<Coupon>> jsonResult = new HttpJsonResult<List<Coupon>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/promotion/coupon/couponadd";
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(HttpServletRequest request, int couponId, Map<String, Object> dataMap) {

        ServiceResult<Coupon> serviceResult = couponService.getCouponById(couponId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/coupon/couponlist";
            }
        }
        Coupon coupon = serviceResult.getResult();

        dataMap.put("coupon", coupon);
        return "admin/promotion/coupon/couponaudit";
    }
    
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(Coupon coupon, HttpServletRequest request, Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        coupon.setCreateUserId(userId);
        coupon.setCreateUserName(adminUser.getName());
        coupon.setUpdateUserId(adminUser.getId());
        coupon.setUpdateUserName(adminUser.getName());

        coupon.setStatus(Coupon.STATUS_1);
        //sellerId设置成0代表商城通用红包
        coupon.setSellerId(0);

        // 已发放数量默认0
        coupon.setReceivedNum(0);
        // 前缀修改为大写
        coupon.setPrefix(coupon.getPrefix().toUpperCase());

        ServiceResult<Boolean> serviceResult = couponService.saveCoupon(coupon);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("coupon", coupon);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/coupon/couponadd";
            }
        }
        return "redirect:/admin/promotion/coupon";
    }
    
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int couponId, Map<String, Object> dataMap) {
        ServiceResult<Coupon> serviceResult = couponService.getCouponById(couponId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/coupon/couponlist";
            }
        }
        Coupon coupon = serviceResult.getResult();

        dataMap.put("coupon", coupon);
        return "admin/promotion/coupon/couponedit";
    }
    
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(Coupon coupon, HttpServletRequest request, Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        coupon.setUpdateUserId(adminUser.getId());
        coupon.setUpdateUserName(adminUser.getName());

        coupon.setSellerId(0);

        // 前缀修改为大写
        coupon.setPrefix(coupon.getPrefix().toUpperCase());

        ServiceResult<Boolean> serviceResult = couponService.updateCoupon(coupon);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("coupon", coupon);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/coupon/couponedit";
            }
        }
        return "redirect:/admin/promotion/coupon";
    }
    
    @RequestMapping(value = "audit2", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> audit(HttpServletRequest request,
                                                       @RequestParam("id") Integer id) {

        ServiceResult<Coupon> couponRlt = couponService.getCouponById(id);

        if (!couponRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(couponRlt.getMessage());
        }
        if (couponRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("优惠券信息获取失败。");
        }
        Coupon couponDb = couponRlt.getResult();
        if (couponDb.getStatus().intValue() != Coupon.STATUS_1
            && couponDb.getStatus().intValue() != Coupon.STATUS_4) {
            return new HttpJsonResult<Boolean>("非新建或审核失败的优惠券不能提交审核。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (0 != couponDb.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能操作自己店铺的优惠券。");
        }

        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setStatus(Coupon.STATUS_2);
        coupon.setUpdateUserId(adminUser.getId());
        coupon.setUpdateUserName(adminUser.getName());
        coupon.setSellerId(0);
        ServiceResult<Boolean> serviceResult = couponService.updateCouponStatus(coupon);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<Coupon> couponResult = couponService.getCouponById(id);
        if (!couponResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(couponResult.getMessage());
        }
        if (couponResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("优惠券信息获取失败。");
        }
        Coupon coupon = couponResult.getResult();
        if (coupon.getStatus().intValue() != Coupon.STATUS_1
            && coupon.getStatus().intValue() != Coupon.STATUS_4) {
            return new HttpJsonResult<Boolean>("只能删除新建或审核失败的优惠券。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (0 != coupon.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能删除自己店铺的优惠券。");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = couponService.deleteCoupon(id, adminUser.getId(),
            adminUser.getName());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> up(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {

        ServiceResult<Coupon> couponRlt = couponService.getCouponById(id);

        if (!couponRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(couponRlt.getMessage());
        }
        if (couponRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("优惠券信息获取失败。");
        }
        Coupon couponDb = couponRlt.getResult();
        if (couponDb.getStatus().intValue() != Coupon.STATUS_3) {
            return new HttpJsonResult<Boolean>("非审核通过状态的优惠券不能上架。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (0 != couponDb.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能操作自己店铺的优惠券。");
        }

        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setStatus(Coupon.STATUS_5);
        coupon.setUpdateUserId(adminUser.getId());
        coupon.setUpdateUserName(adminUser.getName());
        coupon.setSellerId(0);
        ServiceResult<Boolean> serviceResult = couponService.updateCouponStatus(coupon);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> down(HttpServletRequest request,
                                                      @RequestParam("id") Integer id) {

        ServiceResult<Coupon> couponRlt = couponService.getCouponById(id);

        if (!couponRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(couponRlt.getMessage());
        }
        if (couponRlt.getResult() == null) {
            return new HttpJsonResult<Boolean>("优惠券信息获取失败。");
        }
        Coupon couponDb = couponRlt.getResult();
        if (couponDb.getStatus().intValue() != Coupon.STATUS_5) {
            return new HttpJsonResult<Boolean>("非上架状态的优惠券不能执行下架操作。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (0 != couponDb.getSellerId().intValue()) {
            return new HttpJsonResult<Boolean>("只能操作自己店铺的优惠券。");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setStatus(Coupon.STATUS_6);
        coupon.setUpdateUserId(adminUser.getId());
        coupon.setUpdateUserName(adminUser.getName());
        coupon.setSellerId(0);
        ServiceResult<Boolean> serviceResult = couponService.updateCouponStatus(coupon);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAudit(HttpServletRequest request,
                                                         @RequestParam("id") Integer id,
                                                         @RequestParam("status") Integer status) {

        ServiceResult<Coupon> serviceResult = couponService.getCouponById(id);

        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        Coupon couponDb = serviceResult.getResult();
        if (couponDb == null) {
            return new HttpJsonResult<Boolean>("优惠券信息获取失败。");
        }

        if (couponDb.getStatus().intValue() != Coupon.STATUS_2) {
            return new HttpJsonResult<Boolean>("非提交审核状态的优惠券不能执行审核操作。");
        }

        String auditOpinion = request.getParameter("auditOpinion");
        if (status.intValue() == Coupon.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Boolean>("请填写审核失败原因。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setStatus(status);
        coupon.setAuditOpinion(auditOpinion);
        coupon.setAuditUserId(adminUser.getId());
        coupon.setAuditUserName(adminUser.getName());
        coupon.setAuditTime(new Date());
        coupon.setSellerId(couponDb.getSellerId());
        ServiceResult<Boolean> updateResult = couponService.updateCouponStatus(coupon);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!updateResult.getSuccess()) {
            jsonResult.setMessage(updateResult.getMessage());
        }
        return jsonResult;
    }

}
