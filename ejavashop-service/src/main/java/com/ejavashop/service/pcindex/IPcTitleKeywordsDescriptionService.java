package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.WmsClassify;
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;

public interface IPcTitleKeywordsDescriptionService {

	/**
     * 根据id取得pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescriptionId
     * @return
     */
    ServiceResult<PcTitleKeywordsDescription> getPcTitleKeywordsDescriptionById(Integer pcTitleKeywordsDescriptionId);
    
    /**
     * 保存pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescription
     * @return
     */
     ServiceResult<Integer> savePcTitleKeywordsDescription(PcTitleKeywordsDescription pcTitleKeywordsDescription);
     
     /**
     * 更新pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescription
     * @return
     */
     ServiceResult<Integer> updatePcTitleKeywordsDescription(PcTitleKeywordsDescription pcTitleKeywordsDescription);

     /**
      * 根据查询路径名获取唯一值
      * @param visitPath
      * @return
      */
     ServiceResult<PcTitleKeywordsDescription> getByPath(String visitPath);

     
     ServiceResult<List<PcTitleKeywordsDescription>> page(Map<String, String> queryMap, PagerInfo pager);
}