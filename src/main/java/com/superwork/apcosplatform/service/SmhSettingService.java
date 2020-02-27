package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.List;
import java.util.Map;

public interface SmhSettingService {
    void insert(PSmhSetting data) throws Exception;

    PageInfo<PSmhSetting> listAccount_3(PSmhSetting data, Integer page, Integer limit);

    void editAccountInfo(PSmhSetting data);

    void sbdy(PSmhSetting data) throws Exception;

    void delSbdy(PSmhSetting data) throws Exception;


    List<PSmhSetting> find(PSmhSetting smhSetting);

    void updateDyjg(PSmhSetting smhSetting);

    String syncSbxx(PSmhSetting smhSetting) throws Exception;

    void delAccount(PSmhSetting data)throws Exception ;

    void dyAccount(String data) throws Exception;

    PSmhSetting findByid(String setting_id);

    String syncSbLx(PSbzcLx pSbzcLx ) throws Exception;

    PageInfo<PSbzcLx> listsblx(PSbzcLx data, Integer page, Integer limit);

    List<SbzclxTemp> getMblist(PSbgl data) throws Exception;

    PSbzcLx selSbzcByCode(PSbzcLx sbzcLx);

    PageInfo<PSbmogl> listMs(PSbmogl data, Integer page, Integer limit);

    void addJzly(PSbmogl data);

    void editMsById(PSbmogl data);

    void setMsml(List<PYktMsgngx> pYktMsgngxes, String msId , PSysUser user) throws Exception;

    List<PSmhSetting> getAccount(String userId);

    void msControl(String data) throws Exception;


    void delms(PSbmogl data) throws Exception;

    void addZxjh(PYktZxjh data) throws Exception;

     PageInfo<PYktZxjh> listPlan(PYktZxjh data, Integer page, Integer limit);

    void updateZxjh(PYktZxjh data) throws Exception;

    void delZxjh(PYktZxjh data) throws Exception;

    void setHtmlModel(PControFormwork pControFormwork);

    List<PWebPropertyInfo>  selTjlx(Map<String, String> maps);

    List<PSbgl> getSbLxlist(String data);

    List<Map<String, String>> selCfzByTjlx(Map<String, String> maps);

    List<PSbmogl> getMs(String account);

    ResultDO<String> checkIDCard(String data);

//    void saveServiceInfo(PSmhDevice data);

    void syncPlan(String productCode) throws Exception;

    String selmodle(String data);

    PSbgl findAccountBySernum(String serial_num);

    void  delmb(PSbgl data);

    String selUimodle(String data);

    Map<String, Object> wgOnLine(PSbgl pSbgl) throws Exception;
}
