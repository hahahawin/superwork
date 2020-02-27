package com.superwork.apcosplatform.aop;


import com.superwork.apcosplatform.domain.PSysLog;
import com.superwork.apcosplatform.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费队列
 */
@Component
public class ConsumeLogQueue {

    private static final Logger logger = LoggerFactory.getLogger(ConsumeLogQueue.class);
    @Autowired
    SysLogService sysLogService;

    @PostConstruct
    public void startThread() {
        ExecutorService e = Executors.newFixedThreadPool(2);// 两个大小的固定线程池
        e.submit(new PollLog(sysLogService));
        e.submit(new PollLog(sysLogService));
    }

    class PollLog implements Runnable {
        SysLogService sysLogService;

        public PollLog(SysLogService sysLogService) {
            this.sysLogService = sysLogService;
        }

        @Override
        public void run(){
            while (true) {
                try {
                    PSysLog sysLogInfo = LogQueue.getMailQueue().consume();
                    if (sysLogInfo != null) {
                        logger.info("剩余日志总数:{}",LogQueue.getMailQueue().size());
                        //可以设置延时 以及重复校验等等操作
                        sysLogService.save(sysLogInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @PreDestroy
    public void stopThread() {
        logger.info("destroy");
    }
}

