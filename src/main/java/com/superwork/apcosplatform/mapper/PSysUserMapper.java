package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.PSysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PSysUserMapper {
    long countByExample(PSysUserExample example);

    int deleteByExample(PSysUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(PSysUser record);

    int insertSelective(PSysUser record);

    List<PSysUser> selectByExampleWithBLOBs(PSysUserExample example);

    List<PSysUser> selectByExample(PSysUserExample example);

    PSysUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") PSysUser record, @Param("example") PSysUserExample example);

    int updateByExampleWithBLOBs(@Param("record") PSysUser record, @Param("example") PSysUserExample example);

    int updateByExample(@Param("record") PSysUser record, @Param("example") PSysUserExample example);

    int updateByPrimaryKeySelective(PSysUser record);

    int updateByPrimaryKeyWithBLOBs(PSysUser record);

    int updateByPrimaryKey(PSysUser record);

    List<PSysUser> listUser(PSysUser data);

    List<PSysUser> listMyUser(PSysUser data);

    PSysUser login(PSysUser data);

    List<PSysUser> listUserByOrgId(@Param("orgId") String orgId);

    List<PSysUser> listUserByOrgIdWithLevel(@Param("orgId") String orgId);

    List<PSysUser> listUserByOrgIdAandOther(@Param("orgId") String orgId);

    List<PSysUser> selectUserOnOrg(Map<String,Object> map);

    /**
     * @Description //TODO  查询当前组织或组织下的用户
     * @author xjj
     * @date 2020/2/11
     * @param  * @param null
     * @return
     */
    List<PSysUser> selectUserOnOrg1(@Param("leves") String leves);

    /**
     * @Description //TODO
     * @author xjj
     * @date 2020/2/16
     * @param  * @param null
     * @return
     */
    List<Map> listThirdUser(Map<String,String> map);
}