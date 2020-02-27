package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.service.InterFaceService;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.HTTPclient;
import com.superwork.apcosplatform.utils.MyJsonUtils;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.SmhSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Jianjian Xu
 * @create: 2019/10/24 16:18
 * @description:
 */
@Service
public class SmhSettingServiceImpl implements SmhSettingService {


    @Autowired
    PSmhSettingMapper pSmhSettingMapper;

    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    PSbzcLxMapper pSbzcLxMapper;

    @Autowired
    PZnsbMszhMapper pZnsbMszhMapper;

    @Autowired
    PSbmoglMapper pSbmoglMapper;

    @Autowired
    PYktMsgngxMapper pYktMsgngxMapper;

    @Autowired
    PYktZxjhMapper pYktZxjhMapper;

    @Autowired
    PWebPropertyInfoMapper pWebPropertyInfoMapper;

    @Autowired
    PControFormworkMapper pControFormworkMapper;


    @Autowired
    PWebProductCmdUpMapper pWebProductCmdUpMapper;

    @Autowired
    PWebProductCmdUpParamMapper pWebProductCmdUpParamMapper;


    @Autowired
    PWebPlanInfoMapper pWebPlanInfoMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    InterFaceService interFaceService;

    @Autowired
    PD3orgZcMapper pd3orgZcMapper;

    @Autowired
    PSysUserMapper pSysUserMapper;


    @Value("${cd_api_url}")
    private String cd_api_url;//成都平台
    @Value("${back_url}")
    private String back_url;//回掉地址
    @Value("${kf_api_url}")
    private String kf_api_url;//开发者平台api
    @Value("${dgj_api_url}")
    private String dgj_url;


    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void insert(PSmhSetting data) throws Exception {
        pSmhSettingMapper.insert(data);
//        sbdy(data);
    }

    @Override
    public PageInfo<PSmhSetting> listAccount_3(PSmhSetting data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.listAccount_3(data);
        PageInfo<PSmhSetting> objectPageInfo = new PageInfo<>(pSmhSettings);
        return objectPageInfo;
    }

    @Override
    public void editAccountInfo(PSmhSetting data) {
        pSmhSettingMapper.updateByPrimaryKeySelective(data);
    }

