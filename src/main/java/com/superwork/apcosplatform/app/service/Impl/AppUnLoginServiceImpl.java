package com.superwork.apcosplatform.app.service.Impl;

import com.superwork.apcosplatform.app.service.AppUnLoginService;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.PSysUserExample;
import com.superwork.apcosplatform.mapper.PSysUserMapper;
import com.superwork.apcosplatform.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: code->UnLoginServiceImpl
 * @description:
 * @author: xjj
 * @create: 2019-11-28 13:59
 **/
@Service
public class AppUnLoginServiceImpl implements AppUnLoginService {
    @Autowired
    PSysUserMapper pSysUserMapper;
    @Override
    public ResultDO<String> getCodeWhenPassword(String account) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysUserExample pSysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = pSysUserExample.createCriteria();
        criteria.andUserAccountEqualTo(account);
        List<PSysUser> pSysUsers = pSysUserMapper.selectByExample(pSysUserExample);
        if(pSysUsers.size()==0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("账号不存在！");
        }else{
            resultDO.setData(pSysUsers.get(0).getCellphoneNo());
        }
        return resultDO;
    }
}
