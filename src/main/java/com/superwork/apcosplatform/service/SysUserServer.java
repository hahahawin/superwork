package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.Map;

/**
 * @author Jianjian Xu
 * @create: 2019/10/21 14:46
 * @description:
 */
public interface SysUserServer {


    ResultDO<String> findPhone(String cellphoneNo);


    ResultDO<String> checkUserAccount(String userAccount);

    void register(PSysUser sysUser);

    ResultDO<Map<String,Object>> login(PSysUser data);

    PSysUser getUserInfo(String userId);

    PSysUser editUserInfo(PSysUser data) throws Exception;

    PageInfo<PSysUser> listUser(PSysUser data, Integer page, Integer limit);

    ResultDO<String> getCodeWhenPassword(String data);

    ResultDO<String>  getBackPassword(PSysUser data) throws Exception;

    void delUser(PSysUser data) throws Exception;

    void addUser(PSysUser data) throws Exception;

//    void uploadHead(PSysUserEx pSysUserEx);

    String getUserHead(String userId);

}
