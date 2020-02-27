package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PD3orgZcExample;
import com.superwork.apcosplatform.domain.PD3orgZc;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PD3orgZcMapper {
    long countByExample(PD3orgZcExample example);

    int deleteByExample(PD3orgZcExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PD3orgZc record);

    int insertSelective(PD3orgZc record);

    List<PD3orgZc> selectByExample(PD3orgZcExample example);

    PD3orgZc selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PD3orgZc record, @Param("example") PD3orgZcExample example);

    int updateByExample(@Param("record") PD3orgZc record, @Param("example") PD3orgZcExample example);

    int updateByPrimaryKeySelective(PD3orgZc record);

    int updateByPrimaryKey(PD3orgZc record);

    PD3orgZc selectServiceInfo(@Param("userId") String userId);

    List<PD3orgZc> listOrg(PD3orgZc data);
}