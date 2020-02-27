package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PProductType;
import com.superwork.apcosplatform.domain.PProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PProductTypeMapper {
    long countByExample(PProductTypeExample example);

    int deleteByExample(PProductTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(PProductType record);

    int insertSelective(PProductType record);

    List<PProductType> selectByExample(PProductTypeExample example);

    PProductType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PProductType record, @Param("example") PProductTypeExample example);

    int updateByExample(@Param("record") PProductType record, @Param("example") PProductTypeExample example);

    int updateByPrimaryKeySelective(PProductType record);

    int updateByPrimaryKey(PProductType record);

    List<PProductType> listMyProType(PProductType data);
}