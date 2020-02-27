package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PMscontrolRecord;
import com.superwork.apcosplatform.domain.PSbcontrolOriginal;

public interface ControlRecordService {


    PageInfo<PSbcontrolOriginal> listSbControl(PSbcontrolOriginal pSbcontrolOriginal,Integer page,Integer limit);

    PageInfo<PMscontrolRecord> listMsControl(PMscontrolRecord data, Integer page, Integer limit);
}
