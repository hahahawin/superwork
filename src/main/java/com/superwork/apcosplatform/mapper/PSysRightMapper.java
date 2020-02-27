package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSysRight;
import com.superwork.apcosplatform.domain.PSysRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PSysRightMapper {
    long countByExample(PSysRightExample example);

    int deleteByExample(PSysRightExample example);

    int deleteByPrimaryKey(String rightId);

    int insert(PSysRight record);

    int insertSelective(PSysRight record);

    List<PSysRight> selectByExample(PSysRightExample example);

    PSysRight selectByPrimaryKey(String rightId);

    int updateByExampleSelective(@Param("record") PSysRight record, @Param("example") PSysRightExample example);

    int updateByExample(@Param("record") PSysRight record, @Param("example") PSysRightExample example);

    int updateByPrimaryKeySelective(PSysRight record);

    int updateByPrimaryKey(PSysRight record);

    List<String> loadRight();

    List<PSysRight> loadMenu(@Param("userId") String userId);

    List<String> selectMyRight(String userId);

    List<PSysRight>  loadMyRight(@Param("userId") String userId);

    List<PSysRight> listApplication(PSysRight data);
}