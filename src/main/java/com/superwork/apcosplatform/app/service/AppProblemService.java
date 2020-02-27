package com.superwork.apcosplatform.app.service;

import com.superwork.apcosplatform.domain.PAppColumn;
import com.superwork.apcosplatform.domain.PAppCommonpro;
import com.superwork.apcosplatform.domain.PAppProblem;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.List;

public interface AppProblemService {
    void addProblem(PAppProblem pAppProblem);

    List<PAppProblem> listMyProblem(String userId);

    List<PAppColumn> listCommonProblem(String query);

    ResultDO<String> commonProYesOrNo(String id,String decision);
}
