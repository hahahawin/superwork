package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSbglExample;
import com.superwork.apcosplatform.domain.PSbgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PSbglMapper {
    long countByExample(PSbglExample example);

    int deleteByExample(PSbglExample example);

    int deleteByPrimaryKey(String id);

    int insert(PSbgl record);

    int insertSelective(PSbgl record);

    List<PSbgl> selectByExample(PSbglExample example);

    PSbgl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PSbgl record, @Param("example") PSbglExample example);

    int updateByExample(@Param("record") PSbgl record, @Param("example") PSbglExample example);

    int updateByPrimaryKeySelective(PSbgl record);

    int updateByPrimaryKey(PSbgl record);


    List<PSbgl> listsb(PSbgl data);

    List<Map<String, String>> selCfzByTjlx(Map<String, String> maps);

    List<PSbgl>  listMySb(PSbgl data);

    List<PSbgl>  listMysbOn3d(PSbgl data);

    List<Map<String,String>>  getSblist(Map<String, String> map1);

    List<Map<String,String>>  getEquipList(Map<String, String> map);
}