package com.superwork.apcosplatform.utils;

/**
 * @program: code->IDUtils
 * @description: ID
 * @author: xjj
 * @create: 2019-12-04 11:25
 **/
public class IDUtils {
    private static byte[] lock = new byte[0];
    // 位数，默认是8位
    private final static long w = 1000;

    public static String createID() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }

    public static String random6(){
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * 100000);
        }
        return String.valueOf(r);
    }


//    public static void main(String[] args) {
//        System.out.println(random6());
//    }




}
