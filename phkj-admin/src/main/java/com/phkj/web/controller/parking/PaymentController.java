package com.phkj.web.controller.parking;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;

import com.phkj.entity.praking.StAppletPayment;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.praking.PaymentService;
import com.phkj.web.util.WebAdminSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/admin/payment")
public class PaymentController {

    private final static Logger LOGGER = LogManager.getLogger(PaymentController.class);

    @Autowired
    PaymentService paymentService;

    /**
     * @return
     */
    @RequestMapping("/")
    public String system(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/parking/paymentlist";
    }

    /**
     * Z管理页面
     */
    @ResponseBody
    @RequestMapping("/system/getAllPayment")
    public HttpJsonResult<List<StAppletPayment>> getAllPayment(HttpServletRequest request) {
        HttpJsonResult<List<StAppletPayment>> resultJson = new HttpJsonResult<>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String sts = request.getParameter("sts");
            String type = request.getParameter("type");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            PageInfo<StAppletPayment> pageInfo = paymentService.getAllPayment(page, rows, sts, type,
                    adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            resultJson.setRows(pageInfo.getList());
            resultJson.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return resultJson;
    }

}
