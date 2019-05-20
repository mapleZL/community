package com.phkj.vo.member;

import java.io.Serializable;
import java.util.List;

import com.phkj.entity.repair.StAppletComment;

public class NocticeCommnetVO implements Serializable{

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private StAppletComment mianComment;
    
    private List<StAppletComment> childrenComments;

    public StAppletComment getMianComment() {
        return mianComment;
    }

    public void setMianComment(StAppletComment mianComment) {
        this.mianComment = mianComment;
    }

    public List<StAppletComment> getChildrenComments() {
        return childrenComments;
    }

    public void setChildrenComments(List<StAppletComment> childrenComments) {
        this.childrenComments = childrenComments;
    }
    
    
}
