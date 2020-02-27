package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PWebProductCmdUpParam;
import com.superwork.apcosplatform.domain.PWebProductCmdUpParamExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PWebProductCmdUpParamMapper {
    long countByExample(PWebProductCmdUpParamExample example);

    int deleteByExample(PWebProductCmdUpParamExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PWebProductCmdUpParam record);

    int insertSelective(PWebProductCmdUpParam record);

    List<PWebProductCmdUpParam> selectByExampleWithBLOBs(PWebProductCmdUpParamExample example);

    List<PWebProductCmdUpParam> selectByExample(PWebProductCmdUpParamExample example);

    PWebProductCmdUpParam selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PWebProductCmdUpParam record, @Param("example") PWebProductCmdUpParamExample example);

    int updateByExampleWithBLOBs(@Param("record") PWebProductCmdUpParam record, @Param("example") PWebProductCmdUpParamExample example);

    int updateByExample(@Param("record") PWebProductCmdUpParam record, @Param("example") PWebProductCmdUpParamExample example);

    int updateByPrimaryKeySelective(PWebProductCmdUpParam record);

    int updateByPrimaryKeyWithBLOBs(PWebProductCmdUpParam record);

    int updateByPrimaryKey(PWebProductCmdUpParam record);
}