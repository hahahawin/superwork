package com.superwork.apcosplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSysRight;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.List;
import java.util.Map;

public interface RighrService {
    void addRight(PSysRight data);

    List<PSysRight> loadMenu();

    ResultDO<String> checkRightName(PSysRight pSysRight);

    ResultDO<String> editRight(PSysRight pSysRight);

    void delRight(String id);

    List<String> loadRight();

    JSONObject findRightwithDepId(String depId);

    void setRightAndDep(Map<String, Object> map);

    List<PSysRight> loadAllMenu();

    List<String> selectMyRight();

    PageInfo<PSysRight> listApplication(PSysRight data, Integer page, Integer limit);

    ResultDO<String> addApplication(PSysRight pSysRight);

    ResultDO<String> editApplication(PSysRight pSysRight);

    void delApplication(String rightId);
}
