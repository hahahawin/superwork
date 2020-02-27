package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PWebPropertyInfo;
import com.superwork.apcosplatform.domain.PWebPropertyInfoExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PWebPropertyInfoMapper {
    long countByExample(PWebPropertyInfoExample example);

    int deleteByExample(PWebPropertyInfoExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PWebPropertyInfo record);

    int insertSelective(PWebPropertyInfo record);

    List<PWebPropertyInfo> selectByExample(PWebPropertyInfoExample example);

    PWebPropertyInfo selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PWebPropertyInfo record, @Param("example") PWebPropertyInfoExample example);

    int updateByExample(@Param("record") PWebPropertyInfo record, @Param("example") PWebPropertyInfoExample example);

    int updateByPrimaryKeySelective(PWebPropertyInfo record);

    int updateByPrimaryKey(PWebPropertyInfo record);
}