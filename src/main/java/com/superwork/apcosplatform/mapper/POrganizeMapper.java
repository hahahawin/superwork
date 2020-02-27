package com.superwork.apcosplatform.mapper;

import com.superwork.apcosplatform.domain.POrganize;
import com.superwork.apcosplatform.domain.POrganizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface POrganizeMapper {
    long countByExample(POrganizeExample example);

    int deleteByExample(POrganizeExample example);

    int deleteByPrimaryKey(String id);

    int insert(POrganize record);

    int insertSelective(POrganize record);

    List<POrganize> selectByExample(POrganizeExample example);

    POrganize selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") POrganize record, @Param("example") POrganizeExample example);

    int updateByExample(@Param("record") POrganize record, @Param("example") POrganizeExample example);

    int updateByPrimaryKeySelective(POrganize record);

    int updateByPrimaryKey(POrganize record);

    List<POrganize> listOrganize(POrganize data);

    /**
     * @Description //TODO 查询当前组织级组织下的
     * @author xjj
     * @date 2020/2/20
     * @param  * @param null
     * @return
     */
    List<POrganize> listOrganizeOnUser(@Param("leves") String leves);

    String selectAutoId();
}