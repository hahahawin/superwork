package com.superwork.apcosplatform.service;


import com.superwork.apcosplatform.domain.POrganize;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.result.ResultDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrganizeService {

    List<POrganize> listOrganize(BigDecimal id);

    ResultDO<String> addOrganize(POrganize pOrganize);

    ResultDO<String> editOrganize(POrganize pOrganize);

    ResultDO<String> delOrganize(String id);

    List<PSysUser> getUserListByOrgID(String orgId) throws Exception;

    void setUserAndOrg(List<Map<String, String>> data, String orgId);
}
