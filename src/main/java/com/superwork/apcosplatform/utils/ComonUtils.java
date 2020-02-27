package com.superwork.apcosplatform.utils;

import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.PD3orgZc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jianjian Xu
 * @create: 2019/11/6 9:55
 * @description:
 */
public class ComonUtils {

    public static PSysUser getUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser)session.getAttribute("user");
        return user;
    }

    public static HttpSession getSession(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return session;
    }
    public static PD3orgZc getBccaServiceInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
//        PSmhDevice pSmhDevice = (PSmhDevice)session.getAttribute("bccaService");
        PD3orgZc pD3orgZc = (PD3orgZc)session.getAttribute("bccaService");
        return pD3orgZc;
    }
//    public static PD3orgZc get3DServiceInfo(){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        PD3orgZc pD3orgZc = (PD3orgZc)session.getAttribute("3DServiceInfo");
//        return pD3orgZc;
//    }
    //客户端获取user
    public static PSysUser getUsetOnApp(RedisUtil redisUtil){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        PSysUser user =(PSysUser) redisUtil.get(token);
        return user;
    }




}
