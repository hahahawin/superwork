package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSbalias;
import com.superwork.apcosplatform.domain.PSbaliasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PSbaliasMapper {
    long countByExample(PSbaliasExample example);

    int deleteByExample(PSbaliasExample example);

    int deleteByPrimaryKey(String id);

    int insert(PSbalias record);

    int insertSelective(PSbalias record);

    List<PSbalias> selectByExampleWithBLOBs(PSbaliasExample example);

    List<PSbalias> selectByExample(PSbaliasExample example);

    PSbalias selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PSbalias record, @Param("example") PSbaliasExample example);

    int updateByExampleWithBLOBs(@Param("record") PSbalias record, @Param("example") PSbaliasExample example);

    int updateByExample(@Param("record") PSbalias record, @Param("example") PSbaliasExample example);

    int updateByPrimaryKeySelective(PSbalias record);

    int updateByPrimaryKeyWithBLOBs(PSbalias record);

    int updateByPrimaryKey(PSbalias record);
}