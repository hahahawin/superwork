package com.superwork.apcosplatform.aop;


import com.google.gson.Gson;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PSysLog;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.utils.HTTPUtils;
import com.superwork.apcosplatform.utils.MobileDevice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    RedisUtil redisUtil;

    Gson gson = new Gson();
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.superwork.apcosplatform.aop.Log)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        try {
            //保存日志
            PSysLog sysLog = new PSysLog();

            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();

            //获取操作
            Log myLog = method.getAnnotation(Log.class);
            if (myLog != null) {
                String value = myLog.value();
                sysLog.setOperation(value);//保存获取的操作
            }

            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法名
            String methodName = method.getName();
            sysLog.setMethod(className + "." + methodName);

            //请求的参数
            Object[] args = joinPoint.getArgs();
            List<Object> param = new ArrayList<>();
            for (Object o : args) {
                if (o.getClass().getName().indexOf("com.superwork.apcosplatform.domain")>=0 || o.getClass().getName().indexOf("java.lang")>=0) {
                    param.add(o);
                }else{
                    param.add(o.toString());
                }
            }
            //将参数所在的数组转换成json
            String params = gson.toJson(param);
            sysLog.setParam(params);
            sysLog.setCreateDate(new Date());
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();
            HttpSession session = request.getSession();
            //获取用户ip地址
            sysLog.setIp(HTTPUtils.getIpAddress(request));
            boolean mobileDevice = MobileDevice.isMobileDevice(request);
            if(mobileDevice){
                System.out.println("手机端登录");
                String token = request.getHeader("token");
                if(StringUtils.isEmpty(token)){
                    token = session.getAttribute("token").toString();
                }
                System.out.println("token:"+token);
                PSysUser user =(PSysUser) redisUtil.get(token);
                if(user != null){
                    sysLog.setUsername(user.getUserId());
                }
                //插入日志队列
                LogQueue.getMailQueue().produce(sysLog);
            }else{
                System.out.println("WEB端登录");
                PSysUser user = (PSysUser)session.getAttribute("user");
                if(user != null){
                    sysLog.setUsername(user.getUserId());
                }
                //插入日志队列
                LogQueue.getMailQueue().produce(sysLog);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
