package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PProductType;
import com.superwork.apcosplatform.result.ResultDO;

public interface ProductTypeService {

    PageInfo<PProductType> listMyProType(PProductType data, Integer page, Integer limit);

    ResultDO<String> addProType(PProductType productType);

    ResultDO<String> editProType(PProductType productType);

    void delProType(PProductType productType);
}
