package com.phkj.service.impl.resvervation;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.repaire.StAppletCommentDao;
import com.phkj.dao.shop.read.resvervation.StAppletReservationReadMapper;
import com.phkj.dao.shop.write.resvervation.StAppletReservationWriteMapper;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.repair.StAppletComment;
import com.phkj.entity.resvervation.StAppletReservation;
import com.phkj.service.resvervation.ResvervationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResvervationServiceImpl implements ResvervationService {

    @Autowired
    StAppletReservationReadMapper reservationReadMapper;

    @Autowired
    StAppletReservationWriteMapper reservationWriteMapper;

    @Autowired
    StAppletCommentDao stAppletCommentDao;

    /**
     * @param userId
     * @param villageCode
     * @param sts
     * @param status
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    @Override
    public PageInfo<StAppletReservation> getAllReservation(String userId, String villageCode, String sts,
                                                           String status, Integer pageNum, Integer pageSize, String type) {

        PageInfo<StAppletReservation> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                reservationReadMapper.getAllReservation(userId, villageCode, sts, status, type);
            }
        });
        return pageInfo;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public StAppletReservation getReservation(String id) {

        StAppletReservation stAppletReservation = reservationReadMapper.getReservation(Long.valueOf(id));
        return stAppletReservation;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id, String userId, String userName) {
        StAppletReservation reservation = reservationReadMapper.getReservation(Long.valueOf(id));
        reservation.setModifyUserId(userId);
        reservation.setModifyUserName(userName);
        reservation.setSts("0");
        reservation.setStatus("0");
        reservation.setModifyTime(new Date());
        int i = reservationWriteMapper.updateByPrimaryKey(reservation);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param stAppletReservation
     * @return
     */
    @Override
    public boolean update(StAppletReservation stAppletReservation) {
        stAppletReservation.setStatus("0");
        stAppletReservation.setModifyTime(new Date());
        int i = reservationWriteMapper.updateByPrimaryKey(stAppletReservation);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param stAppletReservation
     * @return
     */
    @Override
    public boolean add(StAppletReservation stAppletReservation) {
        stAppletReservation.setCreateTime(new Date());
        stAppletReservation.setStatus("0");
        stAppletReservation.setSts("1");
        int i = reservationWriteMapper.insert(stAppletReservation);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param comment
     * @return
     */
    @Override
    public boolean addComment(Comment comment) {
        StAppletComment stAppletComment = new StAppletComment();
        stAppletComment.setRId(Long.valueOf(comment.getId()));
        stAppletComment.setSts(1);
        stAppletComment.setCreateUserName(comment.getCreateUserName());
        stAppletComment.setCreateUserId(Long.valueOf(comment.getCreateUserId()));
        stAppletComment.setrType("reser");
        stAppletComment.setParentId(Integer.valueOf(comment.getParentId()));
        stAppletComment.setCreateTime(new Date());
        stAppletComment.setContent(comment.getContent());
        Integer i = stAppletCommentDao.insert(stAppletComment);
        if (i <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @param status
     * @param sts
     * @param type
     * @param page
     * @param rows
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletReservation> getSystemReservation(String status, String sts, String type,
                                                              String page, String rows, String villageCode) {
        int pageNum = Integer.valueOf(page);
        int pageSize = Integer.valueOf(rows);
        PageInfo<StAppletReservation> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {

            }
        });
        return null;
    }
}
