package com.phkj.echarts.component;

/**
 * 物业维修状态
 *                       
 * @Filename: RepairStatus.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class RepairStatus {

    /** 提交待审核状态 */
    public static final int STATE_1 = 1;

    /** 物业已下发待维修确认 */
    public static final int STATE_2 = 2;

    /**
     * 审核不通过
     */
    public static final int STATE_3 = 3;
    
    /**
     * 维修人员已确认
     */
    public static final int STATE_4 = 4;
    
    /**
     * 维修完成
     */
    public static final int STATE_5 = 5;
    
    /**
     * 已评论
     */
    public static final int STATE_6 = 6;
}
