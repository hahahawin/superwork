package com.superwork.apcosplatform.service;

import com.alibaba.fastjson.JSONObject;

public interface SbAliasService {
    String getRemark(String serialNum);

    void saveRemark(JSONObject jsonObject) throws Exception;
}
