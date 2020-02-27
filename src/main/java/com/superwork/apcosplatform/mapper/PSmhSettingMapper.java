package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSmhSetting;
import com.superwork.apcosplatform.domain.PSmhSettingExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PSmhSettingMapper {
    long countByExample(PSmhSettingExample example);

    int deleteByExample(PSmhSettingExample example);

    int deleteByPrimaryKey(BigDecimal settingId);

    int insert(PSmhSetting record);

    int insertSelective(PSmhSetting record);

    List<PSmhSetting> selectByExample(PSmhSettingExample example);

    PSmhSetting selectByPrimaryKey(BigDecimal settingId);

    int updateByExampleSelective(@Param("record") PSmhSetting record, @Param("example") PSmhSettingExample example);

    int updateByExample(@Param("record") PSmhSetting record, @Param("example") PSmhSettingExample example);

    int updateByPrimaryKeySelective(PSmhSetting record);

    int updateByPrimaryKey(PSmhSetting record);

    List<PSmhSetting> listAccount_3(PSmhSetting data);

    List<PSmhSetting> getSmhlist(@Param("id") BigDecimal id);
}