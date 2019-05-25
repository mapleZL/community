package com.phkj.service.repair;

import com.phkj.core.ServiceResult;
import com.phkj.entity.repair.StAppletComment;

import java.util.List;

public interface IStAppletCommentService {

    /**
     * 根据id取得st_applet_comment对象
     * @param  stAppletCommentId
     * @return
     */
    ServiceResult<StAppletComment> getStAppletCommentById(Long stAppletCommentId);

    /**
     * 保存st_applet_comment对象
     * @param  stAppletComment
     * @return
     */
    ServiceResult<Integer> saveStAppletComment(StAppletComment stAppletComment);

    /**
    * 更新st_applet_comment对象
    * @param  stAppletComment
    * @return
    */
    ServiceResult<Integer> updateStAppletComment(StAppletComment stAppletComment);

    /**
     * 查询评论记录列表
     *
     * @param rId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServiceResult<List<StAppletComment>> getStAppletRepairList(Long rId, String rType, int pageNum,
                                                               int pageSize);

    /**
     * 获取某条活动的评论数量
     * @param id
     * @return
     */
    ServiceResult<Long> getCountByRId(Long id, String rType);

    /**
     * 获取评论数量
     * @param memberId
     * @param id
     * @return
     */
    Integer getCommentCount(Integer memberId, Long id);
}