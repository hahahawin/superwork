package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.service.SbglService;
import com.superwork.apcosplatform.service.SmhSettingService;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.HTTPclient;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * @author Jianjian Xu
 * @create: 2019/10/24 15:37
 * @description:
 */
@Controller
@RequestMapping("account_3")
public class PSmhSettingController {

    public static Logger logger = Logger.getLogger(PSmhSettingController.class);

    @Autowired
    SmhSettingService smhSettingService;

    @Autowired
    SbglService sbglService;

    @Value("${dgj_api_url}")
    private String dgj_url;

    @Autowired
    RedisUtil redisUtil;


    /**
     * @param * @param queryRequest
     * @return PageResponse<PSmhSetting>
     * @Description //TODO 分页查询申请的账号（大管家）
     * @author xjj
     * @date 2019/10/24
     */
    @RequestMapping("listAccount_3.json")
    @ResponseBody
    public PageResponse<PSmhSetting> listAccount_3(@RequestBody QueryRequest<PSmhSetting> queryRequest) {
        PageResponse<PSmhSetting> response = new PageResponse<>();
        PSmhSetting data = queryRequest.getData() == null ? new PSmhSetting() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setCreatorId(user.getUserId());
            PageInfo<PSmhSetting> pSmhSettingPageInfo = smhSettingService.listAccount_3(data, page, limit);
            response.setData(pSmhSettingPageInfo.getList());
            response.setTotal(pSmhSettingPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询申请的账号（大管家）异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 获取区域编码
     * @Param: [area_code]
     * @return: com.superwork.apcosplatform.result.SingleResponse<com.alibaba.fastjson.JSONArray>
     * @Author: xujianjian
     * @Date: 2020/1/14 15:18
     */
    @RequestMapping("getAreas.json")
    @ResponseBody
    public  SingleResponse<JSONArray> getAreas(@RequestParam String area_code){

        SingleResponse<JSONArray> response = new SingleResponse<>();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if(bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())){
                throw new Exception("请先完善服务信息！");
            }
            String appid = bccaServiceInfo.getServiceId();
            String appkey = bccaServiceInfo.getServiceKey();
            String token = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);//获取TOKEN
            JSONObject obj = new JSONObject();
            obj.put("token",token);
            obj.put("service_id",appid);
            obj.put("area_code",area_code);
            String method = "/thirdInterface/getAreas";
            String result = HTTPclient.httpRequestPostMethod(dgj_url + method, obj);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject.get("code").toString().equals("200")){
                JSONArray data = jsonObject.getJSONArray("data");
                response.setData(data);
            }else{
                response.setStatus(100);
                response.setMsg(jsonObject.get("msg").toString());
            }
        } catch (Exception e) {
            logger.error("获取区域编码异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }



    /**
     * @Description: TODO 申请bcca账号(大管家新版接口)
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/18 10:27
     */
    @RequestMapping("add.json")
    @ResponseBody
    @Log("申请bcca账号")
    public SingleResponse<String> newadd(@RequestBody QueryRequest<PSmhSetting> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        PSmhSetting data = queryRequest.getData();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String appid = bccaServiceInfo.getServiceId();
            String appkey = bccaServiceInfo.getServiceKey();
            String token = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);//获取TOKEN
            JSONObject obj = new JSONObject();
            obj.put("token", token);
            obj.put("service_id", appid);
            obj.put("password", "111111");
            obj.put("name", data.getAttr4());
            obj.put("phone", data.getAttr7());
            obj.put("gender", data.getAttr5());
            obj.put("email", data.getAttr8());
            String area_code1 = "";
            if(StringUtils.isEmpty(data.getAttr9())){
                area_code1 = "500000";
            }else{
                area_code1 = data.getAttr9();
            }
            obj.put("area_code1", area_code1);//城市代码
            String method = "/thirdInterface/addAccount";
            String s = HTTPclient.httpRequestPostMethod(dgj_url + method, obj);
            JSONObject object3 = JSONObject.parseObject(s);
            if (object3.get("code").toString().equals("200")) {
                data.setSmarthomeAccount(object3.get("data").toString());
                data.setSmarthomePwd("111111");
                data.setSfkt("2");//已启用
                data.setAttr2("2");//未订阅
                data.setCreatorId(user.getUserId());
                data.setCreatedTime(new Date());
                smhSettingService.insert(data);
            } else {
                response.setStatus(100);
                response.setMsg(object3.get("msg").toString());
            }
        } catch (Exception e) {
            logger.error("申请BCCA账号账号异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }


    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 三方账号禁用和启用
     * @author xjj
     * @date 2019/10/24
     */
    @RequestMapping("editAccountInfo.json")
    @ResponseBody
    public SingleResponse<String> editAccountInfo(@RequestBody QueryRequest<PSmhSetting> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        PSmhSetting data = queryRequest.getData();
        try {
            smhSettingService.editAccountInfo(data);
        } catch (Exception e) {
            logger.error("三方账号禁用和启用异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 账户订阅（存在订阅记录）
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:58
     */
    @RequestMapping("sbdy.json")
    @ResponseBody
    public SingleResponse<String> sbdy(@RequestBody QueryRequest<PSmhSetting> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        PSmhSetting data = queryRequest.getData();
        try {
            PSmhSetting pSmhSetting = new PSmhSetting();
            pSmhSetting.setSettingId(data.getSettingId());
            smhSettingService.sbdy(data);
            response.setMsg("已发送订阅请求！");
//            pSmhSetting.setAttr2("3");//审核中
//            smhSettingService.editAccountInfo(pSmhSetting);
        } catch (Exception e) {
            logger.error("账户订阅异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 取消订阅
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:58
     */
    @RequestMapping("delSbdy.json")
    @ResponseBody
    public SingleResponse<String> delSbdy(@RequestBody QueryRequest<PSmhSetting> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        PSmhSetting data = queryRequest.getData();
        try {
            smhSettingService.delSbdy(data);
        } catch (Exception e) {
            logger.error("账户订阅异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 手动添加订阅账户
     * @author xjj
     * @date 2019/10/25
     */
    @RequestMapping("dyAccount.json")
    @ResponseBody
    public SingleResponse<String> dyAccount(@RequestBody QueryRequest<String> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            smhSettingService.dyAccount(data);
        } catch (Exception e) {
            logger.error("账户订阅异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 删除第三方账号
     * @author xjj
     * @date 2019/10/25
     */
    @RequestMapping("delAccount.json")
    @ResponseBody
    public SingleResponse<String> delAccount(@RequestBody QueryRequest<PSmhSetting> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        PSmhSetting data = queryRequest.getData();
        try {
            smhSettingService.delAccount(data);
        } catch (Exception e) {
            logger.error("删除第三方账号异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 同步智能设备
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:59
     */
    @RequestMapping("syncSbxx.json")
    @ResponseBody
    public SingleResponse<String> syncSbxx(@RequestBody QueryRequest<PSmhSetting> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        PSmhSetting smhSetting = queryRequest.getData();
        try {
            String msg = smhSettingService.syncSbxx(smhSetting);
            response.setMsg(msg);
        } catch (Exception e) {
            logger.error("同步智能设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }



    /**
     * @Description: TODO 设备绑定（大管家新版接口）
     * @Param: [request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/18 10:44
     */
    @RequestMapping("sbbinding.json")
    @ResponseBody
    public SingleResponse<String> newsbbinding(HttpServletRequest request) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善BCCA服务信息！");
            }
            String appid = bccaServiceInfo.getServiceId();
            String appkey = bccaServiceInfo.getServiceKey();
            String account = request.getParameter("account");//账号
            String genuine_code = request.getParameter("genuine_code");//正品码
            String serial_num = request.getParameter("serial_num");//序列号
            String token = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);//获取TOKEN
            String method = "/thirdInterface/bind";
            JSONObject obj = new JSONObject();
            obj.put("token", token);
            obj.put("service_id", appid);
            obj.put("account", account);
            obj.put("serial_num", serial_num);
            obj.put("genuine_code", genuine_code);

            String s = HTTPclient.httpRequestPostMethod(dgj_url + method, obj);
            System.out.println("返回结果：" + s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            if (!jsonObject.get("code").toString().equals("200")) {
                response.setStatus(100);
                response.setMsg("绑定失败：" + jsonObject.get("msg").toString());
            }
        } catch (Exception e) {
            logger.error("绑定设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }



    /**
     * @Description: TODO 设备解绑（大管家新版接口）
     * @Param: [request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/18 11:01
     */
    @PostMapping("sbJiebing")
    @ResponseBody
    public SingleResponse<String> newsbJiebing(HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String appid = bccaServiceInfo.getServiceId();
            String appkey = bccaServiceInfo.getServiceKey();
            String account = request.getParameter("account");
            String mac = request.getParameter("deviceMac");
            String id = request.getParameter("id");
            String token = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);
            String method = "/thirdInterface/unbind";
            JSONObject obj = new JSONObject();
            obj.put("token", token);
            obj.put("service_id", appid);
            obj.put("account", account);
            obj.put("mac", mac);
            String result = HTTPclient.httpRequestPostMethod(dgj_url + method, obj);
            System.out.println("返回的结果："+result);
            JSONObject object = JSONObject.parseObject(result);
            if(!object.get("code").toString().equals("200")){
                response.setStatus(100);
                response.setMsg("解绑失败："+object.get("msg").toString());
            }
        } catch (Exception e) {
            logger.error("设备解绑异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 验证身份证是否唯一
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:00
     */
    @RequestMapping("checkIDCard.json")
    @ResponseBody
    public SingleResponse<String> checkIDCard(@RequestBody QueryRequest<String> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            ResultDO<String> stringResultDO = smhSettingService.checkIDCard(data);
            if (!stringResultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("验证身份证号是否唯一异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }


//    /**
//     * @param *       @param queryRequest
//     * @param request
//     * @return SingleResponse<java.lang.String>
//     * @Description //TODO 保存服务信息
//     * @author xjj
//     * @date 2019/11/6
//     */
//    @RequestMapping("saveServiceInfo.json")
//    @ResponseBody
//    public SingleResponse<String> saveServiceInfo(@RequestBody QueryRequest<PSmhDevice> queryRequest, HttpServletRequest request) {
//        SingleResponse<String> response = new SingleResponse<>();
//        PSmhDevice data = queryRequest.getData();
//        HttpSession session = request.getSession();
//        PSysUser user = (PSysUser) session.getAttribute("user");
//        data.setCreatorId(user.getUserId());
//        data.setCreatedTime(new Date());
//
//        try {
//            smhSettingService.saveServiceInfo(data);
//            redisUtil.del(data.getServiceId());//删除token
//        } catch (Exception e) {
//            logger.error("保存服务信息异常", e);
//            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
//            response.setMsg(e.getMessage());
//        }
//        return response;
//
//    }


//    /**
//     * @param *       @param queryRequest
//     * @param request
//     * @return SingleResponse<java.lang.String>
//     * @Description //TODO 获取BCCA服务接入信息
//     * @author xjj
//     * @date 2019/11/6
//     */
//    @RequestMapping("getServiceInfo.json")
//    @ResponseBody
//    public SingleResponse<PSmhDevice> getServiceInfo(HttpServletRequest request) {
//        SingleResponse<PSmhDevice> response = new SingleResponse<>();
//        HttpSession session = request.getSession();
//        try {
//            PSmhDevice pSmhDevice = (PSmhDevice) session.getAttribute("bccaService");
//            response.setData(pSmhDevice);
//        } catch (Exception e) {
//            logger.error("获取BCCA服务接入信息异常", e);
//            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
//            response.setMsg(e.getMessage());
//        }
//        return response;
//
//    }


    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 从大管家同步执行计划，属性，指令，参数
     * @author xjj
     * @date 2019/11/6
     */
    @RequestMapping("syncPlan.json")
    @ResponseBody
    public SingleResponse<String> syncPlan(@RequestBody QueryRequest<String> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            smhSettingService.syncPlan(data);
        } catch (Exception e) {
            logger.error("从大管家同步执行计划，属性，指令，参数异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
