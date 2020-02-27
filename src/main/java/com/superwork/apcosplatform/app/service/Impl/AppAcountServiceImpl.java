package com.superwork.apcosplatform.app.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.app.service.AppAcountService;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PSmhSettingMapper;
import com.superwork.apcosplatform.mapper.PZzUserMapper;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.HTTPclient;
import com.superwork.apcosplatform.mapper.PD3orgZcMapper;
import com.superwork.apcosplatform.mapper.PSbglMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.utils.D3HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.math.BigDecimal;
import java.util.*;


/**
 * @program: code->AcountServiceImpl
 * @description: 账号
 * @author: xjj
 * @create: 2019-11-29 13:52
 **/
@Service
public class AppAcountServiceImpl implements AppAcountService {


    @Autowired
    PD3orgZcMapper pd3orgZcMapper;
    //    @Autowired
//    PSmhDeviceMapper pSmhDeviceMapper;
    @Autowired
    PSmhSettingMapper pSmhSettingMapper;
    @Autowired
    PSbglMapper pSbglMapper;


    @Value("${serviceIp}")//本项目发布的地址
    private String serviceIp;
    @Value("${cd_api_url}")//核心平台地址
    private String cd_api_url;
    @Value("${back_url}")//核心平台回掉本项目地址
    private String back_url;

    @Value("${d3url}")
    private String d3url;
    @Value("${dgj_api_url}")
    private String dgj_api_url;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PZzUserMapper pZzUserMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInfo(Map<String, String> map) throws Exception {
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        String id = map.get("id");
        String serviceId = map.get("serviceId");
        String serviceKey = map.get("serviceKey");
        String orgName = map.get("orgName");
//        String account = map.get("account");
        //3D服务信息
        PD3orgZc pd3orgZc = new PD3orgZc();
//        pd3orgZc.setAccount(account);
        pd3orgZc.setServiceId(serviceId);
        pd3orgZc.setServiceKey(serviceKey);
        pd3orgZc.setOrgName(orgName);
        //如果存在ID，就是修改
        if (StringUtils.isEmpty(id)) {
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id", serviceId);
            jsonObject.put("service_key", serviceKey);
            jsonObject.put("call_back_url", serviceIp);
            jsonObject.put("call_project_url", serviceIp + "/interface/getSmhlist");
            jsonObject.put("org_name", orgName);
            String org_id = D3HttpClient.registerOrg(d3url, jsonObject);//3D平台的ID
            pd3orgZc.setBelongOrgId(org_id);
            //注册用户
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("service_id", serviceId);
            jsonObject2.put("dept_id", user.getUserId());
            jsonObject2.put("user_id", user.getUserId());
            jsonObject2.put("user_name", user.getUserAccount());
            D3HttpClient.registerUser(d3url, jsonObject2);
            pd3orgZc.setCreatorId(user.getUserId());
            pd3orgZc.setCreatedTime(new Date());
            pd3orgZcMapper.insert(pd3orgZc);
            PZzUser pZzUser = new PZzUser();
            pZzUser.setZzId(pd3orgZc.getId().toString());
            pZzUser.setUserId(user.getUserId());
            pZzUserMapper.insert(pZzUser);

        } else {
            PD3orgZc pd3orgZcs = pd3orgZcMapper.selectServiceInfo(user.getUserId());
            pd3orgZc.setId(pd3orgZcs.getId());
            pd3orgZc.setEditedTime(new Date());
            pd3orgZc.setEditorId(user.getUserId());
            pd3orgZc.setBelongOrgId(pd3orgZcs.getBelongOrgId());
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id", serviceId);
            jsonObject.put("service_key", serviceKey);
            jsonObject.put("org_id", pd3orgZcs.getBelongOrgId());
            jsonObject.put("call_back_url", serviceIp);
            jsonObject.put("call_project_url", serviceIp + "/interface/getSmhlist");
            jsonObject.put("org_name", orgName);
            D3HttpClient.updateOrg(d3url, jsonObject);
            pd3orgZcMapper.updateByPrimaryKeySelective(pd3orgZc);
        }


    }

    @Override
    public Map<String, String> getInfo() {
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        HashMap<String, String> map = new HashMap<>();
        PD3orgZc pd3orgZc = pd3orgZcMapper.selectServiceInfo(user.getUserId());
        if (pd3orgZc == null) {
            map.put("id", null);
            map.put("serviceId", null);
            map.put("serviceKey", null);
            map.put("orgName", null);
//            map.put("account", null);
        } else {
            map.put("id", pd3orgZc.getId().toString());
            map.put("serviceId", pd3orgZc.getServiceId());
            map.put("serviceKey", pd3orgZc.getServiceKey());
            map.put("orgName", pd3orgZc.getOrgName());
//            map.put("account", pd3orgZc.getAccount());
        }
        return map;
    }

