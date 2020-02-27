package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PAppColumn;
import com.superwork.apcosplatform.domain.PAppCommonpro;
import com.superwork.apcosplatform.domain.PAppProblem;
import com.superwork.apcosplatform.domain.PAppSoftware;
import com.superwork.apcosplatform.result.ResultDO;

import java.util.List;

/**
 * @program: code->APPVersionService
 * @description: APP版本
 * @author: xjj
 * @create: 2019-12-16 13:38
 **/
public interface APPVersionService {

    PageInfo<PAppSoftware> listApp(PAppSoftware data, Integer page, Integer limit);

    void pAppSoftware(PAppSoftware pAppSoftware);

    void editApp(PAppSoftware pAppSoftware);

    PAppSoftware getAppPath(String type);

    PAppSoftware scanning(String id);

    void addColumn(PAppColumn pAppColumn);

    ResultDO<String> editColumn(PAppColumn pAppColumn);

    void delColumn(PAppColumn pAppColumn);

    PageInfo<PAppColumn> listColumn(PAppColumn data, Integer page, Integer limit);

    void addCommonPro(PAppCommonpro pAppCommonpro);

    void editCommonPro(PAppCommonpro pAppCommonpro);

    void delCommonPro(PAppCommonpro pAppCommonpro);

    PageInfo<PAppCommonpro> listCommonPro(PAppCommonpro data, Integer page, Integer limit);

    ResultDO<String> checkName(String title);

    List<PAppColumn> findAllColumn();

    PageInfo<PAppProblem> listAppProblem(PAppProblem data, Integer page, Integer limit);

    void editAppProblem(PAppProblem pAppProblem);
}
