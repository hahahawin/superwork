package com.superwork.apcosplatform.service.Impl;


import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.POrgUserMapper;
import com.superwork.apcosplatform.mapper.POrganizeMapper;
import com.superwork.apcosplatform.mapper.PSysUserMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.OrganizeService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: code->OrganizeServiceImpl
 * @description:
 * @author: xjj
 * @create: 2020-01-08 14:06
 **/
@Service
public class OrganizeServiceImpl implements OrganizeService {

    @Autowired
    POrganizeMapper pOrganizeMapper;
    @Autowired
    POrgUserMapper pOrgUserMapper;
    @Autowired
    PSysUserMapper pSysUserMapper;

    @Override
    public List<POrganize> listOrganize(BigDecimal id) {

        POrganizeExample pOrganizeExample = new POrganizeExample();
        POrganizeExample.Criteria criteria = pOrganizeExample.createCriteria();
        criteria.andCompanyIdEqualTo(id.toString());
        List<POrganize> pOrganizes = pOrganizeMapper.selectByExample(pOrganizeExample);

        return pOrganizes;
    }

    @Override
    public ResultDO<String> addOrganize(POrganize pOrganize) {
        ResultDO<String> resultDO = new ResultDO<>();
        POrganizeExample pOrganizeExample = new POrganizeExample();
        POrganizeExample.Criteria criteria = pOrganizeExample.createCriteria();

        criteria.andCompanyIdEqualTo(pOrganize.getCompanyId()).andOrgNameEqualTo(pOrganize.getOrgName());
        List<POrganize> pOrganizes = pOrganizeMapper.selectByExample(pOrganizeExample);
        if (pOrganizes.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("名字重复！");
            return resultDO;
        }
        String id = pOrganizeMapper.selectAutoId();
        if (StringUtils.isEmpty(pOrganize.getLevels())) {
            pOrganize.setLevels(id);
        } else {
            pOrganize.setLevels(pOrganize.getLevels() + "." + id);
        }
        pOrganize.setId(id);
        pOrganizeMapper.insert(pOrganize);
        return resultDO;
    }

    @Override
    public ResultDO<String> editOrganize(POrganize pOrganize) {
        ResultDO<String> resultDO = new ResultDO<>();
        POrganizeExample pOrganizeExample = new POrganizeExample();
        POrganizeExample.Criteria criteria = pOrganizeExample.createCriteria();
        criteria.andOrgNameEqualTo(pOrganize.getOrgName()).andCompanyIdEqualTo(pOrganize.getCompanyId());
        List<POrganize> pOrganizes = pOrganizeMapper.selectByExample(pOrganizeExample);
        if (pOrganizes.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("名字重复！");
            return resultDO;
        }
        pOrganizeMapper.updateByPrimaryKeySelective(pOrganize);
        return resultDO;

    }

    @Override
    public ResultDO<String> delOrganize(String id) {
        ResultDO<String> resultDO = new ResultDO<>();
        POrganizeExample pOrganizeExample = new POrganizeExample();
        POrganizeExample.Criteria criteria = pOrganizeExample.createCriteria();
        criteria.andPidEqualTo(id);
        List<POrganize> pOrganizes = pOrganizeMapper.selectByExample(pOrganizeExample);
        if (pOrganizes.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("当前部门下有子级部门，不能直接删除！");
            return resultDO;
        }
        POrgUserExample pOrgUserExample = new POrgUserExample();
        POrgUserExample.Criteria criteria1 = pOrgUserExample.createCriteria();
        criteria1.andOrgIdEqualTo(id);
        List<POrgUser> pOrgUsers = pOrgUserMapper.selectByExample(pOrgUserExample);
        if (pOrgUsers.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("当前部门下还有人员，请移除人员后再删除！");
            return resultDO;
        }
        pOrganizeMapper.deleteByPrimaryKey(id);
        return resultDO;
    }

    @Override
    public List<PSysUser> getUserListByOrgID(String orgId) throws Exception {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null) {
            throw new Exception("请完善组织信息！");
        }
        PSysUser user = ComonUtils.getUser();
        List<PSysUser> pSysUsers = null;
        //主账号
        if (user.getUserLevel().equals("1")) {
            PSysUser user1 = new PSysUser();
            user1.setCreatorId(user.getUserId());
            pSysUsers = pSysUserMapper.listMyUser(user1);
        }else{
            pSysUsers = pSysUserMapper.listUserByOrgIdAandOther(bccaServiceInfo.getId().toString());//没有被分配的账号
            POrganize pOrganize = pOrganizeMapper.selectByPrimaryKey(orgId);
            Map<String, Object> map = new HashMap<>();
            map.put("leves",pOrganize.getLevels());
            map.put("companyId",bccaServiceInfo.getId().toString());
            List<PSysUser> pSysUsers1 = pSysUserMapper.selectUserOnOrg(map);
            pSysUsers.addAll(pSysUsers1);
        }

        POrgUserExample pOrgUserExample = new POrgUserExample();
        POrgUserExample.Criteria criteria = pOrgUserExample.createCriteria();
        criteria.andOrgIdEqualTo(orgId);
        List<POrgUser> pOrgUsers = pOrgUserMapper.selectByExample(pOrgUserExample);
        for (PSysUser pSysUser : pSysUsers) {
            for (POrgUser pOrgUser : pOrgUsers) {
                if (pSysUser.getUserId().equals(pOrgUser.getUserId())) {
                    pSysUser.setChecked(true);
                    break;
                }
            }
        }
        return pSysUsers;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setUserAndOrg(List<Map<String, String>> data, String orgId) {
        //删除部门下之前人员的关系
        POrgUserExample pOrgUserExample = new POrgUserExample();
        POrgUserExample.Criteria criteria = pOrgUserExample.createCriteria();
        criteria.andOrgIdEqualTo(orgId);
        pOrgUserMapper.deleteByExample(pOrgUserExample);
        for (Map<String, String> datum : data) {
            String userId = datum.get("value");
            // 先删除人员关系
            pOrgUserExample.clear();
            POrgUserExample.Criteria criteria1 = pOrgUserExample.createCriteria();
            criteria1.andUserIdEqualTo(userId);
            pOrgUserMapper.deleteByExample(pOrgUserExample);
            //新增关系
            POrgUser pOrgUser = new POrgUser();
            pOrgUser.setOrgId(orgId);
            pOrgUser.setUserId(userId);
            pOrgUserMapper.insert(pOrgUser);
        }
    }
}
