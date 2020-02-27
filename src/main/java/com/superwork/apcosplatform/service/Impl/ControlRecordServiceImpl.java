package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PMscontrolRecord;
import com.superwork.apcosplatform.domain.PSbcontrolOriginal;
import com.superwork.apcosplatform.mapper.PMscontrolRecordMapper;
import com.superwork.apcosplatform.mapper.PSbcontrolOriginalMapper;
import com.superwork.apcosplatform.service.ControlRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: code->SbControlOriginalServiceImpl
 * @description:
 * @author: xjj
 * @create: 2020-01-07 08:59
 **/
@Service
public class ControlRecordServiceImpl implements ControlRecordService {
    @Autowired
    PSbcontrolOriginalMapper pSbcontrolOriginalMapper;

    @Autowired
    PMscontrolRecordMapper pMscontrolRecordMapper;


    @Override
    public PageInfo<PSbcontrolOriginal> listSbControl(PSbcontrolOriginal pSbcontrolOriginal, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PSbcontrolOriginal> pSbcontrolOriginals = pSbcontrolOriginalMapper.listSbControl(pSbcontrolOriginal);
        PageInfo<PSbcontrolOriginal> pageInfo = new PageInfo<>(pSbcontrolOriginals);
        return pageInfo;
    }

    @Override
    public PageInfo<PMscontrolRecord> listMsControl(PMscontrolRecord data, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PMscontrolRecord> pMscontrolRecords = pMscontrolRecordMapper.listMsControl(data);
        PageInfo<PMscontrolRecord> pageInfo = new PageInfo<>(pMscontrolRecords);
        return pageInfo;
    }
}
