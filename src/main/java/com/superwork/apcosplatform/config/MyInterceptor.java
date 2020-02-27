package com.superwork.apcosplatform.config;

import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.utils.HTTPUtils;
import com.superwork.apcosplatform.utils.MobileDevice;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jianjian Xu
 * @create: 2019/10/23 12:21
 * @description:
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    private RedisUtil redisUtil;

    public MyInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean mobileDevice = MobileDevice.isMobileDevice(request);
        if (mobileDevice) {//客户端登录
            String token = request.getHeader("token");
            System.out.println("打印token:"+token);
            if (StringUtils.isEmpty(token)) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"status\":401, \"msg\":\"请重新登陆！\",\"version\":1}");
                return false;
            } else {
                Object o = redisUtil.get(token);
                if (o == null) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write("{\"status\":401, \"msg\":\"请重新登陆！\",\"version\":1}");
                    return false;
                }
            }
        } else {
            String servletPath = request.getServletPath();
            System.out.println("请求的地址："+servletPath);
            HttpSession session = request.getSession();
            String id = session.getId();
            PSysUser user = (PSysUser) session.getAttribute("user");
            boolean ajaxRequest = HTTPUtils.isAjaxRequest(request);
            if (user == null) {
                if (!ajaxRequest) {
                    response.sendRedirect("/");
                } else {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write("{\"status\":401, \"msg\":\"请重新登陆！\",\"version\":1}");
                }
                return false;
            }else{
                List<String> jurisdiction =(List) redisUtil.get("jurisdiction");
                if(jurisdiction.contains(servletPath)){
                    Object o = redisUtil.get(user.getUserId() + ":jurisdiction");
                    List<String> myjurisdiction = new ArrayList<>();
                    if(o != null){
                        myjurisdiction =(List) redisUtil.get(user.getUserId()+":jurisdiction");
                    }
                    if(myjurisdiction.size()== 0 || !myjurisdiction.contains(servletPath)){
                        if (!ajaxRequest) {
                            response.sendRedirect("/");
                        } else {
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().write("{\"status\":402, \"msg\":\"权限不足，请联系管理员或重新登录！！\",\"version\":1}");
                        }
                        return false;
                    }
                }
//                String str = (String) redisUtil.get(user.getUserId());
//                if (!id.equals(str)) {
//                    if (!ajaxRequest) {
//                        response.sendRedirect("/");
//                    } else {
//                        response.setContentType("application/json;charset=utf-8");
//                        response.getWriter().write("{\"status\":401, \"msg\":\"你的账号在其他地方登陆了！\",\"version\":1}");
//                    }
//                    return false;
//                }
            }
        }
        return true;
    }
}
