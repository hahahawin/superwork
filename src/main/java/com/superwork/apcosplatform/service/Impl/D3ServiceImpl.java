package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.service.D3Service;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.D3HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @program: beone-platform
 * @description:
 * @author: xjj
 * @create: 2019-11-07 16:57
 **/
@Service
public class D3ServiceImpl implements D3Service {

    @Autowired
    PSbglMapper pSbglMapper;
    @Autowired
    PSbmoglMapper pSbmoglMapper;

    @Autowired
    PYktMsgngxMapper pYktMsgngxMapper;

    @Autowired
    PSbzcLxMapper pSbzcLxMapper;

    @Autowired
    PD3orgZcMapper pd3orgZcMapper;

    @Autowired
    PControFormworkMapper pControFormworkMapper;



    @Value("${cd_api_url}")
    private String cd_api_url;//成都平台
    @Value("${back_url}")
    private String back_url;
    @Value("${d3url}")
    private String d3url;
    @Value("${serviceIp}")//本项目发布的地址
    private String serviceIp;
    @Value("${server.port}")//本项目发布的地址
    private String port;
    @Value("${dgj_api_url}")//大管家
    private String dgj_api_url;



    @Override
    public List<PSbgl> listMySb(PSbgl data, String yj_id) throws Exception {
        PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
        if(dInfo == null || StringUtils.isEmpty(dInfo.getServiceId())){
            throw new Exception("请先完善服务信息！");
        }
        String service_id = dInfo.getServiceId();
        String isBong = data.getAttr3();
        List<PSbgl> pSbgls = pSbglMapper.listMySb(data);
        JSONObject jsonObject = new JSONObject();
        PSysUser user = ComonUtils.getUser();
        String dToken = D3HttpClient.get3DToken(d3url, service_id, user);
        jsonObject.put("yj_id", yj_id);
        jsonObject.put("user_token", dToken);
        String bondInfo = null;
        try {
            bondInfo = D3HttpClient.getBondInfo(d3url, jsonObject);
        } catch (Exception e) {
            System.out.println("获取原件出错"+e.getMessage());
        }
        List<String> strings = new ArrayList<>();
        if(bondInfo != null){
            strings = JSONArray.parseArray(bondInfo, String.class);
        }

        ArrayList<PSbgl> list = new ArrayList<>();//绑定的设备
        ArrayList<PSbgl> list1 = new ArrayList<>();//未绑定的设备
        for (PSbgl pSbgl : pSbgls) {
            boolean boo = true;
            for (String string : strings) {
                if (string.equals(pSbgl.getDeviceMac())) {
                    pSbgl.setBong(true);
                    pSbgl.setMark(true);
                    list.add(pSbgl);
                    boo = false;
                    break;
                }
            }
            if (boo) {
                list1.add(pSbgl);
            }
        }

        if ("1".equals(isBong)) {
            return list;
        } else if ("2".equals(isBong)) {
            return list1;
        } else {
            return pSbgls;
        }
    }

    @Override
    public String bongsb(List<PSbgl> data, String yj_id) throws Exception {
        int jbnum = 0;
        int bdnum = 0;
        PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
        if(dInfo == null || StringUtils.isEmpty(dInfo.getServiceId())){
            throw new Exception("请先完善服务信息！");
        }
        String service_id = dInfo.getServiceId();
        ArrayList<Map<String, String>> bond_list = new ArrayList<>();//需要绑定的设备
        HashMap<String, String> unmap = new HashMap<>();//需要解除绑定的设备
        for (PSbgl datum : data) {
            if (datum.isBong() && !datum.isMark()) {//现在绑定，之前不绑定
                HashMap<String, String> map = new HashMap<>();
                map.put("yj_id", yj_id);
                map.put("serial_num", datum.getDeviceMac());
                map.put("equip_name", datum.getDeviceName());
                bond_list.add(map);
            }
            if (datum.isMark() && !datum.isBong()) {//之前绑定，现在不绑定
                unmap.put(datum.getDeviceMac(), yj_id);
            }
        }
        PSysUser user = ComonUtils.getUser();
        String dToken = D3HttpClient.get3DToken(d3url, service_id, user);
        //1.解除绑定
        if (unmap.size() > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id", service_id);
            jsonObject.put("user_token", dToken);
            jsonObject.put("bond_list", unmap);
            D3HttpClient.unbondDevFromEle(d3url, jsonObject);
            jbnum = unmap.size();
        }

        if (bond_list.size() > 0) {
            //2.绑定设备
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("service_id", service_id);
            jsonObject1.put("user_token", dToken);
            jsonObject1.put("bond_list", bond_list);
            D3HttpClient.bondDevToEle(d3url, jsonObject1);
            bdnum = bond_list.size();
        }
        String msg = "绑定成功设备："+bdnum+";解绑成功设备："+jbnum;
        return msg;
    }