    /**
     * @Description: TODO 账号订阅
     * @Param: [data]
     * @return: void
     * @Author: xujianjian
     * @Date: 2020/1/2 10:53
     */
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void sbdy(PSmhSetting data) throws Exception {
        String account = data.getSmarthomeAccount();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo ==null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        String result = BCCAClient.accountSubscrip(cd_api_url, service_id, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            result = BCCAClient.accountSubscrip(cd_api_url, service_id, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000") && !jsonObject.get("resultCode").equals("0003")) {//成功或重复订阅都算成功
            throw new Exception(jsonObject.get("resultDesc").toString()+jsonObject.getString("resultContent"));
        }

    }

    @Override
    public void delSbdy(PSmhSetting data) throws Exception {
        String account = data.getSmarthomeAccount();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        String result = BCCAClient.removeSubscripRelation(cd_api_url, service_id, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            result = BCCAClient.removeSubscripRelation(cd_api_url, service_id, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000")) {
            throw new Exception(jsonObject.get("resultDesc") + "，" + jsonObject.get("resultContent"));
        } else {
            PSmhSetting pSmhSetting = new PSmhSetting();
            pSmhSetting.setSettingId(data.getSettingId());
            pSmhSetting.setAttr2("2");//未订阅
            pSmhSettingMapper.updateByPrimaryKeySelective(pSmhSetting);
        }

    }

    @Override
    public List<PSmhSetting> find(PSmhSetting smhSetting) {
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(smhSetting.getSmarthomeAccount());
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        return pSmhSettings;
    }

    @Override
    public void updateDyjg(PSmhSetting smhSetting) {
        String attr2 = smhSetting.getAttr2();//返回的结果 1: 通过 2.未通过
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(smhSetting.getSmarthomeAccount());
        if ("1".equals(attr2)) {
            smhSetting.setAttr2("1");//订阅
            pSmhSettingMapper.updateByExampleSelective(smhSetting, pSmhSettingExample);
        } else {
            smhSetting.setAttr2("2");
            pSmhSettingMapper.updateByExampleSelective(smhSetting, pSmhSettingExample);
        }
    }


    /**
     * 同步智能设备
     */
    @Transactional(rollbackFor = Exception.class)
    public String syncSbxx(PSmhSetting smhSetting) throws Exception {
        String account = smhSetting.getSmarthomeAccount();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        //回调账号设备更新
        if (bccaServiceInfo == null) {
            bccaServiceInfo = getBCCAserviceInfoByAccount(account);
        }
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        //先重redis中获取token
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        String msg = "";
        int snum = 0;//同步总数量
        String result = BCCAClient.getDeviceList(cd_api_url, service_id, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_key, token);
            result = BCCAClient.getDeviceList(cd_api_url, service_id, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("resultContent").toString());
        String sblist = jsonObject2.get("deviceList").toString();
        ArrayList<PSbgl2> list = (ArrayList<PSbgl2>) JSONArray.parseArray(sblist, PSbgl2.class);
        //查询此账号下的设备
        PSbglExample pSbglExample = new PSbglExample();
        PSbglExample.Criteria criteria1 = pSbglExample.createCriteria();
        criteria1.andAccountEqualTo(account);
        List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);

        pSbglMapper.deleteByExample(pSbglExample);

        if (list != null && list.size() > 0) {
            PSysUser user = ComonUtils.getUser();
            String user_id = "";
            //上报同步时
            if (user == null) {
                user_id = bccaServiceInfo.getCreatorId();
            } else {
                user_id = user.getUserId();
            }
            snum = list.size();
            for (int i = 0; i < list.size(); i++) {
                PSbgl pSbgl = changEntity(list.get(i));
                pSbgl.setCreatorId(user_id);
                pSbgl.setCreatedTime(new Date());
                for (PSbgl sbgl : pSbgls) {
                    if(pSbgl.getId().equals(sbgl.getId())){
                        pSbgl.setAttr2(sbgl.getAttr2());
                        pSbgl.setAttr3(sbgl.getAttr3());
                    }
                }
                pSbglMapper.insert(pSbgl);
            }
        }
        msg = "同步设备数量:" + snum;
        return msg;
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
        pSbgl.setAttr1(pSbgl2.getRegister_gateway_mac());//注册网关MAC
        return pSbgl;
    }

    /**
     * @param * @param data
     * @return void
     * @Description //TODO 删除第三个账号
     * @author xjj
     * @date 2019/10/25
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delAccount(PSmhSetting data) throws Exception {
        PSysUser user = ComonUtils.getUser();
        PSmhSetting pSmhSetting = pSmhSettingMapper.selectByPrimaryKey(data.getSettingId());
        if (pSmhSetting == null) {
            throw new Exception("账号不存在！");
        } else {
            if (!pSmhSetting.getCreatorId().equals(user.getUserId())) {
                throw new Exception("该账号不是你订阅的账号不能删除！");
            }
        }
        try {
            delSbdy(data);
        } catch (Exception e) {
            System.out.println(data.getSmarthomeAccount()+"取消订阅失败");
            e.printStackTrace();
        }
        pSmhSettingMapper.deleteByPrimaryKey(pSmhSetting.getSettingId());
        PSbglExample pSbglExample1 = new PSbglExample();
        PSbglExample.Criteria criteria = pSbglExample1.createCriteria();
        criteria.andAccountEqualTo(pSmhSetting.getSmarthomeAccount());
        pSbglMapper.deleteByExample(pSbglExample1);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dyAccount(String data) throws Exception {
        PSmhSetting pSmhSetting = new PSmhSetting();
        pSmhSetting.setSmarthomeAccount(data);
        List<PSmhSetting> pSmhSettings = find(pSmhSetting);
        if (pSmhSettings.size() > 0) {
            PSysUser user = pSysUserMapper.selectByPrimaryKey(pSmhSettings.get(0).getCreatorId());
            throw new Exception("账号已存在,请勿重复订阅！账号归属于："+user.getUserAccount());
        }
        PSysUser user = ComonUtils.getUser();
        pSmhSetting.setCreatorId(user.getUserId());
        pSmhSetting.setAttr2("3");//订阅审核中
        pSmhSetting.setCreatedTime(new Date());
        pSmhSetting.setAttr4(user.getUserAccount());
        pSmhSettingMapper.insert(pSmhSetting);
        pSmhSetting.setSettingId(null);
        sbdy(pSmhSetting);
    }

    @Override
    public PSmhSetting findByid(String setting_id) {
        PSmhSetting pSmhSetting = pSmhSettingMapper.selectByPrimaryKey(new BigDecimal(Integer.valueOf(setting_id)));
        return pSmhSetting;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String syncSbLx(PSbzcLx pSbzcLx) throws Exception {
        String msg = "";
        int cgcount = 0; // 成功设备数
        int czcount = 0; // 修改设备数
        int snum = 0;//同步总数量
        String user_id = pSbzcLx.getEditorId();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
//        PSmhDevice bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId()) || StringUtils.isEmpty(bccaServiceInfo.getServiceKey())) {
            throw new Exception("请先完善服务接入信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        String account = null;
        String token = HTTPclient.getNewAccessToken(dgj_url, service_id, service_key);
        String result = BCCAClient.getSblxList(kf_api_url, service_id, token, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0000")) {
            String resultContent = jsonObject.get("resultContent").toString();
            ArrayList<Sbzclx2> list = (ArrayList<Sbzclx2>) JSONArray.parseArray(resultContent, Sbzclx2.class);
            snum = list.size();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    pSbzcLx = new PSbzcLx();
                    pSbzcLx.setYtlx("3");
                    pSbzcLx.setMldm(list.get(i).getProductCode());
                    pSbzcLx.setMlmc(list.get(i).getTypeName());
                    pSbzcLx.setSfty(new BigDecimal(1));
                    pSbzcLx.setAttr1(list.get(i).getTypeTelplate());
                    pSbzcLx.setBz(list.get(i).getTypeDescription());
                    pSbzcLx.setCreatorId(user_id);
                    pSbzcLx.setCreatedTime(new Date());
                    pSbzcLx.setEditorId(user_id);
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
        return msg;
    }

    /**
     * 分页查询设备类型
     */
    @Override
    public PageInfo<PSbzcLx> listsblx(PSbzcLx data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PSbzcLx> listsblx = pSbzcLxMapper.listsblx(data);
        PageInfo<PSbzcLx> pSbzcLxPageInfo = new PageInfo<>(listsblx);
        return pSbzcLxPageInfo;
    }

    @Override
    public List<SbzclxTemp> getMblist(PSbgl data) throws Exception {
        String productCode = data.getProductCode();
        String account = null;
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
//        PSmhDevice bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (StringUtils.isEmpty(bccaServiceInfo.getServiceId()) || StringUtils.isEmpty(bccaServiceInfo.getServiceKey())) {
            throw new Exception("请先完善服务接入信息！");
        }
       String service_id = bccaServiceInfo.getServiceId();
       String service_key = bccaServiceInfo.getServiceKey();
        String token = HTTPclient.getNewAccessToken(dgj_url, service_id, service_key);
        String result = BCCAClient.getTemplateList(kf_api_url, service_id, token, account, productCode);
        JSONObject jsonObject = JSONObject.parseObject(result);
        ArrayList<SbzclxTemp> list = new ArrayList<>();
        if (jsonObject.get("resultCode").equals("0000")) {
            String resultContent = jsonObject.get("resultContent").toString();
            list = (ArrayList<SbzclxTemp>) JSONArray.parseArray(resultContent, SbzclxTemp.class);
        } else if (jsonObject.get("resultCode").equals("0101")) {
            token = HTTPclient.getNewAccessToken(dgj_url, service_id, service_key);
            result = BCCAClient.getTemplateList(kf_api_url, service_id, token, account, productCode);
            jsonObject = JSONObject.parseObject(result);
            if (jsonObject.get("resultCode").equals("0000")) {
                String resultContent = jsonObject.get("resultContent").toString();
                list = (ArrayList<SbzclxTemp>) JSONArray.parseArray(resultContent, SbzclxTemp.class);
            } else {
                throw new Exception(jsonObject.get("resultDesc").toString());
            }
        } else {

        }
        return list;
    }

