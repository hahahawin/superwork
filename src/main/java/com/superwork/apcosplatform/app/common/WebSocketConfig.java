package com.superwork.apcosplatform.app.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {

//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }

    @Bean
    public WebSocketEndpointConfigure newConfigure() {
        return new WebSocketEndpointConfigure();
    }

}
