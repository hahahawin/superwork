package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PAppColumn;
import com.superwork.apcosplatform.domain.PAppColumnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PAppColumnMapper {
    long countByExample(PAppColumnExample example);

    int deleteByExample(PAppColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(PAppColumn record);

    int insertSelective(PAppColumn record);

    List<PAppColumn> selectByExample(PAppColumnExample example);

    PAppColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PAppColumn record, @Param("example") PAppColumnExample example);

    int updateByExample(@Param("record") PAppColumn record, @Param("example") PAppColumnExample example);

    int updateByPrimaryKeySelective(PAppColumn record);

    int updateByPrimaryKey(PAppColumn record);

    List<PAppColumn> listColumn(PAppColumn data);

    List<PAppColumn> listCommonProblem(@Param("query") String query);
}