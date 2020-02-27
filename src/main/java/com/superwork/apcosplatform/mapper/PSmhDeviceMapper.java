//package com.superwork.apcosplatform.mapper;
//
//import com.superwork.apcosplatform.domain.PSmhDevice;
//import com.superwork.apcosplatform.domain.PSmhDeviceExample;
//
//import java.util.List;
//import org.apache.ibatis.annotations.Param;
//
//public interface PSmhDeviceMapper {
//    long countByExample(PSmhDeviceExample example);
//
//    int deleteByExample(PSmhDeviceExample example);
//
//    int deleteByPrimaryKey(String id);
//
//    int insert(PSmhDevice record);
//
//    int insertSelective(PSmhDevice record);
//
//    List<PSmhDevice> selectByExample(PSmhDeviceExample example);
//
//    PSmhDevice selectByPrimaryKey(String id);
//
//    int updateByExampleSelective(@Param("record") PSmhDevice record, @Param("example") PSmhDeviceExample example);
//
//    int updateByExample(@Param("record") PSmhDevice record, @Param("example") PSmhDeviceExample example);
//
//    int updateByPrimaryKeySelective(PSmhDevice record);
//
//    int updateByPrimaryKey(PSmhDevice record);
//}