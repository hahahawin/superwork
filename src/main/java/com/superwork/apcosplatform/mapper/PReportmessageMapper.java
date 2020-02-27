package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PReportmessage;
import com.superwork.apcosplatform.domain.PReportmessageExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PReportmessageMapper {
    long countByExample(PReportmessageExample example);

    int deleteByExample(PReportmessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(PReportmessage record);

    int insertSelective(PReportmessage record);

    List<PReportmessage> selectByExampleWithBLOBs(PReportmessageExample example);

    List<PReportmessage> selectByExample(PReportmessageExample example);

    PReportmessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PReportmessage record, @Param("example") PReportmessageExample example);

    int updateByExampleWithBLOBs(@Param("record") PReportmessage record, @Param("example") PReportmessageExample example);

    int updateByExample(@Param("record") PReportmessage record, @Param("example") PReportmessageExample example);

    int updateByPrimaryKeySelective(PReportmessage record);

    int updateByPrimaryKeyWithBLOBs(PReportmessage record);

    int updateByPrimaryKey(PReportmessage record);

    List<PReportmessage> listPReportmessage(PReportmessage message);
}