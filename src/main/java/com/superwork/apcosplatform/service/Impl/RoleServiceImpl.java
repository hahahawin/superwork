package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PRoleMapper;
import com.superwork.apcosplatform.mapper.PRoleUserMapper;
import com.superwork.apcosplatform.mapper.PSysUserMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.RoleService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: code->RoleServiceImpl
 * @description:
 * @author: xjj
 * @create: 2019-12-26 15:11
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    PRoleMapper pRoleMapper;
    @Autowired
    PRoleUserMapper pRoleUserMapper;
    @Autowired
    PSysUserMapper pSysUserMapper;

    @Override
    public PageInfo<PRole> listRole(PRole data, Integer page, Integer limit) throws Exception {
        PageHelper.startPage(page, limit);
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null) {
            throw new Exception("请先完善组织服务信息！");
        }
        data.setOrgId(bccaServiceInfo.getId().toString());
        List<PRole> pRoles = pRoleMapper.listRole(data);
        PageInfo<PRole> pRolePageInfo = new PageInfo<>(pRoles);
        return pRolePageInfo;
    }

    @Override
    public ResultDO<String> addRole(PRole pRole) throws Exception {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        PSysUser user = ComonUtils.getUser();
        if (bccaServiceInfo == null) {
            throw new Exception("请先完善组织服务信息！");
        }
        ResultDO<String> resultDO = new ResultDO<>();
        PRoleExample pRoleExample = new PRoleExample();
        PRoleExample.Criteria criteria = pRoleExample.createCriteria();
        criteria.andOrgIdEqualTo(bccaServiceInfo.getId().toString()).andRoleNameEqualTo(pRole.getRoleName());
        List<PRole> pRoles = pRoleMapper.selectByExample(pRoleExample);
        if(pRoles.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("角色名已存在，请换一个！");
            return resultDO;
        }
        pRole.setOrgId(bccaServiceInfo.getId().toString());
        pRole.setCreateDate(new Date());
        pRole.setCreateId(user.getUserId());
        pRoleMapper.insert(pRole);
        return resultDO;
    }

    @Override
    public ResultDO<String> editRole(PRole pRole) throws Exception {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        PSysUser user = ComonUtils.getUser();
        if (bccaServiceInfo == null) {
            throw new Exception("请先完善组织服务信息！");
        }
        ResultDO<String> resultDO = new ResultDO<>();
        PRoleExample pRoleExample = new PRoleExample();
        PRoleExample.Criteria criteria = pRoleExample.createCriteria();
        criteria.andOrgIdEqualTo(bccaServiceInfo.getId().toString()).andRoleNameEqualTo(pRole.getRoleName()).andIdNotEqualTo(pRole.getId());
        List<PRole> pRoles = pRoleMapper.selectByExample(pRoleExample);
        if(pRoles.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("角色名已存在，请换一个！");
            return resultDO;
        }
        pRole.setEditDate(new Date());
        pRole.setEditId(user.getUserId());
        pRoleMapper.updateByPrimaryKeySelective(pRole);
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delRole(PRole pRole) {
        pRoleMapper.deleteByPrimaryKey(pRole.getId());
        //删除角色与人员关系
        PRoleUserExample pRoleUserExample = new PRoleUserExample();
        PRoleUserExample.Criteria criteria = pRoleUserExample.createCriteria();
        criteria.andRoleIdEqualTo(pRole.getId());
        pRoleUserMapper.deleteByExample(pRoleUserExample);
    }

    @Override
    public Map<String,Object> listUserByRoleId(String roleId)  {
        HashMap<String, Object> map = new HashMap<>();
        PSysUser user = ComonUtils.getUser();
        PSysUser user1 = new PSysUser();
        user1.setCreatorId(user.getUserId());
        List<PSysUser> pSysUsers = pSysUserMapper.listMyUser(user1);//所在部门的所有用户
        map.put("all",pSysUsers);
        PRoleUserExample pRoleUserExample = new PRoleUserExample();
        PRoleUserExample.Criteria criteria = pRoleUserExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<PRoleUser> pRoleUsers = pRoleUserMapper.selectByExample(pRoleUserExample);//当前角色下的用户
        ArrayList<String> list = new ArrayList<>();
        for (PRoleUser pRoleUser : pRoleUsers) {
            list.add(pRoleUser.getUserId());
        }
        map.put("selected",list);

        return map;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setUserAndRole(List<Map<String, String>> data, String roleId) {
        PRoleUserExample pRoleUserExample = new PRoleUserExample();
        PRoleUserExample.Criteria criteria = pRoleUserExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        pRoleUserMapper.deleteByExample(pRoleUserExample);
        PRoleUser pRoleUser = new PRoleUser();
        pRoleUser.setRoleId(roleId);
        for (Map<String, String> datum : data) {
            pRoleUser.setUserId(datum.get("value"));
            pRoleUserMapper.insert(pRoleUser);
        }

    }


}
