package com.phkj.dao.shop.write.mindex;

import com.phkj.entity.mindex.MIndexBanner;
import org.springframework.stereotype.Repository;


@Repository
public interface MIndexBannerWriteDao {

    MIndexBanner get(java.lang.Integer id);

    Integer insert(MIndexBanner mIndexBanner);

    Integer update(MIndexBanner mIndexBanner);

    Integer delete(Integer id);
}