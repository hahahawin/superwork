package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PControFormwork;
import com.superwork.apcosplatform.domain.PControFormworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PControFormworkMapper {
    long countByExample(PControFormworkExample example);

    int deleteByExample(PControFormworkExample example);

    int insert(PControFormwork record);

    int insertSelective(PControFormwork record);

    List<PControFormwork> selectByExampleWithBLOBs(PControFormworkExample example);

    List<PControFormwork> selectByExample(PControFormworkExample example);

    int updateByExampleSelective(@Param("record") PControFormwork record, @Param("example") PControFormworkExample example);

    int updateByExampleWithBLOBs(@Param("record") PControFormwork record, @Param("example") PControFormworkExample example);

    int updateByExample(@Param("record") PControFormwork record, @Param("example") PControFormworkExample example);
}