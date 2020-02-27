package com.superwork.apcosplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.MD5;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.AsyncTask;
import com.superwork.apcosplatform.service.SmhSettingService;
import com.superwork.apcosplatform.service.SysUserServer;


import com.superwork.apcosplatform.service.ThirdInterfaceService;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.HTTPclient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Jianjian Xu
 * @create: 2020/2/12 17:09
 * @description:
 */
@Controller
@RequestMapping("thirdInterface")
public class ThirdInterface {

    public static Logger logger = Logger.getLogger(ThirdInterface.class);


    @Autowired
    SysUserServer sysUserServer;

    @Autowired
    SmhSettingService smhSettingService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ThirdInterfaceService thirdInterfaceService;

    @Autowired
    AsyncTask asyncTask;

    @Value("${dgj_api_url}")
    private String dgj_url;

    /**
     * @param * @param queryRequest
     * @param
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 登陆
     * @author xjj
     * @date 2019/10/22
     */
    @Log("登陆")
    @RequestMapping("login.json")
    @ResponseBody
    public JSONObject login(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            String userAccount = jsonObject.getString("userAccount");
            String userPassword = jsonObject.getString("userPassword");
            String backUrl = jsonObject.getString("backUrl");
            if (StringUtils.isEmpty(userAccount) || StringUtils.isEmpty(userPassword)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数错误");
                return jsonObject1;
            }

            PSysUser pSysUser = new PSysUser();
            pSysUser.setUserAccount(userAccount);
            MD5 md5 = new MD5();
            pSysUser.setUserPassword(md5.getMD5ofStr(userPassword));
            ResultDO<Map<String, Object>> login = sysUserServer.login(pSysUser);
            if (login.isSuccess()) {
                PSysUser user = (PSysUser) login.getData().get("user");
                PD3orgZc pd3orgZc = (PD3orgZc) login.getData().get("bccaService");
                HashMap<String, Object> map = new HashMap<>();
                map.put("user", user);
                map.put("bccaService", pd3orgZc);
                thirdInterfaceService.addBackUrl(backUrl,user.getUserId());
                map.put("backUrl", backUrl);
                String token = UUID.randomUUID().toString();
                token = token.replaceAll("-", "");
                redisUtil.set(token, map, 60 * 60 * 24 * 30);
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
                jsonObject1.put("data", token);
            } else {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", login.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("登陆异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;

    }

    /**
     * @param * @param queryRequest
     * @param
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 注册回调地址
     * @author xjj
     * @date 2019/10/22
     */
    @Log("登陆")
    @RequestMapping("registerBackUrl.json")
    @ResponseBody
    public JSONObject registerBackUrl(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            String token = jsonObject.getString("token");
            String backUrl = jsonObject.getString("backUrl");
            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(backUrl)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数错误");
                return jsonObject1;
            }

            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                Map map = (Map) redisUtil.get(token);
                PSysUser user = (PSysUser)map.get("user");
                thirdInterfaceService.addBackUrl(backUrl,user.getUserId());
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
            }
        } catch (Exception e) {
            logger.error("注册回调地址异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;

    }



    /**
     * @param * @param queryRequest
     * @param
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 获取用户信息
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("getUserInfo.json")
    @ResponseBody
    public JSONObject getUserInfo(@RequestParam String token) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            if (StringUtils.isEmpty(token)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                Map map = (Map) redisUtil.get(token);
                map.remove("backUrl");
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
                jsonObject1.put("data", map);

            }
        } catch (Exception e) {
            logger.error("获取用户信息异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @param * @param queryRequest
     * @param
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 订阅BCCA账号
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("subscribeAccount.json")
    @ResponseBody
    public JSONObject subscribeAccount(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            String bccaAccount = jsonObject.getString("bccaAccount");
            String token = jsonObject.getString("token");

            if (StringUtils.isEmpty(bccaAccount) || StringUtils.isEmpty(token)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                thirdInterfaceService.dyAccount(token,bccaAccount);
                Map map = (Map) redisUtil.get(token);
                asyncTask.syncSbxx(map,bccaAccount);
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
            }
        } catch (Exception e) {
            logger.error("订阅BCCA账号异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @param * @param queryRequest
     * @param
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 设备绑定
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("bindProduct.json")
    @ResponseBody
    public JSONObject bindProduct(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            String bccaAccount = jsonObject.getString("bccaAccount");
            String token = jsonObject.getString("token");
            String genuine_code = jsonObject.getString("genuine_code");
            String serial_num = jsonObject.getString("serial_num");
            if (StringUtils.isEmpty(bccaAccount) || StringUtils.isEmpty(token) || StringUtils.isEmpty(genuine_code) || StringUtils.isEmpty(serial_num)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {

                Map map = (Map) redisUtil.get(token);
                PD3orgZc bccaService = (PD3orgZc) map.get("bccaService");
                if (bccaService == null || StringUtils.isEmpty(bccaService.getServiceId())) {
                    jsonObject1.put("code", 102);
                    jsonObject1.put("msg", "服务信息未完善！");
                    return jsonObject1;
                }

                String appid = bccaService.getServiceId();
                String appkey = bccaService.getServiceKey();
                String token1 = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);//获取TOKEN
                String method = "/thirdInterface/bind";
                JSONObject obj = new JSONObject();
                obj.put("token", token1);
                obj.put("service_id", appid);
                obj.put("account", bccaAccount);
                obj.put("serial_num", serial_num);
                obj.put("genuine_code", genuine_code);
                String s = HTTPclient.httpRequestPostMethod(dgj_url + method, obj);
                System.out.println("返回结果：" + s);
                JSONObject jsonObject2 = JSONObject.parseObject(s);
                if (jsonObject2.get("code").toString().equals("200")) {
                    jsonObject1.put("code", 200);
                    jsonObject1.put("msg", "操作成功！");
                }else{
                    jsonObject1.put("code", 400);
                    jsonObject1.put("msg", "绑定失败：" + jsonObject2.get("msg").toString());
                }
            }

        } catch (Exception e) {
            logger.error("订阅BCCA账号异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @param * @param queryRequest
     * @param
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 设备解绑
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("relieve.json")
    @ResponseBody
    public JSONObject relieve(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            String bccaAccount = jsonObject.getString("bccaAccount");
            String token = jsonObject.getString("token");
            String deviceMac = jsonObject.getString("deviceMac");

            if (StringUtils.isEmpty(bccaAccount) || StringUtils.isEmpty(token) || StringUtils.isEmpty(deviceMac)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                Map map = (Map) redisUtil.get(token);
                PD3orgZc bccaService = (PD3orgZc) map.get("bccaService");
                if (bccaService == null || StringUtils.isEmpty(bccaService.getServiceId())) {
                    jsonObject1.put("code", 102);
                    jsonObject1.put("msg", "服务信息未完善！");
                    return jsonObject1;
                }
                String appid = bccaService.getServiceId();
                String appkey = bccaService.getServiceKey();
                String token1 = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);
                String method = "/thirdInterface/unbind";
                JSONObject obj = new JSONObject();
                obj.put("token", token1);
                obj.put("service_id", appid);
                obj.put("account", bccaAccount);
                obj.put("mac", deviceMac);
                String result = HTTPclient.httpRequestPostMethod(dgj_url + method, obj);
                JSONObject jsonObject2 = JSONObject.parseObject(result);
                if(jsonObject2.get("code").toString().equals("200")){
                    jsonObject1.put("code", 200);
                    jsonObject1.put("msg", "操作成功！");
                }else{
                    jsonObject1.put("code", 400);
                    jsonObject1.put("msg", "解绑失败：" + jsonObject2.get("msg").toString());
                }
            }
        } catch (Exception e) {
            logger.error("设备解绑异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }


    /**
     * @Description //TODO  查询我的设备
     * @author xjj
     * @date 2020/2/14
     * @param  * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping("listProduct.json")
    @ResponseBody
    public JSONObject listProduct(@RequestBody JSONObject jsonObject) {

        JSONObject jsonObject1 = new JSONObject();
        try {
            String token = jsonObject.getString("token");
            if ( StringUtils.isEmpty(token)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                Map map = (Map) redisUtil.get(token);
                PSysUser user = (PSysUser) map.get("user");
                List<PSbgl> pSbgls = thirdInterfaceService.listProduct(user.getUserId());
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
                jsonObject1.put("data",pSbgls);
            }
        } catch (Exception e) {
            logger.error("查询我的设备异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @Description //TODO  查询我的模式
     * @author xjj
     * @date 2020/2/14
     * @param  * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping("listPattern.json")
    @ResponseBody
    public JSONObject listPattern(@RequestBody JSONObject jsonObject) {

        JSONObject jsonObject1 = new JSONObject();
        try {
            String token = jsonObject.getString("token");
            if ( StringUtils.isEmpty(token)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                Map map = (Map) redisUtil.get(token);
                PSysUser user = (PSysUser) map.get("user");
                List<PSbmogl> pSbmogls = thirdInterfaceService.listPattern(user.getUserId());
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
                jsonObject1.put("data",pSbmogls);
            }
        } catch (Exception e) {
            logger.error(" 查询我的模式异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @Description //TODO  解析服务
     * @author xjj
     * @date 2020/2/14
     * @param  * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping("analysis.json")
    @ResponseBody
    public JSONObject analysis(@RequestBody JSONObject jsonObject) {

        JSONObject jsonObject1 = new JSONObject();
        try {
            String token = jsonObject.getString("token");
            JSONObject data = jsonObject.getJSONObject("data");
            if ( StringUtils.isEmpty(token)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {

                String analysis = thirdInterfaceService.analysis(data);
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
                jsonObject1.put("data",analysis);
            }
        } catch (Exception e) {
            logger.error(" 解析服务异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }


    /**
     * @Description //TODO  获取我的用户列表
     * @author xjj
     * @date 2020/2/14
     * @param  * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping("listMyUser.json")
    @ResponseBody
    public JSONObject listMyUser(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            String token = jsonObject.getString("token");
            if ( StringUtils.isEmpty(token)) {
                jsonObject1.put("code", 101);
                jsonObject1.put("msg", "参数有误");
                return jsonObject1;
            }
            Object o = redisUtil.get(token);
            if (o == null) {
                jsonObject1.put("code", 100);
                jsonObject1.put("msg", "token失效");
                return jsonObject1;
            } else {
                Map map = (Map) redisUtil.get(token);
                List<Map> maps = thirdInterfaceService.listMyUser(map);
                jsonObject1.put("code", 200);
                jsonObject1.put("msg", "操作成功！");
                jsonObject1.put("data",maps);
            }
        } catch (Exception e) {
            logger.error(" 获取我的用户列表异常", e);
            jsonObject1.put("code", 500);
            jsonObject1.put("msg", e.getMessage());
        }
        return jsonObject1;
    }

    /**
     * @Description //TODO  查询组织结构
     * @author xjj
     * @date 2020/2/17
     * @param  * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping("getOrg.json")
    @ResponseBody
   public JSONObject getOrg(@RequestBody JSONObject jsonObject){

       JSONObject jsonObject1 = new JSONObject();
       try {
           String token = jsonObject.getString("token");
           if ( StringUtils.isEmpty(token)) {
               jsonObject1.put("code", 101);
               jsonObject1.put("msg", "参数有误");
               return jsonObject1;
           }
           Object o = redisUtil.get(token);
           if (o == null) {
               jsonObject1.put("code", 100);
               jsonObject1.put("msg", "token失效");
               return jsonObject1;
           } else {
               Map map = (Map) redisUtil.get(token);
               List<POrganize> org = thirdInterfaceService.getOrg(map);
               jsonObject1.put("code", 200);
               jsonObject1.put("msg", "操作成功！");
               jsonObject1.put("data",org);
           }
       } catch (Exception e) {
           logger.error("查询组织结构异常", e);
           jsonObject1.put("code", 500);
           jsonObject1.put("msg", e.getMessage());
       }
       return jsonObject1;
   }


    /**
     * @Description //TODO  set设置cookie
     * @author xjj
     * @date 2020/2/17
     * @param  * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping("setcookie.json")
    public void setcookie(@RequestParam String from, HttpServletResponse response){
        try {
            PSysUser user = ComonUtils.getUser();
            PD3orgZc pd3orgZc = ComonUtils.getBccaServiceInfo();
            String token = UUID.randomUUID().toString();
            token = token.replaceAll("-", "");
            HashMap<String, Object> map = new HashMap<>();
            map.put("user", user);
            map.put("bccaService", pd3orgZc);
            redisUtil.set(token, map, 60 * 60 * 24 * 30);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", response.getHeader("Origin"));
            response.addHeader("Set-Cookie", "public_token="+token+";Path=/;HttpOnly");
            response.sendRedirect(from+"?public_token="+token);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }







}
