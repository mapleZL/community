package com.phkj.dao.shop.read.mindex;

import java.util.List;
import java.util.Map;

import com.phkj.entity.mindex.MIndexBanner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MIndexBannerReadDao {

    MIndexBanner get(Integer id);

    Integer getMIndexBannersCount(@Param("queryMap") Map<String, String> queryMap);

    List<MIndexBanner> getMIndexBanners(@Param("queryMap") Map<String, String> queryMap,
                                        @Param("start") Integer start, @Param("size") Integer size);


    /**
     * 为展示页面取数据，数据order by `order_no`
     * 
     * @param queryMap
     * @return
     */
    List<MIndexBanner> getMIndexBannersForView(@Param("queryMap") Map<String, String> queryMap);
}