package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PAppCommonpro;
import com.superwork.apcosplatform.domain.PAppCommonproExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PAppCommonproMapper {
    long countByExample(PAppCommonproExample example);

    int deleteByExample(PAppCommonproExample example);

    int deleteByPrimaryKey(String id);

    int insert(PAppCommonpro record);

    int insertSelective(PAppCommonpro record);

    List<PAppCommonpro> selectByExample(PAppCommonproExample example);

    PAppCommonpro selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PAppCommonpro record, @Param("example") PAppCommonproExample example);

    int updateByExample(@Param("record") PAppCommonpro record, @Param("example") PAppCommonproExample example);

    int updateByPrimaryKeySelective(PAppCommonpro record);

    int updateByPrimaryKey(PAppCommonpro record);

    List<PAppCommonpro> listCommonPro(PAppCommonpro data);
}