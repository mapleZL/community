package com.phkj.web.controller.visitor;


import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.visit.VisitorService;
import com.phkj.web.util.WebAdminSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/visit")
public class VisitParkingLotController {

    @Autowired
    private VisitorService visitorService;

    /**
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("/applyLot")
    public String system(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("pageSize", "30");
        modelMap.put("pageNum", "1");
        return "/admin/visitor/parkinglotlist";
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getAll")
    public HttpJsonResult<List<StBaseinfoParkingLotOrder>> getAll(HttpServletRequest request) {
        HttpJsonResult<List<StBaseinfoParkingLotOrder>> returnJson = new HttpJsonResult<>();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String sts = request.getParameter("q_status");
        String type = request.getParameter("type");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        PageInfo<StBaseinfoParkingLotOrder> pageInfo = visitorService.getAllVisParkinigLot(page, rows, sts, type,
                adminUser);
        String total = String.valueOf(pageInfo.getTotal());
        returnJson.setTotal(Integer.valueOf(total));
        returnJson.setRows(pageInfo.getList());
        return returnJson;
    }
}
