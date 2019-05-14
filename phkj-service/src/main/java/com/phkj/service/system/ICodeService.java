package com.phkj.service.system;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.system.Code;

/**
 *                       
 * @Filename: ICodeService.java
 * @Version: 1.0
 * @date: 2019年5月14日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public interface ICodeService {

    /**
     * 获取字典数据。
     * @param queryMap
     * @param pager
     * @return ServiceResult<List<Code>>
     */
    ServiceResult<List<Code>> getCodes(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据条件获取单个字典数据。
     * @param codeDiv
     * @param codeCd
     * @return ServiceResult<Code>
     */
    ServiceResult<Code> getCode(String codeDiv, String codeCd);

    /**
     * 新增字典数据。
     * @param code
     * @return ServiceResult<Integer>
     */
    ServiceResult<Integer> createCode(Code code);

    /**
     * 修改字典数据。
     * @param code
     * @return ServiceResult<Boolean>
     */
    ServiceResult<Boolean> updateCode(Code code);
    
    List<Map<String, Object>> customReport(String sql); 
    
    /**
     * 获取全部字典配置
     * @return
     */
    List<Code> getAllCodes();
}
