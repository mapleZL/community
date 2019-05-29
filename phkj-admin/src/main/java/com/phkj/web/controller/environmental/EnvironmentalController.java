package com.phkj.web.controller.environmental;

import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.StAppletEnviron;
import com.phkj.service.environmental.EnvironmentalService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 *
 */
@Controller
@RequestMapping("/admin/environ")
public class EnvironmentalController {

    private final static Logger LOGGER = LogManager.getLogger(EnvironmentalController.class);

    @Autowired
    private EnvironmentalService environmentalService;

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResponseUtil add(@RequestBody StAppletEnviron stAppletEnviron) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (environmentalService.add(stAppletEnviron)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败" + e);
        }
        return responseUtil;
    }


    @ResponseBody
    @RequestMapping("/")
    public ResponseUtil getAll(String id, Integer pageNum, Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            Map<String, Object> returnMap = environmentalService.getAll(id,pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败" + e);
        }
        return responseUtil;
    }
}
