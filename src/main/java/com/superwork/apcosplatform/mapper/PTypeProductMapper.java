package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PTypeProduct;
import com.superwork.apcosplatform.domain.PTypeProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PTypeProductMapper {
    long countByExample(PTypeProductExample example);

    int deleteByExample(PTypeProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(PTypeProduct record);

    int insertSelective(PTypeProduct record);

    List<PTypeProduct> selectByExample(PTypeProductExample example);

    PTypeProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PTypeProduct record, @Param("example") PTypeProductExample example);

    int updateByExample(@Param("record") PTypeProduct record, @Param("example") PTypeProductExample example);

    int updateByPrimaryKeySelective(PTypeProduct record);

    int updateByPrimaryKey(PTypeProduct record);
}