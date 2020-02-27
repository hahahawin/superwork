package com.superwork.apcosplatform.app.service;


import com.superwork.apcosplatform.domain.PSmhSetting;
import com.superwork.apcosplatform.domain.PSbgl;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.Map;


public interface AppAcountService {
    void saveInfo(Map<String,String> map) throws Exception;

    Map<String,String> getInfo();

    ResultDO<String> sbdy(PSmhSetting pSmhSetting) throws Exception;

    ResultDO<String> dyAccount(String account) throws Exception;

    ResultDO<String> delSbdy(PSmhSetting pSmhSetting) throws Exception;

    ResultDO<String> syncSbxx(String account) throws Exception;

    ResultDO<String> newsbbinding(Map<String, String> map) throws Exception;

    ResultDO<String> newsbJiebing(PSbgl pSbgl) throws Exception;

    void delAccount(String settingId) throws Exception;

    ResultDO<String> newadd(PSmhSetting pSmhSetting) throws Exception;

    void editAccount(PSmhSetting pSmhSetting);
}
