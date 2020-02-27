package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.service.ThirdInterfaceService;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author Jianjian Xu
 * @create: 2020/2/14 15:30
 * @description:
 */

@Service
public class ThirdInterfaceServiceImpl implements ThirdInterfaceService {

    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    PSbmoglMapper pSbmoglMapper;

    @Autowired
    PSmhSettingMapper pSmhSettingMapper;

    @Autowired
    PSysUserMapper pSysUserMapper;

    @Autowired
    POrgUserMapper pOrgUserMapper;

    @Autowired
    POrganizeMapper pOrganizeMapper;

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

    public List<PSbgl> listProduct(String userId) {

        PSbgl pSbgl = new PSbgl();
        pSbgl.setCreatorId(userId);
        List<PSbgl> pSbgls = pSbglMapper.listMySb(pSbgl);
        return pSbgls;
    }

    public List<PSbmogl> listPattern(String userId) {

        PSbmogl pSbmogl = new PSbmogl();
        pSbmogl.setCreatorId(userId);
        List<PSbmogl> pSbmogls = pSbmoglMapper.listMs(pSbmogl);
        return pSbmogls;
    }

    @Override
    public void addBackUrl(String backUrl, String userId) {
        PSmhSetting pSmhSetting = new PSmhSetting();
        pSmhSetting.setCreatorId(userId);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.listAccount_3(pSmhSetting);
        for (PSmhSetting smhSetting : pSmhSettings) {
            redisUtil.set("backUrl:"+smhSetting.getSmarthomeAccount(),backUrl);
        }
    }

    @Override
    public String analysis(JSONObject data) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://47.98.119.20:9530/bcca2-middle-sapi/sapiReadme");
        String respContent = null;
        StringEntity entity = new StringEntity(data.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        if (execute.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity1 = execute.getEntity();
            respContent = EntityUtils.toString(entity1, "UTF-8");
        }

        return respContent;
    }

    @Override
    public  List<Map> listMyUser(Map map) {
        PSysUser user = (PSysUser)map.get("user");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId",user.getUserId());
        if(user.getUserLevel().equals("1")){
            //主账号
            hashMap.put("type","all");

        }else{
            //查询当前用户所在部门
            POrgUserExample pOrgUserExample = new POrgUserExample();
            POrgUserExample.Criteria criteria = pOrgUserExample.createCriteria();
            criteria.andUserIdEqualTo(user.getUserId());
            List<POrgUser> pOrgUsers = pOrgUserMapper.selectByExample(pOrgUserExample);
            if(pOrgUsers.size() == 0){
                //当前用户不属于任何部门
                hashMap.put("type","one");
            }else{
                hashMap.put("type","other");

                POrganize pOrganize = pOrganizeMapper.selectByPrimaryKey(pOrgUsers.get(0).getOrgId());
                hashMap.put("q1",pOrganize.getLevels()+"%");
                hashMap.put("q2",pOrganize.getLevels()+".%");
            }
        }
        List<Map> maps = pSysUserMapper.listThirdUser(hashMap);
        return maps;
    }

    @Override
    public  List<POrganize> getOrg(Map map) {
        PD3orgZc pD3orgZc = (PD3orgZc)map.get("bccaService");
        PSysUser user = (PSysUser)map.get("user");

        List<POrganize> pOrganizes = new ArrayList<>();
        if(user.getUserLevel().equals("1")){
            //主账号
            POrganizeExample pOrganizeExample = new POrganizeExample();
            POrganizeExample.Criteria criteria = pOrganizeExample.createCriteria();
            criteria.andCompanyIdEqualTo(pD3orgZc.getId().toString());
            pOrganizes = pOrganizeMapper.selectByExample(pOrganizeExample);
        }
        //查询当前用户所在组织
        POrgUserExample pOrgUserExample = new POrgUserExample();
        POrgUserExample.Criteria criteria = pOrgUserExample.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        List<POrgUser> pOrgUsers = pOrgUserMapper.selectByExample(pOrgUserExample);
        if(pOrgUsers.size()!=0){
            POrganize pOrganize = pOrganizeMapper.selectByPrimaryKey(pOrgUsers.get(0).getOrgId());
            pOrganizes = pOrganizeMapper.listOrganizeOnUser(pOrganize.getLevels());
        }
        return pOrganizes;

    }

    @Override
    public void dyAccount(String token, String bccaAccount) throws Exception {
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria = pSmhSettingExample.createCriteria();
        criteria.andSmarthomeAccountEqualTo(bccaAccount);
        List<PSmhSetting> pSmhSettings = pSmhSettingMapper.selectByExample(pSmhSettingExample);
        if (pSmhSettings.size() > 0) {
            PSysUser user = pSysUserMapper.selectByPrimaryKey(pSmhSettings.get(0).getCreatorId());
            throw new Exception("账号已存在,请勿重复订阅！账号归属于："+user.getUserAccount());
        }

        Map map = (Map) redisUtil.get(token);
        PD3orgZc pd3orgZc =(PD3orgZc)map.get("bccaService");
        if (pd3orgZc ==null || StringUtils.isEmpty(pd3orgZc.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = pd3orgZc.getServiceId();
        String service_key = pd3orgZc.getServiceKey();
        Object o = redisUtil.get(service_id);
        String token1 = null;
        if (o != null) {
            token1 = o.toString();
        } else {
            token1 = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, bccaAccount);
            redisUtil.set(service_id, token1);
        }
        String result = BCCAClient.accountSubscrip(cd_api_url, service_id, token1, bccaAccount);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("resultCode").equals("0101")) {
            token1 = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, bccaAccount);
            redisUtil.set(service_id, token1);
            result = BCCAClient.accountSubscrip(cd_api_url, service_id, token, bccaAccount);
            jsonObject = JSONObject.parseObject(result);
        }
        if (!jsonObject.get("resultCode").equals("0000") && !jsonObject.get("resultCode").equals("0003")) {//成功或重复订阅都算成功
            throw new Exception(jsonObject.get("resultDesc").toString()+jsonObject.getString("resultContent"));
        }else{
            PSysUser user = (PSysUser)map.get("user");
            PSmhSetting pSmhSetting = new PSmhSetting();
            pSmhSetting.setSmarthomeAccount(bccaAccount);
            pSmhSetting.setCreatorId(user.getUserId());
            pSmhSetting.setAttr2("3");//订阅审核中
            pSmhSetting.setCreatedTime(new Date());
            pSmhSetting.setAttr4(user.getUserAccount());
            pSmhSettingMapper.insert(pSmhSetting);
        }


    }

}
