package com.phkj.model.repair;


import com.phkj.core.StringUtil;
import com.phkj.dao.index.read.repair.StAppletCommentDao;
import com.phkj.entity.repair.StAppletComment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StAppletCommentModel {
    
    @Resource
    private StAppletCommentDao stAppletCommentDao;
    
    /**
     * 根据id取得st_applet_comment对象
     * @param  stAppletCommentId
     * @return
     */
    public StAppletComment getStAppletCommentById(Long stAppletCommentId) {
    	return stAppletCommentDao.get(stAppletCommentId);
    }
    
    /**
     * 保存st_applet_comment对象
     * @param  stAppletComment
     * @return
     */
     public Integer saveStAppletComment(StAppletComment stAppletComment) {
     	this.dbConstrains(stAppletComment);
     	return stAppletCommentDao.insert(stAppletComment);
     }
     
     /**
     * 更新st_applet_comment对象
     * @param  stAppletComment
     * @return
     */
     public Integer updateStAppletComment(StAppletComment stAppletComment) {
     	this.dbConstrains(stAppletComment);
     	return stAppletCommentDao.update(stAppletComment);
     }
     
     private void dbConstrains(StAppletComment stAppletComment) {
		stAppletComment.setCreateUserName(StringUtil.dbSafeString(stAppletComment.getCreateUserName(), true, 50));
		stAppletComment.setContent(StringUtil.dbSafeString(stAppletComment.getContent(), true, 255));
     }


    /**
     * create by: zl
     * description: 查询报修记录列表
     * create time:
     *
     * @return
     * @Param: createUserId
     * @Param: pageNum
     * @Param: pageSize
     */
    public List<StAppletComment> getStAppletCommentList(Long rId, int pageNum, int pageSize) {
        return stAppletCommentDao.getStAppletCommentList(rId, pageNum, pageSize);
    }
}