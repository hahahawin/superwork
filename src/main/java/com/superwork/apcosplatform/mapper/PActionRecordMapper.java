package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PActionRecord;
import com.superwork.apcosplatform.domain.PActionRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PActionRecordMapper {
    long countByExample(PActionRecordExample example);

    int deleteByExample(PActionRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(PActionRecord record);

    int insertSelective(PActionRecord record);

    List<PActionRecord> selectByExample(PActionRecordExample example);

    PActionRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PActionRecord record, @Param("example") PActionRecordExample example);

    int updateByExample(@Param("record") PActionRecord record, @Param("example") PActionRecordExample example);

    int updateByPrimaryKeySelective(PActionRecord record);

    int updateByPrimaryKey(PActionRecord record);
}