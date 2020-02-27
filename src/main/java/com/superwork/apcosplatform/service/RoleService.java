package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PRole;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.List;
import java.util.Map;

public interface RoleService {
    PageInfo<PRole> listRole(PRole data, Integer page, Integer limit) throws Exception;

    ResultDO<String> addRole(PRole pRole) throws Exception;

    ResultDO<String> editRole(PRole pRole) throws Exception;

    void delRole(PRole pRole);

    Map<String,Object> listUserByRoleId(String roleId) throws Exception;

    void setUserAndRole(List<Map<String, String>> data, String roleId);
}
