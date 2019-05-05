package com.ejavashop.web.controller.member;

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

import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

@Controller
@RequestMapping(value = "member/coupon")
public class MemberCouponController extends BaseController {
    @Resource
    private ICouponService couponService;

    /**
     * 优惠券列表
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list.html", method = { RequestMethod.GET,RequestMethod.POST })
    public String list(HttpServletRequest request, HttpServletResponse response,
                       Map<String, Object> dataMap, Integer rownum) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        Integer pageSize = rownum == null || rownum == 0 ? 5 : rownum + 5;
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, pageSize);
        ServiceResult<List<CouponUser>> serviceResult = couponService
            .getCouponUserByMemberId(sessionMember.getId(), pager);
        List<CouponUser> couponUsers = serviceResult.getResult();
        for (CouponUser couponUser : couponUsers) {
            if (couponUser.getUseEndTime().getTime() > new Date().getTime()) {
                couponUser.setTimeout(false);
            } else {
                couponUser.setTimeout(true);
            }
            if (couponUser.getCanUse() != null && couponUser.getCanUse().intValue() == 1) {
                //未使用
                couponUser.setIsuse(false);
            } else {
                couponUser.setIsuse(true);
            }
        }

        String url = request.getRequestURI() + "";

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(), url);

        dataMap.put("couponUsers", couponUsers);
        dataMap.put("page", pm);

        return "h5v1/member/coupon/membercoupon";
    }

    /**
     * 用户在线领取优惠券
     * @param request
     * @param response
     * @param couponId
     * @return
     */
    @RequestMapping(value = "/reveivecoupon.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> getProductFlashInfo(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     @RequestParam(value = "couponId", required = true) Integer couponId) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> receiveCoupon = couponService.receiveCoupon(couponId,
            sessionMember.getId());
        if (!receiveCoupon.getSuccess()) {
            return new HttpJsonResult<Boolean>(receiveCoupon.getMessage());
        }
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        return jsonResult;
    }
}
