package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PDepRightMapper;
import com.superwork.apcosplatform.mapper.PSysRightMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.RighrService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jianjian Xu
 * @create: 2019/10/24 11:27
 * @description:
 */
@Service
public class RighrServiceImpl implements RighrService {

    @Autowired
    PSysRightMapper pSysRightMapper;
    @Autowired
    PDepRightMapper pDepRightMapper;

    @Value("${spring.hasAllRight}")
    private String hasAllRight;

    @Autowired
    RedisUtil redisUtil;



    @Override
    public void addRight(PSysRight data) {
        pSysRightMapper.insert(data);
        List<String> strings = loadRight();
        redisUtil.set("jurisdiction",strings);//更新需要拦截的权限

    }

    @Override
    public List<PSysRight> loadMenu() {
        PSysUser user = ComonUtils.getUser();
//        String[] split = hasAllRight.split(",");
        if(hasAllRight.contentEquals(user.getUserAccount())){
            PSysRightExample pSysRightExample = new PSysRightExample();
            PSysRightExample.Criteria criteria = pSysRightExample.createCriteria();
            criteria.andIsMeuaEqualTo(BigDecimal.ONE);
            List<PSysRight> pSysRights = pSysRightMapper.selectByExample(pSysRightExample);
            return pSysRights;
        }
        List<PSysRight> pSysRights = pSysRightMapper.loadMenu(user.getUserId());
        return pSysRights;
    }

    @Override
    public ResultDO<String> checkRightName(PSysRight pSysRight) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysRightExample pSysRightExample = new PSysRightExample();
        PSysRightExample.Criteria criteria = pSysRightExample.createCriteria();
        if(pSysRight.getRightId()!= null){
            criteria.andParentIdEqualTo(pSysRight.getParentId()).andRightIdNotEqualTo(pSysRight.getRightId()).andRightNameEqualTo(pSysRight.getRightName());
        }else{
            criteria.andParentIdEqualTo(pSysRight.getParentId()).andRightNameEqualTo(pSysRight.getRightName());
        }
        List<PSysRight> pSysRights = pSysRightMapper.selectByExample(pSysRightExample);
        if(pSysRights.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("权限名已存在，请换一个!");
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> editRight(PSysRight pSysRight) {
        ResultDO<String> stringResultDO = checkRightName(pSysRight);
        if(!stringResultDO.isSuccess()){
            return  stringResultDO;
        }
        pSysRightMapper.updateByPrimaryKeySelective(pSysRight);
        List<String> strings = loadRight();
        redisUtil.set("jurisdiction",strings);//更新需要拦截的权限
        return stringResultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delRight(String id) {
        PSysRightExample pSysRightExample = new PSysRightExample();
        PSysRightExample.Criteria criteria = pSysRightExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        pSysRightMapper.deleteByExample(pSysRightExample);
        pSysRightMapper.deleteByPrimaryKey(id);
        List<String> strings = loadRight();
        redisUtil.set("jurisdiction",strings);//更新需要拦截的权限
    }

    @Override
    public List<String> loadRight() {
        List<String> list = pSysRightMapper.loadRight();
        return list;

    }

    @Override
    public JSONObject findRightwithDepId(String depId) {
        PSysUser user = ComonUtils.getUser();
        JSONObject jsonObject = new JSONObject();
        if (hasAllRight.indexOf(user.getUserAccount()) != -1) {
            PSysRightExample pSysRightExample = new PSysRightExample();
            List<PSysRight> pSysRights = pSysRightMapper.selectByExample(pSysRightExample);
            jsonObject.put("pSysRights",pSysRights);
        }else{
            List<PSysRight> pSysRights = pSysRightMapper.loadMyRight(user.getUserId());
            jsonObject.put("pSysRights",pSysRights);
        }
        PDepRightExample pDepRightExample = new PDepRightExample();
        PDepRightExample.Criteria criteria = pDepRightExample.createCriteria();
        criteria.andDepIdEqualTo(depId);
        List<PDepRight> pDepRights = pDepRightMapper.selectByExample(pDepRightExample);
        ArrayList<String> list = new ArrayList<>();
        for (PDepRight pDepRight : pDepRights) {
            list.add(pDepRight.getRightId());
        }
        jsonObject.put("checkedKey",list);
        return jsonObject;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRightAndDep(Map<String, Object> map) {
        String depId = (String)map.get("depId");
        List<String> data = (List<String>)map.get("data");
        PDepRightExample pDepRightExample = new PDepRightExample();
        PDepRightExample.Criteria criteria = pDepRightExample.createCriteria();
        criteria.andDepIdEqualTo(depId);
        pDepRightMapper.deleteByExample(pDepRightExample);
        PDepRight pDepRight = new PDepRight();
        pDepRight.setDepId(depId);
        for (String datum : data) {
            pDepRight.setRightId(datum);
            pDepRightMapper.insertSelective(pDepRight);
        }
    }

    @Override
    public List<PSysRight> loadAllMenu() {
        PSysRightExample pSysRightExample = new PSysRightExample();
        List<PSysRight> pSysRights = pSysRightMapper.selectByExample(pSysRightExample);
        return pSysRights;
    }

    @Override
    public List<String> selectMyRight() {
        PSysUser user = ComonUtils.getUser();
        List<String> myRight = pSysRightMapper.selectMyRight(user.getUserId());
        return myRight;
    }

    @Override
    public PageInfo<PSysRight> listApplication(PSysRight data, Integer page, Integer limit) {

        PageHelper.startPage(page,limit);
        List<PSysRight> pSysRights = pSysRightMapper.listApplication(data);
        PageInfo<PSysRight> pageInfo = new PageInfo<>(pSysRights);

        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> addApplication(PSysRight pSysRight) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysRightExample pSysRightExample = new PSysRightExample();
        PSysRightExample.Criteria criteria = pSysRightExample.createCriteria();
        criteria.andRightNameEqualTo(pSysRight.getRightName());
        List<PSysRight> pSysRights = pSysRightMapper.selectByExample(pSysRightExample);
        if(pSysRights.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("名称重复，请换一个！");
            return resultDO;
        }
        pSysRight.setRightType(new BigDecimal(2));//外接应用
        pSysRight.setIsMeua(new BigDecimal(1));//菜单
        pSysRight.setParentId("0");
        String url = pSysRight.getUrl();
        pSysRight.setUrl(null);
        pSysRightMapper.insert(pSysRight);//新增父级

        pSysRight.setUrl(url);
        pSysRight.setParentId(pSysRight.getRightId());
        pSysRightMapper.insert(pSysRight);//新增子集

        return resultDO;
    }

    @Override
    public ResultDO<String> editApplication(PSysRight pSysRight) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysRightExample pSysRightExample = new PSysRightExample();
        PSysRightExample.Criteria criteria = pSysRightExample.createCriteria();
        criteria.andRightNameEqualTo(pSysRight.getRightName()).andRightIdNotEqualTo(pSysRight.getRightId());
        List<PSysRight> pSysRights = pSysRightMapper.selectByExample(pSysRightExample);
        if(pSysRights.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("名称重复，请换一个！");
            return resultDO;
        }
        pSysRightMapper.updateByPrimaryKeySelective(pSysRight);
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delApplication(String rightId) {
        PSysRight pSysRight = pSysRightMapper.selectByPrimaryKey(rightId);

        pSysRightMapper.deleteByPrimaryKey(pSysRight.getParentId());
        pSysRightMapper.deleteByPrimaryKey(rightId);
    }
}