    @Override
    public PageInfo<PSbgl> listMysb3d(PSbgl pSbgl, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PSbgl> pSbgls = pSbglMapper.listMysbOn3d(pSbgl);
        PageInfo<PSbgl> pSbglPageInfo = new PageInfo<>(pSbgls);
        return pSbglPageInfo;
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void add3DPlan(String mszh_mc, String msmls, Map<String,Object> plan_data) throws Exception {
//        PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
////        PD3orgZc dInfo = ComonUtils.get3DServiceInfo();
//        if(StringUtils.isEmpty(dInfo.getServiceId())){
//            throw new Exception("请先完善服务信息！");
//        }
////        String account = dInfo.getAccount();
//        String service_id = dInfo.getServiceId();
//        String service_key = dInfo.getServiceKey();
//        String mszh_id = "";//核心平台返回的模式的ID
//        PSysUser user = ComonUtils.getUser();
//        String token =BCCAClient.getNewToken(dgj_api_url,cd_api_url,service_id,service_key,back_url,account);
//        String dToken = D3HttpClient.get3DToken(d3url, service_id, user);//3d的
//        List<PYktMsgngx> pYktMsgngxes = JSONArray.parseArray(msmls, PYktMsgngx.class);
//        ArrayList<String> cmds = new ArrayList<>();
//        for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
//            String params = pYktMsgngx.getParams();
//            if (params == null || "".equals(params)) {
//                params = "none";
//                pYktMsgngx.setParams("");
//            }
//            cmds.add(pYktMsgngx.getWhid() + "_" + pYktMsgngx.getProductCode() + "_" + pYktMsgngx.getCode() + "_" + params);
//        }
//        if(StringUtils.isEmpty(plan_data.get("fa_id"))){//
//            //新增模式
//            PSbmogl pSbmogl = new PSbmogl();
//            pSbmogl.setCreatorId(user.getUserId());
//            pSbmogl.setCreatedTime(new Date());
//            pSbmogl.setSbmoglName(mszh_mc);
//            pSbmogl.setAttr2(account);
//            pSbmoglMapper.insert(pSbmogl);
//            for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
//                pYktMsgngx.setCreatorId(user.getUserId());
//                pYktMsgngx.setEditorId(user.getUserId());
//                pYktMsgngx.setMsid(pSbmogl.getSbmoglId());
//                pYktMsgngxMapper.insert(pYktMsgngx);
//            }
//            //添加
//            JSONObject obj = new JSONObject();
//            JSONObject obj2 = new JSONObject();
//            obj2.put("account", account);
//            obj2.put("enable_mode", "yes");
//            obj2.put("mode_name", pSbmogl.getSbmoglName());
//            obj.put("service_id", service_id);
//            obj.put("token", token);
//            obj.put("processTime", new Date().getTime());
//            obj.put("cmds", cmds);
//            obj.put("modeConfDO", obj2);
//            String result =BCCAClient.addMode(cd_api_url,obj,account);
//            System.out.println("result:" + result);
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            if (jsonObject.get("resultCode").equals("0101")) {
//                token =  BCCAClient.getNewToken(dgj_api_url,cd_api_url,service_id,service_key,back_url,account);
//                obj.put("token", token);
//                result =BCCAClient.addMode(cd_api_url,obj,account);
//                jsonObject = JSONObject.parseObject(result);
//            }
//            if (jsonObject.get("resultCode").equals("0000")) {
//                JSONObject object2 = JSONObject.parseObject(jsonObject.get("resultContent").toString());
//                mszh_id = object2.get("modeId").toString();//新的模式ID
//                pSbmogl.setSbmoglYmsid(object2.get("modeId").toString());
//                pSbmoglMapper.updateByPrimaryKeySelective(pSbmogl);
//            } else {
//                throw new Exception(jsonObject.get("resultDesc").toString());
//            }
//        }else{
//            //删除之前模式下的命令
//            PSbmoglExample pSbmoglExample = new PSbmoglExample();
//            PSbmoglExample.Criteria criteria1 = pSbmoglExample.createCriteria();
//            criteria1.andSbmoglNameEqualTo(mszh_mc);
//            List<PSbmogl> pSbmogls = pSbmoglMapper.selectByExample(pSbmoglExample);
//            PYktMsgngxExample pYktMsgngxExample = new PYktMsgngxExample();
//            PYktMsgngxExample.Criteria criteria = pYktMsgngxExample.createCriteria();
//            criteria.andMsidEqualTo(pSbmogls.get(0).getSbmoglId());
//            pYktMsgngxMapper.deleteByExample(pYktMsgngxExample);
//            //旧的模式ID
//            mszh_id = pSbmogls.get(0).getSbmoglYmsid();
//            //重新插入命令
//            for (PYktMsgngx pYktMsgngx : pYktMsgngxes) {
//                pYktMsgngx.setCreatorId(user.getUserId());
//                pYktMsgngx.setEditorId(user.getUserId());
//                pYktMsgngx.setMsid(pSbmogls.get(0).getSbmoglId());
//                pYktMsgngxMapper.insert(pYktMsgngx);
//            }
//            //模式修改
//            JSONObject obj = new JSONObject();
//            JSONObject obj2 = new JSONObject();
//            obj2.put("account", account);
//            obj2.put("id", pSbmogls.get(0).getSbmoglYmsid());
//            obj2.put("mode_name", mszh_mc);
//            obj.put("service_id", service_id);
//            obj.put("token", token);
//            obj.put("processTime", new Date().getTime());
//            obj.put("cmds", cmds);
//            obj.put("modeConfDO", obj2);
//            String result = BCCAClient.updateMode(cd_api_url, obj, account);
//            System.out.println("result:" + result);
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            if (jsonObject.get("resultCode").equals("0101")) {
//                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
//                obj.put("token", token);
//                result = BCCAClient.updateMode(cd_api_url, obj, account);
//                jsonObject = JSONObject.parseObject(result);
//            }
//            if (!jsonObject.get("resultCode").equals("0000")) {
//                //删除之前的方案
//                JSONObject jsonObject2 = new JSONObject();
//                jsonObject2.put("mszh_id",pSbmogls.get(0).getSbmoglYmsid());
//                jsonObject2.put("user_token",dToken);
//                D3HttpClient.otherDelSjfaOne(d3url,jsonObject2);
//            } else {
//                throw new Exception(jsonObject.get("resultDesc").toString());
//            }
//        }
//        //3D新增方案
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("dataStr",plan_data);
//        jsonObject1.put("mszh_id",mszh_id);
//        jsonObject1.put("user_token",dToken);
//        D3HttpClient.otherSaveFA(d3url, jsonObject1);
//    }

    @Override
    public PSbzcLx selmodleWithSum(String mac) {
        PSysUser user = ComonUtils.getUser();
        //mac地址查询设备
//        PSbglExample pSbglExample = new PSbglExample();
//        PSbglExample.Criteria criteria1 = pSbglExample.createCriteria();
//        criteria1.andDeviceMacEqualTo(mac);
//        List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
//        //查询设备类型
//        PSbzcLxExample pSbzcLxExample = new PSbzcLxExample();
//        PSbzcLxExample.Criteria criteria = pSbzcLxExample.createCriteria();
//        criteria.andMldmEqualTo(pSbgls.get(0).getProductCode());
//        List<PSbzcLx> pSbzcLxes = pSbzcLxMapper.selectByExampleWithBLOBs(pSbzcLxExample);
//        PSbzcLx pSbzcLx = pSbzcLxes.get(0);
        //查询模版
        PSbzcLx pSbzcLx = new PSbzcLx();
        PControFormworkExample pControFormworkExample = new PControFormworkExample();
        PControFormworkExample.Criteria criteria2 = pControFormworkExample.createCriteria();
        criteria2.andCodeEqualTo(mac).andCreatorIdEqualTo(user.getUserId()).andAttr1EqualTo("pc");
        List<PControFormwork> pControFormworks = pControFormworkMapper.selectByExample(pControFormworkExample);
        if (pControFormworks.size() != 0) {
            pSbzcLx.setHtmlmodle(pControFormworks.get(0).getHtmlmodle());
        }else{
            List<PSbzcLx> pSbzcLxes = pSbzcLxMapper.selectHtmlWithMac(mac);
            pSbzcLx = pSbzcLxes.get(0);
        }
        return pSbzcLx;
    }

    @Override
    public List<PYktMsgngx> getMlByMsName(String data) {
        List<PYktMsgngx> mlByMsName = pYktMsgngxMapper.getMlByMsName(data);
        return mlByMsName;
    }

    @Override
    public void checkName(String data) throws Exception {
        PSbmoglExample pSbmoglExample = new PSbmoglExample();
        PSbmoglExample.Criteria criteria1 = pSbmoglExample.createCriteria();
        criteria1.andSbmoglNameEqualTo(data);
        List<PSbmogl> pSbmogls = pSbmoglMapper.selectByExample(pSbmoglExample);
        if(pSbmogls.size()>0){
            throw new Exception("名字重复，请换一个！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerOrg(PD3orgZc data) throws Exception {
        PSysUser user = ComonUtils.getUser();
        if(StringUtils.isEmpty(data.getId())){
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",data.getServiceId());
            jsonObject.put("service_key",data.getServiceKey());
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            jsonObject.put("org_name",data.getOrgName());
            String org_id = D3HttpClient.registerOrg(d3url, jsonObject);//3D平台的ID
            data.setBelongOrgId(org_id);
            //注册用户
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("service_id",data.getServiceId());
            jsonObject2.put("dept_id",user.getUserId());
            jsonObject2.put("user_id",user.getUserId());
            jsonObject2.put("user_name",user.getUserAccount());
            String s1 = D3HttpClient.registerUser(d3url, jsonObject2);//userToken
            data.setAttr3(s1);//保持Token
            data.setCreatorId(user.getUserId());
            data.setCreatedTime(new Date());
            pd3orgZcMapper.insert(data);
            HttpSession session = ComonUtils.getSession();
            session.setAttribute("3DServiceInfo",data);

        }else{
            PD3orgZc pd3orgZc = pd3orgZcMapper.selectByPrimaryKey(data.getId());
            String belongOrgId = pd3orgZc.getBelongOrgId();
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",data.getServiceId());
            jsonObject.put("service_key",data.getServiceKey());
            jsonObject.put("org_id",belongOrgId);
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            jsonObject.put("org_name",data.getOrgName());
            D3HttpClient.updateOrg(d3url,jsonObject);
            pd3orgZcMapper.updateByPrimaryKeySelective(data);
            HttpSession session = ComonUtils.getSession();
            session.setAttribute("3DServiceInfo",data);
        }

    }


    /**
     * @Description: TODO 返回当前登录者3D服务信息
     * @Param: []
     * @return: PD3orgZc
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    public PD3orgZc get3DInfo(){
        PSysUser user = ComonUtils.getUser();
        PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
        PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
         criteria.andCreatorIdEqualTo(user.getUserId());
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
        if(pd3orgZcs.size() == 0){
           return null;
        }
        return pd3orgZcs.get(0);
    }

    @Override
    public PSbmogl selectAcountByMsId(String data) {
        PSbmoglExample pSbmoglExample = new PSbmoglExample();
        PSbmoglExample.Criteria criteria = pSbmoglExample.createCriteria();
        criteria.andSbmoglYmsidEqualTo(data);
        List<PSbmogl> pSbmogls = pSbmoglMapper.selectByExample(pSbmoglExample);
        return pSbmogls.get(0);
    }


}
