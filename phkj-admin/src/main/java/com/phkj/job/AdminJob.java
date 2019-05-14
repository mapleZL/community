package com.phkj.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.redis.RedisComponent;
import com.phkj.entity.system.Code;
import com.phkj.model.system.CodeModel;


/**
 * 
 *                       
 * @Filename: AdminJob.java
 * @Version: 1.0
 * @date: 2019年5月14日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class AdminJob {
    
    @Resource
    private CodeModel codeModel;
    @Resource
    private RedisComponent redisComponent;
    
    private static final Logger  log = LogManager.getLogger(AdminJob.class);
    
    /**
     * 字典定时更新
     */
    public void jobUpdateCode() {
        List<Code> codes = codeModel.getAllCodes();
        Map<String, List<Code>> codeMap = new HashMap<>();
        if (codes != null) {
            List<Code> tempList = null;
            for (Code code : codes) {
                if (codeMap.containsKey(code.getCodeDiv())) {
                    tempList = codeMap.get(code.getCodeDiv());
                } else {
                    tempList = new ArrayList<>();
                    codeMap.put(code.getCodeDiv(), tempList);
                }
                tempList.add(code);
            }
            redisComponent.setStringPersistence("codeValuesKey", JSONObject.toJSONString(tempList));
            log.info("字典表更新成功");
        } else {
            log.error("定时更新字典表发生错误，未查询到数据");
        }
    }
}
