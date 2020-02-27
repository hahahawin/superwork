package com.superwork.apcosplatform.app.service.Impl;

import com.superwork.apcosplatform.app.service.AppSysUserService;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.PSysUserExample;
import com.superwork.apcosplatform.mapper.PSysUserMapper;
import com.superwork.apcosplatform.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: code->AppSysUserServiceImpl
 * @description:
 * @author: xjj
 * @create: 2019-12-05 14:40
 **/
@Service
public class AppSysUserServiceImpl implements AppSysUserService {
    @Autowired
    PSysUserMapper pSysUserMapper;

    @Override
    public void replacePhone(String userAccount, String newPhone) {
        PSysUserExample pSysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = pSysUserExample.createCriteria();
        criteria.andUserAccountEqualTo(userAccount);
        PSysUser pSysUser = new PSysUser();
        pSysUser.setCellphoneNo(newPhone);
        pSysUserMapper.updateByExampleSelective(pSysUser,pSysUserExample);
    }

    @Override
    public ResultDO<String> replacePassWord(String userAccount, String newPassWord) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysUserExample pSysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = pSysUserExample.createCriteria();
        criteria.andUserAccountEqualTo(userAccount).andUserPasswordEqualTo(newPassWord);
        List<PSysUser> pSysUsers = pSysUserMapper.selectByExample(pSysUserExample);
        if(pSysUsers.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("新旧密码不能一样！");
            return resultDO;
        }
        pSysUserExample.clear();
        PSysUserExample.Criteria criteria1 = pSysUserExample.createCriteria();
        criteria1.andUserAccountEqualTo(userAccount);
        PSysUser pSysUser = new PSysUser();
        pSysUser.setUserPassword(newPassWord);
        pSysUserMapper.updateByExampleSelective(pSysUser,pSysUserExample);
        return resultDO;
    }
}
