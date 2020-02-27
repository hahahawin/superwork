package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PReportmessage;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.mapper.PReportmessageMapper;
import com.superwork.apcosplatform.service.PReportmessageService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jianjian Xu
 * @create: 2019/10/25 13:28
 * @description:
 */
@Service
public class PReportmessageServiceImpl implements PReportmessageService {

    @Autowired
    PReportmessageMapper pReportmessageMapper;

    @Override
    public void insertMessage(PReportmessage message) {
        pReportmessageMapper.insert(message);
    }

    @Override
    public PageInfo<PReportmessage> listPReportmessage(PReportmessage message, Integer page, Integer limit) {
        PSysUser user = ComonUtils.getUser();
        message.setCreatorId(user.getUserId());
        PageHelper.startPage(page,limit);
        List<PReportmessage> pReportmessages = pReportmessageMapper.listPReportmessage(message);
        PageInfo<PReportmessage> objectPageInfo = new PageInfo<>(pReportmessages);
        return objectPageInfo;
    }
}
