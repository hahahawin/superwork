package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PRole;
import com.superwork.apcosplatform.domain.PRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PRoleMapper {
    long countByExample(PRoleExample example);

    int deleteByExample(PRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(PRole record);

    int insertSelective(PRole record);

    List<PRole> selectByExample(PRoleExample example);

    PRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PRole record, @Param("example") PRoleExample example);

    int updateByExample(@Param("record") PRole record, @Param("example") PRoleExample example);

    int updateByPrimaryKeySelective(PRole record);

    int updateByPrimaryKey(PRole record);

    List<PRole> listRole(PRole data);
}