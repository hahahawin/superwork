package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PZzUser;
import com.superwork.apcosplatform.domain.PZzUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PZzUserMapper {
    long countByExample(PZzUserExample example);

    int deleteByExample(PZzUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(PZzUser record);

    int insertSelective(PZzUser record);

    List<PZzUser> selectByExample(PZzUserExample example);

    PZzUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PZzUser record, @Param("example") PZzUserExample example);

    int updateByExample(@Param("record") PZzUser record, @Param("example") PZzUserExample example);

    int updateByPrimaryKeySelective(PZzUser record);

    int updateByPrimaryKey(PZzUser record);
}