package com.phkj.core;

import java.util.Map;

/**
 * 来源配置类
 *                       
 * @Filename: NoticeSourceConfig.java
 * @Version: 1.0
 * @date: 2019年5月21日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class NoticeSourceConfig {

    private Map<String, String> sourceMap;


    public Map<String, String> getSourceMap() {
        sourceMap.put("1", "街道");
        sourceMap.put("2", "社区");
        sourceMap.put("3", "物业");
        return sourceMap;
    }


    public void setSourceMap(Map<String, String> sourceMap) {
        this.sourceMap = sourceMap;
    }
    
    
}
