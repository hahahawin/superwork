package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSysLog;

public interface SysLogService {

    void save(PSysLog pSysLog);

    PageInfo<PSysLog> listLog(PSysLog data, Integer page, Integer limit);

    PageInfo<PSysLog> listMyLog(PSysLog data, Integer page, Integer limit);
}
