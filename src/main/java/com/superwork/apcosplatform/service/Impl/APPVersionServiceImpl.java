package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PAppColumnMapper;
import com.superwork.apcosplatform.mapper.PAppCommonproMapper;
import com.superwork.apcosplatform.mapper.PAppProblemMapper;
import com.superwork.apcosplatform.mapper.PAppSoftwareMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.APPVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: code->APPVersionServiceImpl
 * @description:
 * @author: xjj
 * @create: 2019-12-16 13:39
 **/
@Service
public class APPVersionServiceImpl implements APPVersionService {


    @Autowired
    PAppSoftwareMapper pAppSoftwareMapper;
    @Autowired
    PAppColumnMapper pAppColumnMapper;
    @Autowired
    PAppCommonproMapper pAppCommonproMapper;
    @Autowired
    PAppProblemMapper pAppProblemMapper;

    @Override
    public PageInfo<PAppSoftware> listApp(PAppSoftware data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PAppSoftware> pAppSoftwares = pAppSoftwareMapper.listApp(data);
        PageInfo<PAppSoftware> pAppSoftwarePageInfo = new PageInfo<>(pAppSoftwares);
        return pAppSoftwarePageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pAppSoftware(PAppSoftware pAppSoftware) {
        if(pAppSoftware.getAttr2().equals("yes")){
            PAppSoftwareExample pAppSoftwareExample = new PAppSoftwareExample();
            PAppSoftwareExample.Criteria criteria = pAppSoftwareExample.createCriteria();
            criteria.andAttr2EqualTo("yes").andStatusEqualTo(pAppSoftware.getStatus());
            PAppSoftware pAppSoftware1 = new PAppSoftware();
            pAppSoftware1.setAttr2("no");
            pAppSoftwareMapper.updateByExampleSelective(pAppSoftware1,pAppSoftwareExample);
        }
        pAppSoftwareMapper.insert(pAppSoftware);
    }

    @Override
    public void editApp(PAppSoftware pAppSoftware) {
        if(pAppSoftware.getAttr2().equals("yes")){
            PAppSoftwareExample pAppSoftwareExample = new PAppSoftwareExample();
            PAppSoftwareExample.Criteria criteria = pAppSoftwareExample.createCriteria();
            criteria.andAttr2EqualTo("yes").andStatusEqualTo(pAppSoftware.getStatus());
            PAppSoftware pAppSoftware1 = new PAppSoftware();
            pAppSoftware1.setAttr2("no");
            pAppSoftwareMapper.updateByExampleSelective(pAppSoftware1,pAppSoftwareExample);
        }
        pAppSoftware.setCreatedate(null);
        pAppSoftware.setCreateid(null);
        pAppSoftware.setPath(null);
        pAppSoftware.setStatus(null);
        pAppSoftwareMapper.updateByPrimaryKeySelective(pAppSoftware);
    }

    @Override
    public PAppSoftware getAppPath(String type) {
        PAppSoftware appPath = pAppSoftwareMapper.getAppPath(type);
        return appPath;
    }

    @Override
    public PAppSoftware scanning(String id) {
        PAppSoftware pAppSoftware = pAppSoftwareMapper.selectByPrimaryKey(id);
        int sun = pAppSoftware.getAttr1() == null ? 0 : Integer.valueOf(pAppSoftware.getAttr1());
        pAppSoftware.setAttr1(String.valueOf(sun + 1));
        pAppSoftwareMapper.updateByPrimaryKeySelective(pAppSoftware);
        return pAppSoftware;
    }

    @Override
    public void addColumn(PAppColumn pAppColumn) {
        pAppColumnMapper.insert(pAppColumn);
    }

    @Override
    public ResultDO<String> editColumn(PAppColumn pAppColumn) {

        ResultDO<String> resultDO = new ResultDO<>();
        PAppColumn pAppColumn1 = pAppColumnMapper.selectByPrimaryKey(pAppColumn.getId());

        if (pAppColumn1.getTitle().equals(pAppColumn.getTitle())) {
            pAppColumnMapper.updateByPrimaryKeySelective(pAppColumn);
        } else {
            ResultDO<String> stringResultDO = checkName(pAppColumn.getTitle());
            if (!stringResultDO.isSuccess()) {
                resultDO = stringResultDO;
            }else{
                pAppColumnMapper.updateByPrimaryKeySelective(pAppColumn);
            }

        }
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delColumn(PAppColumn pAppColumn) {
        pAppColumnMapper.deleteByPrimaryKey(pAppColumn.getId());
        PAppCommonproExample pAppCommonproExample = new PAppCommonproExample();
        PAppCommonproExample.Criteria criteria = pAppCommonproExample.createCriteria();
        criteria.andColumuIdEqualTo(pAppColumn.getId());
        pAppCommonproMapper.deleteByExample(pAppCommonproExample);
    }

    @Override
    public PageInfo<PAppColumn> listColumn(PAppColumn data, Integer page, Integer limit) {

        PageHelper.startPage(page, limit);
        List<PAppColumn> pAppColumns = pAppColumnMapper.listColumn(data);
        PageInfo<PAppColumn> pAppColumnPageInfo = new PageInfo<>(pAppColumns);
        return pAppColumnPageInfo;
    }

    @Override
    public void addCommonPro(PAppCommonpro pAppCommonpro) {
        pAppCommonproMapper.insert(pAppCommonpro);
    }

    @Override
    public void editCommonPro(PAppCommonpro pAppCommonpro) {
        pAppCommonproMapper.updateByPrimaryKeySelective(pAppCommonpro);
    }

    @Override
    public void delCommonPro(PAppCommonpro pAppCommonpro) {
        pAppCommonproMapper.deleteByPrimaryKey(pAppCommonpro.getId());
    }

    @Override
    public PageInfo<PAppCommonpro> listCommonPro(PAppCommonpro data, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PAppCommonpro> pAppCommonpros = pAppCommonproMapper.listCommonPro(data);
        PageInfo<PAppCommonpro> pAppCommonproPageInfo = new PageInfo<>(pAppCommonpros);
        return pAppCommonproPageInfo;
    }

    @Override
    public ResultDO<String> checkName(String title) {
        ResultDO<String> resultDO = new ResultDO<>();
        PAppColumnExample pAppColumnExample = new PAppColumnExample();
        PAppColumnExample.Criteria criteria = pAppColumnExample.createCriteria();
        criteria.andTitleEqualTo(title);
        List<PAppColumn> pAppColumns = pAppColumnMapper.selectByExample(pAppColumnExample);
        if (pAppColumns.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("名字已存在！");
        }
        return resultDO;
    }

    @Override
    public List<PAppColumn> findAllColumn() {
        PAppColumnExample pAppColumnExample = new PAppColumnExample();
        List<PAppColumn> pAppColumns = pAppColumnMapper.selectByExample(pAppColumnExample);
        return pAppColumns;
    }

    @Override
    public PageInfo<PAppProblem> listAppProblem(PAppProblem data, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PAppProblem> pAppProblems = pAppProblemMapper.listAppProblem(data);
        PageInfo<PAppProblem> pAppProblemPageInfo = new PageInfo<>(pAppProblems);
        return pAppProblemPageInfo;
    }

    @Override
    public void editAppProblem(PAppProblem pAppProblem) {
        pAppProblemMapper.updateByPrimaryKeySelective(pAppProblem);
    }
}
