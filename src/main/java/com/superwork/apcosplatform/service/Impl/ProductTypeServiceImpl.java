package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PD3orgZc;
import com.superwork.apcosplatform.domain.PProductType;
import com.superwork.apcosplatform.domain.PProductTypeExample;
import com.superwork.apcosplatform.domain.PTypeProductExample;
import com.superwork.apcosplatform.mapper.PProductTypeMapper;
import com.superwork.apcosplatform.mapper.PTypeProductMapper;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.ProductTypeService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jianjian Xu
 * @create: 2020/2/11 16:22
 * @description:
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    PProductTypeMapper pProductTypeMapper;

    @Autowired
    PTypeProductMapper pTypeProductMapper;

    @Override
    public PageInfo<PProductType> listMyProType(PProductType data, Integer page, Integer limit) {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        data.setCompanyId(bccaServiceInfo.getId().toString());
        PageHelper.startPage(page,limit);
        List<PProductType> pProductTypes = pProductTypeMapper.listMyProType(data);
        PageInfo<PProductType> pageInfo = new PageInfo<>(pProductTypes);
        return pageInfo;
    }

    @Override
    public ResultDO<String> addProType(PProductType productType) {
        ResultDO<String> resultDO = new ResultDO<>();
        PProductTypeExample pProductTypeExample = new PProductTypeExample();
        PProductTypeExample.Criteria criteria = pProductTypeExample.createCriteria();
        criteria.andCompanyIdEqualTo(productType.getCompanyId()).andTypeNameEqualTo(productType.getTypeName());
        List<PProductType> pProductTypes = pProductTypeMapper.selectByExample(pProductTypeExample);
        if(pProductTypes.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("类型名已存在！");
            return resultDO;
        }
        pProductTypeMapper.insert(productType);
        return resultDO;

    }

    @Override
    public ResultDO<String> editProType(PProductType productType) {
        ResultDO<String> resultDO = new ResultDO<>();
        PProductTypeExample pProductTypeExample = new PProductTypeExample();
        PProductTypeExample.Criteria criteria = pProductTypeExample.createCriteria();
        criteria.andCompanyIdEqualTo(productType.getCompanyId()).andTypeNameEqualTo(productType.getTypeName()).andIdNotEqualTo(productType.getId());
        List<PProductType> pProductTypes = pProductTypeMapper.selectByExample(pProductTypeExample);
        if(pProductTypes.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("类型名已存在！");
            return resultDO;
        }
        pProductTypeMapper.updateByPrimaryKeySelective(productType);
        return resultDO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delProType(PProductType productType) {
        pProductTypeMapper.deleteByPrimaryKey(productType.getId());
        PTypeProductExample pTypeProductExample = new PTypeProductExample();
        PTypeProductExample.Criteria criteria = pTypeProductExample.createCriteria();
        criteria.andTypeIdEqualTo(productType.getId());
        pTypeProductMapper.deleteByExample(pTypeProductExample);
    }


}
