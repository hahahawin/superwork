package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSysUserEx;
import com.superwork.apcosplatform.domain.PSysUserExExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PSysUserExMapper {
    long countByExample(PSysUserExExample example);

    int deleteByExample(PSysUserExExample example);

    int deleteByPrimaryKey(String id);

    int insert(PSysUserEx record);

    int insertSelective(PSysUserEx record);

    List<PSysUserEx> selectByExampleWithBLOBs(PSysUserExExample example);

    List<PSysUserEx> selectByExample(PSysUserExExample example);

    PSysUserEx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PSysUserEx record, @Param("example") PSysUserExExample example);

    int updateByExampleWithBLOBs(@Param("record") PSysUserEx record, @Param("example") PSysUserExExample example);

    int updateByExample(@Param("record") PSysUserEx record, @Param("example") PSysUserExExample example);

    int updateByPrimaryKeySelective(PSysUserEx record);

    int updateByPrimaryKeyWithBLOBs(PSysUserEx record);

    int updateByPrimaryKey(PSysUserEx record);
}