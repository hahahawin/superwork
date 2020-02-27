package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PReportmessage;

/**
 * @author Jianjian Xu
 * @create: 2019/10/25 13:28
 * @description:
 */
public interface PReportmessageService {
    void insertMessage(PReportmessage message);

    PageInfo<PReportmessage> listPReportmessage(PReportmessage message,Integer page,Integer limit);
}
