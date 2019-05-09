package com.phkj.web.controller.share;

import com.phkj.service.share.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * gaowei
 */
@Controller
@RequestMapping("/share")
public class ShareController {


    @Autowired
    private ShareService shareService;

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeShareInfo")
    public List<Object> getMeShareInfo(HttpServletRequest request, Integer pageNum, Integer pageSize) {


        String userId = request.getParameter("userId");
        List<Object> list = shareService.getMeShareInfo(userId, pageNum, pageSize);
        return null;
    }
}
