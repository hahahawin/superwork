package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;

import java.util.List;
import java.util.Map;

public interface D3Service {

    List<PSbgl> listMySb(PSbgl data, String yj_id) throws Exception;

    String bongsb(List<PSbgl> data, String yj_id) throws Exception;

    PageInfo<PSbgl> listMysb3d(PSbgl pSbgl, Integer page, Integer limit);

//    void add3DPlan(String mszh_mc, String msmls, Map<String,Object> plan_data) throws Exception;

    PSbzcLx selmodleWithSum(String sbzcLx);

    List<PYktMsgngx> getMlByMsName(String data);

    void checkName(String data) throws Exception;

    void registerOrg(PD3orgZc data) throws Exception;

    PD3orgZc get3DInfo() throws Exception;

    PSbmogl selectAcountByMsId(String data);
}
