package com.ejavashop.service.impl.operate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.model.operate.ProductLabelModel;
import com.ejavashop.service.operate.IProductLabelService;

@Service(value = "productLabelService")
public class ProductLabelServiceImpl implements IProductLabelService {
    private static Logger     log = LogManager.getLogger(ProductLabelServiceImpl.class);

    @Resource
    private ProductLabelModel productLabelModel;

    /**
    * 根据id取得标签管理
    * @param  productLabelId
    * @return
    */
    @Override
    public ServiceResult<ProductLabel> getProductLabelById(Integer productLabelId) {
        ServiceResult<ProductLabel> result = new ServiceResult<ProductLabel>();
        try {
            result.setResult(productLabelModel.getProductLabelById(productLabelId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductLabelService][getProductLabelById]根据id[" + productLabelId
                      + "]取得标签管理时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductLabelService][getProductLabelById]根据id[" + productLabelId
                      + "]取得标签管理时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存标签管理
     * @param  productLabel
     * @return
     */
    @Override
    public ServiceResult<Integer> saveProductLabel(ProductLabel productLabel) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productLabelModel.saveProductLabel(productLabel));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductLabelService][saveProductLabel]保存标签管理时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductLabelService][saveProductLabel]保存标签管理时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新标签管理
    * @param  productLabel
    * @return
    * @see com.ejavashop.service.ProductLabelService#updateProductLabel(ProductLabel)
    */
    @Override
    public ServiceResult<Integer> updateProductLabel(ProductLabel productLabel) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productLabelModel.updateProductLabel(productLabel));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductLabelService][updateProductLabel]更新标签管理时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductLabelService][updateProductLabel]更新标签管理时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductLabel>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ProductLabel>> serviceResult = new ServiceResult<List<ProductLabel>>();
        try {
            serviceResult.setResult(productLabelModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ProductLabelServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }
    
    /**
     * 获取所有标签名称即辅料
     * @author 仝照美
     */
	@Override
	public ServiceResult<List<ProductLabel>> getProductLabelName() {
		ServiceResult<List<ProductLabel>> serviceResult = new ServiceResult<List<ProductLabel>>();
		try {
			serviceResult.setResult(productLabelModel.getProductLabelName());
		} catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ProductLabelServiceImpl][page] exception:" + e.getMessage());
        }
		return serviceResult;
	}
	/**
     * 根据sku_other值，来动态获得对应的辅料name
     * @param queryMap
     * @param pager
     * @return
     */
	@Override
	public ServiceResult<List<ProductLabel>> getProductLabelNameByskuother(String[] skuothers) {
		ServiceResult<List<ProductLabel>> serviceResult = new ServiceResult<List<ProductLabel>>();
		try {
			serviceResult.setResult(productLabelModel.getProductLabelNameByskuother(skuothers));
		} catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ProductLabelServiceImpl][page] exception:" + e.getMessage());
        }
		return serviceResult;
	}
}