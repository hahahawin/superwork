package com.superwork.apcosplatform.app.service.Impl;

import com.superwork.apcosplatform.app.service.AppProblemService;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PActionRecordMapper;
import com.superwork.apcosplatform.mapper.PAppColumnMapper;
import com.superwork.apcosplatform.mapper.PAppCommonproMapper;
import com.superwork.apcosplatform.mapper.PAppProblemMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @program: code->AppProblemServiceImpl
 * @description:
 * @author: xjj
 * @create: 2019-12-18 14:20
 **/
@Service
public class AppProblemServiceImpl implements AppProblemService {
    @Autowired
    PAppProblemMapper pAppProblemMapper;
    @Autowired
    PAppColumnMapper pAppColumnMapper;

    @Autowired
    PAppCommonproMapper pAppCommonproMapper;

    @Autowired
    PActionRecordMapper pActionRecordMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void addProblem(PAppProblem pAppProblem) {
        pAppProblemMapper.insert(pAppProblem);
    }

    @Override
    public List<PAppProblem> listMyProblem(String userId) {
        PAppProblemExample pAppProblemExample = new PAppProblemExample();
        PAppProblemExample.Criteria criteria = pAppProblemExample.createCriteria();
        criteria.andCreateIdEqualTo(userId);
        List<PAppProblem> pAppProblems = pAppProblemMapper.selectByExample(pAppProblemExample);
        return pAppProblems;
    }

    @Override
    public List<PAppColumn> listCommonProblem(String query) {
        List<PAppColumn> pAppColumns = pAppColumnMapper.listCommonProblem(query);
//        PAppColumnExample pAppColumnExample = new PAppColumnExample();
//        List<PAppColumn> pAppColumns = pAppColumnMapper.selectByExample(pAppColumnExample);
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        for (PAppColumn pAppColumn : pAppColumns) {
            PAppCommonproExample pAppCommonproExample = new PAppCommonproExample();
            PAppCommonproExample.Criteria criteria = pAppCommonproExample.createCriteria();
            if (StringUtils.isEmpty(query)) {
                criteria.andColumuIdEqualTo(pAppColumn.getId());
            } else {
                criteria.andColumuIdEqualTo(pAppColumn.getId()).andProTitleLike("%" + query + "%");
            }
            List<PAppCommonpro> pAppCommonpros = pAppCommonproMapper.selectByExample(pAppCommonproExample);
            for (PAppCommonpro pAppCommonpro : pAppCommonpros) {
                PActionRecordExample pActionRecordExample = new PActionRecordExample();
                PActionRecordExample.Criteria criteria1 = pActionRecordExample.createCriteria();
                criteria1.andUserIdEqualTo(user.getUserId()).andComonproIdEqualTo(pAppCommonpro.getId());
                List<PActionRecord> pActionRecords = pActionRecordMapper.selectByExample(pActionRecordExample);
                if (pActionRecords.size() > 0) {
                    pAppCommonpro.setEvaluateType(pActionRecords.get(0).getAttr1());
                }
            }
            pAppColumn.setListCommonPro(pAppCommonpros);
        }
        return pAppColumns;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDO<String> commonProYesOrNo(String id, String decision) {
        ResultDO<String> resultDO = new ResultDO<>();
        if ("yes".equals(decision) || "no".equals(decision)) {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            PAppCommonpro pAppCommonpro = pAppCommonproMapper.selectByPrimaryKey(id);
            int yseNum = pAppCommonpro.getFavorNum() == null ? 0 : Integer.valueOf(pAppCommonpro.getFavorNum());
            int noNum = pAppCommonpro.getOppositionNum() == null ? 0 : Integer.valueOf(pAppCommonpro.getOppositionNum());
            PActionRecordExample pActionRecordExample = new PActionRecordExample();
            PActionRecordExample.Criteria criteria = pActionRecordExample.createCriteria();
            criteria.andUserIdEqualTo(user.getUserId()).andComonproIdEqualTo(id);
            List<PActionRecord> pActionRecords = pActionRecordMapper.selectByExample(pActionRecordExample);
            PActionRecord pActionRecord = null;
            if (pActionRecords.size() > 0) {
                pActionRecord = pActionRecords.get(0);
                if (pActionRecord.getAttr1().equals(decision)) {
                    resultDO.setSuccess(false);
                    resultDO.setErrorMsg("不能重复评价！");
                    resultDO.setErrorCode(201);
                    return resultDO;
                }
                pActionRecord.setAttr1(decision);
                pActionRecordMapper.updateByPrimaryKeySelective(pActionRecord);
            } else {
                pActionRecord = new PActionRecord();
                pActionRecord.setUserId(user.getUserId());
                pActionRecord.setComonproId(id);
                pActionRecord.setAttr1(decision);
                pActionRecord.setCreateTime(new Date());
                pActionRecordMapper.insert(pActionRecord);
            }
            if ("yes".equals(decision)) {
                yseNum = yseNum+1;
                pAppCommonpro.setFavorNum(String.valueOf(yseNum));
            } else if ("no".equals(decision)) {
                noNum = noNum + 1;
                pAppCommonpro.setOppositionNum(String.valueOf(noNum));
            }
            pAppCommonproMapper.updateByPrimaryKeySelective(pAppCommonpro);
        } else {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("参数错误！");
            resultDO.setErrorCode(202);
            return resultDO;
        }
        return resultDO;
    }


}
