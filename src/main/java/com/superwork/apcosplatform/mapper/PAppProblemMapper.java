package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PAppProblem;
import com.superwork.apcosplatform.domain.PAppProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PAppProblemMapper {
    long countByExample(PAppProblemExample example);

    int deleteByExample(PAppProblemExample example);

    int deleteByPrimaryKey(String id);

    int insert(PAppProblem record);

    int insertSelective(PAppProblem record);

    List<PAppProblem> selectByExample(PAppProblemExample example);

    PAppProblem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PAppProblem record, @Param("example") PAppProblemExample example);

    int updateByExample(@Param("record") PAppProblem record, @Param("example") PAppProblemExample example);

    int updateByPrimaryKeySelective(PAppProblem record);

    int updateByPrimaryKey(PAppProblem record);

    List<PAppProblem> listAppProblem(PAppProblem data);
}