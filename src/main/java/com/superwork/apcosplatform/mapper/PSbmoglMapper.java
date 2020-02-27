package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSbmoglExample;
import com.superwork.apcosplatform.domain.PSbmogl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PSbmoglMapper {
    long countByExample(PSbmoglExample example);

    int deleteByExample(PSbmoglExample example);

    int deleteByPrimaryKey(String sbmoglId);

    int insert(PSbmogl record);

    int insertSelective(PSbmogl record);

    List<PSbmogl> selectByExample(PSbmoglExample example);

    PSbmogl selectByPrimaryKey(String sbmoglId);

    int updateByExampleSelective(@Param("record") PSbmogl record, @Param("example") PSbmoglExample example);

    int updateByExample(@Param("record") PSbmogl record, @Param("example") PSbmoglExample example);

    int updateByPrimaryKeySelective(PSbmogl record);

    int updateByPrimaryKey(PSbmogl record);

    List<PSbmogl> listMs(PSbmogl data);

    List<PSbmogl> listMyMs3d(PSbmogl pSbmogl);

    List<Map<String,String>> getZhms(@Param("account") String account);

    List<PSbmogl> listmsBysum(@Param("sum") String sum);
}