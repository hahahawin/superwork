package com.superwork.apcosplatform.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: code->GetWeather
 * @description: 天气
 * @author: xjj
 * @create: 2019-11-29 09:58
 **/
public class GetWeather {

    /**
     *
     * 获取实时天气1<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid  城市名
     *
     */
    //注：参数字符串，如果拼接在请求链接之后，需要对中文进行 URLEncode   字符集 UTF-8，转化方式在下面，直接传过去就好了
    public static Map<String, Object> getTodayWeather1(String Cityid)
            throws IOException, NullPointerException {
        // 连接和风天气的API
        String url1= "https://free-api.heweather.net/s6/weather/now?location="+Cityid+"&key=3c3fa198cacc4152b94b20def11b2455";

        URL url = new URL(url1);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        BufferedReader br=null;
        StringBuilder sb=null;
        try {
             br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
             sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            //截取[]转化为json格式
            datas = datas.replace(datas.substring(datas.indexOf(":")+1,datas.indexOf(":")+2),"");
            datas = datas.replace(datas.substring(datas.length()-2,datas.length()-1),"");
            JSONObject jsonData = JSONObject.parseObject(datas);
            JSONObject info = jsonData.getJSONObject("HeWeather6");
            JSONObject jsonData1 = JSONObject.parseObject(info.getString("basic").toString());
            JSONObject jsonData2 = JSONObject.parseObject(info.getString("update").toString());
            JSONObject jsonData3 = JSONObject.parseObject(info.getString("now").toString());
            map.put("location",jsonData1.getString("location").toString());
            map.put("parent_city",jsonData1.getString("parent_city").toString());
            map.put("admin_area",jsonData1.getString("admin_area").toString());
            map.put("cnty",jsonData1.getString("cnty").toString());

            String time = jsonData2.getString("loc").toString();

            String week = strToDate(time);

            map.put("week",week);
            map.put("time",jsonData2.getString("loc").toString());

            map.put("tmp",jsonData3.getString("tmp").toString());
            map.put("wind_dir",jsonData3.getString("wind_dir").toString());
            map.put("cond_txt",jsonData3.getString("cond_txt").toString());
            map.put("cond_code",jsonData3.getString("cond_code").toString());
            System.out.println(map);
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            //关闭流
                if( br!= null){
                    br.close();
                }

        }

        return map;

    }

