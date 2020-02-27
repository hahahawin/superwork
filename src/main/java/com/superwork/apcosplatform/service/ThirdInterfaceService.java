package com.superwork.apcosplatform.service;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.domain.POrganize;
import com.superwork.apcosplatform.domain.PSbgl;
import com.superwork.apcosplatform.domain.PSbmogl;
import com.superwork.apcosplatform.domain.PSysUser;


import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface ThirdInterfaceService {

    List<PSbgl> listProduct(String userId);

    List<PSbmogl> listPattern(String userId);

    void addBackUrl(String backUrl, String userId);

    String analysis(JSONObject data) throws IOException;

    List<Map> listMyUser(Map map);

    List<POrganize> getOrg(Map map);

    void dyAccount(String token, String bccaAccount) throws Exception;
}
