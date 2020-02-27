package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.MD5;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PD3orgZc;
import com.superwork.apcosplatform.domain.PSysRight;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.RighrService;
import com.superwork.apcosplatform.service.ServiceInfo;
import com.superwork.apcosplatform.service.SysUserServer;
import com.superwork.apcosplatform.utils.CollectionUtils;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Jianjian Xu
 * @create: 2019/10/22 13:36
 * @description:
 */
@Controller
@RequestMapping("pSysUser")
public class PSysUserController {

    public static Logger logger = Logger.getLogger(PSysUserController.class);
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysUserServer sysUserServer;

    @Autowired
    RighrService righrService;

    @Value("${spring.hasAllRight}")
    private String hasAllRight;

    @Autowired
    ServiceInfo serviceInfo;

    /**
     * @Description: TODO 跳转至首页
     * @Param: [request]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: xujianjian
     * @Date: 2019/11/20 16:00
     */
    @RequestMapping("toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        Object jurisdiction = redisUtil.get("jurisdiction");
        if(jurisdiction == null){
            List<String> strings = righrService.loadRight();
            redisUtil.set("jurisdiction",strings);
        }
        modelAndView.addObject("user", user);
        List<PSysRight> pSysRights = righrService.loadMenu();//菜单
        List<String> strings;
        if(hasAllRight.indexOf(user.getUserAccount()) == -1){
            strings = righrService.selectMyRight();//权限
        }else{
            strings = righrService.loadRight();
        }
        redisUtil.set(user.getUserId()+":jurisdiction",strings);
        List<PSysRight> pSysRights1 = CollectionUtils.parseTreeList(pSysRights);
        //组织信息
        PD3orgZc pd3orgZc = serviceInfo.selectServiceInfo(user.getUserId());
        if(pd3orgZc == null){
            pd3orgZc.setOrgName("还未设置组织信息！请前往[第三方服务信息]中完善");
            modelAndView.addObject("zzInfo",pd3orgZc);
        }else{
            modelAndView.addObject("zzInfo",pd3orgZc);
        }
        modelAndView.setViewName("index");
        modelAndView.addObject("menus",pSysRights1);
        String token = UUID.randomUUID().toString();
        token = token.replaceAll("-", "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("bccaService", pd3orgZc);
        redisUtil.set(token, map, 60 * 60 * 24 * 30);
        Cookie cookie = new Cookie("public_token", token);
        cookie.setPath("/");// 这个要设置
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", response.getHeader("Origin"));
        response.addCookie(cookie);
        return modelAndView;
    }

    /**
     * @Description: TODO 获取当前登录者信息
     * @Param: [request]
     * @return: SingleResponse<PSysUser>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:01
     */
    @RequestMapping("getUserInfo.json")
    @ResponseBody
    public SingleResponse<PSysUser> getUserInfo() {
        SingleResponse<PSysUser> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            response.setData(user);
        } catch (Exception e) {
            logger.error("获取当前登录者信息异常", e);
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
    @RequestMapping("editUserInfo.json")
    @ResponseBody
    public SingleResponse<PSysUser> editUserInfo(@RequestBody QueryRequest<PSysUser> queryRequest) {
        SingleResponse<PSysUser> response = new SingleResponse<>();
        PSysUser data = queryRequest.getData();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setEditorId(user.getUserId());
            data.setEditedTime(new Date());
            PSysUser user1 = sysUserServer.editUserInfo(data);
            if(data.getUserId().equals(user.getUserId())){
                HttpSession session = ComonUtils.getSession();
                session.setAttribute("user",user1);
            }
        } catch (Exception e) {
            logger.error("修改用户信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description //TODO 修改密码
     * @author xjj
     * @date 2019/10/23
     * @param request
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("editPassword.json")
    @ResponseBody
    public SingleResponse<String > editPassword(HttpServletRequest request){
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser)session.getAttribute("user");
        MD5 md5 = new MD5();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        try {
            String oldPassword1 = md5.getMD5ofStr(oldPassword);
            String newPassword1 = md5.getMD5ofStr(newPassword);
            if(user.getUserPassword().equals(oldPassword1)){
                user.setUserPassword(newPassword1);
                sysUserServer.editUserInfo(user);
                session.setAttribute("user",user);
            }else{
                response.setStatus(100);
                response.setMsg("原密码错误！");
            }
        } catch (Exception e) {
            logger.error("修改密码异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }

    /**
     * @Description: TODO 分页查询用户
     * @Param: [queryRequest]
     * @return: PageResponse<PSysUser>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:01
     */
    @RequestMapping("listUser.json")
    @ResponseBody
    public PageResponse<PSysUser> listUser(@RequestBody QueryRequest<PSysUser> queryRequest){

        PSysUser data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ?1:queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ?20:queryRequest.getLimit();
        PageResponse<PSysUser> response = new PageResponse<>();
        try {
            PageInfo<PSysUser> pSysUserPageInfo = sysUserServer.listUser(data, page, limit);
            response.setData(pSysUserPageInfo.getList());
            response.setTotal(pSysUserPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询用户异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description //TODO 管理员重置密码
     * @author xjj
     * @date 2019/10/23
     * @param  * @param queryRequest
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("setPassWord.json")
    @ResponseBody
    public SingleResponse<String> setPassWord(@RequestBody QueryRequest<PSysUser> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser data = queryRequest.getData();
        String pwd= "123456";
        MD5 md5 = new MD5();
        data.setUserPassword(md5.getMD5ofStr(pwd));
        try {
            sysUserServer.editUserInfo(data);
            response.setData(pwd);
        } catch (Exception e) {
            logger.error("管理员重置密码异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }



    /**
     * @Description //TODO 删除用户
     * @author xjj
     * @date 2019/10/31
     * @param  * @param queryRequest
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("delUser.json")
    @ResponseBody
    @Log("删除用户")
    public SingleResponse<String> delUser(@RequestBody QueryRequest<PSysUser> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser data = queryRequest.getData();
        if(data.getUserAccount().equals("admin")){
            response.setStatus(100);
            response.setMsg("admin账号不能删除！");
            return response;
        }
        try {
            sysUserServer.delUser(data);
        } catch (Exception e) {
            logger.error("删除用户异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 添加用户
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:02
     */
    @RequestMapping("addUser.json")
    @ResponseBody
    @Log("添加用户")
    public SingleResponse<String> addUser(@RequestBody QueryRequest<PSysUser> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser data = queryRequest.getData();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if(bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())){
                response.setStatus(100);
                response.setMsg("请完善组织服务信息！");
                return response;
            }
            sysUserServer.addUser(data);
        } catch (Exception e) {
            logger.error("添加用户异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

}
