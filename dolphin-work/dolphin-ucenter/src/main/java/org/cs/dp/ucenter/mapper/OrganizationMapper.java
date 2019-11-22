package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.ucenter.domain.OrganizationEntity;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer org_id);

    int insert(OrganizationEntity record);

    int insertSelective(OrganizationEntity record);

    OrganizationEntity selectByPrimaryKey(Integer org_id);

    int updateByPrimaryKeySelective(OrganizationEntity record);

    int updateByPrimaryKey(OrganizationEntity record);

    List<OrganizationEntity> getList(OrganizationEntity param);
}