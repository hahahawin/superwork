package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.MD5;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PSysUserMapper;
import com.superwork.apcosplatform.mapper.PZzUserMapper;
import com.superwork.apcosplatform.service.ServiceInfo;
import com.superwork.apcosplatform.mapper.PD3orgZcMapper;
import com.superwork.apcosplatform.service.SysUserServer;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.D3HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: code->ServiceInfoImpl
 * @description:
 * @author: xjj
 * @create: 2019-11-26 11:13
 **/
@Service
public class ServiceInfoImpl implements ServiceInfo {

    @Autowired
    PD3orgZcMapper pd3orgZcMapper;

    @Autowired
    PZzUserMapper pZzUserMapper;

    @Autowired
    PSysUserMapper pSysUserMapper;

    @Autowired
    SysUserServer sysUserServer;



    @Value("${serviceIp}")//本项目发布的地址
    private String serviceIp;
    @Value("${server.port}")//本项目发布的地址
    private String port;

    @Value("${d3url}")
    private String d3url;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInfo(Map<String, String> map) throws Exception {
        PSysUser user = ComonUtils.getUser();
        HttpSession session = ComonUtils.getSession();
        String id = map.get("id");
        String serviceId = map.get("serviceId");
        String serviceKey = map.get("serviceKey");
        String orgName = map.get("orgName");
        String belongOrgId = map.get("belongOrgId");
        String address = map.get("address");
        String companyPhone = map.get("companyPhone");
        String logo = map.get("logo");
        String companyInfo = map.get("companyInfo");
        //3D服务信息
        PD3orgZc pd3orgZc = new PD3orgZc();
        pd3orgZc.setServiceId(serviceId);
        pd3orgZc.setServiceKey(serviceKey);
        pd3orgZc.setOrgName(orgName);
        pd3orgZc.setAddress(address);
        pd3orgZc.setCompanyPhone(companyPhone);
        pd3orgZc.setLogo(logo);
        pd3orgZc.setCompanyInfo(companyInfo);
        if(StringUtils.isEmpty(id)){
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",serviceId);
            jsonObject.put("service_key",serviceKey);
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            jsonObject.put("org_name",orgName);
            String org_id = D3HttpClient.registerOrg(d3url, jsonObject);//3D平台的ID
            pd3orgZc.setBelongOrgId(org_id);
            //注册用户
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("service_id",serviceId);
            jsonObject2.put("dept_id",user.getUserId());
            jsonObject2.put("user_id",user.getUserId());
            jsonObject2.put("user_name",user.getUserAccount());
            D3HttpClient.registerUser(d3url, jsonObject2);
            pd3orgZc.setCreatorId(user.getUserId());
            pd3orgZc.setCreatedTime(new Date());
            pd3orgZcMapper.insert(pd3orgZc);
            //新增组织和人员关系
            PZzUser pZzUser = new PZzUser();
            pZzUser.setUserId(user.getUserId());
            pZzUser.setZzId(pd3orgZc.getId().toString());
            pZzUserMapper.insert(pZzUser);
            session.setAttribute("bccaService",pd3orgZc);
        }else if(StringUtils.isEmpty(belongOrgId)){
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",serviceId);
            jsonObject.put("service_key",serviceKey);
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            jsonObject.put("org_name",orgName);
            String org_id = D3HttpClient.registerOrg(d3url, jsonObject);//3D平台的ID
            pd3orgZc.setBelongOrgId(org_id);
            //注册用户
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("service_id",serviceId);
            jsonObject2.put("dept_id",user.getUserId());
            jsonObject2.put("user_id",user.getUserId());
            jsonObject2.put("user_name",user.getUserAccount());
            D3HttpClient.registerUser(d3url, jsonObject2);
            pd3orgZc.setId(new BigDecimal(id));
            pd3orgZc.setEditorId(user.getUserId());
            pd3orgZc.setEditedTime(new Date());
            pd3orgZcMapper.updateByPrimaryKeySelective(pd3orgZc);
            session.setAttribute("bccaService",pd3orgZc);
        }else{
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",serviceId);
            jsonObject.put("service_key",serviceKey);
            jsonObject.put("org_id",belongOrgId);
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            jsonObject.put("org_name",orgName);
            D3HttpClient.updateOrg(d3url,jsonObject);
            pd3orgZc.setId(new BigDecimal(id));
            pd3orgZc.setEditedTime(new Date());
            pd3orgZc.setEditorId(user.getUserId());
            pd3orgZcMapper.updateByPrimaryKeySelective(pd3orgZc);
            session.setAttribute("bccaService",pd3orgZc);
        }
    }

