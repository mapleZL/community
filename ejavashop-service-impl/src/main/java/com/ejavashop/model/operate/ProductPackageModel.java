package com.ejavashop.model.operate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.operate.ProductPackageReadDao;
import com.ejavashop.dao.shop.write.operate.ProductPackageWriteDao;
import com.ejavashop.dao.shop.write.system.CodeWriteDao;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.system.Code;

@Component
public class ProductPackageModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductPackageModel.class);

    @Resource
    private ProductPackageWriteDao         productPackageWriteDao;
    @Resource
    private ProductPackageReadDao         productPackageReadDao;

    @Resource
    private CodeWriteDao                   codeWriteDao;

    /**
     * 根据id取得二次加工套餐设定
     * @param  productPackageId
     * @return
     */
    public ProductPackage getProductPackageById(Integer productPackageId) {
        return productPackageReadDao.get(productPackageId);
    }

    /**
     * 保存二次加工套餐设定
     * @param  productPackage
     * @return
     */
    public Integer saveProductPackage(ProductPackage productPackage) {
        this.dbConstrains(productPackage);
        return productPackageWriteDao.save(productPackage);
    }

    /**
    * 更新二次加工套餐设定
    * @param  productPackage
    * @return
    */
    public Integer updateProductPackage(ProductPackage productPackage) {
        this.dbConstrains(productPackage);
        return productPackageWriteDao.update(productPackage);
    }

    private void dbConstrains(ProductPackage productPackage) {
        productPackage.setName(StringUtil.dbSafeString(productPackage.getName(), false, 200));
        productPackage.setDescribe(StringUtil.dbSafeString(productPackage.getDescribe(), true,
            65535));
        productPackage.setImage(StringUtil.dbSafeString(productPackage.getImage(), true, 200));
        productPackage.setCreateName(StringUtil.dbSafeString(productPackage.getCreateName(), false,
            11));
    }

    public List<ProductPackage> page(Map<String, String> queryMap, PagerInfo pager) {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productPackageReadDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<ProductPackage> list = productPackageReadDao.page(param);
        for (ProductPackage propack : list) {
            Code code = codeWriteDao.getCode("PRO_PACKAGE_TYPE", propack.getPackingType() + "");
            propack.setPackingTypeName(code.getCodeText());
            code = codeWriteDao.getCode("PRO_PACKAGE_UNIT", propack.getUnitType() + "");
            propack.setUnitTypeName(code.getCodeText());

            StringBuffer sb = new StringBuffer();
            if (!StringUtil.isNullOrZero(propack.getIsHook())) {
                sb.append("钩子").append("、");
            }
            if (!StringUtil.isNullOrZero(propack.getIsGlue())) {
                sb.append("不干胶").append("、");
            }
            if (!StringUtil.isNullOrZero(propack.getIsLiner())) {
                sb.append("衬板").append("、");
            }
            if (!StringUtil.isNullOrZero(propack.getIsBag())) {
                sb.append("中包袋").append("、");
            }
            if (!StringUtil.isNullOrZero(propack.getIsLabel())) {
                sb.append("防伪标").append("、");
            }
            if (!StringUtil.isNullOrZero(propack.getIsGirdle())) {
                sb.append("腰封").append("、");
            }

            if (sb.length() > 0) {
                propack.setPackOther(sb.substring(0, sb.length() - 1));
            }
        }
        return list;
    }
}