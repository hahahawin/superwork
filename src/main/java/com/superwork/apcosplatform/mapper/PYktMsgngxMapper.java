package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PYktMsgngx;
import com.superwork.apcosplatform.domain.PYktMsgngxExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PYktMsgngxMapper {
    long countByExample(PYktMsgngxExample example);

    int deleteByExample(PYktMsgngxExample example);

    int deleteByPrimaryKey(String msgngxId);

    int insert(PYktMsgngx record);

    int insertSelective(PYktMsgngx record);

    List<PYktMsgngx> selectByExample(PYktMsgngxExample example);

    PYktMsgngx selectByPrimaryKey(String msgngxId);

    int updateByExampleSelective(@Param("record") PYktMsgngx record, @Param("example") PYktMsgngxExample example);

    int updateByExample(@Param("record") PYktMsgngx record, @Param("example") PYktMsgngxExample example);

    int updateByPrimaryKeySelective(PYktMsgngx record);

    int updateByPrimaryKey(PYktMsgngx record);

    List<PYktMsgngx> listmlBymsId(@Param("data") String data);

    List<PYktMsgngx> getMlByMsName(@Param("data") String data);

    List<Map<String,String>> listmlBymsId3d(@Param("data") String string);
}