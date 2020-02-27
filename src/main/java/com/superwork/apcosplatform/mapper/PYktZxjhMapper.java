package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PYktZxjh;
import com.superwork.apcosplatform.domain.PYktZxjhExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PYktZxjhMapper {
    long countByExample(PYktZxjhExample example);

    int deleteByExample(PYktZxjhExample example);

    int deleteByPrimaryKey(String zxjhId);

    int insert(PYktZxjh record);

    int insertSelective(PYktZxjh record);

    List<PYktZxjh> selectByExample(PYktZxjhExample example);

    PYktZxjh selectByPrimaryKey(String zxjhId);

    int updateByExampleSelective(@Param("record") PYktZxjh record, @Param("example") PYktZxjhExample example);

    int updateByExample(@Param("record") PYktZxjh record, @Param("example") PYktZxjhExample example);

    int updateByPrimaryKeySelective(PYktZxjh record);

    int updateByPrimaryKey(PYktZxjh record);

    List<PYktZxjh> listPlan(PYktZxjh data);
}