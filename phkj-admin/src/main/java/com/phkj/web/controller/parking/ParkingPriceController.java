package com.phkj.web.controller.parking;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.praking.StAppletParkingPrice;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.praking.ParkingPriceService;
import com.phkj.web.util.WebAdminSession;

@Controller
@RequestMapping("/admin/price")
public class ParkingPriceController {

    private final static Logger LOGGER = LogManager.getLogger(ParkingPriceController.class);

    @Autowired
    ParkingPriceService parkingPriceService;

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping
    public String system(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/parking/price";
    }

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping("/system/add")
    public String systemadd(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/parking/addprice";
    }

    /**
     * 修改数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(String id, String type, HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            if (parkingPriceService.update(id, type,adminUser.getVillageCode())) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改失败!" + e);
        }
        return responseUtil;
    }


    /**
     * 保存加个
     *
     * @param stAppletParkingPrice
     * @param request
     * @return
     */
    @RequestMapping("/system/save")
    public String save(StAppletParkingPrice stAppletParkingPrice, HttpServletRequest request, ModelMap modelMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        parkingPriceService.save(stAppletParkingPrice, adminUser);
        modelMap.put("pageSize", "30");
        return "/admin/parking/price";
    }


    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllPrice")
    public HttpJsonResult<List<StAppletParkingPrice>> getAllPrice(HttpServletRequest request) {
        HttpJsonResult<List<StAppletParkingPrice>> resultJson = new HttpJsonResult<List<StAppletParkingPrice>>();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            PageInfo<StAppletParkingPrice> pageInfo = parkingPriceService.selectAllPrice(page, rows,
                    adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            resultJson.setRows(pageInfo.getList());
            resultJson.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("查询失败!" + e);
        }
        return resultJson;
    }
}
