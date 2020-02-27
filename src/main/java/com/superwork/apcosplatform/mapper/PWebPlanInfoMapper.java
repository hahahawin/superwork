package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PWebPlanInfo;
import com.superwork.apcosplatform.domain.PWebPlanInfoExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PWebPlanInfoMapper {
    long countByExample(PWebPlanInfoExample example);

    int deleteByExample(PWebPlanInfoExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PWebPlanInfo record);

    int insertSelective(PWebPlanInfo record);

    List<PWebPlanInfo> selectByExample(PWebPlanInfoExample example);

    PWebPlanInfo selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PWebPlanInfo record, @Param("example") PWebPlanInfoExample example);

    int updateByExample(@Param("record") PWebPlanInfo record, @Param("example") PWebPlanInfoExample example);

    int updateByPrimaryKeySelective(PWebPlanInfo record);

    int updateByPrimaryKey(PWebPlanInfo record);
}