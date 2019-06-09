package com.phkj.service.resvervation;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.resvervation.StAppletReservation;

public interface ResvervationService {


    PageInfo<StAppletReservation> getAllReservation(String userId, String villageCode, String sts, String status,
                                                    Integer pageNum, Integer pageSize, String type);

    StAppletReservation getReservation(String id);

    boolean delete(String id, String userId, String userName);

    boolean update(StAppletReservation stAppletReservation);

    boolean add(StAppletReservation stAppletReservation);

    boolean addComment(Comment comment);

    PageInfo<StAppletReservation> getSystemReservation(String status, String sts, String type, String page,
                                                       String rows, String villageCode);
}
