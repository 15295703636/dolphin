package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;

import java.util.List;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer org_id);

    int insert(OrganizationEntity record);

    int insertSelective(OrganizationEntity record);

    int selectByCusIdCou(Integer customer_id);

    OrganizationEntity selectByPrimaryKey(Integer org_id);

    int updateByPrimaryKeySelective(OrganizationEntity record);

    int updateByPrimaryKey(OrganizationEntity record);

    List<OrganizationEntity> getList(OrganizationEntity param);
}