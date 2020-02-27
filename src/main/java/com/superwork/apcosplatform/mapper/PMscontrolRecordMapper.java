package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PMscontrolRecord;
import com.superwork.apcosplatform.domain.PMscontrolRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PMscontrolRecordMapper {
    long countByExample(PMscontrolRecordExample example);

    int deleteByExample(PMscontrolRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(PMscontrolRecord record);

    int insertSelective(PMscontrolRecord record);

    List<PMscontrolRecord> selectByExampleWithBLOBs(PMscontrolRecordExample example);

    List<PMscontrolRecord> selectByExample(PMscontrolRecordExample example);

    PMscontrolRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PMscontrolRecord record, @Param("example") PMscontrolRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") PMscontrolRecord record, @Param("example") PMscontrolRecordExample example);

    int updateByExample(@Param("record") PMscontrolRecord record, @Param("example") PMscontrolRecordExample example);

    int updateByPrimaryKeySelective(PMscontrolRecord record);

    int updateByPrimaryKeyWithBLOBs(PMscontrolRecord record);

    int updateByPrimaryKey(PMscontrolRecord record);

    List<PMscontrolRecord> listMsControl(PMscontrolRecord data);
}