package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.POrgUser;
import com.superwork.apcosplatform.domain.POrgUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface POrgUserMapper {
    long countByExample(POrgUserExample example);

    int deleteByExample(POrgUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(POrgUser record);

    int insertSelective(POrgUser record);

    List<POrgUser> selectByExample(POrgUserExample example);

    POrgUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") POrgUser record, @Param("example") POrgUserExample example);

    int updateByExample(@Param("record") POrgUser record, @Param("example") POrgUserExample example);

    int updateByPrimaryKeySelective(POrgUser record);

    int updateByPrimaryKey(POrgUser record);
}