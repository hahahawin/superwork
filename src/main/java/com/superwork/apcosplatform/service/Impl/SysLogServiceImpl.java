package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSysLog;
import com.superwork.apcosplatform.mapper.PSysLogMapper;
import com.superwork.apcosplatform.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jianjian Xu
 * @create: 2019/10/24 9:10
 * @description:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    PSysLogMapper pSysLogMapper;
    @Override
    public void save(PSysLog pSysLog) {
        pSysLogMapper.insert(pSysLog);
    }

    @Override
    public PageInfo<PSysLog> listLog(PSysLog data, Integer page, Integer limit) {

        PageHelper.startPage(page,limit);

        List<PSysLog> pSysLogs = pSysLogMapper.listLog(data);

        PageInfo<PSysLog> objectPageInfo = new PageInfo<>(pSysLogs);

        return objectPageInfo;
    }

    @Override
    public PageInfo<PSysLog> listMyLog(PSysLog data, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PSysLog> pSysLogs = pSysLogMapper.listMyLog(data);
        PageInfo<PSysLog> pSysLogPageInfo = new PageInfo<>(pSysLogs);
        return pSysLogPageInfo;
    }
}
