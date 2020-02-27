package com.superwork.apcosplatform.utils;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.service.Impl.SmhSettingServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.superwork.apcosplatform.common.Constants.service_key;

/**
 * @program: code->BCCAClient
 * @description:
 * @author: xjj
 * @create: 2019-11-14 15:57
 **/
public class BCCAClient {

    /**
     * http请求
     */
    public static String client_bcca(String url, String account, JSONObject obj) throws Exception {
        System.out.println("url:" + url);
        System.out.println("account:" + account);
        System.out.println("obj:" + obj.toString());
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String respContent = null;
        StringEntity entity = new StringEntity(obj.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.addHeader("owner", account);

        CloseableHttpResponse execute = client.execute(post);

        if (execute.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity1 = execute.getEntity();
            respContent = EntityUtils.toString(entity1, "UTF-8");
            if("account invalid".equals(respContent.trim())){
                throw new Exception("账号无效！");
            }
        }
        return respContent;
    }

    public static String registerCallBackUrl(String url,String token,String service_id,String call_back_url,String account) throws Exception {
        String methodUrl = "/api/thirdSystemSubscription/registerCallBackUrl";
        JSONObject obj = new JSONObject();
        Long ytdate1 = getLdate(-1000000);
        obj.put("token", token);
        obj.put("service_id", service_id);
        obj.put("processTime", ytdate1);
        obj.put("call_back_url", call_back_url);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }


    public static String getNewToken(String dgjUrl,String url, String service_id, String service_key, String call_back, String account) throws Exception {
        String token = HTTPclient.getNewAccessToken(dgjUrl, service_id, service_key);
        try {
            String result = registerCallBackUrl(url, token, service_id, call_back, account);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(!jsonObject.get("resultCode").equals("0000")){
                System.out.println("注册回调地址失败！"+jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

    /**
     * 获取token
     */
//    public static String getToken(String url, String service_id, String service_key, String call_back, String account) throws Exception {
//        JSONObject obj = new JSONObject();
//        String tokenUrl = "/api/token/getToken";
//        obj.put("service_id", service_id);
//        obj.put("call_back_url", call_back);
//        obj.put("service_key", service_key);
//        String token = client_bcca(url + tokenUrl, account, obj);
//        System.out.println(token);
//        if (token.trim().equals("account invalid")) {
//            throw new Exception("账户错误！");
//        }
//        System.out.println("token:" + token);
//        JSONObject jsonObject = JSONObject.parseObject(token);
//        if (jsonObject.get("resultCode").equals("0200")) {
//            JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("resultContent").toString());
//            token = jsonObject2.get("token").toString();
//        } else {
//            throw new Exception(jsonObject.get("resultDesc").toString());
//        }
//        return token;
//    }

    /**
     * 新增计划
     */
    public static String addExcutionPlan(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlan/addExcutionPlan";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }
    /**
     * 编辑执行计划
     */
    public static String updateExcutionPlanById(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlan/updateExcutionPlanById";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }
    /**
     * 删除执行计划
     */
    public static String deleteExcutionPlanById(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlan/deleteExcutionPlanById";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 新增执行计划进程
     */
    public static String addExcutionPlanProcess(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlanProcess/addExcutionPlanProcess";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 修改执行计划进程
     */
    public static String updateExcutionPlanProcess(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlanProcess/updateExcutionPlanProcess";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 删除执行计划进程
     */
    public static String deleteExcutionPlanProcess(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlanProcess/deleteExcutionPlanProcess";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }
    /**
     * 查询执行计划
     */
    public static String queryExcutionPlanByCondition(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/excutionPlan/queryExcutionPlanByCondition";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 添加模式
     */
    public static String addMode(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/mode/addMode";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 删除模式
     */
    public static String deletMode(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/mode/deletMode";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 修改模式
     */
    public static String updateMode(String url,JSONObject obj,String account ) throws Exception {
        String methodUrl = "/api/mode/updateMode";
        Long ytdate1 = getLdate(-1000000);
        obj.put("processTime",ytdate1);
        String result= client_bcca(url + methodUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 账号订阅
     */
    public static String accountSubscrip(String url, String service_id, String token, String account) throws Exception {
        String sbUrl = "/api/thirdSystemSubscription/accountSubscrip";
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("processTime", new Date().getTime());
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 取消订阅
     */
    public static String removeSubscripRelation(String url, String service_id, String token, String account) throws Exception {
        String sbUrl = "/api/thirdSystemSubscription/removeSubscripRelation";
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("processTime", new Date().getTime());
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 获取设备列表
     */
    public static String getDevice(String url, String service_id, String token, String account) throws Exception {
        String sbUrl = "/api/device/getDevice";
        Long ytdate = new Date().getTime();
        Long ytdate1 = getLdate(-1000000);
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("processTime", ytdate);
        obj.put("lastCreatTime", ytdate1);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 控制设备
     */
    public static String sendDeviceCmd(String url,JSONObject obj, String account) throws Exception {
        String sbUrl = "/api/device/sendDeviceCmd";
        Long ytdate = new Date().getTime();
        obj.put("processTime", ytdate);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }


    /**
     * 控制模式
     */
    public static String sendMode(String url,JSONObject obj, String account) throws Exception {
        String sbUrl = "/api/mode/sendMode";
        Long ytdate = new Date().getTime();
        obj.put("processTime", ytdate);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 控制模式
     */
    public static String queryExcutionPlanProcess(String url,JSONObject obj, String account) throws Exception {
        String sbUrl = "/api/excutionPlanProcess/queryExcutionPlanProcess";
        Long ytdate = new Date().getTime();
        obj.put("processTime", ytdate);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 获取模板list
     */
    public static String getTemplateList(String url, String service_id, String token, String account, String product_code) throws Exception {
        String sbkzUrl = "/api/template/getTemplateList";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", service_id);
        obj.put("product_code", product_code);
        String result = client_bcca(url + sbkzUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 获取模板内容
     */
    public static String getTempLate(String url, String service_id, String token, String account, String template_id) throws Exception {
        String sbkzUrl = "/api/template/getTemplate";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", service_id);
        obj.put("template_id", template_id);
        String result = client_bcca(url + sbkzUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 获取设备类型
     */
    public static String getSblxList(String url, String service_id, String token, String account) throws Exception {
        String sbkzUrl = "/api/prodcut/getProdcutType";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", service_id);
        obj.put("processTime", new Date().getTime());
        String result = client_bcca(url + sbkzUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 获取设备列表
     */
    public static String getDeviceList(String url, String service_id, String token, String account) throws Exception {
        String sbUrl = "/api/device/getDevice";
        Long ytdate = new Date().getTime();
        Long ytdate1 = getLdate(-1000000);
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("processTime", ytdate);
        obj.put("lastCreatTime", ytdate1);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }

    /**
     * 查询网关在线
     */
    public static String queryGateWayStatusByMac(String url, String service_id, String token, String account,String device_mac) throws Exception {
        String sbUrl = "/api/device/queryGateWayStatusByMac";
        Long ytdate = new Date().getTime();
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("processTime", ytdate);
        obj.put("device_mac", device_mac);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }



    /**
     * 查询设备
     */
    public static String getDevicesByAccount(String url, String service_id, String token, String account) throws Exception {
        String sbUrl = "/api/device/getDevicesByAccount";
        Long ytdate = new Date().getTime();
        Long ytdate1 = getLdate(-1000000);
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("account", account);
//        obj.put("device_mac", deviceMac);
        obj.put("processTime", ytdate);
        obj.put("lastCreatTime", ytdate1);
        String result = client_bcca(url + sbUrl, account, obj);
        System.out.println("result:" + result);
        return result;
    }


    //获取系统时间（long类型）
    public static Long getLdate(Integer num) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, +num);//1分钟前
        String ytdate = dateFormat.format(c.getTime());
        return dateFormat.parse(ytdate).getTime();
    }





}
