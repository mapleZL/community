package com.phkj.dao.shop.read.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.news.NewsPartner;

@Repository
public interface NewsPartnerReadDao {

    NewsPartner get(java.lang.Integer id);
    
    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<NewsPartner> page(@Param("param1") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    List<NewsPartner> list();
}