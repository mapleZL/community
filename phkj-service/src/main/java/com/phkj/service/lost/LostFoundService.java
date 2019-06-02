package com.phkj.service.lost;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.lost.StAppletLostFound;
import com.phkj.entity.system.SystemAdmin;

public interface LostFoundService {


    PageInfo<StAppletLostFound> getAllLostFoundList(Integer pageNum, Integer pageSize, String type,
                                                    String villageCode);

    PageInfo<StAppletLostFound> getMeLostFound(Integer pageNum, Integer pageSize, String type, String villageCode,
                                               String userId);

    StAppletLostFound getDetail(String id);

    boolean delete(String id, String userId, String userName);

    boolean addLostFound(StAppletLostFound stAppletLostFound);

    PageInfo<StAppletLostFound> getSystemAllLostFound(Integer pageNum, Integer pageSize, String type, String sts,
                                                      String villageCode);

    boolean systemDelete(String id, SystemAdmin adminUser);

    PageInfo<StAppletLostFound> getSystemLostFoundList(Integer page, Integer rows, String type, String sts,
                                                       String villageCode);

    boolean addSystemLostFound(StAppletLostFound stAppletLostFound, SystemAdmin adminUser);
}
