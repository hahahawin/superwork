package com.superwork.apcosplatform.controller;


import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.MD5;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PAppSoftware;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.APPVersionService;
import com.superwork.apcosplatform.service.SysUserServer;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author Jianjian Xu
 * @create: 2019/10/21 13:33
 * @description:
 */
@Controller
@RequestMapping("index")
public class IndexController {

    public static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysUserServer sysUserServer;

    @Autowired
    APPVersionService appVersionService;

    @Value("${serviceIp}")
    private String serviceIp;

    /**
     * @param *       @param phone
     * @param request
     * @return com.jxsc.result.SingleResponse<java.lang.String>
     * @Description //TODO 发送短信验证码并验证号码是否注册
     * @author xjj
     * @date 2019/10/21
     */
    @RequestMapping("getCode.json")
    @ResponseBody
    public SingleResponse<String> getCode(@RequestBody QueryRequest<String> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        String cellphoneNo = queryRequest.getData();
        try {
            if (StringUtils.isEmpty(cellphoneNo)) {
                response.setStatus(100);
                response.setMsg("手机号码不能位空！");
                return response;
            }
            //TODO 调用短信接口
            //String random6 = IDUtils.random6();//生成
//            HttpXmlUtil.send(cellphoneNo,random6);
            ResultDO<String> phone1 = sysUserServer.findPhone(cellphoneNo);
            if (!phone1.isSuccess()) {
                response.setStatus(100);
                response.setMsg(phone1.getErrorMsg());
                return response;
            }
            String id = request.getSession().getId();
            String code = "111111";
            redisUtil.set(id, code, 60 * 5);
            response.setMsg("验证码已发送至你的手机，5分钟内有效！");
        } catch (Exception e) {
            logger.error("发送短信验证码发生异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }

    /**
     * @param * @param userAccount
     * @return com.jxsc.result.SingleResponse<java.lang.String>
     * @Description //TODO 验证登陆账号是否唯一
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("checkUserAccount.json")
    @ResponseBody
    public SingleResponse<String> checkUserAccount(@RequestBody QueryRequest<String> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String userAccount = queryRequest.getData();
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
            logger.error("发送短息验证码和验证手机号是的注册异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param *       @param queryRequest
     * @param request
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 验证验证码
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("checkedCode.json")
    @ResponseBody
    public SingleResponse<String> checkedCode(@RequestBody QueryRequest<String> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        String id = request.getSession().getId();
        String o = (String) redisUtil.get(id);
        try {
            if (!data.equals(o)) {
                response.setStatus(100);
                response.setMsg("验证码错误！");
            }
        } catch (Exception e) {
            logger.error("验证验证码异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @param * @param queryRequest
     * @return com.jxsc.result.SingleResponse<java.lang.String>
     * @Description //TODO 注册
     * @author xjj
     * @date 2019/10/22
     */
    @RequestMapping("register.json")
    @ResponseBody
    @Log("注册")
    public SingleResponse<String> register(@RequestBody QueryRequest<PSysUser> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser sysUser = queryRequest.getData();
        sysUser.setCreatedTime(new Date());
        String s = UUID.randomUUID().toString();
        String s1 = s.replaceAll("-", "");
        sysUser.setUserId(s1);
        sysUser.setEnable(new BigDecimal(2));
        sysUser.setUserType(new BigDecimal(2));//普通用户
        sysUser.setIsdel("2");//未删除
        MD5 d = new MD5();
        sysUser.setUserPassword(d.getMD5ofStr(sysUser.getUserPassword()));
        try {
            sysUserServer.register(sysUser);
            session.setAttribute("user", sysUser);
            redisUtil.set(sysUser.getUserId(),session.getId());
            redisUtil.del("data:Register");//删除注册统计数缓存
        } catch (Exception e) {
            logger.error("注册异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 跳转到注册页面
     * @Param: []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: xujianjian
     * @Date: 2019/11/20 15:55
     */
    @RequestMapping("register")
    public ModelAndView toRegister() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /**
     * @param *       @param queryRequest
     * @param request
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 登陆
     * @author xjj
     * @date 2019/10/22
     */
    @Log("登陆")
    @RequestMapping("login.json")
    @ResponseBody
    public SingleResponse<String> login(@RequestBody QueryRequest<PSysUser> queryRequest, HttpServletRequest request,HttpServletResponse response1) {
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser data = queryRequest.getData();
        MD5 md5 = new MD5();
        data.setUserPassword(md5.getMD5ofStr(data.getUserPassword()));
        try {
            ResultDO<Map<String, Object>> login = sysUserServer.login(data);
            if (login.isSuccess()) {
                PSysUser user = (PSysUser)login.getData().get("user");
//                redisUtil.set(user.getUserId(),session.getId());
                session.setAttribute("user", user);
                session.setAttribute("bccaService", login.getData().get("bccaService"));
                redisUtil.del("data:Login");//删除登陆缓存数据
            } else {
                response.setStatus(100);
                response.setMsg(login.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("登陆异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @param * @param request
     * @return org.springframework.web.servlet.ModelAndView
     * @Description //TODO 退出登陆
     * @author xjj
     * @date 2019/10/24
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        session.invalidate();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * @Description: TODO 找回密码时发送验证码
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:55
     */
    @RequestMapping("getCodeWhenPassword.json")
    @ResponseBody
    public SingleResponse<String> getCodeWhenPassword(@RequestBody QueryRequest<String> queryRequest,HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            ResultDO<String> codeWhenPassword = sysUserServer.getCodeWhenPassword(data);
            if(!codeWhenPassword.isSuccess()){
                response.setStatus(100);
                response.setMsg(codeWhenPassword.getErrorMsg());
            }else{
                String id = request.getSession().getId();
                //TODO 调用短信接口
                //String random6 = IDUtils.random6();//生成
//            HttpXmlUtil.send(cellphoneNo,random6);
                String code = "111111";
                redisUtil.set(id,code,60*5);
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
     * @Description: TODO 找回密码
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:56
     */
    @RequestMapping("getBackPassword.json")
    @ResponseBody
    public SingleResponse<String> getBackPassword(@RequestBody QueryRequest<PSysUser> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser data = queryRequest.getData();
        MD5 md5 = new MD5();
        data.setUserPassword(md5.getMD5ofStr(data.getUserPassword()));
        try {
            sysUserServer.getBackPassword(data);
        } catch (Exception e) {
            logger.error("找回密码时异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询APP下载地址
     * @Param: [type]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/17 15:04
     */
    @RequestMapping("getAppPath.json")
    @ResponseBody
    public void getAppPath(HttpServletRequest request, HttpServletResponse response){
        try {
            String id = request.getParameter("id");
            //下载次数加1
            PAppSoftware scanning = appVersionService.scanning(id);
            response.sendRedirect(serviceIp+scanning.getPath());
        } catch (Exception e) {
            logger.error("查询APP下载地址异常", e);
        }
    }


    /**
     * @Description: TODO 验证电话号码是否唯一
     * @Param: [testNetity]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2020/1/10 11:11
     */
    @RequestMapping("checkCellPhone.json")
    @ResponseBody
    public SingleResponse<String> checkCellPhone(@RequestParam String cellPhone){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> phone = sysUserServer.findPhone(cellPhone);
            if(!phone.isSuccess()){
                response.setStatus(100);
                response.setMsg(phone.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("验证电话号码是的存在异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 第三方登录获取token
     * @Param: [pSysUser]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/13 17:28
     */
    @RequestMapping("getToken.json")
    @ResponseBody
    public SingleResponse<String> getToken(@RequestBody PSysUser pSysUser){

        String password = pSysUser.getUserPassword();
        SingleResponse<String> response = new SingleResponse<>();
        try {
            MD5 md5 = new MD5();
            pSysUser.setUserPassword(md5.getMD5ofStr(pSysUser.getUserPassword()));
            ResultDO<Map<String, Object>> login = sysUserServer.login(pSysUser);
            if(!login.isSuccess()){
                response.setStatus(100);
                response.setMsg(login.getErrorMsg());
                return response;
            }else{
                String s = UUID.randomUUID().toString();
                s=s.replaceAll("-","");
                //密码还原
                pSysUser.setUserPassword(password);
                redisUtil.set(s,pSysUser,60*30);
                response.setToken(s);
                response.setMsg("token:30分钟有效！");
            }

        } catch (Exception e) {
            logger.error("登陆异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 使用token登录
     * @Param: [token]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/13 17:28
     */
    @RequestMapping("thirdLogin.json")
    @ResponseBody
    public SingleResponse<String> thirdLogin(@RequestParam String token){
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = ComonUtils.getSession();
        try {
            Object o = redisUtil.get(token);
            if(o==null){
                response.setStatus(100);
                response.setMsg("token失效");
                return response;
            }
           PSysUser data = (PSysUser)redisUtil.get(token);//获取token
            MD5 md5 = new MD5();
            data.setUserPassword(md5.getMD5ofStr(data.getUserPassword()));
            ResultDO<Map<String, Object>> login = sysUserServer.login(data);
            if (login.isSuccess()) {
                PSysUser user = (PSysUser)login.getData().get("user");
//                redisUtil.set(user.getUserId(),session.getId());
                session.setAttribute("user", user);
                session.setAttribute("bccaService", login.getData().get("bccaService"));
                redisUtil.del("data:Login");//删除登陆缓存数据
            } else {
                response.setStatus(100);
                response.setMsg(login.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("登陆异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }





}
