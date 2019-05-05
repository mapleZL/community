package com.ejavashop.web.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.seller.SellerResourcesRoles;
import com.ejavashop.entity.seller.SellerRoles;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.ResourceTree;
import com.ejavashop.entity.system.SystemResources;
import com.ejavashop.service.seller.ISellerResourcesRolesService;
import com.ejavashop.service.seller.ISellerRolesService;
import com.ejavashop.service.system.ISystemResourcesService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 角色管理controller
 *                       
 * @Filename: RoleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/system/role")
public class SellerRoleController extends BaseController {
    @Resource
    private ISellerRolesService          sellerRolesService;
    @Resource
    private ISystemResourcesService      resourcesService;
    @Resource
    private ISellerResourcesRolesService sellerResourcesRolesService;

    Logger                               log = Logger.getLogger(this.getClass());

    /**
     * 验证角色编码不重复
     * @param request
     * @param response
     * @param roleCode
     */
    @RequestMapping(value = "validateRole", method = { RequestMethod.POST })
    public void validateRole(HttpServletRequest request, HttpServletResponse response,
                             String roleCode) {
        response.setContentType("text/plain");
        boolean isValid = true;
        PrintWriter pw = null;

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("q_roleCode", roleCode);
            ServiceResult<List<SellerRoles>> serviceResult = sellerRolesService.getSellerRoles(map,
                null);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            isValid = serviceResult.getResult().size() == 0;

            pw = response.getWriter();
        } catch (IOException e) {
            isValid = false;
            e.printStackTrace();
        }
        pw.print(isValid);
    }

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/system/role/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerRoles>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerRoles>> serviceResult = sellerRolesService.getSellerRoles(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SellerRoles>> jsonResult = new HttpJsonResult<List<SellerRoles>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "role2Res", method = { RequestMethod.GET })
    public String role2Res(HttpServletRequest request, Integer id, String rolesName,
                           Map<String, Object> dataMap) {
        dataMap.put("id", id);
        dataMap.put("rolesName", rolesName);
        return "seller/system/role/resWin";
    }

    @RequestMapping(value = "save", method = { RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, SellerRoles role) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        role.setUserId(sellerUser.getId());
        role.setStatus(SellerRoles.STATUS_1);
        role.setSellerId(sellerUser.getSellerId());
        ServiceResult<Integer> serviceResult = null;
        if (role.getId() != null && role.getId() != 0) {
            //编辑
            role.setUpdateTime(new Date());
            serviceResult = sellerRolesService.updateSellerRoles(role);
        } else {
            //新增
            serviceResult = sellerRolesService.saveSellerRoles(role);
        }
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.print(serviceResult.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存角色资源
     * @param request
     * @param response
     * @param roleId
     * @param resIds
     */
    @RequestMapping(value = "saveRoleRes", method = { RequestMethod.POST })
    public void saveRoleRes(HttpServletRequest request, HttpServletResponse response, String roleId,
                            String resIds) {
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;

        String[] resArr = resIds.split(",");
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            pw = response.getWriter();

            serviceResult = sellerResourcesRolesService.save(roleId, resArr);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        ServiceResult<Boolean> serviceResult = sellerRolesService.deleteSellerRole(id);
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.print(serviceResult.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "roleResTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> roleResTree(HttpServletRequest request,
                                                        Map<String, Object> dataMap,
                                                        String roleId) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        ServiceResult<List<SystemResources>> serviceResult = resourcesService
            .getByPid(ConstantsEJS.SELLER_RESOURCE_ROOT);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("rolesId", roleId);
        List<SellerResourcesRoles> srrlist = sellerResourcesRolesService.page(map, null)
            .getResult();
        List<Integer> resRoleIds = new ArrayList<Integer>();
        for (SellerResourcesRoles sr : srrlist) {
            resRoleIds.add(sr.getResourcesId());
        }
        generateTree(tree, serviceResult.getResult(), resRoleIds);
        return tree;
    }

    /**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     */
    private List<ResourceTree> generateTree(List<ResourceTree> treelist, List<SystemResources> data,
                                            List<Integer> resRoleIds) {
        for (SystemResources sr : data) {
            ResourceTree tree = new ResourceTree();
            tree.setId(sr.getId());
            tree.setText(sr.getContent());
            if (resRoleIds != null && resRoleIds.contains(sr.getId()))
                tree.setChecked(true);
            tree.setChildren(generateTree(new ArrayList<ResourceTree>(),
                resourcesService.getByPid(sr.getId()).getResult(), resRoleIds));
            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
            treelist.add(tree);
        }
        return treelist;
    }
}
