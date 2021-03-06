package com.phkj.web.controller.parking;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.praking.StAppletParking;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.praking.ParkingService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/parking")
public class ParkingController {

    private final static Logger LOGGER = LogManager.getLogger(ParkingController.class);

    @Autowired
    ParkingService parkingService;

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping("/system")
    public String system(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/parking/parkinglist";
    }

    @ResponseBody
    @RequestMapping("/system/update")
    public ResponseUtil systemUpdateParking(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            if (parkingService.systemUpdateParking(id, type, adminUser)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("更新失败!");
        }
        return responseUtil;
    }


    @ResponseBody
    @RequestMapping("/system/wx/update")
    public ResponseUtil systemWxUpdateParking(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String userId = request.getParameter("userId");
            String userName = request.getParameter("userName");
            SystemAdmin adminUser = new SystemAdmin();
            adminUser.setId(Integer.valueOf(userId));
            adminUser.setName(userName);
            if (parkingService.systemUpdateParking(id, type, adminUser)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("更新失败!");
        }
        return responseUtil;
    }


    /**
     * 后台管理查询所有
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getSystemAll")
    public HttpJsonResult<List<StAppletParking>> getSystemAll(HttpServletRequest request) {
        HttpJsonResult<List<StAppletParking>> resultJson = new HttpJsonResult<List<StAppletParking>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String sts = request.getParameter("q_status");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            PageInfo<StAppletParking> pageInfo = parkingService.getSystemAll(sts, page, rows, adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            resultJson.setRows(pageInfo.getList());
            resultJson.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return resultJson;
    }


    /**
     * 后台管理查询所有
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/wx/getSystemAll")
    public HttpJsonResult<List<StAppletParking>> getWxSystemAll(HttpServletRequest request) {
        HttpJsonResult<List<StAppletParking>> resultJson = new HttpJsonResult<List<StAppletParking>>();
        try {
            String sts = request.getParameter("status");
            String villageCode = request.getParameter("villageCode");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            PageInfo<StAppletParking> pageInfo = parkingService.getSystemAll("0", page, rows, villageCode);
            String total = String.valueOf(pageInfo.getTotal());
            resultJson.setRows(pageInfo.getList());
            resultJson.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return resultJson;
    }


    /**
     * 删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteParking")
    public ResponseUtil deleteParking(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String id = request.getParameter("id");
            String userId = request.getParameter("userId");
            String userName = request.getParameter("userName");
            if (parkingService.deleteParking(id, userId, userName)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            LOGGER.error("删除失败!" + e);
            e.printStackTrace();
        }
        return responseUtil;
    }

    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateParking")
    public ResponseUtil updateParking(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String id = request.getParameter("id");
            String userId = request.getParameter("userId");
            String villageCode = request.getParameter("villageCode");
            String userName = request.getParameter("userName");
            if (parkingService.updateParking(id, userId, villageCode, userName)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("续费失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 获取所有支付信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPayment")
    public ResponseUtil getPayment(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String villageCode = request.getParameter("villageCode");
            Map<String, Object> returnMap = parkingService.getPayment(villageCode);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return responseUtil;
    }


    /**
     * 查询我的租用车位
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeParking")
    public ResponseUtil getMeParking(HttpServletRequest request) {
        ResponseUtil result = new ResponseUtil();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String villageCode = request.getParameter("villageCode");
        String userId = request.getParameter("userId");
        String sts = request.getParameter("sts");
        try {
            PageInfo<StAppletParking> pageInfo = parkingService.getMeParking(pageNum, pageSize, villageCode,
                    userId, sts);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("list", pageInfo.getList());
            resultMap.put("total", pageInfo.getTotal());
            result.setSuccess(true);
            result.setData(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return result;
    }


    /**
     * 申请车位
     *
     * @param stAppletParking
     * @return
     */
    @ResponseBody
    @RequestMapping("/addParking")
    public ResponseUtil addParking(@RequestBody StAppletParking stAppletParking) {
        ResponseUtil result = new ResponseUtil();
        try {
            if (parkingService.addParking(stAppletParking)) {
                result.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return result;
    }
}
