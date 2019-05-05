package com.ejavashop.model.pcindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shopm.read.pcindex.PcTitleKeywordsDescriptionReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcTitleKeywordsDescriptionWriteDao;
import com.ejavashop.entity.product.WmsClassify;
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;

@Component
public class PcTitleKeywordsDescriptionModel {

	@SuppressWarnings("unused")
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(PcTitleKeywordsDescriptionModel.class);
    
    @Resource
    private PcTitleKeywordsDescriptionReadDao pcTitleKeywordsDescriptionReadDao;
    
    @Resource
    private PcTitleKeywordsDescriptionWriteDao pcTitleKeywordsDescriptionWriteDao;
    
    /**
     * 根据id取得pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescriptionId
     * @return
     */
    public PcTitleKeywordsDescription getPcTitleKeywordsDescriptionById(Integer pcTitleKeywordsDescriptionId) {
    	return pcTitleKeywordsDescriptionReadDao.get(pcTitleKeywordsDescriptionId);
    }
    
    /**
     * 保存pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescription
     * @return
     */
     public Integer savePcTitleKeywordsDescription(PcTitleKeywordsDescription pcTitleKeywordsDescription) {
     	this.dbConstrains(pcTitleKeywordsDescription);
     	return pcTitleKeywordsDescriptionWriteDao.insert(pcTitleKeywordsDescription);
     }
     
     /**
     * 更新pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescription
     * @return
     */
     public Integer updatePcTitleKeywordsDescription(PcTitleKeywordsDescription pcTitleKeywordsDescription) {
     	this.dbConstrains(pcTitleKeywordsDescription);
     	return pcTitleKeywordsDescriptionWriteDao.update(pcTitleKeywordsDescription);
     }
     
     private void dbConstrains(PcTitleKeywordsDescription pcTitleKeywordsDescription) {
		pcTitleKeywordsDescription.setPath(StringUtil.dbSafeString(pcTitleKeywordsDescription.getPath(), false, 250));
		pcTitleKeywordsDescription.setTitle(StringUtil.dbSafeString(pcTitleKeywordsDescription.getTitle(), false, 250));
		pcTitleKeywordsDescription.setKeywords(StringUtil.dbSafeString(pcTitleKeywordsDescription.getKeywords(), false, 250));
		pcTitleKeywordsDescription.setDescription(StringUtil.dbSafeString(pcTitleKeywordsDescription.getDescription(), true, 250));
		pcTitleKeywordsDescription.setApplyPage(StringUtil.dbSafeString(pcTitleKeywordsDescription.getApplyPage(), true, 250));
     }

     public PcTitleKeywordsDescription getByPath(String visitPath) {
         return pcTitleKeywordsDescriptionReadDao.getByPath(visitPath);
     }
    
     public int pageCount(Map<String, Object> param) {
         return pcTitleKeywordsDescriptionReadDao.getCount(param);
     }

     public List<PcTitleKeywordsDescription> page(Map<String, Object> param) {
         return pcTitleKeywordsDescriptionReadDao.page(param);
     }
}