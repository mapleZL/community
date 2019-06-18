package com.phkj.core;

import java.util.HashMap;
import java.util.Map;

public class RelationShipConfig {
    
    private Map<String, String> relationMap;

    public Map<String, String> getRelationMap() {
        relationMap = new HashMap<>();
        relationMap.put("业主", "1");
        relationMap.put("亲属", "3");
        relationMap.put("租客", "6");
        return relationMap;
    }

    public void setRelationMap(Map<String, String> relationMap) {
        this.relationMap = relationMap;
    }
    
    
}
