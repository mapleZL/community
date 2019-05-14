package com.phkj.web.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.system.Code;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.system.ICodeService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;
import com.phkj.web.util.freemarkerutil.CodeManager;

/**
 *                       
 * @Filename: AdminCodeController.java
 * @Version: 1.0
 * @date: 2019年5月14日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/system/code")
public class AdminCodeController extends BaseController {

    @Resource(name = "codeService")
    private ICodeService codeService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/system/code/codelist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Code>> list(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Code>> serviceResult = codeService.getCodes(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Code>> jsonResult = new HttpJsonResult<List<Code>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        //        code.setUseYn(ConstantsJM.USE_YN_Y);
        dataMap.put("code", code);
        return "admin/system/code/codeadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(Code code, HttpServletRequest request, Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        code.setCreateUser(adminUser.getName());
        code.setCreateUserId(adminUser.getId());

        ServiceResult<Integer> serviceResult = codeService.createCode(code);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("code", code);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/system/code/codeadd";
            }
        }
        CodeManager.init();
        return "redirect:";
    }

    /**
     * 
     * @param codeDiv
     * @param codeCd
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(String codeDiv, String codeCd, Map<String, Object> dataMap) {
        ServiceResult<Code> serviceResult = codeService.getCode(codeDiv, codeCd);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("code", serviceResult.getResult());
        return "admin/system/code/codeedit";
    }

    /**
     * 更新字典表
     * @param code
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(Code code, HttpServletRequest request, Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        code.setUpdateUserId(adminUser.getId());
        code.setUpdateUser(adminUser.getName());

        ServiceResult<Boolean> serviceResult = codeService.updateCode(code);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("code", code);
                return "admin/system/code/codeedit";
            }
        }
        CodeManager.init();
        return "redirect:";
    }
}
