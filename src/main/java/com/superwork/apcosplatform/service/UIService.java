package com.superwork.apcosplatform.service;

import com.alibaba.fastjson.JSONObject;

public interface UIService {
    JSONObject kfsbkz(String serial_num, String product_id, String operation, String controlParams, String account) throws Exception;
}
