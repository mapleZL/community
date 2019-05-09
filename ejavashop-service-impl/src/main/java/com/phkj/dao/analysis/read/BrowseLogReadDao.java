package com.phkj.dao.analysis.read;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.phkj.dto.PVDto;
import com.phkj.entity.analysis.BrowseLog;

@Repository
public interface BrowseLogReadDao {

    BrowseLog get(java.lang.Integer id);

    List<PVDto> getProductPV(Map<String, String> queryMap);
}