package com.superwork.apcosplatform.app.service.Impl;

import com.superwork.apcosplatform.app.service.IndexService;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: code->IndexServiceImpl
 * @description: 数据
 * @author: xjj
 * @create: 2019-12-13 15:08
 **/
@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    PYktZxjhMapper pYktZxjhMapper;

    @Autowired
    PSbmoglMapper pSbmoglMapper;

    @Autowired
    PSysLogMapper pSysLogMapper;
    



   public Map<String, Integer> getUserDataWithOther(){
       Map<String, Integer> map = new HashMap<>();
       PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
       PSbgl pSbgl = new PSbgl();
       pSbgl.setCreatorId(user.getUserId());
       List<PSbgl> pSbgls = pSbglMapper.listMySb(pSbgl);//设备数量
       map.put("product",pSbgls.size());

       PYktZxjh pYktZxjh = new PYktZxjh();
       pYktZxjh.setCreatorId(user.getUserId());
       List<PYktZxjh> pYktZxjhs = pYktZxjhMapper.listPlan(pYktZxjh);//执行计划
       map.put("plan",pYktZxjhs.size());

       PSbmogl pSbmogl = new PSbmogl();
       pSbmogl.setCreatorId(user.getUserId());
       List<PSbmogl> pSbmogls = pSbmoglMapper.listMs(pSbmogl);//模式
       map.put("ms",pSbmogls.size());

       return map;

   }

    @Override
    public Integer getCountBySum(String sum) {
        PSysLog pSysLog = new PSysLog();
//        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
//        pSysLog.setUsername(user.getUserAccount());
        pSysLog.setOperation("设备控制");
        pSysLog.setParam(sum);
        Integer countBySum = pSysLogMapper.getCountBySum(pSysLog);
        return countBySum;
    }


}
