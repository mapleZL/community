package com.ejavashop.model.system;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.system.RegionsReadDao;
import com.ejavashop.dao.shop.write.system.RegionsWriteDao;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.SystemResources;
import com.ejavashop.vo.system.RegionsVO;

@Component(value = "regionsModel")
public class RegionsModel {

    @Resource
    private RegionsWriteDao regionsWriteDao;

    @Resource
    private RegionsReadDao  regionsReadDao;

    /**
    * 根据id取得regions对象
    * @param  regionsId
    * @return
    */
    public Regions getRegionsById(Integer regionsId) {
        return regionsReadDao.get(regionsId);
    }

    public List<Regions> getProvince() {
        List<Regions> list = this.regionsReadDao.getProvince();
        return list;
    }
    
    public List<Regions> getParterSignArea(Integer memberId,String signNo) {
    	List<Regions> list = this.regionsReadDao.getParterSignArea(memberId,signNo);
    	return list;
    }

    public List<Regions> getChildrenArea(Integer parentid, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", parentid);
        map.put("type", type);
        List<Regions> list = this.regionsReadDao.getChildrenArea(map);
        return list;
    }
    
    public Integer pageCount(Map<String, String> queryMap) {
        return regionsReadDao.getCount(queryMap);
    }

    public List<Regions> page(Map<String, String> queryMap) {
        return regionsReadDao.page(queryMap);
    }

    public List<RegionsVO> getAllArea() throws IllegalAccessException, InvocationTargetException,
                                        NoSuchMethodException {
        List<Regions> pros = regionsReadDao.getProvince();
        List<RegionsVO> prosVO = new ArrayList<RegionsVO>();
        for (Regions pro : pros) {
            RegionsVO proVO = new RegionsVO();
            PropertyUtils.copyProperties(proVO, pro);

            List<Regions> citys = regionsReadDao.getByParentId(pro.getId());
            List<RegionsVO> citysVO = new ArrayList<RegionsVO>();
            for (Regions city : citys) {
                RegionsVO cityVO = new RegionsVO();
                PropertyUtils.copyProperties(cityVO, city);
                citysVO.add(cityVO);
                
                List<Regions> areas = regionsReadDao.getByParentId(city.getId());
                List<RegionsVO> areasVO = new ArrayList<RegionsVO>();
                for (Regions area : areas) {
                	RegionsVO areaVO = new RegionsVO();
                    PropertyUtils.copyProperties(areaVO, area);
                    areasVO.add(areaVO);
                }
                cityVO.setChildren(areasVO);
                
            }
            proVO.setChildren(citysVO);

            prosVO.add(proVO);
        }
        return prosVO;
    }

    public List<Regions> getRegionsByParentId(Integer parentId) {
        return regionsReadDao.getByParentId(parentId);
    }
}
