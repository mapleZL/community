package com.ejavashop.web.controller.order;

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
import com.ejavashop.entity.backgoods.BackGoods;
import com.ejavashop.entity.backgoods.BackGoodsRecord;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.backgoods.IBackGoodsRecordService;
import com.ejavashop.service.backgoods.IBackGoodsService;
import com.ejavashop.service.member.IMemberProductBackService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 用户退货商家管理controller
 *
 * @Filename: AdminProductBackController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/order/productBack")
public class AdminProductBackController extends BaseController {
    Logger                            log = Logger.getLogger(this.getClass());

    @Resource
    private IMemberProductBackService memberProductBackService;
    
    @Resource
    private IBackGoodsService bookGoodsService;
    
    @Resource
    private IBackGoodsRecordService bookGoodsRecordService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/productback/backlist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<BackGoods>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<BackGoods>> serviceResult = memberProductBackService
            .page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<BackGoods>> jsonResult = new HttpJsonResult<List<BackGoods>>();
        jsonResult.setRows((List<BackGoods>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "backmoney", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> backmoney(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Integer id) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ServiceResult<Boolean> serviceResult = memberProductBackService.backMoney(id,
            adminUser.getId(), adminUser.getName());
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        if (serviceResult.getResult() == null || !serviceResult.getResult()) {
            return new HttpJsonResult<Boolean>("退款失败，请重试！");
        }
        return new HttpJsonResult<Boolean>();
    }
    @RequestMapping(value="getBackGoodRecord",method={RequestMethod.POST ,RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<BackGoodsRecord>> getBackGoodRecord(HttpServletRequest request,
            HttpServletResponse response,Integer backId) {
    	ServiceResult<List<BackGoodsRecord>> res = bookGoodsRecordService
                .getBackGoodRecordByGoodsId(backId);
            HttpJsonResult<List<BackGoodsRecord>> json = new HttpJsonResult<List<BackGoodsRecord>>();
            json.setRows(res.getResult());
            json.setTotal(res.getResult().size());
            return json;
    }

}
