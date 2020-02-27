package com.superwork.apcosplatform.service;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public interface DataService {
    Map<String, Object> getRegisterOnWeek();


    Map<String, Object> getLoginOnWeek();

    Map<String, Object> getProductStatus() throws Exception;
}
