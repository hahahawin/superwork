package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.service.SmhSettingService;

import com.superwork.apcosplatform.service.UIService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @program: code->UIController
 * @description: UI设备控制
 * @author: xjj
 * @create: 2019-12-04 09:51
 **/
@Controller
@RequestMapping("device")
public class UIController {
    public static Logger logger = Logger.getLogger(UIController.class);
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SmhSettingService smhSettingService;
    @Value("${cd_api_url}")
    String cd_api_url;
    @Value("${back_url}")
    String back_url;

    @Autowired
    UIService uIService;


    /**
     * @Description: TODO 控制设备（不使用）
     * @Param: [jsonObject]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2019/12/7 13:39
     */
    @RequestMapping("sbkz")
    @ResponseBody
    @Log("设备控制")
    public JSONObject sbkz(@RequestBody JSONObject jsonObject) {
        String account = jsonObject.getString("account");
        String productId = jsonObject.getString("productId");
        String serial_num = jsonObject.getString("serial_num");
        System.out.println(serial_num);
        String operation = jsonObject.getString("operation");
        String controlParams = jsonObject.getString("controlParams");
        JSONObject jsonObject1 = new JSONObject();
        try {
            uIService.kfsbkz(serial_num, productId, operation, controlParams, account);
            jsonObject1.put("code", 0);
            jsonObject1.put("msg", "控制成功！");
            jsonObject1.put("message", "控制成功！");
        } catch (Exception e) {
            logger.error("设备控制异常", e);
            jsonObject1.put("code", "-1");
            jsonObject1.put("msg", e.getMessage());
            jsonObject1.put("message", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @Description: TODO 控制设备
     * @Param: [jsonObject]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2020/1/14 17:21
     */
    @RequestMapping("deviceControlBySerialNum")
    @ResponseBody
    @Log("设备控制")
    public JSONObject deviceControlBySerialNum(@RequestBody JSONObject jsonObject) {

        String account = jsonObject.getString("account");
        String productId = jsonObject.getString("productId");
        String serial_num = jsonObject.getString("serial_num");
        System.out.println(serial_num);
        String operation = jsonObject.getString("operation");
        String controlParams = jsonObject.getString("controlParams");
        JSONObject jsonObject1 = new JSONObject();
        try {
            JSONObject kfsbkz = uIService.kfsbkz(serial_num, productId, operation, controlParams, account);
            return kfsbkz;
        } catch (Exception e) {
            logger.error("设备控制异常", e);
            jsonObject1.put("code", "-1");
            jsonObject1.put("msg", e.getMessage());
            jsonObject1.put("message", e.getMessage());
        }
        return jsonObject1;
    }


}
