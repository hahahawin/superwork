package com.superwork.apcosplatform.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSbzcLx;
import com.superwork.apcosplatform.domain.PSmhSetting;

import java.util.List;
import java.util.Map;

public interface InterFaceService {

    List<PSmhSetting> getSmhlist(String borg_id) throws Exception;

    List<PSbzcLx> getSbLxlist(String borg_id, String account) throws Exception;

    PageInfo<Map<String, String>> getSblist(Map<String, String> map1);

    List<Map<String, String>> getZhms(Map<String, String> map1) throws Exception;

    String addZxjh(Map<String, String> map1) throws Exception;

    void editZxjh(Map<String, String> map) throws Exception;

    void delZxjh(Map<String, String> map) throws Exception;

    String zxjhJcadd(Map<String, Object> map) throws Exception;

    void zxjhJcedit(Map<String, Object> map) throws Exception;

    void zxjhJcdel(Map<String, String> map) throws Exception;

    String zxjhJcList(Map<String, String> map) throws Exception;

    Map<String, Object> addAllMsxx(JSONObject map) throws Exception;

    Map<String, Object> selMsDetail(String sbmogl_id) throws Exception;

    Map<String, Object> getMouldBySerialNum(Map<String, String> map) throws Exception;

    PageInfo<Map<String, String>> getEquipList(Map<String, String> map) throws Exception;

    Map<String,Object> multiAccountMode(JSONArray mode_data) throws Exception;

    List<Object> multiModeInfo(JSONObject jsonObject) throws Exception;

    void multiModeControl(JSONObject jsonObject) throws Exception;
}
