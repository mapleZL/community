package com.phkj.web.controller.share;

import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.product.Product;
import com.phkj.service.share.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * gaowei
 */
@Controller
@RequestMapping("/share")
public class ShareController {


    @Autowired
    private ShareService shareService;

    /**
     * 查询我的发布信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeShareInfo")
    public ResponseUtil getMeShareInfo(HttpServletRequest request, Integer pageNum,
                                       Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String userId = request.getParameter("userId");
            Map<String, Object> returnMap = shareService.getMeShareInfo(userId, pageNum, pageSize);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            responseUtil.setSuccess(false);

        }
        return responseUtil;
    }


    /**
     * 根据id删除 假删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteShareInfo", method = RequestMethod.GET)
    public ResponseUtil deleteShareInfo(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        if (shareService.deleteShareInfo(id)) {
            responseUtil.setSuccess(true);
        } else {
            responseUtil.setSuccess(false);
        }

        return responseUtil;
    }
}
