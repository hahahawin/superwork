package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PScene;
import com.superwork.apcosplatform.domain.PSceneExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PSceneMapper {
    long countByExample(PSceneExample example);

    int deleteByExample(PSceneExample example);

    int deleteByPrimaryKey(String id);

    int insert(PScene record);

    int insertSelective(PScene record);

    List<PScene> selectByExampleWithBLOBs(PSceneExample example);

    List<PScene> selectByExample(PSceneExample example);

    PScene selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PScene record, @Param("example") PSceneExample example);

    int updateByExampleWithBLOBs(@Param("record") PScene record, @Param("example") PSceneExample example);

    int updateByExample(@Param("record") PScene record, @Param("example") PSceneExample example);

    int updateByPrimaryKeySelective(PScene record);

    int updateByPrimaryKeyWithBLOBs(PScene record);

    int updateByPrimaryKey(PScene record);

    List<PScene> listScene(PScene pScene);
}