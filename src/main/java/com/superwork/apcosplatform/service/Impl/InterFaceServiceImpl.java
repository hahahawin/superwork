package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.service.AsyncTask;
import com.superwork.apcosplatform.service.InterFaceService;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class InterFaceServiceImpl implements InterFaceService {

    @Autowired
    PD3orgZcMapper pd3orgZcMapper;

    @Autowired
    PSmhSettingMapper pSmhSettingMapper;

    @Autowired
    PSbzcLxMapper pSbzcLxMapper;

    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    PSbmoglMapper pSbmoglMapper;

//    @Autowired
//    PSmhDeviceMapper pSmhDeviceMapper;

    @Autowired
    PYktZxjhMapper pYktZxjhMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PYktMsgngxMapper pYktMsgngxMapper;

    @Autowired
    AsyncTask asyncTask;

    @Value("${cd_api_url}")
    private String cd_api_url;
    @Value("${back_url}")
    private String back_url;
    @Value("${dgj_api_url}")
    private String dgj_api_url;


    @Override
    public List<PSmhSetting> getSmhlist(String borg_id) throws Exception {
        PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
        PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
        criteria.andBelongOrgIdEqualTo(borg_id);
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
        if (pd3orgZcs.size() == 0) {
            throw new Exception("未找到注册用户");
        }
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.getSmhlist(pd3orgZcs.get(0).getId());
//        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
//        PSmhSettingExample.Criteria criteria1 = pSmhSettingExample.createCriteria();
//        criteria1.andCreatorIdEqualTo(pd3orgZcs.get(0).getCreatorId());
//        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        return pSmhSettings;

    }

    @Override
    public List<PSbzcLx> getSbLxlist(String borg_id, String account) throws Exception {
        PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
        PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
        criteria.andBelongOrgIdEqualTo(borg_id);
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
        if (pd3orgZcs.size() == 0) {
            throw new Exception("未找到注册用户");
        }
        List<PSbzcLx> sbLxlist = pSbzcLxMapper.getSbLxlist(account);
        return sbLxlist;
    }

    @Override
    public PageInfo<Map<String, String>> getSblist(Map<String, String> map1) {
        PageHelper.startPage(Integer.valueOf(map1.get("pageNo")), Integer.valueOf(map1.get("pageSize")));
        List<Map<String, String>> sblist = pSbglMapper.getSblist(map1);
        PageInfo<Map<String, String>> pSbglPageInfo = new PageInfo<>(sblist);

        return pSbglPageInfo;
    }

    @Override
    public List<Map<String, String>> getZhms(Map<String, String> map1) throws Exception {
        PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
        PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
        criteria.andBelongOrgIdEqualTo(map1.get("borg_id"));
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
        if (pd3orgZcs.size() == 0) {
            throw new Exception("未找到注册用户");
        }
        String account = map1.get("account");
        List<Map<String, String>> zhms = pSbmoglMapper.getZhms(account);
        return zhms;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addZxjh(Map<String, String> map1) throws Exception {
        PD3orgZc pD3orgZc = get3DserviceInfo(map1.get("borg_id"));
        if(pD3orgZc == null){
            throw new Exception("未找到3D注册信息！");
        }
        String service_id = pD3orgZc.getServiceId();
        String service_key = pD3orgZc.getServiceKey();
        String account = map1.get("account");
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("account", account);
        jsonObject.put("plan_name", map1.get("mc"));
        jsonObject.put("ser_code", "1");
        jsonObject.put("enable", map1.get("zt"));
        String result = BCCAClient.addExcutionPlan(cd_api_url, jsonObject, account);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.addExcutionPlan(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        String yptjh_id = "";
        if (jsonObject1.get("resultCode").equals("0000")) {
            //成功
            yptjh_id = jsonObject1.get("resultContent").toString();
            PYktZxjh pYktZxjh = new PYktZxjh();
            pYktZxjh.setCreatorId(map1.get("user_id"));
            pYktZxjh.setCreatedTime(new Date());
            pYktZxjh.setYptjhId(yptjh_id);
            pYktZxjh.setZxjhMc(map1.get("mc"));
            pYktZxjh.setZxjhYptzh(account);
            pYktZxjh.setZxjhZt(map1.get("zt"));
            pYktZxjhMapper.insert(pYktZxjh);
        } else {
            throw new Exception("新增执行计划失败！" + jsonObject1.get("resultDesc"));
        }

        return yptjh_id;
    }

    @Override
    public void editZxjh(Map<String, String> map) throws Exception {
        PD3orgZc pD3orgZc = get3DserviceInfo(map.get("org_id"));
        if (pD3orgZc == null) {
            throw new Exception("当前账号未完善服务信息或账号不存在");
        }
        PYktZxjhExample pYktZxjhExample = new PYktZxjhExample();
        PYktZxjhExample.Criteria criteria = pYktZxjhExample.createCriteria();
        criteria.andYptjhIdEqualTo(map.get("yid"));
        List<PYktZxjh> pYktZxjhs = pYktZxjhMapper.selectByExample(pYktZxjhExample);
        if (pYktZxjhs.size() == 0) {
            throw new Exception("未查询到云平台执行计划");
        }
        PYktZxjh pYktZxjh = pYktZxjhs.get(0);
        String service_id = pD3orgZc.getServiceId();
        String service_key = pD3orgZc.getServiceKey();
        String account = map.get("account");
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("id", map.get("yid"));
        jsonObject.put("account", map.get("account"));
        jsonObject.put("plan_name", map.get("mc"));
        jsonObject.put("enable", map.get("zt"));
        jsonObject.put("ser_code", "1");
        String result = BCCAClient.updateExcutionPlanById(cd_api_url, jsonObject, account);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.updateExcutionPlanById(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        if (jsonObject1.get("resultCode").equals("0000")) {
            pYktZxjh.setEditorId(map.get("user_id"));
            pYktZxjh.setEditedTime(new Date());
            pYktZxjh.setYptjhId(map.get("yid"));
            pYktZxjh.setZxjhMc(map.get("mc"));
            pYktZxjh.setZxjhYptzh(account);
            pYktZxjh.setZxjhZt(map.get("zt"));
            pYktZxjhMapper.updateByPrimaryKeySelective(pYktZxjh);
        } else {
            throw new Exception("编辑执行计划失败！" + jsonObject1.get("resultDesc"));
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delZxjh(Map<String, String> map) throws Exception {
        PD3orgZc pSmhDevice = getBCCAserviceInfoByAccount(map.get("account"));
//        PSmhDevice pSmhDevice = getBCCAserviceInfoByAccount(map.get("account"));
        if (StringUtils.isEmpty(pSmhDevice.getServiceId())) {
            throw new Exception("当前账号未完善服务信息或账号不存在");
        }
        PYktZxjhExample pYktZxjhExample = new PYktZxjhExample();
        PYktZxjhExample.Criteria criteria = pYktZxjhExample.createCriteria();
        criteria.andYptjhIdEqualTo(map.get("yid"));
        List<PYktZxjh> pYktZxjhs = pYktZxjhMapper.selectByExample(pYktZxjhExample);
        if (pYktZxjhs.size() == 0) {
            throw new Exception("未查询到云平台执行计划");
        }
        PYktZxjh pYktZxjh = pYktZxjhs.get(0);
        String service_id = pSmhDevice.getServiceId();
        String service_key = pSmhDevice.getServiceKey();
        String account = map.get("account");
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("id", map.get("yid"));
        String result = BCCAClient.deleteExcutionPlanById(cd_api_url, jsonObject, account);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.deleteExcutionPlanById(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        if (jsonObject1.get("resultCode").equals("0000")) {
            pYktZxjhMapper.deleteByPrimaryKey(pYktZxjh.getZxjhId());
            PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
            PYktMsgngxExample.Criteria criteria2 = pYktMsgngxExample.createCriteria();
            criteria2.andMsidEqualTo(pYktZxjh.getZxjhId());
            pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
        } else {
            throw new Exception("删除执行计划失败！" + jsonObject1.get("resultDesc"));
        }


    }

    @Override
    public String zxjhJcadd(Map<String, Object> map) throws Exception {
        PD3orgZc pSmhDevice = getBCCAserviceInfoByAccount(map.get("account").toString());
//        PSmhDevice pSmhDevice = getBCCAserviceInfoByAccount(map.get("account").toString());
        PYktZxjhExample pYktZxjhExample = new PYktZxjhExample();
        PYktZxjhExample.Criteria criteria = pYktZxjhExample.createCriteria();
        criteria.andYptjhIdEqualTo(map.get("plan_id").toString());
        List<PYktZxjh> pYktZxjhs = pYktZxjhMapper.selectByExample(pYktZxjhExample);
        if (pYktZxjhs.size() == 0) {
            throw new Exception("未查询到云平台执行计划");
        }
        PYktZxjh pYktZxjh = pYktZxjhs.get(0);
        String service_id = pSmhDevice.getServiceId();
        String service_key = pSmhDevice.getServiceKey();
        String account = map.get("account").toString();
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("process_name", map.get("process_name"));
        jsonObject.put("ser_order", "1");
        jsonObject.put("trigger_conditon_desc", map.get("trigger_conditon_desc"));
        jsonObject.put("plan_id", map.get("plan_id"));
        jsonObject.put("has_condition", map.get("has_condition"));
        jsonObject.put("mode_ids", map.get("mode_ids"));
        jsonObject.put("delay_trigger_time", map.get("delay_trigger_time"));
        jsonObject.put("excutionConditionList", map.get("excutionConditionList"));
        jsonObject.put("excutionTimeConditionList", map.get("excutionTimeConditionList"));
        jsonObject.put("pre_trigger_time", map.get("pre_trigger_time"));
        jsonObject.put("mode_desc", map.get("mode_desc"));
        String result = BCCAClient.addExcutionPlanProcess(cd_api_url, jsonObject, account);
        System.out.println("返回结果："+result);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.addExcutionPlanProcess(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        String resultContent = "";
        if (jsonObject1.get("resultCode").equals("0000")) {
            resultContent = jsonObject1.get("resultContent").toString();

        } else {
            throw new Exception("新增执行计划进程失败！" + jsonObject1.get("resultContent"));
        }
        return resultContent;
    }

    @Override
    public void zxjhJcedit(Map<String, Object> map) throws Exception {
        PD3orgZc pSmhDevice = getBCCAserviceInfoByAccount(map.get("account").toString());
//        PSmhDevice pSmhDevice = getBCCAserviceInfoByAccount(map.get("account").toString());
        PYktZxjhExample pYktZxjhExample = new PYktZxjhExample();
        PYktZxjhExample.Criteria criteria = pYktZxjhExample.createCriteria();
        criteria.andYptjhIdEqualTo(map.get("plan_id").toString());
        List<PYktZxjh> pYktZxjhs = pYktZxjhMapper.selectByExample(pYktZxjhExample);
        if (pYktZxjhs.size() == 0) {
            throw new Exception("未查询到云平台执行计划");
        }
        PYktZxjh pYktZxjh = pYktZxjhs.get(0);
        String service_id = pSmhDevice.getServiceId();
        String service_key = pSmhDevice.getServiceKey();
        String account = map.get("account").toString();
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("id", map.get("id"));
        jsonObject.put("process_name", map.get("process_name"));
        jsonObject.put("ser_order", map.get("ser_order"));
        jsonObject.put("trigger_conditon_desc", map.get("trigger_conditon_desc"));
        jsonObject.put("plan_id", map.get("plan_id"));
        jsonObject.put("has_condition", map.get("has_condition"));
        jsonObject.put("mode_ids", map.get("mode_ids"));
        jsonObject.put("delay_trigger_time", map.get("delay_trigger_time"));
        jsonObject.put("excutionConditionList", map.get("excutionConditionList"));
        jsonObject.put("excutionTimeConditionList", map.get("excutionTimeConditionList"));
        jsonObject.put("pre_trigger_time", map.get("pre_trigger_time"));
        jsonObject.put("mode_desc", map.get("mode_desc"));
        String result = BCCAClient.updateExcutionPlanProcess(cd_api_url, jsonObject, account);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.updateExcutionPlanProcess(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        if (!jsonObject1.get("resultCode").equals("0000")) {
            throw new Exception("修改执行计划进程失败！" + jsonObject1.get("resultContent"));
        }

    }

    @Override
    public void zxjhJcdel(Map<String, String> map) throws Exception {

        PD3orgZc pSmhDevice = getBCCAserviceInfoByAccount(map.get("account"));
//        PSmhDevice pSmhDevice = getBCCAserviceInfoByAccount(map.get("account"));
        String service_id = pSmhDevice.getServiceId();
        String service_key = pSmhDevice.getServiceKey();
        String account = map.get("account");
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("id", map.get("id"));
        String result = BCCAClient.deleteExcutionPlanProcess(cd_api_url, jsonObject, account);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.updateExcutionPlanProcess(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        if (!jsonObject1.get("resultCode").equals("0000")) {
            throw new Exception("删除执行计划进程失败！" + jsonObject1.get("resultContent"));
        }
    }

    @Override
    public String zxjhJcList(Map<String, String> map) throws Exception {

        PD3orgZc pSmhDevice = getBCCAserviceInfoByAccount(map.get("account"));
//        PSmhDevice pSmhDevice = getBCCAserviceInfoByAccount(map.get("account"));
        String service_id = pSmhDevice.getServiceId();
        String service_key = pSmhDevice.getServiceKey();
        String account = map.get("account");
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("service_id", service_id);
        jsonObject.put("account", account);
        String result = BCCAClient.queryExcutionPlanByCondition(cd_api_url, jsonObject, account);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if (jsonObject1.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            jsonObject.put("token", token);
            result = BCCAClient.updateExcutionPlanProcess(cd_api_url, jsonObject, account);
            jsonObject1 = JSONObject.parseObject(result);
        }
        String resultContent = "";
        if (jsonObject1.get("resultCode").equals("0000")) {
            resultContent = jsonObject1.get("resultContent").toString();
        } else {
            throw new Exception("新增执行计划进程失败！" + jsonObject1.get("resultContent"));
        }

        return resultContent;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addAllMsxx(JSONObject map) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        PD3orgZc pSmhDevice = getBCCAserviceInfoByAccount(map.get("sbmogl_account").toString());
//        PSmhDevice pSmhDevice = getBCCAserviceInfoByAccount(map.get("sbmogl_account").toString());
        String service_id = pSmhDevice.getServiceId();
        String service_key = pSmhDevice.getServiceKey();
        String account = map.get("sbmogl_account").toString();
        Object o = redisUtil.get(service_id);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        } else {
            token = o.toString();
        }
        String type = map.get("type").toString();
        String user_id = map.get("user_id").toString();
        String sbmogl_name = map.get("sbmogl_name").toString();
        JSONObject jsonObject = new JSONObject();

        if("add".equals(type)){
            PSbmogl pSbmogl = new PSbmogl();
            pSbmogl.setSbmoglName(sbmogl_name);
            pSbmogl.setSbmoglZt("1");
            pSbmogl.setCreatorId(user_id);
            pSbmogl.setCreatedTime(new Date());
            pSbmogl.setAttr2(account);
            pSbmoglMapper.insert(pSbmogl);
            JSONArray msmls1 = map.getJSONArray("msmls");
            //[{code=close, params={"p1":"350","p2":"100"}, memo=关灯, account=1561200000001459, whid=000d6f0004d77059, product_code=00023e02, memo1=智能插座小Q, msid=, ymsid=}]
            MyJsonUtils.convert(msmls1);
            List<PYktMsgngx> pYktMsgngxes = JSONArray.parseArray(msmls1.toString(), PYktMsgngx.class);
            //新增模式
            ArrayList<String> cmds = new ArrayList<>();
            for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
                pYktMsgngx.setCreatorId(user_id);
                pYktMsgngx.setEditorId(user_id);
                pYktMsgngx.setMsid(pSbmogl.getSbmoglId());
                String params = pYktMsgngx.getParams();
                if (params == null || "".equals(params)) {
                    params = "none";
                    pYktMsgngx.setParams("");
                }
                pYktMsgngxMapper.insert(pYktMsgngx);
                cmds.add(pYktMsgngx.getWhid() + "_" + pYktMsgngx.getProductCode() + "_" + pYktMsgngx.getCode() + "_" + params);
            }
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("account",account);
            jsonObject1.put("enable_mode","yes");
            jsonObject1.put("mode_name",sbmogl_name);
            jsonObject.put("token",token);
            jsonObject.put("service_id",service_id);
            jsonObject.put("cmds",cmds);
            jsonObject.put("modeConfDO",jsonObject1);
            String result = BCCAClient.addMode(cd_api_url, jsonObject, account);
            JSONObject jsonObject2 = JSONObject.parseObject(result);
            if (jsonObject2.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
                jsonObject1.put("token", token);
                result = BCCAClient.addMode(cd_api_url, jsonObject, account);
                jsonObject2 = JSONObject.parseObject(result);
            }
            if (jsonObject2.get("resultCode").equals("0000")) {
                JSONObject resultContent1 = jsonObject2.getJSONObject("resultContent");
                pSbmogl.setSbmoglYmsid(resultContent1.get("modeId").toString());
                pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);
                resultMap.put("sbmogl_id",pSbmogl.getSbmoglId());
                resultMap.put("sbmogl_ymsid",resultContent1.get("modeId").toString());
            } else {
                throw new Exception(jsonObject2.get("resultDesc").toString());
            }
        }else if("edit".equals(type)){
            PSbmogl pSbmogl = new PSbmogl();
            pSbmogl.setSbmoglName(sbmogl_name);
            pSbmogl.setEditorId(user_id);
            pSbmogl.setCreatedTime(new Date());
            pSbmogl.setSbmoglId(map.get("sbmogl_id").toString());
            pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);
            //删除之前的命令
            PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
            PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
            criteria.andMsidEqualTo(map.get("sbmogl_id").toString());
            pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
            JSONArray msmls1 = map.getJSONArray("msmls");
            MyJsonUtils.convert(msmls1);
            List<PYktMsgngx> pYktMsgngxes = JSONArray.parseArray(msmls1.toString(), PYktMsgngx.class);
            //新增模式
            ArrayList<String> cmds = new ArrayList<>();
            for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
                pYktMsgngx.setCreatorId(user_id);
                pYktMsgngx.setEditorId(user_id);
                pYktMsgngx.setMsid(pSbmogl.getSbmoglId());
                String params = pYktMsgngx.getParams();
                if (params == null || "".equals(params)) {
                    params = "none";
                    pYktMsgngx.setParams("");
                }
                pYktMsgngxMapper.insert(pYktMsgngx);
                cmds.add(pYktMsgngx.getWhid() + "_" + pYktMsgngx.getProductCode() + "_" + pYktMsgngx.getCode() + "_" + params);
            }
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("account",account);
            jsonObject1.put("id",map.get("sbmogl_ymsid").toString());
            jsonObject1.put("mode_name",sbmogl_name);
            jsonObject.put("token",token);
            jsonObject.put("service_id",service_id);
            jsonObject.put("cmds",cmds);
            jsonObject.put("modeConfDO",jsonObject1);
            String result = BCCAClient.updateMode(cd_api_url, jsonObject, account);
            JSONObject jsonObject2 = JSONObject.parseObject(result);
            if (jsonObject2.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
                jsonObject1.put("token", token);
                result = BCCAClient.addMode(cd_api_url, jsonObject, account);
                jsonObject2 = JSONObject.parseObject(result);
            }
            if (jsonObject2.get("resultCode").equals("0000")) {
                resultMap.put("sbmogl_id",map.get("sbmogl_id").toString());
                resultMap.put("sbmogl_ymsid",map.get("sbmogl_ymsid").toString());
            }else {
                throw new Exception(jsonObject2.get("resultDesc").toString());
            }
        }else{
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id",map.get("sbmogl_ymsid").toString());
            jsonObject.put("token",token);
            jsonObject.put("service_id",service_id);
            jsonObject.put("modeConfDO",jsonObject1);
            String result = BCCAClient.deletMode(cd_api_url, jsonObject, account);
            JSONObject jsonObject2 = JSONObject.parseObject(result);
            if (jsonObject2.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
                jsonObject1.put("token", token);
                result = BCCAClient.addMode(cd_api_url, jsonObject, account);
                jsonObject2 = JSONObject.parseObject(result);
            }
            if (jsonObject2.get("resultCode").equals("0000")) {
                //删除模式
                pSbmoglMapper.deleteByPrimaryKey(map.get("sbmogl_id").toString());
                resultMap.put("sbmogl_id",map.get("sbmogl_id").toString());
                resultMap.put("sbmogl_ymsid",map.get("sbmogl_ymsid").toString());
                //删除 模式命令
                PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
                PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
                criteria.andMsidEqualTo(map.get("sbmogl_id").toString());
                pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
            } else {
                throw new Exception(jsonObject2.get("resultDesc").toString());
            }
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> selMsDetail(String sbmogl_id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(sbmogl_id);
        if(pSbmogl==null){
            throw new Exception("未找到相应的模式！");
        }
        map.put("sbmogl_account",pSbmogl.getAttr2());
        map.put("sbmogl_id",pSbmogl.getSbmoglId());
        map.put("sbmogl_ymsid",pSbmogl.getSbmoglYmsid());
        map.put("sbmogl_name",pSbmogl.getSbmoglName());
        PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
        PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
        criteria.andMsidEqualTo(map.get("sbmogl_id").toString());
        List<PYktMsgngx> pYktMsgngxes = pYktMsgngxMapper.selectByExample(pYktMsgngxExample);
        ArrayList<Map<String,String>> objects = new ArrayList<>();
        for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("code",pYktMsgngx.getCode());
            objectObjectHashMap.put("memo1",pYktMsgngx.getMemo1());
            objectObjectHashMap.put("memo",pYktMsgngx.getMemo());
            objectObjectHashMap.put("whid",pYktMsgngx.getWhid());
            objectObjectHashMap.put("params",pYktMsgngx.getParams());
            objectObjectHashMap.put("product_code",pYktMsgngx.getProductCode());
            objects.add(objectObjectHashMap);
        }
        map.put("msmls",objects);
        return map;
    }

    @Override
    public Map<String, Object> getMouldBySerialNum(Map<String, String> map) throws Exception {
        Map<String, Object> map1 = new HashMap<>();
        String org_id = map.get("org_id");
        String serial_num = map.get("serial_num");
        PD3orgZc dserviceInfo = get3DserviceInfo(org_id);
        if(dserviceInfo == null){
            throw new Exception("未找到"+org_id+"注册组织信息！");
        }
        PSbglExample pSbglExample = new PSbglExample();
        PSbglExample.Criteria criteria = pSbglExample.createCriteria();
        criteria.andSerialNumEqualTo(serial_num);
        List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
        if(pSbgls.size() == 0){
            throw new Exception("未找到"+serial_num+"设备信息！");
        }
        List<PSbzcLx> pSbzcLxes = pSbzcLxMapper.selectHtmlWithMac(pSbgls.get(0).getDeviceMac());
        map1.put("equip_account",pSbgls.get(0).getAccount());
        map1.put("product_code",pSbgls.get(0).getProductCode());
        if(pSbzcLxes.size() == 0){
            throw new Exception("未找到设备类型信息！");
        }
        map1.put("htmlmodle",pSbzcLxes.get(0).getHtmlmodle());
        map1.put("htmlmobile",pSbzcLxes.get(0).getHtmlmobile());
        return map1;
    }

    @Override
    public PageInfo<Map<String, String>> getEquipList(Map<String, String> map) throws Exception {
        String org_id = map.get("org_id");
        String pageSize = map.get("pageSize");
        String pageNo = map.get("pageNo");
        PD3orgZc dserviceInfo = get3DserviceInfo(org_id);
        if(dserviceInfo == null){
            throw new Exception("未找到"+org_id+"注册组织信息！");
        }
        map.put("org_id",dserviceInfo.getId().toString());
        PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        List<Map<String, String>> equipList = pSbglMapper.getEquipList(map);
        PageInfo<Map<String, String>> pageInfo = new PageInfo<>(equipList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> multiAccountMode(JSONArray mode_data) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> sbmogl_id = new ArrayList<>();
        ArrayList<String> sbmogl_ymsid = new ArrayList<>();
        for(int i = 0;i<mode_data.size();i++){
          JSONObject jsonObject = mode_data.getJSONObject(i);
          String type = jsonObject.getString("type");
          String account = jsonObject.getString("sbmogl_account");
          String user_id = jsonObject.getString("user_id");
          String sbmogl_name = jsonObject.getString("sbmogl_name");
          String sbmogl_desc = jsonObject.getString("sbmogl_desc");//模式描述
          PD3orgZc bccAserviceInfoByAccount = getBCCAserviceInfoByAccount(account);
          String serviceId = bccAserviceInfoByAccount.getServiceId();
          String serviceKey = bccAserviceInfoByAccount.getServiceKey();
            String token = "";
            Object o = redisUtil.get(serviceId);
            if(o == null){
                token =BCCAClient.getNewToken(dgj_api_url,cd_api_url,serviceId,serviceKey,back_url,account);
            }else{
                token = o.toString();
            }
          if("add".equals(type)){
              //新增模式
              PSbmogl pSbmogl = new PSbmogl();
              pSbmogl.setCreatorId(user_id);
              pSbmogl.setCreatedTime(new Date());
              pSbmogl.setSbmoglName(sbmogl_name);
              pSbmogl.setAttr1(sbmogl_desc);//模式描述
              pSbmogl.setAttr2(account);
              pSbmoglMapper.insert(pSbmogl);
              JSONArray msmls = jsonObject.getJSONArray("msmls");
              ArrayList<String> cmds = new ArrayList<>();
              for(int j=0;j<msmls.size();j++){
                  PYktMsgngx pYktMsgngx = new PYktMsgngx();
                  JSONObject jsonObject1 = msmls.getJSONObject(j);
                    String params = jsonObject1.getString("params");
                  String code = jsonObject1.getString("code");
                    String paccount = jsonObject1.getString("account");
                    String whid = jsonObject1.getString("whid");
                    String product_code = jsonObject1.getString("product_code");
                    String memo1 = jsonObject1.getString("memo1");
                    if (params == null || "".equals(params)) {
                        params = "none";
                    }
                  pYktMsgngx.setParams(params);
                  pYktMsgngx.setCode(code);
                  pYktMsgngx.setAccount(paccount);
                  pYktMsgngx.setWhid(whid);
                  pYktMsgngx.setProductCode(product_code);
                  pYktMsgngx.setCreatorId(user_id);
                  pYktMsgngx.setMemo1(memo1);
                  pYktMsgngx.setMsid(pSbmogl.getSbmoglId());
                  pYktMsgngxMapper.insert(pYktMsgngx);
                  cmds.add(whid + "_" + product_code + "_" + code + "_" + params);
                }

              //添加
              JSONObject obj = new JSONObject();
              JSONObject obj2 = new JSONObject();
              obj2.put("account", account);
              obj2.put("enable_mode", "yes");
              obj2.put("mode_name", sbmogl_name);
              obj.put("service_id", serviceId);
              obj.put("token", token);
              obj.put("processTime", new Date().getTime());
              obj.put("cmds", cmds);
              obj.put("modeConfDO", obj2);
              String result =BCCAClient.addMode(cd_api_url,obj,account);
              System.out.println("result:" + result);
              JSONObject resultjsonObject = JSONObject.parseObject(result);
              if (resultjsonObject.get("resultCode").equals("0101")) {
                  token =  BCCAClient.getNewToken(dgj_api_url,cd_api_url,serviceId,serviceKey,back_url,account);
                  obj.put("token", token);
                  redisUtil.set(serviceId,token);
                  result =BCCAClient.addMode(cd_api_url,obj,account);
                  resultjsonObject = JSONObject.parseObject(result);
              }
              if (resultjsonObject.get("resultCode").equals("0000")) {
                  JSONObject object2 = JSONObject.parseObject(resultjsonObject.get("resultContent").toString());
                  String mszh_id = object2.get("modeId").toString();//新的模式ID
                  pSbmogl.setSbmoglYmsid(mszh_id);
                  pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);
                  sbmogl_id.add(pSbmogl.getSbmoglId());
                  sbmogl_ymsid.add(mszh_id);
              } else {
                  throw new Exception(jsonObject.get("resultDesc").toString());
              }
          }else if("edit".equals(type)){
              String sbmoglid = jsonObject.getString("sbmogl_id");
              String sbmoglymsid = jsonObject.getString("sbmogl_ymsid");
              //修改模式
              PSbmogl pSbmogl = new PSbmogl();
              pSbmogl.setEditorId(user_id);
              pSbmogl.setEditedTime(new Date());
              pSbmogl.setSbmoglName(sbmogl_name);
              pSbmogl.setAttr1(sbmogl_desc);//模式描述
              pSbmogl.setAttr2(account);
              pSbmogl.setSbmoglId(sbmoglid);
              pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);
              PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
              PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
              criteria.andMsidEqualTo(sbmoglid);
              pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
              JSONArray msmls = jsonObject.getJSONArray("msmls");
              ArrayList<String> cmds = new ArrayList<>();
              for(int j=0;j<msmls.size();j++){
                  PYktMsgngx pYktMsgngx = new PYktMsgngx();
                  JSONObject jsonObject1 = msmls.getJSONObject(j);
                  String params = jsonObject1.getString("params");
                  String code = jsonObject1.getString("code");
                  String paccount = jsonObject1.getString("account");
                  String whid = jsonObject1.getString("whid");
                  String product_code = jsonObject1.getString("product_code");
                  String memo1 = jsonObject1.getString("memo1");
                  if (params == null || "".equals(params)) {
                      params = "none";
                  }
                  pYktMsgngx.setParams(params);
                  pYktMsgngx.setCode(code);
                  pYktMsgngx.setAccount(paccount);
                  pYktMsgngx.setWhid(whid);
                  pYktMsgngx.setProductCode(product_code);
                  pYktMsgngx.setCreatorId(user_id);
                  pYktMsgngx.setMemo1(memo1);
                  pYktMsgngx.setMsid(sbmoglid);
                  pYktMsgngxMapper.insert(pYktMsgngx);
                  cmds.add(whid + "_" + product_code + "_" + code + "_" + params);
              }

              //添加
              JSONObject obj = new JSONObject();
              JSONObject obj2 = new JSONObject();
              obj2.put("account", account);
              obj2.put("id", sbmoglymsid);
              obj2.put("mode_name", sbmogl_name);
              obj.put("service_id", serviceId);
              obj.put("token", token);
              obj.put("processTime", new Date().getTime());
              obj.put("cmds", cmds);
              obj.put("modeConfDO", obj2);
              String result =BCCAClient.updateMode(cd_api_url,obj,account);
              System.out.println("result:" + result);
              JSONObject resultjsonObject = JSONObject.parseObject(result);
              if (resultjsonObject.get("resultCode").equals("0101")) {
                  token =  BCCAClient.getNewToken(dgj_api_url,cd_api_url,serviceId,serviceKey,back_url,account);
                  obj.put("token", token);
                  redisUtil.set(serviceId,token);
                  result =BCCAClient.updateMode(cd_api_url,obj,account);
                  resultjsonObject = JSONObject.parseObject(result);
              }
              if (resultjsonObject.get("resultCode").equals("0000")) {
                  sbmogl_id.add(sbmoglid);
                  sbmogl_ymsid.add(sbmoglymsid);
              }else{
                  throw new Exception(resultjsonObject.get("resultDesc").toString());
              }
          }else if("del".equals(type)){
              String sbmoglid = jsonObject.getString("sbmogl_id");
              String sbmoglymsid = jsonObject.getString("sbmogl_ymsid");
              pSbmoglMapper.deleteByPrimaryKey(sbmoglid);
              PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
              PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
              criteria.andMsidEqualTo(sbmoglid);
              pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
              JSONObject obj = new JSONObject();
              JSONObject obj2 = new JSONObject();
              obj2.put("id", sbmoglymsid);
              obj.put("service_id", serviceId);
              obj.put("token", token);
              obj.put("processTime", new Date().getTime());
              obj.put("modeConfDO", obj2);
              String result = BCCAClient.deletMode(cd_api_url, obj, account);
              System.out.println("result:" + result);
              JSONObject resultjsonObject = JSONObject.parseObject(result);
              if (resultjsonObject.get("resultCode").equals("0101")) {
                  token =  BCCAClient.getNewToken(dgj_api_url,cd_api_url,serviceId,serviceKey,back_url,account);
                  obj.put("token", token);
                  redisUtil.set(serviceId,token);
                  result =BCCAClient.addMode(cd_api_url,obj,account);
                  resultjsonObject = JSONObject.parseObject(result);
              }
              if (!resultjsonObject.get("resultCode").equals("0000")) {
              }
          }

      }
        map.put("sbmogl_id",sbmogl_id);
        map.put("sbmogl_ymsid",sbmogl_ymsid);
        return map;

    }

    @Override
    public  List<Object> multiModeInfo(JSONObject jsonObject) throws Exception {
        JSONArray mode_id_list = jsonObject.getJSONArray("mode_id_list");
        if(mode_id_list.size() == 0){
            throw new Exception("模式列表为空！");
        }
        List<Object> list = new ArrayList<>();
        JSONObject obj1 = null;
        for (int i=0;i<mode_id_list.size();i++){
            obj1 = new JSONObject();
            PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(mode_id_list.getString(i));
            obj1.put("sbmogl_name",pSbmogl.getSbmoglName());
            obj1.put("sbmogl_account",pSbmogl.getAttr2());
            obj1.put("sbmogl_name",pSbmogl.getSbmoglName());
            obj1.put("sbmogl_id",mode_id_list.getString(i));
            obj1.put("sbmogl_ymsid",pSbmogl.getSbmoglYmsid());
            obj1.put("sbmogl_desc",pSbmogl.getAttr1());
            List<Map<String, String>> maps = pYktMsgngxMapper.listmlBymsId3d(mode_id_list.getString(i));
            obj1.put("msmls",maps);
            list.add(obj1);
        }
        return list;


    }

    @Override
    public void multiModeControl(JSONObject jsonObject) throws Exception {
        JSONArray mode_id_list = jsonObject.getJSONArray("mode_id_list");
        String org_id = jsonObject.getString("org_id");
        PD3orgZc dserviceInfo = get3DserviceInfo(org_id);
        if(dserviceInfo == null){
            throw new Exception("未找到"+org_id+"组织信息！");
        }
        String serviceId = dserviceInfo.getServiceId();
        String serviceKey = dserviceInfo.getServiceKey();
        for(int i=0;i<mode_id_list.size();i++){
            PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(mode_id_list.getString(i));
            String sbmoglYmsid = pSbmogl.getSbmoglYmsid();
            String account = pSbmogl.getAttr2();
            if(!StringUtils.isEmpty(sbmoglYmsid)){
                String token = "";
                Object o = redisUtil.get(serviceId);
                if(o==null){
                    token =BCCAClient.getNewToken(dgj_api_url,cd_api_url,serviceId,serviceKey,back_url,account);
                }else{
                    token = o.toString();
                }
                JSONObject obj = new JSONObject();
                JSONObject obj2 = new JSONObject();
                obj2.put("id", sbmoglYmsid);
                obj.put("service_id", serviceId);
                obj.put("token", token);
                obj.put("processTime", new Date().getTime());
                obj.put("modeConfDO", obj2);
               String result = BCCAClient.sendMode(cd_api_url,obj,account);
                System.out.println("result:" + result);
                JSONObject resultjsonObject = JSONObject.parseObject(result);
                if (resultjsonObject.get("resultCode").equals("0101")) {
                    token =  BCCAClient.getNewToken(dgj_api_url,cd_api_url,serviceId,serviceKey,back_url,account);
                    obj.put("token", token);
                    redisUtil.set(serviceId,token);
                    result =BCCAClient.sendMode(cd_api_url,obj,account);
                    resultjsonObject = JSONObject.parseObject(result);
                    asyncTask.msControlRecord(pSbmogl.getSbmoglId(),pSbmogl.getCreatorId(),"1",null);
                }
                if (!resultjsonObject.get("resultCode").equals("0000")){
                    asyncTask.msControlRecord(pSbmogl.getSbmoglId(),pSbmogl.getCreatorId(),"2",resultjsonObject.getJSONObject("resultContent").getString("message"));
                    throw new  Exception(resultjsonObject.getString("resultDesc")+resultjsonObject.getJSONObject("resultContent").getString("message"));
                }

            }
        }
    }

    /**
     * @Description: TODO 根据组织机构ID查询3D服务信息
     * @Param: [userId]
     * @return: PSmhDevice
     * @Author: xujianjian
     * @Date: 2019/11/14 15:49
     */
    public PD3orgZc get3DserviceInfo(String borg_id) {
        PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
        PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
        criteria.andBelongOrgIdEqualTo(borg_id);
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
        if (pd3orgZcs.size() == 0) {
            return null;
        }
        return pd3orgZcs.get(0);
    }


    /**
     * @Description: TODO 根据USERID获取bcca服务信息
     * @Param: [userId]
     * @return: PSmhDevice
     * @Author: xujianjian
     * @Date: 2019/11/14 15:49
     */
    public PD3orgZc getBCCAserviceInfo(String userId) {
        PD3orgZc pd3orgZc = pd3orgZcMapper.selectServiceInfo(userId);
        return pd3orgZc;
    }

    /**
     * @Description: TODO 根据USERID获取bcca服务信息
     * @Param: [userId]
     * @return: PSmhDevice
     * @Author: xujianjian
     * @Date: 2019/11/14 15:49
     */
    public PD3orgZc getBCCAserviceInfoByAccount(String account) throws Exception {
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria1 = pSmhSettingExample.createCriteria();
        criteria1.andSmarthomeAccountEqualTo(account);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        if (pSmhSettings.size() == 0) {
            throw new Exception("当前账号不存在，或未订阅！");
        }
        PD3orgZc pd3orgZc = pd3orgZcMapper.selectServiceInfo(pSmhSettings.get(0).getCreatorId());
        return pd3orgZc;
    }
}
