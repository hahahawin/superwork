package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PD3orgZc;
import com.superwork.apcosplatform.domain.PSbgl;
import com.superwork.apcosplatform.domain.PSmhSetting;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.mapper.PSbglMapper;
import com.superwork.apcosplatform.mapper.PSmhSettingMapper;
import com.superwork.apcosplatform.mapper.PSysLogMapper;
import com.superwork.apcosplatform.service.DataService;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author Jianjian Xu
 * @create: 2019/11/6 12:39
 * @description:
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    PSysLogMapper pSysLogMapper;
    @Autowired
    PSmhSettingMapper pSmhSettingMapper;
    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    RedisUtil redisUtil;

    @Value("${cd_api_url}")
    private String cd_api_url;//成都平台
    @Value("${back_url}")
    private String back_url;//回掉地址
    @Value("${kf_api_url}")
    private String kf_api_url;//开发者平台api
    @Value("${dgj_api_url}")
    private String dgj_url;


    @Override
    public Map<String, Object> getRegisterOnWeek() {

        List<Map<String, String>> registerOnWeek = pSysLogMapper.getRegisterOnWeek();
        Map<String, Object> map =new HashMap<>();

        ArrayList<String> xlist = new ArrayList<>();
        ArrayList<String> ylist = new ArrayList<>();
        for (Map<String, String> stringObjectMap : registerOnWeek) {
            xlist.add(stringObjectMap.get("DATA"));
            ylist.add(stringObjectMap.get("COUNTS"));
        }
        map.put("xlist",xlist);
        map.put("ylist",ylist);

        return map;
    }
    @Override
    public Map<String, Object> getLoginOnWeek() {
        List<Map<String, String>> loginOnWeek = pSysLogMapper.getLoginOnWeek();
        Map<String, Object> map =new HashMap<>();
        ArrayList<String> xlist = new ArrayList<>();
        ArrayList<String> w_ylist = new ArrayList<>();
        ArrayList<String> a_ylist = new ArrayList<>();

        for (Map<String, String> stringStringMap : loginOnWeek) {
            a_ylist.add(stringStringMap.get("A_COUNT"));
            w_ylist.add(stringStringMap.get("W_COUNT"));
            xlist.add(stringStringMap.get("DATA"));
        }
        map.put("xlist",xlist);
        map.put("a_ylist",a_ylist);
        map.put("w_ylist",w_ylist);
        return map;
    }

    @Override
    public  Map<String, Object> getProductStatus() throws Exception {
        PSysUser user = ComonUtils.getUser();
        Map<String, Object> map = new HashMap<>();
        ArrayList<PSbgl> list1 = new ArrayList<>();
        ArrayList<PSbgl> list2 = new ArrayList<>();
        PSbgl pSbgl = new PSbgl();
        pSbgl.setCreatorId(user.getUserId());
        List<PSbgl> pSbgls = pSbglMapper.listMySb(pSbgl);
        for (PSbgl sbgl : pSbgls) {
            Boolean prosTatus = getProsTatus(sbgl.getDeviceMac(), sbgl.getAccount());
            if(prosTatus){
                list1.add(sbgl);
            }else{
                list2.add(sbgl);
            }
        }
        map.put("online",list1);
        map.put("unline",list2);
        return map;


    }



    public Boolean getProsTatus(String mac,String account) throws Exception {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息");
        }
        String serviceId = bccaServiceInfo.getServiceId();
        String serviceKey = bccaServiceInfo.getServiceKey();
        String token = "";

        Object o = redisUtil.get(serviceId);
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId,token);
        } else {
            token = o.toString();
        }
        String result =  BCCAClient.queryGateWayStatusByMac(cd_api_url,serviceId,token, account,mac);
        if(StringUtils.isEmpty(result)){
            return false;
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId,token);
            result = BCCAClient.getDevicesByAccount(cd_api_url, serviceId, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (jsonObject.get("resultCode").equals("0000")) {
            JSONObject resultContent = jsonObject.getJSONObject("resultContent");

            String code = resultContent.getString("code");
            if("0".equals(code)){
                return true;
            }else{
                return false;
            }

        }
        return false;

    }

//    public JSONArray getProByAccount(String account) throws Exception {
//        JSONArray array = new JSONArray();
//        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
//        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
//            throw new Exception("请先完善服务信息");
//        }
//        String serviceId = bccaServiceInfo.getServiceId();
//        String serviceKey = bccaServiceInfo.getServiceKey();
//        String token = "";
//
//        Object o = redisUtil.get(serviceId);
//        if (o == null) {
//            token = BCCAClient.getNewToken(dgj_url, cd_api_url, serviceId, serviceKey, back_url, account);
//        } else {
//            token = o.toString();
//        }
//        String result = BCCAClient.getDevicesByAccount(cd_api_url, serviceId, token, account);
//        if (result == null) {
//            throw new Exception("无返回值！");
//        }
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        if (jsonObject.get("resultCode").equals("0101")) {
//            token = BCCAClient.getNewToken(dgj_url, cd_api_url, serviceId, serviceKey, back_url, account);
//            result = BCCAClient.getDevicesByAccount(cd_api_url, serviceId, token, account);
//            jsonObject = JSONObject.parseObject(result);
//        }
//        if (jsonObject.get("resultCode").equals("0000")) {
//            JSONObject resultContent = jsonObject.getJSONObject("resultContent");
//            JSONArray deviceList = resultContent.getJSONArray("deviceList");
//           return deviceList;
//        }
//        return array;
//
//
//    }





}
