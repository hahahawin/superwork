package com.superwork.apcosplatform.app.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.result.ResultDO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface AppSbglService {
    PageInfo<PSbgl> listsb(PSbgl data, Integer page, Integer limit);

    ResultDO<String> sbControl(Map<String, String> map) throws Exception;

    ResultDO<String> addJzly(PSbmogl pSbmogl) throws Exception;

    PageInfo<PSbmogl> listMs(PSbmogl data, Integer page, Integer limit);

    ResultDO<String> editMsById(PSbmogl pSbmogl) throws Exception;

    ResultDO<String> delms(PSbmogl pSbmogl) throws Exception;

    ResultDO<String> msControl(String msId) throws Exception;

    ResultDO<String> addZxjh(PYktZxjh pYktZxjh) throws Exception;

    ResultDO<String> updateZxjh(PYktZxjh pYktZxjh) throws Exception;

    ResultDO<String> delZxjh(PYktZxjh pYktZxjh) throws Exception;

    ResultDO<String> zxjhJcadd(Map<String, Object> map) throws Exception;

    ResultDO<String> deljc(String id, String account) throws Exception;

    ResultDO<List<ExcutionPlanProcess>> zxjhJcList(PYktZxjh pYktZxjh) throws Exception;

    String selmodle(String mac) throws URISyntaxException, IOException;

    ResultDO<List<SbzclxTemp>> getMblist(String productCode) throws Exception;

    void setMrmb(String id, String mac) throws Exception;

    ResultDO<Integer> syncPlan(String productCode) throws Exception;

    ResultDO<String> syncSbLx() throws Exception;

    void delmb(PSbgl pSbgl);
}
