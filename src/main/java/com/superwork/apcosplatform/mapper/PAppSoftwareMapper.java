package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PAppSoftware;
import com.superwork.apcosplatform.domain.PAppSoftwareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PAppSoftwareMapper {
    long countByExample(PAppSoftwareExample example);

    int deleteByExample(PAppSoftwareExample example);

    int deleteByPrimaryKey(String id);

    int insert(PAppSoftware record);

    int insertSelective(PAppSoftware record);

    List<PAppSoftware> selectByExample(PAppSoftwareExample example);

    PAppSoftware selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PAppSoftware record, @Param("example") PAppSoftwareExample example);

    int updateByExample(@Param("record") PAppSoftware record, @Param("example") PAppSoftwareExample example);

    int updateByPrimaryKeySelective(PAppSoftware record);

    int updateByPrimaryKey(PAppSoftware record);

    List<PAppSoftware> listApp(PAppSoftware data);

    PAppSoftware getAppPath(@Param("type") String type);
}