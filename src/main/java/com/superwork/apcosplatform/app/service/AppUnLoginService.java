package com.superwork.apcosplatform.app.service;

import com.superwork.apcosplatform.result.ResultDO;

public interface AppUnLoginService {
    ResultDO<String> getCodeWhenPassword(String accountOrPhone);
}