    @Override
    public PSbzcLx selSbzcByCode(PSbzcLx sbzcLx) {
        PSbzcLxExample pSbzcLxExample = new PSbzcLxExample();
        PSbzcLxExample.Criteria criteria = pSbzcLxExample.createCriteria();
        criteria.andMldmEqualTo(sbzcLx.getMldm());
        List<PSbzcLx> pSbzcLxes = pSbzcLxMapper.selectByExampleWithBLOBs(pSbzcLxExample);
        if (pSbzcLxes.size() == 0) {
            return null;
        }
        return pSbzcLxes.get(0);
    }

    @Override
    public PageInfo<PSbmogl> listMs(PSbmogl data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PSbmogl> pZnsbMszhs = pSbmoglMapper.listMs(data);
        PageInfo<PSbmogl> objectPageInfo = new PageInfo<>(pZnsbMszhs);
        return objectPageInfo;
    }

    @Override
    public void addJzly(PSbmogl data) {
        pSbmoglMapper.insert(data);
    }

    @Override
    public void editMsById(PSbmogl data) {
        pSbmoglMapper.updateByPrimaryKeySelective(data);


    }

    @Transactional(rollbackFor = Exception.class)
    public void setMsml(List<PYktMsgngx> pYktMsgngxes, String msId, PSysUser user) throws Exception {
        PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(msId);
        String account = pSbmogl.getAttr2();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        //先删除原先的模式
        if (pSbmogl.getSbmoglYmsid() != null) {//云服务模式id
            JSONObject obj2 = new JSONObject();
            obj2.put("id", pSbmogl.getSbmoglYmsid());
            JSONObject obj = new JSONObject();
            obj.put("service_id", service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            obj.put("modeConfDO", obj2);
            //发送删除模式
            String result = BCCAClient.deletMode(cd_api_url, obj, account);
            if (result != null) {
                JSONObject object = JSONObject.parseObject(result);
                if (object.get("resultCode").equals("0101")) {
                    token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
                    redisUtil.set(service_id, token);
                    obj.put("token", token);
                    result = BCCAClient.deletMode(cd_api_url, obj, account);
                    object = JSONObject.parseObject(result);
                }
                if (!object.get("resultCode").equals("0000")) {
                    throw new Exception("原账户" + account + "模式删除失败！");
                }
            }
            //本地删除模式下的命令
            PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
            PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
            criteria.andMsidEqualTo(pSbmogl.getSbmoglId());
            pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);

        }
        //新增模式
        ArrayList<String> cmds = new ArrayList<>();
        for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
            pYktMsgngx.setCreatorId(user.getUserId());
            pYktMsgngx.setEditorId(user.getUserId());
            pYktMsgngx.setMsid(msId); //模式ID
            String params = pYktMsgngx.getParams();
            if (params == null || "".equals(params)) {
                params = "none";
                pYktMsgngx.setParams("");
            }
            pYktMsgngxMapper.insert(pYktMsgngx);
            cmds.add(pYktMsgngx.getWhid() + "_" + pYktMsgngx.getProductCode() + "_" + pYktMsgngx.getCode() + "_" + params);
        }
        //添加
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        obj2.put("account", account);
        obj2.put("enable_mode", "yes");
        obj2.put("mode_name", pSbmogl.getSbmoglName());
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("processTime", new Date().getTime());
        obj.put("cmds", cmds);
        obj.put("modeConfDO", obj2);
        String result = BCCAClient.addMode(cd_api_url, obj, account);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
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


    }

    /**
     * @param * @param userId
     * @return java.util.List<PSmhSetting>
     * @Description //TODO 查询当前登陆者所订阅的账号
     * @author xjj
     * @date 2019/10/29
     */
    @Override
    public List<PSmhSetting> getAccount(String userId) {
//        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
//        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
//        criteria.andCreatorIdEqualTo(userId).andSfktEqualTo("2");//选择当前登陆者创建的有效账号
        PSmhSetting pSmhSetting = new PSmhSetting();
        pSmhSetting.setCreatorId(userId);
        pSmhSetting.setSfkt("2");
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.listAccount_3(pSmhSetting);
//        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        return pSmhSettings;
    }

    @Override
    public void msControl(String data) throws Exception {
        PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(data);
        String account = pSbmogl.getAttr2();
        if (pSbmogl.getSbmoglYmsid() == null) {
            throw new Exception("未设置命令，无法控制！");
        }
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        JSONObject obj2 = new JSONObject();
        obj2.put("id", pSbmogl.getSbmoglYmsid());
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("processTime", new Date().getTime());
        obj.put("modeConfDO", obj2);
        String result = BCCAClient.sendMode(cd_api_url, obj, account);
        System.out.println("result:" + result);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            obj.put("token", token);
            result = BCCAClient.sendMode(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }

        if (!object.get("resultCode").equals("0000")) {
            throw new Exception("模式控制失败！" + JSONObject.parseObject(object.get("resultContent").toString()).get("message"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delms(PSbmogl data) throws Exception {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        //先删除原先的模式
        PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(data.getSbmoglId());
        String account = pSbmogl.getAttr2();
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        if (pSbmogl.getSbmoglYmsid() != null) {
            JSONObject obj2 = new JSONObject();
            obj2.put("id", pSbmogl.getSbmoglYmsid());
            JSONObject obj = new JSONObject();
            obj.put("service_id", service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            obj.put("modeConfDO", obj2);
            //发送删除模式
            String result = BCCAClient.deletMode(cd_api_url, obj, account);
            if (result != null) {
                JSONObject object = JSONObject.parseObject(result);
                if (object.get("resultCode").equals("0101")) {
                    token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
                    redisUtil.set(service_id, token);
                    obj.put("token", token);
                    result = BCCAClient.deletMode(cd_api_url, obj, account);
                    object = JSONObject.parseObject(result);
                }
                if (!object.get("resultCode").equals("0000")) {
                    throw new Exception("原账户" + account + "模式删除失败！");
                }
            }
            //删除模式下的命令
            PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
            PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
            criteria.andMsidEqualTo(pSbmogl.getSbmoglYmsid());
            pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
        }
        pSbmoglMapper.deleteByPrimaryKey(data.getSbmoglId());
    }

    /**
     * @param * @param data
     * @return void
     * @Description //TODO 新增执行计划
     * @author xjj
     * @date 2019/10/30
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addZxjh(PYktZxjh data) throws Exception {
        String account = data.getZxjhYptzh();

        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("processTime", new Date().getTime());
        obj.put("account", account);
        obj.put("plan_name", data.getZxjhMc());//执行计划名称
        obj.put("ser_code", "1");
        obj.put("enable", data.getZxjhZt());//计划状态
        String result = BCCAClient.addExcutionPlan(cd_api_url, obj, account);
        if (result == null) {
            throw new Exception("修改执行计划接口无返回！");
        }
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            obj.put("token", token);
            result = BCCAClient.addExcutionPlan(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            String yptjh_id = object.get("resultContent").toString();
            data.setYptjhId(yptjh_id);
            pYktZxjhMapper.insert(data);
        } else {
            throw new Exception("添加执行计划失败！" + object.get("resultDesc"));
        }

    }

    @Override
    public PageInfo<PYktZxjh> listPlan(PYktZxjh data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        List<PYktZxjh> pYktZxjhs = pYktZxjhMapper.listPlan(data);

        PageInfo<PYktZxjh> pYktZxjhPageInfo = new PageInfo<>(pYktZxjhs);

        return pYktZxjhPageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZxjh(PYktZxjh data) throws Exception {
        String account = data.getZxjhYptzh();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("processTime", new Date().getTime());
        obj.put("account", account);
        obj.put("plan_name", data.getZxjhMc());
        obj.put("ser_code", "1");
        obj.put("enable", data.getZxjhZt());
        obj.put("id", data.getYptjhId());
        String result = BCCAClient.updateExcutionPlanById(cd_api_url, obj, account);
        if (result == null) {
            throw new Exception("修改执行计划接口无返回！");
        }
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            obj.put("token", token);
            result = BCCAClient.updateExcutionPlanById(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            pYktZxjhMapper.updateByPrimaryKeySelective(data);
        } else {
            throw new Exception("修改计划修改失败！" + object.get("resultDesc"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delZxjh(PYktZxjh data) throws Exception {
        String account = data.getZxjhYptzh();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = bccaServiceInfo.getServiceId();
        String service_key = bccaServiceInfo.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
        JSONObject obj = new JSONObject();
        obj.put("service_id", service_id);
        obj.put("token", token);
        obj.put("processTime", new Date().getTime());
        obj.put("id", data.getYptjhId());
        String result = BCCAClient.deleteExcutionPlanById(cd_api_url, obj, account);
        if (result == null) {
            throw new Exception("删除执行计划接口无返回！");
        }
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
            obj.put("token", token);
            result = BCCAClient.deleteExcutionPlanById(cd_api_url, obj, account);
            object = JSONObject.parseObject(result);
        }
        if (object.get("resultCode").equals("0000")) {
            pYktZxjhMapper.deleteByPrimaryKey(data.getZxjhId());
        } else {
            throw new Exception("执行计划删除失败！" + object.get("resultDesc"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setHtmlModel(PControFormwork pControFormwork) {
        PControFormworkExample pControFormworkExample = new PControFormworkExample();
        PControFormworkExample.Criteria criteria = pControFormworkExample.createCriteria();
        criteria.andCreatorIdEqualTo(pControFormwork.getCreatorId()).andCodeEqualTo(pControFormwork.getCode()).andAttr1EqualTo(pControFormwork.getAttr1());
        pControFormworkMapper.deleteByExample(pControFormworkExample);
        pControFormworkMapper.insert(pControFormwork);
    }

    /**
     * @param * @param maps
     * @return java.util.List<java.util.Map   < java.lang.String , java.lang.String>>
     * @Description //TODO
     * @author xjj
     * @date 2019/11/4
     */
    @Override
    public List<PWebPropertyInfo> selTjlx(Map<String, String> maps) {
        PWebPropertyInfoExample pWebPropertyInfoExample = new PWebPropertyInfoExample();
        PWebPropertyInfoExample.Criteria criteria = pWebPropertyInfoExample.createCriteria();
        criteria.andProductCodeEqualTo(maps.get("code"));
        List<PWebPropertyInfo> pWebPropertyInfos = pWebPropertyInfoMapper.selectByExample(pWebPropertyInfoExample);
        return pWebPropertyInfos;
    }

    @Override
    public List<PSbgl> getSbLxlist(String data) {
        PSbglExample pSbglExample = new PSbglExample();
        PSbglExample.Criteria criteria = pSbglExample.createCriteria();
        criteria.andAccountEqualTo(data);
        List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
        return pSbgls;
    }

    @Override
    public List<Map<String, String>> selCfzByTjlx(Map<String, String> maps) {

        List<Map<String, String>> maps1 = pSbglMapper.selCfzByTjlx(maps);
        return maps1;
    }

    @Override
    public List<PSbmogl> getMs(String account) {
        PSbmoglExample pSbmoglExample = new PSbmoglExample();
        PSbmoglExample.Criteria criteria = pSbmoglExample.createCriteria();
        criteria.andSbmoglYmsidIsNotNull().andAttr2EqualTo(account);
        List<PSbmogl> pSbmogls = pSbmoglMapper.selectByExample(pSbmoglExample);
        return pSbmogls;
    }

    @Override
    public ResultDO<String> checkIDCard(String data) {

        ResultDO<String> resultDO = new ResultDO<>();
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andAttr6EqualTo(data);

        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);

        if (pSmhSettings.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("身份证重复！");
        }

        return resultDO;
    }

//    @Override
//    public void saveServiceInfo(PSmhDevice data) {
//        PSmhDeviceExample pSmhDeviceExample = new PSmhDeviceExample();
//        PSmhDeviceExample.Criteria criteria = pSmhDeviceExample.createCriteria();
//        criteria.andCreatorIdEqualTo(data.getCreatorId());
//        List<PSmhDevice> pSmhDevices = pSmhDeviceMapper.selectByExample(pSmhDeviceExample);
//        HttpSession session = ComonUtils.getSession();
//        if (pSmhDevices.size() > 0) {
//            pSmhDeviceMapper.updateByPrimaryKeySelective(data);
//            PSmhDevice pSmhDevice = pSmhDeviceMapper.selectByPrimaryKey(data.getId());
//            session.setAttribute("bccaService", pSmhDevice);
//
//        } else {
//            pSmhDeviceMapper.insert(data);
//            session.setAttribute("bccaService", data);
//        }
//
//
//    }


//    public PSysUser getUser() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        PSysUser user = (PSysUser) session.getAttribute("user");
//        return user;
//    }


    /**
     * @Description: TODO 同步执行计划，属性，指令（大管家新版接口）
     * @Param: [productCode]
     * @return: void
     * @Author: xujianjian
     * @Date: 2019/11/18 11:12
     */
    @Transactional(rollbackFor = Exception.class)
    public void syncPlan(String productCode) throws Exception {

        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息");
        }
        String appid = bccaServiceInfo.getServiceId();
        String appkey = bccaServiceInfo.getServiceKey();
        String token = HTTPclient.getNewAccessToken(dgj_url, appid, appkey);
        String mothod = "/thirdInterface/findPropertyByProCode";
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("service_id", appid);
        obj.put("product_code", productCode);
        String result = HTTPclient.httpRequestPostMethod(dgj_url + mothod, obj);
        System.out.println("返回结果：" + result);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("code").toString().equals("200")) {
            String resultContent = object.get("data").toString();
            JSONArray objects = JSONArray.parseArray(resultContent);
            MyJsonUtils.convert(objects);
            List<PWebPropertyInfo> pWebPropertyInfos = JSONArray.parseArray(objects.toString(), PWebPropertyInfo.class);
            for (PWebPropertyInfo pWebPropertyInfo : pWebPropertyInfos) {
                //TODO 增加属性  pWebPropertyInfo pWebPropertyInfoMapper
                pWebPropertyInfoMapper.deleteByPrimaryKey(pWebPropertyInfo.getId());
                pWebPropertyInfoMapper.insert(pWebPropertyInfo);
                List<PWebPlanInfo> webPlanInfo = pWebPropertyInfo.getWebPlanInfo();
                for (PWebPlanInfo pWebPlanInfo : webPlanInfo) {
                    //TODO 增加执行计划  pWebPlanInfo pWebPlanInfoMapper
                    pWebPlanInfoMapper.deleteByPrimaryKey(pWebPlanInfo.getId());
                    pWebPlanInfoMapper.insert(pWebPlanInfo);
                    //TODO 增加上行指令  pWebPlanInfo.getWebProductCmdUp(); pWebProductCmdUpMapper
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
    }


    /**
     * @Description: TODO 电脑端查询模板
     * @Param: [data]
     * @return: java.lang.String
     * @Author: xujianjian
     * @Date: 2019/12/3 11:23
     */
    @Override
    public String selmodle(String data) {
        PSysUser user = ComonUtils.getUser();
        PControFormworkExample pControFormworkExample = new PControFormworkExample();
        PControFormworkExample.Criteria criteria1 = pControFormworkExample.createCriteria();
        criteria1.andCodeEqualTo(data).andCreatorIdEqualTo(user.getUserId()).andAttr1EqualTo("pc");
        List<PControFormwork> pControFormworks = pControFormworkMapper.selectByExampleWithBLOBs(pControFormworkExample);
        if (pControFormworks.size() > 0) {
            return pControFormworks.get(0).getHtmlmodle();
        } else {
            List<PSbzcLx> pSbzcLxes = pSbzcLxMapper.selectHtmlWithMac(data);
            if (pSbzcLxes.size() > 0) {
                return pSbzcLxes.get(0).getHtmlmodle();
            } else {
                return "";
            }
        }
    }

    @Override
    public PSbgl findAccountBySernum(String serial_num) {
        PSbglExample pSbglExample = new PSbglExample();
        PSbglExample.Criteria criteria = pSbglExample.createCriteria();
        criteria.andSerialNumEqualTo(serial_num);
        List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
        return pSbgls.get(0);
    }

    @Override
    public void delmb(PSbgl data) {
        String account = data.getAccount();
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(account);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        PControFormworkExample pControFormworkExample = new PControFormworkExample();
        PControFormworkExample.Criteria criteria1 = pControFormworkExample.createCriteria();
        criteria1.andCreatorIdEqualTo(pSmhSettings.get(0).getCreatorId()).andCodeEqualTo(data.getDeviceMac()).andAttr1EqualTo("pc");
        pControFormworkMapper.deleteByExample(pControFormworkExample);

    }

    @Override
    public String selUimodle(String mac) {
        PSysUser user = ComonUtils.getUser();
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
                htmlmodle = pSbzcLxes.get(0).getHtmlmobile();//手机端
            }
        }
        return htmlmodle;
    }

    @Override
    public Map<String, Object> wgOnLine(PSbgl pSbgl) throws Exception {
        Map<String, Object> map = new HashMap<>();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请先完善服务信息");
        }
        String serviceId = bccaServiceInfo.getServiceId();
        String serviceKey = bccaServiceInfo.getServiceKey();
        String account = pSbgl.getAccount();
        String deviceMac = pSbgl.getDeviceMac();
        String token = "";

        Object o = redisUtil.get(serviceId);
        if (o == null) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, serviceId, serviceKey, back_url, account);
        } else {
            token = o.toString();
        }
        String result = BCCAClient.getDevicesByAccount(cd_api_url, serviceId, token, account);
        if (result == null) {
            throw new Exception("无返回值！");
        }

        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, serviceId, serviceKey, back_url, account);
            result = BCCAClient.getDevicesByAccount(cd_api_url, serviceId, token, account);
            jsonObject = JSONObject.parseObject(result);
        }
        JSONObject obj = null;
        if (jsonObject.get("resultCode").equals("0000")) {
            JSONObject resultContent = jsonObject.getJSONObject("resultContent");
            JSONArray deviceList = resultContent.getJSONArray("deviceList");
            if (deviceList.size() == 0) {
                throw new Exception("未查找到注册网关！");
            }
            for (int i = 0; i < deviceList.size(); i++) {
                JSONObject jsonObject1 = deviceList.getJSONObject(i);
                if(jsonObject1.getString("device_mac").equals(pSbgl.getDeviceMac())){
                    obj = jsonObject1;
                    break;
                }
            }
            if(obj != null){
                for (int i = 0; i < deviceList.size(); i++) {
                    JSONObject jsonObject1 = deviceList.getJSONObject(i);
                    if(jsonObject1.getString("device_mac").equals(obj.getString("register_gateway_mac"))){
                        obj.put("mac_register_time",jsonObject1.getString("register_time"));
                        break;
                    }
                }
            }
            map.put("code", 200);
            map.put("data", obj);
        } else {
            throw new Exception(jsonObject.getString("resultDesc"));
        }
        if (obj == null) {
            throw new Exception("未查找到注册网关！");
        }
        return map;
    }

}
