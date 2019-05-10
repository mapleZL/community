package com.phkj.web.controller.repair;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.repair.StAppletComment;
import com.phkj.entity.repair.StAppletCommentParam;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.service.repair.IStAppletCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ：zl
 * @date ：Created in 2019/5/10 15:50
 * @description：评论controller
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/comment")
public class StAppletCommentController {

    @Autowired
    IStAppletCommentService iStAppletCommentService;

    /**
     * create by: zl
     * description: 添加评论
     * create time:
     *
     * @return
     * @Param: stAppletComment
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil save(@RequestBody StAppletCommentParam stAppletCommentParam) {
        ResponseUtil flag = checkParam(stAppletCommentParam);
        if (flag != null) {
            return flag;
        }
        StAppletComment stAppletComment = new StAppletComment();
        stAppletComment.setRId(stAppletCommentParam.getrId());
        stAppletComment.setCreateUserId(stAppletCommentParam.getCreateUserId());
        stAppletComment.setCreateUserName(stAppletCommentParam.getCreateUserName());
        stAppletComment.setContent(stAppletCommentParam.getContent());
        ServiceResult<Integer> result = iStAppletCommentService.saveStAppletComment(stAppletComment);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    /**
     * create by: zl
     * description: 查看评论列表记录
     * create time:
     *
     * @return
     * @Param: rid
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil list(Long rId, int pageNum, int pageSize) {
        if (rId == null) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "rId is blank", true, null);
        }
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<List<StAppletComment>> result = iStAppletCommentService.getStAppletRepairList(rId, pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }


    private ResponseUtil checkParam(StAppletCommentParam stAppletComment) {
        if (stAppletComment == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        if (stAppletComment.getrId() == null || StringUtils.isBlank(stAppletComment.getContent())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "rId or content is blank", true, null);
        }
        if (stAppletComment.getCreateUserId() == null || StringUtils.isBlank(stAppletComment.getCreateUserName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "createUserId or createUserName is blank", true, null);
        }
        return null;
    }
}
