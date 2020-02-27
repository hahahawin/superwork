package com.superwork.apcosplatform.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class MobileDevice {
    public static boolean isMobileDevice(HttpServletRequest request) {
        // return userAgent.matches(".*Android.*") ||
        // userAgent.matches(".*iPhone.*") || userAgent.matches(".*iPad.*");
        String ua = request.getParameter("ua");


        if (StringUtils.hasText(ua)) {
            return "ios".equals(ua) || "android".equals(ua);
        } else {
            String userAgent = request.getHeader("User-Agent");
            return userAgent.matches(".*Android.*") || userAgent.matches(".*iPhone.*") || userAgent.matches(".*iPad.*");
        }
    }
}