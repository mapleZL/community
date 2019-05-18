package com.phkj.web.controller.member;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.StringUtil;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.member.StAppletPet;
import com.phkj.entity.member.StAppletPetWithBLOBs;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.member.IMemberPetService;
import com.phkj.web.controller.share.ShareController;
import com.phkj.web.util.WebAdminSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宠物登录页面
 *
 * @author gaowei
 */
@Controller
@RequestMapping("/admin/member/pet")
public class MemberPetController {

    private final static Logger LOGGER = LogManager.getLogger(ShareController.class);


    @Autowired
    IMemberPetService iMemberPetService;

    /**
     * 管理业接口
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/system")
    public String getSystem(ModelMap modelMap) {
        modelMap.put("pageSize", 30);
        modelMap.put("pageNum", 1);
        return "admin/member/member/memberPetList";
    }

    /**
     * 通过申请接口
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/updatePet")
    public ResponseUtil systemUpdate(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            Integer userId = adminUser.getId();
            String name = adminUser.getName();
            if (iMemberPetService.systemUpdatePet(id, type,userId,name)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("审批失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 后台管理页面列表页
     *
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/getAllPetList")
    public HttpJsonResult<List<StAppletPetWithBLOBs>> getAllPetList(HttpServletRequest request, Integer page,
                                                                    Integer rows) {
        String sts = request.getParameter("q_status");
        HttpJsonResult<List<StAppletPetWithBLOBs>> jsonResult = new HttpJsonResult<List<StAppletPetWithBLOBs>>();
        PageInfo<StAppletPetWithBLOBs> pageInfo = iMemberPetService.getAllPetList(page, rows, sts);
        String total = String.valueOf(pageInfo.getTotal());
        jsonResult.setRows(pageInfo.getList());
        jsonResult.setTotal(Integer.valueOf(total));
        return jsonResult;
    }

    /**
     * 更新宠物申请
     *
     * @param pet
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePet", method = RequestMethod.POST)
    public ResponseUtil updatePet(@RequestBody StAppletPetWithBLOBs pet) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (iMemberPetService.updatePet(pet)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            LOGGER.error("申请更新宠物失败!");
            e.printStackTrace();
        }
        return responseUtil;
    }

    /**
     * 申请删除宠物
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delPet", method = RequestMethod.GET)
    public ResponseUtil delPet(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (iMemberPetService.delPet(id, userId, userName)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            LOGGER.error("申请删除宠物失败!");
            e.printStackTrace();
        }
        return responseUtil;
    }

    /**
     * 登记宠物
     *
     * @param pet
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addPet", method = RequestMethod.POST)
    public ResponseUtil addPet(@RequestBody StAppletPetWithBLOBs pet) {
        ResponseUtil responseUtil = new ResponseUtil();
        String msg = checkParam(pet);
        if (StringUtils.isNotBlank(msg)) {
            responseUtil.setSuccess(false);
            responseUtil.setMsg(msg);
            return responseUtil;
        }
        try {
            if (iMemberPetService.addPet(pet)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("登记宠物失败!");
        }
        return responseUtil;
    }


    /**
     * 我的宠物详情
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPetDetail", method = RequestMethod.GET)
    public ResponseUtil getPetDetail(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        try {
            StAppletPet pet = iMemberPetService.getPetDetail(id);
            responseUtil.setSuccess(true);
            responseUtil.setData(pet);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取我的宠物详情失败!");
        }
        return responseUtil;
    }

    /**
     * 小程序查询我的宠物
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMePetListByPage", method = RequestMethod.GET)
    public ResponseUtil getMePetListByPage(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String userId = request.getParameter("userId");
            String sts = request.getParameter("sts");
            PageInfo<Map<String, Object>> pageInfo = iMemberPetService.getMePetListByPage(userId, sts, pageNum, pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            responseUtil.setSuccess(true);
            responseUtil.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取我的宠物列表失败!");
        }
        return responseUtil;
    }


    /**
     * (
     * 校验参数
     *
     * @param pet
     * @return
     */
    private String checkParam(StAppletPetWithBLOBs pet) {
        if (StringUtils.isBlank(pet.getCreateUserId())) {
            return "用户信息错误!";
        }
        if (StringUtils.isBlank(pet.getPetBreed())) {
            return "宠物类型不能为空!";
        }

        if (StringUtils.isBlank(pet.getGender())) {
            return "宠物性别不能为空!";
        }
        if (StringUtils.isBlank(pet.getExemptionNum())) {
            return "宠物健康免疫证不能为空!";
        }
        if (null == pet.getExemptionTime()) {
            return "宠物健康免疫时间不能为空!";
        }
        if (null == pet.getVaccineType()) {
            return "宠物疫苗种类不能为空!";
        }
        if (null == pet.getPetRegisNum()) {
            return "养犬登记编号不能为空!";
        }
       /* if (null == pet.getExemptionImg()) {
            return "请上传免检证书!";
        }
        if (null == pet.getPetRegisNum()) {
            return "请上传养犬登记证书!";
        }*/
        return "";
    }

}
