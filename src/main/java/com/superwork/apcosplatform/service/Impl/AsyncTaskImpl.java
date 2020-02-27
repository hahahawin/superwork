package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.service.AsyncTask;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: code->AsyncTaskImpl
 * @description: 异步任务
 * @author: xjj
 * @create: 2020-01-02 10:44
 **/
@Service
public class AsyncTaskImpl implements AsyncTask {

    public static Logger logger = Logger.getLogger(AsyncTaskImpl.class);
    @Autowired
    PSbmoglMapper pSbmoglMapper;

    @Autowired
    PYktMsgngxMapper pYktMsgngxMapper;

    @Autowired
    PMscontrolRecordMapper pMscontrolRecordMapper;

    @Autowired
    PSbcontrolOriginalMapper pSbcontrolOriginalMapper;

    @Autowired
    PSbglMapper pSbglMapper;

    @Value("${upload.filePath}")
    String filePath;

    @Autowired
    PAppSoftwareMapper pAppSoftwareMapper;

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


    /**
     * @Description: TODO 记录设备控制
     * @Param: [pSbcontrolOriginal]
     * @return: void
     * @Author: xujianjian
     * @Date: 2020/1/14 14:13
     */
    @Override
    @Async
    public void sbControlRecord(PSbcontrolOriginal pSbcontrolOriginal) {
        if (StringUtils.isEmpty(pSbcontrolOriginal.getDeviceName())) {
            PSbglExample pSbglExample = new PSbglExample();
            PSbglExample.Criteria criteria = pSbglExample.createCriteria();
            criteria.andDeviceMacEqualTo(pSbcontrolOriginal.getSerialNum());
            List<PSbgl> pSbgls = pSbglMapper.selectByExample(pSbglExample);
            if (pSbgls.size() != 0) {
                pSbcontrolOriginal.setDeviceName(pSbgls.get(0).getDeviceName());
            }
        }
        pSbcontrolOriginalMapper.insert(pSbcontrolOriginal);
    }

    /**
     * @Description: TODO 记录模式控制
     * @Param: [msId, userId, type, ex]
     * @return: void
     * @Author: xujianjian
     * @Date: 2020/1/14 14:13
     */
    @Override
    @Async
    public void msControlRecord(String msId, String userId, String type, String ex) {
        PSbmogl pSbmogl = pSbmoglMapper.selectByPrimaryKey(msId);
        if (pSbmogl != null) {
            PMscontrolRecord pMscontrolRecord = new PMscontrolRecord();
            pMscontrolRecord.setResult(type);//结果
            pMscontrolRecord.setControlId(userId);//控制人
            pMscontrolRecord.setCreateDate(new Date());//控制时间
            pMscontrolRecord.setAbnormalReason(ex);
            String s1 = JSON.toJSONString(pSbmogl);
            pMscontrolRecord.setMsOriginal(s1);//模式原文
            pMscontrolRecord.setAccount(pSbmogl.getAttr2());//账号
            pMscontrolRecord.setMsName(pSbmogl.getSbmoglName());//模式名称
            List<PYktMsgngx> pYktMsgngxes = pYktMsgngxMapper.listmlBymsId(msId);
            String s = JSON.toJSONString(pYktMsgngxes);
            pMscontrolRecord.setMsmlOriginal(s);//模式命令原文
            pMscontrolRecordMapper.insert(pMscontrolRecord);
        }
    }

    /**
     * @Description: TODO 凌晨3 点触发删除无效的APP
     * @Param: []
     * @return: void
     * @Author: xujianjian
     * @Date: 2020/1/14 14:10
     */
    @Async
//    @Scheduled(initialDelay = 6000, fixedRate = 2000)
    @Scheduled(cron = "0 0 3 * * ? ")//凌晨3点触发
    public void delInvalidApp() {
        PAppSoftwareExample pAppSoftwareExample = new PAppSoftwareExample();
        List<PAppSoftware> pAppSoftwares = pAppSoftwareMapper.selectByExample(pAppSoftwareExample);
        File file = new File(filePath + "/app");
        File[] files = file.listFiles();
        for (File file1 : files) {
            String name = file1.getPath();
            File file2 = new File(name);
            boolean isDel = true;
            for (PAppSoftware pAppSoftware : pAppSoftwares) {
                String path = pAppSoftware.getPath();
                File file3 = new File(path);
                if (file3.getName().equals(file2.getName())) {
                    isDel = false;
                }
            }
            if (isDel) {
                file2.delete();
                logger.info("凌晨3点执行删除文件：" + name);
            }
        }
    }

    /**
     * @Description: TODO 凌晨4点 触发删除临时的网页文件
     * @Param: []
     * @return: void
     * @Author: xujianjian
     * @Date: 2020/1/14 14:11
     */
    @Async
    @Scheduled(cron = "0 0 4 * * ? ")//凌晨4点触发
    public void delTemporaryHtml() {

        File file = new File(filePath + "/temporary");
        File[] files = file.listFiles();
        for (File file1 : files) {
            String name = file1.getPath();
            File file2 = new File(name);
            long updateTime = file2.lastModified();
            long nowTime = System.currentTimeMillis();
            boolean isDel = true;
            if ((nowTime - updateTime) < 1000 * 60 * 10) {
                isDel = false;
            }
            if (isDel) {
                file2.delete();
                logger.info("凌晨4点执行删除文件：" + name);
            }
        }
    }

    @Async
    @Override
    public void pushReportMsg(PReportmessage message) {
        String obj = JSON.toJSONString(message);
        String account = message.getAccount();
        String backUrl= (String)redisUtil.get("backUrl:" + account);
        System.out.println("推送消息地址："+backUrl);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(backUrl);
        StringEntity entity = new StringEntity(obj, "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.addHeader("owner", account);
        try {
            client.execute(post);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.error(e);
        }

    }

    /**
     * 同步智能设备
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void syncSbxx(Map map,String account) throws Exception {
        PD3orgZc pd3orgZc =(PD3orgZc)map.get("bccaService");
        PSysUser user = (PSysUser)map.get("user");
        //回调账号设备更新

        if (pd3orgZc == null || StringUtils.isEmpty(pd3orgZc.getServiceId())) {
            throw new Exception("请先完善服务信息！");
        }
        String service_id = pd3orgZc.getServiceId();
        String service_key = pd3orgZc.getServiceKey();
        //先重redis中获取token
        Object o = redisUtil.get(service_id);
        String token = null;
        if (o != null) {
            token = o.toString();
        } else {
            token = BCCAClient.getNewToken(dgj_url, cd_api_url, service_id, service_key, back_url, account);
            redisUtil.set(service_id, token);
        }
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
        System.out.println("size:" + list.size());
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PSbgl pSbgl = changEntity(list.get(i));
                pSbgl.setCreatorId(user.getUserId());
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


}