    /**
     * 时间获得星期
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static String strToDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(strDate));
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(c.getTime());
        return week;
    }
    /**
     * 字符集转码
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String url) throws UnsupportedEncodingException {
        if(url == null) {
            return null;
        }

        final String reserved_char = ";/?:@=&";
        String ret = "";
        for(int i=0; i < url.length(); i++) {
            String cs = String.valueOf( url.charAt(i) );
            if(reserved_char.contains(cs)){
                ret += cs;
            }else{
                ret += URLEncoder.encode(cs, "utf-8");
            }
        }
        return ret.replace("+", "%20");
    }

    //以下用的国家气象局提供的天气预报接口，该接口也是免费的

    /**
     *
     * 获取实时天气2<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid
     *            城市编码
     */
    public static Map<String, Object> getTodayWeather2(String Cityid)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("http://www.weather.com.cn/data/cityinfo/" + Cityid
                + ".html");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.parseObject(datas);
            JSONObject info = jsonData.getJSONObject("weatherinfo");
            map.put("city", info.getString("city").toString());// 城市
            map.put("temp1", info.getString("temp1").toString());// 最高温度
            map.put("temp2", info.getString("temp2").toString());// 最低温度
            map.put("weather", info.getString("weather").toString());//天气
            map.put("ptime", info.getString("ptime").toString());// 发布时间
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }


    public static Map<String, String> getTodayWeatherByIp(String Cityip)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("https://www.tianqiapi.com/api/?version=v1&appid=25549233&appsecret=tl2EM16j&ip=" + Cityip);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, String> map = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            //{"cityid":"101040100","update_time":"2019-11-29 07:30:00","city":"\u91cd\u5e86","cityEn":"chongqing","country":"\u4e2d\u56fd","countryEn":"China",
            // "data":[{"day":"29\u65e5\uff08\u4eca\u5929\uff09","date":"2019-11-29","week":"\u661f\u671f\u4e94","wea":"\u9634\u8f6c\u5c0f\u96e8","wea_img":"yu","air":21,"humidity":92,"air_level":"\u4f18","air_tips":"\u7a7a\u6c14\u5f88\u597d\uff0c\u53ef\u4ee5\u5916\u51fa\u6d3b\u52a8\uff0c\u547c\u5438\u65b0\u9c9c\u7a7a\u6c14\uff0c\u62e5\u62b1\u5927\u81ea\u7136\uff01","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"tem1":"11\u2103","tem2":"10\u2103","tem":"10\u2103","win":["\u5357\u98ce","\u5357\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"29\u65e508\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e511\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e514\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e517\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e520\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e523\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e502\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e505\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u8f83\u5dee","desc":"\u6c14\u8c61\u6761\u4ef6\u8f83\u4e0d\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002\u3002"}]},{"day":"30\u65e5\uff08\u660e\u5929\uff09","date":"2019-11-30","week":"\u661f\u671f\u516d","wea":"\u5c0f\u96e8","wea_img":"yu","tem1":"10\u2103","tem2":"8\u2103","tem":"8\u2103","win":["\u5357\u98ce","\u4e1c\u5357\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"30\u65e508\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e511\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e514\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e517\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e520\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e523\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e502\u65f6","wea":"\u5c0f\u96e8","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e505\u65f6","wea":"\u5c0f\u96e8","tem":"8\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em><\/em><em><\/em><em>","level":null,"desc":"\u96e8\u5929\u5ba4\u5185\u8fd0\u52a8\u4e0b\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u4e0d\u6613\u5f15\u8d77\u8840\u7cd6\u6ce2\u52a8\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u826f","desc":"\u6c14\u8c61\u6761\u4ef6\u6709\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002"}]},{"day":"1\u65e5\uff08\u540e\u5929\uff09","date":"2019-12-01","week":"\u661f\u671f\u65e5","wea":"\u9634","wea_img":"yin","tem1":"10\u2103","tem2":"7\u2103","tem":"7\u2103","win":["\u4e1c\u5357\u98ce","\u4e1c\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"01\u65e508\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e511\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e514\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e517\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e520\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e523\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e502\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e505\u65f6","wea":"\u9634","tem":"7\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u8f83\u4e0d\u5b9c","desc":"\u8def\u9762\u6709\u79ef\u6c34\uff0c\u8f66\u5b50\u6613\u88ab\u6e85\u4e0a\u6ce5\u6c34\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u8f83\u5dee","desc":"\u6c14\u8c61\u6761\u4ef6\u8f83\u4e0d\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002\u3002"}]},{"day":"2\u65e5\uff08\u5468\u4e00\uff09","date":"2019-12-02","week":"\u661f\u671f\u4e00","wea":"\u591a\u4e91","wea_img":"yun","tem1":"11\u2103","tem2":"8\u2103","tem":"8\u2103","win":["\u4e1c\u98ce","\u4e1c\u5317\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"02\u65e508\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e514\u65f6","wea":"\u591a\u4e91","tem":"11\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e520\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"03\u65e502\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u9002\u5b9c","desc":"\u5929\u6c14\u8f83\u597d\uff0c\u9002\u5408\u64e6\u6d17\u6c7d\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u4e2d","desc":"\u6613\u611f\u4eba\u7fa4\u5e94\u9002\u5f53\u51cf\u5c11\u5ba4\u5916\u6d3b\u52a8\u3002"}]},{"day":"3\u65e5\uff08\u5468\u4e8c\uff09","date":"2019-12-03","week":"\u661f\u671f\u4e8c","wea":"\u9634\u8f6c\u591a\u4e91","wea_img":"yun","tem1":"11\u2103","tem2":"9\u2103","tem":"9\u2103","win":["\u4e1c\u5317\u98ce","\u5317\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"03\u65e508\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"03\u65e514\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"03\u65e520\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"04\u65e502\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u8f83\u9002\u5b9c","desc":"\u65e0\u96e8\u4e14\u98ce\u529b\u8f83\u5c0f\uff0c\u6613\u4fdd\u6301\u6e05\u6d01\u5ea6\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u8f83\u5dee","desc":"\u6c14\u8c61\u6761\u4ef6\u8f83\u4e0d\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002\u3002"}]},{"day":"4\u65e5\uff08\u5468\u4e09\uff09","date":"2019-12-04","week":"\u661f\u671f\u4e09","wea":"\u5c0f\u96e8","wea_img":"yu","tem1":"11\u2103","tem2":"8\u2103","tem":"8\u2103","win":["\u5317\u98ce","\u5357\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"04\u65e508\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"04\u65e514\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"04\u65e520\u65f6","wea":"\u5c0f\u96e8","tem":"9\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"05\u65e502\u65f6","wea":"\u5c0f\u96e8","tem":"8\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em><\/em><em><\/em><em>","level":null,"desc":"\u96e8\u5929\u5ba4\u5185\u8fd0\u52a8\u4e0b\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u4e0d\u6613\u5f15\u8d77\u8840\u7cd6\u6ce2\u52a8\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u826f","desc":"\u6c14\u8c61\u6761\u4ef6\u6709\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002"}]},{"day":"5\u65e5\uff08\u5468\u56db\uff09","date":"2019-12-05","week":"\u661f\u671f\u56db","wea":"\u5c0f\u96e8\u8f6c\u591a\u4e91","wea_img":"yun","tem1":"12\u2103","tem2":"6\u2103","tem":"7\u2103","win":["\u4e1c\u5317\u98ce","\u5317\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"05\u65e508\u65f6","wea":"\u5c0f\u96e8","tem":"8\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"05\u65e514\u65f6","wea":"\u5c0f\u96e8","tem":"11\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"05\u65e520\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"06\u65e502\u65f6","wea":"\u6674","tem":"7\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em><\/em><em><\/em><em>","level":null,"desc":"\u96e8\u5929\u5ba4\u5185\u8fd0\u52a8\u4e0b\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u4e0d\u6613\u5f15\u8d77\u8840\u7cd6\u6ce2\u52a8\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u826f","desc":"\u6c14\u8c61\u6761\u4ef6\u6709\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002"}]}]}
            System.out.println(datas);
            JSONObject jsonData = JSONObject.parseObject(datas);
            map.put("city", jsonData.getString("city"));// 城市
            map.put("update_time", jsonData.getString("update_time"));//更新时间
            JSONArray data = jsonData.getJSONArray("data");
            JSONObject info = data.getJSONObject(0);
            map.put("date", info.getString("date").toString());// 日期
            map.put("week", info.getString("week").toString());// 星期
            map.put("wea", info.getString("wea").toString());// 天气情况
            map.put("tem", info.getString("tem").toString());//当前温度
            map.put("air", info.getString("air").toString());// 空气质量
            map.put("air_level", info.getString("air_level").toString());// 空气等级
            map.put("air_tips", info.getString("air_tips").toString());// 空气质量描述
            map.put("win_speed", info.getString("win_speed").toString());// 风速等级
            JSONArray win = info.getJSONArray("win");
            map.put("win", win.get(1).toString());//风向
            map.put("humidity", info.getString("humidity").toString());// 湿度
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }

    public static Map<String, String> getTodayWeatherByName(String city)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("https://www.tianqiapi.com/api/?version=v1&appid=25549233&appsecret=tl2EM16j&city=" + city);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, String> map = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            //{"cityid":"101040100","update_time":"2019-11-29 07:30:00","city":"\u91cd\u5e86","cityEn":"chongqing","country":"\u4e2d\u56fd","countryEn":"China",
            // "data":[{"day":"29\u65e5\uff08\u4eca\u5929\uff09","date":"2019-11-29","week":"\u661f\u671f\u4e94","wea":"\u9634\u8f6c\u5c0f\u96e8","wea_img":"yu","air":21,"humidity":92,"air_level":"\u4f18","air_tips":"\u7a7a\u6c14\u5f88\u597d\uff0c\u53ef\u4ee5\u5916\u51fa\u6d3b\u52a8\uff0c\u547c\u5438\u65b0\u9c9c\u7a7a\u6c14\uff0c\u62e5\u62b1\u5927\u81ea\u7136\uff01","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"tem1":"11\u2103","tem2":"10\u2103","tem":"10\u2103","win":["\u5357\u98ce","\u5357\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"29\u65e508\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e511\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e514\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e517\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e520\u65f6","wea":"\u9634","tem":"11\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"29\u65e523\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e502\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e505\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u8f83\u5dee","desc":"\u6c14\u8c61\u6761\u4ef6\u8f83\u4e0d\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002\u3002"}]},{"day":"30\u65e5\uff08\u660e\u5929\uff09","date":"2019-11-30","week":"\u661f\u671f\u516d","wea":"\u5c0f\u96e8","wea_img":"yu","tem1":"10\u2103","tem2":"8\u2103","tem":"8\u2103","win":["\u5357\u98ce","\u4e1c\u5357\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"30\u65e508\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e511\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e514\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e517\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e520\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"30\u65e523\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e502\u65f6","wea":"\u5c0f\u96e8","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e505\u65f6","wea":"\u5c0f\u96e8","tem":"8\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em><\/em><em><\/em><em>","level":null,"desc":"\u96e8\u5929\u5ba4\u5185\u8fd0\u52a8\u4e0b\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u4e0d\u6613\u5f15\u8d77\u8840\u7cd6\u6ce2\u52a8\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u826f","desc":"\u6c14\u8c61\u6761\u4ef6\u6709\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002"}]},{"day":"1\u65e5\uff08\u540e\u5929\uff09","date":"2019-12-01","week":"\u661f\u671f\u65e5","wea":"\u9634","wea_img":"yin","tem1":"10\u2103","tem2":"7\u2103","tem":"7\u2103","win":["\u4e1c\u5357\u98ce","\u4e1c\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"01\u65e508\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e511\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e514\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e517\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e520\u65f6","wea":"\u9634","tem":"9\u2103","win":"\u4e1c\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"01\u65e523\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e502\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e505\u65f6","wea":"\u9634","tem":"7\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u8f83\u4e0d\u5b9c","desc":"\u8def\u9762\u6709\u79ef\u6c34\uff0c\u8f66\u5b50\u6613\u88ab\u6e85\u4e0a\u6ce5\u6c34\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u8f83\u5dee","desc":"\u6c14\u8c61\u6761\u4ef6\u8f83\u4e0d\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002\u3002"}]},{"day":"2\u65e5\uff08\u5468\u4e00\uff09","date":"2019-12-02","week":"\u661f\u671f\u4e00","wea":"\u591a\u4e91","wea_img":"yun","tem1":"11\u2103","tem2":"8\u2103","tem":"8\u2103","win":["\u4e1c\u98ce","\u4e1c\u5317\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"02\u65e508\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e514\u65f6","wea":"\u591a\u4e91","tem":"11\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"02\u65e520\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u4e1c\u98ce","win_speed":"<3\u7ea7"},{"day":"03\u65e502\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u9002\u5b9c","desc":"\u5929\u6c14\u8f83\u597d\uff0c\u9002\u5408\u64e6\u6d17\u6c7d\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u4e2d","desc":"\u6613\u611f\u4eba\u7fa4\u5e94\u9002\u5f53\u51cf\u5c11\u5ba4\u5916\u6d3b\u52a8\u3002"}]},{"day":"3\u65e5\uff08\u5468\u4e8c\uff09","date":"2019-12-03","week":"\u661f\u671f\u4e8c","wea":"\u9634\u8f6c\u591a\u4e91","wea_img":"yun","tem1":"11\u2103","tem2":"9\u2103","tem":"9\u2103","win":["\u4e1c\u5317\u98ce","\u5317\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"03\u65e508\u65f6","wea":"\u591a\u4e91","tem":"8\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"03\u65e514\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"03\u65e520\u65f6","wea":"\u9634","tem":"10\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"04\u65e502\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em>","level":null,"desc":"\u5929\u6c14\u8f83\u8212\u9002\uff0c\u51cf\u80a5\u6b63\u5f53\u65f6\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u597d\uff0c\u8840\u7cd6\u4e0d\u6613\u6ce2\u52a8\uff0c\u53ef\u9002\u65f6\u8fdb\u884c\u6237\u5916\u953b\u70bc\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u8f83\u9002\u5b9c","desc":"\u65e0\u96e8\u4e14\u98ce\u529b\u8f83\u5c0f\uff0c\u6613\u4fdd\u6301\u6e05\u6d01\u5ea6\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u8f83\u5dee","desc":"\u6c14\u8c61\u6761\u4ef6\u8f83\u4e0d\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002\u3002"}]},{"day":"4\u65e5\uff08\u5468\u4e09\uff09","date":"2019-12-04","week":"\u661f\u671f\u4e09","wea":"\u5c0f\u96e8","wea_img":"yu","tem1":"11\u2103","tem2":"8\u2103","tem":"8\u2103","win":["\u5317\u98ce","\u5357\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"04\u65e508\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"04\u65e514\u65f6","wea":"\u5c0f\u96e8","tem":"10\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"04\u65e520\u65f6","wea":"\u5c0f\u96e8","tem":"9\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"05\u65e502\u65f6","wea":"\u5c0f\u96e8","tem":"8\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em><\/em><em><\/em><em>","level":null,"desc":"\u96e8\u5929\u5ba4\u5185\u8fd0\u52a8\u4e0b\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u4e0d\u6613\u5f15\u8d77\u8840\u7cd6\u6ce2\u52a8\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u826f","desc":"\u6c14\u8c61\u6761\u4ef6\u6709\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002"}]},{"day":"5\u65e5\uff08\u5468\u56db\uff09","date":"2019-12-05","week":"\u661f\u671f\u56db","wea":"\u5c0f\u96e8\u8f6c\u591a\u4e91","wea_img":"yun","tem1":"12\u2103","tem2":"6\u2103","tem":"7\u2103","win":["\u4e1c\u5317\u98ce","\u5317\u98ce"],"win_speed":"<3\u7ea7","hours":[{"day":"05\u65e508\u65f6","wea":"\u5c0f\u96e8","tem":"8\u2103","win":"\u5357\u98ce","win_speed":"<3\u7ea7"},{"day":"05\u65e514\u65f6","wea":"\u5c0f\u96e8","tem":"11\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"05\u65e520\u65f6","wea":"\u591a\u4e91","tem":"9\u2103","win":"\u4e1c\u5317\u98ce","win_speed":"<3\u7ea7"},{"day":"06\u65e502\u65f6","wea":"\u6674","tem":"7\u2103","win":"\u5317\u98ce","win_speed":"<3\u7ea7"}],"index":[{"title":"\u7d2b\u5916\u7ebf\u6307\u6570","level":"\u6700\u5f31","desc":"\u8f90\u5c04\u5f31\uff0c\u6d82\u64e6SPF8-12\u9632\u6652\u62a4\u80a4\u54c1\u3002"},{"title":"<\/em><em><\/em><em><\/em><em>","level":null,"desc":"\u96e8\u5929\u5ba4\u5185\u8fd0\u52a8\u4e0b\u3002"},{"title":"\u5065\u81fb\u00b7\u8840\u7cd6\u6307\u6570","level":"\u4e0d\u6613\u6ce2\u52a8","desc":"\u5929\u6c14\u6761\u4ef6\u4e0d\u6613\u5f15\u8d77\u8840\u7cd6\u6ce2\u52a8\u3002"},{"title":"\u7a7f\u8863\u6307\u6570","level":"\u8f83\u51b7","desc":"\u5efa\u8bae\u7740\u539a\u5916\u5957\u52a0\u6bdb\u8863\u7b49\u670d\u88c5\u3002"},{"title":"\u6d17\u8f66\u6307\u6570","level":"\u4e0d\u5b9c","desc":"\u6709\u96e8\uff0c\u96e8\u6c34\u548c\u6ce5\u6c34\u4f1a\u5f04\u810f\u7231\u8f66\u3002"},{"title":"\u7a7a\u6c14\u6c61\u67d3\u6269\u6563\u6307\u6570","level":"\u826f","desc":"\u6c14\u8c61\u6761\u4ef6\u6709\u5229\u4e8e\u7a7a\u6c14\u6c61\u67d3\u7269\u6269\u6563\u3002"}]}]}
            System.out.println(datas);
            JSONObject jsonData = JSONObject.parseObject(datas);
            map.put("city", jsonData.getString("city"));// 城市
            map.put("update_time", jsonData.getString("update_time"));//更新时间
            JSONArray data = jsonData.getJSONArray("data");
            JSONObject info = data.getJSONObject(0);
            map.put("date", info.getString("date").toString());// 日期
            map.put("week", info.getString("week").toString());// 星期
            map.put("wea", info.getString("wea").toString());// 天气情况
            map.put("tem", info.getString("tem").toString());//当前温度
            map.put("air", info.getString("air").toString());// 空气质量
            map.put("air_level", info.getString("air_level").toString());// 空气等级
            map.put("air_tips", info.getString("air_tips").toString());// 空气质量描述
            map.put("win_speed", info.getString("win_speed").toString());// 风速等级
            JSONArray win = info.getJSONArray("win");
            map.put("win", win.get(1).toString());//风向
            map.put("humidity", info.getString("humidity").toString());// 湿度
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }




    public static void main(String[] args) {
        try {

            String ip = "113.204.9.70";
            Map<String, String> todayWeatherByIp = getTodayWeatherByIp(ip);
            System.out.println(todayWeatherByIp);
            //测试获取实时天气1(包含风况，湿度)
//            Map<String, Object> map = getTodayWeather1("北京");
//            System.out.println(map);
//
//
//            //测试获取实时天气2(包含天气，温度范围)
//            Map<String, Object> map2 = getTodayWeather2("101110908"); //这块填的是城市编码
//            System.out.println(map2.get("city") + "\t" + map2.get("temp1")
//                    + "\t" + map2.get("temp2") + "\t" + map2.get("weather")
//                    + "\t" + map2.get("ptime"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