    @Override
    public PD3orgZc selectServiceInfo(String userId) {
        PD3orgZc pd3orgZc = pd3orgZcMapper.selectServiceInfo(userId);
        return pd3orgZc;
    }

    @Override
    public PageInfo<PD3orgZc> listOrg(PD3orgZc data, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.listOrg(data);
        for (PD3orgZc pd3orgZc : pd3orgZcs) {

            List<PSysUser> pSysUsers = pSysUserMapper.listUserByOrgIdWithLevel(pd3orgZc.getId().toString());
            if(pSysUsers.size()!=0){
                pd3orgZc.setTelePhone(pSysUsers.get(0).getCellphoneNo());
                pd3orgZc.setMainAccount(pSysUsers.get(0).getUserAccount());
            }
        }
        PageInfo<PD3orgZc> pd3orgZcPageInfo = new PageInfo<>(pd3orgZcs);
        return pd3orgZcPageInfo;
    }

    @Override
    public PageInfo<PSysUser> listUserByOrgId(String data, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PSysUser> pSysUsers = pSysUserMapper.listUserByOrgId(data);
        PageInfo<PSysUser> pageInfo = new PageInfo<>(pSysUsers);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editOrg(PD3orgZc pd3orgZc) throws Exception {
        if(!StringUtils.isEmpty(pd3orgZc.getOrgName())){
            PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
            PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
            criteria.andOrgNameEqualTo(pd3orgZc.getOrgName()).andIdNotEqualTo(pd3orgZc.getId());
            List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
            if(pd3orgZcs.size()>0){
                throw new Exception("组织名称已存在！");
            }
        }
        if(pd3orgZc.getEffective().equals("2")){
            List<PSysUser> pSysUsers = pSysUserMapper.listUserByOrgIdWithLevel(pd3orgZc.getId().toString());
            for (PSysUser pSysUser : pSysUsers) {
                if(pSysUser.getUserAccount().equals("admin")){
                    throw new Exception("admin账号的下的组织不能禁用！");
                }
            }
        }

        PD3orgZc pd3orgZc1 = pd3orgZcMapper.selectByPrimaryKey(pd3orgZc.getId());
        if(!StringUtils.isEmpty(pd3orgZc1.getBelongOrgId())){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",pd3orgZc1.getServiceId());
            jsonObject.put("service_key",pd3orgZc1.getServiceKey());
            jsonObject.put("org_id",pd3orgZc1.getBelongOrgId());
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            if (StringUtils.isEmpty(pd3orgZc.getOrgName())){
                jsonObject.put("org_name",pd3orgZc1.getOrgName());
            }else {
                jsonObject.put("org_name",pd3orgZc.getOrgName());
            }
            D3HttpClient.updateOrg(d3url,jsonObject);
        }
        pd3orgZcMapper.updateByPrimaryKeySelective(pd3orgZc);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrg(Map<String, String> map) throws Exception {
        String orgName = map.get("orgName");//组织机构名称
        PD3orgZcExample pd3orgZcExample = new PD3orgZcExample();
        PD3orgZcExample.Criteria criteria = pd3orgZcExample.createCriteria();
        criteria.andOrgNameEqualTo(orgName);
        List<PD3orgZc> pd3orgZcs = pd3orgZcMapper.selectByExample(pd3orgZcExample);
        if(pd3orgZcs.size()>0){
            throw new Exception("组织名称已存在！");
        }
        String serviceId = map.get("serviceId");//服务ID
        String serviceKey = map.get("serviceKey");//服务KET
        String theme = map.get("theme");//主题
        String logo = map.get("logo");//LOG
        String companyInfo = map.get("companyInfo");//备案信息
        String address = map.get("address");//公司地址
        String companyPhone = map.get("companyPhone");//公司电话

        String userAccount = map.get("userAccount");//登录账号
        String userName = map.get("userName");//用户名
        String userPassword = map.get("userPassword");//密码
        String cellphoneNo = map.get("cellphoneNo");//电话号码
        String gender = map.get("gender");//性别
        String userType = map.get("userType");//用户类型
        PSysUser user1 = ComonUtils.getUser();
        PSysUser user = new PSysUser();
        String s = UUID.randomUUID().toString();
        String s1 = s.replaceAll("-", "");
        user.setUserId(s1);
        user.setEnable(new BigDecimal(2));//默认可用
        user.setUserType(new BigDecimal(userType));
        user.setIsdel("2");
        user.setGender(BigDecimal.valueOf(Integer.valueOf(gender)));
        user.setUserName(userName);
        user.setUserLevel("1");//主账号
        MD5 d = new MD5();
        user.setUserPassword(d.getMD5ofStr(userPassword));
        user.setUserAccount(userAccount);
        user.setCellphoneNo(cellphoneNo);
        user.setCreatorId(user1.getUserId());
        user.setCreatedTime(new Date());
        sysUserServer.register(user);

        PD3orgZc pd3orgZc = new PD3orgZc();
        pd3orgZc.setOrgName(orgName);
        pd3orgZc.setServiceId(serviceId);
        pd3orgZc.setServiceKey(serviceKey);
        pd3orgZc.setEffective("1");//默认启用
        pd3orgZc.setCompanyInfo(companyInfo);
        pd3orgZc.setAddress(address);
        pd3orgZc.setCompanyPhone(companyPhone);
        pd3orgZc.setTheme(theme);
        pd3orgZc.setLogo(logo);

        if(!StringUtils.isEmpty(serviceKey) && !StringUtils.isEmpty(serviceId)){
            //注册组织机构
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service_id",serviceId);
            jsonObject.put("service_key",serviceKey);
            jsonObject.put("call_back_url",serviceIp);
            jsonObject.put("call_project_url",serviceIp+"/interface/getSmhlist");
            jsonObject.put("org_name",orgName);
            String org_id = D3HttpClient.registerOrg(d3url, jsonObject);//3D平台的ID
            pd3orgZc.setBelongOrgId(org_id);
            //注册用户
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("service_id",serviceId);
            jsonObject2.put("dept_id",user.getUserId());
            jsonObject2.put("user_id",user.getUserId());
            jsonObject2.put("user_name",user.getUserAccount());
            D3HttpClient.registerUser(d3url, jsonObject2);
        }
        pd3orgZc.setCreatorId(user.getUserId());
        pd3orgZc.setCreatedTime(new Date());
        pd3orgZcMapper.insert(pd3orgZc);
        //新增组织和人员关系
        PZzUser pZzUser = new PZzUser();
        pZzUser.setUserId(user.getUserId());
        pZzUser.setZzId(pd3orgZc.getId().toString());
        pZzUserMapper.insert(pZzUser);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delOrg(PD3orgZc pd3orgZc) throws Exception {
        //admin的组织不能删除
        List<PSysUser> pSysUsers = pSysUserMapper.listUserByOrgIdWithLevel(pd3orgZc.getId().toString());
        for (PSysUser pSysUser : pSysUsers) {
            if(pSysUser.getUserAccount().equals("admin")){
                throw new Exception("admin账号下的组织不能删除！");
            }
        }
        //删除组织下的用户
        PZzUserExample pZzUserExample = new PZzUserExample();
        PZzUserExample.Criteria criteria = pZzUserExample.createCriteria();
        criteria.andZzIdEqualTo(pd3orgZc.getId().toString());
        List<PZzUser> pZzUsers = pZzUserMapper.selectByExample(pZzUserExample);
        PSysUser user = new PSysUser();
        for (PZzUser pZzUser : pZzUsers) {
            user.setUserId(pZzUser.getUserId());
            sysUserServer.delUser(user);
        }
        //删除组织
        pd3orgZcMapper.deleteByPrimaryKey(pd3orgZc.getId());
    }
}
