package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSbzcLxExample;
import com.superwork.apcosplatform.domain.PSbzcLx;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PSbzcLxMapper {
    long countByExample(PSbzcLxExample example);

    int deleteByExample(PSbzcLxExample example);

    int deleteByPrimaryKey(String sbzcLxId);

    int insert(PSbzcLx record);

    int insertSelective(PSbzcLx record);

    List<PSbzcLx> selectByExampleWithBLOBs(PSbzcLxExample example);

    List<PSbzcLx> selectByExample(PSbzcLxExample example);

    PSbzcLx selectByPrimaryKey(String sbzcLxId);

    int updateByExampleSelective(@Param("record") PSbzcLx record, @Param("example") PSbzcLxExample example);

    int updateByExampleWithBLOBs(@Param("record") PSbzcLx record, @Param("example") PSbzcLxExample example);

    int updateByExample(@Param("record") PSbzcLx record, @Param("example") PSbzcLxExample example);

    int updateByPrimaryKeySelective(PSbzcLx record);

    int updateByPrimaryKeyWithBLOBs(PSbzcLx record);

    int updateByPrimaryKey(PSbzcLx record);

    List<PSbzcLx>  listsblx(PSbzcLx data);

    int myupdateByPrimaryKey(PSbzcLx data);

    List<PSbzcLx> getSbLxlist(@Param("account") String account);

    List<PSbzcLx> selectHtmlWithMac(@Param("mac") String mac);
}