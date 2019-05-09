package com.phkj.service.system;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.system.Regions;
import com.phkj.vo.system.RegionsVO;

/**
 * 区域
 *                       
 * @Filename: IRegionsService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IRegionsService {

    /**
     * 根据id取得regions对象
     * @param  regionsId
     * @return
     */
    ServiceResult<Regions> getRegionsById(Integer regionsId);

    List<Regions> getProvince();

    /**
     * 以上级地区获取其下所有子地区
     * @param parent
     * @param type
     * @return
     */
    List<Regions> getChildrenArea(Integer parent, String type);

    /**
     * 所有地区
     * @return
     */
    List<RegionsVO> getAllArea();

    /**
     * 根据父id取得regions对象
     * @param parentId
     * @return
     */
    ServiceResult<List<Regions>> getRegionsByParentId(Integer parentId);
    
    ServiceResult<List<Regions>> getParterSignArea(Integer memberId,String signNo);
    
    ServiceResult<List<Regions>> page(Map<String, String> queryMap, PagerInfo pager);
}