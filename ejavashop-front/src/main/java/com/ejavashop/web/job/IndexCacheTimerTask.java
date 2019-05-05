package com.ejavashop.web.job;

import java.util.Date;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpClientUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;

/**
 * 
 * @author wangpeng 
 * 功能：定时器，定时更新内容
 */
public class IndexCacheTimerTask extends TimerTask {

    private static final Log log       = LogFactory.getLog(IndexCacheTimerTask.class);

    private ServletContext   context   = null;
    private static boolean   isRunning = false;

    public IndexCacheTimerTask() {
    }

    public IndexCacheTimerTask(ServletContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        log.info("----------------定时器开始执行,执行时间：" + new Date());
        if (!isRunning) {
            isRunning = true;
            String sendGet = HttpClientUtil.sendGet(DomainUrlUtil.getEJS_URL_RESOURCES() + "/cacheIndex.html");
            context.setAttribute(ConstantsEJS.PC_INDEX_HTML_CACHE, sendGet);
            isRunning = false;
        } else {
            log.info("上次还在执行中……");
        }
        log.info("----------------定时器执行结束-----------------------");
    }

}