    @Override
    public ResultDO<String> sbdy(PSmhSetting pSmhSetting) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        String account = pSmhSetting.getSmarthomeAccount();
        Map<String, String> info = getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        String result = BCCAClient.accountSubscrip(cd_api_url, serviceId, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            result = BCCAClient.accountSubscrip(cd_api_url, serviceId, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000")) {
            throw new Exception(jsonObject.get("resultDesc").toString());
        }
        return resultDO;
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> dyAccount(String data) throws Exception {
        PSmhSetting pSmhSetting = new PSmhSetting();
        pSmhSetting.setSmarthomeAccount(data);
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(data);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        if (pSmhSettings.size() > 0) {
            throw new Exception("账号已被订阅！");
        }
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        pSmhSetting.setSfkt("2");
        pSmhSetting.setCreatorId(user.getUserId());
        pSmhSetting.setAttr2("3");
        pSmhSetting.setCreatedTime(new Date());
        pSmhSetting.setAttr4(user.getUserAccount());
        pSmhSettingMapper.insert(pSmhSetting);
        ResultDO<String> resultDO1 = sbdy(pSmhSetting);
        return resultDO1;
    }

    @Override
    public ResultDO<String> delSbdy(PSmhSetting pSmhSetting) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        String account = pSmhSetting.getSmarthomeAccount();
        Map<String, String> info = getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        //先重redis中获取token
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        String result = BCCAClient.removeSubscripRelation(cd_api_url, serviceId, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            result = BCCAClient.removeSubscripRelation(cd_api_url, serviceId, token, account); // 取消订阅
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000")) {
            throw new Exception(jsonObject.get("resultDesc") + "，" + jsonObject.get("resultContent"));
        } else {
            pSmhSetting.setAttr2("2");//取消订阅
            pSmhSettingMapper.updateByPrimaryKeySelective(pSmhSetting);
        }
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> syncSbxx(String account) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        //回调账号设备更新
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        //先重redis中获取token
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        String msg = "";
        int snum = 0;//同步总数量
        String result = BCCAClient.getDevice(cd_api_url, serviceId, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url, cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            result = BCCAClient.getDevice(cd_api_url, serviceId, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("resultContent").toString());
        String sblist = jsonObject2.get("deviceList").toString();
        ArrayList<PSbgl2> list = (ArrayList<PSbgl2>) JSONArray.parseArray(sblist, PSbgl2.class);
        //查询此账号下的设备并删除设备
        PSbglExample pSbglExample = new PSbglExample();
        PSbglExample.Criteria criteria1 = pSbglExample.createCriteria();
        criteria1.andAccountEqualTo(account);
        List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
        pSbglMapper.deleteByExample(pSbglExample);
        System.out.println("size:" + list.size());
        if (list != null && list.size() > 0) {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            String user_id = user.getUserId();
            snum = list.size();
            for (int i = 0; i < list.size(); i++) {
                PSbgl pSbgl = changEntity(list.get(i));
                boolean mark = true;
                for (PSbgl sbgl : pSbgls) {
                    if (pSbgl.getId().equals(sbgl.getId())) {
                        mark = false; //存在
                    }
                }
                if (mark) {
                    pSbgl.setCreatorId(user_id);
                    pSbgl.setCreatedTime(new Date());
                    pSbglMapper.insert(pSbgl);
                } else {
                    pSbglMapper.updateByPrimaryKeySelective(pSbgl);//存在就更新
                }

            }
        }
        msg = "同步设备数量:" + snum;
        resultDO.setData(msg);
        return resultDO;
    }

    @Override
    public ResultDO<String> newsbbinding(Map<String, String> map) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String account = map.get("account");//账号
        String genuine_code = map.get("genuine_code");//正品码
        String serial_num = map.get("serial_num");//序列号
        String token = HTTPclient.getNewAccessToken(dgj_api_url, serviceId, serviceKey);//获取TOKEN
        String method = "/thirdInterface/bind";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", serviceId);
        obj.put("account", account);
        obj.put("serial_num", serial_num);
        obj.put("genuine_code", genuine_code);
        String s = HTTPclient.httpRequestPostMethod(dgj_api_url + method, obj);
        System.out.println("返回结果：" + s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        if (!jsonObject.get("code").toString().equals("200")) {
            throw new Exception("绑定失败：" + jsonObject.get("msg").toString());
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> newsbJiebing(PSbgl pSbgl) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setErrorMsg("请先完善服务信息！");
            resultDO.setSuccess(false);
            return resultDO;
        }
        String mac = pSbgl.getDeviceMac();
        String account = pSbgl.getAccount();
        String id = pSbgl.getId();
        String token = HTTPclient.getNewAccessToken(dgj_api_url, serviceId, serviceKey);
        String method = "/thirdInterface/unbind";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", serviceId);
        obj.put("account", account);
        obj.put("mac", mac);
        String result = HTTPclient.httpRequestPostMethod(dgj_api_url + method, obj);
        System.out.println("返回的结果：" + result);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("code").toString().equals("200")) {
            pSbglMapper.deleteByPrimaryKey(id);//删除设备
        } else {
            throw new Exception("解绑失败：" + object.get("msg").toString());
        }
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAccount(String settingId) throws Exception {
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        PSmhSetting pSmhSetting = pSmhSettingMapper.selectByPrimaryKey(new BigDecimal(settingId));
        if (pSmhSetting.getSfkt().equals("2")) {
            throw new Exception("请先将账号禁用！");
        }
        if (pSmhSetting.getAttr2().equals("1")) {
            throw new Exception("请先将账号取消订阅！");
        }
        if (!pSmhSetting.getCreatorId().equals(user.getUserId())) {
            throw new Exception("该账号不是你订阅的账号不能删除！");
        }
        //删除账号
        pSmhSettingMapper.deleteByPrimaryKey(new BigDecimal(settingId));
        //删除账号下的设备
        PSbglExample pSbglExample1 = new PSbglExample();
        PSbglExample.Criteria criteria = pSbglExample1.createCriteria();
        criteria.andAccountEqualTo(pSmhSetting.getSmarthomeAccount());
        pSbglMapper.deleteByExample(pSbglExample1);
    }

    @Override
    public ResultDO<String> newadd(PSmhSetting pSmhSetting) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        String token = HTTPclient.getNewAccessToken(dgj_api_url, serviceId, serviceKey);//获取TOKEN
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", serviceId);
        obj.put("password", "111111");
        obj.put("name", pSmhSetting.getAttr4());
        obj.put("phone", pSmhSetting.getAttr7());
        obj.put("gender", pSmhSetting.getAttr5());
        obj.put("email", pSmhSetting.getAttr8());
        obj.put("area_code1", "500000");//重庆市
        String method = "/thirdInterface/addAccount";
        String s = HTTPclient.httpRequestPostMethod(dgj_api_url + method, obj);
        System.out.println("result:" + s);
        JSONObject object3 = JSONObject.parseObject(s);
        if (object3.get("code").toString().equals("200")) {
            pSmhSetting.setSmarthomeAccount(object3.get("data").toString());
            pSmhSetting.setSmarthomePwd("111111");
            pSmhSetting.setSfkt("2");//默认启用
            pSmhSetting.setAttr2("2");//未订阅
            pSmhSetting.setCreatorId(user.getUserId());
            pSmhSetting.setCreatedTime(new Date());
            pSmhSettingMapper.insert(pSmhSetting);
            resultDO.setData(object3.get("data").toString());
        } else {
            throw new Exception(object3.get("msg").toString());
        }
        return resultDO;
    }

    @Override
    public void editAccount(PSmhSetting pSmhSetting) {
        PSmhSetting pSmhSetting1 = new PSmhSetting();
        pSmhSetting1.setSettingId(pSmhSetting.getSettingId());
        pSmhSetting1.setAttr2("3");
        pSmhSettingMapper.updateByPrimaryKeySelective(pSmhSetting1);
    }

    private PSbgl changEntity(PSbgl2 pSbgl2) {
        PSbgl pSbgl = new PSbgl();
        pSbgl.setId(pSbgl2.getId());
        pSbgl.setProductCode(pSbgl2.getProduct_code());
        pSbgl.setAccount(pSbgl2.getAccount());
        pSbgl.setSerialNum(pSbgl2.getSerial_num());
        pSbgl.setDeviceMac(pSbgl2.getDevice_mac());
        pSbgl.setDeviceName(pSbgl2.getDevice_name());
        pSbgl.setDeviceType(pSbgl2.getDevice_type());
        pSbgl.setCreatedTime(pSbgl2.getCreate_time());
        pSbgl.setEditedTime(pSbgl2.getUpdate_time());
        pSbgl.setWhetherRegister(pSbgl2.getWhether_register());
        return pSbgl;
    }

}
