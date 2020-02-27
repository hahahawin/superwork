package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PWebProductCmdUp;
import com.superwork.apcosplatform.domain.PWebProductCmdUpExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PWebProductCmdUpMapper {
    long countByExample(PWebProductCmdUpExample example);

    int deleteByExample(PWebProductCmdUpExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PWebProductCmdUp record);

    int insertSelective(PWebProductCmdUp record);

    List<PWebProductCmdUp> selectByExampleWithBLOBs(PWebProductCmdUpExample example);

    List<PWebProductCmdUp> selectByExample(PWebProductCmdUpExample example);

    PWebProductCmdUp selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PWebProductCmdUp record, @Param("example") PWebProductCmdUpExample example);

    int updateByExampleWithBLOBs(@Param("record") PWebProductCmdUp record, @Param("example") PWebProductCmdUpExample example);

    int updateByExample(@Param("record") PWebProductCmdUp record, @Param("example") PWebProductCmdUpExample example);

    int updateByPrimaryKeySelective(PWebProductCmdUp record);

    int updateByPrimaryKeyWithBLOBs(PWebProductCmdUp record);

    int updateByPrimaryKey(PWebProductCmdUp record);
}