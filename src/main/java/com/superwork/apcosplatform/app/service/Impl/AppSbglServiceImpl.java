package com.superwork.apcosplatform.app.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.app.service.AppAcountService;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.utils.*;
import com.superwork.apcosplatform.app.service.AppSbglService;
import com.superwork.apcosplatform.result.ResultDO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @program: code->AppSbglServiceImpl
 * @description: 设备
 * @author: xjj
 * @create: 2019-11-30 13:54
 **/
@Service
public class AppSbglServiceImpl implements AppSbglService {

    @Autowired
    AppAcountService appAcountService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    PSbglMapper pSbglMapper;
    @Autowired
    PSbmoglMapper pSbmoglMapper;
    @Autowired
    PYktMsgngxMapper pYktMsgngxMapper;
    @Autowired
    PYktZxjhMapper pYktZxjhMapper;
    @Autowired
    PControFormworkMapper pControFormworkMapper;
    @Autowired
    PSbzcLxMapper pSbzcLxMapper;
    @Autowired
    PWebPropertyInfoMapper pWebPropertyInfoMapper;
    @Autowired
    PWebPlanInfoMapper pWebPlanInfoMapper;
    @Autowired
    PWebProductCmdUpMapper pWebProductCmdUpMapper;
    @Autowired
    PWebProductCmdUpParamMapper pWebProductCmdUpParamMapper;
    @Autowired
    PSmhSettingMapper pSmhSettingMapper;

    @Autowired
    private Configuration configuration;

    @Value("${upload.filePath}")
    private String uploadPath;

    @Value("${serviceIp}")//本项目发布的地址
    private String serviceIp;
    @Value("${cd_api_url}")//核心平台地址
    private String cd_api_url;
    @Value("${back_url}")//核心平台回掉本项目地址
    private String back_url;
    @Value("${kf_api_url}")//开发者地址
    private String kf_api_url;
    @Value("${dgj_api_url}")//开发者地址
    private String dgj_api_url;



    @Override
    public PageInfo<PSbgl> listsb(PSbgl data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        if (data == null) {
            data = new PSbgl();
        }
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        data.setCreatorId(user.getUserId());
        List<PSbgl> listsb = pSbglMapper.listsb(data);
        PageInfo<PSbgl> objectPageInfo = new PageInfo<>(listsb);
        return objectPageInfo;
    }

