package com.superwork;

import com.superwork.apcosplatform.aop.ConsumeLogQueue;
import com.superwork.apcosplatform.aop.LoadRight;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.superwork.**.mapper")
@ServletComponentScan
@EnableAsync
@EnableScheduling
public class SuperworkApplication extends SpringBootServletInitializer {

    @Autowired
    ConsumeLogQueue consumeLogQueue;

    @Autowired
    LoadRight loadRight;

    public static void main(String[] args) {
        SpringApplication.run(SuperworkApplication.class, args);
    }

}
