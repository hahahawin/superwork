package com.superwork.apcosplatform.controller;

import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.service.APPVersionService;
import com.superwork.apcosplatform.service.SysUserServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 驾驶舱接口
 */
@Controller
@RequestMapping("cockpit")
public class CockpitController {

    public static Logger logger = Logger.getLogger(CockpitController.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysUserServer sysUserServer;

    @Autowired
    APPVersionService appVersionService;

}
