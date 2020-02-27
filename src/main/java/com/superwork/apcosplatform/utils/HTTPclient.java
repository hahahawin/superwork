package com.superwork.apcosplatform.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Jianjian Xu
 * @create: 2019/10/24 15:52
 * @description:
 */
public class HTTPclient {



    /**
     * @Description: TODO 获取大管家token(新版本)
     * @Param: [dgj_url, appid, appkey]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/18 9:30
     */
    public static String getNewAccessToken(String dgj_url, String appid, String appkey) throws Exception {
        String method = "/butler/getAccessToken";
        JSONObject obj = new JSONObject();
        obj.put("service_id",appid);
        obj.put("service_key",appkey);
        String token ="";
        String line = httpRequestPostMethod(dgj_url+method, obj);
        JSONObject jsonObject = JSONObject.parseObject(line);
        if(jsonObject.get("code").toString().equals("200")){
            String data = jsonObject.get("data").toString();
            jsonObject = JSONObject.parseObject(data);
            token = jsonObject.get("token").toString();
        }else{
            throw new Exception(jsonObject.get("msg").toString());
        }
        return token;
    }

//    public static void main(String[] args) throws Exception {
//        String url = "http://47.99.127.16:20083";
//        String service_id = "nKM9PI4fYCtw2c2T5z2";
//        String appkey = "0f4af8ae252848c3a667c1ae2881ad19";
//
//        String token = getNewAccessToken(url, service_id, appkey);
//        JSONObject obj = new JSONObject();
//        obj.put("token", token);
//        obj.put("service_id", service_id);
//        obj.put("product_code", "00006b01");
//        String method = "/thirdInterface/findPropertyByProCode";
//        String resultContent = httpRequestPostMethod(url + method, obj);
//        JSONObject jsonObject = JSONObject.parseObject(resultContent);
//
////        System.out.println(resultContent);
//        String data = jsonObject.get("data").toString();
//        JSONArray objects = JSONArray.parseArray(data);
//        MyJsonUtils.convert(objects);
//        System.out.println(objects);
////        List<PWebPropertyInfo> pWebPropertyInfos = JSONArray.parseArray(jsonObject.get("data").toString(), PWebPropertyInfo.class);
////        System.out.println(pWebPropertyInfos);
////        System.out.println(resultContent);
//
//    }





    /**
     * @Description //TODO 获取大管家token
     * @author xjj
     * @date 2019/10/30
     * @param  * @param dgj_url
     * @param appid
     * @param appkey
     * @return java.lang.String
     */
    //获取用户accessToken 第一步
    public static String getAccessToken(String dgj_url, String appid, String appkey) throws Exception{

        String ytdate = getDate(0);

        JSONObject obj = new JSONObject();
        obj.put("appid", appid);
        obj.put("appkey", appkey);
        obj.put("appcode", "V0001");
        obj.put("acctoken", "");
        obj.put("processTime",ytdate);
        obj.put("serviceContent", null);
//		String result = SmhSettingServiceImpl.client_bcca2(path, null, obj);
        StringBuilder sb = new StringBuilder();
        String line = httpRequestPostMethod(sb, dgj_url, obj.toString());
        System.out.println("line:"+line);
        return line;
    }

    //获取系统时间
    public static String getDate(Integer num){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c= Calendar.getInstance();
        c.add(Calendar.MINUTE, +num);//1分钟前
        String ytdate = dateFormat.format(c.getTime());
        return ytdate;
    }



    /**
     * @Description //TODO 连接大管家api
     * @author xjj
     * @date 2019/10/30
     * @param  * @param sb
     * @param path
     * @param params
     * @return java.lang.String
     */
    //获取连接数据
    public static String httpRequestPostMethod(StringBuilder sb, String path, String params) {
        System.out.println("请求地址："+path);
        System.out.println("参数："+params);
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
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.connect();// 连接会话
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            String content = "$reqdata=" + params;
            System.out.println("content:"+content);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 获取输入流
            br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
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
           e.printStackTrace();
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

    /**
     * http请求(新版)
     */
    public static String httpRequestPostMethod(String url,JSONObject obj) throws Exception {
        System.out.println("url:" + url);
        System.out.println("obj:" + obj.toString());
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String respContent = null;
        StringEntity entity = new StringEntity(obj.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        if (execute.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity1 = execute.getEntity();
            respContent = EntityUtils.toString(entity1, "UTF-8");
        }
        System.out.println("大管家返回结果："+respContent);
        return respContent;
    }





}
