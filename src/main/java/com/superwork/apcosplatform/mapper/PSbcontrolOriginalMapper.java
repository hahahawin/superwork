package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSbcontrolOriginal;
import com.superwork.apcosplatform.domain.PSbcontrolOriginalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PSbcontrolOriginalMapper {
    long countByExample(PSbcontrolOriginalExample example);

    int deleteByExample(PSbcontrolOriginalExample example);

    int deleteByPrimaryKey(String id);

    int insert(PSbcontrolOriginal record);

    int insertSelective(PSbcontrolOriginal record);

    List<PSbcontrolOriginal> selectByExample(PSbcontrolOriginalExample example);

    PSbcontrolOriginal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PSbcontrolOriginal record, @Param("example") PSbcontrolOriginalExample example);

    int updateByExample(@Param("record") PSbcontrolOriginal record, @Param("example") PSbcontrolOriginalExample example);

    int updateByPrimaryKeySelective(PSbcontrolOriginal record);

    int updateByPrimaryKey(PSbcontrolOriginal record);

    List<PSbcontrolOriginal> listSbControl(PSbcontrolOriginal pSbcontrolOriginal);
}