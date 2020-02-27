package com.superwork.apcosplatform.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JxscHttp {

    //获取连接数据
    public static String httpRequestPostMethod(StringBuilder sb, String path, String params) {
        HttpURLConnection connection = null;
        BufferedReader br = null;
        try {
            URL url = new URL(path); // 把字符串转换为URL请求地址
            connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.setDoOutput(true);// 使用 URL 连接进行输出
            connection.setDoInput(true);// 使用 URL 连接进行输入
            connection.setUseCaches(false);// 忽略缓存
            connection.setRequestMethod("POST");// 设置URL请求方法
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.connect();// 连接会话
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String content = "$data=" + params;
            System.out.println("content:"+content);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 获取输入流
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            out.flush();
            out.close();
            String line = null;

            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }

            br.close();// 关闭流
            connection.disconnect();// 断开连接
            return sb.toString();
        } catch (Exception e) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
            return null;
        }
    }
}
