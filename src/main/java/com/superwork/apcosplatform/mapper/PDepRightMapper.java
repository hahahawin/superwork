package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PDepRight;
import com.superwork.apcosplatform.domain.PDepRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDepRightMapper {
    long countByExample(PDepRightExample example);

    int deleteByExample(PDepRightExample example);

    int deleteByPrimaryKey(String id);

    int insert(PDepRight record);

    int insertSelective(PDepRight record);

    List<PDepRight> selectByExample(PDepRightExample example);

    PDepRight selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PDepRight record, @Param("example") PDepRightExample example);

    int updateByExample(@Param("record") PDepRight record, @Param("example") PDepRightExample example);

    int updateByPrimaryKeySelective(PDepRight record);

    int updateByPrimaryKey(PDepRight record);
}