package com.phkj.web.controller.lost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.lost.StAppletLostFound;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.lost.LostFoundService;
import com.phkj.web.util.WebAdminSession;


/**
 * 失物招领
 */
@Controller
@RequestMapping("/admin/lost")
public class LostFoundController {

    private final static Logger LOGGER = Logger.getLogger(LostFoundController.class);

    @Autowired
    LostFoundService lostFoundService;

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping("/")
    public String system(ModelMap modelMap) {
        modelMap.put("pageSize", "30");
        return "/admin/lost/lostfound";
    }

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping("/system")
    public String sys(ModelMap modelMap) {
        modelMap.put("pageSize", "30");
        return "/admin/lost/systemlostfound";
    }

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping("/system/create")
    public String create(ModelMap modelMap) {
        return "/admin/lost/addlostfound";
    }

    /**
     * 物业发布
     *
     * @param stAppletLostFound
     * @return
     */
    @RequestMapping("/system/addLostFound")
    public String systemAddLostFound(StAppletLostFound stAppletLostFound, HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        stAppletLostFound.setCreateUserId(adminUser.getId().toString());
        stAppletLostFound.setModifyUserName(adminUser.getName());
        stAppletLostFound.setModifyUserId(adminUser.getId().toString());
        stAppletLostFound.setVillageCode(adminUser.getVillageCode());
        if (lostFoundService.addSystemLostFound(stAppletLostFound, adminUser)) {
            responseUtil.setSuccess(true);
        }
        return "redirect:/admin/lost/system/";
    }

    /**
     * 物业发布
     *
     * @param stAppletLostFound
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/wx/addLostFound")
    public ResponseUtil systemWxAddLostFound(@RequestBody StAppletLostFound stAppletLostFound, HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        if (lostFoundService.addSystemLostFound(stAppletLostFound, null)) {
            responseUtil.setSuccess(true);
        }
        return responseUtil;
    }


    /**
     * 社区管理 删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/delete")
    public ResponseUtil systemDelete(String id, HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (lostFoundService.systemDelete(id, adminUser)) {
            responseUtil.setSuccess(true);
        }
        return responseUtil;
    }


    /**
     * 列表大厅
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getSystemLostFoundList")
    public HttpJsonResult<List<StAppletLostFound>> getSystemLostFoundList(Integer page, Integer rows,
                                                                          HttpServletRequest request) {
        HttpJsonResult<List<StAppletLostFound>> jsonResult = new HttpJsonResult<List<StAppletLostFound>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String type = request.getParameter("q_type");
            String sts = request.getParameter("q_sts");
            PageInfo<StAppletLostFound> pageInfo = lostFoundService.getSystemLostFoundList(page, rows, type,
                    sts, adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setRows(pageInfo.getList());
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!");
        }
        return jsonResult;
    }


    /**
     * 列表大厅
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/wx/getSystemLostFoundList")
    public HttpJsonResult<List<StAppletLostFound>> getWxSystemLostFoundList(Integer page, Integer rows,
                                                                            HttpServletRequest request) {
        HttpJsonResult<List<StAppletLostFound>> jsonResult = new HttpJsonResult<List<StAppletLostFound>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String type = request.getParameter("type");
            String sts = request.getParameter("sts");
            String villageCode = request.getParameter("villageCode");
            PageInfo<StAppletLostFound> pageInfo = lostFoundService.getSystemAllLostFound(page, rows, type,
                    sts, villageCode);
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setRows(pageInfo.getList());
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!");
        }
        return jsonResult;
    }

    /**
     * 列表大厅
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getSystemAllLostFound")
    public HttpJsonResult<List<StAppletLostFound>> getSystemAllLostFound(Integer page, Integer rows,
                                                                         HttpServletRequest request) {
        HttpJsonResult<List<StAppletLostFound>> jsonResult = new HttpJsonResult<List<StAppletLostFound>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String type = request.getParameter("q_type");
            String sts = request.getParameter("q_sts");
            PageInfo<StAppletLostFound> pageInfo = lostFoundService.getSystemAllLostFound(page, rows, type,
                    sts, adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setRows(pageInfo.getList());
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!");
        }
        return jsonResult;
    }


    /**
     * 发布失物招领
     *
     * @param stAppletLostFound
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addLostFound", method = RequestMethod.POST)
    public ResponseUtil addLostFound(@RequestBody StAppletLostFound stAppletLostFound) {
        ResponseUtil responseUtil = new ResponseUtil();
        if (StringUtils.isBlank(stAppletLostFound.getType())) {
            responseUtil.setMsg("类型不能为空");
        }
        if (StringUtils.isBlank(stAppletLostFound.getTitle())) {
            responseUtil.setMsg("标题不能为空!");
        }
        if (StringUtils.isBlank(stAppletLostFound.getTelephone())) {
            responseUtil.setMsg("手机号不能为空!");
        }
        if (StringUtils.isBlank(stAppletLostFound.getVillageCode())) {
            responseUtil.setMsg("清选择小区!");
        }
        if (lostFoundService.addLostFound(stAppletLostFound)) {
            responseUtil.setSuccess(true);
        }
        return responseUtil;
    }

    /**
     * 删除
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
        if (lostFoundService.delete(id, userId, userName)) {
            responseUtil.setSuccess(true);
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
    @RequestMapping("/getLostFoundDetail")
    public ResponseUtil getLostFoundDetail(String id) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            StAppletLostFound stAppletLostFound = lostFoundService.getDetail(id);
            responseUtil.setSuccess(true);
            responseUtil.setData(stAppletLostFound);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取详情失败" + e);
        }
        return responseUtil;
    }


    /**
     * 我的发布列表
     *
     * @param pageNum
     * @param pageSize
     * @param type
     * @param villageCode
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeLostFound")
    public ResponseUtil getMeLostFound(Integer pageNum, Integer pageSize, String type, String villageCode,
                                       String userId) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            PageInfo<StAppletLostFound> pageInfo = lostFoundService.getMeLostFound(pageNum, pageSize, type,
                    villageCode, userId);
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("list", pageInfo.getList());
            returnMap.put("total", pageInfo.getTotal());
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            LOGGER.error("查询我的列表失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 失物招领大厅
     *
     * @param pageNum
     * @param pageSize
     * @param type
     * @param villageCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllLostFoundList")
    public ResponseUtil getAllLostFoundList(Integer pageNum, Integer pageSize, String type, String villageCode) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            PageInfo<StAppletLostFound> pageInfo = lostFoundService.getAllLostFoundList(pageNum, pageSize, type, villageCode);
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("list", pageInfo.getList());
            returnMap.put("total", pageInfo.getTotal());
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取列表大厅失败!" + e);
        }
        return responseUtil;
    }


}
