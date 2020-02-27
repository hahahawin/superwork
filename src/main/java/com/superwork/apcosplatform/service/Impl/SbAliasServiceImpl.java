package com.superwork.apcosplatform.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.domain.PSbalias;
import com.superwork.apcosplatform.domain.PSbaliasExample;
import com.superwork.apcosplatform.mapper.PSbaliasMapper;
import com.superwork.apcosplatform.service.SbAliasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: code->SbAliasServiceImpl
 * @description: 设备别名
 * @author: xjj
 * @create: 2020-01-07 14:27
 **/
@Service
public class SbAliasServiceImpl implements SbAliasService {

    @Autowired
    PSbaliasMapper pSbaliasMapper;

    @Override
    public String getRemark(String serialNum) {
        PSbaliasExample pSbaliasExample = new PSbaliasExample();
        PSbaliasExample.Criteria criteria = pSbaliasExample.createCriteria();
        criteria.andSerialnumEqualTo(serialNum);
        List<PSbalias> pSbaliases = pSbaliasMapper.selectByExampleWithBLOBs(pSbaliasExample);
        if (pSbaliases.size() == 0) {
            return null;
        } else {
            return pSbaliases.get(0).getRemark();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRemark(JSONObject jsonObject) throws Exception {
        String serialNum = jsonObject.getString("serialNum");
        String remark = jsonObject.getString("remark");
        if (StringUtils.isEmpty(serialNum) || StringUtils.isEmpty(remark)) {
            throw new Exception("MAC地址或别名为空");
        }
        PSbaliasExample pSbaliasExample = new PSbaliasExample();
        PSbaliasExample.Criteria criteria = pSbaliasExample.createCriteria();
        criteria.andSerialnumEqualTo(serialNum);
        pSbaliasMapper.deleteByExample(pSbaliasExample);
        PSbalias pSbalias = new PSbalias();
        pSbalias.setRemark(remark);
        pSbalias.setSerialnum(serialNum);
        pSbaliasMapper.insert(pSbalias);
    }
}
