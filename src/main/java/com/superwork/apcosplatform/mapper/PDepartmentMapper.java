package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PDepartment;
import com.superwork.apcosplatform.domain.PDepartmentExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDepartmentMapper {
    long countByExample(PDepartmentExample example);

    int deleteByExample(PDepartmentExample example);

    int deleteByPrimaryKey(String depId);

    int insert(PDepartment record);

    int insertSelective(PDepartment record);

    List<PDepartment> selectByExample(PDepartmentExample example);

    PDepartment selectByPrimaryKey(String depId);

    int updateByExampleSelective(@Param("record") PDepartment record, @Param("example") PDepartmentExample example);

    int updateByExample(@Param("record") PDepartment record, @Param("example") PDepartmentExample example);

    int updateByPrimaryKeySelective(PDepartment record);

    int updateByPrimaryKey(PDepartment record);

    List<PDepartment> listDepartment(PDepartment data);
}