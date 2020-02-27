package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSysLog;
import com.superwork.apcosplatform.domain.PSysLogExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PSysLogMapper {
    long countByExample(PSysLogExample example);

    int deleteByExample(PSysLogExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PSysLog record);

    int insertSelective(PSysLog record);

    List<PSysLog> selectByExampleWithBLOBs(PSysLogExample example);

    List<PSysLog> selectByExample(PSysLogExample example);

    PSysLog selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PSysLog record, @Param("example") PSysLogExample example);

    int updateByExampleWithBLOBs(@Param("record") PSysLog record, @Param("example") PSysLogExample example);

    int updateByExample(@Param("record") PSysLog record, @Param("example") PSysLogExample example);

    int updateByPrimaryKeySelective(PSysLog record);

    int updateByPrimaryKeyWithBLOBs(PSysLog record);

    int updateByPrimaryKey(PSysLog record);

    List<PSysLog> listLog(PSysLog data);

    List<Map<String,String>> getRegisterOnWeek();

    List<Map<String,String>> getLoginOnWeek();

    int getCountBySum(PSysLog pSysLog);

    List<PSysLog> listMyLog(PSysLog data);
}