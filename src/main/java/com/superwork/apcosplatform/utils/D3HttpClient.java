package com.superwork.apcosplatform.utils;


import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.domain.PSysUser;
import org.apache.http.HttpEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



/**
 * @program: beone-platform
 * @description:
 * @author: xjj
 * @create: 2019-11-07 11:01
 **/
public class D3HttpClient {


    /**
     * @Description: TODO http请求
     * @Param: [url, obj]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String client_bcca(String url, JSONObject obj) throws Exception {

        System.out.println("url:" + url);
        System.out.println("obj:" + obj.toString());
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String respContent = null;
        StringEntity entity = new StringEntity(obj.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        CloseableHttpResponse resp = client.execute(post);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        System.out.println(respContent);
        return respContent;
    }


    /**
     * @Description: TODO 获取token
     * @Param: [url, service_id, sysUser]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String get3DToken(String url, String service_id, PSysUser sysUser) throws Exception {
        String function = "/otheAPI/getUserToken";
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("user_id", sysUser.getUserId());
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        System.out.println(object2);
        if(object2.get("status").toString().equals("401")){//不合法用户，重新注册！
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",service_id);
            jsonObject.put("user_id",sysUser.getUserId());
            jsonObject.put("user_name",sysUser.getUserAccount());
            result= registerUser(url, jsonObject);
            object2 = JSONObject.parseObject(result);
        }
        if (object2.get("status").toString().equals("200")) {
            String data = object2.get("data").toString();
            object2 = JSONObject.parseObject(data);
            String userToken = object2.get("userToken").toString();
            result = userToken;
        } else {
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }


    /**
     * @Description: TODO 获取获取原件列表
     * @Param: [url, obj]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String getYJData(String url, JSONObject obj) throws Exception {

        String function = "/otheAPI/getYJData";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (object2.get("status").equals(200)) {
            String data = object2.get("data").toString();
            result = data;
        } else {
            result = "";
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }


    /**
     * @Description: TODO 获取获取原件类型
     * @Param: [url, obj]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String getYJLXInfo(String url, JSONObject obj) throws Exception {

        String function = "/otheAPI/getYJLXInfo";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (object2.get("status").equals(200)) {
            String data = object2.get("data").toString();
            result = data;
        } else {
            result = "";
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }


    /**
     * @Description: TODO 绑定设备到元件
     * @Param: [url, obj]
     * @return: void
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static void bondDevToEle(String url, JSONObject obj) throws Exception {
        String function = "/otheAPI/bondDevToEle";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (!object2.get("status").equals(200)) {
            throw new Exception(object2.get("msg").toString());
        }

    }


    /**
     * @Description: TODO 解绑设备
     * @Param: [url, obj]
     * @return: void
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static void unbondDevFromEle(String url, JSONObject obj) throws Exception {

        String function = "/otheAPI/unbondDevFromEle";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (!object2.get("status").equals(200)) {
            throw new Exception(object2.get("msg").toString());
        }
    }


    /**
     * @Description: TODO 查询元件绑定关系
     * @Param: [url, obj]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String getBondInfo(String url, JSONObject obj) throws Exception {

        String function = "/otheAPI/getBondInfo";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (object2.get("status").equals(200)) {
            String data = object2.get("data").toString();
            result = data;
        } else {
            result = "";
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }


    /**
     * @Description: TODO 查询元件绑定关系
     * @Param: [url, obj]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String viewYJ(String url, JSONObject obj) throws Exception {
        String function = "/otheAPI/viewYJ";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (object2.get("status").equals(200)) {
            String data = object2.get("data").toString();
            result = data;
        } else {
            result = "";
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }

    /**
     * @Description: TODO 用户注册：返回userToken
     * @Param: [url, obj]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    public static String registerUser(String url, JSONObject obj) throws Exception {
        String function = "/otheAPI/regUser";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (object2.get("status").equals(200)) {
            String data = object2.get("data").toString();
            result = data;
        } else {
            result = "";
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }

    /**
     * 注册组织机构：返回机构ID
     */
    public static String registerOrg(String url, JSONObject obj) throws Exception {
        String function = "/otheAPI/registerOrg";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (object2.get("status").equals(200)) {
            String data = object2.get("data").toString();
            JSONObject jsonObject = JSONObject.parseObject(data);
            String org_id = jsonObject.get("org_id").toString();
            result = org_id;
        } else {
            result = "";
            throw new Exception(object2.get("msg").toString());
        }
        return result;
    }

    /**
     * @Description: TODO 新增一条方案
     * @Param: [url, obj]
     * @return: void
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    public static void otherSaveFA(String url, JSONObject obj) throws Exception {

        String function = "/otheAPI/otherSaveFA";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (!object2.get("status").equals(200)) {
            throw new Exception(object2.get("msg").toString());
        }
    }
    /**
     * @Description: TODO 删除一条方案
     * @Param: [url, obj]
     * @return: void
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    public static void otherDelSjfaOne(String url, JSONObject obj) throws Exception {
        String function = "/otheAPI/otherDelSjfaOne";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (!object2.get("status").equals(200)) {
            throw new Exception(object2.get("msg").toString());
        }
    }

    /**
     * @Description: TODO 修改组织机构信息
     * @Param: [url, obj]
     * @return: void
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    public static void updateOrg(String url, JSONObject obj) throws Exception {
        String function = "/otheAPI/updateOrg";
        String result = client_bcca(url + function, obj);
        JSONObject object2 = JSONObject.parseObject(result);
        if (!object2.get("status").equals(200)) {
            throw new Exception(object2.get("msg").toString());
        }
    }





//
//    public static void main(String[] args) throws Exception {
//        String url="http://113.204.9.70:9080/3dwebgl";
//        PSysUser sysUser = null;
//        String service_id ="vV40XS4pXCPo95wosb8";
//        String dToken = get3DToken(url,service_id,sysUser);
//        String yjlxInfo = getYJData(url, dToken);
//        JSONObject object = JSONObject.parseObject(yjlxInfo);
//        Object list = object.get("list");
//
//        System.out.println(list);
//
//        System.out.println(yjlxInfo);
//    }

}
