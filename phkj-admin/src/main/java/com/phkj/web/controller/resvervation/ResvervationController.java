package com.phkj.web.controller.resvervation;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.environmental.StAppletEnvironment;
import com.phkj.entity.resvervation.StAppletReservation;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.resvervation.ResvervationService;
import com.phkj.web.util.WebAdminSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import javax.xml.ws.spi.http.HttpExchange;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 预约服务
 */
@Controller
@RequestMapping("/admin/reservation")
public class ResvervationController {

    private final static Logger LOGGER = LogManager.getLogger(ResvervationController.class);

    @Autowired
    ResvervationService resvervationService;

    /**
     * 跳转链接
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/")
    public String system(ModelMap modelMap) {
        modelMap.put("pageSize", "30");
        return "/admin/reservation/reservation";
    }

    @ResponseBody
    @RequestMapping("/system/getSystemReservation")
    public HttpJsonResult<List<StAppletReservation>> getSystemReservation(HttpServletRequest request) {
        HttpJsonResult<List<StAppletReservation>> jsonResult = new HttpJsonResult<List<StAppletReservation>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String status = request.getParameter("status");
            String sts = request.getParameter("sts");
            String type = request.getParameter("type");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            PageInfo<StAppletReservation> pageInfo = resvervationService.getSystemReservation(status, sts, type,
                    page, rows, adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setRows(pageInfo.getList());
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return jsonResult;
    }

    /**
     * 添加评论
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addComment")
    public ResponseUtil addComment(@RequestBody Comment comment) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (resvervationService.addComment(comment)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("评论失败!" + e);
        }
        return responseUtil;
    }


    /**
     * 添加数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addReservation")
    public ResponseUtil add(@RequestBody StAppletReservation stAppletReservation) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (resvervationService.add(stAppletReservation)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return responseUtil;
    }


    /**
     * 修改信息
     *
     * @param stAppletReservation
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(@RequestBody StAppletReservation stAppletReservation) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (resvervationService.update(stAppletReservation)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("更新失败!" + e);
        }
        return responseUtil;
    }


    /**
     * 删除预约
     *
     * @param id
     * @param userId
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(String id, String userId, String userName) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (resvervationService.delete(id, userId, userName)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败" + e);
        }
        return responseUtil;
    }

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getReservationDetail")
    public ResponseUtil getReservationDetail(String id) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            StAppletReservation stAppletReservation = resvervationService.getReservation(id);
            responseUtil.setSuccess(true);
            responseUtil.setData(stAppletReservation);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询详情失败" + e);
        }
        return responseUtil;
    }

    /**
     * 查询我的服务列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeReservation")
    public ResponseUtil getAllReservation(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        String userId = request.getParameter("userId");
        String villageCode = request.getParameter("villageCode");
        String sts = request.getParameter("sts");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            PageInfo<StAppletReservation> pageInfo = resvervationService.getAllReservation(userId,
                    villageCode, sts, status, pageNum, pageSize, type);
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("list", pageInfo.getList());
            returnMap.put("total", pageInfo.getTotal());
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return responseUtil;
    }

}
