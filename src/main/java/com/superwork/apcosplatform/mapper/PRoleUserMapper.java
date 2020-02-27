package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PRoleUser;
import com.superwork.apcosplatform.domain.PRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PRoleUserMapper {
    long countByExample(PRoleUserExample example);

    int deleteByExample(PRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(PRoleUser record);

    int insertSelective(PRoleUser record);

    List<PRoleUser> selectByExample(PRoleUserExample example);

    PRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PRoleUser record, @Param("example") PRoleUserExample example);

    int updateByExample(@Param("record") PRoleUser record, @Param("example") PRoleUserExample example);

    int updateByPrimaryKeySelective(PRoleUser record);

    int updateByPrimaryKey(PRoleUser record);
}