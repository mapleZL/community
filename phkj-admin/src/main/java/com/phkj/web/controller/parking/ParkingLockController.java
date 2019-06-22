package com.phkj.web.controller.parking;

import com.phkj.core.response.ResponseUtil;
import com.phkj.service.praking.ParkingLockService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/lock")
public class ParkingLockController {

    private final static Logger LOGGER = LogManager.getLogger(ParkingLockController.class);

    @Autowired
    ParkingLockService parkingLockService;

    /**
     * 解锁
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/unLock")
    public ResponseUtil unLock(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            if (parkingLockService.unLock(id,type)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("解锁失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 查询状态
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLockStatus")
    public ResponseUtil getLockStatus(String id,String type) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String lockStatus = parkingLockService.getLockStatus(id,type);
            responseUtil.setSuccess(true);
            if ("0".equals(lockStatus)) {
                responseUtil.setData("0");
            } else if ("1".equals(lockStatus)) {
                responseUtil.setData("1");
            } else {
                responseUtil.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("解锁失败!" + e);
        }
        return responseUtil;
    }
}
