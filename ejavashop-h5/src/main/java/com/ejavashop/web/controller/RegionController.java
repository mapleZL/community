package com.ejavashop.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.vo.system.RegionsVO;
import com.ejavashop.vo.system.Tree;
import com.alibaba.fastjson.JSON;

/**
 * 区域controller
 *
 * @Filename: RegionController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class RegionController extends BaseController {

    private static Logger   log = LogManager.getLogger(RegionController.class);
    
    @Resource
    private IRegionsService regionsService;

    @RequestMapping(value = "getRegionByParentId", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Regions>> getRegionByParentId(Integer parentId,
                                                                           HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           Map<String, Object> dataMap) {

        ServiceResult<List<Regions>> serviceResult = regionsService.getRegionsByParentId(parentId);
        if (!serviceResult.getSuccess()) {
            log.error("[RegionController][getRegionByParentId]根据父ID获取区域信息失败："
                      + serviceResult.getMessage());
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Regions>> jsonResult = new HttpJsonResult<List<Regions>>();
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "jsonRegion", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Tree>> jsonRegion(HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           Map<String, Object> dataMap) {
    	
    	List<RegionsVO> regionList = regionsService.getAllArea();
    	List<Tree> tree = new ArrayList<Tree>();
    	for (RegionsVO rv : regionList) {
    		Tree t = new Tree();
    		t.setId(rv.getId());
    		t.setName(rv.getRegionName());
    		
    		if (rv.getChildren() != null) {
    	    	List<Tree> tree1 = new ArrayList<Tree>();
    			for (RegionsVO rv1 : rv.getChildren()) {
    	    		Tree t1 = new Tree();
    	    		t1.setId(rv1.getId());
    	    		t1.setName(rv1.getRegionName());
    	    		
    	    		if (rv1.getChildren() != null) {
    	    	    	List<Tree> tree2 = new ArrayList<Tree>();
	    	    		for (RegionsVO rv2 : rv1.getChildren()) {
	        	    		Tree t2 = new Tree();
	        	    		t2.setId(rv2.getId());
	        	    		t2.setName(rv2.getRegionName());
	        	    		
	        	    		tree2.add(t2);
		    			}
	    	    		t1.setChild(tree2);
    	    		}
    	    		
    	    		tree1.add(t1);
    			}
    			t.setChild(tree1);
    		}
    		
    		tree.add(t);
    	}
        HttpJsonResult<List<Tree>> jsonResult = new HttpJsonResult<List<Tree>>();
        jsonResult.setData(tree);
        return jsonResult;
    }
    
}
