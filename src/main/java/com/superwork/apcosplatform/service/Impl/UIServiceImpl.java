package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PD3orgZcMapper;
import com.superwork.apcosplatform.mapper.PSmhSettingMapper;
import com.superwork.apcosplatform.service.AsyncTask;
import com.superwork.apcosplatform.service.UIService;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.mapper.PSbglMapper;
import com.superwork.apcosplatform.service.SmhSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * @program: code->UIServiceImpl
 * @description: 设备控制的接口（手机端和WEB都可以访问）
 * @author: xjj
 * @create: 2019-12-04 18:29
 **/
@Service
public class UIServiceImpl implements UIService {

    @Autowired
    PSmhSettingMapper pSmhSettingMapper;





//    @Autowired
//    PSmhDeviceMapper pSmhDeviceMapper;
    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    PD3orgZcMapper pd3orgZcMapper;


    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SmhSettingService smhSettingService;
    @Value("${cd_api_url}")
    String cd_api_url;
    @Value("${back_url}")
    String back_url;
    @Value("${dgj_api_url}")
    String dgj_api_url;


    @Autowired
    AsyncTask asyncTask;

    @Override
    public JSONObject kfsbkz(String serial_num, String product_id, String operation, String controlParams, String account) throws Exception {
        PSbcontrolOriginal pSbcontrolOriginal = new PSbcontrolOriginal();
        if(StringUtils.isEmpty(account)){
            PSbglExample pSbglExample = new PSbglExample();
            PSbglExample.Criteria criteria = pSbglExample.createCriteria();
            criteria.andSerialNumEqualTo(serial_num);
            List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
            if(pSbgls.size()==0){
                throw new Exception("设备不存在");
            }else{
                account =  pSbgls.get(0).getAccount();
                pSbcontrolOriginal.setDeviceName(pSbgls.get(0).getDeviceName());
            }
        }
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(account);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        PSmhDeviceExample pSmhDeviceExample = new PSmhDeviceExample();
        PSmhDeviceExample.Criteria criteria1 = pSmhDeviceExample.createCriteria();
        criteria1.andCreatorIdEqualTo(pSmhSettings.get(0).getCreatorId());
        PD3orgZc bccaServiceInfo = pd3orgZcMapper.selectServiceInfo(pSmhSettings.get(0).getCreatorId());
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        String token = "";
        Object o = redisUtil.get(service_id);
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("serial_num", serial_num);
        obj.put("operation", operation);
        obj.put("controlParams", controlParams);
        obj.put("productId", product_id);
        System.out.println(obj);
        String result = BCCAClient.sendDeviceCmd(cd_api_url,obj,account);
        JSONObject jsonObject = JSONObject.parseObject(result);

        pSbcontrolOriginal.setAccount(account);//所属账号
        pSbcontrolOriginal.setProductId(product_id);//设备类型
        pSbcontrolOriginal.setSerialNum(serial_num);
        pSbcontrolOriginal.setOperation(operation);
        pSbcontrolOriginal.setControlparams(URLDecoder.decode(controlParams,"UTF-8"));
        pSbcontrolOriginal.setControlId(pSmhSettings.get(0).getCreatorId());
        pSbcontrolOriginal.setCreateDate(new Date());

        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            obj.put("token", token);
            result = BCCAClient.sendDeviceCmd(cd_api_url, obj, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (jsonObject.get("resultCode").equals("0000")) {
            JSONObject object2 = JSONObject.parseObject(jsonObject.get("resultContent").toString());
            if (object2.containsKey("code")) {
                if (!object2.getString("code").equals("0")) {
                    pSbcontrolOriginal.setResult("2");
                    pSbcontrolOriginal.setAbnormalReason(object2.get("message").toString());
                    asyncTask.sbControlRecord(pSbcontrolOriginal);
                }else{
                    pSbcontrolOriginal.setResult("1");
                    asyncTask.sbControlRecord(pSbcontrolOriginal);
                }
            }
            return object2;
        } else {
            pSbcontrolOriginal.setResult("2");
            pSbcontrolOriginal.setAbnormalReason(jsonObject.get("resultDesc").toString());
            asyncTask.sbControlRecord(pSbcontrolOriginal);
            throw new Exception(jsonObject.get("resultDesc").toString());
        }

    }
}
