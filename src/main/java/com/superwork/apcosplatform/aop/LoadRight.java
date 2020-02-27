package com.superwork.apcosplatform.aop;

import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.service.RighrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @program: code->MyTest
 * @description: 测试
 * @author: xjj
 * @create: 2019-12-23 17:30
 **/
@Component
public class LoadRight {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RighrService righrService;

    @PostConstruct
    public void test(){
        List<String> strings = righrService.loadRight();
        redisUtil.set("jurisdiction",strings);//需要拦截的权限
        System.out.println("需要拦截的权限已缓存");
    }


}
