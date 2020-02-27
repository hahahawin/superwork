package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PYktMsgngx;
import com.superwork.apcosplatform.domain.PSbgl;
import com.superwork.apcosplatform.domain.PSbmogl;

import java.util.List;

/**
 * @author Jianjian Xu
 * @create: 2019/10/25 11:38
 * @description:
 */
public interface SbglService {

    void delete(PSbgl sbgl);

    PageInfo<PSbgl> listsb(PSbgl data, Integer page, Integer limit);

    List<PYktMsgngx> listmlBymsId(String data);

    PageInfo<PSbmogl> listmsBysum(String sum,Integer page,Integer limit);

    void editSb(PSbgl pSbgl);
}
