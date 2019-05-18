package com.phkj.web.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.AESHelper;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.member.MemberHouse;
import com.phkj.entity.relate.StBaseinfoResidentHouse;
import com.phkj.entity.relate.StBaseinfoResidentinfo;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.member.IMemberHouseService;
import com.phkj.service.relate.IStBaseinfoResidentHouseService;
import com.phkj.service.relate.IStBaseinfoResidentinfoService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

/**
 * 房屋处理
 *                       
 * @Filename: MemberHouseController.java
 * @Version: 1.0
 * @date: 2019年5月15日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/admin/member/house")
public class MemberHouseController extends BaseController {

    @Resource
    IMemberHouseService             memberHouseService;
    @Resource
    IStBaseinfoResidentHouseService residentHouseService;
    @Autowired
    IStBaseinfoResidentinfoService  residentinfoService;

    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/memberhouselist";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil add(@RequestBody MemberHouse memberHouse, HttpServletRequest request) {
        // 校验信息是否填写完整
        ResponseUtil result = checkParam(memberHouse);
        if (result != null) {
            return result;
        }
        // 认证房屋与用户信息是否匹配
        result = authenticationHouseholder(memberHouse);

        if (result.isSuccess()) {
            memberHouseService.saveMemberHouse(memberHouse);
        }

        return result;
    }

    // 认证用户房屋信息
    private ResponseUtil authenticationHouseholder(MemberHouse memberHouse) {
        // TODO 校验本地信息是否已经存在，避免重复验证
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("houseId", memberHouse.getRoomId());
        queryMap.put("residentiaId", memberHouse.getVillageId());
        // 根据房屋信息查询用户房屋信息
        StBaseinfoResidentHouse stBaseinfoResidentHouse = residentHouseService
            .getResidentBouseByParam(queryMap);
        if (stBaseinfoResidentHouse != null) {
            // 根据关联居民信息查找用户
            StBaseinfoResidentinfo residentinfo = residentinfoService
                .getStBaseinfoResidentinfoById(stBaseinfoResidentHouse.getResidentId()).getResult();
            if (residentinfo != null) {
                // 身份证账号解密
                String idNo = AESHelper.Decrypt(residentinfo.getEncryptionIdNumber());
                if (residentinfo.getName().equals(memberHouse.getName())
                    && idNo.equals(memberHouse.getIdNumber())) {
                    return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), "认证成功",
                        true, null);
                } else {
                    return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), "认证失败",
                        false, null);
                }
            } else {
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(),
                    "您还没有在物业处登记身份，请先去登记身份", false, null);
            }
        } else {
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), "您还没有在物业处登记身份，请先去登记身份", false,
                null);
        }

    }

    /**
     * 校验必填参数
     * @param memberHouse
     * @return
     */
    private ResponseUtil checkParam(MemberHouse memberHouse) {
        if (memberHouse == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getRegion())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "region is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getCommunity())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "community is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getBuilding())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "building is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getRoom())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "room is empity", true,
                null);
        }
        if (StringUtils.isEmpty(memberHouse.getIdentityInformation())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "identityInformation is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getIdNumber())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "idNumber is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getStreet())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "street is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getVillage())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "village is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getUnit())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "unit is empity", true,
                null);
        }
        if (StringUtils.isEmpty(memberHouse.getPhone())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "phone is empity",
                true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "name is empity", true,
                null);
        }
        if (StringUtils.isEmpty(memberHouse.getImg())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "img is empity", true,
                null);
        }
        return null;
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberHouse>> list(HttpServletRequest request,
                                                                ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberHouse>> serviceResult = memberHouseService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberHouse>> jsonResult = new HttpJsonResult<List<MemberHouse>>();
        jsonResult.setRows((List<MemberHouse>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }
    
    /**
     * 微信端获取指定居民名下所有房屋信息
     * @param createUserId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    @ResponseBody
    public ResponseUtil list(String memberId, HttpServletResponse response) {
        if (StringUtils.isBlank(memberId)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "createUserId is blank", true, null);
        }
        ServiceResult<List<MemberHouse>> serviceResult = memberHouseService.getAllHouse(memberId);
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true,
            serviceResult.getResult());
    }

    /**
     * 房屋认证-通过（暂时弃用，采用数据对比的方式直接进行）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/passInfo", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                                      HttpServletResponse response, Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        MemberHouse memberHouse = new MemberHouse();
        memberHouse.setId(id);
        memberHouse.setExamineDate(new Date());
        memberHouse.setExamineUserId(userId);
        memberHouse.setStatus(MemberPropertyStatus.STATE_2);
        ServiceResult<Integer> serviceResult = memberHouseService.updateMemberHouse(memberHouse);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(true);
        return jsonResult;
    }

    /**
     * 房屋认证-不通过（暂时弃用，采用数据对比的方式直接进行）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/noPassInfo", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> noPass(HttpServletRequest request,
                                                        HttpServletResponse response, Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        MemberHouse memberHouse = new MemberHouse();
        memberHouse.setId(id);
        memberHouse.setExamineDate(new Date());
        memberHouse.setExamineUserId(userId);
        memberHouse.setStatus(MemberPropertyStatus.STATE_0);
        ServiceResult<Integer> serviceResult = memberHouseService.updateMemberHouse(memberHouse);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(true);
        return jsonResult;
    }
}
