package com.superwork.apcosplatform.app.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.app.common.ApiJsonObject;
import com.superwork.apcosplatform.app.common.ApiJsonProperty;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.app.service.AppAcountService;
import com.superwork.apcosplatform.controller.IndexController;

import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.SmhSettingService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: code->AccountController
 * @description: 关于账号的操作
 * @author: xjj
 * @create: 2019-11-29 13:37
 **/
@Controller
@RequestMapping("appAccount")
public class AppAccountController {

    public static Logger logger = Logger.getLogger(AppAccountController.class);

    @Autowired
    AppAcountService appAcountService;
    @Autowired
    SmhSettingService smhSettingService;
    @Autowired
    RedisUtil redisUtil;


    /**
     * @Description: TODO 查询服务信息
     * @Param: []
     * @return: SingleResponse<java.util.Map < java.lang.String , java.lang.String>>
     * @Author: xujianjian
     * @Date: 2019/11/29 14:18
     */
    @ApiOperation(value = "获取服务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getInfo.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<Map<String, String>> getInfo() {

        SingleResponse<Map<String, String>> response = new SingleResponse<>();
        try {
            Map<String, String> info = appAcountService.getInfo();
//            PSmhDevice bccaServiceInfo = ComonUtils.getBccaServiceInfo();
//            PD3orgZc dServiceInfo = ComonUtils.get3DServiceInfo();
//            HashMap<String, String> map = new HashMap<>();
//
//            map.put("id", bccaServiceInfo.getId());//ID
//            map.put("serviceId", bccaServiceInfo.getServiceId());//服务ID
//            map.put("serviceKey", bccaServiceInfo.getServiceKey());//服务KEY
////            map.put("attr5",bccaServiceInfo.getAttr5());//接入商账号
////            map.put("attr1",bccaServiceInfo.getAttr1());//核心平台服务地址
////            map.put("serviceBackUrl",bccaServiceInfo.getServiceBackUrl());//核心平台回调本项目地址
////            map.put("d3Id",dServiceInfo.getId() == null ? null:dServiceInfo.getId().toString());//3D信息ID
//            map.put("orgName", dServiceInfo.getOrgName());//3D组织名称
//            map.put("account", dServiceInfo.getAccount());//3D服务账号
//            map.put("belongOrgId", dServiceInfo.getBelongOrgId());//3D注册成功后的组织ID
            response.setData(info);
        } catch (Exception e) {
            logger.error("获取服务信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端保存服务信息
     * @Param: [map]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/29 13:40
     */
    @ApiOperation(value = "保存服务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "saveInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> saveInfo(@ApiJsonObject(name = "login_model", value = {
            @ApiJsonProperty(key = "serviceId", example = "vV40XS4pXCPo95wosb8", description = "服务ID（必填）"),
            @ApiJsonProperty(key = "serviceKey", example = "d09e53beb9634b64b554b7ff8116dd1a", description = "服务KEY（必填）"),
            @ApiJsonProperty(key = "orgName", example = "金鑫科技", description = "组织机构名称（必填）"),
            @ApiJsonProperty(key = "id", example = "id", description = "非必填")
    }) @RequestBody Map<String, String> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            appAcountService.saveInfo(map);
        } catch (Exception e) {
            logger.error("保存服务信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 客户端手动添加订阅账户
     * @author xjj
     * @date 2019/10/25
     */
    @ApiOperation(value = "手动添加订阅账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "dyAccount.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> dyAccount(@RequestParam String account) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appAcountService.dyAccount(account);
            if (stringResultDO.isSuccess()) {
                response.setMsg("订阅账户成功！");
            } else {
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("账户订阅异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端账户订阅
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/30 9:10
     */
    @ApiOperation(value = "账户订阅")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "userAccount", value = "userAccount", required = true),
            @ApiImplicitParam(name = "settingId", value = "settingId", required = true)
    })
    @RequestMapping(value = "sbdy.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> sbdy(@RequestBody PSmhSetting pSmhSetting) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            appAcountService.editAccount(pSmhSetting);
            ResultDO<String> sbdy = appAcountService.sbdy(pSmhSetting);
            if (sbdy.isSuccess()) {
                response.setMsg("账户订阅成功！");
            } else {
                response.setStatus(501);
                response.setMsg(sbdy.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("账户订阅异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端取消订阅
     * @Param: [pSmhSetting]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/30 9:56
     */
    @ApiOperation(value = "取消订阅")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "smarthomeAccount", value = "smarthomeAccount", required = true),
            @ApiImplicitParam(name = "settingId", value = "settingId", required = true)
    })
    @RequestMapping(value = "delSbdy.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> delSbdy(@RequestBody PSmhSetting pSmhSetting) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appAcountService.delSbdy(pSmhSetting);
            if (stringResultDO.isSuccess()) {
                response.setMsg("取消订阅成功！");
            } else {
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("账户订阅异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 客户端同步智能设备
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:59
     */
    @ApiOperation(value = "同步智能设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "userAccount", value = "userAccount", required = true)
    })
    @RequestMapping(value = "syncSbxx.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> syncSbxx(@RequestParam String account) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appAcountService.syncSbxx(account);
            if (stringResultDO.isSuccess()) {
                response.setMsg(stringResultDO.getData());
            } else {
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(501);
            }
        } catch (Exception e) {
            logger.error("同步智能设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 客户端设备绑定（大管家新版接口）
     * @Param: [request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/18 10:44
     */
    @ApiOperation(value = "设备绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "sbbinding.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> newsbbinding(@ApiJsonObject(name = "map", value = {
            @ApiJsonProperty(key = "account", example = "15000000000000", description = "BCCA账号（必填）"),
            @ApiJsonProperty(key = "genuine_code", example = "sdfsfdvfd565df6dhf6g", description = "正品码（必填）"),
            @ApiJsonProperty(key = "serial_num", example = "15610126265946262565", description = "序列号（必填）")
    })
                                               @RequestBody Map<String, String> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appAcountService.newsbbinding(map);
            if (stringResultDO.isSuccess()) {
                response.setMsg("设备绑定成功！");
            } else {
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(501);
            }
        } catch (Exception e) {
            logger.error("客户端设备绑定异常", e);
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
    @ApiOperation(value = "设备解绑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "deviceMac", value = "deviceMac", required = true),
            @ApiImplicitParam(name = "account", value = "account", required = true)
    })
    @RequestMapping(value = "sbJiebing.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> newsbJiebing(@RequestBody PSbgl pSbgl) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appAcountService.newsbJiebing(pSbgl);
            if(stringResultDO.isSuccess()){
                response.setMsg("设备解绑成功！");
            }else{
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(501);
            }
        } catch (Exception e) {
            logger.error("设备解绑异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端分页查询申请的账号
     * @Param: [queryRequest]
     * @return: PageResponse<PSmhSetting>
     * @Author: xujianjian
     * @Date: 2019/11/30 13:32
     */
    @ApiOperation(value = "分页查询申请的账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "smarthomeAccount", value = "账号"),
            @ApiImplicitParam(name = "attr4", value = "申请人姓名")
    })
    @RequestMapping(value = "listAccount_3.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PSmhSetting> listAccount_3(@RequestBody QueryRequest<PSmhSetting> queryRequest) {
        PageResponse<PSmhSetting> response = new PageResponse<>();
        PSmhSetting data = queryRequest.getData() == null ? new PSmhSetting() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            data.setCreatorId(user.getUserId());
            PageInfo<PSmhSetting> pSmhSettingPageInfo = smhSettingService.listAccount_3(data, page, limit);
            response.setData(pSmhSettingPageInfo.getList());
            response.setTotal(pSmhSettingPageInfo.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
        } catch (Exception e) {
            logger.error("分页查询申请的账号（大管家）异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端三方账号禁用和启用
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/30 13:40
     */
    @ApiOperation(value = "三方账号禁用和启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "settingId", value = "ID", required = true),
            @ApiImplicitParam(name = "sfkt", value = "1:禁用；2：启用", required = true)
    })
    @RequestMapping(value = "editAccountInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> editAccountInfo(@RequestBody PSmhSetting pSmhSetting) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            smhSettingService.editAccountInfo(pSmhSetting);
        } catch (Exception e) {
            logger.error("三方账号禁用和启用异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 删除第三方账号
     * @Param: [settingId]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/30 13:46
     */
    @ApiOperation(value = "删除第三方账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "settingId", value = "ID", required = true)
    })
    @RequestMapping(value = "delAccount.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> delAccount(@RequestParam String settingId) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            appAcountService.delAccount(settingId);
        } catch (Exception e) {
            logger.error("删除第三方账号异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param request
     * @return ListResponse<PSmhSetting>
     * @Description //TODO 查询当前登陆用户下所订阅(启用)的账号
     * @author xjj
     * @date 2019/10/29
     */
    @ApiOperation(value = "查询当前登陆用户下所订阅(启用)的账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getAccount.json", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse<PSmhSetting> getAccount() {
        ListResponse<PSmhSetting> response = new ListResponse<>();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            List<PSmhSetting> account = smhSettingService.getAccount(user.getUserId());
            response.setData(account);
        } catch (Exception e) {
            logger.error("查询当前登陆用户下所订阅的账号异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 申请bcca账号
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/18 10:27
     */
    @ApiOperation(value = "申请bcca账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "applyBccaAccount.json", method = RequestMethod.POST)
    @ResponseBody
    @Log("申请bcca账号")
    public SingleResponse<String> newadd(@RequestBody PSmhSetting pSmhSetting) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appAcountService.newadd(pSmhSetting);
            if(stringResultDO.isSuccess()){
                response.setMsg("申请bcca账号成功！");
                response.setData(stringResultDO.getData());
            }else{
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(501);
            }
        } catch (Exception e) {
            logger.error("申请大管家账号异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }




}
