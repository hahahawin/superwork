package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PDepartment;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    PageInfo<PDepartment> listDepartment(PDepartment data, Integer page, Integer limit);

    void addDepartment(PDepartment data);

    ResultDO<String> checkName(String data);

    ResultDO<String> checkNameWith(PDepartment data);

    void editDepartment(PDepartment data);

    ResultDO<String> delDepartment(PDepartment data);

    List<PSysUser> getUserListByDepId(String data);

    void setUserAndDep(List<Map<String, String>> data, String depId);
}
