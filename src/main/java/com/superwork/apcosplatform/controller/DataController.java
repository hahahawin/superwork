package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.DataService;

import com.superwork.apcosplatform.utils.ComonUtils;
import freemarker.template.Configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jianjian Xu
 * @create: 2019/11/6 12:36
 * @description:
 */
@Controller
@RequestMapping("data")
public class DataController {

    public static Logger logger = Logger.getLogger(DataController.class);

    @Autowired
    DataService dataService;

    @Autowired
    Configuration configuration;
    @Autowired
    RedisUtil redisUtil;



    /**
     * @Description: TODO  欢迎页获取近一个星期注册人数
     * @Param: []
     * @return: SingleResponse<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:52
     */
    @RequestMapping("getRegisterOnWeek.json")
    @ResponseBody
    public SingleResponse<Map<String, Object>> getRegisterOnWeek() {
        SingleResponse<Map<String, Object>> response = new SingleResponse<>();
        try {
            Object obj = redisUtil.get("data:Register");
            if(obj == null){
                Map<String, Object> registerOnWeek = dataService.getRegisterOnWeek();
                JSONObject jsonObject = new JSONObject(registerOnWeek);
                redisUtil.set("data:Register",jsonObject.toJSONString());
                response.setData(registerOnWeek);
            }else{
                Map map = JSONObject.parseObject(obj.toString(), Map.class);
                response.setData(map);
            }
        } catch (Exception e) {
            logger.error("查询最近周注册数异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 查询最近一周访问数
     * @Param: []
     * @return: SingleResponse<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:53
     */
    @RequestMapping("getLoginOnWeek.json")
    @ResponseBody
    public SingleResponse<Map<String, Object>> getLoginOnWeek() {

        SingleResponse<Map<String, Object>> response = new SingleResponse<>();
        try {
            Object obj = redisUtil.get("data:Login");
            if(obj == null){
                Map<String, Object> loginOnWeek = dataService.getLoginOnWeek();
                JSONObject jsonObject = new JSONObject(loginOnWeek);
                redisUtil.set("data:Login",jsonObject.toJSONString());
                response.setData(loginOnWeek);
            }else{
                Map map = JSONObject.parseObject(obj.toString(), Map.class);
                response.setData(map);
            }
        } catch (Exception e) {
            logger.error("查询最近一周访问数异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 设备在线情况
     * @Param: []
     * @return: SingleResponse<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:53
     */
    @RequestMapping("getProductStatus.json")
    @ResponseBody
    public SingleResponse<Map<String, Object>> getProductStatus() {

        SingleResponse<Map<String, Object>> response = new SingleResponse<>();
        try {
            Map<String, Object> productStatus = dataService.getProductStatus();
            response.setData(productStatus);
        } catch (Exception e) {
            logger.error("设备在线情况异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

//    public









}
