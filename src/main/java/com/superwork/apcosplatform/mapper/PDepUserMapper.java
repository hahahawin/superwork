package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PDepUser;
import com.superwork.apcosplatform.domain.PDepUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDepUserMapper {
    long countByExample(PDepUserExample example);

    int deleteByExample(PDepUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(PDepUser record);

    int insertSelective(PDepUser record);

    List<PDepUser> selectByExample(PDepUserExample example);

    PDepUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PDepUser record, @Param("example") PDepUserExample example);

    int updateByExample(@Param("record") PDepUser record, @Param("example") PDepUserExample example);

    int updateByPrimaryKeySelective(PDepUser record);

    int updateByPrimaryKey(PDepUser record);
}