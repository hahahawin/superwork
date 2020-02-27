package com.superwork.apcosplatform.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.app.common.HttpXmlUtil;
import com.superwork.apcosplatform.app.service.AppSysUserService;
import com.superwork.apcosplatform.app.service.AppUnLoginService;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.MD5;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.controller.IndexController;

import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.SysUserServer;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.IDUtils;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @program: code->UnLoginController
 * @description: 移动端无需拦截
 * @author: xjj
 * @create: 2019-11-28 13:54
 **/
@Controller
@RequestMapping("appUnLogin")
public class AppUnLoginController {

    public static Logger logger = Logger.getLogger(AppUnLoginController.class);

    @Autowired
    AppUnLoginService appUnLoginService;

    @Autowired
    SysUserServer sysUserServer;

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AppSysUserService appSysUserService;

    /**
     * @Description: TODO 移动端登陆
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/28 15:46
     */

    @Log("移动端登陆")
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号",required=true),
            @ApiImplicitParam(name = "userPassword", value = "密码",required=true)
    })
    @RequestMapping(value = "login.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> login(@RequestBody PSysUser pSysUser, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        MD5 md5 = new MD5();
        pSysUser.setUserPassword(md5.getMD5ofStr(pSysUser.getUserPassword()));
        try {
            ResultDO<Map<String, Object>> login = sysUserServer.login(pSysUser);
            if (login.isSuccess()) {
                PSysUser user = (PSysUser)login.getData().get("user");
                String token = UUID.randomUUID().toString();
                token = token.replaceAll("-", "");
                System.out.println("登录时："+token);
                response.setToken(token);
//                Object o = redisUtil.get(user.getUserId());//是否有存入过token
//                if(o!=null){
//                    redisUtil.del(o.toString()); //删除之前的token
//                }
                HttpSession session = request.getSession();
                session.setAttribute("token",token);
//                redisUtil.set(user.getUserId(),token);
                redisUtil.set(token,user,60*60*24*30);//把token和user存入redis中 30天有效
                redisUtil.del("data:Login");//删除登陆缓存数据
            } else {
                response.setStatus(100);
                response.setMsg(login.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("移动端登陆异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端注册
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/28 16:37
     */
    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号",required=true),
            @ApiImplicitParam(name = "userPassword", value = "密码",required=true),
            @ApiImplicitParam(name = "cellphoneNo", value = "电话号码",required=true),
    })
    @RequestMapping(value = "register.json",method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "账号或密码不能为空！")
    })
    @Log("注册")
    public SingleResponse<String> register(@RequestBody PSysUser sysUser,HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            if(StringUtils.isEmpty(sysUser.getUserAccount()) || StringUtils.isEmpty(sysUser.getUserPassword()) ){
                response.setMsg("账号或密码不能为空！");
                response.setStatus(100);
                return response;
            }
            sysUser.setCreatedTime(new Date());
            String s = UUID.randomUUID().toString();
            String s1 = s.replaceAll("-", "");
            sysUser.setUserId(s1);
            sysUser.setEnable(new BigDecimal(2));
            sysUser.setUserType(new BigDecimal(2));//普通用户
            sysUser.setIsdel("2");//未删除
            MD5 d = new MD5();
            sysUser.setUserPassword(d.getMD5ofStr(sysUser.getUserPassword()));
            sysUserServer.register(sysUser);
            String token = UUID.randomUUID().toString();
            redisUtil.set(s1,token);
            redisUtil.set(token,sysUser);//把token和user存入redis中
            HttpSession session = request.getSession();
            session.setAttribute("token",token);
            response.setMsg("注册成功！");
            response.setToken(token);
            redisUtil.del("data:Register");//删除注册统计数缓存
        } catch (Exception e) {
            logger.error("客户端注册异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端发送短信验证码并验证号码是否注册
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/29 9:24
     */
    @ApiOperation(value = "发送短信验证码并验证号码是否注册")
    @RequestMapping(value = "getCode.json",method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> getCode(@RequestParam String  cellphoneNo) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            if (StringUtils.isEmpty(cellphoneNo)) {
                response.setStatus(100);
                response.setMsg("手机号码不能位空！");
                return response;
            }
            //TODO 调用短信接口
//            String random6 = IDUtils.random6();//生成
//            HttpXmlUtil.send(cellphoneNo,random6);
            ResultDO<String> phone1 = sysUserServer.findPhone(cellphoneNo);
            if (!phone1.isSuccess()) {
                response.setStatus(100);
                response.setMsg(phone1.getErrorMsg());
                return response;
            }
            String code = "111111";
            redisUtil.set(cellphoneNo,code,60 * 5);
            response.setMsg("验证码已发送至你的手机，5分钟内有效！");
        } catch (Exception e) {
            logger.error("客户端发送短信验证码发生异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端验证登陆账号是否唯一
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/29 9:29
     */
    @ApiOperation(value = "验证登陆账号是否唯一")
    @RequestMapping(value = "checkUserAccount.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> checkUserAccount(@RequestBody String userAccount) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            if (StringUtils.isEmpty(userAccount)) {
                response.setStatus(100);
                response.setMsg("登陆账号不能为空！");
                return response;
            }
            ResultDO<String> resultDO = sysUserServer.checkUserAccount(userAccount);
            if (!resultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
                return response;
            }
        } catch (Exception e) {
            logger.error("验证登陆账号是否唯一", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端验证验证码
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/29 9:31
     */
    @ApiOperation(value = "验证验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellphoneNo", value = "电话号码",required=true),
            @ApiImplicitParam(name = "code", value = "验证码",required=true)
    })
    @RequestMapping(value = "checkedCode.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> checkedCode(@RequestBody Map<String,String> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            String rcode = redisUtil.get(map.get("cellphoneNo")).toString();
            if(!map.get("code").equals(rcode)){
                response.setStatus(100);
                response.setMsg("验证码错误！");
            }else{
                response.setMsg("验证成功！");
                redisUtil.del(map.get("cellphoneNo"));//清除验证码
            }

        } catch (Exception e) {
            logger.error("验证验证码异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }



    /**
     * @Description: TODO 客户端找回密码时发送验证码
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/29 9:40
     */
    @ApiOperation(value = "找回或修改密码时发送验证码")
    @RequestMapping(value = "getCodeWhenPassword.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> getCodeWhenPassword(@RequestBody String account) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> codeWhenPassword = appUnLoginService.getCodeWhenPassword(account);
            if(!codeWhenPassword.isSuccess()){
                response.setStatus(100);
                response.setMsg(codeWhenPassword.getErrorMsg());
            }else{
                //TODO 调用短信接口

                //String random6 = IDUtils.random6();//生成
//            HttpXmlUtil.send(cellphoneNo,random6);
                String code = "111111";
                redisUtil.set(account,code,60*5);
                response.setMsg("验证码已发送至你的手机，5分钟内有效！");
            }
        } catch (Exception e) {
            logger.error("找回密码时发送短信验证异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 客户端找回密码(作废)
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/29 9:42
     */
    @ApiOperation(value = "找回密码(作废)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellphoneNo", value = "电话号码",required=true),
            @ApiImplicitParam(name = "password", value = "新密码",required=true)
    })
    @RequestMapping(value = "getBackPassword.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> getBackPassword(@RequestBody PSysUser data){
        SingleResponse<String> response = new SingleResponse<>();
        MD5 md5 = new MD5();
        data.setUserPassword(md5.getMD5ofStr(data.getUserPassword()));
        try {
            ResultDO<String> resultDO = sysUserServer.getBackPassword(data);
            if(!resultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
                return response;
            }
        } catch (Exception e) {
            logger.error("找回密码时异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    @ApiOperation(value = "修改密码或找回密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required=true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功！"),
            @ApiResponse(code = 100,message = "验证码错误！")
    })
    @RequestMapping(value="replacePassWord.json",method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> replacePassWord(@RequestBody JSONObject jsonObject){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            String userAccount = jsonObject.get("userAccount").toString();
            String newPassWord = jsonObject.get("newPassWord").toString();
            String code = jsonObject.get("code").toString();
            Object o = redisUtil.get(userAccount);
            if(o==null || !o.toString().equals(code)){
                response.setStatus(100);//验证码错误
                response.setMsg("验证码错误");
                return response;
            }
            MD5 md5 = new MD5();
            newPassWord = md5.getMD5ofStr(newPassWord);
            ResultDO<String> resultDO = appSysUserService.replacePassWord(userAccount, newPassWord);
            if(!resultDO.isSuccess()){
                response.setMsg(resultDO.getErrorMsg());
                response.setStatus(100);
                return response;
            }
            response.setMsg("修改密码成功！");
            redisUtil.del(userAccount);
        } catch (Exception e) {
            logger.error("修改密码或找回密码异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }




}
