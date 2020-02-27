package com.superwork.apcosplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @program: code->AsyncConfig
 * @description: 异步定时任务线程
 * @author: xjj
 * @create: 2020-01-14 10:59
 **/
@Configuration
public class AsyncConfig {
    private int corePoolSize =10;
    private int maxPoolSize =200;
    private int queueCapacity =10;
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
