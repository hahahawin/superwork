package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PZnsbMszh;
import com.superwork.apcosplatform.domain.PZnsbMszhExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PZnsbMszhMapper {
    long countByExample(PZnsbMszhExample example);

    int deleteByExample(PZnsbMszhExample example);

    int deleteByPrimaryKey(String mszhId);

    int insert(PZnsbMszh record);

    int insertSelective(PZnsbMszh record);

    List<PZnsbMszh> selectByExample(PZnsbMszhExample example);

    PZnsbMszh selectByPrimaryKey(String mszhId);

    int updateByExampleSelective(@Param("record") PZnsbMszh record, @Param("example") PZnsbMszhExample example);

    int updateByExample(@Param("record") PZnsbMszh record, @Param("example") PZnsbMszhExample example);

    int updateByPrimaryKeySelective(PZnsbMszh record);

    int updateByPrimaryKey(PZnsbMszh record);

    List<PZnsbMszh> listMs(PZnsbMszh data);
}