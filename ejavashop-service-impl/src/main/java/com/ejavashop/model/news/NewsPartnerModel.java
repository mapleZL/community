package com.ejavashop.model.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.news.NewsPartnerReadDao;
import com.ejavashop.dao.shop.write.news.NewsPartnerWriteDao;
import com.ejavashop.entity.news.NewsPartner;

@Component(value = "newsPartnerModel")
public class NewsPartnerModel {

    @Resource
    private NewsPartnerWriteDao newsPartnerWriteDao;

    @Resource
    private NewsPartnerReadDao newsPartnerReadDao;

    public NewsPartner get(Integer newsPartnerId) {
        return newsPartnerReadDao.get(newsPartnerId);
    }

    public Integer save(NewsPartner newsPartner) {
        return newsPartnerWriteDao.save(newsPartner);
    }

    public Integer update(NewsPartner newsPartner) {
        return newsPartnerWriteDao.update(newsPartner);
    }

    public int getCount(Map<String, String> queryMap) {
        return newsPartnerReadDao.getCount(queryMap);
    }

    public List<NewsPartner> page(Map<String, String> queryMap, Integer start, Integer size) {
        return newsPartnerReadDao.page(queryMap, start, size);
    }

    public List<NewsPartner> list() {
        return newsPartnerReadDao.list();
    }

    public int del(Integer id) {
        return newsPartnerWriteDao.del(id);
    }
}
