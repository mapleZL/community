package com.phkj.dao.shop.write.news;

import org.springframework.stereotype.Repository;

import com.phkj.entity.news.NewsPartner;

@Repository
public interface NewsPartnerWriteDao {

    Integer save(NewsPartner newsPartner);

    Integer update(NewsPartner newsPartner);

    Integer del(Integer id);
}