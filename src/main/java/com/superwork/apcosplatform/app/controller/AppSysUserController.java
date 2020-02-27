package com.superwork.apcosplatform.app.controller;

import com.google.gson.JsonObject;
import com.superwork.apcosplatform.app.service.AppSysUserService;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.controller.IndexController;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.SysUserServer;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;


/**
 * @program: code->SysUserController
 * @description: 用户操作
 * @author: xjj
 * @create: 2019-11-28 16:16
 **/
@Controller
@RequestMapping("appUser")
public class AppSysUserController {

    @Autowired
    RedisUtil redisUtil;
    public static Logger logger = Logger.getLogger(AppSysUserController.class);
    @Autowired
    SysUserServer sysUserServer;
    @Autowired
    AppSysUserService appSysUserService;



    /**
     * @Description: TODO 客户端获取当前登录者信息
     * @Param: [request]
     * @return: SingleResponse<PSysUser>
     * @Author: xujianjian
     * @Date: 2019/11/29 9:51
     */
    @ApiOperation(value = "获取当前登录者信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @RequestMapping(value="getUserInfo.json",method=RequestMethod.GET)
    @ResponseBody
    public SingleResponse<PSysUser> getUserInfo(HttpServletRequest request) {
        SingleResponse<PSysUser> response = new SingleResponse<>();
        String token = request.getHeader("token");
        try {
            PSysUser pSysUser = (PSysUser) redisUtil.get(token);
            response.setData(pSysUser);
            response.setMsg("成功！");
        } catch (Exception e) {
            logger.error("客户端获取当前登录者信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 退出账号
     * @Param: [request]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: xujianjian
     * @Date: 2019/11/28 16:18
     */
    @ApiOperation(value = "退出账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @RequestMapping(value="logout.json",method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> logout(HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            String token = request.getHeader("token");
            redisUtil.del(token);
            response.setMsg("退出成功！");
        } catch (Exception e) {
            logger.error("客户端退出登录异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param *       @param queryRequest
     * @param
     * @return SingleResponse<PSysUser>
     * @Description //TODO 修改用户信息
     * @author xjj
     * @date 2019/10/22
     */
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @RequestMapping(value="editUserInfo.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> editUserInfo(@RequestBody PSysUser pSysUser,HttpServletRequest request ) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            pSysUser.setEditedTime(new Date());
            pSysUser.setEditorId(user.getUserId());
            pSysUser = sysUserServer.editUserInfo(pSysUser);
            String token = request.getHeader("token");
            redisUtil.set(token,pSysUser);//重新存入用户信息
            response.setMsg("修改成功！");
        } catch (Exception e) {
            logger.error("修改用户信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "更换号码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功！"),
            @ApiResponse(code = 201,message = "更换的号码已经存在！"),
            @ApiResponse(code = 100,message = "验证码错误！")
    })
    @RequestMapping(value="replacePhone.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> replacePhone(@RequestBody JsonObject jsonObject,HttpServletRequest request){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            String userAccount = jsonObject.get("userAccount").toString();
            String newPhone = jsonObject.get("newPhone").toString();
            String code = jsonObject.get("code").toString();
            ResultDO<String> phone = sysUserServer.findPhone(newPhone);
            if(!phone.isSuccess()){
                response.setStatus(201);//号码已存在
                response.setMsg(phone.getErrorMsg());
                return response;
            }
            Object o = redisUtil.get(userAccount);
            if(o==null || !o.toString().equals(code)){
                response.setStatus(100);//验证码错误
                response.setMsg("验证码错误");
                return response;
            }
            appSysUserService.replacePhone(userAccount,newPhone);
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            user.setCellphoneNo(newPhone);
            String token = request.getHeader("token");
            redisUtil.set(token,user);//重新存入信息
            redisUtil.del(userAccount);//清除验证码
        } catch (Exception e) {
            logger.error("修改用户信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "获取当前登录者头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功！"),
            @ApiResponse(code = 101,message = "当前用户未上传头像！")
    })
    @RequestMapping(value="getUserImg.json",method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> getUserHead(HttpServletRequest request){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            String userHead = sysUserServer.getUserHead(user.getUserId());
            if(StringUtils.isEmpty(userHead)){
                response.setStatus(101);
                response.setMsg("当前用户未上传头像！");
            }else{
                response.setMsg("查询成功！");
                response.setData(userHead);
            }
        } catch (Exception e) {
            logger.error("获取当前登录者头像异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 静脉指纹登录
     * @Param: []
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/11 8:55
     */
    @ApiOperation(value = "静脉指纹登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功！"),
            @ApiResponse(code = 101,message = "token失效，请使用账号登录！")
    })
    @RequestMapping(value="fingerPrintLogin.json",method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> fingerPrintLogin(HttpServletRequest request){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            String token = request.getHeader("token");
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            if(user == null){
                response.setStatus(101);
                response.setMsg("token失效，请使用账号登录！");
                return response;
            }
            String newToken = UUID.randomUUID().toString();
            newToken = newToken.replaceAll("-", "");
            response.setData(newToken);
            response.setToken(newToken);
            response.setMsg("登录成功！");
            redisUtil.del(token);
            redisUtil.set(newToken,user,30*24*60*60);
            redisUtil.del("data:Login");//删除登陆缓存数据
        } catch (Exception e) {
            logger.error("静脉指纹登录异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

}
