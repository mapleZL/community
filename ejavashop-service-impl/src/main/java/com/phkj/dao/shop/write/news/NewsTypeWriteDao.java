package com.phkj.dao.shop.write.news;

import org.springframework.stereotype.Repository;

import com.phkj.entity.news.NewsType;

@Repository
public interface NewsTypeWriteDao {

    //NewsType get(java.lang.Integer id);

    Integer save(NewsType newsType);

    Integer update(NewsType newsType);

    Integer updateNotNull(NewsType newsType);

    //Integer getCount(@Param("param1") Map<String, String> queryMap);

    /*List<NewsType> page(@Param("param1") Map<String, String> queryMap,
                        @Param("start") Integer start, @Param("size") Integer size);

    List<NewsType> list();
*/
    Integer del(Integer id);
}