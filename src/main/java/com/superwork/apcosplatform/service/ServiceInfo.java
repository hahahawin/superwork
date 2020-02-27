package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PD3orgZc;
import com.superwork.apcosplatform.domain.PSysUser;

import java.util.Map;

/**
 * @program: code->ServiceInfo
 * @description:
 * @author: xjj
 * @create: 2019-11-26 11:13
 **/
public interface ServiceInfo {
    void saveInfo(Map<String, String> map) throws Exception;

    PD3orgZc selectServiceInfo(String userId);

    PageInfo<PD3orgZc> listOrg(PD3orgZc data, Integer page, Integer limit);

    PageInfo<PSysUser>  listUserByOrgId(String data, Integer page, Integer limit);

    void editOrg(PD3orgZc pd3orgZc) throws Exception;

    void addOrg(Map<String, String> map) throws Exception;

    void delOrg(PD3orgZc pd3orgZc) throws Exception;
}
