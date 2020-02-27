package com.superwork.apcosplatform.app.service;

import com.superwork.apcosplatform.result.ResultDO;

public interface AppSysUserService {
    void replacePhone(String userAccount, String newPhone);

    ResultDO<String> replacePassWord(String userAccount, String newPassWord);
}