    @Override
    public ResultDO<String> sbControl(Map<String, String> map) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String productCode = map.get("productCode");
        String serial_num = map.get("serial_num");
        String operation = map.get("operation");
        String controlParams = map.get("controlParams");
        String account = map.get("account");
        Object o = redisUtil.get(serviceId);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        } else {
            token = o.toString();
        }
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", serviceId);
        obj.put("serial_num", serial_num);
        obj.put("operation", operation);
        controlParams = URLEncoder.encode(controlParams, "utf-8");
        obj.put("controlParams", controlParams);
        obj.put("productId", productCode);
        String result = BCCAClient.sendDeviceCmd(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.sendDeviceCmd(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            JSONObject object2 = JSONObject.parseObject(object.get("resultContent").toString());
            if (object2.containsKey("code")) {
                if (!object2.get("code").equals("0")) {
                    throw new Exception(object2.get("message").toString());
                }
            } else {
                throw new Exception("返回信息格式错误，请联系管理员！");
            }
        } else {
            throw new Exception(object.get("resultDesc").toString());
        }
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> addJzly(PSbmogl pSbmogl) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        pSbmogl.setCreatorId(user.getUserId());
        pSbmogl.setCreatedTime(new Date());
        //先保存模式名称
        pSbmoglMapper.insert(pSbmogl);
        String msId = pSbmogl.getSbmoglId();//本地模式ID
        String account = pSbmogl.getAttr2();//账号
        List<PYktMsgngx> pYktMsgngxes = pSbmogl.getListMl();//命令
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        ArrayList<String> cmds = new ArrayList<>();
        for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
            pYktMsgngx.setCreatorId(user.getUserId());
            pYktMsgngx.setCreatedTime(new Date());
            pYktMsgngx.setEditorId(user.getUserId());
            pYktMsgngx.setMsid(msId); //模式ID
            String params = pYktMsgngx.getParams();
            if (params == null || "".equals(params)) {
                params = "none";
                pYktMsgngx.setParams("");
            }
            //保存命令
            pYktMsgngxMapper.insert(pYktMsgngx);
            cmds.add(pYktMsgngx.getWhid() + "_" + pYktMsgngx.getProductCode() + "_" + pYktMsgngx.getCode() + "_" + params);
        }
        //添加
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        obj2.put("account", account);
        obj2.put("enable_mode", "yes");//默认激活
        obj2.put("mode_name", pSbmogl.getSbmoglName());
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("cmds", cmds);
        obj.put("modeConfDO", obj2);
        String result = BCCAClient.addMode(cd_api_url, obj, account);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.addMode(cd_api_url, obj, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (jsonObject.get("resultCode").equals("0000")) {
            JSONObject object2 = JSONObject.parseObject(jsonObject.get("resultContent").toString());
            pSbmogl.setSbmoglYmsid(object2.get("modeId").toString());
            pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);
        } else {
            throw new Exception(jsonObject.get("resultDesc").toString());
        }

        return resultDO;
    }

    @Override
    public PageInfo<PSbmogl> listMs(PSbmogl data, Integer page, Integer limit) {
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        data.setCreatorId(user.getUserId());
        PageHelper.startPage(page, limit);
        List<PSbmogl> pSbmogls = pSbmoglMapper.listMs(data);
        for (PSbmogl pSbmogl : pSbmogls) {
            PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
            PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
            criteria.andMsidEqualTo(pSbmogl.getSbmoglId());
            List<PYktMsgngx> pYktMsgngxes = pYktMsgngxMapper.selectByExample(pYktMsgngxExample);
            pSbmogl.setListMl(pYktMsgngxes);
        }
        PageInfo<PSbmogl> pSbmoglPageInfo = new PageInfo<>(pSbmogls);
        return pSbmoglPageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> editMsById(PSbmogl pSbmogl) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        //更新模式
        pSbmogl.setEditorId(user.getUserId());
        pSbmogl.setEditedTime(new Date());
        pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);

        //删除本地模式命令
        PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
        PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
        criteria.andMsidEqualTo(pSbmogl.getSbmoglId());
        pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);

        String sbmoglId = pSbmogl.getSbmoglId();//本地模式ID
        String ymmsId = pSbmogl.getSbmoglYmsid();//云模式ID
        String account = pSbmogl.getAttr2();//账号
        List<PYktMsgngx> pYktMsgngxes = pSbmogl.getListMl();//命令
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        ArrayList<String> cmds = new ArrayList<>();
        for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
            pYktMsgngx.setCreatorId(user.getUserId());
            pYktMsgngx.setEditorId(user.getUserId());
            pYktMsgngx.setMsid(sbmoglId); //本地模式ID
            String params = pYktMsgngx.getParams();
            if (params == null || "".equals(params)) {
                params = "none";
                pYktMsgngx.setParams("");
            }
            //保存命令
            pYktMsgngxMapper.insert(pYktMsgngx);
            cmds.add(pYktMsgngx.getWhid() + "_" + pYktMsgngx.getProductCode() + "_" + pYktMsgngx.getCode() + "_" + params);
        }

        //添加
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        obj2.put("id", ymmsId);
        obj2.put("account", account);
        obj2.put("mode_name", pSbmogl.getSbmoglName());
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("cmds", cmds);
        obj.put("modeConfDO", obj2);
        String result = BCCAClient.updateMode(cd_api_url, obj, account);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.updateMode(cd_api_url, obj, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000")) {
            throw new Exception(jsonObject.get("resultDesc").toString());
        }
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> delms(PSbmogl pSbmogl) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String account = pSbmogl.getAttr2();
        //先删除原先的模式
        pSbmoglMapper.deleteByPrimaryKey(pSbmogl.getSbmoglId());
        //删除模式下的命令
        PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
        PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
        criteria.andMsidEqualTo(pSbmogl.getSbmoglId());
        pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }

        JSONObject obj2 = new JSONObject();
        obj2.put("id", pSbmogl.getSbmoglYmsid());
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("modeConfDO", obj2);
        String result = BCCAClient.deletMode(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.deletMode(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (!object.get("resultCode").equals("0000")) {
            throw new Exception("原账户" + account + "模式删除失败！");
        }
        return resultDO;

    }

    @Override
    public  ResultDO<String> msControl(String msId) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(msId);
        String account = pSbmogl.getAttr2();
        if (pSbmogl.getSbmoglYmsid() == null) {
            throw new Exception("未设置命令，无法控制！");
        }
        Map<String, String> info = appAcountService.getInfo();
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
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        JSONObject obj2 = new JSONObject();
        obj2.put("id", pSbmogl.getSbmoglYmsid());
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("modeConfDO", obj2);
        String result = BCCAClient.sendMode(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.sendMode(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (!object.get("resultCode").equals("0000")) {
            throw new Exception("模式控制失败！" + JSONObject.parseObject(object.get("resultContent").toString()).get("message"));
        }
        return resultDO;
    }

    @Override
    public  ResultDO<String> addZxjh(PYktZxjh pYktZxjh) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String account = pYktZxjh.getZxjhYptzh();
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("plan_name", pYktZxjh.getZxjhMc());//执行计划名称
        obj.put("ser_code", "1");
        obj.put("enable", pYktZxjh.getZxjhZt());//计划状态
        String result = BCCAClient.addExcutionPlan(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.addExcutionPlan(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            String yptjh_id = object.get("resultContent").toString();
            pYktZxjh.setYptjhId(yptjh_id);
            pYktZxjhMapper.insert(pYktZxjh);
        } else {
            throw new Exception("添加执行计划失败！" + object.get("resultDesc"));
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> updateZxjh(PYktZxjh pYktZxjh) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String account = pYktZxjh.getZxjhYptzh();
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("account", account);
        obj.put("plan_name", pYktZxjh.getZxjhMc());
        obj.put("ser_code", "1");
        obj.put("enable", pYktZxjh.getZxjhZt());
        obj.put("id", pYktZxjh.getYptjhId());
        String result = BCCAClient.updateExcutionPlanById(cd_api_url, obj, account);
        if (result == null) {
            throw new Exception("修改执行计划接口无返回！");
        }
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.updateExcutionPlanById(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            pYktZxjhMapper.updateByPrimaryKeySelective(pYktZxjh);
        } else {
            throw new Exception("修改计划修改失败！" + object.get("resultDesc"));
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> delZxjh(PYktZxjh pYktZxjh) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        String account = pYktZxjh.getZxjhYptzh();
        Map<String, String> info = appAcountService.getInfo();
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
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("processTime", new Date().getTime());
        obj.put("id", pYktZxjh.getYptjhId());
        String result = BCCAClient.deleteExcutionPlanById(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.deleteExcutionPlanById(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            pYktZxjhMapper.deleteByPrimaryKey(pYktZxjh.getZxjhId());
        } else {
            throw new Exception("执行计划删除失败！" + object.get("resultDesc"));
        }
        return resultDO;
    }

    @Override
    public  ResultDO<String> zxjhJcadd(Map<String, Object> map) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        String account = map.get("account").toString();
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        JSONObject obj = new JSONObject(map);
        System.out.println(obj);
        Object o = redisUtil.get(serviceId);
        String token = null;
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
        } else {
            token = o.toString();
        }
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("ser_order", "1");// 序号
        String result = BCCAClient.addExcutionPlanProcess(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.addExcutionPlanProcess(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (!object.get("resultCode").equals("0000")) {
            throw new Exception("新增执行计划进程失败！" + object.get("resultContent").toString());
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> deljc(String id, String account) throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        Object o = redisUtil.get(serviceId);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
        } else {
            token = o.toString();
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("id", id);
        String result = BCCAClient.deleteExcutionPlanProcess(cd_api_url, obj, account);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.deleteExcutionPlanProcess(cd_api_url, obj, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000")) {
            throw new Exception(jsonObject.get("resultContent").toString());
        }
        return resultDO;
    }

    @Override
    public ResultDO<List<ExcutionPlanProcess>> zxjhJcList(PYktZxjh pYktZxjh) throws Exception {
        ResultDO<List<ExcutionPlanProcess>> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String yptjh_id = pYktZxjh.getYptjhId();
        String account = pYktZxjh.getZxjhYptzh();
        Object o = redisUtil.get(serviceId);
        String token = "";
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
        } else {
            token = o.toString();
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", serviceId);
        obj.put("token", token);
        obj.put("plan_id", yptjh_id);
        String result = BCCAClient.queryExcutionPlanProcess(cd_api_url, obj, account);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(cd_api_url, serviceId, serviceKey, back_url, account);
            redisUtil.set(serviceId, token);
            obj.put("token", token);
            result = BCCAClient.queryExcutionPlanProcess(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            String jclist = object.get("resultContent").toString();
            ArrayList<ExcutionPlanProcess> list = (ArrayList<ExcutionPlanProcess>) JSONArray.parseArray(jclist, ExcutionPlanProcess.class);
            resultDO.setData(list);
        } else {
            throw new Exception(object.get("resultDesc").toString());
        }
        return resultDO;
    }

    /**
     * @Description: TODO 控制模板生成URL
     * @Param: [productCode]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/12/3 11:34
     */
    @Override
    public String selmodle(String mac) throws  IOException {
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        PControFormworkExample pControFormworkExample = new PControFormworkExample();
        PControFormworkExample.Criteria criteria1 = pControFormworkExample.createCriteria();
        criteria1.andCodeEqualTo(mac).andCreatorIdEqualTo(user.getUserId()).andAttr1EqualTo("mobile");//手机端
        List<PControFormwork> pControFormworks = pControFormworkMapper.selectByExampleWithBLOBs(pControFormworkExample);
        String htmlmodle = null;
        if (pControFormworks.size() > 0 && !StringUtils.isEmpty(pControFormworks.get(0).getHtmlmodle())) {
            htmlmodle = pControFormworks.get(0).getHtmlmodle();
        } else {
            List<PSbzcLx> pSbzcLxes = pSbzcLxMapper.selectHtmlWithMac(mac);
            if (pSbzcLxes.size() > 0 && !StringUtils.isEmpty(pSbzcLxes.get(0).getHtmlmobile())) {
                htmlmodle = pSbzcLxes.get(0).getHtmlmobile();
            }
        }
        if (htmlmodle != null) {
            String id = IDUtils.createID();
            String name =id+".html";
            File targetFile = new File(new File(uploadPath).getAbsolutePath()+"/temporary/"+name);
            FileOutputStream fileOutputStream = null;
            if(!targetFile.exists()){
                targetFile.getParentFile().mkdirs();
            }
            htmlmodle = addString(htmlmodle);
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8"));
            writer.write(htmlmodle);
            writer.flush();
            writer.close();
            return targetFile.getAbsolutePath();
        } else {
            return null;
        }


    }

    @Override
    public  ResultDO<List<SbzclxTemp>> getMblist(String productCode) throws Exception {
        ResultDO<List<SbzclxTemp>> resultDO = new ResultDO<>();
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String account = null;
        String token = HTTPclient.getNewAccessToken(dgj_api_url,serviceId,serviceKey);
//        String token = BCCAClient.getNewToken(dgj_api_url,kf_api_url, serviceId, serviceKey, back_url, account);
//        String token = BCCAClient.getToken(kf_api_url, serviceId, serviceKey, back_url, account);
        String result = BCCAClient.getTemplateList(kf_api_url, serviceId, token, account, productCode);
        JSONObject jsonObject = JSONObject.parseObject(result);
        ArrayList<SbzclxTemp> list = new ArrayList<>();
        if (jsonObject.get("resultCode").equals("0101")) {
            token = HTTPclient.getNewAccessToken(dgj_api_url,serviceId,serviceKey);
//            token = BCCAClient.getNewToken(dgj_api_url,kf_api_url, serviceId, serviceKey, back_url, account);
//            token = BCCAClient.getToken(kf_api_url, serviceId, serviceKey, back_url, account);
            result = BCCAClient.getTemplateList(kf_api_url, serviceId, token, account, productCode);
            jsonObject = JSONObject.parseObject(result);
        }
        if (jsonObject.get("resultCode").equals("0000")) {
            String resultContent = jsonObject.get("resultContent").toString();
            list = (ArrayList<SbzclxTemp>) JSONArray.parseArray(resultContent, SbzclxTemp.class);
            resultDO.setData(list);
        } else {
            throw new Exception(jsonObject.get("resultDesc").toString());
        }
        return resultDO;
    }

    @Override
    public void setMrmb(String id, String mac) throws Exception {
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        String account = null;
        if (StringUtils.isEmpty(serviceId)) {
            throw new Exception("请先完善服务信息！");
        }
        String token = HTTPclient.getNewAccessToken(dgj_api_url,serviceId,serviceKey);
        String result = BCCAClient.getTempLate(kf_api_url, serviceId, token, account, id);
        JSONObject jsonObject2 = JSONObject.parseObject(result);
        if (jsonObject2.get("resultCode").equals("0101")) {
            token = HTTPclient.getNewAccessToken(dgj_api_url,serviceId,serviceKey);
            result = BCCAClient.getTempLate(kf_api_url, serviceId, token, account, id);
            jsonObject2 = JSONObject.parseObject(result);
        }
        if (jsonObject2.get("resultCode").equals("0000")) {
            JSONObject resultContent = JSONObject.parseObject(jsonObject2.get("resultContent").toString());
            String template_html = resultContent.get("template_html").toString();
            if (template_html == null || "".equals(template_html)) {
                throw new Exception("模板代码为空，默认模板设置失败！");
            }
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            PControFormworkExample pControFormworkExample = new PControFormworkExample();
            PControFormworkExample.Criteria criteria = pControFormworkExample.createCriteria();
            criteria.andCreatorIdEqualTo(user.getUserId()).andCodeEqualTo(mac).andAttr1EqualTo("mobile");//移动端模板
            pControFormworkMapper.deleteByExample(pControFormworkExample);
            PControFormwork pControFormwork = new PControFormwork();
            pControFormwork.setCode(mac);
            pControFormwork.setCreatorId(user.getUserId());
            pControFormwork.setCreatedTime(new Date());
            pControFormwork.setHtmlmodle(template_html);
            pControFormwork.setAttr1("mobile");//移动端模板
            pControFormworkMapper.insert(pControFormwork);
        } else {
            throw new Exception(jsonObject2.get("resultDesc").toString());
        }
    }

    @Override
    public ResultDO<Integer> syncPlan(String productCode) throws Exception {
        ResultDO<Integer> resultDO = new ResultDO<>();
        int msg = 0;
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        String token = HTTPclient.getNewAccessToken(dgj_api_url, serviceId, serviceKey);
        String mothod = "/thirdInterface/findPropertyByProCode";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", serviceId);
        obj.put("product_code", productCode);
        String result = HTTPclient.httpRequestPostMethod(dgj_api_url + mothod, obj);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("code").toString().equals("200")) {
            String resultContent = object.get("data").toString();
            JSONArray objects = JSONArray.parseArray(resultContent);
            MyJsonUtils.convert(objects);
            List<PWebPropertyInfo> pWebPropertyInfos = JSONArray.parseArray(objects.toString(), PWebPropertyInfo.class);
            msg = pWebPropertyInfos.size();
            resultDO.setData(msg);
            for (PWebPropertyInfo pWebPropertyInfo : pWebPropertyInfos) {
                pWebPropertyInfoMapper.deleteByPrimaryKey(pWebPropertyInfo.getId());
                pWebPropertyInfoMapper.insert(pWebPropertyInfo);
                List<PWebPlanInfo> webPlanInfo = pWebPropertyInfo.getWebPlanInfo();
                for (PWebPlanInfo pWebPlanInfo : webPlanInfo) {
                    pWebPlanInfoMapper.deleteByPrimaryKey(pWebPlanInfo.getId());
                    pWebPlanInfoMapper.insert(pWebPlanInfo);
                    if (pWebPlanInfo.getWebProductCmdUp() != null) {
                        pWebProductCmdUpMapper.deleteByPrimaryKey(pWebPlanInfo.getWebProductCmdUp().getId());
                        pWebProductCmdUpMapper.insert(pWebPlanInfo.getWebProductCmdUp());
                    }
                    if (pWebPlanInfo.getWebProductCmdUpParam() != null) {
                        pWebProductCmdUpParamMapper.deleteByPrimaryKey(pWebPlanInfo.getWebProductCmdUpParam().getId());
                        pWebProductCmdUpParamMapper.insert(pWebPlanInfo.getWebProductCmdUpParam());
                    }
                }
            }
        } else {
            throw new Exception(object.get("msg").toString());
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> syncSbLx() throws Exception {
        ResultDO<String> resultDO = new ResultDO<>();
        String msg = "";
        int cgcount = 0; // 成功设备数
        int snum = 0;//同步总数量
        Map<String, String> info = appAcountService.getInfo();
        String serviceId = info.get("serviceId");
        String serviceKey = info.get("serviceKey");
        String account = null;
        if (StringUtils.isEmpty(serviceId)) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("请先完善服务信息！");
            return resultDO;
        }
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        String token = HTTPclient.getNewAccessToken(dgj_api_url,serviceId,serviceKey);
//        String token = BCCAClient.getNewToken(dgj_api_url,kf_api_url, serviceId, serviceKey, back_url, account);
//        String token = BCCAClient.getToken(kf_api_url, serviceId, serviceKey, back_url, account);
        String result = BCCAClient.getSblxList(kf_api_url, serviceId, token, account);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0000")) {
            String resultContent = jsonObject.get("resultContent").toString();
            ArrayList<Sbzclx2> list = (ArrayList<Sbzclx2>) JSONArray.parseArray(resultContent, Sbzclx2.class);
            snum = list.size();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    PSbzcLx pSbzcLx = new PSbzcLx();
                    pSbzcLx.setYtlx("3");
                    pSbzcLx.setMldm(list.get(i).getProductCode());
                    pSbzcLx.setMlmc(list.get(i).getTypeName());
                    pSbzcLx.setSfty(new BigDecimal(1));
                    pSbzcLx.setAttr1(list.get(i).getTypeTelplate());
                    pSbzcLx.setBz(list.get(i).getTypeDescription());
                    pSbzcLx.setCreatorId(user.getUserId());
                    pSbzcLx.setCreatedTime(new Date());
                    pSbzcLx.setEditorId(user.getUserId());
                    pSbzcLx.setHtmlmodle(list.get(i).getPcTemplate());
                    pSbzcLx.setHtmlmobile(list.get(i).getMobileTemplate());
                    //删除之前的设备类型
                    PSbzcLxExample pSbzcLxExample = new PSbzcLxExample();
                    PSbzcLxExample.Criteria criteria = pSbzcLxExample.createCriteria();
                    criteria.andMldmEqualTo(list.get(i).getProductCode());
                    pSbzcLxMapper.deleteByExample(pSbzcLxExample);
                    cgcount = cgcount + 1;
                    //添加
                    pSbzcLxMapper.insert(pSbzcLx);
                }
            }
        } else {
            throw new Exception(jsonObject.get("resultDesc").toString());
        }
        msg = "同步设备类型数量:" + snum + "，成功添加：" + cgcount;
        resultDO.setData(msg);
        return resultDO;
    }

    @Override
    public void delmb(PSbgl pSbgl) {
        String account = pSbgl.getAccount();
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(account);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        PControFormworkExample pControFormworkExample = new PControFormworkExample();
        PControFormworkExample.Criteria criteria1 = pControFormworkExample.createCriteria();
        criteria1.andCreatorIdEqualTo(pSmhSettings.get(0).getCreatorId()).andCodeEqualTo(pSbgl.getDeviceMac()).andAttr1EqualTo("mobile");
        pControFormworkMapper.deleteByExample(pControFormworkExample);
    }



    public String addString(String res){
        String mi ="\n<script type=\"text/javascript\" src=\""+serviceIp+"/js/uni.webview.1.5.2.js\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "\t\t\t\tdocument.addEventListener('UniAppJSBridgeReady', function() {\n" +
                "\t\t\t\t\tvar sendControlBtns =  document.getElementsByClassName(\"sendControlBtn\");\n" +
                "\t\t\t\t\tif(sendControlBtns && sendControlBtns.length > 0) {\n" +
                "\t\t\t\t\t\tconsole.log('sendControlBtn 不为空.');\n" +
                "\t\t\t\t\t\tfor(var i = 0; i < sendControlBtns.length; i++) {\n" +
                "\t\t\t\t\t\t\tsendControlBtns[i].addEventListener('click', function() {\n" +
                "\t\t\t\t\t\t\t\tonSendControlListener(this)\n" +
                "\t\t\t\t\t\t\t}, false);\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t} else {\n" +
                "\t\t\t\t\t\tconsole.log('sendControlBtn is null.');\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\tfunction onSendControlListener(btn) {\n" +
                "\t\t\t\t\t\tvar datas = getCmdParams(btn);\n" +
                "\t\t\t\t\t\tconsole.log(JSON.stringify(datas));\n" +
                "\t\t\t\t\t\tif(datas){\n" +
                "\t\t\t\t\t\t\tconsole.log(JSON.stringify(datas));\n" +
                "\t\t\t\t\t\t\tuni.postMessage({\n" +
                "\t\t\t\t\t\t\t            data: datas\n" +
                "\t\t\t\t\t\t\t   });\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t});\n" +
                "</script>\n";
        int i = res.lastIndexOf("</script>");
        String head = res.substring(0, i+9);
        String end = res.substring(i+9);
        return head+mi+end;

    }




    public  String createIndexHtml(String filepath, String rec, Configuration configuration) {
        String url = "";
        try {
            /**获取输出目标文件输出流------开始*/
            File folder = new File(filepath);
            //如果文件夹不存在
            if (!folder.exists()) {
                folder.mkdir();
            }
            System.out.println(filepath);
            String id = IDUtils.createID();
//            String indexFileName = "ControTemplate.html";
            String indexFileName = id+".html";
            File indexHtml = new File(folder, indexFileName);
            //如果html文件不存在
            if (!indexHtml.exists()) {
                indexHtml.createNewFile();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexHtml), "UTF-8"));
            /**获取输出目标文件输出流------结束*/
            //获取数据
            Map<String, Object> map = new HashMap<>();
            map.put("content", rec);
            //获取模板
            Template template = configuration.getTemplate("content.ftl");
            //把数据和输出文件信息交给模板得到静态html文件
            template.process(map, out);
            out.flush();
            out.close();
            url = "/html/temporary/" + indexFileName;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return url;
    }
}
