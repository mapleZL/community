package com.ejavashop.web.controller.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.service.member.IMemberProductBackService;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 用户退货商家管理controller
 *
 * @Filename: SellerProductBackController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/order/productBack")
public class SellerProductBackController extends BaseController {

    Logger                            log = Logger.getLogger(this.getClass());

    @Resource
    private IMemberProductBackService memberProductBackService;
    @Resource
    private ICouponService            couponService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "seller/order/productback/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductBack>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService.page1(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberProductBack>> jsonResult = new HttpJsonResult<List<MemberProductBack>>();
        jsonResult.setRows((List<MemberProductBack>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 审核
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        MemberProductBack memberProductBack = this.memberProductBackService
            .getMemberProductBackById(id).getResult();
        dataMap.put("obj", memberProductBack);

        if (memberProductBack != null && memberProductBack.getBackCouponUserId() != null
            && memberProductBack.getBackCouponUserId() > 0) {
            ServiceResult<CouponUser> couponResult = couponService
                .getCouponUserById(memberProductBack.getBackCouponUserId());
            dataMap.put("couponUser", couponResult.getResult());
        }

        return "seller/order/productback/edit";
    }

    /**
     * 审核
     * @param request
     * @param response
     * @param id
     * @param type
     */
    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public void audit(HttpServletRequest request, HttpServletResponse response, Integer id,
                      Integer type, String remark) {
        //        if (type == null || "".equals(type))
        //            throw new IllegalArgumentException("type is null");
        //
        //        ServiceResult<MemberProductBack> serviceResult = memberProductBackService
        //            .getMemberProductBackById(id);
        //        if (!serviceResult.getSuccess()) {
        //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
        //                throw new RuntimeException(serviceResult.getMessage());
        //            } else {
        //                throw new BusinessException(serviceResult.getMessage());
        //            }
        //        }

        MemberProductBack back = new MemberProductBack();
        back.setId(id);
        if (!StringUtil.isEmpty(remark, true)) {
            back.setRemark(remark);
        }
        if (type.equals(2)) {
            back.setStateReturn(MemberProductBack.STATE_RETURN_2);
        } else if (type.equals(4)) {
            back.setStateReturn(MemberProductBack.STATE_RETURN_4);
        }
        memberProductBackService.updateMemberProductBack(back);

    }

    @RequestMapping(value = "takeDeliver", method = { RequestMethod.GET })
    public void takeDeliver(HttpServletRequest request, HttpServletResponse response, Integer id) {

        ServiceResult<MemberProductBack> serviceResult = memberProductBackService
            .getMemberProductBackById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        MemberProductBack backDB = serviceResult.getResult();
        if (backDB.getStateReturn() != MemberProductBack.STATE_MONEY_2) {
            throw new BusinessException("该退货申请不是可收货状态！");
        }
        MemberProductBack back = new MemberProductBack();
        back.setId(id);
        // 退货状态-已收货
        back.setStateReturn(MemberProductBack.STATE_RETURN_3);
        memberProductBackService.updateMemberProductBack(back);

    }

}
